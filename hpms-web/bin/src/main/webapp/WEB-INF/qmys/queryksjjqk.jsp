<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String at_hpms = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/at_hpms/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% Object nd = request.getAttribute("nd"); %>
<% Object yd = request.getAttribute("yd"); %>
<% Object ksnm = request.getAttribute("ksnm"); %>
<% Object ksmc = request.getAttribute("ksmc"); %>
<!DOCTYPE html>
<html>
<head>
	<style>
		A {text-decoration: NONE} 
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>奖金查询</title>
	<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
	<style type="text/css">
	.detailBHL, .detailKS, .detailGR {
		cursor: pointer;
		color: blue;
	}
	.datagrid .datagrid-pager {
		display: none;
	}
	</style>
	<script type="text/javascript">
		//设置额外查询参数
		var searchParams;  
	
		$(function(){
			$("#dg").datagrid({
				url:'qmys/queryksjjqk/find',
				queryParams: {
					ksnm:<%=ksnm%>,
					nd:<%=nd%>,
					yd:<%=yd%>
				},
		        pagination:true,
		        //fit:true,
		        fitColumns:true,
		        pageList:[50,80,100,130,150],
	            pageSize:100,
	            columns:[[
	      			    {field:'lsh',hidden:true},
						{field:'ksnm',hidden:true},
	      			    {field:'ksmc',title:'科室名称',width:150},  
	      			  	{field:'bhgzl',title:'标化工作量',width:150,
	      			    	formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			return "";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailBHL' data-detail='<%=nd%>,<%=yd%>,"+row.ksnm+",0,"+row.ksmc+"'>"+value+"</span>";
	      					}
	      			    },
	      				{field:'dj',title:'单价',width:120},
	      				{field:'cl',title:'常量',width:120},
	      				{field:'pjxs',title:'综合评价系数',width:150,
	      					formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			return "";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return '<a href="javascript:detailZhpjxs();">' + value + '</a>';
	      					}
	      				},
	      				{field:'myd',title:'满意度',width:120},
	      				{field:'kszxjj',title:'科室专项奖金',width:100,
	      					formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			value = "0.0";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailKS' data-detail='<%=nd%>,<%=yd%>,"+(typeof(row.lsh) == "undefined"?"null":row.ksnm)+","+(typeof(row.lsh) == "undefined"?"null":row.ksmc)+"'>"+value+"</span>";
	      					}
	      			  	},
	      			    {field:'grzxjj',title:'个人专项奖金',width:100,
		      			  	formatter: function(value,row,index){
	      			    		if (value == null) {
	      			    			value = "0.0";
	      			    		}
	      			    		value = Number(value).toFixed(2);
	      			    		return "<span class='detailGR' data-detail='<%=nd%>,<%=yd%>,"+(typeof(row.lsh) == "undefined"?"null":row.ksnm)+","+(typeof(row.lsh) == "undefined"?"null":row.ksmc)+"'>"+value+"</span>";
	      					}
	      			    },
	      				{field:'fpjj',title:'实发奖金',width:150}
	      			    ]],
	      			onLoadSuccess: function() {
	      				$(".detailBHL").click(function() {
	      					window.location.href="<%=basePath %>qmys/cx/ny/fp?detail=" + $(this).attr("data-detail");
	      				});
	      				$(".detailKS").click(function() {
							window.location.href="<%=basePath %>qmys/cx/zx/ks?detail=" + $(this).attr("data-detail");
						});
	      				$(".detailGR").click(function() {
							window.location.href="<%=basePath %>qmys/cx/zx/gr?detail=" + $(this).attr("data-detail");
						});
	      			}
			});
			
			//查询实现
			function query(){
				searchParams = $("#dg").datagrid("options").queryParams; 
				$("#dg").datagrid("reload");
			}
		});
		
		function detailZx(){
			var url = encodeURI('<%=basePath %>jjfp/querygrzxjc?ksnm=<%=ksnm%>&ksmc=<%=ksmc%>&nd=<%=nd%>&yd=<%=yd%>');
	   		window.location.href = url;
		}
		function detailZhpjxs(){
			window.location.href="<%=at_hpms %>index_right.jsp?u=superadmin&p=1qaz-pl,&m=com@rongda@view@bpe@BPE_JGJXSCModule.swf";  
		}
	</script>
