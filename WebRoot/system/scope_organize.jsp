
<%@ page import="java.util.*,com.gh.entity.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String RoleId = request.getParameter("RoleId");
	String MenuId = request.getParameter("MenuId");
	Map<String, List<BaseMenu>> baseMenu_map = (Map<String, List<BaseMenu>>) request.getAttribute("baseMenu_map");
	List<BaseMenu> baseMenu_list = (List<BaseMenu>) request.getAttribute("baseMenu_list");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/eayui/easyui.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 	$('#tt2').tree({
	        url:'<%=basePath%>Organize!ScopeTree?RoleId=<%=RoleId%>&MenuId=<%=MenuId%>',
	        checkbox:true,
	        animate :true,
	        onLoadSuccess:function(node, data){
	        	//$('#tt2').tree('collapseAll');
	        }
   		 });
	 });
		function add(){
			var nodes = $('#tt2').tree('getChecked');
			var s = '';
			$("#hid").empty();
			for(var i=0; i<nodes.length; i++){
				s+=nodes[i].id+",";
			}
			s=s.length>0?s.substring(0,s.length-1):s;
			$("#hid").append("<input type='hidden' name='id' value='"+s+"' />");
 	 		$("#myform").ajaxSubmit({
                    type: 'post',
                    url: "Scope!ScopeQx?RoleId=<%=RoleId%>&MenuId=<%=MenuId%>",

			success : function(data) {
				alert("分配成功！");
				window.parent.close();
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	}
</script>
		<style>
</style>
	</head>
	<body>
		<div id="content">
			<div class="content-center" style="height: 510px">
				<ul id="tt2"></ul>
				 <div style="text-align: center;">
					<input type="button" onclick="add()" id="resetButton" value="保存" class="input-page-btn" />
				</div>
			</div>
		</div>
		<form id="myform" action="" method="post">
			<div id='hid'>
				<input type='hidden' />
			</div>
		</form>
	</body>

</html>