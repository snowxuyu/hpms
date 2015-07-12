<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科教按科室明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
        $('.easyui-window').window('close');
   		$('#kslb').combobox({
   			url : 'kjjx/jxryAction/getKsList',
			required : false,
			valueField : 'zdbm',
			textField : 'zdmc'
   		});
   		
   		query();
	});
	
	function query(){
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
 		var kslb = $('#kslb').combobox('getValue');
 		if(startTime == "" && endTime != ""){
			$.messager.alert('提示','请选择开始时间!');
		}
		if(startTime != "" && endTime == ""){
			$.messager.alert('提示','请选择结束时间!');
		}
		
 		$('#dg').datagrid({
			fit:true,
            singleSelect:true ,
            rownumbers:true,
            pagination:true,
            pageSize:18,
            pageList:[18,20,22],
            closable:true,
            toolbar:"#tb",
			queryParams:{"startTime":startTime,"endTime":endTime,"kslb":kslb},
			url:'kjjx/kjkmxz/queryAll',
			method:'post'
		});
 		
	}
	
	function exp(){
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
 		var kslb = $('#kslb').combobox('getValue');
 		/* $.get("kjjx/kjkmxz/ksmxexpor?kslb="+kslb+"&startTime="+startTime+"&endTime="+endTime); */
 		if(navigator.userAgent.indexOf("Firefox") != -1
				|| navigator.userAgent.indexOf("Chrome") != -1){
 			window.open("kjjx/kjkmxz/ksmxexpor?kslb="+kslb+"&startTime="+startTime+"&endTime="+endTime);
		}else if(navigator.userAgent.indexOf("Gecko") != -1){
			window.open("kjjx/kjkmxz/ksmxexpor?kslb="+kslb+"&startTime="+startTime+"&endTime="+endTime);
		}
		else{
			window.open("kjkmxz/ksmxexpor?kslb="+kslb+"&startTime="+startTime+"&endTime="+endTime);
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="科室绩效科室年度筛选" data-options="region:'north'" >
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始时间：<input type="text" name="startTime" id="startTime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="wdate"/>
			结束时间：<input type="text" name="endTime" id="endTime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="wdate"/>
			科室类别：<input type="text" name="kslb" id="kslb" class="easyui-combobox"/>
		</fieldset>	
	</div>
	<div data-options="region:'center',title:'科室绩效科室年度明细'">
		<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'ksmc',width:100">科室名称</th>
						<th data-options="field:'a',width:90">A科技成果</th>
						<th data-options="field:'b',width:90">B发表论文</th>
						<th data-options="field:'c',width:90">C职务专利</th>
						<th data-options="field:'d',width:90">D科研项目</th>
						<th data-options="field:'e',width:90">E三新项目</th>
						<th data-options="field:'f',width:90">F学术兼职</th>
						<th data-options="field:'k',width:90">k重点学科</th>
						<th data-options="field:'l',width:90">L人才培养</th>
						
						<th data-options="field:'kypjf',width:90,formatter: function(value,row,index){
				    		return value.toFixed(2);
							},styler: function(value,row,index){
								return 'background-color:#E0ECFF;color:red;';
							}
						">科研平均分</th>
						
						<th data-options="field:'g',width:90">G院内培训</th>
						<th data-options="field:'h',width:90">H三基考试</th>
						<th data-options="field:'i',width:90">I继续教育</th>
						<th data-options="field:'j',width:90">J带教情况</th>
						
						<th data-options="field:'jypjf',width:90,formatter: function(value,row,index){
				    		return value.toFixed(2);
							},styler: function(value,row,index){
								return 'background-color:#E0ECFF;color:red;';
							}
						">教育平均分</th>
						
						<th data-options="field:'hj',width:100,formatter: function(value,row,index){
							if (value == null) {
								return '0.00';
							}
				    		return value.toFixed(2);
							},styler: function(value,row,index){
								return 'background-color:#ffeeee;color:blue;';
							}
						">合计</th>
					</tr>
				</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
		<a id="addBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query()">查询</a>
        <a id="printBtn" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="exp()">导出</a>
	</div>
</body>
</html>