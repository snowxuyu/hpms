<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>护理排班日常导入</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
    $(function(){
    	$("#win").window("open");
    	var _date = new Date();
       	var _year = _date.getFullYear();
       	var _month = _date.getMonth() + 1;
       	
       	var to_month = _year + "-" + ((_month < 10) ? ("0" + _month) : _month);
       	$("#ny").val(to_month);
    	//操作类型
    	var operate = "";
        /* $("#data").datagrid({
            url:"yljx/pbbdr/query",
            rownumbers:true,
            pagination:true,
            fit:true,
            fitColumns: true,
            singleSelect:true,
            toolbar:"#tb"
        });
    	//查询
       	function query() {
       		var queryParams = $("#data").datagrid("options").queryParams;
    		queryParams["ksmc"] = $("#s_ks").textbox("getValue");
    		queryParams["ygmc"] = $("#s_yg").textbox("getValue");
    		$("#data").datagrid("load");
    		
       	} 
    	//条件查询
        $("#searchBtn").click(function() {
        	query();
        }); */
    	//导入
        $("#addBtn").click(function() {
        	operate = "add";
        	$("#form").form("reset");
        	$("#win").window("open");
        });
    	$("#cancelBtn").click(function() {
    		$("#form").form("reset");
    	});
    	//处理导入
        $("#saveBtn").click(function() {
        	if ($("#ny").val() == "" || $("#ny").val() == null) {
				$.messager.alert("警告","年月不能为空！","warning");
				$.messager.progress("close");
				return false;
			}
        	if ($("#ks").combobox("getValue") == null || $("#ks").combobox("getValue") == "") {
				$.messager.alert("警告","科室不能为空！","warning");
				return false;
			}
			if ($("#file").val() == "" || $("#file").val() == null) {
				$.messager.alert("警告","Excel文件不能为空！","warning");
				$.messager.progress("close");
				return false;
			}
			$("#_ksnm").val($("#ks").combobox("getValue"));
        	$.messager.confirm("确认", "是否立即导入?", function(r){
        		if(!r) return;
        		$.messager.progress();
	        	$("#form").form("submit", {
	        		url:"yljx/pbbdr/import",
	        		onSubmit: function(param) {},
	        		success: function(res) {
						if (res == "success"){
							//$("#data").datagrid("reload");
							//$("#win").window("close");
							$.messager.progress("close");
							$.messager.show({
								title:"导入提示",
								msg: "数据导入成功！",
								timeout:3000,
								showType:"slide"
							});
						} else if (res == null || res == "") {
							$.messager.progress("close");
							$.messager.alert("警告","服务器超时未响应！","error");
						} else {
							$.messager.progress("close");
							$.messager.alert("警告",res,"error");
						}
	        		}
	        	});
        	})
        });
    });
</script>
</head>
<body class="easyui-layout">
	<!-- 
	<div class="search-condition" title="护理排班日常导入检索"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			科室名称：<input id="s_ks" class="easyui-textbox" type="text" />
			&emsp;&emsp;
			员工姓名：<input id="s_yg" class="easyui-textbox" type="text">
		</fieldset>
	</div>
	<div title="护理排班日常导入信息" data-options="region:'center'">
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'nd',width:150">年度</th>
					<th data-options="field:'yd',width:150">月度</th>
					<th data-options="field:'ksmc',width:250">科室名称</th>
					<th data-options="field:'ygmc',width:150">员工姓名</th>
					<th data-options="field:'bbmc',width:150">班别</th>
					<th data-options="field:'bbsl',width:200">班别数量</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-upload" plain="true">导入排班表</a>
	</div>
	 -->
	<div id="win" class="easyui-window win" title="导入排班表" closable="false">
		<form id="form" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>年月 <span class="need">*</span>：</td>
					<td>
						<input type="text" name="ny" id="ny" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" 
							class="wdate" style="width: 200px;"/>
					</td>
				</tr>
				<tr>
					<td>应用科室：</td>
					<td>
						<input id="ks" class="easyui-combobox" data-options="valueField:'id',textField:'text',url:'yljx/ks/query',required:true" />
						<input id="_ksnm" type="hidden" name="ksnm">
					</td>
				</tr>
				<tr>
					<td>导入路径 <span class="need">*</span>：</td>
					<td>
						<input id="file" name="file" type="file" style="width:200px;" accept="application/msexcel">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="saveBtn" class="easyui-linkbutton" iconCls="icon-ok">导入</a>
						&emsp;&emsp;
	        			<a id="cancelBtn" class="easyui-linkbutton" iconCls="icon-no">清除</a>
					</td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>