<%@page import="com.hpms.util.UtilStr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*,com.hpms.jjfp.vo.*,com.hpms.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
	#rlcbysTb tr td input {
		width:60px;
		min-width: 40px;
		border: none;
	}
	.xjTitle {
		font-weight: bold;
		background: #eaf2ff;
	}
	.innerTitle {
		color: #bbb;
	}
	.zxIt {
		width: 16px;
		height: 16px;
		background: rgb(51, 153, 255);
		background: rgba(51, 153, 255, 0.3);
		border-radius: 100%;
		display: inline-block;
		vertical-align: middle;
		cursor: pointer;
		position: relative;
	}
	.zx_a {
	  content: "";
	  display:block;
	  width: 6px;
	  height: 6px;
	  position:absolute;
	  border-radius:100%;
	  top:50%;
	  left: 50%;
	  margin: -3px 0 0 -3px;
	  padding: 5px;
	  z-index:2;
	  background: rgba(255,255,255,1);
	}
	.zx_b {
	  content: "";
	  display:block;
	  width: 20px;
	  height: 20px;
	  position:absolute;
	  border-radius:100%;
	  top:50%;
	  left: 50%;
	  margin: -12px 0 0 -12px;
	  z-index:2;
	  border: 2px solid rgb(51, 153, 255);
	}
	td:hover .zxIt {
		background: rgb(51, 153, 255);
	}
	.xj, .axj {
		color:blue;
	}
	/* .axj {
		cursor: pointer;
	} */
