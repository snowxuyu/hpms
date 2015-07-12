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
<title>相对点数表</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
	.detail,.detailhlf,.detailzlf{
		color: blue;
		cursor: pointer;
	}
</style>
</head>
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
			url:'qmys/xddsbAction/queryAll',
			method:'post',
			columns:[[
						{field:'zhlf',title:'科室总护理费',width:200,
							 formatter:function(value,row,index){
								 if (value == null) {
						    			return "";
						    		}
								 return "<span class='detailhlf' data-detail='<%=nd %>,<%=yd %>,<%=ksnm %>,<%=ksmc %>'>"+value+"</span>";
							 }	
						},
						{field:'zzlf',title:'科室总治疗费',width:200,
							formatter:function(value,row,index){
								 if (value == null) {
						    			return "";
						    		}
								 return "<span class='detailzlf' data-detail='<%=nd %>,<%=yd %>,<%=ksnm %>,<%=ksmc %>'>"+value+"</span>";
							 }
						},
						{field:'hlxsde',title:'护理系数',width:150},
						{field:'hlssde',title:'护理时数',width:150},
						{field:'hlhdrs',title:'核对人数',width:150},
						{field:'hldcds',title:'护理等次点数',width:200,
					    	formatter: function(value,row,index){
					    		if (value == null) {
					    			return "0.00";
					    		}
					    		return Number(value).toFixed(2);
							}}
			        ]],
			        onLoadSuccess: function() {
			        	$(".detailhlf").click(function() {
			        		window.location.href="<%=basePath %>qmys/xddsbAction/hlmx?detail=" + $(this).attr("data-detail");
			        	});
			    	   $(".detailzlf").click(function() {
			    		   window.location.href="<%=basePath %>qmys/xddsbAction/zlmx?detail=" + $(this).attr("data-detail");
			    	   });
			        }
		});
	});
</script>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px">
		<p>护理等次点数=【(科室护理费+护理治疗费)*护理时数/(床日数*核定人数)】*护理系数</p>
	</div>
	<div data-options="region:'center'" title="<%=ksmc %><%=nd %>年<%=yd %>月度相对点数表">
		<table id="dg">
		</table>
	</div>
</body>
</html>