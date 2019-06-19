 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type=request.getParameter("type");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title>后台维护信息</title>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
			<link rel="stylesheet" type="text/css" href="<%=basePath%>css/verification.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
			<script type="text/javascript" src="<%=basePath%>js/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath %>js/common/AutoSelect.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
		<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>

		<script>
	function edit(){
		var myform=$("#myform");
		myform.attr("action","<%=basePath%>buBarcode!edit?type=<%=type%>&skey=${skey}&indexPage=${indexPage}");
		myform.submit();
}
function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
function res(){
	 window.location.href="javascript:history.go(-1);";
}
	$(document).ready(function() {
		 
		var sBasePath ="<%= basePath%>js/fckeditor/";
		var oFCKeditor = new FCKeditor( 'editor1' ) ;
		oFCKeditor.Width="100%";
		oFCKeditor.Height="500";
		oFCKeditor.BasePath	= sBasePath ;
		oFCKeditor.ReplaceTextarea();
		 
		var codelx='${buBarcode.type}';
		codelx=codelx.length==0?'<%=type%>':codelx;
		$("#ftype").AutoSelect({
			data:basetemp,	
			name:"buBarcode.type",
			showNum :3,
			initCode : codelx
		});
		
		$("#myform").validate({
			rules : {
				fullname:{
					required: true
				},
				cwidth:{
					number: true
				} 
			} 
			});
      });
	</script>
		<style>
 
</style>
	</head>
<body>
		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div class="inright">
				<div class="rightnav">
					<a href="#" class="active">二维码管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									名称：
								</td>
								<td>
								 <input type="text"  class="input-text"    name="buBarcode.code"  value='${buBarcode.code}' />
									 <input type="hidden"     name="buBarcode.fuid"  value='${buBarcode.fuid}' />
								</td>
							</tr>
							<tr>
								<td width="75">
										类型：
								</td>
								<td>
								  <div id="ftype" ></div>
								</td>
							</tr>	
								
							<!--<tr>
								<td width="75">
									描述：
								</td>
								<td>
								<textarea  rows="4" cols="70" name="buBarcode.description">${buBarcode.description}</textarea>
								</td>
							</tr>	
							--><tr>
								<td width="75">
									内容
								</td>
								<td>
									<textarea  style='border: 1px solid #bfbfbf' cols="50" id="editor1" rows="5" name="buBarcode.content">${ buBarcode.content}</textarea>
								</td>
							</tr>	
						 <tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<input name="" type="button" class="btn2" onclick="edit()" value="提交" />
									<input name="" type="button" value="返回" class="btn4" onclick="res()" /> 
								</td>
							</tr>
						
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>