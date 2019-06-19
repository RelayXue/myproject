 
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
	});


	
	  
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>微信留言</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="buNews!edit"   method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
			
				 
							
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>发送人ID：</strong></label>
							  <div class="controls">
								<input type="text"  name="buWeixinmessage.fromusername"   value='${buWeixinmessage.fromusername}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>消息类型：</strong></label>
							  <div class="controls">
								<input type="text"  name="buWeixinmessage.msgtype"   value='${buWeixinmessage.msgtype}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>消息ID：</strong></label>
							  <div class="controls">
								<input type="text"  name="buWeixinmessage.msgid"   value='${buWeixinmessage.msgid}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>消息发送时间：</strong></label>
							  <div class="controls">
								<input type="text"  name="buWeixinmessage.messagetime"   value='<s:date name="buWeixinmessage.createdate" format="yyyy-MM-dd HH:mm:ss" />' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>图片：</strong></label>
							  <div class="controls">
							  	<a target=_black href="<s:property value="#list.picurl" />"><img height="320" src="<s:property value="buWeixinmessage.picurl" />"/></a>
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="textarea2" style="color: gray"><strong>内容: </strong></label>
							  <div class="controls" >
									<textarea  style="width: 500px;height: 200px" id="content" name="buNews.content">${buWeixinmessage.content}</textarea>
									 <input type="hidden"     name="buWeixinmessage.fuid"  value='${buWeixinmessage.fuid}' />
							  </div>
							</div>
							
							
							<div class="form-actions" >
								<!-- <input type="hidden" id="flag" name="flag" /> -->
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->


		


	
	</body>
</html>
