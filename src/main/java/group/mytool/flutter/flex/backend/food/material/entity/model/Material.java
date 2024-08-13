package group.mytool.flutter.flex.backend.food.material.entity.model;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import group.mytool.flutter.flex.backend.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
@Table("food_material")
public class Material extends BaseEntity {

    @Schema(description = "名称")
    @Column(value = "material_name")
    private String name;

    @Schema(description = "单位")
    @Column(value = "material_unit")
    private String unit;

    @Schema(description = "图片")
    @Column(value = "material_image_url")
    private String imageUrl;

    @Schema(description = "保质期")
    @Column(value = "material_shelf_life")
    private LocalDate shelfLife;

}
