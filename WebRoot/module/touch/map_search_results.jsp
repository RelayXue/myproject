<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/touch/";
	String category = request.getParameter("category");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
<head>

		<!-- Basic -->
		<meta charset="utf-8">
		<title>乌镇国际旅游区</title>		
		<meta name="keywords" content="乌镇" />
		<meta name="description" content="乌镇旅游">
		<meta name="author" content="taoguang.com">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		
		<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />

		<link rel="stylesheet" href="<%=curl %>css/bootstrap.css">
		<link rel="stylesheet" href="<%=curl %>css/theme.css">
		<link rel="stylesheet" href="<%=curl %>css/k_tables.css">
		
		<script src="<%=curl %>js/jquery.min.js"></script>
		<script src="<%=curl %>js/bootstrap.min.js"></script>
		<script src="<%=curl %>js/owl.carousel.min.js"></script>
		<script src="<%=curl %>js/k_tables.js"></script>
 

	 
		 <script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
		 <script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>
		 <script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
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
        .icosp{ background-image: url("img/sor.png");background-repeat: no-repeat;width: 22px; height: 37px;overflow: hidden; color: #fff; text-align: center;}
    </style>
<script type="text/javascript">
	//地图相关参数
    var lon = 121.4;
    var lat = 31.3;

    var map = null;
    var vectorLayer;
    var layer1, layer2, layer3, layer4, layer5;
    var featureStyle = null, featureStyle2 = null, meatureStyle = null;
    var mapParamS;
    var markerL;
 
	
	$(function(){
		$("#search_content").val("<%=request.getParameter("key")%>");
		Init();
		loadData();
	});
	
	/**参数说明：
	 * 根据长度截取先使用字符串，超长部分追加…
	 * str 对象字符串
	 * len 目标字节长度
	 * 返回值： 处理结果字符串
	 */
	function cutString(str, len) {
		//length属性读出来的汉字长度为1
		if(str.length*2 <= len) {
			return str;
		}
		var strlen = 0;
		var s = "";
		for(var i = 0;i < str.length; i++) {
			s = s + str.charAt(i);
			if (str.charCodeAt(i) > 128) {
				strlen = strlen + 2;
				if(strlen >= len){
					return s.substring(0,s.length-1) + "...";
				}
			} else {
				strlen = strlen + 1;
				if(strlen >= len){
					return s.substring(0,s.length-2) + "...";
				}
			}
		}
		return s;
	}
	
	//修改url地址
	function addHistory(){
		/*
		var state = {
			title: "",
			url: "<%=curl%>map_search_results.jsp?key="+$.trim($("#search_content").val()),
			otherkey: null
		};
		window.history.pushState(state, document.title, "<%=curl%>map_search_results.jsp?key="+$.trim($("#search_content").val()));
		*/
	}
	
	function addPoint(name, tab_info, fx, fy){
		//地图上添加点
           var icon2 = new SIcon(null, new SSize(22, 37));;
           icon2.GetDiv().innerHTML = '<div class="icosp" >'+name+'</div>';
           icon2.SetOpacity(0.2);
           var mm = new SMarker(new SLonLat(fx, fy), icon2, "tj");
           mm.info = tab_info;
           markerL.AddMarker(mm);
           mm.GetEVTReference().AddEventListener("click", null, function() {
               map.SInfoWindow.SetLonLat(this.GetLonLat());
        	   map.SInfoWindow.SetOffset(new SSize(-3, -34));
               map.SInfoWindow.SetInnerHTML(this.info);
               map.SInfoWindow.SetSize(new SSize(530, 360));
               map.SInfoWindow.Show();
           });
	}
	
	function nullStrCheck(str, def){
		if(str == undefined || str == null || str == "null" || str == "undefined" || str == ""){
			str = def;
		}
		return str;
	}
	
	function loadData(){
		clearAllPoint();
		var key = $.trim($("#search_content").val());
		addHistory();
		if(key.length > 0){
			var parms = new Object();
			parms.key = key;
			$.post("touch_main_mapSearch", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				if (code == 1) {
					
					var o = $.parseJSON(json.o);
					var points = [];
					for(i=0;i<o.length;i++){
						var img_name = '';
						if(o[i].dimages != undefined){
							var img_html = '';
							var imgs = o[i].dimages.split(",");
							if(imgs.length > 0) img_name = '<%=basePath%>upload/L_'+imgs[0]+'';
						}
						var url = "javascript:void(0)";
						var category = o[i].type;
						if(o[i].type.indexOf("002018") >= 0){url = "<%=curl%>nav_meishi_detail.jsp?id="+o[i].fuid; category = "餐饮";}
						if(o[i].type.indexOf("002015") >= 0){url = "<%=curl%>nav_gouwu_detail.jsp?id="+o[i].fuid; category = "购物";}
						if(o[i].type.indexOf("002016") >= 0){url = "<%=curl%>nav_yule_detail.jsp?id="+o[i].fuid; category = "娱乐";}
						if(o[i].type.indexOf("002017") >= 0){url = "<%=curl%>nav_zhusu_detail.jsp?id="+o[i].fuid; category = "住宿"; }
						var maker_info = '<div class="popup"><ul><li><img class="img-responsive" src="'+img_name+'" style="width:210px; height:310px;"/></li><li style="width: 280px; overflow: hidden;"><h3>'+cutString(o[i].fullname, 20)+'</h3>负责人：'+nullStrCheck(o[i].supervisor, '-')+'<br>	负责电话：'+nullStrCheck(o[i].supervisorphone, '-')+'<br>店铺电话：'+nullStrCheck(o[i].phone, '-')+'<br>地址：'+cutString(o[i].address, 18)+'</p><a class="btn btn-lg btn-yule" href="'+url+'">详情<i class="fa fa-angle-right"></i></a><a class="btn btn-lg btn-zhusu" href=javascript:showGoToRoute("'+o[i].fuid+'")>前往路径<i class="fa fa-location-arrow"></i></a></li></ul></div>';

						addPoint(String.fromCharCode(65+i), maker_info, o[i].fx, o[i].fy);
						points.push(new SLonLat(o[i].fx, o[i].fy));
					 
						if(o.length == 1){
			             
			               map.SInfoWindow.SetLonLat(new SLonLat(o[i].fx, o[i].fy));
			        	   map.SInfoWindow.SetOffset(new SSize(-3, -34));
			               map.SInfoWindow.SetInnerHTML(maker_info);
			               map.SInfoWindow.SetSize(new SSize(530, 360));
			               map.SInfoWindow.Show();
			                //map.SetCenter(new SLonLat(o[i].fx, o[i].fy), 4);
			               
						 
						}
						 
					}
					//点位自适应屏幕
					//if(o.length > 1){ map.ZoomToExtent(SUtil.GetMaxBounds(points)); }
				}else{
					$("#search_list").html("");
				}
			}, "json");
		}else{ $("#search_list").html(""); }
	}
	
	function clearAllPoint(){map.SInfoWindow.Hide(); vectorLayer.ClearFeatures(); markerL.ClearMarkers(); vectorLayer.DestroyFeatures();  addMyLocation(); }
	function addMyLocation(){
		var pos_x = "<%=request.getSession().getServletContext().getAttribute("Pos_X") %>";	
		var pos_y = "<%=request.getSession().getServletContext().getAttribute("Pos_Y") %>";	
		
		//地图上添加点
        var icon2 = new SIcon("<%=curl%>img/myi.png", new SSize(37, 62));
        icon2.SetOpacity(0.2);
        var mm = new SMarker(new SLonLat(pos_x, pos_y), icon2, "tj");
        mm.info = '<span style="text-align: center; width:100%">当前位置</span>';
        markerL.AddMarker(mm);
        /*
        mm.GetEVTReference().AddEventListener("click", null, function() {
            map.SInfoWindow.SetLonLat(this.GetLonLat(), new SSize(0, -28));
            map.SInfoWindow.SetInnerHTML(this.info);
            map.SInfoWindow.Show();
        });
        */
        map.SetCenter(new SLonLat(pos_x, pos_y)); 
	}
	
	function showGoToRoute(id){
		if(id != undefined && id != null && id.length > 0){
			var parms = new Object();
			parms.id = id;
			$.post("touch_main_queryRouteMes", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				dialog(hint);
				if (code == 1) {
					clearAllPoint();
					
	    			var data = json.o;
	    			
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
	        	    window.parent.drowLine3(data.xy);	//画线
	        	    
	        	    
	        	    var ppS1 = SPoint.GetPointSFromString(data.xy);
					map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
	        	    
					topSetTab(1);
				}
			}, "json");
		}
	}
	
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
	
</script>
<style type="text/css">
#box_1, #box_2, #box_3, #box_4{ display: none; }
</style>
</head>

<body class="sub">
<div id="map"></div>

<header class="text-center">
	<div class="top-left"><a href="javascript:history.go(-1)"><i class="fa fa-angle-left"></i></a></div>
	<div class="search-bar">
    	<div class="form-group">
            	<input class="search" type="search" placeholder="桐乡市乌镇..." id="search_content"><button type="button" class="btn btn-lg btn-search" onclick="loadData()">搜索</button>
		</div>
    </div>
</header>

<div class="tools">
	<button class="tool map-path" style="display: none;"><i></i>手选路线</button>
    <button class="tool map-search" onclick="window.location.href='<%=curl %>map_search.jsp'" style="display: none;"><i></i>搜索</button>
    <button class="tool map-home" onclick="window.location.href='<%=basePath %>touch/index'"><i></i>首页</button>
</div>
<div class="tools-zoom">
    <div class="map-zoom">
		<div class="z-in" onclick="map.ZoomIn()" ><i style="margin: 10px 8px;"></i></div>
		<div class="z-out" onclick="map.ZoomOut()"><i style="margin: 10px 8px;"></i></div>
	</div>
</div>
</body>
</html>