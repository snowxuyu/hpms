<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核心项目类别配置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
    $(function(){
    	//操作类型
    	var operate = "";
    	//标题数组
   		var arrTitle = ["核心项目类别维护-新增","核心项目类别维护-修改"];
    	//数据表格
    	var queryParams;
        $("#data").datagrid({
            url:"yljx/hxxmlb/query",
            rownumbers:true,
            pagination:true,
            fit:true,
            fitColumns:true,
            singleSelect:true,
            toolbar:"#tb",
            onDblClickRow:function(rowIndex, rowData){
   				update();
   			}
        });
    	//获取数据表格参数方法
        function getParams() {
        	queryParams = $("#data").datagrid("options").queryParams;
		}
    	//查询
       	function query() {
       		getParams();
    		queryParams["xmlbmc"] = $("#s_xmlbmc").textbox("getValue");
    		$("#data").datagrid("load");
       	} 
    	//条件查询
        $("#searchBtn").click(function() {
        	query();
        });
    	//新增
        $("#addBtn").click(function() {
        	operate = "add";
        	$("#form").form("reset");
        	$("#xmlbbm").textbox({"disabled":false});
        	//$("#fjlb").combobox({"disabled":false});
        	$("#win").window("setTitle",arrTitle[0]);
        	$("#win").window("open");
        });
    	//更新
        $("#updateBtn").click(function() {
        	update();
        });
    	//删除
        $("#removeBtn").click(function() {
        	var row = $("#data").datagrid("getSelected");
        	if (!row) {
        		$.messager.alert("警告","当前没有选择行！","warning");
				return false;
			}
        	$.messager.confirm("确认", "是否删除该条记录?", function(r){
        		if(!r) return;
        		$.post("yljx/hxxmlb/remove", {"xmlbbm":row.xmlbbm}, function(res) {
        			if (res == "success"){
							$("#data").datagrid("reload");
							$("#win").window("close");
							$.messager.show({
								title:"操作提示",
								msg:"删除成功！",
								timeout:3000
							});
	                 } else if (res == "exist") {
							$.messager.alert("警告","该记录已被占用，不可删除！","error");
	                 } else {
							$.messager.alert("警告","删除失败！","error");
	                 }
        		});
    		});
        	
        });
    	//处理新增、修改
        $("#saveBtn").click(function() {
        	var isValid = $("#form").form("validate");
        	if (!isValid){
				return false;
			}
        	var op = operate == "add"?"新增":"修改";
        	$.messager.confirm("确认", "是否" + op + "该条记录？", function(r){
        		if(!r) return;
        		$.messager.progress();
	        	$("#form").form("submit", {
	        		url:"yljx/hxxmlb/" + operate,
	        		onSubmit: function(param) {
	        			if (!isValid){
	        				$.messager.progress("close");
	        			}
	        			setForm();
	        			return isValid;
	        		},
	        		success: function(res) {
	        			$.messager.progress("close");
						if (res == "success"){
							$("#data").datagrid("reload");
							$("#win").window("close");
							$.messager.show({
								title:"操作提示",
								msg: op + "成功！",
								timeout:3000
							});
						} else if (res == "exist") {
							$.messager.alert("警告","类别编码已存在，"+op+"失败！","error");
						} else {
							$.messager.alert("警告",op+"失败！","error");
						}
	        		}
	        	});
        	})
        });
    	function setForm() {
			$("#_xmlbbm").val($("#xmlbbm").textbox("getValue"));
        	$("#_xmlbmc").val($("#xmlbmc").textbox("getValue"));
        	//$("#_fjlb").val($("#fjlb").combobox("getValue"));
        	$("#_bzxx").val($("#bzxx").textbox("getValue"));
        	//alert($("#_fjlb").val());
		}
    	//更新数据传递
        function update() {
        	var row = $("#data").datagrid("getSelected");
        	if (!row) {
        		$.messager.alert("警告","当前没有选择行！","warning");
				return false;
			}
        	operate = "update";
        	$("#win").window("setTitle",arrTitle[1]);
        	$("#win").window("open");
        	
        	$("#xmlbbm").textbox("setValue",row.xmlbbm);
        	$("#xmlbbm").textbox({"disabled":true});
        	$("#xmlbmc").textbox("setValue",row.xmlbmc);
        	
        	/* if (row.fjbm == null || row.fjbm == "") {
        		$("#fjlb").combobox({"disabled":true});
			} else {
				$("#fjlb").combobox({"disabled":false});
				$("#fjlb").combobox("select",row.fjbm);
			} */
        	$("#bzxx").textbox("setValue",row.bzxx);
		}
    });
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="核心项目类别筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			类别名称：<input id="s_xmlbmc" type="text" class="easyui-textbox"/>
		</fieldset>
	</div>
	<div title="核心项目类别维护" data-options="region:'center'">
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'xmlbbm',width:200">类别编码</th>
					<th data-options="field:'xmlbmc',width:250">类别名称</th>
					<th data-options="field:'fjbm'" hidden="true"></th>
					<!-- <th data-options="field:'fjmc',width:250">父级名称</th> -->
					<th data-options="field:'bzxx',width:250">备注信息</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	
	<div id="win" class="easyui-window win" title="核心项目类别维护-新增">
		<form id="form" method="post">
			<table style="width: 300px">
				<tr>
					<td>类别编码 <span class="need">*</span>：</td>
					<td>
						<input id="xmlbbm" class="easyui-textbox" type="text" data-options="validType:['length[0,10]']" required="true" /></input>
						<input id="_xmlbbm" type="hidden" name="xmlbbm">
					</td>
				</tr>
				<tr>
					<td>类别名称 <span class="need">*</span>：</td>
					<td>
						<input id="xmlbmc" class="easyui-textbox" type="text" data-options="validType:['length[0,50]']" required="true"/></input>
						<input id="_xmlbmc" type="hidden" name="xmlbmc">
					</td>
				</tr>
				<!-- <tr>
					<td>父级类别：</td>
					<td>
						<input id="fjlb" class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'text',url:'yljx/hxxmlb/query/fj'" />
						<input id="_fjlb" type="hidden" name="fjbm">
					</td>
				</tr> -->
				<tr>
					<td>备注信息：</td>
					<td>
						<input id="bzxx" class="easyui-textbox" type="text" data-options="validType:['length[0,100]']"/></input>
						<input id="_bzxx" type="hidden" name="bzxx">
					</td>
				</tr>
				<tr>
					<td colspan="2"  style="text-align: center;">
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