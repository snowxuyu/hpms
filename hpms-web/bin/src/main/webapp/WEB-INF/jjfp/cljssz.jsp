<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>常量基数维护</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<style>
	.detailCL {
		color: blue;
	}
	</style>
	<script type="text/javascript">
		$(function(){
			//初始化窗口
			$(".easyui-window").window("close");
			
			//默认显示当前年月
			var mydate = new Date();
			var year = mydate.getFullYear();
			var month = mydate.getMonth() + 1;
			month = String(month).length <= 1 ? "0" + month : month;
			$("#q_ny").val(year+"-"+month);
			
			//操作记录
			var operate = "";
			
			//标题数组
			var title = ["常量基数-新增", "常量基数-修改"];
			
			//查询参数
			var searchParams;
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'jjfp/cljssz/findAll',
				rownumbers:true,
		        pagination:true,
		        pageList: [10,14,20],
		        pageSize:14,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        toolbar:"#tb",
		        onDblClickRow: function(rowIndex, rowData){
					update();
				},
				onLoadSuccess: function() {
					$(".detailCL").click(function() {
						window.open($(this).attr("data-url"), "newwindow", "height=400, width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
					});
				},
				columns:[[
					{field:'ck',checkbox:true},	
					{field:'lsh',hidden:true},
					{field:'nd',title:'年度',width:250},
					{field:'yd',title:'月度',width:250},
					{field:'kscl',title:'常量',width:250,
						formatter: function(value,row,index){
							<%-- window.location.href = '<%=basePath %>jjfp/cljssz/showClmx?nd='+row.nd+'&yd='+row.yd; --%>
							return "<span class='detailCL' data-url='<%=basePath %>jjfp/cljssz/showClmx?nd="+row.nd+"&yd="+row.yd+"'>"+value+"</span>";
						}
					}
				]]
			});
			//获取查询参数
			function getSearchParam(){
				searchParams = $("#dg").datagrid("options").queryParams;
			}
			
			function query(){
				getSearchParam();
				searchParams['nd'] = getNd();
				searchParams['yd'] = getYd();
				$("#dg").datagrid("reload");
			}
			
			//点击查询
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
			
			//点击修改
			$("#updateBtn").click(function(){
				update();
			});
			
			//更新操作
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
				$("#ny").val(selectedRow.nd+"-"+selectedRow.yd);
				$("#ks").combobox("setValue", selectedRow.ksnm);
				$("#cl").numberbox("setValue", selectedRow.kscl);
			};
			
			//点击删除
			$("#removeBtn").click(function(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow){
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				$.messager.confirm("确认", "是否删除该条记录?", function(r){
					if (!r) return false;
					$.post("jjfp/cljssz/remove",{lsh:selectedRow.lsh}, function(result){
						if ("success"==result) {
							$.messager.show({
								title:"操作提示",
								msg:"删除成功！",
								timeout:3000
							});
							 $('#dg').datagrid('reload');
						} else {
							$.messager.alert("警告","删除失败！","warning");
						}
					});
				});
			});
			
			//点击确定按钮
			$("#sureBtn").click(function(){
				/* //var op = operate=="add" ? "新增":"删除";
				if (check()) {
					$("#form").form("submit", {
						url: "jjfp/cljssz/"+operate,
						onSubmit: function(){
							setForm();
						},
						success: function(result){
							if ("success" == result){
								$.messager.show({
									title:"操作提示",
									msg:"操作成功！",
									timeout:3000
								});
								$('#dg').datagrid('reload');
			                    $("#window").window("close");
							} else {
								$.messager.alert("警告","操作失败！","warning");
							}
						}
					});
				} */
				var ny = $("#ny").val();
				if (null==ny || ""==ny) {
					$.messager.alert("警告","请选择年月！","warning");
					return false;
				}
				
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				$("#showWin").window("setTitle", "安亭医院"+nd+"年"+yd+"月度科室常量情况表");
				$("#showWin").window("open");
				
				var lastIndex;
				
				$("#dg2").datagrid({
					url:'jjfp/cljssz/findByNYNoPageRows',
					queryParams: {
						"nd":getAddNd(),
						"yd":getAddYd()
					},
					columns : [[
						{field:'ck',checkbox:true},	
						{field:'lsh',hidden:true},
						{field:'ksfl',title:'科室分类',width:250},
						{field:'yjks',title:'一级科室',width:250},
						{field:'ejks',title:'二级科室',width:250},
						{field:'kscl',title:'常量',width:250,editor : {
							type : 'numberbox',
							options : {
									precision : 2
								}
							}
						}
					]],
					onBeforeLoad : function () {
						$(this).datagrid('rejectChanges');
					},
					onDblClickRow : function (rowIndex) {
						if (lastIndex != rowIndex) {
							$("#dg2").datagrid('endEdit', lastIndex);
							$("#dg2").datagrid('beginEdit', rowIndex);
						}
						lastIndex = rowIndex;
					},
					rownumbers:true,
			       	//pagination:true,
			        fit:true,
			        fitColumns:true,
			        singleSelect:true,
			        showFooter:true
				});
			});
			
			$("#okBtn").click(function(){
				var data = $("#dg2").datagrid("getRows");
				var lsh ="";
				var kscl = "";
				$.messager.progress();
				$.each(data, function(n, value){
					lsh += data[n].lsh + ",";
					kscl += data[n].kscl + ",";
				});
				$.post('jjfp/cljssz/update', {lshs:lsh, kscls:kscl},function(res){
					$("#showWin").window("close");
					$("#window").window("close");
					$.messager.progress("close");
					if ("success"==res){
						$.messager.show({
							title:"操作提示",
							msg:"操作成功！",
							timeout:3000
						});} else {
							$.messager.alert("警告",res,"warning");
						}
				});
			});
			
			//向隐藏域赋值
			function setForm(){
				var ny = $("#ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				$("#_nd").val(nd);
				$("#_yd").val(yd);
				/* $("#_ksnm").val($("#ks").combobox("getValue"));
				$("#_kscl").val($("#cl").textbox("getValue")); */
			}
			
			//前端验证
			function check(){
				var ny = $("#ny").val();
				//var ks = $("#ks").combobox("getValue");
				//var cl = $("#cl").numberbox("getValue");
				if (""==ny || null == ny) {
					$.messager.alert("警告","年月不能为空!","warning");
					return false;
				}
				/* if (""==ks || null == ks) {
					$.messager.alert("警告","科室不能为空!","warning");
					return false;
				}
				if (""==cl || null==cl) {
					$.messager.alert("警告","常量不能为空!","warning");
					return false;
				} */
				return true;
			}
			
			/*
			//获取年度
			function getNd(){
				var ny = $("#q_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				return nd;
			}
			
			//获取月度
			function getYd(){
				var ny = $("#q_ny").val();
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				return yd;
			}
			
			*/
			//获取年度
			function getAddNd(){
				var ny = $("#ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				return nd;
			}
			
			//获取月度
			function getAddYd(){
				var ny = $("#ny").val();
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				return yd;
			}
			
			//点击关闭
			$("#cancelBtn").click(function(){
				$(".easyui-window").window("close"); 
			});
			
			$("#noBtn").click(function(){
				$("#showWin").window("close");
			});
			
		});
	</script>
