<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="${table.entityName}Map" type="${package.Po}.${table.entityName}">
    <#list table.fields as field>
        <#if field.keyFlag><#--生成主键排在第一位-->
            <id column="${field.name}" property="${field.propertyName}"/>
        </#if>
    </#list>
    <#list table.fields as field>
        <#if !field.keyFlag><#--生成普通字段 -->
            <result column="${field.name}" property="${field.propertyName}"/>
        </#if>
    </#list>
    <#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
    </#list>
    </resultMap>
</#if>

</mapper>