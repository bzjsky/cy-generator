<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.${namespace}.service;

import ${basepackage}.${namespace}.entity.vo.${className}Vo;
<#if (!table.pkColumn.javaType?contains("lang") && table.pkColumn.javaType?contains("."))>
import ${javaType};
</#if>
import com.blm.sdk.service.BasicsService;

/**
<#include "/java_description.include">
 */
public interface ${className}Service extends BasicsService<${className}Vo, ${table.pkColumn.simpleJavaType}>{

}
