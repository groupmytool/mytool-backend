package group.mytool.flutter.flex.backend.common.user.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_session_record")
public class SessionRecord extends BaseEntity {

  /** 客户端标记ID */
  @TableField("client_id")
  private String clientId;

  /** 用户ID */
  @TableField("user_id")
  private String userId;

  /** 登陆时间 */
  @TableField("login_time")
  private LocalDateTime loginTime;

  /** 最近访问时间 */
  @TableField("fresh_time")
  private LocalDateTime freshTime;

  /** 过期时间 */
  @TableField("expire_time")
  private LocalDateTime expireTime;

  @TableField("deleted")
  private Boolean deleted;

}
