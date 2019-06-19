<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 String type=(String)request.getAttribute("type");
%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>诗歌投票</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
			var ch="";
			$(function(){
				var cc=localStorage.getItem("cc")
				if(cc!=null && cc!=""){
					ch=cc;
					var c1="";
					for(var i=0;i<3;i++){
						if(cc.length>0){
							c1=cc.substr(0,cc.indexOf(","));
							cc=cc.substr(cc.indexOf(",")+1,cc.length);
							document.getElementById(c1).checked=true;
						}
						if($("input[name='box']:checked").length >= 3){
							$("input[name='box']").not("input:checked").attr("disabled","disabled");  
						}
					}
				}
			})
			
			function choose(obj){
				if(obj.checked){
					//插入
					ch+=obj.id+",";
				}else{
					//移除
					ch=ch.replace(obj.id+"," , "");
				}
				localStorage.setItem("cc",ch);
				
				var str=document.getElementsByName("box");
				var num=0
				for(var i=0;i<str.length;i++){
					if(str[i].checked){
						num++
						if(num>=3){
							for(var j=0;j<str.length;j++){
								document.getElementById(j).disabled="disabled"
							}
							for(var k=0;k<str.length;k++){
								if(str[k].checked){
									document.getElementById(k).disabled=""
								}
							}
						}else{
							for(var j=0;j<str.length;j++){
								document.getElementById(j).disabled=""
							}
						}
					}
				}
			}
			
			function Vote(){
				if($("#is").val()==false || $("#is").val()=="false"){
					alert("亲：您已经投过一次票了，每人只能投一次票，一次最多只能投三首！")
					return
				}
				var str=document.getElementsByName("box");
				var onefuid=null;
				var twofuid=null;
				var threefuid=null;
				for(var j=0;j<str.length;j++){
					if(str[j].checked){
						if(onefuid==null){onefuid=document.getElementById(j).value}else
						if(twofuid==null){twofuid=document.getElementById(j).value}else
						if(threefuid==null){threefuid=document.getElementById(j).value}
					}
				}
				if(onefuid!=null || twofuid!=null || threefuid!=null){
					if(confirm("亲:每人每次只能投一次，一次最多投三首，您确定选投这几首诗歌么？")){	
						alert("投票成功；感谢您的参与")
						$("#ifrom").attr("action","<%=basePath%>fnews!updateVote?onefuid="+onefuid+"&twofuid="+twofuid+"&threefuid="+threefuid)
						$("#ifrom").submit();
					}
				}else{
					alert("请选择投票的对象")
					return
				}
			}
			function sousuo(){
				$("#myform").submit();
			}
			
		</script>
		<style>
			html,body{
				text-align:center;
				padding:0px;
				margin:0px;
				width:100%;
				height:100%
			}
			.div_top{
				width:100%;
				height:100%;
				float:left;
				position:fixed;
				z-index:-10;
			}
			.div_top img{
				filter:alpha(opacity=30); -moz-opacity:0.3; -khtml-opacity: 0.3; opacity: 0.3;
			}
			.div_middle{
				width:80%;
				height:70%;
				float:left;
				margin-left:10%;
				overflow: scroll;
			}
			.button{
				margin-top:2%
			}
			a{
				text-decoration: none;
				color:#000000
			}
			.left{
				float:left;
				width:60%;
				text-align:left;
				font-size:15px;
				margin-top:6px;
				font-family:微软雅黑;
				white-space:nowrap; 
				text-overflow:ellipsis; 
				text-overflow:ellipsis; 
				overflow: hidden; 
			}
			.middle{
				float:left;
				width:20%;
				font-size:15px;
				margin-top:6px;
				font-family:微软雅黑;
			}
			.right{
				float:right;
				width:20%;
				font-size:15px;
				margin-top:6px;
				font-family:微软雅黑;
			}
			.biaoti{
				width:80%;
				float:left;
				margin-left:10%;
				margin-top:5%
			}
			.checkbox {
				 width: 16px;
				 height: 16px;
				}
			.shousuo{
				width:80%;
				height:3%;
				float:left;
				margin-left:10%;
				margin-top:12%;
				text-align:left;
			}
			.but{
				width:60%;
				float:left;
				margin-top:15px;
				margin-left:20%;
				background:#B82726;
				padding:5px;
				border-radius:5px;
				font-family:微软雅黑;
				font-size:14px;
				color:#ffffff
			}
		</style>
	</head>
	<body>
		<div class="div_top"><img src="<%=basePath%>module/weixin/images/bg2.png" style="width:100%;height:100%"></div>
		<div class="shousuo">
			<div style="float:left;width:70%;margin-right:5%">
			<form action="<%=basePath%>fnews!likeFind" method="post" id="myform">
				<input type="text" value="${text}" placeholder="请输入搜索诗名或作者…" style="border-radius:5px;color:#959898;height:22px;width:100%" id="text" name="text"/>
				<input type="hidden" value='<s:property value="is"/>' name="is" id="is"/>
			</form>
			
			</div>
			<img src="<%=basePath%>module/weixin/images/sousuo.png" style="height:28px;border-radius:5px;" onclick="sousuo()">
		</div>
		<div class="biaoti">
			<div class="left" style="font-size:20px;color:#895F8B;"><b>&nbsp;&nbsp;&nbsp;&nbsp;诗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</b></div>
			<div class="middle" style="font-size:20px;color:#895F8B;"><b>作者</b></div>
			<div class="right" style="font-size:20px;color:#895F8B;"><b>票数</b></div>
		</div>
		<div class="div_middle">
			<s:iterator value="buWeixinactivity_list" var="list" status="st">
				<div class="left" style="padding-bottom:5px">
					<s:if test="#list.worder.length()==1">
						0<s:property value="#list.worder"/>
					</s:if>
					<s:else>
						<s:property value="#list.worder"/>
					</s:else>
					<input type="checkbox" name="box" id='<s:property value="#st.index"/>' class="checkbox" onClick="choose(this)" value='<s:property value="#list.fuid"/>' />&nbsp;&nbsp;<a href='<%=basePath%>fnews!voteEntity?fuid=<s:property value="#list.fuid"/>'><s:property value="#list.title"/></a>
				</div>
				<div class="middle" style="padding-bottom:5px"><s:property value="#list.author"/></div>
					<div class="right" style="padding-bottom:5px">
					<s:if test="#list.number == null">
						0
					</s:if>
					<s:else>
						<s:property value="#list.number"/>
					</s:else>
					</div>
			</s:iterator>
		</div>
		<div class="but" onclick="Vote()">
			投&nbsp;&nbsp;票
		</div>
	</body>
	<form action="" id="ifrom" method="post">
	</form>
</html>

