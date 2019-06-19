<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="buNews_list" var="list">
		<div id="xxsd-2">
			<div class="title-1">
				<h3>
					<a href="<%=basePath%>fnews!youliDetails?id=<s:property  value="#list.fuid"/>"><s:property  value="#list.fullname"/> </a>
				</h3>
				<span><s:date name="#list.createdate" format="yyyy-MM-dd" /> </span>
			</div>
			<div class="xxsd-2img">
				<a href="<%=basePath%>fnews!youliDetails?id=<s:property  value="#list.fuid"/>"><img src="<%=basePath%>upload/B_<s:property  value="#list.img1"/>" />
				</a>
			</div>
			<div class="xxsd-2text">
				<s:property value="@com.gh.common.SplitChinese@splitStr(content,16)" escape="false"/> 
			
					
		</div>
			<div class="xxsd-2function">
				<div class="read">
					阅读 <s:property  value="#list.readnum"/>
				</div>
				<div class="chan">
					<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=youli"><img src="<%=basePath%>module/weixin/images/chan-1.png" />
					</a> <s:property  value="#list.praise"/> 
				</div>
				<div class="shareit"></div>
				<div class="correction">
					<a href="<%=basePath%>fnews!youliDetails?id=<s:property  value="#list.fuid"/>">详情 <img src="<%=basePath%>module/weixin/images/jiantou.png" />
					</a>
				</div>
			</div>
		</div>
		</s:iterator>