<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
                    <id column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
            <#list table.commonFields as field><#--生成公共字段 -->
                <result column="${field.name}" property="${field.propertyName}"/>
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                    <result column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <#list table.commonFields as field>
                ${field.columnName},
            </#list>
            ${table.fieldNames}
        </sql>

    </#if>
    <!--查询条件-->
    <sql id="where">
        FROM ${table.name}
        <where>
            <#list table.fields as field>
                <#if (logicDeleteFieldName!"") == field.name>
                    ${field.name} = 1
                </#if>
            </#list>
            <#list table.fields as field>
                <if test="${field.propertyName}!=null and ${field.propertyName}!=''">
                    AND ${field.name} = <#noparse>#</#noparse>{${field.propertyName}}
                </if>
            </#list>
        </where>
    </sql>
    <!--获取列表-->
    <select id="list" parameterType="${package.Entity}.${table.entityName}"
            resultType="${package.Entity}.${table.entityName}">
        SELECT
        <include refid="Base_Column_List"/>
        <include refid="where"/>
        <#if (cfg.enablePage!"") == true>
            <#noparse>
                <if test="offset !=null and limit !=null">
                    LIMIT #{offset},#{limit}
                </if>
            </#noparse>
        </#if>
    </select>

    <!--获取总数-->
    <select id="count" parameterType="${package.Entity}.${table.entityName}" resultType="java.lang.Integer">
        SELECT COUNT(1)
        <include refid="where"/>
    </select>
</mapper>
