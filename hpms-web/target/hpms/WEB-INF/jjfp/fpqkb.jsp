<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>分配情况表</title>
		<jsp:include page="/WEB-INF/common/base.jsp"></jsp:include>
		<style>
			.detail,.detailJJ,.detailJJ2,.detailJJ3,.detailBF,.detailD {
				color: blue;
				cursor: pointer;
			}
		</style>
		<script>
			function selectOne() {
				var value = $('#cc').combobox('getValue');
				var words = value.split(",");
				$("#data-box").panel({'title':words[0]+'年'+words[1]+'月安亭医院标化工作量汇总表'});
					$("#data").datagrid({
				            url:"qmys/cx/ny",
				            rownumbers:true,
				            pagination:true,
				            pageList:[50,80,100,130,150],
				            pageSize:100,
				            fit:true,
				            fitColumns:true,
				            singleSelect:true,
				            striped: true,
				            queryParams: {
				            	nd:words[0],
				            	yd:words[1],
				            	q:1
				            },
				            columns:[[
							    {field:'kslbmc',title:'科室类别',width:200}, 
							    {field:'yjks',title:'一级科室',width:200},    
							    {field:'ejks',title:'二级科室',width:200},
							    {field:'fpjj',title:'奖金',width:100,
							    	formatter: function(value,row,index){
							    		if (value == null) {
							    			return "";
							    		}
							    		value = Number(value).toFixed(2);
							    		if (row.kslb == "C" && row.sfbf == "1") {
							    			return "<span class='detailJJ' data-detail='"+words[0]+","+words[1]+","+row.ksbh+","+row.ejks+"'>"+value+"</span>";
							    		} else if (row.kslb == "D") {
							    			return "<span class='detailJJ2' data-detail='"+words[0]+","+words[1]+","+row.ksbh+","+row.ejks+"'>"+value+"</span>";
							    		} else {
							    			return "<span class='detailJJ3' data-detail='"+words[0]+","+words[1]+","+(typeof(row.ksbh) == "undefined"?"null":row.ksbh)+","+(typeof(row.ejks) == "undefined"?"null":row.ejks)+"'>"+value+"</span>";
							    		}
									}
							    },
							    {field:'hj',title:'工作总量',width:160,
				                    formatter: function(value,row,index){
				                        if (value == null) {
				                            return "";
				                        }
				                        value = Number(value).toFixed(2);
				                        if (row.kslb == "C" && row.sfbf == "1") {
				                            return "<span class='detailBF' data-detail='"+words[0]+","+words[1]+","+row.ksbh+","+row.ejks+"'>"+value+"</span>";
				                        } else if (row.kslb == "D") {
				                            return "<span class='detailD'>"+value+"</span>";
				                        }
				                        if (typeof(row.ksbh) == "undefined") {
				                        	return "<span class='detailJJ3' data-detail='"+words[0]+","+words[1]+","+(typeof(row.ksbh) == "undefined"?"null":row.ksbh)+","+(typeof(row.ejks) == "undefined"?"null":row.ejks)+"'>"+value+"</span>";
				                        }
				                        return "<span class='detail' data-detail='"+words[0]+","+words[1]+","+row.ksbh+",0,"+row.ejks+"'>"+value+"</span>";
				                    }
				                }
							    ]],
							onLoadSuccess: function() {
								$(".detail").click(function() {
									window.open("<%=basePath %>qmys/cx/ny/fp?detail=" + $(this).attr("data-detail"),'newwin');
								});
								$(".detailJJ").click(function() {
									window.open("<%=basePath %>qmys/jj/gr?detail=" + $(this).attr("data-detail"),'newwin');
								});
								$(".detailJJ2").click(function() {
									window.open("<%=basePath %>qmys/jj/gr2?detail=" + $(this).attr("data-detail"),'newwin');
								});
								$(".detailJJ3").click(function() {
									window.open("<%=basePath %>qmys/queryksjjqk?detail=" + $(this).attr("data-detail"),'newwin');
								});
								$(".detailBF").click(function() {
									window.open("<%=basePath %>qmys/hlbhgzlAction?detail=" + $(this).attr("data-detail"),'newwin');
								});
							},
							showFooter:true
				        });
				        var pager = $('#data').datagrid('getPager');    // 得到datagrid的pager对象  
				        pager.pagination({    
				            buttons:[{
				                iconCls:'icon-download',    
				                handler:function(){
				                	var url="<%=basePath %>qmys/cx/ny/exportExcel?nd="+words[0]+"&"+"yd="+words[1];
				        			window.open(url,'ExcelWin','z-look=1');
				        			return false; 
				                }    
				            }]   
				        });
			}
			$(function(){
				var _date = new Date();
		       	var _year = _date.getFullYear();
		       	var _month = _date.getMonth() + 1;
		       	
		       	var to_month = _year + "," +  _month;
				
				var queryParams,nd,yd;
				//获取数据表格参数方法
		        function getParams() {
		        	queryParams = $("#data").datagrid("options").queryParams;
				}
		    	//查询
		       	function query() {
		       		getParams();
		    		queryParams["nd"] = nd;
		    		queryParams["yd"] = yd;
		    		$("#data").datagrid("load");
		       	}
				$("#cc").combobox({
					editable: false,
					onLoadSuccess: function () { //数据加载完毕事件
                        var data = $('#cc').combobox('getData');
                        if (data.length > 0) {
                            $("#cc").combobox('select', to_month);
			            	selectOne();
                        }
                    },
                    onSelect:function(){
                    	selectOne();
					 }
				});
			})
		</script>
	</head>
<body class="easyui-layout">
	<div title="" data-options="region:'north'" style="padding: 20px;">
		时间选择：
		<select id="cc" style="width:120px;">  
			<c:forEach var="item" items="${listFPB }">
				<optgroup label="${item.nd }年">
				<% for (int i = 1;i<=12;i++) {%>
					<option value="${item.nd },<%=i %>">${item.nd }年<%=i %>月</option>
				<% } %> 
				</optgroup>
			</c:forEach>
		</select>
	</div>
	<div title="" data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">
            <div id="data-box" title="安亭医院标化工作量汇总表" data-options="region:'center'">
				<table id="data"></table>
			</div>
        </div>   
	</div>
</body>

	<%-- <table id="rlcbysTb">
		<caption>安亭医院月度奖金分配情况表</caption>
		<thead>
			<tr>
				<c:forEach var="item" items="${listFPB }">
					<th>${item.nd }年度</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 1;i<=12;i++) {%>
				<tr>
					<c:forEach var="item" items="${listFPB }">
						<td class="detail" data-detail="${item.nd },<%=i %>">${item.nd }.<%=i %></td>
					</c:forEach>
				</tr>
			<% } %>
		</tbody>
	</table> --%>

</html>