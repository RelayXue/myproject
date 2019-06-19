﻿ 
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
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buScenic?skey=${skey}',
					showNum : 6  // 显示数字
					,show:1
				});
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
		 var url="buScenic?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>business/BuScenicEditor.jsp";
	 window.location.href=url;
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
	//关联景点
	function relation(scenicId){
		$("#ifr").attr("src","<%=basePath %>buAttractions!Scenicshow?id="+scenicId+"&time="+new Date());
	 	 $('#dd').dialog({
		    modal:true,
			title:"景点列表"
		  });
	
	}
	function closeDialog(){
		$('#dd').dialog("close");
	}
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">景点管理</a>
		</div>
		<div class="rsearch">
			<span class="info">搜索:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
			<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
		</div>
		<div class="rhandle">
			<a href="javascript:add()" class="tip1">添加</a>
			<!--<a href="#" class="tip2">编辑</a>-->
			<a href="javascript:delMore()" class="tip3">删除</a>
		</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>
					<th width="3%">
						&nbsp;
					</th>
					<th width="11%">
						 名称
					</th>
					<th width="14%">
						地址
					</th>
					<th width="14%">
						门票
					</th>
					<th width="8%">
						修改时间
					</th>
					<th width="16%">
						操作
					</th>
				</tr>
				<s:iterator value="buScenic_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
								<td><s:property value="#list.fullname" />&nbsp; </td>
								<td><s:property value="#list.address" />&nbsp; </td>
								<td><s:property value="#list.tickets" />&nbsp; </td>
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<s:if test="com.hisOther.get('business_addAtt')==true">
							 <a href="javascript:relation('<s:property value="#list.fuid"  />')">关联景点</a>&nbsp;&nbsp;
							</s:if>
							<s:if test="com.hisAdd==true">
							<a href="javascript:add()">添加</a>&nbsp;&nbsp;
							</s:if>
							<s:if test="com.hisUpdate==true">
							<a href="<%=basePath%>buScenic!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">编辑</a>&nbsp;&nbsp;
							</s:if>
							<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('确定删除！')){return false;}" href="<%=basePath%>buScenic!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">删除</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		<div style="display: none;">
			<form id="mform" action="buScenic!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 390px;">
					<iframe id="ifr" width="100%" height="100%" src="" scrolling="yes" frameborder="0"></iframe>
				</div>
			</div>
	</body>

</html>