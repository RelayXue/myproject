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
<title>乌镇国际旅游区</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Resource/Theme/StyleDefault/style.css"/>  
<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TTuJing.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TMapType.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TJSoft.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/layer-v1.8.5/layer.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.css" />
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.theme.css" /> 
<script type="text/javascript" src="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.min.js"></script>
<script type="text/javascript">
var p = '${page}';
var total = '${totalPage}';
var pic_status = '${pic_status}';
//综合查询
function searchAll(){
	var skey = $("#searchText").val();
	window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
}

function redirectList(url){
	window.location.href = url;
}

function nextPage(){
	p++;
	//if(total==p&&'${pic_status}'==2){
	//	$("#nextBtn").css("display","none");
	//}
	var data = "page="+p+"&flag=1&skey="+encodeURIComponent(encodeURIComponent('${skey}'))+"&pic_status="+pic_status;
	var loadi = layer.load(0);
	$.ajax({
		  type: 'POST',
		  async:false,
		  url: "web/search_searchAll",
		  data: data,
		  dataType: 'html',
		  success: function(data){
			  $("#nextPageDiv").append(data);
			  layer.close(loadi);
		  }
	 });
}

function pageScroll(){
	$('html,body').animate({scrollTop:0},1000);
}

function show_detail(id,type){
	if(id.length==0||type.length==0){
		alert("参数传递出错，请刷新页面！");
		return;
	}
	window.open("search_showDetail?id="+id+"&type="+type);
}

function show_map(name,x,y){
	if(name.length==0||x.length==0||y.length==0){
		alert("参数传递出错，请刷新页面！");
		return;
	}
	var q = encodeURIComponent(encodeURIComponent(name+"|"+x+","+y+"|oe_l"));
	window.location.href = "index?q="+q;
}
</script>
</head>
<body>
<div class="_top">
	<div class="_grop">
    	<div class="_logo"></div>
        <div class="_box">
        	<div class="_name1" style="cursor: pointer;" onclick="redirectList('index')"></div>
            <div class="_ms">
            	<div class="_search_box">
               		<input id="searchText" type="text" value="${skey }"/>
                    <div class="_search_btn" style="cursor:pointer;" onclick="searchAll();"></div>
                </div>
                <div class="_menu"><span onclick="redirectList('search_scenicList');" style="cursor: pointer;">景区</span>|<span onclick="redirectList('search_typeSearch?type=002018');" style="cursor: pointer;">餐饮</span>|<span onclick="redirectList('search_typeSearch?type=002017');" style="cursor: pointer;">住宿</span>|<span onclick="redirectList('search_typeSearch?type=002016');" style="cursor: pointer;">娱乐</span>|<span onclick="redirectList('search_typeSearch?type=002015');" style="cursor: pointer;">购物</span>|<span onclick="redirectList('search_trafficPlayList');" style="cursor: pointer;">交通播报</span>|<span onclick="redirectList('search_newsList');" style="cursor: pointer;">新鲜速递</span></div>
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
<div style=" width:980px; margin:0 auto;">
    <div id="searchList" class="_body_list">
        <s:if test="!list.isEmpty()">
		<s:iterator var="s" value="list" status="status">
        <s:if test="pic_status==1">
           <!-- 有图片 -->
        <div class="_body_list_item1">
        	<div class="_img_box">
                <div id="${s.fuid}" class="owl-carousel owl-theme">
                  <s:if test="%{!#s.images.isEmpty()}">
                  <s:iterator var="img" value="%{#s.images}">
                  <div class="item"><img src="<%=basePath%>upload/${img }" style="height: 425px;"></div>
                  </s:iterator>
                  </s:if>
                </div>
            
            </div>
            <div class="_content">
            	<div style="font-size:20px;" class="mtp"><span>${s.fullname }</span></div>
                <div style="overflow:hidden;" class="mtp">
                <!-- 
                <img src="<%=basePath%>module/web/images/m_icon3_06.png" class="fleft"/>
                <span class="fleft" style="line-height:33px; color:#e5772d";>${s.star } 人均：￥${s.consumption }&nbsp;&nbsp;</span>
                 -->
                <span style="color:#888888;line-height: 32px;" class="fleft">&nbsp;&nbsp; 电话：${s.phone }&nbsp;&nbsp;&nbsp;&nbsp; 地址：${s.address }</span>
                </div>
                <div style="border-top:1px solid #cfcfca; margin:auto 6px; min-height:10px; color:#888888; padding-top:8px;" class="mtp">
                </div>
            </div>

            <div class="_botm fleft">
            	<span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png);">${s.examine }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:10px 2px;">${s.praise }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:10px 5px;">${s.comment }</span>
                <div style="clear:both"></div>
            </div>
            <div class="_btn1" onclick="show_map('${s.fullname}','${s.fx }','${s.fy }');">前往&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="_btn2" onclick="show_detail('${s.fuid}','${s.type }');">详情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> 
        </div>
        <script type="text/javascript">
        $('#'+"${s.fuid}").owlCarousel({
    		items: 1,
    		navigation : false, // Show next and prev buttons
    		  slideSpeed : 300,
    		  paginationSpeed : 400,
    		  singleItem:true
    	});
    </script>
        </s:if>
        
        <s:else>
           <!-- 无图片 -->
        <div class="_body_list_item">
        	<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>${s.fullname }</td></tr></table>
            <div class="_content">
            	<div style="font-size:20px;"><span>${s.fullname }</span></div>
            	<!-- 
                <div style="display:block; height:30px; line-height:30px; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0 3px; padding-left:26px;color:#e5772d; font-size:16px;margin-top:3px;">${s.star } 人均：￥${s.consumption }</div>
                 -->
                <div style="font-size:14px; margin-top:3px; color:#615F5F;">电话：${s.phone }</div>
                <div style="font-size:14px; margin-top:3px; color:#615F5F; margin-bottom:10px;"> 地址：${s.address }</div>
            </div>
                <div class="_btn1" onclick="show_map('${s.fullname}','${s.fx }','${s.fy }');">前往&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <div class="_btn2" onclick="show_detail('${s.fuid}','${s.type }');">详情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> 
                <div style="clear:both"></div>
            <div class="_botm">
            	<span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png)">${s.examine }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:10px 2px;">${s.praise }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:10px 5px;">${s.comment }</span>
                <div style="clear:both"></div>
            </div>
        </div>
        </s:else>
        </s:iterator>
        </s:if>
        <div id="nextPageDiv"></div>
    <div style="clear:both"></div>
    
    <!-- 分页 -->
    <div id="nextBtn" class="_next_btn" onclick="nextPage();" style="cursor: pointer;">展开更多</div>
</div>
</div>
<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>


<!-- 回首页 -->
<div class="_totop" onclick="pageScroll();" style="cursor: pointer;">回顶部</div>

</body>
</html>