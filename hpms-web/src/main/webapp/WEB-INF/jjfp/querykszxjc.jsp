<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>科室专项奖惩维护</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
			//初始化datagrid
			$("#dg").datagrid({
				url:'jjfp/querykszxjc/find',
				queryParams: {
				},
				rownumbers:true,
		        pagination:true,
		        fit:true,
		        singleSelect:true
			});
			
			//设置额外查询参数
			var searchParams;  
			
			//模糊查询的参数
			var mh_searchParam;
			
			
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
				$("#dg").datagrid("reload");
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
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div title="科室专项奖惩维护查询" data-options="region:'center'">
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