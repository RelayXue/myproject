
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String OrganizeId=request.getParameter("OrganizeId");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 });
	 function add(){
		 var url="<%=basePath%>system/role_edit.jsp?OrganizeId=<%=OrganizeId%>";
		 window.location.href=url;
	 }
	 function showQx(RoleId){
	 	$("#ifr").attr("src","<%=basePath %>Menu!ShowMenuRole?RoleId="+RoleId+"&OrganizeId=${OrganizeId}");
	 	 $('#dd').dialog({
		           	modal:true,
					title:"菜单列表"
		          });
	 }
	 function close(){
			$('#dd').dialog('close');
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
					<span class="hui-14-41-b">角色管理</span>
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
          				<td  style="width: 120px"   >
						名称
						</td>
						<td    >
							是否有效
						</td>
						<td    >
							备注
						</td>
						<td    >
							排序
						</td>
						<td align="center" class="noborder"  >
							操作
						</td>
       				 </tr>
					<s:iterator value="baseRole_list" var="list"    status="stuts">  
					<s:if test="#stuts.odd==true">
						<tr class="row1">
					</s:if>
					<s:else>
						<tr class="row2">
					</s:else>
					
						<td    ><s:property value="#list.realname" />&nbsp;
						</td>
						<td>
							<s:if test="#list.enabled==1">有效</s:if>
							<s:else>无效</s:else>
							 
						</td>
						<td  >
							<s:property value="#list.description"  />&nbsp;
						</td>
						<td  >
							<s:property value="#list.sortcode"  />&nbsp;
						</td>
						<td   align="center" >
							<a class="blue-12"  href="javascript:showQx('<s:property value="#list.fuid"  />','${OrganizeId }')">
							分配权限</a>
							<s:if test="#list.enabled==1">
							<a class="blue-12"  href="<%=basePath %>Role!valid?id=<s:property value="#list.fuid"  />&skey=0&OrganizeId=${OrganizeId}">
							禁用</a>
							</s:if>
							<s:else>
							<a class="blue-12"  href="<%=basePath %>Role!valid?id=<s:property value="#list.fuid"  />&skey=1&OrganizeId=${OrganizeId}">
							启用</a>
							</s:else>
							<a class="blue-12"  href="<%=basePath %>Role!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&OrganizeId=${OrganizeId}">修改</a>
							<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
								href="Role!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
						</td>
					</tr>
					</s:iterator>
					 </table>
			</div>
			<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 390px;">
					<iframe id="ifr" width="100%" height="100%" src="" scrolling="yes" frameborder="0"></iframe>
				</div>
			</div>
		</div>
	</div>
	</body>

</html>