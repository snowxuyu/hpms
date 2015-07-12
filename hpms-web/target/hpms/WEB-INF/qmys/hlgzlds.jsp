<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksnm = request.getAttribute("ksnm"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标化工作量</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({
			fit:true,
			fitColumns:true,
	        singleSelect:true, 
	        rownumbers:true,
	        pagination:true,
	        closable:true,
	        queryParams:{"ksnm":<%=ksnm %>,"nd":<%=nd %>,"yd":<%=yd %>},
			url:'qmys/hlgzldsAction/queryAll',
			method:'post'
		});
	});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px">
		<p>护理工作量点数=(床日数+入院人数*0.5+出院人数*0.5)*护理点数</p>
	</div>
	<div data-options="region:'center'" title="<%=ksmc %><%=nd %>年<%=yd %>月标化工作量">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'crs',width:200,formatter: function(value,row,index){
			    		return Number(value).toFixed(2);
					}">床日数</th>
					<th data-options="field:'ryrs',width:200">入院人数</th>
					<th data-options="field:'cyrs',width:200">出院人数</th>
					<th data-options="field:'hlgzlds',width:200,formatter: function(value,row,index){
			    		return Number(value).toFixed(2);
					}">护理工作量点数</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>