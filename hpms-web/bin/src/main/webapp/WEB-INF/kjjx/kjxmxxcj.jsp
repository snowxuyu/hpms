<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科教项目信息采集</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<style type="text/css">
	table, th {
		font-size: 12px;
		font-weight: normal;
	}
	#tableB {
		margin: 0 auto;
	}
	#tableB, #tableB > tr > th {
		font-size: 12px;
		font-weight: normal;
	}
</style>
<script src="<%=basePath %>js/jquery.json.min.js"></script>
<script type="text/javascript">
function inputTextChange()
{
	 var item = $('#dg').datagrid('getSelected');
	 var t = $("#tableB");
	 var str = "";
	if(item != null){
		if (item.xmbm == 'A') {
			str += "<tr id='kz1TR'><th>"
					+ "题目及编号:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "所获成果称号:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm == 'B') {
			str += "<tr id='kz1TR'><th>"
					+ "期刊名称:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "论文名称:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'C') {
			str += "<tr id='kz1TR'><th>"
					+ "题目及编号:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "所获专利类型:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'D') {
			str += "<tr id='kz1TR'><th>"
					+ "项目名称及级别:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "项目组成员排名:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'E') {
			str += "<tr id='kz1TR'><th>"
					+ "项目名称及级别:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "项目组成员排名:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'F') {
			str += "<tr id='kz1TR'><th>"
					+ "聘任机构:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "职务名称:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'G') {
			str += "<tr id='kz1TR'><th>"
					+ "院级/科室培训:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "参加次数:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'H') {
			str += "<tr id='kz1TR'><th>"
					+ "参加院级/科室考核次数:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "平均分:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'I') {
			str += "<tr id='kz1TR'><th>"
					+ "I类学分完成数:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "是否达标:"
					+ "</th>"
					+ "<td><input id='kz2' type='text'/></td>"
					+ "</tr>";

		}
		if (item.xmbm  == 'J') {
			str += "<tr id='kz1TR'><th>"
					+ "教学工作内容:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "完成情况(份次/次/人次):"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>";
		}
		if (item.xmbm  == 'K') {
			str += "<tr id='kz1TR'><th>"
					+ "扩展3:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "备注:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>";
		}
		if (item.xmbm  == 'L') {
			str += "<tr id='kz1TR'><th>"
					+ "扩展3:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "备注:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
					+ "</tr>";
		}
		t.append(str);
		if(item.xmbm == 'A'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'B'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'C'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'D'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'E'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'F'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'G'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:0
			});
		}
		if(item.xmbm == 'H'){
			$('#kz1').numberbox({
				width:153,
				max:10000,
				precision:0
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:2
			});
		}
		if(item.xmbm == 'I'){
			$("#kz1").numberbox({
				width:153,
				max:10000,
				precision:0
			});
			$("#kz2").combobox({
				width:153,
				data:[{
	   			    id:"是",
	   			    text:"是"
	   			},{
	   			    id:"否",
	   			    text:"否"
	   			}],
	   		    valueField:'id',
	   		    textField:'text'
			});
		}
		if(item.xmbm == 'J'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:0
			});
		}
		if(item.xmbm == 'K'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(item.xmbm == 'L'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
	}else{
		if (($('#xmmc').combobox('getValue')) == 'A') {
			str += "<tr id='kz1TR'><th>"
					+ "题目及编号:"
					+ "</th>"
					+ "<td><input id='kz1' type='text'/></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "所获成果称号:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'B') {
			str += "<tr id='kz1TR'><th>"
					+ "期刊名称:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "论文名称:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'C') {
			str += "<tr id='kz1TR'><th>"
					+ "题目及编号:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "所获专利类型:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'D') {
			str += "<tr id='kz1TR'><th>"
					+ "项目名称及级别:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "项目组成员排名:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'E') {
			str += "<tr id='kz1TR'><th>"
					+ "项目名称及级别:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "项目组成员排名:"
					+ "</th>"
					+ "<td><input id='kz2' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'F') {
			str += "<tr id='kz1TR'><th>"
					+ "聘任机构:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>"
					+ "<tr id='kz2TR'><th>"
					+ "职务名称:"
					+ "</th>"
					+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
					+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'G') {
			str += "<tr id='kz1TR'><th>"
				+ "院级/科室培训:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "参加次数:"
				+ "</th>"
				+ "<td><input id='kz2' type='text'/></td>"
				+ "</tr>";
		}
		if (($('#xmmc').combobox('getValue')) == 'H') {
			str += "<tr id='kz1TR'><th>"
				+ "参加院级/科室考核次数:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' class='easyui-numberbox' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "平均分:"
				+ "</th>"
				+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
				+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'I') {
			str += "<tr id='kz1TR'><th>"
				+ "I类学分完成数:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' class='easyui-numberbox' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "是否达标:"
				+ "</th>"
				+ "<td><input id='kz2' type='text'/></td>"
				+ "</tr>";

		}
		if (($('#xmmc').combobox('getValue')) == 'J') {
			str += "<tr id='kz1TR'><th>"
				+ "教学工作内容:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' class='easyui-textbox' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "完成情况(份次/次/人次):"
				+ "</th>"
				+ "<td><input id='kz2' type='text' class='easyui-numberbox' /></td>"
				+ "</tr>";
		}
		if (($('#xmmc').combobox('getValue')) == 'K') {
			str += "<tr id='kz1TR'><th>"
				+ "扩展3:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "备注:"
				+ "</th>"
				+ "<td><input id='kz2' type='text'  /></td>"
				+ "</tr>";
		}
		if (($('#xmmc').combobox('getValue')) == 'L') {
			str += "<tr id='kz1TR'><th>"
				+ "扩展3:"
				+ "</th>"
				+ "<td><input id='kz1' type='text' /></td>"
				+ "</tr>"
				+ "<tr id='kz2TR'><th>"
				+ "备注:"
				+ "</th>"
				+ "<td><input id='kz2' type='text'  /></td>"
				+ "</tr>";
		}
		t.append(str);
		if(($('#xmmc').combobox('getValue')) == 'A'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'B'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'C'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'D'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'E'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'F'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'G'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:0
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'H'){
			$('#kz1').numberbox({
				width:153,
				max:10000,
				precision:0
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:2
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'I'){
			$("#kz1").numberbox({
				width:153,
				max:10000,
				precision:0
			});
			$("#kz2").combobox({
				width:153,
				data:[{
	   			    id:"是",
	   			    text:"是"
	   			},{
	   			    id:"否",
	   			    text:"否"
	   			}],
	   		    valueField:'id',
	   		    textField:'text'
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'J'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$("#kz2").numberbox({
				width:153,
				max:10000,
				precision:0
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'K'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
		if(($('#xmmc').combobox('getValue')) == 'L'){
			$('#kz1').textbox({
				width:153,
				validType:['length[0,50]']
			});
			$('#kz2').textbox({
				width:153,
				validType:['length[0,50]']
			});
		}
	}
}
</script>
<script type="text/javascript">
	var ind =null;
	var kjbm = null;
	var dysmItem = null;
	var desmItem = null;
	var dssmItem = null;
	var dsismItem = null;
	var dwsmItem = null;
	var _html = null;
	$(function(){
		_html = $("#form1").html();
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
   		$('.easyui-window').window('close');
		$('#xmmc1').combobox({
			url:'kjjx/kjxmmx/queryXmmc',
			method:'post',
			valueField:'xmbm',
			textField:'xmmc'
		});
		$('#ksmc').combobox({
			url:'kjjx/kjxmmx/queryKs',
			method:'post',
			valueField:'id',
			textField:'text'
		});
		query();
		
		
		 $("#export").click(function(){
			  $("#export_win").window("open");
		  });
		 
		 $("#exportWinBtn").click(function(){
			 var value = $("#mbmc").combobox("getValue");
			 if (""==value || null==value) {
				$.messager.alert("警告","模板名称不能为空!","warning");
				return;
			 }
			 
			 /* $("#export_form").form("submit", {
				url:'kjjx/kjxmmx/exportExcel',
				onSubmit:function(){
					 $("#_mbmc").val($("#mbmc").combobox("getText"));
				},
				success:function(result){
					
				}
			 }); */
			 var url="<%=basePath %>kjjx/kjxmmx/"+$("#mbmc").combobox("getText") + "/exportExcel";
			 window.open(url);
			 $("#export_win").window("close");
			 return false;
		 });
		 $('#data2').datagrid({
			  fit:true,
			  rownumbers:true,
			  singleSelect:false,
			  toolbar:"#toolbar2",
			  type:'post',
	   		  url:'kjjx/kjxmmx/queryYG'
	   		});
		 
	});
		function query(){ 
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var xmbm1 = $('#xmmc1').combobox('getValue');
		var xm = $('#xm').textbox('getValue');
		var ksnm= $('#ksmc').combobox('getValue');
		if(startTime == "" && endTime != ""){
			$.messager.alert('提示','请选择开始时间!');
		}
		if(startTime != "" && endTime == ""){
			$.messager.alert('提示','请选择结束时间!');
		}
		$('#dg').datagrid({
			fit:true,
			fitColumns:true,
            singleSelect:true , 
            rownumbers:true,
            pagination:true,
            pageSize:18,
            pageList:[18,20,22],
            closable:true,
            toolbar:"#tb",
			queryParams :{"startTime":startTime,"endTime":endTime,"xmbm":xmbm1,"xm":xm,"ksnm":ksnm},
			url:'kjjx/kjxmmx/queryAll',
			method:'post',
			onDblClickRow:function(){
				update();
			}
		});
	}
	
	//关闭
   	function closeDiv(){
   		$('#updateDiv').window('close');
   	}
	
	function insertTable(){
		var buttEvent = "add";
		$('#form1').form("clear");
		openDiv(buttEvent);
	}
	function update(){
		var buttEvent = "edit";
		$("#form1").empty().append(_html);
		openDiv(buttEvent);
	}
	

		//打开记录
	   	function openDiv(buttEvent){
	   		//DIV显示控制
	   		var selected;
	   		var addIsInitFirst = false;
	   		var editIsInitFirst = false;
	   		var oldXMBM;
	   		var newXMBM;
	   		
// 	   		$('#nd').attr("disabled", "disabled"); 
	   		
	   		$('#rhsj').val("");
	   		$('#oldxmid').val("");
	   		
	   		if(buttEvent=='edit'){
	   			selected = $('#dg').datagrid('getSelected'); 
	   			if(selected==null){
	   				$.messager.alert('警告','当前没有选择行！','warning');
	  				return;
	   			}else{
	   				$('#oldxmid').val(selected.xmid);
	   			}
	   		}
			$('#updateDiv').window('open');
	   		
			if(buttEvent=="add"){
				$("#updateDiv").panel({title:"科教项目明细-新增"});
			}
			else{
				$("#updateDiv").panel({title:"科教项目明细-修改"});
			}

	   		$("#kz1TR").remove();
	   		$("#kz2TR").remove();
	   		$("#kz3TR").remove();
			$("#remarkTR").remove();
			$("#dysmTR").remove();
			$("#desmTR").remove();
			$("#dssmTR").remove();
			$("#dsismTR").remove();
			$("#dwsmTR").remove();
			
// 	   		getSjxflRemoteSelect('nd','nd','',null);
	   		getSjxflRemoteSelect('xmmc','xm','',null);
// 	   		$('#nd').combobox({
// 				onLoadSuccess: function(){
// 	  					if(buttEvent=='edit'){
// 	  						$(this).combobox('setValue',selected.rhnd);
// 							$(this).combobox('disable');
// 	  					}else{
// 	  						$(this).combobox("enable");
// 	  					}
// 				}
// 			});
	   		$('#xmmc').combobox({
				onChange: function(){
					getSjxflRemoteSelect('lbmc','lb',$('#xmmc').combobox('getValue'),null);
				},
				onLoadSuccess: function(){
					if(buttEvent=='edit'){
						$(this).combobox('setValue',selected.xmbm);
						$(this).combobox('disable');
					}else{
						$(this).combobox("enable");
					}
				}
			});
	   		
	   		$('#lbmc').combobox({
				onChange: function(){
					getSjxflRemoteSelect('jbmc','jb',$('#lbmc').combobox('getValue'),null);
				},
				onLoadSuccess: function(){
					if(buttEvent=='edit'){
						$(this).combobox('setValue',selected.lbbm);
						$(this).combobox('disable');
					}else{
						$(this).combobox("enable");
					}
				}
			});
	   		$('#jbmc').combobox({
				onChange: function(){
					$("#kz1TR").remove();
					$("#kz2TR").remove();
					$("#kz3TR").remove();
					$("#remarkTR").remove();
					$("#dysmTR").remove();
					$("#desmTR").remove();
					$("#dssmTR").remove();
					$("#dsismTR").remove();
					$("#dwsmTR").remove();
	   				newXMBM = ($('#xmmc').combobox('getValue'))+($('#lbmc').combobox('getValue'))+($('#jbmc').combobox('getValue'));
	   				if(buttEvent=="add" && (addIsInitFirst==false || newXMBM != oldXMBM)){
	   					inputTextChange();
						getSjxflRemoteSelect('smmc','sm',$('#jbmc').combobox('getValue'),null);
						addIsInitFirst=true;
						oldXMBM = ($('#xmmc').combobox('getValue'))+($('#lbmc').combobox('getValue'))+($('#jbmc').combobox('getValue'));
	   				}
				},
				onLoadSuccess: function(){
					if(buttEvent=='edit'){
						$(this).combobox('setValue',selected.jbbm)
						$(this).combobox('disable');
						if(editIsInitFirst==false){
							inputTextChange();
							getSjxflRemoteSelect('smmc','sm',selected.jbbm,selected);
							editIsInitFirst=true;
						}
					}else{
						$(this).combobox("enable");
					}
				}
			});
	   	}
	
	  //数据项分类信息 加载
	   	function getSjxflRemoteSelect(id,param,data,selected){
			var data1;
			var url;
			if(param=="xm"){
				data1 = {"param":param,"xmbm":data};
				url = "kjjx/kjxmmx/queryKjzd";
			}
			if(param=="lb"){
				data1 = {"param":param,"xmbm":data};
				url = "kjjx/kjxmmx/queryKjzd";
			}
			if(param=="jb"){
				data1 = {"param":param,"lbbm":data};
				url = "kjjx/kjxmmx/queryKjzd";
			}
			if(param=="sm"){
				if(selected != null){
					data1 = {"param":param,"jbbm":data,"lbbm":selected.lbbm};
				}else{
					data1 = {"param":param,"jbbm":data,"lbbm":$('#lbmc').combobox('getValue')};
				}
				url = "kjjx/kjxmmx/queryKjsm";
			}
// 			if(param=="nd"){
// 				url = "kjjx/kjxmmx/queryNd";
// 			}
			$.ajax({				
				url: url,	
				method:'post',
				dataType: 'json',  
				data: data1,              
				success: function(dataRes){
					if(param!="sm"){
// 						if(param == "nd"){
// 							$("#" + id).combobox({ valueField: 'jxnd',
// 								textField: 'jxnd',
// 								data: dataRes,
// 								setValue : '',
// 								filter: function(q, row){
// 									var opts = $(this).combobox('options');
// 								    return row[opts.textField].indexOf(q) >= 0;
// 								}
// 							  });
// 						}else{
							$("#" + id).combobox({ valueField:'id',
								textField: 'name',
								data: dataRes,
								setValue : '',
								filter: function(q, row){
									var opts = $(this).combobox('options');
								    return row[opts.textField].indexOf(q) >= 0;
								}
							  });
// 						}
						
					}else{
						 $.each(dataRes, function(index,item){
							 kjbm = item.kjbm;
						   }); 
						$("tr[name='trName']").each(function(){
							$(this).remove();
						});
						var t = $("#tableB");
						var str = "";
						for(var i = 0; i < 5 ; i++){
							if(i == 0)
								checkId = "dysm";
							else if(i==1)
								checkId = "desm";
							else if(i==2)
								checkId = "dssm";
							else if(i==3)
								checkId = "dsism";
							else
								checkId = "dwsm";
							str += "<tr id='"+checkId+"TR'><th>"+(i<dataRes.length?dataRes[i].smmc+":":"")+"</th>" 
								+"<td "+(i<dataRes.length?"":"type='hidden'")+"><input id='"+checkId+"mc' "+(i<dataRes.length?"type='text'":"type='hidden'")+"  style='width:150px;' readonly='readonly'> "
								+"<input id='"+checkId+"' type='hidden' style='width:150px;'>"
								+"<input id='"+checkId+"fz' type='hidden' value='"+(i<dataRes.length?dataRes[i].fz:"")+"' style='width:150px;'>"
								+"<input id='"+checkId+"bm' type='hidden' value='"+(i<dataRes.length?dataRes[i].smbm:"")+"' style='width:150px;'>"
								+"<input id='"+checkId+"LimitedNum' type='hidden' class='easyui-numberbox'  style='width:150px;'>"
								+"<input id='"+checkId+"Button'"+(i<dataRes.length?"type='button'":"type='hidden'")+"  value='选择人员' onclick='chooseUserInfo("+i+");'></td>"
								+"</tr>";
		  				}
						t.append(str);
						if(selected!=null){
							if(selected.dysm!=null){
								$('#dysmmc').val(selected.dysmmc);
								$('#dysm').val(selected.dysm);
							}
							if(selected.desm!=null){
								$('#desmmc').val(selected.desmmc);
								$('#desm').val(selected.desm);
							}
							if(selected.dssm!=null){
								$('#dssmmc').val(selected.dssmmc);
								$('#dssm').val(selected.dssm);
							}
							if(selected.dsism!=null){
								$('#dsismmc').val(selected.dsismmc);
								$('#dsism').val(selected.dsism);
							}
							if(selected.dwsm!=null){
								$('#dwsmmc').val(selected.dwsmmc);
								$('#dwsm').val(selected.dwsm);
							}
							if(selected.kz1!=null){
								$('#kz1').textbox('setValue',selected.kz1);
							}
							if(selected.kz2!=null){
								$('#kz2').textbox('setValue',selected.kz2);
							}
							if(selected.kz3!=null){
								$('#kz3').textbox('setValue',selected.kz3);
							}
							if(selected.rhsj !=null){
								$('#rhsj').val(selected.rhsj);
							}
							if(selected.remark!=null){
								if(selected.xmbm != "I"){
									$('#remark').textbox('setValue',selected.remark);
								}else{
									$('#remark').combobox('setValue',selected.remark);
								}
							}
						}
					}
				}	
			});		
	   	}
	   	function del()
		{
			var selected = $('#dg').datagrid('getSelected'); 
			$.messager.confirm('确认','是否删除该条记录?',function(result){
				if(result){
					if(selected==null)
						$.messager.alert('警告','当前没有选择行!','warning');
					else{
						id =selected.xmid; 
		               	$.ajax({ 
		                       type:"POST", 
		                       url:"kjjx/kjxmmx/del", 
		                       data:{"id":id}, 
		                      success:function (msg){
		                    	  if(msg == "1"){
		                    		  $.messager.show({
		                    				title:"操作提示",
		                    				msg:"删除成功！",
		                    				timeout:3000
		                    			});
		                    		  query(); 
		                    	  }
								}
		                    });
					}
	              }
			});
		}
	  function save(){
		  var dy = dysmItem;
		  var de = desmItem;
		  var ds = dssmItem;
		  var dsi = dsismItem;
		  var dw = dwsmItem;
		  var xmid = $('#oldxmid').val();
		  var dysmfz = $('#dysmfz').val();
		  var desmfz = $('#desmfz').val();
		  var dssmfz = $('#dssmfz').val();
		  var dsismfz = $('#dsismfz').val();
		  var dwsmfz = $('#dwsmfz').val();
		  var dysmbm = $('#dysmbm').val();
		  var desmbm = $('#desmbm').val();
		  var dssmbm = $('#dssmbm').val();
		  var dsismbm = $('#dsismbm').val();
		  var dwsmbm = $('#dwsmbm').val();
		  var rhsj = $('#rhsj').val();
		  var dysm = $('#dysm').val();
		  var dysmmc = $('#dysmmc').val();
		  var desm = $('#desm').val();
		  var desmmc = $('#desmmc').val();
		  var dssm = $('#dssm').val();
		  var dssmmc = $('#dssmmc').val();
		  var dsism = $('#dsism').val();
		  var dsismmc = $('#dsismmc').val();
		  var dwsm = $('#dwsm').val();
		  var dwsmmc = $('#dwsmmc').val();
		  var kz1 = $('#kz1').val();
		  var kz2 = $('#kz2').val();
		  var kz3 = $('#kz3').val();
		  var xmmc = $('#xmmc').combobox('getValue')
		  var lbmc = $('#lbmc').combobox('getValue')
		  var jbmc = $('#jbmc').combobox('getValue')
		  if(xmmc != "I"){
			  var remark = $('#remark').val(); 
		  }else{
			  var remark = $('#remark').combobox('getValue');
		  }
		  if(xmmc == "" || xmmc == null ){
			  $.messager.alert('提示',"项目名称不能为空！");
			  return;
		  }
		  if(lbmc == "" || lbmc == null){
			  $.messager.alert('提示',"类别名称不能为空！"); 
			  return;
		  }
		  if(jbmc == "" || jbmc == null){
			  $.messager.alert('提示',"级别名称不能为空！");
			  return;
		  }
		  if(rhsj == "" || rhsj == null){
			  $.messager.alert('提示',"荣获时间不能为空！");
			  return;
		  }
		  if(xmid != null && xmid !=""){
			  $.messager.progress();
			  $.ajax({
				  type:'post',
				  url:'kjjx/kjxmmx/update',
				  data:{"xmid":xmid,"rhsj":rhsj,"dysm":dysm,"dysmmc":dysmmc,"desm":desm,"desmmc":desmmc,
					  "dssm":dssm,"dssmmc":dssmmc,"dsism":dsism,"dsismmc":dsismmc,"dwsm":dwsm,"dwsmmc":dwsmmc,
					  "kz1":kz1,"kz2":kz2,"kz3":kz3,"remark":remark,
					  "dysmfz":dysmfz,"desmfz":desmfz,"dssmfz":dssmfz,"dsismfz":dsismfz,"dwsmfz":dwsmfz,
					  "dysmbm":dysmbm,"desmbm":desmbm,"dssmbm":dssmbm,"dsismbm":dsismbm,"dwsmbm":dwsmbm,
					  "dysmItem":dysmItem,"desmItem":desmItem,"dssmItem":dssmItem,"dsismItem":dsismItem,"dwsmItem":dwsmItem},
				  success:function(msg){
					  $.messager.progress('close');
					  if(msg == "1"){
	            		  $('#updateDiv').window('close');
	            		  $.messager.show({
	            				title:"操作提示",
	            				msg:"修改成功！",
	            				timeout:3000
	            			});
							query();
							dysmItem=null;
							desmItem=null;
							dssmItem=null;
							dsismItem=null;
							dwsmItem=null;
	            	  }
				  },
				  error:function(XMLHttpRequest, textStatus, errorThrown){
					  $.messager.progress('close');
					  $.messager.alert('警告',"修改失败!","error");
					    dysmItem=null;
						desmItem=null;
						dssmItem=null;
						dsismItem=null;
						dwsmItem=null;
				  }
			  });
		  }else{
			  $.messager.progress();
			  $.ajax({
				  type:'post',
				  url:'kjjx/kjxmmx/insert',
				  data:{"kjbm":kjbm,"rhsj":rhsj,"dysm":dysm,"dysmmc":dysmmc,"desm":desm,"desmmc":desmmc,
					  "dssm":dssm,"dssmmc":dssmmc,"dsism":dsism,"dsismmc":dsismmc,"dwsm":dwsm,"dwsmmc":dwsmmc,
					  "kz1":kz1,"kz2":kz2,"kz3":kz3,"remark":remark,
					  "dysmfz":dysmfz,"desmfz":desmfz,"dssmfz":dssmfz,"dsismfz":dsismfz,"dwsmfz":dwsmfz,
					  "dysmbm":dysmbm,"desmbm":desmbm,"dssmbm":dssmbm,"dsismbm":dsismbm,"dwsmbm":dwsmbm,
					  "dysmItem":dysmItem,"desmItem":desmItem,"dssmItem":dssmItem,"dsismItem":dsismItem,"dwsmItem":dwsmItem},
				  success:function(msg){
					  $.messager.progress('close');
					  if(msg == "1"){
						  $('#updateDiv').window('close');
						  $.messager.show({
								title:"操作提示",
								msg:"新增成功！",
								timeout:3000
							});
							query();
							dysmItem=null;
							desmItem=null;
							dssmItem=null;
							dsismItem=null;
							dwsmItem=null;
	            	  }
				  },
				  error:function(XMLHttpRequest, textStatus, errorThrown){
					  $.messager.progress('close');
					  $.messager.alert('警告',"新增失败!",'error');
					    dysmItem=null;
						desmItem=null;
						dssmItem=null;
						dsismItem=null;
						dwsmItem=null;
				  }
			  });
		  }
	  }
	  function chooseUserInfo(i){
		  ind = i;
		  $('#userChooseDiv').window('open');
		  var mhxm = $("#mhxm").textbox('getValue');
		  
	  }
	  function chooseUserInfo1(i){
		  ind = i;
		  $('#userChooseDiv').window('open');
		  var mhxm = $("#mhxm").textbox('getValue');
		  var op = $('#data2').datagrid("options").queryParams;
		  op["ygxm"] = mhxm;
		  
		  $('#data2').datagrid("reload");
	  }
	  function saveUserInfo(){
		  var ygxm = "";
		  var ygbh = "";
		  var xmmc = $('#xmmc').combobox('getValue')
		  var checkedItem = $('#data2').datagrid('getChecked');
		  if(xmmc == "A" || xmmc == "G"){
			  if(ind != 4){
				  if(checkedItem.length>1){
					  $.messager.alert('提示',"限制人数为一个人!"); 
					  return;
				  }
			  }else{
				  if(checkedItem.length>5){
					  $.messager.alert('提示',"人数过多!"); 
					  return;
				  }  
			  }
		  }else{
			  if(checkedItem.length>5){
				  $.messager.alert('提示',"人数过多!"); 
				  return;
			  } 
		  }
 		  if(checkedItem.length == 1){
 			 ygxm= checkedItem[0].ygxm+',';
			  ygbh= checkedItem[0].ygbh+',';
			  if(ind==0){
				  $("#dysmmc").val(ygxm);
				  $("#dysm").val(ygbh);
				  dysmItem = $.toJSON(checkedItem);
			  }
			  if(ind==1){
				  $("#desmmc").val(ygxm);
				  $("#desm").val(ygbh);
				  desmItem = $.toJSON(checkedItem);
			  }
			  if(ind==2){
				 $("#dssmmc").val(ygxm);
				  $("#dssm").val(ygbh);
				  dssmItem = $.toJSON(checkedItem);
			  }
			  if(ind==3){
				 $("#dsismmc").val(ygxm);
				  $("#dsism").val(ygbh);
				  dsismItem = $.toJSON(checkedItem);
			  }
			  if(ind==4){
				 $("#dwsmmc").val(ygxm);
				  $("#dwsm").val(ygbh);
				  dwsmItem = $.toJSON(checkedItem);
			  }
		  }else{
			  $.each(checkedItem, function(index,item){
				  ygxm+= item.ygxm+',';
				  ygbh+= item.ygbh+',';
				  if(ind==0){
					  $("#dysmmc").val(ygxm);
					  $("#dysm").val(ygbh);
					  dysmItem = $.toJSON(checkedItem);
				  }
				  if(ind==1){
					  $("#desmmc").val(ygxm);
					  $("#desm").val(ygbh);
					  desmItem = $.toJSON(checkedItem);
				  }
	 			  if(ind==2){
	 				 $("#dssmmc").val(ygxm);
					  $("#dssm").val(ygbh);
					  dssmItem = $.toJSON(checkedItem);
				  }
	 			  if(ind==3){
	 				 $("#dsismmc").val(ygxm);
					  $("#dsism").val(ygbh);
					  dsismItem = $.toJSON(checkedItem);
				  }
	 			  if(ind==4){
	 				 $("#dwsmmc").val(ygxm);
					  $("#dwsm").val(ygbh);
					  dwsmItem = $.toJSON(checkedItem);
				  }
			   }); 
		  }
 		  $("#data2").datagrid("uncheckAll");
		  $('#userChooseDiv').window('close');
	  }
	  
	  function exp(){
		    var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var xmbm1 = $('#xmmc1').combobox('getValue');
			var xm = $('#xm').textbox('getValue');
			var ksnm = $('#ksmc').combobox('getValue');

			if(navigator.userAgent.indexOf("Firefox") != -1
					|| navigator.userAgent.indexOf("Chrome") != -1){
				window.open("kjjx/kjxmmx/xmmxExp?startTime="+startTime+"&endTime="+endTime+"&xmbm="+xmbm1+"&xm="+xm+"&ksnm="+ksnm);
			}else if(navigator.userAgent.indexOf("Gecko") != -1){
				window.open("kjjx/kjxmmx/xmmxExp?startTime="+startTime+"&endTime="+endTime+"&xmbm="+xmbm1+"&xm="+xm+"&ksnm="+ksnm);
			}
			else{
				window.open("kjxmmx/xmmxExp?startTime="+startTime+"&endTime="+endTime+"&xmbm="+xmbm1+"&xm="+xm+"&ksnm="+ksnm);
			}
	  }
	  
	  function imp(){
		  $('#importDiv').window('open');
		  $('#form3').form('clear');
	  }
	  function closeImportDiv(){
		  $('#importDiv').window('close');
	  }
	  function saveImp(){
		  var rhsj = $('#rhsj2').val();
		  var file = $('#file').val();
		  
		  if(rhsj == null || rhsj == ""){
			  $.messager.alert('提示',"请选择时间!"); 
			  return;
		  }
		  
		  if(file == null || file == ""){
			  $.messager.alert('提示',"请选择要导入的文件!"); 
			  return;
		  }
		  $.messager.progress();
		  $("#form3").form("submit", {
      		url:'kjjx/kjxmmx/importExl',
      		success: function(msge) {
      			$.messager.progress('close');
				  if(msge == "1"){
					  $('#importDiv').window('close');
					  $.messager.show({
							title:"操作提示",
							msg:"导入成功！",
							timeout:3000
						});
						query();
         	  		}else if(msge == "2"){
         	  			$.messager.alert('提示',"导入文件中的项目编码，类别编码，级别编码不能为空!"); 
         	  		}else if (msge=="3"){
         	  			$.messager.alert('提示',"导入的文件必须为EXCEL表格!"); 
         	  		}else {
         	  			$.messager.alert('提示',"错误数据，导入失败!");
         	  		}
      		}
      	});
	  }
	  $("#exportWinBtn")
</script>
</head>
<body class="easyui-layout">
	<div class="search-condition" title="科教项目信息筛选" data-options="region:'north'" >
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			开始时间：<input type="text" name="startTime" id="startTime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="wdate"/>
			结束时间：<input type="text" name="endTime" id="endTime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="wdate"/>
			项目名称：<input type="text" name="xmmc1" id="xmmc1" class="easyui-combobox"/>
			<p>
			&emsp;&emsp;姓名：<input type="text" name="xm" id="xm" class="easyui-textbox"/>
			&emsp;&emsp;科室：<input type="text" name="ksmc" id="ksmc" class="easyui-combobox"/>
			</p>
		</fieldset>	
	</div>
	<div data-options="region:'center',title:'科教项目信息采集'">
		<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'xmmc',width:80">项目名称</th>
						<th data-options="field:'lbmc',width:110">类别名称</th>
						<th data-options="field:'jbmc',width:110">级别名称</th>
						<th data-options="field:'rhsj',width:80">荣获时间</th>
						<th data-options="field:'dysmmc',width:80">第1署名</th>
						<th data-options="field:'desmmc',width:80">第2署名</th>
						<th data-options="field:'dssmmc',width:80">第3署名</th>
						<th data-options="field:'dsismmc',width:80">第4署名</th>
						<th data-options="field:'dwsmmc',width:80">第5署名</th>
						<th data-options="field:'kz1',width:150">扩展信息1</th>
						<th data-options="field:'kz2',width:150">扩展信息2</th>
<!-- 						<th data-options="field:'kz3',width:80">其他信息3</th> -->
<!-- 						<th data-options="field:'remark',width:80">备注</th> -->
					</tr>
				</thead>
		</table>
	</div>
	<div id="tb" style="padding:5px;height:auto">
	    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search"  plain="true" onclick="query()">查询</a>
		<a id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="insertTable()">新增</a>
        <a id="updateBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
        <a id="removeBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
        <a id="impBtn" class="easyui-linkbutton" iconCls="icon-upload" plain="true" onclick="imp()">导入</a>
        <a id="prinBtn" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="exp()">导出</a>
        <a id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true">下载导入模板</a>
	</div>
	<div id="updateDiv" class="easyui-window" data-options="inline:true" style="width:480px;height:450px" >
		<form id="form1">
			<div  data-options="region:'center'" style="padding:10px;">
		      <table  id="tableB">
		      <tr>
				<td>
				    <input id="oldxmid" type="hidden" style="width:150px" />
				</td>
				</tr>
				<tr>
					<th>
						项目名称<span class="need">*</span>:
					</th>
					<td>
					    <input id="xmmc" name="xmmc"   type="text" style="width:150px" />
					</td>
				</tr>
				<tr>
					<th>
						类型名称<span class="need">*</span>:
					</th>
					<td>
					     <input id="lbmc" name="lbmc"  type="text" style="width:150px" />
					</td>
				</tr>
				<tr>
					<th>
						级别名称<span class="need">*</span>:
					</th>
					<td>
						<input id="jbmc" name="jbmc"   type="text" style="width:150px" />
					</td>
				</tr>
				<tr id="rhsjTR">
					<th id="rhsjName">获得年月<span class="need">*</span>:</th>
					<td><input id="rhsj"  type="text" style="width:150px" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" readonly="readonly"/></td>
				</tr>
				</table>
				</div>
				<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
				      <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" id="btnSave" onclick="save()">保    存</a>
				      <a class="easyui-linkbutton" data-options="iconCls:'icon-no'" href="javascript:void(0)" onclick="closeDiv()">取    消</a>
				</div>
		</form>
	</div>
	<div id="userChooseDiv" class="easyui-window" title="用户选择" style="width:600px;height:410px">
		<div data-options="region:'west'">
			员工名称:<input type="text" id="mhxm" name="mhxm" class="easyui-textbox"/>
		</div>
		<div data-options="region:'center'" style="height: 350px">
			<table id="data2">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="ksnm" width="100">科室类别</th>
						<th field="ksmc" width="150">科室名称</th>
						<th field="ygbh" width="150">员工编号</th>
						<th field="ygxm" width="100">员工名称</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbar2">
				 <a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="chooseUserInfo1(ind)">模糊查询</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true" onclick="saveUserInfo()">保存</a>
		</div>
	</div>
	<div id="importDiv" class="easyui-window" title="文件导入"  style="width:350px;height:200px">
		<form id="form3"  enctype="multipart/form-data" method="post">
			<div data-options="region:'center'" style="padding:10px;">
				<table style="font-size: 12px">
					<tr style="height: 10px"></tr>
					<tr>
						<td>荣获时间<span class="need">*</span>:
						</td>
						<td><input id="rhsj2" name="rhsj2" type="text" style="width: 150px" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" /></td>
					</tr>
					<tr style="height: 10px"></tr>
					<tr>
						<td>导入路径<span class="need">*</span>:
						</td>
						<td><input id="file" name="file" type="file" style="width:200px" accept="application/msexcel"/></td>
					</tr>
					<tr style="height: 10px"></tr>
				</table>
			</div>
			<div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveImp()">保    存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-no'" href="javascript:void(0)" onclick="closeImportDiv()">取    消</a>
			</div>
		</form>
	</div>	
	<div id="export_win" class="easyui-window win" title="导入模板下载" data-options="maximizable:false,minimizable:false">
		<form id="export_form" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>模板名称:<span class="need">*</span></td> 
					<td>
						<input id="mbmc" class="easyui-combobox" data-options="valueField:'id', textField:'text', url:'kjjx/kjxmmx/exportExportExcel'"/>
						<input id="_mbmc" name="fileName" type="hidden">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a id="exportWinBtn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">导出</a>
						&emsp;&emsp;
						<a id="expcancelBtn" href="javascript:;" class="easyui-linkbutton cancel" data-options="iconCls:'icon-no'">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>