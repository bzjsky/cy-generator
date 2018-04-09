package com.cy.generator.provider.db.table.model.util;

import com.cy.generator.provider.db.table.model.Column;
import com.cy.generator.util.typemapping.DatabaseDataTypesUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ColumnHelper {
	
	public static String[] removeHibernateValidatorSpecialTags(String str) {
		if(str == null || str.trim().length() == 0) return new String[]{};
		return str.trim().replaceAll("@", "").replaceAll("\\(.*?\\)", "").trim().split("\\s+");
	}
	
	/** 得到JSR303 bean validation的验证表达式 */
	public static String getHibernateValidatorExpression(Column c) {
		if(!c.isPk() && !c.isNullable()) {
			if(DatabaseDataTypesUtils.isString(c.getJavaType())) {
				return  "@NotBlank " + getNotRequiredHibernateValidatorExpression(c);
			}else {
				return  "@NotNull " + getNotRequiredHibernateValidatorExpression(c);
			}
		}else {
			return getNotRequiredHibernateValidatorExpression(c);
		}
	}

	public static String getNotRequiredHibernateValidatorExpression(Column c) {
		String result = "";
		if(c.getSqlName().indexOf("mail") >= 0) {
			result += "@Email ";
		}
		if(DatabaseDataTypesUtils.isString(c.getJavaType()) && c.getSize() > 0) {
			result += String.format("@Length(max=%s)",c.getSize());
		}
/*		if(DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType())) {
			String javaType = DatabaseDataTypesUtils.getPreferredJavaType(c.getSqlType(), c.getSize(), c.getDecimalDigits());
			if(javaType.toLowerCase().indexOf("short") >= 0) {
				result += " @Max("+Short.MAX_VALUE+")";
			}else if(javaType.toLowerCase().indexOf("byte") >= 0) {
				result += " @Max("+Byte.MAX_VALUE+")";
			}else if(c.getSize() > 0) {
				try {
					long maxValue = Long.parseLong(StringHelper.repeat("9", c.getSize()));
					result += " @Max("+maxValue+"L)";
				}catch(NumberFormatException e) {
					result += " @Max("+Long.MAX_VALUE+"L)";
				}
			}
		}*/
		return result.trim();
	}
	
	/** 得到rapid validation的验证表达式 */
	public static String getRapidValidation(Column c) {
		String result = "";
		if(c.getSqlName().indexOf("mail") >= 0) {
			result += "validate-email ";
		}
		if(DatabaseDataTypesUtils.isFloatNumber(c.getJavaType())) {
			result += "validate-number ";
		}
		if(DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType())) {
			result += "validate-integer ";
			if(c.getJavaType().toLowerCase().indexOf("short") >= 0) {
				result += "max-value-"+Short.MAX_VALUE;
			}else if(c.getJavaType().toLowerCase().indexOf("integer") >= 0) {
				result += "max-value-"+Integer.MAX_VALUE;
			}else if(c.getJavaType().toLowerCase().indexOf("byte") >= 0) {
				result += "max-value-"+Byte.MAX_VALUE;
			}
		}
		return result;
	}

	public static String getJqueryValidationString(Column c) {
		List<String> result = new ArrayList<>();
		if(!c.isNullable()){
			result.add("required:true");
		}
		if(c.getSqlName().indexOf("mail") >= 0) {
			result.add("email:true");
		}
		if(DatabaseDataTypesUtils.isFloatNumber(c.getJavaType())) {
			result.add("number:true");
		}
		if(DatabaseDataTypesUtils.isString(c.getJavaType()) && c.getSize() > 0){
			result.add("maxlength:"+(c.getSize()%2 == 0 ? c.getSize()/2 : c.getSize()/2-1));
		}else if(DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType())) {
			result.add("digits:true");
			if(c.getJavaType().toLowerCase().indexOf("short") >= 0) {
				result.add("max:"+Short.MAX_VALUE);
			}else if(c.getJavaType().toLowerCase().indexOf("integer") >= 0) {
				result.add("max:"+Integer.MAX_VALUE);
			}else if(c.getJavaType().toLowerCase().indexOf("byte") >= 0) {
				result.add("max:"+Byte.MAX_VALUE);
			}
		}
		return StringUtils.join(result, ",");
	}

	public static String getElementUIDataRule(Column c) {
//		List<Map> result = new ArrayList<>();
		List<String> result = new ArrayList<>();
		if(!c.isNullable()){
//			Map map = new HashMap();
//			map.put("required", true);
//			map.put("message", "不能为空");
//			map.put("trigger", "blur");
//			result.add(map);
			result.add("{required: true, message: '不能为空', trigger: 'change'}");
		}

		if(c.getSqlName().indexOf("mail") >= 0) {
//			Map map = new HashMap();
//			map.put("type", "email");
//			map.put("message", "请输入正确的邮箱地址");
//			map.put("trigger", "blur");
//			result.add(map);
			result.add("{type: 'email', message: '请输入正确的邮箱地址', trigger: 'change'}");
		}
		if(DatabaseDataTypesUtils.isFloatNumber(c.getJavaType())) {
//			Map map = new HashMap();
//			map.put("type", "number");
//			map.put("message", "必须为数字值");
//			map.put("trigger", "blur");
//			result.add(map);
			result.add("{type: 'number', message: '必须为数字值', trigger: 'change'}");
		}
		if(DatabaseDataTypesUtils.isString(c.getJavaType()) && c.getSize() > 0){
//			Map map = new HashMap();
			int max = c.getSize()%2 == 0 ? c.getSize()/2 : c.getSize()/2-1;
//			map.put("max", max);
//			map.put("message", "不能超过"+max+"个字符");
//			map.put("trigger", "blur");
//			result.add(map);
			result.add("{max: " + max + ", message: '不能超过" + max + "个字符', trigger: 'change'}");
		}else if(DatabaseDataTypesUtils.isIntegerNumber(c.getJavaType())) {
//			Map map = new HashMap();
//			int max = c.getSize()%2 == 0 ? c.getSize()/2 : c.getSize()/2-1;
//			map.put("type", "number");
//			map.put("message", "必须为数字值");
//			map.put("trigger", "blur");
			String str = "{";

//
			if(c.getJavaType().toLowerCase().indexOf("short") >= 0) {
//				map.put("max", Short.MAX_VALUE);
				str += "max: " + Short.MAX_VALUE + ", ";
			}else if(c.getJavaType().toLowerCase().indexOf("integer") >= 0) {
//				map.put("max", Integer.MAX_VALUE);
				str += "max: " + Integer.MAX_VALUE + ", ";
			}else if(c.getJavaType().toLowerCase().indexOf("byte") >= 0) {
//				map.put("max", Byte.MAX_VALUE);
				str += "max: " + Byte.MAX_VALUE + ", ";
			}
//			result.add(map);
			result.add(str + "type: 'number', message: '必须为数字值', trigger: 'change'}");
		}
//		return JSON.toJSONString(result, SerializerFeature.UseSingleQuotes);
		return "["+StringUtils.join(result, ", ")+"]";
	}

}
