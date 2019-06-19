<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
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
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>

<script type="text/javascript">
	//ckeditor的content替换
	$(document).ready(function() {
		//CKEDITOR.replace('buWeixinconfig.content');
	});


	/*添加或修改*/
	function edits(){
		var myform=$("#myform");
		/*//-----图片编辑参数----
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
		reNum=reNum.lenth>0?reNum:reNum.substring(0,reNum.length-1);*/
		//-----------------------------------------
		myform.attr("action","<%=basePath%>buWeixinconfig!edit?type=${type}");
		myform.submit();
		
	}

	 
	
</script>
	</head>

<body>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-edit"></i>编辑信息</h2>
				<div class="box-icon"></div>																
			</div>
		<div class="box-content">
	<form class="form-horizontal" action="buWeixinconfig!edit"  id="myform"  method="post" enctype="multipart/form-data"  >
		<fieldset>
		<legend></legend>
		
		<%--<div class="control-group">
		  <label class="control-label" for="typeahead" style="color: gray"><strong>标题：</strong></label>
		  <div class="controls">
			<input type="text"  name="buWeixinconfig.fullname"   value="${buWeixinconfig.fullname}"  class="span6 typeahead" id="typeahead"   data-items="4" />
			<input type="hidden"  name="buWeixinconfig.fuid"     value="${buWeixinconfig.fuid}"  />
		  </div>
		</div>--%>
		
		<s:if test="%{type=='005001'}">
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信欢迎语：</strong></label>
			    <div class="controls">
					<textarea  name="buWeixinconfig.content" style="margin: 0px; width: 600px; height: 110px;">${buWeixinconfig.content}</textarea>
					<input type="hidden" name="buWeixinconfig.type" value="${type}"/>
					<input type="hidden"  name="buWeixinconfig.fuid"     value="${buWeixinconfig.fuid}"  />
					<input type="hidden"  name="buWeixinconfig.fullname"     value="微信欢迎语"  />
			    </div>
			</div>
		</s:if>
		
		<s:elseif test="%{type=='005002'}">
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>默认预发送微信号：</strong></label>
			    <div class="controls">
					<textarea  name="buWeixinconfig.content" style="margin: 0px; width: 300px; height: 20px;">${buWeixinconfig.content}</textarea>
					<input type="hidden" name="buWeixinconfig.type" value="${type}"/>
					<input type="hidden"  name="buWeixinconfig.fuid"     value="${buWeixinconfig.fuid}"  />
					<input type="hidden"  name="buWeixinconfig.fullname"     value="默认预发送微信号"  />
			    </div>
			</div>
		</s:elseif>
		
		<%--<s:else>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信摇奖概率：</strong></label>
			    <div class="controls">
					<textarea  name="buWeixinconfig.content" style="margin: 0px; width: 300px; height: 20px;">${buWeixinconfig.content}</textarea>
					<input type="hidden" name="buWeixinconfig.type" value="${type}"/>
					<input type="hidden"  name="buWeixinconfig.fuid"     value="${buWeixinconfig.fuid}"  />
					<input type="hidden"  name="buWeixinconfig.fullname"     value="微信摇奖概率"  />
			    </div>
			</div>
		</s:else>--%>
		
		
		
		 
							
		<div class="form-actions" >
			<!-- <input type="hidden" id="flag" name="flag" /> -->
			<button class="btn btn-large btn-success" type="button" onclick="edits()" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>提交	</button>
			<%--<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>--%>
		</div>
		
		</fieldset>
	</form>   

		</div>
	</div><!--/span-->
</div><!--/row-->
</body>
</html>
