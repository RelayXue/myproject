<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>征集路名列表</title>
    <style type="text/css">
    	html,body{
    		margin:0px;
    		padding:0px;
    	}
    	.ba{
    		width:100%;height:100%;
    		position: absolute;
    		z-index:-999;
    	}
    	.ba img{
    		width:100%;
    		height:100%;
    	}
    	.conDiv{
    		width:75%;
    		height:75%;
    		background:rgba(255,255,255,0.4);
    		margin-left:10%;
    		padding:2.5%;
    		border-radius: 7px;
    		overflow:auto
    	}
    	table{
    		width:100%;
    	}
    	td{
    		height:40px;
    		text-align:center;
    		font-family: 微软雅黑; 
    	}
    </style>
  </head>
  <body>
    <div class="ba">
    	<img src="<%=basePath%>activity/2017-1/img/bg2.png"/>
    </div>
    <div style="height:8%"></div>
    <div class="conDiv">
    	<table>
    		<tr>
    			<th style="width:20%">序号</th>
    			<th style="width:35%">暂命名</th>
    			<th style="width:45%">说明简介</th>
    		</tr>
    	</table>
    	
    	<s:iterator value="list_a20171bridgeroad" var="li" status="s">
    	<a href="<%=basePath%>2017/one!rename?id=<s:property value="#li.fuid"/>" style="text-decoration: none">
	    	<table>
	    		<tr>
	    			<th style="width:20%"><s:property value="#s.index+1"/></th>
	    			<td style="width:35%;text-decoration:underline"><s:property value="#li.temporaryname"/></td>
	    			<td style="width:45%">
	    				<s:if test="#li.remark.length()>5">
		    				<s:property value="#li.remark.substring(0,5)"/>...
		    			</s:if>
	    			</td>
	    		</tr>
	    	</table>
    	</a>
    	</s:iterator>
    </div>
  </body>
</html>
