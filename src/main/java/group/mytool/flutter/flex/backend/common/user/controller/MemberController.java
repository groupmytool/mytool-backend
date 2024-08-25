package group.mytool.flutter.flex.backend.common.user.controller;

import group.mytool.flutter.flex.backend.common.user.entity.req.LoginParam;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.common.user.service.MemberService;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.USER_NAME_OBTAIN;
import static group.mytool.flutter.flex.backend.core.util.Constant.MEMBER_CONTROLLER;
import static group.mytool.flutter.flex.backend.core.util.Constant.USER_OBTAIN;

/**
 * 用户登录、注册等session校验放行接口
 *
 * @author adolphor <0haizhu0@gmail.com>
 */
@RestController
@RequestMapping(MEMBER_CONTROLLER)
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping("/register")
  public String register(@RequestBody @Validated RegisterParam registerParam) {
    // 不允许注册保留账号
    if (USER_OBTAIN.contains(registerParam.getUsername())) {
      throw SystemException.build(USER_NAME_OBTAIN);
    }
    return memberService.register(registerParam);
  }

  @PostMapping("/login")
  public LoginTokenVo login(@RequestBody @Validated LoginParam loginParam) {
    return memberService.login(loginParam);
  }

}