</style>
<script>
	$(function() {
		$("#rlcbysTb td input").each(function() {
			$(this).attr("maxlength",15);
			$(this).on("keyup",function() {
				var _val = $(this).val();
				var _c = _val.substring(0,1);
				$(this).val($(this).val().replace(/[^\d.]/g,""));  //清除“数字”和“.”以外的字符
				$(this).val($(this).val().replace(/^\./g,""));  //验证第一个字符是数字而不是.
				$(this).val($(this).val().replace(/\.{2,}/g,".")); //只保留第一个. 清除多余的.
				$(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	            if (_c == '-') {
	            	if (_val.substring(1,2) == '0') {
	            		_c = _val.substring(2,3);
		            	if (_val.length > 2 && _c != ".") {
		            		$(this).val($(this).val().substring(0,1)+$(this).val().substring(2));
		            	}
	            	}
	            	$(this).val("-"+$(this).val());
	            } else if (_c == '0') {
	            	_c = _val.substring(1,2);
	            	if (_val.length > 1 && _c != ".") {
	            		$(this).val($(this).val().substring(1));
	            	}
	            }
	            _val = $(this).val();
	            var _dot = _val.indexOf(".");
	            if (_dot > 0) {
	            	$(this).val(_val.substring(0,_dot+3));
	            }
				$(this).width($(this).val().length*10);
				$(this).addClass("red");
			}).css("ime-mode", "disabled");
		});
		$("#rlcbysTb td input").focus(function() {
			$(this).parent().css("border","1px solid red");
		}).blur(function() {
			$(this).parent().css("border","1px dotted #ccc");
		});
		
		$(".zxIt").on("click",function() {
			$this = $(this);
			var $a=$('<div></div>');
			var $b=$('<div></div>');
			//$($a).addClass("zx_a");
			$($b).addClass("zx_b");
			$(this).empty().append($a).append($b);
			if ($(this).html() == "") {
				
			}
			$(this).css("background","rgb(51, 153, 255)");
			
			var xmbm = $(this).parent().parent().find(".s_xmbm").val();
			var yd = $(this).next(".ycy").val();
			var zxz;
			if ($(this).parent().hasClass("axj")) {
				zxz = $(this).parent().find(".zxz").val();
			} else {
				zxz = $(this).parent().prev().find(".zxz").val();
			}
			
			$.post("jjfp/rlcbzx/update", {
				"xmbm":xmbm,
				"yd":yd,
				"zxz":zxz
				}, function(res) {
					$.messager.progress("close");
	    			if (res == "success"){
	    				$($this).empty();
	    				$($this).css("background","rgba(51, 153, 255, 0.3)");
					} else {
						$($this).css("background","red");
						$.messager.alert("警告","人力成本预算执行失败！");
					}
    		});
		});
	})
</script>
<% int startC = 0; %>
<% int allC = 0; %>
<% String fjmc = ""; %>
<% List<Integer> listC = (List<Integer>) request.getAttribute("listCount"); %>
<% List<RlcbzxVO> rlcbzxList = (List<RlcbzxVO>) request.getAttribute("rlcbzxList"); %>
<% List<Xj> ysxjList = (List<Xj>) request.getAttribute("ysxjList"); %>
<% List<Xj> zxxjList = (List<Xj>) request.getAttribute("zxxjList"); %>

<div style="width: 100%; text-align: center; margin: 20px 0;">
	<h2><%=session.getAttribute("nd") %>人力总成本预算执行表</h2>
</div>
<div style="width: 95%; margin: 0 auto 5px;">
	<div style="float: left;">
		执行金额：<span class="success" style="font-size: 14px"><%=UtilStr.formatDouble((Double)request.getAttribute("zxje")) %></span> 万
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
			
			
		<!-- 1-3月 -->	
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="axj axj_1 axj_1_<%=i %>" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY3()==null?"":rlcbzxList.get(i).getY3() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ3()==null?"":rlcbzxList.get(i).getZ3() %>">
					&emsp;
					<span class="innerTitle">执行率：</span>
						<% if(rlcbzxList.get(i).getY3() != null && rlcbzxList.get(i).getY3() != 0.0) { %><div class="zxIt"></div><% } %>&emsp;<input class="ycy" type="hidden" value="3">
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx3()==null?0:rlcbzxList.get(i).getZx3()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="xj xj_1 xj_1_<%=i %>"><%=rlcbzxList.get(i).getY1()==null?"":rlcbzxList.get(i).getY1() %></td>
			<td class="xj xj_1 xj_1_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ1()==null?"":rlcbzxList.get(i).getZ1() %>"></td>
			<td class="xj xj_1 xj_1_<%=i %>">
				<% if(rlcbzxList.get(i).getY1() != null && rlcbzxList.get(i).getY1() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="1">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx1()==null?0:rlcbzxList.get(i).getZx1()/100 %>" />
			</td>
			<td class="xj xj_1 xj_1_<%=i %>"><%=rlcbzxList.get(i).getY2()==null?"":rlcbzxList.get(i).getY2() %></td>
			<td class="xj xj_1 xj_1_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ2()==null?"":rlcbzxList.get(i).getZ2() %>"></td>
			<td class="xj xj_1 xj_1_<%=i %>">
				<% if(rlcbzxList.get(i).getY2() != null && rlcbzxList.get(i).getY2() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="2">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx2()==null?0:rlcbzxList.get(i).getZx2()/100 %>" />
			</td>
			<td class="xj xj_1 xj_1_<%=i %>"><%=rlcbzxList.get(i).getY3()==null?"":rlcbzxList.get(i).getY3() %></td>
			<td class="xj xj_1 xj_1_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ3()==null?"":rlcbzxList.get(i).getZ3() %>"></td>
			<td class="xj xj_1 xj_1_<%=i %>">
				<% if(rlcbzxList.get(i).getY3() != null && rlcbzxList.get(i).getY3() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="3">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx3()==null?0:rlcbzxList.get(i).getZx3()/100 %>" />
			</td>
			
			<% } %>
			
		<!-- 4-6月 -->
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="axj axj_2 axj_2_<%=i %>" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY6()==null?"":rlcbzxList.get(i).getY6() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ6()==null?"":rlcbzxList.get(i).getZ6() %>">
					&emsp;
					<span class="innerTitle">执行率：</span>
						<% if(rlcbzxList.get(i).getY6() != null && rlcbzxList.get(i).getY6() != 0.0) { %><div class="zxIt"></div><% } %>&emsp;<input class="ycy" type="hidden" value="6">
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx6()==null?0:rlcbzxList.get(i).getZx6()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="xj xj_2 xj_2_<%=i %>"><%=rlcbzxList.get(i).getY4()==null?"":rlcbzxList.get(i).getY4() %></td>
			<td class="xj xj_2 xj_2_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ4()==null?"":rlcbzxList.get(i).getZ4() %>"></td>
			<td class="xj xj_2 xj_2_<%=i %>">
				<% if(rlcbzxList.get(i).getY4() != null && rlcbzxList.get(i).getY4() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="4">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx4()==null?0:rlcbzxList.get(i).getZx4()/100 %>" />
			</td>
			<td class="xj xj_2 xj_2_<%=i %>"><%=rlcbzxList.get(i).getY5()==null?"":rlcbzxList.get(i).getY5() %></td>
			<td class="xj xj_2 xj_2_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ5()==null?"":rlcbzxList.get(i).getZ5() %>"></td>
			<td class="xj xj_2 xj_2_<%=i %>">
				<% if(rlcbzxList.get(i).getY5() != null && rlcbzxList.get(i).getY5() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="5">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx5()==null?0:rlcbzxList.get(i).getZx5()/100 %>" />
			</td>
			<td class="xj xj_2 xj_2_<%=i %>"><%=rlcbzxList.get(i).getY6()==null?"":rlcbzxList.get(i).getY6() %></td>
			<td class="xj xj_2 xj_2_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ6()==null?"":rlcbzxList.get(i).getZ6() %>"></td>
			<td class="xj xj_2 xj_2_<%=i %>">
				<% if(rlcbzxList.get(i).getY6() != null && rlcbzxList.get(i).getY6() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="6">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx6()==null?0:rlcbzxList.get(i).getZx6()/100 %>" />
			</td>
			
			<% } %>
			
		<!-- 7-9月 -->
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="axj axj_3 axj_3_<%=i %>" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY9()==null?"":rlcbzxList.get(i).getY9() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ9()==null?"":rlcbzxList.get(i).getZ9() %>">
					&emsp;
					<span class="innerTitle">执行率：</span>
						<% if(rlcbzxList.get(i).getY9() != null && rlcbzxList.get(i).getY9() != 0.0) { %><div class="zxIt"></div><% } %>&emsp;<input class="ycy" type="hidden" value="9">
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx9()==null?0:rlcbzxList.get(i).getZx9()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="xj xj_3 xj_3_<%=i %>"><%=rlcbzxList.get(i).getY7()==null?"":rlcbzxList.get(i).getY7() %></td>
			<td class="xj xj_3 xj_3_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ7()==null?"":rlcbzxList.get(i).getZ7() %>"></td>
			<td class="xj xj_3 xj_3_<%=i %>">
				<% if(rlcbzxList.get(i).getY7() != null && rlcbzxList.get(i).getY7() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="7">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx7()==null?0:rlcbzxList.get(i).getZx7()/100 %>" />
			</td>
			<td class="xj xj_3 xj_3_<%=i %>"><%=rlcbzxList.get(i).getY8()==null?"":rlcbzxList.get(i).getY8() %></td>
			<td class="xj xj_3 xj_3_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ8()==null?"":rlcbzxList.get(i).getZ8() %>"></td>
			<td class="xj xj_3 xj_3_<%=i %>">
				<% if(rlcbzxList.get(i).getY8() != null && rlcbzxList.get(i).getY8() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="8">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx8()==null?0:rlcbzxList.get(i).getZx8()/100 %>" />
			</td>
			<td class="xj xj_3 xj_3_<%=i %>"><%=rlcbzxList.get(i).getY9()==null?"":rlcbzxList.get(i).getY9() %></td>
			<td class="xj xj_3 xj_3_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ9()==null?"":rlcbzxList.get(i).getZ9() %>"></td>
			<td class="xj xj_3 xj_3_<%=i %>">
				<% if(rlcbzxList.get(i).getY9() != null && rlcbzxList.get(i).getY9() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="9">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx9()==null?0:rlcbzxList.get(i).getZx9()/100 %>" />
			</td>
			
			<% } %>
			
		<!-- 10-12月 -->
			<% if ("2".equals(rlcbzxList.get(i).getSjzt())) {%>
				<td class="axj axj_4 axj_4_<%=i %>" colspan="9" style="text-align: center;">
					<span class="innerTitle">预算值：</span><%=rlcbzxList.get(i).getY12()==null?"":rlcbzxList.get(i).getY12() %>
					&emsp;&emsp;
					<span class="innerTitle">执行值：</span><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ12()==null?"":rlcbzxList.get(i).getZ12() %>">
					&emsp;
					<span class="innerTitle">执行率：</span>
						<% if(rlcbzxList.get(i).getY12() != null && rlcbzxList.get(i).getY12() != 0.0) { %><div class="zxIt"></div><% } %>&emsp;<input class="ycy" type="hidden" value="12">
						<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx12()==null?0:rlcbzxList.get(i).getZx12()/100 %>" />
				</td>
			<% } else { %>
			
			<td class="xj xj_4 xj_4_<%=i %>"><%=rlcbzxList.get(i).getY10()==null?"":rlcbzxList.get(i).getY10() %></td>
			<td class="xj xj_4 xj_4_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ10()==null?"":rlcbzxList.get(i).getZ10() %>"></td>
			<td class="xj xj_4 xj_4_<%=i %>">
				<% if(rlcbzxList.get(i).getY10() != null && rlcbzxList.get(i).getY10() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="10">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx10()==null?0:rlcbzxList.get(i).getZx10()/100 %>" />
			</td>
			<td class="xj xj_4 xj_4_<%=i %>"><%=rlcbzxList.get(i).getY11()==null?"":rlcbzxList.get(i).getY11() %></td>
			<td class="xj xj_4 xj_4_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ11()==null?"":rlcbzxList.get(i).getZ11() %>"></td>
			<td class="xj xj_4 xj_4_<%=i %>">
				<% if(rlcbzxList.get(i).getY11() != null && rlcbzxList.get(i).getY11() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="11">
				<fmt:formatNumber type="percent" maxFractionDigits="2" value="<%=rlcbzxList.get(i).getZx11()==null?0:rlcbzxList.get(i).getZx11()/100 %>" />
			</td>
			<td class="xj xj_4 xj_4_<%=i %>"><%=rlcbzxList.get(i).getY12()==null?"":rlcbzxList.get(i).getY12() %></td>
			<td class="xj xj_4 xj_4_<%=i %>"><input class="zxz" type="text" value="<%=rlcbzxList.get(i).getZ12()==null?"":rlcbzxList.get(i).getZ12() %>"></td>
			<td class="xj xj_4 xj_4_<%=i %>">
				<% if(rlcbzxList.get(i).getY12() != null && rlcbzxList.get(i).getY12() != 0.0) { %><div class="zxIt"></div><% } %>&nbsp;<input class="ycy" type="hidden" value="12">
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