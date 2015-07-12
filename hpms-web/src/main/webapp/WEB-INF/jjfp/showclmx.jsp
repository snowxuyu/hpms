<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>常量基数明细</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#window").window("setTitle", "安亭医院"+$("#nd").val()+"年"+$("#yd").val()+"月度科室常量情况表");
			$("#window").window("open");
			
			$("#dg").datagrid({
				url:'jjfp/cljssz/findByNY',
				queryParams: {
					"nd":$("#nd").val(),
					"yd":$("#yd").val()
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        showFooter: true,
		        columns:[[
					{field:'ck',checkbox:true},	
					{field:'lsh',hidden:true},
					{field:'ksfl',title:'科室分类',width:250},
					{field:'yjks',title:'一级科室',width:250},
					{field:'ejks',title:'二级科室',width:250},
					{field:'kscl',title:'常量',width:250,
						formatter: function(value, row, index) {
							if (typeof(row.lsh) == "undefined") {
								return "<span style='color:red;'>"+value+"</span>";
							} else {
								return value;
							}
						}
					}
						
				]]
			});
		});
	</script>
</head>
<body class="easyui-layout">
	<div id="window" style="width: 1000px; height: 400px;" class="easyui-window" data-options="maximizable:false,minimizable:false">
		<input id="nd" type="hidden" value="${nd }">
		<input id="yd" type="hidden" value="${yd }">
		<table id="dg" style="height: 400px;"></table>
	</div>
</body>
</html>