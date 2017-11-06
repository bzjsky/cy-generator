<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameFirstLower = table.classNameFirstLower> 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<#macro mapperEl value>${r"#{"}${value}}</#macro>
<mapper namespace="${basepackage}.biz.${namespace}.mapper.${className}Mapper">

	<!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="${basepackage}.biz.${namespace}.entity.vo.${className}Vo" id="BasicsResultMap">
		<#list table.pkColumns as column>
		<id property="${column.columnNameLower}" column="${column.sqlName}" />
		</#list>
		<#list table.notPkColumns as column>
		<result property="${column.columnNameLower}" column="${column.sqlName}"/>
		</#list>
    </resultMap>
    
   	<!-- 用于select查询公用抽取的列 -->
	<sql id="Basics_columns">
		<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>
	</sql>

	<!-- 新增 useGeneratedKeys="true"(主键自增)  keyProperty="xxx" for sqlserver and mysql -->
	<insert id="insert" useGeneratedKeys="true" keyProperty="${table.pkColumn.columnNameFirstLower}" parameterType="${basepackage}.biz.${namespace}.entity.${className}">
		INSERT INTO ${table.sqlName}
		<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.notPkColumns as column>
			<if test="${column.columnNameFirstLower} != null" >
				${column.sqlName},
			</if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list table.notPkColumns as column>
			<if test="${column.columnNameFirstLower} != null" >
				<@mapperEl column.columnNameFirstLower/>,
			</if>
		</#list>
		</trim>
		<!--
			oracle: order="BEFORE" SELECT sequenceName.nextval AS ID FROM DUAL
			DB2: order="BEFORE"" values nextval for sequenceName
			<selectKey resultType="java.lang.Long" order="BEFORE" keyProperty="userId">
				SELECT sequenceName.nextval AS ID FROM DUAL
			</selectKey>
		-->
	</insert>

	<!-- 批量新增 -->
	<insert id="insertBatch" parameterType="${basepackage}.biz.${namespace}.entity.${className}">
		INSERT INTO ${table.sqlName} (
		<#list table.columns as column> ${column.sqlName}<#if column_has_next>,</#if></#list>
		) VALUES
		<foreach item="item" collection="list" separator=",">
		(
		<#list table.columns as column> ${r"#{item."}${column.columnNameFirstLower},jdbcType=${column.jdbcType}}<#if column_has_next>,</#if></#list>
		)
		</foreach>
	</insert>

	<!-- 删除 -->
	<delete id="delete" parameterType="${table.pkColumn.javaType}">
		DELETE FROM ${table.sqlName}
		WHERE
		<#list table.pkColumns as column>
		${column.sqlName} = <@mapperEl column.columnNameLower/> <#if column_has_next> AND </#if>
		</#list>
	</delete>

	<!-- 批量删除 -->
	<delete id="deleteBatch">
		DELETE FROM ${table.sqlName}
		WHERE
		<#if (table.pkColumns?size>1)>
			<foreach item="id" collection="list" open="(" separator="OR" close=")">
				<#list table.pkColumns as column>
					${column.sqlName} = <@mapperEl column.columnNameLower/> <#if column_has_next> AND </#if>
				</#list>
			</foreach>
		</#if>
		<#if (table.pkColumns?size==1)>
		<#list table.pkColumns as column>
		${column.sqlName} IN
		<foreach item="id" collection="list" open="(" separator="," close=")">
			${r"#{"}id}
		</foreach>
		</#list>
		</#if>
	</delete>


	<!-- 更新 -->
	<update id="update" parameterType="${basepackage}.biz.${namespace}.entity.${className}">
		UPDATE ${table.sqlName}
		<set>
			<#list table.notPkColumns as column>
			<if test="${column.columnNameFirstLower} != null" >
				${column.sqlName} = <@mapperEl column.columnNameFirstLower/>,
			</if>
			</#list>
		</set>
		WHERE
			<#list table.pkColumns as column>${column.sqlName} = <@mapperEl column.columnNameLower/> <#if column_has_next> AND </#if> </#list>
	</update>

	<!-- 查询详情 -->
	<select id="getObjectByPK"  resultMap="BasicsResultMap" parameterType="${table.pkColumn.javaType}">
		SELECT <include refid="Basics_columns" />
		FROM ${table.sqlName}
		WHERE
			<#list table.pkColumns as column>${column.sqlName} = <@mapperEl 'id'/> <#if column_has_next> AND </#if></#list>
	</select>

	<!-- 查询列表 -->
	<select id="queryList"  resultMap="BasicsResultMap" parameterType="${basepackage}.biz.${namespace}.entity.${className}">
		SELECT <include refid="Basics_columns" /> FROM ${table.sqlName}
        <include refid="condition"/>
		${table.defaultOrder}
	</select>

	<!-- 查询总记录数 -->
 	<select id="queryTotal" resultType="int" parameterType="${basepackage}.biz.${namespace}.entity.${className}">
		SELECT COUNT(1) FROM ${table.sqlName}
        <include refid="condition"/>
	</select>

    <sql id="condition">
        <where>
		<#list table.notPkColumns as column>
            <if test="${column.columnNameFirstLower} != null" >
			<#if column.isStringColumn>
				AND ${column.sqlName} LIKE CONCAT('%', <@mapperEl column.columnNameFirstLower/>, '%')
			<#else>
				AND ${column.sqlName} = <@mapperEl column.columnNameFirstLower/>
			</#if>
            </if>
		</#list>
        </where>
	</sql>
</mapper>