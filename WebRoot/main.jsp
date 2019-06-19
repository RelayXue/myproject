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
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/gray/easyui.css"/>
 <link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/icon.css"/>
 <link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/color.css"/>
<script language="javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
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
		var mpar=$("a[name='mpar']");
	 	mpar.click(function (){
	 		var menuId=$(this).attr("mid");
	 		mpar.each(function (){
	 			$(this).removeAttr("class");
	 		});
	 		$(this).attr("class","active");
	 		$("#left").attr("src","User!getChild?id="+menuId+"&skey="+$(this).html());
	 	});
	 	//alert($("a[name='par']",document.frames('left').document.length()));
	 	//alert($("a[name='par']",document.frames('left').contentWindow).html());
	 	//var  ileft=window.document.getElementById("left").contentWindow.document.getElementById("par");
	 	//var  ileft=window.frames["left11"].document.getElementById("par");
	 	//alert(ileft.length);
	 	
	}); 
	
	function navAuto(){
		var w=$(window).width();
		var h=$(window).height();
		$("#right").css("width",w-191);
		$("#cle").css("height",h-168); 
		$("#left").css("height",h-170); 
		$("#right").css("height",h-170);
	}

	function showPass(){
			$("#ifr").attr("src","<%=basePath%>User!UpdateShowPass");
			$('#d22').dialog({
				title:"修改密码",
				 width: 470,
		   		 height:320,
		   		 top:216
				
			});
		}
	
</script>

</head>

<body>

<div class="warpall">
	<!--top-->
	<div class="topban clearfix">
	    <div class="fl"><img src="images/logo_pic.jpg"></div>
	    <div class="fr"><img src="images/top_pic.jpg"></div>
  	</div>
    <div class="topnav">
        <ul class="clearfix">
        	<s:iterator value="parentList" var="list">
	               	<li><a class=""   name="mpar" mid='<s:property value="#list.fuid" />' href="javascript:void(0)" ><s:property value="#list.menuName" /></a>
	                </li>
             </s:iterator>
         </ul>
    </div>
    <!--main-->
    <div class="toplinebg"  >
        <div class="topline">
            <div class="info"><strong>${session.username }</strong>，您好！欢迎光临！您上一次登录的时间是：<s:date name="#session.previousvisit"  format="yyyy-MM-dd HH:mm:ss" /></div>
            <div class="link clearfix"><a href="javascript:showPass()"  class="password">修改密码</a><a href="<%=basePath%>Login!logout" class="exit">注销退出</a></div>
        </div>
    </div>
    <div id="cle" class="indexall clearfix" >
    	<iframe class="inleft"  name="left11" id="left" src="User!getChild?id=<s:property value="#request.parentList[0].fuid"/>&skey=<s:property value="#request.parentList[0].menuName"/>" frameborder="0" scrolling="no"></iframe>
    	<iframe class="inright" style=" margin-left:181px;"  name="right" id="right" src="${defaultPage}" frameborder="0" scrolling="auto"></iframe>
    </div>
</div>
<div style="display: none;">
			 <div id="d22" title="修改密码" data-options="iconCls:'icon-edit'" style="width: 500px;height: 380px;top: 200px;overflow:hidden;">
       			 <iframe id="ifr"  style="width: 100%;height: 100%;overflow: hidden;"  scrolling="no" frameborder="0"   src=""></iframe>
   			 </div>
</div>
</body>
</html>
