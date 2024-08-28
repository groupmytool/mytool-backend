package group.mytool.flutter.flex.backend.common.user.entity.dto;

import group.mytool.flutter.flex.backend.core.util.validator.ValidPassword;
import group.mytool.flutter.flex.backend.core.util.validator.ValidUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class RegisterParam {

  public static final String usernameNotNullMessage = "用户名不能为空";
  public static final String usernameSizeMessage = "用户名长度必须大于3";
  public static final String passwordNotNullMessage = "密码不能为空";
  public static final String passwordSizeMessage = "密码长度必须大于8";

  /** 用户名 */
  @ValidUsername
  @Size(min = 3, message = usernameSizeMessage)
  @NotNull(message = usernameNotNullMessage)
  private String username;

  /** 密码 */
  @ValidPassword
  @Size(min = 8, message = passwordSizeMessage)
  @NotNull(message = passwordNotNullMessage)
  private String password;

}
