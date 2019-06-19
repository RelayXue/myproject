<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId = request.getParameter("otherId");
	String name = request.getParameter("name");
	name = name == null ? "" : name;
	String type =request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<title>My JSP 'index.jsp' starting page</title>
		<!-- The styles -->
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<style type="text/css">
	  body {
		padding-bottom: 40px;
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

<script type="text/javascript">
	//ckeditor的content替换
	$(document).ready(function() {
		CKEDITOR.replace('buNews.content');
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
		$("#up_img").click(function() {
			$("#up_btn").click();
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
	
	//获取修改图片的选择对象
	function test(){
	var flag1 = $("#up_btn1").val();
	var flag2 = $("#up_btn2").val();
	var flag3 = $("#up_btn3").val();
	var str = "";
	if(flag1.length!=0){
	str+="1#";
	}
	if(flag2.length!=0){
	str+="2#";
	}
	if(flag3.length!=0){
	str+="3#";
	}
	$("#flag").val(str);
	}
	
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 乌镇概括管理</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="buNews!edit?type=<%=type%>" onsubmit="test();" method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
			
			
				<div class="control-group" >
				<label class="control-label" for="fileInput" style="color: gray"><strong>滚动图片一:</strong></label>
					<div class="controls">
							<s:if test="%{buNews==null||buNews.img1==null||buNews.img1==''||buNews.img1=='default.jpg'}">
								<img id="ImgPr1" width="120px" src="<%=basePath%>images/tishi.png" height="120px" style="border:1px solid gray"/>
							</s:if>	
							<s:else>
								<img id="ImgPr1" width="120px" src="<%=basePath%>upload/B_${buNews.img1}" height="120px" style="border:1px solid gray"/>
							</s:else>
					</div>
					<div style="float: left;margin-left: 297px;margin-top: -120px;">
						<input type="file" id="up_btn1" name="img"/>
					</div>
				</div>
			
				
				<div class="control-group" >
					<label class="control-label" for="fileInput" style="color: gray"><strong>滚动图片二:</strong></label>
					<div class="controls">
					
						<s:if test="%{buNews==null||buNews.img2==null||buNews.img2==''||buNews.img2=='default.jpg'}">
							<img id="ImgPr2" width="120px" src="<%=basePath%>images/tishi.png" height="120px" style="border:1px solid gray" />
						</s:if>	
						<s:else>
							<img id="ImgPr2" width="120px" src="<%=basePath%>upload/B_${buNews.img2}" height="120px" style="border:1px solid gray"/>
						</s:else>
					</div>
					<div style="float: left;margin-left: 297px;margin-top: -120px;">
						<input type="file" id="up_btn2" name="img"/>
					</div>
				</div>
				
				
			<div class="control-group" >
				<label class="control-label" for="fileInput" style="color: gray"><strong>滚动图片三:</strong></label>
					<div class="controls">
					
						<s:if test="%{buNews==null||buNews.img3==null||buNews.img3==''||buNews.img3=='default.jpg'}">
						  <img id="ImgPr3" width="120px" src="<%=basePath%>images/tishi.png" height="120px" style="border:1px solid gray"/>
						</s:if>	
						<s:else>
						  <img id="ImgPr3" width="120px" src="<%=basePath%>upload/B_${buNews.img3}" height="120px" style="border:1px solid gray"/>
						</s:else>
					</div>
					<div style="float: left;margin-left: 297px;margin-top: -120px;">
						<input type="file" id="up_btn3" name="img" />
					</div>
			</div>
				
					
							<input type="hidden" id="" name="buNews.fullname" value=""/>
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>内容编辑:</strong></label>
							  <div class="controls">
									<textarea id="content" name="buNews.content">${buNews.content}</textarea>
									<input type="hidden" id="fuid" name="buNews.fuid"	value="${buNews.fuid}" />
							  </div>
							</div>
							
							
							
							<div class="form-actions" >
								<input type="hidden" id="flag" name="flag" />
							    <button class="btn btn-large btn-success" type="submit"  style="border-bottom-width: 0px;"><i class="icon-check icon-white"></i>提交	</button>
								<!--<button class="btn btn-large btn-primary " type="button" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
								-->
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->


		


	
	</body>
</html>
