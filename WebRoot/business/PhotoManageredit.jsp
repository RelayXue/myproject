 
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
	<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>

<script type="text/javascript">
	//ckeditor的content替换
	$(document).ready(function() {
		CKEDITOR.replace('a20161characterworks.introduce');
		//-----图片上传--------
		var img='${a20161characterworks.imgurl}';
		if(img!='null'&&img.length>0&&img!='default.jpg'){
			$("").uploadShow({data : img,path:"/upload/B_" });
		}else{
			$("").uploadInit();
		}
	});


	
	function edits(){
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
		myform.attr("action","<%=basePath%>characterworks!save?reNum="+reNum+"&imgNum="+imgNum+"&skey=${skey}&indexPage=${indexPage}&type=<%=type%>");
		myform.submit();
	}
	 var n=0;
	 function count(){
	 	n++;
	 	if(n>5){
	 		$("#addpic").hide();
	 	}	
	 }
	 
function remove(num){
	$("input[id='up_btn"+ num+ "']").parent().remove();
	reNum+=num+",";
	//$("input[id='up_btn"+ num+ "']").parent().remove();
	n--;
	if(n<6){
	 		$("#addpic").show();
	 	}	
}
	
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>作品管理</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="characterworks!save"  id="myform"  method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
			
				<div class="control-group" >
						<label class="control-label" for="fileInput" style="color: gray"><strong>图片上传:</strong></label>
					<div class="controls">
						<div id="file_div"></div>
						<div style="margin-top: 30px;margin-bottom: 30px;" id="addpic"><a id="AddBtn" onclick="count()"  href="javascript:;"><img src="<%=basePath%>images/upload.png"/></a></div>
						<span style="color: blue;">注：最多可以提交6张图片<span/>
					</div>
				</div>
					<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>作品名称：</strong></label>
							  <div class="controls">
								<input type="hidden"  name="a20161characterworks.fuid"   value='${a20161characterworks.fuid}'  />
								<input type="text"  name="a20161characterworks.title"   value='${a20161characterworks.title}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>作者：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.peoplename"   value='${a20161characterworks.peoplename}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>
						
						 	<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>联系电话：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.phone"   value='${a20161characterworks.phone}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div> 
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>地址：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.address"   value='${a20161characterworks.address}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div> 
						<%-- 	<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>身份证：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.peopleid"   value='${a20161characterworks.peopleid}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>  --%>
						 	<%-- <div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>微信id：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.weixinid"   value='${a20161characterworks.weixinid}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>   --%>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>内容：</strong></label>
							  <div class="controls">
								<textarea id="content"  name="a20161characterworks.introduce"    class="span6 typeahead"  >${a20161characterworks.introduce}</textarea>
							  </div>
							</div>
							 
							
							<div class="form-actions" >
								<!-- <input type="hidden" id="flag" name="flag" /> -->
								<button class="btn btn-large btn-success" type="button" onclick="edits()" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>提交	</button>
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->


		


	
	</body>
</html>
