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
			<th colspan="3">一月</th>
			<th colspan="3">二月</th>
			<th colspan="3">三月</th>
			<th colspan="3">四月</th>
			<th colspan="3">五月</th>
			<th colspan="3">六月</th>
			<th colspan="3">七月</th>
			<th colspan="3">八月</th>
			<th colspan="3">九月</th>
			<th colspan="3">十月</th>
			<th colspan="3">十一月</th>
			<th colspan="3">十二月</th>
		</tr>
		<tr>
			<% for (int i = 0; i < 12; i++) { %>
			<th>预算值</th><th>执行值</th><th>执行率</th>
			<% } %>
		</tr>
	</thead>
	<tbody>
		<% for (int i = 0; i < rlcbzxList.size(); i++) { %>
		
			<tr>
			
			<% if (i == 0) { %>
				<td rowspan="<%=listC.get(startC)+1 %>" style="background: #d9f0ff;"><%=rlcbzxList.get(i).getFjmc() %></td>
			<% 
				allC += listC.get(startC);
			} %>
			
			<% if (allC == i) { 
				startC++;
				allC += listC.get(startC);
			%>
			<td rowspan="<%=listC.get(startC)+1 %>" style="background: #d9f0ff;"><%=rlcbzxList.get(i).getFjmc() %></td>
			<% } %>
			
			<td style="display: none;"><input class="s_xmbm" type="hidden" name="rlcbzxList[<%=i %>].xmbm" value="<%=rlcbzxList.get(i).getXmbm() %>"></td>
			<td><%=rlcbzxList.get(i).getXmmc() %></td>
			
			
		<%-- 1-3月 --%>	
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ3()!=null) {%>red<% } %> axj_1" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY3()==null?"":rlcbzxList.get(i).getY3() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><%=rlcbzxList.get(i).getZ3()==null?"":rlcbzxList.get(i).getZ3() %>
					&emsp;
					<span class="innerTitle">执行率：</span>
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx3()==null?0:rlcbzxList.get(i).getZx3()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ1()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getY1()==null?"":rlcbzxList.get(i).getY1() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ1()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getZ1()==null?"":rlcbzxList.get(i).getZ1() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ1()!=null) {%>red<% } %> xj_1">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx1()==null?0:rlcbzxList.get(i).getZx1()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ2()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getY2()==null?"":rlcbzxList.get(i).getY2() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ2()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getZ2()==null?"":rlcbzxList.get(i).getZ2() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ2()!=null) {%>red<% } %> xj_1">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx2()==null?0:rlcbzxList.get(i).getZx2()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ3()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getY3()==null?"":rlcbzxList.get(i).getY3() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ3()!=null) {%>red<% } %> xj_1"><%=rlcbzxList.get(i).getZ3()==null?"":rlcbzxList.get(i).getZ3() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ3()!=null) {%>red<% } %> xj_1">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx3()==null?0:rlcbzxList.get(i).getZx3()/100 %>" />
			</td>
			
			<% } %>
			
		<%-- 4-6月 --%>
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ6()!=null) {%>red<% } %> axj_2" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY6()==null?"":rlcbzxList.get(i).getY6() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><%=rlcbzxList.get(i).getZ6()==null?"":rlcbzxList.get(i).getZ6() %>
					&emsp;
					<span class="innerTitle">执行率：</span>
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx6()==null?0:rlcbzxList.get(i).getZx6()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ4()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getY4()==null?"":rlcbzxList.get(i).getY4() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ4()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getZ4()==null?"":rlcbzxList.get(i).getZ4() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ4()!=null) {%>red<% } %> xj_2">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx4()==null?0:rlcbzxList.get(i).getZx4()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ5()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getY5()==null?"":rlcbzxList.get(i).getY5() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ5()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getZ5()==null?"":rlcbzxList.get(i).getZ5() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ5()!=null) {%>red<% } %> xj_2">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx5()==null?0:rlcbzxList.get(i).getZx5()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ6()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getY6()==null?"":rlcbzxList.get(i).getY6() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ6()!=null) {%>red<% } %> xj_2"><%=rlcbzxList.get(i).getZ6()==null?"":rlcbzxList.get(i).getZ6() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ6()!=null) {%>red<% } %> xj_2">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx6()==null?0:rlcbzxList.get(i).getZx6()/100 %>" />
			</td>
			
			<% } %>
			
		<%-- 7-9月 --%>
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ9()!=null) {%>red<% } %> axj_3" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY9()==null?"":rlcbzxList.get(i).getY9() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><%=rlcbzxList.get(i).getZ9()==null?"":rlcbzxList.get(i).getZ9() %>
					&emsp;
					<span class="innerTitle">执行率：</span>
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx9()==null?0:rlcbzxList.get(i).getZx9()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ7()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getY7()==null?"":rlcbzxList.get(i).getY7() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ7()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getZ7()==null?"":rlcbzxList.get(i).getZ7() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ7()!=null) {%>red<% } %> xj_3">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx7()==null?0:rlcbzxList.get(i).getZx7()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ8()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getY8()==null?"":rlcbzxList.get(i).getY8() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ8()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getZ8()==null?"":rlcbzxList.get(i).getZ8() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ8()!=null) {%>red<% } %> xj_3">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx8()==null?0:rlcbzxList.get(i).getZx8()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ9()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getY9()==null?"":rlcbzxList.get(i).getY9() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ9()!=null) {%>red<% } %> xj_3"><%=rlcbzxList.get(i).getZ9()==null?"":rlcbzxList.get(i).getZ9() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ9()!=null) {%>red<% } %> xj_3">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx9()==null?0:rlcbzxList.get(i).getZx9()/100 %>" />
			</td>
			
			<% } %>
			
		<%-- 10-12月 --%>
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ12()!=null) {%>red<% } %> axj_4" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY12()==null?"":rlcbzxList.get(i).getY12() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><%=rlcbzxList.get(i).getZ12()==null?"":rlcbzxList.get(i).getZ12() %>
					&emsp;
					<span class="innerTitle">执行率：</span>
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx12()==null?0:rlcbzxList.get(i).getZx12()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ10()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getY10()==null?"":rlcbzxList.get(i).getY10() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ10()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getZ10()==null?"":rlcbzxList.get(i).getZ10() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ10()!=null) {%>red<% } %> xj_4">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx10()==null?0:rlcbzxList.get(i).getZx10()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ11()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getY11()==null?"":rlcbzxList.get(i).getY11() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ11()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getZ11()==null?"":rlcbzxList.get(i).getZ11() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ11()!=null) {%>red<% } %> xj_4">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx11()==null?0:rlcbzxList.get(i).getZx11()/100 %>" />
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ12()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getY12()==null?"":rlcbzxList.get(i).getY12() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ12()!=null) {%>red<% } %> xj_4"><%=rlcbzxList.get(i).getZ12()==null?"":rlcbzxList.get(i).getZ12() %></td>
			<td class="<% if (rlcbzxList.get(i).getZ12()!=null) {%>red<% } %> xj_4">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx12()==null?0:rlcbzxList.get(i).getZx12()/100 %>" />
			</td>
			
			<% } %>
			
			
			<% if (allC == i + 1) { %>
			</tr><tr class="xjTitle">
			<td >小计</td>
			<%
				Double ys1 = ysxjList.get(startC).getY1()+ysxjList.get(startC).getY2()+ysxjList.get(startC).getY3();
				Double zx1 = zxxjList.get(startC).getY1()+zxxjList.get(startC).getY2()+zxxjList.get(startC).getY3();
				Double ys2 = ysxjList.get(startC).getY4()+ysxjList.get(startC).getY5()+ysxjList.get(startC).getY6();
				Double zx2 = zxxjList.get(startC).getY4()+zxxjList.get(startC).getY5()+zxxjList.get(startC).getY6();
				Double ys3 = ysxjList.get(startC).getY7()+ysxjList.get(startC).getY8()+ysxjList.get(startC).getY9();
				Double zx3 = zxxjList.get(startC).getY7()+zxxjList.get(startC).getY8()+zxxjList.get(startC).getY9();
				Double ys4 = ysxjList.get(startC).getY10()+ysxjList.get(startC).getY11()+ysxjList.get(startC).getY12();
				Double zx4 = zxxjList.get(startC).getY10()+zxxjList.get(startC).getY11()+zxxjList.get(startC).getY12();
			%>
			
			
			<td colspan="9">
				预算值：<%=UtilStr.formatDouble(ys1) %>&emsp;&emsp;&emsp;&emsp;
				执行值：<%=UtilStr.formatDouble(zx1) %>&emsp;&emsp;&emsp;&emsp;
				执行率：
				<% if (ys1 == 0.0) { %>0%
				<% } else { %>
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=zx1/ys1 %>" />
				<% } %>
			</td>
			<td colspan="9">
				预算值：<%=UtilStr.formatDouble(ys2) %>&emsp;&emsp;&emsp;&emsp;
				执行值：<%=UtilStr.formatDouble(zx2) %>&emsp;&emsp;&emsp;&emsp;
				执行率：
				<% if (ys2 == 0.0) { %>0%
				<% } else { %>
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=zx2/ys2 %>" />
				<% } %>
			</td>
			<td colspan="9">
				预算值：<%=UtilStr.formatDouble(ys3) %>&emsp;&emsp;&emsp;&emsp;
				执行值：<%=UtilStr.formatDouble(zx3) %>&emsp;&emsp;&emsp;&emsp;
				执行率：
				<% if (ys3 == 0.0) { %>0%
				<% } else { %>
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=zx3/ys3 %>" />
				<% } %>
			</td>
			<td colspan="9">
				预算值：<%=UtilStr.formatDouble(ys4) %>&emsp;&emsp;&emsp;&emsp;
				执行值：<%=UtilStr.formatDouble(zx4) %>&emsp;&emsp;&emsp;&emsp;
				执行率：
				<% if (ys4 == 0.0) { %>0%
				<% } else { %>
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=zx4/ys4 %>" />
				<% } %>
			</td>
			
			<% } 
				
			%>
			
			
			</tr>
		
		<% } %>
	</tbody>

</table>
</form>