$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewUser").bind("click",function(){
		//将被绑定的超链接a 转换成jquery对象，就可以使用jquery方法了
		var obj=$(this);
		window.location.href=path+"/view?uid="+obj.attr("userid");
	});
	
	//对每个class为modifyUser的元素进行动作绑定（click）
	$(".modifyUser").bind("click",function(){
		//将被绑定的超链接a 转换成jquery对象，就可以使用jquery方法了
		var obj=$(this);
		window.location.href=path+"/modify?uid="+obj.attr("userid");
	});
	
	//对每个class为deleteUser的元素进行动作绑定（click）
	$(".deleteUser").bind("click",function(){
		var obj=$(this);
		
		if(confirm("您确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/delete",
				data:{uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					//删除成功：移除删除行
					if(data.delResult=="true"){
						alert("删除成功！");
						//移除行
						obj.parents("tr").remove();
					}else if(data.delResult=="false"){
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult=="notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败！");
				}
			});
		}
	});
	
	
	
});