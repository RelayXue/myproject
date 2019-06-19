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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="<%=curl %>css/common.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/tourist-share2.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=curl %>js/common_business.js"></script>
<title>乌镇-交通播报</title>
</head>

<body> 
	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title" style="overflow: hidden;"><s:property value="news.fullname"/></div>
        </div>
        <div id="main">
        	<div id="large">
            	<span id="dt"><img src="<%=basePath %>upload/L_<s:property value="news.img1"/>" style="width: 100%;"></span>
                <div>
                	<a href="javascript:clickLike('praise','<s:property value="news.fuid"/>','1')" id="praise"></a>
                    <!--<a href="#">
                    	<span><img src="<%=curl %>images/menu2.png"></span>
                        <span>分享</span>
                    </a>
                    --><!--<a href="#" class="f_r"><img src="<%=curl %>images/fd.png"></a>
                --></div>
                <div class="bg"></div>
            </div>
            <div id="text">
            	<div style="border-bottom: 1px solid #d2d0cc; padding: 8px 0;">
                	<p style="font-size: 22px; color:#000;"><s:property value="news.fullname"/></p>
                </div>
            	<div style="color: #3F3F3F; padding: 8px 0; ">
                	<p><s:property value="news.content"  escape="false"/></p>
                	<br />
                    <p style="font-size: 14px; color: #777;">发于<s:property value="news.createdate" /></p>
                </div><!--
                <div class="pl">
                	<p class="title">评论（10条）</p>
                    <p class="nr">
                    	<span class="nr_title">超赞的，我喜欢</span>
                        <span class="date">来自乌镇地图2014-10-10 17:00</span>
                    </p>
                </div>
            --></div>
        </div><!--
        <div id="footer">
        	<span><input type="text" placeholder="输入评论内容"></span>
            <span><img src="images/tx.png"></span>
        </div>
    --></div>
</body>
</html>
<script type="text/javascript">
	setLikeImg("praise", "${news.fuid}", "${news.praise}");
</script>
