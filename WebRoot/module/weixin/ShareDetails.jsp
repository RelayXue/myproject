
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String type=request.getParameter("type");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>美图欣赏</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>module/phone/css/nearby-locating6.css">
		<script>
	jQuery(document).ready(function($) {

	});
	
	 function Praise(id,type){
		 $.ajax({
			    url: "<%=basePath%>fnews!Praise?time=" + new Date(),
			    type:"post",
			    data:"id="+id+"&re=summary&type=<%=type %>",
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			        if(data!=null&&data!='error'){
			        	$("#sp").html(data);
			        }
			    }
			});
		 
	 }
</script>
<style type="text/css">
*{border: 0;padding: 0;margin: 0;}
#details{padding: 10px; background-color:#ded8cb;}
#details .ykfxfunction {
	width:100%;
	height:36px;
	overflow:hidden;
	line-height:36px;
	position:relative;
	color:#656565;
	margin:10px auto;
	padding:0;
}
#details .ykfxfunction a {
	color:#656565;
}
#details .ykfxfunction img {
	margin-right:2%;
}
#details .ykfxfunction .read {
	float:left;
	height:100%;
	overflow:hidden;
	position:relative;
font-size:14px;
}
#details .ykfxfunction .chan {
	float:left;
	width:25%;
	height:100%;
	overflow:hidden;
	position:relative;
}

</style>
	</head>
	<body>
	<s:if test="#parameters.source!=null">
    	<div id="header">
           <div id="return"><a href="javascript:history.go(-1)">&nbsp;</a></div>
           <div id="title" style="font-size: 20px;">美图欣赏</div>
        </div>
    </s:if>
		<div id="details">
			<div id="detailsimg">
				<img width="100%" src="<%=basePath%>upload/B_<s:property  value="buNews.img1"/>" />
			</div>
			<div class="ykfxfunction">
				<div class="read">
					阅读
					<s:property value="buNews.readnum" />
				</div>
				<div class="chan">
					<a href="javascript:Praise('<s:property value="buNews.fuid"/>','<%=type %>')"><img src="<%=basePath%>module/weixin/images/chan.png" style="width: 21px;height: 21px;margin-top: 5px;"/></a>
					<span id="sp"><s:property value="buNews.praise" /></span> 
				</div>
			</div>
			<s:property value="buNews.content" escape="false" />
		</div>
	</body>

</html>