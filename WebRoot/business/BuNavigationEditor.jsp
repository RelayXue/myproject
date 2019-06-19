 
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
		myform.attr("action","<%=basePath%>buNavigation!edit?skey=${skey}&indexPage=${indexPage}");
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
					<a href="#" class="active">路线管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									名称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buNavigation.fullname"  value="${buNavigation.fullname}" />
									 <input type="hidden"     name="buNavigation.fuid"  value='${buNavigation.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="75">
									介绍：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buNavigation.introduction"  value='${buNavigation.introduction}' />
								</td>
							</tr>	
							<tr>
								<td  >
									地图：   
								</td>
								<td>
									<input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />&nbsp;<input id="btnMove" type="button" class="input-page-btn"   value="漫游"   />
								<strong><span style="font-size: 14px"> 注：红色图标表示未关联，蓝色表示已关联</span> </strong>
								<input type="hidden"  id="fxy" class="input-text"    name="buNavigation.fxy"  value='${buNavigation.fxy}' />
								</td>
							</tr>	
							<tr>
								<td colspan="4" style="height: 582px">
										<iframe width="100%" id="ifmap" height="100%" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=navigation&id=${id }&time=<%=new Date() %>"></iframe>
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