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
<title>标化工作量表</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
	.detail,.detailgzl,.detaildc,.detailbc {
		color: blue;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({
			fit:true,
			fitColumns:true,
	        singleSelect:true, 
	        rownumbers:true,
	        pagination:true,
	        pageList:[15,20,25],
	        pageSize:15,
	        closable:true,
	        queryParams:{"nd":<%=nd %>,"yd":<%=yd %>,"ksnm":<%=ksnm %>},
			url:'qmys/hlbhgzlAction/queryAll',
			method:'post',
			columns:[[
			         {field:'ygxm',title:'姓名',width:100},
			         {field:'hlgzlds',title:'护理工作量点数',width:100,
			        	 formatter:function(value,row,index){
			        		 if (value == null) {
					    			return "";
					    		}
			        		 return "<span class='detailgzl' data-detail='<%=nd %>,<%=yd %>,<%=ksnm %>,<%=ksmc %>'>"+value+"</span>";
			        	 }	 
			         },
			         {field:'hldcds',title:'护理等次点数',width:100,
			        	 formatter:function(value,row,index){
			        		 if (value == null) {
					    			return "";
					    		}
			        		 return "<span class='detaildc' data-detail='<%=nd %>,<%=yd %>,<%=ksnm %>,<%=ksmc %>'>"+value+"</span>";
			        	 } 
			         },
			         {field:'zh',title:'病房护理标化工作量(前两项相加)',width:210},
			         {field:'grbcdf',title:'个人班次得分',width:100,
			        	 formatter:function(value,row,index){
			        		 if (value == null) {
					    			return "";
					    		}
			        		 return "<span class='detailbc' data-detail='<%=nd %>,<%=yd %>,<%=ksnm %>,<%=ksmc %>,"+row.ygbh+","+row.ygxm+"'>"+value+"</span>";
			        	 }	 
			         },
			         {field:'hlbcdfzh',title:'科室个人班次得分总和',width:200},
			         {field:'grbhgz',title:'护士个人标化工作量',width:150}
			       ]],
			       onLoadSuccess: function() {
			    	   $(".detailgzl").click(function() {
							window.location.href="<%=basePath %>qmys/hlgzldsAction?detail=" + $(this).attr("data-detail");
						});
			    	   $(".detaildc").click(function() {
							window.location.href="<%=basePath %>qmys/xddsbAction?detail=" + $(this).attr("data-detail");
						});
			    	   $(".detailbc").click(function() {
			    		   window.location.href="<%=basePath %>hpxt/gzlAction?detail=" + $(this).attr("data-detail");
						});
			       },
			       showFooter:true
		});
	});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px">
		<p>护士个人标化工作量=病房护理标化工作量*个人班次得分/∑个人班次得分</p>
	</div>
	<div data-options="region:'center'" title="<%=ksmc %><%=nd %>年<%=yd %>月份标化工作量">
		<table id="dg">
		</table>
	</div>
</body>
</html>