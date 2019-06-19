
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=request.getParameter("type");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>自驾攻略</title>
		
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script >
		
		 function Praise(id,type){
			 $.ajax({
				    url: "<%=basePath%>fnews!Praise?time=" + new Date(),
				    type:"post",
				    data:"id="+id+"&re=summary&type=<%=type %>",
				    async: false,
				    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
				    dataType: "text",
				    success: function(data) {
				        if(data!=null&&data!='error'){
				        	$("#sp").html(data);
				        }
				    }
				});
			 
		 }
		</script>
	</head>
	<body>
		<div id="details">
			<%--<h3>
				<s:property value="buNews.fullname" />
			</h3>
			--%><%if(type!=null&&type.equals("004004002")){ %>
			<div id="detailsimg">
				<%--<img width="100%" src="<%=basePath%>upload/B_<s:property  value="buNews.img1"/>" /> 
			--%>
			<a href="<%=basePath%>module/weixin/BaiduMap.jsp?st=${buNews.remark}" style="font-size: 16px; color: blue; background-color: #fff; padding: 8px; border: 1px solid #ccc;">进入百度地图导航  ➤</a>
			</div>
			<%} %>
			<div id="detailsintroduction">
				<s:property value="buNews.content" escape="false"/>
			</div>
		</div>
		<%--<div id="function">
			<div class="read">
				阅读
				<s:property value="buNews.readnum" />
			</div>
			<div class="chan">
				<a href="javascript:Praise('<s:property  value="buNews.fuid"/>','<%=type %>')"><img src="<%=basePath%>module/weixin/images/chan.png" />
				</a> <span id="sp"><s:property   value="buNews.praise"/></span> 
			</div>
			<div class="shareit"></div>
			<div class="correction"></div>
		</div>
	--%></body>

</html>