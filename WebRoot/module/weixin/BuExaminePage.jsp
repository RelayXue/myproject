<%@page import="com.gh.common.DateUtil"%>
<%@page import="com.gh.entity.BuExamine"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=(String)request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:iterator value="buExamine_list" var="list" status="st">
	<a href="z_weixin/select_where?department=public_security&fuid=<s:property value='#list.fuid'/>">
	<s:if test=" #st.index % 2==0">
		<table class="odd">
		</s:if>
	<s:else>
		<table>
	</s:else>
			<tr>
				<td class="left">游客姓名</td>
				<td class="right" >
					<div style="width:60%;float:left"><s:property value="#list.passenger"/></div>
					<div style="width:35%;float:right">
					<s:if test="#list.auditstatus==0">
						<div  style="color:red">未审核</div>
					</s:if>
					<s:elseif test="#list.auditstatus==1">
						<div>审核通过</div>
					</s:elseif>
					</div>
				</td>
			</tr>

			<tr>
				<td class="left">手机号码</td>
				<td class="right"><s:property value="#list.ephone"/></td>
			</tr>

			<tr>
				<td class="left">到访时间</td>
				<td class="right"><div class="yincang"><s:date name="#list.arrivetime" format="yyyy-MM-dd HH:mm:ss" /> </div></td>
			</tr>

			<tr>
				<td class="left">到访事由</td>
				<td class="right"><div class="yincang"><s:property value="#list.reason"/></div></td>
			</tr>
			
		</table>
	</a>
</s:iterator>
