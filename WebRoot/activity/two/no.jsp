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
			<p style = "color: #dcdcdc; font-size: 30px; font-weight: 600;margin-top:25%" class="tc mt100"><img src="<%=basePath%>activity/two/img/tanhao.png" style="width:24px;">很遗憾！未中奖</p>
		</div>
		 <a href="http://mp.weixin.qq.com/s?__biz=MzA4MTQxNDczMA==&mid=2691236515&idx=1&sn=00e563324bab2e222b7d2d10a0b789cc&chksm=ba08fd868d7f7490a2565014fad8702cefb355eaf2e4116b1aff82eb9f48625dd8635504e13b&scene=0#wechat_redirect"><div class="t_btn" style="background: #ED7189;">
				我要参加诗歌大赛
		 	</div></a>
	</body>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
