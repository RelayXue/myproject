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
  	<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination2.js"></script>
    <title>作品展示</title>
    <style type="text/css">
    	html body{
    		padding:0px;
    		margin:0px
    	}
    	a{
			text-decoration: none; 
    	}
    	td{
    		font-family:微软雅黑;
    	}
    	input{
    		height:80%;
    		border:none;
    		outline:none;
    		font-size:18px;
    		color:#E3D3B9;
    		font-family:微软雅黑;
    		border-radius:0px
    	}
    	.backDiv{
    		position:absolute;
    		z-Index:-100;
    	}
    	.backgroundUrl{
    		width:100%;
    		height:100%;
    		position:fixed
    	}
    	.souhuo{ 
    		width:93%;
    		height:45px;
    		background:#C6A572;
    		padding-top:2%;
    		padding-left:30px
    	}
    	.ss{
    		float:left;
    	}
    	.content{
    		width:100%; 
    		margin-top:20px;
    		padding-left:1%;
    	}
    	.jianjie{
    		width:42%; 
    		float:left; 
    		border:2px solid #C6A572;
    		border-radius:10px;
    		background:#ffffff;
    		margin:0px 5px 10px 5px;
    		padding:5px;
    	}
    	.jianjie1{
    	}
    </style>
  </head> 
  <body onload="center()">
  <%--背景图片--%>
  	<div class="backDiv">
    	<img src="<%=basePath%>Photography/PhotographyImg/beijing.png" class="backgroundUrl">
    </div>
    <div class="souhuo">
   		<div class="ss">
    		<img src="<%=basePath%>Photography/PhotographyImg/left.png" style="height:80%">
    	</div >
    	<div class="ss">
    		<input type="text" value="输入编号或者作者名称搜说作品" onblur="reduction()" onfocus="clean()" id="ssContent">
   		</div>
   		<div class="ss">
    		<img src="<%=basePath%>Photography/PhotographyImg/right.png" style="height:80%" onclick="soushuo()">
    	</div>
    </div>
    <div class="content">
    <s:iterator value="list_works" var="list" status="st">
    	<div class="jianjie">
    		<table style="width:100%" onclick="tiaozhuan(this.id)" id="<s:property value="#list.fuid"/>">
    		<input type="hidden" value="<s:property value="#list.fuid"/>">
    			<tr>
    				<td style="width:70%;color:#C6A572">编号：<s:property value="#list.fuid"/></td>
    				<td style="text-align:right;width:30%;color:#6096D1">
    					<b> 详情</b>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" style="width:100%"><img src="<%=basePath%>upload/<s:property value="#list.imgurl"/>" style="width:100%;height:120px;border:1px solid #C6A572"></td> 
    			</tr>
    			<tr>
    				<td colspan="2" style="color:#C6A572">标题：<s:property value="#list.title"/></td>
    			</tr>
    			<tr>
    				<td colspan="2"><hr style="border: #C6A572 1px dashed"/></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="font-size:14px;color:888888">作者：<s:property value="#list.peoplename"/></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="font-size:14px;color:888888;width:100%">
    				<div class="jianjie1">
    					<s:if test="#list.introduce==null || #list.introduce==''">
    						&nbsp;&nbsp;&nbsp;
    					</s:if>
    					<s:else>
    						<s:property value="#list.introduce"/>
    					</s:else>
    				</div>
    				</td>
    			</tr>
    		</table>
    		<s:if test="#list.operation==1">
	    		<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fPhotography!vote%3fid%3d<s:property value="#list.fuid"/>&response_type=code&scope=snsapi_base&state=123#wechat_redirect">
	    		<!-- <a href="<%=basePath%>Photography!vote?id=<s:property value="#list.fuid"/>">-->
	    			<div style="width:100%;border-radius:5px;text-align:center;padding-bottom: 5px" id="fuid<s:property value="#st.index"/>">
				    	<input type="hidden" value="<s:property value="#list.fuid"/>">
				    	<img src="<%=basePath%>Photography/PhotographyImg/dianzan.png" style="width:100%"/>
				    	<div style="float:left;position:relative;left:43%;top:-25px;height:0px;color:#fff;font-size:19px"><s:property value="#list.num"/></div>
				    </div>
				</a>
    		</s:if>
    	</div>
    </s:iterator>
    	<div id="content"></div>
    </div>
   
    <form action="" method="post" id="iform">
    	<input type="hidden" value="" id="fuid" name="id"/>
    	<input type="hidden" value="<s:property value="operation"/>" id="operation" name="operation"/>
    </form>
    <form action="" method="post" id="sform"></form>
  </body>
  
  <script type="text/javascript">
  	$(function() {
	  		if('${type}'=="2"){
				  alert("亲：投票成功！")
				  $("#sform").attr("action","<%=basePath%>Photography!exhibitionWorks?operation=1")
				  $("#sform").submit();
				  return
			  }else if('${type}'=="1"){
				  alert("亲：您已经投过一次票了！")
				   $("#sform").attr("action","<%=basePath%>Photography!exhibitionWorks?operation=1")
				    $("#sform").submit();
				   return
			  }else if('${type}'=="4"){
				  alert("亲：发布作品成功！")
				   $("#sform").attr("action","<%=basePath%>Photography!exhibitionWorks?operation=0")
				    $("#sform").submit();
				   return
			  }else if('${type}'=="3"){
				  alert("亲：发布作品失败，后退可重新上传作品")
				   $("#sform").attr("action","<%=basePath%>Photography!exhibitionWorks?operation=0")
				    $("#sform").submit();
				   return
			  }
		var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>Photography!exhibitionWorks?operation=${operation}&soushuo=${soushuo}&type=${type}', 
			'contentData' : 'pageType=page&&indexPage='+indexPage,
			'scrollTarget' : $(window), 
			'windowHeight': window.innerHeight,
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 100) { 
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
		});

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};
	});
  	function center(){
  		var width=$(".souhuo").width()-120
  		$("input").css("width",width)
  		$(".souhuo").css("width",window.innerWidth-30)
  	}
  	function clean(){
  		if($("input[type='text']").val()=="输入编号或者作者名称搜说作品"){
	  		$("input").attr("value","");
	  		$("input").css("color","#000000")
  		}
  	}
  	function reduction(){
  		if($("input[type='text']").val()==""){
  			$("input").css("color","#E3D3B9")
  			$("input").attr("value","输入编号或者作者名称搜说作品");
  		}
  	}
  	function tiaozhuan(id){
  		var tb=$("#"+id).children("input").get(0)
  		$("#fuid").val($(tb).val());
  		$("#iform").attr("action","<%=basePath%>Photography!worksDetails");
  		$("#iform").submit();
  	}
  	function soushuo(){
  		var cc="";
  		if($("#ssContent").val()=="输入编号或者作者名称搜说作品"){
  		}else{
  			cc=$("#ssContent").val();
  		}
  		$("#sform").attr("action","Photography!exhibitionWorks?weixinid="+'${weixinid}'+"&operation="+'${operation}'+"&soushuo="+cc)
  		$("#sform").submit();
  	}
  </script>
</html>
