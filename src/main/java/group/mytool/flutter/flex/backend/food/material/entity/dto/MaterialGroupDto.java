package group.mytool.flutter.flex.backend.food.material.entity.dto;

import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import lombok.Data;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupDto extends MaterialGroup {

  /** 子节点 */
  private List<MaterialGroupDto> children;

  /** 食材列表 */
  private List<MaterialVo> materials;

}
