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
<% Object ygbh = request.getAttribute("ygbh"); %>
<% Object ygxm = request.getAttribute("ygxm"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px">
		<p>个人班次得分=岗位次数*(岗位次数+班次季数)</p>
	</div>
	<div data-options="region:'center'" title="<%=ksmc %><%=ygxm %>&nbsp<%=nd %>年<%=yd %>月份个人班次标化工作量">
		<table id="grid">
			<thead>
				<tr>
					<th data-options="field:'bbmc',width:200">岗位</th>
					<th data-options="field:'bbcs',width:200,align:',right'">岗位次数</th>
					<th data-options="field:'gwdz',width:200,align:',right'">岗位系数</th>
					<th data-options="field:'bbdz',width:200,align:',right'">班次系数</th>
					<th data-options="field:'bcdf',width:200,align:',right'">个人班次得分</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	$('#grid').datagrid({
		queryParams:{"ksnm":<%=ksnm %>,"nd":<%=nd %>,"yd":<%=yd %>,"ygbh":<%=ygbh %>},
		url : 'hpxt/gzlAction/findByPage',
		method: 'get',
		fitColumns: true,
		fit:true,
		singleSelect: true,
		rownumbers: true,
		pagination:true,
		showFooter: true
	});
</script>
</html>