package group.mytool.backend.common.user.service;

import group.mytool.backend.FlutterFlexBackendApplicationTests;
import group.mytool.backend.common.user.entity.req.LoginParam;
import group.mytool.backend.common.user.entity.req.RegisterParam;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.backend.core.exception.EnumGlobalError;
import group.mytool.backend.core.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

import static group.mytool.backend.common.user.controller.MemberControllerTest.CLIENT_ID;
import static group.mytool.backend.common.user.controller.MemberControllerTest.PASSWORD;
import static group.mytool.backend.common.user.controller.MemberControllerTest.USERNAME;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
class MemberServiceTest extends FlutterFlexBackendApplicationTests {

  public static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

  @Autowired
  private MemberService memberService;
  @Autowired
  private UserService userService;

  @Order(1)
  @Test
  void register() {
    RegisterParam registerParam = new RegisterParam();
    registerParam.setUsername(USERNAME);
    registerParam.setPassword(PASSWORD);

    userService.deleteByUserNamePhysical(registerParam.getUsername());

    boolean register = memberService.register(registerParam);
    Assertions.assertTrue(register);

    Executable executable = () -> memberService.register(registerParam);
    String message = EnumGlobalError.USER_NAME_EXIST.getMessage();
    Assertions.assertThrowsExactly(SystemException.class, executable, message);

  }

  @Order(2)
  @Test
  void login() {
    LoginParam loginParam = new LoginParam();
    loginParam.setUsername(USERNAME);
    loginParam.setPassword(PASSWORD);
    loginParam.setClientId(CLIENT_ID);
    LoginTokenVo loginToken = memberService.login(loginParam);
    logger.debug("loginToken: {}", loginToken);
    Assertions.assertTrue(Objects.nonNull(loginToken));
    Assertions.assertTrue(Objects.nonNull(loginToken.getToken()));
    Assertions.assertTrue(loginToken.getExpireTime().isAfter(LocalDateTime.now()));
  }

}