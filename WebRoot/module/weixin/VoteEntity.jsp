<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>诗歌详情</title>
	<style>
		html,body{
			background:#DED6C9;
			text-align:center;
			height:100%;
			width:98%
		}
		.top_div{
			font-size:25px;
			font-family:微软雅黑;
			float:left;
			width:100%
		}
		.middle_div{
			font-family:微软雅黑;
			float:left;
			width:50%
		}
	</style>
  </head>
  
  <body>
    <div class="top_div"><b><s:property value="buWeixinactivity.title"/></b></div>
    <div style="height:8%"></div>
    <div class="middle_div">作者:<s:property value="buWeixinactivity.author"/></div>
    <div class="middle_div">票数:
    			<s:if test="buWeixinactivity.number == null">
						0
				</s:if>
				<s:else>
					<s:property value="buWeixinactivity.number"/>
				</s:else>
				票</div>
    <div style="height:3%;float:left;width:100%"></div>
    <div style="font-size:14px;font-family:微软雅黑;float:left;width:100%"><s:property value="buWeixinactivity.content" escape="false"/></div>
  </body>
</html>
