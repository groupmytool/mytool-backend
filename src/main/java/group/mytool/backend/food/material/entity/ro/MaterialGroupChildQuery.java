package group.mytool.backend.food.material.entity.ro;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupChildQuery {

  public static final String parentIdNotNullMessage = "父节点分组ID不能为空";

  /** 父节点分组ID */
  @NotNull(message = parentIdNotNullMessage)
  private String parentId;

}
