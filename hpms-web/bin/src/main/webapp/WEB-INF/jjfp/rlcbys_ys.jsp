<%@page import="com.hpms.util.UtilStr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*,com.hpms.jjfp.vo.*,com.hpms.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style>
	#rlcbysTb {
		width:95%;
		min-width:400px;
		margin: 0 auto;
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
	#rlcbysTb tr td input {
		width:50px;
		min-width: 40px;
		border: none;
	}
	.xjTitle {
		font-weight: bold;
		background: #eaf2ff;
	}
	/* .axj {
		cursor: pointer;
	} */
</style>
<% if (request.getParameter("refresh")!=null) { %>
<script type="text/javascript">
$(function() {
	$.messager.show({
		title:"操作提示",
		msg:"人力成本预算设置成功！",
		timeout:3000,
		showType:"slide"
	});
})
</script>
<% } %>
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
		/* $(".axj").parent().find(".xj").hide();
		$(".axj_1").dblclick(function() {
			$(this).hide().parent().find(".xj_1").show();
		});
		$(".axj_2").dblclick(function() {
			$(this).hide().parent().find(".xj_2").show();
		});
		$(".axj_3").dblclick(function() {
			$(this).hide().parent().find(".xj_3").show();
		});
		$(".axj_4").dblclick(function() {
			$(this).hide().parent().find(".xj_4").show();
		}); */
		
		$("#saveYSBtn,#saveForBtn").on("click",function() {
			/* $("html").css("overflow","hidden"); */
			$("html").height(0);
			window.scroll(0, 0);
			$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post("jjfp/rlcbys/update", $("#ysForm").serialize(), function(res) {
				$.messager.progress("close");
    			if (res == "success"){
    				window.location.href='<%=basePath %>jjfp/rlcbys/query/nd?nd=<%=session.getAttribute("nd") %>&refresh=1';
						
                 } else {
						$.messager.alert("警告","人力成本预算设置失败！");
                 }
    		});
		});
	})
</script>
<% int startC = 0; %>
<% int allC = 0; %>
<% String fjmc = ""; %>
<% List<Integer> listC = (List<Integer>) request.getAttribute("listCount"); %>
<% List<RlcbysVO> rlcbysList = (List<RlcbysVO>) request.getAttribute("rlcbysList"); %>
<% List<Xj> xjList = (List<Xj>) request.getAttribute("xjList"); %>

<div style="width: 100%; text-align: center; margin: 20px 0;">
	<h2><%=session.getAttribute("nd") %>人力总成本预算表</h2>
</div>
<div style="width: 95%; margin: 0 auto 5px;">
	<div style="float: left;">
		预算金额：<span class="success"><%=UtilStr.formatDouble((Double)request.getAttribute("ysje")) %></span> 万
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
			
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="axj axj_1 axj_1_<%=i %>" colspan="3">
					<input type="text" name="rlcbysList[<%=i %>].y3" value="<%=rlcbysList.get(i).getY3()==null?"":rlcbysList.get(i).getY3() %>" style="min-width: 80px">
				</td>
			<% } else { %>
			
			<td class="xj xj_1 xj_1_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y1" value="<%=rlcbysList.get(i).getY1()==null?"":rlcbysList.get(i).getY1() %>"></td>
			<td class="xj xj_1 xj_1_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y2" value="<%=rlcbysList.get(i).getY2()==null?"":rlcbysList.get(i).getY2() %>"></td>
			<td class="xj xj_1 xj_1_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y3" value="<%=rlcbysList.get(i).getY3()==null?"":rlcbysList.get(i).getY3() %>"></td>
			
			<% } %>
			
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="axj axj_2 axj_2_<%=i %>" colspan="3">
					<input type="text" name="rlcbysList[<%=i %>].y6" value="<%=rlcbysList.get(i).getY6()==null?"":rlcbysList.get(i).getY6() %>" style="min-width: 80px">
				</td>
			<% } else { %>
			
			<td class="xj xj_2 xj_2_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y4" value="<%=rlcbysList.get(i).getY4()==null?"":rlcbysList.get(i).getY4() %>"></td>
			<td class="xj xj_2 xj_2_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y5" value="<%=rlcbysList.get(i).getY5()==null?"":rlcbysList.get(i).getY5() %>"></td>
			<td class="xj xj_2 xj_2_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y6" value="<%=rlcbysList.get(i).getY6()==null?"":rlcbysList.get(i).getY6() %>"></td>
			
			<% } %>
			
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="axj axj_3 axj_3_<%=i %>" colspan="3">
					<input type="text" name="rlcbysList[<%=i %>].y9" value="<%=rlcbysList.get(i).getY9()==null?"":rlcbysList.get(i).getY9() %>" style="min-width: 80px">
				</td>
			<% } else { %>
			
			<td class="xj xj_3 xj_3_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y7" value="<%=rlcbysList.get(i).getY7()==null?"":rlcbysList.get(i).getY7() %>"></td>
			<td class="xj xj_3 xj_3_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y8" value="<%=rlcbysList.get(i).getY8()==null?"":rlcbysList.get(i).getY8() %>"></td>
			<td class="xj xj_3 xj_3_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y9" value="<%=rlcbysList.get(i).getY9()==null?"":rlcbysList.get(i).getY9() %>"></td>
			
			<% } %>
			
			<% if ("2".equals(rlcbysList.get(i).getSjzt())) {%>
				<td class="axj axj_4 axj_4_<%=i %>" colspan="3">
					<input type="text" name="rlcbysList[<%=i %>].y12" value="<%=rlcbysList.get(i).getY12()==null?"":rlcbysList.get(i).getY12() %>" style="min-width: 80px">
				</td>
			<% } else { %>
			
			<td class="xj xj_4 xj_4_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y10" value="<%=rlcbysList.get(i).getY10()==null?"":rlcbysList.get(i).getY10() %>"></td>
			<td class="xj xj_4 xj_4_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y11" value="<%=rlcbysList.get(i).getY11()==null?"":rlcbysList.get(i).getY11() %>"></td>
			<td class="xj xj_4 xj_4_<%=i %>"><input type="text" name="rlcbysList[<%=i %>].y12" value="<%=rlcbysList.get(i).getY12()==null?"":rlcbysList.get(i).getY12() %>"></td>
			
			<% } %>
			
			<% if (allC == i + 1) { %>
			</tr><tr class="xjTitle">
			<td >小计</td>
			
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getJ1()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getJ2()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getJ3()) %></td>
			<td colspan="3"><%=UtilStr.formatDouble(xjList.get(startC).getJ4()) %></td>
			
			<% } %>
			
			
			</tr>
		
		<% } %>
	</tbody>

</table>
<div style="width: 100%; text-align: center;margin: 20px 0;">
	<input type="button" id="saveYSBtn" value="保存需继续完善">
	&emsp;&emsp;
	<input type="button" id="saveForBtn" value="保存并通过审核">
</div>
</form>