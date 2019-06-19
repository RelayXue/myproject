<%@ page language="java" import="java.util.*,com.gh.entity.*,com.gh.common.SplitChinese,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<BuNews> buNews_list=(List<BuNews>)request.getAttribute("buNews_list");
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link href="<%=basePath%>module/weixin/css/news.css" rel="stylesheet" type="text/css" />
	<head>
		<title></title>
	</head>
	<body>
		<div class="container">
		<%if(buNews_list!=null&&buNews_list.size()>0){   
			for(int a=0;a<buNews_list.size();a++){
		%>
			<div class="item">
				<a href="<%=basePath%>fnews!showNews?id=<%=buNews_list.get(a).getFuid() %>  ">
					<div class="right"> 
						<div class="font">
						<%=buNews_list.get(a).getFullname() %>   <span  style="font-size: 12px;font-weight: normal;"><%=df.format(buNews_list.get(a).getCreatedate()) %></span> 	 
						</div>
						<div class="content">  
						<%=SplitChinese.splitStr(buNews_list.get(a).getContent(),13)   %>
						</div>
					</div> 
				</a> 
			</div>
			<div style="background-color: #b5b5b5; height: 1px; width: 100%" ></div>
		<%		
			}
		}
		%>
			
		</div>
	</body>
</html>
