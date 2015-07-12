<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位系数配置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
    $(function(){
    	//操作类型
    	var operate = "";
    	//标题数组
   		var arrTitle = ["岗位系数配置-新增","岗位系数配置-修改"];
    	//数据表格
    	var queryParams;
        $("#data").datagrid({
            url:"yljx/gwxspz/query",
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
    		queryParams["ksnm"] = $("#s_ks").combobox("getValue");
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
        		$.post("yljx/gwxspz/remove", {"lsh":row.lsh}, function(res) {
        			if (res == "success"){
							$("#data").datagrid("reload");
							$("#win").window("close");
							$.messager.show({
								title:"操作提示",
								msg:"删除成功！",
								timeout:3000
							});
	                 } else {
	                	 $.messager.alert("警告","删除失败！","error");
	                 }
        		});
    		});
        	
        });
    	//处理新增、修改
        $("#saveBtn").click(function() {
        	if ($("#gw").combobox("getValue") == null || $("#gw").combobox("getValue") == "") {
				$.messager.alert("警告","岗位不能为空！","warning");
				return false;
			}
        	var op = operate == "add"?"新增":"修改";
        	$.messager.confirm("确认", "是否" + op + "该条记录？", function(r){
        		if(!r) return;
        		$.messager.progress();
	        	$("#form").form("submit", {
	        		url:"yljx/gwxspz/" + operate,
	        		onSubmit: function(param) {
	        			var isValid = $(this).form("validate");
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
						} else {
		                	 $.messager.alert("警告",op+"失败！","error");
						}
	        		}
	        	});
        	})
        });
    	function setForm() {
    		$("#_gwbm").val($("#gw").combobox("getValue"));
			$("#_gwmc").val($("#gw").combobox("getText"));
			$("#_gwxs").val($("#gwxs").numberbox("getValue"));
			$("#_ksnm").val($("#ks").combobox("getValue"));
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
        	$("#_lsh").val(row.lsh);
        	$("#gw").combobox("setValue",row.gwbm);
        	$("#gwxs").numberbox("setValue",row.gwxs);
        	$("#ks").combobox("setValue",row.ksnm);
		}
    });
</script>
</head>
<body class="easyui-layout">
	<!-- <div class="search-condition" title="岗位系数配置"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			科室名称：<input id="s_ks" class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'text',url:'yljx/ks/query'" />
		</fieldset>
	</div> -->
	<div title="岗位系数明细" data-options="region:'center'">
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'lsh'" hidden="true"></th>
					<th data-options="field:'gwbm',width:200">岗位编码</th>
					<th data-options="field:'gwmc',width:200">岗位名称</th>
					<th data-options="field:'gwxs',width:200">岗位系数</th>
					<th data-options="field:'ksnm'" hidden="true">科室内码</th>
					<th data-options="field:'ksmc',width:250">应用科室</th>
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
	
	<div id="win" class="easyui-window win" title="岗位系数配置-新增">
		<form id="form" method="post">
			<table style="width: 300px">
				<tr>
					<td>岗位名称 <span class="need">*</span>：</td>
					<td>
						<input id="gw" class="easyui-combobox" data-options="valueField:'id',textField:'text',url:'yljx/gw/query',required:true"/></input>
						<input id="_gwbm" type="hidden" name="gwbm">
						<input id="_gwmc" type="hidden" name="gwmc">
						<input id="_lsh" type="hidden" name="lsh">
					</td>
				</tr>
				<tr>
					<td>岗位系数 <span class="need">*</span>：</td>
					<td>
						<input id="gwxs" type="text" class="easyui-numberbox" data-options="required:true,precision:2,max:999.99"></input>
						<input id="_gwxs" type="hidden" name="gwxs">
					</td>
				</tr>
				<tr>
					<td>应用科室：</td>
					<td>
						<input id="ks" class="easyui-combobox" data-options="valueField:'id',textField:'text',url:'yljx/ks/query'" />
						<input id="_ksnm" type="hidden" name="ksnm">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
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