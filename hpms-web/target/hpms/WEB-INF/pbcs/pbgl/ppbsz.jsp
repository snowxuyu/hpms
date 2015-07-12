<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班表设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" title="排班表设置" class="search-condition">
			<h2 style="text-align: center">科室排班表</h2>
			日期:<input type="text" name="rq" id="rq" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',ychanged:cFunc,Mchanged:cFunc})" class="wdate"/>
			科室:<input type="text" name="ksnm" id="ksnm" class="easyui-combobox"/>
	</div>
	<div data-options="region:'center'">
		<table id="disgrid"></table>
	</div>
	<div id="winBb" class="easyui-window win" title="选择班别">
		
	</div>
	<div id="winqt" class="easyui-window win" title="其他设置">
		
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save()">保存</a>
		<a id="searchBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">递交</a>
        <a id="printBtn" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="exp()">导出</a>
	</div>
</body>
<script type="text/javascript">
	var options = {};
	$(function() {
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
	    $('.easyui-window').window('close');
		$('#ksnm').combobox({
			url : 'yljx/ks/query',
			required : false,
			valueField : 'id',
			textField : 'text'
		});
		
		Date.prototype.format = function(format){ 
			var o = { 
			"M+" : this.getMonth()+1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(), 
			"s+" : this.getSeconds(), 
			"q+" : Math.floor((this.getMonth()+3)/3),
			"S" : this.getMilliseconds()
			} 

			if(/(y+)/.test(format)) { 
			format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			} 

			for(var k in o) { 
			if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
			} 
			} 
			return format; 
		} 
		var date = new Date();
		var fDate = date.format("yyyy-MM");
		$('#rq').val(fDate);
		
		var s=fetchData();
// 		var data = $.parseJSON(s);
		$("#disgrid").datagrid({
			type : 'POST',
			nowrap : false,
			striped : true,
			fit : true,
			fitColumns :true,
			pageSize:18,
            pageList:[18,20,22],
			remoteSort : false,
			pagination : true,
			rownumbers : true,
			singleSelect : true,
			toolbar:"#tb",
			columns:s
			         
		});
	});

	function cFunc(){
		$('#rq').val($dp.cal.newdate['y']+"-"+$dp.cal.newdate['M']);
		fetchData();
	}
	function getJson(url) {
 		var rq = $('#rq').val();
 		var ksnm = '31011400300101';
		var val;
		$.ajax({
			url : url,
			method : 'post',
			data:{rq:rq,ksnm:ksnm},
			cache : false,
			async : false,
			dataType : 'text',
			success : function(data) {
				val = data;
			}
		});
		return val
	}

	function fetchData() {
		var s="["+getJson("pbcs/pbgl/ppbsz/query")+"]";
 		//var ss="["+getJson("pbcs/pbgl/ppbsz/queryAll")+"]";
		//options = {};
		//options.columns = eval(s);
		//$('#disgrid').datagrid(options);
		//$('#disgrid').datagrid('reload');
		//$('#disgrid').datagrid('loadData',ss);
		return s;
	}
</script>
</html>