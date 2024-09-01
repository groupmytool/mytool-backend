package group.mytool.flutter.flex.backend.food.material.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class MaterialGroupChildVo {

  /** ID */
  private String id;

  /** 名称 */
  private String name;

  /** 排序 */
  private Integer sort;

  /** 食材列表 */
  private List<MaterialVo> materials;

}
