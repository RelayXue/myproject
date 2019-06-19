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
	<script type="text/javascript" src="<%=basePath%>Photography/js/zyFile.js"></script>
	
    <title>我要参加</title>
    <style type="text/css">
    	html body{
    		padding:0px;
    		margin:0px
    	}
    	a{
			text-decoration: none; 
    	}
    	input{
    		background:#E8E8E8;
    		border-radius:7px;
    		border:none;
    		height:30px;
    		font-size:20px;
    	}
    	textarea{	
    		background:#E8E8E8;
    		border-radius:7px;
    		border:none;
    		resize: none;
    		height:80px;
    		font-size:20px;
    	}
    	.backDiv{
    		position:absolute;
    		z-Index:-10;
    	}
    	.upload{
    		width:100%;
    		height:100%;
    		position:absolute;
    		z-Index:-100;
    		background:rgba(0, 0, 0, 0.1) none repeat scroll 0 0 !important;
    		vertical-align:middle; 
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
    		border-radius:15px;
    		padding-top:13px;
    		overflow :yes;
    		overflow :auto
    	}
    	.left{
    		width:90px;  
    		text-align:right;
    		padding:2% 0px 2% 0px;
    		font-family:黑体;
    		font-size:18px;
    		color:#B99260
    	}
    	.imgUrl{
    		width:97%;
    	}
    	.top{
    		text-align:center;
    		width:90%; 
    		height:30px;
    		background:#B99260;
    		margin-left:5%;
    		border-radius:8px;
    		color:#ffffff;
    		padding-top:6px;
    		font-family:黑体;
    		font-size:20px;
    	}
    </style>
  </head> 
  <body onload="kuan()">
   <div class="upload">
  	<center><div style="top:200px;position:relative;"><img src="<%=basePath%>Photography/PhotographyImg/upload.gif"></div></center>
  </div>
  <%--背景图片--%>
  	<div class="backDiv">
    	<img src="<%=basePath%>Photography/PhotographyImg/beijing.png" class="backgroundUrl">
    </div>
     <%--内容--%>
    <div style="width:100%;height:78%;padding-top:4%">
    <form action="<%=basePath%>Photography!saveWorks" method="post" id="myform" enctype="multipart/form-data">
    <input type="hidden" name="weixinid" value="<s:property value="weixinid"/>" id="weixinid"/>
    <input type="hidden" name="operation" value="0"/>
    	<div class="content">
    		<table>
    			<tr>
    				<td class="left">作品名称&nbsp;</td>
    				<td><input type="text" name="a20161characterworks.title" id="worksNmae" /></td>
    			</tr>
    			<tr>
    				<td class="left">作者姓名&nbsp;</td>
    				<td><input type="text" name="a20161characterworks.peoplename" id="worksPeople"/></td>
    			</tr>
    			<tr>
    				<td class="left">联系电话&nbsp;</td>
    				<td><input type="text" name="a20161characterworks.phone" id="phone"/></td>
    			</tr>
    			<tr>
    				<td class="left">通讯地址&nbsp;</td>
    				<td><input type="text" name="a20161characterworks.address" id="address"/></td>
    			</tr>
    			<tr>
    				<td class="left" valign="top">作品简介&nbsp;</td>
    				<td>
    					<textarea id="jianjie" name="a20161characterworks.introduce"></textarea>
    				</td>
    			</tr>
    			<tr>
    				<td class="left" valign="top" colspan="2" style="text-align:left;padding-left:3%">上传图片<span style="font-size:13px"> (最多上传6张作品)</span>
    				&nbsp;<img src="<%=basePath%>Photography/PhotographyImg/tianjia.jpg" style="width:15px" onclick="fileImg()"/></td>
    				<input type="file" id="file" name="filedata" style="display:none" multiple accept="image/gif,image/jpg,image/png,image/jpeg">
    			</tr>
    		</table>
    		<div id="imgs" style="width:97%;float:left;padding-left:3%"></div>
    		<div style="color:#C2C2C2;font-family:微软雅黑;padding-left:3%">最多添加6张照片，并确认是本人原创，每张照片大小不低于1M。</div>
    		<div style="text-align:left;color:red;font-size:15px;font-family:黑体;padding-left:3%" id="tishi"></div>
    		<div style="padding-left:3%;wdith:97%;padding-top:10px" id="img">
    		</div>
    		<br/>
    		 <div style="width:100%;height:9%">
		    	<div class="top" onclick="tijiao()">
		    		确认上传
		    	</div>
		    	<br/>
		    </div> 
    	</div>
    	<input type="hidden" name="imageServerId" id="imageServerId"  value=""/>
    	</form>
    </div>
    <div id="show" style="display: none;position: absolute;top: 10px;z-index: 10">  
				        <div id="photo" style="height: 400px;">  
				            <img style="height: 400px"/>
				        </div>  
				        <div id="info"></div>  
				    </div>
				    
	<form action="" method="post" id="iform"></form>
    <script type="text/javascript">
    $(function(){
    	$.ajax({
		    type:"POST",
		    data:"weixinid="+'${weixinid}',
		    dataType:"text",
		    url:"<%=basePath%>Photography!isWorks",
		    success: function(data) {
		    	if(data=="false"){
		    		alert("亲：您已经上传过作品！本次活动每人只能上传一次作品，现在为您跳转至作品展示界面")
		    		$("#iform").attr("action","Photography!exhibitionWorks?operation=0");
		    		$("#iform").submit();
		    		return;
		    	}else if(data=="false2"){
		    		alert("亲：只有关注【微信发布】公众号才能投票")
		    		$("#iform").attr("action","Photography!QRcode");
		    		$("#iform").submit();
		    		return
		    	}
		    }
		})
    });
    
    function fileImg(){
    	$("#file").on("change",function(){
    		$("#img").html("");
    		var file=document.getElementById("file")
    		var len=file.files.length
    		if(len>6){
    			$("#tishi").html("* 亲：最多只能上传6张图片哦")
    			file.outerHTML=file.outerHTML; 
    			return
    		}
    		var ht="";
    		for(var i=0;i<len;i++){
    			if(file.files[i].size<1024*1024){
    				$("#tishi").html("* 亲：上传图片每张必须不小于1M")
    				file.outerHTML=file.outerHTML; 
    				return
    			}else if(file.files[i].size>1024*1024*6){
    				$("#tishi").html("* 亲：上传图片每张必须不大于6M")
    				file.outerHTML=file.outerHTML; 
    				return
    			}else{
	    			ht+="<img src='"+window.URL.createObjectURL(file.files[i])+"' style='width:80px;height:80px;margin:5px'/>"
	    			ht+="<input type='hidden' value='"+file.files[i].name+"' name='imgFileName'/>"
    			}
    		}
    		$("#tishi").html("")
    		$("#img").append(ht);
    	})
    	$("#file").click();
    }
    
    function tijiao(){
    	
		    		if($("#worksNmae").val()==null || $("#worksNmae").val()==""){
		        		alert("亲：提交前请填写您的作品名称")
		        		return;
		        	}
		        	if($("#worksPeople").val()==null || $("#worksPeople").val()==""){
		        		alert("亲：提交前请填写您的作者姓名")
		        		return;
		        	}else{
		        		var reg = /^[\u4e00-\u9fa5]+$/;
		        		if(!(reg.test($("#worksPeople").val())) || ($("#worksPeople").val().length<2 || $("#worksPeople").val().length>4)){
		        			alert("亲：请填写您的真实名字")
		        			return;
		        		}
		        	}
		        	if($("#phone").val()==null || $("#phone").val()==""){
		        		alert("亲：提交前请填写您的联系电话")
		        		return;
		        	}else{
		        		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		        		if(!(reg.test($("#phone").val()))){
		        			alert("亲：请填写正确的手机号码！")
		        			return;
		        		}
		        	}
		        	if($("#address").val()==null || $("#address").val()==""){
		        		alert("亲：请输入您所在的位置");
		        		return;
		        	}
		        	if($("#file").val()==null || $("#file").val()==""){
		        		alert("亲：至少上传一张图片");
		        		return;
		        	}
		        	$(".upload").css("z-index","100");
		        	$("#myform").submit();
		    	}
    function kuan(){
    	var big=$(".content").width()-$(".left").width()-80
    	$("input:text").width(big)
    	$("#jianjie").width(big)
    }
   </script>
  </body>
</html>
