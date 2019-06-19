
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

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
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/demo.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script>
	 jQuery(document).ready(function($) {
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'OperatingLog?skey=${skey}',
					showNum : 6  // 显示数字
					,show:1
				});
	 });
	 function serach(){
		 var skey=$("#mc").val();
		 skey=skey=='请输入用户名'?'':skey;
		 var url="OperatingLog?skey="+skey;
		 window.location.href=url;
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div class="content-center" >
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">系统操作日志</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<li class="btn">
							<input type="button" class="common" value="查询" onclick="serach()"/>
						</li>
						<li class="input-box" style="width: 240px">
						关键字：<input type="text" class="input-text" onfocus="javascript:this.value=''" id="mc" value="${skey==null?'请输入用户名':skey}" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td    >
						用户名
						</td>
          				<td    >
						操作时间
						</td>
						<td >
						操作内容
						</td>
       				 </tr>
					<s:iterator value="baseOperationLog_list" var="list"    status="stuts">  
					<s:if test="#stuts.odd==true">
						<tr class="row1">
					</s:if>
					<s:else>
						<tr class="row2">
					</s:else>
					
						<td>
							<s:property value="#list.username" />&nbsp;
						</td>
						<td>
							<s:date name="#list.createdate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td    >
							<s:property value="#list.operating" />&nbsp;
						</td>
					</tr>
					</s:iterator>
					 </table>
			</div>
			<div id="page"></div>
		</div>
	</div>
	</body>

</html>