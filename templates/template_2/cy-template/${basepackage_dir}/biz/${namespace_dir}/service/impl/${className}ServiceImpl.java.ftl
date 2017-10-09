<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.business.${namespace}.service.impl;

import org.springframework.stereotype.Service;
import ${basepackage}.business.${namespace}.service.${className}Service;
import ${basepackage}.business.${namespace}.entity.${className};
<#if (!table.pkColumn.javaType?contains("lang") && table.pkColumn.javaType?contains("."))>
        import ${javaType};
</#if>
import com.cy.sdk.service.impl.BasicsServiceImpl;

/**
<#include "/java_description.include">
 */
@Service
public class ${className}ServiceImpl extends BasicsServiceImpl<${className}, ${table.pkColumn.simpleJavaType}>  implements ${className}Service {
  
}
