<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.dao.IBaseDao;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IBaseDao<T> {
	
	@Autowired
	//mybatis-spring1.0无需此方法；mybatis-spring1.2必须注入sqlSessionFactory
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	String ns;
	public String getNs() {
		return ns;
	}
	//子类重写setNs(),mybatis配置文件namespace名称
	public void setNs(String ns) {
		this.ns = ns;
	}
	

	@Override
	public List<T> find(Map paraMap) {// ".selectAll" 对应mybatis 配置文件的方法ID 
		List<T> list = this.getSqlSession().selectList(ns + ".findAll", paraMap);
 		return list;
	}

	@Override
	public T findById(Serializable id) {
		return this.getSqlSession().selectOne(ns +".findById", id);
	}

	@Override
	public void save(T entity) {
		this.getSqlSession().insert(ns +".insert", entity);
	}

	@Override
	public void update(T entity) {
        this.getSqlSession().update(ns +".update", entity);
	}

	@Override
	public void deleteById(Serializable id) {
		this.getSqlSession().delete(ns +".delete", id);
	}

}
