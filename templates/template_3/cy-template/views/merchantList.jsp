<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/head.jsp" %>
	<script type="text/javascript" src="js/pro_list.js"></script>
</head>
<body>
	<div class="blank5"></div>
	<div class="search">
		<form id="form1" method="post">
			<input id="reviewStatus" name="reviewStatus" type="hidden" value="${dto.reviewStatus}"/>
			商品名称：<input id="proName" name="proName" type="text" class="textbox" style="width:100px;" />&nbsp;
			商品编号：<input id="proNo" name="proNo" type="text" class="textbox" style="width:100px;" />&nbsp;
			上下架状态：<input id="shelveStatus" name="shelveStatus" class="easyui-combobox" style="width:100px;"
			        data-options="panelHeight:'auto', editable:false, url:'dict/queryCacheDict?name=proShelveStatus'" />&nbsp;
			创建时间：<input id="startCreateTime" name="startCreateTime" type="text" style="width:100px;"
			        onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endCreateTime\')}',dateFmt:'yyyy-MM-dd'})" />&nbsp;~&nbsp;
			        <input id="endCreateTime" name="endCreateTime" type="text" style="width:100px;"
			        onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startCreateTime\')}',dateFmt:'yyyy-MM-dd'})" />&nbsp;
			<input id="searchBtn" type="button" value="搜索">
		</form>
	</div>
	<!-- 
	<div class="blank5"></div>
	<div class="operate">
		<input type="button" value="增加" class="button" onclick="add()">&nbsp;&nbsp;
		<input type="button" value="删除" class="button" onclick="del()">
	</div>
	 -->
	<div class="blank5"></div>
	<div class="dataDiv">
		<div class="dataDivHeader"></div>
			<table cellspacing="0" cellpadding="0" id="dataTable"></table>
		<div class="dataDivFooter"></div>
	</div>
</body>
</html>