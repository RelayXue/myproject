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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/index.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/select-target.css">
<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
<script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&libraries=convertor&key=WE7BZ-WJCHJ-ZOBFU-KAJAQ-REZAO-TZBFD"></script>

<title>游在乌镇-街景</title>
<style type="text/css">
    #map
    {
        width: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
    }
    #street_view_box{
        width: 100%;
        position: absolute;
        left: 0px;
        background-color: #fff;
        background-image: none;
        border: none;
        z-index: 1000;
    }
    #street_view{
        width: 100%;
        height: 100%;
        left: 0px;
        top: 0px;
        background-color: #f3f5ed;
        background-image: none;
        border: none;
    }
    .remark{
    	text-align: center;padding-top: 30%; font-size: 14px;color: #000;
    }
    .fullview_btn{ 
	    position: absolute;
		background-image: url('images/fullIcons.png');
		height: 26px;
		width: 28px;
		top: 0;
		z-index:121313;
		right: 0;
		cursor: pointer;
		display: none;
    }
    .lie{
    	width: 40px;
    	height: 1px;
    	border-top: 1px solid #444;
    	display: block;
    	margin: 3px auto;
   	}
</style>
<script type="text/javascript">
	var route = "<%=request.getParameter("route")%>";

	var pos_x = 0;
	var pos_y = 0;

	//地图相关参数
    var lon = 121.4;
    var lat = 31.3;

    var map = null;
    var vectorLayer;
    var layer1, layer2, layer3, layer4, layer5;
    var featureStyle = null, featureStyle2 = null, meatureStyle = null;
    var mapParamS;
    var markerL;
    
    var window_height = $(window).height();
    
    var slider_line_top = 200;
    var slider_line_height = 20;
    
    $(function(){ 

    	slider(slider_line_height, slider_line_top);
    	//滑动监听
		function touchMove(event) {
		    event.preventDefault();
		    var touch = event.touches[0];
		    if(touch.pageY > 100 && touch.pageY < window_height - 100){
		    	slider(slider_line_height, touch.pageY);
		    	slider_line_top = touch.pageY;
		    }
		}
	
		// add touch start listener
		document.getElementById("slider_line").addEventListener("touchmove", touchMove, false);
    
    	Init(); 
    	showRouteStatus();
    	if(map != null){
			map.AddEventListener("touchend", MapClick);
			map.SetCenter(new SLonLat(120.488236, 30.740840), 2);
			addPoint(120.488236, 30.740840);
    	}

    });
    
    function slider(slider_line_height, slider_line_top){
    	$("#map").height(slider_line_top);
    	$("#slider_line").css("top", slider_line_top+"px");
    	$("#street_view_box").css("top", (slider_line_top + slider_line_height)+"px");
    	$("#street_view_box").height((window_height-slider_line_height-slider_line_top)+"px");
    }
    
    function MapClick(evt) {
    	markerL.ClearMarkers(); 
        var ll = map.GetLonLatFromViewPortPx(new SPixel(evt.pXY.GetX(), evt.pXY.GetY()));
        addPoint(ll.GetLon(), ll.GetLat());
    }
    
    function addPoint(x, y){
        var icon2 = new SIcon("<%=curl%>images/32.png", new SSize(21, 37));
        var mm = new SMarker(new SLonLat(x, y), icon2, "tj");
        markerL.AddMarker(mm);
        showInStreentView(x, y);
    }
    
    var show_route_state = false;
    function showRouteStatus(){
		vectorLayer.ClearFeatures();
		markerL.ClearMarkers(); 
		vectorLayer.DestroyFeatures();
		if(show_route_state == true){ show_route_state = false; return; }
    	//alert("正在检索路况信息..请稍候");
		var parms = new Object();
		$.post("phone_main_routeStatus", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var html = "";
				for(i=0;i<o.length;i++){
					var color = "#0063b4";
					drowLine2(o[i].xy, color);
				}
				show_route_state = true;
			}else{
				//alert("检索失败,没有路况信息");
			}
		}, "json");
    }
    
    function showInStreentView(x,y) {
        var pano_service = new qq.maps.PanoramaService();
        var point = new qq.maps.LatLng(y, x);
        var radius;
        var points = [];
        points.push[point];
        qq.maps.convertor.translate(point, 1, function (res) {
            var latlng = res[0];
            
            pano_service.getPano(latlng, 1000, function (result) {
                //pano.setPano(result.svid);  
                //如果result为null，说明当前位置无街景
                $("#street_view").remove(); 
                if(result != null){ 
                	//var url = "http://jiejing.soso.com/#pano="+result.svid+"&heading=45&pitch=10&zoom=1&addressControl=false";
                	
                	$(".remark").remove();
                	$("#street_view").remove();
                	$("#street_view_box #content").append('<div id="street_view"></div>');
		            //创建街景
		            var pano = new qq.maps.Panorama(document.getElementById('street_view'), {
		                pano: result.svid,
		                pov: {
		                    heading: 90,
		                    pitch: 4
		                }
		            });
		            $(".fullview_btn").show();
                }else{
                	$("#street_view_box").html('<div class="remark">该地点没有街景</div>');
                	$(".fullview_btn").hide();
                }
            });
        });
    }
    
    var is_fullview = false;
    function fullview(){
    	if(is_fullview){
    		is_fullview = false;
    		slider(slider_line_height, slider_line_top);
    		$("#slider_line").show();
    	}else{
    		is_fullview = true;
    		$("#street_view_box").css("height", "100%");
    		$("#street_view_box").css("top", "0");
    		$("#slider_line").hide();
    	}
    }
    </script>
</head>

<body>
	<div id="container" style="background-image: none;">
	<div id="map" style="z-index: 100;"></div>
	<div id="slider_line" style="height:18px;background-color: #ccc;position: absolute;left: 0;width: 100%;z-index: 131654;border-top: 1px solid #8B8B8B;border-bottom: 1px solid #8B8B8B;"><div class="lie"></div><div class="lie"></div><div class="lie"></div></div>
	<div id="street_view_box">
		<div style="width: 100%;height: 100%; position: relative;" id="content">
			<div class="fullview_btn" id="fullview_btn" onclick="fullview()" style="display: block;"></div>
			<!--<div class="remark">请点击上方地图选择街景地点</div>
		--></div>
	</div> 
    <div id="zoom" style="top: 10px;bottom: auto  !important;z-index: 100;">
    	<span class="add" onclick="map.ZoomIn()"></span>
        <span class="minus" onclick="map.ZoomOut()"></span>
    </div>
        <!-- <div id="dw"><img src="<%=curl %>images/dw.png"></div> -->
    </div>
</body>
</html>
