﻿<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
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
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>

		<script>
	 jQuery(document).ready(function($) {
			//--------------全选-------------
				$("input[name='chAll']").click(function (){
					if($(this).attr("checked")){
						$("input[name='ch']").attr("checked","true");
					}else{
						$("input[name='ch']").removeAttr("checked");
					}
				});
				
	 });
	 function serach(){
		 var skey=$("#mc").val();
		 var url="buEvent?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
		 var url="<%=basePath%>business/BuEventEditor.jsp";
		 window.location.href = url;
	}
	function delMore() {
		var ch = $("input[name='ch']:checked");
		if (ch.length == 0) {
			alert("请先勾选数据！");
			return;
		}
		if(!window.confirm('确定要删除所选数据？')){
            return;
         } 
		var id = "";
		ch.each(function() {
			id += $(this).val() + ",";
		});
		id = id.length > 0 ? id.substring(0, id.length - 1) : id;
		$("#ids").attr("value", id);
		$("#mform").submit();
	}
</script>
		 
	</head>
	<body>
		<div class="rightnav">
			<a href="#" class="active">设备设施统计</a>
		</div>
		<!--<div class="rsearch">
			<span class="info">搜索:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey }" />
			<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
		</div>
		--><div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>
					<th width="11%">
						名称
					</th>
					<th width="14%">
						总数
					</th>
				</tr>
				<s:iterator value="buInterest_list" var="list" status="stuts">
					<s:if test="#list.ftype!=null">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<s:property value="#application.DatadictionaryMap[ftype]"  /> 
						</td>
						<td>
							<s:property value="#list.count" />
						</td>
					</tr>
					</s:if>
					<s:if test="#list.address==9">
						<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<s:property value="#list.fullname"  /> 
						</td>
						<td>
							<s:property value="#list.count" />
						</td>
					</tr>
					</s:if>
				</s:iterator>
			</table>
		</div>
		<div class="rpage"></div>
		<div style="display: none;">
			<form id="mform" action="buEvent!delete" method="post">
				<input type="hidden" name="id" value="" id="ids" />
			</form>
		</div>
	</body>

</html>