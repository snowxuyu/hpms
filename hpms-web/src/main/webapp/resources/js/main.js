$(function() {
	$('.easyui-window').window({modal:true,resizable:false,minimizable:false,maximizable:false,collapsible:false,inline:true,top:10});
   	$('.easyui-window').window('close');
   	$(".cancel").click(function() {
    	$(this).parents(".win").window("close");
    });
});