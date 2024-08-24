package group.mytool.flutter.flex.backend.common.user.controller;

import group.mytool.flutter.flex.backend.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户登录、注册等session校验放行接口
 *
 * @author adolphor <0haizhu0@gmail.com>
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

}
