package group.mytool.backend.food.material.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import group.mytool.backend.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("food_material")
public class Material extends BaseEntity {

  /** 分组ID */
  @TableField("group_id")
  private String groupId;

  /** 名称 */
  @TableField("material_name")
  private String name;

  /** 单位 */
  @TableField("material_unit")
  private String unit;

  /** 图片 */
  @TableField("material_image_url")
  private String imageUrl;

  /** 保质期 */
  @TableField("material_shelf_life")
  private LocalDate shelfLife;

  /** 排序 */
  @TableField("material_sort")
  private Integer sort;

}
