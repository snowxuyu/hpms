<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div class="search-condition"
		data-options="title:'${title }',region:'north',height:30">
		<legend>个人班次得分=岗位次数*(岗位次数+班次季数)</legend>
	</div>
	<div data-options="region:'center'">
		<table id="grid" class="easyui-datagrid"
			data-options="fitColumns: true ,
                          singleSelect:true , rownumbers:true,pagination:true,
                          closable:true,fit:true,showFooter:true">
			<thead>
				<tr>
					<th data-options="field:'gw',width:200">岗位</th>
					<th data-options="field:'gwcs',width:200,align:',right'">岗位次数</th>
					<th data-options="field:'gwxs',width:200,align:',right'">岗位系数</th>
					<th data-options="field:'bcxs',width:200,align:',right'">班次系数</th>
					<th data-options="field:'grbcdf',width:200,align:',right'">个人班次得分</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	$('#grid').datagrid({
		url : 'hpxt/gzlAction/findByPage',
		method: 'get',
		fitColumns: true,
		singleSelect: true,
		rownumbers: true,
		showFooter: true
	});
</script>
</html>