/**
 * @see 单个景点定位显示
 * @author xiao
 */
function AttractionsDw(fuid) {
	$.ajax({
		url : "virtual!attractions?id=" + fuid + "&time" + new Date(),
		async : false,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			ClearVS();
			AttractionsShow(data,"one");
		}
	});
}
/**
 * @see 单个景区景点定位显示
 * @author xiao
 */
function ScenicDw(fuid,type) {
	//景点
	if(type!=null&&type=='Attractions'){
		$.ajax({
			url : "virtual!attractions?id=" + fuid + "&time" + new Date(),
			async : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				ClearVS();
				AttractionsShow(data,"one","attr");
			}
		});
	//景区
	}else{
		$.ajax({
			url : "virtual!scenic?id=" + fuid + "&time" + new Date(),
			async : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				ClearVS();
				AttractionsShow(data,"one","sce");
			}
		});
	}
}
/**
 * @see 景点地图显示
 * @param data
 */
var te="";
function AttractionsShow(data,num,type,isShow) {
	if(isShow==null){
		isShow=true;
	}
	var zb_x = data.fx;
	var zb_y = data.fy;
	var icon2 = new SIcon("/images/v_mappoint.png", new SSize(20, 32),
			new SPixel(-10, -32));
	var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, "attractions");
	var ll = new SLonLat(zb_x, zb_y);

	
	var Ssize = new SSize(30, 30);
	maker2._ll = ll;
	maker2.__text=getDivAttractions(data,type);
	markerL.AddMarker(maker2);
	if(isShow){
	map.SInfoWindow.SetLonLat(ll);
	map.SInfoWindow.SetInnerHTML(maker2.__text);
	map.SInfoWindow.SetSize(new SSize(550, 460));
	map.SInfoWindow.Show();
	}
	maker2.AddEventListener("click", maker2, function() {
		map.SInfoWindow.SetLonLat(this._ll);
		map.SInfoWindow.SetInnerHTML(this.__text);
		map.SInfoWindow.SetSize(new SSize(550, 460));
		map.SInfoWindow._marker=this;
		map.SInfoWindow.Show();
	});
	if(num=='one'){ 
		var dtLonLat = getLonLatFromPixel(map,new SPixel(40,40));
		//ll = ll.Add(dtLonLat.GetLon(), dtLonLat.GetLat())
		map.SetCenter(ll,3); 
		map.Pan(330,-230);
	}
	if(num=='Nav'){
		 map.SInfoWindow.ReWriteInfoClose(function () {
		        map.SInfoWindow.Hide();
		        $('body').everyTime('1s',te,movePer);
		   });
	}
   
}

function getLonLatFromPixel(currentMap, addPixel) {
    if (currentMap != null && addPixel != null) {
        var currRes = currentMap.GetResolution();
        return new SLonLat(addPixel.GetX() * currRes, addPixel.GetY()*currRes);
    }
    return new SLonLat(0, 0);
}

/**
 * @see 兴趣点地图显示
 * @param data
 */
