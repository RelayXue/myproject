 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String parentId=request.getParameter("parentId");		
	parentId=parentId!=null?parentId:"";
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
		myform.attr("action","<%=basePath%>weixinMenu!edit?skey=${skey}&indexPage=${indexPage}&parentId=<%=parentId%>");
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
					<a href="#" class="active">微信管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									菜单名：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buWeixinmenu.fullname"  value="${buWeixinmenu.fullname}" />
									 <input type="hidden"     name="buWeixinmenu.fuid"  value='${buWeixinmenu.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="75">
									链接地址：
								</td>
								<td> 
								   <input type="text"  class="inp2"    name="buWeixinmenu.wurl"  value='${buWeixinmenu.wurl}' />
								</td>
							</tr>	
							<tr>
								<td width="75">
									排序：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buWeixinmenu.sortcode"  value='${buWeixinmenu.sortcode}' />
								</td>
							</tr>	
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<input name="" value="提交" type="button" class="btn2" onclick="edit()" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>