<%@ page language="java" contentType="text/html; charset=utf-8" isErrorPage="true" pageEncoding="utf-8"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%
/** *//**
* 本页面是在客户查找的页面无法找到的情况下调用的
*/
response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:v>
  <head>
    <title>提示页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<style type="text/css">
v\:* {
	behavior: url(#default#vml);
}

body,div,span,li,td,a {
	color: #222222;
	font-size: 14px !important;
	font-size: 14px;
	font-family: tahoma, arial, 'courier new', verdana, sans-serif;
	line-height: 19px;
}

a {
	color: #2c78c5;
	text-decoration: none;
}

a:hover {
	color: red;
	text-decoration: none;
}
</style>
	</head>
  
  <body style='text-align:center;margin:90px 20px 50px 20px'>
<div style='margin:auto; width:450px; text-align:center; background:#fff'>
<v:roundrect style='text-align:left; display:table; margin:auto; padding:15px; width:450px; height:210px; overflow:hidden; position:relative;' arcsize='3200f' coordsize='21600,21600' fillcolor='#fdfdfd' strokecolor='#D9EDF6' strokeweight='4px'>
<table width='100%' cellpadding='0' cellspacing='0' border='0' style='padding-bottom:6px; border-bottom:2px #D9EDF6 solid'>
  <tr>
    <td><b>提示页</b></td>
    <td align='right' style='color:#f8f8f8'>--- oriental_pearl</td>
  </tr>
</table>
<table width='100%' cellpadding='0' cellspacing='0' border='0' style='word-break:break-all; overflow:hidden'>
    <tr>
        <td width='73' valign='top' style='padding-top:4px'><span style='font-size:18px; zoom:4; color:#FF0000;font-family:webdings'></span></td>
        <td width="362" valign='top' style='padding-top:17px'>
          <SPAN style='margin-bottom:22px; font-size:18px; font-weight:600'>对不起，您无权限访问当前页面，请联系系统管理员！</SPAN>
            <ul>
            <li><a href='javascript:window.history.back();'>返回上一层</a></li>
            </ul>
        </td>
    </tr>
</table>
</v:roundrect>
</div>
</body>
</html>
