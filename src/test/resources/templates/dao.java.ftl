package ${package.Parent}.dao;

import group.mytool.backend.core.dao.BaseDao;
import ${package.Entity}.po.${table.entityName};
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * ${table.comment!} 数据持久层
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
@RequiredArgsConstructor
public class ${entity}Dao extends BaseDao<${table.mapperName}, ${table.entityName}> {

  private final ${table.mapperName} mapper;

}