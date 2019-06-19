<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showVideo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"  
                    codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0"  
                    width="500" height="400">   
                    <param name="movie" value="Flvplayer.swf" />   
                    <param name="quality" value="high" />   
                    <param name="allowFullScreen" value="true" />   
                    <param name="FlashVars"  
                        value="vcastr_file=<%=basePath%>jk.flv" />   
                    <embed src="Flvplayer.swf"  
                        allowfullscreen="true"  
                        flashvars="vcastr_file=<%=basePath%>jk.flv&LogoText=www.cnzz.cc"  
                        quality="high"  
                        pluginspage="http://www.macromedia.com/go/getflashplayer"  
                        type="application/x-shockwave-flash" width="500" height="400"></embed>   
                </object>

  </body>
</html>
