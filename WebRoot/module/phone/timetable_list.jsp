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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/traffic-reporter.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/k_tables.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>

<title>乌镇-交通播报-交通时刻表</title>
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
		$.post("phone_traffic_timetableList", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialog(hint);
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var html = "";
				for(i=0;i<o.length;i++){ html += genHTML(o[i]); }
				$("#content").append(html);
				index++;
				scroll_loading = false;
			}
			
		}, "json");
	}
	
	function genHTML(item){
		var hm = "";
		hm += '<li onclick=window.location.href="<%=curl%>phone_traffic_detail?id='+item.fuid+'">';
		hm += '<p class="p1" style="overflow: hidden;"><span style="padding-left: 10px;width:auto; ">'+item.fullname+'</span></p>';
		hm += '<p class="p2"><span class="s1"><img src="<%=curl %>images/hand.png"></span><span style="padding:0 10px 0 8px;">'+((item.praise.length == 0)?'0':item.praise)+'</span><span class="s2"><img src="<%=curl %>images/jt3.png"></span></p>';
		hm += '</li>';
		return hm;
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
           <div id="title">交通时刻表</div>
           <div id="xq"><a href="<%=curl %>parking_service_list.jsp">停车服务</a></div>
        </div>
        <div id="hover">
        	<div>
            	<ul id="content"></ul>
            </div>
        </div>
    </div>
</body>
</html>
