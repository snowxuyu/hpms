<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>采集手工数据查询</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//初始化 datagrid
			$("#dg").datagrid({
				url:'xtwh/cdmkzc/query',
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        singleSelect:true,
		        columns:[[ 
					{field:'cdbh',title:'菜单编号',width:80}, 
					{field:'cdmc',title:'菜单名称',width:80}, 
					{field:'url',title:'URL',width:80,align:'right', 
						formatter:function(value,rowData,rowIndex){ 

                            //function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
 
                            //alert(rowData.username);  
							 return "<a href='"+rowData.url+"' target='_blank'>链接</a>";
 							
                       }  

					}
					
					]] 

			});
			
			//定义查询参数
			var searchParams;
			
			//查询
			$("#queryBtn").click(function(){
				searchParams = $("#dg").datagrid('options').queryParams;
				$("#dg").datagrid("reload");
			});
			//链接
			function detail(url){
				var row = $("#data").datagrid("getSelected");
				var url=$("#url").val(row.url);
		   		alert(url);
		   	   	url = encodeURI(url);
		   		window.location.href =  url;
		   	}
		  
		   	
			
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div  data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" pagination="true" toolbar="#toolbar" 
			data-options="title:'菜单', fit:true, singleSelect:true, ctrlSelect:true">
				<thead>
					<tr>
					    <th data-options="field:'cdbh',width:100"  >菜单编号</th>
					    <th data-options="field:'cdmc',width:150 ">菜单名称</th>
						<th data-options="field:'url',width:200">链接</th>
					</tr>
			</thead>
		</table>
	</div>
</body>
</html>