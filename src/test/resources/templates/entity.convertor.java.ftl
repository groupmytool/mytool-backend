package ${package.Entity}.convertor;

import ${package.Entity}.po.${entity};
import ${package.Entity}.vo.${entity}Vo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * ${table.comment!} 转换器
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface ${entity}Convertor {

  ${entity}Vo poToVo(${entity} po);

  List<${entity}Vo> poToVoList(List<${entity}> poList);

}