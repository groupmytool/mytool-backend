package group.mytool.flutter.flex.backend.common.user.entity.req;

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

  /** 客户端标记ID */
  @NotNull(message = clientIdNotNullMessage)
  private String clientId;

  /** 用户名 */
  @NotNull(message = usernameNotNullMessage)
  private String username;

  /** 密码 */
  @NotNull(message = passwordNotNullMessage)
  private String password;

}
