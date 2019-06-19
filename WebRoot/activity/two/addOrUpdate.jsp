<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId = request.getParameter("otherId");
	String name = request.getParameter("name");
	name = name == null ? "" : name;
	String type = request.getParameter("type");
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
	function changeSelected(obj){
		var cc;
		$("input[name='a20162subject.type']").val(obj)
		for(var i=0;i<4;i++){
			if(i<obj){
				$("#"+i).attr("disabled",false);
				cc=document.getElementsByName("yes")[i];
				$(cc).attr("disabled",false);
			}else{
				$("#"+i).attr("disabled",true);
				cc=document.getElementsByName("yes")[i];
				$(cc).attr("disabled",true);
			}
		}
	}
	
	function changeYes(v){
		$("input[name='a20162subject.yes']").val(v);
	}
</script>
	</head>

	<body>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2>. 新增旅店表单</h2>
						<div class="box-icon"></div>																
					</div>
					<div class="box-content">
			<form class="form-horizontal" action="<%=basePath%>poetryChallenge!addOrUpdate"  method="post">
				<fieldset>
				<!-- 旅店名称 
					*************************************************************************************************************
					-->
							<div class="control-group">
								<table class="control-label">
									<tr>
										<td><b>题目</b>:</b></td>
									</tr>
									<tr>
										<td><b>题型</b>:</b></td>
									</tr>
									<tr>
										<td><b>选项 A</b>:</b></td>
									</tr>
									<tr>
										<td><b>选项 B</b>:</b></td>
									</tr>
									<tr>
										<td><b>选项 C</b>:</b></td>
									</tr>
									<tr>
										<td><b>选项 D</b>:</b></td>
									</tr>
									<tr>
										<td><b>正确答案</b>:</b></td>
									</tr>
								</table>
								<table class="controls">
									<tr>
										<td><input type="text" style="width:500px" name="a20162subject.title" value='<s:property value="a20162subject.title"/>'></td>
									</tr>
									<tr>
										<td style="font-size:15px">
											<input type="radio" name="type" <s:if test="a20162subject==null || a20162subject.type==2">checked="checked"</s:if> value="2" onclick="changeSelected(this.value)"/>&nbsp;&nbsp;&nbsp;简单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="type" <s:if test="a20162subject!=null && a20162subject.type==3">checked="checked"</s:if> value="3" onclick="changeSelected(this.value)"/>&nbsp;&nbsp;&nbsp;中等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="type" <s:if test="a20162subject!=null && a20162subject.type==4">checked="checked"</s:if> value="4" onclick="changeSelected(this.value)"/>&nbsp;&nbsp;&nbsp;困难
											<input type="hidden" name="a20162subject.type" <s:if test="a20162subject==null">value="2"</s:if><s:else>value="<s:property value="a20162subject.type"/>"</s:else>/>
											
										</td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="a20162subject.one" id="0" value='<s:property value="a20162subject.one"/>'></td>
									</tr>
									<tr>
										<td><input type="text" style="width:500px" name="a20162subject.two" id="1" value='<s:property value="a20162subject.two"/>'></td>
									</tr>
									<tr>
										
										<td>
											<s:if test="a20162subject!=null && a20162subject.type>2">
												<input type="text" style="width:500px" name="a20162subject.three" id="2" value='<s:property value="a20162subject.three"/>'>
											</s:if>
											<s:else>
												<input type="text" style="width:500px" name="a20162subject.three" id="2" disabled="disabled">
											</s:else>
										</td>
									</tr>
									<tr>
										<td>
											<s:if test="a20162subject!=null && a20162subject.type>3">
													<input type="text" style="width:500px" name="a20162subject.four" id="3" value='<s:property value="a20162subject.four"/>'>
												</s:if>
												<s:else>
													<input type="text" style="width:500px" name="a20162subject.four" id="3" disabled="disabled">
											</s:else>
										</td>
									</tr>
									<tr>
										<s:if test="a20162subject==null">
											<td style="font-size:15px">
												<input type="radio" name="yes" checked="checked" value="0" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="yes" value="1" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="yes" disabled="disabled" value="2" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="yes" disabled="disabled" value="3" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;D
												<input type="text" value="0" name="a20162subject.yes"/>
											</td>
										</s:if>
										<s:else>
											<td style="font-size:15px">
												<input type="radio" name="yes" checked="checked" value="0" onclick="changeYes(this.value)" <s:if test="a20162subject.yes==0">checked="checked"</s:if>/>&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="yes" value="1" onclick="changeYes(this.value)" <s:if test="a20162subject.yes==1">checked="checked"</s:if>/>&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<s:if test="a20162subject==null">
													<input type="radio" name="yes" value="2" onclick="changeYes(this.value)" disabled="disabled"/>&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="yes" value="3" onclick="changeYes(this.value)" disabled="disabled"/>&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</s:if>
												<s:else>
													<s:if test="a20162subject.three==null || a20162subject.three==''">
														<input type="radio" name="yes" value="2" onclick="changeYes(this.value)" disabled="disabled"/>&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</s:if>
													<s:else>
														<s:if test="a20162subject.yes==2">
															<input type="radio" name="yes" value="2" onclick="changeYes(this.value)" checked="checked"/>&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</s:if>
														<s:else>
															<input type="radio" name="yes" value="2" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</s:else>
													</s:else>
													
													<s:if test="a20162subject.four==null || a20162subject.four==''">
														<input type="radio" name="yes" value="3" onclick="changeYes(this.value)" disabled="disabled"/>&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</s:if>
													<s:else>
														<s:if test="a20162subject.yes==3">
															<input type="radio" name="yes" value="3" onclick="changeYes(this.value)" checked="checked"/>&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</s:if>
														<s:else>
															<input type="radio" name="yes" value="3" onclick="changeYes(this.value)"/>&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</s:else>
													</s:else>
												</s:else>
												
												<input type="hidden" value="<s:property value="a20162subject.yes"/>" name="a20162subject.yes"/>
											</td>
										</s:else>
									</tr>
								</table>
								<input type="hidden" value="<s:property value="a20162subject.fuid"/>" name="a20162subject.fuid"/>
							</div>
					<!-- 旅店名称 
					*************************************************************************************************************
					-->	
					<!-- 按钮 
					*************************************************************************************************************
					-->		
							<div class="form-actions" >
								<button class="btn btn-large btn-success" type="submit" style="border-bottom-width: 0px;" ><i class="icon-check icon-white"></i>保存	</button>
								<button class="btn btn-large btn-primary " type="button" style="border-bottom-width: 0px;" onclick="javascript:history.back()"><i class="icon-backward icon-white"></i>返回</button>
							</div>
					<!-- 按钮 
					*************************************************************************************************************
					-->		
						  </fieldset>
						</form>   

					</div>
				</div><!--/span-->

			</div><!--/row-->
	</body>
</html>
