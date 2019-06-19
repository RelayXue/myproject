var c=0;
var _data_zb="";
var _data_time="";
var _index=0;


var _dt = "";
var ppS1 = null;      // 轨迹坐标
var zb_yx="";
var idx = 0;
var cnt =0;					
var cnt1 =0;	     					 // 加点坐标长度			
var mm3 = null;
var flag = true;
var ele ;
var speed1="";								//轨迹播放速度
//播放
function GpsPlay(data,data_yx,speed){	 
	//$('body').stopTime();
	ClearVS();
	removeRy();
	GpsInit(data,data_yx); 
	SetCurrentIdx(0); 
	GpsStart(data,data_yx,speed);
}
//初始画轨迹
function showLine(data,data_yx){
	ClearVS();
	if(data=="nodata"){
		alert("暂无轨迹数据");
		return;
	}
	if(data!=null&&data.length>0){
		ppS1 = GetPointSFromString(data);
	    ppS2 = data_yx.split(";");
		cnt = ppS1.length;
		cnt1=ppS2.length;
	}
	 var data1=data_yx.split(";");	
	 var c=0;
	 InitDrawLine();
	for(var a=0;a<cnt;a++){
		for(var b=0;b<data1.length;b++){
			 var data2=data1[b].split(",");
			 if(data2[2]==ppS1[a].GetX()&&data2[3]==ppS1[a].GetY()){
				 dwgj(data1[b],c);
				 c++;	
			 }
		}
	}
}

function InitDrawLine() {
	var  mStyle2 = new SFeatureStyle();
    mStyle2.SetFillColor("none");
   // meatureStyle.SetHoverFillColor("red");
    mStyle2.SetStrokeColor("#5ECB46");
    mStyle2.SetStrokeStartArrow("Oval");
    mStyle2.SetStrokeEndArrow("Classic");
    mStyle2.SetStrokeDashStyle("Solid"); 
    mStyle2.SetHoverEnabled(false);
    mStyle2.SetStrokeWidth(3);
    vectorLayer.DestroyFeatures();
    var rtn = [];
    for (var u = 0; u <= cnt; u++) {
        rtn.push(ppS1[u]);
    }
    var xL = new SLineString(rtn);
    var pf = new SFeature(xL, mStyle2);
    vectorLayer.AddFeatures([pf]);
}


// 轨迹定位
function dwgj(data,xh) {
	// 解析
	var aa=data.split(",");
	var zb_x=aa[2];
	var zb_y=aa[3];
	var ll = new SLonLat(zb_x, zb_y);
	var inHtml="<div style='background: url(Images/point.png);width:26px;height:27px;background-position: "+(-xh*26.2)+" 0;'></div>";
	var lbl2 = new SLabel(null, ll, null, inHtml);
	lbl2.SetOffset(new SSize(-9, -23));
	lbl2.SetAdaptive();
	lbl2.__text = getDiv(data);
	map.AddLabel(lbl2);
	lbl2.AddEventListener("click", map, function() {
		map.SInfoWindow.SetLonLat(ll);
		map.SInfoWindow.SetInnerHTML(lbl2.__text);
		map.SInfoWindow.Show();
	});
	
	
	
/*	maker2.AddEventListener("click", maker2, function() {
		map.SInfoWindow.SetLonLat(this.__ll);
		map.SInfoWindow.SetInnerHTML(maker2.__text);
		map.SInfoWindow.Show();
		// var tL = ConvertSizeToLonLat(new SSize(250,-100));
		// map.SetCenter(new SLonLat(this.__ll.GetLon() +
		// tL.GetLon(),this.__ll.GetLat() + tL.GetLat()));
	});*/
}
function GpsDisplay(zbS){
	var _data=zbS.split(";");
	var zb="";var time="";
	for(var a=0;a<_data.length;a++){
		var _data1=_data[a].split("~");
		// for(var b=0;b<_data1.length;b++){
			zb+=_data1[0]+" "+_data1[1]+",";
			time+=_data1[2]+",";
		// }
	} 
	zb=zb.substring(0,zb.length-1); 
	LineDispaly(zb);
}
function speedInit(speed){
	speed1=speed*100;
	 $('body').stopTime('A');
	 $('body').everyTime(speed1,'A', GpsStart);
}
function GpsStart(data,data_yx,speed){
		//_interval =  setInterval(GpsStart, 100);
	if(speed1==""||speed1.length==0){
		speed1=speed*100;
	}
	$('body').everyTime(speed1,'A', GpsStart);
	if(data!=null && data.length>0){
		_data_zb=data;
	}
	if(data_yx!=null && data_yx.length>0){
		_data_time = data_yx;
	}
	var _zb=_data_zb.split(";");
	// var _zb=_data_zb.split(",");
	var _time=_data_time.split(",");
	var a=_zb.length;
	if(IsEnd()){ 
		$('body').stopTime('A');
		_data_zb="";
		_data_time=""; 
		c=0;
		window.frames["menu"].$("#btnStart").removeAttr("disabled");
		window.frames["menu"].$("#btnStop").attr("disabled","disabled"); 
		window.frames["menu"].$("#btnPause").attr("disabled","disabled"); 
		window.frames["menu"].$("#btnPause").attr("value","暂停");
		window.frames["menu"].$("#btnPause").attr("ty","zt");
	}else{
		var tmp = (a-_index)>7?7:(a-_index); 
		var link1="<div style='margin-top:2px;width:100%' ><table width=\"100%\">"; 
		for(var b= _index;b<tmp + _index;b++){
		 
			var _xy=_zb[b].split(",");
					if(_xy[2]!=null&&_xy[2].length>15){
						_xy[2]=_xy[2].substring(0,15)+"...";
					}
					if(_xy[3]!=null&&_xy[3].length>15){
						_xy[3]=_xy[1].substring(3,15)+"...";
					}
			if(b==_index){
				link1+="<tr><td bgcolor='#E2E9BF' style='line-height:22px; padding-left:5px; text-align:left; height:24px; border:1px solid #fff; width:100%'><strong>经度："+_xy[0]+"  纬度："+_xy[1]+"<br>时间："+_time[b]+"</strong></td></tr>";
			}else{
				link1+="<td bgcolor='#ECF1D6' style='line-height:22px; padding-left:5px; text-align:left; height:24px; border:1px solid #fff; width:100%'>经度："+_xy[0]+"  纬度："+_xy[1]+"<br>时间："+_time[b]+"</td></tr>";
			}
		}
		_index++;
		link1+="</table></div>";
		$("#conent2").empty().append(link1);
		XingZhou();
	}
}
 
