<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String at_hpms = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/at_hpms/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksnm = request.getAttribute("ksnm"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<!DOCTYPE html>
<html>
<head>
	<style>
		A {text-decoration: NONE} 
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>奖金查询</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<style type="text/css">
	.detailBHL, .detailJJ3, .detailKS, .detailGR {
		cursor: pointer;
		color: blue;
	}
	</style>
	<script type="text/javascript">
		//设置额外查询参数
		var searchParams;
	
		$(function(){
			$("#dg").datagrid({
				url:'qmys/queryksjjqk/findAll',
				queryParams: {
					ksnm:<%=ksnm%>,
					nd:<%=nd%>,
					yd:<%=yd%>
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        pageList:[50,80,100,130,150],
	            pageSize:100,
	            striped: true,
	            columns:[[
	      			    {field:'lsh',hidden:true},
						{field:'ksnm',hidden:true},
	      			    {field:'ksmc',title:'科室名称',width:150},  
	      			  	{field:'bhgzl',title:'标化工作量',width:150,
	      			    	formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			return "";
	      			    		}
	      			    		if (typeof(row.lsh) == "undefined") {
	      			    			return "<span class='detailBHL' data-detail='<%=nd%>,<%=yd%>,"+"null"+",0,"+"null"+"'>"+value+"</span>";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailBHL' data-detail='<%=nd%>,<%=yd%>,"+row.ksnm+",0,"+row.ksmc+"'>"+value+"</span>";
	      					}
	      			    },
	      				{field:'dj',title:'单价',width:120},
	      				{field:'cl',title:'常量',width:120},
	      				{field:'pjxs',title:'综合评价系数',width:150,
	      					formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			return "";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return '<a href="javascript:detailZhpjxs();">' + value + '</a>';
	      					}
	      				},
	      				{field:'myd',title:'满意度',width:120},  
	      			  	{field:'kszxjj',title:'科室专项奖金',width:100,
	      					formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			value = "0.0";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailKS' data-detail='<%=nd%>,<%=yd%>,"+(typeof(row.lsh) == "undefined"?"null":row.ksnm)+","+(typeof(row.lsh) == "undefined"?"null":row.ksmc)+"'>"+value+"</span>";
	      					}
	      			  	},
	      			    {field:'grzxjj',title:'个人专项奖金',width:100,
		      			  	formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			value = "0.0";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailGR' data-detail='<%=nd%>,<%=yd%>,"+(typeof(row.lsh) == "undefined"?"null":row.ksnm)+","+(typeof(row.lsh) == "undefined"?"null":row.ksmc)+",null'>"+value+"</span>";
	      					}
	      			    },
	      				{field:'fpjj',title:'实发奖金',width:150,
	      			    	formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			return "";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		if (typeof(row.myd) == "undefined") {
	      			    			return value;
	      			    		}
	      			    		return "<span class='detailJJ3' data-detail='<%=nd%>,<%=yd%>,"+(typeof(row.ksnm) == "undefined"?"null":row.ksnm)+","+(typeof(row.ksmc) == "undefined"?"null":row.ksmc)+"'>"+value+"</span>";
	      					}
	      				}
	      			    ]],
	      			onLoadSuccess: function() {
	      				$(".detailBHL").click(function() {
	      					window.location.href="<%=basePath %>qmys/cx/ny/fp?detail=" + $(this).attr("data-detail");
	      				});
	      				$(".detailJJ3").click(function() {
							window.location.href="<%=basePath %>qmys/queryksjjqk?detail=" + $(this).attr("data-detail");
						});
	      				$(".detailKS").click(function() {
							window.location.href="<%=basePath %>qmys/cx/zx/ks?detail=" + $(this).attr("data-detail");
						});
	      				$(".detailGR").click(function() {
							window.location.href="<%=basePath %>qmys/cx/zx/gr?detail=" + $(this).attr("data-detail");
						});
	      			},
	      			showFooter:true
			});
			
			//查询实现
			function query(){
				searchParams = $("#dg").datagrid("options").queryParams; 
				$("#dg").datagrid("reload");
			}
			
		});
		function detailZhpjxs(){
			window.location.href="<%=at_hpms %>index_right.jsp?u=superadmin&p=1qaz-pl,&m=com@rongda@view@bpe@BPE_JGJXSCModule.swf";  
		}
	</script>
</head>
<body class="easyui-layout">
	<div title="安亭医院${nd }年${yd }月度奖金分配情况表（总表）" data-options="region:'center'">
		<table id="dg"></table>
	</div>
</body>
</html>