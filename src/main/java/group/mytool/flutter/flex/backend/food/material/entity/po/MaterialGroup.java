package group.mytool.flutter.flex.backend.food.material.entity.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */

@Data
@Table("food_material_group")
public class MaterialGroup extends BaseEntity {

    @Schema(description = "父节点ID")
    @Column(value = "parent_id")
    private String parentId;

    @Schema(description = "名称")
    @Column(value = "group_name")
    private String name;

    @Schema(description = "排序")
    @Column(value = "group_sort")
    private Integer sort;


}
