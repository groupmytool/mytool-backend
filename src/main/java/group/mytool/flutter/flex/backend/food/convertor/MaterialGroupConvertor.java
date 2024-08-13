package group.mytool.flutter.flex.backend.food.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.model.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface MaterialGroupConvertor {

    MaterialGroupConvertor INSTANCE = Mappers.getMapper(MaterialGroupConvertor.class);

    MaterialGroupVo doToVo(MaterialGroup materialGroup);

    List<MaterialGroupVo> doToVoList(List<MaterialGroup> materialGroups);

}