</head>
<body class="easyui-layout">
<!-- 
	<div class="search-condition" title="常量基数筛选" data-options="region:'north', collapsible:false">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			年月:<input id="q_ny" type="text" readonly="readonly" required="required" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
		</fieldset>
	</div> -->
	
	<div title="常量基数明细" data-options="region:'center'">
		<table id="dg"></table>
	</div>
	
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	
	<div id="window" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table width="250px">
				<tr>
					<td>年月<span class="need">*</span>:</td>
					<td>
						<input id="ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="_nd" name="nd" type="hidden" />
						<input id="_yd" name="yd" type="hidden" />
						<input id="_lsh" name="lsh" type="hidden">
					</td>
				</tr>
				<!-- <tr>
					<td>科室<span class="need">*</span>:</td>
					<td>
						<input id="ks" class="easyui-combobox" required="true" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
						<input id="_ksnm" name="ksnm" type="hidden" />
					</td>
				</tr>
				<tr>
					<td>常量<span class="need">*</span>:</td>
					<td>
						<input id="cl" class="easyui-numberbox" required="true" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_kscl" name="kscl" type="hidden">
					</td>
				</tr> -->
				<tr>
					<td colspan="2">
						<a id="sureBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
						&emsp;&emsp;
						<a id="cancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="showWin" class="easyui-window" data-options="maximizable:false,minimizable:false" style="width: 900px;height: 440px;">
		
		<div class="easyui-layout" data-options="fit:true">   
            <div data-options="region:'north'" style="">
            	<table id="dg2" style="height: 370px;"></table>
            </div>   
            <div data-options="region:'center'">
            	<div style="width: 100%;text-align: center;padding: 5px 0">
            	<a id="okBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
				&emsp;&emsp;
				<a id="noBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
            	</div>
            </div>   
        </div> 
	</div>
</body>
</html>