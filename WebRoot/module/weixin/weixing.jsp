<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId = request.getParameter("otherId");
	String name = request.getParameter("name");
	name = name == null ? "" : name;
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>js/ckeditor/ckeditor.js"></script>  
    <script type="text/javascript" src="<%=basePath%>js/ckeditor/config.js"></script>  
    <script type="text/javascript">
	    $(document).ready(function(){  
	    	CKEDITOR.replace('buNews.content'); 
	    });  
    </script>
  </head>
  
  <body>
    <form action="buNews!edit" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td >
				&nbsp;&nbsp;<strong>新闻内容：</strong>
			</td>
			</tr>
			
			
			<tr>
			<td >
			</td>
			<td>
				<textarea  id="content" name="buNews.content" >${buNews.content}</textarea>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="hidden" id="fuid" name="buNews.fuid" value="${buNews.fuid} "/>&nbsp;
			</td>
			<td>
				<input type="submit" class="btn btn-primary" value="提交"/>&nbsp;&nbsp;
				<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</form>
  </body>
</html>
