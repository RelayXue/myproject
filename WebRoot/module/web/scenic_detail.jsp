<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>景点详情</title>
<link href="<%=basePath%>module/web/css/pic_play.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>module/web/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>module/web/js/jQuery-jcImg.js" language="javascript"></script>
<script src="<%=basePath%>module/web/js/imgReady.js" language="javascript"></script>
<script type="text/javascript">
$(function(){
	$("#imags").find("img").each(function(){
		var new_image = new Image();
		new_image.src = $(this).attr("src");
		
		imgReady($(this).attr("src"), function (obj) {
			//alert('size ready: width=' + this.width + '; height=' + this.height);
			//height="350" width="625"
			if(this.width>625&&this.height<350){
				$(obj).attr("width",625); 
			}else if(this.width<625&&this.height>350){
				$(obj).attr("height",350);
			}else if(this.width>625&&this.height>350){
				var a = 625/this.width,b=350/this.height;
				if(a<=b){
					$(obj).attr("width",625);
				}else{
					$(obj).attr("height",350);
				}
			}else{
				$(obj).attr("width",this.width);
				$(obj).attr("height",this.height);
			}
		},$(this));
	});
	
	$("#jcImg").jcImg({
		speed : 400, // 动画速度
		height :64,  // 缩略图高度设置
		width : 115, // 缩略图宽度设置
		textBg : .8, // 文本透明度设置
		page : 5     // 每页缩略图显示个数
	});
	
});

function praiseClick(type,id){
	 var val = $("#praiseDiv").html();
	 if(val.length!=0){
		 $("#praiseDiv").html(parseInt(val)+1);
		 var data = "q="+$("#praiseDiv").html()+"&type="+type+"&id="+id;
		 $.ajax({
			  type: 'POST',
			  async:false,
			  url: "web/search_praiseClick",
			  data: data,
			  dataType: 'html',
			  success: function(data){
			  }
		 });
	 }
}
</script>
</head>

<body style="background-image:none; background-color:#f8f8ec;">
<div id="jcImg" class="TmpStyle" style=" background-color:#f8f8ec;">
	<ul id="imags">
	    <s:if test="!ba.images.isEmpty()">
		<s:iterator var="img" value="ba.images" status="status">
    	<li style="text-align: center;" ><a><img id="img${status.index}" src="<%=basePath%>upload/${img}" title="${ba.fullname}" /></a></li>
        </s:iterator>
        </s:if>
    </ul>
</div>


<div style="font-size:22px; padding:8px;">${ba.fullname}</div>
<div style="font-size:14px; color:#333; height:100px; border-bottom:1px solid #ccc; padding:0 8px; overflow-y:auto;">${ba.introduction}</div>
<div style="padding:8px; overflow:hidden"><div class="_eye_btn fleft" style="margin-right:10px;">${ba.browse}</div><div id="praiseDiv" onclick="praiseClick('scenic','${ba.fuid}');" style="cursor: pointer;" class="_point_like_btn fleft">${ba.praise}</div></div>
</body>
</html>
