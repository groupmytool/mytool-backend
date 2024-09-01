package group.mytool.flutter.flex.backend.food.material.entity.bo;

import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialGroupBo extends MaterialGroup {

  /** 子节点 */
  private List<MaterialGroupBo> children;

  /** 食材列表 */
  private List<MaterialVo> materials;

}
