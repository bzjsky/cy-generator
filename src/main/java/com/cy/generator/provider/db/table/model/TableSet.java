package com.cy.generator.provider.db.table.model;

import com.cy.generator.provider.db.table.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含一组 Table对象的容器类
 * @author cy
 *
 */
public class TableSet implements java.io.Serializable{
	private static final long serialVersionUID = -6500047411657968878L;
	
	private List<com.cy.generator.provider.db.table.model.Table> tables = new ArrayList<com.cy.generator.provider.db.table.model.Table>();

	public List<com.cy.generator.provider.db.table.model.Table> getTables() {
		return tables;
	}

	public void setTables(List<com.cy.generator.provider.db.table.model.Table> tables) {
		this.tables = tables;
	}
	
	public void addTable(com.cy.generator.provider.db.table.model.Table c) {
		tables.add(c);
	}

	public com.cy.generator.provider.db.table.model.Table getBySqlName(String name) {
		for(com.cy.generator.provider.db.table.model.Table c : tables) {
			if(name.equalsIgnoreCase(c.getSqlName())) {
				return c;
			}
		}
		return null;
	}
	
	public com.cy.generator.provider.db.table.model.Table getByClassName(String name) {
		for(Table c : tables) {
			if(name.equals(c.getClassName())) {
				return c;
			}
		}
		return null;
	}
	
}
