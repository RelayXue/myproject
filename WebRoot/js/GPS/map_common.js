 
    var lon = 786.75;
    var lat = 501;

    var map = null;
    var vectorLayer;
    var layer1, layer2, layer3, layer4, layer5;
    var featureStyle = null, featureStyle2 = null, meatureStyle = null;
    var mapParamS;
    var pZoom = null;
    function Init() { 
        SUtil.SetImagesLocation("/Resource/Theme/StyleDefault/");
        mapParamS = new SMapParamOption();
        //mapParamS.Resolutions =[0.000133695117187497*2,0.000133695117187497, 0.0000668475585937487, 0.0000334237792968743, 0.0000167118896484372, 0.00000835594482421859,0.00000835594482421859/2];  
        mapParamS.Resolutions =[ 0.0000668475585937487, 0.0000167118896484372,  0.00000835594482421859/2];//0.000133695117187497*2,   
        mapParamS.MaxExtent = new SBounds(120.2241140886411, 30.46069566628205, 120.7717292886409, 31.00831086628195); 
        map = new SMap('map', mapParamS);
        vectorLayer = new SVectorLayer("vLayer");
 
        layer1 = new STileLayer("乌镇地图", "http://60.12.184.19/wuzhen/tiles/", 'jpg');
        layer1.SetTilePathRuleFun(CustomUrlSw);
        map.AddLayer(layer1);
        //  layer3.SetZIndex(130); 

        map.AddLayer(vectorLayer);
        //map.AddControl(new SPanZoom());
        markerL = new SMarkerLayer("POI层");
        map.AddLayer(markerL);
        map.SetCursor("hand");
        //map.SetCenter(mapParamS.MaxExtent.GetCenterLonLat(), 1);  
        map.SetCenter(new SLonLat(120.484154,30.742853), 1); 
       // map.SetEffectiveExtent(new SBounds(120.403891,30.631676,120.629345,30.816679));
        
  
        
    } 
    //用于城管卫星地图
    function InitCG() { 
        SUtil.SetImagesLocation("/Resource/Theme/StyleDefault/");
        mapParamS = new SMapParamOption();
        mapParamS.Resolutions = [0.0000334237792968743, 0.0000167118896484372, 0.00000835594482421859];  
        mapParamS.MaxExtent = new SBounds(120.2241140886411, 30.46069566628205, 120.7717292886409, 31.00831086628195); 
        map = new SMap('map', mapParamS);
        vectorLayer = new SVectorLayer("vLayer");
 
        layer1 = new STileLayer("乌镇地图", "http://60.12.184.19/wuzhen/tileszh/", 'png');
        layer1.SetTilePathRuleFun(CustomUrlCG);
        map.AddLayer(layer1);
        //  layer3.SetZIndex(130); 

        map.AddLayer(vectorLayer);
        map.AddControl(new SPanZoomBar());
        markerL = new SMarkerLayer("POI层");
        map.AddLayer(markerL);
        map.SetCursor("hand");
        map.SetCenter(mapParamS.MaxExtent.GetCenterLonLat(), 0);  
        map.SetEffectiveExtent(new SBounds(120.403891,30.631676,120.629345,30.816679));
        
  
        
    } 
    function CustomUrlCG(pTile) {
        var z = this.GetMap().GetZoom() + 4;
        var r = pTile.GetRow();
        var col = pTile.GetCol();
        var path = (z + 1) + "/" + r + "/" + col + "." + this.GetType();
        z = null;
        r = null;
        col = null;
        return this.GetPrefixPath() + path;
    };
    

    function CustomUrlSw(pTile) {
        var z = (this.GetMap().GetZoom() + 2)*2;
        var r = pTile.GetRow(); 
        var col = pTile.GetCol();
        var path = (z) + "/" + r + "/" + col + "." + this.GetType();
        z = null;
        r = null;
        col = null;
        return this.GetPrefixPath() + path;
    };
    


    function __AddMarker(x, y) {
        var gg = new SIcon("Resource/Theme/StyleDefault/marker.png", new SSize(21, 25), new SPixel(-10, -25));
        var maker2 = new SMarker(new SLonLat(x, y), gg);
        markerL.AddMarker(maker2);
    }

    function LayerSwitch(index) {
        if (index == "1") {
            layer2.SetVisibility(true);
            layer1.SetVisibility(false); 
        } else if (index == "2") {
            layer2.SetVisibility(false);
            layer1.SetVisibility(true); 
        } else if (index == "3") {
        }
    }

    function LayerAutoDisplay(layerObj) {
        if (layerObj != null) {
            if (layerObj.GetVisibility() == true) {
                layerObj.SetVisibility(false);
            } else {
            layerObj.SetVisibility(true);
        } 
        map.SetCenter(map.GetCenter());
        }
    } 
    
    
    
