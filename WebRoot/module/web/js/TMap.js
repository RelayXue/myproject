var map = null;
var vectorLayer,vectorLayer2;
var layer1, layer2, layer3, layer4, layer5;
var zoomInRect=null;
var drawUt = null,drawUt1=null;	//画图类
var featureStyle = null, featureStyle2 = null, meatureStyle = null,measure = null,drawRect=null,drawRect2=null,drawRect3=null,drawRect4=null;
var meature=null;
var beginPoint;
var endPoint;
var townRange = null;
var markerL;
var xmapRK;
var currZoom=0;

function init(isRk){
	SUtil.SetImagesLocation("/Resource/Theme/StyleDefault/");
    var mapParamS = new SMapParamOption();
    //mapParamS.Resolutions =[0.000133695117187497*2,0.000133695117187497, 0.0000668475585937487, 0.0000334237792968743, 0.0000167118896484372, 0.00000835594482421859,0.00000835594482421859/2];
    mapParamS.Resolutions =[0.0000668475585937487, 0.0000167118896484372,  0.00000835594482421859/2];//0.000133695117187497*2,  
    mapParamS.NumZoomLevels = 11;
    mapParamS.MaxExtent = new SBounds(120.2241140886411, 30.46069566628205, 120.7717292886409, 31.00831086628195); 
    map = new SMap('map', mapParamS);
    vectorLayer = new SVectorLayer("vLayer"); 
    layer11 = new STileLayer("乌镇地图", "http://60.12.184.19/wuzhen/tiles/", 'jpg');
    layer11.SetTilePathRuleFun(CustomUrl2);
    map.AddLayer(layer11);
    
    markerL = new SMarkerLayer("POI");
    map.AddLayer(markerL);

    layer11.SetZIndex(120);

    map.AddLayer(vectorLayer);
    //var zp = new SPanZoomBar();
    //map.AddControl(zp);
    map.SetCursor("pointer");
    //zp.GetDiv().style.float = "right";
    //zp.GetDiv().style.right = "110px";
    //zp.GetDiv().style.left = null;

    
    var meatureStyle = new SFeatureStyle();
    meatureStyle.SetFillColor("none");
    meatureStyle.SetHoverFillColor("none");
    meatureStyle.SetStrokeColor("red");
    //meatureStyle.SetStrokeStartArrow("Oval");
    //meatureStyle.SetStrokeEndArrow("Classic");
    meatureStyle.SetStrokeDashStyle("ShortDashDot");
    //测距
    meature = new SMeasure(vectorLayer, SHPath, meatureStyle);
    
    map.SetCenter(new SLonLat(120.484154,30.742853), 1); 
    
    map.AddEventListener("zoomend",function(){
    	var zoom =  map.GetZoom();
    	if(!map_flag){
    		if(zoom>currZoom){
        		runZoomBarBtn2(0);
        	}else{
        		runZoomBarBtn2(1);
        	}
    	}
    	map_flag = false;
    	currZoom = zoom;
    });
    if(isRk){
    	xmapRK = document.getElementById("mapRK");

        map.AddEventListener("mouseup", MapClickListener);
    }
}

function MapClickListener(evt) { 
    if ((evt.which && evt.which == 1) || (evt.button & evt.button == 1)) {
        xmapRK.style.display = "none";
    } else {
    	var ll = map.GetLonLatFromViewPortPx(new SPixel(evt.RelativeXY.GetX(), evt.RelativeXY.GetY()));
        $("#rkXyTxt").val(ll.GetLon()+','+ll.GetLat());
        xmapRK.style.left = evt.RelativeXY.GetX()+"px";
        xmapRK.style.top = evt.RelativeXY.GetY()+"px";
        xmapRK.style.display = "inline";
    }
}

function CustomUrl2(pTile) {
	var z = (this.GetMap().GetZoom() + 2)*2;
    var r = pTile.GetRow();
    var col = pTile.GetCol();
    var path = (z) + "/" + r + "/" + col + "." + this.GetType();
    z = null;
    r = null;
    col = null;
    return this.GetPrefixPath() + path;
};

//放大
function zoomIn(){
	map.ZoomIn();
}
//缩小
function zoomOut(){
	map.ZoomOut();
}
//测距
function openMeasure(){
    map.AddControl(meature);
    meature.Activate();
}

