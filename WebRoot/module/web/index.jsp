<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=11" />
<title>乌镇国际旅游区</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Resource/Theme/StyleDefault/style.css"/>  
<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TMap.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/jquery.autocomplete.css" />
<script type='text/javascript' src='<%=basePath%>module/web/js/jquery.autocomplete.min.js' charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/layer-v1.8.5/layer.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/jquery.cookie.js"></script>
    <script type="text/javascript">
    var pathh = '<%=basePath%>';
    var s_q = '${q}';
    var dragging = false;
    var isStart = true;
    var iY;
	var iOffsetY;
	var s = 0;
	var mi = 0;	//最小层级
	var ma = 2;	//最大层级
	var interval = 11;	//滚动间隔
	var is_full = false;	//表示是否全屏
	var is_open = true;	//表示是否有标签打开
	var is_road = false;
	var _left_menu_icos;
	var current_tab=0;
	var current_clear=false;
	var map_flag = false;
			$(function(){
				var mask_cookie = $.cookie('mask_cookie');
				if(mask_cookie==null){
					$("._mask").height($(document).height());
					$("._mask").show();
					$("._mask").click(function(){$(this).hide();});
					$.cookie('mask_cookie',"mask_cookie_value", { expires: 36500 });
				}
				
				$("._zoom_bar_line").height(interval * (ma+2));
				$("#_zoom_bar_btn").mousedown(function(e) {
					dragging = true;
					iY = e.clientY;
					iOffsetY = this.offsetTop;
					$("#v").html(iY+","+this.offsetTop);
					this.setCapture && this.setCapture();
					return false;
				});
				document.onmousemove = function(e) {
					if (dragging && s <= (ma*interval) && s >= (mi*interval)) {
						
						var e = e || window.event;
						var oY = e.clientY - iY + iOffsetY;
						setZoomBar(oY);
						return false;
					}
					
				};
				$(document).mouseup(function(e) {
					dragging = false;
					$("#v").html("-1");
				});
				init(true);
				//初始默认层级 
				setZoomBar(interval*1);
				
				openTab("._simple_content","._child_items","icon_open","icon_packup");
	
				//var q = '${q}';
				//if(q.length!=0){
				//	mouseover_show(q);
				//}
				
				//控制标签显示隐藏
				_left_menu_icos = $("._left_menu ._ico1, ._left_menu ._ico2, ._left_menu ._ico3, ._left_menu ._ico4, ._left_menu ._ico5, ._top_menu ._left ._ico1, ._top_menu ._left ._ico3, ._top_menu ._left ._ico5,._right_menu ._ico1, ._right_menu ._ico2, ._right_menu ._ico3");
				_left_menu_icos.click(function(){
					//if(!is_open) { is_open = true; return; }
					_left_menu_icos.children("#_ipage").hide();
					_left_menu_icos.removeClass("clicked");
					$(this).children("#_ipage").show();	
					$(this).addClass("clicked");
					is_open = true;
					
					$("#rkStartTxt").val("");
					$("#rkEndTxt").val("");
				});
				
				_left_menu_icos.children().each(function(){
					$(this).click(function(event){
						event.stopPropagation();
					});
				});
				
				var _close_btn = $("#_ipage #_close_btn");
				_close_btn.click(function(){
					//clearAll();
					//路况 点击关闭按钮，清除
					if($(this).attr("title")=="clear"){
						clearAll();
					}
					_left_menu_icos.children("#_ipage").hide();
					_left_menu_icos.removeClass("clicked");
					is_open = false;
				});

				fullView();
				
				initOpen();
			});
			
			function startBlur(){
				var val = $("#startHiddTxt").val();
				if(val.length==0){
					$("#startText").val("");
				}
			}
			
			function endBlur(){
				var val = $("#endHiddTxt").val();
				if(val.length==0){
					$("#endText").val("");
				}
			}
			
			function clearMapAndTag(){
				clearAll();
				_left_menu_icos.children("#_ipage").hide();
				_left_menu_icos.removeClass("clicked");
				is_open = false;
			}
			
			function showTagDetail(){
				$("._left_menu ._ico1").children("#_ipage").show();
				$("._left_menu ._ico1").addClass("clicked");
			}
	
		 //地图标签内容切换
		 function setMapTab(i){
			 $("._tab1, ._tab2, ._tab3").removeClass("_hover");
			 $("._tabc").hide();
			 $("._tab"+i).addClass("_hover");
			 $("#_tabc"+i).show();
		 }
		 
		 function openTab(title_class, child_class, def_css, hover_css){
			$(title_class).click(function(){
				var css = $(this).attr("class");
				if(css.indexOf(def_css) >= 0){
					$(this).next(child_class).eq(0).show(300);
					$(this).removeClass(def_css);
					$(this).addClass(hover_css);
				}else{
					$(this).next(child_class).eq(0).hide(300);
					$(this).removeClass(hover_css);
					$(this).addClass(def_css);
				}
			});
		 }
		 
		 function runZoomBarBtn2(i){
				var oY = parseInt($("#_zoom_bar_btn").css("top"));
				if(i == 1){
					oY += interval;
				}else if(i == 0){
					oY -= interval;
				}
				if (oY <= (ma*interval) && oY >= (mi*interval)) {
					if(oY < (mi*interval)) oY = (mi*interval);
					if(oY > (ma*interval)) oY = (ma*interval);
					if(oY % interval > 0) oY = parseInt(oY / interval) * interval;
					$("#_zoom_bar_btn").css({"top":oY + "px"});
					s = oY;
				}
			}

			function runZoomBarBtn(i){
				var oY = parseInt($("#_zoom_bar_btn").css("top"));
				if(i == 1){
					oY += interval;
				}else if(i == 0){
					oY -= interval;
				}
				setZoomBar(oY);
			}
			
			function setZoomBar(oY){
				if (oY <= (ma*interval) && oY >= (mi*interval)) {
					map_flag = true;
					if(oY < (mi*interval)) oY = (mi*interval);
					if(oY > (ma*interval)) oY = (ma*interval);
					if(oY % interval > 0) oY = parseInt(oY / interval) * interval;
					$("#_zoom_bar_btn").css({"top":oY + "px"});
					s = oY;
					zoomBarScrollListener(parseInt(oY / interval));
				}
			}
			
			function zoomBarScrollListener(level){
				if(map!=null){ 
					map.ZoomTo(ma-level);
				}
			}
			
			function addPoint(obj){
				addMarker(obj,false);
			}
			
			//综合查询
			function searchAll(){
				var skey = $("#searchText").val();
				window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
			}
			
			function fullView(){
				 var max_width;
				 var max_height;
				 if(!is_full){
					 max_width = 980;
					 max_height = 804;
					 $("body").css({"overflow-y":"scroll"});
					 $("._canvas").css({"width":max_width+"px", "height":max_height+"px", "margin":"0 auto", "position":"relative", "top":0, "left":0});
					 is_full = true;
				 }else{
					 $("body").css({"overflow":"hidden"});
					 max_width = $(window).width();
					 max_height = $(window).height();
					 
					 $("._canvas").css({"width":max_width+"px", "height":max_height+"px", "position":"absolute", "top":0, "left":0});
					 is_full = false;
				 }
				$("._top_menu").width(max_width-2*72);
				$("._left_menu").height(max_height-2*72);
				$("._right_menu").height(max_height-2*72);
				$("#bottom_bar").width(max_width-2*72);
				var t22 = map.GetCenter();
				$("#map").height(max_height);
				$("#map").width(max_width);
				map.SetCenter(t22);
				t22 = null;
			 }
			
			function redirectList(url){
				window.location.href = url;
			}
			
			function initOpen(){
				var o = false;
				//全屏
				o = ${oe.fullScreen};
				if(o){
					is_full = true;
					fullView();
				}
				//驾驶路线
				o = ${oe.car};
				if(o){
					current_tab = 10;
					$("#main10").attr("src","search_carLineInit?skey=${name}&xy=${xy}");
					$("._top_menu ._left ._ico3").click();
				}
			}
			
			function closeAllTab(){
				_left_menu_icos.children("#_ipage").hide();
				_left_menu_icos.removeClass("clicked");
				is_open = false;
			}
			
			function mouseover_show(type){
				var src = "",src2 = "",id="",data="",flag = false;
				if(current_tab==type){
					flag = true;
				}
				if(type==1){
					//美食
					src = $("#main1").attr("src");
					src2 = "search_typeList?type=002018";
					id = "main1";
				}else if(type==2){
					//住宿
					src = $("#main2").attr("src");
					src2 = "search_typeList?type=002017";
					id = "main2";
				}else if(type==3){
					//娱乐
					src = $("#main3").attr("src");
					src2 = "search_typeList?type=002016";
					id = "main3";
				}else if(type==4){
					//购物
					src = $("#main4").attr("src");
					src2 = "search_typeList?type=002015";
					id = "main4";
				}else if(type==5){
					//诚信商家
				}else if(type==6){
					//导览
					src = $("#main6").attr("src");
					src2 = "search_wuzhen360";
					id = "main6";
				}else if(type==7){
					//路况
				}else if(type==8){
					//分享
				}else if(type==9){
					//查找
					src = $("#main9").attr("src");
					src2 = "search_init";
					id = "main9";
				}else if(type==10){
					//驾驶路线
					src = $("#main10").attr("src");
					src2 = "search_carLineInit";
					id = "main10";
					current_tab = 10;
				}else if(type==11){
					//步行路线
					src = $("#main11").attr("src");
					src2 = "search_walkLineInit";
					id = "main11";
					current_tab = 11;
				}else if(type==12){
					//全屏
				}else if(type==13){
					//视野内搜索
				}else if(type==14){
					//娱乐
				}
				//if(id.length!=0&&src.length==0){
				//	$("#"+id).attr("src","");
				//	$("#"+id).attr("src",src2);
				//}
				if(!flag){
					clearAll();
					//第一次打开
					$("#"+id).attr("src","");
					$("#"+id).attr("src",src2);
				}
				current_tab=type;
			}
			
			function rightClick(type){
				if(type==1){
					//起点
					$("#rkStartTxt").val($("#rkXyTxt").val());
					rkSearchLine(1);
				}else if(type==2){
					//终点
					$("#rkEndTxt").val($("#rkXyTxt").val());
					rkSearchLine(2);
				}else if(type==3){
					//放大
					zoomIn();
				}else if(type==4){
					//缩小
					zoomOut();
				}else if(type==5){
					//居中
					$("#rkStartTxt").val("");
					$("#rkEndTxt").val("");
					clearAll();
				}
				$("#mapRK").css("display","none");
			}
			
			function searchView(type1,type2){
				
			}
			
			function searchCarLine1(type,name,x,y,url){
				var data = "skey="+name+"&xy="+x+","+y+"&type="+type;
				if(type==2){
					if($("#startText").val().length==0){
						showlayer('请输入起点！', 2,8);
						return;
					}
					data+="&xy1="+$("#startHiddTxt").val();
					data+="&name1="+$("#startText").val();
				}
				if(type==1){
					if($("#endText").val().length==0){
						showlayer('请输入终点！', 2,8);
						return;
					}
					data+="&xy1="+$("#endHiddTxt").val();
					data+="&name1="+$("#endText").val();
				}
				clearMapAndTag();
				if(is_full){
					fullView();
				}
				//乌镇管委会
				if(url=="walk"){
					$("#main11").attr("src","search_walkLineInit?"+data);
					current_tab = 11;
					$("._top_menu ._left ._ico5").click();
				}else{
					$("#main10").attr("src","search_carLineInit?"+data);
					current_tab = 10;
					$("._top_menu ._left ._ico3").click();
				}
			}
			
			function clickReturn(){
				return;
			}
			
			function open360(url){
				if(url.length==0){
					return;
				}
				var pageii = $.layer({
				    type: 1,
				    title: false,
				    area: ['900px', '500px'],
				    shift: 'left', //从左动画弹出
				    page: {
				        html: "<iframe frameborder='0' style='height: 500px;z-index:4000; width: 900px; font-size: 12px; line-height: 20px;' src='"+pathh+"module/web/wuzhen360/"+url+"'></iframe>"
				    }
				});
			}
			
			function show_detail(id,type){
				if(id.length==0||type.length==0){
					alert("参数传递出错，请刷新页面！");
					return;
				}
				window.open("search_showDetail?id="+id+"&type="+type);
			}
			
			function showlayer(msg,t,m){
				layer.msg(msg,t,m);
			}
			
    </script>
    <style type="text/css">
		#map {
			width: 100%;
			height: 100%;
			position: absolute;
			top: 0px;
			left: 0px;
			right: 0px;
			background-color: #F0EEE9;
			border: 0px solid black;
		}
   		._mask{ 
			display:none;
			background-color: rgba(0,0,0,0.4);  
			opacity:0.4 \9;  
			filter:alpha(opacity=40) \9;
			background-color:#000000 \9;
			display:none;
			position:absolute;
			top:0;
			left:0;
			width:100%;
			z-index:5999;
		}
		._mask ._guide{
			display:block;
			margin:0 auto;
			width:1004px;
			height:1020px;
			background-image:url(<%=basePath%>module/web/images/bg_remark.png);
			background-repeat:no-repeat;
			z-index:6000;
			position:relative;
		}

