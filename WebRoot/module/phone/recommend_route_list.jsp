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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/recommend-route.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<title>游在乌镇-<%=request.getParameter("title") %></title>
<script type="text/javascript">
	var category = "<%=request.getParameter("category")%>";
	var cur_page_index = 1;
	var scroll_loading = false;
	function loadData(meo){
		scroll_loading = true;
		var parms = new Object();
		parms.category = category;
		parms.indexPage = cur_page_index;
		$.post("phone_traffic_recommendRouteList", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialog(hint);
			if (code == 1) {
				var o = json.o;
				var html = "";
				for(i=0;i<o.length;i++){
					html += '<li onclick=window.location.href="<%=curl%>phone_traffic_detail?id='+o[i].fuid+'"><p class="p1" style="display: block; overflow: hidden;"><span>'+o[i].fullname+'</span></p><p class="p2"><span class="s1"><img src="images/hand.png"></span>&nbsp<span>'+((o[i].praise==undefined)?0:o[i].praise)+'</span><span class="s2">详情<img src="images/jt4.png"></span></p></li>';
				}
				$("#list").append(html);
				cur_page_index++;
				scroll_loading = false;
			}
		}, "json");
	}
	
	//滚动加载
	function aa(){
	    var winH = $(window).height(); //页面可视区域高度 
	    $(window).scroll(function () { 
	        var pageH = $(document).height(); 
	        var scrollT = $(window).scrollTop(); //滚动条top 
	        var aa = (pageH-winH-scrollT)/winH;
	        if(aa<0.02 && !scroll_loading){
				loadData(1);
	        } 
	    }); 
	}
	
	/**参数说明：
	 * 根据长度截取先使用字符串，超长部分追加…
	 * str 对象字符串
	 * len 目标字节长度
	 * 返回值： 处理结果字符串
	 */
	function cutString(str, len) {
		//length属性读出来的汉字长度为1
		if(str.length*2 <= len) {
			return str;
		}
		var strlen = 0;
		var s = "";
		for(var i = 0;i < str.length; i++) {
			s = s + str.charAt(i);
			if (str.charCodeAt(i) > 128) {
				strlen = strlen + 2;
				if(strlen >= len){
					return s.substring(0,s.length-1) + "...";
				}
			} else {
				strlen = strlen + 1;
				if(strlen >= len){
					return s.substring(0,s.length-2) + "...";
				}
			}
		}
		return s;
	}

	$(function(){
		loadData(1);
		aa();
	});	
</script>
<style type="text/css">
	/* dialog */
	.dialog {
		position: fixed;
		background-color: #000;
		color: #FFF;
		text-align: center;
		padding: 15px;
		-moz-border-radius: 8px;      /* Gecko browsers */
		-webkit-border-radius: 8px;   /* Webkit browsers */
		border-radius:8px;
		filter:alpha(Opacity=70);
		-moz-opacity:0.7;
		opacity: 0.7;
		display: none;
		font-family: "微软雅黑", "幼圆", "黑体";
		font-size: 12px;
		z-index:99;
	}
</style>
</head>

<body>
	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title"><%=request.getParameter("title") %></div>
           <!--<div id="xq"><a href="#">更多</a></div> -->
        </div>
        <div id="hover">
        	<div>
            	<ul id="list">

                </ul>
            </div>
        </div>
    </div>
</body>
</html>
	