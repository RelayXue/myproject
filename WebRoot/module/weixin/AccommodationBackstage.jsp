<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title>后台维护信息</title>
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		$("#page").AutoPage({
				totalPage : '${totalPage}', // 总页数
				currentPage: '${indexPage}',//当前页
				pageSize:'${pageSize}',    //每页显示条数
				url:'accommodation!SetUpVoteList?strWhere='+"${strWhere}",
				showNum : 6  // 显示数字
				,show:1
			});
	 });
	 function shousuo(){
	 	if(document.getElementById("mc").value=="请输入搜索的旅店名或者旅店所有人…"){
	 		window.location.href='accommodation!SetUpVoteList'
	 	}else{
	 		window.location.href='accommodation!SetUpVoteList?strWhere='+$("#mc").val()
	 	}
	 }
	 function qc(){
	 	if(document.getElementById("mc").value=="请输入搜索的旅店名或者旅店所有人…"){
	 		document.getElementById("mc").value="";
		 	document.getElementById("mc").style.color="#000000"
	 	}
	 }
	 function lk(){
	 	if(document.getElementById("mc").value==""){
	 		document.getElementById("mc").value="请输入搜索的旅店名或者旅店所有人…";
	 		document.getElementById("mc").style.color="#aaaaaa"
	 	}
	 }
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">二期旅店投票</a>
	</div>
	
	<div class="rsearch" style="float:left">
		<s:if test="strWhere==null || strWhere==''">
			<input id="mc" type="text" class="inp" onfocus="qc()" onblur="lk()" style="color:#aaaaaa;font-family:微软雅黑;width:250px" value='请输入搜索的旅店名或者旅店所有人…' />
		</s:if>
		<s:else>
			<input id="mc" type="text" class="inp" onfocus="qc()" onblur="lk()" style="font-family:微软雅黑;width:250px" value='<s:property  value="strWhere"/>' />
		</s:else>
		<a href="javascript:serach()" class="btn btn-success" title="搜索" onclick="shousuo()"><i class=" icon-search icon-white"></i>搜索</a>
	</div>
	
	<div class="rhandle" style="clean:both">
	</div>
	
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="3%">
						编号
					</th>
					<th width="10%">
						旅店名
					</th>
					<th width="6%">
						所有人
					</th>
					<th width="3%">
						是否投票
					</th>
					<th width="3%">
						一期票数
					</th>
					<th width="3%">
						二期票数
					</th>
					<th width="10%">
						操作
					</th>
				</tr>
				
				<s:iterator value="buAccommodation_list" var="list" status="stuts">
					<tr>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
						
						<td>
							<s:property value="#list.hname" />&nbsp;
						 </td>
						<td>
							<s:property value="#list.householder" />&nbsp; 
						</td>
						<td>
						<s:if test="#list.isvote==1">
							<span style="color: red;">是</span> 
						</s:if>
						<s:else>
						否
						</s:else>
						 &nbsp; 
						</td>
						<td>
							<s:property value="#list.num" />&nbsp; 
						</td>
						<td>
							<s:property value="#list.num2" />&nbsp; 
						</td>
						
						<td>
							<s:if test='#list.isvote==null || #list.isvote=="" || #list.isvote=="0"'>
							<a class="btn btn-danger" href='accommodation!setVote?isVote=true&AccommodationFuid=<s:property value="#list.fuid" />'><i class="icon-edit icon-white"></i>设为投票</a>
							</s:if>
							<s:else>
							<a class="btn btn-success" href='accommodation!setVote?isVote=false&AccommodationFuid=<s:property value="#list.fuid" />'><i class="icon-edit icon-white"></i>取消投票</a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="buWeixinactivity!delete" method="post">
				<input type="hidden" name="id" value="" id="ids" />
			</form>
		</div>
</body>

</html>