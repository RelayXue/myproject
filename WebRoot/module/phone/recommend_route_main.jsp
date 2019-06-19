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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/recommend-route.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<title>游在乌镇-推荐路线</title>
</head>

<body>
	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title">推荐路线</div>
           <!--<div id="xq"><a href="#">我来推荐</a></div>
        --></div>
        <div id="menu">
        	<ul>
            	<li class="bor_r">
                	<a href="<%=curl %>recommend_route_list.jsp?category=004026&title=公交路线">
                    	<div>
                            <p class="top">
                                <span class="h30"><img src="images/gj.png"></span>
                                <span>公交路线</span>
                            </p>
                            <p class="bottom">
                                <span class="f_l">阅读 <s:property value="readnum1"/></span>
                                <span class="f_r">赞 <s:property value="count1"/> </span>
                            </p>
                        </div>
                    </a>
                </li>
                <li class="bor_l">
                	<a href="<%=curl %>recommend_route_list.jsp?category=004027&title=自驾路线">
                    	<div>
                            <p class="top">
                                <span class="h30"><img src="images/qc.png"></span>
                                <span>自驾路线</span>
                            </p>
                            <p class="bottom">
                                <span class="f_l">阅读 <s:property value="readnum2"/></span>
                                <span class="f_r">赞 <s:property value="count2"/></span>
                            </p>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div id="gxq">热线推荐</div>
        <div id="hover">
        	<div>
            	<ul>
            	<s:iterator value="news_list" var="list">
                	<li onclick="window.location.href='<%=curl%>phone_traffic_detail?id=<s:property value="#list.fuid"/>'">
                    	<p class="p1" style="display: block; overflow: hidden;">
                        	<span><s:property value="#list.fullname"/></span>
                        </p>
                        <p class="p2">
                        	<span class="s1"><img src="images/hand.png"></span>
                            <span><s:property value="(#list.praise==null)?0:#list.praise"/></span>
                            <span class="s2">详情<img src="images/jt4.png"></span>
                        </p>
                    </li>
            	</s:iterator>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
	