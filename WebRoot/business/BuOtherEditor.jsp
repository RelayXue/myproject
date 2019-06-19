﻿<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html>
<head>
	<title>后台维护信息</title>
	<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/layout.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/eayui/easyui.css"/>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>js/common/AutoSelect.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>
	<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>

<script>

function edit(){
	var myform=$("#myform");
	//-----图片编辑参数----
	var imgNum="";
		$("img[id^=ImgPr]").each(function (i){
			var isUpdata=$(this).parent().attr("style");
			var src=$(this).attr("src");
			//IE
			if(isUpdata!=null&&isUpdata.length>0){
				imgNum+=i+",";
			}
			//GOOGLE  firfox
			if(src!=null&&src.split("blob").length>1){
				imgNum+=i+",";
			}
		});
	imgNum=imgNum.length>0?imgNum.substring(0,imgNum.length-1):imgNum;
	reNum=reNum.lenth>0?reNum:reNum.substring(0,reNum.length-1);
	//-----------------------------------------
	var select =$("select").val();
	

	
	
	
	myform.attr("action","<%=basePath%>buGeographys!edits?reNum="+reNum+"&imgNum="+imgNum+"&skey=${skey}&indexPage=${indexPage}");
	myform.submit();
}


function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
//--返回--
function res(){
	window.location.href="javascript:history.go(-1);";
}

$(document).ready(function() {
	$("#myform").validate({
		rules : {
			fullname:{
				required: true
			}
			
		} 
	});
	
	//判断是否是党员
	var dangyuan="${buGeography.dangyuan}";
	if(dangyuan==1){
	   $("#yes").attr("checked","checked");
	}else{
	   $("#no").attr("checked","checked");
	}
	

	//-----图片上传--------
	var img='${buGeography.dimages}';
	if(img!='null'&&img.length>0&&img!='default.jpg'){
		$("").uploadShow({data : img,path:"/upload/B_" });
	}else{
		$("").uploadInit();
	}
	
	
});





</script>

<style>
</style>

</head>
<body>
	<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
		<div class="inright">
		
			<div class="rightnav">
			 <a href="" class="active">基础数据维护</a>
			<!-- <a href="#" class="active"><s:property value="#application.DatadictionaryMap[type]"  /></a>-->
			</div>
				
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									&nbsp;&nbsp;&nbsp;名称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buGeography.fullname"  value="${buGeography.fullname}" style="float: left;"/>
									 <input type="hidden"  id="fuid"    name="buGeography.fuid"  value='${buGeography.fuid}' />
								</td>
							</tr>
							
							<tr>
								<td width="75">
									&nbsp;&nbsp;&nbsp;地址：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buGeography.address"  value='${buGeography.address}' />
								</td>
							</tr>	
							
							<tr>
								<td width="75">
									&nbsp;&nbsp;负责人：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buGeography.supervisor"  value='${buGeography.supervisor}' />
								</td>
							</tr>
							
							<tr>
								<td width="75">
									&nbsp;&nbsp;&nbsp;党员：
								</td>
								<td>
								   <input type="radio" id="yes" name="buGeography.dangyuan" value="1"  />是
									<input type="radio" id="no" name="buGeography.dangyuan" value="0" />否
									<font style="color: gray;">(这里的党员是针对<font style="color: red">负责人的身份</font>,默认是否)</font>
								</td>
							</tr>	
							
						<tr>
							<td width="75">负责人手机：</td>
							<td >
								<input type="text"    class="inp2"   name="buGeography.supervisorphone"  value='${buGeography.supervisorphone}'/>
							</td>
						</tr>
							
							<tr>
								<td width="75">
									&nbsp;联系电话：
								</td>
								<td>
								   <input type="text"  class="inp2" id="phone"   name="buGeography.phone"  value='${buGeography.phone}'  style="float: left;"/>
								</td>
							</tr>
							
							
							<tr>
								<td width="75" valign="top">
									&nbsp;&nbsp;&nbsp;介绍：
								</td>
								<td>
								<textarea   style='border: 1px solid #bfbfbf;width:400px' rows="5" cols="80" name="buGeography.description">${buGeography.description}</textarea>
								</td>
							</tr>	
							
							<tr>
								 <td valign="top">图片上传：
							</td>
							
							<td>
						
								<div id="file_div"></div>
								<div style="margin-top: 30px;margin-bottom: 30px;"><a id="AddBtn"   href="javascript:;"><img src="<%=basePath%>images/upload.png"/></a></div>
							
							</td>
							
							</tr>	
							</table>
							
							<table width="100%" border="0">
							
							<tr>
								<td  >
								 地图：
								</td>
								<td>
								<input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
									<input type="hidden"  id="fx" class="input-text"    name="buGeography.fx"  value='${buGeography.fx}' />
									 <input type="hidden"  class="input-text"  id="fy"  name="buGeography.fy"  value='${buGeography.fy}' />
								</td>
							</tr>	
							
							<tr>
							<td> </td>
								<td colspan="3" style="height: 500px">
										<iframe width="690px" id="ifmap" height="500px" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=attractions"></iframe>
								</td>
							</tr>
							
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<!--<input name="" type="button" value="提交" class="btn2" onclick="edit()" />
									<input name="" type="button" value="返回" class="btn4" onclick="res()" /> -->
									<button class="btn btn-large btn-success" type="button" onclick="edit()"><i class="icon-check icon-white"></i>提交</button>
									<button class="btn btn-large btn-primary " type="button"  onclick="res()" ><i class="icon-backward icon-white"></i>返回</button>
									
								</td>
							</tr>
							
							<tr>
								<td width="75"  colspan="4" >
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>	
</body>
</html>