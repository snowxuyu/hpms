<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>项目单价维护</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		var selected = "";
		 $(function(){
				//默认显示当前年月
				var mydate = new Date();
				var year = mydate.getFullYear();
				var month = mydate.getMonth() + 1;
				month = String(month).length <= 1 ? "0" + month : month;
				$("#n_nd").val(year);
		    	//操作类型
		    	var operate = "";
		    	//标题数组
		   		var arrTitle = ["项目单价-新增","项目单价-修改"];
		    	//数据表格
		    	var queryParams;
		        $("#data").datagrid({
		            url:"jjfp/ndcs/query",
		            queryParams: {
						"nd":getNd()
					},
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
		       		var nd=document.getElementById("n_nd").value;
		    		//queryParams['ny'] = $("#n_ny").textbox('getValue');
		    		queryParams['nd'] = nd;
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
		        		$.post("jjfp/ndcs/remove", {"jxbh":row.jxbh}, function(res) {
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
				        		url:"jjfp/ndcs/" + operate,
				        		onSubmit: function(param) {
				        			setForm();
				        			check();
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
					                 } else {
					                       $.messager.alert('警告','操作失败!',"warning");
					                 }
				        		}
				        	});
			        	})
		        		
		        	}
		        	
		        });
		    	function setForm() {
					$("#_jxnd").val($("#jxnd").val());
					$("#_jxdj").val($("#jxdj").numberbox("getValue"));
					
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
		        	$("#jxnd").val(row.jxnd);
		        	$("#_jxbh").val(row.jxbh);
		        	$("#jxdj").numberbox("setValue",row.jxdj);
				}
		    	//验证
		        function check(){
		        	var nd=$("#jxnd").val();
		        	var je=$("#jxdj").numberbox("getValue");
					if (nd =="" || nd ==null) {
						$.messager.alert('警告','年度不能为空!',"warning");
						return false;
					}
					if (je=="" ||je==null) {
						$.messager.alert('警告','项目单价不能为空!',"warning");
						return false;
					}
					return true;
				}
		      //获取年度
				function getNd(){
					var nd = $("#n_nd").val();
					return nd;
				}
		    });
		
		
		
	</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="项目单价筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年度：<input id="n_nd"  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" class="wdate" />
		</fieldset>
	</div>
	<div data-options="region:'center'">
		<table id="data" class="easyui-datagrid" pagination="true" toolbar="#toolbar" 
			data-options="title:'项目单价明细', fit:true, singleSelect:true, ctrlSelect:true">
				<thead>
					<tr>
					    <th data-options="field:'ck',checkbox:true"></th>
					    <th field="jxbh"  hidden="true"></th>
						<th field="jxnd"  width="150">年度</th>
						<th field="jxdj"  width="150">项目单价</th>
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
			<table width="250px">
				<tr>
					<td>年度<span class="need">*</span>:</td>
					<td>
						<input id="jxnd" class="text" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy'})"/>
						<input id="_jxnd" type="hidden" name="jxnd">	
					</td>
				</tr>
				<tr>
					<td>项目单价<span class="need">*</span>:</td>
					<td>
						<input id="jxdj" class="easyui-numberbox" data-options="required:true,min:0,max:999999,precision:2,missingMessage:'必须输入数字'"></input>
						<input id="_jxdj" type="hidden" name="jxdj">
						<input id="_jxbh" type="hidden" name="jxbh">
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