function GpsInit(zb,data_yx) {
	idx = 0;
	/*
	//显示有效点
	 var e=document.getElementById("gj_data");
	e.scrollTop=0;
	*/
	if(zb!=null&&zb.length>0){
		ppS1 = GetPointSFromString(zb);
	    ppS2 = data_yx.split(";");
		cnt = ppS1.length;
		cnt1=ppS2.length;
	}
	if(zb!=null&&zb.length>0){
		ppS1 = GetPointSFromString(zb);
	}
	if(data_yx!=null&&data_yx.length>0){
		zb_yx=data_yx;
		
	} 
  /*  var gg5 = new SIcon("../TJMap/Resource/Theme/StyleDefault/marker1.png", new SSize(21, 25));
    var gg2 = new SIcon("../TJMap/Resource/Theme/StyleDefault/close1.gif", new SSize(21, 25));
    mm3 = new SMarker(new SLonLat(ppS1[0].GetX(), ppS1[0].GetY()), gg5);
    markerL.AddMarker(mm3);
    mm3.SetHoverIcon(gg2);
    mm3.AddEventListener("click", mm3, function() {
        map.SInfoWindow.SetLonLat(mm3.GetLonLat(), new SSize(0, -40));
        var textHtml = "<DIV style=\"PADDING-LEFT: 10px; PADDING-TOP: 5px\"> </DIV>";
        textHtml += "<DIV style=\"BORDER-BOTTOM: #c9c9c9 1px solid; PADDING-BOTTOM: 10px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; PADDING-TOP: 10px\">";
        textHtml += "<TABLE style=\"FONT-FAMILY: '宋体'; FONT-SIZE: 12px\">";
        textHtml += "<TBODY>";
        textHtml += "<TR>";
     
        map.SInfoWindow.SetInnerHTML(textHtml);
        map.SInfoWindow.Show(); 
        map.SInfoWindow.SetSize(new SSize(300, 150));
    });*/
    ele = document.getElementById("btnGps");
}
function GetCurrentIdx(){
	return idx;
}
function SetCurrentIdx(_idx){
	if(_idx!=null){
		try{
			idx = parseInt(_idx);
		}catch(evcd){}
	}
}
function IsEnd(){
	if(idx==ppS1.length-1){
		return true;
	}
	return false;
}
 //轨迹控制
