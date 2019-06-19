<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	<link href="css/layout.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="js/jquery-1.4.2.min.js"></script>

	<script>
		$(document).ready(function() {
			var mpar=$("a[name='par']");
		 	mpar.click(function (){
		 		var cl=$(this).attr("class");
		 		var div=$("div").find(".nav2");
		 		div.each(function (){
		 			$(this).css("display","none");
		 		});
		 		mpar.each(function (){
		 			$(this).attr("class","nav1");
		 		});
		 		if(cl=='nav1'){
		 			$(this).attr("class","active nav1");
		 			$(this).next(".nav2").css("display","block");
		 		}else{
		 			$(this).attr("class","nav1");
		 			$(this).next(".nav2").css("display","none");
		 		}
		 	});
		 	pageLoad();
	});
	
   function pageLoad(){
      var url  =$("a[name='par']").eq(0).attr("href");
      if(url=='javascript:void(0)'){
     	 url=$("a[name='cpar']").eq(0).attr("href");
     	 $("a[name='par']").eq(0).click();
      }else{
      	$("a[name='par']").eq(0).click();
      }
      window.parent.document.getElementById("right").src=url;
   }
	
	</script>
	
	
  </head>
  
  <body>
   <div class="inleft"  >
        	<h2><s:property value="#request.skey"/></h2>
            <div class="leftnav">
            	<s:iterator value="parentList" var="list">
	                <a style="cursor: pointer;"id="par" name="par" href="<s:property value="#list.menuUrl" />" target="right" class="nav1">
	                		<span style="background-image: url('<%=basePath%><s:property value="#list.images" />')" class="tip1"><s:property value="#list.menuName" /></span></a>  <!--  class="active nav1" -->
	                <s:if test="#list.children.size>0">
		                 <div style="display: none;" class="nav2" >
			                <s:iterator var="listc" value="#list.children">
				                	<a target="right" name="cpar" href="<s:property value="#listc.menuUrl"/>"><s:property value="#listc.menuName" /></a>
			                </s:iterator>
		               	</div>
	               	</s:if>
            	 </s:iterator>
            </div>
        </div>
  </body>
</html>
