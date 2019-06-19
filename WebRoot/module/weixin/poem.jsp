
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
				width:100%;height:100%;
				position:fixed;
				z-index:-999;
			}
			.bg img{
				width:100%;height:100%
			}
			.button{ 
				width:100%;
				position: absolute;
				text-align:center;
				top:50%
			}
			.button img{
				width:45%;
			}
		</style>
	</head>
	<body>
		<div class="bg">
			<img src="<%=basePath%>module/weixin/images/ba.png"/>
		</div>
		<div class="button">
			<a href="<%=basePath%>fnews!showRule2">
				<img src="<%=basePath%>module/weixin/images/gz.png"/>
			</a><br/><br/>
			<!-- <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2ffnews!GetWeixin&response_type=code&scope=snsapi_base&state=123#wechat_redirect"> -->
			<a href="<%=basePath%>fnews!GetWeixin">
				<img src="<%=basePath%>module/weixin/images/tp.png"/>
			</a>
		</div>
		
	
	
	
	
	
	
		<!--<div>
			<div class="utop">
				<img src="<%=basePath%>module/weixin/images/top1.png" width="100%" />
			</div>
			<div class="utitle">
				全国诗歌征集大赛
			</div>
			<div class="ucontainer">
				<table style="width:100%">
					<tr>
						<td align="center">
							<a href="<%=basePath%>fnews!showRule2"><div class="item">
									大赛规则
								</div> </a>
							<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2ffnews!GetWeixin&response_type=code&scope=snsapi_base&state=123#wechat_redirect">
								<a href="<%=basePath%>fnews!GetWeixin">
								<div class="item">
									诗歌鉴赏
								</div> </a>
						</td>
					</tr>
				</table>
			</div>
			<div class="ubottom">
			</div>
		</div>-->
	</body>
</html>