//清除所有
function clearAll(){
	map.ClearAll();
	try{
	   map.ReleaseEventListener("mouseup", mapClick);
	}catch(evdd){}
	markerL.ClearMarkers(); 
    vectorLayer.DestroyFeatures();
}

//最佳视野
function zoomToExtent(xy){
	var ppS1 = SPoint.GetPointSFromString(xy);
	map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
}

//关闭窗口
function closeWindows(){
	map.SInfoWindow.Hide();
}

function addMarker(obj,show,content){
	if(obj==null||obj.x==null||obj.x==""||obj.y==null||obj.y==""){
		return ;
	}
	if(map==null){
		init();
	}
	if(content==null||content.length==0){
		content = "<iframe id='countIframe' frameborder='0' style='height: 307px;z-index:4000; width: 428px; font-size: 12px; line-height: 20px;' src='search_viewPointDetail?id="+obj.id+"&type="+obj.type+"'></iframe>";
	}
	var img = "/images/dataicon.png";
	var icon = new SIcon(img, new SSize("22","37"),new SPixel(-11,-37));
	icon.GetDiv().innerHTML = '<span style=\"text-align:center;color:#fff;line-height:22px;display:block;font-size:13px;width:22px;\">'+obj.index+'</span>';
	var marker = new SMarker(new SLonLat(obj.x,obj.y), icon,obj.name); 
    var ll = new SLonLat(obj.x,obj.y);
    marker.__ll = ll; 
    marker.__text = content;
    markerL.AddMarker(marker); 
    map.SInfoWindow.SetOffset(new SSize(0,-10));
 	if(show){
 		closeAllTab();
 		map.SetCenter(new SLonLat(obj.x+0.5,obj.y+0.5));
		map.SInfoWindow.SetLonLat(ll);
 		map.SInfoWindow.SetOffset(new SSize(0,-32));
 		map.SInfoWindow.SetInnerHTML(content);
 		if(obj.width!=undefined&&obj.height!=undefined){
 			map.SInfoWindow.SetSize(new SSize(obj.width,obj.height));
 		}else{
 			map.SInfoWindow.SetSize(new SSize(460,338));
 		}
 		map.SInfoWindow.Show();
 	} 
 	 
 	marker.AddEventListener("click", marker, function() {
 		closeAllTab();
 		map.SInfoWindow.SetLonLat(this.__ll);
 		map.SInfoWindow.SetOffset(new SSize(0,-32));
 		map.SInfoWindow.SetInnerHTML(marker.__text);
 		if(obj.width!=undefined&&obj.height!=undefined){
 			map.SInfoWindow.SetSize(new SSize(obj.width,obj.height));
 		}else{
 			map.SInfoWindow.SetSize(new SSize(460,338));
 		}
 		map.SInfoWindow.Show();
	 }); 
}

