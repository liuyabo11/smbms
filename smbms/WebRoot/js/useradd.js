var userCode=null;
var userName=null;
var userPassword=null;
var ruserPassword=null;
var phone=null;
var birthday=null;
var addBtn=null;


$(function(){
	userCode=$("#userCode");
	userName=$("#userName");
	userPassword=$("#userPassword");
	ruserPassword=$("#ruserPassword");
	phone=$("#phone");
	birthday=$("#birthday");
	addBtn=$("#add");

	//初始化的时候，要把所有的提示信息变为：* 以提示必填项
	userCode.next().html("*");
	userName.next().html("*");
	userPassword.next().html("*");
	ruserPassword.next().html("*");
	phone.next().html("*");
	birthday.next().html("*");
	
	/**
	 * 验证必填项用户编码 userCode
	 * 失焦/获焦
	 * jquery方法传递
	 */
	userCode.bind("blur",function(){
		//ajax后台验证---userCode是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:path+"/ucexist",//请求的url
			data:{userCode:userCode.val()},//请求时的参数
			dataType:"json",//ajax接口请求url 返回的数据类型
			success:function(data){//data:返回数据（json对象）
				if(data.userCode=="exist"){//帐号已存在 错误提示
					validateTip(userCode.next(),{"color":"red"},imgNo+" 用户账号已存在",false);
				}else{//帐号可以使用 正确提示
					validateTip(userCode.next(),{"color":"green"},imgYes+" 该账号可以使用",true);
				}
			},
			error:function(){//当访问的时候：404，500等非200的错误状态吗
				validateTip(userCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
			
		});
	}).bind("focus",function(){
		//显示友情提示
		validateTip(userCode.next(),{"color":"#6666666"},"* 用户编码是您登陆的帐号",false);
	}).focus();
	
	/**
	 * 验证用户名 userName
	 */
	userName.bind("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* 用户名必须是大于3小于10的字符！",false);
	}).bind("blur",function(){
		if(userName.val()!=null&&userName.val().length>3&&userName.val().length<10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 用户名输入不规范，请重新输入！",false);
		}
	});
	/**
	 * 验证用户密码 userPassword
	 */
	userPassword.bind("focus",function(){
		validateTip(userPassword.next(),{"color":"#666666"},"* 密码必须大于6位，小于20位！！",false);
	}).bind("blur",function(){
		if(userPassword.val()!=null&&userPassword.val().length>6&&userPassword.val().length<20){
			validateTip(userPassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userPassword.next(),{"color":"red"},imgNo+" 密码输入不规范，请重新输入！",false);
		}
	});
	/**
	 * 验证确认密码 ruserPassword
	 */
	ruserPassword.bind("focus",function(){
		validateTip(ruserPassword.next(),{"color":"#666666"},"* 请输入与上面一致的密码！",false);
	}).bind("blur",function(){
		if(ruserPassword.val()!=null&&ruserPassword.val().length>6&&ruserPassword.val().length<20&&ruserPassword.val()==userPassword.val()){
			validateTip(ruserPassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(ruserPassword.next(),{"color":"red"},imgNo+" 两次密码输入不一致，请重新输入！",false);
		}
	});
	/**
	 * 验证出生日期 birthday
	 */
	birthday.bind("focus",function(){
		validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框, 选择日期！！",false);
	}).bind("blur",function(){
		if(birthday.val()!=null&&birthday.val()!=""){
			validateTip(birthday.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(birthday.next(),{"color":"red"},imgNo+" 选择的日期不正确，请重新选择！",false);
		}
	});
	
	/**
	 * 验证手机号 phone
	 */
	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号！",false);
	}).bind("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo+" 您输入的手机号格式不正确，请重新输入！",false);
		}
	});
	
	
	/**
	 * 提交
	 */
	addBtn.bind("click",function(){
		//判断
		if(userCode.attr("validateStatus") != "true"){
			userCode.blur();
		}else if(userName.attr("validateStatus") != "true"){
			userName.blur();
		}else if(userPassword.attr("validateStatus") != "true"){
			userPassword.blur();
		}else if(ruserPassword.attr("validateStatus") != "true"){
			ruserPassword.blur();
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
		}else if(birthday.attr("validateStatus") != "true"){
			birthday.blur();
		}else{
			//提交
			if(confirm("是否确认提交数据？")){
				$("#userAdd").submit();
			}
		}
		
	});
});