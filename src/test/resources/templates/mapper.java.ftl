package ${package.Mapper};

import ${package.Po}.${table.entityName};
import group.mytool.backend.core.dao.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${table.comment!} Mapper接口模版方法
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper
public interface ${table.mapperName} extends MyBaseMapper<${table.entityName}> {
}