//地图上显示房屋视频
function show(num,data){
	if(num==1){
		var contour=data;
	if(contour.length>0){
		for(var i=0;i<contour.length;i++){
 		   AreaShowLy(contour[i],contour[i].fxy);
		}
	}}
	if(num==2){
		var video=data;
	if(video.length>0){
		for(var i=0;i<video.length;i++){
			show_point(video[i].fx,video[i].fy,video[i].fuid);
		}
	}}
	 
}
//地图上影藏房屋视频
function hide(num,data){
	if(num==1){
		var contour=data;
	if(contour.length>0){
		for(var i=0;i<contour.length;i++){
			removeVectorBy("house");
		}
	}}
	if(num==2){var video=data;
	if(video.length>0){
		for(var i=0;i<video.length;i++){
			removeRy(video[i].fuid);
		}
	}}
	 
}
//地图小标签
function getPersonPanelHtml(node){
	try{ 
		var textHtml = "";
		textHtml += "<div id='label_box' style='width:150px'>";
		textHtml += "<div class='title_txt'>人员详情</div>";
		textHtml += "<div class='hr'></div>";
		textHtml += "<div  class='content_txt' >";
		textHtml +="<div class='itm'><span>人员姓名:</span>" + node.username+"</div>";
		textHtml += "</div>";
		textHtml += "<div class='hr'></div>";
		textHtml += "<div style='clear: both;' mce_style='clear: both;'></div>";
		textHtml += "</div>";
		}catch(evdd){}
		return textHtml;
	
}
//影藏一个房屋
function HideHouse(fxy){
	
}
//显示实时位置
function showPoints(nodes){
	if(nodes!=null&&nodes.length>0){
		for(var x=0;x<nodes.length;x++){
			var fx=nodes[x].fx;
			var fy=nodes[x].fy;
			var name=nodes[x].username;
			if (fx != null && fx.length > 0) {
				var icon2 = new SIcon("/image/marker.png",
						new SSize(21, 25));
				icon2.SetOpacity(0.2);
				var ll = new SLonLat(fx, fy);
				mm = new SMarker(new SLonLat(fx, fy), icon2, nodes[x].fuserUid);
				mm.__ll = ll;
				mm.__text = getPersonPanelHtml(nodes[x]); 
				markerL.AddMarker(mm);
				mm.AddEventListener("click", mm, function() { 
					map.SInfoWindow.SetLonLat(this.__ll); 
					map.SInfoWindow.SetInnerHTML(this.__text);
				 	 map.SInfoWindow.SetSize(new SSize(200, 200));
					 map.SInfoWindow.Show();
				}); 
			}
			var lbl = new SLabel(null, new SLonLat(fx, fy), null, name);
			lbl.SetOpacity(0.8);
			lbl.SetOffset(new SSize(10, -30));
			lbl.SetAdaptive();
			map.AddLabel(lbl);
			lbl.SetCssStyle("tJLabel");
		}
	}
}
function GetPointS(str) {
    var pointC2ll = [];
    var sT1 = str.split(',');
    for (var u = 0; u < sT1.length; u++) {
        var sT2 = sT1[u];
        var xx = sT2.split(' ');
        pointC2ll.push(new SPoint(xx[0], xx[1]));
    }
     
    return pointC2ll;
}
function AreaShowLy(date,fshape) {
    //坐标串
	  var l_rcgs2_1 = fshape;
      var geo = new SLinearRing(GetPointS(l_rcgs2_1));
      var featureStyle3 = new SFeatureStyle();
      featureStyle3.SetFillColor("white");
      featureStyle3.SetHoverFillColor("yellow");
      featureStyle3.SetStrokeColor("yellow");
      featureStyle3.SetStrokeStartArrow("Oval");
      featureStyle3.SetStrokeEndArrow("Classic");
      featureStyle3.SetHoverStrokeColor("yellow");
      //featureStyle3.SetStrokeDashStyle("Single");
      featureStyle3.SetStrokeArrowLength(5);
      featureStyle3.SetStrokeArrowWidth(5);
      featureStyle3.SetStrokeWidth(1.2);
      featureStyle3.SetHoverStrokeOpacity(0.8);
       var pf2 = new SFeature(geo, featureStyle3,"house");
       var textHtml = getHousePanelHtml(date);
		 
	    var cp = pf2.GetGeometry().GetComponents();
	    if(cp.length>0){
		    var point = cp[0];  }
	   // map.SetCenter(new SLonLat(point.GetX(),point.GetY()), 1); 
      vectorLayer.AddFeatures([pf2]);
      pf2.Activate();
      pf2.Activate();
	  pf2.AddEventListener("click", pf2, function() {
		  var cp = this.GetGeometry().GetComponents();
		 if(cp.length>0){
		   	var points = cp[0];  
		 	 map.SInfoWindow.SetLonLat(new SLonLat(points.GetX(),points.GetY()));
		  	 map.SInfoWindow.SetInnerHTML(textHtml);
		  	 map.SInfoWindow.SetSize(new SSize(300, 200));
		   		 map.SInfoWindow.Show();
		   	}
      
      
});
}
function getHousePanelHtml(node){
	try{ 
		var textHtml = "";
		textHtml += "<div id='label_box' style='width:200px'>";
		textHtml += "<div class='title_txt'>楼房详情</div>";
		textHtml += "<div class='hr'></div>";
		textHtml += "<div  class='content_txt' >";
		textHtml +="<div class='itm'><span>楼房名称:</span>" + node.fname +"</div>";
		textHtml +="<div class='itm'><span>楼房地址:</span>" + node.faddress +"</div>";
		textHtml += "</div>";
		textHtml += "<div class='hr'></div>";
		textHtml += "<div style='clear: both;' mce_style='clear: both;'></div>";
		textHtml += "</div>";
		}catch(evdd){}
		return textHtml;
	
}
 
 function dd(evt){
	 alert(123);
 }
