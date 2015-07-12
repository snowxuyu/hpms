<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核心项目字典维护</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
    $(function(){
    	
    	//操作类型
    	var operate = "";
    	//标题数组
   		var arrTitle = ["核心项目字典维护-新增","核心项目字典维护-修改"];
    	//数据表格
    	var queryParams;
        $("#data").datagrid({
            url:"yljx/hxxmzd/query",
            rownumbers:true,
            pagination:true,
            fit:true,
            fitColumns:true,
            singleSelect:true,
            toolbar:"#tb",
            columns:[[
				{field:"ck",checkbox:true},
				//{field:"hxlbbm",hidden:true},
				//{field:"hxlbmc",title:"核心类别",width:"100"},
				{field:"xmlbbm",hidden:true},
				{field:"xmlbmc",title:"项目类别",width:"200"},
				/* {field:"xmlx",hidden:true},
				{field:"xmbs",title:"项目标识",width:"100",formatter: function(value,row,index){
						if (row.xmlx == "1") {
							return "个性项目";
						} else if (row.xmlx == "2") {
							return "公共项目";
						} else if (row.xmlx == "3") {
							return "材料项目";
						}
					}
				}, */
				{field:"xmbm",title:"项目编码",width:"150"},
				{field:"xmmc",title:"项目名称",width:"350"},
				//{field:"dsjsa",title:"点数基数A",width:"100"},
				{field:"dsdeb",title:"RBRVS点数",width:"100"}
            ]],
            onDblClickRow:function(rowIndex, rowData){
   				update();
   			}
        });
      	//核心类别下拉列表级联
		/* $("#s_hxlb").combobox({
			 valueField:'id',
			 textField:'text',
			 editable:false,
			 url:'yljx/hxxmpz/queryHxlb',
			 onChange:function(newValue, oldValue){
				 if (newValue == null || newValue == "") {
					 _xmlb.combobox("clear");
					 return;
				 }
				 $.post("yljx/hxxmpz/queryXmlb", {fjbm:newValue}, function(data){
					 _xmlb.combobox("clear").combobox("loadData",data);
				 },"json");
			 }
		}); */
		//项目类别combobox
		var _xmlb = $("#s_xmlb").combobox({
			 valueField:'id',
			 textField:'text',
			 editable:false,
			 url:'yljx/hxxmpz/queryXmlb?fjbm='
		});
      	//核心类别下拉列表级联
		/* $("#s_hxlbbm").combobox({
			 valueField:'id',
			 textField:'text',
			 editable:false,
			 url:'yljx/hxxmpz/queryHxlb',
			 onChange:function(newValue, oldValue){
				 if (newValue == null || newValue == "") {
					 _xmlbbm.combobox("clear");
					 return;
				 }
				 $.post("yljx/hxxmpz/queryXmlb", {fjbm:newValue}, function(data){
					 _xmlbbm.combobox("clear").combobox("loadData",data);
				 },"json");
			 }
		}); */
		//项目类别combobox
		var _xmlbbm = $("#s_xmlbbm").combobox({
			 valueField:'id',
			 textField:'text',
			 editable:false,
			 url:'yljx/hxxmpz/queryXmlb?fjbm='
		});
      	
    	//获取数据表格参数方法
        function getParams() {
        	queryParams = $('#data').datagrid('options').queryParams;
		}
    	//查询
       	function query() {
       		getParams();
    		//queryParams['hxlbbm'] = $("#s_hxlb").combobox('getValue');
    		queryParams['xmlbbm'] = $("#s_xmlb").combobox('getValue');
    		queryParams['xmmc'] = $("#s_xmmc").textbox('getValue');
    		$('#data').datagrid('load');
       	} 
    	//条件查询
        $("#searchBtn").click(function() {
        	query();
        });
    	//新增
        $("#addBtn").click(function() {
        	operate = "add";
        	$("#form").form("reset");
        	$("#xmbm").textbox({"disabled":false});
        	$("#win").window("setTitle",arrTitle[0]);
        	$("#win").window("open");
        });
    	//更新
        $("#updateBtn").click(function() {
        	update();
        });
    	//删除
        $("#removeBtn").click(function() {
        	var row = $("#data").datagrid("getSelected");
        	if (!row) {
        		$.messager.alert("警告","当前没有选择行！","warning");
				return false;
			}
        	$.messager.confirm("确认", "是否删除该条记录？", function(r){
        		if(!r) return;
        		$.post("yljx/hxxmzd/remove", {"xmbm":row.xmbm}, function(res) {
	        		if (res == "success") {
						$("#data").datagrid("reload");
						$("#win").window("close");
						$.messager.show({
							title : "操作提示",
							msg : "删除成功！",
							timeout : 3000
						});
					} else {
						$.messager.alert("警告", "删除失败！","error");
					}
				});
			});

		});
		//处理新增、修改
		$("#saveBtn").click(function() {
			/* if ($("#s_hxlbbm").combobox("getValue") == null || $("#s_hxlbbm").combobox("getValue") == "") {
				$.messager.alert("警告","核心类别不能为空！","warning");
				return false;
			} */
			if ($("#s_xmlbbm").combobox("getValue") == null || $("#s_xmlbbm").combobox("getValue") == "") {
				$.messager.alert("警告","项目类别不能为空！","warning");
				return false;
			}
			var isValid = $("#form").form("validate");
        	if (!isValid){
				return false;
			}
			var op = operate == "add" ? "新增" : "修改";
			$.messager.confirm("确认", "是否" + op + "该条记录？", function(r) {
				if (!r) return;
				$.messager.progress();
				$("#form").form('submit', {
					url : "yljx/hxxmzd/" + operate,
					onSubmit : function(param) {
						if (!isValid){
	        				$.messager.progress("close");
	        			}
						setForm();
						return isValid;
					},
					success : function(res) {
						$.messager.progress("close");
						if (res == "success") {
							$("#data").datagrid("reload");
							$("#win").window("close");
							$.messager.show({
								title : "操作提示",
								msg : op + "成功！",
								timeout : 3000
							});
						} else if (res == "e1") {
							$.messager.alert("警告", "项目编码已存在,"+op + "失败！","error");
						} else if (res == "e2") {
							$.messager.alert("警告", "项目名称已存在,"+op + "失败！","error");
						} else {
							$.messager.alert("警告", op + "失败！","error");
						}
					}
				});
			})
		});
		function setForm() {
			//$("#_hxlbbm").val($("#s_hxlbbm").combobox('getValue'));
			$("#_xmlbbm").val($("#s_xmlbbm").combobox('getValue'));
			//$("#_xmlx").val($("#xmlx").combobox('getValue'));
			$("#_xmbm").val($("#xmbm").textbox('getValue'));
			$("#_xmmc").val($("#xmmc").textbox('getValue'));
			//$("#_dsjsa").val($("#dsjsa").numberbox('getValue'));
			$("#_dsdeb").val($("#dsdeb").numberbox('getValue'));
		}
		//更新数据传递
		function update() {
			var _first = true;
			var row = $("#data").datagrid("getSelected");
			if (!row) {
				$.messager.alert("警告", "当前没有选择行！", "warning");
				return false;
			}
			
			$("#form").form("reset");
			operate = "update";
			$("#win").window("setTitle", arrTitle[1]);
			$("#win").window("open");
			
			//$("#s_hxlbbm").combobox('setValue', row.hxlbbm);
			
			$("#s_xmlbbm").combobox({
				onLoadSuccess: function() {
					if (_first) {
						$("#s_xmlbbm").combobox('select', row.xmlbbm);
						_first = false;
					}
				}
			})
			//$("#xmlx").combobox('setValue', row.xmlx);
			$("#xmbm").textbox('setValue', row.xmbm);
			$("#xmmc").textbox('setValue', row.xmmc);
			//$("#dsjsa").numberbox('setValue', row.dsjsa);
			$("#dsdeb").numberbox('setValue', row.dsdeb);
			
			$("#xmbm").textbox({"disabled":true});
		}
		//下载
		$("#downloadBtn").click(function() {
			var url="<%=basePath %>yljx/hxxmzd/download?"
					+"xmlbbm=" + $("#s_xmlb").combobox('getValue') + "&xmmc=" + $("#s_xmmc").textbox('getValue');
			window.open(url,'ExcelWin','z-look=1');
			return false;
		});
		
		//导入
		$("#uploadBtn").click(function() {
			$("#file").val("");
			$("#win-upload").window("open");
		})
		//处理导入
        $("#saveBtn1").click(function() {
        	if ($("#file").val() == "" || $("#file").val() == null) {
				$.messager.alert("警告","Excel文件不能为空！","warning");
				return false;
			}
        	$.messager.confirm("确认", "是否立即导入?", function(r){
        		if(!r) return;
        		$.messager.progress();
	        	$("#form2").form("submit", {
	        		url:"yljx/hxxmzd/import",
	        		onSubmit: function(param) {},
	        		success: function(res) {
						if (res == "success"){
							$("#data").datagrid("reload");
							$("#win-upload").window("close");
							$.messager.progress("close");
							$.messager.show({
								title:"操作提示",
								msg: "数据导入成功！",
								timeout:3000
							});
						} else if (res == null || res == "") {
							$.messager.progress("close");
							$.messager.alert("警告","服务器超时未响应！","error");
						} else {
							$.messager.progress("close");
							$.messager.alert("警告",res,"error");
						}
	        		}
	        	});
        	})
        });
	});
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="核心项目字典维护" data-options="region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			<!-- 核心类别：<input id="s_hxlb"/> -->
			项目类别：<input id="s_xmlb"/>
			项目名称：<input id="s_xmmc" type="text" class="easyui-textbox"></input>
		</fieldset>
	</div>
	<div title="核心项目字典明细" data-options="region:'center'">
		<table id="data"></table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a id="uploadBtn" class="easyui-linkbutton" iconCls="icon-upload" plain="true">导入</a>
        <a id="downloadBtn" class="easyui-linkbutton" iconCls="icon-download" plain="true">导出</a>
	</div>
	
	<div id="win-upload" class="easyui-window win" title="导入">
		<form id="form2" method="post" enctype="multipart/form-data">
			<table style="width: 300px">
				<tr>
					<td>文件路径 <span class="need">*</span>：</td>
					<td>
						<input id="file" name="file" type="file" style="width:200px;" accept="application/msexcel">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="saveBtn1" class="easyui-linkbutton" iconCls="icon-ok">导入</a>
						&emsp;&emsp;
	        			<a class="easyui-linkbutton cancel" iconCls="icon-no">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="win" class="easyui-window win" title="核心项目字典维护-新增">
		<form id="form" method="post">
			<table>
				<tr>
					<!-- <td>核心类别 <span class="need">*</span>：</td>
					<td>
						<input id="s_hxlbbm" style="width: 160px"/>
						<input id="_hxlbbm" type="hidden" name="hxlbbm">
					</td> -->
					<td>项目类别 <span class="need">*</span>：</td>
					<td>
						<input id="s_xmlbbm" style="width: 160px"/>
						<input id="_xmlbbm" type="hidden" name="xmlbbm">
					</td>
					<td>项目编码 <span class="need">*</span>：</td>
					<td>
						<input id="xmbm" type="text" class="easyui-textbox" data-options="required:true,validType:['length[0,15]']"></input>
						<input id="_xmbm" type="hidden" name="xmbm">
					</td>
				</tr>
				<!-- <tr>
					<td>项目标识 <span class="need">*</span>：</td>
					<td>
						<select id="xmlx" class="easyui-combobox" style="width:160px;" data-options="editable:false">   
						    <option value="1">个性项目</option>
						    <option value="2">公共项目</option>
						    <option value="3">材料项目</option>
						</select> 
						<input id="_xmlx" type="hidden" name="xmlx">
					</td>
					<td></td>
					<td></td>
				</tr> -->
				<tr>
					
					<td>项目名称 <span class="need">*</span>：</td>
					<td>
						<input id="xmmc" type="text" class="easyui-textbox" data-options="required:true,validType:['length[0,50]']"></input>
						<input id="_xmmc" type="hidden" name="xmmc">
					</td>
					<td>RBRVS点数：</td>
					<td>
						<input id="dsdeb" type="text" class="easyui-numberbox" data-options="max:999999.99,precision:2" value="0"></input>
						<input id="_dsdeb" type="hidden" name="dsdeb">
					</td>
				</tr>
				<tr>
					<!-- <td>点数基数A：</td>
					<td>
						<input id="dsjsa" type="text" class="easyui-numberbox" data-options="max:999999.99,precision:2" value="0"></input>
						<input id="_dsjsa" type="hidden" name="dsjsa">
					</td> -->
					
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<a id="saveBtn" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
						&emsp;&emsp;
	        			<a id="cancelBtn" class="easyui-linkbutton cancel" iconCls="icon-no">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>  
</body>
</html>
