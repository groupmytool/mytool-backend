package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.UserConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.common.user.mapper.UserMapper;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.LOGIN_ACCOUNT_ERROR;
import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.USER_NAME_EXIST;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> {

  private final SessionRecordService sessionRecordService;

  /**
   * 用户注册
   *
   * @param registerParam 注册参数
   * @return 注册结果
   */
  public boolean register(RegisterParam registerParam) {
    User po = UserConvertor.INSTANCE.regParamToPo(registerParam);
    // 生成盐值
    po.setPasswordSalt(IdUtil.simpleUUID());
    // 密码加密
    String md5Hex = DigestUtil.md5Hex(po.getPassword() + po.getPasswordSalt());
    po.setPassword(md5Hex);
    // 检查用户名是否已存在
    boolean result;
    synchronized (UserService.class) {
      User user = queryByUserName(po.getUsername());
      if (Objects.nonNull(user)) {
        // 用户名已存在
        throw SystemException.build(USER_NAME_EXIST);
      }
      // 保存用户
      result = save(po);
    }
    return result;
  }

  /**
   * 用户登录
   *
   * @param loginParam
   * @return
   */
  public LoginTokenVo login(LoginParam loginParam) {
    User user = this.queryByUserName(loginParam.getUsername());
    if (Objects.isNull(user)) {
      // 用户不存在
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    String md5Hex = DigestUtil.md5Hex(loginParam.getPassword() + user.getPasswordSalt());
    if (!md5Hex.equals(user.getPassword())) {
      // 密码错误
      log.error("用户登录失败，用户名：{}，输入密码：{}, 数据库密码：{}",
          loginParam.getUsername(),
          md5Hex,
          user.getPassword());
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    // 生成token
    return sessionRecordService.createLoginToken(loginParam.getClientId(), user.getId());
  }

  /**
   * 根据用户名查询用户是否存在
   *
   * @param username 用户名
   * @return 用户对象
   */
  private User queryByUserName(String username) {
    QueryWrapper queryWrapper = this.query()
        .select(User::getId, User::getUsername, User::getPassword, User::getPasswordSalt)
        .eq(User::getUsername, username);
    return this.getOne(queryWrapper);
  }

}
