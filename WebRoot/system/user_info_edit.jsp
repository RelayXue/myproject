
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
	myform.attr("action","<%=basePath%>User!edit?id=${id}&skey=${skey}&OrganizeId=<%=OrganizeId%>");
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
					<%if(id==null){%>
					,
					myusername_repeat :true
					<%}%>
				},
				password:{
					required: true
				},
				mc:{
					required: true
				}
			} 
			});
	});
</script>
 
	</head>
	<body>

		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div id="content" >
				<div class="content-center">
					<div class="manage-title">
						<div class="manage-title-l">
							<img src="<%=basePath%>images/title_06.png" />
							<span class="hui-14-41-b">用户信息</span>
						</div>
					</div>
					<div class="manage-content">
						<div class="edit-content" >
							<ul>
								<li class="titlel" style="width: 100px" >
									用户名：
								</li>
								<li class="input-box" style="line-height: 22px;">
									<input type="text" id="username" name="baseUser.username" class="input-text" value='${baseUser.username }' />
									<input type="hidden" name="baseUser.fuid"  value='${id }' />
								</li>
								<li class="titler" style="margin-left: 0px;width: 100px">
									密码：
								</li>
								<li class="input-box"  style="line-height: 22px">
									<input type="text" id="password" name="baseUser.userpassword" class="input-text" value='${baseUser.userpassword }' />
								</li>
							</ul>
							<ul>
								<li class="titlel"  style=" width: 100px">
									姓名：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" id="mc" name="baseUser.realname" class="input-text" value='${baseUser.realname }' />
								</li>
								<li class="titler"  style="margin-left: 0px;width: 100px">
									地址：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" name="baseUser.useraddressid" class="input-text" value='${baseUser.useraddressid }' />
								</li>
							</ul>
							<ul>
								<li class="titlel"  style=" width: 100px">
									性别：
								</li>
								<li class="input-box" style="line-height: 22px">
									<s:if test='baseUser.sex == "男"'>
										<input type="radio" checked="checked" name="baseUser.sex" value="男" /> 男 <input type="radio" value="女" name="baseUser.sex" />女
									</s:if>
									<s:else>
										<input type="radio" checked="checked" name="baseUser.sex" value="男" /> 男 <input type="radio" value="女"  name="baseUser.sex" />女
									</s:else>
								</li>
								<li class="titler"  style="margin-left: 0px;width: 100px">
									手机：
								</li>
								<li class="input-box" style="line-height: 22px">
									<input type="text" name="baseUser.mobile" class="input-text" value='${baseUser.mobile }' />
								</li>
							</ul>
							<ul style="height: 70px">
								<li class="titlel" style="height: 50px; line-height: 70px;width: 100px">
									备注：
								</li>
								<li class="input-box">
									<textarea rows="4" cols="70" name="baseUser.description">${baseUser.description}</textarea>
								</li>
							</ul>
							<ul>
								<li class="titlel"  style=" width: 100px">

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