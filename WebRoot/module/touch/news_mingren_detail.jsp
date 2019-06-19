<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/touch/";
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

</head>
<body class="sub">
<header class="intro text-center">
	<div class="top-left"><a href="javascript:history.go(-1)"><i class="fa fa-angle-left"></i></a></div>
	<div class="tab_top"><h2>乌镇名人</h2></div>
	<div class="top-right"><a href="#"><img class="img-responsive" src="img/icon-search.png" alt=""></a></div>
</header>

<div class="container intro">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<article class="post post-small">
			<div class="thumbnail pull-left"><img class="img-responsive" width="200" src="<%=basePath %>upload/<s:property value="news.img1"/>" alt=""></div>
            <div class="content">
				<h3><s:property value="news.fullname"/><small>(<s:property value="news.remark"/>)</small></h3>
				<p><s:property value="news.content"  escape="false"/></p>
			</div>
			</article>
		</div>
	</div>
</div>
</body>
</html>