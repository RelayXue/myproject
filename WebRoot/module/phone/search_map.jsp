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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/objective.css">
<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
<script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
<script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>

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
	//1显示列表点 2显示单点
	var p = "<%=request.getParameter("p")%>";

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
    	if(p == "1"){
    		//显示列表点
    		var point_list = localStorage.getItem("point_list");
    		if(point_list != null && point_list.length > 0){
    			var data = $.parseJSON(point_list);
    			var points = [];
    			for(i=0;i<data.length;i++){
				    var icon2 = new SIcon(null, new SSize(22, 37));
				    icon2.GetDiv().innerHTML = '<div class="icosp" >'+String.fromCharCode(65+i)+'</div>';
				    icon2.SetOpacity(0.2);
				    var mm = new SMarker(new SLonLat(data[i].fx, data[i].fy), icon2, "tj");
				    mm.id = data[i].fuid;
				    mm.ix = String.fromCharCode(65+i);
				    mm.name = data[i].fullname;
				    mm.type = data[i].type;
				    mm.address = data[i].address;
				    markerL.AddMarker(mm);
				    mm.GetEVTReference().AddEventListener("touchstart", null, function() {
				    	showInfoBox(this.id, this.ix, this.name, this.type, this.address);
				    });
				    points.push(new SLonLat(data[i].fx, data[i].fy));
				    if(i == 0) {showInfoBox(data[i].fuid, String.fromCharCode(65+i), data[i].fullname, data[i].type, data[i].address); map.SetCenter(new SLonLat(data[i].fx, data[i].fy)); }
    			}
		  		//地图自适应布满点位
				if(map != null) map.ZoomToExtent(SUtil.GetMaxBounds(points));
    		}
    	}else if(p == "2"){
			var icon2 = new SIcon(null, new SSize(22, 37));
		    icon2.GetDiv().innerHTML = '<div class="icosp" ></div>';
		    icon2.SetOpacity(0.2);
		    var mm = new SMarker(new SLonLat("<%=request.getParameter("x")%>", "<%=request.getParameter("y")%>"), icon2, "tj");
		    markerL.AddMarker(mm);
		    showInfoBox("<%=request.getParameter("id")%>", "", "<%=request.getParameter("name")%>", "<%=request.getParameter("type")%>", "<%=request.getParameter("address")%>"); 
		    map.SetCenter(new SLonLat("<%=request.getParameter("x")%>", "<%=request.getParameter("y")%>"), 3);
    	}
    });
    
    function showInfoBox(id, ix, name, type, address){
    	var type_name = "";
		for(k = 0;k<basetemp.length;k++){
			var code = basetemp[k].code;
			if(code == type){ type_name = '【'+basetemp[k].fullname+'】'; break;}
		}
    
    	var html = '<div>';
    	html += '<p class="title">';
    	html += '<span>'+ix+' '+name+'</span>';
    	html += '<br /><a>'+type_name+'</a>';
    	html += '</p>';
    	html += '<p class="f_r"><a href="<%=curl%>search_detail.jsp?id='+id+'&category='+type+'">详情></a></p>';
    	html += '</div>';
    	html += '<div id="text">';
    	html += '<p>地址：'+address+'</p>';
    	html += '</div>';
    	html += '<div id="btn">';
    	html += '<p class="qza"><a href="<%=curl%>line_in_query.jsp?end_point_id='+id+'&end_point_name='+name+'"><img src="images/qza.jpg">去这儿</a></p>';
    	html += '<p class="zzb"><a href="<%=curl %>rim.jsp"><img src="images/zzb.jpg">找周边</a></p>';
    	html += '</div>';
    	$("#footer").html(html);
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
    </script>
</head>

<body>

	<div id="container" style="background-image: none;">
	<div id="map" style="z-index: 0;"></div>
    	<div id="header" style="position: relative; z-index: 10;">
        	<div id="return">
            	<p><a href="javascript:toBack()">&nbsp;</a></p>
            </div>
            <div id="search" style="width: 88%">
            	<span></span>
                <input type="text" placeholder="综合查询" id="key" onclick="toSearch()"/>
                <!--<a href="#"></a>--></div>
        </div>
        <div id="zoom" style="top: 65px; bottom:auto;">
        	<span class="add" onclick="map.ZoomIn()"></span>
            <span class="minus" onclick="map.ZoomOut()"></span>
        </div>
        <div id="footer">

        </div>
    </div>
</body>
</html>
