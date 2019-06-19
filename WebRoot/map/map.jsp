<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
	<head>

		<!-- Basic -->
		<meta charset="utf-8">
		<title>乌镇名人</title>		
		<meta name="keywords" content="乌镇" />
		<meta name="description" content="乌镇旅游">
		<meta name="author" content="taoguang.com">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">

		<link rel="stylesheet" href="<%=basePath %>module/touch/css/bootstrap.css">
		<link rel="stylesheet" href="<%=basePath %>module/touch/css/theme.css">

		<!--[if IE]>
			<link rel="stylesheet" href="css/ie.css">
		<![endif]-->

		<!--[if lte IE 8]>
			<script src="js/respond.js"></script>
			<script src="js/excanvas.js"></script>
		<![endif]-->
    <style type="text/css">
        #map
        {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0px;
            left: 0px;
            background-color: #f3f5ed;
            border: 1px solid black;
            background-image: none;
        }
        body
        {
            margin: 0 0 0 0;
            padding: 0 0 0 0;
            overflow: hidden;
        }
    </style>
    
<script src="map.js" type="text/javascript"></script>

<script type="text/javascript">

    var lon = 121.4;
    var lat = 31.3;

    var map = null;
    var vectorLayer;
    var layer1, layer2, layer3, layer4, layer5;
    var featureStyle = null, featureStyle2 = null, meatureStyle = null;
    var mapParamS;
    function Init() {
        SUtil.SetImagesLocation("/Resource/Theme/StyleDefault/");
        mapParamS = new SMapParamOption();
        mapParamS.Resolutions = [0.000133695117187497, 0.0000668475585937487, 0.0000334237792968743, 0.0000167118896484372, 0.00000835594482421859];  
        mapParamS.MaxExtent = new SBounds(120.429469788641, 30.666051366282, 120.566373588641, 30.802955166282); 
        map = new SMap('map', mapParamS);
        vectorLayer = new SVectorLayer("vLayer");
 
        layer1 = new STileLayer("三维图", "tiles/", 'png');
        layer1.SetTilePathRuleFun(CustomUrlSw);
        map.AddLayer(layer1);
        //  layer3.SetZIndex(130); 

        map.AddLayer(vectorLayer);
        map.AddControl(new SPanZoom());
        markerL = new SMarkerLayer("POI层");
        map.AddLayer(markerL);
        map.SetCursor("hand");
        map.SetCenter(mapParamS.MaxExtent.GetCenterLonLat(), 0); 
    } 

    function CustomUrlSw(pTile) {
        var z = this.GetMap().GetZoom()+2;
        var r = pTile.GetRow();
        var col = pTile.GetCol();
        var path = (z + 1) + "/" + r + "/" + col + "." + this.GetType();
        z = null;
        r = null;
        col = null;
        return this.GetPrefixPath() + path;
    };
</script>
</head>
<body onload="Init()">
<div id="map"></div>
<header class="text-center">

            <!-- <div class="top-left"><a href="<%=basePath %>module/touch/index.jsp"><i class="fa fa-angle-left"></i></a></div> -->
            <div class="tab_top">
            	<ul class="nav">
                	<li class="active"><a href="javascript:void(0)"><i class="fa fa-globe"></i>地图</a></li>
                    <li><a href="<%=basePath %>module/touch/nav.jsp"><i class="fa fa-th"></i>罗列</a></li>
                </ul>
            </div>
            <div class="top-right"><a href="#"><img class="img-responsive" src="img/icon-search.png" alt=""></a></div>

<!-- 
	<div class="icon-left"><a href="#"><i class="fa fa-angle-left"></i></a></div>
	<div class="search-bar">
    	<div class="form-group">
			<form>
            	<input class="search" type="search" placeholder="桐乡市乌镇..."><button type="submit" class="btn btn-lg btn-search">周边</button>
            </form>
		</div>
    </div>
 -->

</header>

<div class="popup yel" style="display: none;">
	<div class="arrow"></div>
		<ul>
        	<li><img class="img-responsive" src="img/hotel-1.jpg" alt=""></li>
            <li>
            	<p>&nbsp;</p>
                <p>&nbsp;</p>
                <h3>乌镇晨阳宾馆</h3>
                <p>行业类型：住宿<br />
                负责人：陈小萍<br />
                负责电话：13806732889<br />
                店铺电话：84064625<br />
                地址：桐乡市乌镇镇碓坊桥村民兴中路。</p>
                <a class="btn btn-lg btn-gouwu" href="#">前往路径</a>
    		</li>
		</ul>
</div>

<div class="tools">
	<button class="tool map-path"><i></i>手选路线</button>
    <button class="tool map-search"><i></i>搜索</button>
    <button class="tool map-home"><i></i>首页</button>
</div>
<div class="tools-zoom">
    <div class="map-zoom">
		<div class="z-in"><i></i></div>
		<div class="zslider slider-vertical" style="height: 200px;">
			<div class="slider-range bg-z"></div>
			<a class="slider-handle state-default corner-all" href="#" style="bottom: 55%;"></a>
		</div>
		<div class="z-out"><i></i></div>
	</div>
</div>
</body>
</html>