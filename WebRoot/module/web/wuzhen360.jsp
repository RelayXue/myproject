<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>乌镇国际旅游区</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<iframe scrolling='no' frameborder='0' src='http://yibo.iyiyun.com/js/yibo404/key/7545' width='640' height='464' style='display:block;'></iframe>
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
	<script type="text/javascript" language="javascript"src="<%=basePath%>js/page_num.js"></script>
  </head>
  <script type="text/javascript">
  var pathh = '<%=basePath%>';
  if(window.parent.map!=null&&window.parent.markerL!=null){
	  window.parent.clearAll();
	 }
  var xys = [];
  //120.47844174962033 30.750092950815425,
	$(document).ready(function(){
		window.parent.zoomToExtent(xys.join(","));
		//createurl(eval("${totalPage}"),eval("${currentPage}"),0,"web/search_list",null);
	});
	
	function search(){
		var skey = $("#skey").val();
		var type='${type}';
		window.location.href="web/search_list?skey="+encodeURIComponent(encodeURIComponent(skey))+"&type="+type;
	}
	
	function select(name,x,y,url,show,index){
		var obj = {};
		obj.name=name;
		obj.x = x;
		obj.y = y;
		obj.width = 635;
		obj.height = 435;
		obj.index = index;
		var content = "<iframe id='countIframe' frameborder='0' style='height: 400px;z-index:4000; width: 600px; font-size: 12px; line-height: 20px;padding:10px;' src='"+pathh+"module/web/wuzhen360/"+url+"'></iframe>";
		window.parent.addMarker(obj,show,content);
		//window.parent.TJSoft.map.addMarker(obj,TJSoft.map.config.iconType.hnCheckIcon,false,false);
	}
</script>
  <body style="background-image: none;background-color: rgb(248,248,236)">
  <div style="overflow:hidden; ">
                        <s:if test="!newsList.isEmpty()">
		                <s:iterator var="s" value="newsList" status="status">
                    	<div style="width:182px; float:left; margin-left: 15px;margin-left: 10px; /* ie8-9 */margin-bottom:15px;cursor: pointer;" onclick="select('${s.fullname }','${s.fx }','${s.fy }','${s.img2}',true,<s:property value="#status.index+1"/>);"><img src="<%=basePath%>upload/L_${s.img1}" style="width:180px; height:120px; border:1px solid #ccc"/><div style="margin-top:3px; text-align:center">${s.fullname }</div></div>
                    	<script type="text/javascript">xys.push('${s.fx }'+' '+'${s.fy }');select('${s.fullname }','${s.fx }','${s.fy }','${s.img2}',false,<s:property value="#status.index+1"/>);</script>
                    	</s:iterator>
                    	</s:if>
                    </div>
  </body>
</html>
