package group.mytool.backend.common.user.controller;

import group.mytool.backend.common.user.client.UserControllerInterface;
import group.mytool.backend.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户登录、注册等session校验放行接口
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerInterface {

  private final UserService service;

}
