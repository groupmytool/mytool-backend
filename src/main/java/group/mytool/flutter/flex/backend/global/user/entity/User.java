package group.mytool.flutter.flex.backend.global.user.entity;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseEntity {

    /** 昵称 */
    private String nickName;
    /** 登录名 */
    private String userName;
    /** 手机号码 */
    private String phone;
    /** 生日 */
    private Date birthday;
    /** 简介 */
    private String bio;
    /** 头像 */
    private String avatar;
    /** 密码 */
    private String password;
    /** 是否是管理员 */
    private Integer manager;

}
