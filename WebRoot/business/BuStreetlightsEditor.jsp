 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>

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
		myform.attr("action","<%=basePath%>buStreetlights!edit?skey=${skey}&indexPage=${indexPage}");
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
					<a href="#" class="active">路灯管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="10px">
									名称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buStreetlights.fullname"  value="${buStreetlights.fullname}" />
									 <input type="hidden"     name="buStreetlights.fuid"  value='${buStreetlights.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="45">
									地址：
								</td>
								<td>
								   <input type="text"  class="inp1"    name="buStreetlights.address"  value='${buStreetlights.address}' />
								</td>
							</tr>	
						 	 
							<tr>
								<td width="45">
									编号：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buStreetlights.dnumber"  value='${buStreetlights.dnumber}' />
								</td>
							</tr>	
							<tr>
								<td width="45">
									状态：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buStreetlights.status"  value='${buStreetlights.status}' />
								     <input type="hidden"  class="input-text" id="fx"    name="buStreetlights.fx"  value='${buStreetlights.fx}' />
									   <input type="hidden"  class="input-text" id="fy"    name="buStreetlights.fy"  value='${buStreetlights.fy}' />
								</td>
							</tr>	
							<tr>
								
								<td colspan="2">
								   <input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
									 <div style="width: 900px;height: 600px" > 
									 	<iframe width="100%" id="ifmap" height="100%" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=attractions"></iframe>
									 </div>
								</td>
							</tr>	
							 
							<tr>
								<td width="45">
									&nbsp;
								</td>
								<td>
									<input name="" type="button" value="提交" class="btn2" onclick="edit()" />
									<input name="" type="button" value="返回" class="btn4" onclick="res()" /> 
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>