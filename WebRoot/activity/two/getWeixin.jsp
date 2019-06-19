<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title></title>
  </head>
  <body>
  	<!-- 服务器-->
  	<form action="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fpoetryChallenge!getWeiXinId&response_type=code&scope=snsapi_base&state=a1#wechat_redirect" id="myForm" method="post"></form>
     <!-- 本地 
     <form action="<%=basePath%>poetryChallenge!getWeiXinId" method="post" id="myForm"> -->
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#myForm").submit();
    	})
    </script>
  </body>
</html>
