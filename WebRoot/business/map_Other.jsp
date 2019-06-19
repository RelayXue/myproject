 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String name=request.getParameter("name");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title>后台维护信息</title>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>
		<script>
	 jQuery(document).ready(function($) {
	 	var codelx='${skey}';
		codelx=codelx.length==0?'002001':codelx;
		$("#ftype").AutoSelect({
			data:basetemp,	
			name:"buInterest.ftype",
			showNum :2,
			initCode : codelx
		});
	 });
	 
	 function serach(){
		 var skey=$("select[name='buInterest.ftype']").val();
		 var url="buInterest!showInter?skey="+skey;
		 window.location.href=url;
	 }
	</script>
		<style>
 
</style>
	</head>
	<body>
		<div class="rsearch">
			<span class="info">类别:</span>
			<div style="float: left;margin-right: 20px" id="ftype"></div> &nbsp;&nbsp;
			<a href="javascript:serach()"  class="btn" title="搜索">搜索</a>
		</div>
		<div class="rtable">
			<table width="100%" border="0">
				<s:iterator value="buInterest_list" var="list" status="stuts">
					<tr>
						<td>
							<a href="javascript:window.parent.getInterestOne('<s:property value="#list.fuid" />')" style="color: blue;">	<s:property value="#list.fullname" /></a> 
						</td>
					</tr> 
				</s:iterator>
			</table>
			<div id="page" class="rpage"></div>
		</div>
	</body>

</html>