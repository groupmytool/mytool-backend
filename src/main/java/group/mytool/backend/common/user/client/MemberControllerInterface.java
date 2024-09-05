package group.mytool.backend.common.user.client;

import group.mytool.backend.common.user.entity.ro.LoginParam;
import group.mytool.backend.common.user.entity.ro.RegisterParam;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.vo.Val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public interface MemberControllerInterface {

  @PostMapping("/register")
  Result<Val> register(@RequestBody RegisterParam registerParam);

  @PostMapping("/login")
  Result<LoginTokenVo> login(@RequestBody LoginParam loginParam);

}
