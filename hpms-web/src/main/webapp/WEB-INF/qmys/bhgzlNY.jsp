<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
.detail,.detailJJ,.detailJJ2,.detailJJ3,.detailBF,.detailD {
		color: blue;
		cursor: pointer;
	}
</style>
<script>
    $(function(){
        $("#data").datagrid({
            url:"qmys/cx/ny",
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
            	yd:<%=yd %>
            },
            columns:[[
			    {field:'kslbmc',title:'科室类别',width:200}, 
			    {field:'yjks',title:'一级科室',width:200},    
			    {field:'ejks',title:'二级科室',width:200},
			    {field:'fpjj',title:'奖金',width:100,
			    	formatter: function(value,row,index){
			    		if (value == null) {
			    			return "";
			    		}
			    		value = Number(value).toFixed(2);
			    		if (row.kslb == "C" && row.sfbf == "1") {
			    			return "<span class='detailJJ' data-detail='<%=nd %>,<%=yd %>,"+row.ksbh+","+row.ejks+"'>"+value+"</span>";
			    		} else if (row.kslb == "D") {
			    			return "<span class='detailJJ2' data-detail='<%=nd %>,<%=yd %>,"+row.ksbh+","+row.ejks+"'>"+value+"</span>";
			    		} else {
			    			return "<span class='detailJJ3' data-detail='<%=nd %>,<%=yd %>,"+(typeof(row.ksbh) == "undefined"?"null":row.ksbh)+","+(typeof(row.ejks) == "undefined"?"null":row.ejks)+"'>"+value+"</span>";
			    		}
					}
			    },
			    {field:'hj',title:'工作总量',width:160,
                    formatter: function(value,row,index){
                        if (value == null) {
                            return "";
                        }
                        value = Number(value).toFixed(2);
                        if (row.kslb == "C" && row.sfbf == "1") {
                            return "<span class='detailBF' data-detail='<%=nd %>,<%=yd %>,"+row.ksbh+","+row.ejks+"'>"+value+"</span>";
                        } else if (row.kslb == "D") {
                            return "<span class='detailD'>"+value+"</span>";
                        }
                        if (typeof(row.ksbh) == "undefined") {
                        	return "<span class='detailJJ3' data-detail='<%=nd %>,<%=yd %>,"+(typeof(row.ksbh) == "undefined"?"null":row.ksbh)+","+(typeof(row.ejks) == "undefined"?"null":row.ejks)+"'>"+value+"</span>";
                        }
                        return "<span class='detail' data-detail='<%=nd %>,<%=yd %>,"+row.ksbh+",0,"+row.ejks+"'>"+value+"</span>";
                    }
                }
			    ]],
			onLoadSuccess: function() {
				$(".detail").click(function() {
					window.location.href="<%=basePath %>qmys/cx/ny/fp?detail=" + $(this).attr("data-detail");
				});
				$(".detailJJ").click(function() {
					window.location.href="<%=basePath %>qmys/jj/gr?detail=" + $(this).attr("data-detail");
				});
				$(".detailJJ2").click(function() {
					window.location.href="<%=basePath %>qmys/jj/gr2?detail=" + $(this).attr("data-detail");
				});
				$(".detailJJ3").click(function() {
					window.location.href="<%=basePath %>qmys/queryksjjqk?detail=" + $(this).attr("data-detail");
				});
				$(".detailBF").click(function() {
					window.location.href="<%=basePath %>qmys/hlbhgzlAction?detail=" + $(this).attr("data-detail");
				});
			},
			showFooter:true
        });
        var pager = $('#data').datagrid('getPager');    // 得到datagrid的pager对象  
        pager.pagination({    
            buttons:[{
                iconCls:'icon-download',    
                handler:function(){
                	var url="<%=basePath %>qmys/cx/ny/exportExcel?nd=<%=nd %>&yd=<%=yd %>";
        			window.open(url,'ExcelWin','z-look=1');
        			return false;
                }
            }]   
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div title="<%=nd %>年<%=yd %>月安亭医院标化工作量汇总表" data-options="region:'center'">
		<table id="data"></table>
	</div>
</body>
</html>