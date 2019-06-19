
<%@ page import="java.util.*,com.gh.entity.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>出游天气</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>module/weixin/js-photo/jquery-1.7.1.min.js"></script>


		<script>
	jQuery(document).ready(function($) {

	});
</script>
		<style>
</style>
	</head>
	<body>
		<s:iterator value="buWeather_list" var="list" status="sl">
			<s:if test="#sl.index==0">
				<div id="cytq-1">
				    <div id="title-1"><b>乌镇</b> (实时天气)  </div>
				    <div id="time-1"><s:property value="#list.time" /> 【<s:property value="#list.week" />】  </div>
				    <div id="weather-1">
				        <div class="temperature"><span><b><s:property value="#list.temperaturenow" />℃</b><span><s:property value="#list.changes" /></span></span>
				        <br /><s:property value="#list.wind" /></div>
				        <div class="tqimg"><img src="<%=basePath%>module/weixin/images/weather/<s:property value="#list.image" />" />
				        <br/>
				        <s:property value="#list.temperature" /></div>
				    </div>
				    <div id="air-1">[旅游 <s:property value="#list.traveling" />] [运动 <s:property value="#list.movement" />]</div>
				</div>
			</s:if>
			<s:else>
				<div id="cytq-2">
					<div id="time-2">
						<span><s:property value="#list.week" /></span>
						<br />
						<s:property value="#list.time" />
					</div>
					<div id="tqimg-2">
						<img src="<%=basePath%>module/weixin/images/weather/<s:property value="#list.image" />" />
					</div>
					<div id="weather-2">
						  <s:property value="#list.temperature" />
						<br />
						[<s:property value="#list.wind" />]
					</div>
				</div>
			</s:else>
		</s:iterator>
	</body>

</html>