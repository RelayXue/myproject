<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>最美乌镇人家</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		body{
			margin:0px;
			padding:0px
		}
		.img_big{
			width:100%;
			height:101%
		}
		.img_div{
			width:100%;
			height:99%;
			float:left;
			position:absolute;
			z-index:-10
		}
		.con_div{
			width:99%;
			height:60%;
			float:left;
		}
		.son_div_one{
			text-align:center;
			margin-top:20%;
			width:100%;
			height:25%;
		}
		.img_one{
			height:100%;
		}
		.jieshu{
			width:60%;
			font-size:40px;
			text-align:left;
			padding-top:30px;
			font-family:微软雅黑;
			color:#ffffff
		}
	</style>
	<script type="text/javascript">
		function fun(){
			var tt='${ctime}';
			if(tt=='true'){
				window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2faccommodation!getWeiXinId&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			}else{
				alert("投票活动尚未开始！");
				return;
			}
		}
	</script>
  </head>
  
  <body> 
  	<div class="img_div"><img src="<%=basePath%>module/weixin/images/bj.png" class="img_big"></div>
    <div class="con_div">
    	<div class="son_div_one"><a href="<%=basePath%>accommodation!Paging"><img src="<%=basePath%>module/weixin/images/lie_one.jpg" class="img_one"></a></div>
    	<div class="son_div_one" style="margin-top:10%">
    	<a id="ta"   href="javascript:fun()"><img src="<%=basePath%>module/weixin/images/lie_two.jpg" class="img_one"></a>
    	<%-- <img src="<%=basePath%>module/weixin/images/lie_two.jpg" class="img_one" onclick="fun()"> --%>
    	</div>
    	</br>
    	</br>
    	<center><div class="jieshu">最美乌镇人家投票已经结束，您可进入投票界面查看最终结果</div></center>
    </div>
  </body>
</html>
