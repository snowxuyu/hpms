<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>年度计划执行</title>
<style type="text/css">
	*{margin:0;padding:0;font-size:13px;color:#333333;font-family:Arial,"微软雅黑";}
	a{text-decoration:none;}
	li{list-style:none;}
	h1{font-weight:normal;}
	b{font-weight:normal;}
	img{border:none;}
	.tableRenliTitle{height:84px;width:900px;margin:0 auto;background-image:url(<%=basePath %>img/titleBeijing_01.png); background-position:center center;}
	.tableRenliText{margin-left:300px;padding-top:30px;}
	.tableRenliText a{font-size:26px;color:#e4f1fb;}
	.danweiRenli{color:#e4f1fb;float:right;margin-right:60px;}
	.tableRenliBox{width:900px;margin:0 auto;margin-top:10px;}
	.tableconterBox table{border:#aac1de 2px solid;}
	.tableconterBox td{border:#aac1de 1px solid;line-height:26px;text-align:center;}
	.jixiaoIcon a{display:block;}
	.jixiaoIcon01{background-repeat:no-repeat;background-position:bottom center;padding-bottom:5px;}
	.jixiaoIcon01a{background-image:url(<%=basePath %>/img/icon_01.png);}
	.jixiaoIcon01b{background-image:url(<%=basePath %>/img/icon_02.png);}
	.tableconterBox .renlixian td{border-left:#aac1de 0.5px solid;border-right:#aac1de 2px solid;}
	.renlixianTitle td{border:#aac1de 1px solid;line-height:30px;}
	.renlixian .renlixianNo{border:none;}
	.tableconterBox .renlixianRight{border-Right:#aac1de 2px solid;}
	.tableconterBox .renlixianBottom{border-bottom:#aac1de 2px solid;}
	.beizhuRenli{margin:5px 0;}
	.beizhuRenli a{display:block;float:left;color:#0d4e8c;}
	.beizhuRenliLeft{margin-left:150px;}
</style>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<c:if test="${(not empty modify)&&modify=='true' }">
<script src="<%=basePath %>js/jquery.json.min.js"></script>
<script type="text/javascript">
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            bindGridEvent(editIndex);
            if ($('#grid').datagrid('validateRow', editIndex)){
                 $('#grid').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickRow(index){
            if (editIndex != index){
                if (endEditing()){
                    $('#grid').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    editIndex = index;
                    bindGridEvent(editIndex);
                } else {
                    $('#grid').datagrid('selectRow', editIndex);
                }
            }
           
        }
        function append(){
            if (endEditing()){
                $('#grid').datagrid('appendRow',{status:'P'});
                editIndex = $('#grid').datagrid('getRows').length-1;
                $('#grid').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
        function removeit(){
            if (editIndex == undefined){return}
            $('#grid').datagrid('cancelEdit', editIndex)
                    .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
        function accept(){
            if (endEditing()){
            	
                $('#grid').datagrid('acceptChanges');
            }
        }
        function reject(){
            $('#grid').datagrid('rejectChanges');
            editIndex = undefined;
        }
        function getChanges(){
            var rows = $('#grid').datagrid('getChanges');
            alert(rows.length+' rows are changed!');
        }
</script>
<script type="text/javascript">
var lsh = null;
var ysbm = null;
$(function(){
	//初始化class为easyui-window的div不显示
	$(".easyui-window").window("close");  
});

function clickHandler(id ,year){
	$("#nd").textbox("setValue", year);
	$.ajax({
		type:'POST',
		url:'<%=basePath %>jjfp/HpysRlcbysAction/queryByXmbm',
		data:{xmbm:id,year:year},
		success:function(map){
			var item = map.rlcbdy;
			$("#xmmc").textbox("setValue", item.xmmc);
			
			if(item.sjzt == "1"){
				$("#sjzt").textbox("setValue","月度" );
			}else if(item.sjzt == "2"){
				$("#sjzt").textbox("setValue","季度" );
			}else{
				$("#sjzt").textbox("setValue",null );
			}
			$('#grid').datagrid({
				onClickRow:onClickRow
			});
			$('#grid').datagrid('loadData',map.list2);
			$("#window").window("open");
		}
	});
}

function closeDiv(){
	$(".easyui-window").window("close"); 
}

function save(){
	endEditing();
	var rows = $('#grid').datagrid("getRows")//获取当前的数据行
	if(rows[0].lsh != null){
		rows = $.toJSON(rows);
		$.ajax({
			type:'POST',
			url:'<%=basePath %>jjfp/HpysRlcbysAction/updateRlcbzx',
			data:{rows:rows},
			success:function(msg){
				if(msg == "1"){
					$.messager.alert('提示','修改成功！');
					closeDiv();
				}else{
					$.messager.alert('警告','请填写实际值和执行率！');
				}
			}
		});
	}else{
		rows = $.toJSON(rows);
		$.ajax({
			type:'POST',
			url:'<%=basePath %>jjfp/HpysRlcbysAction/insertRlcbzx',
			data:{rows:rows},
			success:function(msg){
				if(msg == "1"){
					$.messager.alert('提示','新增成功！');
					closeDiv();
				}else{
					$.messager.alert('警告','请填写实际值和执行率！');
				}
			}
		});
	}
}

function bindGridEvent(indexRow){
	try{
		var zxlValue;
		var yszValue;
		var sjzValue;
		var objGrid = $("#grid"); 
		var ysz= objGrid.datagrid('getEditor', {index:indexRow,field:'ysz'});           
	    var sjz= objGrid.datagrid('getEditor', {index:indexRow,field:'sjz'}); 
	    var zxl= objGrid.datagrid('getEditor', {index:indexRow,field:'zxl'});
	    
	    yszValue= $(ysz.target).val();  
	    sjzValue= $(sjz.target).val();
	    if(sjzValue == null || sjzValue == 0){
	    	 zxlValue = 0;
	    }else{
	    	 zxlValue = yszValue/sjzValue*100;
	    }
	    $(zxl.target).numberbox("setValue",zxlValue);
	}catch(e){
		alert(e);
	}
}

</script>
</c:if>
</head>
<body>
<div class="tableRenliTitle"><div class="tableRenliText"><a >${year }年人力总成本预算执行表</a></div>
<div class="danweiRenli">（单位：万元）</div>
</div>
<div class="tableRenliBox">
<div class="tableconterBox">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="renlixianTitle">
    <td class="renlixianRight" colspan="2">预算项目</td>
    <td width="7%">1月</td>
    <td width="7%">2月</td>
    <td class="renlixianRight" width="8%">3月</td>
    <td width="7%">4月</td>
    <td width="7%">5月</td>
    <td class="renlixianRight" width="7%">6月</td>
    <td width="7%">7月</td>
    <td width="7%">8月</td>
    <td class="renlixianRight" width="7%">9月</td>
    <td width="7%">10月</td>
    <td width="7%">11月</td>
    <td width="7%">12月</td>
    <c:if test="${(not empty modify)&&modify=='true' }">
     <td width="7%">操作</td>
    </c:if>
  </tr> 
  <c:forEach var="item" items="${list}" varStatus="status" > 
	  <tr>
	    <td class="jixiaoIcon renlixianBottom" width="2%" rowspan="${fn:length(item.children) }"><a class="jixiaoIcon01 jixiaoIcon01a">${item.xmmc}</a></td>
	    <td class="renlixianRight" >${item.children[0].xmmc }</td> 
	    <c:forEach var="val" items="${item.children[0].rlcbyses}">
	        <td <c:if test="${item.children[0].sjzt=='2' }"> colspan="3"</c:if> >
	            <c:choose>
	              <c:when test="${!(empty val.sjz)&&val.sjz!=0 }">
	                <a style="color: red;"  title="计划金额：${val.ysz}</br>实际金额：${val.sjz }</br>执行率：${val.zxl}%" class="easyui-tooltip"> ${val.sjz }</a>
	              </c:when>
	              <c:otherwise>
	               <a title="计划金额：${val.ysz}</br>实际金额：${val.sjz }</br>执行率：${val.zxl}%" class="easyui-tooltip"> ${val.ysz }</a>
	              </c:otherwise>
	            </c:choose>
	        </td> 
	    </c:forEach>
	    <c:if test="${(not empty modify)&&modify=='true' }">
	     <td><button onclick="clickHandler('${item.children[0].xmbm}', '${year }')">操作</button></td>
	    </c:if>
	  </tr>
	  <c:forEach var="child" items="${item.children}" varStatus="cs" begin="1" > 
	   <tr class="renlixian">
           <td class="renlixianRight">${child.xmmc}</td>
           <c:forEach var="val" items="${child.rlcbyses}">
               <td <c:if test="${child.sjzt=='2' }"> colspan="3"</c:if> >
                   <c:choose>
		              <c:when test="${!(empty val.sjz)&&val.sjz!=0 }">
		                <a style="color: red;"  title="计划金额：${val.ysz}</br>实际金额：${val.sjz }</br>执行率：${val.zxl}%" class="easyui-tooltip"> ${val.sjz }</a>
		              </c:when>
		              <c:otherwise>
		               <a title="计划金额：${val.ysz}</br>实际金额：${val.sjz }</br>执行率：${val.zxl}%" class="easyui-tooltip"> ${val.ysz }</a>
		              </c:otherwise>
	              </c:choose>
               </td> 
           </c:forEach>
	       <c:if test="${(not empty modify)&&modify=='true' }">
	        <td><button onclick="clickHandler('${child.xmbm}' , '${year }')">操作</button></td>
	       </c:if>
       </tr>
	  </c:forEach>
  </c:forEach>
</table>
<div id="window" class="easyui-window win" title="奖金设置" style="width:560px;height:auto">
	<form id="form" method="post">
		<table cellpadding="0"  cellspacing="0">
			<tr>
				<td>项目名称：</td>
				<td><input id="xmmc" name="xmmc" class="easyui-textbox"  data-options="width:100" readOnly="readOnly"/></td>
				<td>年度：</td>
				<td><input id="nd" name="nd" class="easyui-textbox" data-options="width:50" readOnly="readOnly"/></td>
				<td>时间状态:</td>
				<td><input id="sjzt" name="sjzt" class="easyui-textbox" data-options="width:50" readOnly="readOnly"/></td>
			</tr>
		</table>
	</form>
		<table id="grid" class="easyui-datagrid"  data-options=" fitColumns: true , width:420,
	                          singleSelect:true , rownumbers:true, 
	                          closable:true" style="height:auto">
	            <thead>
	            	<tr>
	            		<th data-options="field:'ysz',width:100" editor="{type:'numberbox',options:{precision:2}}" align="center">预算值(万元)</th>
	            		<th data-options="field:'sjz',width:100" editor="{type:'numberbox',options:{precision:2}}" align="center" >实际值（万元）</th>
	            		<th data-options="field:'zxl',width:100" editor="{type:'numberbox',options:{precision:2}}" align="center">执行率(%)</th>
	            	</tr>
	            </thead>
		</table>
		<table cellpadding="0"  cellspacing="0">
			<tr>
				<td><button onclick="save()">保存</button></td>
				<td><button onclick="closeDiv()">取消</button></td>
			</tr>
		</table>
</div>
<c:choose>
<c:when test="${not empty zxl }">
<div class="beizhuRenli"><a >全年已执行数：</a><a style="color: red">${zxl.zsj }</a>
<a >万元（执行率</a><a style="color: red">${zxl.zxl*100 } </a><a >%)</a><a class="beizhuRenliLeft">全年未执行数：<c:choose><c:when test="${zxl.zys-zxl.zsj<=0 }">0</c:when><c:otherwise>${(zxl.zys-zxl.zsj)}</c:otherwise></c:choose>万元 </a>
<a class="beizhuRenliLeft">全年预算数：${zxl.zys }万元</a></div>
</c:when>
<c:otherwise>
<div class="beizhuRenli"><a >全年已执行数：</a><a style="color: red">0</a>
<a >万元（执行率</a><a style="color: red">0 </a><a >%)</a><a class="beizhuRenliLeft">全年未执行数：0万元 </a>
<a class="beizhuRenliLeft">全年预算数：0万元</a></div>
</c:otherwise>
</c:choose>

</body>
</html>