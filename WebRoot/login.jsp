<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=request.getParameter("type");
	String tname="";
	if("1".equals(type)){
		tname="综合管控平台";
	}else if("2".equals(type)){
		tname="三维应用系统";
	}else if("3".equals(type)){
		tname="二维码识别系统";	
	}else if("4".equals(type)){
		tname="虚拟游";	
	}		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/layout.css" />
		<script language="javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/jquery.cookie.js"></script>
		<script>
	$(document).ready(function() {
		if (window.self != window.top) {
			window.top.location.href = 'login.jsp';
		}
		$("#username").focus();
		var cookie_username=$.cookie('login_cookie_username');
		var cookie_userpwd=$.cookie('login_cookie_password');
		if(cookie_username!=null&&cookie_username!=""){
			$("#username").val(cookie_username);
			$("#jzmm").attr("checked","checked");
		}
		if(cookie_userpwd!=null&&cookie_userpwd!=""){
			$("#password").val(cookie_userpwd);
			$("#jzmm").attr("checked","checked");
		}
	});
	
	function login(){
		var check=$("#jzmm").attr("checked");
				if(check==true){
					var cookie_username=$("#username").val();
					var cookie_userpwd=$("#password").val();
					$.cookie('login_cookie_username',$.trim(cookie_username), { expires: 30 });
					$.cookie('login_cookie_password',cookie_userpwd, { expires: 30 });
				}else{
					$.cookie('login_cookie_username',null);
					$.cookie('login_cookie_password',null);
				}
			   	$("#myform").submit();
	}
	function keyLogin(){
		  if (event.keyCode==13)  
		      $("#myform").submit();
	}
</script>
		<title>旅游生活服务管理系统</title>
	</head>

	<body  onkeydown="keyLogin();" class="loginbg">
		<form action="Login" method="post"  id="myform" name="myform">
		<div class="login2">
			<div class="login_warp login2warp">
				<h2>
					<%=tname %>
				</h2>
				<div class="logintab">
					<div class="clearfix">
						<input  type="text" id="username" name="username"  class="logininp inp1" />
							<input name="password" id="password" type="password" class="logininp inp2" />
							<input value=""  onclick="login()" type="button" class="loginbtn1"  /> 
							<input type="hidden" value="<%=type %>" name="type" />
					</div>
					<div class="logininfo">
						<label>
							<input name="" type="checkbox" id="jzmm" value="" />
								<span>记住密码</span>  
						</label>
						<div class="info">
							欢迎登录乌镇国际旅游区！ 
						</div>
						  <span style="color: red; margin-left:50px; font-size: 14px">${error }</span>
					</div>
				</div>
				<div class="loginbtn2">
					<!--<a href="guide.jsp" target="_self" title="返回"></a>-->
				</div>
			</div>
		</div>
		</form>
	</body>
</html>