function suspend_gj(isOpen,speed){
	speed=speed*100;
   if (isOpen=='zt') {
	   $('body').stopTime('A');
    } else if(isOpen=='jx'){
    	$('body').everyTime(speed,'A', GpsStart);
      }else if(isOpen=='stop'){
    	  $('body').stopTime('A');
    	  idx = 0;
      }
}
// 行走
function XingZhou() {
    if (idx < cnt - 1) {
        idx++;
    } else{
        idx = 0;
    }
    //判断是否超出范围
    var x=ppS1[idx].GetX();
    var y=ppS1[idx].GetY();
   /* if(x<120.14648688459397||x>20141584300995||y<30.2495790402436||y>30.27804154440108){
    	//alert("轨迹超出范围！");
    	//return;
    }*/
    var _ll=new SLonLat(ppS1[idx].GetX(), ppS1[idx].GetY());
  //  mm3.MoveToWithLonLat(_ll); 
    //当前屏幕坐标
    var  aa=map.GetExtent();
    var x1=aa.GetLeft();
    var y1=aa.GetTop();
    var x2=aa.GetRight();
    var y2=aa.GetBottom();
    if(ppS1[idx].GetX()<x1||ppS1[idx].GetX()>x2|| ppS1[idx].GetY()>y1|| ppS1[idx].GetY()<y2){
    	map.SetCenterMoveSlowing(_ll);
    }
  //  map.GetLonLatFromViewPortPx();
   // map.SInfoWindow.SetLonLat(_ll);
    ReDrawLine();
    var data=zb_yx.split(";");
    for(var a=0;a<data.length;a++){
    	 var data1=data[a].split(",");
    	 if(data1[2]==ppS1[idx].GetX()&&data1[3]==ppS1[idx].GetY()){
    		 dwgj(data[a],a);
    		 var e=document.getElementById("gj_data");
    		 var tr=$("tr[name='gj_tr']").each(function (i){
    			 var num=$(this).attr("num");
    			 if(a+1==num){
    				 $(this).css("background-color","#fbec88");
    				 if(a+1>4){
    					 e.scrollTop=24*(a-4);
    				 }
    			 }else{
    				 $(this).css("background-color","#efefef");
    			 }
    		 });
    		 
    	 }
    }
}
function ReDrawLine() {
	var  mStyle2 = new SFeatureStyle();
    mStyle2.SetFillColor("none");
   // meatureStyle.SetHoverFillColor("red");
    mStyle2.SetStrokeColor("#5ECB46");
    mStyle2.SetStrokeStartArrow("Oval");
    mStyle2.SetStrokeEndArrow("Classic");
    mStyle2.SetStrokeDashStyle("Solid"); 
    mStyle2.SetHoverEnabled(false);
    mStyle2.SetStrokeWidth(3);
    vectorLayer.DestroyFeatures();
    var xL = new SLineString(GetppSPart());
    var pf = new SFeature(xL, mStyle2);
    vectorLayer.AddFeatures([pf]);
}
function GetppSPart() {
    var rtn = [];
    for (var u = 0; u <= idx; u++) {
        rtn.push(ppS1[u]);
    }
    return rtn;
}

function GetPointSFromString(pStr) {
    var rtnS = [];
    if (pStr != null && pStr != "") {
        var tmR = pStr.split(';');
        var bb=0;
        for (var u = 0; u < tmR.length; u++) {
            if (tmR[u] != "") {
                var tmp2 = tmR[u].split(',');
                if (tmp2.length == 6) {
                    var pt = new SPoint(tmp2[2], tmp2[3]);
                    if(bb==0){
                    	map.SetCenter(new SLonLat(tmp2[2],tmp2[3]),3);
                    }
                    rtnS.push(pt);
                }
            }
            bb++;
        }
    }
    return rtnS;
}  


function Z_DxClick(evt) {  
    xmapRK.style.left = evt.x+7;
    xmapRK.style.top = evt.y+8;
    xmapRK.style.display = "inline";
} 



function LineDispaly(zbStr){ 
	var  mStyle2 = new SFeatureStyle();
    mStyle2.SetFillColor("none");
 // meatureStyle.SetHoverFillColor("red");
    mStyle2.SetStrokeColor("blue");
    mStyle2.SetStrokeStartArrow("Oval");
    mStyle2.SetStrokeEndArrow("Classic");
    mStyle2.SetStrokeDashStyle("ShortDashDot"); 
    mStyle2.SetHoverEnabled(false);
    mStyle2.SetStrokeWidth(3);
    vectorLayer.DestroyFeatures();
    var xL = new SLineString(GetPointSFromString(zbStr));
    var pf = new SFeature(xL, mStyle2);
    vectorLayer.AddFeatures([pf]);
}
