<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>班别点数配置</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//初始化class为easyui-window的div不显示
			$(".easyui-window").window("close");  
			
			//操作类型
			var operate = "";
			
			//标题数组
			var title = ["班别系数-新增", "班别系数-修改"];
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'yljx/bbdzdz/findBypage',
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        toolbar:"#tb",
				onDblClickRow: function(rowIndex, rowData){
					update();
				}
			});
			
			//表格中的数据
			var queryParams;
			
			//获取表格的查询参数
			function getQueryParam(){
				queryParams = $('#dg').datagrid('options').queryParams;
			}
			
			//查询
			function query(){
				getQueryParam();
				queryParams['ksnm'] = $("#q_ks").combobox("getValue");
				$("#dg").datagrid("reload");
			}
			
			//点击查询按钮
			$("#queryBtn").click(function(){
				query();
			});
			
			//点击新增
			$("#addBtn").click(function(){
				operate = "add";
				$("#form").form("reset");
				$("#window").window("setTitle",title[0]);
				$("#window").window("open");
			});
			
			//点击更新
			$("#updateBtn").click(function(){
				update();
			});
			
			//获取到点击表格的数据
			function update(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow){
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				operate = "update";
				$("#window").window("setTitle",title[1]);
				$("#window").window("open");
				
				$("#_lsh").val(selectedRow.lsh);
				$("#bb").combobox("setValue",selectedRow.bbbm);
				$("#bbdz").numberbox("setValue", selectedRow.bbdz);
				$("#gwxs").numberbox("setValue", selectedRow.gwxs);
				$("#ks").combobox("setValue", selectedRow.ksnm);
			}
			
			//保存
			$("#saveBtn").click(function(){
				
				if (check()){
					$("#form").form("submit",{
						url: "yljx/bbdzdz/"+operate,
						onSubmit: function(){
							setForm();
						},
						success: function(result){
							if ("success" == result) {
								$.messager.alert('提示','操作成功!');
								$('#dg').datagrid('reload');
			                    $("#window").window("close");
							} else {
								$.messager.alert('提示','操作失败!');
								$("#window").window("close");
							}
						}
					});
				}
			});
			
			//删除
			$("#removeBtn").click(function(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow) {
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				$.messager.confirm("确认", "是否删除该条记录?", function(r){
					if (!r) return;
					$.post("yljx/bbdzdz/removeById", {lsh:selectedRow.lsh}, function(result){
						if ("success" == result) {
							 $.messager.alert('提示','删除成功!');
							 $('#dg').datagrid('reload');
						} else {
							$.messager.alert('提示','删除失败!');
						}
					});
				});
			});
			
			//向隐藏域赋值
			function setForm(){
				$("#_bbmc").val($("#bb").combobox("getText"));
				$("#_bbbm").val($("#bb").combobox("getValue"));
				$("#_bbdz").val($("#bbdz").numberbox("getValue"));
				$("#_gwxs").val($("#gwxs").numberbox("getValue"))
				$("#_ksnm").val($("#ks").combobox("getValue"));
			}
			
			//前端验证
			function check(){
				var bb = $("#bb").combobox("getValue");
				var dz = $("#bbdz").numberbox("getValue");
				var gw = $("#gwxs").numberbox("getValue");
				if (null==bb || ""==bb){
					$.messager.alert("警告","班别名称不能为空!","warning");
					return false;
				}
				if (""==dz || null ==dz){
					$.messager.alert("警告","班别点值不能为空!","warning");
					return false;
				}
				if (""==gw|| null ==gw){
					$.messager.alert("警告","岗位系数不能为空!","warning");
					return false;
				}
				return true;
			}
			
			
			//点击取消按钮
			$("#cancelBtn").click(function(){
				$(".easyui-window").window("close"); 
			});
		});
	</script>

</head>
<body class="easyui-layout">
	<div title="班别系数筛选" class="search-condition" data-options="region:'north',collapsible:false">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			科室名称:<input id="q_ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/> 
		</fieldset>
	</div>
	<div title="班别系数明细" data-options="region:'center'">
		<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="lsh" hidden="true"></th>
						<th field="bbbm" width="150">班别编码</th>
						<th field="bbmc" width="160">班别名称</th>
						<th field="bbdz" width="150">班别点值</th>
						<th field="gwxs" width="150">岗位系数</th>
						<th field="ksmc" width="180">应用科室</th>
						<th field="ksnm" hidden="true"></th>
					</tr>
				</thead>
		</table>
	</div>
	
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	
	<div id="window" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table style="width: 300px">
				<tr>
					<td>班 别名称<span class="need">*</span>:</td>
					<td>
						<input id="bb" class="easyui-combobox" required="required" data-options="valueField:'id',textField:'text',url:'yljx/bbfl/query'"/>
						<input id="_bbmc" name="bbmc" type="hidden"/>
						<input id="_bbbm" name="bbbm" type="hidden"/>
						<input id="_lsh" name="lsh" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>班别点值<span class="need">*</span>:</td>
					<td>
						<input id="bbdz" class="easyui-numberbox" required="required" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_bbdz" name="bbdz" type="hidden">
					</td>
				</tr>
				<tr>
					<td>岗位系 数<span class="need">*</span>:</td>
					<td>
						<input id="gwxs" class="easyui-numberbox" required="required" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_gwxs" name="gwxs" type="hidden">
					</td>
				</tr>
				<tr>
					<td>应用科室:</td>
					<td>
						<input id="ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
						<input id="_ksnm" name="ksnm" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a id="saveBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
						&emsp;&emsp;
						<a id="cancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>