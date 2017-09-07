<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao;

import java.util.List;
import java.util.Map;
<#include "/java_imports.include">

/**
<#include "/java_description.include">
 */
public interface I${className}Dao { 

	/** 
	 * 创建${className}
	 **/
	public void save(${className} ${classNameLower});
	
	/** 
	 * 更新${className}
	 **/	
    public void update(${className} ${classNameLower});
    
	/** 
	 * 删除${className}
	 **/
    public void deleteById(${table.idColumn.javaType} id);
    
	/** 
	 * 根据ID查询${className}
	 **/    
    public ${className} findById(${table.idColumn.javaType} id);
	
	/** 
	 * 查询: ${className}
	 **/   
	public List<${className}> findAll(Map paraMap);
    
}
