package group.mytool.flutter.flex.backend.food.material.entity.po;

import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
@Table(name = "food_material")
public class Material extends BaseEntity {

  @Schema(description = "名称")
  @Column(name = "material_name")
  private String name;

  @Schema(description = "单位")
  @Column(name = "material_unit")
  private String unit;

  @Schema(description = "图片")
  @Column(name = "material_image_url")
  private String imageUrl;

  @Schema(description = "保质期")
  @Column(name = "material_shelf_life")
  private LocalDate shelfLife;

}
