<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId = request.getParameter("otherId");
	String name = request.getParameter("name");
	name = name == null ? "" : name;
	String type = request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<title>添加</title>
		<!-- The styles -->
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  td{
	  	height:40px;
	  	color:#80809F
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="<%=basePath%>js/BootstrapV2/css/bootstrap-responsive.css" rel="stylesheet"/>
	<link href="<%=basePath%>js/BootstrapV2/css/charisma-app.css" rel="stylesheet"/>
	<link href="<%=basePath%>js/BootstrapV2/css/jquery-ui-1.8.21.custom.css" rel="stylesheet"/>
	<link href='<%=basePath%>js/BootstrapV2/css/fullcalendar.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/fullcalendar.print.css' rel='stylesheet'  media='print'/>
	<link href='<%=basePath%>js/BootstrapV2/css/chosen.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/uniform.default.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/colorbox.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/jquery.cleditor.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/jquery.noty.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/noty_theme_default.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/elfinder.min.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/elfinder.theme.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/jquery.iphone.toggle.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/opa-icons.css' rel='stylesheet'/>
	<link href='<%=basePath%>js/BootstrapV2/css/uploadify.css' rel='stylesheet'/>
	<link rel="shortcut icon" href="<%=basePath%>js/BootstrapV2/img/favicon.ico"/>
	
	<!-- The script -->
	<script type="text/javascript"src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript"src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
	<script type="text/javascript"src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript"src="<%=basePath%>js/ckeditor/config.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath %>js/common/AutoSelect.js"></script>
		<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>

<script type="text/javascript">
	//ckeditor的content替换
	$(document).ready(function() {
	});


	
	//图片上传于回显效果
	$(function() {
		$("#up_btn").uploadPreview({
			Img : "ImgPr",
			Width : 120,
			Height : 120,
			ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
			Callback : function() {
			}
		});
		for(var ImgUpNum=1;ImgUpNum<4;ImgUpNum++){
			$("#up_btn" + ImgUpNum).uploadPreview({
					Img : "ImgPr" + ImgUpNum,
					Width : 120,
					Height : 120,
					ImgType : [ "gif", "jpeg", "jpg",
							"bmp", "png" ],
					Callback : function() {
					}
			});	
		}
							
	});
	
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2>. 新增旅店表单</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
					
					
					
					
			<form class="form-horizontal" action="<%=basePath%>accommodation!updateHostl"  method="post" enctype="multipart/form-data"  >
				<fieldset>
				
				
				
				<!-- 旅店名称 
					*************************************************************************************************************
					-->
							<div class="control-group">
								<table class="control-label">
									<tr>
										<td><b>旅店名称:</b></td>
									</tr>
									<tr>
										<td><b>旅店编号:</b></td>
									</tr>
									<tr>
										<td><b>地址:</b></td>
									</tr>
									<tr>
										<td><b>所有人:</b></td>
									</tr>
									<tr>
										<td><b>电话:</b></td>
									</tr> 
								</table>
								<table class="controls">
									<tr>
										<td><input type="text" style="width:500px" name="buAccommodation.hname" value='<s:property value="buAccommodation.hname"/>'></td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="buAccommodation.morder" value='<s:property value="buAccommodation.morder"/>'></td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="buAccommodation.address" value='<s:property value="buAccommodation.address"/>'></td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="buAccommodation.householder" value='<s:property value="buAccommodation.householder"/>'></td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="buAccommodation.mobile" value='<s:property value="buAccommodation.mobile"/>'></td>
									</tr>
								</table>
							</div>
					<!-- 旅店名称 
					*************************************************************************************************************
					-->	
			
			<!-- 图片上传 
				*****************************************************************************************************
				-->
				<div class="control-group" >
				<label class="control-label" for="fileInput" style="color: gray"><strong>上传图片:</strong></label>
					<div class="controls">
							<s:if test="buAccommodation.himg==null || buAccommodation.himg==''">
								<img id="ImgPr1" width="120px" src="<%=basePath%>module/weixin/images/tishi.png" height="120px" style="border:1px solid gray"/>
							</s:if>	
							<s:else>
								<img id="ImgPr1" width="120px" src="<%=basePath%>upload/<s:property value='buAccommodation.himg'/>" height="120px" style="border:1px solid gray"/>
							</s:else>
					</div>
					<div style="float: left;margin-left: 297px;margin-top: -120px;">
						<input type="file" id="up_btn1" name="image"/>
					</div>	
				</div>
			<!-- 
				*************************************************************************************************************
				-->		
				
				
				
				
					<!-- 简介 
					*************************************************************************************************************
					-->		
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>旅店简介: </strong></label>
							  <div class="controls">
									<textarea id="content" name="buAccommodation.description" style="width:500px;height:300px"><s:property value="buAccommodation.description"/></textarea>
							  </div>
							</div>
					<!-- 简介 
					*************************************************************************************************************
					-->		
					<!-- 按钮 
					*************************************************************************************************************
					-->		
							<div class="form-actions" >
								<!-- <input type="hidden" id="flag" name="flag" /> -->
								<button class="btn btn-large btn-success" type="submit" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>保存	</button>
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
					<!-- 按钮 
					*************************************************************************************************************
					-->		
						  </fieldset>
						  <input type="hidden" value='<s:date name="buAccommodation.createdate" format="yyyy-MM-dd hh:mm:ss"/>' name="buAccommodation.createdate"/>
						  <input type="hidden" value='<s:property value="buAccommodation.fuid"/>' name="buAccommodation.fuid"/>
						  <input type="hidden" value='<s:property value="buAccommodation.num"/>' name="buAccommodation.num"/>
						  <input type="hidden" value='<s:property value="buAccommodation.isvote"/>' name="buAccommodation.isvote"/>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->
	</body>
</html>
