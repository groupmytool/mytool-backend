package group.mytool.flutter.flex.backend.common.user.entity.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Table("common_user")
public class User extends BaseEntity {

    @Schema(description = "用户名")
    @Column(value = "user_name")
    private String userName;

    @Schema(description = "昵称")
    @Column(value = "nike_name")
    private String nickName;

    @Schema(description = "简介")
    @Column(value = "bio")
    private String bio;

    @Schema(description = "头像")
    @Column(value = "avatar")
    private String avatar;

    @Schema(description = "密码")
    @Column(value = "password")
    private String password;

    @Schema(description = "密码盐")
    @Column(value = "pswd_salt")
    private String passwordSalt;

    @Schema(description = "邮箱")
    @Column(value = "email")
    private String email;

}
