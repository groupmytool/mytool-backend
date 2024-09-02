package group.mytool.backend.common.user.entity.req;

import group.mytool.backend.core.util.validator.ValidPassword;
import group.mytool.backend.core.util.validator.ValidUsername;
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
  @ValidUsername
  @Size(min = 3, max = 30, message = usernameSizeMessage)
  @NotNull(message = usernameNotNullMessage)
  private String username;

  /** 密码 */
  @ValidPassword
  @Size(min = 8, max = 32, message = passwordSizeMessage)
  @NotNull(message = passwordNotNullMessage)
  private String password;

}
