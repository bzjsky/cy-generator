<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.${namespace}.service.impl;

import org.springframework.stereotype.Service;
import ${basepackage}.${namespace}.service.${className}Service;
import ${basepackage}.${namespace}.entity.vo.${className}Vo;
<#if (!table.pkColumn.javaType?contains("lang") && table.pkColumn.javaType?contains("."))>
        import ${javaType};
</#if>
import com.blm.sdk.service.impl.BasicsServiceImpl;

/**
<#include "/java_description.include">
 */
@Service
public class ${className}ServiceImpl extends BasicsServiceImpl<${className}Vo, ${table.pkColumn.simpleJavaType}>  implements ${className}Service {
  
}
