package group.mytool.backend.food.material.entity.ro;

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

  public static final String groupIdNotNullMessage = "分组不能为空";
  public static final String groupIdSizeMessage = "分组信息错误";
  public static final String nameNotNullMessage = "名称不能为空";
  public static final String nameSizeMessage = "名称过长";
  public static final String unitNotNullMessage = "单位不能为空";
  public static final String unitSizeMessage = "单位过长";
  public static final String imageUrlNotNullMessage = "图片地址不能为空";
  public static final String imageUrlSizeMessage = "图片地址过长";

  @Size(min = 32, max = 32, message = groupIdSizeMessage)
  @NotNull(message = groupIdNotNullMessage)
  private String groupId;

  @Size(max = 20, message = nameSizeMessage)
  @NotNull(message = nameNotNullMessage)
  private String name;

  @Size(max = 10, message = unitSizeMessage)
  @NotNull(message = unitNotNullMessage)
  private String unit;

  @Size(max = 100, message = imageUrlSizeMessage)
  @NotNull(message = imageUrlNotNullMessage)
  private String imageUrl;

}
