 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String type=request.getParameter("type");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title>
		
		 <%if(type!=null&&type.equals("two")){ %>
		  <s:property value="buNews.fullname" />
		   <%}else{ %>
		   	乌镇发布：<s:property value="buNews.fullname" />
		   <%} %>
		</title>
 
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		
        
		<script>
	 jQuery(document).ready(function($) {
		 
	 });
	 function Praise(id,type){
		 $.ajax({
			    url: "<%=basePath%>fnews!Praise?time=" + new Date(),
			    type:"post",
			    data:"id="+id+"&re=summary&type=<%=type %>",
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			        if(data!=null&&data!='error'){
			        	$("#sp").html(data);
			        }
			    }
			});
		 
	 }
	 
	</script>
		<style>
 
</style>
	</head>
	 <body>
	<div id="details">
	<h3><s:property value="buNews.fullname" /> </h3>
   <span><s:date name="buNews.createdate" format="yyyy-MM-dd"  /> &nbsp;&nbsp;&nbsp; 
   <%if(type!=null&&type.equals("two")){ %>
   <a style="color: #969696" href="javascript:void(0)">来自乌镇发布</a> 
   <%}else{ %>
   <a style="color: #969696" href="javascript:void(0)">乌镇发布</a> 
   <%} %>
   </span>
    
    
    
    
    <div id="detailsimg">		 
		<img width="100%" src="<%=basePath%>upload/B_<s:property  value="buNews.img1"/>"/>
    </div>
    <div id="detailsintroduction" style="font-size: 2.8em">
        <s:property value="buNews.content" escape="false"/>

    </div>
    <div style="width: 100%;text-align: center;">
    	<img date-type="jpg" style="width: 100%"  src="<%=basePath%>module/weixin/images/weixinCode.jpg" alt="" />
    </div>
</div>
<div id="function">
    <div class="read">阅读 <s:property value="buNews.readnum" /></div>
    <div class="chan"><a href="javascript:Praise('<s:property value="buNews.fuid"/>','<%=type %>')"><img src="<%=basePath%>module/weixin/images/chan.png" /></a><span id="sp"><s:property  value="buNews.praise"/></span> </div>
    <div class="correction"><a href="#"></a></div>
</div>

</body>

</html>