package ${package.Mapper};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package.Entity}.po.${table.entityName};
import org.apache.ibatis.annotations.Mapper;

/**
* ${table.comment!} Mapper接口模版方法
*
* @author MyTool <0haizhu0@gmail.com>
*/
@Mapper
public interface ${table.mapperName} extends BaseMapper<${table.entityName}> {
}