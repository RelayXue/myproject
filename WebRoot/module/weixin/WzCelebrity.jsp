<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head> 
		<title>乌镇名人</title>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<link href="<%=basePath%>module/weixin/css/mrmain.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="main">
			<s:iterator value="buNews_list"  var="list" >  
				<div class="item">
					<a href="javascript:void(0)">
						<div class="pic">
							<img src='<%=basePath %>upload/<s:property value="#list.img1" />' />
						</div>    
						<div class="content">
							<div class="bt">
								<s:property  value="#list.fullname" />	  
							</div>
							<div class="nr">
								 <s:property escape="false" value="@com.gh.common.SplitChinese@splitStr(#list.content,22)"/>  
							</div>
						</div> </a>
				</div>
			</s:iterator>
		</div>
	</body>
</html>
