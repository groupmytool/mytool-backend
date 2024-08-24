package group.mytool.flutter.flex.backend.common.user.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class LoginTokenVo {

  private String token;
  private LocalDateTime expireTime;

}
