<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script src="<%=basePath%>js/.json.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'科室信息',width: 260">
		<table id="ksGrid">
			<thead>
				<tr>
					<th data-options="field:'symc',width:150">名称</th>
					<th
						data-options="field:'sylsh',width:40,align:'right',formatter:Sz">设置科室</th>
				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'center',title:'fdsfdsfds'">
		<table id="grid">
			<thead>
				<tr>
					<th data-options="field:'lsh',width:100">流水号</th>
					<th data-options="field:'ygbh',width:100">员工编号</th>
					<th data-options="field:'pblsh',width:100">排班流水号</th>
					<th data-options="field:'bbbm',width:100">班别编码</th>
					<th data-options="field:'bbjs',width:100">班别计数</th>
					<th data-options="field:'rq',width:100">日期</th>
					<th data-options="field:'sjxq',width:100">实际星期</th>
					<th data-options="field:'sjrq',width:100">实际日期</th>
					<th data-options="field:'fjbb',width:100">附加班别</th>
					<th data-options="field:'fjjs',width:100">附加计数</th>
					<th data-options="field:'cjr',width:100,hidden:true">创建人</th>
					<th data-options="field:'cjsj',width:100,hidden:true">创建时间</th>
					<th data-options="field:'xgr',width:100,hidden:true">修改人</th>
					<th data-options="field:'xgsj',width:100,hidden:true">修改时间</th>
					<th data-options="field:'zt',width:100,hidden:true">状态</th>

				</tr>
			</thead>
		</table>
	</div>
	<div id="win">
		<form id="form1">
			<div id="tree"></div>
			<input type="hidden" name="sel" id="sel" />
			<!-- 			<div data-options="region:'south',border:false" -->
			<!-- 				style="text-align: right; padding: 5px 0 0;"> -->
			<!-- 				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" -->
			<!-- 					href="javaScript:saveTree()" style="width: 60px">保存</a> <a -->
			<!-- 					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" -->
			<!-- 					href="javaScript:closeWin()" style="width: 60px">取消</a> -->
			<!-- 			</div> -->
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#ksGrid').datagrid({
			url : 'pbcs/bbsz/getBbsy',
			pageSize : 20,
			method : 'post',
			fit : true,
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true
		});
		$('#grid').datagrid({
			url : 'pbcs/bbsz/findByPage',
			pageSize : 20,
			method : 'post',
			fit : true,
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true
		});
	})

	function Sz(value, row, index) {
		return "<a href='javaScript:opWin(" + value + ")'>设置</a>";
	}
	function opWin(id) {
		$('#win').dialog({
			width : 300,
			title : '设置科室',
			height : 650,
			modal : true,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					saveTree(id);
				}
			} ]
		});
		$('#tree').tree({
			url : 'pbcs/bbsz/getTreeList',
			animate : true,
			checkbox : true,
			queryParams : {
				"sylsh" : id
			},
			onLoadSuccess : function(node, data) {
				$.ajax({
					url : 'pbcs/bbsz/findTreeList',
					cache : false,
					dataType : 'text',
					success : function(data) {
						var array = data.split(',');
						for (var i = 0; i < array.length; i++) {
							var node = $('#tree').tree('find', array[i]);
							$('#tree').tree('check', node.target);
						}
					}
				})
			}
		});
		$('#win').window('open');
	}

	function saveTree(id) {
		var nodes = $('#tree').tree('getChecked');
		var json = "";
		if (nodes.length <= 0) {
			return;
		}
		for (var i = 0; i < nodes.length; i++) {
			var ksxx = nodes[i];
			json += "{ksnm:\"" + ksxx.id + "\",sylsh:\"" + id
					+ "\",cjr:\"admin\",xgr:\"admin\"},"
		}
		json = json.substring(0, json.length - 1);
		$("#sel").val("[" + json + "]");
		$("#form1").form("submit", {
			url : 'pbcs/bbsz/saveTree',
			success : function(result) {
				if ("success" == result) {
					$.messager.show({
						title : "操作提示",
						msg : "操作成功！",
						timeout : 3000
					});
					$("#win").window("close");
				} else {
					alert(result);
					$.messager.alert("警告", "操作失败！", "warning");
					$("#win").window("close");
				}
			}
		});
	}
	function closeWin() {
		$('#win').window('close');
	}
</script>