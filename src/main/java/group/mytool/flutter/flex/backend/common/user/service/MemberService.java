package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.UserConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.common.user.mapper.UserMapper;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.LOGIN_ACCOUNT_ERROR;
import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.USER_NAME_EXIST;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class MemberService {

  public static final Log logger = LogFactory.get();

  private final UserConvertor convertor;
  private final UserMapper mapper;
  private final SessionRecordService sessionRecordService;

  public MemberService(UserConvertor convertor,
                       UserMapper userMapper,
                       SessionRecordService sessionRecordService) {
    this.convertor = convertor;
    this.mapper = userMapper;
    this.sessionRecordService = sessionRecordService;
  }

  /**
   * 用户注册
   *
   * @param registerParam 注册参数
   * @return 注册结果
   */
  public String register(RegisterParam registerParam) {
    User po = convertor.regParamToPo(registerParam);
    // 生成盐值
    po.setPswdSalt(IdUtil.simpleUUID());
    // 密码加密
    String md5Hex = DigestUtil.md5Hex(po.getPassword() + po.getPswdSalt());
    po.setPassword(md5Hex);
    // 检查用户名是否已存在
    synchronized (MemberService.class) {
      User user = mapper.selectByUsername(po.getUsername());
      if (Objects.nonNull(user)) {
        // 用户名已存在
        throw SystemException.build(USER_NAME_EXIST);
      }
      // 保存用户
      po.setId(IdUtil.simpleUUID());
      po.setSvip(false);
      mapper.register(po);
    }
    return po.getId();
  }

  /**
   * 用户登录
   *
   * @param loginParam
   * @return
   */
  public LoginTokenVo login(LoginParam loginParam) {
    User user = mapper.selectByUsername(loginParam.getUsername());
    if (Objects.isNull(user)) {
      // 用户不存在
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    String md5Hex = DigestUtil.md5Hex(loginParam.getPassword() + user.getPswdSalt());
    if (!md5Hex.equals(user.getPassword())) {
      throw SystemException.build(LOGIN_ACCOUNT_ERROR);
    }
    // 生成token
    LoginTokenVo loginToken = sessionRecordService.createLoginToken(loginParam.getClientId(), user.getId());
    logger.debug("{} login success: {}", user.getUsername(), loginToken);
    return loginToken;
  }

}
