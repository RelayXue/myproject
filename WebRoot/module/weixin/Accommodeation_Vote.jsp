<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=(String)request.getAttribute("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0"> 



<title>最美乌镇人家</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/scrollpagination1.js"></script>
<script type="text/javascript">
	var P=100
	
	$(function() {
	var box=document.getElementsByName("box");
	for(var j=0;j<box.length;j++){
		document.getElementsByName("box")[j].disabled=""
		document.getElementsByName("box")[j].checked=""
	} 
	<%--var hh=$("#nr").height();
	 	var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>accommodation!Vote?weixinid=${weixinid}', 
			'contentData' : 'pageType=page&type=<%=type%>&indexPage='+indexPage,  
			'scrollTarget' : $("#nr"),  
			'heightOffset' : hh,  
			'beforeLoad' : function() {
				$('#loading').fadeIn();
				var len=document.getElementsByName("Q").length
				for(var i=0;i<len;i++){
					if(parseInt(document.getElementById(i).value)>P){
						P=P+100;
						i--
					}
					if(i+1==len){
						progress();
					}
				}
				
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&type=<%=type%>&skey=${skey}&indexPage='+indexPage;
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
		};  --%>
	}); 
	
	var fuid=""
	function getFuid(){
		var box=document.getElementsByName("box");
		for(var i=0;i<box.length;i++){
			if(box[i].checked){
				for(var j=0;j<box.length;j++){
					document.getElementsByName("box")[j].disabled="disabled"
				}
				document.getElementsByName("box")[i].disabled=""
				fuid=document.getElementsByName("box")[i].value
				box1=document.getElementsByName("box")[i]
				return
			}
			document.getElementsByName("box")[i].disabled=""
		}
	}
	function action(){
		if(fuid==""){
			alert("请选择旅店再投票！")
			return
		}
		var weixin=document.getElementById("weixin").value
		window.location.href="accommodation!Voting?id="+fuid+"&weixinid="+weixin+"&batch=${batch}";
	}
	function progress(){
		var len=document.getElementsByName("Q").length
		
		for(var i=0;i<len;i++){
			document.getElementById("d"+i).style.width=((parseInt(document.getElementById(i).value)/P) * 100)+"%"
		}
	}
	
	function search(){
		var skey=$("#skey").val();
		var weixin=document.getElementById("weixin").value;
		window.location.href="<%=basePath%>accommodation!Vote?weixinid="+weixin+"&skey="+skey;
		
	}
	
</script>
</head>

<style>
body {
	padding-left: 12px;
	padding-right: 12px;
	text-align:center
}
a{
	color:#103864;
	text-decoration: none
}
#head {
	margin-top: 14px;
	margin-bottom: 4px;
	float: left;
	display: block;
	padding-top: 5px;
	background-color: #eac873;
	width: 100%;
	border-radius: 5px;
	height: 30px;
	color: #555555;
	font-weight: bold;
	padding-top:10px;
	-webkit-box-shadow: 3px 3px 3px;  
	-moz-box-shadow: 3px 3px 3px;  
	text-align:left
}

#first {
	padding-left: 25px;
}

#nr {
	width: 99%;
	height: 75%;
	border: 1px solid #dcdadb;
	font-size: 0.24rem;
	overflow: scroll; /*任何时候都强制显示滚动条*/
	overflow: auto; /*需要的时候会出现滚动条*/
	overflow-y: auto; /*控制Y方向的滚动条*/
	background:#ffffff;
	padding:0px
	
}
.img_div{
	width:100%;
	height:100%;
	float:left;
	margin-left:-20px;
	margin-top:-8px;
	position:absolute;
	z-index:-10;
}
.img_big{
	width:100%;
	height:101%
}
.button_div{
	 padding:10px; width:300px; height:50px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px; 
    border-radius:5px;
    width:50%;
    padding-top:6px;
    height:25px;
    margin-left:25%;
    padding-top:20px
}
.button_div1{
    width:50%;
    height:25px;
    padding-top:20px;
}
.list{
	float:left;
	width:97%;
	height:14%;
	padding-left:3%;
	padding-top:10px;
	padding-bottom: 10px;
}
.list_left{
	float:left;
	width:20%;
	color:#414141;
	font-size:15px;
	font-family: 微软雅黑;
	text-align:left
}
.two{
	float:left;
	width:100%;
	font-family: 微软雅黑;
	font-size:17px;
}
</style>

