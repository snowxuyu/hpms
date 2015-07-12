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
<title>治疗明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
    $("#data").datagrid({
        url:"qmys/xddsbAction/queryHl",
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        pageList:[20,40,60,80,100],
        pageSize:20,
        fit:true,
        singleSelect:true,
        queryParams: {
        	nd:<%=nd %>,
        	yd:<%=yd %>,
        	ksnm:<%=ksnm %>,
        	xmlbbm:'hx08',
        },
        columns:[[
		    {field:'xmbm',title:'项目代码',width:150},    
		    {field:'xmmc',title:'项目名称',width:350},
		    {field:'dsdeb',title:'项目点数',width:200},
		    {field:'sl',title:'项目数量',width:200},
			{field:'bhl',title:'治疗标化工作量',width:200,
		    	formatter: function(value,row,index){
		    		if (value == null) {
		    			return "0.00";
		    		}
		    		return Number(value).toFixed(2);
				}}
		]]
    });
});
</script>
</head>
<body class="easyui-layout">
	<div title="<%=ksmc %>-<%=nd %>年<%=yd %>月-治疗明细表" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>