
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String type=request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>乌镇特产</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>

		<script>
	$(function() {
		var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!recommend', 
			'contentData' : 'pageType=page&&indexPage='+indexPage,  
			'scrollTarget' : $(window),  
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&indexPage='+indexPage;
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
		<div id="xxsdtopx"></div>
		
		<s:iterator value="buNews_list" var="list">
		<div id="xxsd">
			<div class="xxsd">
				<div class="xxsdimg">
					<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>"><img src="<%=basePath%>upload/L_<s:property  value="#list.img1"/>" />
					</a>
				</div>
				<div class="xxsdtext">
					<h3>
						<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>"><s:property  value="#list.fullname"/> </a>
					</h3>
					<span><s:date name="#list.createdate" format="yyyy-MM-dd"  /> </span>
					
						<s:property value="@com.gh.common.SplitChinese@splitStr(content,16)" escape="false"/>
				</div>
			</div>
			<div id="function2">
				<div class="read">
					阅读  <s:property value="#list.readnum"/>
				</div>
				<div class="chan">
					<%--<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=Recommend"><img src="<%=basePath%>module/weixin/images/chan-1.png" />
					</a> <s:property  value="#list.praise"/>--%>
				</div>
				<div class="correction">
					<a href="<%=basePath%>fnews!recommendDetails?id=<s:property  value="#list.fuid"/>">详情 <img src="<%=basePath%>module/weixin/images/jiantou.png" />
					</a>
				</div>
			</div>
			<div class="xxsdbj">
				<img width="100%" src="<%=basePath%>module/weixin/images/xxsd-bj2.jpg" />
			</div>
		</div>
		 </s:iterator>
	
		<div id="content">
		
		
		</div><!--
		
			<div   id="loading" style="font-size: 30px;" align="center" style="display: none;">
				正在努力加载中... 请稍等!
			</div>
			<div  style="font-size: 36px;" align="center"  id="nomoreresults" >
				没有更多信息了
			</div>
		
	
	--></body>

</html>