<%@page import="com.hpms.util.UtilStr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*,com.hpms.jjfp.entity.*,com.hpms.jjfp.vo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% int startC = 0; %>
<% int allC = 0; %>
<% String fjmc = ""; %>
<% List<HPYS_YSZX> ndysList = (List<HPYS_YSZX>) request.getAttribute("ndysList"); %>
<% List<Integer> listC = (List<Integer>) request.getAttribute("listCount"); %>
<% List<RlcbysVO> rlcbysList = (List<RlcbysVO>) request.getAttribute("rlcbysList"); %>
<% List<RlcbzxVO> rlcbzxList = (List<RlcbzxVO>) request.getAttribute("rlcbzxList"); %>
<% List<Xj> xjList = (List<Xj>) request.getAttribute("xjList"); %>

<% Object nd = session.getAttribute("nd"); %>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<head>
	<title><%=session.getAttribute("nd") %>人力总成本预算表</title>
</head>
<style>
	#rlcbysTb {
		width:95%;
		min-width:1000px;
		margin: 0 auto 20px;
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
	}
	.xjTitle {
		font-weight: bold;
		background: #eaf2ff;
	}
	.ndys {
		color: red;
		font-size: 14px;
	}
	.detail {
		cursor: pointer;
	}
</style>
<script>
	$(function() {
		$(".detail").click(function() {
			window.location.href="<%=basePath %>qmys/cx/ny?detail=" + $(this).attr("data-detail");
		});
		$("#downloadBtn").click(function() {
			var url="<%=basePath %>jjfp/rlcbys/query/ck/<%=nd %>/exportExcel/1";
			window.open(url,'ExcelWin','z-look=1');
			return false;
		});
	})
</script>


<div style="width: 100%; text-align: center; margin: 20px 0;">
	<h2><%=session.getAttribute("nd") %>人力总成本预算表</h2>
</div>
<div style="width: 95%; margin: 0 auto 5px;">
	<div style="float: left;">
		预算金额：<span class="ndys"><%=UtilStr.formatDouble((Double)ndysList.get(0).getYszje()) %></span> 万，
		已执行：<span class="ndys"><%=UtilStr.formatDouble((Double)ndysList.get(0).getSjzje()==null?0:ndysList.get(0).getSjzje()) %></span> 万，
		执行率：<span class="ndys"><fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=ndysList.get(0).getZxl() %>"></fmt:formatNumber></span>
	</div>
	<div style="float: right">
		<button id="downloadBtn">导出Excel</button> 单位：万
	</div>
	<div style="clear: both;"></div>
