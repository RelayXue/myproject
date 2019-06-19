<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/phone/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="<%=curl %>css/common.css">
<link rel="stylesheet" type="text/css" href="css/valet-parking.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/k_tables.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=curl %>js/common_business.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>

<title>乌镇-交通播报-实时路况播报</title>
<style type="text/css">
</style>
<script type="text/javascript">
	//页面索引
	var index = 1;
	
	//滚动加载标识 用于判断当前是否在加载中
	var scroll_loading = false;
	function loadData(){
		scroll_loading = true;
		var parms = new Object();
		parms.indexPage = index;
		$.post("phone_traffic_syncTrafficList", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialog(hint);
			if (code == 1) {
				var o = json.o;
				var html = "";
				for(i=0;i<o.length;i++){ genHTML(o[i]); }
				index++;
				scroll_loading = false;
			}
		}, "json");
	}
	
	function genHTML(item){
		var hm = "";
		hm += '<ul class="bor">';
		//hm += '<li class="title3">'+item.fullname+'</li>';
		
		var img_html = "";
		if(item.img1 != undefined){
			hm += '<li><p class="dt"><img src="<%=basePath%>upload/'+item.img1+'"></p></li>';
		}
		hm += '<li>'+item.content+'</li>';
		var click_like_id = randomStr();
		var praise_count = ((item.praise == undefined)?'0':item.praise);
		hm += '<li><a href=javascript:clickLike("'+click_like_id+'","'+item.fuid+'","1")><span id="'+click_like_id+'">12</span></a><p class="date">发于'+parseDate(item.createdate)+'</p></li>';
		hm += '</ul>';
		$("#main").append(hm);
		setLikeImg(click_like_id, item.fuid, praise_count);
	}


	//滚动加载
	function aa(){
	    var winH = $(window).height(); //页面可视区域高度 
	    $(window).scroll(function () { 
	        var pageH = $(document.body).height(); 
	        var scrollT = $(window).scrollTop(); //滚动条top 
	        var aa = (pageH-winH-scrollT)/winH;
	        if(aa<0.02 && !scroll_loading){
				loadData();
	        } 
	    }); 
	}
	
	$(function(){
		aa();
		loadData();
	});
</script>
</head>

<body>
	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title">实时路况播报</div>
           <div id="xq"><a href="<%=curl %>parking_service_list.jsp" style="font-size: 0.8em">停车服务</a></div>
        </div>
        <div id="main"></div>
    </div>
    
</body>
</html>
