<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String st=request.getParameter("st");
%>

<!DOCTYPE html>  
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>百度导航</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">  
 body, html,#l-map {width: 100%;height: 100%;overflow: hidden;hidden;margin:0;}   
 </style> 
 
 <script src="http://api.map.baidu.com/api?type=quick&ak=mAiri7rBdiVGxmp95tDudHfP&v=1.0" type="text/javascript"></script> 
 

  </head>
  
 <body>  
<div id="l-map"></div>  

  <script type="text/javascript"><%--
	var x;
  	var y;
      if (window.navigator.geolocation) {
        var options = {
            enableHighAccuracy: true
        };
        window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
    } else {
        alert("浏览器不支持html5来获取地理位置信息");
    }
      //获取到位置回调
      function handleSuccess(position){
          // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
          x = position.coords.longitude;
          y = position.coords.latitude;
          alert(x);
          initMap();
         
      }
      //获取失败回调 
      function handleError(error){
    	  alert("暂无法获取当前位置");
      }
    //调用百度
      function initMap(){ 
    	  var map = new BMap.Map("l-map"); 
          var routeSearch=new BMap.RouteSearch();  
          var myGeo = new BMap.Geocoder();      
          var start = {  
        	  latlng:new BMap.Point(x, y),  
        	  name:""  
        	}  
           var end = {  
        			 latlng:null,  
        	      	 name:"乌镇"  
        	    }  
        	  var opts = {  
        	       mode:BMAP_MODE_DRIVING,//公交、驾车、导航均修改该参数  
        	       region:"桐乡市"  
        	    }  
        	  var ss = new BMap.RouteSearch();  
        	  routeSearch.routeCall(start,end,opts);
    	} 
    
  --%></script>
  
  
  <script type="text/javascript">
  var map = new BMap.Map("l-map");      
  map.centerAndZoom(new BMap.Point(116.404, 39.915), 14);      
  var routeSearch=new BMap.RouteSearch();  
  var start = {  
        latlng:null,  
        name:"<%=st%>"  
    }  
  var end = {  
       latlng:null,  
       name:"乌镇"  
    }  
  var opts = {  
       mode:BMAP_MODE_DRIVING,//公交、驾车、导航均修改该参数  
       region:"桐乡市"  
    }  
  var ss = new BMap.RouteSearch();  
  routeSearch.routeCall(start,end,opts);
  </script>
</body>
</html>  
