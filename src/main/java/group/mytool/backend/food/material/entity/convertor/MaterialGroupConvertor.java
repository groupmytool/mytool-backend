package group.mytool.backend.food.material.entity.convertor;

import group.mytool.backend.food.material.entity.po.MaterialGroup;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupRootVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface MaterialGroupConvertor {

  List<MaterialGroupRootVo> poToTopVoList(List<MaterialGroup> materialGroups);

  List<MaterialGroupChildVo> poToChildVoList(List<MaterialGroup> materialGroups);


}
