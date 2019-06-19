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
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/comprehensive3.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<title>乌镇-路线信息</title>
<script type="text/javascript">
	$(function(){
		var o = localStorage.getItem("route_detail_json");
		if(o != null){
			var json = $.parseJSON(o);
			var html = '';
			$("[name='start']").html(json.start_name);
			$("[name='end']").html(json.end_name);
			for(i=0;i<json.roadText.length;i++){
				html += '<li><img src="images/yu.png">'+json.roadText[i]+'</li>';
			}
			$("#content").html(html);
		}
	});
	
</script>
</head>

<body>
	<div id="container" style="overflow: hidden;">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title">路线信息</div>
           <div id="xq"><a href="<%=curl %>index.jsp?route=1"></a></div>
        </div>
        <div id="hover" style="margin-bottom: 10px;">
        	<div id="cont">
            	<div class="cont_title">
                	<p><span name="start"></span></p>
                	<p><span>⇓</span></p>
                	<p><span name="end"></span></p>
                </div>
                <div id="list">
                    <ul>
                    	<li class="title tit1"><img src="images/qi.png"><tt name="start"></tt></li>
                    	<tt id="content"></tt>
                        <li class="title tit3"><img src="images/wei.png"><tt name="end"></tt></li>
                    </ul>
                </div>
            </div>
        </div><!--
        <div id="footer" >
        	<a href="javascript:void(0)" class="bor"><img src="images/tx.png">点评</a>
            <a href="javascript:void(0)"><img src="images/menu2.png">分享</a>
        </div>
    --></div>
</body>
</html>
