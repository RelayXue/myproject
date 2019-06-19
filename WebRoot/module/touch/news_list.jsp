<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/touch/";
	String category = request.getParameter("category");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
<head>

		<!-- Basic -->
		<meta charset="utf-8">
		<title>乌镇国际旅游区</title>		
		<meta name="keywords" content="乌镇" />
		<meta name="description" content="乌镇旅游">
		<meta name="author" content="taoguang.com">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		
		<link rel="stylesheet" href="<%=curl %>css/bootstrap.css">
		<link rel="stylesheet" href="<%=curl %>css/theme.css">
		<link rel="stylesheet" href="<%=curl %>css/k_tables.css">
		
		<script type="text/javascript" language="javascript" src="<%=basePath%>JsContext!DictionaryData"></script>
		<script src="<%=curl %>js/jquery.min.js"></script>
		<script src="<%=curl %>js/bootstrap.min.js"></script>
		<script src="<%=curl %>js/owl.carousel.min.js"></script>
		<script src="<%=curl %>js/k_tables.js"></script>
		<script src="<%=curl %>js/custom.js"></script>

		<!--[if IE]>
			<link rel="stylesheet" href="css/ie.css">
		<![endif]-->

		<!--[if lte IE 8]>
			<script src="js/respond.js"></script>
			<script src="js/excanvas.js"></script>
		<![endif]-->

<script type="text/javascript">
	$(function(){
		var category = "<%=category%>";
		$("#menu li").eq((category-1)).attr("class", "active");
		$("#menu li").eq((category-1)).attr("href", "javascript:void(0)");
	});
</script>
<style type="text/css">
</style>
</head>
<body class="sub">
<header class="intro text-center">
            <div class="top-left"><a href="<%=basePath %>touch/index"><i class="fa fa-angle-left"></i></a></div>
            <div class="tab_top"><h2>
            
            	<s:if test="category==1">
            		乌镇地理
            	</s:if>
            	<s:elseif test="category==2">
            		乌镇历史
            	</s:elseif>
            	<s:elseif test="category==3">
            		乌镇民俗
            	</s:elseif>
            	<s:elseif test="category==4">
            		乌镇故事
            	</s:elseif>
            	<s:elseif test="category==5">
            		乌镇名人
            	</s:elseif>
            	<s:elseif test="category==6">
            		乌镇保护
            	</s:elseif>
            </h2></div>
            <div class="top-right"><a href="<%=curl %>hot_recommended.jsp"><img class="img-responsive" src="img/icon-search.png" alt=""></a></div>
                
<div class="container nav">
	<ul class="nav sub-nav" id="menu">
		<li><a href="<%=curl %>touch_main_showNewsList?category=1">乌镇地理</a></li>
		<li><a href="<%=curl %>touch_main_showNewsList?category=2">乌镇历史</a></li>
		<li><a href="<%=curl %>touch_main_showNewsList?category=3">乌镇民俗</a></li>
		<li><a href="<%=curl %>touch_main_showNewsList?category=4">乌镇故事</a></li>
		<li><a href="<%=curl %>touch_main_showNewsList?category=5">乌镇名人</a></li>
		<li><a href="<%=curl %>touch_main_showNewsList?category=6">乌镇保护</a></li>
	</ul>
</div>
</header>

<div class="container intro">
	<s:if test="category!=5">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<s:iterator value="news_list" var="list">
	        	<article class="post post-large">
	        	<h2 class="section_header fancy" style="background-image:url(img/lined.jpg); background-repeat: repeat-x;background-position: 0 center;"><span style="padding: 0 15px;background-color: rgb(223, 223, 223); "><s:property value="#list.fullname" /></span></h2>
				<div class="owl-carousel owl-theme owl-carousel-init">
					<s:generator val="#list.img1" separator="," id="imgs"></s:generator>
					<s:iterator status="st" value="#request.imgs" id="img_list">   
					   <div class="img-thumbnail"><img class="img-responsive" src="<%=basePath %>upload/<s:property value="#img_list"/>"  /></div>
					</s:iterator>   
				</div>
	            <p>
	            <s:property value="#list.content" escape="false"/>
	            </p>
	            </article>
			</s:iterator>
		</div>
  </div>
</s:if>
<s:elseif test="category==5">
	<s:iterator value="news_list" var="list">
		<div class="col-md-4 col-sm-4">
			<article class="post post-small">
			<div class="img-thumbnail"><img class="img-responsive" src="<%=basePath %>upload/<s:property value="#list.img1" />" style="height:360px; width:255px;" alt=""></div>
            <h3><s:property value="@com.gh.common.SplitChinese@splitStr(#list.fullname,8)"/> <small>(<s:property value="#list.remark"/>)</small></h3>
			<p><s:property value="#list.content" /></p>
            <a class="btn btn-lg btn-wuzhen" href="<%=curl %>touch_main_showNewsMingRenDetail?id=<s:property value="#list.fuid"/>" style="width: 100%">经典概述 ></a>
			</article>
		</div>
	</s:iterator>
</s:elseif>
</div>
</body>
</html>