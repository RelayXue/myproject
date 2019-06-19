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
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/traffic-guide.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/k_tables.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script src="<%=curl %>js/common_function.js"></script>
<script src="<%=basePath %>js/GPS/config.js"></script>
<title>乌镇-路线查询</title>
<script type="text/javascript">

var end_point_id = "<%=request.getParameter("end_point_id")%>";
var end_point_name = "<%=request.getParameter("end_point_name")%>";

	function searchPoint(key, input_id){
		var parms = new Object();
		parms.key = key;
		$.post("phone_main_mapSearch", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			if (code == 1) {
				var o = $.parseJSON(json.o);
				var html = '';
				for(i=0;i<o.length;i++){
					html += '<li id="vll" onclick=setVal("'+input_id+'","'+o[i].fullname+'","'+o[i].fuid+'") '+((i == o.length-1)?'style="border-bottom:none"':'')+'><p class="p1"></p><p class="p2"><span>'+cutString(o[i].fullname, 22)+'</span></p><p class="p3"></p></li>';
				}
				$("#search_list").html(html);
			}else{
				$("#search_list").html('<li class="nob"><a href="javascript:void(0)">没有相关内容</a></li>');
			}
		}, "json");
		
	}
	
	$(function(){
		/*
		var start_save = localStorage.getItem("query_start_point");
		var end_save = localStorage.getItem("query_end_point");
		
		if(start_save != null){
			$("#start_point").val(start_save.split(",")[0]);
			$("#start_point_id").val(start_save.split(",")[1]);
		}
		*/
		if(end_point_id != null && end_point_id !=  "null"){
			$("#end_point").val(end_point_name);
			$("#end_point_id").val(end_point_id);
		}/*else if(end_save != null){
			$("#end_point").val(end_save.split(",")[0]);
			$("#end_point_id").val(end_save.split(",")[1]);
		}*/
		
		
		$("#nav3").click();

		$("#start_point").bind("input", function(){
			var v = $.trim($(this).val());
			if(v.length == 0){
				$("#search_list").html("");
			}else{
				//$(this).val(v);
				searchPoint(v, 'start_point');
			}
		});
		$("#end_point").bind("input", function(){
			var v = $.trim($(this).val());
			if(v.length == 0){
				$("#search_list").html("");
			}else{
				//$(this).val(v);
				searchPoint(v, 'end_point');
			}
		});
	});
	
	function setVal(id, val, data_id){
		$("#"+id).val(val);
		$("#"+id+"_id").val(data_id);
		localStorage.setItem("query_"+id, val+","+data_id);
		$("#vll").css("display","none");
		//$("#"+id).focus();
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
	
	function replace(){
		var a = $("#start_point").val();
		var a_data = $("#start_point_id").val();
		var b = $("#end_point").val();
		var b_data = $("#end_point_id").val();
		$("#start_point").val(b);
		$("#start_point_id").val(b_data);
		$("#end_point").val(a);
		$("#end_point_id").val(a_data);
	}
	
	
	function search(){
	try {
		//$("#search_btn").focus();
		var a = $("#start_point").val();
		a=a=='我的位置'?"":a;
		var b = $("#end_point").val();
		if(a.length == 0) $("#start_point_id").val("");
		if(b.length == 0) $("#end_point_id").val("");
		if(a.length==0&&(cur_x == 0 || cur_y == 0)){
			dialog("无法获取当前位置，请输入起点");
		}else if(a.length==0&&!(MAP_AREA_TOP < cur_x && cur_x < MAP_AREA_BOTTOM && cur_y > MAP_AREA_LEFT && cur_y < MAP_AREA_RIGHT)){
        	dialog("未在乌镇范围内, 请手动输入起点");
        }else if(b.length == 0){
			dialog("请输入终点");
		}else{
			var parms = new Object();
			if(a.length == 0){
				parms.pos_x = cur_x;
				parms.pos_y = cur_y;
			}else{
				parms.pos_x = 0;
				parms.pos_y = 0;
			}
			parms.start_point = $("#start_point").val();
			parms.end_point = $("#end_point").val();
			parms.start_point_id = $("#start_point_id").val();
			parms.end_point_id = $("#end_point_id").val();
			$.post("phone_main_queryRouteMes", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				dialog(hint);
				if (code == 1) {
					var o = json.o;
					var query_history = localStorage.getItem("query_history");
					if(query_history == null){
						query_history = b+","+$("#end_point").val();
					}else{
						query_history += ";"+b+","+$("#end_point").val();
					}
					localStorage.setItem("query_history", query_history); 
					localStorage.setItem("route_detail_json", JSON.stringify(o)); 
					//window.location.href = "<%=curl%>route_detail.jsp";
					window.location.href = "<%=curl %>index.jsp?route=1";
				}
			}, "json");
		}
	} catch (e) {alert(e);	}
	}
	
	var cur_x = 0;
	var cur_y = 0;
	
    if (window.navigator.geolocation) {
        var options = {
            enableHighAccuracy: true
        };
        window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
    } else {
        alert("浏览器不支持html5来获取地理位置信息");
    }
    
    function handleSuccess(position){
        // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
        cur_x = position.coords.longitude;
        cur_y = position.coords.latitude;
        if(MAP_AREA_TOP < cur_x && cur_x < MAP_AREA_BOTTOM && cur_y > MAP_AREA_LEFT	&& cur_y < MAP_AREA_RIGHT){
        	$("#start_point").attr("placeholder","当前位置");
        }else{
        	$("#start_point").attr("placeholder","请输入起点");
        }
    }
    
    function handleError(error){
    	$("#start_point").attr("placeholder","请输入起点");
    }
    

