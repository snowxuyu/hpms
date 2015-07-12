<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksbh = request.getAttribute("ksbh"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<% Object fpyz = request.getAttribute("fpyz"); %>
<% Object xmlbdm = request.getAttribute("xmlbdm"); %>
<% Object xmlbmc = request.getAttribute("xmlbmc"); %>
<% Object ygbh = request.getAttribute("ygbh"); %>
<% Object ygxm = request.getAttribute("ygxm"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
.detail {
		color: blue;
		cursor: pointer;
	}
</style>
<script>
    $(function(){
        $("#data").datagrid({
            url:"qmys/cx/ny/fp/lbxm",
            rownumbers:true,
            pagination:true,
            pageList:[50,80,100,130,150],
            pageSize:50,
            fit:true,
            fitColumns:true,
            singleSelect:true,
            striped: true,
            queryParams: {
            	nd:<%=nd %>,
            	yd:<%=yd %>,
            	ksbh:<%=ksbh==""?null:ksbh %>,
            	xmlbdm:"<%=xmlbdm %>",
            	fpyz:<%=fpyz %>,
            	ygbh:<%=ygbh %>
            },
            columns:[[
			    {field:'xmdm',title:'项目代码',width:150},    
			    {field:'xmmc',title:'项目名称',width:350},
			    {field:'rbrvs',title:'RBRVS点数',width:200,
			    	formatter: function(value,row,index){
						if (value == null) {
							return "";
						} else {
							return Number(value).toFixed(2);
						}
					}
			    },
			    {field:'sl',title:'数量',width:200,
			    	formatter: function(value,row,index){
						if (value == null) {
							return "";
						} else {
							return value;
						}
					}
			    },
				{field:'bhgzl',title:'标化工作量',width:200,
			    	formatter: function(value,row,index){
						if (value == null) {
							return "";
						} else {
							<%-- if (typeof(row.xmmc) == "undefined") {
								<% if ("".equals(xmlbdm)) {%>
								return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,<%=ksbh==""?"null":ksbh %>,<%=ksmc==""?"null":ksmc %>,null,null'>"+Number(value).toFixed(2)+"</span>";
								<% } else { %>
								return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,<%=ksbh==""?"null":ksbh %>,<%=ksmc==""?"null":ksmc %>,<%=xmlbdm %>,<%=xmlbmc %>'>"+Number(value).toFixed(2)+"</span>";
								<% } %>
							} --%>
							return Number(value).toFixed(2);
						}
					}
				}
			]],
			onLoadSuccess: function() {
				$(".detail").click(function() {
					window.location.href="<%=basePath %>qmys/cx/ny/fp/lb/grbhgzl?detail=" + $(this).attr("data-detail");
				});
			},
			showFooter:true
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div title="<%=ygxm %><%=nd %>年<%=yd %><%=xmlbmc %>标化工作量明细" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>