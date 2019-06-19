<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String otherId = request.getParameter("otherId");
	String name = request.getParameter("name");
	name = name == null ? "" : name;
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title></title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>
		<style>
 
</style>

<script>
	$(function() {
		var img='${skey}';
		if(img!='null'&&img.length>0){
			$("").uploadShow({data : img,path:"/upload/" });
		}else{
			$("").uploadInit();
		}
	});
	
	function sub(){
		$("#fro").submit();
	}
</script>
	</head>
	<body>
		<form id="fro" name="fro" action="phoneJd!test" enctype="multipart/form-data" method="post">
			<div id="file_div"></div>
			<div> <a id="AddBtn"   href="javascript:;">新增上传</a> </div>
			<button  onclick="sub()"  >提交</button>
		</form>
	</body>
	
	<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title></title>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/ImgUpload/uploadPreview.js"></script>
		<style>
 
</style>

		<script>
	$(function() {
		$("#up_btn").uploadPreview({
			Img : "ImgPr",
			Width : 120,
			Height : 120,
			ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
			Callback : function() {
			}
		});
		$("#up_img").click(function() {
			$("#up_btn").click();
		});
		var ImgUpNum = 1;
		$("#AddBtn")
				.click(
						function() {
							ImgUpNum = ImgUpNum + 1;
							$("#up_div")
									.append(
											'<div><input type="file" name="img" id="up_btn' + ImgUpNum + '" /><div><img id="ImgPr' + ImgUpNum + '" width="120px" height="120px" /></div><a id="up_img' + ImgUpNum + '" index="' + ImgUpNum + '" href="javascript:;">打开图片'
													+ ImgUpNum + '</a></div>');
							$("#up_btn" + ImgUpNum).uploadPreview(
									{
										Img : "ImgPr" + ImgUpNum,
										Width : 120,
										Height : 120,
										ImgType : [ "gif", "jpeg", "jpg",
												"bmp", "png" ],
										Callback : function() {
										}
									});
							$("#up_img" + ImgUpNum).click(function() {
								$("#up_btn" + $(this).attr("index")).click();
							});
						});
	});
	
	function sub(){
		$("#fro").submit();
	}
</script>
	</head>
	<body>
		<form id="fro" name="fro" action="phoneJd!test" enctype="multipart/form-data" method="post">
			<div id="up_div">
				<div>
					<input type="file" id="up_btn" name="img" />
					<div class="div_h">
						<img id="ImgPr" width="120px" height="120px" />
					</div>
					<a id="up_img" href="javascript:;">打开图片</a>
				</div>
			</div>
			<br />
			<div>
				<a id="AddBtn" href="javascript:;">新增上传</a>
			</div>
			<button  onclick="sub()"  >提交</button>
		</form>
	</body>
</html>	
	
	 -->
</html>