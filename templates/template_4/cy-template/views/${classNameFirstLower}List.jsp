<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/head-v2.0.jsp" %>
	<script type="text/javascript" src="js/jquery.ext/jquery.serializeJSON.js"></script>
	<script type="text/javascript" src="js/service/${classNameLower}.js"></script>
</head>
<body>
	<div class="blank5"></div>
	<div class="search">
		<form id="form1">
			搜索：<input name="name" type="text" prompt="关键词" class="easyui-textbox" style="width:100px;" />
			<input type="text" style="display: none;">
			<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',onClick: search">搜索</a>
		</form>
	</div>
	<div class="blank5"></div>
	<div class="operate">
		<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',onClick: edit">增加</a>
		<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',onClick: del">删除</a>
	</div>
	<div class="dataDiv">
		<div class="dataDivHeader"></div>
			<table cellspacing="0" cellpadding="0" id="dataTable"></table>
		<div class="dataDivFooter"></div>
	</div>
</body>
</html>