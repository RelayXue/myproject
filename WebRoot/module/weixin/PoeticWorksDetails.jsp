
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
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link href="<%=basePath%>module/weixin/css/ssh_2.css" rel="stylesheet" type="text/css" />
		<title></title>
	</head>
	<body>
		<div>
			<div class="utop">
				<img src="<%=basePath%>module/weixin/images/top1.png" />
			</div>
			<div class="utitle">
				${buWeixinactivity.title }
			</div>
			<div class="utitle2">
				${buWeixinactivity.author }
			</div> 
			<div class="utitle3">
				<s:date name="buWeixinactivity.createtime" format="yyyy-MM-dd"/>
			</div>
			<div class="ucontent">
				${buWeixinactivity.content }
			</div>

			<div class="ubottom">
			</div>
		</div>
	</body>
</html>
