<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.business.${namespace}.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.cy.sdk.mapper.BasicsMapper;

/**
<#include "/java_description.include">
*/
@Mapper
public interface ${className}Mapper extends BasicsMapper{
    
}
