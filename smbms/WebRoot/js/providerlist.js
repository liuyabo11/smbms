$(function(){
	//对每个class为viewProvider的元素进行动作绑定（click）
	$(".viewProvider").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/viewProvider?proid="+obj.attr("providerid");
	});
	//对每个class为modifyProvider的元素进行动作绑定（click）
	$(".modifyProvider").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/modifyProvider?proid="+obj.attr("providerid");
	});
	//对每个class为deleteProvider的元素进行动作绑定（click）
	$(".deleteProvider").on("click",function(){
		var obj=$(this);
		
		if(confirm("您确定要删除供应商【"+obj.attr("providername")+"】吗?")){
			
			//ajax异步删除
			$.ajax({
				type:"GET",
				url:path+"/delProvider",
				data:{proid:obj.attr("providerid")},
				dataType:"json",
				success:function(data){
					if(data.result=="notexist"){
						alert("对不起，供应商【"+obj.attr("username")+"】不存在！");
					}else if(data.result=="delsuccess"){
						alert("删除成功！");
						//移除行
						obj.parents("tr").remove();
					}else if(data.result=="delfailed"){
						alert("删除失败！");
					}else if(data.result=="notdel"){
						alert("对不起，该供应商【"+obj.attr("username")+"】下有账单，不能删除！");
					}
				},
				error:function(data){
					alter("error");
				}
			});
		}
	});
});