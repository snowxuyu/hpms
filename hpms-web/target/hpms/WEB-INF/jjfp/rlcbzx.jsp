<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>人力成本执行</title>
		<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
		<script>
			$(function(){
				$("#nd").val(new Date().getFullYear());
				$("#searchBtn").click(function() {
					var queryParams = $("#data").datagrid("options").queryParams;
					queryParams["nd"] = $("#nd").val();
			    	$("#data").datagrid("load");
				})
				$("#data").datagrid({
					url:'jjfp/rlcbzx/query',
					columns:[[
						{field:'ck'},
						{field:'nd',title:'年度',width:60},    
						{field:'yszje',title:'预算总金额（万）',width:110},    
						{field:'sjzje',title:'当前执行金额（万）',width:110},  
						{field:'zxl',title:'执行率',width:100,
							formatter: function(value,row,index){
								if (value == null || value == "") {
									return "0%";
								} else {
									return (value*100).toFixed(2)+"%";
								}
							}
						},
						{field:'ztName',title:'预算审核状态',width:100}
					]],
					singleSelect:true,
		            toolbar:"#tb",
		            fit:true,
		            fitColumns:true,
					onDblClickRow:function(rowIndex, rowData){
						/* $("#zxDiv").load("jjfp/rlcbzx/query/nd",{"nd":rowData.nd}); */
						window.location.href='<%=basePath %>jjfp/rlcbzx/query/nd?nd='+rowData.nd;
					}
				});
			})
		</script>
	</head>
<body class="easyui-layout">
	<div class="search-condition" title="人力成本预算执行筛选" data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年度：<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" 
							class="wdate" style="width: 150px;"/>
		</fieldset>
	</div>
	<div title="人力成本预算执行" data-options="region:'center'">
		<table id="data"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>

	<!-- <div class="search-condition" title="人力成本执行" data-options="region:'west'" style="width:300px">
		<div style="margin-bottom: 20px">
			年度：<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" 
							class="wdate" style="width: 150px;"/>
			<a id="searchBtn" class="easyui-linkbutton searchBtn" iconCls="icon-search">查询</a>
		</div>
		<table id="data"></table>
			
	</div>
	<div data-options="region:'center'">
		<div id="zxDiv" style="padding-top: 20px">
		</div>
	</div> -->
</body>
</html>