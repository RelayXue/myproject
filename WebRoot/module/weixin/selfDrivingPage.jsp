<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<s:iterator value="buNews_list" var="list">
		<li>
			<div class="read">
				<a href="<%=basePath%>fnews!selfDrivingDetails?id=<s:property  value="#list.fuid"/>"><s:property  value="#list.fullname"/></a>
			</div>
			<div class="chan">
				阅读 <s:property  value="#list.readnum"/>
			</div>
			<div class="shareit">
				<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=selfDriving"><img src="<%=basePath%>module/weixin/images/chan.png" /> </a> <s:property   value="#list.praise"/>
			</div>
			<div class="correction">
				<a href="<%=basePath%>fnews!selfDrivingDetails?id=<s:property  value="#list.fuid"/>">详情&gt;</a>
			</div>
		</li>
	</s:iterator>
	

			

