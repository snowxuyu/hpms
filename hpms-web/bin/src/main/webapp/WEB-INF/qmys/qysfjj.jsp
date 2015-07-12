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
<title>奖金情况表</title>
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
            queryParams:{"nd":<%=nd %>,"yd":<%=yd %>,"ksnm":<%=ksnm %>},
			url:'qmys/qysfjjAction/queryAll',
			method:'post'
		});
	});
</script>
</head>
<body class="easyui-layout" >
	<div data-options="region:'center'" title="<%=ksmc %><%=nd %>年<%=yd %>月度奖金情况表">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'ksmc',width:200">姓名</th>
					<th data-options="field:'qypjjj',width:200">全院平均奖*0.8</th>
					<th data-options="field:'dj',width:200">系数</th>
					<th data-options="field:'pjxs',width:200">综合评价系数</th>
					<th data-options="field:'myd',width:200">满意度</th>
					<th data-options="field:'fpjj',width:200">实发奖金</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>