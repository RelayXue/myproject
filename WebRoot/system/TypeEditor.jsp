<%@ page language="java" import="java.util.*,com.gh.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id=request.getParameter("id");
	String code=request.getParameter("code");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>人员群组信息</title>
		<style type="text/css">
</style>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/verification.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
			
		<script>
var code='<%=code%>';
function edit(){
	var myform = $("#myform");
	var fuid=$("#fuid").val();
	if(fuid!=null&&fuid.length>0){
		if(code!='null'){
			myform.attr("action","Datadictionary!update?id=<%=id%>&code="+code+"&skey=${skey}");
		}else{
			myform.attr("action","Datadictionary!update?id=<%=id%>&skey=${skey}");
		}
	}else{
		if(code!='null'){
			myform.attr("action","Datadictionary!addChild?code="+code);
		}else{
			myform.attr("action","Datadictionary!add");
		}
	}
	myform.submit();
}

function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
function res(){
				 window.location.href="javascript:history.go(-1);";
	}
	$(document).ready(function() {
			var code=$("#code").val();
			if(code!=null&&code.length>3){
				code=code.substring(code.length-3,code.length);
				$("#code").attr("value",code);
			}
			var fuid=$("#fuid").val();
			var tt=true;
			if(fuid!=null&&fuid.length>0){
				tt=false;
			}
       		$("#myform").validate({
			rules : {
			mc : "required",
			code:{
				rangelength:[3,3],
				code_repeat:tt
			}
			},
			messages : {
			mc : "名称不能为空",
			code: {
			rangelength:"代码输入有误，长度必须为3位数字",
			required:"代码不能为空"
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
							<span class="hui-14-41-b">数据字典</span>
						</div>
					</div>
					<div class="manage-content">
						<div class="edit-content">
							<ul>
								<li class="titlel">
									名称：
								</li>
								<li class="input-box">
									 <input type="text"  class="input-text" id="mc" name="baseDatadictionary.fullname"  value='${baseDatadictionary.fullname }' />
									 <input type="hidden"  id="fuid"   name="baseDatadictionary.fuid"  value='${baseDatadictionary.fuid}' />
								</li>
								<li class="titler">
									排序：
								</li>
								<li class="input-box">
									 <input type="text"  name="baseDatadictionary.sequence"  class="input-text" value='${baseDatadictionary.sequence }' /> 
								</li>
							</ul>
							<ul >
								<li class="titlel">
									代码：
								</li>
								<li class="input-box" style="line-height: 22px">
									 <input type="text" id="code"  name="baseDatadictionary.code"   class="input-text"  value='${baseDatadictionary.code}' />
									 <input type="hidden" id="codeHid"      value='<%=code %>' />
								</li>
								<li class="titler">
									 
								</li>
								<li class="input-box" style="line-height: 22px" >
									 
								</li>
							</ul>
							 
							<ul style="height: 70px">
								<li class="titlel" style="height: 50px;line-height: 70px">
									备注：
								</li>
								<li class="input-box">
									<textarea  rows="4" cols="70" name="obShip.description">${obShip.description}</textarea>
								</li>
							</ul>
							<ul>
								<li class="titlel">
									
								</li>
								<li class="input-box" style="line-height: 22px">
								</li>
								<li class="input-box" style="line-height: 22px;width: 400px">
								<input type="button" onclick="edit()" id="updateButton" class="input-page-btn"
									value="保存" />
								<input type="button" onclick="resetB()" id="resetButton" value="重置" class="input-page-btn"
									  />
								<input type="button" name="" id="backButton" value="返回" onclick="res()"
									class="input-page-btn" />
									
								</li>
							</ul>
							
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>