</head>
<body class="easyui-layout">
	<div title="安亭医院${ksmc }${nd }年${yd }月度奖金分配情况表" data-options="region:'north'" style="height: 180px;">
		<table id="dg"></table>
	</div>
	<div title="" data-options="region:'center'">
			<div id="tt" class="easyui-tabs" data-options="fit:true">
				<div title="标化工作量" style="padding: 20px;">
					<div id="con1" style="min-width:600px;"></div>
				</div>
				<div title="综合评价系数" style="padding: 20px;">
					<div id="con2" style="width:95%"></div>
				</div>
				<div title="满意度" style="padding: 20px;">
					<div id="con3" style="width:95%"></div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript" src="js/highcharts.js"></script>

<script type="text/javascript">
$(function () {
	var opt1 = {
        chart: {
            //将报表对象渲染到层上
            renderTo: 'con1'
        },
        credits: {
            enabled: false
        },
        title: {
            text: '标化工作量情况图表展示',
            x: -20
        },
        subtitle: {
            //text: 'Source: WorldClimate.com',
            //x: -20
        },
        xAxis: {
        	categories: []
            //categories: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
        },
        yAxis: {
        	title: {
                text: ''
            },
            labels: {
                format: '{value} 元'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '元'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '标化工作量',
            data: []
            //data: [51938.36,44073.44,61761.08,60868.45,60115.21,58687.93,64595.79,59815.01,57749.01,62602.83,55726.48,60594.15]
        }]
    };
	var opt2 = {
	        chart: {
	            //将报表对象渲染到层上
	            renderTo: 'con2'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: '综合评价系数情况图表展示',
	            x: -20
	        },
	        subtitle: {
	            //text: 'Source: WorldClimate.com',
	            //x: -20
	        },
	        xAxis: {
	        	categories: []
	            //categories: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	        },
	        yAxis: {
	        	title: {
	                text: ''
	            },
	            labels: {
	                format: '{value}'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '综合评价系数',
	            data: []
	            //data: [51938.36,44073.44,61761.08,60868.45,60115.21,58687.93,64595.79,59815.01,57749.01,62602.83,55726.48,60594.15]
	        }]
	    };
	var opt3 = {
	        chart: {
	            //将报表对象渲染到层上
	            renderTo: 'con3'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: '满意度情况图表展示',
	            x: -20
	        },
	        subtitle: {
	            //text: 'Source: WorldClimate.com',
	            //x: -20
	        },
	        xAxis: {
	        	categories: []
	            //categories: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	        },
	        yAxis: {
	        	title: {
	                text: ''
	            },
	            labels: {
	                format: '{value}'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '满意度',
	            data: []
	            //data: [51938.36,44073.44,61761.08,60868.45,60115.21,58687.93,64595.79,59815.01,57749.01,62602.83,55726.48,60594.15]
	        }]
	    };
	$.post("qmys/queryksjjqk/charts", {ksnm:<%=ksnm %>, nd:<%=nd %>, yd:<%=yd %>}, function(data){
		var i,len=data.length;
		for( i=0;i<len;i++){
			opt1.xAxis.categories[i] = data[i].nd+"年"+data[i].yd+"月";
			opt1.series[0].data[i] = data[i].bhgzl;
			
			opt2.xAxis.categories[i] = data[i].nd+"年"+data[i].yd+"月";
			opt2.series[0].data[i] = data[i].pjxs;
			
			opt3.xAxis.categories[i] = data[i].nd+"年"+data[i].yd+"月";
			opt3.series[0].data[i] = data[i].myd;
		}
		var chart1 = new Highcharts.Chart(opt1);
		var chart2 = new Highcharts.Chart(opt2);
		var chart3 = new Highcharts.Chart(opt3);
	});
});
	
</script>
</html>