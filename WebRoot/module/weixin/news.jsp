<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id=(String)request.getAttribute("id");		
%>

<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="dns-prefetch" >
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<title>${buNews.fullname }</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>module/weixin/css/page_mp_article_improve25ded2.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>module/weixin/css/not_in_mm25ded2.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>module/weixin/css/mui.css">
	</head>

	<body id="activity-detail" class="zh_CN mm_appmsg not_in_mm" ontouchstart="">
		<div id="js_article" class="rich_media">
			<div class="rich_media_inner">
				<div id="page-content">
					<div id="img-content" class="rich_media_area_primary">
						<h2 class="rich_media_title" id="activity-name" style="font-size: 20px">
							${buNews.fullname }
						</h2>
						<div class="rich_media_meta_list" style="margin: 0"> 
							<em id="post-date" class="rich_media_meta rich_media_meta_text"><s:date name="buNews.createdate" format="yyyy-MM-dd" />  </em>
							<em class="rich_media_meta rich_media_meta_text"></em> 
							<a class="rich_media_meta rich_media_meta_link rich_media_meta_nickname"
								href="javascript:void(0);" id="post-user"></a>
							<span
								class="rich_media_meta rich_media_meta_text rich_media_meta_nickname">乌镇发布</span>
						</div>
						<div class="rich_media_content" id="js_content" style="padding-top: 10px">
							<fieldset class="tn-Powered-by-XIUMI"
								style="white-space: normal; border: 0px; margin: 0px; text-align: justify; box-sizing: border-box; padding: 0px;">
								<section class="tn-Powered-by-XIUMI"
										style="font-family: inherit; box-sizing: border-box;">
										<s:if test="buNews.pic!=null">
										<p style="color: rgb(221, 60, 91); font-size: 14px; line-height: 1.4;">
											 <img style="width: 100%;height: 100%" src="http://wx.wuzhen.gov.cn/upload/<s:property  value="buNews.pic" />"></img>
										</p>
										 </s:if>
									</section>
							</fieldset>
							<p style="margin-top: 15px; white-space: normal;">
								<span style="font-size: 14px;"><s:property escape="false" value="buNews.content" /> </span>
								<%if(!"71c6b457d0c94f87b606a7caf13ef20a".equals(id)&&!"94d958841dfe4de6aa48a95465bb1c75".equals(id)){ %>
									意见征询：<textarea id="skey" rows="3" cols="37"></textarea>
								<div onclick="save()" class="mui-btn mui-btn-primary" align="center" style="left: 40%">
									提交
								</div>
								<%} %>
							
							</p>
							<p style="white-space: normal;">
								<br>
							</p>

						</div>
						 
						 
					</div>
				</div>

			</div>
		</div>
	<script type="text/javascript"src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
		function save(){
			var skey=$("#skey").val();
			$.ajax({
			    url: "<%=basePath%>fnews!opinion?time=" + new Date(),
			    type:"post",
			    data:"skey="+skey,
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			    	alert("感谢您提交的宝贵建议！");
			    }
			});
		}
	</script>
	</body>

</html>