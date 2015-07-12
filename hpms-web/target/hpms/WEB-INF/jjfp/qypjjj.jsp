<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>全院平均奖金维护</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		var selected = "";
		 $(function(){
			//初始化窗口
				$(".easyui-window").window("close");
				//默认显示当前年月
				var mydate = new Date();
				var year = mydate.getFullYear();
				var month = mydate.getMonth() + 1;
				month = String(month).length <= 1 ? "0" + month : month;
				$("#n_ny").val(year+"-"+month);
		    	//操作类型
		    	var operate = "";
		    	//标题数组
		   		var arrTitle = ["全院平均奖金-新增","全院平奖金-修改"];
		    	//数据表格
		    	var queryParams;
		    	//查询参数
		    	var searchParams;
		        $("#data").datagrid({
		            url:"jjfp/qypjjj/query",
		            queryParams: {
						"nd":getNd(),
						"yd":getYd()
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
		       		//var ny1=document.getElementById("n_ny").value;
		    		var ny=$("#n_ny").val();
		    		var nd = ny.substring(0, ny.indexOf("-"));
		    		var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
		    		queryParams['nd'] =nd;
		    		queryParams['yd'] =yd;
		    		$('#data').datagrid('load');
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
		        		$.post("jjfp/qypjjj/remove", {"lsh":row.lsh}, function(res) {
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
				        		url:"jjfp/qypjjj/" + operate,
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
					                 } else {
					                       $.messager.alert('警告','操作失败!',"warning");
					                 }
				        		}
				        	});
			        	})
		        		
		        	}
		        	
		        });
		       
		    	function setForm() {
		    		var ny=document.getElementById("ny").value;
					var nd = ny.substring(0, ny.indexOf("-"));
					var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
					$("#_nd").val(nd);
					$("#_yd").val(yd);
					$("#_ksnm").val($("#ks").combobox("getValue"));
					$("#_kspjj").val($("#kspjj").numberbox("getValue"));
					
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
		        	$("#_lsh").val(row.lsh);
		        	$("#ny").val(row.nd+"-"+row.yd);
		        	$("#ks").combobox('setValue',row.ksnm);
		        	$("#kspjj").numberbox("setValue",row.kspjj);
		        	
				}
		      //验证
		        function check(){
		        	var ny=$("#ny").val();
		        	var ks=$("#ks").combobox("getValue");
		        	var je=$("#kspjj").numberbox("getValue");
					if (ny=="" || ny ==null) {
						$.messager.alert('警告','年月不能为空!',"warning");
						return false;
					}
					if (ks=="" ||ks==null) {
						$.messager.alert('警告','科室不能为空!',"warning");
						return false;
					}
					if (je=="" ||je==null) {
						$.messager.alert('警告','金额不能为空!',"warning");
						return false;
					}
					return true;
				}
		    	//获取年度
				function getNd(){
					var ny = $("#n_ny").val();
					var nd = ny.substring(0, ny.indexOf("-"));
					return nd;
				}
				
				//获取月度
				function getYd(){
					var ny = $("#n_ny").val();
					var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
					return yd;
				}
				
		    });

	</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="全院平均奖金筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年月：<input id="n_ny"  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="wdate" />
		</fieldset>
	</div>
	<div data-options="region:'center'">
		<table id="data" class="easyui-datagrid" pagination="true" toolbar="#toolbar" 
			data-options="title:'全院平均奖金明细', fit:true, singleSelect:true, ctrlSelect:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="lsh" hidden="true"></th>
						<th field="ksnm" width="160">科室内码</th>
						<th field="nd" width="150">年度</th>
						<th field="yd" width="180">月度</th>
						<th field="ksmc"  width="150">科室</th>
						<th field="kspjj"  width="150">全院平均奖</th>
					</tr>
				</thead>
		</table>
	</div>
	
	<div id="tb" >
			<a id="searchBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
			<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<div id="win" class="easyui-window win"  data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table width="250px">
				<tr>
					<td>年月<span class="need">*</span>:</td>
					<td>
					<input id="ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="_nd" name="nd" type="hidden" />
						<input id="_yd" name="yd" type="hidden" />
						<input id="_lsh" type="hidden" name="lsh">
					</td>
				</tr>
				<tr>
					<td>科室<span class="need">*</span>:</td>
					<td>
						<input id="ks" class="easyui-combobox" data-options="required:true,valueField:'id',textField:'text',url:'yljx/ks/query'" />
						<input id="_ksnm" type="hidden" name="ksnm">	
					</td>
				</tr>
				<tr>
					<td>金额<span class="need">*</span>:</td>
					<td>
						<input id="kspjj" class="easyui-numberbox" data-options="required:true,max:999999,precision:2,missingMessage:'必须输入数字'" ></input>
						<input id="_kspjj" type="hidden" name="kspjj">
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