  
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
	//	CKEDITOR.replace('buNews.content');
		var se='${buRoad.state}';
		$("select[name='buRoad.state']").find("option").each(function (){
			if($(this).val()==se){
				$(this).attr("selected","selected");
			}
		});
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
	/*function test(){
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
	}*/
	
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>交通路况</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="buRoad!edit"  method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
			
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>路名：</strong></label>
							  <div class="controls">
								<input type="text" style="width:220px"  name="buRoad.fullname"   value='${buRoad.fullname}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>状态：</strong></label>
							  <div class="controls">
							  	<select  name="buRoad.state">
							  		<option value="畅通">畅通</option>
							  		<option value="缓慢">缓慢</option>
							  		<option value="拥堵">拥堵</option>
							  		<option value="严重拥堵">严重拥堵</option>
							  	</select>
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>描述: </strong></label>
							  <div class="controls">
									<textarea style="width: 400px"   rows="10" id="content" name="buRoad.description">${buRoad.description }</textarea>
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
