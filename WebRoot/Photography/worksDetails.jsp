<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
  <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title>作品详情</title>
    <style type="text/css">
    	html body{
    		padding:0px;
    		margin:0px
    	}
    	.backDiv{
    		position:absolute;
    		z-Index:-5;
    	}
    	.backgroundUrl{
    		width:100%;
    		height:100%;
    		position:fixed
    	}
    	.bigDiv{
    		width:95%;
    		height:79%;
    		padding-top:7%;
    		padding-left:5%; 
    		
    	}
    	.content{
    		width:90%;
    		background:#ffffff;
    		border-radius:10px;
    		border:#B99260 solid 2px;
    		padding-left:3%;
    		padding-top:2%; 
    	}
    	.imgDiv{
    		width:95%; 
    		height:220px;
    		border:#B99260 solid 1px;
    	}
    	.jt-left{
    		position:absolute;
    		left:0px;
    		top:0px;
    		width:50px;
    		height:50px
    	}
    	.jt-right{
    		position:absolute;
    		left:0px;
    		top:0px;
    		width:50px;
    		height:50px
    	}
    	.yincang{
    		white-space:nowrap; 
			text-overflow:ellipsis; 
			-o-text-overflow:ellipsis; 
			overflow: hidden; 
    	}
    </style>
  </head> 
  <body onload="jt()">
  <div id="bigDiv" style="padding-top:5%;width:100%;height:100%;position:absolute;z-index:-10;float:left;background:rgba(0, 0, 0, 0.5) none repeat scroll 0 0 !important;">
 	 <img src="" id="bigImg" style="width:100%;" onclick="none()">
  </div>
  
  <input type="hidden" value="<s:property value="type"/>" id='type'>
  <input type="hidden" value="<s:property value="id"/>" id="fuid">
  <%--背景图片--%>
  	<div class="backDiv">
    	<img src="<%=basePath%>Photography/PhotographyImg/beijing.png" class="backgroundUrl">
    </div>
    <div class="bigDiv">
    	<div class="content">
    		<div style="color:#B99260;font-family:微软雅黑;padding-bottom:4px"> 
    			编号：<s:property value="a20161characterworks.fuid"/>
    		</div>
    		<div class="imgDiv" id="img">
    			<img src="<%=basePath%>Photography/PhotographyImg/jt-left.png" class="jt-left" onclick="left()" id="jt_left"/>
    			
    			<input type="hidden" value="1" id="num">
    			<%
    				List<String> url=(List<String>)request.getAttribute("imgUrl");
    				for(int i=0;i<url.size();i++){
    					%>
    					<img src="<%=basePath%>upload/<%=url.get(i)%>" style="width:100%;height:100%;display:block;<%if(i>0){%>display:none<%}%>" id='<%=i+1%>' name="img" onclick="imgEnlarge(this.id)"/>
    					<%
    				}
    			%>
    			
    			<img src="<%=basePath%>Photography/PhotographyImg/jt-right.png" class="jt-right" onclick="right()" id="jt_left"/>
    		</div>
    		<div style="width:100%;text-align:center;padding-top:8px;color:#B0B0B0;font-size:14px;font-family:幼圆">第<span id="cc">1</span>张，共<s:property value="countUrl"/>张</div>
    		<div style="width:95%;margin-top:8px;font-size:15px;font-family:微软雅黑">
    			<div class="yincang" style="float:left;width:60%;color:#B6905C">
    				标题:<s:property value="a20161characterworks.title"/>
    			</div>
    			<div class="yincang" style="float:right;width:40%;text-align:right;color:#8B8B8B">
    				作者：<s:property value="a20161characterworks.peoplename"/>
    			</div>
    		</div>
    		<br/>
    		<hr style="width:95%;margin-left:-1;border: #C6A572 1px dashed"/>
    		<%--<div style="width:95%;margin-top:8px;font-size:15px;font-family:微软雅黑;color:#B6905C">
    			通讯地址：<s:property value="a20161characterworks.address"/>
    		</div>
    		<hr style="width:95%;margin-left:-1;border: #C6A572 1px dashed"/>--%>
    		<div style="width:95%;color:#8B8B8B;font-size:13px;font-family:幼圆;word-break:break-all">
    			<s:property value="a20161characterworks.introduce" escape="false"/>
    		</div>
    		<s:if test="operation==1">
	    		<br/><br/>
	    		<div style="width:95%">
	    		<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fPhotography!worksDetails%3fid%3d<s:property value="a20161characterworks.fuid"/>%26operation%3d1&response_type=code&scope=snsapi_base&state=123#wechat_redirect">
	    			<div style="width:100%;height:35px;border-radius:5px">
	    				<img src="<%=basePath%>Photography/PhotographyImg/dianzan.png" style="width:100%;height:100%"/>
	    				<div style="float:left;position:relative;left:40%;top:-35px;height:0px;color:#fff;font-size:35px"><s:property value="a20161characterworks.num"/></div>
	    			</div>
	    		</a>
	    		</div>
    		</s:if>
    		<br/>
    	</div>
    </div>
    
    <form action="" method="post" id="myform"></form>
  </body>
  <script type="text/javascript">
  $(function() {
	  if($("#type").val()=="2"){
		  alert("亲：投票成功！")
	  }else if($("#type").val()=="1"){
		  alert("亲：您已经投过一次票了！")
	  }
  })
  	function jt(){
  		var dinggao=document.getElementById("img").offsetTop;
  		var hei=$(".imgDiv").height()/2;
  		$(".jt-left").css("top",dinggao+hei-30);
  		$(".jt-right").css("top",dinggao+hei-30);
  		
  		var bodyWid=$("body").width()
  		var aa=bodyWid*0.05;
  		bodyWid=bodyWid-aa*2
		var bb=bodyWid*0.03
		$(".jt-left").css("left",aa+bb+20)
		$(".jt-right").css("left",$("body").width()-(aa+bb+77))
  	}
  	function left(){
  		var num=$("#num").val();
  		var img=document.getElementsByName("img");
  		var len=img.length;
  		if(num-1<1){
  			num=len;
  		}else{
  			num--
  		}
  		for(var i=0;i<len;i++){
  			img[i].style.display="none";
  		}
  		$("#num").val(num);
  		$("#cc").html(num)
  		document.getElementById(num).style.display="block";
  	}
  	function right(){
  		var num=parseInt($("#num").val())
  		var img=document.getElementsByName("img");
  		var len=img.length;
  		if(num+1>len){
  			num=1;
  		}else{
  			num++
  		}
  		for(var i=0;i<len;i++){
  			img[i].style.display="none";
  		}
  		$("#num").val(num);
  		$("#cc").html(num)
  		document.getElementById(num).style.display="block";
  	}
  	function imgEnlarge(id){
  		document.getElementById("bigImg").src=document.getElementById(id).src;
  		document.getElementById("bigDiv").style.zIndex=10;
  	}
  	function none(){
  		document.getElementById("bigDiv").style.zIndex=-10;
  	}
  </script>
</html>
