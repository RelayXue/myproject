<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>联系方式</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/base.css"/>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/animate.min.css"/>
  	<style type="text/css">
		.bg_img{background-image: url(<%=basePath%>activity/two/img/bg2.png);background-size: 100%; background-repeat:no-repeat;}
		.web_content{width: 85%; margin: 0px auto;border-radius: 5px; border: 3px solid #d1c0a5;
		margin-top: 5%;background: #FFFFFF;padding-top:5%;padding-bottom: 5% }
		.header_title{font-size: 20px; color: #ffffff;}
		.t_btn{width:85%; height: 40px; line-height: 40px; margin: 0px auto;text-align: center; 
		background: #D1C0A5; margin-top:20px; border-radius: 5px; color: #FFFFFF;}
		.hj_title{-webkit-animation: bounceInDown 1s;}
		.lw_img{width:120px; margin: 0px auto;position: relative; top:40px}
	</style>
  </head>
 <body class = "bg_img">
 <div style="text-align:center;color:red;font-family:微软雅黑;font-size:23px"><b><br>恭喜您中奖</b></div>
		<div  class="web_content">
			<div>
			<form action="<%=basePath%>poetryChallenge!updateUse" method="post" id="myform">
				<table style="width:100%;font-family: 微软雅黑">
					<tr>
						<td style="text-align:right;height:40px;" valign="middle">姓名：</td> 
						<td style="text-align:right;height:40px;width:60%;text-align:left">
							<input type="text" id="name" name="name" style="width:80%;height:25px;border-radius: 3px;border:#DFDFDF solid 1px;">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;height:30px;" valign="middle">联系方式：</td>
						<td style="text-align:right;height:40px;width:60%;text-align:left">
							<input type="text" id="phone" name="phone" style="width:80%;height:25px;border-radius: 3px;border:#DFDFDF solid 1px;">
						</td>
					</tr>
				</table>
				<input type="hidden" name="weixinid" value="${weixinid}"/>
			</form>
			</div>  
			<div>
				<center>
					<div onclick="sub()" class="t_btn" style="background:#ADD697;">
						提 交
					</div>
<div style="padding-top:10px;color:red;font-size:14px;font-family: 微软雅黑;width:80%;text-align:left">
				*&nbsp;&nbsp;注明：奖品会在几个工作日后由工作人员联系您，谢谢您的配合，祝你生活愉快
			</div>
				</center>
			</div>
		</div>
		 <a href="http://mp.weixin.qq.com/s?__biz=MzA4MTQxNDczMA==&mid=2691236515&idx=1&sn=00e563324bab2e222b7d2d10a0b789cc&chksm=ba08fd868d7f7490a2565014fad8702cefb355eaf2e4116b1aff82eb9f48625dd8635504e13b&scene=0#wechat_redirect"><div class="t_btn" style="background: #ED7189;">
				我要参加诗歌大赛
		 	</div></a>
	</body>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function sub(){
			if($("#name").val()=="" || $("#phone").val()==""){
				alert("请填写您的资料，方便工作人员通知您领奖！")
				return;
			}
			if($("#weixinid").val==""){
				alert("已丢失您的个人信息！")
				$("#myform").attr("action","<%=basePath%>poetryChallenge!homePage")
				return;
			}
			alert("资料提交成功！请等候通知！")
			$("#myform").submit();
		}
	</script>
</html>


