function TTuJing() { }
TTuJing.prototype.map = null;
TTuJing.prototype.vectorLayer = null;
TTuJing.prototype.layer = null;
TTuJing.prototype.markerL = null;
TTuJing.prototype.measure = null;
TTuJing.prototype.marker = null;
TTuJing.prototype.geocoder = null;
TTuJing.prototype.markers = new Map();

TTuJing.prototype.Init = function() {
	SUtil.SetImagesLocation("/Resource/Theme/StyleDefault/");
    var mapParamS = new SMapParamOption();
    mapParamS.Resolutions =[0.000133695117187497*2,0.000133695117187497, 0.0000668475585937487, 0.0000334237792968743, 0.0000167118896484372, 0.00000835594482421859,0.00000835594482421859/2];
    mapParamS.NumZoomLevels = 11;
    mapParamS.MaxExtent = new SBounds(120.2241140886411, 30.46069566628205, 120.7717292886409, 31.00831086628195); 
    this.map = new SMap('map', mapParamS);
    this.vectorLayer = new SVectorLayer("vLayer"); 
    this.layer = new STileLayer("乌镇地图", "http://60.12.184.19/wuzhen/tiles/", 'jpg');
    this.layer.SetTilePathRuleFun(this.CustomUrl);
    this.map.AddLayer(this.layer); 
    this.markerL = new SMarkerLayer("POI");
    this.map.AddLayer(this.markerL);

    this.layer.SetZIndex(120);

    this.map.AddLayer(this.vectorLayer);
    var zp = new SPanZoomBar();
    this.map.AddControl(zp);
    this.map.SetCursor("hand");
    //zp.GetDiv().style.float = "right";
    //zp.GetDiv().style.right = "110px";
    //zp.GetDiv().style.left = null;

    
    var meatureStyle = new SFeatureStyle();
    meatureStyle.SetFillColor("none");
    meatureStyle.SetHoverFillColor("none");
    meatureStyle.SetStrokeColor("green");
    meatureStyle.SetStrokeStartArrow("Oval");
    meatureStyle.SetStrokeEndArrow("Classic");
    meatureStyle.SetStrokeDashStyle("ShortDashDot");
    //测距
    this.meature = new SMeasure(this.vectorLayer, SHPath, meatureStyle);
    
    this.map.SetCenter(mapParamS.MaxExtent.GetCenterLonLat(), 0);
};

TTuJing.prototype.CustomUrl = function(pTile) {
	var z = this.GetMap().GetZoom()+1;
    var r = pTile.GetRow();
    var col = pTile.GetCol();
    var path = (z + 1) + "/" + r + "/" + col + "." + this.GetType();
    z = null;
    r = null;
    col = null;
    return this.GetPrefixPath() + path;
};

//放大
TTuJing.prototype.ZoomIn = function(){
	this.map.ZoomIn();
};
//缩小
TTuJing.prototype.ZoomOut = function(){
	this.map.ZoomOut();
};
//测距
TTuJing.prototype.OpenMeasure = function(){
	this.map.AddControl(this.meature);
	this.meature.Activate();
};
//漫游
TTuJing.prototype.Roam = function(){
	
};
//清除所有
TTuJing.prototype.ClearAll = function(){
	this.map.ClearAll();
	this.markerL.ClearMarkers(); 
	this.vectorLayer.DestroyFeatures();
};
//添加标注
TTuJing.prototype.AddMarker = function(obj,iconUrl,iconX,iconY,content,show,history) {
	if(this.map==null){
		this.init();
	}
	if(obj==null||obj.x==null||obj.x==""||obj.y==null||obj.y==""){
		return ;
	}
	var icon = new SIcon(iconUrl, new SSize(iconX,iconY),new SPixel(-10,-32)); 
	var marker = new SMarker(new SLonLat(obj.x,obj.y), icon,obj.name); 
    var ll = new SLonLat(obj.x,obj.y);
    marker.__ll = ll; 
    marker.__text = content;
    if(!history){
		if(this.markers == null){
			this.markers = new Map();
		}
		this.RemoveMarker(obj.name);
		this.markers.put(obj.name,marker);
	}
    this.markerL.AddMarker(marker); 
    this.map.SInfoWindow.SetOffset(new SSize(0,-10));
 
 	if(show){
 		this.map.SInfoWindow.SetLonLat(ll);
 		this.map.SInfoWindow.SetInnerHTML(marker.__text);
 		this.map.SInfoWindow.Show(); 
 	} 
 	 
 	marker.AddEventListener("click", marker, function() {
 		TJSoft.map.config.map.map.SInfoWindow.SetLonLat(this.__ll);
 		TJSoft.map.config.map.map.SInfoWindow.SetOffset(new SSize(0,-32));
 		TJSoft.map.config.map.map.SInfoWindow.SetInnerHTML(marker.__text);
 		TJSoft.map.config.map.map.SInfoWindow.SetSize(new SSize(460,338));
 		TJSoft.map.config.map.map.SInfoWindow.Show();
// 		document.getElementById("countIframe").style.width = "400px";
// 		alert(document.getElementById("countIframe").style.width);
		//var tL = ConvertSizeToLonLat(new SSize(250,-100));
	 }); 
};
//删除特定图层
TTuJing.prototype.RemoveOverlay = function(name){
	this.map.removeOverlay(name);
};

