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
	<title>人员编制设置</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
			var lastIndex;
			
			//查询参数
			var searchParams;
			
			//默认当前年月
			function getYearMonth() {
				var mydate = new Date();
				var year = mydate.getFullYear();
				var month = mydate.getMonth() + 1;
				month = String(month).length <= 1 ? "0" + month : month;
				return year+"-"+month;
			}
			
			$("#q_ny").val(getYearMonth());
			
			
			function getNd() {
				var ny = $("#q_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				return nd;
			}
			
			function getYd() {
				var ny = $("#q_ny").val();
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				return yd;
			}
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'jcpz/rybzsz/findListNoPage',
				rownumbers:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        toolbar:"#tb",
		        queryParams: {
					"nd":getNd(),
					"yd":getYd()
				},
		        columns:[[
					{field:'ck',checkbox:true},	
					{field:'lsh',hidden:true},
					{field:'ksnm',hidden:true},
					{field:'nd',hidden:true},
					{field:'yd',hidden:true},
					{field:'rq',title:'日期',width:250,formatter: function(value){
							if (value=="null-null"){
								return value="";
							} else {
								return value;
							}
						}
					},
					{field:'ksmc',title:'科室名称',width:250},
					{field:'bzs',title:'编制人数',width:250,editor : {
						type : 'numberbox',
						options : {
								precision : 0
							}
						}}
				]],
			 	onBeforeLoad : function () {
					$(this).datagrid('rejectChanges');
				},
				onDblClickRow : function (rowIndex) {
					if (lastIndex != rowIndex) {
						$("#dg").datagrid('endEdit', lastIndex);
						$("#dg").datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
			
			$("#queryBtn").click(function(){
				query();
			});
			
			$("#saveBtn").click(function(){
				save();
			});
			
			$("#copyBtn").click(function() {
				copyList();
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
			
			function save(){
				$("#dg").datagrid('endEdit', lastIndex);
				var datas = $("#dg").datagrid("getRows");
				var data = "";
				var nd = getNd();
				var yd = getYd();
				$.messager.progress();
				$.each(datas, function(n, values){
					data += datas[n].lsh+","+datas[n].ksnm+","+datas[n].nd+","+datas[n].yd+","+datas[n].bzs+","+datas[n].ksmc+";";
				});
				$.post("jcpz/rybzsz/update", {data:data, nd:nd, yd:yd}, function(res){
					$.messager.progress("close");
					if ("success"==res){
						$.messager.show({
							title:"操作提示",
							msg:"操作成功！",
							timeout:3000
						});
						 $('#dg').datagrid('load');
					} else {
							$.messager.alert("警告",res,"warning");
						}
				});
				
			}
			
			function copyList() {
				var nd = getNd();
				var yd = getYd();
				$.messager.progress();
				$.post("jcpz/rybzsz/copyLast", {nd:nd, yd:yd}, function(res){
					$.messager.progress("close");
					if ("success"==res){
						$.messager.show({
							title:"操作提示",
							msg:"操作成功！",
							timeout:3000
						});
						 $('#dg').datagrid('load');
					} else {
							$.messager.alert("警告",res,"warning");
						}
				});
				
			}
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="人员编制筛选" class="search-condition" data-options="region:'north' ,collapsible:false" style="overflow-y: hidden">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			年月<span class="need">*</span>:<input id="q_ny" type="text" readonly="readonly" required="required" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
		</fieldset>
	</div>
	
	<div title="人员编制设置" data-options="region:'center'">
		<table id="dg"></table>
	</div>
	
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="saveBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:true">保存</a>
		<a id="copyBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-copy', plain:true">复制上期</a>
	</div>
</body>
</html>