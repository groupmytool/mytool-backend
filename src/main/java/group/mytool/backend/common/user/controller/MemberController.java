package group.mytool.backend.common.user.controller;

import group.mytool.backend.common.user.client.MemberControllerInterface;
import group.mytool.backend.common.user.entity.ro.LoginParam;
import group.mytool.backend.common.user.entity.ro.RegisterParam;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.backend.common.user.service.MemberService;
import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.vo.Val;
import group.mytool.backend.core.exception.SystemException;
import group.mytool.backend.core.util.validate.ValidationSequence;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static group.mytool.backend.core.exception.EnumGlobalError.USER_NAME_OBTAIN;
import static group.mytool.backend.core.util.Constant.MEMBER_CONTROLLER;
import static group.mytool.backend.core.util.Constant.USER_OBTAIN;

/**
 * 用户登录、注册等session校验放行接口
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(MEMBER_CONTROLLER)
public class MemberController implements MemberControllerInterface {

  private final MemberService service;

  @Override
  public Result<Val> register(@RequestBody @Validated(ValidationSequence.class) RegisterParam registerParam) {
    // 不允许注册保留账号
    if (USER_OBTAIN.contains(registerParam.getUsername())) {
      throw SystemException.build(USER_NAME_OBTAIN);
    }
    return Result.ok(service.register(registerParam));
  }

  @Override
  public Result<LoginTokenVo> login(@RequestBody @Valid LoginParam loginParam) {
    return Result.ok(service.login(loginParam));
  }

}
