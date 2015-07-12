<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国定假设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
	    $('.easyui-window').window('close');
	    var myDate = new Date();
		$('#nd').val(myDate.getFullYear());
	    query();
	});
	
	function query(){
		var nd = $('#nd').val();
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
 			queryParams:{"nd":nd},
  			url:'pbcs/gdjsz/queryAll',
			method:'post',
			columns:[[
	              		{field:'lsh',title:'编号',width:100},
	              		{field:'nd',title:'年度',width:100},
	              		{field:'jqmc',title:'国定假名称',width:100},
	              		{field:'sjd',title:'时间段',width:200},
	              		{field:'sc',title:'时长(天)',width:100},
	              		{field:'bz',title:'备注',width:200}
	                  ]]
		});
	}
	
	var opter = null;
	function insert(){
		$('#win').window('open');
		$("#win").window("setTitle","国定假维护-新增");
		$('#form').form('clear');
		opter = "insert";
	}
	
	function update(){
		var row = $("#dg").datagrid("getSelected");
		if (!row) {
			$.messager.alert("警告", "当前没有选择行！", "warning");
			return;
		}
		$('#win').window('open');
		$("#win").window("setTitle","国定假维护-修改");
		$("#form").form("load",row);
		opter = "update";
	}
	
	function del(){
		var row = $("#dg").datagrid("getSelected");
    	if (!row) {
    		$.messager.alert("警告","当前没有选择行！","warning");
			return false;
		}
    	$.messager.confirm("确认", "是否删除该条记录?", function(r){
    		if(!r) return;
    		$.post("pbcs/gdjsz/remove", {"lsh":row.lsh}, function(res) {
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
	
	function save(){
		if($('#kssj').val() == null || $('#kssj').val() == ""){
			$.messager.alert("警告","开始时间不能为空!","warning");
			return;
		}
		if($('#jzsj').val() == null || $('#jzsj').val() == ""){
			$.messager.alert("警告","截止时间不能为空!","warning");
			return;
		}
		if(new Date($('#kssj').val()) > new Date($('#jzsj').val())){
			$.messager.alert("警告","开始时间无法早于截止时间!","warning");
			return;
		}
		var op = opter == "insert"?"新增":"修改";
		 $.messager.confirm("确认", "是否" + op + "该条记录？", function(r){
       		if(!r) return;
       		$.messager.progress();
	        	$("#form").form("submit", {
	        		url:"pbcs/gdjsz/" + opter,
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
	
	function cancel(){
		$('#win').window('close');
		opter = null;
	}
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="国定假筛选" data-options="region:'north'" >
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年度:<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" class="wdate"/>
		</fieldset>	
	</div>
	<div data-options="region:'center',title:'国定假维护'">
		<table id="dg">
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query()">查询</a>
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="insert()">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
	</div>
	<div id="win" class="easyui-window win" title="国定假维护">
		<form id="form" method="post">
			<table>
				<tr>
				   <td>假期名称<span class="need">*</span>:</td>
				   <td><input type="text" class="easyui-textbox" id="jqmc" 
				   		name="jqmc" data-options="required:'true',validType:'length[0,16]'"/>
				   		<input type="hidden" id="lsh" name="lsh"/></td>
				</tr>
				<tr>
					<td>开始时间<span class="need">*</span>:</td>
					<td><input type="text" name="kssj" id="kssj" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="wdate"/></td>
					<td>截止时间<span class="need">*</span>:</td>
					<td><input type="text" name="jzsj" id="jzsj" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="wdate"/></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td colspan="3"><input type="text" class="easyui-textbox" id="bz" 
						name="bz" data-options="validType:'length[0,100]',width:430,multiline:'true',height:50"/></td>
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
</body>
</html>