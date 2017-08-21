var proCode=null;
var proName=null;
var proContact=null;
var proPhone=null;
var addBtn=null;

$(function(){
	proCode=$("#proCode");
	proName=$("#proName");
	proContact=$("#proContact");
	proPhone=$("#proPhone");
	addBtn=$("#add");
	
	proCode.next().html("*");
	proName.next().html("*");
	proContact.next().html("*");
	proPhone.next().html("*");
	
	
	/**
	 * 验证供应商编码
	 */
	proCode.on("focus",function(){
		validateTip(proCode.next(),{"color":"#666666"},"* 请输入供应商编码！",false);
	}).on("blur",function(){
		if(proCode.val()!=null && proCode.val()!=""){
			validateTip(proCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proCode.next(),{"color":"red"},imgNo+"* 供应商编码不能为空，请重新输入！",false);
		}
	});
	
	/**
	 * 验证供应商名称
	 */
	proName.on("focus",function(){
		validateTip(proName.next(),{"color":"#666666"},"* 请输入供应商名称！",false);
	}).on("blur",function(){
		if(proName.val()!=null && proName.val()!=""){
			validateTip(proName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proName.next(),{"color":"red"},imgNo+"* 供应商名称不能为空，请重新输入！",false);
		}
	});
	/**
	 * 验证联系人
	 */
	proContact.on("focus",function(){
		validateTip(proContact.next(),{"color":"#666666"},"* 请输入联系人！",false);
	}).on("blur",function(){
		if(proContact.val()!=null && proContact.val()!=""){
			validateTip(proContact.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proContact.next(),{"color":"red"},imgNo+"* 联系人不能为空，请重新输入！",false);
		}
	});
	/**
	 * 验证联系电话
	 */
	proPhone.on("focus",function(){
		validateTip(proPhone.next(),{"color":"#666666"},"* 请输入联系电话",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		
		if(proPhone.val().match(patrn)){
			validateTip(proPhone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proPhone.next(),{"color":"red"},imgNo+" 您输入的联系电话格式不正确，请重新输入！",false);
		}
	});
	
	/**
	 * 提交
	 */
	addBtn.on("click",function(){
		if(proCode.attr("validateStatus")!="true"){
			proCode.blur();
		}else if(proName.attr("validateStatus")!="true"){
			proName.blur();
		}else if(proContact.attr("validateStatus")!="true"){
			proContact.blur();
		}else if(proPhone.attr("validateStatus")!="true"){
			proPhone.blur();
		}else{
			if(confirm("是否确认提交数据？")){
				$("#providerModify").submit();
			}
		}
	});
	
});