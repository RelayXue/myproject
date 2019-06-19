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
 jQuery(document).ready(function() {
	 //--------------分页-------------
	$("#page").AutoPage({
			totalPage : '${totalPage}', // 总页数
			currentPage: '${indexPage}',//当前页
			pageSize:'${pageSize}',    //每页显示条数
			url:'<%=basePath%>2017/one!list',
			showNum : 6 , // 显示数字
			show:1
		});
 });
 //---添加---
 function add(id){
 	var url="<%=basePath%>/2017/one!edit?id="+id;
	window.location.href=url;
 }
</script>
<style>
	a{
		color:#457DC7
	}
</style>
 
</head>
<body>
	<div class="rightnav"> 
		<a href="#" class="active">征集路名基础信息 &nbsp;</a>
	</div>
	<div class="rhandle" style="padding-top:5px;">
		<a class="btn btn-success" href="<%=basePath%>2017/one!edit"><i class="icon-plus-sign icon-white"></i>添加</a>
	</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="23%">
						征集路名暂定名
					</th>
					<th width="23%">
						描述
					</th>
					<th width="23%">
						创建时间
					</th>
					<th width="23%">
						操作
					</th>
				</tr>
				<s:iterator value="list_a20171bridgeroad" var="li">
				<tr style="color:#457DC7">
					<td>
						<s:property value="#li.temporaryname"/>
					</td>
					<td>
						<s:property value="#li.remark"/>
					</td>
					<td>
						<s:date name="#li.createtime" format="yyyy-MM-dd"/>
					</td>
					<td>
						<a href="<%=basePath%>2017/one!edit?id=<s:property value="#li.fuid"/>">编辑</a>&nbsp;&nbsp;
						<a href="<%=basePath%>2017/one!delete?id=<s:property value="#li.fuid"/>">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
</body>
</html>