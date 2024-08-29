package ${package.Entity}.req;

import ${package.Entity}.po.${table.entityName};
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.comment!} 查询对象
 *
 * @author MyTool <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ${entity}Query extends ${table.entityName} {
}