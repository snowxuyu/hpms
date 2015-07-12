<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>人力成本预算</title>
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
					url:'jjfp/rlcbys/query/ck',
					singleSelect:true,
		            toolbar:"#tb",
		            fit:true,
		            fitColumns:true,
					onDblClickRow:function(rowIndex, rowData){
						/* $("#ysDiv").load("jjfp/rlcbys/query/ck/nd",{"nd":rowData.nd}); */
						var url='<%=basePath %>jjfp/rlcbys/query/ck/nd?nd='+rowData.nd;
						window.open(url,'YSWin');
						//window.open(url,'YSWin','width='+ (window.screen.availWidth-10)+',height='+(window.screen.availHeight-50)+ ',top=0,left=0,resizable=yes,toolbar=yes,location=yes,scrollbars=yes');
					}
				});
			})
		</script>
	</head>
<body class="easyui-layout">
	<div class="search-condition" title="人力成本预算筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年度：<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" 
							class="wdate" style="width: 150px;"/>
		</fieldset>
	</div>
	<div title="人力成本预算" data-options="region:'center'">
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'nd',width:70">年度</th>
					<th data-options="field:'yszje',width:120">预算总金额</th>
					<th data-options="field:'ztName',width:60">状态</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>

	<!-- <div class="search-condition" title="人力成本预算" data-options="region:'west'" style="width:300px">
		<div style="margin-bottom: 20px">
			年度：<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" 
							class="wdate" style="width: 150px;"/>
			<a id="searchBtn" class="easyui-linkbutton searchBtn" iconCls="icon-search">查询</a>
		</div>
		<table id="data">
			<thead>
				<tr>
					<th data-options="field:'nd',width:70">年度</th>
					<th data-options="field:'yszje',width:120">预算总金额</th>
					<th data-options="field:'ztName',width:60">状态</th>
				</tr>
			</thead>
		</table>
			
	</div>
	
	<div data-options="region:'center'">
		<div id="ysDiv" style="padding-top: 20px">
		</div>
	</div> -->
</body>
</html>