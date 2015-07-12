<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.hpms.xtwh.entity.*,java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效管理信息平台</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="themes/default/easyui.css">
<link rel="stylesheet" href="themes/icon.css">
<link rel="stylesheet" href="css/main.css">

</head>
<body class="easyui-layout">
	<div id="top_panel" data-options="region:'north'">
    <div style="display: inline-block;width: 60px"></div>
    <h1 style="display: inline-block;position:absolute;">绩效管理信息平台</h1>
    <div class="other_menu">
        <ul>
            <li><a href="#" class="icon-lock-edit">修改密码</a></li>
            <li><a href="#" class="icon-user">个人信息</a></li>
            <li><a href="#" class="icon-information">关于我们</a></li>
            <li><a href="#" class="icon-door-out">安全退出</a></li>
        </ul>
    </div>
    <div style="clear: both"></div>
    <div class="other_menu mess_line">
        <span>安亭医院</span>&emsp;&emsp;
        <span>用户：superadmin</span>&emsp;&emsp;
        <span>角色：超级管理员</span>
    </div>
    <div class="menu_one">
        <ul id="menu_list">
        <% List<HPXT_CDMKZC> cdList = (List<HPXT_CDMKZC>)request.getAttribute("cdList"); %>
        <% for (int i = 0;i < cdList.size(); i++) { %>
        	<li><a href="javascript:void(0);" class='menu-p' data-href='xtwh/cdmkzc/menu/1/<%=request.getAttribute("pg") %>' data-p='<%=cdList.get(i).getCdbh()%>'><%=cdList.get(i).getCdmc() %></a></li>
        <% } %>
        </ul>
        <div style="float: right;margin-right: 30px;line-height: 30px"></div>
        <div id="top-up" class="panel-tool">
            <a class="layout-button-up" href="javascript:void(0)"></a>
        </div>
    </div>
</div>
<div id="south-panel" data-options="region:'south'" style="height:28px;overflow: hidden;">
    <div id="footer" >
        CopyRight &copy; 上海融达信息科技有限公司
    </div>
</div>
<div id="side_panel" data-options="region:'west',title:'系统功能',split:true,iconCls:'icon-computer'" style="width:200px;min-width:200px;">
    <div id="menu_list_two" class="easyui-accordion" data-options="fit:true,border:false">
        
    </div>
</div>
<div id="main_panel" data-options="region:'center'" style="">
    <div id="main-tabs" class="easyui-tabs" data-options="fit:true,border:false" style="margin: 0;padding: 0">
        <div title="欢迎页" style="padding:20px;" data-options="iconCls:'icon-home'">
        </div>
    </div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/easyui-extends.js"></script>
<script>
    $(function() {
        $("#top-up").click(function() {
            $("body").layout("collapse","north");
        });
        $("#main-tabs").tabs({
            onContextMenu:function(e) {
                e.preventDefault();
                $('#tab-menu').menu('show', {
                    left:e.pageX,
                    top:e.pageY
                });
            }
        });
      	$("#menu_list").on("click",".menu-p",function() {
      		var $this = $(this);
      		var _size = $("#menu_list_two").find(".panel").size();
			for (var i = 0; i < _size; i++) {
				$("#menu_list_two").accordion("remove",0);
			}
      				var _d = "<ul id='m_tree' class='easyui-tree tree-menu' data-options=''>"
      				$.post("xtwh/cdmkzc/menu/2",{"p":$(this).attr("data-p")}, function(data) {
              			$.each(data,function(i,it) {
              				_d += "<li><a data-href='"+it.url+"'>"+it.cdmc+"</a></li>";
              			})
              			_d += "</ul>";
        				$("#menu_list_two").accordion("add",{
        					title:$($this).html(),
        					content: _d
        				})
        				$("#m_tree").tree({
       		            onClick:function(){
       		                var nodes = $(this).tree('getSelected');
       		                var node = nodes.text;
       		                var _title = $(node).html();
       		                var _href = $(node).attr("data-href");
       		                var content = '<iframe frameborder="0"  src="'+_href+'" style="width:100%;height:100%"></iframe>';

       		                if ($("#main-tabs").tabs("exists",_title)) {
       		                    $("#main-tabs").tabs("select",_title);
       		                } else {
       		                    $("#main-tabs").tabs("add",{
       		                        title:_title,
       		                        content:content,
       		                        //href:_href,
       		                        closable:true
       		                    })
       		                }
       		               $("#main-tabs").tabs("maxTabs",5);
       		            }
       		        });
              		});
  			});
        //
        
    })
</script>
<div id="tab-menu" class="easyui-menu" style="width:150px;">
    <div id="closeCurrent" data-options="iconCls:'icon-tab-delete'">关闭</div>
    <div id="closeAll" data-options="iconCls:'icon-clear'">全部关闭</div>
    <div id="closeOther" data-options="iconCls:'icon-cancel'">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="closeLeft" data-options="iconCls:'icon-close-left'">当前页左侧全部关闭</div>
    <div id="closeRight" data-options="iconCls:'icon-close-right'">当前页右侧全部关闭</div>
</div>
<script>
    $(function() {
        $("#closeCurrent").click(function() {$("#main-tabs").tabs("closeCurrent");});
        $("#closeAll").click(function() {$("#main-tabs").tabs("closeAll");});
        $("#closeOther").click(function() {$("#main-tabs").tabs("closeOther");});
        $("#closeLeft").click(function() {$("#main-tabs").tabs("closeLeft");});
        $("#closeRight").click(function() {$("#main-tabs").tabs("closeRight");});
        
        if(navigator.userAgent.indexOf("MSIE")>0) {   
            if(navigator.userAgent.indexOf("MSIE 8.0")>0 || navigator.userAgent.indexOf("MSIE 9.0")>0) { 
            	$("#menu_list_two").accordion({"animate":"false"});
            	
            }
        }
    });
</script>
</body>
</html>