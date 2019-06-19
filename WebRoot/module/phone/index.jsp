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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/k_tables.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/index.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/select-target.css">
<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
<script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>

<title>游在乌镇</title>
<style type="text/css">
    #map
    {
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        background-color: #f3f5ed;
        background-image: none;
    }
    .icosp{ background-image: url("images/sor.png");background-repeat: no-repeat;width: 22px; height: 37px;overflow: hidden; color: #fff; text-align: center;}
</style>
<script type="text/javascript">
	//1显示路线 2显示美图欣赏
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
    
    //定位经纬度
    var pos_x = 0;
    var pos_y = 0;
    
    $(function(){ 
    	Init(); 
    	if(route == "1"){
    		//显示路线
    		var route_detail_json = localStorage.getItem("route_detail_json");
    		if(route_detail_json != null && route_detail_json.length > 0){
    			var data = $.parseJSON(route_detail_json);
    			var start = data.start;
    			var end = data.end;
    			
        		var icon,marker;
        		icon = new SIcon('/module/web/images/begin.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    
           	    window.parent.markerL.ClearMarkersByTag("begin");
           	    maker = new SMarker(new SLonLat(start.split(",")[0],start.split(",")[1]), icon,"begin");
           	    window.parent.markerL.AddMarker(maker);
           	    icon = new SIcon('/module/web/images/end.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    window.parent.markerL.ClearMarkersByTag("end");
        	    maker = new SMarker(new SLonLat(end.split(",")[0],end.split(",")[1]), icon,"end");
        	    window.parent.markerL.AddMarker(maker);
	        	window.parent.drowLine3(data.xy);
	        	
	        	var ppS1 = SPoint.GetPointSFromString(data.xy);
				map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
				map.ZoomOut();
    		}
    	}else if(route == "2"){
			loadPicShare();
    	}else{
    		//显示当前位置
    		if(pos_x > 0 && pos_y > 0 && map != null){
    			var icon2 = new SIcon("<%=basePath%>module/touch/img/map_marker_03.png", new SSize(32, 32));
	            var mm = new SMarker(new SLonLat(pos_x, pos_y), icon2, "tj");
	            markerL.AddMarker(mm);
	            map.SetCenter(new SLonLat(pos_x, pos_y));
    		}
    	}
    	
        //回车查询
		$("#key").keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
		    var key = e.which; //e.which是按键的值
		    if (key == 13) {
				var parms = new Object();
				parms.key = $.trim($("#key").val());
				parms.category = 0;
				parms.pos_x = pos_x;
				parms.pos_y = pos_y;
				
				$.post("<%=basePath%>phone_main_mapSearch", parms, function(json) {
					var code = json.code;
					var hint = json.hint;
					if (code == 1) {
						var o = $.parseJSON(json.o);
						for(i=0;i<o.length;i++){
							//类型转换
							var type = o[i].type;
							var category = 0;
							if(type == 1){ category = 1;}
							if(type == 2){ category = 4;}
							
							var maker_info = '<div id="hover" style="width: 300px;position: absolute;top: 0px;width: auto;left: 0px;margin-left: 0;padding: 0px;background: none;border: none;border-radius: 5px;"><div><dl><dt><img src="<%=curl%>images/dt.jpg"></dt><dd><div><span class="pf"><img src="<%=curl%>images/dj.jpg">4.5</span> <span class="xq"><a href="<%=curl%>search_detail.jsp?id='+o[i].fuid+'&category='+category+'">详情></a></span></div><ul><li class="title">'+o[i].fullname+'</li><li>负责人：'+o[i].supervisor+'</li><li>负责电话：'+o[i].supervisorphone+'</li></ul></dd></dl></div><div id="cont"><div id="btn"><p class="phone"><a href="#"><img src="<%=curl%>images/phone.jpg">电话</a></p><p class="zzb"><a href="#"><img src="<%=curl%>images/qza.jpg">前往</a></p></div></div></div>';
							
							//地图上添加点
				            var icon2 = new SIcon("<%=curl%>images/map_marker_03.png", new SSize(32, 32));
				            icon2.SetOpacity(0.2);
				            var mm = new SMarker(new SLonLat(o[i].fx, o[i].fy), icon2, "tj");
				            markerL.AddMarker(mm);
						}
					}
				}, "json");
		    }
		});
    });
    
	function GetLonLatS(pointS){
		var rtn = [];
		if(pointS!=null && pointS.length>0){
			for(var u=0;u<pointS.length;u++){
				var itm = new SLonLat(pointS[u].GetX(),pointS[u].GetY());
				rtn.push(itm);
			}
		}
		return rtn;
	}
    
    function toSearch(){
    	window.location.href ='<%=curl%>rim.jsp?key='+$.trim($("#key").val());
    }
    
    if (window.navigator.geolocation) {
        var options = {
            enableHighAccuracy: true
        };
        window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
    } else {
        alert("浏览器不支持html5来获取地理位置信息");
    }
    
    //获取到位置回调
    function handleSuccess(position){
        // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
        pos_x = position.coords.longitude;
        pos_y = position.coords.latitude;
    }
    
    //获取失败回调
    function handleError(error){}
    
    function loadPicShare(){
		vectorLayer.ClearFeatures();
		markerL.ClearMarkers(); 
		vectorLayer.DestroyFeatures();
		
		//修改url地址
		var state = {
			title: document.title,
			url: "<%=curl%>index.jsp?route=2",
			otherkey: null
		};
		window.history.pushState(state, document.title, "<%=curl%>index.jsp?route=2");
		
		var parms = new Object();
		$.post("phone_main_pictureShare", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialog(hint);
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var html = "";
				for(i=0;i<o.length;i++){
					//地图上添加点
		            var icon2 = new SIcon(null, new SSize(64, 64));
		            icon2.GetDiv().innerHTML = '<div style="border:1px solid #ccc;  overflow: hidden;" ><img src="<%=basePath%>upload/L_'+o[i].img1+'" style="width:63px; height:63px; " /></div>';
		           
		            icon2.SetOpacity(0.2);
		            var mm = new SMarker(new SLonLat(o[i].fx, o[i].fy), icon2, "tj");
		            mm.linkUrl = "<%=basePath%>fnews!shareDetails?id="+o[i].fuid+"&source=1";
		            markerL.AddMarker(mm);
		            mm.GetEVTReference().AddEventListener("touchstart", null, function() {
		            	window.location.href = this.linkUrl;
		            });
					map.SetCenter(new SLonLat(o[i].fx, o[i].fy));
				}
			}
		}, "json");
    }
    
    
    var show_route_state = false;
    function showRouteStatus(){
		vectorLayer.ClearFeatures();
		markerL.ClearMarkers(); 
		vectorLayer.DestroyFeatures();
		if(show_route_state == true){ show_route_state = false;  $("#route_status_img").attr("src", "<%=curl %>images/li2.jpg");}else{
			show_route_state = true;
			$("#route_status_img").attr("src", "<%=curl %>images/li2_1.jpg");
			
	    	dialog("正在检索路况信息..请稍候");
			var parms = new Object();
			$.post("phone_main_routeStatus", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				if (code == 1) {
					var o = $.parseJSON(json.o);
					var html = "";
					for(i=0;i<o.length;i++){
						var color = "";
						var state = o[i].state;
						if(state == "严重拥堵"){ 
							color = "#ff0000";
						}else if(state == "拥堵"){ 
							color = "#ff0000";
						}else if(state == "缓慢"){ 
							color = "#ffe13a";
						}else if(state == "畅通"){ 
							color = "#00ff00";
						}
						drowLine2(o[i].xy, color);
					}
				}else{
					dialog("检索失败,没有路况信息");
				}
			}, "json");
		}
    }
    </script>
    
    <style type="text/css">
    	.bom_menu{
			position: fixed;
			width: 94%;
			bottom: 10px;
			left: 3%;
			background: #2e3c50;
			border-radius:6px;
    	}
    	.bom_menu .lir{
    		display: inline-table;
    		background-repeat: no-repeat;
    		background-position: 20% center;
    		padding:13px 0 12px;
    		color: #ffffff;
    		width: 24%;
    		text-align: center;
    		font-size: 13px;
    		cursor: pointer;
    		background-size: 16px;
    	}
    </style>
