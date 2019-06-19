<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link id="bs-css"
	href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css"
	rel="stylesheet" />
<link href="<%=basePath%>css/layout.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/eayui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/eayui/themes/default/easyui.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
	<script type="text/javascript"src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript"src="<%=basePath%>js/ckeditor/config.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		CKEDITOR.replace('a20161activity.introduce');	
	});
	function addtype(type,fuid){
		var url="activity!addType?type="+type+"&id="+fuid;
		window.location.href=url;
	}
	function save(id){
	var myform=$("#myform");
	myform.attr("action","<%=basePath%>activity!save?id="+id);
	myform.submit();
		/* var url="activity!save?content="+type+"&id="+fuid;
		window.location.href=url; */
	}
</script>
<title>My JSP 'Photoactivity.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form id="myform" action=""  method="post"  >
	<div align="center"
		style="position:absolute;top:40%;left:40%;margin:-250px 0 0 -250px;">
		<div class="control-group">
			<div class="controls">
				<textarea  class="cleditor" style="width: 800px;"
					name="a20161activity.introduce" rows="30"  id="content"><s:property value="a20161activity.introduce"  escape="false"/>
				</textarea>
			</div>
		</div>
		<div class="rsearch" align="left" >
			<div style="height: 4px"></div>
			<s:if test="a20161activity.type==0">
	
			<a href="javascript:addtype(<s:property value="a20161activity.type"/>,<s:property value="a20161activity.fuid"/>)" class="btn btn-success" style=""  title="活动开启"><i
				class=" icon-edit icon-white"></i>第一步：活动正式开启&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</s:if>
			<s:else>
				<a href="javascript:void(0)" class="btn btn-danger" title="活动开启" style="background:gray;height: 1000px;width: 1000px"><i
				class=" icon-edit icon-white"></i>第一步：活动正式开启&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</s:else>
		
			<a style="float: right;"  href="javascript:save(<s:property value="a20161activity.fuid"/>)" class="btn btn-success"  id="edit" title="保存内容">保存内容</a>
			<br>
			<div style="height: 10px"></div>
			<s:if test="a20161activity.type==1">
				<a href="javascript:addtype(<s:property value="a20161activity.type"/> ,<s:property value="a20161activity.fuid"/>)"
				class="btn btn-success" title="专家筛选"><i
				class=" icon-edit icon-white"></i>第二步：活动投稿结束，专家筛选&nbsp;&nbsp;&nbsp;</a>
				</s:if>
			<s:else>
				<a href="javascript:void(0)" style="background:gray" class="btn btn-danger" title="专家筛选" ><i
				class=" icon-edit icon-white"></i>第二步：活动投稿结束，专家筛选&nbsp;&nbsp;&nbsp;</a>
			</s:else>

			<br>
			<div style="height: 10px"></div>
			<s:if test="a20161activity.type==2">
				 <a href="javascript:addtype(<s:property value="a20161activity.type"/>,<s:property value="a20161activity.fuid"/> )"
				class="btn btn-success" title="进入投票(专家打分)"><i
				class=" icon-edit icon-white"></i>第三步：专家筛选结束，进入微信点赞&nbsp;</a>
			</s:if>
			<s:else>
				<a style="background:gray" href="javascript:void(0)" class="btn btn-danger" title="进入投票(专家打分)" ><i
				class=" icon-edit icon-white"></i>第三步：专家筛选结束，进入微信点赞&nbsp;</a>
			</s:else>
			<br>
			<div style="height: 10px"></div>
			<s:if test="a20161activity.type==3">
				<a
				href="javascript:addtype(<s:property value="a20161activity.type"/>,<s:property value="a20161activity.fuid"/> )" class="btn btn-success" title="活动结束"><i
				class=" icon-edit icon-white"></i>第四步：计算排名，公布结果排名&nbsp;&nbsp;&nbsp;</a>
			</s:if>
			<s:else>
				<a href="javascript:void(0)" style="background:gray" class="btn btn-danger" title="活动结束" ><i
				class=" icon-edit icon-white"></i>第四步：计算排名，公布结果排名&nbsp;&nbsp;&nbsp;</a>
			</s:else>
		
			<br>
			<div style="height: 30px"></div>
		</div>
	</div>
	</form>
</body>
</html>
