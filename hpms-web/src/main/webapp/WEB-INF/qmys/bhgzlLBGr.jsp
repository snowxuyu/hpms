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
<% Object xmlbdm = request.getAttribute("xmlbdm"); %>
<% Object xmlbmc = request.getAttribute("xmlbmc"); %>
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
            url:"qmys/cx/ny/fp/lb/grbhgzl",
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
            	xmlbdm:"<%=xmlbdm %>"
            },
            columns:[[
				{field:'ygxm',title:'姓名',width:200}, 
				{field:'grbhgz',title:'个人标化工作量',width:200,
					formatter: function(value,row,index){
						if (row.ygxm != "合计") {
							return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,<%=ksbh==""?"null":ksbh %>,<%=ksmc==""?"null":ksmc %>,"+row.ygbh+","+row.ygxm+"'>"+Number(value).toFixed(2)+"</span>";
						}
						return Number(value).toFixed(2);
					}	
				}
			]],
			onLoadSuccess: function() {
				$(".detail").click(function() {
					window.location.href="<%=basePath %>qmys/cx/ny/fp/lb/ksgrbhgzl?detail=" + $(this).attr("data-detail");
				});
			},
			showFooter:true
        });
        var pager = $('#data').datagrid('getPager');
        pager.pagination({    
            buttons:[{
                iconCls:'icon-download',    
                handler:function(){
                	var url="<%=basePath %>qmys/cx/ny/fp/lb/gr/exportExcel?nd=<%=nd %>&yd=<%=yd %>&ksbh=<%=ksbh %>&xmlbdm=<%=xmlbdm %>&xmlbmc=<%=xmlbmc %>";
        			window.open(url,'ExcelWin','z-look=1');
        			return false; 
                }
            }]   
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div title="<%=nd %>年<%=yd %>月<%=xmlbmc %>个人标化工作量明细" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>