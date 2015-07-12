<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>项目补录</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//补录主体的类型
			var bllx;
			
			//默认当前年月
			var mydate =   new Date();
			var myyear = mydate.getFullYear();
			var mymonth = mydate.getMonth()+1;
			if (String(mymonth).length<2) {
				mymonth = "0"+mymonth;
			}
			$("#imp_ny").val(myyear+"-"+mymonth);
			
			//设置额外查询参数
			var searchParams;
			var mh_search_ksmc_Params;
			var mh_search_ygxm_Params;
			var mh_search_hxxm_Params
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'yljx/hxxmcfbl/find',
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        fitColumns: true,
		        singleSelect:true,
		        toolbar:"#tb",
				onDblClickRow: function(rowIndex, rowData){
					
				}
			});
			
			function query(){
				searchParams = $("#dg").datagrid("options").queryParams;
				searchParams['nd'] = getNd();
				searchParams['yd'] = getYd();
				searchParams['ksmc'] = $("#q_ksmc").textbox("getValue");
				searchParams['ygmc'] = $("#q_ygmc").textbox("getValue");
				
				$("#dg").datagrid("reload");
			}
			
			$("#queryBtn").click(function(){
				query();
			});
			
			//点击补录
			$("#blBtn").click(function(){
				$("#bl_window").window("open");
				$("#bl_form").form("reset");
				$("#bl_ny").val(myyear+"-"+mymonth);
			});
			
			//隐藏掉按钮
			$("#bl_ksmc_queryBtn").hide();
    		$("#bl_ygmc_queryBtn").hide();
    		
			//补录主体的combobox
			$("#bl_zt").combobox({
				url:'js/hxxmcfbl.json',    
			    valueField:'value',    
			    textField:'label',  
			    method:"GET",
				onSelect:function(result) {
			    	bllx = result.value;
			    	var _d = $("#bl_ny").val();
			    	if (1==bllx) {
			    		$("#bl_form").form("reset");
			    		$("#bl_ksmc_queryBtn").hide();
			    		$("#bl_ygmc_queryBtn").show();
			    	} else if(2==bllx) {
			    		$("#bl_form").form("reset");
			    		$("#bl_ygmc_queryBtn").hide();
			    		$("#bl_ksmc_queryBtn").show();
			    	} else {
			    		$.messager.alert("警告","请选择一个补录主体!","warning");
			    	}
			    	$("#bl_zt").combobox("setValue", result.value);
		    		$("#bl_zt").combobox("setText", result.label);
			    	$("#bl_ny").val(_d);
			    }
			});
			
			//点击员工名称右边的查询
			$("#bl_ygmc_queryBtn").click(function(){
				$("#mh_ygxx_win").window("open");
				//初始化里面的datagrid
				$("#mh_ygxx_dg").datagrid({
					url: 'jjfp/grzxjc/findYgxx',
					rownumbers:true,
			        pagination:true,
			        fit:true,
			        singleSelect:true,
			        onClickRow: function(rowIndex, mh_selectedRow) {
			        	$("#bl_ygmc").textbox("setValue", mh_selectedRow.ygxm);
			        	$("#bl_ksmc").textbox("setValue", mh_selectedRow.ksmc);
			        	$("#bl_ygbh").val(mh_selectedRow.ygbh);
			        	$("#bl_ksnm").val(mh_selectedRow.ksnm);
			        	$("#mh_ygxx_win").window("close");
			        }
				});
			});
			
			//点击科室名称右边的查询
			$("#bl_ksmc_queryBtn").click(function(){
				$("#mh_ksxx_win").window("open");
				$("#mh_ksxx_dg").datagrid({
					url: 'jjfp/kszxjc/findKsxx',
					rownumbers:true,
			        pagination:true,
			        fit:true,
			        singleSelect:true,
			        onClickRow: function(rowIndex, mh_selectedRow) {
			        	$("#bl_ksmc").textbox("setValue", mh_selectedRow.ksmc);
			        	$("#bl_ksnm").val(mh_selectedRow.ksnm);
			        	$("#mh_ksxx_win").window("close");
			        }
				});
			});
			
			//点击项目名称右边的查询
			$("#bl_xmmc_queryBtn").click(function(){
				$("#mh_hxxm_win").window("open");
				//初始化里面的datagrid
				$("#mh_hxxm_dg").datagrid({
					url: 'yljx/hxxmpz/find',
					rownumbers:true,
			        pagination:true,
			        fit:true,
			        singleSelect:true,
			        onClickRow: function(rowIndex, mh_selectedRow) {
			        	$("#bl_xmmc").textbox("setValue", mh_selectedRow.xmmc);
			        	$("#_bl_xmbm").val(mh_selectedRow.xmbm);
			        	$("#bl_hxlb").combobox("setValue", mh_selectedRow.hxlbbm);
			        	$("#bl_hxlb").combobox("setText", mh_selectedRow.hxlbmc);
			        	$("#bl_xmlb").combobox("setValue", mh_selectedRow.xmlbbm);
			        	$("#bl_xmlb").combobox("setText", mh_selectedRow.xmlbmc);
			        	$("#bl_xmbsbm").val(mh_selectedRow.xmbs);
			        	$("#bl_xmbsmc").val(mh_selectedRow.xmbsmc);
			        	$("#bl_xmlxmc").val(mh_selectedRow.xmlbmc);
			        	$("#bl_hxlbmc").val(mh_selectedRow.hxlbmc);
			        	//$("#bl_dsjsa").numberbox("setValue", mh_selectedRow.dsjsa);
			        	$("#bl_dsdeb").numberbox("setValue", mh_selectedRow.dsdeb);
			        	$("#mh_hxxm_win").window("close");
			        }
				});
			});
			
			//点击科室名称后面的 模糊查询按钮
			$("#mh_ksxx_queryBtn").click(function(){
				mh_search_ksmc_Params = $("#mh_ksxx_dg").datagrid("options").queryParams;
				mh_search_ksmc_Params['ksmc'] = $("#mh_ksmc_query").textbox("getValue");
				$("#mh_ksxx_dg").datagrid("reload");
				
			});
			
			//点击员工姓名后面的模糊查询
			$("#mh_ygxx_queryBtn").click(function(){
				mh_search_ygxm_Params = $("#mh_ygxx_dg").datagrid("options").queryParams;
				mh_search_ygxm_Params['ygxm'] = $("#mh_ygmc_query").textbox("getValue");
				$("#mh_ygxx_dg").datagrid("reload");
			});
			
			//点击核心项目后面的模糊查询
			$("#mh_xmmc_queryBtn").click(function(){
				mh_search_hxxm_Params = $("#mh_hxxm_dg").datagrid("options").queryParams;
				mh_search_hxxm_Params['xmmc'] = $("#mh_xmmc_query").textbox("getValue");
				$("#mh_hxxm_dg").datagrid("reload");
			});
			
			//点击保存
			$("#bl_saveBtn").click(function(){
				if (check()){
					$("#bl_form").form("submit",{
						url:'yljx/hxxmcfbl/'+bllx,
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
			                    $("#bl_window").window("close");
							} else {
								$.messager.alert("警告","操作失败！","warning");
								//$("#bl_window").window("close");
							}
						}
					});
				}
			});
			
			//点击导入
			$("#importBtn").click(function(){
				$("#cfbl_import").window("open");
			});
			
			//判断导入必选项是否选择
			$("#importWinBtn").click(function() {
				var ks = $("#imp_ks").combobox("getValue");
				var ny = $("#imp_ny").val();
				var wj = $("#imp_wj").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1, ny.indexOf("-")+3);
				
				if (""==ks || null==ks) {
					$.messager.alert("警告","科室名称不能为空!","warning");
					return;
				}
				if (""==ny || null==ny) {
					$.messager.alert("警告","年月不能为空!","warning");
					return;
				}
				if (""==wj || null==wj) {
					$.messager.alert("警告","请选择上传文件!","warning");
					return;
				}
				
				$("#impform").form("submit",{
					url:'yljx/hxxmcfbl/import',
					onSubmit:function(){
						$("#_imp_ks").val(ks);
						$("#imp_nd").val(nd);
						$("#imp_yd").val(yd);
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
							$.messager.alert("警告","导入失败,数据格式不正确!","warning");
							$("#importWin").window("close");
						}
					}
				});
			});
			
			//删除
			$("#removeBtn").click(function(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow){
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				$.messager.confirm("确认", "是否删除该条记录?", function(r){
					if(!r) return ;
						$.post("yljx/hxxmcfbl/deleteById",{
							lsh:selectedRow.lsh,
							lx:selectedRow.lx
						},
						function (result){
							if ("success" == result) {
								$.messager.show({
									title:"操作提示",
									msg:"删除成功！",
									timeout:3000
								});
								$('#dg').datagrid('reload');
							} else {
								$.messager.alert("警告","删除失败！","warning");
							}
						});
				});
			});
			
			
			//向隐藏域赋值
			function setForm() {
				var ny = $("#bl_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1, ny.indexOf("-")+3);
				$("#bl_nd").val(nd);
				$("#bl_yd").val(yd);
				$("#_bl_ksmc").val($("#bl_ksmc").textbox("getValue"));
				$("#_bl_ygmc").val($("#bl_ygmc").textbox("getValue"));
				$("#_bl_xmmc").val($("#bl_xmmc").textbox("getValue"));
				$("#bl_hxlbbm").val($("#bl_hxlb").combobox("getValue"));
				$("#bl_xmlbbm").val($("#bl_xmlb").combobox("getValue"));
				//$("#_bl_dsjsa").val($("#bl_dsjsa").numberbox("getValue"));
				$("#_bl_dsdeb").val($("#bl_dsdeb").numberbox("getValue"));
				$("#_bl_sl").val($("#bl_sl").numberbox("getValue"));
			}
			
			//前端验证
			function check(){
				var ck_ny = $("#bl_ny").val();
				var ck_blzt = $("#bl_zt").combobox("getValue");
				var ck_xmmc = $("#bl_xmmc").textbox("getValue");
				var ck_hxlb = $("#bl_hxlb").combobox("getValue");
				var ck_xmlb = $("#bl_xmlb").combobox("getValue");
				//var ck_dsjsa = $("#bl_dsjsa").numberbox("getValue");
				var ck_dsdeb = $("#bl_dsdeb").numberbox("getValue");
				var ck_sl = $("#bl_sl").numberbox("getValue");
				
				if (""==ck_ny || null==ck_ny){
					$.messager.alert("警告","请选择年月!","warning");
					return false;
				}
				if (""==ck_blzt || null==ck_blzt) {
					$.messager.alert("警告","请选择补录的主体!","warning");
					return false;
				}
				if (""==ck_xmmc || null==ck_xmmc) {
					$.messager.alert("警告","请选择项目名称!","warning");
					return false;
				}
				if (""==ck_hxlb || null==ck_hxlb) {
					$.messager.alert("警告","请选择核心类别!","warning");
					return false;
				}
				if (""==ck_xmlb || null==ck_xmlb) {
					$.messager.alert("警告","请选择项目类别!","warning");
					return false;
				}
				if (""==ck_dsdeb || null==ck_dsdeb) {
					$.messager.alert("警告","点数定额不能为空!","warning");
					return false;
				}
				if (""==ck_sl || null==ck_sl) {
					$.messager.alert("警告","数量不能为空!","warning");
					return false;
				}
				return true;
			}
			
			//获取年度
			function getNd(){
				var ny = $("#q_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				return nd;
			}
			
			//获取月度
			function getYd(){
				var ny = $("#q_ny").val();
				var yd = ny.substring(ny.indexOf("-")+1,ny.indexOf("-")+3);
				return yd;
			}
			
			//点击取消 关闭等按钮
			$("#bl_cancelBtn").click(function(){
				$("#bl_window").window("close");
			});
			
			$("#impcancelBtn").click(function() {
				$("#cfbl_import").window("close");
			});
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="项目筛选" class="search-condition" data-options="region:'north',collapsible:false">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			年月:<input id="q_ny" type="text" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
			科室名称:<input id="q_ksmc" class="easyui-textbox"/>
			员工名称:<input id="q_ygmc" class="easyui-textbox"/>
		</fieldset>
	</div>
	
	<div title="项目补录" data-options="region:'center'">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="lsh" hidden="true"></th>
					<th field="lx" hidden="true"></th>
					<th field="nd" width="100">年度</th>
					<th field="yd" width="100">月度</th>
					<th field="ksmc" width="150">科室名称</th>
					<th field="ygmc" width="150">员工名称</th>
					<th field="xmmc" width="150">项目名称</th>
					<!-- <th field="dsjsa" width="150">点数基数A</th> -->
					<th field="sl" width="150">数量</th>
					<!--  <th field="zt" width="100">状态</th> -->
					
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="blBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'',plain:true">补录</a>
		<!-- <a id="cfBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'',plain:true">拆分</a>   -->
		<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	
	<div id="bl_window" title="项目补录--补录" class="easyui-window win search-condition" data-options="maximizable:true,minimizable:true">
		<form id="bl_form" method="post">
			<fieldset>
				<legend>补录信息(带“*”为必填)</legend>
				<table>
					<tr>
						<td>补录年月<span class="need">*</span>:</td>
						<td>
							<input id="bl_ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
							<input id="bl_nd" name="nd" type="hidden"/>
							<input id="bl_yd" name="yd" type="hidden"/>
						</td> 
						<td>补录对象<span class="need">*</span>:</td>
						<td>
							<!-- <input id="bl_zt" class="easyui-combobox" data-options="textField: 'label',valueField: 'value',data: [{label: '个人',value: '1'},
																																	{label: '科室',value: '2'}]"/>  -->
							<input id="bl_zt" required="true" style="width:150px;"/>
						</td>
					</tr>
					<tr>
						<td>科室名称:</td>
						<td>
							<input id="bl_ksmc" class="easyui-textbox" readonly="true"/>
							<input id="bl_ksnm" name="ksnm" type="hidden"/>
							<input id="_bl_ksmc" name="ksmc" type="hidden"/>
							<a id="bl_ksmc_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
						</td> 
						<td>员工名称:</td>
						<td>
							<input id="bl_ygmc" class="easyui-textbox" readonly="true"/>
							<input id="bl_ygbh" name="ygbh" type="hidden"/>
							<input id="_bl_ygmc" name="ygmc" type="hidden"/>
							<a id="bl_ygmc_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
						</td>
					</tr>
				</table>
			</fieldset>
			&emsp;
			<fieldset>
				<legend>项目信息(带“*”为必填)</legend>
				<table>
					<tr>
						<td>项目名称<span class="need">*</span>:</td>
						<td>
							<input id="bl_xmmc" class="easyui-textbox" required="true" readonly="true"/>
							<input id="_bl_xmmc" name="xmmc" type="hidden"/>
							<input id="_bl_xmbm" name="xmbm" type="hidden"/>
							<a id="bl_xmmc_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 查询</a>
						</td> 
						<td></td><td></td>
					</tr>
					<tr>
						<td>核心类别<span class="need">*</span>:</td>
						<td>
							<input id="bl_hxlb" class="easyui-combobox" required="true" readonly="true" data-options="valueField:'id', textField:'text'"/>
							<input id="bl_hxlbbm" name="hxlbbm" type="hidden"/>
							<input id="bl_hxlbmc" name="hxlbmc" type="hidden"/>
							<input id="bl_xmbsbm" name="xmbsbm" type="hidden"/>
							<input id="bl_xmbsmc" name="xmbsmc" type="hidden"/>
						</td> 
						<td>项目类别<span class="need">*</span>:</td>
						<td>
							<input id="bl_xmlb" class="easyui-combobox" required="true" readonly="true" data-options="valueField:'id', textField:'text'"/>
							<input id="bl_xmlbbm" name="xmlbbm" type="hidden"/>
							<input id="bl_xmlxmc" name="xmlbmc" type="hidden"/>
						</td>
					</tr>
					<tr>
						<!-- <td>基础点数<span class="need">*</span>:</td>
						<td>
							<input id="bl_dsjsa" class="easyui-numberbox" required="true" readonly="true" data-options="min:0,precision:2,missingMessage:'必须输入数字'">
							<input id="_bl_dsjsa" name="dsjsa" type="hidden"/>
						</td>  -->
						<td>RBRVS点数<span class="need">*</span>:</td>
						<td style="text-align: left;">
							<input id="bl_dsdeb" class="easyui-numberbox" required="true" readonly="true" data-options="min:0,precision:2,missingMessage:'必须输入数字'">
							<input id="_bl_dsdeb" name="dsdeb" type="hidden"/>
						</td>
						<td style="text-align:right;">数量<span class="need">*</span>:</td>
						<td style="text-align:left;">
							<input id="bl_sl" class="easyui-numberbox" required="true" data-options="min:0,precision:2,missingMessage:'必须输入数字'">
							<input id="_bl_sl" name="sl" type="hidden"/>
						</td> 
					</tr>
				</table>
			</fieldset>
			&emsp;
			<div align="center">
				<a id="bl_saveBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
					&emsp;&emsp;
				<a id="bl_cancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
			</div>
		</form>
	</div>
	
	<div id="mh_hxxm_win" title="项目-模糊查询" class="easyui-window" data-options="maximizable:true,minimizable:true" style="height:400px; width:750px">
		<div id="mh_hxxm_tb">
			项目名称:<input id="mh_xmmc_query" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="mh_xmmc_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 模糊查询</a>
		</div>
		<table id="mh_hxxm_dg" data-options="fit:true" toolbar="#mh_hxxm_tb">
			<thead>
				<tr>
					<th field="xmbm" width="100">项目编号</th>
					<th field="xmmc" width="100">项目名称</th>
					<!-- <th field="dsjsa" width="100">点数基数A</th> -->
					<th field="dsdeb" width="100">RBRVS点数</th>
					<th field="hxlbmc" width="100">核心类别</th>
					<th field="hxlbbm" hidden="true"></th>
					<th field="xmlbmc" width="100">项目类别</th>
					<th field="xmlbbm" hidden="true"></th>
					<th field="xmlx" hidden="true"></th>
					<th field="xmbs" width="100">项目标识</th>
					<th field="xmbsmc" hidden="true"></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="mh_ygxx_win" title="员工信息-模糊查询" class="easyui-window" data-options="maximizable:true,minimizable:true" style="height:400px; width:450px">
		<div id="mh_ygxx_tb">
			员工名称:<input id="mh_ygmc_query" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="mh_ygxx_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 模糊查询</a>
		</div>
		<table id="mh_ygxx_dg" data-options="fit:true" toolbar="#mh_ygxx_tb">
			<thead>
				<tr>
					<th field="ksnm" hidden="true"></th>
					<th field="ygbh" width="100">员工编号</th>
					<th field="ygxm" width="100">员工姓名</th>
					<th field="ksmc" width="100">所在科室名称</th>
					<th field="kslb" width="100">科室类别</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="mh_ksxx_win" title="科室信息-模糊查询" class="easyui-window" data-options="maximizable:true,minimizable:true" style="height:400px; width:650px">
		<div id="mh_ksxx_tb">
			科室名称:<input id="mh_ksmc_query" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="mh_ksxx_queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 模糊查询</a>
		</div>
		<table id="mh_ksxx_dg" data-options="fit:true" toolbar="#mh_ksxx_tb">
			<thead>
				<tr>
					<th field="ksnm" hidden="true"></th>
					<th field="ksnm" width="100">科室编码</th>
					<th field="ksmc" width="100">科室名称</th>
					<th field="fjmc" width="100">上级科室</th>
					<th field="kslb" width="100">科室类别</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="cfbl_import" title="项目拆分补录-导入" class="easyui-window win" data-options="maximizable:true,minimizable:true" >
		<form id="impform" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>科室<span class="need">*</span></td> 
					<td>
						<input id="imp_ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'">
						<input id="_imp_ks" name="ksnm" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>年月<span class="need">*</span></td> 
					<td>
						<input id="imp_ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="imp_nd" name="nd" type="hidden">
						<input id="imp_yd" name="yd" type="hidden">
					</td>
				</tr>
				<tr>
					<td>导入路径<span class="need">*</span>:</td> 
					<td>
						<input id="imp_wj" name="file" type="file" style="width:200px" accept="application/msexcel">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="importWinBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导入</a>
							&emsp;&emsp;
						<a id="impcancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
				<tr><td></td> <td></td></tr>
			</table>
		</form>
	</div>
</body>
</html>