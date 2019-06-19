<%@ page language="java"
	import="java.util.*,com.gh.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String code=request.getParameter("code");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>数据字典</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
		 var code='<%=code%>';
	 jQuery(document).ready(function($) {
		  $("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'Datadictionary!showChild2?code=${code}&skey=${skey}',
					showNum : 6  // 显示数字
					,show:1
				});
	 });
	 function serach(){
	 var skey=$("#mc").val();
	 var url="Datadictionary!showChild2?skey="+skey+"&code="+code;
	 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>system/TypeEditor.jsp?code="+code;
	 window.location.href=url;
	 }
	 function one(){
	 window.location.href="Datadictionary";
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
					<span class="hui-14-41-b">数据字典</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<li class="btn">
							<input type="button" onclick="one()" class="common" value="返回一级" />
						</li>
						<s:if test="com.hisAdd==true">
						<li class="btn">
							<input type="button" onclick="add()" class="common" value="新增二级" />
						</li>
						</s:if>
						<li class="btn">
							<input type="button" class="common" value="查询" onclick="serach()"/>
						</li>
						<li class="input-box" style="width: 240px">
						名称:<input type="text" class="input-text" onfocus="javascript:this.value=''" id="mc" value="${skey}" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content" style="margin-top: 20px">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td    >
						名称
						</td>
          				<td    >
						代码
						</td>
						<td >
						排序
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
					<s:iterator value="baseDatadictionary_list" var="list"    status="stuts">  
					<s:if test="#stuts.odd==true">
						<tr class="row1">
					</s:if>
					<s:else>
						<tr class="row2">
					</s:else>
					
						<td>
						 <s:property value="#list.fullname" />&nbsp;
						</td>
						<td    ><s:property value="#list.code" />&nbsp;
						</td>
						<td    >
						<s:property value="#list.sequence" />&nbsp;
						</td>
						<td   align="center" >
						<s:if test="com.hisUpdate==true">
							<a class="blue-12"  href="<%=basePath %>Datadictionary!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&code=<%=code %>">修改</a>
						</s:if>
						<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="Datadictionary!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&code=<%=code %>">删除</a>
						</s:if>
							<a href="Datadictionary!showChild3?code=<s:property value="#list.code"  />" class="blue-12" >编辑子目录</a>
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