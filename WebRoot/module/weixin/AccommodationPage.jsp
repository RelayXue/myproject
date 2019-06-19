<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String type=(String)request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="buAccommodation_list" var="list" status="st">
	<a href='<%=basePath%>accommodation!selectById?fuid=<s:property value="#list.fuid"/>'>
		<br/>
		<s:if test=" #st.index % 2==0">
			<div class="big_one">
				<div class="div_left">
					<s:if test="#list.himg!=null && #list.himg!=''">
						<img src="<%=basePath%>upload/L_<s:property value="#list.himg"/>" style="width:100%;height:90px"/>
					</s:if>
					<s:else>
						<img src="<%=basePath%>module/weixin/images/zw.jpg" style="width:100%;height:90px"/>
					</s:else>
				</div>
				<div class="div_right" style="color:#000000"><b><s:property value="#list.hname"/></b></div>
				<div class="div_right" style="color:#767475"><img src="<%=basePath%>module/weixin/images/dz-g.jpg";style="height:100%">&nbsp;<s:property value="#list.address"/></div>
				<div class="div_right_bo" style="color:#767475"><img src="<%=basePath%>module/weixin/images/phone-g.jpg"/>&nbsp;<s:property value="#list.mobile"/></div>
			</div>
		</s:if>
		<s:else>
			<div class="big_two">
				<div class="div_left">
					<s:if test="#list.himg!=null && #list.himg!=''">
						<img src="<%=basePath%>upload/L_<s:property value="#list.himg"/>" style="width:100%;height:90px"/>
					</s:if>
					<s:else>
						<img src="<%=basePath%>module/weixin/images/zw.jpg" style="width:100%;height:90px"/>
					</s:else>
				</div>
				<div class="div_right" style="color:#000000"><b><s:property value="#list.hname"/></b></div>
				<div class="div_right" style="color:#767475"><img src="<%=basePath%>module/weixin/images/dz-w.jpg";style="height:100%">&nbsp;<s:property value="#list.address"/></div>
				<div class="div_right_bo" style="color:#767475"><img src="<%=basePath%>module/weixin/images/phone-w.jpg"/>&nbsp;<s:property value="#list.mobile"/></div>
			</div>
		</s:else>
	</a>
	</s:iterator>