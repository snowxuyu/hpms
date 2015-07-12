<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪酬项目数据查询</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div class="search-condition"
		data-options="title:'薪酬项目数据查询',region:'north',height:130"
		toolbar="#tb">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend> 
			年月：<input type="text" name="time" id="time"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" />&nbsp;&nbsp;&nbsp;&nbsp;元项目：<input id="ymc"
				style="width: 200px" />&nbsp;&nbsp; &nbsp;&nbsp;元对象：<input id="ydx"
				style="width: 200px" />
			<!-- 
			<div style="float: right">
				<a id="query" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:false">查询</a>
				&nbsp;&nbsp;
				<a id="reset" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-redo',plain:false">重置</a>
			</div>
			 -->
			<div id="tb">
				<a id="query" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a>
			</div>
		</fieldset>

	</div>
	<div data-options="region:'center',title:'薪酬项目数据'">
		<table id="grid" class="easyui-datagrid"
			data-options="fitColumns: true ,
                          singleSelect:true , rownumbers:true,pagination:true,
                          closable:true,fit:true"
			toolbar="#tb">
			<thead>
				<tr>
					<th data-options="field:'nd',width:200,align:',right'">年度</th>
					<th data-options="field:'yd',width:200,align:',right'">月度</th>
					<th data-options="field:'ybm',width:200">元项目编码</th>
					<th data-options="field:'ymc',width:200">元项目名称</th>
					<th data-options="field:'yld',width:200,align:',right'">元粒度</th>
					<th data-options="field:'ydxbm',width:200">元对象编码</th>
					<th data-options="field:'ydxmc',width:200">元对象名称</th>
					<th data-options="field:'sjz',width:200,align:',right'">计算值</th>
				</tr>
			</thead>
		</table>



	</div>
</body>
<script type="text/javascript">
	$('#grid').datagrid({
		url : 'hpxt/xcxmAction/findByPage',
		pageSize : 20,
		method : 'post'
	});
	$(function() {
		$('#query').bind('click', function() {
			var time = $("#time").val();
			var ymc = $("#ymc").val();
			var ydx = $("#ydx").val();
			$('#grid').datagrid({
				url : 'hpxt/xcxmAction/findByPage',
				method : 'post',
				queryParams : {
					time : time,
					ymc : ymc,
					ydx : ydx
				}
			});
		});
	});
	$(function() {
		$('#reset').bind('click', function() {
			$("#time").val(null);
			$("#ymc").val(null);
			$("#ydx").val(null);
		})
	})
</script>
</html>