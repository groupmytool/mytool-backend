package group.mytool.flutter.flex.backend.common.user.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "common_session_record")
public class SessionRecord extends BaseEntity {

  @Schema(description = "客户端标记ID")
  @Column(name = "client_id")
  private String clientId;

  @Schema(description = "用户ID")
  @Column(name = "user_id")
  private String userId;

  @Schema(description = "登录时间")
  @Column(name = "login_time")
  private LocalDateTime loginTime;

  @Schema(description = "最近访问时间")
  @Column(name = "fresh_time")
  private LocalDateTime freshTime;

  @Schema(description = "过期时间")
  @Column(name = "expire_time")
  private LocalDateTime expireTime;

}
