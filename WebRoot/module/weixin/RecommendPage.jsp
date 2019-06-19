<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
		<s:iterator value="buNews_list" var="list">
		<div id="xxsd">
			<div class="xxsd">
				<div class="xxsdimg">
					<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>"><img src="<%=basePath%>upload/L_<s:property  value="#list.img1"/>" />
					</a>
				</div>
				<div class="xxsdtext">
					<h3>
						<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>"><s:property  value="#list.fullname"/> </a>
					</h3>
					<span><s:date name="#list.createdate" format="yyyy-MM-dd"  /> </span>
					
						<s:property value="@com.gh.common.SplitChinese@splitStr(content,16)" escape="false"/>
				</div>
			</div>
			<div id="function2">
				<div class="read">
					阅读  <s:property value="#list.readnum"/>
				</div>
				<div class="chan">
					<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=Recommend"><img src="<%=basePath%>module/weixin/images/chan-1.png" />
					</a> <s:property  value="#list.praise"/>
				</div>
				<div class="correction">
					<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>">详情 <img src="<%=basePath%>module/weixin/images/jiantou.png" />
					</a>
				</div>
			</div>
			<div class="xxsdbj">
				<img width="100%" src="<%=basePath%>module/weixin/images/xxsd-bj2.jpg" />
			</div>
		</div>
		 </s:iterator>
