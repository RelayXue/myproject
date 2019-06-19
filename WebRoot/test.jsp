<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="http://demo.jb51.net/jslib/jquery/jquery1.3.2.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"> 
/*$(document).ready(function(){ 
// Scroll page to the bottom 
$('a.scrollToBottom').click(function(){ 
$('html, body, .content').animate({scrollTop: $(document).height()}, 300); 
return false; 
}); 
}) */
	$(function(){
		//获取外网IP
		xml=new ActiveXObject("Microsoft.XMLHTTP");
		xml.open("GET","http://city.ip138.com/city0.asp",false);
		xml.send();
		kk=xml.ResponseText;
		i=kk.indexOf("[");
		ie=kk.indexOf("]");
		ip=kk.substring(i+1,ie);
		document.write("<span style='color:red;font-size:12;' cursor='hand'>您的IP地址是:" + ip + "</span>");
	})
</script> 
  </head>
  
  <body>123
  <%=request.servervariables("REMOTE_HOST")%>
  </body>
</html>
