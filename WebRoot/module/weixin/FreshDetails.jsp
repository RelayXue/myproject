
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link href="<%=basePath%>module/weixin/css/fangwen1.css"
		rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<head>
		<title>
			<%
				if (type != null && type.equals("two")) {
			%> <s:property
				value="buNews.fullname" /> <%
 	} else {
 %> 乌镇发布：<s:property
				value="buNews.fullname" /> <%
 	}
 %>
		</title>
	</head>

	<body>
		<div class="chara">
			<p>
				<s:property value="buNews.fullname" />
			</p>
		</div>
		<div class="chara1">
			<s:date name="buNews.createdate" format="yyyy-MM-dd" />
			&nbsp;
			<%
				if (type != null && type.equals("two")) {
			%>
			<a style="color: #969696" href="javascript:void(0)">来自乌镇发布</a>
			<%
				} else {
			%>
			<a style="color: #969696" href="javascript:void(0)">乌镇发布</a>
			<%
				}
			%>
			<div>
				<div class="pic">
					<img src="<%=basePath%>upload/B_<s:property  value="buNews.img1"/>" />

				</div>
				<div class="chara2" style="color: black;" >
					<s:property value="buNews.content" escape="false" />
				</div>
				<div class="pic2">
					<img date-type="jpg" style="width: 100%"
						src="<%=basePath%>module/weixin/images/weixinCode.jpg" alt="" />
				</div>
				<div class="nav2">
					<div class="chara3">
						阅读 <s:property value="buNews.readnum" />
					</div>
					<div class="nav3">
						<div class="pic5" onclick="Praise('<s:property value="buNews.fuid"/>','<%=type %>')">
							<img src="<%=basePath%>module/weixin/images/chan.png" />
						</div>
						<div class="chara4" id="sp">
							<s:property  value="buNews.praise"/>
						</div>
					</div>
				</div>
	</body>
	<script type="text/javascript">
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
</html>
