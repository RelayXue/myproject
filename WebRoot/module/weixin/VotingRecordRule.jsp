<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>最美乌镇人家</title>
    <style type="text/css">
    	body {
			padding: 0px;
			margin: 0px;
		}
    	.img_div{
			width:100%;
			height:100%;
			float:left;
			position:absolute;
			z-index:-10;
		}
		.img_big{
			width:100%;
			height:101%
		}
		.div{
			color:#ffffff;
			padding-top:10%;
			padding-bottom:30%;
			font-family:黑体;
			font-size:23px;
			text-align:left;
			width:90%
		}
		a{
			color:#103864;
			text-decoration: none
		}
		span{
			background:#EAC872;
			padding:6px 14px 6px 14px;
			border-radius:5px;
		}
    </style>
  </head>
  <body>
    <div class="img_div"><img src="<%=basePath%>module/weixin/images/bj.png" class="img_big"></div>
    <center>
	    <div class="div">
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投完票后每人可抽奖一次，若您中奖，请在评选结束后关注“乌镇发布”的排名公告后自行联系相应民宿预约订
	    </div>
    </center>
    <center>
    	<div style="width:100%;text-align:center"><a href='<%=basePath%>accommodation!Vote?weixinid=<s:property value="weixinid"/>'><span>返回至投票界面</span></a></div>
    </center>
  </body>
</html>
