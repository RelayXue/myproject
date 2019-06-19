
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String OrganizeId=request.getParameter("OrganizeId");
	String id=request.getParameter("id");				
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/verification.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>

		<script>
function edit(){
	var update=$("#updateButton");
	var myform=$("#myform");
	myform.attr("action","<%=basePath%>Menu!edit?parentid=<%=id%>");
	myform.submit();
}
function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
function re(){
	window.location.href="javascript:history.go(-1);";
}
	$(document).ready(function() {
		$("#myform").validate({
			rules : {
				username:{
					required: true
				},
				sort:{
					required: true,
					number :true
				} 
			} 
			});
	});
</script>
 
	</head>
	<body>
<div class="content-center" >
		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div id="content">
				<div class="">
					<div class="manage-title">
						<div class="manage-title-l">
							<img src="<%=basePath%>images/title_06.png" />
							<span class="hui-14-41-b">菜单信息</span>
						</div>
					</div>
					<div class="manage-content">
						<div class="edit-content">
							<ul>
								<li class="titlel">
									名称：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" id="username" name="baseMenu.menuName" class="input-text" value='${baseMenu.menuName }' />
									<input type="hidden" name="baseMenu.fuid"  value='${id }' />
								</li>
								<li class="titler">
									排序：
								</li>
								<li class="input-box"  style="line-height: 22px">
									<input type="text" id="sort" name="baseMenu.menuOrder" class="input-text" value='${baseMenu.menuOrder }' />
								</li>
							</ul>
							<ul>
								<li class="titlel">
									路径：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text"  name="baseMenu.menuUrl" class="input-text" value='${baseMenu.menuUrl }' />
								</li>
								<li class="titler">
									图片：
								</li>
								<li class="input-box"  style="line-height: 22px">
									 <input type="file" name="uploadpic" class="input-text" />
								</li>
							</ul>
							<ul>
								<li class="titlel">

								</li>
								<li class="input-box" style="line-height: 22px">
								</li>
								<li class="input-box" style="line-height: 22px; width: 400px">
									<input type="button" onclick="edit()" id="saveButton" value="保存" class="input-page-btn" />
									<input type="button" onclick="resetB()" id="resetButton" value="重置" class="input-page-btn" />
									<input type="button" name="" id="backButton" value="返回" onclick="re()" class="input-page-btn" />
								</li>
							</ul>

						</div>
					</div>
				</div>
			</div>
		</form>
		</div>
	</body>
</html>