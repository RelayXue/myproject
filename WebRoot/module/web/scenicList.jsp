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
<style type="text/css">
._stream_list{
	display:block;
	width:254px;
	float:left;
}

._stream_list ._item{
	display:block;
	border-radius: 5px;            /* W3C syntax */
    -moz-border-radius: 5px;      /* Gecko browsers */
    -webkit-border-radius: 5px;   /* Webkit browsers */
	box-shadow:0px 0px 6px #4E4E4E;
	width:243px;
	background-color:#f8f8ec;
	margin:0 12px 12px 0;
	overflow:hidden;
	font-family:"微软雅黑", "幼圆", "黑体";
	overflow:hidden;
}

._stream_list ._item img{
	display:block;
	border-radius: 5px;            /* W3C syntax */
    -moz-border-radius: 5px;      /* Gecko browsers */
    -webkit-border-radius: 5px;   /* Webkit browsers */
	width:227px;
	overflow:hidden;
	margin:8px;
}

._stream_list ._item ._title{
	margin-left:8px;
	font-size:20px;
}
._stream_list ._item ._content{
	font-size:14px;
	color:#666;
	margin:0 8px 8px 8px;
}
</style>
<script type="text/javascript">
var type = '${type}';
var pathh = "<%=basePath%>";
var p = '${page}';
var total = '${totalPage}';

function openDetailLayout(id){
	if(id.length==0){
		return ;
	}
	$.layer({
    type: 2,
    border: [0],
    title: false,
    shadeClose: true,
    //closeBtn: false,
    iframe: {src : 'search_scenicDetail?id='+id},
    area: ['637px', '628px']
});
}

$(function(){
	if(type=="002019002"){
		//西
		$("#d_show").removeClass("_hover");
		$("#d_show").css("cursor","pointer");
		$("#x_show").addClass("_hover");
		$("#x_show").css("cursor","default");
		$("#x_show").attr("onClick","");
		$("#y_show").removeClass("_hover");
		$("#y_show").css("cursor","pointer");
	}else if(type=="002019003"){
		//雅达
		$("#d_show").removeClass("_hover");
		$("#d_show").css("cursor","pointer");
		$("#x_show").removeClass("_hover");
		$("#x_show").css("cursor","pointer");
		$("#y_show").addClass("_hover");
		$("#y_show").css("cursor","default");
		$("#y_show").attr("onClick","");
	}else{
		$("#d_show").addClass("_hover");
		$("#d_show").css("cursor","default");
		$("#d_show").attr("onClick","");
		$("#x_show").removeClass("_hover");
		$("#x_show").css("cursor","pointer");
		$("#y_show").removeClass("_hover");
		$("#y_show").css("cursor","pointer");
	}
	
	/*
	var pageii = $.layer({
	    type: 1,
	    title: '景区介绍',
	    area: ['600px', 'auto'],
	    shift: 'top', //从左动画弹出
	    page: {
	        html: $("#scenicContent").html()
	    }
	});*/
});

function redirectList(url){
	window.location.href = url;
}

function nextPage(){
	p++;
	if(total==p){
		$("#nextBtn").css("display","none");
	}
	var data = "type="+'${type}'+"&page="+p+"&flag=1";
	var loadi = layer.load(0);
	$.ajax({
		  type: 'POST',
		  async:true,
		  url: "web/search_scenicList",
		  data: data,
		  dataType: 'json',
		  success: function(data){
			  layer.close(loadi);
			  if(!(data==null||data.length==0)){
				 for(var i=0;i<data.length;i++){
					 var img = pathh+"upload/"+data[i].dimages;
					 var str = "<div class=\"_item\" onclick=\"openDetailLayout('"+data[i].fuid+"');\">";
					     str+= "<img src='"+img+"'/>";
					     str+= "<div class=\"_title\">"+data[i].fullname+"</div>";
					     str+= "<div class=\"_content\">"+data[i].introduction+"</div>";
					     str+= "</div>";
					     if(i==0||i==4||i==8){
					    	 $("#list_show1").append(str);
							}else if(i==1||i==5||i==9){
								$("#list_show2").append(str);
							}else if(i==2||i==6||i==10){
								$("#list_show3").append(str);
							}else if(i==3||i==7||i==11){
								$("#list_show4").append(str);
							}
				 }
				 $("._stream_list ._item").click(function(){
						var id = $(this).find("input").val();
						if(id.length==0){
							return ;
						}
						openDetailLayout(id); 
					});
			  }
		  },
		  error:function(){
			  layer.close(loadi);
		  }
	 });
}

