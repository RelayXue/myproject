<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>身份绑定</title>
	</head>
	<style type="text/css">
		body
		{
			height: 500px;
			font-family:"黑体" ;
		}
		.content
		{
			width: 90%;
			height: 42%;
			margin-left: 5%;
			margin-top: 5%;
			border: solid 1px #dcdadb;
			border-radius: 5px;
			box-shadow: 0px 2px 2px #8c8889;
		}
		
		.title
		{
			background-color: #ecce81;	
			border-radius: 5px;
			height: 35px;
			font-size: 20px;
			line-height: 35px;
			box-shadow: 0px 2px 2px #a89360;
			margin-bottom: 15px;
			padding-left:10px ;
		}
		.info
		{
			padding-left:10px ;
			height: 40px;
		}
		.tag
		{
			display: inline-block;
			font-size: 15px;
			width: 40%;
			height: 100%;
		}
		.input
		{
			display: inline-block;
			width: 50%;
			height: 100%;
			
		}
		.input input
		{
			width: 100%;
			height: 50%;
			border-radius: 5px;
		}
		.input1
		{
			display: inline-block;
			margin-left: -10px;
			width: 20%;
			height: 100%;
			
		}
		.input1 input
		{
			width: 100%;
			height: 50%;
			border-radius: 5px;
		}
		.btn1
		{
			display: inline-block;			
			width: 30%;
			height: 40px;
		}
		.btn1 button
		{
			width: 100%;
			height: 26px;
			background-color: #eaeaea;	
			box-shadow: 0px 2px 2px #a0a0a0;
			border:solid 1px #707070;
			border-radius: 5px;
			font-size: 10px;
			font-weight: bold;
		}
		.btn
		{
			text-align: center;
			height: 30%;
			margin-top: -5px;
		}
		.btn button
		{
			width: 60%;
			height: 35px;
			background-color: #ecce81;	
			box-shadow: 0px 2px 2px #a89360;
			border:solid 1px #c2bcae;
			border-radius: 5px;
			font-size: 13px;
			font-weight: bold;
		}
		.bk
		{
			position: fixed;
			bottom: 0px;
			width: 100%;
			z-index: -1;
		}
		.bk img
		{
			height: 20%;
			width: 100%;
		}
		.fct
		{
			display: inline-block;
			vertical-align: middle;
			text-align: center;
			width: 27%;
			font-size: 12px;
			line-height: 26px;
			border:solid 1px #c2bcae;
			background-color: #eaeaea;	
			border-radius: 5px;
			
		}
		.fct img 
		{
			width: 30px;
			height: 20px;
			vertical-align: middle;
		}
	</style>
	<link href="<%=basePath%>css/verification.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
	<script type="text/javascript">
	
var countdown=60; 
var countdown_lv=60; 

function settime(obj) { 
	var phone=	$("#phone").val();
	if(phone==null||phone.length==0){
		alert("请输入手机号");
	return;
	}


	if(countdown==60){
		 $.ajax({
				type : "POST",
				dataType : "text",
				url : "<%=basePath%>z_weixin/hotelAudit!tverification",
				data:"phone="+$("#phone").val(),
				 success: function(msg){
					 if(msg!=null&&msg.length>0){
					 alert(msg);
					 if(msg=="该手机号不在库中，请联系管理员！"){
					 		countdown=0;
					 		
					 	}
					 }
				   }
			});
		}

	if (countdown == 0) { 
	    obj.removeAttribute("disabled");    
	    obj.innerHTML="获取验证码"; 
	    countdown = 60; 
	    return;
	} else { 
	    obj.setAttribute("disabled", true); 
	    obj.innerHTML="重新发送(" + countdown + ")"; 
	    
	    countdown--; 
	} 
	
	
	setTimeout(function() { 
		settime(obj) }
		,1000) 
}

function settime_lv(obj) { 
	var phone_lv=$("#phone_lv").val();
	if(phone_lv==null||phone_lv.length==0){
		alert("请输入手机号");
	return;
	}


	if(countdown_lv==60){
		 $.ajax({
				type : "POST",
				dataType : "text",
				url : "<%=basePath%>z_weixin/isPhone_lv!tverification",
				data:"phone_lv="+$("#phone_lv").val(),
				success: function(msg_lv){
					 if(msg_lv!=null&&msg_lv.length>0){
					 alert(msg_lv);
						if(msg_lv=="该手机号不在库中，请联系管理员！"){
					 		countdown_lv=0;
					 	}
					 }
				 }
			});
		}

	if (countdown_lv == 0) { 
	    obj.removeAttribute("disabled");    
	    obj.innerHTML="获取验证码"; 
	    countdown_lv = 60; 
	    return;
	} else { 
	    obj.setAttribute("disabled", true); 
	    obj.innerHTML="重新发送(" + countdown_lv + ")"; 
	    
	    countdown_lv--; 
	} 
	
	
	setTimeout(function() { 
		settime_lv(obj) }
		,1000) 
}


	
function refreshCheckCode(){
	 $("#img").attr("src","<%=basePath%>checkCode.htm?id="+new Date().getTime());
}
	</script>
	
	<body>
	<form action="<%=basePath %>z_weixin/insertPhone" method="post" id="myform">
		 <div class="content">
			<div class="title">公安输入</div>
			<div class="info"><div class="tag">姓名</div><div class="input"><input type="text" name="name"></div></div>
			<div class="info"><div class="tag">手机号码</div><div class="input"><input type="text" id="phone" name="phone" onblur="asd()"></div></div>
			<div class="info"><div class="tag">短信验证码</div>
			<div class="input1"><input type="text"></div>
			<input type="hidden"  value="${weixinid }" >
			<div class="btn1"><button onclick="settime(this)" type="button">获取验证码</button></div>
			</div>
			<div class="btn"><input type="image"  src="<%=basePath%>module/weixin/images/bangding.png" alt="Submit"></div>
		</div>
	</form>	
	
	<form action="<%=basePath %>z_weixin/insertPhone_lv" method="post" id="myform_lv">
		<div class="content" style="height: 51%;">
			<div class="title">旅店输入</div>
			<div class="info"><div class="tag">名称</div><div class="input"><input type="text" name="name_lv"></div></div>
			<div class="info"><div class="tag">手机号码</div><div class="input"><input type="text" name="phone_lv" id="phone_lv"></div></div>
			<input type="hidden" name="auditstatus" value="0">
			<div class="info">
				<div class="tag">上传相关图片</div>
				<div class="fct" style="margin-left: -10px;"><img src="<%= basePath %>module/weixin/images/fct_01.png">拍照</div>
				<div class="fct"><img src="<%= basePath %>module/weixin/images/fct_02.png">相册</div>
			</div>
			<div class="info"><div class="tag">短信验证码</div>
			<div class="input1"><input type="text"></div>
			<div class="btn1"><button onclick="settime_lv(this)" type="button">获取验证码</button></div>
			</div>
			<div class="btn"><input type="image"  src="<%=basePath%>module/weixin/images/bangding.png" alt="Submit"></div>
		</div>
	</form>
	
		<div class="bk"><img src="<%= basePath %>module/weixin/images/bk.png"> </div>
	</body>
</html>
