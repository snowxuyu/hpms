<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科室缺勤统计</title>
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
		$('#ksnm').combobox({
			url : 'yljx/ks/query',
			required : false,
			valueField : 'id',
			textField : 'text'
		});
		$('#dg').datagrid({
			fit : true,
			fitColumns : true,
			url : 'pbcs/pbgl/ksqqtj/query',
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			pageSize : 18,
			pageList : [ 18, 20, 22 ],
			closable : true,
			toolbar : "#tb",
			columns : [ [ {
				field : 'ksmc',
				title : '科室',
				width : 80,
				align : 'center',
				rowspan : 2
			}, {
				field : 'ygxm',
				title : '姓名',
				width : 80,
				align : 'center',
				rowspan : 2
			}, {
				field : 'zgts',
				title : '在岗天数',
				width : 80,
				align : 'center',
				rowspan : 2
			}, {
				field : 'jxgz',
				title : '进修挂职',
				width : 80,
				align : 'center',
				rowspan : 2
			}, {
				title : '缺勤情况',
				colspan : 7
			}, ], [ {
				field : 'bj',
				title : '病假',
				align : 'center',
				width : 80
			}, {
				field : 'shiJ',
				title : '事假',
				align : 'center',
				width : 80
			}, {
				field : 'cj',
				title : '产假',
				align : 'center',
				width : 80
			}, {
				field : 'hj',
				title : '婚假',
				align : 'center',
				width : 80
			}, {
				field : 'tqj',
				title : '探亲假',
				align : 'center',
				width : 80
			}, {
				field : 'sj',
				title : '丧假',
				align : 'center',
				width : 80
			}, {
				field : 'gj',
				title : '公假',
				align : 'center',
				width : 80
			} ] ]
		});
	});

	function query() {
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		var ksnm = $('#ksnm').combobox('getValue');
		if (startTime == "" || startTime == null) {
			$.messager.alert('提示', '请选择开始年月!');
			return;
		}
		if (endTime == null && endTime == "") {
			$.messager.alert('提示', '请选择结束年月!');
			return;
		}
		if (ksnm == "" || ksnm == null) {
			$.messager.alert('提示', '请选择科室!');
			return;
		}
		$('#dg').datagrid({
			queryParams : {
				"startTime" : startTime,
				"endTime" : endTime,
				"ksnm" : ksnm
			},
			url : 'pbcs/pbgl/ksqqtj/query',
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
			window.open("pbcs/pbgl/ksqqtj/exporExcel?ksnm=" + ksnm
					+ "&startTime=" + startTime + "&endTime=" + endTime);
		} else if (navigator.userAgent.indexOf("Gecko") != -1) {
			window.open("pbcs/pbgl/ksqqtj/exporExcel?ksnm=" + ksnm
					+ "&startTime=" + startTime + "&endTime=" + endTime);
		} else {
			window.open("pbcs/pbgl/ksqqtj/exporExcel?ksnm=" + ksnm
					+ "&startTime=" + startTime + "&endTime=" + endTime);
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="科室缺勤筛选"
		data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始年月<span class="need">*</span>:<input type="text" name="startTime"
				id="startTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" /> 截至年月<span class="need">*</span>:<input type="text"
				name="endTime" id="endTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" /> 科室名称<span class="need">*</span>:<input type="text"
				name="ksnm" id="ksnm" class="easyui-combobox" />
		</fieldset>
	</div>
	<div data-options="region:'center',title:'科室缺勤统计'">
		<table id="dg">
		</table>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search"
			plain="true" onclick="query()">查询</a> <a id="printBtn"
			class="easyui-linkbutton" iconCls="icon-download" plain="true"
			onclick="exp()">导出</a>
	</div>
</body>
</html>