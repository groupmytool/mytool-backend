package group.mytool.flutter.flex.backend.food.material.entity.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.po.Material;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface MaterialConvertor {

  MaterialConvertor INSTANCE = Mappers.getMapper(MaterialConvertor.class);

  MaterialVo doToVo(Material material);

  List<MaterialVo> doToVoList(List<Material> material);

}
