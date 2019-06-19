<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="!list.isEmpty()">
		<s:iterator var="s" value="list" status="status">
    	<div class="_body_list_item">
        	<div class="_img" style="background-image:url(<%=basePath%>upload/${s.dimages })"></div>
            <div class="_content">
            	<div style="font-size:20px;"><span>${s.fullname }</span></div>
                <div style="display:block; height:30px; line-height:30px; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0 3px; padding-left:26px;color:#e5772d; font-size:16px;margin-top:3px;">4.5 人均：￥50</div>
                <div style="font-size:14px; margin-top:3px; color:#615F5F;">电话：${s.phone }</div>
                <div style="font-size:14px; margin-top:3px; color:#615F5F; margin-bottom:10px;"> 地址：${s.address }</div>
            </div>
                <div style="width:50%; background-color:#b99483; color:#fff; background-image:url(<%=basePath%>module/web/images/list_icon1_14.png); background-position:18px center; background-repeat:no-repeat; line-height:32px; text-align:center; float:left">前往</div>
                <div style="width:50%; background-color:#717699; color:#fff; background-image:url(<%=basePath%>module/web/images/list_icon1_11.png); background-position:80px center; background-repeat:no-repeat; line-height:32px; text-align:center; float:left">详情</div> 
                <div style="clear:both"></div>
            <div class="_botm">
            	<span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png)">1000</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:10px 2px;">1000</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:10px 5px;">1000</span>
                <div style="clear:both"></div>
            </div>
        </div>
        </s:iterator>
        </s:if>