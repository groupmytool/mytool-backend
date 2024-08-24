package group.mytool.flutter.flex.backend.food.material.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupTopVo {

  @Schema(description = "ID")
  private String id;

  @Schema(description = "名称")
  private String name;

  @Schema(description = "排序")
  private Integer sort;

  @Schema(description = "子节点")
  private List<MaterialGroupTopVo> children;

}
