package group.mytool.flutter.flex.backend.food.material.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class ChildMaterialGroupQuery {

  /** 父节点分组ID */
  @NotNull(message = "父节点分组ID不能为空")
  private String parentId;

}
