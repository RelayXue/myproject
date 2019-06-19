 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String stayid = request.getParameter("stayid");
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
		
		myform.attr("action","<%=basePath%>buStayRoom!edit?reNum="+reNum+"&imgNum="+imgNum+"&skey=${skey}&indexPage=${indexPage}&stayid=<%=stayid%>");
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
				morelive:{
					number: true
				} ,
				price:{
					number:true
				}
			} 
			});
		//-----图片上传--------
	var img='${buStayRoom.dimages}';
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
					<a href="#" class="active">房间管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
						
							<tr>
								<td width="75">
									名&nbsp;&nbsp;&nbsp;&nbsp;称：
								</td>
								<td width="75">
									 <input type="text"  class="inp2" id="fullname"  name="buStayRoom.fullname"  value="${buStayRoom.fullname}" />
									 <input type="hidden"  id="fuid"   name="buStayRoom.fuid"  value='${buStayRoom.fuid}' />
								</td>
								<td   >
									楼&nbsp;&nbsp;&nbsp;&nbsp;层：
								</td>
								<td style="width: 266px;">
								   <input type="text"  class="inp2"    name="buStayRoom.floor"  value='${buStayRoom.floor}' style="width: 120px;"/>
								</td>
							</tr>
							
							<tr>
								<td width="75">
									床&nbsp;&nbsp;&nbsp;&nbsp;型：
								</td>
								<td>
								    <input type="text"  class="inp2"    name="buStayRoom.bed"  value='${buStayRoom.bed}' />
								</td>
								<td width="75">
									最多入住人数：
								</td>
								<td width="75">
								    <input type="text"  class="inp2"  id="morelive"  name="buStayRoom.morelive"  value='${buStayRoom.morelive}' style="width: 120px;"/>
								</td>
							</tr>	
							
							<tr>
								<td width="75">
									上网方式：
								</td>
								<td>
								   <input type="text"  class="inp2"    name="buStayRoom.internet"  value='${buStayRoom.internet}' />
								</td>
								
								<td width="75">
									价&nbsp;&nbsp;&nbsp;&nbsp;格：
								</td>
								<td width="75">
								    <input type="text"  class="inp2" id="price"   name="buStayRoom.price"  value='${buStayRoom.price}' style="width: 120px;"/>
								</td>
							</tr>
							 		 				
							<tr>
								 <td valign="top">图片上传：</td>
								<td >
								<div id="file_div"></div>
								<div style="margin-top: 30px;margin-bottom: 30px;"><a id="AddBtn"   href="javascript:;"><img src="<%=basePath%>images/upload.jpg"/></a></div>
								
								</td>
							</tr>	
							
							
							<tr>
								<td width="75">
								 
								</td>
								<td>
									<!--<input name="" type="button" value="提交" class="btn2" onclick="edit()" /> 
									<input name="" type="button" value="返回" class="btn4" onclick="res()" /> -->
									<button class="btn btn-large btn-success" type="submit" onclick="edit()"><i class="icon-check icon-white"></i>提交</button>
									<button class="btn btn-large btn-primary " type="button"  onclick="res()" ><i class="icon-backward icon-white"></i>返回</button>
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

