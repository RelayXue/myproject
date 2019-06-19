 
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
		myform.attr("action","<%=basePath%>obVideo!edit?skey=${skey}&indexPage=${indexPage}");
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
					<a href="#" class="active">视频管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									<strong>	名称：</strong>
								</td>
								<td>
									 <input type="text"  class="input-text" id="fullname"  name="obVideo.fullname"  value="${obVideo.fullname}" />
									 <input type="hidden"     name="obVideo.fuid"  value='${obVideo.fuid}' />
								</td>
								<td width="75">
										设备编号：
								</td>
								<td>
								   <input type="text"   class="input-text" name="obVideo.camid"  value='${obVideo.camid }' />
									 <input type="hidden"  id="fx"    name="obVideo.fx"  value='${obVideo.fx}' />
									 <input type="hidden"  id="fy"   name="obVideo.fy"  value='${obVideo.fy}' />
								</td>
							</tr>
							 
							<tr>
								<td width="75">
									 <strong>通道号：</strong>
								</td>
								<td>
								  <input type="text" id="channel"   name="obVideo.channel"  class="input-text" value='${obVideo.channel }' /> 
								</td>
								 
							</tr>	
							 
							<tr>
							</tr>	
							<tr>
								<td width="75" colspan="4">
									地图：
								</td>
								 
							</tr>	
							<tr>
								<td colspan="4">
									  <input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
									 <div style="width: 900px;height: 600px" > 
									 	<iframe width="100%" id="ifmap" height="100%" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=video"></iframe>
									 </div>
								</td>
							</tr>	
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<input name="" type="button" value="提交" class="btn2" onclick="edit()" />
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