package group.mytool.backend.food.material.entity.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class MaterialVo {

  /** 分组ID */
  private String groupId;

  /** 名称 */
  private String name;

  /** 单位 */
  private String unit;

  /** 图片 */
  private String imageUrl;

  /** 保质期 */
  private LocalDate shelfLife;

  /** 排序 */
  private Integer sort;

}
