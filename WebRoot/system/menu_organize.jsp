
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
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 	$('#tt2').tree({
	        url:'<%=basePath %>Menu!treeOrg?OrganizeId=${OrganizeId}',
	        checkbox:true,
	        id:'node.fuid',
	        text:'node.fuid',
	        animate :true,
	        onLoadSuccess: function(node, param) { 
	       	  $('#tt2').tree("collapseAll");
	        }
   		 });
	 	
	 });
		function add(){
			var nodes = $('#tt2').tree('getChecked');
			var s = '';
			$("#hid").empty();
			for(var i=0; i<nodes.length; i++){
			 	var children = $('#tt2').tree('getChildren', nodes[i].target);
			 	if(children.length==0){
			 		var parent = $('#tt2').tree('getParent', nodes[i].target);
			 		s=parent.id+"~"+nodes[i].id;
					$("#hid").append("<input type='hidden' name='id' value='"+s+"' />");
			 	}
				
			}
			$("#myform").attr("action","Organize!Assign?OrganizeId=${OrganizeId}");
			$("#myform").submit();
		}
		 	
</script>
		<style>
</style>
	</head>
	<body>
	<div id="content">
		<div class="content-center" style="height: 510px">
			 <ul id="tt2"></ul> 
			 <div style="text-align:center;">
			 	<input type="button"   onclick="add()" id="resetButton" value="保存" class="input-page-btn" />
			 </div>
		</div>	 
	</div>
	<form id="myform" action="" method="post">
	<div id='hid'>
		<input type='hidden'/>	
	</div>
	</form>
	</body>

</html>