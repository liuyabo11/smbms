var userName=null;
var birthday=null;
var phone=null;
var saveBtn=null;

$(function(){
	userName=$("#userName");
	birthday=$("#birthday");
	phone=$("#phone");
	saveBtn=$("#save");
	
	
	userName.next().html("*");
	birthday.next().html("*");
	phone.next().html("*");
	
	/**
	 * 验证userName
	 */
	userName.on("focus",function(){
		validateTip(userName.next(),{"clolor":"#666666"},"* 用户名的长度必须是大于3小于10的字符",false);
	}).on("blur",function(){
		if(userName.val()!=null && userName.val().length>3 && userName.val().length<10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
			
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入！",false);
		}
	});
	
	/**
	 * 验证birthday
	 */
	birthday.on("focus",function(){
		validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框，选择日期！",false);
	}).on("blur",function(){
		if(birthday.val()!=null && birthday.val()!=""){
			validateTip(birthday.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(birthday.next(),{"color":"red"},imgNo+" 选择的日期不正确，请重新选择！",false);
		}
	});
	
	/**
	 * 验证phone
	 */
	
	phone.on("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号！",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo+" 您输入的手机号格式不正确，请重新输入！",false);
		}
	});
	
	
	/**
	 * 提交验证
	 */
	
	saveBtn.on("click",function(){
		userName.blur();
		birthday.blur();
		phone.blur();
		
		if(userName.attr("validateStatus")=="true"
			&& birthday.attr("validateStatus")=="true"
			&& phone.attr("validateStatus")=="true"){
			if(confirm("是否确认要提交数据？")){
				$("#modifyForm").submit();
			}
		}
	});
	
	
	
});