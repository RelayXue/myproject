<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
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
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'<%=basePath%>fnews!votingrecordPage',
					showNum : 6  // 显示数字
					,show:1
				});
	 });
	 
	</script>
	</head>
	<body>
	
	<div class="rightnav">
			<a href="#" class="active">  投票记录</a>
	</div>	
	
	<div class="rsearch" style="float:left">
	</div>
	
	<div class="order" style="color:#4a4a4a; font-weight:bold;">
	   
	</div>
	<div class="rhandle" style="clean:both">
	</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="10%">
						编号
					</th>
					<th width="40%">
						投票人微信
					</th>
					<th width="50%">
						所投的诗歌
					</th>
				</tr>
				
				<s:iterator value="votingrecord_list" var="list" status="stuts">
					<tr>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
						<td>
							<span><s:property value="#list.weixinid" /> </span>
						</td>
						<td>
							<span><s:property value="#list.becast" /> </span>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
</body>

</html>