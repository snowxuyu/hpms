<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				url:'zhpj/cjsjcx/query',
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true
			});
			
			//定义查询参数
			var searchParams;
			
			//查询
			$("#queryBtn").click(function(){
				searchParams = $("#dg").datagrid('options').queryParams;
				searchParams['nd'] = getNd();
				searchParams['yd'] = getYd();
				searchParams['dxbm'] = $("#q_bm").val();
				searchParams['dxmc'] = $("#q_dx").val();
				$("#dg").datagrid("reload");
			});
			
			function getNd(){
				var ny = $("#q_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				return nd;
			}
			
			function getYd(){
				var ny = $("#q_ny").val();
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				return yd;
			}
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="采集手工数据检索" class="search-condition" data-options="region:'north',collapsible:false">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			年月:<input id="q_ny" type="text" required="required" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
				<input id="_nd" name="nd" type="hidden">
				<input id="_yd" name="yd" type="hidden">
			对象编码:<input id="q_bm" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			对象名称:<input id="q_dx" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
		</fieldset>
	</div>
	<div title="采集手工数据明细" data-options="region:'center'">
		<table id="dg">
				<thead>
					<tr>
						<th field="nd" width="150">年度</th>
						<th field="yd" width="160">月度</th>
						<th field="dxbm" width="150">对象编码</th>
						<th field="dxmc" width="180">对象名称</th>
						<th field="dxsjz"  width="150">对象实际值</th>
						<th field="zcdxbm"  width="150">职称对象编码</th>
						<th field="zcdxlx"  width="150">职称对象类型</th>
					</tr>
			</thead>
		</table>
	</div>
</body>
</html>