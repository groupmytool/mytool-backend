package group.mytool.backend.common.user.controller;

import group.mytool.backend.common.BaseValidateTest;
import group.mytool.backend.common.user.client.MemberControllerClient;
import group.mytool.backend.common.user.dao.UserDao;
import group.mytool.backend.common.user.entity.po.User;
import group.mytool.backend.common.user.entity.req.LoginParam;
import group.mytool.backend.common.user.entity.req.RegisterParam;
import group.mytool.backend.common.user.entity.util.validator.ValidPassword;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.vo.Val;
import group.mytool.backend.core.util.IdUtil;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Set;

import static group.mytool.backend.core.exception.EnumGlobalError.SUCCESS;
import static group.mytool.backend.core.exception.EnumGlobalError.USER_NAME_OBTAIN;
import static group.mytool.backend.core.util.Constant.USER_OBTAIN;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Configuration
public class MemberControllerTest extends BaseValidateTest {

  public static final String USERNAME = "test";
  public static final String PASSWORD = "Test!@#1234";
  public static final String CLIENT_ID = IdUtil.simpleUUID();

  @Autowired
  private MemberControllerClient memberControllerClient;
  @Autowired
  private UserDao userDao;

  @Test
  void register() {
    RegisterParam registerParam = new RegisterParam();
    // 环境准备
    registerParam.setUsername(USERNAME);
    registerParam.setPassword(PASSWORD);
    User user = userDao.selectByUsername(registerParam.getUsername());
    if (Objects.nonNull(user)) {
      int cnt = userDao.deleteByIdPhysical(user.getId());
      Assertions.assertTrue(cnt > 0);
    }
    // - username is null
    registerParam.setUsername(null);
    Result<Val> register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(RegisterParam.usernameNotNullMessage, register.getMessage());
    // - username is obtained
    registerParam.setUsername(USER_OBTAIN.getFirst());
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(USER_NAME_OBTAIN.getMessage(), register.getMessage());
    // - username too short
    registerParam.setUsername("ts");
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(RegisterParam.usernameSizeMessage, register.getMessage());

    // - password is null
    registerParam.setUsername(USERNAME);
    registerParam.setPassword(null);
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(RegisterParam.passwordNotNullMessage, register.getMessage());
    // - password too short
    registerParam.setPassword("T");
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(RegisterParam.passwordSizeMessage, register.getMessage());
    // - password not match pattern: not contain upper case character
    registerParam.setPassword("test1234");
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(ValidPassword.defaultMessage, register.getMessage());
    // - password not match pattern: not contain lower case character
    registerParam.setPassword("TEST1234");
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(ValidPassword.defaultMessage, register.getMessage());
    // - password not match pattern: not contain number
    registerParam.setPassword("TestTest");
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(ValidPassword.defaultMessage, register.getMessage());
    // - password match pattern: could contain special character
    registerParam.setPassword(PASSWORD);
    Set<ConstraintViolation<RegisterParam>> violations = validate(registerParam);
    Assertions.assertTrue(violations.isEmpty());
    register = memberControllerClient.register(registerParam);
    Assertions.assertEquals(SUCCESS.getCode(), register.getCode());
  }

  @Test
  void login() {
    // 参数准备
    LoginParam loginParam = new LoginParam();
    loginParam.setClientId(IdUtil.simpleUUID());
    loginParam.setUsername(USERNAME);
    loginParam.setPassword(PASSWORD);
    // username is null
    loginParam.setUsername(null);
    Result<LoginTokenVo> result = memberControllerClient.login(loginParam);
    Assertions.assertEquals(LoginParam.usernameNotNullMessage, result.getMessage());
    loginParam.setUsername(USERNAME);
    // password is null
    loginParam.setPassword(null);
    result = memberControllerClient.login(loginParam);
    Assertions.assertEquals(LoginParam.passwordNotNullMessage, result.getMessage());
    loginParam.setPassword(PASSWORD);
    // clientId is null
    loginParam.setClientId(null);
    result = memberControllerClient.login(loginParam);
    Assertions.assertEquals(LoginParam.clientIdNotNullMessage, result.getMessage());
    loginParam.setClientId(IdUtil.simpleUUID());
    Set<ConstraintViolation<LoginParam>> violations = validate(loginParam);
    Assertions.assertTrue(violations.isEmpty());
    result = memberControllerClient.login(loginParam);
    Assertions.assertEquals(SUCCESS.getCode(), result.getCode());
  }


}