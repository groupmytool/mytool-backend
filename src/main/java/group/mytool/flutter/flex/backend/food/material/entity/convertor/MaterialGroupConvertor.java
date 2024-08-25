package group.mytool.flutter.flex.backend.food.material.entity.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.dto.MaterialGroupDto;
import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface MaterialGroupConvertor {

  MaterialGroupDto doToDto(MaterialGroup materialGroup);

  List<MaterialGroupDto> doToDtoList(List<MaterialGroup> materialGroups);

  MaterialGroupChildVo dtoToChildVo(MaterialGroupDto materialGroup);

  List<MaterialGroupChildVo> dtoToChildVoList(List<MaterialGroupDto> materialGroups);

  MaterialGroupTopVo dtoToTopVo(MaterialGroupDto materialGroup);

  List<MaterialGroupTopVo> dtoToTopVoList(List<MaterialGroupDto> materialGroups);

}