// 描点----
function Point() {
	 
	drawPoint = new SDrawControl(vectorLayer, SHPoint, {
		Complete : Points
	}, null, null, "_rrctV");
	map.AddControl(drawPoint);
	document.getElementById("btnPoint").onclick = function() {
		if (drawPoint.GetActive() == true) {
			drawPoint.DeActivate();
			document.getElementById("btnPoint").value = "开启描点";
		} else {
			vectorLayer.ClearFeatures();
			drawPoint.Activate();
			document.getElementById("btnPoint").value = "取消描点";
		}
	};
}
function show_point(fx,fy,fuid) {
	if (fx != null && fx.length > 0) {
		var icon2 = new SIcon("/image/nvideo.png",new SSize(21, 25));
		icon2.SetOpacity(0.2);
		var ll = new SLonLat(fx, fy);
		mm = new SMarker(new SLonLat(fx, fy), icon2, fuid);
		
		markerL.AddMarker(mm);
		mm.AddEventListener("click", mm, function() {
			alert("视频有待接入...");
		})
	 
	}
}
function Points(geo) {
	vectorLayer.RemoveFeaturesByTag("_rrctV");
	// document.getElementById("txtXY").value = geo.GetX() + " " + geo.GetY();
	document.getElementById("fxy").value = geo.GetX() + "," + geo.GetY();
	document.getElementById("btnPoint").value = "开启描点";
	drawPoint.DeActivate();
	if (mm != null) {
		mm.MoveToWithLonLat(new SLonLat(geo.GetX(), geo.GetY()));
	}

};

// 显示地理位置
function showMaker(x, y, text, radius) {
	close();
	removeRy();
	// 1公里代表的经度
	if (radius != null && radius.length > 0) {
		var aa = Number(radius) / (SSystem.GetDtMi() / 1000);
		_drawCircle(parseFloat(x), parseFloat(y), parseFloat(aa));
		var temp = "";
		$.ajax({
			url : "serach_radius!getdata?fx=" + x + "&fy=" + y + "&raduis="
					+ aa + "&time=" + new Date(),
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				temp = data; // decodeURIComponent(data);
			}
		});
		if (temp.length > 0) {
			temp = decodeURIComponent(temp);
			var data1 = temp.split(";");
			if (data1 != null) {
				for ( var a = 0; a < data1.length; a++) {
					var data = data1[a].split(",");
					var icon2 = new SIcon(
							"/Gckq/resource/images/icon_marka.png", new SSize(
									30, 31), new SPixel(-14, -28));
					var maker2 = new SMarker(new SLonLat(data[2], data[3]),
							icon2);
					var ll = new SLonLat(data[2], data[3]);
					var Ssize = new SSize(30, 30);
					maker2.__ll = ll;
					maker2.__text = getDiv(data1[a]);
					markerL.AddMarker(maker2);
					maker2.AddEventListener("click", maker2, function() {
						map.SInfoWindow.SetLonLat(this.__ll);
						map.SInfoWindow.SetInnerHTML(this.__text);
						map.SInfoWindow.Show();
						map.SInfoWindow.SetSize(new SSize(330, 450));
					});
					var lbl = new SLabel(null, new SLonLat(data[2], data[3]),
							null, data[0]);
					lbl.SetOpacity(0.8);
					lbl.SetOffset(new SSize(20, -10));
					lbl.SetAdaptive();
					map.AddLabel(lbl);
					lbl.SetCssStyle("tJLabel");
				}
			}
		}
	}

	var icon2 = new SIcon("/Gckq/resource/images/map_marker_03.png", new SSize(
			32, 32), new SPixel(-16, -32));
	var maker2 = new SMarker(new SLonLat(x, y), icon2);
	var ll = new SLonLat(x, y);
	var Ssize = new SSize(30, 30);
	maker2.__ll = ll;
	markerL.AddMarker(maker2);
	map.SetCenter(new SLonLat(x, y), 6);

	var lbl = new SLabel(null, new SLonLat(x, y), null, text);
	lbl.SetOpacity(0.8);
	// lbl.SetBackgroundColor("red");
	lbl.SetOffset(new SSize(10, -30));
	lbl.SetAdaptive();
	map.AddLabel(lbl);
	// lbl.SetHoverCssStyle("tJLabel2");
	lbl.SetCssStyle("tJLabel");
}
// 简单定位
function dw(data) {
	// 解析
	var aa = data.split(",");
	var zb_x = aa[2];
	var zb_y = aa[3];
	/*
	 * if(zb_x=='null'){ alert("暂无GPS数据"); return; }
	 */
	// ----
	var icon2 = new SIcon("/Gckq/resource/images/icon_marka.png", new SSize(30,
			31), new SPixel(-14, -28));
	var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, aa[1]);
	var ll = new SLonLat(zb_x, zb_y);
	var Ssize = new SSize(30, 30);
	maker2.__ll = ll;
	maker2.__text = getDiv(data);
	markerL.AddMarker(maker2);
	map.SetCenter(new SLonLat(zb_x, zb_y), 6);
	/*
	 * map.SInfoWindow.SetLonLat(maker2.__ll);
	 * map.SInfoWindow.SetInnerHTML(maker2.__text); map.SInfoWindow.Show();
	 */

	maker2.AddEventListener("click", maker2, function() {
		map.SInfoWindow.SetLonLat(this.__ll);
		map.SInfoWindow.SetInnerHTML(maker2.__text);
		map.SInfoWindow.Show();
		// map.SInfoWindow.SetSize(new SSize(330, 150));
		// map.SInfoWindow.SetSize(new SSize(320, 150));
		// var tL = ConvertSizeToLonLat(new SSize(250,-100));
		// map.SetCenter(new SLonLat(this.__ll.GetLon() +
		// tL.GetLon(),this.__ll.GetLat() + tL.GetLat()));
		// map.SInfoWindow.SetSize(new SSize(300, 120));
	});
}
// 实时定位
function SsDw(data) {
	// 解析
	var aa = data.split(",");
	var zb_x = aa[2];
	var zb_y = aa[3];
	// ----
	// 清除点
	removeRy(aa[1]);
	var icon2 = new SIcon("/Gckq/resource/images/icon_marka.png", new SSize(30,
			31), new SPixel(-14, -28));
	var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, aa[1]);
	var ll = new SLonLat(zb_x, zb_y);
	var Ssize = new SSize(30, 30);
	maker2.__ll = ll;
	maker2.__text = getDiv(data);
	markerL.AddMarker(maker2);
	/*
	 * if(map.SInfoWindow.GetContainer().style.display=="none"){
	 * map.SInfoWindow.SetLonLat(maker2.__ll);
	 * map.SInfoWindow.SetInnerHTML(maker2.__text); map.SInfoWindow.Show(); }
	 */
	maker2.AddEventListener("click", maker2, function() {
		map.SInfoWindow.SetLonLat(this.__ll);
		map.SInfoWindow.SetInnerHTML(maker2.__text);
		map.SInfoWindow.Show();
		// var tL = ConvertSizeToLonLat(new SSize(250,-100));
		// map.SetCenter(new SLonLat(this.__ll.GetLon() +
		// tL.GetLon(),this.__ll.GetLat() + tL.GetLat()));
	});
	map.SetCenter(new SLonLat(zb_x, zb_y), 3);
}

