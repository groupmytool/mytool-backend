package group.mytool.backend.core.entity.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class IdQuery {

  public static final String idNotNullMessage = "ID不能为空";

  @NotNull(message = idNotNullMessage)
  private String id;

}
