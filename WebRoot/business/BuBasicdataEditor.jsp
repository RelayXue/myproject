<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
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
	myform.attr("action","<%=basePath%>buBasicdata!edit?reNum="+reNum+"&imgNum="+imgNum+"&skey=${skey}&indexPage=${indexPage}&type=<%=type%>");
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
			},
			phone:{
				mylxdh:true
			} 
		} 
	});
	
	
	var codelx='002014';
	$("#ftype").AutoSelect({
		data:basetemp,	
		name:"buBasicdata.type",
		showNum :2,
		disabled:2,
		initCode : codelx
	});

	//-----图片上传--------
	var img='${buBasicdata.dimages}';
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
			 <a href="" class="active"><%=((Map)application.getAttribute("DatadictionaryMap")).get(type) %></a>
			<!-- <a href="#" class="active"><s:property value="#application.DatadictionaryMap[type]"  /></a>-->
			</div>
				
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									名&nbsp;&nbsp;&nbsp;&nbsp;称：
								</td>
								<td>
									 <input type="text"  class="inp2" id="fullname"  name="buBasicdata.fullname"  value="${buBasicdata.fullname}" style="float: left;"/>
									 <input type="hidden"  id="fuid"    name="buBasicdata.fuid"  value='${buBasicdata.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="75">
									类&nbsp;&nbsp;&nbsp;&nbsp;型：
								</td>
								<td>
									  <div id="ftype"></div>
								</td>
							</tr>
							
							<tr>
								<td width="75">
									地&nbsp;&nbsp;&nbsp;&nbsp;址：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buBasicdata.address"  value='${buBasicdata.address}' />
								</td>
							</tr>	
							
							<tr>
								<td width="75">
									联系电话：
								</td>
								<td>
								   <input type="text"  class="inp2" id="phone"   name="buBasicdata.phone"  value='${buBasicdata.phone}'  style="float: left;"/>
								</td>
							</tr>	
							
							<tr>
								<td width="75" valign="top">
									介&nbsp;&nbsp;&nbsp;&nbsp;绍：
								</td>
								<td>
								<textarea   style='border: 1px solid #bfbfbf' rows="5" cols="80" name="buBasicdata.introduction">${buBasicdata.introduction}</textarea>
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
							
							
							
							<tr>
								<td  >
								 地&nbsp;&nbsp;&nbsp;&nbsp;图：
								</td>
								<td>
								<input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
									<input type="hidden"  id="fx" class="input-text"    name="buBasicdata.fx"  value='${buBasicdata.fx}' />
									 <input type="hidden"  class="input-text"  id="fy"  name="buBasicdata.fy"  value='${buBasicdata.fy}' />
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