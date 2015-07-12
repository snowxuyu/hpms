<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核心项目类别维护</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
    $(function(){
    	//操作类型
    	var operate = "";
    	//标题数组
   		var arrTitle = ["核心项目类别-新增","核心项目类别-修改"];
    	//数据表格
    	var queryParams;
    	var editingId;
        $("#data").treegrid({
            url:"yljx/xmlbdz/treegrid",
            idField:"xmlbbm",
            treeField:"xmlbmc",
            rownumbers:true,
            fit:true,
            singleSelect:true,
            toolbar:"#tb",
            onDblClickRow:function(rowIndex, rowData){
   				update();
   			}
        });
    	//获取数据表格参数方法
        function getParams() {
        	queryParams = $('#data').treegrid('options').queryParams;
		}
    	//新增
        $("#addBtn").click(function() {
        	var row = $("#data").treegrid("getSelected");
        	operate = "add";
        	$("#form").form("reset");
        	if(row) {
        		$("#fjxm").combotree("setValue",row.xmlbbm);
        	}
        	$("#win").window("setTitle",arrTitle[0]);
        	$("#win").window("open");
        });
    	//更新
        $("#updateBtn").click(function() {
        	update();
        });
        $("#saveUpdateBtn").click(function() {
        	if (editingId != undefined){
        		$('#data').treegrid('endEdit', editingId);
        		var row = $("#data").datagrid("getSelected");
        		$.post("yljx/xmlbdz/treegrid/update", 
        				{
        					"xmlbbm":row.xmlbbm,
        					"xmlbmc":row.xmlbmc,
        					"bzxx":row.bzxx
        				}, 
        				function(res) {
        				if (res == "success"){
							$('#data').datagrid('reload');
							$.messager.show({
								title:"操作提示",
								msg:"更新操作成功！",
								timeout:3000,
								showType:'slide'
							});
	                 } else {
							$.messager.alert('警告','更新数据失败!');
	                 }
        		});
        		editingId = undefined;
        	}
        });
        $("#updateUndoBtn").click(function() {
        	if (editingId != undefined){
        		$('#data').treegrid('cancelEdit', editingId);
        		editingId = undefined;
        	}
        });
    	//删除
        $("#removeBtn").click(function() {
        	var row = $("#data").treegrid("getSelected");
        	if (!row) {
        		$.messager.alert("警告","当前没有选择行！","warning");
				return false;
			}
        	$.messager.confirm("确认", "是否删除该条记录?", function(r){
        		if(!r) return;
        		$.post("yljx/xmlbdz/treegrid/remove", {"xmlbbm":row.xmlbbm}, function(res) {
        			if (res == "success"){
        				$("#data").treegrid("remove",row.xmlbbm);
						$("#win").window("close");
						$.messager.show({
							title:"操作提示",
							msg:"删除操作成功！",
							timeout:3000,
							showType:"slide"
						});
	                 } else {
	                       $.messager.alert('警告','删除数据失败!');
	                 }
        		});
    		});
        	
        });
    	//处理新增
        $("#saveBtn").click(function() {
        	$.messager.confirm("确认", "是否新增该条记录?", function(r){
        		if(!r) return;
        		$.messager.progress();
	        	$("#form").form('submit', {
	        		url:"yljx/xmlbdz/treegrid/add",
	        		onSubmit: function(param) {
	        			var isValid = $(this).form("validate");
	        			if (!isValid){
	        				$.messager.progress("close");
	        			}
	        			setForm();
	        			return isValid;
	        		},
	        		success: function(res) {
	        			if (res == "success"){
							$("#data").treegrid("reload");
							$("#win").window("close");
							$.messager.progress("close");
							$.messager.show({
								title:"操作提示",
								msg: op + "操作成功！",
								timeout:3000,
								showType:"slide"
							});
						} else {
							$.messager.progress("close");
							$.messager.alert("警告","操作失败！");
						}
	        		}
	        	});
        	})
        });
    	$("#refreshBtn").click(function() {
    		var row = $("#data").treegrid("getSelected");
    		$("#data").treegrid("refresh");
    	});
    	function setForm() {
			$("#_fjxm").val($("#fjxm").combotree("getValue"));
			$("#_xmjb").val($("input[name='xmjb']:checked").val());
			$("#_xmmc").val($("#xmmc").textbox("getValue"));
			$("#_bzxx").val($("#BZXX").textbox("getValue"));
		}
        function update() {
        	if (editingId != undefined){
	     		$('#data').treegrid("select", editingId);
	     		return;
	     	}
     		var row = $("#data").treegrid("getSelected");
     		if (row){
	     		editingId = row.xmlbbm;
	     		$("#data").treegrid("beginEdit", editingId);
			}
		}
    });
</script>
</head>
<body class="easyui-layout">
	<div title="核心项目类别维护" data-options="region:'center'">
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'xmlbbm'" hidden="true"></th>
					<th data-options="field:'xmlbmc',width:200,editor:'text'">项目名称</th>
					<th data-options="field:'bzxx',width:200,editor:'text'">备注信息</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a id="saveUpdateBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
        <a id="updateUndoBtn" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a id="refreshBtn" class="easyui-linkbutton" iconCls="icon-refresh" plain="true">刷新</a>
	</div>
	
	<div id="win" class="easyui-window win" title="核心项目类别-新增">
		<form id="form" method="post">
			<table>
				<tr>
					<td>父级项目：</td>
					<td>
						<select id="fjxm" class="easyui-combotree" style="width: 200px" data-options="url:'yljx/xmlbdz/tree',required:true,lines:true"></select>
						<input id="_fjxm" type="hidden" name="fjxm">
					</td>
				</tr>
				<tr>
					<td>项目级别：</td>
					<td>
						<input id="xmjb1" type="radio" name="xmjb" value="0"></input><label for="xmjb1">同级</label>
						&emsp;&emsp;
						<input id="xmjb2" type="radio" name="xmjb" value="1" checked="checked"></input><label for="xmjb2">子级</label>
						<input id="_xmjb" type="hidden" name="xmjb">
					</td>
				</tr>
				<tr>
					<td>项目名称：</td>
					<td>
						<input id="xmlbmc" type="text" class="easyui-textbox" data-options="required:true"></input>
						<input id="_xmlbmc" type="hidden" name="xmlbmc">
					</td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td>
						<input id="bzxx" type="text" class="easyui-textbox"></input>
						<input id="_bzxx" type="hidden" name="bzxx">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a id="saveBtn" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
						&emsp;&emsp;
	        			<a id="cancelBtn" class="easyui-linkbutton cancel" iconCls="icon-no">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>
