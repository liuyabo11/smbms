var oldpassword=null;
var newpassword=null;
var rnewpassword=null;
var saveBtn=null;

$(function(){
	oldpassword=$("#oldpassword");
	newpassword=$("#newpassword");
	rnewpassword=$("#rnewpassword");
	saveBtn=$("#save");
	
	
	oldpassword.next().html("*");
	newpassword.next().html("*");
	rnewpassword.next().html("*");
	/**
	 * 验证旧密码
	 */
	oldpassword.on("blur",function(){
		$.ajax({
			type:"GET",
			url:path+"/pwdmodify",
			data:{oldpassword:oldpassword.val()},
			dataType:"json",
			success:function(data){
				
				if(data.result=="true"){//旧密码正确
					validateTip(oldpassword.next(),{"color":"green"},imgYes,true);
				}else if(data.result=="false"){//旧密码不正确
					validateTip(oldpassword.next(),{"color":"red"},imgNo+" 旧密码输入不正确！",false);
				}else if(data.result=="error"){//当前用户session过期，请重新登录
					validateTip(oldpassword.next(),{"color":"red"},imgNo+" 当前用户session过期，请重新登录！",false);
				}
				
				
			},
			error:function(data){
				//请求出错
				validateTip(oldpassword.next(),{"color":"red"},imgNo+" 请求错误！",false);
			}
		});
	}).on("focus",function(){
		validateTip(oldpassword.next(),{"color":"#666666"},"* 请输入旧密码！",false);
	});
	/**
	 * 验证新密码
	 */
	newpassword.on("focus",function(){
		validateTip(newpassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20的字符！",false);
	}).on("blur",function(){
		if(newpassword.val()!=null && newpassword.val().length>6 && newpassword.val().length<20){
			validateTip(newpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(newpassword.next(),{"color":"red"},imgNo+" 您输入的密码不符合规范，请重新输入！",false);
		}
	});
	
	/**
	 * 验证确认密码
	 */
	rnewpassword.on("focus",function(){
		validateTip(rnewpassword.next(),{"color":"#666666"},"* 请输入与上面相同的密码！",false);
	}).on("blur",function(){
		if(rnewpassword.val()!=null && rnewpassword.val().length>6 && rnewpassword.val().length<20 && rnewpassword.val()==newpassword.val()){
			validateTip(rnewpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rnewpassword.next(),{"color":"red"},imgNo+" 两次密码输入不一致，请重新输入！",false);
		}
	});
	
	
	/**
	 * 提交
	 */
	saveBtn.on("click",function(){
		oldpassword.blur();
		newpassword.blur();
		rnewpassword.blur();
		
		if(oldpassword.attr("validateStatus")=="true"
			&& newpassword.attr("validateStatus")=="true"
			&& rnewpassword.attr("validateStatus")=="true"){
			if(confirm("确定要修改密码吗？")){
				$("#pwdForm").submit();
			}
		}
	});
	
});