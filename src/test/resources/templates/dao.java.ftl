package ${package.Parent}.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.po.${table.entityName};
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
* ${table.comment!} 数据持久层
*
* @author MyTool <0haizhu0@gmail.com>
*/
@Repository
@RequiredArgsConstructor
public class ${entity}Dao extends ServiceImpl<${table.mapperName}, ${table.entityName}> {

  private final ${table.mapperName} mapper;

}