</div>
<table id="rlcbysTb">
	<thead>
		<tr>
			<th colspan="2">预算项目</th>
			<th>一月</th>
			<th>二月</th>
			<th>三月</th>
			<th>四月</th>
			<th>五月</th>
			<th>六月</th>
			<th>七月</th>
			<th>八月</th>
			<th>九月</th>
			<th>十月</th>
			<th>十一月</th>
			<th>十二月</th>
		</tr>
	</thead>
	<tbody>
		<% for (int i = 0; i < rlcbysList.size(); i++) { %>
		
			<tr>
			
			<% if (i == 0) { %>
				<td rowspan="<%=listC.get(startC)+1 %>"><%=rlcbysList.get(i).getFjmc() %></td>
			<% 
				allC += listC.get(startC);
			} %>
			
			<% if (allC == i) { 
				startC++;
				allC += listC.get(startC);
			%>
			<td rowspan="<%=listC.get(startC)+1 %>"><%=rlcbysList.get(i).getFjmc() %></td>
			<% } %>
			
			<td style="display: none;"><input type="hidden" name="rlcbysList[<%=i %>].xmbm" value="<%=rlcbysList.get(i).getXmbm() %>"></td>
			<td><%=rlcbysList.get(i).getXmmc() %></td>
		
		<%-- 1-3月 --%>	
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ3() != null) { %>red<% } %> axj_1 axj_1_<%=i %>" colspan="3">
					<%=rlcbysList.get(i).getY3()==null?"":rlcbysList.get(i).getY3() %>
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ1() != null) { %>red<% } %> xj_1 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,1,<%=rlcbysList.get(i).getY1() %>">
				<%=rlcbysList.get(i).getY1()==null?"":rlcbysList.get(i).getY1() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ2() != null) { %>red<% } %> xj_1 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,2,<%=rlcbysList.get(i).getY2() %>">
				<%=rlcbysList.get(i).getY2()==null?"":rlcbysList.get(i).getY2() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ3() != null) { %>red<% } %> xj_1 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>"  data-detail="<%=nd %>,3,<%=rlcbysList.get(i).getY3() %>">
				<%=rlcbysList.get(i).getY3()==null?"":rlcbysList.get(i).getY3() %>
			</td>
			
			<% } %>
			
		<%-- 4-6月 --%>	
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ6() != null) { %>red<% } %> axj_2 axj_2_<%=i %>" colspan="3">
					<%=rlcbysList.get(i).getY6()==null?"":rlcbysList.get(i).getY6() %>
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ4() != null) { %>red<% } %> xj_2 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,4,<%=rlcbysList.get(i).getY4() %>">
				<%=rlcbysList.get(i).getY4()==null?"":rlcbysList.get(i).getY4() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ5() != null) { %>red<% } %> xj_2 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,5,<%=rlcbysList.get(i).getY5() %>">
				<%=rlcbysList.get(i).getY5()==null?"":rlcbysList.get(i).getY5() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ6() != null) { %>red<% } %> xj_2 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,6,<%=rlcbysList.get(i).getY6() %>">
				<%=rlcbysList.get(i).getY6()==null?"":rlcbysList.get(i).getY6() %>
			</td>
			
			<% } %>
			
		<%-- 7-9月 --%>	
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ9() != null) { %>red<% } %> axj_3 axj_3_<%=i %>" colspan="3">
					<%=rlcbysList.get(i).getY9()==null?"":rlcbysList.get(i).getY9() %>
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ7() != null) { %>red<% } %> xj_3 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,7,<%=rlcbysList.get(i).getY7() %>">
				<%=rlcbysList.get(i).getY7()==null?"":rlcbysList.get(i).getY7() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ8() != null) { %>red<% } %> xj_3 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,8,<%=rlcbysList.get(i).getY8() %>">
				<%=rlcbysList.get(i).getY8()==null?"":rlcbysList.get(i).getY8() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ9() != null) { %>red<% } %> xj_3 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,9,<%=rlcbysList.get(i).getY9() %>">
				<%=rlcbysList.get(i).getY9()==null?"":rlcbysList.get(i).getY9() %>
			</td>
			
			<% } %>
		
		<%-- 10-12月 --%>	
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="<% if (rlcbzxList.get(i).getZ12() != null) { %>red<% } %> axj_4 axj_4_<%=i %>" colspan="3">
					<%=rlcbysList.get(i).getY12()==null?"":rlcbysList.get(i).getY12() %>
				</td>
			<% } else { %>
			
			<td class="<% if (rlcbzxList.get(i).getZ10() != null) { %>red<% } %> xj_4 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,10,<%=rlcbysList.get(i).getY1() %>">
				<%=rlcbysList.get(i).getY10()==null?"":rlcbysList.get(i).getY10() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ11() != null) { %>red<% } %> xj_4 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,11,<%=rlcbysList.get(i).getY1() %>">
				<%=rlcbysList.get(i).getY11()==null?"":rlcbysList.get(i).getY11() %>
			</td>
			<td class="<% if (rlcbzxList.get(i).getZ12() != null) { %>red<% } %> xj_4 <% if ("A02".equals(rlcbysList.get(i).getXmbm())) { %>detail<% } %>" data-detail="<%=nd %>,12,<%=rlcbysList.get(i).getY1() %>">
				<%=rlcbysList.get(i).getY12()==null?"":rlcbysList.get(i).getY12() %>
			</td>
			
			<% } %>
			
			<% if (allC == i + 1) { %>
			</tr><tr class="xjTitle">
			<td >小计</td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getY1()+xjList.get(startC).getY2()+xjList.get(startC).getY3()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getY4()+xjList.get(startC).getY5()+xjList.get(startC).getY6()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getY7()+xjList.get(startC).getY8()+xjList.get(startC).getY9()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getY10()+xjList.get(startC).getY11()+xjList.get(startC).getY12()) %></td>
			
			<% } %>
			
			
			</tr>
		
		<% } %>
	</tbody>

</table>