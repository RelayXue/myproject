
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
					<div class="photo-img">
						<a href="<%=basePath%>fnews!shareDetails?id=<s:property  value="#list.fuid"/>""><img src="<%=basePath%>upload/B_<s:property  value="#list.img1"/>" />
						</a>
						<h3>
							<a href="<%=basePath%>fnews!shareDetails?id=<s:property  value="#list.fuid"/>""><s:property value="@com.gh.common.SplitChinese@splitStr(fullname,16)" escape="false"/> </a>
						</h3>
					</div>
				</li>
				</s:iterator>
	 