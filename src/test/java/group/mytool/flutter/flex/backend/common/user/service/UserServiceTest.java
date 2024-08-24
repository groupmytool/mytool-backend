package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.query.QueryWrapper;
import group.mytool.flutter.flex.backend.FlutterFlexBackendApplicationTests;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.core.exception.EnumGlobalError;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Slf4j
class UserServiceTest extends FlutterFlexBackendApplicationTests {

  @Autowired
  private UserService userService;

  @Order(1)
  @Test
  void register() {
    RegisterParam registerParam = new RegisterParam();
    registerParam.setUsername("test");
    registerParam.setPassword("Test@123");

    QueryWrapper queryWrapper = userService.query()
        .eq(User::getUsername, registerParam.getUsername());
    Runnable runnable = () -> userService.remove(queryWrapper);
    LogicDeleteManager.execWithoutLogicDelete(runnable);

    boolean register = userService.register(registerParam);
    Assertions.assertTrue(register);

    Executable executable = () -> userService.register(registerParam);
    String message = EnumGlobalError.USER_NAME_EXIST.getMessage();
    Assertions.assertThrowsExactly(SystemException.class, executable, message);

  }

  @Order(2)
  @Test
  void login() {
    LoginParam loginParam = new LoginParam();
    loginParam.setUsername("test");
    loginParam.setPassword("Test@123");
    loginParam.setClientId(IdUtil.simpleUUID());
    LoginTokenVo loginToken = userService.login(loginParam);
    log.debug("loginToken: {}", loginToken);
    Assertions.assertTrue(Objects.nonNull(loginToken));
    Assertions.assertTrue(Objects.nonNull(loginToken.getToken()));
    Assertions.assertTrue(loginToken.getExpireTime().isAfter(LocalDateTime.now()));
  }

}