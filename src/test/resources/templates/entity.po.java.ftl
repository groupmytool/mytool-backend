package ${package.Entity}.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import entity.core.group.mytool.flutter.backend.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.comment!}
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${table.name}")
public class ${entity} extends BaseEntity {

<#-- ----------  属性私有化  ---------->
<#assign baseField = ["id", "create_time", "update_time", "operate_info", "deleted"]>
<#list table.fields as field>
  <#-- 判断 ${field.name} 是否在 stringArray 中存在 -->
  <#if !baseField?seq_contains(field.name)>
  /** ${field.comment!} */
  <#-- 乐观锁注解 -->
  <#if (versionFieldName!"") == field.name>
  @Version
  </#if>
  <#-- 逻辑删除注解 -->
  <#if (logicDeleteFieldName!"") == field.name>
  @TableLogic
  </#if>
  @TableField("${field.name}")
  private ${field.propertyType} ${field.propertyName};

  </#if>
</#list>
}