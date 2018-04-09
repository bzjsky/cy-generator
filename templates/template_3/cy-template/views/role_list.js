var dialogHeight;

$(function(){
	var parentHeight = window.parent.getSelectedIframeHeight();
	var datagridHeight = parentHeight - $(".search").outerHeight(true) - $(".operate").outerHeight(true) - 27;
	dialogHeight = parentHeight - 35;
	
    $('#dataTable').datagrid({
        url:'role/queryPageList',
        height:datagridHeight,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		//fit:true,
		//showFooter:true,
        columns:[[
    		{field:'id',checkbox:true,align:"center"},
    		{field:'name',title:'角色名称',width:10,align:"center"},
    		{field:'remark',title:'备注',width:10,align:"center"},
    		{field:'status',title:'状态',width:10,align:"center",formatter:statusFormatter},
    		{field:'createTime',title:'创建时间',width:10,align:"center"},
			{field:'opt',title:'操作',width:10,align:'center',formatter:optFormatter}
        ]],
    	loadFilter: function(data) {
    		var jsObj = {rows:[]};
    		if(data.code == operSuccessCode) {
    			jsObj = data.obj;
    		} else {
    			error(data.msg);
    		}
    		return jsObj;
    	}
    });
    
    //查询按钮
    $('#searchBtn').click(function(){
    	var queryParams = $('#dataTable').datagrid('options').queryParams;
    	queryParams.name = $('#from1 #name').val();
    	queryParams.status =  $('#from1 #status').val();
        $('#dataTable').datagrid({
            url:'role/queryPageList',
            height:datagridHeight,
    		pageSize:10,
    		rownumbers:true,
    		pagination:true,
    		fitColumns:true,
    		onLoadSuccess:function() {
    			$("#dataTable").datagrid('clearSelections');
    		},
            columns:[[
          		{field:'id',checkbox:true,align:"center"},
        		{field:'name',title:'角色名称',width:10,align:"center"},
        		{field:'remark',title:'备注',width:10,align:"center"},
        		{field:'status',title:'状态',width:10,align:"center",formatter:statusFormatter},
        		{field:'createTime',title:'创建时间',width:10,align:"center"},
    			{field:'opt',title:'操作',width:10,align:'center',formatter:optFormatter}
            ]],
        	loadFilter: function(data) {
        		var jsObj = {rows:[]};
        		if(data.code == operSuccessCode) {
        			jsObj = data.obj;
        		} else {
        			error(data.msg);
        		}
        		return jsObj;
        	}
        });
    });
    
	$.ajax({
		type : "post",  
		url : "dict/queryCacheDict",  
		data : {'name':'roleStatus'},  
		async : false,  
		success : function(data){
            $("#dataTable").data("statusDictData", data);
		}
	});
});

function add(){
	$('<div></div>').dialog({
		'id':'addDialog',
		'title':'增加数据',
		'href':'role/add',
		'width': 800,
		'height': dialogHeight,
		'buttons':[{
			text:'保存',
			handler:function() {
				if($('#form1').form('validate')) {
					$.post("role/save", $('#form1').serialize(), function(data) {
						if (data.code == operSuccessCode) {
							info(data.msg);
							// 刷新父页面列表
							$("#dataTable").datagrid('reload');
							$('#addDialog').dialog('destroy');
						} else {
							error(data.msg);
						}
					});
				}
			}
		},{
			text:'关闭',
			handler:function() {
				$("#addDialog").dialog('destroy');
			}
		}],
		'onClose' : function() {
			$(this).dialog('destroy');
		}
	});
	return false;
}

function edit(id) {
	$('<div></div>').dialog({
		'id':'editDialog',
		'title':'编辑数据',
		'href':'role/edit?id='+id,
		'width': 800,
		'height': dialogHeight,
		'buttons':[{
			text:'保存',
			handler:function() {
				if($('#form1').form('validate')) {
					$.post("role/save", $('#form1').serialize(), function(data) {
						if (data.code == operSuccessCode) {
							info(data.msg);
							// 刷新父页面列表
							$("#dataTable").datagrid('reload');
							$('#editDialog').dialog('destroy');
						} else {
							error(data.msg);
						}
					});
				}
			}
		},{
			text:'关闭',
			handler:function() {
				$("#editDialog").dialog('destroy');
			}
		}],
		'onClose' : function() {
			$(this).dialog('destroy');
		},
		'onLoad' : function() {
			var dialogObj = this;
			/*error($("#errorDiv").text(), function(){
				$(dialogObj).dialog('destroy');
			});*/
		}
	});
	return false;
}

