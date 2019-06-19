
function SMath() {
}
//求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
SMath.GetLineCrossPoint = function (pt0, pt1, pt2, pt3) {
    //如果线段两点坐标一样，不进行计算 
	if (pt0.GetX() == pt2.GetX() && pt0.GetY() == pt2.GetY()) {
		return pt0;
	}
	if (pt1.GetX() == pt3.GetX() && pt1.GetY() == pt3.GetY()) {
		return pt1;
	}
    // 假设是求线段pt0-pt1与线段pt2-pt3的交点 
	var t0, t1;
    // (1-t0)*pt0+t0*pt1=(1-t1)*pt2+t1*pt3
    // 两个方程，两个未知数，求解t0和t1
	t0 = pt2.GetY() * (pt3.GetX() - pt2.GetX()) - pt2.GetX() * (pt3.GetY() - pt2.GetY()) - (pt0.GetY() * (pt3.GetX() - pt2.GetX()) - pt0.GetX() * (pt3.GetY() - pt2.GetY()));
	t0 /= ((pt1.GetY() - pt0.GetY()) * (pt3.GetX() - pt2.GetX()) - (pt1.GetX() - pt0.GetX()) * (pt3.GetY() - pt2.GetY()));
	t1 = parseFloat(pt0.GetX()) + parseFloat(t0 * (pt1.GetX() - pt0.GetX())) - parseFloat(pt2.GetX());
	t1 /= (pt3.GetX() - pt2.GetX());
    // 检查t0和t1以判断交点是否在线段上
	if (isNaN(t0) || isNaN(t1)) {
		return null;
	}
	if (t0 < 0 || t0 > 1 || t1 < 0 || t1 > 1) {
		return null;
	}
	return new SPoint((1 - t0) * pt0.GetX() + t0 * pt1.GetX(), (1 - t0) * pt0.GetY() + t0 * pt1.GetY());
};
//求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
SMath.GetLineIsCross = function (pt0, pt1, pt2, pt3) {
	var _tu = SMath.GetLineCrossPoint(pt0, pt1, pt2, pt3);
	if (_tu != null) {
		return true;
	}
	return false;
};
SMath.IsPointInSuface = function (pPt, pPtS) {
	if (pPt != null && pPtS != null) {
		var __c = 0;
		for (var uu = 0; uu < pPtS.length - 1; uu++) {
			var p1 = pPtS[uu];
			var p2 = pPtS[uu + 1];
			if (SMath.GetLineIsCross(pPt, new SPoint(0, 0), p1, p2)) {
				__c = __c + 1;
			}
		}
		if (__c % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	return false;
};
SMath.GetPointSFromString = function (pStr) {
	var rtnS = [];
	if (pStr != null && pStr != "") {
		var tmR = pStr.split(",");
		for (var u = 0; u < tmR.length; u++) {
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
function GetCurrentObjFromCache(lonlat) {
	if (_backData != null) {
		try {
			for (var u = 0; u < _backData.length; u++) {
				var tm1 = _backData[u];
				if (tm1.FZblx == 1) {//点
				} else {
					if (tm1.FZblx == 2) {//线
					} else {
						if (tm1.FZblx == 3) {//面
							if (SMath.IsPointInSuface(p2, SMath.GetPointSFromString(tm1.FZb)) == true) {
								return tm1;
							}
						}
					}
				}
			}
		}
		catch (_e) {
		}
	}
	return null;
}
function IsPointInRect(p2,dItem){
	try{
		
	}catch(evcd){}
}
var lon = 120.16145404661061;
var lat = 30.332947821559802;
var map = null;
var vectorLayer;
var layer1, layer2, layer3, layer4, layer5;
var featureStyle = null, featureStyle2 = null, meatureStyle = null;
var oMap, oPnlRight;
function Init_sw_old() {
	SUtil.SetImagesLocation("TJMap/Resource/Theme/StyleDefault/");
	var mapParamS = new SMapParamOption();
	mapParamS.Resolutions = [0.000254734326145017, 0.000127367163072508, 0.0000636835815362542, 0.0000318417907681271, 0.0000159208953840804];
	mapParamS.NumZoomLevels = 5;
	mapParamS.MaxExtent = new SBounds(119.93996255, 29.97676555, 120.46369632460916, 30.50049932460916);
	map = new SMap("map_sw", mapParamS);//map_sw
	vectorLayer = new SVectorLayer("vLayer");
	layer1 = new STileLayer("\u57fa\u7840\u6570\u636e", "tiles/vr/", "jpg");
	layer1.SetMainLayer(true);
	map.AddLayer(layer1);
	layer1.SetTilePathRuleFun(CustomUrl);
	map.AddLayer(vectorLayer);
	map.AddControl(new SPanZoomBar());
	markerL = new SMarkerLayer("POI\u5c42");
	map.AddLayer(markerL);
	map.SetCenter(new SLonLat(lon, lat), 1);
	map.SetCursor("hand");
	map.AddEventListener("mouseup", MapMouseUp);
	map.AddEventListener("mousemove", MapMouseMove);
	featureStyle = new SFeatureStyle();
	featureStyle.SetFillColor("gray");
	featureStyle.SetFillOpacity(0.3);
	featureStyle.SetStrokeColor("yellow");
	featureStyle.SetStrokeWidth(2);
	InitControl();
	//ZSY();
	MapMouseUp();
	SearchControl();
}
function InitControl() {
	oMap = document.getElementById("map");
	oPnlRight = document.getElementById("pnlRight");
}
function CustomUrl(pTile) {
	var z = this.GetMap().GetZoom();
	var path = (z + 1) + "/" + pTile.GetCol() + "," + pTile.GetRow() + "." + this.GetType();
	return this.GetPrefixPath() + path;
}
function MapMouseUp(evt) {
	var zm = map.GetZoom();
	if(zm>3&& __tag==11){
		var extent = map.GetExtent();
		$.getScript("/Gis/swly?t=" + extent.GetTop() + "&l=" + extent.GetLeft() + "&r=" + extent.GetRight() + "&b=" + extent.GetBottom(), function () {
		}); 
	}else{
		_backData=null;
		_backData=[];
	}
}
var __swHitLbl = null;
var _ftStyle=null;
function _SwHitInit(){  
var tt = map.GetLabelsByTag("ggg");
	if(tt.length==0){
		  __swHitLbl = new SLabel(null, new SLonLat(0,0), null, "","ggg");
		__swHitLbl.SetCssStyle("lab");
		__swHitLbl.SetOffset(new SSize(10, -23));
		map.AddLabel(__swHitLbl);
	}
	
	_ftStyle = new SFeatureStyle();
        _ftStyle.SetFillColor("Yellow");
        _ftStyle.SetStrokeColor("red");
        _ftStyle.SetStrokeWidth(1.6);
	
 
}

function MapMouseMove(evt) {	
	var zm = map.GetZoom(); 
	if (evt.button != 0 && zm<4 && __tag==11) {
		return;
	} 
	try { 
		if (_backData != null && _backData.length > 0) {
			var ll = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
			vectorLayer.RemoveFeaturesByTag("hit"); 
			//map.HideLabelsByTag("ggg");
			var _tmpData = GetInBackDataRect(ll);
			for (var uu = 0; uu < _tmpData.length; uu++) {
				var ppS = SMath.GetPointSFromString(_tmpData[uu].FZb);
				if (SMath.IsPointInSuface(new SPoint(ll.GetLon(), ll.GetLat()), ppS) == true) {
					var xL = new SLinearRing(ppS);
					var pf = new SFeature(xL, _ftStyle, "hit");
					pf._data = _backData[uu];
					vectorLayer.AddFeatures([pf]);
					pf.Activate();
					pf.AddEventListener("click", null, FeatureClick);  
					_SwHitInit();  
					__swHitLbl.SetTitle(decodeURIComponent(_tmpData[uu].FMc));
					__swHitLbl.MoveToWithLonLat(ll);
					break;
				}
			} 
		}
	}
	catch (uuv) {
	}
}
function GetInBackDataRect(_ll){
	var rtnData=[];
	if (_backData != null && _backData.length > 0) {
		try{
			for (var uu = 0; uu < _backData.length; uu++) {
				var itm = _backData[uu];
				if(_ll.GetLon()>itm.FL && _ll.GetLat()<itm.FR && _ll.GetLon()>itm.FB && _ll.GetLat()<itm.FT){
					rtnData.push(itm);
				}
				itm=null;
			}
		}catch(evcd){}
	}
	return rtnData;
}
function FeatureClick(evt) {
	if (this._data) {
		var g = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
		map.SInfoWindow.SetLonLat(new SLonLat(g.GetLon(), g.GetLat()), new SSize(0, -6));
		var name = decodeURIComponent(this._data.FMc);
		var bh = this._data.FBh;
		var uid = this._data.FUid;
		var imageUrl=this._data.FImagePath1;
		var dz =  decodeURIComponent(this._data.FDz);
		var str = "";
  
		str = str + "<table cellpadding=\"0\" cellspacing=\"0\"    style=\"font-size: 12px;\" id=\"bqnr\"  style=\" background-color:#ececec;width:400px;height:285px\">";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\"> ";
			str = str + "<span style='font-family: Arial, Helvetica, sans-serif; color: #008dd7; font-size: 14px;'>"+name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#' onclick='window.open(\"/Gis/cad!show_fw_cad?fw_id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>CAD图</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#'  onclick='window.open(\"/Gis/serach!serach_fw_xx?id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>详情</span></a>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\">";
			str = str + "<table cellpadding=0 cellspacing=0  style=\"width:99%;line-height:18px;font-size: 12px;\" >";
			str = str + "<tr ><td>" + bh + " " + name + dz + "&nbsp;&nbsp; <a  style=\"display:none\" href=\"serach!serach_fw_xx?id=" + uid + "\" target=\"_blank\">\u67e5\u770b\u8be6\u7ec6\u4fe1\u606f</a></td></tr>";
			if(imageUrl=="" || imageUrl==null || imageUrl=="null"  ){
				str = str + "<tr ><td  valign=\"top\"  style=\"font-color:red;width: 420;height: 300\">暂无图片</td></tr>";
			}else{
				str = str + "<tr ><td><img style=\"width: 420;height: 300\" src=\"" + imageUrl + "\"></img></td></tr>";
			}
			str = str + "</table>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "</table>";
		 
		
		map.SInfoWindow.SetInnerHTML(str);
		map.SInfoWindow.Show();
	}
}
function SearchControl() {
	var _s = GetWebParamValue("s");
	if (_s == "1") {
		oPnlRight.style.display = "inline";
	} else {
	}
}
function show_fw(xy,name,bh, wybh, dz, imageUrl, uid) {
if (imageUrl == null || imageUrl == "" || imageUrl == "null") {
			imageUrl = "images/fwtp.jpg";
		}
	var ppS = SMath.GetPointSFromString(xy);
	var xL = new SLinearRing(ppS);
	var pf = new SFeature(xL, _ftStyle);
	vectorLayer.AddFeatures([pf]);
	pf.Activate();
	var str = "";
			str = str + "<table cellpadding=\"0\" cellspacing=\"0\"    style=\"font-size: 12px;\" id=\"bqnr\"  style=\" background-color:#ececec;width:400px;height:285px\">";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\"> ";
			str = str + "<span style='font-family: Arial, Helvetica, sans-serif; color: #008dd7; font-size: 14px;'>"+name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#' onclick='window.open(\"/Gis/cad!show_fw_cad?fw_id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>CAD图</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#'  onclick='window.open(\"/Gis/serach!serach_fw_xx?id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>详情</span></a>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\">";
			str = str + "<table cellpadding=0 cellspacing=0  style=\"width:99%;line-height:18px;font-size: 12px;\" >";
			str = str + "<tr ><td>" + bh + " " + wybh + dz + "&nbsp;&nbsp; <a  style=\"display:none\" href=\"serach!serach_fw_xx?id=" + uid + "\" target=\"_blank\">\u67e5\u770b\u8be6\u7ec6\u4fe1\u606f</a></td></tr>";
			str = str + "<tr ><td><img style=\"width: 420;height: 300\" src=\"" + imageUrl + "\"></img></td></tr>";
			str = str + "</table>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "</table>";
			pf._text=str;
		pf.AddEventListener("click", pf, FeatureClick1);
}
function FeatureClick1(evt) {
		var g = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
		map.SInfoWindow.SetLonLat(new SLonLat(g.GetLon(), g.GetLat()), new SSize(0, -6));
	   // var ll = map.GetLonLatFromViewPortPx(new SPixel(evt.x, evt.y));
	  	 map.SInfoWindow.SetInnerHTML(this._text);
		map.SInfoWindow.Show();
			var tL = ConvertSizeToLonLat(new SSize(250,-80)); 
				map.SetCenter(new SLonLat(g.GetLon() + tL.GetLon(),g.GetLat() + tL.GetLat()));
		 	 
}


function ClearVS() {
	try{
		if (vectorLayer) {
			vectorLayer.DestroyFeatures();
		}
		if (markerL) {
			markerL.ClearMarkers();
		}
		map.ClearLabels();
		map.SInfoWindow.Hide();
	}catch(evcd){}
}
function ShowInfoWindow(xy,name,bh,wybh,dz,imageUrl,uid) {
 	 
  
	//name=decodeURIComponent(name);
	 
	 
	 
	if(xy==null || xy==""){
		return;
	}
	try {
		if (imageUrl == null || imageUrl == "" || imageUrl == "null") {
			//imageUrl = "images/fwtp.jpg";
		}
		var ppS = SMath.GetPointSFromString(xy);
		if (ppS && ppS.length > 0) {
			map.SetCenter(null,5);
			map.SInfoWindow.SetLonLat(new SLonLat(ppS[0].GetX(), ppS[0].GetY()), new SSize(0, -6));
			var str = "";
			str = str + "<table cellpadding=\"0\" cellspacing=\"0\"    style=\"font-size: 12px;\" id=\"bqnr\"  style=\" background-color:#ececec;width:400px;height:285px\">";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\"> ";
			str = str + "<span style='font-family: Arial, Helvetica, sans-serif; color: #008dd7; font-size: 14px;'>"+name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#' onclick='window.open(\"/Gis/cad!show_fw_cad?fw_id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>CAD图</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
			str = str + "<a href='#'  onclick='window.open(\"/Gis/serach!serach_fw_xx?id="+uid+"\")'><span style='font-family: Arial, Helvetica, sans-serif; color: #ff9600; font-size: 12px;'>详情</span></a>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "<tr>";
			str = str + "<td  valign=\"top\" id=\"ptif\">";
			str = str + "<table cellpadding=0 cellspacing=0  style=\"width:99%;line-height:18px;font-size: 12px;\" >";
			str = str + "<tr ><td>" + bh + " " + name + dz + "&nbsp;&nbsp; <a  style=\"display:none\" href=\"serach!serach_fw_xx?id=" + uid + "\" target=\"_blank\">\u67e5\u770b\u8be6\u7ec6\u4fe1\u606f</a></td></tr>";
			if(imageUrl=="" || imageUrl==null || imageUrl=="null"  ){
				str = str + "<tr ><td  valign=\"top\"  style=\"font-color:red;width: 420;height: 300\">暂无图片</td></tr>";
			}else{
				str = str + "<tr ><td><img style=\"width: 420;height: 300\" src=\"" + imageUrl + "\"></img></td></tr>";
			}
			str = str + "</table>";
			str = str + "</td>";
			str = str + "</tr>";
			str = str + "</table>";
			vectorLayer.RemoveFeaturesByTag("hit");
			var xL = new SLinearRing(ppS);
			var pf = new SFeature(xL, featureStyle, "hit");
			vectorLayer.AddFeatures([pf]);
			pf.Activate();
			pf.AddEventListener("click", pf, FeatureClick1);
			
			
			map.SInfoWindow.SetInnerHTML(str);
			//map.SetCenter(new SLonLat(ppS[0].GetX(), ppS[0].GetY()));
				map.SInfoWindow.Show();
				var tL = ConvertSizeToLonLat(new SSize(250,-80)); 
				map.SetCenter(new SLonLat(ppS[0].GetX() + tL.GetLon(),ppS[0].GetY() + tL.GetLat()));
			
		
		}
	}
	catch (ec) {
	}
}
function ZSY() {
	try {
		var _w = document.body.clientWidth;
		var _h = document.body.clientHeight; 
		//oMap.style.height = _h;
		//oPnlRight.style.height = _h;  
		oPnlRight.style.width = 170;
		oPnlRight.style.height = _h;
		oMap.style.width = _w - 171;
	}
	catch (evs) {
	}
}
//AddEventListener("resize", ZSY, false, window);
function AddEventListener(name, func, useCapture, obj) {
	if (window.attachEvent) {
		obj.attachEvent("on" + name, func);
	} else {
		obj.addEventListener(name, func, useCapture);
	}
}
function GetWebParamValue(param) {
	var params = document.location.search;
	var sIndex = params.indexOf("?");
	if (sIndex != -1) {
		params = params.substring(1, params.length);
		if (params.indexOf("&") != -1) {
			params = params.split("&");
			for (var i = 0; i < params.length; i++) {
				if (params[i].indexOf("=") != -1) {
					name = params[i].split("=")[0];
					if (name.toLowerCase() == param.toLowerCase()) {
						return params[i].split("=")[1];
						break;
					}
				}
			}
		} else {
			if (params.indexOf("=") != -1) {
				name = params.split("=")[0];
				if (name.toLowerCase() == param.toLowerCase()) {
					return params.split("=")[1];
				}
			}
		}
	}
	return "";
}

