package group.mytool.flutter.flex.backend.common.user.entity.req;

import group.mytool.flutter.flex.backend.core.util.validator.ValidPassword;
import group.mytool.flutter.flex.backend.core.util.validator.ValidUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class RegisterParam {

    public static final String userNameNotNullMessage = "用户名不能为空";
    public static final String userNameSizeMessage = "用户名长度必须大于3";
    public static final String passwordNotNullMessage = "密码不能为空";
    public static final String passwordSizeMessage = "密码长度必须大于8";

    @ValidUsername
    @Size(min = 3, message = userNameSizeMessage)
    @NotNull(message = userNameNotNullMessage)
    @Schema(description = "用户名")
    private String userName;

    @ValidPassword
    @Size(min = 8, message = passwordSizeMessage)
    @NotNull(message = passwordNotNullMessage)
    @Schema(description = "密码")
    private String password;

}
