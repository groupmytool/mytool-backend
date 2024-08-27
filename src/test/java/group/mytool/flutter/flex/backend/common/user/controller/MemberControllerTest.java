package group.mytool.flutter.flex.backend.common.user.controller;

import group.mytool.flutter.flex.backend.common.BaseValidateTest;
import group.mytool.flutter.flex.backend.common.user.entity.req.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.core.util.IdUtil;
import group.mytool.flutter.flex.backend.core.util.validator.ValidPassword;
import group.mytool.flutter.flex.backend.core.util.validator.ValidUsername;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@SpringBootTest
public class MemberControllerTest extends BaseValidateTest {

  public static final String USERNAME = "mytool";
  public static final String PASSWORD = "MyTool@123";
  public static final String CLIENT_ID = IdUtil.simpleUUID();

  @Test
  void validateRegisterParam() {
    RegisterParam registerParam = new RegisterParam();
    // validate username
    registerParam.setPassword(PASSWORD);
    // - username is null
    String message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(RegisterParam.usernameNotNullMessage));
    Assertions.assertTrue(message.contains(ValidUsername.defaultMessage));
    // - username too short
    registerParam.setUsername("ts");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(RegisterParam.usernameSizeMessage));
    // - username not match pattern: not start with number
    registerParam.setUsername("123test");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(ValidUsername.defaultMessage));
    // - username not match pattern: not start with upper case character
    registerParam.setUsername("Test123");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(ValidUsername.defaultMessage));

    // validate username
    registerParam.setUsername(USERNAME);
    registerParam.setPassword(null);
    // - password is null
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(RegisterParam.passwordNotNullMessage));
    Assertions.assertTrue(message.contains(ValidPassword.defaultMessage));
    // - password too short
    registerParam.setPassword("T");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(RegisterParam.passwordSizeMessage));
    // - password not match pattern: not contain upper case character
    registerParam.setPassword("test1234");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(ValidPassword.defaultMessage));
    // - password not match pattern: not contain lower case character
    registerParam.setPassword("TEST1234");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(ValidPassword.defaultMessage));
    // - password not match pattern: not contain number
    registerParam.setPassword("TestTest");
    message = this.validateMessage(registerParam);
    Assertions.assertTrue(message.contains(ValidPassword.defaultMessage));
    // - password match pattern: could contain special character
    registerParam.setPassword("Test!@#1234");
    Set<ConstraintViolation<RegisterParam>> violations = this.validate(registerParam);
    Assertions.assertTrue(violations.isEmpty());

    // validate success
    registerParam.setUsername(USERNAME);
    registerParam.setPassword(PASSWORD);
    violations = this.validate(registerParam);
    Assertions.assertTrue(violations.isEmpty());

  }

  @Test
  void validateLoginParam() {
    LoginParam loginParam = new LoginParam();
    String message = this.validateMessage(loginParam);
    Assertions.assertTrue(message.contains(LoginParam.clientIdNotNullMessage));
    Assertions.assertTrue(message.contains(LoginParam.usernameNotNullMessage));
    Assertions.assertTrue(message.contains(LoginParam.passwordNotNullMessage));
  }


}