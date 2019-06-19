<%@ page import="java.util.*,com.gh.entity.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	BuWeixinconfig buWeixinconfig=(BuWeixinconfig)request.getAttribute("buWeixinconfig");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link href="css/rule.css" rel="stylesheet" type="text/css" />
<head>
    <title>抽奖规则</title>
    <style type="text/css">
    .explain1{
	margin: 0 auto;
	width: 20px;
	height: 215px;
	line-height: 20px;
	border: 1px;
	position: absolute;
	left: 175px;
	top: 40px;
	
	font-size: 18px;
	font-family: "宋体";
	
} 
.explain2{
	margin: 0 auto;
	width: 20px;
	height: 215px;
	line-height: 20px;
	border: 1px;
	position: absolute;
	left: 155px;
	top: 40px;
	word-wrap: break-word;
	word-break: keep-all;
	word-break: break-all;
	font-size: 18px;
	font-family: "宋体";

}

.explain3{
	margin: 0 auto;
	width: 20px;
	height: 215px;
	line-height: 19px;
	border: 1px;
	position: absolute;
	left: 135px;
	top: 40px;
	word-wrap: break-word;
	word-break: keep-all;
	word-break: break-all;
	font-size: 18px;
	font-family: "宋体";

}
.attention1{
	margin: 0 auto;
	width: 20px;
	height: 170px;
	line-height: 15px;
	border: 1px;
	position: absolute;
	left: 100px;
	top: 70px;
	word-wrap: break-word;
	word-break: break-all;
	font-size: 14px;
	font-family: "宋体";
	word-break: keep-all;
} 
.attention2{
	margin: 0 auto;
	width: 20px;
	height: 170px;
	line-height: 15px;
	border: 1px;
	position: absolute;
	left: 85px;
	top: 70px;
	word-wrap: break-word;
	word-break: break-all;
	font-size: 14px;
	font-family: "宋体";
	word-break: keep-all;
} 
    
    </style>
    
</head>
<body style=" background:#e5e0da">
    <table style="width: 100%; height: 100%">
        <tr>
            <td style="height: 42px;">
            </td>
        </tr>
        <tr>
            <td style="width: 100%; height: 100%" align="center">
                <div style="position: relative; width: 310px; height: 319px">
                    <img src="<%=basePath %>module/weixin/images/rule_bk.png" />
                    <% if(buWeixinconfig!=null){
                    	String re=buWeixinconfig.getRegulation();
                    	if(re.length()>12){
                    	%>
                    <div class="explain1" >
                       <%=re.substring(0,12-1) %>
                    </div>
                   <% }if(re.length()>24-1){%> 	
                   <div class="explain2" >
                        <%=re.substring(12,24) %>
                    </div>
                  
                   <%} %>
                     <% if(re.length()>24){%> 	
                     <div class="explain3" >
                         <%=re.substring(24,re.length()) %>
                    </div>
                   <%} %>
                   <%} %>
                    <div class="attention1"  >                                        
                     	 亲们！每天只能摆渡一次哦
                    </div>
                    <div class="attention2"  >                                        
                  		   关注乌镇发布抽奖才有效。
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td align="center" style="padding-top: 40px">
                  <a href="javascript:window.history.go(-1)"><img src="<%=basePath %>module/weixin/images/rule_btn.png"   /></a>
            </td>
        </tr>
    </table>
</body>
</html>
