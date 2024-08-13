package group.mytool.flutter.flex.backend.food.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.dto.MaterialGroupDto;
import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
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

    MaterialGroupDto doToDto(MaterialGroup materialGroup);

    MaterialGroupVo dtoToVo(MaterialGroupDto materialGroup);

    List<MaterialGroupDto> doToDtoList(List<MaterialGroup> materialGroups);

    List<MaterialGroupVo> dtoToVoList(List<MaterialGroupDto> materialGroups);

}
