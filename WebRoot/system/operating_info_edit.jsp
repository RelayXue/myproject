
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String MenuId=request.getParameter("MenuId");
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
	myform.attr("action","<%=basePath%>Operating!edit?id=${id}&MenuId=<%=MenuId%>");
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
				fullname:{
					required: true
				}
			} 
			});
	});
</script>
 
	</head>
	<body>

		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div id="content">
				<div class="content-center">
					<div class="manage-title">
						<div class="manage-title-l">
							<img src="<%=basePath%>images/title_06.png" />
							<span class="hui-14-41-b">操作信息</span>
						</div>
					</div>
					<div class="manage-content">
						<div class="edit-content">
							<ul>
								<li class="titlel">
									名称：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" id="fullname" name="baseOperating.fullname" class="input-text" value='${baseOperating.fullname }' />
									<input type="hidden" name="baseOperating.fuid"  value='${id }' />
								</li>
							</ul>
							<ul>
								<li class="titlel">
									代码：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" id="mc" name="baseOperating.code" class="input-text" value='${baseOperating.code }' />
								</li>
							</ul>
							<ul style="height: 70px">
								<li class="titlel" style="height: 50px; line-height: 70px">
									备注：
								</li>
								<li class="input-box">
									<textarea rows="4" cols="70" name="baseOperating.description">${baseOperating.description}</textarea>
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
	</body>

</html>