</script>
<style type="text/css">
#list li{width: 33% !important;}

.button { 
display: inline-block; 
zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ 
*display: inline; 
vertical-align: baseline; 
margin: 0 2px; 
outline: none; 
cursor: pointer; 
text-align: center; 
text-decoration: none; 
font: 14px/100% Arial, Helvetica, sans-serif; 
padding: .5em 2em .55em; 
text-shadow: 0 1px 1px rgba(0,0,0,.3); 
-webkit-border-radius: .5em; 
-moz-border-radius: .5em; 
border-radius: .5em; 
-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2); 
-moz-box-shadow: 0 1px 2px rgba(0,0,0,.2); 
box-shadow: 0 1px 2px rgba(0,0,0,.2); 
} 
.button:hover { 
text-decoration: none; 
} 
.button:active { 
position: relative; 
top: 1px; 
} 

/* blue */ 
.blue { 
color: #d9eef7; 
border: solid 1px #0076a3; 
background: #0095cd; 
background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5)); 
background: -moz-linear-gradient(top, #00adee, #0078a5); 
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5'); 
} 
.blue:hover { 
background: #007ead; 
background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e)); 
background: -moz-linear-gradient(top, #0095cc, #00678e); 
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc', endColorstr='#00678e'); 
} 
.blue:active { 
color: #80bed6; 
background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee)); 
background: -moz-linear-gradient(top, #0078a5, #00adee); 
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5', endColorstr='#00adee'); 
} 
</style>
</head>

<body>
	<div id="container" style="background-color:#d8d6d3">
    	<div id="header">
        	<ul>
            	<li id="nav1"><a href="javascript:toBack()"></a></li>
                <li id="nav3"><a href="javascript:void(0)"></a></li>
                <li id="nav4"><a href="javascript:void(0)"></a></li>
                <li id="nav5"  id="search_btn" onclick="search()" ><a style="color: #000; background-image: none; line-height: 50px; background-color: #ccc;">查询</a></li>
            </ul>
        </div>
        <script type="text/javascript">
			var li=document.getElementById("header").getElementsByTagName("li");
			function bg(index){
				for(var j=3;j<5;j++){
					document.getElementById("nav"+j).style.background="#fff";
					document.getElementById("nav"+j).getElementsByTagName("a")[0].style.background="url(images/nav"+j+".png) center center no-repeat";			
				}
				document.getElementById("nav"+index).getElementsByTagName("a")[0].style.background="url(images/nav"+index+"_2.png) center center no-repeat";	
				if(index==2){
					document.getElementById("nav"+index).style.background="#ce7665";
				}else if(index==3){
					document.getElementById("nav"+index).style.background="#2e3c50";
				}else{
					document.getElementById("nav"+index).style.background="#58ac5e";
				}
			}
			for(var i=3;i<5;i++){
				document.getElementById("nav"+i).i=i;
				document.getElementById("nav"+i).onclick=function (){
					bg(this.i);	
				};
			}
        </script>
        <div id="wz">
        	<div id="cont">
            	<div id="content">
                	<div class="left"><a href="javascript:replace()"></a></div>
                    <div class="right">
                    	<div class="qd">
                            <p class="f_l">
                                <span class="s1">起</span>
                                <span class="s2"><input type="text" onclick="this.value=''" placeholder="请输入起点"  id="start_point" value="我的位置" /><input type="hidden" id="start_point_id" /></span>
                            </p>
                            <p class="f_r"><img src="images/jt7.jpg"></p>
                        </div>
                        <div class="zd nob">
                            <p class="f_l">
                                <span class="s1">终</span>
                                <span class="s2"><input type="text" placeholder="请输入目的地" id="end_point" /><input type="hidden" id="end_point_id" /></span>
                            </p>
                            <p class="f_r"><img src="images/jt8.jpg"></p>
                        </div>
                    </div><br>  
                    <div  style="width:100%;display: block;float: left;text-align:center;line-height: 40px " ><input onclick="search()" style="width:  50%;" class="button blue" type="button" value="查询">  </div>
                </div>
            </div>
        </div>

<div style="background-color:#ffffff;width:100%; z-index:2;position:absolute;bottom:0px; ">       
        <div id="hover">
        	<div style="margin:50px 0px 0px 0px">
            	<ul id="search_list">
                    <!--<li class="nob"><a href="#"><img src="images/ljt.jpg">清空历史记录</a></li> -->
               </ul>
            </div>
        </div>
    
        <div id="qhb">一起去嗨吧</div>
        <div id="list">
        	<ul class="bor" >
            	<li style="border-right: none;" onclick="window.location.href='<%=curl%>search_list.jsp?category=002016006&title=茶馆'">
                	<span><img src="images/qhc.png"></span>
                    <span>去喝茶</span>
                </li>
                <li style="border-right: none;" onclick="window.location.href='<%=curl%>search_list.jsp?category=002015&title=购物'">
                	<span><img src="images/qkg.png"></span>
                    <span>去购物</span>
                </li>
                <li style="border-right: none;" onclick="window.location.href='<%=curl%>search_list.jsp?category=002016002&title=酒吧'">
                	<span><img src="images/qhj.png"></span>
                    <span>去喝酒</span>
                </li><!--
                <li class="nob" onclick="window.location.href='<%=curl%>search_list.jsp?category=002007&title=书报'">
                	<span><img src="images/qks.png"></span>
                    <span>去看书</span>
                </li>
            --></ul>
        </div>
  
</div>         
    </div>
</body>
</html>
