<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type");
String id=request.getParameter("id");
%>

<!DOCTYPE html>
<html>
  <head> 
    <base href="<%=basePath%>">
    
    <title></title>
        <meta content="ie=7" http-equiv="x-ua-compatible">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>Resource/Theme/StyleDefault/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
        #map
        {
            width: 98%;
            height: 470px;
            position:absolute;
            top: 0px;
            left: 0px;
            background-color: #f3f5ed;
            border: 1px solid black;
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
   		 	var mm=null;
			$(document).ready(function() { 
				Init(3);
				var type='<%=type%>';
				//视频
				if(type=='video'){
					Point();
					var x=window.parent.document.getElementById("fx").value;
					var y=window.parent.document.getElementById("fy").value;
					show_point(x,y);
				}
				//景点
				else if(type=='attractions'){
					Point();
					var x=window.parent.document.getElementById("fx").value;
					var y=window.parent.document.getElementById("fy").value;
					show_point(x,y);
				}else if(type=='Personnel'){
					var fx=window.parent.document.getElementById("fx").value;
					var fy=window.parent.document.getElementById("fy").value; 
					show_point(fx,fy);
					
					/*var fx=window.parent.document.getElementById("fx").value;
					var fy=window.parent.document.getElementById("fy").value; 
					alert("x的值是："+fx);
					/*var ll = JZbConvert.GetGpsToSwLonLat(new SPoint(x, y));
					var fx=ll.GetLon();
					var fy=ll.GetLat(); 
					show_point(fx,fy);*/
				//线路
				}else if(type=='navigation'){
					var xy=window.parent.document.getElementById("fxy").value;
					showLines(xy);
					showAttractions();
					drawLines();
				}else{
					AddMapTool();
			 		window.parent.showAre();
				}
			 	
			 	});
   		 	
   		 	 function AddMapTool(){
      	 	      
		   	featureStyle = new SFeatureStyle();
			featureStyle.SetFillColor(null);
			featureStyle.SetStrokeColor("#ECC844");
			featureStyle.SetStrokeWidth(0);
			featureStyle.SetFillOpacity(0.5);
			
			featureStyle.SetHoverEnabled(false);
			featureStyle.SetHoverFillOpacity(0.9);
			featureStyle.SetHoverFillColor(null);
			featureStyle.SetHoverStrokeColor("#ECC844");
			featureStyle.SetHoverStrokeWidth(1);
			featureStyle.SetHoverFillOpacity(0.8);
		 
			// 生成绘制多边形事件 PolygonListener
			drawPolygon = new SDrawControl(vectorLayer, SHPolygon, {
				Complete : PolygonListener
			}, featureStyle);
			// 地图增加多边形控件
			map.AddControl(drawPolygon);
			// 给多边形按钮btnPolygon添加点击事件
			window.parent.document.getElementById("draw").onclick = function() {
				// 如果多边形控件可用的话
				if (drawPolygon.GetActive() == true) {
					// 多边形控件失效
					drawPolygon.DeActivate();
				} else {
					drawPolygon.Activate();
				}
			}; 
			  var tt = window.parent.document.getElementById("btnMove");
				 tt.__VV = drawPolygon;
				tt.onclick = function() {
			       tt.__VV.DeActivate();
			      }
		     }
		      
		      
		      
		      // 绘制多边形事件
		function PolygonListener(geo) {
			drawPolygon.DeActivate();
			$("#fxy" , parent.document).attr("value",geo.GetPointsString()); 
		}
		    //显示区域  
		 function AreaShow(fxy) {
            //坐标串
            var l_rcgs2_1 = fxy;
            var geo = new SLinearRing(GetPointS(l_rcgs2_1));
            
            featureStyle = new SFeatureStyle();
			featureStyle.SetFillColor(null);
			featureStyle.SetStrokeColor("#ECC844");
			featureStyle.SetStrokeWidth(0);
			featureStyle.SetFillOpacity(0.5);
			
			featureStyle.SetHoverEnabled(false);
			featureStyle.SetHoverFillOpacity(0.9);
			featureStyle.SetHoverFillColor(null);
			featureStyle.SetHoverStrokeColor("#ECC844");
			featureStyle.SetHoverStrokeWidth(1);
			featureStyle.SetHoverFillOpacity(0.8);
            var pf = new SFeature(geo, featureStyle);
		    var cp = pf.GetGeometry().GetComponents();
		    if(cp.length>0){
			    var point = cp[0];  }
		    map.SetCenter(new SLonLat(point.GetX(),point.GetY()), 1); 
		    
            vectorLayer.AddFeatures([pf]);
        }   
        //处理数据的方法
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
        
        // 描点----
function Point() {
	drawPoint = new SDrawControl(vectorLayer, SHPoint, {
		Complete : RePoints
	}, null, null, "_rrctV"); 
	map.AddControl(drawPoint);
	window.parent.document.getElementById("btnPoint").onclick = function() {
		if (drawPoint.GetActive() == true) {
			drawPoint.DeActivate();
			window.parent.document.getElementById("btnPoint").value = "开启描点";
		} else {
			vectorLayer.ClearFeatures();
			drawPoint.Activate();
			window.parent.document.getElementById("btnPoint").value = "取消描点";
		}
	};
	
}

function RePoints(geo) {
	vectorLayer.RemoveFeaturesByTag("_rrctV");
	// document.getElementById("txtXY").value = geo.GetX() + " " + geo.GetY();
	window.parent.document.getElementById("fx").value = geo.GetX();
	window.parent.document.getElementById("fy").value = geo.GetY();
	window.parent.document.getElementById("btnPoint").value = "开启描点";
	drawPoint.DeActivate();
	if (mm != null) {
		mm.MoveToWithLonLat(new SLonLat(geo.GetX(), geo.GetY()));
	}

};

//显示点
function show_point(fx,fy) {
	if (fx != "null" && fx.length > 0) {
		var icon2 = new SIcon("/images/icon_marka.png", new SSize(30, 31));
		icon2.SetOpacity(0.2);
		mm = new SMarker(new SLonLat(fx, fy), icon2, "tj");
		markerL.AddMarker(mm);
		   map.SetCenter(new SLonLat(fx, fy), 5);
		mm.GetEVTReference().AddEventListener("mouseover", null, function() {
		});
	}

}
//画线
 var drawLine = null;
    function drawLines() {
    	drawLine = new SDrawControl(vectorLayer, SHPath, { Complete: LineListener }, featureStyle2, "_rrLine");
	    map.AddControl(drawLine);
	    var draw=window.parent.document.getElementById("btnPoint");
	    draw.onclick = function () {
	       if (drawLine.GetActive() == true) {
	            drawLine.DeActivate();
	        } else {
	        	 vectorLayer.ClearFeatures(); 
	             drawLine.Activate();
	         } 
	    };
	     var move=window.parent.document.getElementById("btnMove");
	    move.onclick = function () {
	       
	            drawLine.DeActivate();
	       
	    };
    }
    function LineListener(geo){ 
    	 drawLine.DeActivate();
         geo.GetComponentsString();
         window.parent.document.getElementById("fxy").value = geo.GetComponentsString();
    }
    //显示线
    function showLines(xy){
  	    var l_rcgs2_1 =xy;
		var geo = new SLineString(GetPointS(l_rcgs2_1));
		 var ppS1 = SPoint.GetPointSFromString(l_rcgs2_1);
		var xL = new SLineString(ppS1);
		 var featureStyle4 = new SFeatureStyle();
        featureStyle4.SetFillColor("none");
        featureStyle4.SetHoverFillColor("none");
        featureStyle4.SetStrokeColor("red");
        featureStyle4.SetStrokeStartArrow("Oval");
        featureStyle4.SetStrokeWidth(3);
        featureStyle4.SetStrokeEndArrow("Classic");
        featureStyle4.SetStrokeDashStyle("SHORTDASH");
        //featureStyle2.SetStrokeArrowLength(5);
        //featureStyle2.SetStrokeArrowWidth(5);
        featureStyle4.SetHoverEnabled(true);
        featureStyle4.SetHoverStrokeWidth(4);
	    var pf = new SFeature(xL, featureStyle4,"ax");
		 vectorLayer.AddFeatures([pf]); 
		 pf.Activate();
    }
    //显示所有景点
    function showAttractions(){
    		$.ajax({
			    url: "buAttractions!showAll?navigationId=<%=id%>&time=" + new Date(),
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "json",
			    success: function(data) {
			    	Nav_point(data);
			    }
			});
    }
    //路线景点
    function Nav_point(data){
    	removeRy();
    	var navigationid='<%=id%>';
    	if(data!=null&&data.length>0){
    		for(var a=0;a<data.length;a++){
    			var icon2 = new SIcon("/images/icon_marka.png", new SSize(30, 31));
				if(data[a].voice==navigationid){
					icon2 = new SIcon("/images/map_marker_03.png", new SSize(32, 32));
				}    		
				icon2.SetOpacity(0.2);
				var ll = new SLonLat(data[a].fx, data[a].fy);
				mm = new SMarker(ll, icon2, "tj");
				mm.__text=getDivAttractions(data[a]);
				mm._ll = ll;
				markerL.AddMarker(mm);
				mm.AddEventListener("click", mm, function() {
				 map.SInfoWindow.SetLonLat(this._ll);
				 map.SInfoWindow.SetInnerHTML(this.__text);
				 map.SInfoWindow.SetSize(new SSize(250, 160));
				 map.SInfoWindow.Show();
				});
    		}
    	}
    }
    function getDivAttractions(data){
    	var txtHtml="";
		txtHtml+="<div class='in_pbox'>";
		if(data.prstatus=='scenic'){
			txtHtml+=" <div class='ptit ff'>景区名称："+data.fullname+"</div>";
		}else{
			txtHtml+=" <div class='ptit ff'>景点名称："+data.fullname+"</div>";
		}
		txtHtml+=" <div>关联路线：不关联<input type='radio' name='gl' checked='checked' value='0'/>关联<input type='radio' value='1' name='gl'  /></div>";
		txtHtml+="<br/>";
		txtHtml+=" <div align='center'><input type='button' value='确定' onclick=contactNa(\'"+data.fuid+"\','"+data.prstatus+"') /></div>";
		txtHtml+=" </div>";
		return txtHtml;
    }
    //关联线路
    function contactNa(attId,type){
    	var tt=$("input[name=gl]:checked").val();
    	$.ajax({
			    url: "buAttractions!cantactAtt?type="+type+"&check="+tt+"&attractionsId="+attId+"&navigationId=<%=id%>&time=" + new Date(),
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "json",
			    success: function(data) {
			    	alert("操作成功");
			    	Nav_point(data);
			    }
			});
    }
   		 </script>
   		 
   		 
  </head>
  
  <body>
    <div  id="map"></div>  
  </body>
</html>
