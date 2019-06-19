
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
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 });
	 function serach(){
		 var skey=$("#mc").val();
		 var url="User!Usershow?skey="+skey+"&OrganizeId=${OrganizeId}";
		 window.location.href=url;
	 }
	 function add(){
		 var url="<%=basePath%>system/menu_edit.jsp";
		 window.location.href=url;
	 }
	 function delpar(id){
		 $.ajax({
	    url: "Menu!deletePar?parentid=" + id + "&time=" + new Date(),
	    async: false,
	    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    dataType: "text",
	    success: function(data) {
	        if (data == "0") {
	            alert("请先删除子菜单");
	        } else {
	             location.reload();
	        }
	    }
	});
	 }
	 
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div>
			<div class="manage-title">
				<div class="manage-title-l"
					style="width: 150px; border: 0px; padding: 0;">
					<img src="<%=basePath%>images/title_06.png" />
					<span class="hui-14-41-b">数据集权限管理</span>
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
						角色名称
						</td>
          				<td  style="width: 120px"   >
						路径
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
					
						<td    ><strong> <s:property value="#list.menuName" /></strong>&nbsp;
						</td>
						<td    ><s:property value="#list.menuUrl" />&nbsp;
						</td>
						<td  >
							<s:property value="#list.menuOrder"  />&nbsp;
						</td>
						<td   align="center" >
							<a class="blue-12"  href="<%=basePath%>system/menu_edit.jsp?id=<s:property value="#list.fuid"  />">新增子目录</a>
							<a class="blue-12"  href="<%=basePath %>Menu!update_show?id=<s:property value="#list.fuid"  />">修改</a>
							<a  class="blue-12" href="javascript:delpar('<s:property value="#list.fuid"  />')" >删除</a>
						</td>
					</tr>
							 <s:if test="#list.children!=null">
						         <s:iterator var="clist" value="#list.children" status="s">
						         	<s:if test="#s.odd==true">
										<tr class="row2">
									</s:if>
									<s:else>
										<tr class="row1">
									</s:else>
										<td style="color: blue;">----<s:property value="#clist.menuName" />&nbsp;
										</td>
										<td><s:property value="#clist.menuUrl" />&nbsp;
										</td>
										<td  >
											<s:property value="#clist.menuOrder"  />&nbsp;
										</td>
										<td   align="center" >
											<a class="blue-12"  href="<%=basePath%>system/menu_edit.jsp?id=<s:property value="#clist.fuid"  />">新增子目录</a>
											<a class="blue-12"  href="<%=basePath %>Menu!update_show?id=<s:property value="#clist.fuid"  />">修改</a>
											<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
												href="Menu!delete?id=<s:property value="#clist.fuid"  />&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
										</td>
									</tr>
						         	 <s:if test="#clist.children!=null">
							         <s:iterator var="cclist" value="#clist.children" status="ss">
							         	<s:if test="#ss.odd==true">
											<tr class="row2">
										</s:if>
										<s:else>
											<tr class="row1">
										</s:else>
											<td style="color: red;">---------<s:property value="#cclist.menuName" />&nbsp;
											</td>
											<td><s:property value="#cclist.menuUrl" />&nbsp;
											</td>
											<td  >
												<s:property value="#cclist.menuOrder"  />&nbsp;
											</td>
											<td   align="center" >
												<a class="blue-12"  href="<%=basePath %>Menu!update_show?id=<s:property value="#cclist.fuid"  />">修改</a>
												<a onclick="if(!confirm('确定删除！')){return false;}" class="blue-12"
													href="Menu!delete?id=<s:property value="#cclist.fuid"  />&skey=${skey}&OrganizeId=${OrganizeId}">删除</a>
											</td>
										</tr>
							         
							         
							         </s:iterator>
							         </s:if>
						         
						         </s:iterator>
						         </s:if>
					</s:iterator>
					 </table>
			</div>
		</div>
	</div>
	</body>

</html>