</head>

<body>

	<div id="container" style="background-image: none;">
	<div id="map" style="z-index: 0;"></div>
    	<div id="header" style="position: relative; z-index: 10;">
            <div id="search">
            	<span></span>
                <input type="text" placeholder="综合查询" id="key" onclick="toSearch()"/>
                <!--<a href="#"></a>--></div>
        </div>
        <div id="tool">
        	<ul>
                <li>  
                	<a style="display:inline-block;background:#c6c6c5;text-align:center;color:#2e3c50;font-size:0.75em;padding:5px 0px; border-radius:5px;" href="<%=basePath %>phone/index">
                    	<p><img src="<%=curl %>images/li0.jpg" /></p>
                        <p>首页</p>
                    </a>
                </li>
                <li>
                	<a href="javascript:showRouteStatus()">
                    	<p><img src="<%=curl %>images/li2.jpg" id="route_status_img"/></p>
                        <p>路况</p>
                    </a>
                </li>
                <li>  
                	<a href="street_view.jsp">
                    	<p><img src="<%=curl %>images/li5.jpg" /></p>
                        <p>街景</p>
                    </a>
                </li>
                <%
                String ro=request.getParameter("route");
                if(ro!=null&&ro.equals("1")){  %>
                 <li>  
                	<a href="<%=curl%>route_detail.jsp">
                    	<p><img src="<%=curl %>images/li5.jpg" /></p>
                        <p>文字播报</p>
                    </a>
                </li>
              <%  }
                %>;
                
             </ul>
        </div>
        <div id="zoom" style="border-radius:6px;">
        	<span class="add" onclick="map.ZoomIn()"></span>
            <span class="minus" onclick="map.ZoomOut()"></span>
        </div>
        <!-- <div id="dw"><img src="<%=curl %>images/dw.png"></div> -->
        
        
        
        
        <div class="bom_menu">
        	<div class="lir" style="background-image: url('<%=curl %>images/menu1.png');background-size: 10px;" onclick="window.location.href='<%=curl %>rim.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;附近</div>
        	<div class="lir" style="background-image: url('<%=curl %>images/menu3.png');background-size: 14px;" onclick="window.location.href='<%=curl %>line_in_query.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;路线</div>
        	<div class="lir" style="background-image: url('<%=curl %>images/menu5.png');background-size: 14px;" onclick="loadPicShare()">&nbsp;&nbsp;&nbsp;&nbsp;美图</div>
        	<div class="lir" style="background-image: url('<%=curl %>images/menu4.png');background-size: 14px;" onclick="window.location.href='<%=basePath %>phone_traffic_showMain'">&nbsp;&nbsp;&nbsp;&nbsp;播报</div>
        </div>
    </div>
</body>
</html>
