jQuery.validator.addMethod("myusername", function(value, element) {
	var myusername = /^[A-Za-z0-9]{3,14}$/;
	return this.optional(element) || (myusername.test(value));
}, "用户名必须由3-14个字母或数字组成,请正确输入");

jQuery.validator.addMethod("myuserpwd", function(value, element) {
	var mypassword = /^[A-Za-z0-9]{6,30}$/;
	return this.optional(element) || (mypassword.test(value));
}, "密码必须由6-30个字母或数字组成,请正确输入");


jQuery.validator.addMethod("mycheck_code", function(value, element) {
	var mycheck_code = /^[0-9]{4}$/;
	return this.optional(element) || (mycheck_code.test(value));
}, "验证码只由数字组成,请正确输入");

jQuery.validator.addMethod("myuserpwd", function(value, element) {   
    var mypassword = /^[A-Za-z0-9]{6,30}$/;
    return this.optional(element) || (mypassword.test(value));
}, "密码必须由6-30个字母或数字组成！");


jQuery.validator.addMethod("mycheck_code", function(value, element) {   
    var mycheck_code = /^[0-9]{4}$/;
    return this.optional(element) || (mycheck_code.test(value));
}, "验证码只由数字组成,请正确输入");
	

jQuery.validator.addMethod("mylxdh", function(value, element) {   
    var mylxdh = /^(\d{3,4})\-{0,1}(\d{7,8})$/;
    return this.optional(element) || (mylxdh.test(value));
}, " 联系电话号码格式无效,请正确输入!");


jQuery.validator.addMethod("mysj", function(value, element) {
    var mysj = /^1[3,4,5,8]{1}[0-9]{1}[0-9]{8}$/;
    return this.optional(element) || (mysj.test(value));
}, "  手机号码格式无效,请正确输入!");


jQuery.validator.addMethod("myqqhm", function(value, element) {   
    var myqqhm = /^\d{5,10}$/; 
    return this.optional(element) || (myqqhm.test(value));
}, "QQ号码格式无效,请正确输入");

jQuery.validator.addMethod("myqidcard", function(value, element) {   
    var myqidcard = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/; 
    return this.optional(element) || (myqidcard.test(value));
}, "身份证无效,请正确输入");

 



jQuery.validator.addMethod("Port_wnumber", function(value, element) {
	var fullname = $("#fullname").val();
	var wnumber = $("#wnumber").val();
	fullname=encodeURIComponent(fullname);
	wnumber=encodeURIComponent(wnumber);
	var isRight = '';
	$.ajax({
		url : "/portapplication!Verification?wnumber="+wnumber+"&time=" + new Date(),
		dataType:"text",
		async : false,
		success : function(data) {
			isRight = data;
			$("#portapplicationid").attr("value",data);
		}
	});
	return this.optional(element) || isRight != 'false';
}, "无该航次!请确认是否已经填写口岸申请书");


jQuery.validator.addMethod("port_name", function(value, element) {
	var fullname = $("#startport").val();
	fullname=encodeURIComponent(fullname);
	var isRight = '';
	$.ajax({
		url : "/portdatadictionary!Verification?fullname="+fullname+"&time=" + new Date(),
		dataType:"text",
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight != 'false';
}, "无该港口名称!请根据系统自动提示录入");

jQuery.validator.addMethod("myusername_repeat", function(value, element) {
	var username = $("#username").val();
	username=encodeURIComponent(username);
	var isRight = '';
	$.ajax({
		url : "/User!IsExist?username=" + username + "&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == 'false';
}, "该用户已经被注册，请重新输入!");

jQuery.validator.addMethod("code_repeat", function(value, element) {
	var code = $("#code").val();
	var code1=$("#codeHid").val();
	if(code1!=null&&code1!='null'&&code1.length>0){
		code=code1+code;
	}
	var isRight = '';
	$.ajax({
		url : "Datadictionary!IsExist?code=" + code + "&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == 'false';
}, "该代码已经被使用，请重新输入!");

jQuery.validator.addMethod("ProtCode_repeat", function(value, element) {
	var code = $("#code").val();
	var code1=$("#codeHid").val();
	var type=$("#type").val();
	if(code1!=null&&code1!='null'&&code1.length>0){
		code=code1+code;
	}
	var isRight = '';
	$.ajax({
		url : "/portdatadictionary!IsExist?code=" + code + "&type="+type+"&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == 'false';
}, "该代码已经被使用，请重新输入!");

jQuery.validator.addMethod("Dangerous_re", function(value, element) {
	var dangerousgoods = $("#dangerousgoods").val();
	dangerousgoods=encodeURIComponent(dangerousgoods);
	var isRight = '';
	$.ajax({
		url : "/Organize!DangerousIsExist?dangerousgoods=" + dangerousgoods + "&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == '1';
}, "您暂无该危货的申请权!");

jQuery.validator.addMethod("mypassword_repeat", function(value, element) {
	var password = $("#ymm").val();
	var isRight = '';
	$.ajax({
		url : "/WuZhen/User!PasswordRepeat?password=" + password + "&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == 'false';
}, "原密码错误，请重新输入!");


jQuery.validator.addMethod("email_repeat", function(value, element) {
	var email = $("#email").val();
	var isRight = '';
	$.ajax({
		url : "/reg.do?wtd=gr_zh&e=" + email + "&time=" + new Date(),
		async : false,
		success : function(data) {
			isRight = data;
		}
	});
	return this.optional(element) || isRight == 'false';
}, "该邮箱已经被注册，请重新输入!");

//--------
jQuery.extend(jQuery.validator.messages, {
	  required: "请您输入该字段的值！ ",
	  remote: "请修正该字段",
	  email: "请输入正确格式的电子邮件",
	  url: "请输入合法的网址",
	  date: "请输入合法的日期",
	  dateISO: "请输入合法的日期 (ISO).",
	  number: "请输入合法的数字",
	  digits: "只能输入整数",
	  creditcard: "请输入合法的信用卡号",
	  equalTo: "请再次输入相同的密码！",
	  accept: "请输入拥有合法后缀名的字符串",
	  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
	  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
	  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
	  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
	  min: jQuery.validator.format("请输入一个最小为{0} 的值")
	});
