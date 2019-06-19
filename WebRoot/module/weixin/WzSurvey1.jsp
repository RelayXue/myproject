 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=(String)request.getAttribute("type");
	
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title><s:property value="#application.DatadictionaryMap[type]"  /></title>
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
	<div id="about">
	<h3><s:property value="#application.DatadictionaryMap[type]"  /></h3>
    <div id="aboutimg">
        <div class="swipe">
            <ul id="slider"> 
               
				<s:if test="%{buNews.img1==null||buNews.img1==''}">
				</s:if>	
				<s:else>
					<li><a href=""><img width="100%" src="<%=basePath%>upload/B_${buNews.img1}"/></a></li>
				</s:else>
				<s:if test="%{buNews.img2==null||buNews.img2==''}">
				</s:if>	
				<s:else>
					<li><a href=""><img width="100%" src="<%=basePath%>upload/B_${buNews.img2}"/></a></li>
				</s:else>
				<s:if test="%{buNews.img3==null||buNews.img3==''}">
				</s:if>	
				<s:else>
					<li><a href=""><img width="100%" src="<%=basePath%>upload/B_${buNews.img3}"/></a></li>
				</s:else>
                
                
            </ul>
            <div id="pagenavi">
              
                
                <s:if test="%{buNews.img1==null||buNews.img1==''}">
				</s:if>	
				<s:else>
					 <a href="javascript:void(0);" class="active">1</a>		
				</s:else>
                <s:if test="%{buNews.img2==null||buNews.img2==''}">
				</s:if>	
				<s:else>
					 <a href="javascript:void(0);">2</a>			
				</s:else>
                <s:if test="%{buNews.img3==null||buNews.img3==''}">
				</s:if>	
				<s:else>
					 <a href="javascript:void(0);">3</a>			
				</s:else>
                
            </div>
        </div>
         <script type="text/javascript" src="<%=basePath%>module/weixin/js/touchScroll.js"></script>
 		 <script type="text/javascript" src="<%=basePath%>module/weixin/js/touchslider.dev.js"></script>
		<script type="text/javascript" src="<%=basePath%>module/weixin/js/run.js"></script>
    </div>
    <div id="aboutintroduction">
         ${buNews.content }
    </div>
</div>
<div id="function">
    <div class="read">阅读${buNews.readnum }</div>
    <div class="chan"><a href="javascript:Praise('${buNews.fuid}','<%=type %>')"><img src="<%=basePath%>module/weixin/images/chan.png" /></a><span id="sp"> ${buNews.praise}</span></div>
    <div class="correction"><a href="#"></a></div>
</div>

</body>

</html>