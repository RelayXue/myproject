<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>我要抽奖</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/base.css"/>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>activity/two/css/animate.min.css"/>
  	<style type="text/css">
				.bg_img{background-image: url(<%=basePath%>activity/two/img/bg2.png);background-size: 100%; background-repeat:no-repeat;}
				.web_content{width: 85%; height: 250px; margin: 0px auto;border-radius: 5px; border: 3px solid #d1c0a5;
				margin-top: 15%;background: #FFFFFF; -webkit-animation: bounceInDown 1s;}
				.header_title{font-size: 20px; color: #ffffff;}
				.t_btn{width:85%; height: 40px; line-height: 40px; margin: 0px auto;text-align: center; 
				background: #D1C0A5; margin-top:20px; border-radius: 5px; color: #FFFFFF;}
				.hj_title{-webkit-animation: bounceInDown 1s;}
			</style>
  </head>
 <body class = "bg_img">
		<div class="web_content">
			<div style="position: relative; top:20px;text-align:center"  class="hj_title">
				恭喜您获得 <span id="animationSandbox" style="color: red;font-size:20px"  >
				“${name }”
				</span> 称号
			</div>
			<div class="m30 fl" style="margin-left:30%">  
				<img src="<%=basePath%>activity/two/img/person.png" width="100"/>
			</div>
		</div>
		<s:if test="a20162user.isyeas==0"><!--
		 <div class="t_btn" style="background: #f39b77;" id="fenxiang">
		 	<img src="<%=basePath%>activity/two/img/pyq.png" alt="" width="30px" style="padding-top: 5px;"/>
		 	<span style="margin-top: -15px; position: relative; top:-5px">分享朋友圈</span>
		 </div>
			 -->
		<p class="f12 " style="width:85%; margin: 0px auto;margin-top: 10%; color: red;"> 
		注明： 
		<s:if test="a20162user.isyeas==0">
		必需分享此活动才能获得抽奖机会。快点分享吧！！
		</s:if>
		<s:else>
		必须获得“状元”称号，才有机会抽奖
		</s:else>
		</p>
		
		<s:if test="a20162user.isshare==1">
				 <a href="javascript:void(0)" ><div class="t_btn" onclick="LuckDraw()" style="background: #89C997;">
				 	点我抽奖
				 </div></a>
			 </s:if>
			 <s:else>
			 	<a href="javascript:void(0)"  id="cj"><div class="t_btn" style="background:#A5A5A5;">
				 	点我抽奖
				 </div></a>
			 </s:else>
		</s:if>
		<s:else>
			<a href="<%=basePath%>poetryChallenge!answer"><div class="t_btn" style="background: #89C997;">
				我要重玩
		 	</div></a>
		 	<a href="http://mp.weixin.qq.com/s?__biz=MzA4MTQxNDczMA==&mid=2691236515&idx=1&sn=00e563324bab2e222b7d2d10a0b789cc&chksm=ba08fd868d7f7490a2565014fad8702cefb355eaf2e4116b1aff82eb9f48625dd8635504e13b&scene=0#wechat_redirect"><div class="t_btn" style="background: #ED7189;">
				我要参加诗歌大赛
		 	</div></a>
		</s:else>
	</body>
	<form action="<%=basePath%>poetryChallenge!luckDraw" method="post" id="myform">
		<input type='hidden' value="${weixinid }" name="weixinid"/>
	</form>
</html>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
 <script type="text/javascript">
  	wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: 'wx598df1869ba5231a', // 必填，公众号的唯一标识
		timestamp: '${timestamp}', // 必填，生成签名的时间戳
		nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		signature: '${signature}',// 必填，签名，见附录1
		jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	
	wx.ready(function () {
		if('${name}'=="状元"){
			wx.onMenuShareTimeline({
				title: '我是乌镇诗歌状元，不服来战！', // 分享标题
				link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fpoetryChallenge!getWeiXinId&response_type=code&scope=snsapi_base&state=a1#wechat_redirect', // 分享链接
				imgUrl: '<%=basePath%>/activity/two/img/person.png', // 分享图标
				success: function () { 
				        // 用户确认分享后执行的回调函数
			        $.ajax({
						url: "<%=basePath%>poetryChallenge!share",
						type:"post",
						data:"weixinid="+'${weixinid}',
						success: function() {
							$("#cj").html('<div class="t_btn" onclick="LuckDraw()" style="background: #89C997;">点我抽奖</div>')
						}
					})
				},
				cancel: function () { 
				        // 用户取消分享后执行的回调函数
				}
			});
			wx.onMenuShareAppMessage({
			    title: '我是乌镇诗歌状元，不服来战！', // 分享标题
			    desc: '', // 分享描述
			    link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fpoetryChallenge!getWeiXinId&response_type=code&scope=snsapi_base&state=a1#wechat_redirect', // 分享链接
			    imgUrl: '<%=basePath%>/activity/two/img/person.png', // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			        $.ajax({
						url: "<%=basePath%>poetryChallenge!share",
						type:"post",
						data:"weixinid="+'${weixinid}',
						success: function() {
							$("#cj").html('<div class="t_btn" onclick="LuckDraw()" style="background: #89C997;">点我抽奖</div>')
						}
					})
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    }
			});
		}
	})
	
	function LuckDraw(){
		$("#myform").submit();
	}
    </script>
