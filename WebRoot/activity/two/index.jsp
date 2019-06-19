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
   	<link rel="stylesheet" type="text/css" href="css/base.css"/>
    <title>诗歌挑战大赛</title>
    <style type="text/css">
		.web_content{width: 80%; margin: 0px auto; border: 3px solid #d1c0a5;
			margin-top: 38%; border-top: 0px; border-radius: 5px;background: #FFFFFF; overflow: hidden;overflow-y:auto;
			padding:5%;}
		.index_btn{width: 85%; height: 40px; line-height: 40px; border:0px;
		 background: #ED7189; color: #FFFFFF; font-size: 18px; border-radius: 5px;
		  margin: 0px auto; text-align: center; margin-top: 30px;}
		  h2{
		  	margin-top:5px;margin-bottom: 5px;
		  }
	</style>
  </head>
  <body >
  	<div style="width:100%;height:120%;position: fixed;z-index:-100;float;left">
  		<img src="<%=basePath%>activity/two/img/bg.png" style="width:100%;height:100%"/>
  	</div>
  	<div style="float:left;width:100%;">
	  	<div class="web_content">
	  		<s:property value="a20161activity.introduce" escape="false"/>
	  	</div>
		<!--<a href="<%=basePath%>poetryChallenge!answer"><div class="index_btn"> 参加活动</div></a>-->
		<div class="index_btn"> 活动</div>
	</div>
  </body>
</html>
