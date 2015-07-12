<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*,com.hpms.jjfp.entity.*,com.hpms.jjfp.vo.*,com.hpms.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style>
	#rlcbysTb {
		width:95%;
		min-width:400px;
		margin: 0 20px 20px;
		font-size: 12px;
	}
	#rlcbysTb, #rlcbysTb tr td, #rlcbysTb tr th {
		border: 1px dotted #ccc;
		border-collapse: collapse;
	}
	#rlcbysTb {
		border: 1px solid #ccc;
	}
	#rlcbysTb tr th {
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
		background-color: #efefef;
		background: -webkit-linear-gradient(top,#f9f9f9 0,#efefef 100%);
		background: -moz-linear-gradient(top,#f9f9f9 0,#efefef 100%);
		background: -o-linear-gradient(top,#f9f9f9 0,#efefef 100%);
		background: linear-gradient(to bottom,#f9f9f9 0,#efefef 100%);
		background-repeat: repeat-x;
		filter: progid:dximagetransform.microsoft.gradient(startcolorstr=#f9f9f9,endcolorstr=#efefef,gradienttype=0);
		padding: 10px 15px;
	}
	#rlcbysTb tr td {
		padding: 5px 10px;
		min-width: 60px;
		white-space: nowrap;
	}
	.xjTitle {
		font-weight: bold;
		background: #eaf2ff;
	}
	.innerTitle {
		color: #bbb;
	}
	.ndys {
		color: red;
		font-size: 14px;
	}
</style>
<script>
	$(function() {
		
	})
</script>
<% int startC = 0; %>
<% int allC = 0; %>
<% String fjmc = ""; %>
<% List<HPYS_YSZX> ndysList = (List<HPYS_YSZX>) request.getAttribute("ndysList"); %>
<% List<Integer> listC = (List<Integer>) request.getAttribute("listCount"); %>
<% List<RlcbzxVO> rlcbzxList = (List<RlcbzxVO>) request.getAttribute("rlcbzxList"); %>
<% List<Xj> ysxjList = (List<Xj>) request.getAttribute("ysxjList"); %>
<% List<Xj> zxxjList = (List<Xj>) request.getAttribute("zxxjList"); %>

<div style="width: 100%; text-align: center; margin: 20px 0;">
	<h2><%=session.getAttribute("nd") %>人力总成本预算执行表</h2>
</div>
<div style="width: 95%; margin: 0 auto 5px;">
	<div style="float: left;">
		预算金额：<span class="ndys"><%=UtilStr.formatDouble((Double)ndysList.get(0).getYszje()) %></span> 万，
		已执行：<span class="ndys"><%=UtilStr.formatDouble((Double)ndysList.get(0).getSjzje()==null?0:ndysList.get(0).getSjzje()) %></span> 万，
		执行率：<span class="ndys"><fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=ndysList.get(0).getZxl() %>"></fmt:formatNumber></span>
	</div>
	<div style="float: right">
		单位：万
	</div>
	<div style="clear: both;"></div>
</div>
<form id="ysForm" method="post">
<table id="rlcbysTb">
	<thead>
		<tr>
			<th colspan="2" rowspan="2" style="min-width: 150px">预算项目</th>
			<th colspan="2">一月</th>
			<th colspan="2">二月</th>
			<th colspan="2">三月</th>
			<th colspan="2">四月</th>
			<th colspan="2">五月</th>
			<th colspan="2">六月</th>
			<th colspan="2">七月</th>
			<th colspan="2">八月</th>
			<th colspan="2">九月</th>
			<th colspan="2">十月</th>
			<th colspan="2">十一月</th>
			<th colspan="2">十二月</th>
		</tr>
		<tr>
			<% for (int i = 0; i < 12; i++) { %>
			<th>执行值/预算值</th><th>执行率</th>
			<% } %>
		</tr>
	</thead>
	<tbody>
		<% for (int i = 0; i < rlcbzxList.size(); i++) { %>
		
			<tr>
			
			<% if (i == 0) { %>
				<td rowspan="<%=listC.get(startC)+1 %>" style="background: #fafafa;"><%=rlcbzxList.get(i).getFjmc() %></td>
			<% 
				allC += listC.get(startC);
			} %>
			
			<% if (allC == i) { 
				startC++;
				allC += listC.get(startC);
			%>
			<td rowspan="<%=listC.get(startC)+1 %>" style="background: #fafafa;"><%=rlcbzxList.get(i).getFjmc() %></td>
			<% } %>
			
			<td style="display: none;"><input class="s_xmbm" type="hidden" name="rlcbzxList[<%=i %>].xmbm" value="<%=rlcbzxList.get(i).getXmbm() %>"></td>
			<td><%=rlcbzxList.get(i).getXmmc() %></td>
			
			<% for (int k=0;k<12;k++) {%>
				<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
					<td colspan="6" style="text-align: center;">
						<span class="innerTitle">执行值/预算值：</span>
						<% if (rlcbzxList.get(i).getzList().get(k+2)==null) { %>
							<%=rlcbzxList.get(i).getyList().get(k+2)==null?"":rlcbzxList.get(i).getyList().get(k+2) %>
						<% } else { %>
							<span class="red"><%=rlcbzxList.get(i).getzList().get(k+2)==null?"":rlcbzxList.get(i).getzList().get(k+2) %></span> / <%=rlcbzxList.get(i).getyList().get(k+2)==null?"":rlcbzxList.get(i).getyList().get(k+2) %>
						<% } %>
						&emsp;&emsp;
						<span class="innerTitle">执行率：</span>
							<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getzxList().get(k+2)==null?0:rlcbzxList.get(i).getzxList().get(k+2)/100 %>" />
					</td>
					<% k+=2; %>
				<% } else { %>
					<td>
					<% if (rlcbzxList.get(i).getzList().get(k)==null) { %>
						<%=rlcbzxList.get(i).getyList().get(k)==null?"":rlcbzxList.get(i).getyList().get(k) %>
					<%} else { %>
						<span class="red"><%=rlcbzxList.get(i).getzList().get(k)==null?"":rlcbzxList.get(i).getzList().get(k) %></span> / <%=rlcbzxList.get(i).getyList().get(k)==null?"":rlcbzxList.get(i).getyList().get(k) %>
					<% } %>
					</td>
					<td>
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getzxList().get(k)==null?0:rlcbzxList.get(i).getzxList().get(k)/100 %>" />
					</td>
				<% } %>
			<% } %>
			
			<% if (allC == i + 1) { %>
			</tr><tr class="xjTitle">
			<td >小计</td>
			
			<%
				Double ys1 = ysxjList.get(startC).getJ1();
				Double zx1 = zxxjList.get(startC).getJ1();
				Double ys2 = ysxjList.get(startC).getJ2();
				Double zx2 = zxxjList.get(startC).getJ2();
				Double ys3 = ysxjList.get(startC).getJ3();
				Double zx3 = zxxjList.get(startC).getJ3();
				Double ys4 = ysxjList.get(startC).getJ4();
				Double zx4 = zxxjList.get(startC).getJ4();
				
				Double[] yss = {ys1,ys2,ys3,ys4};
				Double[] zxs = {zx1,zx2,zx3,zx4};
			%>
			<% for (int k=0;k<yss.length;k++) {%>
				<td colspan="6">
					执行值/预算值: <span class="red"><%=UtilStr.formatDouble(zxs[k]) %></span> / <%=UtilStr.formatDouble(yss[k]) %>
					&emsp;&emsp;&emsp;&emsp;执行率：
					<% if (yss[k] == 0.0) { %>
						0%
					<% } else { %>
					<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=zxs[k]/yss[k] %>" />
					<% } %>
				</td>
			<% } %>
			
			<% } %>
			
			</tr>
		
		<% } %>
	</tbody>

</table>
</form>