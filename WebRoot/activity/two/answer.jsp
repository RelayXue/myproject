<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
	<head>
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>我要答题</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px
}

.bg_img {
	background-image: url(img/bg2.png);
	background-size: 100%;
	background-position-y: 40px;
}

header {
	width: 100%;
	height: 40px;
	line-height: 40px;
	text-align: center;
	background: #000000;
}

.web_content {
	width: 85%;
	height: 150px;
	margin: 0px auto;
	border: 3px solid #d1c0a5;
	margin-top: 20%;
	background: #FFFFFF;
	padding: 10px;
	padding-top: 50px;
}

.header_title {
	font-size: 20px;
	color: #ffffff;
}

.t_btn {
	width: 85%;
	height: 40px;
	line-height: 40px;
	margin: 0px auto;
	text-align: center;
	background: #D1C0A5;
	margin-top: 20px;
	border-radius: 5px;
	color: #FFFFFF;
}
</style>
	</head>
	<body>
		<div style="width: 100%; height: 100%; position: absolute; z-index: -100;">
			<img src="<%=basePath%>activity/two/img/bg2.png" style="width: 100%; height: 100%" />
		</div>
		<div style="float: left; width: 100%">
			<div class="web_content">
				<div class="tm_title f20 m30" style="font-weight: 600;">
					第<span id="num"></span>题:
				</div>
				<div class="tm_text f14 tc" id="title"
					style="width: 100%; margin: 0px auto; padding-top: 20px; text-align: center">
				</div>
			</div>
			<div id="xx"></div>
		</div>
	</body>
	<input type="hidden" id="subjectId">
	<input type="hidden" id="year">
	<form action="<%=basePath%>poetryChallenge!isAllyear" method="post" id="myForm">
		<input type="hidden" id="weixinid" value="${weixinid}" name="weixinid">
		<input type="hidden" id="numCount" value="10" name="numCount">
	</form>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
  	$(function(){
  		getSubject(1)
  	})
  	function next(e){
  		var num=parseFloat($("#num").html());
  		var v=$(e).children().eq(0).val();
  		
  		$.ajax({
			url: "<%=basePath%>poetryChallenge!answerFun",
			type:"post",
			data:"answer="+v+"&id="+$("#subjectId").val()+"&weixinid="+$("#weixinid").val(),
			success:function(){
				if($("#year").val()==v){
					if(num>=1){
			  			$("#myForm").submit();
			  		}else{
			  			num++
			  			getSubject(num);//换题
			  		}
		  		}else{
		  			$("#numCount").val(parseInt($("#num").html())-1)
		  			$("#myForm").submit();
		  		}
			}
		})
  	}
  	
  	function getSubject(num){//获取题目的方法
  		$.ajax({
			url: "<%=basePath%>poetryChallenge!getSubject",
			type:"post",
			data:"num="+num,
			dataType: "json",
			success: function(data) {
				var year=data.yes;
				if(year==0){
					$("#year").val("A");
				}else if(year==1){
					$("#year").val("B");
				}else if(year==2){
					$("#year").val("C");
				}else if(year==3){
					$("#year").val("D");
				}
			
				$("#title").html(data.title);
				$("#subjectId").val(data.fuid);
				var ht="";
				ht+="<div class='t_btn' onclick='next(this)'><input type='hidden' value='A'/>"+data.one+"</div>";
				ht+="<div class='t_btn' onclick='next(this)'><input type='hidden' value='B'/>"+data.two+"</div>";
				if(data.three!=null && data.three!=""){
					ht+="<div class='t_btn' onclick='next(this)'><input type='hidden' value='C'/>"+data.three+"</div>";
				}
				if(data.four!=null && data.four!=""){
					ht+="<div class='t_btn' onclick='next(this)'><input type='hidden' value='D'/>"+data.four+"</div>";
				}
				$("#xx").html(ht);
				$("#num").html(num)
			},
		});
  	}
  </script>
</html>
