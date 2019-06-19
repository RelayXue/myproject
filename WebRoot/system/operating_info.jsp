
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
				$("#all").change(function() {
		            if (!$("#all").attr("checked")) {
		               $("input[name=uch]").removeAttr('checked');
		            }else if($("#all").attr("checked")){
		            	 $("input[name=uch]").attr('checked','checked');
		            }
		           }); 
	 });
	 function add(){
		 var url="<%=basePath%>system/operating_info_edit.jsp?MenuId=${MenuId}";
		 window.location.href=url;
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div class="content-center">
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">操作权限信息</span>
				</div>
				<div class="manage-title-r">
					<ul>
						<li class="btn">
							<input type="button" onclick="add()" class="common" value="新增" />
						</li>
					</ul>
				</div>
			</div>
			<div class="manage-content">
				<table width="100%" cellSpacing="0" cellPadding="0" class="table">
       			 <tr class="title">
          				<td>
						名称
						</td>
          				<td>
						备注
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
					<s:iterator value="baseOperating_list" var="list"    status="stuts">  
					<s:if test="#stuts.odd==true">
						<tr class="row1">
					</s:if>
					<s:else>
						<tr class="row2">
					</s:else>
						<td>
						<s:property value="#list.fullname" />&nbsp;
						</td>
						<td  >
							<s:property value="#list.description"  />&nbsp;
						</td>
						<td   align="center" >
							<s:if test="#list.code!='base'">
							<a class="blue-12"  href="<%=basePath %>Operating!update_show?id=<s:property value="#list.fuid"  />&MenuId=${MenuId}">修改</a>
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="Operating!delete?id=<s:property value="#list.fuid"  />&MenuId=${MenuId}">删除</a>
							</s:if>
						</td>
					</tr>
					</s:iterator>
					 </table>
			</div>
		</div>
	</div>
	<form id="form2" method="post" action="<%=basePath %>User!AssignRoles?OrganizeId=${OrganizeId}">
			<input type="hidden" name="userid"  id="userid" />
			<input type="hidden" name="roleid"  id="roleid" />
	</form>
	<div style="display: none;">
		<div id="dd" data-options="iconCls:'icon-save'" style="padding:5px;width:400px;height:600px;">
			<iframe width="100%" height="100%" frameborder="0" src="<%=basePath%>Role!showChange"></iframe>
		</div>
	</div>
	</body>

</html>