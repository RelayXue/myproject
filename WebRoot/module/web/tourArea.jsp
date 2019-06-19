 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>乌镇旅游景区</title>
<script src="<%=basePath%>js/jquery-1.7.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.css" />
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.theme.css" />
<script src="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.min.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
	 
	  $(".owl-carousel").owlCarousel({
		  navigation : false, // Show next and prev buttons
		  slideSpeed : 300,
		  paginationSpeed : 400,
		  singleItem:true
	 
	  });
	 
	});
	
	function redirectList(url){
		window.location.href = url;
	}
	
	//综合查询
function searchAll(){
	var skey = $("#searchText").val();
	window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
}
    
    </script>
<style type="text/css">

._links_content{
	width:980px;
	box-shadow: 0px 2px 5px #4E4E4E;
	background-color: #F8F8EC;
	border-radius: 6px;
	-moz-border-radius:6px;
	-webkit-border-radius:  6px;
	font-family: "微软雅黑", "幼圆", "黑体";
	margin:15px auto;
	padding-top:15px;
}
._title_box{
	display:block;
	font-size:20px;
	line-height:30px;
	color:#717699;
}
._line{ border-bottom:1px solid #A6A6A6; margin:10px auto; }
.owl-carousel .item img{
    display: block;
    width: 100%;
    height: auto;
	border-radius: 4px 4px 0 0;
}
.owl-carousel .owl-controls {
margin-top: 10px;
text-align: center;
position: absolute;
left: 45%;
bottom: 5px;
z-index: 9999999;
height: 22px;
background: rgba(0, 0, 0, 0.67);
}
</style>
</head>

<body>
<div class="_top">
	<div class="_grop">
    	<div class="_logo"></div>
        <div class="_box">
        	<div class="_name1" style="cursor: pointer;" onclick="redirectList('index')"></div>
            <div class="_ms">
            	<div class="_search_box">
               		<input id="searchText" type="text" value=""/>
                    <div class="_search_btn" style="cursor:pointer;" onclick="searchAll();"></div>
                </div>
                <div class="_menu"><span onclick="redirectList('search_scenicList');" style="cursor: pointer;">景区</span>  |  <span onclick="redirectList('search_typeSearch?type=002018');" style="cursor: pointer;">餐饮</span>  |  <span onclick="redirectList('search_typeSearch?type=002017');" style="cursor: pointer;">住宿</span>  |  <span onclick="redirectList('search_typeSearch?type=002016');" style="cursor: pointer;">娱乐</span>  |  <span onclick="redirectList('search_typeSearch?type=002015');" style="cursor: pointer;">购物</span>  |  <span onclick="redirectList('search_trafficPlayList');" style="cursor: pointer;">交通播报</span></div>
            </div>
            <div class="_weather">
            	<img src="<%=basePath%>module/weixin/images/weather/${bw.image}" width="65" height="50"/>
                <div class="_tt1">${bw.temperaturenow}℃ </div>
                <div class="_tt2">${bw.changes}</div>
            </div>
        </div>
    </div>
    <div class="_tllp"></div>
</div>

<div class="_body">
<div class="_links_content">
	<div style="margin:0 15px">
	<div class="_title_box">乌镇国际旅游区</div>
    <div class="_line"></div>

    	<div class="_body_list_item1">
    		<s:iterator value="newsList" var="list" >
                <div class="_img_box">
                    <div class="owl-carousel owl-theme">
                    	<s:generator val="#list.img1" separator="," id="imgs"></s:generator>
						<s:iterator status="st" value="#request.imgs" id="img_list">   
					   <div class="item"><img src="<%=basePath %>upload/<s:property value="#img_list" />" alt="GTA V" /></div>
					</s:iterator>   
                    </div>
                </div>
                <div class="_content">
                    <!-- <div style="font-size:20px;" class="mtp">简介</div> -->
                    <div style="margin:auto 0; min-height:50px; color:#888888; padding-top:8px;" class="mtp">
                       ${content}
                    </div>
                </div>
                </s:iterator>
		</div>
            </div>
		<div style="padding:2px 2px 2px 2px; overflow:hidden; background-color:rgb(230,230,230);border-radius: 0px 0 8px 8px;-moz-border-radius:  0px 0 8px 8px;-webkit-border-radius:  0px 0 8px 8px; ">
        </div>
    
		
</div>

</div>


<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span></div>
</body>
</html>
