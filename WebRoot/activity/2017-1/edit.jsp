<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	  a{
	  	background:#E7E7E7;
	  	padding:5px 10px 5px 10px;
	  	border-radius:5px
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
<script type="text/javascript">
	function submit(){
		if($("#roadName").val()==null || $("#roadName").val()==""){
			alert("请输入路、桥暂命名！")
			return
		}
		$("#frame").submit();
	}
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2>. 征集路名基础信息添加</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
					<form class="form-horizontal" action="<%=basePath%>2017/one!addOrEdit"  method="post" id="frame">
							<div class="control-group">
								<table class="control-label">
									<tr>
										<td><b>征集路名暂命名</b>:</b></td>
									</tr>
									<tr>
										<td><b>说明</b>:</b></td>
									</tr>
								</table>
								<table class="controls">
									<tr>
										<td><input type="text" id="roadName" style="width:500px" name="a20171bridgeroad.temporaryname" value='<s:property value="a20171bridgeroad.temporaryname"/>'></td>
									</tr>
									<tr>
										<td>
											<textarea rows="7" style="width:500px" name="a20171bridgeroad.remark"><s:property value="a20171bridgeroad.remark"/></textarea>
										</td>
									</tr>
								</table>
								<input type="hidden" value="<s:property value="a20171bridgeroad.fuid"/>" name="a20171bridgeroad.fuid"/>
							</div>
							<a href="javascript:void(0)" style="margin-left:15%" onclick="submit()">提交</a>&nbsp;&nbsp;
							<a href="<%=basePath%>2017/one!list">取消</a>
						</form>
					</div>
				</div>

			</div>
	</body>
</html>
