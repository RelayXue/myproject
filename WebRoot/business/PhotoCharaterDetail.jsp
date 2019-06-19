 
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
	  .bigDiv{
	  	background:rgba(0, 0, 0, 0.5) none repeat scroll 0 0 !important;
	  	position:absolute;
	  	position:fixed;
	  	z-index:-10;
	  	width:100%;
	  	height:100%;
	  	float:left;
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
	<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload2.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/eayui/easyui.css"/>
	<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	//ckeditor的content替换
	$(document).ready(function() {
		//-----图片上传--------
		var img='${a20161characterworks.imgurl}';
		if(img!='null'&&img.length>0&&img!='default.jpg'){
			$("").uploadShow({data : img,path:"/upload/" });
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
	 function show(id){
		// $("#bigImg").attr("src",document.getElementById(id).src)
		window.open(document.getElementById(id).src);
	 }
	 function none(){
		 document.documentElement.style.overflow='scroll';
		 document.getElementById("bigDiv").style.zIndex=-10;
	 }
	 function Open_Dialog(id) {
				$('#mydialog').show();
				$('#mydialog').dialog({
					collapsible : true,
					minimizable : true,
					maximizable : true,
					
					buttons : [ {
						text : '提交',
						handler : function() {
						 var val = $("#score").val();
						 var type=$("#ty").val();
						 if(!isNaN(val)){
						 	if(val<=100&&val>=0){
						 	var url="characterworks!setScore?id="+id+"&score="+val+"&type="+type;
						 	window.location.href=url;
						 	}else{
						 		alert("该评分满分100分，请输入0-100的数字！");
						 	}
						 }else{
						 	alert("输入的值不是数字,请正确输入数字!");
						 }
						 
						}
					}, {
						text : '取消',
						handler : function() {
							$('#mydialog').window('close');
						}
					} ]
				});
			}
</script>
	</head>

	<body>
	<div style="position: absolute;z-index:-5;height:100%;width:100%;position:fixed;background:#ffffff"></div>
	<div class="bigDiv" onclick="none()" id="bigDiv">
		<img src="" id="bigImg" />
	</div>
	<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>作品详情</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="characterworks!save"  id="myform"  method="post" enctype="multipart/form-data"  >
				<fieldset>
				<legend></legend>
			
				<div class="control-group" >
						<label class="control-label" for="fileInput" style="color: gray"><strong>图片展示:</strong></label>
					<div class="controls">
						<div id="file_div"></div>
					</div>
				</div>
					<div class="control-group">
								<input  type="hidden"  id="ty" value="${type}"/>
							  <label class="control-label" for="typeahead" style="color: gray"><strong>作品名称：</strong></label>
							  <div class="controls">
								<input type="hidden"  name="a20161characterworks.fuid"   value='${a20161characterworks.fuid}'  />
								<div style="height: 30px;width: 500px;line-height:30px; "><s:property value="a20161characterworks.title" escape="false"/></div>
								
							   </div>
							</div>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>作者：</strong></label>
							  <div class="controls">
							  <div>
							  	
							  </div>
							  	<div style="height: 30px;width: 500px;line-height:30px; "><s:property value="a20161characterworks.peoplename" escape="false"/></div>
								 </div>
							</div>
						
						 	<%-- <div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>微信id：</strong></label>
							  <div class="controls">
								<input type="text"  name="a20161characterworks.weixinid"   value='${a20161characterworks.weixinid}' class="span6 typeahead" id="typeahead"   data-items="4" />
							  </div>
							</div>   --%>
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>地址：</strong></label>
							  <div class="controls">
							  <div style="height: 30px;width: 500px;line-height:30px;   "><s:property value="a20161characterworks.address" escape="false"/></div>
								 </div>
							</div> 
							<div class="control-group">
							  <label class="control-label" for="typeahead" style="color: gray"><strong>内容：</strong></label>
							  <div class="controls">
							  	<div style="overflow:auto;height: 100px;width: 500px"><s:property value="a20161characterworks.introduce" escape="false"/></div>
						</div>
							</div>
							 
							<div id="mydialog" style="display:none;padding:5px;width:300px;height:180px;" title="评分弹框" data-options="modal:true"> 
								<div style="padding:20px 20px 10px 40px;">
								<label class="lbInfo">分数：</label> 
									<input id="score" type="text" class="easyui-validatebox" required="true"  /><br /> 
								</div>
							</div> 
							
							<div class="form-actions" >
								<!-- <input type="hidden" id="flag" name="flag" /> -->
							<s:if test="a20161characterworks.votetype==1">
							<s:if test="a20161activity.type==3">
							<a class="btn btn-large btn-success" href="javascript:Open_Dialog('<s:property value="a20161characterworks.fuid"  />')"><i class="icon-edit icon-white"></i>专家评分</a>
							</s:if>
							<s:elseif test="a20161activity.type<3">
								<a class="btn btn-large btn-danger" href="javascript:;">评分未开启</a>
							</s:elseif>
							<s:elseif test="a20161activity.type>3">
								<a class="btn btn-large btn-warning" href="javascript:;">评分已完成</a>
							</s:elseif>
							</s:if>
							<s:if test="a20161activity.type==2">
							<s:if test="a20161characterworks.votetype!=1">
							<a class="btn btn-large btn-success" href="<%=basePath%>characterworks!setVote?id=<s:property value="a20161characterworks.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=1&type=${type}"><i class="icon-edit icon-white"></i>设为选中</a>
							</s:if>
							<s:else>
							<a class="btn btn-large btn-success" href="<%=basePath%>characterworks!setVote?id=<s:property value="a20161characterworks.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=0&type=${type}"><i class="icon-edit icon-white"></i>取消选中</a>
							</s:else>
							</s:if>
							<s:elseif test="a20161activity.type<2">
							<a class="btn btn-large btn-danger" href="javascript:;">筛选未开启</a>	
							</s:elseif>
							<s:elseif test="a20161activity.type>2">
							<a class="btn btn-large btn-warning" href="javascript:;">筛选已完成</a>	
							</s:elseif>
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
							

						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->
			</div><!--/row-->
	</body>
</html>
