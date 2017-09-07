package com.cy.generator.provider.java.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.cy.generator.provider.java.model.JavaClass;
import com.cy.generator.util.typemapping.ActionScriptDataTypesUtils;

public class JavaField {
	private Field field;
	private com.cy.generator.provider.java.model.JavaClass clazz; //与field相关联的class
	
	public JavaField(Field field, com.cy.generator.provider.java.model.JavaClass clazz) {
		super();
		this.field = field;
		this.clazz = clazz;
	}

	public String getFieldName() {
		return field.getName();
	}
	
	public com.cy.generator.provider.java.model.JavaClass getType() {
		return new com.cy.generator.provider.java.model.JavaClass(field.getType());
	}

	public boolean isAccessible() {
        return field.isAccessible();
    }

    public boolean isEnumConstant() {
        return field.isEnumConstant();
    }

    public String toGenericString() {
        return field.toGenericString();
    }

    public JavaClass getClazz() {
		return clazz;
	}

	public String getJavaType() {
		return field.getType().getName();
	}

	public String getAsType() {
		return ActionScriptDataTypesUtils.getPreferredAsType(getJavaType());
	}

	public Annotation[] getAnnotations() {
		return field.getAnnotations();
	}

	public boolean getIsDateTimeField() {
		return  getJavaType().equalsIgnoreCase("java.util.Date")
				|| getJavaType().equalsIgnoreCase("java.sql.Date")
				|| getJavaType().equalsIgnoreCase("java.sql.Timestamp")
				|| getJavaType().equalsIgnoreCase("java.sql.Time");
	}
	
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaField other = (JavaField) obj;
        if (field == null) {
            if (other.field != null)
                return false;
        } else if (!field.equals(other.field))
            return false;
        return true;
    }

    public String toString() {
		return "JavaClass:"+clazz+" JavaField:"+getFieldName();
	}
}
