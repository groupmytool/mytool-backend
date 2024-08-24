package group.mytool.flutter.flex.backend.common.user.entity.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class LoginParam {

  public static final String usernameNotNullMessage = "用户名不能为空";
  public static final String passwordNotNullMessage = "密码不能为空";
  public static final String clientIdNotNullMessage = "客户端标记ID不能为空";

  @NotNull(message = clientIdNotNullMessage)
  @Schema(description = "客户端标记ID")
  private String clientId;

  @NotNull(message = usernameNotNullMessage)
  @Schema(description = "用户名")
  private String username;

  @NotNull(message = passwordNotNullMessage)
  @Schema(description = "密码")
  private String password;

}
