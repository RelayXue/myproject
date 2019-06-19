<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<head>
<title>活动抽奖</title>

<style type="text/css">
.chara{
	margin: 0 auto;
	width:160px;
	height:114px;
	font-size:20px;
	line-height:30px;
	color:#000000;
	position:absolute;
	left: 80px;
	top:  226px;
    z-index:+1;
	font-family:"新宋体"
    
	}
</style>
</head>

<body>
<body style="background: #e5e0da">
    
    <table style="width: 100%; height: 100%">
    	<tr>
            <td style="height: 42px;">
            </td>
        </tr>
        
        <tr>
            <td style="width: 100%; height:100%" align="center">
                <div style="position: relative; width: 310px; height:380px">
                 <img src="<%=basePath %>module/weixin/images/yichouguo.png" />
                
                <div class="chara">您今天已经抽过，请明天再来</div>
         
                </div>
        
             </td>
        <tr>
        
              
</body>
</html>
</body>
</html>