function del(id){
	if(!id) {
		//判断是否选中
		var rows = $('#dataTable').datagrid("getChecked");
	    if(rows.length < 1 ) {
	    	error("请选择要操作的数据！");
	        return;
	    }
	}
    confirm('您确定要删除所选数据吗?', function(r){
		if (r){
			var deleteStr = "";
			if(id) {
				deleteStr = id;
			} else {
				deleteStr = getDeleteStr(rows, "id");
			}
    		$.post("role/delete",{"deleteStr":deleteStr},function(data){
				if (data.code == operSuccessCode) {
					info(data.msg);
					// 刷新父页面列表
					$("#dataTable").datagrid('reload');
					$("#dataTable").datagrid('uncheck');
				} else {
					error(data.msg);
				}
    		});
		}
	});
}

function editResource(id) {
	$('<div></div>').dialog({
		'id':'editResourceDialog',
		'title':'编辑数据',
		'href':'roleResource/edit?roleId='+id,
		'width': 350,
		'height': dialogHeight,
		'buttons':[{
			text:'保存',
			handler:function() {
				var treeObj = $.fn.zTree.getZTreeObj("ztree");
				var nodes = treeObj.getChangeCheckedNodes();
				var deleteStr = "";
				var addStr = "";
				for(var i = 0; i < nodes.length; i++) {
					var node = nodes[i];
					if(node.id != 0) {
						if(node.checkedOld) {
							if(deleteStr.length == 0) {
								deleteStr += node.id;
							} else {
								deleteStr += "," + node.id;
							}
						} else {
							if(addStr.length == 0) {
								addStr += node.id;
							} else {
								addStr += "," + node.id;
							}
						}
					}
				}
				$.post("roleResource/save", {"roleId":id,"deleteStr":deleteStr,"addStr":addStr}, function(data) {
					if (data.code == operSuccessCode) {
						info(data.msg);
						// 刷新父页面列表
						$('#editResourceDialog').dialog('destroy');
					} else {
						error(data.msg);
					}
				});
			}
		},{
			text:'关闭',
			handler:function() {
				$("#editResourceDialog").dialog('destroy');
			}
		}],
		'onClose' : function() {
			$(this).dialog('destroy');
		},
		'onLoad' : function() {
			$.post("roleResource/queryList", {"roleId":id}, function(data) {
				if (data.code == operSuccessCode) {
					$(".ztreeDiv").css("height",dialogHeight - 150);
					var setting = {
						data: {
							simpleData: {
								enable: true,
								idKey: "id",
								pIdKey: "pid"
							}
						},
						check: {
							enable: true,
							dblClickExpand: false,
							chkboxType: { "Y": "ps", "N": "s" }
						},
						async: {
							enable: true,
							url: "roleResource/queryList",
							autoParam: ["id=resourceId"],
							otherParam: {"roleId":id},
							dataFilter: handleAsyncData
						},
						callback: {
							onAsyncSuccess: asyncDataSuccess,
							onAsyncError: asyncDataError
						},
						view: {
							selectedMulti: false
						}
					};
					
					$.fn.zTree.init($("#ztree"), setting, data.obj).expandAll(true);
				    
					$("#expandAllBtn").click(function(){
						var treeObj = $.fn.zTree.getZTreeObj("ztree");
						treeObj.expandAll(true);
					});
					
					$("#collapseAllBtn").click(function(){
						var treeObj = $.fn.zTree.getZTreeObj("ztree");
						treeObj.expandAll(false);
					});
					
					$("#refreshBtn").click(function() {
						var treeObj = $.fn.zTree.getZTreeObj("ztree");
						var node = treeObj.getNodeByParam("id", 0, null);
						treeObj.reAsyncChildNodes(node, "refresh");
					});
				} else {
					error(data.msg);
				}
			});
		}
	});
	return false;
}

function optFormatter(value, row, index) {
	return "<a href='javascript:;' onclick='return edit("+row.id+")';>编辑</a>&nbsp;&nbsp;" + 
	"<a href='javascript:;' onclick='return del("+row.id+")';>删除</a>&nbsp;&nbsp;" + 
	"<a href='javascript:;' onclick='return editResource("+row.id+")';>分配资源</a>";
}

function statusFormatter(value, row, index) {
	var statusDictData = $("#dataTable").data("statusDictData");
	if(statusDictData) {
		for(var i = 0; i < statusDictData.length; i++) {
			if(statusDictData[i].value == value) {
				return statusDictData[i].text;
			}
		}
	}
	return "";
}

function handleAsyncData(treeId, parentNode, responseData) {
	if (responseData.code == operSuccessCode) {
		return responseData.obj;
	} else {
		error("保存成功，但刷新树节点失败");
		return null;
	}
}

function asyncDataError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    error(XMLHttpRequest);
}

function asyncDataSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandAll(true);
	if( treeObj.getCheckedNodes(true).length > 0) {
		treeObj.checkNode(treeNode, true);
	} else {
		treeObj.checkNode(treeNode, false);
	}
}