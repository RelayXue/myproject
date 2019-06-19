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
    <title>抽奖结果</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/base.css"/>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/animate.min.css"/>
  	<style type="text/css">
		.bg_img{background-image: url(<%=basePath%>activity/two/img/bg2.png);background-size: 100%; background-repeat:no-repeat;}
		.web_content{width: 85%; height: 250px; margin: 0px auto;border-radius: 5px; border: 3px solid #d1c0a5;
		margin-top: 15%;background: #FFFFFF; -webkit-animation: bounceInDown 1s;}
		.header_title{font-size: 20px; color: #ffffff;}
		.t_btn{width:85%; height: 40px; line-height: 40px; margin: 0px auto;text-align: center; 
		background: #D1C0A5; margin-top:20px; border-radius: 5px; color: #FFFFFF;}
		.hj_title{-webkit-animation: bounceInDown 1s;}
		.lw_img{width:120px; margin: 0px auto;position: relative; top:40px}
	</style>
  </head>
 <body class = "bg_img">
		<div  class="web_content">
			<p style = "color: #ff0000;" class="f20 tc mt30"><span style="font-size:20px;margin-top:-8px;">${name }</span><br/>恭喜你！中奖了</p>
			<div class="lw_img">
				<img src="<%=basePath%>activity/two/img/lw.png" width="100" />
			</div>
		</div>
		 <div class="t_btn" style="background: #89C997;" onclick="sub()">
		 	领取奖品
		 </div>
		 <form action="<%=basePath%>poetryChallenge!receive" method="post" id="myform">
		 	<input type="hidden" name="weixinid" value="${weixinid }"/>
		 </form>
	</body>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function sub(){
			$("#myform").submit();
		}
	</script>
</html>


