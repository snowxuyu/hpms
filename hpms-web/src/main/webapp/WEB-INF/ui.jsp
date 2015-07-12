<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.apache.commons.codec.binary.*"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/at_hpms/";
%>
<% String username = (String)session.getAttribute("username"); %>
<% String password = (String)session.getAttribute("password"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
		<link rel="stylesheet" href="css/font-awesome.min.css">
    	<script src="js/modernizr.min.js"></script>
    	
	</head>
<body>
	<%
	byte[] b = Base64.encodeBase64(username.getBytes());
	String u = new String(b);
	b = Base64.encodeBase64(password.getBytes());
	String p = new String(b);
	%>
    <div class="metro-box">
        <div class="metro-line">
            <div class="tile tile-lg col1" onclick="window.open('<%=basePath %>hpms.jsp?u=<%=u %>&p=<%=p %>&systemid=1')">
                <div class="metro-title title-lg">薪酬分配</div>
                <span class="fa fa-dollar icon-lg"></span>
            </div>
        </div>
        <div class="metro-line">
            <div class="tile col2 h_tile" onclick="window.open('<%=basePath %>hpms.jsp?u=<%=u %>&p=<%=p %>&systemid=2')">
                <div class="metro-title">指标监管</div>
                <span class="fa fa-line-chart"></span>
            </div>
            <div class="tile col3 h_tile" onclick="window.open('<%=basePath %>hpms.jsp?u=<%=u %>&p=<%=p %>&systemid=3')">
                <div class="metro-title">综合评价</div>
                <span class="fa fa-list-alt"></span>
            </div>
            <div class="tile col4 h_tile" onclick="window.open('<%=basePath %>hpms.jsp?u=<%=u %>&p=<%=p %>&systemid=4')">
                <div class="metro-title">科教绩效</div>
                <span class="fa fa-flask"></span>
            </div>
        </div>
    </div>
	<!-- <script type="text/javascript">
   		$(function() {
   			$(".tile").each(function() {
   				$(this).click(function() {
   					window.open($(this).attr("data-href"));
   				})
   			})
   		})
   	</script> -->
</body>
</html>