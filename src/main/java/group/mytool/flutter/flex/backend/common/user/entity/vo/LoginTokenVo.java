package group.mytool.flutter.flex.backend.common.user.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class LoginTokenVo {

  /** token */
  private String token;

  /** 过期时间 */
  private LocalDateTime expireTime;

}
