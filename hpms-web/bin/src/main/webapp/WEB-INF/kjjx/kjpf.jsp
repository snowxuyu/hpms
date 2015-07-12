<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>科教评分标准设置</title>
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
</head>
<style>
#rlcbysTb {
	width: 100%;
	min-width: 800px;
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
	background: linear-gradient(to bottom, #f9f9f9 0px, #efefef 100%)
		repeat-x scroll 0 0 #f9f9f9;
	padding: 10px 15px;
}

#rlcbysTb tr td {
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	background: #fff;
	/* background: linear-gradient(to bottom, #f9f9f9 0px, #efefef 100%)
		repeat-x scroll 0 0 #f9f9f9; */
	padding: 10px 15px;
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

.locktop {
	position: fixed;
	-ms-position: fixed;
}
</style>
<body>

	<div id="tb">
		<a id="save" href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok',plain:false">保存</a>
	</div>


	<div id="p" class="easyui-dialog" title="科教评分标准设置"
		data-options="fit:true,closable:false,draggable:false,toolbar:'#tb'">
		<table id="rlcbysTb">
			<thead>
				<tr>
					<th>序号</th>
					<th>项目名称</th>
					<th>类别名称</th>
					<th>级别名称</th>
					<th>署名名称</th>
					<th>分数</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${list}" varStatus="i">
				<c:forEach var="item2" items="${item.list}" varStatus="l">
					<c:forEach var="item3" items="${item2.list}" varStatus="k">
						<c:forEach var="item4" items="${item3.list}" varStatus="n">
							<tr>
								<c:if test="${k.index==0&&l.index==0&&n.index==0}">
									<td rowspan="${item.count}">${i.count}</td>
									<td rowspan="${item.count}">${item.name}<%-- ${i.index }${k.index} --%>
									</td>
								</c:if>
								<c:if test="${k.index==0 &&n.index==0}">
									<td rowspan="${item2.count }">${item2.name}${k.index }
										${l.index}</td>
								</c:if>
								<c:if test="${n.index==0 }">
									<td rowspan="${item3.count}">${item3.name}</td>
								</c:if>
								<td>${item4.name }</td>
								<td><input
									style="border: #B4F4F4 1px solid; text-align: right; width: 200px;"
									id="fz_${item4.code }" type="text" value="${item4.fz }"
									onblur="javascript:nihao(this.id,'${item4.code}',this.value,${item4.fz });" />
								</td>
							</tr>

						</c:forEach>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#save').bind('click', function() {
			var rowstr = "";
			if (count <= 0) {
				return;
			}
			if (count > 20) {
				$.messager.alert("操作提示！", "只能一次性编辑20条数据！");
				return;
			}
			rowstr = "[" + str + "]";
			str = "";
			count = 0;
			$.post('kjjx/kjpfAction/update', {
				"mobile" : rowstr
			}, function(data) {
				if (data != "" && data == "success") {
					window.location.href='<%=basePath %>kjjx/kjpfAction?refresh=1';
				} else {
					$.messager.alert("操作提示","保存失败","error",function(){
						window.location.reload();
					});
				}
			});
		})
	})
	var array;
	var str = "";
	var count = 0;
	function nihao(id1, id, val1, val2) {
		var val1 = checkvalue(id1, val1, val2);
		if (val1 != val2) {
			if (str == "" || str == null) {
				str = "{\"SMBM\":\"" + id + "\",\"FZ\":\"" + val1 + "\"}";
				count++;
			} else {
				str += ",{\"SMBM\":\"" + id + "\",\"FZ\":\"" + val1 + "\"}";
				count++;
			}
		}
	}

	function checkvalue(id, newVal, oldVal) {
		if ((/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(newVal))&&newVal.length<=7) {
			document.getElementById(id).value = newVal;
		} else {
			document.getElementById(id).value = oldVal;
		}
		return document.getElementById(id).value;

	}
</script>
</html>