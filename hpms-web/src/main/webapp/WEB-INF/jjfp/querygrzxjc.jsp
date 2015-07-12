<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksnm = request.getAttribute("ksnm"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人专项奖惩维护-查询</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
			//初始化个人datagrid
			$("#dg1").datagrid({
				url:'jjfp/querygrzxjc/findGr',
				queryParams: {
					nd:<%=nd%>,
					yd:<%=yd%>,
					ksnm:<%=ksnm%>
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        columns:[[    
							{field:'lsh',hidden:true},
		                  	{field:'ygxm',title:'员工姓名',width:70,align:'right'},
		                  	{field:'zx1', title:'药比抗菌比奖惩',width:90, align:'right'},
		                  	{field:'zx2', title:'报病卡奖惩',width:70, align:'right'},
		                  	{field:'zx3', title:'劳动纪律奖惩',width:80, align:'right'},
		                  	{field:'zx4', title:'规范服务',width:60, align:'right'},
		                  	{field:'zx5', title:'表扬奖励',width:60, align:'right'},
		                  	{field:'zx6', title:'全面质量奖惩(医技)',width:110, align:'right'},
		                  	{field:'zx7', title:'全面质量奖惩(护理)',width:110, align:'right'},
		                  	{field:'zx8', title:'纠纷投诉',width:60, align:'right'},
		                  	{field:'zx9', title:'护理质量奖惩',width:80, align:'right'},
		                  	{field:'zx10', title:'胃肠镜劳务',width:70, align:'right'},
		                  	{field:'zx11', title:'个人专项-其它',width:80, align:'right'},
		                  	{field:'zx12', title:'门急诊处方不合理用药奖惩',width:150, align:'right'},
		                  	{field:'zx13', title:'大肠癌筛查加班',width:90, align:'right'},
		                  	{field:'zx14', title:'肠道门诊加班费',width:90, align:'right'}
		              ]],   
		        singleSelect:true,
		        onLoadSuccess: function() {
		        	var grArr = ["zx1","zx2","zx3","zx4","zx5","zx6","zx7","zx8","zx9","zx10","zx11","zx12","zx13","zx14"];
		        	var dataObj = $("#dg1").datagrid('getRows');
		        	for (var i=0; i<dataObj; i++) {
		        		for (var j=0; j<grArr; j++) {
		        			if (dataObj[i][grArr[j]]=="" ||dataObj[i][grArr[j]]=="0" ||dataObj[i][grArr[j]]=="0.0" ||dataObj[i][grArr[j]]==0) {
		        				$("#dg1").datagrid('hideColumn',grArr[j]);
		        			}
		        		}
		        	}
		        }
			});
			
		
			
			//初始化科室datagrid
			$("#dg2").datagrid({
				url:'jjfp/querygrzxjc/findKs',
				queryParams: {
					nd:<%=nd%>,
					yd:<%=yd%>,
					ksnm:<%=ksnm%>
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        columns:[[    
							{field:'lsh',hidden:true},
		                  	{field:'zx1', title:'药比抗菌比奖惩',width:90, align:'right'},
		                  	{field:'zx2', title:'报病卡奖惩',width:70, align:'right'},
		                  	{field:'zx3', title:'劳动纪律奖惩',width:80, align:'right'},
		                  	{field:'zx4', title:'规范服务',width:60, align:'right'},
		                  	{field:'zx5', title:'表扬奖励',width:60, align:'right'},
		                  	{field:'zx6', title:'全面质量奖惩(医技)',width:110, align:'right'},
		                  	{field:'zx7', title:'全面质量奖惩(护理)',width:110, align:'right'},
		                  	{field:'zx8', title:'纠纷投诉',width:60, align:'right'},
		                  	{field:'zx9', title:'护理质量奖惩',width:80, align:'right'},
		                  	{field:'zx10', title:'胃肠镜劳务',width:70, align:'right'},
		                  	{field:'zx11', title:'个人专项-其它',width:80, align:'right'},
		                  	{field:'zx12', title:'门急诊处方不合理用药奖惩',width:150, align:'right'},
		                  	{field:'zx13', title:'大肠癌筛查加班',width:90, align:'right'},
		                  	{field:'zx14', title:'肠道门诊加班费',width:90, align:'right'}
		              ]], 
		        singleSelect:true,
		        onLoadSuccess: function() {
		        	var grArr = ["zx1","zx2","zx3","zx4","zx5","zx6","zx7","zx8","zx9","zx10","zx11","zx12","zx13","zx14"];
		        	var dataObj = $("#dg2").datagrid('getRows');
		        	for (var i=0; i<dataObj; i++) {
		        		for (var j=0; j<grArr; j++) {
		        			if (dataObj[i][grArr[j]]=="" ||dataObj[i][grArr[j]]=="0" ||dataObj[i][grArr[j]]=="0.0" ||dataObj[i][grArr[j]]==0) {
		        				$("#dg2").datagrid('hideColumn',grArr[j]);
		        			}
		        		}
		        	}
		        }
			});
			
					
		});
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" class="easyui-tabs">
		<div id="gr" title="${ksmc }-${nd }年${yd }月个人专项奖惩维护查询" data-options="region:'center'">
			<table id="dg1">
				<!-- <thead>
					<tr>
						<th field="lsh" hidden="true"></th>
						<th field="ygxm" width="70">员工姓名</th>
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
				</thead> -->
			</table>
		</div>
		
		<div id="ks" title="${ksmc }-${nd }年${yd }月专项奖惩维护查询" data-options="region:'center'">
			<table id="dg2">
				<!-- <thead>
					<tr>
						<th field="lsh" hidden="true"></th>
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
				</thead> -->
			</table>
		</div>
	</div>
</body>
</html>