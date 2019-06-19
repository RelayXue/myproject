<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>jQuery ScrollPagination</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>
		 
		<script type="text/javascript">
	$(function() {
		var indexPage='1';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!fresh', // the url you are fetching the results
			'contentData' : 'indexPage='+indexPage, // these are the variables you can pass to the request, for example: children().size() to know which page you are
			'scrollTarget' : $(window), // who gonna scroll? in this example, the full window
			'heightOffset' : 10, // it gonna request when scroll is 10 pixels before the page ends
			'beforeLoad' : function() { // before load function, you can display a preloader div
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { // after loading content, you can use this function to animate your new elements
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 100) { // if more than 100 results already loaded, then stop pagination (only for testing)
					alert(1);
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
		});

		// code for fade in element by element
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
	</head>
	<body>
		<div id="scrollpaginationdemo">
		<div id="filter-container">
			<div id="content">
				 
			</div>
			
			</div>
			<div class="loading" id="loading">
				正在努力加载中... 请稍等!
			</div>
			<div class="loading" id="nomoreresults">
				没有更多信息了
			</div>
		</div>
	</body>
</html>