</style>
</head>

<body>
<!-- 遮罩 -->
<div class="_mask">
	<div class="_guide"></div>
</div>

<div class="_top">
	<div class="_grop">
    	<div class="_logo"></div>
        <div class="_box">
        	<div class="_name" style="cursor: pointer;" onclick="redirectList('index')"></div>
            <div class="_ms">
            	<div class="_search_box">
               		<input id="searchText" type="text" value=""/>
                    <div class="_search_btn" style="cursor:pointer;" onclick="searchAll();"></div>
                </div>
                <div class="_menu"><span onclick="redirectList('search_scenicList');" style="cursor: pointer;">景区</span>|<span onclick="redirectList('search_typeSearch?type=002018');" style="cursor: pointer;">餐饮</span>|<span onclick="redirectList('search_typeSearch?type=002017');" style="cursor: pointer;">住宿</span>|<span onclick="redirectList('search_typeSearch?type=002016');" style="cursor: pointer;">娱乐</span>|<span onclick="redirectList('search_typeSearch?type=002015');" style="cursor: pointer;">购物</span>|<span onclick="redirectList('search_trafficPlayList');" style="cursor: pointer;">交通播报</span>|<span onclick="redirectList('search_newsList');" style="cursor: pointer;">新鲜速递</span></div>
            </div>
            <div class="_weather">
            	<img src="<%=basePath%>module/weixin/images/weather/${bw.image}" width="65" height="50"/>
                <div class="_tt1">${bw.temperaturenow}℃ </div>
                <div class="_tt2">${bw.changes}</div>
            </div>
        </div>
    </div>
    <div class="_tllp"></div>