function InterestShow(data,type) {
	var zb_x = data.fx;
	var zb_y = data.fy;
	var icon2 = new SIcon("/images/v_mappoint.png", new SSize(20, 32),
			new SPixel(-10, -32));
	if(type=='002001'){
		icon2 = new SIcon("/images/gps/maptip_1.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002002'){
		icon2 = new SIcon("/images/gps/maptip_2.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002003'){
		icon2 = new SIcon("/images/gps/maptip_3.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002004'){
		icon2 = new SIcon("/images/gps/maptip_4.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002005'){
		icon2 = new SIcon("/images/gps/maptip_5.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002006'){
		icon2 = new SIcon("/images/gps/maptip_6.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002007'){
		icon2 = new SIcon("/images/gps/maptip_7.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002008'){
		icon2 = new SIcon("/images/gps/maptip_8.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002009'){
		icon2 = new SIcon("/images/gps/maptip_9.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002010'){
		icon2 = new SIcon("/images/gps/maptip_10.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002011'){
		icon2 = new SIcon("/images/gps/maptip_11.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002012'){
		icon2 = new SIcon("/images/gps/maptip_12.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	if(type=='002013'){
		icon2 = new SIcon("/images/gps/maptip_13.png", new SSize(24, 30),new SPixel(-12, -30));
	}
	var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, data.ftype);
	var ll = new SLonLat(zb_x, zb_y);
	var Ssize = new SSize(30, 30);
	maker2._ll = ll;
	maker2.__text=getDivInterest(data);
	markerL.AddMarker(maker2);
	maker2.AddEventListener("click", maker2, function() {
		map.SInfoWindow.SetLonLat(this._ll);
		map.SInfoWindow.SetInnerHTML(this.__text);
		map.SInfoWindow.SetSize(new SSize(350, 320));
		map.SInfoWindow.Show();
	});
}

/**
 * @see 景点详细页显示
 * @author xiao
 */
function getDivAttractions(data,type){
	var txtHtml="";
	txtHtml+="<div class='in_pbox'>";
	txtHtml+=" <div class='ptit ff'>"+data.fullname+"</div>";
	txtHtml+=" <div class='mppic'><iframe scrolling='no' width='458' height='264' src='"+data.virtualurl+"' frameborder='0'></iframe></div>";
	txtHtml+=" <div class='mpinfo1 clearfix'>";
	//txtHtml+=" <div><strong>地    址："+(data.address!=undefined?data.address:'')+"</strong></div>";
	//txtHtml+=" <div><strong>开放时间："+(data.openinghours!=undefined?data.openinghours:'')+"</strong></div>";
	//txtHtml+="<div><strong>门票信息1："+(data.tickets!=undefined?data.tickets:'')+"</strong></div>";
	//txtHtml+="&nbsp;&nbsp;&nbsp;&nbsp;"+ (data.introduction!=undefined?data.introduction:'');
	
	var introduction=data.introduction;
	if(introduction.length>120){
		introduction=introduction.substring(0,120)+"...";
	}
	if(type=='attr'){
		txtHtml+="&nbsp;&nbsp;&nbsp;&nbsp;"+introduction+"<a style='color: blue;' target='_blank' href='virtual!pageAtt?id="+data.fuid+"' class='tjaBtn'>查看详情</a>";
	}else if(type=='sce'){
		txtHtml+="&nbsp;&nbsp;&nbsp;&nbsp;"+introduction+"<a style='color: blue;' target='_blank' href='virtual!pageSce?id="+data.fuid+"' class='tjaBtn'>查看详情</a>";
	}else{
		txtHtml+="&nbsp;&nbsp;&nbsp;&nbsp;"+introduction+"<a style='color: blue;' target='_blank' href='virtual!pageAtt?id="+data.fuid+"' class='tjaBtn'>查看详情</a>";
	}
	
	txtHtml+="</div>";
	txtHtml+=" </div>";
	return txtHtml;
}
/**
 * @see 兴趣点详细页显示
 * @author xiao
 */
function getDivInterest(data){
	var txtHtml="";
	txtHtml+="<div class='in_pbox'>";
	txtHtml+=" <div class='ptit ff'>"+data.fullname+"</div>";
	txtHtml+=" <div class='mpinfo1 clearfix'>";
	txtHtml+=" <div><img src='/upload/500-"+(data.imagesurl!=undefined?data.imagesurl:'')+"' width='110' height='110' alt='' /></div>";
	txtHtml+="</div>";
	txtHtml+=" <div class='mpinfo1 clearfix'>";
	txtHtml+=" <div><strong>地    址："+(data.address!=undefined?data.address:'')+"</strong></div>";
	txtHtml+="</div>";
	txtHtml+="<div class='mpinfo2'><strong>介绍：</strong>"+data.introduction+"</div>";
	txtHtml+=" </div>";
	return txtHtml;
}
/**
 * @see 单个兴趣点查询
 * @author xiao
 */
function getInterestOne(id){
	$.ajax({
		url : "virtual!interestOne?id="+id+"&time" + new Date(),
		async : false,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			if(data!=null){
				InterestShow(data,data.ftype);
			}
		}
	});
}
/**
 * @see 路灯详细页显示
 * @author xiao
 */
function getDivLights(data){
	var txtHtml="";
	txtHtml+="<div class='in_pbox'>";
	txtHtml+=" <div class='ptit ff'>"+data.fullname+"</div>";
	txtHtml+=" <div class='mpinfo1 clearfix'>";
	txtHtml+=" <div>编    号："+(data.dnumber!=undefined?data.dnumber:'')+"</div>";
	txtHtml+="</div>";
	txtHtml+="<div class='mpinfo1 clearfix'><div>地    址："+(data.address!=undefined?data.address:'')+"</div></div>";
	txtHtml+="<div class='mpinfo1 clearfix'><div>开关状态："+(data.status!=undefined?data.status:'')+"</div></div>";
	return txtHtml;
}
/**
 * @see 单个服务项目定位显示
 * @author xiao
 */
function ServiceltemsDw(fuid) {
	$.ajax({
		url : "virtual!serviceltems?id=" + fuid + "&time" + new Date(),
		async : false,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			ServiceltemsShow(data);
		}
	});
}
function ServiceltemsShow(data) {
	var zb_x = data.fx;
	var zb_y = data.fy;
	var fuid = data.fuid;
	var name = data.fullname;
	var icon2 = new SIcon("/images/v_mappoint.png", new SSize(20, 32),
			new SPixel(-10, -32));
	var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, data.fuid);
	var ll = new SLonLat(zb_x, zb_y);
	var Ssize = new SSize(30, 30);
	maker2._fuid = fuid;
	maker2._ll = ll;
	markerL.AddMarker(maker2);
	maker2.AddEventListener("click", maker2, function() {
		
	});
}
/**
 * @see 显示图层地图定位显示
 * @author xiao
 */
	function showTc(){
		$("input[name='showTc']").click(function (){
			//景点
			if($(this).val()=='景点'){
				if($(this).attr("checked")){
					$.ajax({
						url : "virtual!attractionsAll?time" + new Date(),
						async : false,
						dataType : "json",
						contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						success : function(data) {
							if(data!=null&&data.length>0){
								for(var a=0;a<data.length;a++){
									AttractionsShow(data[a],null,null,false);
								}
							}
						}
					});
				}else{
					markerL.HideMarkersByTag("attractions");
				}
			}
			//其他兴趣点
			if($(this).val().indexOf('002')>=0){
				if($(this).attr("checked")){
					var type=$(this).val();
					$.ajax({
						url : "virtual!interestAll?type="+type+"&time" + new Date(),
						async : false,
						dataType : "json",
						contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						success : function(data) {
							if(data!=null&&data.length>0){
								for(var a=0;a<data.length;a++){
									InterestShow(data[a],type);
								}
							}
						}
					});
				}else{
					markerL.HideMarkersByTag($(this).val());
				}
			}
			
		});
		
	}
	/**
	 * @see 显示线路
	 * @author xiao
	 */
	var pxy;
	var attrPoint;
	var num=0;
	function showNav(fxy,id){
		ClearVS();
		$('body').stopTime();
		num=0;
		var l_rcgs2_1 =fxy;
		var geo = new SLinearRing(GetPointS(l_rcgs2_1));
		var ppS1 = SPoint.GetPointSFromString(l_rcgs2_1);
		var xL = new SLineString(ppS1); 
		  var featureStyle2 = new SFeatureStyle();
	        featureStyle2.SetFillColor("none");
	        featureStyle2.SetHoverFillColor("none");
	        featureStyle2.SetStrokeColor("red");
	        //featureStyle2.SetStrokeStartArrow("Oval");
	        featureStyle2.SetStrokeWidth(6);
	        //featureStyle2.SetStrokeEndArrow("Classic");
	        featureStyle2.SetStrokeDashStyle("SHORTDASH");
	        featureStyle2.SetStrokeArrowLength(5);
	        featureStyle2.SetStrokeArrowWidth(5);
	        featureStyle2.SetHoverEnabled(true);
	        featureStyle2.SetHoverStrokeWidth(8);
	    var pf = new SFeature(xL, featureStyle2,"ax");
		vectorLayer.AddFeatures([pf]);
		pf.Activate();
		$.ajax({
			url : "virtual!showNav?id="+id+"&time" + new Date(),
			async : false,
			dataType : "text",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				if(data!=null&&data.length>0){
					var obj=eval("("+data+")");
					playNav(fxy,obj);
				}
			}
		});
		
	}
	/**
	 * @see 播放线路
	 * @author xiao
	 */
	function playNav(fxy,data){
		var xy=fxy.split(",");
		te=(new Date()).pattern("yyyy-MM-dd hh:mm:ss.S");
		if(xy.length>0){
			//初始显示人物
			var zb_x = xy[0].split(" ")[0];
			var zb_y = xy[0].split(" ")[1];
			var icon2 = new SIcon("/images/gps/map_guide.gif", new SSize(100, 142),new SPixel(-50, -142));
			var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, "per");
			var Ssize = new SSize(30, 30);
			markerL.AddMarker(maker2);
			//icon2.GetDiv().innerHTML="<img src='images/gps/map_guide.gif'/>";
			//移动人物
			pxy=xy;
			attrPoint=data;
			$('body').everyTime('1s',te,movePer);
		}
	}
	function movePer(){
		if(num==pxy.length){
			$('body').stopTime(te);
			return;
		}
		var attr=attrPoint[pxy[num]];
		//显示管理景点
		if(attr!=null){
			$('body').stopTime(te);
			//显示点
			AttractionsShow(attr,"Nav");
			//-----------延迟执行
			//setTimeout(function(){var a=1;}, 5000);
			//----------继续定时器
		}
		var zb_x = pxy[num].split(" ")[0];
		var zb_y = pxy[num].split(" ")[1];
		var mm=markerL.GetMarkersByTag("per")[0];
		var ll = new SLonLat(zb_x, zb_y);
		mm.MoveToWithLonLat(ll);
		num++;
	}
	/**
	 * @see 视频地图显示
	 * @author xiao
	 */
	function VideoData() {
		$.ajax({
			url : "obVideo!showAll?time" + new Date(),
			async : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				if(data!=null&&data.length>0){
					VideoShow(data);
				}
			}
		});
		
	}
	function VideoShow(data){
		for(var a=0;a<data.length;a++){
			var zb_x = data[a].fx;
			var zb_y = data[a].fy;
			var icon2 = new SIcon("/images/gps/map_camera.png", new SSize(24, 31),
					new SPixel(-12, -31));
			var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, "video");
			var ll = new SLonLat(zb_x, zb_y);
			var Ssize = new SSize(30, 30);
			maker2._ll = ll;
			maker2._sCameraID = data[a].camid;
			maker2._sChannelID = data[a].channel;
			markerL.AddMarker(maker2);
			maker2.AddEventListener("click", maker2, function() {
				//$("#ifrVv").attr("src","/js/OCXDEMO/OcxDemoV2.0.htm?sCameraID="+this._sCameraID+"&sChannelID="+this._sChannelID);
				//$('#vv').window({}).window('open');
				window.showModalDialog("/js/OCXDEMO/OcxDemoV2.0.htm?sCameraID="+this._sCameraID+"&sChannelID="+this._sChannelID,"","dialogWidth=700px;dialogHeight=600px");
			});
		}
	}
	/**
	 * @see 路灯地图显示
	 * @author xiao
	 */
	function LightData() {
		$.ajax({
			url : "buStreetlights!showAll?time" + new Date(),
			async : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			success : function(data) {
				if(data!=null&&data.length>0){
					LigthShow(data);
				}
			}
		});
	   
	}
	function LigthShow(data){
		for(var a=0;a<data.length;a++){
			var zb_x = data[a].fx;
			var zb_y = data[a].fy;
			var icon2 = new SIcon("/images/gps/map_lamp.png", new SSize(14, 28),
					new SPixel(-7, -28));
			var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, "video");
			var ll = new SLonLat(zb_x, zb_y);
			var Ssize = new SSize(30, 30);
			maker2._ll = ll;
			maker2.__text=getDivLights(data[a]);
			markerL.AddMarker(maker2);
			maker2.AddEventListener("click", maker2, function() {
				map.SInfoWindow.SetLonLat(this._ll);
				map.SInfoWindow.SetInnerHTML(this.__text);
				map.SInfoWindow.SetSize(new SSize(320, 260));
				map.SInfoWindow._marker=this;
				map.SInfoWindow.Show();
			});
		}
	}
	