TTuJing.prototype.RemoveMarker = function (name){
	var marker = this.markers.get(name);
	this.markers.remove(name);
	try{this.markerL.ClearMarkersByTag(name);}catch(eevd){}
};
//添加特定标注
TTuJing.prototype.AddOneMarker = function(obj,iconUrl,iconX,iconY,content){
	if(this.map==null){
		this.init();
	}
	if(obj==null||obj.glon==null||obj.glon==""||obj.glat==null||obj.glat==""){
		return ;
	}
	try{
		this.markerL.RemoveMarker(this.marker);
	}catch(e){
	}
	var icon = new SIcon(iconUrl, new SSize(iconX,iconY),new SPixel(-12,-12)); 
	this.marker = new SMarker(new SLonLat(obj.glon,obj.glat), icon,obj.carLicenseNum); 
    var ll = new SLonLat(obj.glon,obj.glat);
    this.marker.__ll = ll; 
    this.marker.__text = content;
    this.markerL.AddMarker(this.marker);
    obj=null,content=null;
};

//画线
TTuJing.prototype.DrawLine = function(arr){
	/*var str = "120.0505323813228 30.31870118844156,120.04964081118127 30.323159039149093,120.04976817834435 30.327107421204342,120.05065974848585 30.331437904748806,120.05155131862737 30.33347577935797,120.05549970068261 30.341627277794607,120.0574102081287 30.34684933148058,120.05855651259635 30.35181665084041,120.05919334841172 30.35500082991722,120.06174069167316 30.358694477646324,120.06645327670685 30.36302496119079,120.07129322890361 30.36595440594146,120.07715211840494 30.370030155159778,120.08046366464482 30.37359643572581";
	var pointC2ll = [];
    var sT1 = str.split(',');
    for (var u = 0; u < sT1.length; u++) {
        var sT2 = sT1[u];
        var xx = sT2.split(' ');
        pointC2ll.push(new SPoint(xx[0], xx[1]));
    }
	var geo = new SLineString(pointC2ll);
    var featureStyle = new SFeatureStyle();
    featureStyle.SetFillColor("none");
    featureStyle.SetHoverFillColor("none");
    featureStyle.SetStrokeColor("blue");
    featureStyle.SetStrokeWidth(3.2); 
    featureStyle.SetHoverStrokeColor("red");
    featureStyle.SetHoverStrokeWidth(4.2); 
    featureStyle.SetStrokeOpacity(0.4);
    featureStyle.SetHoverStrokeOpacity(0.5);
    var pf = new SFeature(geo, featureStyle);
    this.vectorLayer.AddFeatures([pf]);
	*/
	
	var points = [];
	for(var i=0;i<arr.length;i++){
		points.push(new SPoint(arr[i].split(" ")[0],arr[i].split(" ")[1]));
	}
	var geo = new SLineString(points);
    var featureStyle = new SFeatureStyle();
    featureStyle.SetFillColor("none");
    featureStyle.SetHoverFillColor("none");
    featureStyle.SetStrokeColor("blue");
    featureStyle.SetStrokeWidth(2); 
    featureStyle.SetHoverStrokeColor("red");
    featureStyle.SetHoverStrokeWidth(3); 
    featureStyle.SetStrokeOpacity(0.5);
    featureStyle.SetHoverStrokeOpacity(0.8);
    var pf = new SFeature(geo, featureStyle);
    this.vectorLayer.AddFeatures([pf]);
};

//最佳视野
TTuJing.prototype.SetViewport = function(points){
	 
	if(points==null){
		return;
	}
	var arr = [];
	for(var i=0;i<points.length;i++){
		if(points[i]==null||points[i].glon==null||points[i].glon==""||points[i].glat==null||points[i].glat==""){
			continue;
		}
		arr.push(new SLonLat(points[i].glon,points[i].glat));
	} 
	var bnd = this.GetMaxExtent(arr);
 
    //this.map.ZoomToExtent(this.GetMaxExtent(arr));
};

TTuJing.prototype.GetMaxExtent = function(lonlatS){
    var rtnBounds=null;
    if(lonlatS!=null && lonlatS instanceof Array && lonlatS.length>0){
        var left = lonlatS[0].GetLon();
        var bottom = lonlatS[0].GetLat();
        var right = lonlatS[0].GetLon();
        var top = lonlatS[0].GetLat();
        for(var m=1;m<lonlatS.length;m++){
            var x = lonlatS[m].GetLon();
            var y = lonlatS[m].GetLat();
            if(x<left){
                left = x;
            }else if(x>right){
                right = x;
            }
            if(y<bottom){
                bottom = y;
            }else if(y>top){
                top = y;
            }
        }
        rtnBounds = new SBounds(left,bottom,right,top);
    }
    return rtnBounds;
};


