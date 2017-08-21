var billCode=null;
var productName=null;
var productUnit=null;
var productCount=null;
var totalPrice=null;
var providerId=null;

var modifyBtn=null;

/**
 * 验证数字
 * @param value
 * @returns
 */
function priceReg (value){
	//清除“数字”和“.”以外的字符
	value = value.replace(/[^\d.]/g,""); 
	//验证第一个字符是数字而不是.
	value = value.replace(/^\./g,"");
	//只保留第一个. 清除多余的.
    value = value.replace(/\.{2,}/g,"."); 
    //去掉特殊符号￥
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	if(value.indexOf(".")>0){
		value = value.substring(0,value.indexOf(".")+3);
	}
	return value;
}


$(function(){
	billCode=$("#billCode");
	productName=$("#productName");
	productUnit=$("#productUnit");
	productCount=$("#productCount");
	totalPrice=$("#totalPrice");
	providerId=$("#providerId");
	modifyBtn=$("#modify");
	
	billCode.next().html("*");
	productName.next().html("*");
	productUnit.next().html("*");
	productCount.next().html("*");
	totalPrice.next().html("*");
	providerId.next().html("*");
	
	
	
	//异步加载供应商列表
	$.ajax({
		type:"GET",
		url:path+"/getproviderlist",
		dataType:"json",
		success:function(data){
			$("select").html("");
			var options="<option value=\"0\">请选择</option>";
			for(var i=0;i<data.length;i++){
				options+="<option value=\""+data[i].id+"\">"+data[i].proName+"</option>";
			}
			
			$("select").html(options);
		},
		error:function(data){
			validateTip(billCode.next(),{"color":"red"},imgNo+" 供应商加载失败！",false);
		}
		
	});
	
	/**
	 * 验证账单编码
	 */
	billCode.on("focus",function(){
		validateTip(billCode.next(),{"color":"#666666"},"* 请输入账单编码！",false);
	}).on("blur",function(){
		if(billCode.val() !="" && billCode.val() !=null){
			validateTip(billCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(billCode.next(),{"color":"red"},imgNo+" 账单编码不能为空，请重新输入！",false);
		}
	});
	
	/**
	 * 验证商品名称
	 */
	productName.on("focus",function(){
		validateTip(productName.next(),{"color":"#666666"},"* 请输入商品名称！",false);
	}).on("blur",function(){
		if(productName.val() !="" && productName.val() !=null){
			validateTip(productName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productName.next(),{"color":"red"},imgNo+" 商品名称不能为空，请重新输入！",false);
		}
	});
	
	
	/**
	 * 验证商品单位
	 */
	productUnit.on("focus",function(){
		validateTip(productUnit.next(),{"color":"#666666"},"* 请输入商品单位！",false);
	}).on("blur",function(){
		if(productUnit.val() !="" && productUnit.val() !=null){
			validateTip(productUnit.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productUnit.next(),{"color":"red"},imgNo+" 商品单位不能为空，请重新输入！",false);
		}
	});
	
	
	/**
	 * 验证供应商
	 */
	providerId.on("focus",function(){
		validateTip(providerId.next(),{"color":"#666666"},"* 请选择供应商！",false);
	}).on("blur",function(){
		if(providerId.val() !="" && providerId.val() !=null && providerId.val()!=undefined){
			validateTip(providerId.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(providerId.next(),{"color":"red"},imgNo+" 供应商不能为空，请重新选择！",false);
		}
	});
	/**
	 * 验证商品数量
	 */
	productCount.on("focus",function(){
		validateTip(productCount.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位！");
	}).on("keyup",function(){
		this.value=priceReg(this.value);
	}).on("blur",function(){
		this.value=priceReg(this.value);
	});
	
	/**
	 * 验证商品总价
	 */
	totalPrice.on("focus",function(){
		validateTip(totalPrice.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位！");
	}).on("keyup",function(){
		this.value=priceReg(this.value);
	}).on("blur",function(){
		this.value=priceReg(this.value);
	});
	
	
	/**
	 * 提交
	 */
	modifyBtn.on("click",function(){
		if(billCode.attr("validateStatus")!="true"){
			billCode.blur();
		}else if(productName.attr("validateStatus")!="true"){
			productName.blur();
		}else if(productUnit.attr("validateStatus")!="true"){
			productUnit.blur();
		}else if(providerId.attr("validateStatus")!="true"){
			providerId.blur();
		}else{
			if(confirm("是否确认提交数据？")){
				$("#billModify").submit();
			}
		}
	});
});