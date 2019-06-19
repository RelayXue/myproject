<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  	<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title><s:if test="type==0 || type==null">手机</s:if><s:else>相机</s:else>作品排名</title>
    <style type="text/css">
    	html body{
    		width:100%;
    		padding:0px;
    		margin:0px
    	}
    	table{
    		width:99%;
    		height:60px
    	}
    	a{
    		text-decoration:none;
    		font-family:黑体;
    		color:#ffffff
    	}
    	a div{
    		width:80%;
    		height:17px;
    		padding:2px 5px 5px 5px;
    		border-radius:8px;
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
    		height:100%;
    		background:#ffffff;
    		margin-left:5%;
    		border:#B99260 solid 2px;
    		border-radius:15px;
    		overflow :yes;
    		overflow :auto;
    	}
    	.conDiv{
    		white-space:nowrap; 
			text-overflow:ellipsis; 
			-o-text-overflow:ellipsis; 
			overflow: hidden; 
    	}
    	
    </style>
  	<script type="text/javascript">
  		function getWidth(){
  			var tab=parseInt(document.getElementById("tab").offsetWidth);
  			var td=tab-(parseInt(document.getElementById("td1").style.width)+parseInt(document.getElementById("td2").style.width))-60;
  			var con=document.getElementsByName("conDiv");
  			for(var i=0;i<con.length;i++){
  				con[i].style.width=td+"px";
  			}
  		}
  	</script>
  </head> 
  <body onload="getWidth()">
  <%--背景图片--%>
  	<div class="backDiv">
    	<img src="<%=basePath%>Photography/PhotographyImg/beijing.png" class="backgroundUrl">
    </div>
    <div style="margin-left:5%;padding-top:5%;width:91%;height:5px">
    		<div style="width:50%;float:left;text-align:center"> 
    			<center>
    				<s:if test="type==0 || type==null">
	    				<a href="javascrpit:void(0)">
	    					<div style="background:#BDB9B6;">查看手机组排名</div>
	    				</a>
    				</s:if>
    				<s:else>
    					<a href="<%=basePath%>Photography!ranking?type=0">
    						<div style="background:#D15F5F;color:#000;box-shadow:2px 2px 0px #000">查看手机组排名</div>
    					</a>
    				</s:else>
    			</center>
    		</div>
    		<div style="width:50%;float:right;text-align:center">
    			<center>
    				<s:if test="type==1 || type==null">
	    				<a href="javascrpit:void(0)">
	    					<div style="background:#BDB9B6;">查看相机组排名</div>
	    				</a>
    				</s:if>
    				<s:else>
    					<a href="<%=basePath%>Photography!ranking?type=1">
	    					<div style="background:#D15F5F;color:#000;box-shadow:2px 2px 0px #000">查看相机组排名</div>
	    				</a>
    				</s:else>
    			</center>
    		</div>
    	</div>
    	<br/>
     <%--内容--%>
    <div style="width:100%;height:85%;padding-top:4%" id="tab">
    	<div class="content" id="bigDiv">
    		<s:iterator value="list_works" var="list" status="st">
    			<table >
    				<tr>
    					<td rowspan="2" style="font-size:30px;font-family:黑体;color:#A7A7A7;padding-top:10px;width:30px;text-align:center" id="td1"><s:property value="#st.index+1"/></td>
    					<td style="font-size:15;text-align:left;padding-top:10px;color:#B28750;font-family:黑体">
	    					<div class="conDiv" name="conDiv">
	    						编号<s:property value="#list.fuid"/> 标题：<s:property value="#list.title"/>
	    					</div>
    					</td>
    					<td style="font-size:15;text-align:right;padding-top:10px;color:#A7A7A7;font-family:黑体;width:60px" id="td2">综合评分</td>
    				</tr>
    				<tr>
    					<td style="font-size:15;color:#A7A7A7;font-family:黑体">作者：<s:property value="#list.peoplename"/></td>
    					<td style="font-size:15;text-align:right;color:#A7A7A7;font-family:黑体"><s:property value="#list.total"/></td>
    				</tr>
    				<tr>
    					<td colspan="3">
    						<hr style="border:1px dashed #B28750;"/>
    					</td>
    				</tr>
    			</table>
    		</s:iterator>
    	</div>
    </div>
  </body>
</html>
