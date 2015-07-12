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
    	var queryParams;
    	var _date = new Date();
       	var _year = _date.getFullYear();
       	var _month = _date.getMonth() + 1;
       	
       	var to_month = _year + "-" + ((_month < 10) ? ("0" + _month) : _month);
       	$("#ny2").val(to_month);
       	$("#ny1").val(to_month);
        $("#data").datagrid({
            url:"qmys/cx/ny/sr",
            rownumbers:true,
            pagination:true,
            pageList:[50,80,100,130,150],
            pageSize:50,
            fit:true,
            fitColumns:true,
            singleSelect:true,
            striped: true,
            toolbar:"#tb",
			columns:[[
			    {field:'p2',title:'科室名称',width:250},
			    {field:'p3',title:'项目代码',width:250},
			    {field:'p4',title:'项目名称',width:250},
			    {field:'p5',title:'价格',width:250},
			    {field:'p6',title:'RBRVS',width:250},
			    {field:'p7',title:'数量',width:250}
			]],
			queryParams: {
				ny1:$("#ny1").val(),
				ny2:$("#ny2").val()
            },
			onLoadSuccess: function() {
				
			},
			showFooter:true
        });
      //获取数据表格参数方法
        function getParams() {
        	queryParams = $("#data").datagrid("options").queryParams;
		}
    	//查询
       	function query() {
       		getParams();
    		queryParams["ny1"] = $("#ny1").val();
    		queryParams["ny2"] = $("#ny2").val();
    		queryParams["ksnm"] = $("#ks").combobox("getValue");
    		$("#data").datagrid("load");
       	} 
      //条件查询
        $("#searchBtn").click(function() {
        	query();
        });
        var pager = $('#data').datagrid('getPager');
        pager.pagination({    
            buttons:[{
                iconCls:'icon-download',    
                handler:function(){
                	var url="<%=basePath %>qmys/cx/ny/sr/exportExcel";
        			window.open(url,'ExcelWin','z-look=1');
        			return false; 
                }    
            }]   
        });
    });
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="条件筛选" data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			科室名称:<input id="ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
			&emsp;&emsp;
			开始日期：<input type="text" name="ny1" id="ny1" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" 
							class="wdate" style="width: 200px;"/>
			&emsp;&emsp;
			结束日期：<input type="text" name="ny2" id="ny2" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" 
							class="wdate" style="width: 200px;"/>
		</fieldset>
	</div>
	<div title="标化工作量及收入表" data-options="region:'center'">
		<table id="data"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</body>
</html>