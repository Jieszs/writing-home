package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
/**
* <p>
    * ${table.comment!} 服务实现类
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
@Service
@Transactional(rollbackFor = Exception.class)
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};
    /**
    * 获取列表
    */
    @Override
    public List<${entity}> list(${entity} ${entity?uncap_first}){
    return ${table.mapperName?uncap_first}.list(${entity?uncap_first});
    }
    /**
    * 获取总数
    */
    @Override
    public Integer count(${entity} ${entity?uncap_first}){
    return ${table.mapperName?uncap_first}.count(${entity?uncap_first});
    }
    <#if cfg.existFieldName??&&cfg.existFieldName!="">
        /**
        *添加时判重
        */
        @Override
        public boolean exist${cfg.existFieldName?cap_first}(String ${cfg.existFieldName}){
        ${entity} condition = ${entity}.builder()
                                .${cfg.existFieldName}(${cfg.existFieldName})
                                .build();
            List<${entity}> list = list(condition);
            if (!CollectionUtils.isEmpty(list)){
            return true;
            }
            return false;
        }
        /**
        *修改时判重
        */
        @Override
        public boolean exist${cfg.existFieldName?cap_first}(${keyPropertyType} ${keyPropertyName},String ${cfg.existFieldName}){
        ${entity} condition = ${entity}.builder()
        .${cfg.existFieldName}(${cfg.existFieldName})
        .build();
        List<${entity}> list = list(condition);
        if (!CollectionUtils.isEmpty(list)&&!list.get(0).get${keyPropertyName?cap_first}().equals(${keyPropertyName})){
        return true;
        }
        return false;
        }
    </#if>

    <#if (cfg.enableTree!"") == true>
        /**
        *获取树
        */
        @Override
        public List<${entity}> getTree(${entity} ${entity?uncap_first}){
        List<${entity}> all = list(${entity?uncap_first});
        if (CollectionUtils.isEmpty(all)) return null;
        List<${entity}> tree = new ArrayList<>();
        for (${entity} one : all) {
        if (one.getParentId() == 0) {
        setChildren(one, all);
        tree.add(one);
        }
        }
        return tree;
        }

        private void setChildren(${entity} ${entity?uncap_first}, List<${entity}> all) {
        for (${entity} one : all) {
        if (one.getParentId().equals(${entity?uncap_first}.getTagId())) {
        setChildren(one, all);
        if (${entity?uncap_first}.getChildren() != null) {
        ${entity?uncap_first}.getChildren().add(one);
        } else {
        List<${entity}> list = new ArrayList<>();
        list.add(one);
        ${entity?uncap_first}.setChildren(list);
        }
        }
        }
        }
    </#if>



    }
</#if>
