 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

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
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>

		<script>
	function edit(){
		var myform=$("#myform");
		myform.attr("action","<%=basePath%>buPublicfacilities!edit?skey=${skey}&indexPage=${indexPage}");
		myform.submit();
}
function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
function res(){
	 window.location.href="javascript:history.go(-1);";
}
	$(document).ready(function() {
		$("#myform").validate({
			rules : {
				fullname:{
					required: true
				},
				cwidth:{
					number: true
				} 
			} 
			});
      });
	</script>
		<style>
 
</style>
	</head>
<body>
		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div class="inright">
				<div class="rightnav">
					<a href="#" class="active">公共设备管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									名称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buPublicfacilities.fullname"  value="${buPublicfacilities.fullname}" />
									 <input type="hidden"     name="buPublicfacilities.fuid"  value='${buPublicfacilities.fuid}' />
								</td>
							</tr>
					 
							<tr>
								<td width="75">
									地址：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buPublicfacilities.address"  value='${buPublicfacilities.address}' />
								     <input type="hidden"  class="input-text" id="fx"    name="buPublicfacilities.fx"  value='${buPublicfacilities.fx}' />
									   <input type="hidden"  class="input-text"   id="fy" name="buPublicfacilities.fy"  value='${buPublicfacilities.fy}' />
								</td>
							</tr>	
						 <tr>
						 <td width="75">
									类型：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buPublicfacilities.type"  value='${buPublicfacilities.type}' />
								</td>
							</tr>	
							<tr>
								<td width="75">
									介绍：
								</td>
								<td>
								 <textarea  style='border: 1px solid #bfbfbf' rows="4" cols="70" name="buPublicfacilities.introduction">${buPublicfacilities.introduction}</textarea>
								</td>
							</tr>	
							<tr>
								<td width="75" colspan="2">
									地图：
								</td>
								 
							</tr>	
							<tr>
								<td width="75" colspan="2">
								 <input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
									 <div style="width: 900px;height: 600px" > 
									 	<iframe width="100%" id="ifmap" height="100%" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=attractions"></iframe>
									 </div>
								</td>
							</tr>	
							 
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<input name="" type="button" class="btn2" value="提交" onclick="edit()" />
									<input name="" type="button" value="返回" class="btn4" onclick="res()" /> 
								</td>
							</tr>
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>