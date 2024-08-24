package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.UserConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.mapper.UserMapper;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.USER_NAME_EXIST;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

  /**
   * 用户注册
   *
   * @param registerParam 注册参数
   * @return 注册结果
   */
  public boolean register(RegisterParam registerParam) {
    User user = UserConvertor.INSTANCE.regParamToPo(registerParam);
    // 生成盐值
    user.setPasswordSalt(IdUtil.simpleUUID());
    // 密码加密
    String md5Hex = DigestUtil.md5Hex(user.getPassword() + user.getPasswordSalt());
    user.setPassword(md5Hex);
    // 检查用户名是否已存在
    boolean result;
    synchronized (this) {
      List<User> users = queryByUserName(user.getUserName());
      if (CollUtil.isNotEmpty(users)) {
        // 用户名已存在
        throw SystemException.build(USER_NAME_EXIST);
      }
      // 保存用户
      result = save(user);
    }
    return result;
  }

  /**
   * 根据用户名查询用户是否存在
   *
   * @param userName 用户名
   * @return 是否存在
   */
  private List<User> queryByUserName(String userName) {
    QueryWrapper queryWrapper = this.query()
        .select(User::getId, User::getUserName, User::getPassword, User::getPasswordSalt)
        .eq(User::getUserName, userName);
    return this.list(queryWrapper);
  }

}
