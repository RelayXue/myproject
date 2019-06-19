<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>抽奖活动</title>
<style type="">
.chara{
	margin: 0 auto;
	width:200px;
	height:22px;
	font-size:18px;
	line-height:22px;
	color:#000000;
	position:absolute;
	left: 60px;
	top:  70px;
    z-index:+1;
	font-family:"新宋体"
	
	}
.chara1{
	margin: 0 auto;
	width:180px;
	height:65px;
	font-size:14px;
	line-height:20px;
	color:#000000;
	position:absolute;
	left: 70px;
	top:  100px;
    z-index:+1;
	font-family:"新宋体"
	letter-spacing:-60px;
	text-align:left;} 
	}
</style>

</head>

<body style="background: #e5e0da">
     <table style="width:100%; height:100%">
         <tr>
            <td style="height: 42px;">
            </td>
        </tr>
        <tr>
            <td style="width:100%; height:100%" align="center">
                <div style="position: relative; width:310px; height:380px">
                 <img src="<%=basePath  %>module/weixin/images/zhongjiang.png"/>
                  
                 <div class="chara">${buWeixinconfig.winningInfo }</div>
         
                </div>
        
             </td>
        </tr>
      </table>
        
              
</body>
</html>

