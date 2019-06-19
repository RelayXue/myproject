<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId=request.getParameter("otherId");		
	String name=request.getParameter("name");	
	name=name==null?"":name;	
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title></title>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>

		<script>
	$(document).ready(function() {

		 
	});
	
	function add(){
		var ff = $("#uploadImg");
		if(ff.val().length==0){
			alert("请先选择图片");
			return;
		}
		if(!CheckPic(ff.val())){
			alert("请上传正确格式的图片");
			return;
		}
		 $("#form1").submit();
	}
</script>
		<style>
</style>
	</head>
	<body>
		<form id="form1" action="fileUpload!edit?otherId=<%=otherId %>&tname=<%=name %>" method="post" ENCTYPE="multipart/form-data">
			<div class="inright" style="width: 500px;min-width: 500px">
				<div class="sdetail">
					<div class="sdtable">
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<input type="file" id="uploadImg" name="uploadImg"    class="input-text" />
									<input name="" type="button" value="上传" class="btn2" onclick="add()" />
								</td>
							</tr>
							<tr>
								<td>

								</td>
								<td>

								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table border="1" bordercolor="#a0c6e5" style="border-collapse: collapse; width:50%;">
										<tr style="height: 40px">
											<td style="text-align: center;">
												序号
											</td>
											<td style="text-align: center;">
												路径
											</td>
											<td style="text-align: center;" width="20%">
												缩略图
											</td>
											<td style="text-align: center;" width="10%">
												操作
											</td>
										</tr>
										<s:if test="obImages_list!=null">
											<s:iterator value="obImages_list" status="stuts" var="list">
											<tr>
												<td style="text-align: center;">
													<s:property value="#stuts.index+1" /> <input type="hidden" value="${otherId}" name="uid" />
												</td>
												<td style="text-align: center;">
													/upload/500-<s:property value="#list.address" /> <input type="hidden" name="imgHid" id="imgHid" value="<s:property value="#list.address" />" /> 
												</td>
												<td style="text-align: center;">
													<a target="_blank"  href="<%=basePath %>upload/1500-<s:property value="#list.address" /> "><img width="50px" src="/upload/500-<s:property value="#list.address" /> " alt="点击查看大图" /></a>
												</td>
												<td style="text-align: center;">
													<a href="fileUpload!delete?id=<s:property value="#list.fuid" />&otherId=<%=otherId %>">删除</a>
												</td>
											</tr>
											</s:iterator>
										</s:if>
										<s:else>
											<tr style="height: 40px">
												<td colspan="4" style="text-align: center;">
													<span style="color: red;"> 暂无图片上传 </span>
												</td>
											</tr>
										</s:else>
									</table>
							</td>
							</tr>
							<tr>
								<td width="75">
									&nbsp;
								</td>
								<td>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>

</html>