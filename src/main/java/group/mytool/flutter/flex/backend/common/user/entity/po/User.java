package group.mytool.flutter.flex.backend.common.user.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "common_user")
public class User extends BaseEntity {

  @Schema(description = "用户名")
  @Column(name = "username")
  private String username;

  @Schema(description = "昵称")
  @Column(name = "nikename")
  private String nickname;

  @Schema(description = "超级会员")
  @Column(name = "svip")
  private Boolean svip;

  @Schema(description = "简介")
  @Column(name = "bio")
  private String bio;

  @Schema(description = "头像")
  @Column(name = "avatar")
  private String avatar;

  @Schema(description = "密码")
  @Column(name = "password")
  private String password;

  @Schema(description = "密码盐")
  @Column(name = "pswd_salt")
  private String pswdSalt;

  @Schema(description = "邮箱")
  @Column(name = "email")
  private String email;

}
