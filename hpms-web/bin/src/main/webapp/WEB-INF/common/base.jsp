<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" href="themes/default/easyui.css">
<link rel="stylesheet" href="themes/icon.css">
<link rel="stylesheet" href="css/main.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<!-- <script type="text/javascript" src="js/modernizr.min.js"></script> -->
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/easyui-extends.js"></script>
