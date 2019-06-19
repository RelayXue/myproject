
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = (String) request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>乌镇概况</title>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="viewport" 	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<link href="<%=basePath%>module/weixin/css/gkmain.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="main">
			<div class="container">
				<div
					style="width: 2px; height: 430px; background-color: #a19783; position: absolute; top: 0px; left: 0px; margin-left: 17px; z-index: -1;">
				</div>
				<div
					style="width: 2px; height: 430px; background-color: #a19783; position: absolute; top: 0px; right: 0px; margin-right: 17px; z-index: -1">
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!survey'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb01.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇概况】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!other?type=004008'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb02.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇地理】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!histroy'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb03.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇历史】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!other?type=004010'" >
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb04.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇民俗】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!Humanities'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb05.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇故事】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<div class="item" onclick="window.location.href='<%=basePath%>fnews!Celebrity'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb06.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇名人】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
				<%--<div class="item"  onclick="window.location.href='<%=basePath%>fnews!other?type=004013'">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td> 
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
							<td>
								<div class="pic1">
									<img src="<%=basePath%>module/weixin/images/fb07.png" style="width: 49px; height: 49px;" />
								</div>
							</td>
							<td style="padding-right: 28px; width: 100%">
								<div class="content">
									【乌镇保护】
								</div>
							</td>
							<td style="width: 40px;">
								<img src="<%=basePath%>module/weixin/images/fbimg.png" style="width: 25px; height: 25px;" />
							</td>
						</tr>
					</table>
				</div>
			--%></div>
		</div>
	</body>
</html>
