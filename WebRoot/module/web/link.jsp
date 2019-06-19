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

._links_content{
	width:980px;
	box-shadow: 0px 2px 5px #4E4E4E;
	background-color: #F8F8EC;
	border-radius: 6px;
	-moz-border-radius:6px;
	-webkit-border-radius:  6px;
	font-family: "微软雅黑", "幼圆", "黑体";
	margin:15px auto;
	padding:15px;
}
._title_box{
	display:block;
	background-image:url(images/list_icon1_05.png);
	background-repeat:no-repeat;
	background-position:5px center;
	padding-left:50px;
	font-size:20px;
	line-height:30px;
	color:#717699;
}
._line{ border-bottom:1px solid #A6A6A6; margin:10px auto; }

</style>
<script type="text/javascript">

function redirectList(url){
	window.location.href = url;
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

<div class="_body">
<div class="_links_content">

	<div class="_title_box">友情链接</div>
    <div class="_line"></div>
    
<div class="_more" style="overflow-y:auto; height:auto;">

		<s:iterator value="buLink_list" var="list" >
            <div class="_row">
            	<div class="_img" style="width: 108px;height: 108px;" ><img src="<%=basePath%>/upload/B_${logo}" style="width: 108px;height: 108px;"/></div>
                <div class="_content" style="line-height:30px; ">
                    <div class="f_18"><span class="font_blued">${name}</span></div>
                    <div class="fs_16" style="color:#615F5F; ">
        			<div>网址：${url}</div>
        			<div>网站描述:${content}</div>
                    </div>
                    <div class="_btns">
                        <a href="http://${url}" target=_blank><img src="<%=basePath%>module/web/images/d_icon1_34.png" style="border: 0"/></a>
                    </div>
                </div>
            </div>
            </s:iterator>
            
            <!--
            <div class="_row">
                <div class="_img" style="background-image:url(images/bg_05.jpg); background-size: 108px 108px;width: 108px;height: 108px;" ></div>
                <div class="_content" style="line-height:30px; ">
                    <div class="f_18"><span class="font_blued">乌镇国际旅游区</span></div>
                    <div class="fs_16" style="color:#615F5F; ">
        			<div>网址：15588846854854</div>
        			<div>网站描述：当时佛教大师破飞机</div>
                    </div>
                    <div class="_btns">
                        <img src="<%=basePath%>module/web/images/d_icon1_34.png">
                    </div>
                </div>
            </div>
            <div class="_row">
                <div class="_img" style="background-image:url(images/bg_05.jpg); background-size: 108px 108px;width: 108px;height: 108px;" ></div>
                <div class="_content" style="line-height:30px; ">
                    <div class="f_18"><span class="font_blued">乌镇国际旅游区</span></div>
                    <div class="fs_16" style="color:#615F5F; ">
        			<div>网址：15588846854854</div>
        			<div>网站描述：当时佛教大师破飞机</div>
                    </div>
                    <div class="_btns">
                        <img src="<%=basePath%>module/web/images/d_icon1_34.png">
                    </div>
                </div>
            </div>
        </div>
        
		-->
		
		<div style="padding:2px 2px 2px 2px; overflow:hidden; background-color:rgb(230,230,230);border-radius: 0px 0 8px 8px;-moz-border-radius:  0px 0 8px 8px;-webkit-border-radius:  0px 0 8px 8px; ">
        </div>
    
</div>

</div>


<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>
</body>
</html>
