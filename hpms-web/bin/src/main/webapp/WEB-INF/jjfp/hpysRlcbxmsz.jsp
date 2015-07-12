<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人力成本项目设置</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script src="<%=basePath %>js/jquery.json.min.js"></script>
<script type="text/javascript">
	var nd = null; 
	var items = null;  //获取树加载时候的节点
	var yybm = null;
	var sendData = new Object();
	var status = new Object();
	//初始化
	$(function(){
		queryNd(),
		queryXm()
		
	});
	//加载左边的年度表格
	function queryNd(){
	   //var nd1 = document.getElementById("nd").value;
		$('#dg').datagrid({
			//queryParams :{nd:nd1},
			url:'<%=basePath %>jjfp/HpysRlcbysAction/queryNd',
			method:'post',
			onClickRow:function(index,row){
				nd = row.jxnd;
				yybm = row.jxqy;
				queryXm();
			}
		});
	}
	//加载右边的树
	function queryXm(){
		$('#tg').tree({
			queryParams :{nd:nd},
			url:'<%=basePath %>jjfp/HpysRlcbysAction/queryXm',
			method:'post',
			animate:true,
			checkbox:true,
			formatter:function(node){
				if(node.checked == 1){
					node.checked = true;
				}else{
					node.checked = false;
				}
				return node.xmmc;
			},
			onLoadSuccess:function(){
				items = $('#tg').tree('getChecked');
			}
		});
	}
	
	function save(){
		var newItems = $('#tg').tree('getChecked'); //获取树保存之前的节点
		if(nd == null){
			$.messager.alert('警告','请选择年度！');
			return;
		}
		if(newItems.length == 0){
			$.messager.alert('警告','请勾选条目!');
			return;
		}
		if(items.length == 0){
			status =1;
		}else{
			//过滤出加载之后新选择的元素
			for(var i=0;i<items.length;i++){
				for(var j=0;j<newItems.length;j++){
					if(items[i].xmbm==newItems[j].xmbm){
						items.splice(i,1);
						i--;
						newItems.splice(j,1);
						break;
					}
				}
			}
		}
		/* alert(items.length);
		alert(newItems.length); */
		if(status == 1){
			$.messager.confirm("确认", "是否新增这些项目?", function(r){
				if (!r) return false;
			
			$.each(newItems,function(i,c){
				c.hpys_RLCBYS = null;
				delete c.hpys_RLCBYS;
				c.children = null;
				delete c.children;
				c.target = null;
				delete c.target;
			});
				newItems = $.toJSON(newItems);
			$.messager.progress();
			$.ajax({
				type:'POST',
				url:'<%=basePath %>jjfp/HpysRlcbysAction/insertTable',
				data:{item:newItems,nd:nd,yybm:yybm},
				success:function(msg ){
					$.messager.progress('close');
					if(msg=='1'){
						$.messager.show({
							title:"操作提示",
							msg:"新增成功！",
							timeout:3000
						});
						queryXm();
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					$.messager.alert('警告','新增失败！','error');
					queryXm();
				}
			});
			})
		}else{
			
			if(items.length == 0 && newItems.length == 0){
				$.messager.show({
					title:"操作提示",
					msg:"未进行修改！",
					timeout:3000
				});
				return;
			}
			$.messager.confirm("确认", "是否修改这些项目?", function(r){
				if (!r) return false;
			
			if(items.length != 0 && newItems.length == 0){
				$.each(items,function(i,c){
					c.hpys_RLCBYS = null;
					delete c.hpys_RLCBYS;
					c.children = null;
					delete c.children;
					c.target = null;
					delete c.target;
				});
					items = $.toJSON(items);
				$.messager.progress();
				$.ajax({
					type:'POST',
					url:'<%=basePath %>jjfp/HpysRlcbysAction/deleteTable',
					data:{item:items,nd:nd,yybm:yybm},
					success:function(msg ){
						$.messager.progress('close');
						if(msg=='1'){
							$.messager.show({
								title:"操作提示",
								msg:"修改成功！",
								timeout:3000
							});
						}else{
							$.messager.alert("警告","该记录已被占用，不可删除！","error");
						}
						queryXm();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert("警告","修改失败！","error");
						queryXm();
					}
				});
			}
			if(items.length == 0 && newItems.length != 0){
				$.each(newItems,function(i,c){
					c.hpys_RLCBYS = null;
					delete c.hpys_RLCBYS;
					c.children = null;
					delete c.children;
					c.target = null;
					delete c.target;
				});
					newItems = $.toJSON(newItems);
				$.messager.progress();
				$.ajax({
					type:'POST',
					url:'<%=basePath %>jjfp/HpysRlcbysAction/insertTable',
					data:{item:newItems,nd:nd,yybm:yybm},
					success:function(msg){
						$.messager.progress('close');
						if(msg=='1'){
							$.messager.show({
								title:"操作提示",
								msg:"新增成功！",
								timeout:3000
							});
							queryXm();
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('警告','新增失败！','error');
						queryXm();
					}
				});
			}
			if(items.length != 0 && newItems.length != 0){
				var _msg = false;
				$.each(newItems,function(i,c){
					c.hpys_RLCBYS = null;
					delete c.hpys_RLCBYS;
					c.children = null;
					delete c.children;
					c.target = null;
					delete c.target;
				});
					newItems = $.toJSON(newItems);
				$.messager.progress();
				$.ajax({
					type:'POST',
					url:'<%=basePath %>jjfp/HpysRlcbysAction/insertTable',
					data:{item:newItems,nd:nd,yybm:yybm},
					success:function(msg){
						$.messager.progress('close');
						if(msg=='1'){
							/* $.messager.show({
								title:"操作提示",
								msg:"新增成功！",
								timeout:3000
							});
							queryXm(); */
							_msg = true;
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('警告','新增失败！','error');
						queryXm();
					}
				});
				$.each(items,function(i,c){
					c.hpys_RLCBYS = null;
					delete c.hpys_RLCBYS;
					c.children = null;
					delete c.children;
					c.target = null;
					delete c.target;
				});
					items = $.toJSON(items);
				$.messager.progress();
				$.ajax({
					type:'POST',
					url:'<%=basePath %>jjfp/HpysRlcbysAction/deleteTable',
					data:{item:items,nd:nd,yybm:yybm},
					success:function(msg ){
						$.messager.progress('close');
						if(msg=='1'){
							$.messager.show({
								title:"操作提示",
								msg:"修改成功！",
								timeout:3000
							});
						}else{
							$.messager.alert("警告","该记录已被占用，不可删除！","error");
						}
						queryXm();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert("警告","修改失败！","error");
						queryXm();
					}
				});
			}
			})
		}
	}
	
</script>
</head>
<body class="easyui-layout">
	<!-- <div class="search-condition" data-options="title:'绩效年度筛选',region:'north'">
		<fieldset>
			<legend>筛选（带“*”为必填）</legend>
			年度：<input type="text" name="nd" id="nd" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" class="wdate"/>
		</fieldset>	
	</div> -->
	<div data-options="region:'west',width:400,title:'年度查询'" >
		<table id="dg" class="easyui-datagrid" data-options="fitColumns: true ,
                          singleSelect:true , rownumbers:true, 
                          closable:true" toolbar="#tb1"> 
               <thead>
               		<tr>
               			<th  data-options="field:'jxnd',width:150">年度</th>
               		</tr>
               </thead>
		</table>
	</div>
	<div  style="padding:5px" data-options="region:'center',title:'项目名称'">
       	<ul id="tg" class="easyui-tree" data-options="lines:true"></ul>
   	</div>
	<div id="tb1">
	    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryNd()">查询</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="save()">保存</a>
	</div>
</body>
</html>