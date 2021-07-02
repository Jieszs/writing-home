package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import java.util.List;

/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    /**
    * 获取列表
    */
    List<${entity}> list(${entity} ${entity?uncap_first});

    /**
    * 获取总数
    */
    Integer count(${entity} ${entity?uncap_first});
    }
</#if>
