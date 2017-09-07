<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
<#include "/java_description.include">
 */
public interface IBaseDao<T>{
	
	//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public List<T> find(Map paraMap);			
	
	//查询 ID为参数
	public T findById(Serializable id);
	
	//新建 用实体作为参数
	public void save(T entity);					
	
	//更新 用实体作为参数
	public void update(T entity);					
	
	//按id删除，删除一条；支持整数型和字符串类型ID
	public void deleteById(Serializable id);	
    
}
