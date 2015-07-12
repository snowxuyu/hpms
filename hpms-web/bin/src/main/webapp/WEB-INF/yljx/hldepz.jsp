<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>护理基础数据</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		var selected = "";
		 $(function(){
		    	//操作类型
		    	var operate = "";
		    	//标题数组
		   		var arrTitle = ["护理基础数据-新增","护理基础数据-修改"];
		    	//数据表格
		    	var queryParams;
		        $("#data").datagrid({
		            url:"yljx/hldepz/query",
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
		        	queryParams = $('#data').datagrid('options').queryParams;
				}
		    	//查询
		       	function query() {
		       		getParams();
		    		queryParams['ksnm'] = $("#s_ks").combobox('getValue');
		    		$('#data').datagrid('load');
		       	} 
		    	//条件查询
		        $("#searchBtn").click(function() {
		        	query();
		        });
		    	//新增
		        $("#addBtn").click(function() {
		        	operate = "add";
		        	$("#form").form("clear");
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
		        		$.post("yljx/hldepz/remove", {"lsh":row.lsh}, function(res) {
		        			if (res == "success"){
			        				$.messager.show({
				                   		title:"操作提示",
				                   		msg:"删除成功！",
				                   		timeout:3000
				                   	});
			                       $('#data').datagrid('reload');
			                       $("#win").window("close");
			                 } else {
			                       $.messager.alert('警告','删除失败!',"warning");
			                 }
		        		});
		    		});
		        	
		        });
		    	//处理新增、修改
		        $("#saveBtn").click(function() {
		        	if(check()){
		        	var op = operate == "add"?"新增":"修改";
		        	$.messager.confirm("确认", "是否" + op + "该条记录?", function(r){
		        		if(!r) return;
			        	$("#form").form("submit", {
			        		url:"yljx/hldepz/" + operate,
			        		onSubmit: function(param) {
			        			setForm();
			        		},
			        		success: function(res) {
			        			if (res == "success"){
				        				$.messager.show({
					                   		title:"操作提示",
					                   		msg:"操作成功！",
					                   		timeout:3000
					                   	});
				                       $('#data').datagrid('reload');
				                       $("#win").window("close");
				                 }else {
				                       $.messager.alert('警告','操作失败!',"warning");
				                 }
			        		}
			        	});
		        	})
		        	}
		        });
		    	function setForm() {
		    		$("#_ksnm").val($("#ks").combobox('getValue'));
					$("#_hlssde").val($("#hlssde").numberbox("getValue"));
					$("#_hlxsde").val($("#hlxsde").numberbox("getValue"));
					$("#_hldsde").val($("#hldsde").numberbox("getValue"));
					//$("#_hlhdry").val($("#hlhdry").numberbox("getValue"));
				}
		    	//更新数据传递
		        function update() {
		        	var row = $("#data").datagrid("getSelected");
		        	if (null == row) {
		        		$.messager.alert("警告","当前没有选择行！","warning");
						return false;
					}
		        	operate = "update";
		        	if(!row) return;
		        	$("#win").window("setTitle",arrTitle[1]);
		        	$("#win").window("open");
		        	$("#ks").combobox('setValue',row.ksnm);
		        	$("#_lsh").val(row.lsh);
		        	$("#hlssde").numberbox("setValue",row.hlssde);
		        	$("#hlxsde").numberbox("setValue",row.hlxsde);
		        	$("#hldsde").numberbox("setValue",row.hldsde);
		        	//$("#hlhdry").numberbox("setValue",row.hlhdry);
				}
		    	//验证
		        function check(){
		        	var ks=$("#ks").combobox("getValue");
		        	var hlssde=$("#hlssde").numberbox("getValue");
		        	var hlxsde=$("#hlxsde").numberbox("getValue");
		        	var hldsde=$("#hldsde").numberbox("getValue");
		        	//var hlhdry=$("#hlhdry").numberbox("getValue");
					if (ks=="" || ks ==null) {
						$.messager.alert('警告','科室不能为空!',"warning");
						return false;
					}
					if (hlssde=="" ||hlssde==null) {
						$.messager.alert('警告','护理时数定额不能为空!',"warning");
						return false;
					}
					if (hlxsde=="" ||hlxsde==null) {
						$.messager.alert('警告','护理系数定额不能为空!',"warning");
						return false;
					}
					if (hldsde=="" ||hldsde==null) {
						$.messager.alert('警告','护理点数定额不能为空!',"warning");
						return false;
					}
					/*
					if (hlhdry=="" ||hlhdry==null) {
						$.messager.alert('警告','护理核定人员不能为空!',"warning");
						return false;
					}*/
					return true;
					
				}
		    });
		
		
		
	</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="护理基础数据检索"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			科室名称：<input id="s_ks" class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'text',url:'yljx/ks/query'" />
		</fieldset>
	</div>
	<div data-options="region:'center'">
		<table id="data" class="easyui-datagrid" pagination="true" toolbar="#toolbar" 
			data-options="title:'护理基础数据', fit:true, singleSelect:true, ctrlSelect:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="lsh" hidden="true"></th>
						<th field="ksnm"  hidden="true">科室内码</th>
						<th field="ksmc" width="150">科室</th>
						<th field="hlssde" width="180">护理时数定额</th>
						<th field="hlxsde"  width="150">护理系数定额</th>
						<th field="hldsde"  width="150">护理点数定额</th>
						<th field="hlhdry"  width="150" hidden="true">护理核定人员</th>
					</tr>
				</thead>
		</table>
	</div>

	<div id="tb">
			<a id="searchBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
			<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<div id="win" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table>
				<tr>
					<td>护理科室<span class="need">*</span>:</td>
					<td>
						<input id="ks" class="easyui-combobox" data-options="required:true,valueField:'id',textField:'text',url:'yljx/ks/query'" />
						<input id="_ksnm" type="hidden" name="ksnm">	
						<input id="_lsh" type="hidden" name="lsh">
					</td>
				</tr>
				<tr>
						<td>护理时数定额<span class="need">*</span>:</td>
					<td>
						<input id="hlssde" class="easyui-numberbox" data-options="required:true,min:0,max:999999,precision:2,missingMessage:'必须输入数字'" ></input>
						<input id="_hlssde" type="hidden" name="hlssde">
					</td>
				</tr>
				<tr>
					<td>护理系数定额<span class="need">*</span>:</td>
					<td>
						<input id="hlxsde" class="easyui-numberbox"data-options="required:true,min:0,max:999999,precision:2,missingMessage:'必须输入数字'" ></input>
						<input id="_hlxsde" type="hidden" name="hlxsde">
					</td>
				</tr>
				<tr>
					<td>护理点数定额<span class="need">*</span>:</td>
					<td>
						<input id="hldsde" class="easyui-numberbox" data-options="required:true,min:0,max:999999,precision:2,missingMessage:'必须输入数字'"  ></input>
						<input id="_hldsde" type="hidden" name="hldsde">
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