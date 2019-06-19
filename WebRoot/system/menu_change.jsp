
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
		<ul id="tt2" class="easyui-tree" data-options="url:'tree_data.json',checkbox:true,
			onClick: function(node){
				$(this).tree('toggle', node.target);
			},
			onContextMenu: function(e,node){
				e.preventDefault();
				$(this).tree('select',node.target);
				$('#mm').menu('show',{
					left: e.pageX,
					top: e.pageY
				});
			}"
	></ul>
	</div>
	</body>

</html>