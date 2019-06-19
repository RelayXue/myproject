<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
  	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
    <title>“我为乌镇拍封面”摄影比赛</title>
    <style type="text/css">
    	html body{
    		padding:0px;
    		margin:0px;
    		width:100%;height:99%
    	}
    	a{
			text-decoration: none; 
    	}
    	.backDiv{
    		position:absolute;
    		z-Index:-100;
    	}
    	.backgroundUrl{
    		width:100%;
    		height:100%
    	}
    	.content{
    		width:90%;
    		height:99%;
    		background:#ffffff;
    		margin-left:5%;
    		border:#B99260 solid 2px;
    		border-radius:8px;
    		overflow :yes;
    		overflow :auto;
    		padding-left:5px;
    		padding-rigth:5px
    	}
    	.top{
    		text-align:center;
    		width:90%; 
    		background:#B99260;
    		margin-left:5%;
    		border-radius:8px;
    		color:#ffffff;
    		font-family:黑体;
    		font-size:20px;
    		padding:4px
    	}
    	.bottom{
    		background:#D15F5F;
    	}
    </style>
  </head> 
  <body>
  <%--背景图片--%>
  	<div class="backDiv">
    	<img src="<%=basePath%>Photography/PhotographyImg/beijing.png" class="backgroundUrl">
    </div>
     <%--内容--%>
    <div style="width:100%;height:78%;padding-top:4%">
    	<div class="content">
    		<s:property value="a20161activity.introduce" escape="false"/>
    	</div>
    </div>
    <s:if test="type==1 || type==3">
	    <%--上按钮--%>
	    <div style="width:100%;height:9%;padding-top:3%">
		    <a href="<%=basePath%>Photography!exhibitionWorks?operation=0">
		    	<div class="top">
		    		查看参赛作品
		    	</div>
		    </a>
	    </div>
	     <%--下按钮--%>
	    <s:if test="type==1">
		    <div style="width:100%"> 
		   <%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fPhotography!UploadPictures&response_type=code&scope=snsapi_base&state=123#wechat_redirect">
		    --%><a href="<%=basePath%>Photography!UploadPictures">
		    	<div class="top bottom">
		    		我要参赛
		    	</div>
		    </a>
		    </div>
	    </s:if>
	    <s:elseif test="type==3">
	    	 <div style="width:100%">
		    <a href="<%=basePath%>Photography!exhibitionWorks?operation=1">
		    	<div class="top bottom">
		    		我要投票
		    	</div>
		    </a>
		    </div>
	    </s:elseif>
    </s:if>
    <s:else>
    	<s:if test="type==0">
	     	<div style="width:100%;padding-top:3%;">
		    	<div class="top" style="color:red">
		    		此活动于2016年6月3号正式开启，敬请期待!
		    	</div>
		    </div>
	    </s:if>
	    <s:elseif test="type==2">
	    	<div style="width:100%;padding-top:3%">
		    	<div class="top" style="color:red">
		    		专家正在筛选作品，请耐心等待!
		    	</div>
		    </div>
	    </s:elseif>
	     <s:elseif test="type==4">
	    	<div style="width:100%;padding-top:3%">
		    	<a href="<%=basePath%>Photography!ranking">
			    	<div class="top" style="background:#D15F5F">
			    		查看排名
			    	</div>
			    </a>
		    </div>
	    </s:elseif>
	    <s:elseif test="type==5">
	    	<div style="width:100%;height:9%;padding-top:3%">
		    	<div class="top" style="color:red">
		    		本次活动已经结束，敬请期待下期比赛
		    	</div>
		    </div>
	    </s:elseif>
    </s:else>
  </body>
  
  <script type="text/javascript">
  </script>
</html>
