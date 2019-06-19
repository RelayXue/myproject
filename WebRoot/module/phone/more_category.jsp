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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/comprehensive2.css">
<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
<script src="<%=curl %>js/jquery.min.js"></script>
<title>游在乌镇-更多周边</title>
</head>

<body>
	<div id="container">
    	<div id="header">
        	<div id="return">
            	<p><a href="javascript:history.go(-1)">&nbsp;</a></p>
            </div>
             <div id="search">
             	<p>
                    <span></span>
                    <input type="text" placeholder="综合查询" x-webkit-speech />
                    <a href="#"></a>
                </p>
             </div>
        </div>
     	<div id="list">
        	<div class="list1">
                <ul class="bor">
                    <li class="title">美食</li>
                    <li>公交站</li>
                    <li>加油站</li>
                    <li class="nob">汽车站</li>
                </ul>
                <ul>
                    <li class="bg"></li>
                    <li>银行</li>
                    <li>超市</li>
                    <li class="nob">厕所</li>
                </ul>
            </div>
            <div class="list2">
                <ul class="bor">
                    <li class="title">住宿</li>
                    <li>公交站</li>
                    <li>加油站</li>
                    <li class="nob">汽车站</li>
                </ul>
                <ul>
                    <li class="bg"></li>
                    <li>银行</li>
                    <li>超市</li>
                    <li class="nob">厕所</li>
                </ul>
            </div>
            <div class="list3">
                <ul class="bor">
                    <li class="title">休闲娱乐</li>
                    <li>酒吧</li>
                    <li>KTV</li>
                    <li class="nob">商场</li>
                </ul>
                <ul>
                    <li class="bg"></li>
                    <li>咖啡厅</li>
                    <li>网吧</li>
                    <li class="nob">茶馆</li>
                </ul>
            </div>
            <div class="list4">
                <ul class="bor">
                    <li class="title">生活服务</li>
                    <li>超市</li>
                    <li>ATM</li>
                    <li class="nob">药店</li>
                </ul>
                <ul>
                    <li class="bg"></li>
                    <li>银行</li>
                    <li>医院</li>
                    <li class="nob">厕所</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>