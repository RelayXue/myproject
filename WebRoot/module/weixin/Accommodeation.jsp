<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=(String)request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">

<title>最美乌镇人家</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/scrollpagination1.js"></script>
<script type="text/javascript">
	$(function() {
	var hh=$("#nr").height();
	 	var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>accommodation!Paging', 
			'contentData' : 'pageType=page&type=<%=type%>&indexPage='+indexPage,  
			'scrollTarget' : $("#nr"),  
			'heightOffset' : hh,  
			'beforeLoad' : function() {
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&type=<%=type%>&&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 150) {
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
			
		});

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};  
	});
</script>
</head>

<style>
body {
	padding-left: 12px;
	padding-right: 12px;
}
#head {
	margin-top: 14px;
	margin-bottom: 4px;
	float: left;
	display: block;
	padding-top: 5px;
	background-color: #eac873;
	width: 100%;
	border-radius: 5px;
	height: 30px;
	color: #555555;
	font-weight: bold;
	padding-top:10px;
}

#first {
	padding-left: 25px;
}

#nr {
	width: 100%;
	height: 80%;
	border: 1px solid #dcdadb;
	font-size: 0.24rem;
	overflow: scroll; /*任何时候都强制显示滚动条*/
	overflow: auto; /*需要的时候会出现滚动条*/
	overflow-y: auto; /*控制Y方向的滚动条*/
	background:#ffffff
}

#nr table {
	padding: 5px;
	width: 100%;
}

.big_one {
	background-color: #fbf4e3;
	width:97%;
	padding-left:3%;
	height:90px;
}
.big_two {
	width:97%;
	padding-left:3%;
	height:90px;
}
.div_left{
	width:40%;
	height:100%;
	float:left
}
.div_right{
	width:53%;
	height:30px;
	float:left;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis; 
	padding-left:3%;
	font-size:18px;
	font-family:微软雅黑;
}
.div_right_bo{
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis; 
	font-size:17px;
	font-family:微软雅黑;
	width:53%;
	height:30px;
	float:left;
	padding-left:3%;
}
.img_div{
			width:100%;
			height:100%;
			float:left;
			margin-left:-20px;
			margin-top:-8px;
			position:absolute;
			z-index:-10;
		}
.img_big{
			width:100%;
			height:101%
		}
</style>

<body>
	<div class="img_div"><img src="<%=basePath%>module/weixin/images/bj.png" class="img_big"></div>
	<div id="head">
		<a id="first"> 民宿列表</a>
	</div>
	<div id="nr">
	<s:iterator value="buAccommodation_list" var="list" status="st">
	<a href='<%=basePath%>accommodation!selectById?fuid=<s:property value="#list.fuid"/>'>
		<br/>
		<s:if test=" #st.index % 2==0">
			<div class="big_one">
				<div class="div_left">
					<s:if test="#list.himg!=null && #list.himg!=''">
						<img src="<%=basePath%>upload/L_<s:property value="#list.himg"/>" style="width:100%;height:90px"/>
					</s:if>
					<s:else>
						<img src="<%=basePath%>module/weixin/images/zw.jpg" style="width:100%;height:90px"/>
					</s:else>
				</div>
				<div class="div_right" style="color:#000000"><b><s:property value="#list.hname"/></b></div>
				<div class="div_right" style="color:#767475"><img src="<%=basePath%>module/weixin/images/dz-g.jpg";style="height:100%">&nbsp;<s:property value="#list.address"/></div>
				<div class="div_right_bo" style="color:#767475"><img src="<%=basePath%>module/weixin/images/phone-g.jpg"/>&nbsp;<s:property value="#list.mobile"/></div>
			</div>
		</s:if>
		<s:else>
			<div class="big_two">
				<div class="div_left">
					<s:if test="#list.himg!=null && #list.himg!=''">
						<img src="<%=basePath%>upload/L_<s:property value="#list.himg"/>" style="width:100%;height:90px"/>
					</s:if>
					<s:else>
						<img src="<%=basePath%>module/weixin/images/zw.jpg" style="width:100%;height:90px"/>
					</s:else>
				</div>
				<div class="div_right" style="color:#000000"><b><s:property value="#list.hname"/></b></div>
				<div class="div_right" style="color:#767475"><img src="<%=basePath%>module/weixin/images/dz-w.jpg";style="height:100%">&nbsp;<s:property value="#list.address"/></div>
				<div class="div_right_bo" style="color:#767475"><img src="<%=basePath%>module/weixin/images/phone-w.jpg"/>&nbsp;<s:property value="#list.mobile"/></div>
			</div>
		</s:else>
	</a>
	</s:iterator>
	
		<div id="content"></div>
		
	</div>
</body>
</html>