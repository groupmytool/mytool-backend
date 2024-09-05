package group.mytool.backend.common.user.entity.ro;

import group.mytool.backend.common.user.entity.util.validator.ValidPassword;
import group.mytool.backend.common.user.entity.util.validator.ValidUsername;
import group.mytool.backend.core.util.validate.OrderFirst;
import group.mytool.backend.core.util.validate.OrderSecond;
import group.mytool.backend.core.util.validate.OrderThird;
import group.mytool.backend.core.util.validate.ValidationSequence;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@Validated(ValidationSequence.class)
public class RegisterParam {

  public static final String usernameNotNullMessage = "用户名不能为空";
  public static final String usernameSizeMessage = "用户名长度必须3到30位";
  public static final String passwordNotNullMessage = "密码不能为空";
  public static final String passwordSizeMessage = "密码长度必须8到30位";

  /** 用户名 */
  @NotNull(message = usernameNotNullMessage, groups = {OrderFirst.class})
  @Size(min = 3, max = 30, message = usernameSizeMessage, groups = {OrderSecond.class})
  @ValidUsername(groups = {OrderThird.class})
  private String username;

  /** 密码 */
  @NotNull(message = passwordNotNullMessage, groups = {OrderFirst.class})
  @Size(min = 8, max = 32, message = passwordSizeMessage, groups = {OrderSecond.class})
  @ValidPassword(groups = {OrderThird.class})
  private String password;

}
