<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>旅客申请</title>
<style type="text/css">
#head {
	width: 100%;
}

#head  a {
	padding-top: 5px;
	display: block;
	background-color: #eac873;
	border-radius: 5px;
	height: 25px;
	color: #555555;
	font-weight: bold;
	padding-left: 15px;
}

#body {
	width: 100%;
}

.input {
	border-radius: 5px;
}

#div2 {
	border: 1px solid grey;
}

#body {
	font-size: 0.24rem;
	color: #555555;
	font-weight: bold;
}
</style>
    <link href="<%=basePath%>css/verification.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			 $("#myform").validate({
				rules : {
					fullname:{
						required: true,
						myusername:true
					},
					phone:{
						required: true,
						mysj:true
					},
					Tourist:{
						required: true,
						myqidcard: true     
					},
					Reason:{
						required: true
					}
				} 
			});
		})
	</script>

</head>
<body>
	<div id="head">
		<a>游客申请</a>
	</div>

	<div id="div2">
		<form action="z_weixin/buExamineAction_insert" method="post" id="myform" >
			<div align="center" id="body">

				<table cellspacing="10">

					<tr>
						<td>游客姓名</td>
						<td><input type="text" class="input" name="buExamine.passenger" id="fullname" ></td>
					</tr>

					<tr>
						<td>手机号码</td>
						<td><input type="text" class="input" name="buExamine.ephone" id="phone">
						</td>
					</tr>

					<tr>
						<td>到店时间</td>
						<td><input type="text" class="input" name="buExamine.arrivetime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
						</td>
					</tr>

					<tr>
						<td>游客身份证</td>
						<td><input type="text" class="input" name="buExamine.pid" id="Tourist">
						</td>
					</tr>
					<tr>
						<td>来乌事由</td>
						<td><input type="text" class="input" name="buExamine.reason" id="Reason"></td>
					</tr>
					</tr>
					<tr>
						<td>住宿旅馆</td>
						<td><input type="text" class="input" name="buExamine.hostelname">
						</td>
					</tr>
					<tr>
						<td>经办人</td>
						<td><input type="text" class="input" name="buExamine.ephone">
						</td>
					</tr>
					
				</table>
			</div>

			<div align="center" id="tail">
				<input type="image" src="<%=basePath%>module/weixin/images/sq_03.png" > 
			</div>
		</form>
		<img src="<%=basePath%>module/weixin/images/sq_02.png" width="100%">
	</div>
</body>
</html>