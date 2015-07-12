<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksbh = request.getAttribute("ksbh"); %>
<% Object fpyz = request.getAttribute("fpyz"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
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
            url:"qmys/cx/ny/fp",
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
            	fpyz:<%=fpyz %>
            },
            columns:[[
			    {field:'xmlbdm',title:'项目类别代码',width:250},    
			    {field:'xmlbmc',title:'项目类别名称',width:250},
			    {field:'sl',title:'数量',width:200,
			    	formatter: function(value,row,index){
			    		if (value == null) {
			    			return "";
			    		}
			    		return value;
					}
			    },
			    {field:'bhgzl',title:'标化工作量',width:200,
			    	formatter: function(value,row,index){
			    		if (value == null) {
			    			return "";
			    		}
			    		return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,<%=ksbh==""?"null":ksbh %>,<%=fpyz %>,<%=ksmc==""?"null":ksmc %>,"+row.xmlbdm+","+(typeof(row.xmlbmc) == "undefined"?"null":row.xmlbmc)+"'>"+Number(value).toFixed(2)+"</span>";
					}
				},
				{field:'zb',title:'占比（%）',width:200,
					formatter: function(value,row,index){
						if (value != null) {
							return "<div style='background:rgb(184, 226, 232);width:"+value+"%'>"+Number(value).toFixed(2)+"</div>";
						} else {
							return "";
						}
					}
				}
			]],
			onLoadSuccess: function() {
				$(".detail").click(function() {
					window.location.href="<%=basePath %>qmys/cx/ny/fp/lb?detail=" + $(this).attr("data-detail");
				});
			},
			showFooter:true
        });
        var pager = $('#data').datagrid('getPager');
        pager.pagination({    
            buttons:[{
                iconCls:'icon-download',    
                handler:function(){
                	var url="<%=basePath %>qmys/cx/ny/fp/exportExcel?nd=<%=nd %>&yd=<%=yd %>&ksbh=<%=ksbh %>&ksmc=<%=ksmc %>&fpyz=<%=fpyz %>";
        			window.open(url,'ExcelWin','z-look=1');
        			return false;
                }    
            }]   
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div title="<%=nd %>年<%=yd %>月安亭医院<%=ksmc %>标化工作量汇总表" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>