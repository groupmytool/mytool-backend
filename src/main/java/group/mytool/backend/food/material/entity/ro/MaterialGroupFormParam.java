package group.mytool.backend.food.material.entity.ro;

import group.mytool.backend.food.material.entity.po.MaterialGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialGroupFormParam extends MaterialGroup {

  @Size(min = 1, max = 32, message = "分组名称过长")
  @NotNull(message = "分组名称不能为空")
  private String name;

}
