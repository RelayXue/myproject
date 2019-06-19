<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
  	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
  </head>
  <body onload="tijiao()">
  <form action="<%=basePath%>Photography!exhibitionWorks" method="post" id="form">
  	<input type="hidden" value="${type}" name='type'/>
  	<input type="hidden" value="${operation}" name='operation'/>
  </form>
  
  <script type="text/javascript">
  function tijiao(){
	  $("#form").submit();
  }
  </script>
  </body>
</html>
