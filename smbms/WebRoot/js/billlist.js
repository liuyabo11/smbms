$(function(){
	//对每个class为viewBill的元素进行动作绑定（click）
	$(".viewBill").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/viewBill?billid="+obj.attr("billid");
	});
	//对每个class为modifyProvider的元素进行动作绑定（click）
	$(".modifyBill").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/modifyBill?billid="+obj.attr("billid");
	});
	//对每个class为deleteProvider的元素进行动作绑定（click）
	$(".deleteBill").on("click",function(){
		var obj=$(this);
		
		if(confirm("您确定要删除本条账单吗？")){
			$.ajax({
				type:"GET",
				url:path+"/delBill",
				data:{billid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.result=="true"){
						alert("删除成功！");
						//移除行
						obj.parents("tr").remove();
					}else if(data.result=="false"){
						alert("删除失败！");
					}else if(data.result=="notexist"){
						alert("该账单不存在！");
					}
				},
				error:function(data){
					alert("error");
				}
			});
		}
			
	});
});