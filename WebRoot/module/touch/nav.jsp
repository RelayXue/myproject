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
		
		<link rel="stylesheet" href="<%=curl %>css/bootstrap.css">
		<link rel="stylesheet" href="<%=curl %>css/theme.css">
		<link rel="stylesheet" href="<%=curl %>css/k_tables.css">

		<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=curl %>js/jquery.min.js"></script>
		<script src="<%=curl %>js/bootstrap.min.js"></script>
		<script src="<%=curl %>js/owl.carousel.min.js"></script>
		<script src="<%=curl %>js/k_tables.js"></script>
		<script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
    	<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
   		<script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
   		<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>
		<script src="<%=basePath %>module/web/js/TMap.js" type="text/javascript"></script>

		<!--[if IE]>
			<link rel="stylesheet" href="css/ie.css">
		<![endif]-->

		<!--[if lte IE 8]>
			<script src="js/respond.js"></script>
			<script src="js/excanvas.js"></script>
		<![endif]-->
    <style type="text/css">
		body{overflow-y:hidden;}
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
        
        .icosp{ background-image: url("img/sor.png"); background-repeat: no-repeat; width: 22px; height: 37px;overflow: hidden; color: #fff; text-align: center;}
    </style>
<script type="text/javascript">
	var types = new Array();	//类别数组
	var category;
	var category_index = 1;
	var cur_page_index = [1,1,1,1];
	var cur_tab = <%=request.getParameter("tab")%>;	//1地图 2列表 表示当前处于哪个界面 默认为地图
	if(cur_tab == null) {cur_tab = 1; addHistory(); }
	
	//1表示显示路径
	var flag = "<%=request.getParameter("flag")%>";
	var to_id = "<%=request.getParameter("to_id")%>";
	
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
		Init();
		category = "<%=request.getParameter("category")%>";
		topSetTab(cur_tab);
		//默认选中类别 - 列表
		//$("#nav_menu li a[href='"+category+"']").parent().toggleClass("active");
		//initChildCategory(category);
		setTab(category, 0);
		
		$("#nav_menu li").click(function(){ setTab($(this).children("a").attr("href"), 0); filterTabClose(); });
		$("#nav_menu li").eq((category-1)).click();
		
		$("#map_menu a").click(function(){ setTab($(this).attr("val"), 0); });
		
		$("#area_select li").click(function(){ 
			$("#area_select li a").attr("class", "");
			$(this).children("a").attr("class", "checked");
			loadDate(0);
			filterTabClose();
		});
		$("#order_select li").click(function(){ 
			$("#order_select li a").attr("class", "");
			$(this).children("a").attr("class", "checked");
			loadDate(0);
			filterTabClose();
		});
		
		aa();
		
		//加载热点推荐关键字
		$.post("touch_main_hotRecommended", {}, function(json) {
			var code = json.code;
			var hint = json.hint;
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var html = '';
				for(i=0;i<o.length;i++){
					$("#hot").append('<li><a href="<%=curl%>map_search_results.jsp?key='+o[i].fullname+'">'+o[i].fullname+'<i class="goto"></i></a></li>');
				}
			}
		}, "json");
		
		if(parseInt(flag) == 1 && !isNull(to_id)){
			topSetTab(0);
			showGoToRoute(to_id);
		}
	});
	
	function initChildCategory(c){
		$("#filter_select").html("");
		//添加筛选列表子类
		for(i = 0;i<basetemp.length;i++){
			var code = basetemp[i].code;
			if(code.substring(0,6) == c){
				if(code.length > 6)	{ 
					$("#filter_select").append('<li><a href="'+code+'" data-toggle="tab">'+basetemp[i].fullname+'</a></li>'); 
				}else if(code.length == 6){
					$("#filter_select").prepend('<li><a class="checked" href="'+code+'" data-toggle="tab">所有</a></li>');
				}
			}
		}
		$("#filter_select li").click(function(){
			$("#filter_select li a").attr("class", "");
			$(this).children("a").attr("class", "checked");
			loadDate(0);
			filterTabClose();
		});
	}
	
	function filterTabSelect(i){
		filterTabClose();
		var box = $("#filter_box_"+i);
		if(box.is(":hidden")){
			//$("#filter_tab li").eq(i-1).addClass("active");
			box.show();
			$("body").append('<div id="_mask" onclick="filterTabClose()" style=" position: fixed; top: 0px; left: 0px; filter:alpha(opacity=50);-moz-opacity:0.50; opacity:0.50;background-color: #000; width: 100%; height: 9999px; z-index: 100"></div>');
		}
	}
	
	function filterTabClose(){
		$("#filter_box_1").hide();
		$("#filter_box_2").hide();
		$("#filter_box_3").hide();
		//$("#filter_tab li").eq(0).removeClass("active");
		//$("#filter_tab li").eq(1).removeClass("active");
		//$("#filter_tab li").eq(2).removeClass("active");
		$("#_mask").remove();
	}
	
	//滚动加载
	function aa(){
	    var winH = $(window).height(); //页面可视区域高度 
	    $(window).scroll(function () { 
	        var pageH = $(document.body).height(); 
	        var scrollT = $(window).scrollTop(); //滚动条top 
	        var aa = (pageH-winH-scrollT)/winH;
	        if(aa<0.02 && !scroll_loading && cur_tab == 2){
				loadDate(1);
	        } 
	    }); 
	}
	
	//图片滚动
	function owlCarousel(c){
	  $(".g"+c).owlCarousel({
      navigation : false, // Show next and prev buttons
      slideSpeed : 300,
      paginationSpeed : 400,
      singleItem:true
  	  });
	}
	
	//列表标签切换
	function setTab(category, meo){
		vectorLayer.ClearFeatures();
		var id = 0;
		var cp = category.substring(0,6);
		if(cp == "002018"){
			id = 1;
		}else if(cp == "002016"){
			id = 2;
		}else if(cp == "002015"){
			id = 3;
		}else if(cp == "002017"){
			id = 4;
		}
		$("#box_1, #box_2, #box_3, #box_4").hide();
		$("#box_"+id).show();
		$("#nav_menu li").removeClass("active");
		$("#nav_menu li").eq(id-1).toggleClass("active");
		$("#map_menu a").removeClass("active");
		$("#map_menu a").eq(id-1).toggleClass("active");
		
		initChildCategory(category);
		loadDate(meo);
		this.category = category;
		
		addHistory();
	}
	
	//修改url地址
	function addHistory(){
		var state = {
			title: category,
			url: "<%=curl%>nav.jsp?category="+category+"&tab="+cur_tab,
			otherkey: null
		};
		window.history.pushState(state, document.title, "<%=curl%>nav.jsp?category="+category+"&tab="+cur_tab);
	}
	
	//顶部标签切换
	function topSetTab(id){
		$(".nav_box").hide();
		//$("#map_box").hide();
		$("#map_box").css("visibility", "hidden");
		$("#top_tab li").removeClass();
		$("#top_tab li").eq(id-1).addClass("active");
		if(id == 1 ) {
			$("#head_box").css({"border-bottom": "none", "background": "none"});
			$("#map_box").css("visibility", "visible");
			filterTabClose(4);
		}else if(id == 2){
			$("#head_box").css({"border-bottom": "3px solid #97abaf", "background": "#bfcfd3"});
			$(".nav_box").show();
		}
		cur_tab = id;
		addHistory();
	}
	
	function getCategoryName(code){
		var tt = "";
		for(p in basetemp){ 
			if(basetemp[p].code == code){
				return basetemp[p].fullname;
			}
		}
	}
	
	function clearAllPoint(){map.SInfoWindow.Hide(); vectorLayer.ClearFeatures(); markerL.ClearMarkers(); vectorLayer.DestroyFeatures(); addMyLocation(); }
	
	//滚动加载标识 用于判断当前是否在加载中
	var scroll_loading = false;
	function loadDate(meo){
		var id = $("#nav_menu li.active").index();
		if(meo == 0) { cur_page_index[id] = 1; clearAllPoint();}
		if(cur_page_index[id] == 1) { clearAllPoint(); }
		scroll_loading = true;
		var parms = new Object();
		parms.category = $("#filter_select li a[class='checked']").attr("href");
		parms.area = $("#area_select li a[class='checked']").attr("href");
		parms.orderBy = $("#order_select li a[class='checked']").attr("href");
		parms.indexPage = cur_page_index[id];
		parms.pageSize = 15;
		$.post("touch_main_list", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			dialogForBig(hint);
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var cp = parms.category.substring(0,6);
				if(cp == "002018"){
					loadC1(o, meo);
					owlCarousel(1);
				}else if(cp == "002016"){
					loadC2(o, meo);
					owlCarousel(2);
				}else if(cp == "002015"){
					loadC3(o, meo);
					owlCarousel(3);
				}else if(cp == "002017"){
					loadC4(o, meo);
					owlCarousel(4);
				}
				
			}
			scroll_loading = false;
		}, "json");
	}
	
	//地图上添加点
	function addPoint(name, tab_info, fx, fy){
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
	
	function loadC1(o, meo){
		var html = "";
		var points = [];
		for(i=0;i<o.length;i++){
			var url = '<%=curl%>nav_meishi_detail.jsp?id='+o[i].fuid;
			var img_name = '';
			var imgs = o[i].dimages.split(",");
			if(imgs.length > 0){imgs = imgs[0]; img_name = '<%=basePath%>upload/L_'+imgs;}else{imgs = null;}
			html += genItem(imgs, o[i].dimagesStatus, cutString(o[i].fullname, 20), getCategoryName(o[i].type), o[i].phone, cutString(o[i].address, 18), o[i].fuid, url);
			var maker_info = '<div class="popup"><ul><li><img class="img-responsive" src="'+img_name+'" style="width:210px; height:310px;" /></li><li style="width: 280px; overflow: hidden; font-size:15px;"><h3>'+cutString(o[i].fullname, 20)+'</h3><p>行业类型：'+getCategoryName(o[i].type)+'<br>负责人：'+nullStrCheck(o[i].supervisor, '-')+'<br>负责电话：'+nullStrCheck(o[i].supervisorphone, '-')+'<br>店铺电话：'+nullStrCheck(o[i].phone, '-')+'<br>地址：'+cutString(o[i].address, 18)+'</p><a class="btn btn-lg btn-yule" href="<%=curl%>nav_meishi_detail.jsp?id='+o[i].fuid+'">详情<i class="fa fa-angle-right"></i></a><a class="btn btn-lg btn-zhusu" href=javascript:showGoToRoute("'+o[i].fuid+'")>前往路径<i class="fa fa-location-arrow"></i></a></li></ul></div>';
			
			points.push(new SLonLat(o[i].fx, o[i].fy));
			addPoint(String.fromCharCode(65+i), maker_info, o[i].fx, o[i].fy);
		}
		//地图自适应布满点位
		if(map != null) map.ZoomToExtent(SUtil.GetMaxBounds(points));
		//地图定位到最后一条记录上
		if(o.length > 0){ map.SetCenter(new SLonLat(o[o.length-1].fx, o[o.length-1].fy)); }
		if(meo == 0) $("#box_1").html(html);
		if(meo == 1){ $("#box_1").append(html);cur_page_index[0]++;}
		
	}
	
	function loadC2(o, meo){
		var html = "";
		var points = [];
		for(i=0;i<o.length;i++){
			var url = '<%=curl%>nav_yule_detail.jsp?id='+o[i].fuid;
			var img_name = '';
			var imgs = o[i].dimages.split(",");
			if(imgs.length > 0){imgs = imgs[0]; img_name = '<%=basePath%>upload/L_'+imgs; }else{imgs = null;}
			html += genItem(imgs, o[i].dimagesStatus, cutString(o[i].fullname, 20), getCategoryName(o[i].type), o[i].phone, cutString(o[i].address, 18), o[i].fuid, url);
			var maker_info = '<div class="popup"><ul><li><img class="img-responsive" src="'+img_name+'" style="width:210px; height:310px;" /></li><li style="width: 280px; overflow: hidden; font-size:15px;"><h3>'+cutString(o[i].fullname, 20)+'</h3><p>行业类型：'+getCategoryName(o[i].type)+'<br>负责人：'+nullStrCheck(o[i].supervisor, '-')+'<br>负责电话：'+nullStrCheck(o[i].supervisorphone, '-')+'<br>店铺电话：'+nullStrCheck(o[i].phone, '-')+'<br>	地址：'+cutString(o[i].address, 18)+'</p><a class="btn btn-lg btn-yule" href="<%=curl%>nav_yule_detail.jsp?id='+o[i].fuid+'">详情<i class="fa fa-angle-right"></i></a><a class="btn btn-lg btn-zhusu" href=javascript:showGoToRoute("'+o[i].fuid+'")>前往路径<i class="fa fa-location-arrow"></i></a>	</li></ul></div>';

			points.push(new SLonLat(o[i].fx, o[i].fy));
			addPoint(String.fromCharCode(65+i), maker_info, o[i].fx, o[i].fy);
		}
		//地图自适应布满点位
		if(map != null) map.ZoomToExtent(SUtil.GetMaxBounds(points));
		//地图定位到最后一条记录上
		if(o.length > 0){ map.SetCenter(new SLonLat(o[o.length-1].fx, o[o.length-1].fy)); }
		if(meo == 0) $("#box_2").html(html);
		if(meo == 1){ $("#box_2").append(html);	}
		cur_page_index[1]++;
	}
	
	function loadC3(o, meo){
		var html = "";
		var points = [];
		for(i=0;i<o.length;i++){
			var url = '<%=curl%>nav_gouwu_detail.jsp?id='+o[i].fuid;
			var img_name = '';
			var imgs = o[i].dimages.split(",");
			if(imgs.length > 0){imgs = imgs[0]; img_name = '<%=basePath%>upload/L_'+imgs; }else{imgs = null;}
			html += genItem(imgs, o[i].dimagesStatus, cutString(o[i].fullname, 20), getCategoryName(o[i].type), o[i].phone, cutString(o[i].address, 18), o[i].fuid, url);
			var maker_info = '<div class="popup"><ul><li><img class="img-responsive" src="'+img_name+'" style="width:210px; height:310px;" /></li><li style="width: 280px; overflow: hidden; font-size:15px;"><h3>'+cutString(o[i].fullname, 20)+'</h3><p>行业类型：'+getCategoryName(o[i].type)+'<br>负责人：'+nullStrCheck(o[i].supervisor, '-')+'<br>负责电话：'+nullStrCheck(o[i].supervisorphone, '-')+'<br>店铺电话：'+nullStrCheck(o[i].phone, '-')+'<br>	地址：'+cutString(o[i].address, 18)+'</p><a class="btn btn-lg btn-yule" href="<%=curl%>nav_gouwu_detail.jsp?id='+o[i].fuid+'">详情<i class="fa fa-angle-right"></i></a><a class="btn btn-lg btn-zhusu" href=javascript:showGoToRoute("'+o[i].fuid+'")>前往路径<i class="fa fa-location-arrow"></i></a></li></ul></div>';

			points.push(new SLonLat(o[i].fx, o[i].fy));
			addPoint(String.fromCharCode(65+i), maker_info, o[i].fx, o[i].fy);
		}
		//地图自适应布满点位
		if(map != null){ map.ZoomToExtent(SUtil.GetMaxBounds(points));}
		//地图定位到最后一条记录上
		if(o.length > 0){ map.SetCenter(new SLonLat(o[o.length-1].fx, o[o.length-1].fy)); }
		if(meo == 0) $("#box_3").html(html);
		if(meo == 1) {$("#box_3").append(html);		}
		cur_page_index[2]++;
	}
	
	function loadC4(o, meo){
		var html = "";
		var points = [];
		for(i=0;i<o.length;i++){
			var url = '<%=curl%>nav_zhusu_detail.jsp?id='+o[i].fuid;
			var img_name = '';
			var imgs = o[i].dimages.split(",");
			if(imgs.length > 0){imgs = imgs[0]; img_name = '<%=basePath%>upload/L_'+imgs; }else{imgs = null;}
			html += genItem(imgs, o[i].dimagesStatus, cutString(o[i].fullname, 20), getCategoryName(o[i].type), o[i].phone, cutString(o[i].address, 18), o[i].fuid, url);
			var maker_info = '<div class="popup"><ul><li><img class="img-responsive" src="'+img_name+'" style="width:210px; height:310px;" /></li><li style="width: 280px; overflow: hidden; font-size:15px;"><h3>'+cutString(o[i].fullname, 20)+'</h3><p>行业类型：'+getCategoryName(o[i].type)+'<br>负责人：'+nullStrCheck(o[i].supervisor, '-')+'<br>负责电话：'+nullStrCheck(o[i].supervisorphone, '-')+'<br>店铺电话：'+nullStrCheck(o[i].phone, '-')+'<br>	地址：'+cutString(o[i].address, 18)+'</p><a class="btn btn-lg btn-yule" href="<%=curl%>nav_zhusu_detail.jsp?id='+o[i].fuid+'">详情<i class="fa fa-angle-right"></i></a><a class="btn btn-lg btn-zhusu" href=javascript:showGoToRoute("'+o[i].fuid+'")>前往路径<i class="fa fa-location-arrow"></i></a></li></ul></div>';

			points.push(new SLonLat(o[i].fx, o[i].fy));
			addPoint(String.fromCharCode(65+i), maker_info, o[i].fx, o[i].fy);
		}
		//地图自适应布满点位
		if(map != null) map.ZoomToExtent(SUtil.GetMaxBounds(points));
		//地图定位到最后一条记录上
		if(o.length > 0){ map.SetCenter(new SLonLat(o[o.length-1].fx, o[o.length-1].fy)); }
		// map.SetCenter(mapParamS.MaxExtent.GetCenterLonLat(), 4); 
		if(meo == 0) $("#box_4").html(html);
		if(meo == 1) {$("#box_4").append(html);  }	
		cur_page_index[3]++;
	}
	
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
					return s.substring(0,s.length-1) + "..";
				}
			} else {
				strlen = strlen + 1;
				if(strlen >= len){
					return s.substring(0,s.length-2) + "..";
				}
			}
		}
		return s;
	}
	
	function optionSearchBox(i){
		if(i == 1) { $("#search_box").show(); $("#data_box").hide(); }
		else if(i == 0) { $("#search_box").hide(); $("#data_box").show(); }
	}
	
	function searchData(){
		var key = $.trim($("#search_content").val());
		if(key.length > 0){
			var parms = new Object();
			parms.key = key;
			$.post("touch_main_mapSearch", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				if (code == 1) {
					var o = $.parseJSON(json.o);
					var html = '';
					for(i=0;i<o.length;i++){
						html += '<li><a href="<%=curl%>map_search_results.jsp?key='+o[i].fullname+'">'+o[i].fullname+'<small>'+o[i].address+'</small><i class="goto"></i></a></li>';
					}
					$("#search_list").html(html);
				}else{
					$("#search_list").html('<li><a>没有相关数据</a></li>');
				}
			}, "json");
		}else{ $("#search_list").html(""); }
	}
	
	function toResults(){
		window.location.href='<%=curl%>map_search_results.jsp?key='+$.trim($("#search_content").val());
	}
	
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
	
	//显示路线
	function showGoToRoute(id){
		if(!isNull(id)){
			var parms = new Object();
			parms.id = id;
			$.post("touch_main_queryRouteMes", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				dialog(hint);
				if (code == 1) {
					//清除显示标签
					map.SInfoWindow.Hide();
					vectorLayer.ClearFeatures();
					markerL.ClearMarkers(); 
					vectorLayer.DestroyFeatures();
					
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
	
	function genItem(image,image_status,title,type,mobile,address,id,url){
		var html = '';
		if(isNull(image_status) || image_status == 0){
			html += '<div class="_body_list_item" onclick=javascript:window.location.href="'+url+'">';
			html += '<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>'+cutString(title, 22)+'</td></tr></table>';
			html += '<div style="font-size:22px; margin: 15px 0 10px 0;">';
			html += '<span style="font-weight: bolder;">'+cutString(title, 7)+'</span>&nbsp;<span style="font-size:16px; color:rgb(250,128,76);">['+type+']</span>';
			html += '</div>';
			html += '<div style="font-size:14px; line-height: 26px; margin:0 0 10px 0;">';
			html += '电话：'+getNullString(cutString(mobile, 18), '-')+'<br />';
			html += '地址：'+getNullString(cutString(address, 18), '-');
			html += '</div>';
			html += '<a class="btn btn-lg btn-gouwu"><i class="fa fa-align-justify"></i>查看详情</a>';
			html += '</div>';
		}else{
			html += '<div class="_body_list_item1" onclick=javascript:window.location.href="'+url+'">';
			html += '<img src="<%=basePath%>upload/B_'+image+'" style="width: 100%;height: 344px" />';
			html += '<div style="font-size:22px; margin: 20px 0 15px 15px;">';
			html += '<span style="font-weight: bolder;">'+title+'</span>&nbsp;<span style="font-size:16px; color:rgb(250,128,76);">['+type+']</span>';
			html += '</div>';
			html += '<div style="font-size:16px; line-height: 26px;  margin: 0 0 15px 15px;">';
			html += '电话：'+getNullString(mobile, '-')+'<br />';
			html += '地址：'+getNullString(address, '-');
			html += '</div>';
			html += '<a class="btn btn-lg btn-gouwu" style="width: 205px; position: absolute;right: 25px; bottom: 28px;"><i class="fa fa-align-justify"></i>查看详情</a>';
			html += '</div>';
		}
		return  html;
	}
	
	function isNull(str){
		if(str == undefined || str == "" || str == null || str == "null" || str == "undefined"){
			return true;
		}
		return false;
	}
	function getNullString(str, def_str){
		if(isNull(str)){
			return def_str;
		}
		return str;
	}

		
</script>
<style type="text/css">
#box_1, #box_2, #box_3, #box_4{ display: none;  padding: 0 20px;}
._body_list_item1{
	display:block;
	border-radius: 5px;            /* W3C syntax */
    -moz-border-radius: 5px;      /* Gecko browsers */
    -webkit-border-radius: 5px;   /* Webkit browsers */
	box-shadow:0px 0px 6px #4E4E4E;
	background-color:#EFE6CD;
	overflow:hidden;
	font-family:"宋体";
	margin: 25px auto 0 auto;
	width: 100%;
	position: relative;
	border: 1px solid #fff;
}
._body_list_item{
	display:block;
	border-radius: 5px;            /* W3C syntax */
    -moz-border-radius: 5px;      /* Gecko browsers */
    -webkit-border-radius: 5px;   /* Webkit browsers */
	box-shadow:0px 0px 6px #4E4E4E;
	background-color:#EFE6CD;
	overflow:hidden;
	font-family:"宋体";
	margin: 20px 7px 0 7px;
	padding:10px 7px;
	width: 228px;
	position: relative;
	border: 1px solid #fff;
	float: left;
}
._body_list_item ._only_txt{
	height:141px;
	width:212px;
	overflow:hidden;
	background-image:url(img/itmb1.png);
	border:0;
	margin: 0;
}
._body_list_item ._only_txt td{
	font-size:27px;
	font-family:"微软雅黑";
	color:#FFF;
	height:141px; 
	width:212px;
	text-align:center;
	padding:0 15px;
	line-height: 30px;
}
</style>
</head>

<body class="sub" scroll="no">
<div id="data_box">
<header class="intro text-center" id="head_box" style="position: fixed; top: -1px; left: 0; width: 100%; z-index: 999;">
            <div class="top-left nav_box"><a href="<%=basePath %>touch/index"><i class="fa fa-angle-left"></i></a></div>
            <div class="tab_top">
            	<ul class="nav" id="top_tab">
                	<li><a href="javascript:topSetTab(1)"><i class="fa fa-globe"></i>地图</a></li>
                    <li><a href="javascript:topSetTab(2)"><i class="fa fa-th"></i>罗列</a></li>
                </ul>
            </div>
            <div class="top-right nav_box"><a href="javascript:optionSearchBox(1)"><img class="img-responsive" src="img/icon-search.png" alt=""></a></div>

<div class="nav_box">
<div class="container nav" style="margin-bottom:0;">
	<ul class="nav nav-tabs" id="nav_menu">
		<!-- <li><a href="#home" data-toggle="tab">所有</a></li> -->
		<li class="meishi"><a href="002018" data-toggle="tab">餐饮</a></li>
		<li class="yule"><a href="002016" data-toggle="tab">娱乐</a></li>
        <li class="gouwu"><a href="002015" data-toggle="tab">购物</a></li>
        <li class="zhusu"><a href="002017" data-toggle="tab">住宿</a></li>
	</ul>
</div>
<div class="filter">
	<ul class="nav navbar-nav" id="filter_tab">
        <li onclick="filterTabSelect(1)"><a href="javascript:void(0)">筛选</a></li>
        <li onclick="filterTabSelect(2)"><a href="javascript:void(0)">范围</a></li>
        <li onclick="filterTabSelect(3)"><a href="javascript:void(0)">排序</a></li>
	</ul>
</div>
</div>
</header>

<div class="fdropdown" style="display: none; z-index: 200; position: fixed; top: 237px;left: 0; width: 100%;" id="filter_box_1">
	<ul class="nav" id="filter_select">
	</ul>
</div>
<div class="fdropdown" style="display: none; z-index: 200; position: fixed; top: 237px;left: 0; width: 100%;" id="filter_box_2">
	<ul class="nav" id="area_select">
		<li><a class="checked" href="0" data-toggle="tab">所有</a></li>
		<li><a href="500" data-toggle="tab">500m</a></li>
		<li><a href="1000" data-toggle="tab">1000m</a></li>
		<li><a href="2000" data-toggle="tab">2000m</a></li>
		<li><a href="3000" data-toggle="tab">3000m</a></li>
	</ul>
</div>
<div class="fdropdown" style="display: none; z-index: 200; position: fixed; top: 237px;left: 0; width: 100%;" id="filter_box_3">
	<ul class="nav" id="order_select">
		<li><a class="checked" href="0" data-toggle="tab">综合排序</a></li>
		<li><a href="1" data-toggle="tab">距离由近到远</a></li>
		<li><a href="2" data-toggle="tab">价格由高到低</a></li>
		<li><a href="3" data-toggle="tab">价格由低到高</a></li>
		<li><a href="4" data-toggle="tab">人气由高到底</a></li>
	</ul>
</div>

<div class="nav_box" style="margin-top: 239px;">
<!-- food -->
<div id="box_1">

<!-- 
<div class="_body_list_item1">
	<img src="http://f.hiphotos.baidu.com/image/pic/item/42a98226cffc1e17e73f2bbb4890f603728de9bc.jpg" style="width: 100%;height: 240px" />
	<div style="font-size:22px; margin: 20px 0 15px 15px;">
		<span style="font-weight: bolder;">乌镇黄金水岸大酒店</span>&nbsp;<span style="font-size:16px; color:rgb(250,128,76);">[火锅]</span>
	</div>
	<div style="font-size:16px; line-height: 26px;  margin: 0 0 15px 15px;">
		电话：13957612455
		<br />
		地址：士大夫第三方就是93号
	</div>
	<a class="btn btn-lg btn-gouwu" style="width: 205px; position: absolute;right: 25px; bottom: 28px;" href='javascript:showGoToRoute("1679")'><i class="fa fa-level-up"></i>前往路径</a>
</div>

<div class="_body_list_item">
	<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>士大夫第三方渡水复士大夫第三方渡水复渡水渡水</td></tr></table>
	<div style="font-size:22px; margin: 15px 0 10px 0;">
		<span style="font-weight: bolder;">乌镇黄金水岸大酒店</span>&nbsp;<span style="font-size:16px; color:rgb(250,128,76);">[火锅]</span>
	</div>
	<div style="font-size:14px; line-height: 26px;  margin: 0 0 10px 0;">
		电话：13957612455
		<br />
		地址：士大夫第三方就是93号
	</div>
	<a class="btn btn-lg btn-gouwu" href='javascript:showGoToRoute("1679")'><i class="fa fa-level-up"></i>前往路径</a>
</div>
 -->

</div>
<!-- play -->
<div id="box_2">
</div>
<!-- shop -->
<div id="box_3">
</div>
<!-- hotel -->
<div id="box_4">
</div>
</div>
<div id="map_box">
<div id="map"></div>

<div class="btn-menu" id="map_menu">
	<a class="btn-1" val="002018"></a>
	<a class="btn-3" val="002016"></a>
	<a class="btn-2" val="002015"></a>
	<a class="btn-4" val="002017"></a>
</div>

<div class="tools">
	<!--<button class="tool map-path"><i></i>手选路线</button>
    --><button class="tool map-search" onclick="optionSearchBox(1)"><i></i>搜索</button>
    <button class="tool map-home" onclick="window.location.href='<%=basePath %>touch/index'"><i></i>首页</button>
</div>
<div class="tools-zoom">
    <div class="map-zoom">
		<div class="z-in" onclick="map.ZoomIn()"><i style="margin: 10px 8px;"></i></div>
		<div class="z-out" onclick="map.ZoomOut()"><i style="margin: 10px 8px;"></i></div>
	</div>
</div>
</div>
</div>

<div id="search_box" style="display: none;">
<header class="text-center">
	<div class="top-left"><a href="javascript:optionSearchBox(0)"><i class="fa fa-angle-left"></i></a></div>
	<div class="search-bar">
    	<div class="form-group">
			<form>
            	<input class="search" type="search" placeholder="桐乡市乌镇..." id="search_content" onkeyup="searchData()"><button type="button" class="btn btn-lg btn-search" onclick="toResults()">搜索</button>
            </form>
		</div>
    </div>
</header>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<ul class="slist nav" id="search_list"></ul>
		</div>
	</div>
    <div class="row">
    	<h3 class="section_header fancy">热点关键字</h3>
    </div>
    <div class="row">
		<div class="col-md-12 col-sm-12">
			<ul class="slist nav" id="hot">
			</ul>
		</div>
	</div>
</div>
</div>

</body>
</html>