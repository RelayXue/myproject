<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/jquery-1.4.2.min.js"></script>
<title>乌镇信息化平台</title>
 <style type="">
 html, body {
   height: 100%;
} 
 </style>
<script  language="javascript" >
	$(document).ready(function() {
		navAuto();
		$(window).bind("resize", navAuto);
		pageLoad();
	});
	
	function navAuto(){
		var w=$(window).width();
		var h=$(window).height();
		$("#right").css("width",w-11);
		$("#right").css("height",h-170);
	}
	  function pageLoad(){
      var url  =$("a[name='gre']").eq(0).attr("href");
      document.getElementById("right").src=url;
   	
   }
</script>

</head> 

<body>

<div class="warpall">
	<div class="warpall">
  <!--top-->
  <div class="topban clearfix">
    <div class="fl"><img src="images/logo_pic.jpg"></div>
    <div class="fr"><img src="images/top_pic.jpg"></div>
  </div>
  <div class="topnav">
    <ul class="clearfix">
        	<s:iterator value="parentList" var="list">
	               	<li><a name="mpar" mid='<s:property value="#list.fuid" />' href="javascript:void(0)" ><s:property value="#list.menuName" /></a>
	                	<ul>
	                  	<s:iterator var="listc" value="#list.children">
	               			<li><a class="hide" name="gre" target="right" href="<s:property value="#listc.menuUrl" />"><s:property value="#listc.menuName" /></a>
	               				<s:if test="#listc.children!=null&&#listc.children.size>0">
		               				 <ul>
		               				 <s:iterator var="listcc" value="#listc.children">
				                        <li><a target="right" href="<s:property value="#listcc.menuUrl" />"><s:property value="#listcc.menuName" /></a></li>
				                       </s:iterator> 
		                    		  </ul>
	                    		  </s:if>
	               			</li>
	               		</s:iterator>
	                  </ul>
	                </li>
             </s:iterator>
    </ul>
  </div>
  <!--main-->
  <div class="toplinebg">
    <div class="topline">
      <div class="info"><strong>${session.username }</strong>，您好！欢迎光临！您上一次登录的时间是：2014-03-04 19:20:34</div>
      <div class="link clearfix"><a href="#" class="back">返回首页</a><a href="#" class="password">修改密码</a><a href="<%=basePath%>Login!logout" class="exit">注销退出</a></div>
    </div>
  </div>
  <div class="indexall clearfix"> 
    <iframe class="inright" style=""  name="right" id="right" src="" frameborder="0" scrolling="auto"></iframe>
  </div>
</div>
</body>
</html>