// 显示div
function getDiv(data) {
	var aa = data.split(",");
	var textHtml = "";
	textHtml += "<DIV  />";
	textHtml += "<TABLE style=\"FONT-FAMILY: '宋体'; width:250px;height:150px; FONT-SIZE: 14px;\">";
	textHtml += "<TR>";
	textHtml += "<TD nowrap>姓名:" + aa[0] + "</TD>";
	textHtml += "<TR>";
	textHtml += "<TD nowrap>手机编号：" + aa[1] + "</TD>";
	textHtml += "<TR>";
	textHtml += "<TD nowrap>时间：" + aa[4] + "</TD>";
	textHtml += "</TR>";
	textHtml += "<TR >";
	textHtml += "<TD nowrap><a href='javascript:void(0)' onclick=send('"
			+ aa[0]
			+ "-"
			+ aa[1]
			+ "') /></a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick=Allocation('"
			+ aa[5] + "') ></a></TD>";
	textHtml += "</TR>";
	textHtml += "<TR>";
	/*
	 * textHtml += "<TD nowrap>速度："+aa[4]+"</TD>"; textHtml += "</TR>";
	 * textHtml += "<TD nowrap>里程："+aa[5]+"</TD>";
	 */
	textHtml += "<TD style=\"WIDTH: 50px;\"></TD>";
	textHtml += "</TR></TABLE></DIV>";
	return textHtml;
}
// 删除地图标记
function removeRy(GpsID) {
	if (GpsID != null && GpsID.length > 0) {
		markerL.ClearMarkersByTag(GpsID);
	} else {
		markerL.ClearMarkers();
	}
	map.SInfoWindow.Hide();
}
function removeVectorBy(GpsID){
	if (GpsID != null && GpsID.length > 0) {
		vectorLayer.RemoveFeaturesByTag(GpsID);
	}
	else{
		
	}
	map.SInfoWindow.Hide();
	
}
function openDw(dataList) {
	alert(dataList);
}
function times() {
	// 每1秒执行函式test()

	$('body').everyTime('1s', getData);

	// 每1秒执行，并命名计时器名称为A
	$('body').everyTime('1s', 'A', function() {
		// do something...
	});

	// 停止所有的在$('body')上计时器
	$('body').stopTime();

	// 停止$('body')上名称为A的计时器
	$('body').stopTime('A');

	// 停止$('body')上所有呼叫test()的计时器
	$('body').stopTime(test);

}

// 画图工具
function drawUtil(div, SH, TextID, Listener) {
	if (TextID != "" && TextID != null)
		XYTextID = TextID;
	if (drawUt == null) {
		drawUt = new SDrawControl(vectorLayer, SH, {
			Complete : Listener
		}, _SwHitInit("red", "yellow", 1.6));
		map.AddControl(drawUt);
		drawUt.Activate();
	} else {
		drawUt.Activate();
		clearAll();
	}
}

// 画图完毕执行的方法
function PointListener(geo) {
	if (drawUt.GetActive() == true) {
		$("#" + XYTextID, window.parent.document).val("");
		$("#" + XYTextID, window.parent.document).val(
				geo.GetX() + " " + geo.GetY());
		drawUt.DeActivate();
	}
}
function PointPolygon(geo) {
	if (drawUt.GetActive() == true) {
		$("#" + XYTextID, window.parent.document).val("");
		$("#" + XYTextID, window.parent.document)
				.val(geo.GetComponentsString());
		drawUt.DeActivate();
	}
}

// 清空图层
function clearAll() {
	vectorLayer.DestroyFeatures();
	$("#" + XYTextID, window.parent.document).val("");
}

// 停止画图
function DeAct() {
	drawUt.DeActivate();
	// alert("a");
}

// 样式定义
function _SwHitInit(fillColor, StrokeColor, width, transparency, HoverEnabled) {
	var _ftStyle = new SFeatureStyle();
	_ftStyle.SetFillColor(fillColor);
	_ftStyle.SetStrokeColor(StrokeColor);
	_ftStyle.SetStrokeWidth(width);
	// _ftStyle.SetFillOpacity(transparency);
	// _ftStyle.SetHoverEnabled(HoverEnabled);
	return _ftStyle;
}

