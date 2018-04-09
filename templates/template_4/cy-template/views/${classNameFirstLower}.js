<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
var dialogHeight;

$(function(){
	var parentHeight = window.parent.getSelectedIframeHeight();
	var datagridHeight = parentHeight - $(".search").outerHeight(true) - $(".operate").outerHeight(true) - 27;
	dialogHeight = parentHeight - 35;
    var tableConfig = {
        url:'service/${classNameLower}/queryListPage',
        height:datagridHeight,
        pageSize:10,
        rownumbers:true,
        pagination:true,
        fitColumns:true,
        //fit:true,
        //showFooter:true,
        columns:[[
            {field:'id', checkbox:true, align:"center"},
        	<#list table.notPkColumns as column>
			{ field: '${column.columnNameLower}', title: '${column.remarks}',width:10 , align:"center"},
			</#list>
   			{field:'opt',title:'操作',width:10,align:'center',formatter: function(value, row, index) {
                return "<a href='javascript:;' onclick='return edit("+row.id+")';>编辑</a>&nbsp;&nbsp;" +
                    "<a href='javascript:;' onclick='return del("+row.id+")';>删除</a>";
            }}
		]],
		loadFilter: function(data) {
			var jsObj = {rows:[]};
			if(data.code == 0) {
                jsObj.rows = data.obj.list;
                jsObj.total = data.obj.total;
			} else {
				error(data.msg);
			}
			return jsObj;
		},
        queryParams: $('#form1').serializeJSON()
	};
    $('#dataTable').datagrid(tableConfig);
});
function search(){
    $('#dataTable').datagrid('load', $('#form1').serializeJSON());
}
function edit(id) {
	$('<div></div>').dialog({
		'id':'editDialog',
		'title': id ? '编辑数据' : '新增数据',
		'href':'service/${classNameLower}/toEdit'+(id ? '?id='+id : ''),
		'width': 800,
		'height': dialogHeight,
		'buttons':[{
			text:'保存',
			handler:function() {
				if($('#form2').form('validate')) {
                    $.ajax({
                        type: "POST",
                        url: "service/${classNameLower}/"+(id ? "modify" : "save"),
                        contentType: "application/json",
                        data: JSON.stringify($('#form2').serializeJSON()),
                        success: function(data){
                            if(data.code === 0){
                                info(data.msg);
                                // 刷新父页面列表
                                $("#dataTable").datagrid('reload');
                                $('#editDialog').dialog('destroy');
                            }else{
                                error(data.msg);
                            }
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
            var deleteArray = [];
            if(id) {
                deleteArray.push(id);
            } else {
                deleteArray = getDeleteArray(rows, "id");
            }
            $.ajax({
                url: "service/${classNameLower}/removeBatch",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(deleteArray),
                success: function (data) {
                    if (data.code == 0) {
                        info(data.msg);
                        // 刷新父页面列表
                        $("#dataTable").datagrid('reload');
                        // $("#dataTable").datagrid('uncheck');
                    } else {
                        error(data.msg);
                    }
                }
            });
		}
	});
}