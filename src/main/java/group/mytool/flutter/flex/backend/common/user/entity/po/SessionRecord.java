package group.mytool.flutter.flex.backend.common.user.entity.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table("common_session_record")
public class SessionRecord extends BaseEntity {

    @Schema(description = "用户ID")
    @Column(value = "user_id")
    private String userId;

    @Schema(description = "登录时间")
    @Column(value = "login_time")
    private LocalDateTime loginTime;

    @Schema(description = "最近访问时间")
    @Column(value = "fresh_time")
    private LocalDateTime freshTime;

    @Schema(description = "过期时间")
    @Column(value = "expire_time")
    private Long expireTime;

}
