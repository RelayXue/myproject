 
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
		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/gray/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/color.css"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buPersonnel!selectPerson?skey=${skey}',
					showNum : 6  // 显示数字
					,show:1
				});
	 });
	 
	 function serach(){
		 var skey=$("#mc").val();
		 var url="buPersonnel!selectPerson?skey="+skey;
		 window.location.href=url;
	 }
	 
	  function sel(mobile){
        
      /* var doc= parent.frames['fa'].document;
		doc.getElementById("searchKey").value=mobile; 这种写法和下面的一样*/
	 	$('#searchKey', parent.frames['fa'].document).attr("value",mobile);
	 	window.parent.d_close();//执行的是从父类关闭子窗口
	 }
	
	
		
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">选择人员</a>
		</div>
		<div class="rsearch">
			<span class="info">人员姓名:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
			<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
		</div>
	
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>
					<th width="3%">
						编号&nbsp;
					</th>
					<th width="10%">
						姓名
					</th>
					<th width="10%">
						手机
					</th>
					<th width="16%">
						操作
					</th>
				</tr>
				<s:iterator value="buPersonnel_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
								<td><s:property value="#list.realname" />&nbsp; </td>
								<td><s:property value="#list.mobile" />&nbsp; </td>
						<td>
					<a href="javascript:sel('<s:property value="#list.mobile" />')">选择</a>
							
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		
		 
	</body>

</html>