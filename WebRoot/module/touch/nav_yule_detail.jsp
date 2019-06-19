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
		<title></title>		
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

<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
    	<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
   		<script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
<script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>
    <style type="text/css">
        #map
        {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0px;
            left: 0px;
            background-color: #f3f5ed;
            background-image: none;
        }
        .icosp{ background-image: url("img/sor.png"); background-repeat: no-repeat; width: 22px; height: 37px;overflow: hidden; color: #fff; text-align: center;}
    </style>
<script type="text/javascript">
	//地图相关参数
    var lon = 121.4;
    var lat = 31.3;

    var map = null;
    var vectorLayer;
    var layer1, layer2, layer3, layer4, layer5;
    var featureStyle = null, featureStyle2 = null, meatureStyle = null;
    var mapParamS;
    var markerL;
	$(function(){
		Init();
		$("#menu li").click(function(){setTab($(this).children("a").attr("href")); });
		$("#menu li").eq(0).click();
		var parms = new Object();
		parms.id = "<%=request.getParameter("id")%>";
		$.post("touch_main_showYuLeDetail", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialog(hint);
			if (code == 1) {
				var o = json.o;
				$("#name").html(o.fullname);
				$("#category").html("【"+getCategoryName(o.type)+"】");
				$("#phone").html(getNullString(o.phone, '-'));
				$("#address").html(getNullString(o.address, '-'));
				$("#consumption").html(getNullString(o.consumption, '-'));
				$("#toAddress").click(function(){ window.location.href="<%=curl%>nav.jsp?flag=1&to_id="+o.fuid+"&category=002016&tab=0"; });
				if(o.dimages != undefined && o.dimages.length > 0){
					var imgs = o.dimages.split(",");
					for(g = 0;g<imgs.length;g++){
						$("#pictures").append('<div class="img-thumbnail"><img class="img-responsive" src="<%=basePath%>upload/B_'+imgs[g]+'" style="width:710px;" /></div>');
					}
				}else{
					$("#pictures").append('<div class="img-thumbnail"><img class="img-responsive" src="<%=basePath%>images/default.jpg" style="width:710px;" /></div>');
				}
				owlCarousel();
				//填充简介
				$("#content li").html(getNullString(o.introduction, ""));
				//添加点位
 				var icon2 = new SIcon(null, new SSize(22, 37));;
	    		icon2.GetDiv().innerHTML = '<div class="icosp" ></div>';
	    		icon2.SetOpacity(0.2);
	    		var mm = new SMarker(new SLonLat(o.fx, o.fy), icon2, "tj");
	    		markerL.AddMarker(mm);
	    		map.SetCenter(new SLonLat(o.fx, o.fy), 4);
				
				//填充列表
				var more = $.parseJSON(json.os["more"]);
				if(more.length > 0){
					var html = '';
					for(i=0;i<more.length;i++){
						var url = '<%=curl%>nav.jsp?flag=1&to_id='+more[i].fuid+'&category=002016&tab=0';
						html += '<li><div class="left"><i class="fa fa-map-marker"></i></div><div class="text"><h3>'+more[i].fullname+'</h3><p>'+more[i].address+'<br />电话：'+more[i].phone+'</p></div><div class="right"><a class="btn btn-lg btn-yule" onclick=window.location.href="<%=curl%>nav_yule_detail.jsp?id='+more[i].fuid+'"><i class="fa fa-align-justify"></i>详情</a><a class="btn btn-lg btn-gouwu" href="'+url+'"><i class="fa fa-level-up"></i>前往</a></div></li>';
					}
					$("#more_list").html(html);
				}else{
					$("#more_list").html('<span style="font-size: 20px; display: block;padding: 30px 0; text-align: center;">没有更多推荐的娱乐设施</span>');
				}
			}
		}, "json");
	});
	
	function getCategoryName(code){
		var tt = "";
		for(p in basetemp){ 
			if(basetemp[p].code == code){
				return basetemp[p].fullname;
			}
		}
	}
	
	function isNull(str){
		if(str == undefined || str == "" || str == null || str == "null" || str == "undefined"){
			return true;
		}
		return false;
	}
	function getNullString(str, def_str){
		if(isNull(str)){
			return def_str;
		}
		return str;
	}
	
	function setTab(id){
		$("#content, #more_list").hide();
		$("#"+id).show();
	}
	
	//图片滚动
	function owlCarousel(){
	  $(".owl-carousel").owlCarousel({
      navigation : false, // Show next and prev buttons
      slideSpeed : 300,
      paginationSpeed : 400,
      singleItem:true
      // "singleItem:true" is a shortcut for:
      // items : 1, 
      // itemsDesktop : false,
      // itemsDesktopSmall : false,
      // itemsTablet: false,
      // itemsMobile : false
  	  });
	}
	//顶部标签切换
	function topSetTab(i){
		$("#box0, #box1").hide();
		$("#box"+i).show();
		$("#box"+i).css("visibility", "visible");
		$(".nav li").removeClass("active");
		$(".nav li").eq(i).addClass("active");
	}
	
</script>
<style type="text/css">
</style>
</head>
<body class="sub">
<header class="text-center">
            <div class="top-left"><a href="javascript:history.go(-1)"><i class="fa fa-angle-left"></i></a></div>
            <div class="tab_top">
            	<ul class="nav">
                	<li onclick="topSetTab(0)" class="active"><a><i class="fa fa-file-text-o"></i>详情</a></li>
                    <li onclick="topSetTab(1)"><a><i class="fa fa-globe"></i>地图</a></li>
                </ul>
            </div>
</header>


<div class="container intro" id="box0">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="owl-carousel owl-theme owl-carousel-init" id="pictures">
			</div>
			<h2><span id="name"></span><small id="category"></small></h2>
			<p>人均消费：<span id="consumption"></span></p> 
			<p>店铺电话：<span id="phone"></span></p>
			<p>地址：<span id="address"></span></p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="list-1">
				<ul class="tabs" id="menu">
					<li class="active"><a href="content" data-toggle="tab">详细</a></li>
					<li><a href="more_list" data-toggle="tab">更多娱乐</a></li>
				</ul>
				<ul id="content" style="padding: 10px 10px -1 10px;">
					<li style="line-height: 30px;font-size: 18px"></li>
				</ul>
				<ul id="more_list"></ul>
				<!-- <div class="more"><a href="">展开更多推荐<i class="fa fa-angle-down"></i></a></div> -->
			</div>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-12 col-sm-12">
			<a class="btn btn-hg btn-yule" id="toAddress"><i class="fa fa-level-up"></i>前往路径</a>
		</div>
	</div>
</div>

<div id="box1" style="visibility: hidden;">
<div id="map"></div>
<div class="tools-zoom">
    <div class="map-zoom">
		<div class="z-in" onclick="map.ZoomIn()"><i style="margin: 10px 8px;"></i></div>
		<div class="z-out" onclick="map.ZoomOut()"><i style="margin: 10px 8px;"></i></div>
	</div>
</div>
</div>
</body>
</html>