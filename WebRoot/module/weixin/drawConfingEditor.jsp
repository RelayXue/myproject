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
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
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
		//回显抽奖时间断
		$("#weekstart").val('${buWeixinconfig.weekstart}');
		$("#weekend").val('${buWeixinconfig.weekend}');
		var beginDraw = '${buWeixinconfig.beginDraw}';
		if(beginDraw=='1'){
			$("#yes").attr("checked","checked");
		}else{
			$("#no").attr("checked","checked");
		}
		
	});


	/*添加或修改*/
	function edits(){
		var myform=$("#myform");
		myform.attr("action","<%=basePath%>buWeixinconfig!edit?type=${type}");
		myform.submit();
		
	}
	
	/*清空时间*/
	function clearTime(){
		$("#drawstarttime").val("");
		$("#drawendtime").val("");
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
	<form class="form-horizontal" action=""  id="myform"  method="post" enctype="multipart/form-data"  >
		<fieldset>
		<legend></legend>
		
			<%--
		
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖是否开始启动：</strong></label>
			    <div class="controls">
			    	<input id="yes" type="radio" name="buWeixinconfig.beginDraw" value="1"/>是&nbsp;
			    	<input id="no" type="radio" name="buWeixinconfig.beginDraw"  value="0"/>否 &nbsp; <font style="color:red;">(*提示：默认选择 否)</font>
			    </div>
			</div>
			
		
			--%><div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信抽奖时间段：</strong></label>
			    <div class="controls">
					开始时间：<input type="text" id="drawstarttime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="buWeixinconfig.drawstarttime"  style="width: 140px;" value='<s:date name="buWeixinconfig.drawstarttime" format="yyyy-MM-dd HH:mm:ss"  />'/>
					
					结束时间：<input type="text" id="drawendtime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd  HH:mm:ss'})"    name="buWeixinconfig.drawendtime" style="width: 140px;" value='<s:date name="buWeixinconfig.drawendtime" format="yyyy-MM-dd HH:mm:ss" />' />
			    
			 	   <button class="btn btn-large btn-success" type="button" onclick="clearTime()" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>清空时间</button>
			   </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color : gray"><strong>每周抽奖时间段：</strong></label>
			    <div class="controls">
					星期：<select style="width: 90px;border:1px solid gray" name="buWeixinconfig.weekstart" id="weekstart">
						<option value="">-请选择-</option >
						<option value="1">一</option>
						<option value="2">二</option>
						<option value="3">三</option>
						<option value="4">四</option>
						<option value="5">五</option>
						<option value="6">六</option>
						<option value="7">日</option>
					</select>
					 <font size="4">→</font>
					星期：<select style="width: 90px;border:1px solid gray" name="buWeixinconfig.weekend" id="weekend">
						<option value="">-请选择-</option >
						<option value="1">一</option>
						<option value="2">二</option>
						<option value="3">三</option>
						<option value="4">四</option>
						<option value="5">五</option>
						<option value="6">六</option>
						<option value="7">日</option>
					</select>
			    </div>
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信抽奖概率：</strong></label>
			    <div class="controls">
			   
			    	<input type="text" name="buWeixinconfig.content" value="${buWeixinconfig.content}" onkeyup="value=value.replace(/[^\d.]/g,'')" /><font style="color:red;">(*提示：例如 20表示中奖的概率为20%)</font>
					<input type="hidden" name="buWeixinconfig.type" value="005003"/>
					<input type="hidden"  name="buWeixinconfig.fuid"     value="${buWeixinconfig.fuid}"  />
					<input type="hidden"  name="buWeixinconfig.fullname"     value="微信抽奖配置"  />
			    </div>
			   
			</div>
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信抽奖总数：</strong></label>
			    <div class="controls">
			    	<input type="text" name="buWeixinconfig.drawTotal" value="${buWeixinconfig.drawTotal}" onkeyup="value=value.replace(/[^\d.]/g,'')" />
			    </div>
			</div>
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖未开始说明：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinconfig.notDrawInfo" style="width: 400px;height:100px">${buWeixinconfig.notDrawInfo}</textarea>
			    </div>
			   
			</div>
			<%--<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>抽奖开始时间说明：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinconfig.drawStarttimeInfo" style="width: 400px;height:100px">${buWeixinconfig.drawStarttimeInfo}</textarea>
			    </div>
			   
			</div>
			
			--%><div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>中奖说明：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinconfig.winningInfo" style="width: 400px;height:100px">${buWeixinconfig.winningInfo}</textarea>
			    </div>
			</div><%--
			
			<div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>未中奖说明：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinconfig.notWinningInfo" style="width: 400px;height:100px">${buWeixinconfig.notWinningInfo}</textarea>
			    </div>
			   
			</div>
			
			
			--%><div class="control-group">
			 	<label class="control-label" for="typeahead" style="color: gray"><strong>微信抽奖规则：</strong></label>
			    <div class="controls">
			    	<textarea  name="buWeixinconfig.regulation" style="width: 400px;height:100px">${buWeixinconfig.regulation}</textarea> 
			    	 <span style="color: red;">字数请在35字以内</span> 
			    </div>
			   
			</div>
		
		<div class="form-actions" >
			<!-- <input type="hidden" id="flag" name="flag" /> -->
			<button class="btn btn-large btn-success" type="button" onclick="edits()" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>提交</button>
		</div>
		
		</fieldset>
	</form>   

		</div>
	</div><!--/span-->
</div><!--/row-->
</body>
</html>
