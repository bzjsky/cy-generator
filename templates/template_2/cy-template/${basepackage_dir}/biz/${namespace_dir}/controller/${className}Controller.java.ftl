<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.business.${namespace}.controller;

import ${basepackage}.business.${namespace}.entity.${className};
import ${basepackage}.business.${namespace}.service.${className}Service;
import com.cy.sdk.controller.BasicsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<#if (!table.pkColumn.javaType?contains("lang") && table.pkColumn.javaType?contains("."))>
import ${javaType};
</#if>

/**
<#include "/java_description.include">
 */
@RestController
@RequestMapping("${classNameLower}")
public class ${className}Controller extends BasicsController<${className}, ${table.pkColumn.simpleJavaType}>{

	@Autowired
	${className}Service ${classNameLower}Service;

}
