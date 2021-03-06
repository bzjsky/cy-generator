<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<!DOCTYPE html>
<html>
<head>
<title>${table.remarks}</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<link rel="stylesheet" href="../../plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="../../css/main.css">
<script src="../../libs/jquery.min.js"></script>
<script src="../../plugins/layer/layer.js"></script>
<script src="../../libs/bootstrap.min.js"></script>
<script src="../../libs/vue.min.js"></script>
<script src="../../plugins/jqgrid/grid.locale-cn.js"></script>
<script src="../../plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="../../plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="../../plugins/jquery-validation/messages_zh.js"></script>
<script src="../../js/common.js"></script>
</head>
<body>
<div id="app" v-cloak>
	<div v-show="showList">
		<div class="grid-btn">
			<a v-if="hasPermission('${classNameLower}:save')" class="btn btn-success" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
			<a v-if="hasPermission('${classNameLower}:modify')" class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
			<a v-if="hasPermission('${classNameLower}:remove')" class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
		</div>
	    <table id="jqGrid"></table>
	    <div id="jqGridPager"></div>
    </div>
    
    <div v-show="!showList" class="panel panel-default">
		<div class="panel-heading">{{title}}</div>
		<form class="form-horizontal">
		<#list table.optionsColumns as column>
			<#--<#if (!ignore_columns?exists || ignore_columns?index_of(column.columnNameFirstLower) = -1)>-->
			<div class="form-group">
				<div class="col-sm-2 control-label<#if !column.nullable> required</#if>">${column.columnAlias}</div>
				<div class="col-sm-10">
					<input type="text" class="form-control validate_${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" v-model="record.${column.columnNameFirstLower}" placeholder="${column.remarks}"/>
				</div>
			</div>
			<#--</#if>-->
		</#list>
			<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
			</div>
		</form>
	</div>
</div>

<script src="../../js/modules/${namespace}/${classNameLower}.js"></script>
</body>
</html>