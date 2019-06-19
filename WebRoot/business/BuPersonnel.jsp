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
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
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
					url:'buPersonnel?skey=${skey}',
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
		 var url="buPersonnel?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>business/BuPersonnelEditor.jsp";
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
	
	/**查看地图*/
		function showMap(fx,fy){
			
			$("#fx").attr("value",fx);
			$("#fy").attr("value",fy);
			$("#ifmap").attr("src","<%=basePath%>business/MapEdit.jsp?type=Personnel");
			$('#dd').dialog();
}
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">人员管理</a>
		</div>
		<div class="rsearch">
			<span class="info">搜索:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
			<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		</div>
		<div class="rhandle">
			<a href="javascript:add()" class="btn btn-success"  ><i class="icon-plus-sign icon-white"></i>添加</a>
			<!--<a href="#" class="tip2">编辑</a>-->
			<a href="javascript:delMore()"  class="btn btn-danger" ><i class="icon-trash icon-white"></i>删除</a>
		</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="2%">
						<input name="chAll" type="checkbox" value="" />
					</th>
					<th width="3%">
						编号
					</th>
					<th width="8%">
						姓名
					</th>
					<th width="8%">
						手机
					</th>
					<th width="3%">
						性别
					</th>
					<th width="8%">
						修改时间
					</th>
					<th width="15%">
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
									<s:if test="#list.sex==0">
										女
									</s:if>
									<s:else>
										男
									</s:else>
								 </td>
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<a class="btn btn-success" href="javascript:showMap('${fx}','${fy}')" ><i class="icon-share icon-white"></i>查看实时位置</a>
							<s:if test="com.hisUpdate==true">
							<a  class="btn btn-info" href="<%=basePath%>buPersonnel!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-edit icon-white"></i>编辑</a>
							</s:if>
							<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('确定删除！')){return false;}" class="btn btn-danger" href="<%=basePath%>buPersonnel!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-trash icon-white"></i>删除</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		<div style="display: none;">
			<form id="mform" action="buPersonnel!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		<div style="display: none;">
		 	 <div id="dd" title="查看地图" data-options="iconCls:'icon-search'" style="width: 460px;height:330px;top: 37px;overflow:hidden;">
				<iframe  width="615px" id="ifmap" height="450px" frameborder="no" border="0"  src=""></iframe>
   			 </div>
  		</div>
  		<!--<input id="btnPoint" type="hidden" class="input-page-btn"   value="开启描绘"   />-->
  		<input type="hidden"  id="fx" class="input-text"  value="" />
		<input type="hidden"  id="fy" class="input-text"   value="" />
	</body>

</html>