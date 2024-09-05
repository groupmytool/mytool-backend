package ${package.Convertor};

import ${package.Po}.${table.entityName};
import ${package.Vo}.${tableExt.voName};
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * ${table.comment!} 转换器
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface ${tableExt.convertorName} {

  ${tableExt.voName} poToVo(${table.entityName} po);

  List<${tableExt.voName}> poToVoList(List<${table.entityName}> poList);

}