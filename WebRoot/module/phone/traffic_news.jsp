<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/phone/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="<%=curl %>css/common.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/traffic-reporter.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>

<title>乌镇-交通播报</title>
<style type="text/css">
</style>
<script type="text/javascript">

    </script>
</head>

<body>
	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title">交通播报</div>
           <div id="xq"><a href="<%=curl%>parking_service_list.jsp">停车服务</a></div>
        </div>
        <div id="hover">
        	<div>
            	<ul>
                	<li onclick="window.location.href='<%=curl%>sync_traffic_list.jsp'">
                    	<p class="p1">
                        	<span class="img"><img src="<%=curl %>images/li2.jpg"></span>
                        	<span>实时路况播报</span>
                        </p>
                        <p class="p2">
                        	<span class="s1"><img src="<%=curl %>images/hand.png"></span>
                            <span><s:property value="count1" /></span>
                            <span class="s2"><img src="<%=curl %>images/jt3.png"></span>
                        </p>
                    </li>
                    <li onclick="window.location.href='<%=curl%>timetable_list.jsp'">
                    	<p class="p1">
                        	<span class="img"><img src="<%=curl %>images/time.png"></span>
                        	<span>交通时刻表</span>
                        </p>
                        <p class="p2">
                        	<span class="s1"><img src="<%=curl %>images/hand.png"></span>
                            <span><s:property value="count2" /></span>
                            <span class="s2"><img src="<%=curl %>images/jt3.png"></span>
                        </p>
                    </li>
                    <li onclick="window.location.href='<%=curl%>self_drive_traffic_situation_list.jsp'">
                    	<p class="p1">
                        	<span class="img"><img src="<%=curl %>images/cat.png"></span>
                        	<span>自驾交通情况</span>
                        </p>
                        <p class="p2">
                        	<span class="s1"><img src="<%=curl %>images/hand.png"></span>
                            <span><s:property value="count3" /></span>
                            <span class="s2"><img src="<%=curl %>images/jt3.png"></span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
