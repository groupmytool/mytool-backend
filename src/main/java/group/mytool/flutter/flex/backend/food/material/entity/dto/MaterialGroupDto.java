package group.mytool.flutter.flex.backend.food.material.entity.dto;

import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupDto extends MaterialGroup {

  @Schema(description = "子节点")
  private List<MaterialGroupDto> children;

  @Schema(description = "食材列表")
  private List<MaterialVo> materials;

}