<body>
	<input type="hidden" value='<s:property value="weixinid"/>' id="weixin">
	<div class="img_div"><img src="<%=basePath%>module/weixin/images/bj.png" class="img_big"></div>
	<div id="head">
		<a id="first">民宿投票列表</a>
	</div>
	<div >
	<input type="text" placeholder="请输入编号或民宿名称…" value="${skey }" style="border-radius:5px;color:#959898;height:25px;width:70%;float: left;"   id="skey" name="text"/>
	  <img src="<%=basePath%>module/weixin/images/sousuo.png" style="height:28px;border-radius:5px;float: left;margin-left: 10px" onclick="search()">		
	</div>
				
	<div id="nr">
	<s:iterator value="buAccommodation_list" var="list" status="st">
		<s:if test="#st.index%2==0">
		<div class="list">
		</s:if>
		<s:else>
		<div class="list" style="background:#FBF4E2">
		</s:else>
			<div class="list_left">
			<s:if test="#list.himg==null || #list.himg==''">
				<img src="<%=basePath%>module/weixin/images/tishi.png" style="width:100%">
			</s:if>
			<s:else>
				<a href='<%=basePath%>accommodation!selectById?fuid=<s:property value="#list.fuid"/>'><img src='<%=basePath%>upload/L_<s:property value="#list.himg"/>' style="width:100%;height:100%"></a>
			</s:else>
			</div>
			<div style="float:left;width:8px;height:1px"></div>
			<div class="list_left" style="width:52%;padding-top:2%">
				<div class="two" style="white-space:nowrap;text-overflow:ellipsis;text-overflow:ellipsis;overflow: hidden; "><s:property value="#list.morder"/>、<s:property value="#list.hname"/></div>
				<div style="width:1px;height:10%;float:left"></div>
				<div class="two" style="background:#DCDCDC;height:25px;width:100%;border-radius:7px;" id="big">
				
					<input type="hidden" id='<s:property value="#st.index"/>' value='<s:property value="#list.num2"/>'>
					
					<div style="background:#EBC874;text-align:center;height:100%;width:20%;border-radius:7px" id='d<s:property value="#st.index"/>' name="Q"></div>
				</div>
			</div>
			<div style="float:left;width:8px;height:1px"></div>
			<div class="list_left" style="text-align:center;padding-top:2%">
				<input type="checkbox" name="box" style="width:20px;height:20px" value='<s:property value="#list.fuid"/>' onclick="getFuid()" /><br/>
				<span id='piao<s:property value="#st.index"/>'>
				
				<s:if test="batch==1">
					<s:if test="#list.num==null || #list.num==''">0</s:if><s:else><s:property value="#list.num"/></s:else>
				</s:if>
				<s:elseif test="batch==2">
					<s:if test="#list.num2==null || #list.num2==''">0</s:if><s:else><s:property value="#list.num2"/></s:else>
				</s:elseif>
				
				</span>
				票
			</div>
		</div>
	</s:iterator>	
		<div id="content"></div>
	</div>
	<s:if test="ctime==true">
		<s:if test="is==true">
			<div class="button_div"><img src="<%=basePath%>module/weixin/images/toupiao.png" style="width:100%" onclick="action()"></div>
		</s:if>
		<s:else>
			<s:if test='isLuckDraw=="true"'>
				<div class="button_div1" style="width:100%;padding-left:0px"><a href="<%=basePath%>accommodation!goReward?weixinid=<s:property value="weixinid"/>"><span style="padding:10px;background:red;border-radius:8px;background:#EAC872;color:#000000">您获得一次抽奖，点击抽奖<span></a></div>
			</s:if>
			<s:else>
				<div class="button_div1" style="width:100%;padding-left:0px"><span style="padding:6px;background:red;border-radius:5px;background:#EAC872;color:#000000">您已经投过一次票了<span></div>
			</s:else>
		</s:else>
	</s:if>
	<s:else>
	<div class="button_div1" style="width:100%;padding-left:0px"><span style="padding:6px;background:red;border-radius:5px;background:#EAC872;color:#000000">投票活动已经结束<span></div>
	</s:else>
	
</body>
</html>

