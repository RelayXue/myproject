<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String type=(String)request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="buAccommodation_list" var="list" status="st">
		<s:if test="#st.index%2==0">
		<div class="list">
		</s:if>
		<s:else>
		<div class="list" style="background:#FBF4E2">
		</s:else>
			<div class="list_left">
			<s:if test="#list.himg==null || #list.himg==''">
				<img src="<%=basePath%>module/weixin/images/tishi.png" style="width:100%">
			</s:if>
			<s:else>
				<a href='<%=basePath%>accommodation!selectById?fuid=<s:property value="#list.fuid"/>'><img src='<%=basePath%>upload/L_<s:property value="#list.himg"/>' style="width:100%;height:100%"></a>
			</s:else>
			</div>
			<div style="float:left;width:8px;height:1px"></div>
			<div class="list_left" style="width:52%;padding-top:2%">
				<div class="two" style="white-space:nowrap;text-overflow:ellipsis;text-overflow:ellipsis;overflow: hidden; "><s:property value="#list.morder"/>、<s:property value="#list.hname"/></div>
				<div style="width:1px;height:10%;float:left"></div>
				<div class="two" style="background:#DCDCDC;height:25px;width:100%;border-radius:7px;">
					<input type="hidden" id='<%=Integer.parseInt(request.getAttribute("indexPage")+"")-1%><s:property value="#st.index"/>' value='<s:if test="#list.num==null || #list.num==''">0</s:if><s:else><s:property value="#list.num"/></s:else>'>
					<div style="background:#EBC874;text-align:center;height:100%;width:20%;border-radius:7px" id='d<%=Integer.parseInt(request.getAttribute("indexPage")+"")-1%><s:property value="#st.index"/>' name="Q"></div>
				</div>
			</div>
			<div style="float:left;width:8px;height:1px"></div>
			<div class="list_left" style="text-align:center;padding-top:2%">
				<input type="checkbox" name="box" style="width:20px;height:20px" value='<s:property value="#list.fuid"/>' onclick="getFuid()" /><br/>
				<span id='piao<s:property value="#st.index"/>'>
				<s:if test="#list.num==null || #list.num==''">0</s:if><s:else><s:property value="#list.num"/></s:else>
				</span>
				票
			</div>
		</div>
	</s:iterator>