package group.mytool.flutter.flex.backend.common.user.service;

import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.query.QueryWrapper;
import group.mytool.flutter.flex.backend.FlutterFlexBackendApplicationTests;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.core.exception.EnumGlobalError;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
class UserServiceTest extends FlutterFlexBackendApplicationTests {

  @Autowired
  private UserService userService;

  @Test
  void register() {

    RegisterParam registerParam = new RegisterParam();
    registerParam.setUserName("test");
    registerParam.setPassword("Test@123");

    QueryWrapper queryWrapper = userService.query()
        .eq(User::getUserName, registerParam.getUserName());
    Runnable runnable = () -> userService.remove(queryWrapper);
    LogicDeleteManager.execWithoutLogicDelete(runnable);

    boolean register = userService.register(registerParam);
    Assertions.assertTrue(register);

    Executable executable = () -> userService.register(registerParam);
    String message = EnumGlobalError.USER_NAME_EXIST.getMessage();
    Assertions.assertThrowsExactly(SystemException.class, executable, message);

  }

}