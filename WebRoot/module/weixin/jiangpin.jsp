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
			align:center
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
			padding-top:50%;
			padding-bottom:20%;
			font-family:黑体;
			font-size:23px
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
	    	<b><s:property value="msg"/></b>
	    </div>
    </center>
    <center>
    	<div style="width:100%;text-align:center"><a href='<%=basePath%>accommodation!Vote?weixinid=<s:property value="weixinid"/>'><span>返回至投票界面</span></a></div>
    </center>
  </body>
</html>
