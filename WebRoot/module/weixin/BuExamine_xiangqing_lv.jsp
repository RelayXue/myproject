<%@page import="com.gh.common.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%DateUtil dateUtil=new DateUtil();%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
#head {
	width: 100%;
}

#head  a {
	padding-top: 5px;
	display: block;
	background-color: #eac873;
	border-radius: 5px;
	height: 25px;
	color: #555555;
	font-weight: bold;
	padding-left: 15px;
}

#body {
	width: 100%;
}

.input {
	border-radius: 5px;
	
}

#div2 {
	border: 1px solid grey;
}

#body {
	font-size: 0.24rem;
	color: #555555;
	font-weight: bold;
}
</style>
</head>
<body>
	<div id="head">
		<a>游客详情</a>
	</div>

	<div id="div2">
		<form action="z_weixin/buExamineAction_update" method="post" class="registerform">
			<div align="center" id="body">
				<table cellspacing="10">
				
				<s:iterator value="buExamine_list" var="aaa" >
					<tr>
						<td>游客姓名</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.passenger" id="fullname" value=<s:property value="#aaa.passenger"/>></td>
					</tr>
					
					<tr>
						<td>手机号码</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.ephone" id="phone" value=<s:property value="#aaa.ephone"/>>
						</td>
					</tr>
					<tr>
						<td>到店时间</td>
						
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.arrivetime" value='<s:date name="#aaa.arrivetime" format="yyyy-MM-dd HH:mm:ss" />'>
						</td>
					</tr>
					<tr>
						<td>游客身份证</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.pid" id="Tourist" value=<s:property value="#aaa.pid"/>>
						</td>
					</tr>
					<tr>
						<td>来乌事由</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.reason" id="Reason" value=<s:property value="#aaa.reason"/>>
						</td>
					</tr>
					</tr>
					<tr>
						<td>住宿旅馆</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.hostelname" value=<s:property value="#aaa.hostelname"/>>
						</td>
					</tr>
					<tr>
						<td>经办人</td>
						<td><input type="text" class="input"  disabled="disabled" name="buExamine.ephone" value=<s:property value="#aaa.managers"/>>
						</td>
					</tr>
					<input type="hidden" name="buExamine.fuid" value=<s:property value="#aaa.fuid"/>>
					<input type="hidden" name="buExamine.createdate" value='<s:date name="#aaa.createdate" format="yyyy-MM-dd HH:mm:ss" />'>
					</s:iterator>
				</table>
			</div>

			<div align="center" id="tail" style="margin-top:20px">
				<div style="margin-top:5%">
					<a href="<%=basePath%>z_weixin/select_All?department=Inn"><img src="<%=basePath%>module/weixin/images/fanhui.png" style="width:70px"></a>
				</div>
			</div>
		</form>
		<img src="<%=basePath%>module/weixin/images/sq_02.png" width="100%" style="margin-top:30px">
	</div>
</body>
</html>