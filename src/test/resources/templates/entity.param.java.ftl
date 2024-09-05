package ${package.Ro};

import ${package.Po}.${entity};
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${table.comment!} 表单提交对象
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ${tableExt.paramName} extends ${table.entityName} {

<#assign baseField = ["id", "create_time", "update_time", "operate_info", "deleted"]>
<#list table.fields as field>
  <#-- 判断 ${field.name} 是否在 stringArray 中存在 -->
  <#if !baseField?seq_contains(field.name)>
  public static final String ${field.propertyName}SizeMessage = "${field.comment!}过长";
  </#if>
</#list>

<#-- ----------  属性私有化  ---------->
<#assign baseField = ["id", "create_time", "update_time", "operate_info", "deleted"]>
<#list table.fields as field>
  <#-- 判断 ${field.name} 是否在 stringArray 中存在 -->
  <#if !baseField?seq_contains(field.name)>
  /** ${field.comment!} */
  @Size(max = ${field.metaInfo.length}, message = ${field.propertyName}SizeMessage)
  private ${field.propertyType} ${field.propertyName};

  </#if>
</#list>
}