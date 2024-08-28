package group.mytool.flutter.flex.backend.common.user.service;

import group.mytool.flutter.flex.backend.common.user.dao.UserDao;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.UserConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.dto.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.dto.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import group.mytool.flutter.flex.backend.core.util.IdUtil;
import group.mytool.flutter.flex.backend.core.util.Md5Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.LOGIN_ACCOUNT_ERROR;
import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.USER_NAME_EXIST;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
@RequiredArgsConstructor
public class MemberService implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

  private final UserConvertor convertor;
  private final UserDao userDao;
  private final SessionRecordService sessionRecordService;

  /**
   * 用户注册
   *
   * @param registerParam 注册参数
   * @return 注册结果
   */
  public boolean register(RegisterParam registerParam) {
    User po = convertor.regParamToPo(registerParam);
    // 生成盐值
    po.setPswdSalt(IdUtil.simpleUUID());
    // 密码加密
    String md5Hex = Md5Util.md5(po.getPassword() + po.getPswdSalt());
    po.setPassword(md5Hex);
    // 检查用户名是否已存在
    synchronized (MemberService.class) {
      User user = userDao.selectByUsername(registerParam.getUsername());
      if (Objects.nonNull(user)) {
        // 用户名已存在
        throw SystemException.build(USER_NAME_EXIST);
      }
      // 保存用户
      po.setSvip(false);
      userDao.save(po);
    }
    return true;
  }

  /**
   * 用户登录
   *
   * @param loginParam
   * @return
   */
  public LoginTokenVo login(LoginParam loginParam) {
    User user = userDao.selectByUsername(loginParam.getUsername());
    if (Objects.isNull(user)) {
      // 用户不存在
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    String md5Hex = Md5Util.md5(loginParam.getPassword() + user.getPswdSalt());
    if (!md5Hex.equals(user.getPassword())) {
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    // 生成token
    LoginTokenVo loginToken = sessionRecordService.createLoginToken(loginParam.getClientId(), user.getId());
    logger.debug("{} login success: {}", user.getUsername(), loginToken);
    return loginToken;
  }

}
