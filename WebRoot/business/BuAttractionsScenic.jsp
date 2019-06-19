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
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 	function contact(){
	 		var ch = $("input[name='ch']:checked");
			if (ch.length == 0) {
				alert("请先勾选数据！");
				return;
			}
			var attractionsId = "";
			ch.each(function() {
				attractionsId += $(this).val() + ",";
			});
			attractionsId = attractionsId.length > 0 ? attractionsId.substring(0, attractionsId.length - 1) : attractionsId;
			 $.ajax({
			    url: "buAttractions!ScenicContact?time=" + new Date(),
			    type:"post",
			    data:"attractionsId="+attractionsId+"&id=${id}",
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			    	alert("关联成功");
			        window.parent.closeDialog();
			    }
			});
	 	}
	 
	</script>
		<style>
 
</style>
	</head>
	<body>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%">
						 
					</th>
					<th width="11%">
						 名称
					</th>
					<th width="11%">
						 所属景区
					</th>
				 
				</tr>
				<s:iterator value="buAttractions_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td><s:property value="#list.fullname" />&nbsp; </td>
						<td>
							<s:if test="#list.scenicid!=null">
								<s:property value="#list.voice" />
							</s:if>
							<s:else>
								未关联景区
							</s:else>
						</td>
					</tr>
				</s:iterator>
				 <tr>
				 <td colspan="3"><input name="" type="button" value="选择" class="btn2" onclick="contact()" /> </td>
				 </tr>
			</table>
		</div>
	</body>

</html>