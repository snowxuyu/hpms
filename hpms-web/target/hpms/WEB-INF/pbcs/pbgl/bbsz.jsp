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
<title>科室班别设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'科室班别',width: 260">
		<table id="ksGrid">
		</table>
	</div>
	<div id="tbLb" style="padding: 5px; height: auto">
		<a id="btnAddLb" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="addLb()">新增</a>
		<a id="btnSaveLb" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="saveLb()">保存</a>
		<a id="btnDeleteLb" href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="deleteLb()">删除</a>
	</div>
	<div data-options="region:'center',title:'班别明细'">
		<div id="grid"></div>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<a id="btnAdd" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a> <a
			id="btnSave" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="saveMx()">保存</a>
		<a id="btnDelete" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="deleteMx()">删除</a>
	</div>
	<div id="win">
		<form id="form1" method="post">
			<div id="tree"></div>
			<input type="hidden" name="sel" id="sel" /> <input type="hidden"
				name="selId" id="selId" />
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$.extend($.fn.datagrid.methods, {
		editCell : function(jq, param) {
			return jq.each(function() {
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields', true).concat(
						$(this).datagrid('getColumnFields'));
				for (var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field) {
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for (var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});

	var jcData = getJson("yljx/bbfl/queryAll");
	function getJson(url) {
		var val;
		$.ajax({
			url : url,
			cache : false,
			async : false,
			dataType : 'text',
			success : function(data) {
				val = data;
			}
		});
		var data = $.parseJSON(val);
		return data;
	}
	$(function() {
		$('#ksGrid').datagrid({
			url : 'pbcs/bbsz/getBbsy',
			pageSize : 20,
			method : 'post',
			fit : true,
			fitColumns : true,
			rownumbers : true,
			toolbar : "#tbLb",
			pagination : true,
			singleSelect : true,
			onDblClickCell : onClickCell2,
			columns : [ [ {
				field : 'sylsh',
				checkbox : true
			}, {
				field : 'symc',
				title : '名称',
				width : 150,
				editor : {
					type : 'text'
				}
			}, {
				field : '',
				title : '设置科室',
				width : 80,
				align : 'right',
				formatter : Sz
			} ] ],
			onClickRow : function(rowIndex, rowData) {
				query();
			}
		});
		paginationStyle("ksGrid");
		$('#grid').datagrid({
			url : 'pbcs/bbsz/findByPage',
			pageSize : 20,
			method : 'post',
			fit : true,
			queryParams : {
				"sylsh" : "-1"
			},
			fitColumns : true,
			rownumbers : true,
			onDblClickCell : onClickCell,
			pagination : true,
			toolbar : "#tb",
			columns : [ [ {
				field : 'lsh',
				checkbox : true
			}, {
				title : '班次',
				field : 'bbbm',
				width : 200,
				required : true,
				formatter : formatterBx,
				editor : {
					type : 'combobox',
					options : {
						data : jcData,
						valueField : 'bbbm',
						textField : 'bbmc',
						onSelect : function(data) {
							$('#bbjx_' + editIndex).html(data.bbjx);
						}
					}
				}

			}, {
				field : 'bbjx',
				title : '显示',
				width : 200,
				formatter : function(value, row, index) {
					var val = $("#bbjx_" + index + "").text();
					var val2 = (val == "" || val == null) ? value : val;
					return "<div  id='bbjx_"+index+"'>" + val2 + "</div>";
				}
			}, {
				field : 'sjxs',
				title : '时间',
				required : true,
				width : 200,
				editor : {
					type : 'text'
				}
			}, {
				field : 'bfje',
				required : true,
				title : '中夜班费金额',
				width : 200,
				align : 'right',
				editor : {
					type : 'numberbox',
					options : {
						precision : 0
					}
				}
			} ] ]
		});
	})

	function query() {
		var queryParams = $('#grid').datagrid('options').queryParams;
		var row = $('#ksGrid').datagrid('getSelected');
		var sylsh = row.sylsh;
		if (sylsh == null || sylsh == "") {
			sylsh = "-1";
		}
		queryParams.sylsh = sylsh;
		$('#grid').datagrid('options').queryParams = queryParams;
		$("#grid").datagrid('reload');
	}

	function rowClick(rowIndex, rowData) {
	}

	function Sz(value, row, index) {
		return "<a href='javaScript:opWin(" + row.sylsh + ")'>设置</a>";
	}
	function opWin(id) {
		if (id == "" || id == null) {
			$.messager.alert("警告", "请先保存该信息！", "warning");
			return;
		}
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
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$("#win").window("close");
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
					async : false,
					dataType : 'text',
					data : {
						sylsh : id
					},
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
		$("#selId").val(id);
		$.post("pbcs/bbsz/saveTree", $("#form1").serialize(), function(res) {
			if (res == "success") {
				$.messager.show({
					title : "操作提示",
					msg : "操作成功！",
					timeout : 3000
				});
				$("#win").window("close");
			} else {
				$.messager.alert("警告", "操作失败！", "warning");
				$("#win").window("close");
			}
		});
	}
	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#grid').datagrid('validateRow', editIndex)) {
			$('#grid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}

	var editIndex2 = undefined;
	function endEditing2() {
		if (editIndex2 == undefined) {
			return true
		}
		if ($('#ksGrid').datagrid('validateRow', editIndex2)) {
			$('#ksGrid').datagrid('endEdit', editIndex2);
			editIndex2 = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field) {
		if (endEditing()) {
			$('#grid').datagrid('selectRow', index).datagrid('editCell', {
				index : index,
				field : field
			});
			editIndex = index;
		}
	}

	function onClickCell2(index, field) {
		if (endEditing2()) {
			$('#ksGrid').datagrid('selectRow', index).datagrid('editCell', {
				index : index,
				field : field
			});
			editIndex2 = index;
		}
	}

	function closeWin() {
		$('#win').window('close');
	}

	function paginationStyle(id, size) {
		size = size == null ? 10 : size;
		var p = $('#' + id).datagrid('getPager');
		if (p) {
			$(p).pagination({
				showPageList : false,
				displayMsg : '',
				pageSize : size
			});
		}
	}
	function formatterBx(value, row) {
		for (var i = 0; i < jcData.length; i++) {
			if (jcData[i].bbbm == value) {
				return jcData[i].bbmc;
			}
		}
	}

	function saveMx() {
		endEditing();
		var rows = $('#grid').datagrid("getChanges")//获取当前的数据行
		var page = $('#grid').datagrid('getPager').data("pagination").options;
		var pageSize = page.pageSize
		if (rows.length <= 0 && rows.length < pageSize) {
			return;
		}
		var saveJson = "";
		var updateJson = "";
		var row = $('#ksGrid').datagrid('getSelected');
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			var lsh = row.lsh;
			if (lsh == "") {
				saveJson += "{lsh:\"" + row.lsh + "\",bbbm:\"" + row.bbbm
						+ "\",sylsh:\"" + row.sylsh
						+ "\",cjr:\"admin\",xgr:\"admin\",sjxs:\"" + row.sjxs
						+ "\",bfje:" + row.bfje + ",zt:\"" + row.zt + "\"},"
			} else {
				var cjsj = getLocalTime(row.cjsj);
				updateJson += "{lsh:\"" + row.lsh + "\",bbbm:\"" + row.bbbm
						+ "\",sylsh:\"" + row.sylsh + "\",cjr:\"" + row.cjr
						+ "\",xgr:\"admin\",sjxs:\"" + row.sjxs + "\",bfje:"
						+ row.bfje + ",cjsj:\"" + cjsj + "\",zt:\"" + row.zt
						+ "\"},"
			}

		}

		saveJson = saveJson != "" && saveJson != null ? "["
				+ saveJson.substring(0, saveJson.length - 1) + "]" : "";
		updateJson = updateJson != "" && updateJson != null ? "["
				+ updateJson.substring(0, updateJson.length - 1) + "]" : "";
		$.ajax({
			type : 'POST',
			url : 'pbcs/bbsz/saveOrUpdate',
			data : {
				updateJson : updateJson,
				saveJson : saveJson
			},
			success : function(msg) {
				if (msg == "success") {
					query();
					$.messager.show({
						title : "操作提示",
						msg : "操作成功！",
						timeout : 3000
					});
				} else {
					$.messager.alert("警告", "操作失败！", "warning");
				}
			}
		});
	}

	function getLocalTime(a) {
		var date = new Date(parseInt(a));
		var dateStr = date.getFullYear()
				+ "-"
				+ ((date.getMonth() * 1 < 10) ? "0" + date.getMonth() : date
						.getMonth())
				+ "-"
				+ ((date.getDay() * 1 < 10) ? "0" + date.getDay() : date
						.getDay())
				+ " "
				+ ((date.getHours() * 1 < 10) ? "0" + date.getHours() : date
						.getHours())
				+ ":"
				+ ((date.getMinutes() * 1 < 10) ? "0" + date.getMinutes()
						: date.getMinutes())
				+ ":"
				+ ((date.getSeconds() * 1 < 10) ? "0" + date.getSeconds()
						: date.getSeconds());
		return dateStr;
	}
	function deleteMx() {
		var rows = $("#grid").datagrid('getChecked');
		if (rows.length <= 0) {
			return;
		}
		var ids = "'" + rows[0].lsh + "'";
		for (var i = 1; i < rows.length; i++) {
			ids += ",'" + rows[i].lsh + "'";
		}
		$.messager.confirm("提示", "是否删除选中数据？", function(r) {
			if (r) {
				$.ajax({
					type : 'POST',
					url : 'pbcs/bbsz/deleteMx',
					data : {
						ids : ids
					},
					success : function(msg) {
						if (msg == "success") {
							query();
							$.messager.show({
								title : "操作提示",
								msg : "操作成功！",
								timeout : 3000
							});
						} else {
							$.messager.alert("警告", "操作失败！", "warning");
						}
					}
				});
			}
		});
	}
	function saveLb() {
		endEditing2();
		var rows = $('#ksGrid').datagrid("getChanges")//获取当前的数据行
		if (rows.length <= 0) {
			return;
		}
		var saveJson = "";
		var updateJson = "";
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			var zt = row.zt != "" && row.zt != null ? row.zt : "1";
			var sylsh = row.sylsh != "" && row.sylsh != null ? row.sylsh : "";
			if (sylsh == "") {
				saveJson += "{sylsh:\"" + sylsh + "\",symc:\"" + row.symc
						+ "\",cjr:\"admin\",zt:\"" + zt + "\"},"
			} else {
				var cjsj = row.cjsj != "" && row.cjsj != null ? getLocalTime(row.cjsj)
						: "";
				updateJson += "{sylsh:\"" + sylsh + "\",symc:\"" + row.symc
						+ "\",cjr:\"" + row.cjr + "\",zt:\"" + zt
						+ "\",cjsj:\"" + cjsj + "\"},"
			}

		}
		saveJson = saveJson != "" && saveJson != null ? "["
				+ saveJson.substring(0, saveJson.length - 1) + "]" : "";
		updateJson = updateJson != "" && updateJson != null ? "["
				+ updateJson.substring(0, updateJson.length - 1) + "]" : "";
		$.ajax({
			type : 'POST',
			url : 'pbcs/bbsz/saveLb',
			data : {
				updateJson : updateJson,
				saveJson : saveJson
			},
			success : function(msg) {
				if (msg == "success") {
					$("#ksGrid").datagrid('reload');
					$.messager.show({
						title : "操作提示",
						msg : "操作成功！",
						timeout : 3000
					});
				} else {
					$.messager.alert("警告", "操作失败！", "warning");
				}
			}
		});
	}

	function deleteLb() {
		var rows = $("#ksGrid").datagrid('getChecked');
		if (rows.length <= 0) {
			return;
		}
		var ids = "'" + rows[0].sylsh + "'";
		for (var i = 1; i < rows.length; i++) {
			ids += ",'" + rows[i].sylsh + "'";
		}
		$.messager.confirm("提示", "是否删除选中数据？", function(r) {
			if (r) {
				$.ajax({
					type : 'POST',
					url : 'pbcs/bbsz/deleteLb',
					data : {
						ids : ids
					},
					success : function(msg) {
						if (msg == "success") {
							$("#ksGrid").datagrid('reload');
							$.messager.show({
								title : "操作提示",
								msg : "操作成功！",
								timeout : 3000
							});
						} else {
							$.messager.alert("警告", "操作失败！", "warning");
						}
					}
				});
			}
		});
	}
	function addLb() {
		if (endEditing2()) {
			$('#ksGrid').datagrid('appendRow', {
				zt : '1'
			});
			editIndex2 = $('#ksGrid').datagrid('getRows').length - 1;
			$('#ksGrid').datagrid('selectRow', editIndex2).datagrid(
					'beginEdit', editIndex2);
		}
	}

	function add() {
		var row = $('#ksGrid').datagrid('getSelected');

		if (endEditing()) {
			$('#grid').datagrid('appendRow', {
				zt : '1',
				lsh : "",
				sylsh : row.sylsh
			});
			editIndex = $('#grid').datagrid('getRows').length - 1;
			$('#grid').datagrid('selectRow', editIndex2).datagrid('beginEdit',
					editIndex);
		}
	}
</script>