function addMarker2(obj,show,content){
	if(obj==null||obj.x==null||obj.x==""||obj.y==null||obj.y==""){
		return ;
	}
	if(map==null){
		init();
	}
	
	var img = "/images/dataicon.png";
	var icon = new SIcon(img, new SSize("22","37"),new SPixel(-11,-37));
	icon.GetDiv().innerHTML = '<span style=\"text-align:center;color:#fff;line-height:22px;display:block;font-size:13px;width:22px;\">'+obj.index+'</span>';
	var marker = new SMarker(new SLonLat(obj.x,obj.y), icon,obj.name); 
    var ll = new SLonLat(obj.x,obj.y);
    marker.__ll = ll; 
    marker.__text = content;
    markerL.AddMarker(marker); 
    map.SInfoWindow.SetOffset(new SSize(0,-37));
 	if(show){
 		closeAllTab();
 		$.ajax({
			  type: 'POST',
			  async:false,
			  url: "web/search_viewPointDetail",
			  data: 'id='+obj.id+'&type='+obj.type,
			  dataType: 'html',
			  success: function(data){
				  map.SetCenter(new SLonLat(obj.x+0.5,obj.y+0.5));
					map.SInfoWindow.SetLonLat(ll);
			 		map.SInfoWindow.SetOffset(new SSize(-6,-35));
			 		map.SInfoWindow.SetInnerHTML(data);
			 		if(obj.width!=undefined&&obj.height!=undefined){
			 			map.SInfoWindow.SetSize(new SSize(obj.width,obj.height));
			 		}else{
			 			map.SInfoWindow.SetSize(new SSize(437,317));
			 		}
			 		map.SInfoWindow.Show();
			 		$("#startText").autocomplete("web/search_autoCompleteData", {
						minChars: 0,
						max: 10,
						autoFill: false,
						mustMatch: false,
						matchContains: true,
						selectFirst:true,
						scrollHeight: 220,
							formatItem: function(row, i,max) {
								$("#startHiddTxt").val("");
								var obj =eval("(" + row + ")"); //转换成js对象 
								return obj.name;
								}, 
								formatResult: function(row) { 
								var obj =eval("(" + row + ")"); //转换成js对象 
								return obj.name;
								},
								formatMatch:function(row){
									var obj =eval("(" + row + ")"); //转换成js对象
									return obj.name;
								}
					});

					$("#startText").result(function(event, data, formatted) {
						var obj =eval("(" + data + ")");
						$("#startHiddTxt").val(obj.x+","+obj.y);
					});
					
					$("#endText").autocomplete("web/search_autoCompleteData", {
						minChars: 0,
						max: 10,
						autoFill: false,
						mustMatch: false,
						matchContains: true,
						selectFirst:true,
						scrollHeight: 220,
							formatItem: function(row, i,max) { 
								$("#endHiddTxt").val("");
								var obj =eval("(" + row + ")"); //转换成js对象 
								if(obj.name.length>0){
									return obj.name;
								}
								}, 
								formatResult: function(row) { 
								var obj =eval("(" + row + ")"); //转换成js对象 
								if(obj.name.length>0){
									return obj.name;
								}
								},
								formatMatch:function(row){
									var obj =eval("(" + row + ")"); //转换成js对象 
									if(obj.name.length>0){
										return obj.name;
									}
								}
					});

					$("#endText").result(function(event, data, formatted) {
						var obj =eval("(" + data + ")");
						$("#endHiddTxt").val(obj.x+","+obj.y);
					});
			  }
		});
 	} 
 	 
 	marker.AddEventListener("click", marker, function() {
 		closeAllTab();
 		map.SInfoWindow.SetLonLat(this.__ll);
 		$.ajax({
			  type: 'POST',
			  async:false,
			  url: "web/search_viewPointDetail",
			  data: 'id='+obj.id+'&type='+obj.type,
			  dataType: 'html',
			  success: function(data){
			 		map.SInfoWindow.SetOffset(new SSize(-6,-35));
			 		map.SInfoWindow.SetInnerHTML(data);
			 		if(obj.width!=undefined&&obj.height!=undefined){
			 			map.SInfoWindow.SetSize(new SSize(obj.width,obj.height));
			 		}else{
			 			map.SInfoWindow.SetSize(new SSize(437,317));
			 		}
			 		map.SInfoWindow.Show();
			 		$("#startText").autocomplete("web/search_autoCompleteData", {
						minChars: 0,
						max: 10,
						autoFill: false,
						mustMatch: false,
						matchContains: true,
						selectFirst:true,
						scrollHeight: 220,
							formatItem: function(row, i,max) {
								$("#startHiddTxt").val("");
								var obj =eval("(" + row + ")"); //转换成js对象 
								return obj.name;
								}, 
								formatResult: function(row) { 
								var obj =eval("(" + row + ")"); //转换成js对象 
								return obj.name;
								},
								formatMatch:function(row){
									var obj =eval("(" + row + ")"); //转换成js对象
									return obj.name;
								}
					});

					$("#startText").result(function(event, data, formatted) {
						var obj =eval("(" + data + ")");
						$("#startHiddTxt").val(obj.x+","+obj.y);
					});
					
					$("#endText").autocomplete("web/search_autoCompleteData", {
						minChars: 0,
						max: 10,
						autoFill: false,
						mustMatch: false,
						matchContains: true,
						selectFirst:true,
						scrollHeight: 220,
							formatItem: function(row, i,max) { 
								$("#endHiddTxt").val("");
								var obj =eval("(" + row + ")"); //转换成js对象 
								if(obj.name.length>0){
									return obj.name;
								}
								}, 
								formatResult: function(row) { 
								var obj =eval("(" + row + ")"); //转换成js对象 
								if(obj.name.length>0){
									return obj.name;
								}
								},
								formatMatch:function(row){
									var obj =eval("(" + row + ")"); //转换成js对象 
									if(obj.name.length>0){
										return obj.name;
									}
								}
					});

					$("#endText").result(function(event, data, formatted) {
						var obj =eval("(" + data + ")");
						$("#endHiddTxt").val(obj.x+","+obj.y);
					});
			  }
		});
	 }); 
}

