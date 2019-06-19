<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">

<title>最美乌镇人家</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function LuckDraw(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>accommodation!getReward?weixinid="+'${weixinid}',
			success:function(msg){
				if(msg=="请先投票，再来抽奖" || msg=="您已抽过一次奖品" || msg=="今天的奖品已经送出，请明天前来抽奖"){
					alert(msg)
					window.location.href="<%=basePath%>accommodation!Vote?weixinid="+'${weixinid}';
					return
				}else{
					document.getElementById("msg").value=msg
					$("#myform").submit();
					return
				}
			},
		})
	}
</script>
</head>

<style>
body {
	padding-left: 12px;
	padding-right: 12px;
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
.top{
	width:100%;
	height:67%;
}
.buttom{
	margin-left:16%;
	padding-top:2%;
	padding-bottom:2%;
	width:68%;
	background:#CE3F3B;
	border-radius: 60px;
	text-align:center;
	color:#ffffff;
	font-size:25px;
	font-family: 黑体;
	
}
.text{
	margin-left:16%;
	padding-top:3%;
	width:68%;
	border-radius: 60px;
	text-align:center;
}
a{
	color:#ffffff;
	font-family: 黑体;
}
</style>

<body>
	<div class="img_div"><img src="<%=basePath%>module/weixin/images/choujiang.png" class="img_big"></div>
	<div class="top"></div>
	<s:if test='isLuckDraw=="true"'>
		<div class="buttom" onclick="LuckDraw()"><b>点击参与抽奖</b></div>
	</s:if>
	<s:else>
		<div class="buttom"><b>感谢您的参与</b></div>
	</s:else>
	<div class="text"><a href="<%=basePath%>accommodation!Rule?weixinid=${weixinid}">活动及领奖规则</a></div>
	
	
	
	
	<form action="<%=basePath%>accommodation!excessive" method="post" id="myform">
		<input type="hidden" name="weixinid" id="weixin" value="${weixinid}"/>
		<input type="hidden" name="msg" id="msg">
	</form>
</body>
</html>