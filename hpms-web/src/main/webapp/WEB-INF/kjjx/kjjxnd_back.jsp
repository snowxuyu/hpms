<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科教按科室汇总表</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div class="search-condition"
		data-options="title:'科教绩效科室年度筛选',region:'north',height:130">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始时间：<input type="text" name="startTime" id="startTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" />&nbsp;&nbsp;结束时间：<input type="text" name="endTime"
				id="endTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" /> &nbsp;&nbsp;科室类别：<input id="cc"
				style="width: 200px" />&nbsp;
		</fieldset>

	</div>
	<div data-options="region:'center',title:'科教绩效科室年度汇总'">
		<table id="grid" toolbar="#tb">
			<thead>
				<tr>
					<th data-options="field:'ksmc',width:200">科室</th>
					<th data-options="field:'kydf',width:200,align:'right'">科研得分</th>
					<th data-options="field:'jxdf',width:200,align:'right'">教学得分</th>
					<th data-options="field:'zf',width:200,align:'right'">总分</th>
					<th data-options="field:'kyavg',width:200,align:'right'">科研平均得分</th>
					<th data-options="field:'jxavg',width:200,align:'right'">教学平均得分</th>
					<th data-options="field:'avg',width:200,align:'right'">人均平均得分</th>
				</tr>
			</thead>
			<div id="tb">
				<a id="query" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a>
				<!-- &nbsp;&nbsp;
				<a id="reset" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-redo',plain:true">重置</a>  -->
				&nbsp;&nbsp; <a id='export' href="javascript:void(0)"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-download',plain:true">导出</a>
			</div>
		</table>



	</div>
</body>
<script type="text/javascript">
	$('#grid').datagrid({
		url : 'kjjx/jxndAction/findByPage',
		pageSize : 20,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		fit : true,
		method : 'post'
	});
	$(function() {
		$('#cc').combobox({
			url : 'kjjx/jxndAction/getKsList',
			valueField : 'zdbm',
			textField : 'zdmc'
		});
	});
	$(function() {
		$('#query').bind('click', function() {
			var ksdm = $('#cc').combobox('getValue');
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			$('#grid').datagrid({
				url : 'kjjx/jxndAction/findByPage',
				method : 'post',
				queryParams : {
					startTime : startTime,
					endTime : endTime,
					ksdm : ksdm
				}
			});
		});
	});
	$(function() {
		$('#reset').bind('click', function() {
			$("#cc").combobox("setValue", "");
			$("#startTime").val(null);
			$("#endTime").val(null);
		})
	})
	$(function() {
		$('#export').bind(
				'click',
				function() {
					var ksdm = $('#cc').combobox('getValue');
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					if (navigator.userAgent.indexOf("Firefox") != -1
							|| navigator.userAgent.indexOf("Chrome") != -1
							|| navigator.userAgent.indexOf("Gecko") != -1) {
						window.open("kjjx/jxndAction/xmexpor?ksdm=" + ksdm
								+ "&startTime=" + startTime + "&endTime="
								+ endTime + "");
					} else {

						window.open("jxndAction/xmexpor?ksdm=" + ksdm
								+ "&startTime=" + startTime + "&endTime="
								+ endTime + "");
					}
				});

	});
</script>
</html>