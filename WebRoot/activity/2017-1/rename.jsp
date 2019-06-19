<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>征集路名详情</title>
    <style type="text/css"> 
    	html,body{
    		margin:0px;
    		padding:0px;
    	}
    	.ba{
    		width:100%;height:100%;
    		position: absolute;
    		z-index:-999;
    	}
    	.ba img{
    		width:100%;
    		height:100%;
    	}
    	.conDiv{
    		width:75%;
    		height:75%;
    		margin-left:10%;
    		padding:2.5%;
    		border-radius: 7px;
    		overflow:scroll;
    		text-align:center
    	}
    	table{
    		width:100%;
    	}
    	td{
    		font-family: 微软雅黑; 
    		font-size:14px;
    		padding-top:10px
    	}
    	th{
    		font-size:14px;
    		padding-top:10px
    	}
    	a{
    		color:#000000
    	}
    	input[type='text']{
    		width:90%
    	}
    	.button{
    		background:#C9E039;
    		padding:3px 0px 5px 0px;
    		border-radius: 7px;
    		font-family: 微软雅黑;
    		color:#ffffff;
    		width:80%;
    		margin-left:10%
    	}
    </style>
  </head>
  <body>
    <div class="ba">
    	<img src="<%=basePath%>activity/2017-1/img/bg2.png"/>
    </div>
    <div style="height:8%"></div>
    <div class="conDiv">
    	<table>
    		<tr>
    			<th style="width:40%;text-align:right">原路名：</th>
    			<td>${a20171bridgeroad.temporaryname }</td>
    		</tr>
    		<tr>
    			<th style="width:40%;text-align:right;vertical-align:top;">介绍：</th>
    			<td>${a20171bridgeroad.remark }</td>
    		</tr>
    	</table>
    	<br/>
    	<s:if test="weixinid==null || weixinid==''">
    		<!-- 获取不到微信ID的时候不给填写资料 -->
    		<b style="color:blue">若想给路、桥取名<br/><a href="<%=basePath%>2017/one!QRcode" style="color:blue">请点击这里关注该公众号</a></b>
    	</s:if>
    	<s:else>
    		<!-- 当这个微信ID没有取名过时 -->
    		<s:if test="a20171user==null">
	    		<form action="<%=basePath%>2017/one!save" method="post" id="myform">
		    		<input type="hidden" name="a20171user.bridgeroadTemporaryName" value="${a20171bridgeroad.temporaryname }"/>
		    		<input type="hidden" name="a20171user.bridgeroadTemporaryId" value="${a20171bridgeroad.fuid }"/>
		    		<input type="hidden" name="a20171user.weixinid" value="${weixinid }"/>
		    		<table>
			    		<tr>
			    			<th style="width:40%;text-align:right">新路名：</th>
			    			<td><input type="text" name="a20171user.bridgeroadName" id="roadName"/></td>	
			    		</tr>
			    		<tr>
			    			<th style="width:40%;text-align:right">联系人：</th>
			    			<td><input type="text" name="a20171user.name" id="peopleName"/></td>	
			    		</tr>
			    		<tr>
			    			<th style="width:40%;text-align:right">联系号码：</th>
			    			<td><input type="text" name="a20171user.phone" id="phone"/></td>	
			    		</tr>
			    		<tr>
			    			<th style="width:40%;text-align:right">验证码：</th>
			    			<td>
			    				<input type="text" style="width:30%" id="code"/>
			    				<input type="button" style="width:60%;margin-top:5px" value="获取验证码" onclick="timing()"/>
			    				<input type="hidden" id="trueCode" value=""/>
			    			</td>	
			    		</tr>
		    		</table> 
		    		<br/>
		    		<div class="button" onclick="submit()">提 交</div>
		    	</form>
	    	</s:if>
	    	<!-- 该微信ID已经给路、桥取过一次名字 -->
	    	<s:else>
	    		<!-- 当该参与者是给该路、桥命名,展示其名的名字 -->
	    		<s:if test="is=='true'">
	    			<table>
	    				<tr>
			    			<th style="width:40%;text-align:right">联系人：</th>
			    			<td>${a20171user.name }</td>	
			    		</tr>
			    		<tr>
			    			<th style="width:40%;text-align:right">联系号码：</th>
			    			<td>${a20171user.phone }</td>	
			    		</tr>
			    		<tr>
			    			<th style="width:40%;text-align:right">你的命名：</th>
			    			<td>${a20171user.bridgeroadName}</td>	
			    		</tr>
		    		</table>
	    		</s:if>	
	    		<s:else>
	    			<span style="color:red">您已经参与过命名活动，但是不是给该路命名</span>
	    		</s:else>
	    	</s:else>
    	</s:else>
    </div>
  </body>
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
  	function submit(){
  		if($("#roadName").val()=="" || $("#peopleName").val()=="" || $("#phone").val()==""){
  			alert("请完善您的资料！")
  			return
  		}
  		if($("#trueCode").val()==""){
  			alert("请获取验证码！")
  			return
  		}else{
  			if($("#code").val()==""){
  				alert("请填写验证码！")
  				return
  			}else{
  				if(!($("#trueCode").val()==$("#code").val())){
  					alert("您输入的验证码有误！")
  					return
  				}
  			}
  		}
  		alert("取名成功，感谢您的参与！")
  		$("#myform").submit();
  	}
  	
  	function timing(){
  		var phone=$("#phone").val();
  		if(phone==""){
  			alert("请填写您的联系号码！")
  			return "false"
  		}
  		if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
  	        alert("联系方式有误，请重填");
  	        return false; 
  	    }
  		
  		var timer = setInterval(function(){
  			var btValue=$("input[type='button']").val()
  	  		if(btValue=="获取验证码"){
  	  			$("input[type='button']").attr("disabled","disabled");
  	  			$("input[type='button']").val("59");
	  	  		$.ajax({
	  				url: "<%=basePath%>2017/one!isPhone",
	  				type:"post",
	  				data:"phone="+phone,
	  				dataType:"json",
	  				success: function(msg){
	  					if(msg.code=="200"){//200是该号码跟微信id都没有参加过活动
	  						$("#trueCode").val(msg.value);
	  					}else{//该号码或者微信id其中一个参与过活动
	  						$("input[type='button']").attr("disabled",false);
	  		  				$("input[type='button']").val("获取验证码");
	  		  			 	clearInterval(timer); 
	  						alert(msg.value)
	  						return
	  					}
	  				}
	  			})
  	  		}else{
  	  			if(btValue=="1"){
  	  				$("input[type='button']").attr("disabled",false);
  	  				$("input[type='button']").val("获取验证码");
  	  			 	clearInterval(timer); 
  	  			}else{
  	  				btValue=parseInt(btValue)-1
  	  				$("input[type='button']").val(btValue);
  	  			}
  	  		}
  		},1000);
  	}
  </script>
</html>
