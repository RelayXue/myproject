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
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>

		<script>
	function edit(){
		var myform=$("#myform");
		//-----图片编辑参数----
		//-------图片处理前----------
	     var imgNum="";
	    // alert($("img[id^=ImgPr]").length);
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
//-----------------------------------------------------------------------------------------------------------
//----------处理图片后-------------
	var imgNum_="";
	$("img[id^=ImgPr_]").each(function (i){
			var isUpdata=$(this).parent().attr("style");
			var src=$(this).attr("src");
			//IE
			if(isUpdata!=null&&isUpdata.length>0){
				imgNum_+=i+",";
			}
			//GOOGLE  firfox
			if(src!=null&&src.split("blob").length>1){
				imgNum_+=i+",";
			}
		});
	imgNum_=imgNum_.length>0?imgNum_.substring(0,imgNum_.length-1):imgNum_;
	reNum_=reNum_.lenth>0?reNum_:reNum_.substring(0,reNum_.length-1);
	
	myform.attr("action","<%=basePath%>buAffairs!edit?reNum="+reNum+"&imgNum="+imgNum+"&imgNum_="+imgNum_+"&reNum_="+reNum_+"&skey=${skey}&indexPage=${indexPage}");
	myform.submit();
}
function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}

//-----返回----
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
		//-----图片处理前--------
	var img='${buAffairs.beforeimage}';
	if(img!='null'&&img.length>0&&img!='default.jpg'){
		$("").uploadShow({data : img,path:"/upload/B_" });
	}else{
		$("").uploadInit();
	}
	
	//------图片处理后------
	var img_='${buAffairs.afterimage}';
	if(img_!='null'&&img_.length>0&&img_!='default.jpg'){
		$("").uploadShow_({data : img_,path:"/upload/B_" });
	}else{
		$("").uploadInit_();
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
					<a href="#" class="active">事件管理</a>
				</div>
			<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									编&nbsp;&nbsp;&nbsp;&nbsp;号：
								</td>
								<td>
									 <input type="text"  class="inp2" id="code"  name="buAffairs.code"  value="${buAffairs.code}" style="float: left;"/>
									 <input type="hidden"  id="fuid"    name="buAffairs.fuid"  value='${buAffairs.fuid}' />
								</td>
							</tr>
							
							<tr>
								<td width="75">
									上&nbsp;报&nbsp;人：
								</td>
								<td>
								   <input type="text"  class="inp2" id="reported"   name="buAffairs.reported"  value='${buAffairs.reported}'  style="float: left;"/>
								</td>
							</tr>	
							
							<tr>
								<td width="75" valign="top">
									描&nbsp;&nbsp;&nbsp;&nbsp;述：
								</td>
								<td>
								<textarea   style='border: 1px solid #bfbfbf' rows="5" cols="80" name="buAffairs.description">${buAffairs.description}</textarea>
								</td>
							</tr>
							
							<tr>
								<td width="75" valign="top">
									处理后描述：
								</td>
								<td>
								<textarea   style='border: 1px solid #bfbfbf' rows="5" cols="80" name="buAffairs.afterexplain">${buAffairs.afterexplain}</textarea>
								</td>
								
								
							</tr>	
							
							<tr>
							
								<td valign="top">处理前图片：</td>
							<td>
								<div id="file_div"></div>
								<div style="margin-top: 30px;margin-bottom: 30px;"><a id="AddBtn"   href="javascript:;"><img src="<%=basePath%>images/upload.png"/></a></div>
							</td>
							</tr>
							
							<tr>
							
								<td valign="top">处理后图片：</td>
							<td>
								<div id="file_div_"></div>
								<div style="margin-top: 30px;margin-bottom: 30px;"><a id="AddBtn_"   href="javascript:;"><img src="<%=basePath%>images/upload.png"/></a></div>
							</td>
							</tr>
		
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
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