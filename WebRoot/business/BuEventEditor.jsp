
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

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
		<title>后台维护信息</title>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
				<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath %>js/common/AutoSelect.js"></script>

		<script>
	function edit(){
		var myform=$("#myform");
		myform.attr("action","<%=basePath%>buEvent!edit");
		myform.submit();
	}
	function resetB() {
		$("#myform").find(("input[type='text']")).val("");
	}
	function res() {
		window.location.href = "javascript:history.go(-1);";
	}
	$(document).ready(function() {
		$("#myform").validate({
			rules : {
				fullname : {
					required : true
				},
				cwidth : {
					number : true
				}
			}
		});
		var codelx='${buEvent.type}';
					codelx=codelx.length==0?'003001':codelx;
					$("#ftype").AutoSelect({
						data:basetemp,	
						name:"buEvent.type",
						showNum :2,
						initCode : codelx
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
					<a href="#" class="active">事件管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td width="75">
									编号：
								</td>
								<td>
									 <input type="text"     class="inp2"  name="buEvent.dnumber"  value="${buEvent.dnumber}" />
									 <input type="hidden"     name="buEvent.fuid"  value='${buEvent.fuid}' />
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
							<tr>
								<td width="75">
									标题：
								</td>
								<td>
									<input type="text"   class="inp2" id="fullname"   name="buEvent.title"  value='${buEvent.title}' /> 
								</td>
							</tr>
							<tr>
								<td width="75">
									事发地点：
								</td>
								<td>
									<input type="text"   class="inp2"    name="buEvent.address"  value='${buEvent.address}' /> 
								</td>
							</tr>
							<tr>
								<td width="75">
									处理状态：
								</td>
								<td>
									<select style='border: 1px solid #bfbfbf;' name="buEvent.status" >
										<s:if test="buEvent.status==1">
										<option  value="0">未处理</option>
										<option selected="selected" value="1">已处理</option>
										</s:if>
										<s:else>
										<option selected="selected" value="0">未处理</option>
										<option value="1">已处理</option>
										</s:else>
										
									</select>  
								</td>
							</tr>
							<tr>
								<td width="75">
									事件描述：
								</td>
								<td>
									 <textarea  style='border: 1px solid #bfbfbf'  rows="5" cols="40" name="buEvent.description">${buEvent.description}</textarea>  
								</td>
							</tr>
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
									<input name="" value="提交" type="button" class="btn2" onclick="edit()" />
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