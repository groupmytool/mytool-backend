package ${package.Ro};

import ${package.Po}.${table.entityName};
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.comment!} 查询对象
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ${tableExt.queryName} extends ${table.entityName} {
}