<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String at_hpms = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/at_hpms/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ygxm = request.getAttribute("ygxm"); %>
<% Object ygbh = request.getAttribute("ygbh"); %>
<% Object ksbh = request.getAttribute("ksbh"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
.detail,.detailGR,.detailBF {
		color: blue;
		cursor: pointer;
	}
</style>
<script>
    $(function(){
        $("#data").datagrid({
            url:"jjfp/jj/gr",
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
            	ygbh:<%=ygbh %>
            },
            columns:[[
			    {field:'ygxm',title:'姓名',width:200}, 
			    {field:'grbhgz',title:'个人标化工作量',width:200,
			    	formatter: function(value,row,index){
			    		return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,<%=ksbh==""?"null":ksbh %>,<%=ksmc==""?"null":ksmc %>,"+row.ygbh+","+row.ygxm+"'>"+Number(value).toFixed(2)+"</span>";
					}},
			    {field:'pjxs',title:'综合评价系数',width:200,
   					formatter: function(value,row,index){
   			    		if (value == null) {
   			    			return "";
   			    		}
   			    		value = Number(value).toFixed(2);
   			    		return '<a href="javascript:detailZhpjxs();">' + value + '</a>';
   					}
   				},
			    {field:'myd',title:'满意度',width:200},
			    {field:'zxjj',title:'个人专项',width:200,
      			  	formatter: function(value,row,index){
  			    		if (value == null) {
  			    			value = "0.0";
  			    		}
  			    		value = Number(value).toFixed(2);
  			    		return "<span class='detailGR' data-detail='<%=nd%>,<%=yd%>,"+"null"+","+"null"+"'>"+value+"</span>";
  					}
  			    },
			    ]],
			onLoadSuccess: function() {
				$(".detail").click(function() {
					window.location.href="<%=basePath %>qmys/cx/ny/fp/lb/ksgrbhgzl?detail=" + $(this).attr("data-detail");
				});
				$(".detailBF").click(function() {
					window.location.href="<%=basePath %>qmys/hlbhgzlAction?detail=" + $(this).attr("data-detail");
				});
				$(".detailGR").click(function() {
					window.location.href="<%=basePath %>qmys/cx/zx/gr?detail=" + $(this).attr("data-detail");
				});
			}
        });
    });
    function detailZhpjxs(){
		window.location.href="<%=at_hpms %>index_right.jsp?u=superadmin&p=1qaz-pl,&m=com@rongda@view@bpe@BPE_JGJXSCModule.swf";  
	}
</script>
</head>
<body class="easyui-layout">
	<div title="<%=ygxm %><%=nd %>年<%=yd %>月奖金情况表" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>