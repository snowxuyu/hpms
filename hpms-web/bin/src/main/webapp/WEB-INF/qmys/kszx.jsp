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
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script>
$(function(){
	//初始化科室datagrid
	$("#dg").datagrid({
		url:'qmys/cx/zx/ks',
		queryParams: {
			nd:<%=nd%>,
			yd:<%=yd%>,
			ksnm:<%=ksnm==""?null:ksnm %>,
		},
		rownumbers:true,
        pagination:true,
        fit:true,
        fitColumns:true,
        singleSelect:true,
        onLoadSuccess: function() {
        	var myArr = ["zx1","zx2","zx3","zx4","zx5","zx6","zx7","zx8","zx9","zx10","zx11","zx12","zx13","zx14"];
        	var rows = $("#dg").datagrid('getRows');
            var ch = false;
            for (var i=0;i<myArr.length;i++) {
            	for (var j=0;j < rows.length;j++) {
            		if (row[j][myArr[i]] != "" && row[j][myArr[i]] != "0" && row[j][myArr[i]] != "0.0") {
            			break;
            		}
            	}
            	$("#dg").datagrid('hideColumn',myArr[i]);
            }
		}
	});
				
});
</script>
</head>
<body class="easyui-layout">
	<div id="ks" title="<%=ksmc%><%=nd%>年<%=yd%>月科室专项奖惩" data-options="region:'center'">
		<table id="dg">
			<thead>
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
			</thead>
		</table>
	</div>
</body>
</html>