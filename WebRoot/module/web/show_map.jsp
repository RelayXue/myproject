<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>地理位置</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Resource/Theme/StyleDefault/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TMap.js"></script>

    <script type="text/javascript">
    $(function(){
    	init();
    	var obj = {};
    	var xy = '${xy}';
    	if(xy!=null&&xy.length!=0){
    		obj.x = xy.split('|')[0];
    		obj.y = xy.split('|')[1];
    		addMarkerOnly(obj);
    	}
    });
    </script>
<style type="text/css">
#map {
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	background-color: #F0EEE9;
	border: 0px solid black;
}
</style>
</head>

<body>
<div style="background-color: #F0EEE9; overflow: hidden; z-index: 1;" id="map" oncontextmenu="return false;">
</div>
</body>
</html>