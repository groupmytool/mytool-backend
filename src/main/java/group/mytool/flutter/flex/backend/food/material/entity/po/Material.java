package group.mytool.flutter.flex.backend.food.material.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "food_material")
public class Material extends BaseEntity {

  /** 名称 */
  @Column(name = "material_name")
  private String name;

  /** 单位 */
  @Column(name = "material_unit")
  private String unit;

  /** 图片 */
  @Column(name = "material_image_url")
  private String imageUrl;

  /** 保质期 */
  @Column(name = "material_shelf_life")
  private LocalDate shelfLife;

}
