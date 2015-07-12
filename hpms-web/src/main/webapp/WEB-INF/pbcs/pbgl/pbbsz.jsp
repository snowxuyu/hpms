<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,com.pbcs.pbgl.vo.PbmxVo,com.hpms.yljx.entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% String[][] dateAndWeeks = (String[][])request.getAttribute("dateAndWeeks"); %>
<% List<PbmxVo> listMX = (List<PbmxVo>)request.getAttribute("listMX"); %>
<% List<HPCS_BBFLK> listBBFL = (List<HPCS_BBFLK>)request.getAttribute("listBBFL"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
.detail {
		color: blue;
		cursor: pointer;
	}
	.bbfl-ul {
		padding: 0;
		margin: 0;
	}
	.bbfl-ul li {
		padding: 0;
		margin: 0;
		list-style: none;
		float: left;
	}
	.bbfl-ul .block {
		width: 85px;
		height: 30px;
	}
	.choose {
		padding: 20px;
		overflow: hidden;
	}
	.choose fieldset {
		margin-bottom: 20px;
		padding: 10px 10px;
		border: 1px solid rgb(200,200,200);
		border: 1px solid rgba(0, 0, 0, 0.2);
	}
</style>
<script>
    $(function(){
    	var _date = new Date();
       	var _year = _date.getFullYear();
       	var _month = _date.getMonth() + 1;
       	
       	var to_month = _year + "-" + ((_month < 10) ? ("0" + _month) : _month);
       	$("#ny").val(to_month);
        $("#data").datagrid({
            rownumbers:true,
            fit:true,
            toolbar:"#tb",
            singleSelect:true,
            /* columns:[[
				{field:'rq',title:'项目类别代码',width:250},
				
			    {field:'xmlbmc',title:'项目类别名称',width:250},
			]], */
            onDblClickCell: function(index,field,value){
        		if (field != "rq") {
        			$("#form").form("reset");
                	$("#win").window("open");
        		}
        	},
			onLoadSuccess: function() {
				
			}
        });
       
    });
</script>
</head>
<body class="easyui-layout">
	<div title="排班表设置" data-options="region:'north'" style="height: 76px;">
		<div style="padding: 10px;">
			日期：<input type="text" name="ny" id="ny" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" 
							class="wdate" style="width: 180px;"/>
			&emsp;&emsp;
			科室名称:<input id="ks" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'yljx/ks/query'"/>
		</div>
	</div>
	<div title="" data-options="region:'center'">
		<table id="data">
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'rq',width:60">日期</th>
				</tr>
				<tr>
					<th data-options="field:'rq',width:60">星期</th>
				</tr>
			</thead>
			<thead>
				<tr>
					<% for(int i = 0;i < dateAndWeeks.length;i++) { %>
					<th data-options="field:'r<%=dateAndWeeks[i][0] %>',width:50"><%=dateAndWeeks[i][0] %></th>
					<% } %>
				</tr>
				<tr>
					<% for(int i = 0;i < dateAndWeeks.length;i++) { %>
					<th data-options="field:'x<%=dateAndWeeks[i][0] %>',width:50"><%=dateAndWeeks[i][1] %></th>
					<% } %>
				</tr>
			</thead>
			<tbody>
				<% String ygbh = null; %>
				<tr>
				
				<% for (PbmxVo bean : listMX) { %>
					<% if (ygbh == null) {%>
						<td><%=bean.getYgxm() %></td>
						<td><%=bean.getBbmc()==null?"":bean.getBbmc() %></td>
						<% ygbh = bean.getYgbh(); %>
					<% } else if (!bean.getYgbh().equals(ygbh)) { %>
						</tr>
						<tr>
							<td><%=bean.getYgxm() %></td>
							<td><%=bean.getBbmc()==null?"":bean.getBbmc() %></td>
							<% ygbh = bean.getYgbh(); %>
					<% } else { %>
						<td><%=bean.getBbmc()==null?"":bean.getBbmc() %>测试<br/>ceshi</td>
					<% } %>
				<% } %>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="saveBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a>
		<a id="upBtn" class="easyui-linkbutton" iconCls="icon-arrow-up">递交</a>
		<a id="downloadBtn" class="easyui-linkbutton" iconCls="icon-download">导出</a>
	</div>
	<div id="win" class="easyui-window win choose" title="选择班别">
		<form id="form" method="post" style="width: 400px;">
			<fieldset>
				<legend>必选内容</legend>
				<div>
					<ul class="bbfl-ul">
					<% for (HPCS_BBFLK bean : listBBFL) { %>
						<% if ("0".equals(String.valueOf(Integer.parseInt(bean.getBbcs())))) { %>
							<li>
								<label>
									<div class="block">
									<input type="radio" name="must" value="<%=bean.getBbbm()%>">
									<%=bean.getBbjx() %>
									</div>
								</label>
							</li>
						<% } %>
					<% } %>
					</ul>
				</div>
			</fieldset>
			<fieldset>
				<legend>可选内容</legend>
				<div>
					<ul class="bbfl-ul">
					<% for (HPCS_BBFLK bean : listBBFL) { %>
						<% if ("1".equals(String.valueOf(Integer.parseInt(bean.getBbcs())))) { %>
							<li>
								<input type="radio" name="may" value="<%=bean.getBbbm()%>">
								<%=bean.getBbjx() %>
							</li>
						<% } %>
					<% } %>
					</ul>
				</div>
			</fieldset>
		</form>
	</div>  
</body>
</html>