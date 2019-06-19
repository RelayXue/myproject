 
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
		//----------多图上传处理
		 var tt=$(window.frames["ifrImg"].document).find("input[name='imgHid']");
		 var dd=$(window.frames["ifrImg"].document).find("input[name='uid']").val();
		 var img=""
		 tt.each(function (i){
		 	img+=tt.eq(i).val()+";";
		 });
		 img=img.length>0?img.substring(0,img.length-1):img;
		 $("#aimg").val(img);
		 var id=$("#fuid").val();
		 if(id.length==0){
		 	$("#uid").val(dd);
		 }
		 //------------------------------
		myform.attr("action","<%=basePath%>buActivities!edit?skey=${skey}&indexPage=${indexPage}");
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
					<a href="#" class="active">特色活动管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									名称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buActivities.fullname"  value="${buActivities.fullname}" />
									 <input type="hidden"   id="fuid"  name="buActivities.fuid"  value='${buActivities.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="75">
									地址：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buActivities.address"  value='${buActivities.address}' />
								</td>
							</tr>	
							<tr>
								<td width="75">
									活动时间：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buActivities.openinghours"  value='${buActivities.openinghours}' />
								</td>
							</tr>	
							<tr>
								<td width="75">
									介绍：
								</td>
								<td>
										<textarea   style='border: 1px solid #bfbfbf' rows="5" cols="40" name="buActivities.introduction">${buActivities.introduction}</textarea>
								</td>
							</tr>	
							<tr>
								 <td>图片上传：</td>
								<td colspan="3">
									 <input type="hidden" id="uid"    name="uid"  value='' />
									<input type="hidden" id="aimg" name="buActivities.images" value="" />
									<iframe width="100%" id="ifrImg" name="ifrImg"  height="400px"  frameborder="no" border="0"  src="<%=basePath%>fileUpload?otherId=${buActivities.fuid}&name=BuActivities&time=<%=new Date() %>"></iframe>
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