package group.mytool.flutter.flex.backend.food.material.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
@Table(name = "food_material_group")
public class MaterialGroup extends BaseEntity {

  @Schema(description = "父节点ID")
  @Column(name = "parent_id")
  private String parentId;

  @Schema(description = "名称")
  @Column(name = "group_name")
  private String name;

  @Schema(description = "排序")
  @Column(name = "group_sort")
  private Integer sort;

}
