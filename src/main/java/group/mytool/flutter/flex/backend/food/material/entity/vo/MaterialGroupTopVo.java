package group.mytool.flutter.flex.backend.food.material.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupTopVo {

  /** ID */
  private String id;

  /** 名称 */
  private String name;

  /** 排序 */
  private Integer sort;

  /** 子节点 */
  private List<MaterialGroupTopVo> children;

}
