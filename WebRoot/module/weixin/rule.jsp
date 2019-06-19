
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>韵•乌镇 </title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<style type="text/css">
			html,body{
				width:100%;height:100%;
				padding:0px;margin:0px;
			}
			.bg{
				width:100%;height:99.5%;
				position:fixed;
				z-index:-999;
			}
			.bg img{
				width:100%;height:100%
			}
			.button{ 
				width:280px;
				text-align:center;
				background:#ffffff;
				margin-left:2%;
				text-align:left;
				margin:0 auto;
				padding:6px;  
				border:#ccc solid 1px;
				border-radius:8px;
				margin-bottom:10%;
				word-break:break-all;
			}
		</style>
	</head>
	<body>
		<div class="bg">
			<img src="<%=basePath%>module/weixin/images/bg2.png"/>
		</div>
		<div style="height:30px;width:1px"></div>
		<div class="button">
			<span>第二届“梦•乌镇”诗歌大赛网络投票规则说明</span><br/><br/><br/>

				（一）、网络投票规则<br/>
				
				&nbsp;&nbsp;1、投票时间：2017年1月17日至2017年1月24日。<br/>
				
				&nbsp;&nbsp;2、投票次数：投票期间内每人每个id限投三票。<br/>
				 
				&nbsp;&nbsp;3、投票方法：关注“乌镇发布”，点击“我要投票”进入投票页面，在上方搜索栏直接搜索序号或是姓名，直接跳转至作品，点击序号前的方框即可投票。投票成功会有提示语出现。<br/><br/><br/>
				 
				 
				（二）活动说明：<br/>
				
				&nbsp;&nbsp;1、本次活动票选出网络人气奖2名（男女各一名），获奖者将获得由主办方提供的免费乌镇游（西栅景区门票两张+房车住宿体验一晚）奖品。<br/>
				
				&nbsp;&nbsp;2、本次投票仅票选出网络人气奖项，与第二届“梦•乌镇”诗歌大赛最终评选结果无关。<br/>
				
				&nbsp;&nbsp;3、本次活动的最终解释权归第二届“梦•乌镇”诗歌大赛组委会所有，活动详情请关注“乌镇发布”。
		</div>
	</body>
</html>
