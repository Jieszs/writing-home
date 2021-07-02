package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;
/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
        <#assign keyPropertyType="${field.propertyType}"/>
        <#assign keyComment="${field.comment}"/>
    </#if>
</#list>
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>

    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    /**
    * 获取列表
    */
    List<${entity}> list(${entity} ${entity?uncap_first});

    /**
    * 获取总数
    */
    Integer count(${entity} ${entity?uncap_first});
    <#if (cfg.enableTree!"") == true>
        /**
        *获取树
        */
        List<${entity}> getTree(${entity} ${entity?uncap_first});
    </#if>
    <#if cfg.existFieldName??&&cfg.existFieldName!="">
        /**
        *添加时判重
        */
        boolean exist${cfg.existFieldName?cap_first}(String ${cfg.existFieldName});
        /**
        *修改时判重
        */
        boolean exist${cfg.existFieldName?cap_first}(${keyPropertyType} ${keyPropertyName},String ${cfg.existFieldName});
    </#if>
    }
</#if>
