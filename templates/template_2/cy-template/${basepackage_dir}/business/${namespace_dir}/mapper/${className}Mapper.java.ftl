<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.business.${namespace}.mapper;

import org.apache.ibatis.annotations.Mapper;

import ${basepackage}.business.${namespace}.entity.${className};
<#if (!table.pkColumn.javaType?contains("lang") && table.pkColumn.javaType?contains("."))>
import ${javaType};
</#if>
import com.cy.sdk.mapper.BasicsMapper;

/**
<#include "/java_description.include">
*/
@Mapper
public interface ${className}Mapper extends BasicsMapper<${className}, ${table.pkColumn.simpleJavaType}>{
    
}
