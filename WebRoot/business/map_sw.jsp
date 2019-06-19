<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type"); 
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html>
  <head>
    <base href="<%=basePath%>" />
    <title></title>
	<link href="css/layout.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
	<link href="css/virtual.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.timers.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/map_common.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/JZbConvert.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/business.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	 jQuery(document).ready(function($) {
	 	navAuto();
		$(window).bind("resize", navAuto);
		 Init();
		 var type='<%=type%>';
		 if(type=='video'){
		 	VideoData();
		 }
		 if(type=='light'){
		 	LightData();
		 }
		 //显示搜索页
		 if(type=='sw'){
			 $("#dd").css("left",$(document.body).width()-400);
		 	 $('#dd').window({
          	 }).window('open');
		 }
		 if(type=='other'){
			 $("#other").css("left",$(document.body).width()-400);
		 	 $('#other').window({
          	 }).window('open');
		 }
	 });
	 
	 function navAuto(){
	 	 var h=$(window).height();
	 	  $("#dd").css("left",$(document.body).width()-350);
		 $("#map").css("height",h); 
	 } 
	 
	 
	</script>
	

 <style type="text/css"><!--

/* .v_logo img{ filter: Alpha(Opacity=70, FinishOpacity=0, Style=1, StartX=0, StartY=0, FinishX=100, FinishY=100)} */
.v_top{filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5; background-color: #edf9fe}


 
       #map
        {
            width: 100%;
            height: 100%;
            position:absolute;
            left: 0px;
            top: 0px; 
            background-color: #eaedf2;
        }
 


</style>
  </head>
  
  <body>
<div id="map" style="width: 100%;">
</div>
<div  style="display: none;" >
				<div id="dd" title="景点"   style="top: 10px;height: 490px;z-index: 1000;width: 300px ">
					<iframe id="ifr" width="100%" height="1000px" src="buAttractions!showAttr" scrolling="no" frameborder="0"></iframe>
				</div>
				<div id="vv" title="实时视频播放"   style="top: 10px;height: 600px;z-index: 1000;width: 900px ">
					<iframe id="ifrVv" width="100%" height="562px" src="" scrolling="no" frameborder="0"></iframe>
				</div>
				<div id="other" title="设施"   style="top: 10px;height: 500px;z-index: 1000;width: 300px ">
					<iframe id="ifrOther" width="100%" height="450px" src="buInterest!showInter" scrolling="no" frameborder="0"></iframe>
				</div>
			</div>
</body>
</html>
