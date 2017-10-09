<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.business.${namespace}.entity.vo;

import ${basepackage}.business.${namespace}.entity.${className};

/**
<#include "/java_description.include">
 */
public class ${className}Vo extends ${className}{

	private static final long serialVersionUID = 1L;

}