// 地图定位移动
function mapMove(location, str) {
	if (location == null || location.length <= 0) {
		return;
	}
	var ppS = SMath.GetPointSFromString(location);
	// alert(ppS!=null);
	if (ppS != null) {
		map.SetCenter(null, 6); // 移动到中间 zoom为5
		map.SInfoWindow.SetLonLat(new SLonLat(ppS[0].GetX(), ppS[0].GetY()),
				new SSize(0, 0)); // 指针的位置
		map.SInfoWindow.SetInnerHTML(str);
		map.SInfoWindow.Show();
		var tL = ConvertSizeToLonLat(new SSize(150, -80)); // 经纬度转换
		map.SetCenter(new SLonLat(ppS[0].GetX() + tL.GetLon(), ppS[0].GetY()
				+ tL.GetLat())); // 移动到坐标点
	}
}

// 楼宇
function houses(flatcoor, name, bh, address, type, images) {
	var str;
	str = "<table width=\"450\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style='	font-size: 14px;line-height: 24px;'>";
	str = str
			+ "<tr> <td colspan=\"2\"><strong>"
			+ name
			+ "</strong><br /></td>  </tr>  <tr>    <td width=\"20%\">店铺编号</td>  <td width=\"80%\">"
			+ bh + "<br /></td>  </tr>  <tr>    <td>店铺地址</td>    <td>"
			+ address + "<br /></td>  </tr><tr>    <td>楼宇类型</td>    <td>"
			+ type + "<br /></td>  </tr> <tr>    <td colspan=\"2\"><img src=\""
			+ images + "\" width=\"380\" height=\"150\" /></td>  </tr></table>";
	mapMove(flatcoor, str);
}
// 店铺
function dianpu(flatcoor, code, name, type, build, admin, phone, address, image) {
	var str;
	str = "<table width=\"450\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style='	font-size: 14px;line-height: 24px;'>";
	str = str + "<tr><td colspan=\"2\"><strong>" + name
			+ "</strong><br /></td></tr>";
	str = str + "<tr><td>店铺编号</td><td>" + code + "</td></tr>";
	str = str + "<tr><td>店铺类型</td><td>" + type + "</td></tr>";
	str = str + "<tr><td>所属楼宇</td><td>" + build + "</td></tr>";
	str = str + "<tr><td>管理员</td><td>" + admin + "</td></tr>";
	str = str + "<tr><td>联系方式</td><td>" + phone + "</td></tr>";
	str = str + "<tr><td>店铺地址</td><td>" + address + "</td></tr>";
	str = str + "<tr>    <td colspan=\"2\"><img src=\"" + image
			+ "\" width=\"380\" height=\"150\" /></td>  </tr>";
	str = str + "</table>"
	mapMove(flatcoor, str);
}

// 将一组字符串转换成数组
function SMath() {
};
SMath.GetPointSFromString = function(pStr) {
	var rtnS = [];
	if (pStr != null && pStr != "") {
		var tmR = pStr.split(",");
		for ( var u = 0; u < tmR.length; u++) {
			if (tmR[u] != "") {
				var tmp2 = tmR[u].split(" ");
				if (tmp2.length == 2) {
					var pt = new SPoint(tmp2[0], tmp2[1]);
					rtnS.push(pt);
				}
			}
		}
	}
	return rtnS;
};

// 转换尺寸为经纬度
function ConvertSizeToLonLat(ss) {
	var rtnLL = new SLonLat(0, 0);
	if (ss != null) {
		var zm = map.GetZoom();
		rtnLL = new SLonLat(mapParamS.Resolutions[zm] * ss.GetWidth(),
				mapParamS.Resolutions[zm] * ss.GetHeight())
	}
	return rtnLL;
}

// 绘制环行线
function drowLine(flatcoor, name, bh, address, type, imageUrl) {
	if (imageUrl == null || imageUrl == "" || imageUrl == "null") {
		imageUrl = "images/fwtp.jpg";
	}
	var ppS = SMath.GetPointSFromString(flatcoor); // 分割
	var xL = new SLinearRing(ppS); // 画线
	var pf = new SFeature(xL, _SwHitInit("white", "yellow", 1.6)); // 添加样式
	vectorLayer.AddFeatures([ pf ]); // 加入图层
	pf.Activate(); // 激活图层
	str = "<table width=\"450\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style='	font-size: 14px;line-height: 24px;'>";
	str = str
			+ "<tr> <td colspan=\"2\"><strong>"
			+ name
			+ "</strong><br /></td>  </tr>  <tr>    <td width=\"20%\">店铺编号</td>  <td width=\"80%\">"
			+ bh + "<br /></td>  </tr>  <tr>    <td>店铺地址</td>    <td>"
			+ address + "<br /></td>  </tr><tr>    <td>楼宇类型</td>    <td>"
			+ type + "<br /></td>  </tr> <tr>    <td colspan=\"2\"><img src=\""
			+ imageUrl
			+ "\" width=\"380\" height=\"150\" /></td>  </tr></table>";
	pf._text = str;
	pf.AddEventListener("click", pf, FeatureClick1);
}

