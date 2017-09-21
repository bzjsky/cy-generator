<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower> 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<#macro mapperEl value>${r"#{"}${value}}</#macro>
<mapper namespace="${basepackage}.business.${namespace}.mapper.${className}Mapper">

	<!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="${basepackage}.business.${namespace}.entity.vo.${className}Vo" id="BasicsVoResultMap" extends="BasicsResultMap">
		<#list table.compositeIdColumns as column>
		<id property="${column.columnNameLower}" column="${column.sqlName}" />
		</#list>
		<#list table.notPkColumns as column>
		<result property="${column.columnNameLower}" column="${column.sqlName}"/>
		</#list>
    </resultMap>

</mapper>