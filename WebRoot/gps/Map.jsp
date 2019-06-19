<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
		    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/map_label.css">
		<style type="text/css">
        #map
        {
            width: 100%;
            height: 100%;
            position: relative;
            top: 22px;
            left: 0px;
            background-color: #f3f5ed;
        }	
        body
        {
        	margin:0 0 0 0;
        	padding: 0 0 0 0;
    </style>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		 <script src="<%=basePath %>js/GPS/map.js" type="text/javascript"></script>
    	<script src="<%=basePath %>js/GPS/config.js" type="text/javascript"></script>
   		 <script src="<%=basePath %>js/GPS/map_common.js" type="text/javascript"></script>
   		 <script type="text/javascript">
   		 	var drawPolygon = null;
			$(document).ready(function() {
				Init(3);
			 	});
   		 	
  //景点显示
function attractionsShow(){ 
	var jd=$("#jd");
	if(jd.attr("checked")){
		var data=eval('${Attractions_data}'); 
		if(data!=null&&data.length>0){
			for(var a=0;a<data.length;a++){
				var zb_x=data[a].fx; 
				var zb_y=data[a].fy;
				var fuid=data[a].fuid;
				var name=data[a].fullname;
				var icon2 = new SIcon("/images/gps/point.png", new SSize(18, 27),
						new SPixel(-9, -9));
				var maker2 = new SMarker(new SLonLat(zb_x, zb_y), icon2, "jd");
				var ll = new SLonLat(zb_x, zb_y);
				var Ssize = new SSize(30, 30);
				maker2._fuid = fuid;
				maker2._ll=ll;
				maker2.__text=getDivMd(data[a]);
				markerL.AddMarker(maker2);
				var lbl = new SLabel(null, ll, null, name);
		        lbl.SetOpacity(0.8);
		        lbl.SetOffset(new SSize(10, -7));
		        lbl.SetAdaptive();
		        lbl.Hide();
		        map.AddLabel(lbl);
				maker2._lbl = lbl;
				maker2.AddEventListener("click", maker2, function() {
					map.SInfoWindow.SetLonLat(this._ll);
					map.SInfoWindow.SetInnerHTML(this.__text);
					map.SInfoWindow.SetSize(new SSize(370, 230));
					map.SInfoWindow.Show();
				});
				maker2.AddEventListener("mouseover", maker2, function() { 
					  this._lbl.Show();
				});
				maker2.AddEventListener("mouseout", maker2, function() {  
					  this._lbl.Hide();
				});
			}
		}
	}else{
		markerL.HideMarkersByTag("jd");
	}
	
}
		      
		  //景点
function getDivMd(data){
	var textHtml = "";
	textHtml += "<div id='label_box'>";
	textHtml += "<div class='title_txt'><a>" + data.fullname + "</a><br/><span class='close'></span></div>";
	textHtml += "<div class='hr'></div>";
	textHtml += "<div class='content_txt'>";
	textHtml += "<div class='itm'><span>门票:</span>" + (data.tickets!=undefined?data.tickets:"") + "</div>";
	textHtml += "<div class='itm'><span>地址:</span>" + (data.address!=undefined?data.address:"") + "</div>";
	textHtml += "</div>";
	textHtml += "<div class='hr'></div>"; 
	
	textHtml += "<div style='clear: both;' mce_style='clear: both;'></div>";
	textHtml += "</div>";
	return textHtml;
}    
	 
   		 </script>
   		 
   		 
  </head>
  
  <body>
    <div id="map"></div>
    <div style="position:absolute ;z-index: 1000;padding-right: 30px;top: 10px">	<input type="checkbox" id="jd" value="" onclick="attractionsShow()" />景点 </div>
  </body>
</html>