</div>
<div class="_body">
	<div class="_canvas">
	    <!-- 底部背景 -->
        <div class="" style=" width:72px; height:72px; top:0; left:0; background-image:url(<%=basePath%>module/web/images/border1_03.jpg); z-index:10; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; top:0; right:0; background-image:url(<%=basePath%>module/web/images/border1_07.jpg); z-index:10; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; bottom:0; left:0; background-image:url(<%=basePath%>module/web/images/border1_16.jpg); z-index:10; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; bottom:0; right:0; background-image:url(<%=basePath%>module/web/images/border1_18.jpg); z-index:10; position:absolute;"></div>
        <div class="" id="bottom_bar" style=" height:72px; bottom:0; left:72px; background-image:url(<%=basePath%>module/web/images/border1_17.jpg); z-index:10; position:absolute;"></div>
        <!-- end -->
    	<div class="_top_menu" style="z-index:10">
        	<div class="_left">
            	<a class="_ico1" style="cursor:pointer;" onclick="mouseover_show(9);">
                	<div id="_ipage" style="z-index:10">
                  		<!-- 2014-12-23 新增 -->
                		<div class="_tco"><div class="_tit">综合搜索</div><div class="_clo" id="_close_btn">X</div></div>
                    	<iframe id="main9" name="main9" style="width: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                    </div>
                </a>
                <a id="carline_window" class="_ico3" style="cursor:pointer;" onclick="mouseover_show(10);">
                <div id="_ipage" style="z-index:10;cursor:default;">
                   <div class="_tco"><div class="_tit">驾驶路线</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main10" name="main10" style="width: 100%;" src="search_carLineInit" border="0" frameBorder="0" scrolling="no"></iframe>
                </div></a>
                <a class="_ico5" style="cursor:pointer;" onclick="mouseover_show(11);">
                <div id="_ipage" style="z-index:10;cursor:default;">
                   <div class="_tco"><div class="_tit">步行路线</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main11" name="main11" style="width: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                </div>
                </a>
        	</div>
            <div class="_right" style="z-index:10;">
               <!-- 
            	<span class="_ico1" style="cursor:pointer;">视野内查询
                	<div id="_ipage" style="padding:0 8px;z-index:10;cursor:default;" >
                    	<div style=" border-bottom:solid #ccc 1px; padding-left:10px;" class="weight">在当前范围内搜索</div>
                        <div style=" border-bottom:solid #ccc 1px; background-position:12px center; padding:8px 8px 8px 45px;line-height: 20px;" class="font_blued icon_1" >
                           <span style="cursor: pointer;" onclick="searchView('2','002017001');">酒店</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">民宿</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017003');">其他</span>
                        </div>
                        <div style=" border-bottom:solid #ccc 1px; background-position:12px center; padding:8px 8px 8px 45px;line-height: 20px;" class="font_blued icon_2" >
                           <span style="cursor: pointer;" onclick="searchView('1',type2);">餐饮</span>
                        </div>
                        <div style=" border-bottom:solid #ccc 1px; background-position:12px center; padding:8px 8px 8px 45px;line-height: 20px;" class="font_blued icon_3" >
                           <span style="cursor: pointer;" onclick="searchView('2','002017002');">超市</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">土特产</span>
                        </div>
                        <div style=" border-bottom:solid #ccc 1px; background-position:12px center; padding:8px 8px 8px 45px;line-height: 20px;" class="font_blued icon_4" >
                           <span style="cursor: pointer;" onclick="searchView('2','002017002');">咖啡</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">酒吧</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">洗浴</span>
                           &nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">KTV</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="cursor: pointer;" onclick="searchView('2','002017002');">茶馆</span>
                        </div>
                        
                        <div style="margin:8px 0; overflow:hidden; border:1px solid #ccc; background-color:#FFFFFF;">
                        	<input type="text" placeholder="输入关键字" style=" font-size:18px; border:none; margin-top:5px;"/>
                        	<div style=" background-image:url(<%=basePath%>module/web/images/search_btn.png); width:36px; height:33px; " class="icon_no_repeat fright"></div>
                        </div>
                    </div>
                </span>
                 -->
                <span class="_ico2" style="cursor:pointer;" onclick="openMeasure();">测量</span>
            </div>
        </div>
        <div class="_left_menu" style="z-index:10;">
        	<span class="_ico1" style="cursor:pointer;" onclick="mouseover_show(1);">餐饮
        	  <!-- 2014-11-03 新增 -->
                <div id="_ipage" style="z-index:10;">
                   <div class="_tco"><div class="_tit">餐饮</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main1" name="main1" style="width: 100%;height: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                </div>
                <!-- end -->
        	</span>
            <span class="_ico2" style="cursor:pointer;" onclick="mouseover_show(2);">住宿
            <div id="_ipage" style="z-index:10;">
                   <div class="_tco"><div class="_tit">住宿</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main2" name="main2" style="width: 100%;height: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                </div>
            </span>
            <span class="_ico3" style="cursor:pointer;" onclick="mouseover_show(3);">娱乐
            <div id="_ipage" style="z-index:10;">
                   <div class="_tco"><div class="_tit">娱乐</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main3" name="main3" style="width: 100%;height: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                </div>
            </span>
            <span class="_ico4" style="cursor:pointer;" onclick="mouseover_show(4);">购物
            <div id="_ipage" style="z-index:10;">
                   <div class="_tco"><div class="_tit">购物</div><div class="_clo" id="_close_btn">X</div></div>
                   <iframe id="main4" name="main4" style="width: 100%;height: 100%;" src="" border="0" frameBorder="0" scrolling="no"></iframe>
                </div>
            </span>
        </div>
        <div class="_right_menu" style="z-index:5000">
        	<span class="_ico1" style="cursor:pointer;" onclick="mouseover_show(6);">导览
            	<!-- 2014-12-03新增 -->
                <div id="_ipage" style="text-align:left;z-index:10;cursor:default;">
                	<div class="_tco" style="margin-bottom:10px;"><div class="_tit">360°景点导览</div><div class="_clo" id="_close_btn">X</div></div>
                    <iframe id="main6" name="main6" style="width: 100%;height: 100%;" src="" border="0" frameBorder="0" scrolling="auto"></iframe>
                </div>
                <!-- end -->
            </span>
            <span class="_ico2" style="cursor:pointer;" onclick="showTrafficLine(is_road);">路况
            	<!-- 2014-12-03新增 -->
                <div id="_ipage" style="text-align:left;z-index:10;cursor:default;">
                	<div style="margin:10px;">
                	    <div class="_tco" style="margin-bottom:10px; padding-left:0;"><div class="_tit">路况</div><div class="_clo" id="_close_btn" title="clear">X</div></div>
                    	<span class="f_16 fleft" style="margin-right:15px; line-height:24px">实时路况</span>
                    	<!-- <div class="fleft" style=" background-color:#4B231B; color:#FFFFFF; padding:2px 8px; font-size:13px;cursor:pointer;">严重拥堵</div> -->
                        <div class="fleft" style=" background-color:#ff0000; color:#FFFFFF; padding:2px 8px; font-size:13px;cursor:pointer;">拥堵</div>
                        <div class="fleft" style=" background-color:#ffe13a; color:#FFFFFF; padding:2px 8px; font-size:13px;cursor:pointer;">缓慢</div>
                        <div class="fleft" style=" background-color:#00ff00; color:#FFFFFF; padding:2px 8px; font-size:13px;cursor:pointer;">畅通</div>
                        <div style="clear:both"></div>
                        <Br />
                        <div style="width:230px; background-image:url(<%=basePath%>module/web/images/18_08.png); background-repeat:no-repeat; background-position:95% center;cursor:pointer" class="fleft" onclick="showTrafficLine();">时间：${currentTime }</div>
                        <!-- <img src="<%=basePath%>module/web/images/18_05.png"  class="fright"/><img src="<%=basePath%>module/web/images/18_03.png" class="fright"/> -->
                    </div>
                </div>
                <!-- end -->
            </span>
            <span class="_ico3" style="cursor:pointer;" onclick="mouseover_show(8);">分享
            	<!-- 2014-12-03新增 -->
                <div id="_ipage" style="z-index:10;cursor:default;">
                	<div class="_tco" style="margin-bottom:10px; padding-left:10;"><div class="_tit">分享</div><div class="_clo" id="_close_btn">X</div></div>
                	<div style="margin:10px;">
                        <div style="display: inline-table;"><img src="<%=basePath%>images/phone_qcode.png" style="width: 106px; height:106px; "/><br /><div style="text-align: center;">在手机上查看地图</div></div>
                        <div style="display: inline-table; width: 50%;"><img src="<%=basePath%>images/qrcode_for_gh_40e986688102_430.jpg" style="width: 106px; height:106px; " /><br /><div style="text-align: center;">关注微信公众号</div></div>
                    </div>
                </div>
                <!-- end -->
            </span>
        </div>
        <div class="_zoom_bar" style="z-index:5000;">
			<div class="_zoom_bar_add_btn" onclick="runZoomBarBtn(0)" style="cursor:pointer;"></div>
            <div class="_zoom_bar_body" >
                <div style="position:relative;">
                    <div id="_zoom_bar_btn" class="_zoom_bar_btn" style="cursor:pointer;"></div>
                    <div class="_zoom_bar_line"></div>
                </div>
            </div>
            <div class="_zoom_bar_minus_btn" onclick="runZoomBarBtn(1)" style="cursor:pointer;"></div>
        </div>
        <!-- 2014-11-03 新增 -->
        <div style=" position:absolute; width:427px; height:306px; top: 100px; left:150px; display:none">
        	<div style="background:#f8f8ec; padding:8px; position:relative;">
            	<!-- 图片 -->
            	<div style="background-color:#666666; border-radius: 8px; overflow:hidden; width:200px; height:200px; float:left; "><img src="" /></div>
                <!-- 内容 -->
                <div style="width:198px; float:left; margin-left:12px; ">
                	<div style="border-bottom:1px solid #ccc; padding-bottom:8px;">
                    	<img src="<%=basePath%>module/web/images/m_icon5_05.png" style="float:right; margin-left:10px;"  />
                        <img src="<%=basePath%>module/web/images/m_icon5_03.png" style="float:right; margin-left:10px;" />
                    	<div style="float:right; height:30px; width:24px; background-image:url(<%=basePath%>module/web/images/m_icon5_08.png); background-repeat:no-repeat; background-position:center; "></div>
                        <div style="clear:both"></div>
                    </div>
                    <div style="padding-top:8px;">
                    	<div style="font-size:24px;">乌镇风景区</div>
                        <div style="margin-top:8px; font-size: 16px; color:#e5772d; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0px center; padding-left: 25px;">4.5  人均 ￥40~200</div>
                        <div style="margin-top:8px; font-size: 14px; color:#666; ">
                        	类型：景区<br />
                            电话：(057688731088)<br />
                            地址：浙江省嘉兴桐乡市乌镇石佛南路18号
                        </div>
                    </div>
                </div>
                <div style="clear:both"></div>
                <!-- 底部内容 
                <div class="_bom_tab_box">
                	<span class="_tab1 _hover" onclick="setMapTab(1);">去这里</span><span class="_tab2" onclick="setMapTab(2);">从这走</span><span class="_tab3" onclick="setMapTab(3);">找周边</span>
                    <div style="clear:both"></div>
                    <div id="_tabc1" class="_tabc">
                    	<div style="background-color:#64a489; border-radius: 4px; text-align:center; width:32px; height:32px; line-height:32px; color:#FFFFFF;float:left;">起</div>
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; margin-left:10px; float:left;"><input type="text" placeholder="输入关键字" /><div class="_search_btn"></div></div>
                        <div class="_ico1"></div>
                        <div class="_ico2"></div>
                        <div class="_ico3"></div>
                        <div style="clear:both"></div>
                    </div>
                    <div id="_tabc2" class="_tabc" style="display:none">
                    	<div style="background-color:#717699; border-radius: 4px; text-align:center; width:32px; height:32px; line-height:32px; color:#FFFFFF;float:left;">终</div>
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; margin-left:10px; float:left;"><input type="text" placeholder="输入关键字" /><div class="_search_btn"></div></div>
                        <div class="_ico1"></div>
                        <div class="_ico2"></div>
                        <div class="_ico3"></div>
                        <div style="clear:both"></div>
                    </div>
                    <div id="_tabc3" class="_tabc" style="display:none">
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; float:left;"><input type="text" placeholder="输入关键字" /><div class="_search_btn"></div></div> <span style="font-size:14px; color:#6a7685; line-height:32px; padding-left:10px;">美食&nbsp;&nbsp;服务&nbsp;&nbsp;住宿&nbsp;&nbsp;娱乐</span> 
                        <div style="clear:both"></div>
                    </div>
                </div>
                -->
            </div>
        </div>
        <!-- end -->
        <div style="background-color: #F0EEE9; overflow: hidden; z-index: 1;" id="map" oncontextmenu="return false;">
          <div id="mapRK" class="f_14 _right_key_menu" style="position:absolute;z-index:1000;display:none">
	<div style="cursor: pointer;" class="icon_right_click_1" onclick="rightClick(1);">设为起点</div>
    <div style="cursor: pointer;" class="icon_right_click_2" onclick="rightClick(2);">设为终点</div>
    <div style="cursor: pointer;" class="icon_right_click_5" onclick="rightClick(3);">放大</div>
    <div style="cursor: pointer;" class="icon_right_click_6" onclick="rightClick(4);">缩小</div>
    <div style="cursor: pointer;" class="icon_right_click_7" onclick="rightClick(5);">清除</div>
    <input type="hidden" id="rkStartTxt"/>
    <input type="hidden" id="rkEndTxt"/>
    <input type="hidden" id="rkXyTxt"/>
</div>
        </div>
    	<div class="_close" onclick="fullView()" style="cursor:pointer;z-index:10;"></div>
    </div>
</div>
<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>
</body>
</html>