package group.mytool.backend.food.material.entity.ro;

import group.mytool.backend.core.util.validate.Order1;
import group.mytool.backend.core.util.validate.Order2;
import group.mytool.backend.core.util.validate.Order3;
import group.mytool.backend.food.material.entity.po.MaterialGroup;
import jakarta.validation.constraints.Max;
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

  public static final String parentIdSizeMessage = "父节点ID过长";
  public static final String nameNotNullMessage = "分组名称不能为空";
  public static final String nameSizeMessage = "名称过长";
  public static final String sortMaxMessage = "排序最大值99";

  /** 父节点ID */
  @Size(max = 32, message = parentIdSizeMessage, groups = {Order1.class})
  private String parentId;

  /** 分组名称 */
  @NotNull(message = nameNotNullMessage, groups = {Order2.class})
  @Size(max = 10, message = nameSizeMessage, groups = {Order3.class})
  private String name;

  /** 排序 */
  @Max(value = 99, message = sortMaxMessage, groups = {Order3.class})
  private Integer sort;
}