function pageScroll(){
	$('html,body').animate({scrollTop:0},1000);
}

//综合查询
function searchAll(){
	var skey = $("#searchText").val();
	window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
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
               		<input id="searchText" type="text" value=""/>
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
    <div class="_body_top_menu">
    	<div style="background-image:url(<%=basePath%>module/web/images/21_04.png); background-position:0 center; background-repeat:no-repeat; padding-left:32px; height:32px; position:absolute; left:15px; top:12px;" ><span style="font-size:18px; line-height:42px;">景区</span></div>
        <div style="padding-left: 50px;">
        <div id="d_show" class="_menu_btn" onclick="redirectList('search_scenicList');"><span id="d_show_a">东栅</span></div>
        <div id="x_show" class="_menu_btn" onclick="redirectList('search_scenicList?type=002019002');"><span id="x_show_a">西栅</span></div>
        <div id="y_show" class="_menu_btn" onclick="redirectList('search_scenicList?type=002019003');"><span id="y_show_a">雅达</span></div>
        <div style="clear:both"></div>
        </div>
    </div>
    <!-- 详细说明 -->
    <div class="_descp" >
      ${news.content }
    </div>
    <div style="overflow:hidden; width:1020px; margin-top: 15px;">
        <div class="_stream_list" id="list_show1">
            <s:if test="!sc.list1.isEmpty()">
			<s:iterator var="s1" value="sc.list1" status="status">
            <div class="_item" onclick="openDetailLayout('${s1.fuid}');">
            	<img src="<%=basePath%>upload/${s1.dimages}" />
                <div class="_title">${s1.fullname}</div>
                <div class="_content">${s1.introduction}</div>
                <input type="hidden" value="${s1.fuid}"/>
            </div>
            </s:iterator>
            </s:if>
        </div>
        
        <div class="_stream_list" id="list_show2">
            <s:if test="!sc.list2.isEmpty()">
			<s:iterator var="s2" value="sc.list2" status="status">
            <div class="_item" onclick="openDetailLayout('${s2.fuid}');">
            	<img src="<%=basePath%>upload/${s2.dimages}" />
                <div class="_title">${s2.fullname}</div>
                <div class="_content">${s2.introduction}</div>
                <input type="hidden" value="${s2.fuid}"/>
            </div>
            </s:iterator>
            </s:if>
        </div>
        
        <div class="_stream_list" id="list_show3">
            <s:if test="!sc.list3.isEmpty()">
			<s:iterator var="s3" value="sc.list3" status="status">
            <div class="_item" onclick="openDetailLayout('${s3.fuid}');">
            	<img src="<%=basePath%>upload/${s3.dimages}" />
                <div class="_title">${s3.fullname}</div>
                <div class="_content">${s3.introduction}</div>
                <input type="hidden" value="${s3.fuid}"/>
            </div>
            </s:iterator>
            </s:if>
        </div>
        
        <div class="_stream_list" id="list_show4">
            <s:if test="!sc.list1.isEmpty()">
			<s:iterator var="s4" value="sc.list4" status="status">
            <div class="_item" onclick="openDetailLayout('${s4.fuid}');">
            	<img src="<%=basePath%>upload/${s4.dimages}" />
                <div class="_title">${s4.fullname}</div>
                <div class="_content">${s4.introduction}</div>
                <input type="hidden" value="${s4.fuid}"/>
            </div>
            </s:iterator>
            </s:if>
        </div>
        
    </div>
    
      <!-- 分页 -->
      <s:if test="totalPage>page">
      <div id="nextBtn" class="_next_btn" onclick="nextPage();">更多景点</div>
      </s:if>
    
</div>
<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>


<!-- 回首页 -->
<div class="_totop" onclick="pageScroll();" style="cursor: pointer;">回顶部</div>

</body>
</html>