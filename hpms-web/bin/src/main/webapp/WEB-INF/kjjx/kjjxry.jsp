<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科教按人员汇总表</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript" src="js/datagrid-detailview.js"></script>
</head>
<body class="easyui-layout">
	<div class="search-condition"
		data-options="title:'科教绩效人员年度筛选',region:'north',height:130">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始时间：<input type="text" name="startTime" id="startTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" />&nbsp;&nbsp;结束时间：<input type="text" name="endTime"
				id="endTime"
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})"
				class="wdate" /> &nbsp;&nbsp;科室类别：<input id="ks"
				style="width: 200px" />&nbsp;&nbsp; <p>科室名称：
				<input id="ksmc" style="width: 200px" />&nbsp;&nbsp; 职称：<input id="zc"
				style="width: 200px" />&nbsp;&nbsp;</p>
			<!-- 
			<div style="float: right">
				<a id="query" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:false">查询</a>&nbsp;&nbsp; <a
					id="import" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">导出</a>
			</div>
			 -->
		</fieldset>

	</div>
	<div data-options="region:'center',title:'科教绩效人员年度明细'">
		<table id="grid" toolbar="#tb">
			<thead>
				<tr>
					<th data-options="field:'ygbh',hidden:true,width:250"></th>
					<th data-options="field:'ygxm',width:250">员工姓名</th>
					<th data-options="field:'a',align:',right',width:250">A科技成果</th>
					<th data-options="field:'b',align:',right',width:250">B发表论文</th>
					<th data-options="field:'c',align:',right',width:250">C职务专利</th>
					<th data-options="field:'d',align:',right',width:250">D科研项目</th>
					<th data-options="field:'e',align:',right',width:250">E三新项目</th>
					<th data-options="field:'f',align:',right',width:250">F学术兼职</th>
					<th data-options="field:'g',align:',right',width:250">G院内培训</th>

					<th data-options="field:'h',align:',right',width:250">H三基考试</th>
					<th data-options="field:'i',align:',right',width:250">I继续教育</th>

					<th data-options="field:'j',align:',right',width:250">J带教情况</th>
					<th data-options="field:'k',align:',right',width:250">K重点学科</th>
					<th data-options="field:'l',align:',right',width:250">L人才培养</th>
					<th data-options="field:'hj',align:',right',width:250">合计</th>
				</tr>
			</thead>
			<div id="tb">
				<a id="query" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a> &nbsp;&nbsp;
				<!-- 
				<a id="reset" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-redo',plain:true">重置</a> &nbsp;&nbsp;  -->
				<a id="export" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-download',plain:true">导出</a>
			</div>
		</table>



	</div>
</body>
<script type="text/javascript">
	$('#grid')
			.datagrid(
					{
						view : detailview,
						url : 'kjjx/jxryAction/findByPage',
						method : 'post',
						pageSize : 20,
						fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						closable : true,
						fit : true,

						detailFormatter : function(rowIndex, rowData) {
							return '<div id="ddv-' + rowIndex + '" style="padding:5px 0"></div>';
						},
						onExpandRow : function(index, row) {
							var ksdm = $('#ks').combobox('getValue');
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							var zc = $('#zc').combobox('getValue');
							var ksdm = $('#ksmc').combobox('getValue');
							$('#ddv-' + index)
									.datagrid(
											{
												url : "kjjx/jxryAction/getXmmx?ygbh="
														+ row.ygbh
														+ "&ksdm="
														+ ksdm
														+ "&startTime="
														+ startTime
														+ "&endTime="
														+ endTime
														+ "&zc=" + zc 
														+"ksmc"
														+ksmc+"",
												rowStyler : function(index, row) {
													var style = 'background-color:#ffffff;';
													return style;
												},
												columns : [ [ {
													field : 'xmmc',
													title : '项目名称',
													width : 150
												}, {
													field : 'lbmc',
													title : '类别名称',
													width : 150
												}, {
													field : 'jbmc',
													title : '级别名称',
													width : 150
												}, {
													field : 'smmc',
													title : '署名名称',
													width : 150
												}, {
													field : 'fz',
													title : '分值',
													align : 'right',
													width : 150
												} ] ],
												onLoadSuccess : function() {
													$('#grid')
															.datagrid(
																	'fixDetailRowHeight',
																	index);
												}

											});
							$('#grid').datagrid('fixDetailRowHeight', index);
						}
					});
	$(function() {
		$('#ks').combobox({
			url : 'kjjx/jxryAction/getKsList',
			required : false,
			valueField : 'zdbm',
			textField : 'zdmc'
		});
		$('#ksmc').combobox({
			url:'kjjx/kjxmmx/queryKs',
			valueField:'id',
			textField:'text'
		});
		$('#zc').combobox({
			url : 'kjjx/jxryAction/getZcList',
			required : false,
			valueField : 'zdbm',
			textField : 'zdmc'
		});
	});
	$(function() {
		$('#query').bind('click', function() {
			var ksdm = $('#ks').combobox('getValue');
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var zc = $('#zc').combobox('getValue');
			var ksmc = $('#ksmc').combobox('getValue');
			$('#grid').datagrid({
				url : 'kjjx/jxryAction/findByPage',
				method : 'post',
				queryParams : {
					startTime : startTime,
					endTime : endTime,
					ksdm : ksdm,
					zc : zc,
					ksmc:ksmc
				}
			});
		});
	});
	$(function() {
		$('#reset').bind('click', function() {
			$("#ks").combobox("setValue", "");
			$("#ksmc").combobox("setValue", "");
			$("#zc").combobox("setValue", "");
			$("#startTime").val(null);
			$("#endTime").val(null);
		})
	})
	$(function() {
		$('#export').bind(
				'click',
				function() {
					var ksdm = $('#ks').combobox('getValue');
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					var zc = $('#zc').combobox('getValue');
					var ksmc = $('#ksmc').combobox('getValue');
					if (navigator.userAgent.indexOf("Firefox") != -1
							|| navigator.userAgent.indexOf("Chrome") != -1
							|| navigator.userAgent.indexOf("Gecko") != -1) {
						window.open("kjjx/jxryAction/xmexpor?ksdm=" + ksdm
								+ "&startTime=" + startTime + "&endTime="
								+ endTime + "&zc=" + zc +"&ksmc="+ ksmc+ "");
					} else {
						window.open("jxryAction/xmexpor?ksdm=" + ksdm
								+ "&startTime=" + startTime + "&endTime="
								+ endTime + "&zc=" + zc +"&ksmc="+ ksmc+ "");
					}
				});

	});
</script>
</html>