package group.mytool.flutter.flex.backend.common.user.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "common_user")
public class User extends BaseEntity {

  /** 用户名 */
  @Column(name = "username")
  private String username;

  /** 昵称 */
  @Column(name = "nikename")
  private String nickname;

  /** 超级会员 */
  @Column(name = "svip")
  private Boolean svip;

  /** 简介 */
  @Column(name = "bio")
  private String bio;

  /** 头像 */
  @Column(name = "avatar")
  private String avatar;

  /** 密码 */
  @Column(name = "password")
  private String password;

  /** 密码盐 */
  @Column(name = "pswd_salt")
  private String pswdSalt;

  /** 邮箱 */
  @Column(name = "email")
  private String email;

}
