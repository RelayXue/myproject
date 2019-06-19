<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <title>活动抽奖</title>
    <style type="text/css"> 
    </style>
</head>
<body style="background: #e5e0da">
    <div id="ww"> 
        <div id="dvCenter" style="padding-top:50px">
            <a href="#"><img src="<%=basePath %>module/weixin/images/zailai.png" style="width: 100%" /></a>
        </div> 
    </div>
</body>
</html>
