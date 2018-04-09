
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>" />
</head>
<body>
	<div class="blank5"></div>
	<div class="dataDiv">
		<div class="dataDivHeader"></div>
		<form id="form2" class="easyui-form" method="post">
			<input type="hidden" name="id" value="${r"${pojo.id}"}"/>
			<table cellspacing="0" cellpadding="0" class="editTable">
			<#list table.optionsColumns as column>
				<tr>
					<th>${column.columnAlias}：</th>
					<td>
						<input name="${column.columnNameFirstLower}" class="<#if !column.nullable>required </#if>easyui-textbox"
							   maxlength="${column.maxLength}" value="${r"${pojo."}${column.columnNameLower}}" data-options="<#if !column.nullable>required:true</#if>"/>
					</td>
				</tr>
			</#list>
			</table>
		</form>
		<div class="dataDivFooter"></div>
	</div>
	<div class="blank5"></div>
</body>
</html>