function addMarkerOnly(obj){
	if(obj==null||obj.x==null||obj.x==""||obj.y==null||obj.y==""){
		return ;
	}
	if(map==null){
		init();
	}
	var img = "/images/dataicon.png";
	var icon = new SIcon(img, new SSize("22","37"),new SPixel(-11,-37));
	var marker = new SMarker(new SLonLat(obj.x,obj.y), icon,obj.name); 
    var ll = new SLonLat(obj.x,obj.y);
    marker.__ll = ll; 
    markerL.AddMarker(marker); 
    map.SInfoWindow.SetOffset(new SSize(0,-10));
    map.SetCenter(new SLonLat(obj.x,obj.y),2);
}

function drowLine3(range) {
    if(range==null||range==""||range.length<0){
    	return;
    } 
    window.parent.vectorLayer.DestroyFeatures();
    //vectorLayer2.DestroyFeatures();
   
//	var ppS1 = SPoint.GetPointSFromString(range);
   
	var ppS1=SPoint.GetPointSFromString(range);
    var xL = new SLineString(ppS1);
 
    featureStyle = new SFeatureStyle();
 
    featureStyle.SetFillColor("none");
    featureStyle.SetHoverFillColor("none");
    featureStyle.SetHoverStrokeColor("none");
    featureStyle.SetStrokeColor("#0115b0");
    featureStyle.SetStrokeStartArrow("Oval");
    featureStyle.SetStrokeEndArrow("Classic");
    featureStyle.SetStrokeDashStyle("SOLID");
    featureStyle.SetStrokeArrowLength(5);
    featureStyle.SetStrokeArrowWidth(5);
    featureStyle.SetStrokeWidth(5);
    featureStyle.SetHoverEnabled(true);
    var pf = new SFeature(xL, featureStyle); 
    vectorLayer.AddFeatures([pf]);	//加入图层
}

function showTrafficLine(show){
	//if(show){
		//清除所有
		//clearAll();
		//is_road = false;
	//	return;
	//}
	is_road = true;
	$.ajax({
        type:"get",
        url:"web/search_showTrafficLine",
        dataType:"json",
        success:function(data){
        	if(data==null){
        		return;
        	}else{
        		//清除所有
        		clearAll();
        		var xy=[];
        		for(var i=0;i<data.length;i++){
        			var color = '';
        			if(data[i].state=='严重拥堵'){
        				color = '#ff0000';
        			}else if(data[i].state=='拥堵'){
        				color = '#ff0000';
        			}else if(data[i].state=='缓慢'){
        				color = '#ffe13a';
        			}else if(data[i].state=='畅通'){
        				color = '#00ff00';
        			} 
        			drowLine2(data[i].xy,color);
        			xy.push(data[i].xy);
        		}
        		if(xy.length!=0){
        			var ppS1 = SPoint.GetPointSFromString(xy.join(","));
        	    	map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
        		}
        	}
        },
        error:function(data){
     	   alert("运行出错！");
        }
     }); 
}

//画线(xy坐标串,颜色)
function drowLine2(range, color) {
    if(range==null||range==""||range.length<0){
    	return;
    }
	var ppS1=SPoint.GetPointSFromString(range);
    var xL = new SLineString(ppS1);
 
    featureStyle = new SFeatureStyle();
 
    featureStyle.SetFillColor("none");
    featureStyle.SetHoverFillColor("none"); 
    featureStyle.SetStrokeColor(color);
    featureStyle.SetHoverStrokeColor(color);
    featureStyle.SetStrokeStartArrow("Oval");
    featureStyle.SetStrokeEndArrow("Classic");
    featureStyle.SetStrokeDashStyle("SOLID");
    featureStyle.SetStrokeArrowLength(5);
    featureStyle.SetStrokeArrowWidth(5); 
    featureStyle.SetStrokeWidth(6);
    featureStyle.SetHoverEnabled(true);
    var pf = new SFeature(xL, featureStyle); 
    vectorLayer.AddFeatures([pf]);	//加入图层
}

