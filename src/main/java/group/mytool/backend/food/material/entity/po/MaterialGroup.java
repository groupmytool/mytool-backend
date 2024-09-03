package group.mytool.backend.food.material.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import group.mytool.backend.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("food_material_group")
public class MaterialGroup extends BaseEntity {

  /** 父节点ID */
  @TableField("parent_id")
  private String parentId;

  /** 分组名称 */
  @TableField("group_name")
  private String name;

  /** 排序 */
  @TableField("group_sort")
  private Integer sort;

}
