package group.mytool.backend.food.material.entity.req;

import group.mytool.backend.food.material.entity.po.Material;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialFormParam extends Material {

  @Size(min = 32, max = 32, message = "分组信息错误")
  @NotNull(message = "分组不能为空")
  private String groupId;

  @Size(min = 1, max = 32, message = "名称过长")
  @NotNull(message = "名称不能为空")
  private String name;

  @Size(min = 1, max = 32, message = "单位过长")
  @NotNull(message = "单位不能为空")
  private String unit;

  @Size(min = 1, max = 255, message = "图片地址过长")
  @NotNull(message = "图片地址不能为空")
  private String imageUrl;

}