function searchCarLine(){
	var start = $("#startHiddTxt").val();
	var end = $("#endHiddTxt").val();
	var startTxt = $("#startText").val();
	var endTxt = $("#endText").val();
	if(startTxt.length==0||endTxt.length==0){
		window.parent.showlayer('请输入起点或终点！', 1,8);
		return;
	}
	$.ajax({
        type:"get",
        url:"web/search_carLineData?xy="+start+"|"+end+"&q="+encodeURIComponent(encodeURIComponent(startTxt))+"|"+encodeURIComponent(encodeURIComponent(endTxt)),
        dataType:"json",
        success:function(data){
        	//清除所有
    		window.parent.clearAll();
    		var display = $("#resultText").css("display");
    	    if(display=="none"){
    	    	$("#resultText").css("display","block");
    	    }
        	if(data.xy==null||data.xy==""){
        		$("#start").html($("#startText").val());
        	    $("#end").html($("#endText").val());
        	    $("#total_length").html(0);
        	    $("#total_time").html(0);
        	    $("#roadText").html("");
        		$("#roadText").append("<div class=\"f_14\">未找到相关路径！</div>");
        		window.parent.showlayer('未找到相关路径！', 1,8);
        		return;
        	}else{
        		//清除所有
        		window.parent.clearAll();
        		var icon,marker;
        		icon = new SIcon('/module/web/images/begin.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    
           	    window.parent.markerL.ClearMarkersByTag("begin");
           	    maker = new SMarker(new SLonLat(data.start.split(",")[0],data.start.split(",")[1]), icon,"begin");
           	    window.parent.markerL.AddMarker(maker);
           	    icon = new SIcon('/module/web/images/end.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    window.parent.markerL.ClearMarkersByTag("end");
        	    maker = new SMarker(new SLonLat(data.end.split(",")[0],data.end.split(",")[1]), icon,"end");
        	    window.parent.markerL.AddMarker(maker);
        	    $("#start").html($("#startText").val());
        	    $("#end").html($("#endText").val());
        	    $("#total_length").html(data.length);
        	    $("#total_time").html(data.time);
        	    if(data.roadText.length>0&&data.roadXy.length){
        	    	$("#roadText").html("");
        	    	for(var i=0;i<data.roadText.length;i++){
        	    		$("#roadText").append("<div class=\"f_14\">("+(i+1)+")、"+data.roadText[i]+"</div>");
	        	    }
        	    	
        	    	var ppS1 = SPoint.GetPointSFromString(data.xy);
        	    	window.parent.map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
        	    	
        	    	window.parent.drowLine3(data.xy);
        	    }else{
        	    	$("#roadText").append("<div class=\"f_14\">未找到相关路径！</div>");
        	    }
        	    
        	  
        	}
        },
        error:function(data){
        	window.parent.showlayer('未找到相关路径！', 1,8);
        }
     }); 
}

function searchWalkLine(){
	var start = $("#startHiddTxt").val();
	var end = $("#endHiddTxt").val();
	var startTxt = $("#startText").val();
	var endTxt = $("#endText").val();
	if(startTxt.length==0||endTxt.length==0){
		window.parent.showlayer('请输入起点或终点！', 1,8);
		return;
	}
	$.ajax({
        type:"get",
        url:"web/search_walkLineData?xy="+start+"|"+end+"&q="+encodeURIComponent(encodeURIComponent(startTxt))+"|"+encodeURIComponent(encodeURIComponent(endTxt)),
        dataType:"json",
        success:function(data){
        	//清除所有
    		window.parent.clearAll();
    		var display = $("#resultText").css("display");
    	    if(display=="none"){
    	    	$("#resultText").css("display","block");
    	    }
        	if(data.xy==null||data.xy==""){
        		$("#start").html($("#startText").val());
        	    $("#end").html($("#endText").val());
        	    $("#total_length").html(0);
        	    $("#total_time").html(0);
        	    $("#roadText").html("");
        		$("#roadText").append("<div class=\"f_14\">未找到相关路径！</div>");
        		window.parent.showlayer('未找到相关路径！', 1,8);
        		return;
        	}else{
        		//清除所有
        		window.parent.clearAll();
        		var icon,marker;
        		icon = new SIcon('/module/web/images/begin.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    
        		window.parent.markerL.ClearMarkersByTag("begin");
           	    maker = new SMarker(new SLonLat(data.start.split(",")[0],data.start.split(",")[1]), icon,"begin");
           	    window.parent.markerL.AddMarker(maker);
           	    icon = new SIcon('/module/web/images/end.png', new SSize(32, 32),new SPixel(-16,-32)); 
           	    window.parent.markerL.ClearMarkersByTag("end");
        	    maker = new SMarker(new SLonLat(data.end.split(",")[0],data.end.split(",")[1]), icon,"end");
        	    window.parent.markerL.AddMarker(maker);
        	    $("#start").html($("#startText").val());
        	    $("#end").html($("#endText").val());
        	    $("#total_length").html(data.length);
        	    $("#total_time").html(data.time);
        	    if(data.roadText.length>0&&data.roadXy.length){
        	    	$("#roadText").html("");
        	    	for(var i=0;i<data.roadText.length;i++){
        	    		$("#roadText").append("<div class=\"f_14\">("+(i+1)+")、"+data.roadText[i]+"</div>");
	        	    }
        	    	
        	    	var ppS1 = SPoint.GetPointSFromString(data.xy);
        	    	window.parent.map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
        	    	
        	    	window.parent.drowLine3(data.xy);
        	    }else{
        	    	$("#roadText").append("<div class=\"f_14\">未找到相关路径！</div>");
        	    }
        	    
        	  
        	}
        },
        error:function(data){
        	window.parent.showlayer('未找到相关路径！', 1,8);
        }
     }); 
}

function rkSearchLine(type){
	var start = $("#rkStartTxt").val();
	var end = $("#rkEndTxt").val();
	var icon,marker;
	if(type==1){
		//设置起点
		//window.parent.clearAll();
		if(end.length==0){
			window.parent.clearMapAndTag();
		}
		icon = new SIcon('/module/web/images/begin.png', new SSize(32, 32),new SPixel(-16,-32)); 
   	    markerL.ClearMarkersByTag("begin");
   	    maker = new SMarker(new SLonLat(start.split(",")[0],start.split(",")[1]), icon,"begin");
   	    markerL.AddMarker(maker);
	}else{
		//设置终点
		if(start.length==0){
			window.parent.clearMapAndTag();
		}
		icon = new SIcon('/module/web/images/end.png', new SSize(32, 32),new SPixel(-16,-32)); 
   	    markerL.ClearMarkersByTag("end");
	    maker = new SMarker(new SLonLat(end.split(",")[0],end.split(",")[1]), icon,"end");
	    markerL.AddMarker(maker);
	}
	if(start.length!=0&&end.length!=0){
		if(is_full){
			fullView();
		}
		_left_menu_icos.children("#_ipage").hide();
		_left_menu_icos.removeClass("clicked");
		$("._top_menu ._left ._ico3").children("#_ipage").show();	
		$("._top_menu ._left ._ico3").addClass("clicked");
		is_open = true;
		
		
		main10.window.clearHtmlTxt();
		$.ajax({
	        type:"get",
	        url:"web/search_carLineData?xy="+start+"|"+end,
	        dataType:"json",
	        success:function(data){
	        	 
	        	if(data.xy==null||data.xy==""){
	        		alert("未找到相关路径！");
	        		return;
	        	}else{
	        		//清除所有
	        		main10.window.setText("起点","终点",data.length,data.time);
	        	    var display = main10.window.getResultDisplay();
	        	    if(display=="none"){
	        	    	main10.window.setResultDisplay();
	        	    }
	        	    if(data.roadText.length>0&&data.roadXy.length){
	        	    	main10.window.setResultText("");
	        	    	var str = '';
	        	    	for(var i=0;i<data.roadText.length;i++){
	        	    		str += "<div class=\"f_14\">("+(i+1)+")、"+data.roadText[i]+"</div>";
		        	    }
	        	    	main10.window.setResultText(str);
	        	    	var ppS1 = SPoint.GetPointSFromString(data.xy);
	        	    	map.ZoomToExtent(SUtil.GetMaxBounds(GetLonLatS(ppS1)));
	        	    	drowLine3(data.xy);
	        	    	str = null;
	        	    }else{
	        	    	main10.window.setResultText("<div class=\"f_14\">未找到相关路径！</div>");
	        	    }
	        	}
	        },
	        error:function(data){
	     	   alert("未找到相关路径！");
	        }
	     });
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




