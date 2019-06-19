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
	background-image:url('<%=basePath%>module/web/images/list_icon1_05.png');
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
<div class="_links_content" style="position:relative; padding: 15px 0 0 0;">
	<div class="_title_box" style="margin-left:15px;">联系我们</div>
    <div style="position:absolute; top:13px; left:10px; width:985px; height:35px; text-align:center; line-height:35px; font-size:22px; ">乌镇国际旅游区-管委会</div>
    <s:iterator value="newsList" var="list" >
    	${content}
    </s:iterator>
    <!--<div style="border:1px solid rgb(171,169,172); overflow:hidden; margin:15px;">
    	<div style="width:488px; border-right:1px solid rgb(171,169,172); float:left">
        	<div style="font-size:20px; margin:10px 0 10px 15px;">快捷联系</div>
            <div style="color:rgb(132,132,132); font-size:15px; margin-left:15px; line-height:24px;">
            	地址：浙江省桐乡市乌镇石佛南路18号 <br />
            	邮政编码：314501 <br />
                <br />
                电话总机：0573-8873 1088<br />
                全国免费服务热线：<br />
                景区管理中心办公室：0573-8873 1996<br />
                景区管理中心传真：0573-8873 1098<br />
                景区预定电话：0573-8873 1088<br />
                景区预定传真：0573-8873 1087<br />
                旅游投诉电话：0573-8873 1234<br />
                <br />
                网址：www.wuzhen.com.cn<br />
                E-Mail：wuzhen@wuzhen.com.cn<br />
                <br />
            </div>
            
            <div style="font-size:20px; margin:10px 0 10px 15px;">乌镇旅游官方微信</div>
            <div style="margin:0 0 15px 15px; overflow:hidden;">
            	<img src="" style="width:155px; height:155px; float:left; margin-right:20px;" />
                <div style="font-size:15px; line-height:22px;">
                	<span style="font-size:18px;">关注办法：</span><br />
                    1、扫描二维码图片<br />
                    2、搜索官方微信号：<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乌镇旅游：wuzhenjingqu<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乌镇戏剧节：wuzhenfestival<br />
                    3、查找微信公众帐号时<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入“乌镇”、“乌镇戏剧节”
                </div>
            </div>
            
        </div>
    	<div style="width:459px; float:left">
        	<div style="font-size:20px; margin:10px 0 10px 15px; ">乌镇旅游区的当前位置</div>
            <div style=" margin:0 15px 0 15px; border:1px solid rgb(171,169,172); height:535px; overflow:hidden;">添加地图部分</div>
        </div>
    </div>-->

		<div style="padding:2px 2px 2px 2px; overflow:hidden; background-color:rgb(230,230,230);border-radius: 0px 0 8px 8px;-moz-border-radius:  0px 0 8px 8px;-webkit-border-radius:  0px 0 8px 8px; ">
        </div>

</div>


<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</spa
></div>
</body>
</html>
