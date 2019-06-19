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
		<script type="text/javascript"src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript"src="<%=basePath%>js/ckeditor/config.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	/*ckeditor的content替换*/
	$(document).ready(function() {
		//CKEDITOR.replace('buWeixinconfig.content');
		
		
		//是否中奖
		var iswinning="${buWeixinluckdraw.iswinning}";
		if(iswinning==1){
		   $("#iswinning_yes").attr("checked","checked");
		}else{
		   $("#iswinning_no").attr("checked","checked");
		}
		
		//是否中奖
		var isaward="${buWeixinluckdraw.isaward}";
		if(isaward==1){
		   $("#isaward_yes").attr("checked","checked");
		}else{
		   $("#isaward_no").attr("checked","checked");
		}
	});


	/*添加或修改*/
	function edits(){
		var myform=$("#myform");
		
		//-----------------------------------------
		myform.attr("action","<%=basePath%>buWeixinluckdraw!edit");
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
	<form class="form-horizontal" action="buWeixinluckdraw!edit"  id="myform"  method="post" enctype="multipart/form-data"  >
		<fieldset>
		<legend></legend>
		
	
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖人ID：</strong></label>
			    <div class="controls">
			    	<input type="text" name="buWeixinluckdraw.userid"  value="${buWeixinluckdraw.userid}"/>
			    	<input type="hidden" name="buWeixinluckdraw.fuid" value="${buWeixinluckdraw.fuid}" />
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖人昵称：</strong></label>
			    <div class="controls">
			    	<input type="text" name="buWeixinluckdraw.username"  value="${buWeixinluckdraw.username}"/>
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>是否中奖：</strong></label>
			    <div class="controls">
			    	<input type="radio" id="iswinning_yes" name="buWeixinluckdraw.iswinning" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="radio" id="iswinning_no" name="buWeixinluckdraw.iswinning" value="2"/>否
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖时间：</strong></label>
			    <div class="controls">
			    	<input type="text"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"    name="buWeixinluckdraw.drawtime"  value='<s:date name="buWeixinluckdraw.drawtime" format="yyyy-MM-dd" />' />
			    </div>
			</div>
			
		
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>是否领奖：</strong></label>
			    <div class="controls">
			    	<input type="radio" id="isaward_yes" name="buWeixinluckdraw.isaward" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="radio" id="isaward_no" name="buWeixinluckdraw.isaward" value="2"/>否
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>领奖时间：</strong></label>
			    <div class="controls">
			    	<input type="text"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"    name="buWeixinluckdraw.awardtime"  value='<s:date name="buWeixinluckdraw.awardtime" format="yyyy-MM-dd" />' />
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>中奖内容：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinluckdraw.content" style="margin: 0px; width: 485px; height: 125px;">${buWeixinluckdraw.content}</textarea>
			    </div>
			</div>
			
							
		<div class="form-actions" >
			<!-- <input type="hidden" id="flag" name="flag" /> -->
			<button class="btn btn-large btn-success" type="button" onclick="edits()" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>提交	</button>
			<button class="btn btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
		</div>
		
		</fieldset>
	</form>   

		</div>
	</div><!--/span-->
</div><!--/row-->
</body>
</html>
