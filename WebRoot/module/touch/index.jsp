<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/touch/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->



	<head>

		<!-- Basic -->
		<meta charset="utf-8">
		<title>乌镇国际旅游区</title>		
		<meta name="keywords" content="乌镇" />
		<meta name="description" content="乌镇旅游">
		<meta name="author" content="taoguang.com">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		
		<link rel="stylesheet" href="<%=curl %>css/bootstrap.css">
		<link rel="stylesheet" href="<%=curl %>css/theme.css">

		<script src="<%=curl %>js/jquery.min.js"></script>
		<script src="<%=curl %>js/bootstrap.min.js"></script>
		<script src="<%=curl %>js/owl.carousel.min.js"></script>
		<script src="<%=curl %>js/custom.js"></script>
		<script src="<%=basePath %>module/touch/js/ScrollPic.js" type=text/javascript></script>

		<!--[if IE]>
			<link rel="stylesheet" href="css/ie.css">
		<![endif]-->

		<!--[if lte IE 8]>
			<script src="js/respond.js"></script>
			<script src="js/excanvas.js"></script>
		<![endif]-->
		<style type="text/css">
			.content-p3-body-bottom{width:932px; height:115px; float:left;margin:20px 0 0 20px;_display:inline}
.content-p3-body-bottom ul{ width:120px; height:115px; float:left; margin-right:11px;}
.content-p3-body-bottom li{ width:120px; float:left; _display:inline;}
.content-p3-body-bottom li.img{ width:120px; height:90px;}
.content-p3-body-bottom li.text{ text-align:center; margin:7px 0 0 0;}
			
		</style>
		<script type="text/javascript">
			$(function(){
			})
		</script>
</head>
<body class="home" style="overflow-y:hidden; overflow: hidden;">
<header>
<div class="container header-top">
	<img alt="乌镇" src="<%=curl %>img/h1-wuzhen.png">
	<hr class="tall" />
 </div>
<div class="container">
	<ul class="home nav navbar-nav">
        <li><a href="<%=curl %>touch_main_showNewsList?category=1" class="btn btn-wuzhen">乌镇地理</a></li>
        <li><a href="<%=curl %>touch_main_showNewsList?category=2" class="btn btn-wuzhen">乌镇历史</a></li>
        <li><a href="<%=curl %>touch_main_showNewsList?category=3" class="btn btn-wuzhen">乌镇民俗</a></li>
        <li><a href="<%=curl %>touch_main_showNewsList?category=4" class="btn btn-wuzhen">乌镇故事</a></li>
        <li><a href="<%=curl %>touch_main_showNewsList?category=5" class="btn btn-wuzhen">乌镇名人</a></li>
        <li><a href="<%=curl %>touch_main_showNewsList?category=6" class="btn btn-wuzhen">乌镇保护</a></li>
	</ul>
</div>
</header>

<div class="container" style="margin-top:6px;">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div id="carousel-1" class="owl-carousel owl-theme owl-carousel-init">
				<s:iterator value="buNews_list" var="list">
				<s:generator val="#list.img1" separator="," id="imgs"></s:generator>
						<s:iterator status="st" value="#request.imgs" id="img_list">   
					  		 <div class="img-thumbnail"><img class="img-responsive" src="<%=basePath%>upload/<s:property value="#img_list" />" alt=""></div>
					   </s:iterator>
				</s:iterator>
				
				<!--<div class="img-thumbnail"><img class="img-responsive" src="<%=curl %>img/slide-1.png" alt=""></div>-->
				<!--<div class="img-thumbnail"><img class="img-responsive" src="<%=curl %>img/slide-1.png" alt=""></div>-->
			</div>
		</div>
  </div>
</div>

<div class="container" style="max-width: auto;">
	<div class="row padding20">
  		<div class="col-md-3 col-md-offset-3 col-sm-3 col-sm-offset-3">
        	<img class="img-responsive center-block" src="<%=curl %>img/menu-le.png" alt="乐" onclick="window.location.href='<%=curl%>nav.jsp?category=002016'"/>
        </div>
		<div class="col-md-3 col-sm-3 center">
           <img class="img-responsive center-block" src="<%=curl %>img/menu-gou.png" alt="购" onclick="window.location.href='<%=curl%>nav.jsp?category=002015'">
     	</div>
	</div>
  <div class="row padding20">
  		<div class="col-md-3 col-md-offset-3 col-sm-3 col-sm-offset-3">
       	  <img class="img-responsive center-block" src="<%=curl %>img/menu-chi.png" alt="吃" onclick="window.location.href='<%=curl%>nav.jsp?category=002018'">
    </div>
		<div class="col-md-3 col-sm-3 center">
           <img class="img-responsive center-block" src="<%=curl %>img/menu-zhu.png" alt="住" onclick="window.location.href='<%=curl%>nav.jsp?category=002017'">
     	</div>
  </div>
</div>
 
 <div class="content-p3-body-bottom">
          	<div align="center">
          	
	          <div id=demo style="overflow: hidden; height: 120px; width: 100%;">
				<table align=left cellpadding=0 cellspace=0 border=0>
					<tr>
						<td id=demo1 valign=top>
							<div style="width: 1190px">
							 <s:iterator value="buNews02_list" var="list" >
								<div class=box>
									<ul style="list-style-type:none;">
							            <li class="img"><a href="<%=basePath %>touch/index!fresh?id=<s:property  value="#list.fuid" />" target="_blank"><img width="120" height="90" src="<%=basePath %>upload/<s:property  value="#list.img1" />" /></a></li>
							            <li class="text" style="margin-top:13px;"><a href="<%=basePath %>fnews!freshDetails?id=<s:property  value="#list.fuid" />" target="_blank" class="hui-12-444"><s:property value="#list.fullname" /></a></li>
							          </ul>
								</div>
								</s:iterator>
								</div>
						</td>
						<td id=demo2 valign=top></td>
					</tr>
				</table>
			</div>
			<script>
		var speed = 10;
		demo2.innerHTML = demo1.innerHTML;
		function Marquee() {
			if (demo2.offsetWidth - demo.scrollLeft <= 0)
				demo.scrollLeft -= demo1.offsetWidth
			else {
				demo.scrollLeft++;
			}
		}
		var MyMar = setInterval(Marquee, speed);
		demo.onmouseover = function() {
			clearInterval(MyMar);
		};
		demo.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		};
	</script>
          	
           </div>
        </div>
 
<footer id="footer" style="padding: 71px 0 0;">
	<div class="footer-copyright">
		<div class="container">
			<img src="<%=curl %>img/footer-home.png" />
		</div>
	</div>
</footer>
</body>
</html>