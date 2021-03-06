<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '${classNameLower}/queryListPage',
        datatype: "json",
        colModel: [
<#--<#list table.compositeIdColumns as column>
			{ label: '${column.columnNameLower}', name: '${column.columnNameLower}', index: '${column.columnNameLower}', width: 50, key: true },
</#list>-->
<#list table.notPkColumns as column>
			{ label: '${column.remarks}', name: '${column.columnNameLower}', index: '${column.columnNameLower}', width: 80 }<#if column_has_next>, </#if>
</#list>
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "obj.list",
            page: "obj.pageNum",
            total: "obj.pages",
            records: "obj.total"
        },
        prmNames : {
            page:"pageNum", 
            rows:"pageSize", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
	jQuery.validator.addClassRules({
		<#list table.optionsColumns as column>
        validate_${column.columnNameFirstLower}: {
			${column.jqueryValidationString}
		}<#if column_has_next>,</#if>
		</#list>
	});
	$form = $(".form-horizontal").validate({rules: {}});
});

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		q:{},
		record: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.initForm();
			vm.title = "新增";
			vm.record = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.initForm();
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		initForm: function () {
			vm.showList = false;
			$form.resetForm();
			$form.resetElements($("input.error"));
		},
		saveOrUpdate: function (event) {
			if($form.form()){
				var url = vm.record.id == null ? "${classNameLower}/save" : "${classNameLower}/modify";
				$.ajax({
					type: "POST",
					url: baseURL + url,
					contentType: "application/json",
					data: JSON.stringify(vm.record),
					success: function(r){
						if(r.code === 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			}
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "${classNameLower}/removeBatch",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code === 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "${classNameLower}/getById?id="+id, function(r){
                vm.record = r.obj;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:vm.q,
                page:page
            }).trigger("reloadGrid");
		}
	}
});