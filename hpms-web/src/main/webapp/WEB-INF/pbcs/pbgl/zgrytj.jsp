<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科室在岗人员统计</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('.easyui-window').window({
			modal : true,
			resizable : false,
			minimizable : false,
			maximizable : false
		});
		$('.easyui-window').window('close');
		$('#dg').datagrid({
			fit : true,
			url : 'pbcs/pbgl/zgrytj/query',
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			pageSize : 18,
			pageList : [ 18, 20, 22 ],
			closable : true,
			toolbar : "#tb",
			showFooter : true,
			columns : [ [ {
				field : 'ksmc',
				title : '科室',
				width : 100
			}, {
				field : 'bzs',
				title : '编制数',
				width : 100
			}, {
				field : 'zgrs',
				title : '在岗人数',
				width : 100
			} ] ]
		});
	});

	function query() {
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		if (startTime == "" || startTime == null) {
			$.messager.alert('提示', '请选择开始年月!');
			return;
		}
		if (endTime == null && endTime == "") {
			$.messager.alert('提示', '请选择结束年月!');
			return;
		}
		$('#dg').datagrid({
			queryParams : {
				"startTime" : startTime,
				"endTime" : endTime
			},
			url : 'pbcs/pbgl/zgrytj/query',
			method : 'post'
		});

	}

	function exp() {
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
// 		if (startTime == "" || startTime == null) {
// 			$.messager.alert('提示', '请选择开始年月!');
// 			return;
// 		}
// 		if (endTime == null && endTime == "") {
// 			$.messager.alert('提示', '请选择结束年月!');
// 			return;
// 		}
		if (navigator.userAgent.indexOf("Firefox") != -1
				|| navigator.userAgent.indexOf("Chrome") != -1) {
			window.open("pbcs/pbgl/zgrytj/exporExcel?startTime=" + startTime
					+ "&endTime=" + endTime);
		} else if (navigator.userAgent.indexOf("Gecko") != -1) {
			window.open("pbcs/pbgl/zgrytj/exporExcel?startTime=" + startTime
					+ "&endTime=" + endTime);
		} else {
			window.open("pbcs/pbgl/zgrytj/exporExcel?startTime=" + startTime
					+ "&endTime=" + endTime);
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="科室在岗人员筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始年月<span class="need">*</span>:<input type="text" name="startTime"
				id="startTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" /> 截至年月<span class="need">*</span>:<input type="text"
				name="endTime" id="endTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" />
		</fieldset>
	</div>
	<div data-options="region:'center',title:'科室在岗人员统计'">
		<table id="dg">
		</table>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search"
			plain="true" onclick="query()">查询</a> <a id="printBtn"
			class="easyui-linkbutton" iconCls="icon-download" plain="true"
			onclick="()">导出</a>
	</div>
</body>
</html>