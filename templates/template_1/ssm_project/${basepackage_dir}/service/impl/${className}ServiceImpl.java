<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${basepackage}.dao.I${className}Dao;
import ${basepackage}.service.I${className}Service;
import ${basepackage}.pojo.${className};

/**
<#include "/java_description.include">
 */
@Service
public class ${className}ServiceImpl implements I${className}Service {

	@Resource
	I${className}Dao ${classNameLower}Dao;
	
	/**
	 * 带条件查询，条件可以为null，既没有条件；返回list对象集合
	 */
	public List<${className}> findAll(Map paraMap) {
		
		List<${className}> list =  ${classNameLower}Dao.findAll(null);
		
		return list;
	}
	
	/** 
	 * 根据ID查询${className}
	 */    
	 public ${className} findById(${table.idColumn.javaType} id){
		 
		 return ${classNameLower}Dao.findById(id);
	 }
	 
   /** 
    *  根据ID删除${className}
    */
    public void deleteById(${table.idColumn.javaType} id){
    	${classNameLower}Dao.deleteById(id);
    }
    
	/** 
	 * 更新${className}
	 **/	
    public void update(${className} ${classNameLower}){
    	${classNameLower}Dao.update(${classNameLower});
    }
    
    /** 
	 * 创建${className}
	 **/
	public void save(${className} ${classNameLower}){
		${classNameLower}Dao.save(${classNameLower});
	}
    
  
}
