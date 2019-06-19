 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<base href="<%=basePath%>"/>
		<title>后台管理</title>
		<!-- The styles -->
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	  
	  #typeahead{
	  	width:50%;
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
		CKEDITOR.replace('buWeixinactivity.content');
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
	 
	
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>  赛诗会</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="buWeixinactivity!edit" onsubmit="test();" method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>标题:</strong></label>
							  <div class="controls">
								<input type="text"  name="buWeixinactivity.title" value="${buWeixinactivity.title}" class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>作者: </strong></label>
							  <div class="controls">
									<input type="text"  name="buWeixinactivity.author" value="${buWeixinactivity.author}" class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>电话: </strong></label>
							  <div class="controls">
									<input type="text"  name="buWeixinactivity.phone" value="${buWeixinactivity.phone}" class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>排序: </strong></label>
							  <div class="controls">
									<input type="text"  name="buWeixinactivity.worder" value="${buWeixinactivity.worder}" class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>内容编辑: </strong></label>
							  <div class="controls">
									<textarea id="content" name="buWeixinactivity.content">${buWeixinactivity.content}</textarea>
									<input type="hidden" id="fuid" name="buWeixinactivity.fuid"	value="${buWeixinactivity.fuid}" />
							  </div>
							</div>
							
							
							
						
							<div class="form-actions" >
								<!-- <input type="hidden" id="flag" name="flag" /> -->
								<button class="btn btn-large btn-success" type="submit" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>提交	</button>
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->


		


	
	</body>
</html>
