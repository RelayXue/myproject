﻿ 
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


		<script>
	function edit(){
		var myform=$("#myform");
		myform.attr("action","<%=basePath%>buAttractions!examine");
		myform.submit(); 
		window.parent.closeDialog();
}
	$(document).ready(function() {
      });
	</script>
		<style>
 
</style>
	</head>
<body>
		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div  >
				<div class="sdetail">
					<div class="sdtable" style="width: 100%"> 
						<table width="100%" border="0">
							<tr>   
								<td width="60px" height="30"> 
									审核：
								</td>
								<td>
									<s:if test="buAttractions.status==1">
										<input type="radio" style="border: 0;height: 12px " checked="checked"  name="buAttractions.status" value="1" />通过
										 <input type="radio" style="border: 0;height: 12px" name="buAttractions.status"  value="2" />不通过
									</s:if>
									<s:else>
										<input type="radio" style="border: 0;height: 12px "  name="buAttractions.status" value="1" />通过
										 <input type="radio" style="border: 0;height: 12px" checked="checked" name="buAttractions.status"  value="2" />不通过
									</s:else>
								</td>
							</tr>
							<tr>
								<td  height="30">
									名称：
								</td>
								<td>
									 ${buAttractions.fullname}
									 <input type="hidden"     name="buAttractions.fuid"  value='${buAttractions.fuid}' />
								</td>
							</tr>
							<tr>
								<td  height="30">
										地址：
								</td>
								<td>
								  ${buAttractions.address }
								</td>
							</tr>	
								<tr>
								<td  height="30">
									&nbsp;
								</td>
								<td>
									<input name="" type="button" class="btn2" value="审核" onclick="edit()" />
								</td>
							</tr>
						
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>