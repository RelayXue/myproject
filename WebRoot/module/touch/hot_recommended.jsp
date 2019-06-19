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
		<script src="<%=curl %>js/jquery.min.js"></script>
		<script src="<%=curl %>js/bootstrap.min.js"></script>
		<script type="text/javascript">
			function searchData(){
				var key = $.trim($("#search_content").val());
				if(key.length > 0){
					var parms = new Object();
					parms.key = key;
					$.post("touch_main_mapSearch", parms, function(json) {
						var code = json.code;
						var hint = json.hint;
						if (code == 1) {
							var o = $.parseJSON(json.o);
							var html = '';
							for(i=0;i<o.length;i++){
								html += '<li><a href="<%=curl%>map_search_results.jsp?key='+o[i].fullname+'">'+o[i].fullname+'<small>'+o[i].address+'</small><i class="goto"></i></a></li>';
							}
							$("#search_list").html(html);
						}else{
							$("#search_list").html('<li><a>没有相关数据</a></li>');
						}
					}, "json");
				}else{ $("#search_list").html(""); }
			}
			
			$(function(){
				$.post("touch_main_hotRecommended", {}, function(json) {
					var code = json.code;
					var hint = json.hint;
					if (code == 1) {
						var o = $.parseJSON(json.o);
						var html = '';
						for(i=0;i<o.length;i++){
							$("#hot").append('<li><a href="<%=curl%>map_search_results.jsp?key='+o[i].fullname+'">'+o[i].fullname+'<i class="goto"></i></a></li>');
						}
					}
				}, "json");
			});
			
		</script>
</head>
<body>
<header class="text-center">
	<div class="top-left"><a href="javascript:history.go(-1)"><i class="fa fa-angle-left"></i></a></div>
	<div class="search-bar">
    	<div class="form-group">
			<form>
            	<input class="search" type="search" placeholder="桐乡市乌镇..." id="search_content" onkeyup="searchData()"><button type="button" class="btn btn-lg btn-search" onclick="toResults()">搜索</button>
            </form>
		</div>
    </div>
</header>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<ul class="slist nav" id="search_list"></ul>
		</div>
	</div>
    <div class="row">
    	<h3 class="section_header fancy">热点关键字</h3>
    </div>
    <div class="row">
		<div class="col-md-12 col-sm-12">
			<ul class="slist nav" id="hot">
			</ul>
		</div>
	</div>
</div>

</body>
</html>