// 绘制点
function drowPoint(flatcoor, code, name, type, build, admin, phone, address,
		image) {
	if (flatcoor == null || flatcoor.length <= 0) {
		return;
	}
	if (image == null || image == "" || image == "null") {
		image = "images/fwtp.jpg";
	}
	var ppS = SMath.GetPointSFromString(flatcoor); // 分割
	var gg5 = new SIcon("../TJMap/Resource/Theme/StyleDefault/marker.png",
			new SSize(21, 25));
	var mm3 = new SMarker(new SLonLat(ppS[0].GetX(), ppS[0].GetY()), gg5);
	markerL.AddMarker(mm3);

	var str = "<table width=\"450\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style='	font-size: 14px;line-height: 24px;'>";
	str = str + "<tr><td colspan=\"2\"><strong>" + name
			+ "</strong><br /></td></tr>";
	str = str + "<tr><td>店铺编号</td><td>" + code + "</td></tr>";
	str = str + "<tr><td>店铺类型</td><td>" + type + "</td></tr>";
	str = str + "<tr><td>所属楼宇</td><td>" + build + "</td></tr>";
	str = str + "<tr><td>管理员</td><td>" + admin + "</td></tr>";
	str = str + "<tr><td>联系方式</td><td>" + phone + "</td></tr>";
	str = str + "<tr><td>店铺地址</td><td>" + address + "</td></tr>";
	str = str + "<tr>    <td colspan=\"2\"><img src=\"" + image
			+ "\" width=\"380\" height=\"150\" /></td>  </tr>";
	str = str + "</table>"

	mm3._text = str;
	mm3.AddEventListener("click", mm3, FeatureClick2);

}

// 显示框
function FeatureClick1(evt) {
	var g = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
	map.SInfoWindow.SetLonLat(new SLonLat(g.GetLon(), g.GetLat()), new SSize(0,
			-6));
	map.SInfoWindow.SetInnerHTML(this._text);
	map.SInfoWindow.Show();
	var tL = ConvertSizeToLonLat(new SSize(150, -80));
	map.SetCenter(null, 6);
	map.SetCenter(new SLonLat(g.GetLon() /* + tL.GetLon() */, g.GetLat()/*
																		 * +
																		 * tL.GetLat()
																		 */));
}

function FeatureClick2(evt) {
	var g = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
	map.SInfoWindow.SetLonLat(new SLonLat(g.GetLon(), g.GetLat()), new SSize(0,
			0));
	map.SInfoWindow.SetInnerHTML(this._text);
	map.SInfoWindow.Show();
	// var tL = ConvertSizeToLonLat(new SSize(150,-80));
	map.SetCenter(null, 6);
	map.SetCenter(new SLonLat(g.GetLon(), g.GetLat()));
}
// 画圆
var id = "";
var drawCircle2 = null;
function drawCircle(perid) {
	id = perid;
	drawCircle2 = new SDrawControl(vectorLayer, SHCircle, {
		Complete : Circle2Listener
	}, featureStyle, "_rrctVCD", "");
	map.AddControl(drawCircle2);
	drawCircle2.Activate();
}
// 根据圆心 半径画圆
function _drawCircle(x, y, r, fuid) {
	var xL = new SCircle(x, y, r);
	var pf = new SFeature(xL, null, fuid);// featureStyle
	vectorLayer.AddFeatures([ pf ]);
	pf.fuid = fuid;
	pf.Activate();
	pf.AddEventListener("mouseup", pf, FeatureClick);
}
// 取消画圆
function cancel() {
	if (drawCircle2 != null) {
		drawCircle2.DeActivate();
	}
}

var lbl001 = null;
function FeatureClick(evt) {
	if (evt.button == 2) {
		var fuid = this.fuid;
		var ll = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
		if (lbl001 == null) {
			lbl001 = new SLabel(
					null,
					ll,
					null,
					"<a href=javascript:delfence(\'"
							+ fuid
							+ "\') onclick=\"if(!confirm(\'确定删除！\')){return false;}\"  >删除</a>");
			lbl001.SetOpacity(0.8);
			map.AddLabel(lbl001);
		} else {
			lbl001.MoveToWithLonLat(ll);
		}
		lbl001.Show();
	}
}
// 删除围栏
function delfence(fenceid) {
	$.ajax({
		url : "fence!delete?fuid=" + fenceid + "&time=" + new Date(),
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			// 从地图上删除圆
			vectorLayer.RemoveFeaturesByTag(fenceid);
			lbl001.Hide();
			window.frames["right"].location.reload(true);
			// window.frames["right"].reload(true);
			// window.location.reload(true);
		}
	});
}
function _drawCircle1(x, y, r) {
	var xL = new SCircle(x, y, r);
	var pf = new SFeature(xL, featureStyle);
	vectorLayer.AddFeatures([ pf ]);
}
function Circle2Listener(geo) {
	// 圆心坐标
	var x = geo.GetX();
	var y = geo.GetY();
	var xy = x + "," + y;
	// 半径
	var r = geo.GetRadius();
	// 取消画圆
	drawCircle2.DeActivate();
	// 是否确定
	if (confirm("您已成功勾画出围栏，是否保存")) {
		define(id, xy, r, $('#fenceName').val());
	} else {
		cancel();
		close();
		return;
	}

	// show(3,id,xy,r);
}

