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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/verification.css" />
			<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/validate.js"></script>

		<script>
	function update(){
		var update=$("#updateButton");
		var myform=$("#myform");
		var pass=$("#nmm2").val();
		myform.attr("action","<%=basePath%>User!UpdatePass?password="+pass);
		myform.submit();
		//alert("密码修改成功！请您重新登录！");
	}
	$(document).ready(function() {
		$("#myform").validate({
			rules : {
				ymm:{
					required: true,
					mypassword_repeat:true
				},
				nmm1:{
					required: true, 
					myuserpwd:true
				},
				nmm2:{
					required: true,
					equalTo:"#nmm1",
					myuserpwd:true
				}
			}
			 
			});
      });
	</script>
		<style>
</style>
	</head>
	<body style=" padding:0px 0px 0px 0px ; margin:0px 0px 0px 0px">

		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div id="content" >
				<div class="content-center" style="padding-left:5px;">
					
					<div class="manage-content" style="width: 460px;min-width: 350px">
						<div class="edit-content" style="width: 460px;min-width: 350px;magging-top:20px;" >
							<ul style="padding-bottom:22px;">
								<li class="titlel"  >
									原密码：
								</li>
								<li class="input-box"  >
									 <input type="password" id="ymm"  class="input-text"     value='' />
								</li>
							</ul>
							<ul style="padding-bottom:22px;">
								<li class="titlel">
									请输入新密码：
								</li>
								<li class="input-box" style="line-height: 22px;">
									 <input type="password" id="nmm1"  class="input-text"   name="baseUser.userpassword"  value='' />
								</li>
							</ul>
							<ul style="padding-bottom:22px;">
								<li class="titlel">
									 再次输入新密码：
								</li>
								<li class="input-box" style="line-height: 22px; ">
									 <input type="password"  name="baseUser.userpassword" id="nmm2"  class="input-text"  value='' />
								</li>
							</ul>
							<ul>
								<li class="titlel" />
								<li class="input-box" style="line-height: 22px; ">
								<input type="button" onclick="update()" style=" margin-top:1px;" id="resetButton" value="确认" class="btn2"  />
								</li>
							
							</ul>
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font><font color="red" size="3">温馨提示</font>：(修改成功将会跳到登录页面,请您牢记修改后的密码！)</font>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>

</html>