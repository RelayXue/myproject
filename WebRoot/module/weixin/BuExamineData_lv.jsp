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
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">

<title>游客列表——旅店</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination1.js"></script>
<script type="text/javascript">
 
	$(function() {
	var hh=$("#nr").height();
	 	var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>z_weixin/select_All?department=Inn', 
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
				if ($('#content').children().size() > 100) { 
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
	height: 25px;
	color: #555555;
	font-weight: bold;
}

#first {
	padding-left: 25px;
}

#second {
	padding-left: 105px;
}

#nr {
	width: 100%;
	height: 70%;
	border: 1px solid #dcdadb;
	font-size: 0.24rem;
	overflow: scroll; /*任何时候都强制显示滚动条*/
	overflow: auto; /*需要的时候会出现滚动条*/
	overflow-y: auto; /*控制Y方向的滚动条*/
}

#nr table {
	padding: 5px;
	width: 100%;
}

td {
	width: 15px;
	height: 20px;
}

.odd {
	background-color: #fbf4e3;
}

.even {
	
}

.left {
	color: #555555;
	font-weight: bold;
	padding-left: 3%;;
	width:35%;
}

.right {
	padding-right:7%;
	font-family: "新宋体";
	color: #5c5b58;
	width:70%;
}

.yincang{
	width:80%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

#tail {
	margin-top: 10%;
	width: 100%
}

#bgimg {
	position: absolute;
	margin-left: -20px;
	bottom:0px;
	z-index: -1;
	width: 100%;

}
</style>
<div id="head">
	<a id="first"> 历史审核信息</a>
</div>
<body>
	<div id="nr">
	<s:iterator value="buExamine_list" var="list" status="st">
		<a href="z_weixin/select_where?department=Inn&fuid=<s:property value='#list.fuid'/>">
	<s:if test=" #st.index % 2==0">
		<table class="odd">
	</s:if>
	<s:else>
		<table>
	</s:else>
			<tr>
				<td class="left">游客姓名</td>
				<td class="right"><div class="yincang"><s:property value="#list.passenger"/></td>
			</tr>

			<tr>
				<td class="left">手机号码</td>
				<td class="right"><div class="yincang"><s:property value="#list.ephone"/></div></td>
			</tr>

			<tr>
				<td class="left">到访时间</td>
				<td class="right"><div class="yincang"><s:date name="#list.arrivetime" format="yyyy-MM-dd HH:mm:ss" /> </div></td>
			</tr>

			<tr>
				<td class="left">到访事由</td>
				<td class="right"><div class="yincang"><s:property value="#list.reason"/></div></td>
			</tr>
			
		</table>
	</a>
	 </s:iterator>
		<div id="content"></div>
		
	</div> 
</body>
	<div id="tail" align="center">

		<a href="<%=basePath%>module/weixin/BuExamine_shenqing.jsp"><img src="<%=basePath%>module/weixin/images/03.png" ></a>
	</div>
<div id="bgimg">
	<img src="<%=basePath%>module/weixin/images/02.png" width="100%">
	<div>
	

</html>