// 显示提示框
var wBox = null;
function showDiv(type, perid, xy, r) {
	if (type == 1) {
		wBox = $("#wbox1").wBox({
			title : "提示框",
			html : "<div class='demo'>请先选择人员！</div>"
		});
		wBox.showBox();
	}
	if (type == 2) {
		wBox = $("#wbox1").wBox({
			title : "提示框",
			html : "<div class='demo'>点击地图并拖动鼠标调整半径</div>"
		});
		wBox.showBox();
		id = perid;
		drawCircle();
	}
	if (type == 3) {
		wBox = $("#wbox1")
				.wBox(
						{
							title : "提示框",
							html : "<div class='demo' style='padding-bottom:10px' >您已经成功勾画出围栏区域。<br/><br/>请输入围栏名称:<input name='fenceName' id='fenceName' type='text'/><br/><br/><button onclick=javascript:define('"
									+ id
									+ "','"
									+ xy
									+ "','"
									+ r
									+ "',$('#fenceName').val()) >确定</button>"
									+ "&nbsp;&nbsp;<button href='javascript:window.parent.close()'>取消</button><br/><span>   </span>"
						});
		wBox.showBox();
	}
	if (type == 4) {
		wBox = $("#wbox1").wBox({
			title : "围栏区域",
			opacity : 0,
			requestType : "iframe",
			target : "fence!showFence?perid=" + perid
		});
		wBox.showBox();
	}
}
function define(perid, xy, r, name) {
	var childHtml = $(document.getElementById("right").contentWindow.document.body);
	var Stime1 = childHtml.find("input[id='Stime1']").val();
	var Etime1 = childHtml.find("input[id='Etime1']").val();
	var Stime2 = childHtml.find("input[id='Stime2']").val();
	var Etime2 = childHtml.find("input[id='Etime2']").val();
	/*
	 * $("#from1").attr("action", "fence!add"); $("#fxy").attr("value", xy);
	 * $("#Stime1").attr("value", Stime1); $("#Etime1").attr("value", Etime1);
	 * $("#Stime2").attr("value", Stime2); $("#Etime2").attr("value", Etime2);
	 * $("#Radius").attr("value", r); $("#perid_list").attr("value", perid);
	 * $("#fname").attr("value", name);
	 */
	// $("#from1").submit();
	$.ajax({
		type : 'POST',
		url : "fence!add?time=" + new Date(),// ?fxy="+ xy +
		// "&Stime1="+Stime1+"&Etime1="+Etime1+"&Stime2="+Stime2+"&Etime2="+Etime2+"&Radius="+r+"&perid_list="+perid+"
		data : {
			fxy : xy,
			Stime1 : Stime1,
			Etime1 : Etime1,
			Stime2 : Stime2,
			Etime2 : Etime2,
			Radius : r,
			perid_list : perid,
			fname : name
		},
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			window.frames["right"].location.reload(true);
			// window.frames["right"].reload(true);
			// window.location.reload(true);
		}
	});
}
// 取消画圆
function close() {
	// 清除地图标记
	map.ClearAll(); 
	lbl001 = null;
	vectorLayer.DestroyFeatures();
	// 关闭提示框
	if (wBox != null) {
		wBox.close();
	}
}
// 清空地图上的线和点
function ClearVS() {
	//map.ClearAll();
	if (vectorLayer) {
		vectorLayer.DestroyFeatures();
	}
	if (markerL) {
		markerL.ClearMarkers();
	}
	map.ClearLabels();
	map.SInfoWindow.Hide();
}

function addMarkerToMap(id, x, y, text) {
	var tagArr = markerL.GetMarkersByTag(id);
	if (tagArr.length == 0) {
		var icon2 = new SIcon("/Gckq/resource/images/icon_marka.png",
				new SSize(32, 32), new SPixel(-16, -32));
		var maker2 = new SMarker(new SLonLat(x, y), icon2, id);
		var ll = new SLonLat(x, y);
		maker2.__ll = ll;
		markerL.AddMarker(maker2);
		var lbl = new SLabel(null, new SLonLat(x, y), null, text);
		lbl.SetOpacity(0.8);
		lbl.SetOffset(new SSize(10, -30));
		lbl.SetAdaptive();
		map.AddLabel(lbl);
		lbl.SetCssStyle("tJLabel");
		map.SetCenter(new SLonLat(x, y), 6);
	} 
	map.SInfoWindow.Hide();
}
function addCircleToMap(node) {
	map.ClearLabels();
	if (node != null && node.attributes != null) {
		var vS = vectorLayer.GetFeaturesByTag(node.id);
		if (vS.length == 0) {
			var   featureStyle2 = new SFeatureStyle();
	        featureStyle2.SetFillColor("yellow");
	        featureStyle2.SetHoverFillColor("none");
	        featureStyle2.SetStrokeColor("blue");
	        featureStyle2.SetStrokeStartArrow("Oval");
	        featureStyle2.SetStrokeEndArrow("Classic");
	        featureStyle2.SetStrokeDashStyle("ShortDashDot");
	        featureStyle2.SetStrokeArrowLength(5);
	        featureStyle2.SetStrokeArrowWidth(5);
	        featureStyle2.SetStrokeWidth(1.2);
	        featureStyle2.SetHoverEnabled(false);
			var xL = new SCircle(node.attributes.x, node.attributes.y,
					node.attributes.radius / getMapUnitMi());
			var pf = new SFeature(xL, featureStyle2, node.id);// featureStyle
			vectorLayer.AddFeatures([ pf ]);
			pf.Activate();
			pf.AddEventListener("click", pf, circleFeatureClick);
			map.SetCenter(new SLonLat(node.attributes.x, node.attributes.y), 6);
		}
	}
}
function circleFeatureClick(evt) {
}

