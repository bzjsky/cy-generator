<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.business.${namespace}.entity;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

<#list table.javaTypes as javaType>
<#if (!javaType?contains("lang") && javaType?contains("."))>
import ${javaType};
</#if>
</#list>
import com.cy.sdk.entity.BasicsEntity;

/**
<#include "/java_description.include">
 */
public class ${className} extends BasicsEntity{

	private static final long serialVersionUID = 1L;
	<#list table.columns as column>
	<#if (!ignore_columns?exists || ignore_columns?index_of(column.columnNameLower) = -1)>
    /**
    * ${column.remarks!}
    */
	<#if column.hibernateValidatorExprssion?length gt 0>
	${column.hibernateValidatorExprssion}
	</#if>
    private ${column.simpleJavaType} ${column.columnNameLower};

	</#if>
	</#list>
	<@generateConstructor className/>
	<@generateJavaColumns/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			.append(get${column.columnName}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof ${className})) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

<#macro generateJavaColumns>
	<#list table.columns as column>
	<#if (!ignore_columns?exists || ignore_columns?index_of(column.columnNameLower) = -1)>
	/**
	 * 设置 ${column.remarks!}
	 */
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}

	/**
	 * 获取 ${column.remarks!}
	 */
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

	</#if>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
