package group.mytool.flutter.flex.backend.food.material.entity.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.po.Material;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface MaterialConvertor {

  MaterialVo doToVo(Material material);

  List<MaterialVo> doToVoList(List<Material> material);

}
