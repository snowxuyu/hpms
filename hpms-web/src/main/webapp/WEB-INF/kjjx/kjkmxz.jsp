<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科教按科室明细</title>
<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
<script type="text/javascript">
/* var createGridHeaderContextMenu = function(e, field) {  
    e.preventDefault();  
    var grid = $(this);  
    var headerContextMenu = this.headerContextMenu;
    if (!headerContextMenu) {  
        var tmenu = $('<div style="width:100px;"></div>').appendTo('body');  
        var fields = grid.datagrid('getColumnFields');  
        for ( var i = 0; i < fields.length; i++) {  
            var fildOption = grid.datagrid('getColumnOption', fields[i]);  
            if (!fildOption.hidden) {  
                $('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
            } else {  
                $('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
            }  
        }  
        headerContextMenu = this.headerContextMenu = tmenu.menu({
            onClick : function(item) {
                var field = $(item.target).attr('field');  
                if (item.iconCls == 'icon-ok') {  
                    grid.datagrid('hideColumn', field);  
                    $(this).menu('setIcon', {  
                        target : item.target,  
                        iconCls : 'icon-empty'  
                    });  
                } else {  
                    grid.datagrid('showColumn', field);  
                    $(this).menu('setIcon', {  
                        target : item.target,  
                        iconCls : 'icon-ok'  
                    });  
                }  
            }  
        });  
    }  
    headerContextMenu.menu('show', {  
        left : e.pageX,  
        top : e.pageY  
    });  
};  
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu; */
var _val;
var myArr = ['a','b','c','d','e','f','g','h','i','j','k','l'];
	$(function(){
		
		$('#cc').combobox({
            width:'400',
            panelHeight:'auto',
            editable:false,
            multiple:true,
            formatter: function (row) {
                var opts = $(this).combobox('options');
                return '<input type="checkbox" class="combobox-checkbox" checked="false"> &emsp;' + row[opts.textField]
            },
            onLoadSuccess: function () {
                var opts = $(this).combobox('options');
                var target = this;
                var values = $(target).combobox('getValues');
                
                $(target).combobox('setValues','a,b,c,d,e,f,g,h,i,j,k,l');
            },
            onSelect: function (row) {
                var opts = $(this).combobox('options');
                var el = opts.finder.getEl(this, row[opts.valueField]);
                el.find('input.combobox-checkbox')._propAttr('checked', true);
                $('#dg').datagrid('showColumn', row[opts.valueField]);
                changeRow();
            },
            onUnselect: function (row) {
                var opts = $(this).combobox('options');
                var el = opts.finder.getEl(this, row[opts.valueField]);
                el.find('input.combobox-checkbox')._propAttr('checked', false);
                $('#dg').datagrid('hideColumn', row[opts.valueField]);
                changeRow();
            }
        });
		$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false});
        $('.easyui-window').window('close');
   		$('#kslb').combobox({
   			data:[{
   			    id:"A",
   			    text:"临床科室"
   			},{
   			    id:"B",
   			    text:"医技科室"
   			},{
   			    id:"C",
   			    text:"临床护理"
   			},{
   			    id:"D",
   			    text:"行政管理科室"
   			}],
   		    valueField:'id',
   		    textField:'text'
   		});
   		
   		query();
	});
	
	function query(){
		_val = $("#cc").combobox('getValues');
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
			method:'post',
			onLoadSuccess:function() {
				$.each(myArr,function(i,n) {
					if ($.inArray(n,_val)<0) {
						$('#dg').datagrid('hideColumn', n);
					}
				})
				changeRow();
			}
		});
 		
	}
	
	function changeRow() {
		var d = $(".datagrid-view2").find(".datagrid-row");
		$.each(d,function(i,n) {
			var v = 0;
			var last = $(this).find("td").last();
			var e = $(this).find("td").filter(function(index) {
				  return $(this).attr("field").length == 1 && $(this).css('display') != "none";
			});
			$.each(e,function(i2,n2) {
				//alert($(n2).html());
				v += Number($(n2).find("div").text());
			})
			$(last).find("div").text(v.toFixed(2));
		})
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
			筛选：
			<select id="cc">
				<option value="a">A科技成果</option>
				<option value="b">B发表论文</option>
				<option value="c">C职务专利</option>
				<option value="d">D科研项目</option>
				<option value="e">E三新项目</option>
				<option value="f">F学术兼职</option>
				<option value="g">G院内培训</option>
				<option value="h">H三基考试</option>
				<option value="i">I继续教育</option>
				<option value="j">J带教情况</option>
				<option value="k">k重点学科</option>
				<option value="l">L人才培养</option>
			</select>
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
						<th data-options="field:'g',width:90">G院内培训</th>
						<th data-options="field:'h',width:90">H三基考试</th>
						<th data-options="field:'i',width:90">I继续教育</th>
						<th data-options="field:'j',width:90">J带教情况</th>
						<th data-options="field:'k',width:90">k重点学科</th>
						<th data-options="field:'l',width:90">L人才培养</th>
						<th data-options="field:'hj',width:60,hidden:true,formatter: function(value,row,index){
							if (value == null) {
								return '0.00';
							}
				    		return value.toFixed(2);
						}">合计</th>
						<th data-options="field:'sxhj',width:60,formatter: function(value,row,index){
				    		return row.hj;
						}">筛选合计</th>
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