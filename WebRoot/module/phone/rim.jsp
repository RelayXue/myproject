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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/nearby-locating.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/k_tables.css">

<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<title>游在乌镇-附近查询</title>

<style type="text/css">

#container{background:#f4f1e7;}

#header{width:100%;height:35px;background:#2e3c50;padding:10px 0px;float:left;}
#header div{border-left:5px solid transparent;border-right:5px solid transparent;box-sizing: border-box;}
#header #return{width:10%;height:100%;float:left;}
#header #xq{width:20%;height:100%;float:left;}
#header p{width:100%;height:100%;}
#header a{display:block;width:100%;height:100%;line-height:35px;text-align:center;}
#header #return a{background:url(<%=curl %>images/return.png) center center no-repeat;}
#header #xq a{border-radius:5px;background:#717699;}
/*搜索框*/
#search{float:left;width:70%;height:100%;background:#FFF;border-radius:10px;}
#search span{display:block;width:12%;float:left;height:100%;background:url(<%=curl %>images/search.png) center center no-repeat;}
#search input{width:75%;float:left;height:100%;font-size:0.875em;border-radius:0px 10px 10px 0px;}
#search a{width:13%;height:100%;float:left;background:url(<%=curl %>images/close.jpg) center center no-repeat;}

</style>
<script type="text/javascript">
function clear(){ $("#s_val").val(""); }

function search(){
	var val = $.trim($("#s_val").val());
	if(val.length > 0){
		window.location.href = '<%=curl%>search_list.jsp?category=0&title='+val+'&key='+val;
	}else{ dialog("请输入查询内容"); }
}
</script>
</head>

<body>
	<div id="container">
    	<div id="header">
        	<div id="return">
            	<p><a href="javascript:toBack()">&nbsp;</a></p>
            </div>
             <div id="search">
             	<p>
                    <span></span>
                    <input type="text" placeholder="综合查询" id="s_val" value="<%=(request.getParameter("key")!=null)?request.getParameter("key"):"" %>"/>
                    <a href="javascript:clear()"></a>
                </p>
             </div>
            <div id="xq">
            	<p><a href="javascript:search()">查询</a></p>
            </div>
        </div>
        <div id="list">
        	<ul>
            	<li onclick="window.location.href='<%=curl%>search_list.jsp?category=002018&title=餐饮'">
                	<span><img src="images/qhc.png"></span>
                    <span>餐饮</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002016&title=休闲'">
                	<span><img src="images/qkg.png"></span>
                    <span>休闲</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002015&title=购物'">
                	<span><img src="images/qkx.png"></span>
                    <span>购物</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002019&title=景点'">
                	<span><img src="images/hc.png"></span>
                    <span>景点</span>
                </li>
            </ul>
            <ul>
            	<li onclick="window.location.href='<%=curl%>search_list.jsp?category=002006&title=银行'">
                	<span><img src="images/qgj.png"></span>
                    <span>银行</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002014002&title=停车'">
                	<span><img src="images/tcfw.png"></span>
                    <span>停车</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002003&title=厕所'">
                	<span><img src="images/cs.png"></span>
                    <span>厕所</span>
                </li>
                <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002017&title=住宿'">
                	<span><img src="images/qks.png"></span>
                    <span>住宿</span>
                </li>
            </ul>
        </div>
        <div id="zbsc">
        	<div id="cont">
                <div id="title">
                    <p class="left">周边速查</p>
                    <p class="right">
                        <!--<a href="<%=curl %>more_category.jsp">
                            <span class="s1">更多</span>
                            <span class="s2"></span>
                        </a>  
                    --></p>
                </div>
                <div id="list2">
                    <ul class="bor">
                        <li><img src="images/cx.jpg"><span>出行</span></li>
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002011001&title=公交站'">公交站</li> 
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002014001&title=加油站'">加油站</li>
                        <li class="nob" onclick="window.location.href='<%=curl%>search_list.jsp?category=002014002&title=停车场'">停车场</li>
                    </ul>
                    <ul class="bor">
                        <li><img src="images/sh.jpg"><span>生活</span></li>
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002006006&title=银行'">银行</li>
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002015001&title=超市'">超市</li>
                        <li class="nob" onclick="window.location.href='<%=curl%>search_list.jsp?category=002003&title=厕所'">厕所</li>
                    </ul>
                    <ul>
                        <li><img src="images/xx.png"><span>休闲</span></li>
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002017001&title=酒店'">酒店</li>
                        <li onclick="window.location.href='<%=curl%>search_list.jsp?category=002016006&title=茶馆'">茶馆</li>
                        <li class="nob" onclick="window.location.href='<%=curl%>search_list.jsp?category=002016001&title=咖啡'">咖啡</li>
                    </ul>
                </div>
            </div>
        </div><!--
        <div id="gxq"><img src="images/gxq.jpg"></div>
        <div id="menu">
        	<ul>
            	<li><a href="#">所有</a></li>
                <li><a href="#">美食</a></li>
                <li><a href="#">休闲娱乐</a></li>
                <li class="bor"><a href="#">住宿</a></li>
            </ul>
        </div>
    --></div>
</body>
</html>
