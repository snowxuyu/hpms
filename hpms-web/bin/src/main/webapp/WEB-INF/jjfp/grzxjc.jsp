<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人专项奖惩维护</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//初始化easyUI，关闭class为easyui-window的div
			$(".easyui-window").window("close");
			
			//设置新增/修改窗口界面的样式
			$("#window tr:not(:last) td:even").css("textAlign","right");
			
			//定义操作类型
			var operate;
			
			//定义标题
			var title = ["个人专项奖金-新增", "个人专项奖金-修改"];
			
			//默认获取当前年月
			var mydate = new Date();
			var year = mydate.getFullYear();
			var month = mydate.getMonth() + 1;
			month = String(month).length <= 1 ? "0" + month : month;
			$("#q_ny").val(year+"-"+month);
			$("#imp_ny").val(year+"-"+month);
			$("#exp_ny").val(year+"-"+month);
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'jjfp/grzxjc/find',
				queryParams: {
					"nd":getNd(),
					"yd":getYd()
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        singleSelect:true,
		        toolbar:"#tb",
		        onDblClickRow: function(rowIndex, rowData){
					update();
				}
			});
			
			//设置额外查询参数
			var searchParams;  
			
			//模糊查询的参数
			var mh_searchParam;
			
			//点击查询按钮
			$("#queryBtn").click(function(){
				query();
			});
			
			//查询实现
			function query(){
				//获取查询参数
				var nd = getNd();
				var yd = getYd();
				if (""==nd || null==nd){
					$.messager.alert("警告","年月不能为空！","warning");
					return;
				}
				searchParams = $("#dg").datagrid("options").queryParams; 
				searchParams['nd'] = nd;
				searchParams['yd'] = yd;
				searchParams['ksnm'] = $("#q_ks").combobox("getValue");
				searchParams['ygxm'] = $("#q_yg").textbox("getValue");
				$("#dg").datagrid("reload");
			}
			
			//点击新增按钮
			$("#addBtn").click(function(){
				operate = "add";
				$("#form").form("reset");
				$("#window").window("setTitle",title[0]);
				$("#window").window("open");
				
				//新增设置时间框为可修改
				$("#ny").attr('disabled',false);
				//显示按钮
				$("#xzryBtn").show();
			});
			
			$("#updateBtn").click(function(){
				update();
			});
			
			
			//更新,修改
			function update(){
				var selectedRow = $("#dg").datagrid("getSelected");
				if (!selectedRow){
					$.messager.alert("警告","当前没有选择行！","warning");
					return false;
				}
				operate = "update";
				$("#window").window("setTitle",title[1]);
				$("#window").window("open");
				
				//设置年月输入框为只读模式,不可修改
				$("#ny").attr('disabled',true);
				
				//隐藏选中人员按钮
				$("#xzryBtn").hide();
				
				//把选中行的值带到form表单
				$("#_lsh").val(selectedRow.lsh);
				$("#_ksnm").val(selectedRow.ksnm);
				$("#ny").val(selectedRow.nd+"-"+selectedRow.yd);
				$("#ygxm").combobox("setValue",selectedRow.ygbh);
				$("#ybkjb").numberbox("setValue", selectedRow.zx1);
				$("#bbk").numberbox("setValue", selectedRow.zx2);
				$("#ldjl").numberbox("setValue", selectedRow.zx3);
				$("#gffw").numberbox("setValue", selectedRow.zx4);
				$("#byjl").numberbox("setValue", selectedRow.zx5);
				$("#qmzlyj").numberbox("setValue", selectedRow.zx6);
				$("#qmzlhl").numberbox("setValue", selectedRow.zx7);
				$("#jfts").numberbox("setValue", selectedRow.zx8);
				$("#hlzl").numberbox("setValue", selectedRow.zx9);
				$("#wcjlw").numberbox("setValue", selectedRow.zx10);
				$("#grzx_qt").numberbox("setValue", selectedRow.zx11);
				$("#mjzcf").numberbox("setValue", selectedRow.zx12);
				$("#dcasx").numberbox("setValue", selectedRow.zx13);
				$("#cdmz").numberbox("setValue", selectedRow.zx14);
				
			}
			
			//点击 新增/修改的保存按钮
			$("#saveBtn").click(function(){
				 if (check()) {
					$("#form").form("submit", {
						url: "jjfp/grzxjc/"+operate,
						onSubmit: function(){
							setForm();
						},
						success: function(result){
							if ("success"==result) {
								$.messager.show({
									title:"操作提示",
									msg:"操作成功！",
									timeout:3000
								});

								$('#dg').datagrid('reload');
			                    $("#window").window("close");
							} else {
								$.messager.alert("警告","操作失败！","warning");
								$("#window").window("close");
								$('#dg').datagrid('reload');
							}
						}
					});
				}
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
					$.post("jjfp/grzxjc/deleteById",{
						lsh:selectedRow.lsh
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
			
			//导入奖金
			$("#importBtn").click(function(){
				$("#importWin").window("open");
			});
			
			//点击导入按钮
			$("#importWinBtn").click(function(){
				var ny = $("#imp_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1, ny.indexOf("-")+3);
				
				var wj = $("#imp_wj").val();
				if (""==ny || null==ny) {
					$.messager.alert("警告","年月日期不能为空!","warning");
					return;
				}
				if (""==wj || null==wj) {
					$.messager.alert("警告","上传文件为空!","warning");
					return;
				}
				$("#impform").form("submit",{
					url:'jjfp/grzxjc/importGrzxjj',
					onSubmit:function(){
						//给隐藏域赋值
						$("#imp_nd").val(nd);
						$("#imp_yd").val(yd);
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
							$.messager.alert("警告","导入失败,数据格式不正确!","warning");
							$("#importWin").window("close");
						}
					}
				});
			});
			
			//个人专项奖金导出
			$("#exportBtn").click(function(){
				$("#exportWin").window("open");
			});
			
			//点击导出
			$("#exportWinBtn").click(function(){
				var ny = $("#exp_ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1, ny.indexOf("-")+3);
				
				if (""==ny || null==ny) {
					$.messager.alert("警告","年月日期不能为空!","warning");
					return;
				}
				
				$("#expform").form("submit",{
					url:'jjfp/grzxjc/exportGrzxjj',
					onSubmit:function(){
						//给隐藏域赋值
						$("#_exp_ks").val($("#exp_ks").combobox("getValue"));
						$("#exp_nd").val(nd);
						$("#exp_yd").val(yd);
						$("#exportWin").window("close");
					},
					success:function(result){
						
					}
				});
				$("#exportWin").window("close");
			});
			
			
			
			//点击新增/修改页面的选择人员按钮
			$("#xzryBtn").click(function(){
				$("#mh_window").window("open");
				
				//初始化窗口里面的datagrid
				$("#mh_dg").datagrid({
					url:'jjfp/grzxjc/findYgxx',
					rownumbers:true,
			        pagination:true,
			        fit:true,
			        singleSelect:true,
			        onClickRow: function(rowIndex, mh_selectedRow) {
			        	$("#ygxm").combobox("setValue", mh_selectedRow.ygbh);
			        	$("#_ksnm").val(mh_selectedRow.ksnm);
			        	
			        	//关闭模糊查询的页面
						$("#mh_window").window("close");
			        }
				});
			});
			
			//点击模糊查询界面的查询按钮
			$("#mh_cxBtn").click(function(){
				mh_searchParam = $("#mh_dg").datagrid("options").queryParams;
				mh_searchParam['ygxm'] = $("#mh_cxnr").textbox("getValue");
				$("#mh_dg").datagrid("reload");
			});
			
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
			
			//向隐藏域赋值
			function setForm(){
				var ny = $("#ny").val();
				var nd = ny.substring(0, ny.indexOf("-"));
				var yd = ny.substring(ny.indexOf("-")+1, ny.indexOf("-")+3);
				$("#_nd").val(nd);
				$("#_yd").val(yd);
				$("#_ksnm").val();
				$("#_ygbh").val($("#ygxm").combobox("getValue"));
			 	$("#_zx1").val($("#ybkjb").numberbox("getValue"));
			 	$("#_zx2").val($("#bbk").numberbox("getValue"));
				$("#_zx3").val($("#ldjl").numberbox("getValue"));
				$("#_zx4").val($("#gffw").numberbox("getValue"));
				$("#_zx5").val($("#byjl").numberbox("getValue"));
				$("#_zx6").val($("#qmzlyj").numberbox("getValue"));
				$("#_zx7").val($("#qmzlhl").numberbox("getValue"));
				$("#_zx8").val($("#jfts").numberbox("getValue"));
				$("#_zx9").val($("#hlzl").numberbox("getValue"));
				$("#_zx10").val($("#wcjlw").numberbox("getValue"));
				$("#_zx11").val($("#grzx_qt").numberbox("getValue"));
				$("#_zx12").val($("#mjzcf").numberbox("getValue"));
				$("#_zx13").val($("#dcasx").numberbox("getValue"));
				$("#_zx14").val($("#cdmz").numberbox("getValue"));
			}
			
			//前端验证
			function check(){
				var ny = $("#ny").val();
				var ygxm = $("#ygxm").combobox("getValue");
				if (""==ny || null==ny){
					$.messager.alert("警告","年月日期不能为空!","warning");
					return false;
				}
				if (""==ygxm || null==ygxm){
					$.messager.alert("警告","员工姓名不能为空!","warning");
					return false;
				}
				return true;
			}
			
			
			//关闭新增/修改的窗口
			$("#cancelBtn").click(function(){
				$("#window").window("close");
			});
			$("#impcancelBtn").click(function(){
				$("#importWin").window("close");
			});
			$("#expcancelBtn").click(function(){
				$("#exportWin").window("close");
			});
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="个人专项奖惩检索" class="search-condition" data-options="region:'north', collapsible:false">
		<fieldset>
			<legend>筛选(带*号为必填)</legend>
			年月<span class="need">*</span>:<input id="q_ny" type="text" readonly="readonly" required="required" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
			科室:<input id="q_ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
			员工:<input id="q_yg" class="easyui-textbox">
		</fieldset>
	</div>
	<div title="个人专项奖惩维护" data-options="region:'center'">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="lsh" hidden="true"></th>
					<th field="nd" width="40">年度</th>
					<th field="yd" width="35">月度</th>
					<th field="ksmc" width="90">科室名称</th>
					<th field="ksnm" hidden="true"></th>
					<th field="ygxm" width="60">员工</th>
					<th field="ygbh" hidden="true"></th>
					<th field="zx1" width="90" align='right'>药比抗菌比奖惩</th>
					<th field="zx2" width="70" align='right'>报病卡奖惩</th>
					<th field="zx3" width="80" align='right'>劳动纪律奖惩</th>
					<th field="zx4" width="60" align='right'>规范服务</th>
					<th field="zx5" width="60" align='right'>表扬奖励</th>
					<th field="zx6" width="110" align='right'>全面质量奖惩(医技)</th>
					<th field="zx7" width="110" align='right'>全面质量奖惩(护理)</th>
					<th field="zx8" width="60" align='right'>纠纷投诉</th>
					<th field="zx9" width="80" align='right'>护理质量奖惩</th>
					<th field="zx10" width="70" align='right'>胃肠镜劳务</th>
					<th field="zx11" width="80" align='right'>个人专项-其它</th>
					<th field="zx12" width="150" align='right'>门急诊处方不合理用药奖惩</th>
					<th field="zx13" width="90" align='right'>大肠癌筛查加班</th>
					<th field="zx14" width="90" align='right'>肠道门诊加班费</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="tb">
		<a id="queryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"> 查询</a>
		<a id="addBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<a id="updateBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		<a id="removeBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a id="importBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true">导入奖金</a>
		<a id="exportBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true">导出奖金(模板)</a>
	</div>
	<div id="window" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="form" method="post">
			<table>
				<tr>
					<td>年月<span class="need">*</span>:</td> 
					<td>
						<input id="ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="_nd" name="nd" type="hidden">
						<input id="_yd" name="yd" type="hidden">
						<input id="_lsh" name="lsh" type="hidden">
					</td>
					
					<td>员工姓名<span class="need">*</span>:</td> 
					<td>
						<input id="ygxm" class="easyui-combobox" readonly="true" required="true" data-options="valueField:'id', textField:'text', url:'jjfp/grzxjc/queryYG'"/>
						<input id="_ygbh" name="ygbh" type="hidden"/>
						<input id="_ksnm" name="ksnm" type="hidden">
						<a id="xzryBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 选择人员</a>
					</td>
				</tr>
				<tr>
					<td>药比抗菌比奖惩:</td> 
					<td>
						<input id="ybkjb" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_zx1" name="zx1" type="hidden">
					</td> 
					<td>报病卡奖惩:</td> 
					<td>
						<input id="bbk" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_zx2" name="zx2" type="hidden">
					</td>
				</tr>
				<tr>
					<td>劳动纪律奖惩:</td> 
					<td>
						<input id="ldjl" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_zx3" name="zx3" type="hidden">
					</td> 
					<td>规范服务:</td>
					<td>
						<input id="gffw" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_zx4" name="zx4" type="hidden">
					</td>
				</tr>
				<tr>
					<td>表扬奖励:</td> 
					<td>
						<input id="byjl" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'"/>
						<input id="_zx5" name="zx5" type="hidden">
					</td> 
					<td>全面质量奖惩（医技）:</td> 
					<td>
						<input id="qmzlyj" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx6" name="zx6" type="hidden">
					</td>
				</tr>
				<tr>
					<td>全面质量奖惩（护理）:</td> 
					<td>
						<input id="qmzlhl" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx7" name="zx7" type="hidden">
					</td> 
					<td>纠纷投诉:</td> 
					<td>
						<input id="jfts" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx8" name="zx8" type="hidden">
					</td>
				</tr>
				<tr>
					<td>护理质量奖惩:</td> 
					<td>
						<input id="hlzl" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx9" name="zx9" type="hidden">
					</td> 
					<td>胃肠镜劳务:</td> 
					<td>
						<input id="wcjlw" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx10" name="zx10" type="hidden">
					</td>
				</tr>
				<tr>
					<td>个人专项-其它:</td> 
					<td>
						<input id="grzx_qt" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx11" name="zx11" type="hidden">
					</td> 
					<td>门急诊处方不合理用药奖惩:</td> 
					<td>
						<input id="mjzcf" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx12" name="zx12" type="hidden">
					</td>
				</tr>
				<tr>
					<td>大肠癌筛查加班:</td> 
					<td>
						<input id="dcasx" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx13" name="zx13" type="hidden">
					</td> 
					<td>肠道门诊加班费:</td> 
					<td>
						<input id="cdmz" class="easyui-numberbox" data-options="min:0,max:999999,precision:2,missingMessage:'必须输入数字'">
						<input id="_zx14" name="zx14" type="hidden">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<a id="saveBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
						&emsp;&emsp;
						<a id="cancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="mh_window" title="员工信息-模糊查询" class="easyui-window" data-options="maximizable:true,minimizable:true" style="height:400px; width:550px">
		<div id="mh_dg_tb">
		员工姓名<span class="need">*</span>:<input id="mh_cxnr" class="easyui-textbox" data-options="iconCls:'icon-search'"/>
			<a id="mh_cxBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'"> 模糊查询</a>
		</div>
		<table id="mh_dg" data-options="fit:true" toolbar="#mh_dg_tb">
			<thead>
				<tr>
					<th field="ygbh" width="100">员工编号</th>
					<th field="ygxm" width="150">员工姓名</th>
					<th field="ksmc" width="150">所在科室名称</th>
					<th field="ksnm" hidden="true"></th>
					<th field="kslb" width="100">科室类别</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="importWin" title="个人专项奖金导入" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="impform" method="post" enctype="multipart/form-data">
			<table width="300px">
				<tr>
					<td>年月<span class="need">*</span>:</td> 
					<td>
						<input id="imp_ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="imp_nd" name="nd" type="hidden"/>
						<input id="imp_yd" name="yd" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>导入路径<span class="need">*</span>:</td> 
					<td>
						<input id="imp_wj" name="file" type="file" style="width:200px" accept="application/msexcel">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a id="importWinBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导入</a>
						&emsp;&emsp;
						<a id="impcancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="exportWin" title="个人专项奖金导出" class="easyui-window win" data-options="maximizable:false,minimizable:false">
		<form id="expform" method="post" enctype="multipart/form-data">
			<table width="250px">
				<tr>
					<td>科室<span class="need">*</span>:</td>
					<td>
						<input id="exp_ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
						<input id="_exp_ks" name="ksnm" type="hidden">
					</td>
				</tr>
				<tr>
					<td>年月<span class="need">*</span>:</td> 
					<td>
						<input id="exp_ny" type="text" required="true" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM'})"/>
						<input id="exp_nd" name="nd" type="hidden"/>
						<input id="exp_yd" name="yd" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="exportWinBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导出</a>
						&emsp;&emsp;
						<a id="expcancelBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>