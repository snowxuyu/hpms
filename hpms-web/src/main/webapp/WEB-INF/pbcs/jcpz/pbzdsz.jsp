<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班别字典设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
	    $('.easyui-window').window('close');
	    $('#bbnb').combobox({
			url : 'pbcs/pbzdsz/queryList',
			required : false,
			valueField : 'id',
			textField : 'text'
		});
	    $('#bbfl').combobox({
			url : 'pbcs/pbzdsz/queryList',
			required : true,
			valueField : 'id',
			textField : 'text'
		});
 		query();
	});

	function query(){
  		var bbnm = $('#bbnb').combobox('getValue');
		$('#dg').datagrid({
			fit:true,
			fitColumns:true,
            singleSelect:true,
            rownumbers:true,
            pagination:true,
            pageSize:18,
            pageList:[18,20,22],
            closable:true,
            toolbar:"#tb",
 			queryParams:{"bbnm":bbnm},
 			url:'pbcs/pbzdsz/queryAll',
			method:'post',
			columns:[[
	              		{field:'bbbm',title:'编码',width:100},
	              		{field:'bbmc',title:'名称',width:100},
	              		{field:'bbjx',title:'显示',width:100},
	              		{field:'bbfl',title:'所属类别',width:100},
	              		{field:'bz',title:'备注',width:300}
	                  ]]
		});
	}
	
	var opter = null;
	function insert(){
		$('#win').window('open');
		$("#win").window("setTitle","班别字典维护-新增");
		$('#form').form('clear');
		$('#bbbm').textbox({
			 readonly:false
		 });
		opter = "insert";
	}

	function update() {
		var row = $("#dg").datagrid("getSelected");
		if (!row) {
			$.messager.alert("警告", "当前没有选择行！", "warning");
			return;
		}
		$('#win').window('open');
		$("#win").window("setTitle","班别字典维护-修改");
		 $("#form").form("load",row);
		 $('#bbbm').textbox({
			 readonly:true
		 });
		 opter = "update";
	}

	function del() {
		var row = $("#dg").datagrid("getSelected");
    	if (!row) {
    		$.messager.alert("警告","当前没有选择行！","warning");
			return false;
		}
    	$.messager.confirm("确认", "是否删除该条记录?", function(r){
    		if(!r) return;
    		$.post("pbcs/pbzdsz/remove", {"bbbm":row.bbbm}, function(res) {
    			if (res == "success"){
						$("#dg").datagrid("reload");
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
	}

	function save() {
			 var op = opter == "insert"?"新增":"修改";
			 $.messager.confirm("确认", "是否" + op + "该条记录？", function(r){
	        		if(!r) return;
	        		$.messager.progress();
		        	$("#form").form("submit", {
		        		url:"pbcs/pbzdsz/" + opter,
		        		onSubmit: function(param) {
		        			var isValid = $(this).form("validate");
		        			if (!isValid){
		        				$.messager.progress("close");
		        			}
		        			return isValid;
		        		},
		        		success: function(res) {
		        			$.messager.progress("close");
							if (res == "success"){
								$("#dg").datagrid("reload");
								$("#win").window("close");
								$.messager.show({
									title:"操作提示",
									msg: op + "成功！",
									timeout:3000
								});
								opter = null;
							} else {
			                	 $.messager.alert("警告",op+"失败！","error");
							}
		        		}
		        	});
	        	});
		 }

	function cancel() {
		$('#win').window('close');
		opter = null;
	}
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="班别字典筛选" data-options="region:'north'" >
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			班别类别:<input type="text" id="bbnb"/>
		</fieldset>	
	</div>
	<div data-options="region:'center',title:'班别字典维护'">
		<table id="dg">
		</table>
	</div>
	<div id="win" class="easyui-window win" title="班别字典维护">
		<form id="form" method="post">
			<table>
				<tr>
				   <td>编码<span class="need">*</span>:</td>
				   <td><input type="text" class="easyui-textbox" id="bbbm" 
				   		name="bbbm" data-options="required:'true',validType:'length[0,16]'"/></td>
				   <td>名称<span class="need">*</span>:</td>
				   <td><input type="text" class="easyui-textbox" id="bbmc" 
				   		name="bbmc" data-options="required:'true',validType:'length[0,20]'"/></td>
				</tr>
				<tr>
					<td>显示<span class="need">*</span>:</td>
					<td><input type="text" class="easyui-textbox" id="bbjx" 
						name="bbjx" data-options="required:'true',validType:'length[0,16]'"/></td>
					<td>班别次数<span class="need">*</span>:</td>
					<td><input type="text" class="easyui-numberbox" id="bbcs" 
					    name="bbcs" precision="2" data-options="required:'true',validType:'length[0,12]'"/></td>
				</tr>
				<tr>
					<td>所属类别<span class="need">*</span>:</td>
					<td><input type="text" class="easyui-combobox" id="bbfl" 
					    name="bbfl"/></td>
					<td>备注:</td>
					<td><input type="text" class="easyui-textbox" id="bz" 
						name="bz" data-options="validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="saveBtn" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
						&emsp;&emsp;
	        			<a id="cancelBtn" class="easyui-linkbutton cancel" iconCls="icon-no" onclick="cancel()">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query()">查询</a>
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="insert()">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
	</div>
</body>
</html>