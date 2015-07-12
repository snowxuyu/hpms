<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<% Object ksbh = request.getAttribute("ksbh"); %>
<% Object ygxm = request.getAttribute("ygxm"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
.detail,.detailJJ,.detailJJ2,.detailBF,.detailD {
		color: blue;
		cursor: pointer;
	}
</style>
<script>
    $(function(){
        $("#data").datagrid({
            url:"qmys/jj/gr2",
            rownumbers:true,
            pagination:true,
            pageList:[50,80,100,130,150],
            pageSize:100,
            fit:true,
            fitColumns:true,
            singleSelect:true,
            striped: true,
            queryParams: {
            	nd:<%=nd %>,
            	yd:<%=yd %>,
            	ksbh:<%=ksbh %>
            },
            columns:[[
			    {field:'ygxm',title:'姓名',width:200}, 
			    {field:'kspjj',title:'全院平均奖金*0.8',width:200},    
			    {field:'ygxs',title:'系数',width:200},
			    {field:'pjxs',title:'综合评价系数',width:200},
			    {field:'myd',title:'满意度',width:200},
			    {field:'sfjj',title:'实发奖金',width:160,
			    	formatter: function(value,row,index){
			    		if (value == null) {
			    			return "0.00";
			    		}
			    		return Number(value).toFixed(2);
					}}
			    ]],
			onLoadSuccess: function() {
				
			}
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div title="<%=nd %>年<%=yd %>月<%=ksmc %>奖金情况表" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>