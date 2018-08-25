$(document).ready(function(){
	$("button").click(function(){
		var alinum = $('input[name="alinum"]').val();
		var ordernum = $('input[name="ordernum"]').val();
		var proid = $('input[name="proid"]').val();
		var reg = /^[0-9]*$/;
		if(!reg.test(alinum)){
			layer.msg('订单号错误',{icon:7,time:2000,shift:6});   
			return false;
		}
		
		$.post(order,{alinum:alinum,ordernum:ordernum,proid:proid},function(data){
			if(data.status = 1){
				layer.msg(data.info, {icon:1,time:2000},function(){
				  window.location.href = data.url;
				});   
			}else{
				layer.msg(data.info,{icon:7,time:2000,shift:6});   
				return false;
			}
		},'json');
		
	});
});