function getMapUnitMi() {
	return 94690.206;
}
var __idx=0;
function addProjectMarkerToMap(node) { 
	if (node != null) {
		try {  
			if (node.attributes.isProject) {  
				__idx++;
				var iconUrl="/resource/images/build6.png";
				if(__idx%2==0){
					iconUrl="/resource/images/build6.png";
				}
				var x = parseFloat(node.attributes.x);
				var y = parseFloat(node.attributes.y);
				
				var icon2 = new SIcon(iconUrl,
						new SSize(60, 80), new SPixel(-30, -40));
				var maker2 = new SMarker(new SLonLat(x,y), icon2, node.id);
				var ll = new SLonLat(x, y);
				maker2.__ll = ll;
				markerL.AddMarker(maker2);
				var lbl = new SLabel(null, new SLonLat(x, y), null, node.text);
				lbl.SetOpacity(0.8);
				lbl.SetOffset(new SSize(30, -10));
				lbl.SetAdaptive();
				map.AddLabel(lbl);
				lbl.SetCssStyle("tJLabel"); 
				lbl.Hide();
				maker2.__ll = ll;

				maker2.__text = getProjectPanelHtml(node); 
				markerL.AddMarker(maker2);
				maker2.AddEventListener("click", maker2, function() { 
					map.SetCenter(new SLonLat(x, y), 6);
					map.SInfoWindow.SetLonLat(this.__ll); 
					map.SInfoWindow.SetInnerHTML(this.__text);
					 map.SInfoWindow.Show();
				}); 
				
				maker2._label = lbl;
				maker2.AddEventListener("mouseover",maker2,function(){
					this._label.Show(); 
				});
				maker2.AddEventListener("mouseout",maker2,function(){
					this._label.Hide(); 
				});
				map.SetCenter(new SLonLat(node.attributes.x, node.attributes.y), 6);
				icon2=null;
				maker2=null;
				lbl=null;
			}
			if(node.children!=null && node.children.length>0){
				for(var u=0;u<node.children.length;u++){
					var chlNode = node.children[u];
					//addProjectMarkerToMap(chlNode); 
				}
			}

		} catch (ex) {
		}
	}
}
function getProjectPanelHtml(projectNode){
	var rtnHtml="";
	try{ 
	rtnHtml+="<div style=\" height:250px;width:400px \"><table class=\"table\" style=\"font-size: 11pt;\" cellSpacing=\"0\" cellPadding=\"0\"     width=\"400px\">";
	rtnHtml+="        <tbody><tr>";
	rtnHtml+="            <td colSpan=\"4\">";
	rtnHtml+="                <span style=\"font-size: 18pt;\">"+projectNode.text+"</span>";
	rtnHtml+="            </td>";
	rtnHtml+="        </tr>"; 
	rtnHtml+="<tr><td>工程视频:</td><td colSpan=\"3\"><a href=\"javascript:void(0)\" onclick=\"openVideo('"+projectNode.attributes.count+"')\">工程视频</a> &nbsp;</td></tr>";
	rtnHtml+="<tr><td>现场人员:</td><td colSpan=\"3\"><a href=\"javascript:void(0)\">"+getProjectUserHtml(projectNode)+"&nbsp;</td></tr>";
	rtnHtml+="        <tr><td>项目联系人:</td><td>"+projectNode.attributes.xmxx+"&nbsp;</td><td>建筑面积：</td><td>"+projectNode.attributes.jzmj+"&nbsp;</td></tr>";
	rtnHtml+="        <tr><td>开工时间:</td><td>"+projectNode.attributes.kgsj+"&nbsp;</td><td>完工时间：</td><td>"+projectNode.attributes.wksj+"&nbsp;</td></tr>";
	rtnHtml+="        <tr><td>项目地址:</td><td colspan='3'>"+projectNode.attributes.xmdz+"&nbsp;</td></tr>";
	rtnHtml+="        <tr><td>项目描述:</td><td   colSpan=\"3\"> "+projectNode.attributes.fjxx+"&nbsp;</td></tr>";
	rtnHtml+="        <tr><td  colSpan=\"4\" align='right' > <a href=\"javascript:void(0)\"  onclick=\"openProject('"+projectNode.id+"')\" >项目详情</a> </td></tr>";
	rtnHtml+="    </tbody></table></div>";
	}catch(evdd){}
	return rtnHtml;
}
function getProjectUserHtml(projectNode){
	var rtnHtml="";
	for(var u=0;u<projectNode.children.length;u++){
		var cd = projectNode.children[u];
		if(cd.attributes.x=="" || cd.attributes.y==""){
			rtnHtml +="<a href=\"#\">"+cd.text+"</a> &nbsp;";
			// onclick=\"alert('暂无位置信息！')\"
		}else{
			rtnHtml +="<a href=\"#\" >"+cd.text+"</a> &nbsp;";
			//onclick=\"addMarkerToMap('"+cd.id+"',"+cd.attributes.x+","+cd.attributes.y+",'"+cd.text+"')\"
		}

	}
	return rtnHtml;
}

function getMaxBounds(pointS){
	var rtnBnd = new SBounds();
	if(pointS!=null && pointS.length>0){
		var tmpPt = pointS[0];
		rtnBnd.SetLeft(tmpPt.GeX());
		rtnBnd.SetBottom(tmpPt.GeY());
		rtnBnd.SetTop(tmpPt.GeY());
		rtnBnd.SetRight(tmpPt.GeX());
		for(var u=0;u<pointS.length;u++){
			var pt = poingS[u];
			if(pt.GetX()<rtnBnd.GetLeft()){
				rtnBnd.SetLeft(pt.GetX());
			} 
			if(pt.GetX()>rtnBnd.GetLeft()){
				rtnBnd.SetRight(pt.GetX());
			}

			if(pt.GetY()<rtnBnd.GetBottom()){
				rtnBnd.SetBottom(pt.GetX());
			}

			if(pt.GetY()>rtnBnd.GetTop()){
				rtnBnd.SetTop(pt.GetY());
			}
		}
	}
	return rtnBnd;
}

