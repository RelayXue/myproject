<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title></title>
    <style type="text/css">
    	html body{
    		width:100%;
    		padding:0px;
    		margin:0px;
    		text-align:center;
    		color:red;
    		font-family:黑体;
    		padding-top:50px;
    	}
    	img{
    		width:90%
    	}
    </style>
  </head>
  <body>
  	长按以下乌镇发布二维码，进行识别关注；<br/>
	进入活动界面。<br/>
	<img src="<%=basePath%>Photography/PhotographyImg/er.jpg">
  </body>
</html>
