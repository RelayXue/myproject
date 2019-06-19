<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title><s:property value="buAccommodation.hname"/>详情</title>
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style> 
		body{
			padding:0px;
			margin:0px;
		}
		.top_div{
			width:100%;
			height:30%; 
			float:left;
		}
		.buttom_div{
			width:93%;
			height:68%;
			float:left;
			padding-top:3%;
			padding-left:7%
		}
		.top_img{
			width:100%;
			height:100%;
		}
		.Small_div{
			width:91%;
			border-bottom:#B9B9B9 dashed 2px;
			padding-top:7px;
			padding-bottom: 7px;
			padding-left:2%;
			font-size:23px;
			font-family: 黑体;
			white-space:nowrap;
			overflow:hidden;
			text-overflow:ellipsis; 
		}
		.small_img{
			width:25px;
		}
		.text_span{
			padding-left:18px;
			color:#000000
		}
	</style>
	
  </head>
  
  <body>
  		<div class="top_div">
  			<s:if test="buAccommodation.himg==null || buAccommodation.himg==''">
  				<img src="<%=basePath%>module/weixin/images/zw.jpg" class="top_img"/>
  			</s:if>
  			<s:else>
  				<img src="<%=basePath%>upload/B_<s:property value="buAccommodation.himg"/>" class="top_img"/>
  			</s:else>
  		</div>
  		<div class="buttom_div"><%-- <span class="text_span">dsa</span><br/><span style="padding-left:96px;color:#000000">dsa</span> --%>
  			<div class="Small_div"><b><s:property value="buAccommodation.hname"/></b></div>
  			<div class="Small_div" style="font-size:18px;color:#707070"><img src="<%=basePath%>module/weixin/images/dz_xq.jpg" class="small_img"> 地址 <span class="text_span"><s:property value="buAccommodation.address"/></span></div>
  			<div class="Small_div" style="font-size:18px;color:#707070"><img src="<%=basePath%>module/weixin/images/syr_xq.jpg" class="small_img"> 所有人  <span style="color:#000000"><s:property value="buAccommodation.householder"/></span></div>
  			<div class="Small_div" style="font-size:18px;color:#707070">
	  			<div style="float:left;width:89px;padding-top:8px">
	  				<img src="<%=basePath%>module/weixin/images/phone_xq.jpg" style="width:25px"/> 电话 
	  			</div>
	  			<s:if test="phone_2!=null && phone_2!=''">
		  			<div style="float:left;color:#000000;width:55%"><s:property value="phone_1"/></div>
		  			<div style="float:left;color:#000000;width:55%;padding-top:5px"><s:property value="phone_2"/></div>
		  		</s:if>
		  		<s:else>
		  			<div style="float:left;color:#000000;width:55%;padding-top:10px"><s:property value="phone_1"/></div>
		  		</s:else>
  			</div>
  			<div style="padding-left:5px;width:93%;font-size:15px;padding-top:8px;font-family:黑体;line-height:23px">&nbsp;&nbsp;&nbsp;&nbsp;<b><s:property value="buAccommodation.description"/></b></div>
  		</div>
  </body>
</html>
