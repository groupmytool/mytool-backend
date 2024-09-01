package group.mytool.flutter.flex.backend.food.material.entity.convertor;

import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface MaterialGroupConvertor {

  List<MaterialGroupTopVo> poToTopVoList(List<MaterialGroup> materialGroups);

  List<MaterialGroupChildVo> poToChildVoList(List<MaterialGroup> materialGroups);


}
