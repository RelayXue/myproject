<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<script type="text/javascript" language="javascript"src="<%=basePath%>module/web/js/page_num.js"></script>
  </head>
  <script type="text/javascript">
  if(window.parent.map!=null&&window.parent.markerL!=null){
	  window.parent.clearAll();
	 }
  
  var xys = [];
	$(document).ready(function(){
		window.parent.zoomToExtent(xys.join(","));
		createurl(eval("${totalPage}"),eval("${currentPage}"),3,"web/search_list",null);
	});
	
	function search(){
		var skey = $("#skey").val();
		var type='${type}';
		window.location.href="web/search_list?skey="+encodeURIComponent(encodeURIComponent(skey))+"&type="+type;
	}
	
	function select(id,name,x,y,type,show,index){
		var obj = {};
		obj.name=name;
		obj.x = x;
		obj.y = y;
		obj.id=id;
		obj.type=type;
		obj.index = index;
		window.parent.addMarker2(obj,show);
		//window.parent.TJSoft.map.addMarker(obj,TJSoft.map.config.iconType.hnCheckIcon,false,false);
	}
</script>
  <body style="background-image: none;background-color: #F8F8EC;">

      <div class="" style="border:1px solid #ccc; height: 45px; margin:10px; background-color:#fff;">
                        	<input id="skey" type="text" placeholder="输入关键字" style="border:none;	height:28px; font-size:16px; margin-left:10px ;	margin-top:8px ; width:320px; line-height:28px;" value="${skey }"/>
                            <div class="" style="float:right; border-left:1px solid #ccc; height:100%; width:50px; background-image:url(<%=basePath%>module/web/images/search_btn.png); background-repeat:no-repeat; background-position:center;" onclick="search();"></div>
                        </div>
                        <div class="listA">
                            <s:if test="!list.isEmpty()">
						    <s:iterator var="s" value="list" status="status">
                        	<div class="row right_icon icon_in right_icon_bb" style="cursor: pointer;" onclick="select('${s.fuid}','${s.fullname }','${s.fx }','${s.fy }','${s.type}',true,<s:property value="#status.index+1"/>);">
                            	<span class="fleft" style="background-image:url(<%=basePath%>module/web/images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:20px; color:#FFF;"><s:property value="#status.index+1"/></span>
                            	<span class="fleft">${s.fullname }</span>
                            	<!-- 
                                <span class="fleft" style="display:block; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0 3px; padding-left:26px;margin-left:20px; color:#e5772d">${s.star }</span>
								 -->
								<span class="fright" style="color:#717699;cursor: pointer;"></span>
                                <div style="clear:both"></div>
                            </div>
                            <script type="text/javascript">xys.push('${s.fx }'+' '+'${s.fy }');select('${s.fuid}','${s.fullname }','${s.fx }','${s.fy }','${s.type}',false,<s:property value="#status.index+1"/>);</script>
                            </s:iterator>
                            </s:if>
                        </div>
                        <div class="_page_tool">
                        	<div id="page" style=" width: 100%"></div>
                        </div>
  </body>
</html>
