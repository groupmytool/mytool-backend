package group.mytool.flutter.flex.backend.global.session.entity;

import com.mybatisflex.annotation.Table;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Table("session_record")
public class SessionRecord extends BaseEntity {

  @Schema(description = "用户ID")
  private String userId;

  @Schema(description = "登录时间")
  private Date loginTime;

  @Schema(description = "最近访问时间")
  private Date freshTime;

}
