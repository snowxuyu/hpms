<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>科室核心项目配置</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//关闭class为easyui-window的window
			$(".easyui-window").window("close");
			
			//操作类型
			var operate = "";
			
			//弹出窗口标题
			var title = ["科室核心项目-新增","科室核心项目-基数微调"];
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'yljx/hxxmpz/find',
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns:true,
		        singleSelect:true,
		        toolbar:"#tb",
				onDblClickRow: function(rowIndex, rowData){
					update();
				}
			});
			
			// 额外查询参数
			var searchParams;
			var mh_cx_param;
			
			//点击查询
			$("#queryBtn").click(function(){
				query();
			});
			
			//查询实现
			function query(){
				searchParams = $("#dg").datagrid("options").queryParams;
				searchParams['ksnm'] = $("#q_ks").combobox("getValue");
				searchParams['hxlbbm'] = $("#q_hxlb").combobox("getValue");
				searchParams['xmlbbm'] = $("#q_xmlb").combobox("getValue");
				searchParams['xmmc'] = $("#q_xmmc").val();
				
				$("#dg").datagrid("reload");
			}
			
			//点击新增
			$("#addBtn").click(function(){
				operate = "add";
				$("#ksmc").combobox("readonly", false);
				$("#form").form("reset");
				$("#window").window("setTitle",title[0]);
				$("#window").window("open");
				$("#hxlb").combobox("readonly", false);
				$("#cxBtn").show();
			});
			
			
			//根据选择的项目标识判断科室是否可选
			$("#xmbz").combobox({onChange: function(){
				var value = $("#xmbz").combobox("getValue");
				if (value=='2') {
					$("#ksmc").combobox({
						disabled:true  ,
						editable:false 
					});
				} else {
					$("#ksmc").combobox({
						disabled:false  ,
						editable:true 
					});
				}
			}});
			
			//点击新增/修改页面的查询按钮
			$("#cxBtn").click(function(){
				$("#mh_window").window("open");
				//初始化datagrid
				$("#mh_dg").datagrid({
					url:'yljx/hxxmpz/mhQueryHxxm',
					rownumbers:true,
			        pagination:true,
			        fit:true,
			        singleSelect:true,
			        onClickRow: function(rowIndex, mh_selectedRow) {
			        	//把选中行的数据传递给新增页面
			        	$("#xmmc").combobox("setValue", mh_selectedRow.xmbm);
			        	$("#xmmc").combobox("setText", mh_selectedRow.xmmc);
						$("#hxlb").combobox("setValue", mh_selectedRow.hxlbbm);
						$("#hxlb").combobox("setText", mh_selectedRow.hxlbmc);
						$("#xmlb").combobox("setValue", mh_selectedRow.xmlbbm);
						$("#xmlb").combobox("setText", mh_selectedRow.xmlbmc);
						/* $("#xmbz").combobox("setValue", mh_selectedRow.xmbs);
						$("#xmbz").combobox("setText", mh_selectedRow.xmbsmc); 
						$("#dsjs").numberbox('setValue', mh_selectedRow.dsjsa);*/
						$("#dsde").numberbox('setValue', mh_selectedRow.dsdeb);
						
						//关闭模糊查询的页面
						$("#mh_window").window("close");
			        }
				});
			});
			
			//点击模糊界面的查询
			$("#mh_cxBtn").click(function(){
				mh_cx_param = $("#mh_dg").datagrid("options").queryParams;
				mh_cx_param['xmmc'] = $("#mh_cxnr").textbox("getValue");
				$("#mh_dg").datagrid("reload");
			});
			
			//点击修改
			$("#updateBtn").click(function(){
				update();
			});
			
			//修改实现
			function update(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow){
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				operate = "update";
				$("#ksmc").combobox("readonly", true);
				$("#window").window("setTitle", title[1]);
				$("#window").window("open");
				$("#cxBtn").hide();
				//把表格数据传给页面
				$("#_lsh").val(selectedRow.lsh);
				$("#ksmc").combobox("setValue", selectedRow.ksnm);
				$("#ksmc").combobox("setText", selectedRow.ksmc);
				$("#xmmc").combobox("setValue", selectedRow.xmbm);
				$("#xmmc").combobox("setText", selectedRow.xmmc);
				$("#_xmbm").val(selectedRow.xmbm);
				$("#hxlb").combobox("select", selectedRow.hxlbbm);
				//$("#hxlb").combobox("setValue", selectedRow.hxlbbm);
				//$("#hxlb").combobox("setText", selectedRow.hxlbmc);
				$("#xmlb").combobox("setValue", selectedRow.xmlbbm);
				$("#xmlb").combobox("setText", selectedRow.xmlbmc);
				/* $("#xmbz").combobox("setValue", selectedRow.xmbs);
				$("#xmbz").combobox("setText", selectedRow.xmbsmc); 
				$("#dsjs").numberbox('setValue',selectedRow.dsjsa);*/
				$("#dsde").numberbox('setValue',selectedRow.dsdeb);
				$("#hxlb").combobox("readonly", true);
			}
			
			//点击删除， 实现删除
			$("#removeBtn").click(function(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow) {
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				$.messager.confirm("确认", "是否删除该条记录?", function(r){
					if(!r) return ;
					$.post("yljx/hxxmpz/removeById", {
						lsh:selectedRow.lsh
					},
					function(result){
						if ("success" == result) {
							$.messager.show({
								title:"操作提示",
								msg:"删除成功！",
								timeout:3000
							});
							$('#dg').datagrid('reload');
						} else {
							$.messager.alert("警告","删除失败!","warning");
						}
					});
				});
			});
			
			//点击保存
			$("#saveBtn").click(function(){
				if (check()){
					$("#form").form("submit",{
						url:'yljx/hxxmpz/'+operate,
						onSubmit: function(){
							setForm();
						},
						success: function(result){
							if ("success" == result){
								$.messager.show({
									title:"操作提示",
									msg:"操作成功！",
									timeout:3000
								});
								$('#dg').datagrid('reload');
			                    $("#window").window("close");
							} else {
								$.messager.alert("警告","操作失败!","warning");
								$("#window").window("close");
							}
						}
					});
				}
			});
			
			
			//向隐藏域赋值
			function setForm(){
				$("#_ksnm").val($("#ksmc").combobox("getValue"));
				$("#_xmmc").val($("#xmmc").combobox("getText"));
				$("#_xmbm").val($("#xmmc").combobox("getValue"))
				$("#_hxlbbm").val($("#hxlb").combobox("getValue"));
				$("#_xmlbbm").val($("#xmlb").combobox("getValue"));
				$("#_xmbz").val($("#xmbz").combobox("getValue"));
				//$("#_dsjs").val($("#dsjs").numberbox('getValue'));
				$("#_dsde").val($("#dsde").numberbox('getValue'));
			}
			
			//前端验证
			function check(){
				var ksmc = $("#ksmc").combobox("getValue");
				var xmmc = $("#xmmc").combobox("getValue");
				var hxlb = $("#hxlb").combobox("getValue");
				var xmlb = $("#xmlb").combobox("getValue");
				var xmbz = $("#xmbz").combobox("getValue");
				//var dsjs = $("#dsjs").numberbox('getValue');
				var dsde = $("#dsde").numberbox('getValue');
				
				/* if (""==ksmc || null==ksmc){
					$.messager.alert("警告","科室名称不能为空!","warning");
					return false;
				} */
				if (""==xmbz || null==xmbz){
					$.messager.alert("警告","项目标识不能为空!","warning");
					return false;
				}
				if (xmbz=='2') {
					$("#ksmc").combobox("setValue",null);
				}
				if (""==xmmc || null==xmmc){
					$.messager.alert("警告","项目名称不能为空!","warning");
					return false;
				}
				if (""==hxlb || null==hxlb){
					$.messager.alert("警告","核心类别不能为空!","warning");
					return false;
				}
				if (""==xmlb || null==xmlb){
					$.messager.alert("警告","核心标识不能为空!","warning");
					return false;
				}
				if (""==dsde){
					$.messager.alert("警告","点数定额不能为空!","warning");
					return false;
				}
				return true;
			}
			//点击导入
			$("#importBtn").click(function(){
				$("#importWin").window("open");
			});
			
			//点击导入的导入按钮
			$("#importWin_Btn").click(function(){
				var imp_ks = $("#impks").combobox("getValue");
				var uploadfile = $("#impwj").val();
				var check = $("#dr_xm input:radio[name='impxm']:checked").val();
				if (check!='1' && check!='2') {
					$.messager.alert("警告","请选择导入共性项目或者是个性项目!","warning");
					return;
				}
				if(check=='1') {
					if (""==imp_ks || null==imp_ks){
						$.messager.alert("警告","科室不能为空!","warning");
						return;
					}
				} 
				if (""==uploadfile || null==uploadfile){
					$.messager.alert("警告","上传文件不能为空!","warning");
					return;
				}
				$("#importform").form("submit",{
					url:'yljx/hxxmpz/importHxxm',
					onSubmit:function(){
						$("#_imp_ks").val(imp_ks);
						$("#importWin").window("close");
						$.messager.progress();
					},
					success:function(result){
						$.messager.progress("close");
						if ("success"==result){
							$.messager.show({
								title:"操作提示",
								msg:"导入成功！",
								timeout:3000
							});

							$("#importWin").window("close");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert("警告",result,"warning");
							$("#importWin").window("close");
						}
					}
				});		
			});
			
			//点击导出
			$("#exportBtn").click(function(){
				$("#exportWin").window("open");
			});
			
			//点击导出的导出按钮
			$("#exportWin_Btn").click(function(){
				var exp_ks = $("#expks").combobox("getValue");
				var exp_xmfl = $("#exp_xmfl").combobox("getValue");
				var check = $("#div_exp input:radio[name='expxm']:checked").val();
				if (check!='1' && check!='2') {
					$.messager.alert("警告","请选择导出共性项目或者是个性项目!","warning");
					return;
				}
				if (check=='1') {
					if (""==exp_ks || null==exp_ks){
						$.messager.alert("警告","科室不能为空!","warning");
						return;
					}
				}
				$("#exportform").form("submit",{
					url:'yljx/hxxmpz/exportHxxm',
					onSubmit:function(){
						$("#_exp_ks").val(exp_ks),
						$("#_exp_xmfl").val(exp_xmfl);
					},
					success:function(result){
						
					}
					
				});
				$("#exportWin").window("close");
			});
			
			
			//点击取消按钮
			$("#cancelBtn").click(function(){
				$("#window").window("close"); 
			});
			
			$("#impcancelBtn").click(function(){
				$("#importWin").window("close");
			});
			$("#expcancelBtn").click(function(){
				$("#exportWin").window("close");
			});
			
			//核心类别下拉列表级联
			$("#q_hxlb").combobox({
				 valueField:'id',
				 textField:'text',
				 editable:false,
				 url:'yljx/hxxmpz/queryHxlb',
				 onChange:function(newValue, oldValue){
					 $.post("yljx/hxxmpz/queryXmlb", {fjbm:newValue}, function(data){
						 _xmlb.combobox("clear").combobox("loadData",data);
					 },"json");
				 }
			});
			
			//项目类别combobox
			var _xmlb = $("#q_xmlb").combobox({
				 valueField:'id',
				 textField:'text',
				 editable:false
			});
			
			$("#impRadio").click(function (){
				$("#impks").combobox({
					disabled:false  ,
					editable:true 
				});
			});
			$("#impRadio2").click(function (){
				$("#impks").combobox({
					disabled:true  ,
					editable:false 					
				});
			});
			
			$("#expRadio").click(function (){
				$("#expks").combobox({
					disabled:false  ,
					editable:true 
				});
				
				/* $("#exp_xmfl").combobox({
					disabled:false  ,
					editable:true 
				}); */
			});
			$("#expRadio2").click(function (){
				$("#expks").combobox({
					disabled:true  ,
					editable:false 
				});
				/* $("#exp_xmfl").combobox({
					disabled:true  ,
					editable:false 
				}); */
			});
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="科室核心项目筛选" class="search-condition" data-options="region:'north',collapsible:false" style="overflow-y: hidden">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			科室名称:<input id="q_ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/> 
			核心类别:<input id="q_hxlb"/>
			项目类别:<input id="q_xmlb"/>
			项目名称:<input id="q_xmmc" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
		</fieldset>
	</div>
	<div title="科室核心项目配置" data-options="region:'center'">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="lsh" hidden="true">流水号</th>
					<th field="ksmc" width="150">科室名称</th>
					<th field="ksnm" hidden="true"></th>
					<th field="hxlbmc" width="150">核心类别</th>
					<th field="hxlbbm" hidden="true"></th>
					<th field="xmlbmc" width="150">项目类别</th>
					<th field="xmlbbm" hidden="true"></th>
					<th field="xmbm" width="150">项目编码</th>
					<th field="xmmc" width="250">项目名称</th>
					<th field="dsjsa" width="100" align="right" hidden="true">点数基数A</th>
					<th field="dsdeb" width="100" align="right">RBRVS点数</th>
					<th field="xmbs" hidden="true"></th>
					<th field="xmbsmc" hidden="true"></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		<a id="importBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true">导入</a>
		<a id="exportBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true">导出</a>
		<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	
	<div id="window" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table style="width: 580px">
				<tr>
					<td>
						项目标识:<span class="need">*</span>
					</td>
					<td>
						<input id="xmbz" class="easyui-combobox" data-options="valueField:'id', textField:'text', data:[{id:'1', text:'个性项目'},{id:'2', text:'共性项目'}]"/>
						<input id="_xmbz" name="xmlx" type="hidden">
					</td> 
					<td>
						科室名称:
					</td> 
					<td>
						<input id="ksmc" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/> 
						<input id="_ksnm" name="ksnm" type="hidden">
						<input id="_lsh" name="lsh" type="hidden">
					</td> 
				</tr>
				<tr>
					<td>
						项目名称:<span class="need">*</span>
					</td> 
					<td>
						<input id="xmmc" class="easyui-combobox" data-options="readonly:true,valueField:'id', textField:'text'"/>
						<input id="_xmmc" name="xmmc" type="hidden">
						<input id="_xmbm" name="xmbm" type="hidden">
						<a id="cxBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
					</td>
					<td>
						项目类别:<span class="need">*</span>
						
					</td> 
					<td>
						<input id="xmlb" class="easyui-combobox" data-options="readonly:true,valueField:'id', textField:'text'"/>
						<input id="_xmlbbm" name="xmlbbm" type="hidden"/>
						<input id="_xmlbmc" name="xmlbmc" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>
						核心类别:<span class="need">*</span>
					</td> 
					<td>
						<input id="hxlb" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/hxxmpz/queryHxlb'"/>
						<input id="_hxlbbm" name="hxlbbm" type="hidden">
						<input id="_hxlbmc" name="hxlbmc" type="hidden">
					</td> 
					<td>
						RBRVS点数:<span class="need">*</span>
					</td> 
					<td>
						<input id="dsde" type="text" class="easyui-numberbox" data-options="max:999999.99,precision:2"></input>
						<input id="_dsde" name="dsdeb" type="hidden">
					</td>
				</tr>
				<!-- <tr>
					 <td>
						点数基数A:<span class="need">*</span>
					</td> 
					<td>
						<input id="dsjs" name="dsjsa" type="text"/>
						<input id="dsjs" type="text" class="easyui-numberbox" data-options="max:999999.99,precision:2"></input>
						<input id="_dsjs" name="dsjsa" type="hidden">
					</td>
				</tr> -->
				<tr>
					<td colspan="4" style="text-align:center;">
						<a id="saveBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
						&emsp;&emsp;
						<a id="cancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="mh_window" title="核心项目-模糊查询" class="easyui-window" data-options="maximizable:true,minimizable:true" style="height: 400px;">
		<div id="mh_dg_tb">
		项目名称:<input id="mh_cxnr" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="mh_cxBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
		</div>
		<table id="mh_dg" data-options="fit:true" toolbar="#mh_dg_tb">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="xmbm" width="150">项目编码</th>
					<th field="xmmc" width="150">项目名称</th>
					<th field="dsdeb" width="150">RBRVS点数</th>
					<th field="hxlbmc" width="150">核心类别</th>
					<th field="hxlbbm" hidden="true"></th>
					<th field="xmlbmc" width="150">项目类别</th>
					<th field="xmlbbm" hidden="true"></th>
					<th field="xmbsmc" width="150">项目标识</th>
					<th field="xmbs" hidden="true"></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="importWin" title="导入" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="importform" method="post" enctype="multipart/form-data">
			<table style="width: 320px">
				<tr>
					<td>项目标识：</td> 
					<td>
					<div id="dr_xm">
						<input id="impRadio" type="radio" name="impxm" checked="checked" value="1"/>科室个性项目
						<input id="impRadio2" type="radio" name="impxm" value="2"/>共性性项目
					</div>
					</td>
				</tr>
				<tr>
					<td>科室名称:</td> 
					<td>
						<input id="impks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
						<input id="_imp_ks" name="ksnm" type="hidden">
					</td>
				</tr>
				<tr>
					<td>文件路径<span class="need">*</span>:</td> 
					<td>
						<!-- <input id="impwj" name="file" class="easyui-filebox"  data-options="buttonText:'选择文件(EXCEL)'"> -->
						<input id="impwj" type="file" name="file" accept="application/msexcel" >
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="importWin_Btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导入</a>
						&emsp;&emsp;
						<a id="impcancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td> 
				</tr>
			</table>
		</form>
	</div>
	
	<div id="exportWin" title="导出" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="exportform" method="post" enctype="multipart/form-data">
			<table style="width: 280px">
					<tr>
						<td>项目标识：</td> 
						<td>
						<div id="div_exp">
							<input id="expRadio" type="radio" name="expxm" checked="checked" value="1"/>科室个性项目
							<input id="expRadio2" type="radio" name="expxm" value="2"/>共性项目
						</div>
						</td>
					</tr>
					<tr>
						<td>科室名称:</td> 
						<td>
							<input id="expks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
							<input id="_exp_ks" name="ksnm" type="hidden">
						</td>
					</tr>
					<tr>
						<td>项目分类:</td> 
						<td>
							<input id="exp_xmfl" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/hxxmpz/queryXmfl'">
							<input id="_exp_xmfl" name="xmlbbm" type="hidden">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<a id="exportWin_Btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导出</a>
							&emsp;&emsp;
							<a id="expcancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
						</td> 
					</tr>
			</table>
		</form>
	</div>
</body>
</html>