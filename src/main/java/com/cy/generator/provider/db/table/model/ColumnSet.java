package com.cy.generator.provider.db.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import com.cy.generator.provider.db.table.model.Column;
import com.cy.generator.util.StringHelper;
/**
 * 包含一组Column对象的容器类
 * @author cy
 *
 */
public class ColumnSet implements java.io.Serializable{
	private static final long serialVersionUID = -6500047411657968878L;
	
	private LinkedHashSet<com.cy.generator.provider.db.table.model.Column> columns = new LinkedHashSet<com.cy.generator.provider.db.table.model.Column>();

	public ColumnSet(){
	}
	
	public ColumnSet(Collection<? extends com.cy.generator.provider.db.table.model.Column> columns) {
        super();
        this.columns = new LinkedHashSet(columns);
    }

    public LinkedHashSet<com.cy.generator.provider.db.table.model.Column> getColumns() {
		return columns;
	}

	public void setColumns(LinkedHashSet<com.cy.generator.provider.db.table.model.Column> columns) {
		this.columns = columns;
	}
	
	public void addColumn(com.cy.generator.provider.db.table.model.Column c) {
		columns.add(c);
	}

	public com.cy.generator.provider.db.table.model.Column getBySqlName(String name, int sqlType) {
		for(com.cy.generator.provider.db.table.model.Column c : columns) {
			if(name.equalsIgnoreCase(c.getSqlName()) && c.getSqlType() == sqlType) {
				return c;
			}
		}
		return null;
	}
	
	public com.cy.generator.provider.db.table.model.Column getBySqlName(String name) {
	    if(name == null) return null;
	    
		for(com.cy.generator.provider.db.table.model.Column c : columns) {
			if(name.equalsIgnoreCase(c.getSqlName())) {
				return c;
			}
		}
		return null;
	}
	
	public com.cy.generator.provider.db.table.model.Column getByName(String name) {
	    if(name == null) return null;
	    
        com.cy.generator.provider.db.table.model.Column c = getBySqlName(name);
        if(c == null) {
            c = getBySqlName(StringHelper.toUnderscoreName(name));
        }
        return c;
	}

	public com.cy.generator.provider.db.table.model.Column getByName(String name, int sqlType) {
        com.cy.generator.provider.db.table.model.Column c = getBySqlName(name,sqlType);
        if(c == null) {
            c = getBySqlName(StringHelper.toUnderscoreName(name),sqlType);
        }
        return c;
	}
	
    public com.cy.generator.provider.db.table.model.Column getByColumnName(String name) {
        if(name == null) return null;
        
        for(com.cy.generator.provider.db.table.model.Column c : columns) {
            if(name.equals(c.getColumnName())) {
                return c;
            }
        }
        return null;
    }
	/**
	 * 得到是主键的全部column
	 * @return
	 */	
	public List<com.cy.generator.provider.db.table.model.Column> getPkColumns() {
		List results = new ArrayList();
		for(com.cy.generator.provider.db.table.model.Column c : getColumns()) {
			if(c.isPk())
				results.add(c);
		}
		return results;
	}
	
	/**
	 * 得到不是主键的全部column
	 * @return
	 */
	public List<com.cy.generator.provider.db.table.model.Column> getNotPkColumns() {
		List results = new ArrayList();
		for(com.cy.generator.provider.db.table.model.Column c : getColumns()) {
			if(!c.isPk())
				results.add(c);
		}
		return results;
	}
	
	/**
	 * 得到主键总数
	 * @return
	 */
	public int getPkCount() {
		int pkCount = 0;
		for(com.cy.generator.provider.db.table.model.Column c : columns){
			if(c.isPk()) {
				pkCount ++;
			}
		}
		return pkCount;
	}
	
	/** 得到单主键，等价于getPkColumns().get(0)  */
	public com.cy.generator.provider.db.table.model.Column getPkColumn() {
		if(getPkColumns().isEmpty()) {
			return null;
		}
		return getPkColumns().get(0);
	}
	
	public List<com.cy.generator.provider.db.table.model.Column> getEnumColumns() {
        List results = new ArrayList();
        for(Column c : getColumns()) {
            if(!c.isEnumColumn())
                results.add(c);
        }
        return results;	    
	}
}
