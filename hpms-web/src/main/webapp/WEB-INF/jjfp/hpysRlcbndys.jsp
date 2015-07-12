<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人力成本预算设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	var nd =null;
	function querySum(){
		var nd1 = document.getElementById("nd").value;
		$('#dg').datagrid({
			queryParams :{nd:nd1},
			url:'<%=basePath %>jjfp/HpysRlcbNdYsAction/queryNdYs',
			method:'post',
			onClickRow:function(index,row){
				 nd = row.nd;
				 creatIframe();
			}
		});
	}
	
	function creatIframe(){
		 document.getElementById("iframe").innerHTML = 
			 '<iframe name="myiframe" width="99%" height="99%" marginwidth="0" marginheight="0" scrolling="yes" '+
			'src="<%=basePath %>jjfp/HpysRlcbysAction/queryYear?year='+nd+'"></iframe>';
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',width:300">
		<div class="easyui-panel" data-options="title:'年度预算筛选',region:'north'" style="height:100px;padding-top:20px">
			年度:<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" class="wdate"
										style="width: 180px" />
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true" onclick="querySum()">查询</a>
		</div>
		<div data-options="region:'center'">
			<table id="dg" class="easyui-datagrid" data-options="fitColumns: true ,
	                          singleSelect:true , rownumbers:true,
	                          closable:true">
	               <thead>
	               		<tr>
	               			<th  data-options="field:'nd',width:50">年度</th>
	               			<th  data-options="field:'yszje',width:100">预算总金额(万)</th>
	               			<th  data-options="field:'ztName',width:50">状态</th>
	               		</tr>
	               </thead>
			</table>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="iframe" style="width:100%;height:100%" >
		</div>
	</div>
</body>
</html>