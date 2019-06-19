
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:property value="buNews.fullname" /></title>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<style type="text/css">
			
		</style>
	</head>
	<body>
		<div id="details">
			<div style="font-size: 20px;font-weight: bold;line-height: 25px">
				<s:property value="buNews.fullname" />
			</div>
			<span style="font-size: 15px;line-height: 20px; margin-bottom: 10px; display: block;"><s:date name="buNews.modifydate" format="yyyy-MM-dd" />
			</span>
			<div id="detailsimg">
				<img width="100%"
					src="<%=basePath%>upload/700-<s:property  value="buNews.img1"/>" />
			</div>
			<div id="detailsintroduction">

				<s:property value="buNews.content" escape="false" />


			</div>
		</div>

	</body>

</html>