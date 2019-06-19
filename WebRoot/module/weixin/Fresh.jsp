
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=(String)request.getAttribute("type");
	String text="新鲜速递";
	if(type!=null&&type.equals("two")){
		text="最新资讯";
	}
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>
		 <%if(type!=null&&type.equals("two")){ %>
		  最新资讯
		   <%}else{ %>
		   	新鲜速递
		   <%} %>
		</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>
		 
		<script type="text/javascript">
	$(function() {
		var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!fresh', 
			'contentData' : 'pageType=page&type=<%=type%>&indexPage='+indexPage,  
			'scrollTarget' : $(window),  
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&type=<%=type%>&&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 100) { 
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
		});

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};

	});
</script> 
 
		<style>
</style>
	</head>
	<body>
	<s:iterator value="buNews_list" var="list">
		<div id="xxsd-2">
			<div class="title-1">
				<h3>
					<a href="<%=basePath%>fnews!freshDetails?id=<s:property  value="#list.fuid"/>"><s:property  value="#list.fullname"/> </a>
				</h3>
				<span><s:date name="#list.createdate" format="yyyy-MM-dd" /> </span>
			</div>
			<div class="xxsd-2img">
				<a href="<%=basePath%>fnews!freshDetails?id=<s:property  value="#list.fuid"/>&type=<%=type %>"><img src="<%=basePath%>upload/B_<s:property  value="#list.img1"/>" />
				</a>
			</div> 
			<%--<div class="xxsd-2text">
				<s:property value="@com.gh.common.SplitChinese@splitStr(content,16)" escape="false"/> 
			
					
		</div>
			--%><div class="xxsd-2function">
				<div class="read">
					阅读 <s:property  value="#list.readnum"/>
				</div>
				<div class="chan">
					<%--<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=fresh"><img src="<%=basePath%>module/weixin/images/chan-1.png" />
					</a> <s:property  value="#list.praise"/> --%>
				</div>
				<div class="shareit"></div>
				<div class="correction">
					<a href="<%=basePath%>fnews!freshDetails?id=<s:property  value="#list.fuid"/>&type=<%=type %>">详情 <img src="<%=basePath%>module/weixin/images/jiantou.png" />
					</a>
				</div>
			</div>
		</div>
		</s:iterator>
			<div id="content">
				 
			</div>
	</body>

</html>