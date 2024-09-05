package group.mytool.backend.common.user.entity.ro;

import group.mytool.backend.common.user.entity.util.validator.ValidPassword;
import group.mytool.backend.common.user.entity.util.validator.ValidUsername;
import group.mytool.backend.core.util.validate.Order1;
import group.mytool.backend.core.util.validate.Order2;
import group.mytool.backend.core.util.validate.Order3;
import group.mytool.backend.core.util.validate.Order4;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class RegisterParam {

  public static final String usernameNotNullMessage = "用户名不能为空";
  public static final String usernameSizeMessage = "用户名长度必须3到30位";
  public static final String passwordNotNullMessage = "密码不能为空";
  public static final String passwordSizeMessage = "密码长度必须8到30位";

  /** 用户名 */
  @NotNull(message = usernameNotNullMessage, groups = {Order1.class})
  @Size(min = 3, max = 30, message = usernameSizeMessage, groups = {Order2.class})
  @ValidUsername(groups = {Order3.class})
  private String username;

  /** 密码 */
  @NotNull(message = passwordNotNullMessage, groups = {Order2.class})
  @Size(min = 8, max = 32, message = passwordSizeMessage, groups = {Order3.class})
  @ValidPassword(groups = {Order4.class})
  private String password;

}
