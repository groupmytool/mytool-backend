package group.mytool.backend.common.user.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import group.mytool.backend.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_user")
public class User extends BaseEntity {

  /** 用户名 */
  @TableField("username")
  private String username;

  /** 昵称 */
  @TableField("nickname")
  private String nickname;

  /** 超级会员 */
  @TableField("svip")
  private Boolean svip;

  /** 简介 */
  @TableField("bio")
  private String bio;

  /** 头像 */
  @TableField("avatar")
  private String avatar;

  /** 密码 */
  @TableField("password")
  private String password;

  /** 密码盐 */
  @TableField("pswd_salt")
  private String pswdSalt;

  /** 邮箱 */
  @TableField("email")
  private String email;

}
