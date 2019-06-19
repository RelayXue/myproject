<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String type = request.getParameter("type");
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
			url:'buStay?skey=${skey}&typeName=${typeName}',
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
//---搜索----	 
 function serach(){
	 var skey=$("#mc").val();
	 var typeName=$("#typeName").val();
	 var url="buStay?skey="+skey+"&typeName="+typeName;
	 window.location.href=url;
 }
//---添加---
 function add(){
	 var url="<%=basePath%>business/BuStayEditor.jsp?type="+"002017";
	 window.location.href=url;
 }
 //---批量选择---
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

//查看房间
	function showRoom(fuid){
	//alert(fuid);
		$("#ifmap").attr("src","<%=basePath%>buStayRoom?stayid="+fuid);
		$('#dd').dialog();
	}

</script>
<style>
</style>
</head>
<body>
	<div class="rightnav">
			<a href="#" class="active">住宿管理</a>
	</div>
	
	<div class="rsearch">
		
		<!-- <span class="info">搜索:</span> -->
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<select name="typeName" id="typeName" style="width:126px;" class="inp" >
			<option value="-1">请选择搜索类型</option>
			<option value="002017001"  <s:if test="%{typeName=='002017001'}">selected</s:if>>酒店</option>
			<option value="002017002" <s:if test="%{typeName=='002017002'}">selected</s:if>>民宿</option>
			<option value="002017003" <s:if test="%{typeName=='002017003'}">selected</s:if>>一般住宿</option>
			<!--<option value="002017004" <s:if test="%{typeName=='002017004'}">selected</s:if>>其他</option>-->
		</select>
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		
		<!-- <a href="javascript:serach()" class="btn" title="搜索">搜索</a> -->
	</div>
	
	<div class="rhandle">
		<a class="btn btn-success" href="javascript:add()"><i class="icon-plus-sign icon-white"></i>添加</a>
		<!--<a href="javascript:add()" class="tip1">添加</a> -->
		<!--<a href="#" class="tip2">编辑</a>-->
		<!-- <a href="javascript:delMore()" class="tip3">删除</a> -->
	</div>
	
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<!--<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>-->
					<th width="3%">
						编号
					</th>
					<th width="10%">
						名称
					</th>
					<%--<th width="10%">
						行业类型
					</th>--%>
					<!--<th width="10%">
						负责人
					</th>
					<th width="10%">
						负责人电话
					</th>
					-->
					<th width="7%">
						店铺电话
					</th>
					<th width="17%">
						地址
					</th>
					<th width="8%">
						修改时间
					</th>
					<th width="12%">
						操作
					</th>
				</tr>
				<s:iterator value="buStay_list" var="list" status="stuts">
					<tr>
						<!--<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>-->
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
								<td><s:property value="#list.fullname" />&nbsp; </td>
								<%--<td><s:property value="#list.industry" />&nbsp; </td>--%>
								
								<!--<td><s:property value="#list.supervisor" />&nbsp; </td>
								<td><s:property value="#list.supervisorphone" />&nbsp; </td>
								-->
								<td><s:property value="#list.phone" />&nbsp; </td>
								<td style="text-align:left;padding-left:6px"><s:property value="#list.address" />&nbsp; </td>
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						
						<td >
							<a class="btn btn-success" href="javascript:showRoom('${fuid}')" ><i class="icon-share icon-white"></i>查看</a>	
							<s:if test="com.hisUpdate==true">
							<!-- <a href="<%=basePath%>buStay!update_show?id=<s:property value="#list.fuid" />&skey=${skey}&indexPage=${indexPage}">编辑</a>&nbsp;-->
							<a class="btn btn-info" href="<%=basePath%>buStay!update_show?id=<s:property value="#list.fuid" />&skey=${skey}&indexPage=${indexPage}"><i class="icon-edit icon-white"></i> 编辑 </a>		
							</s:if>
							
							<s:if test="com.hisDelete==true">
							<!-- <a onclick="if(!confirm('确定删除！')){return false;}" href="<%=basePath%>buStay!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">删除</a> -->
							<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href="<%=basePath%>buStay!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-trash icon-white"></i>删除</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="buStay!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		
		<div style="display: none;">
		 	 <div id="dd" title="查看房间" data-options="iconCls:'icon-search'" style="width: 850px;height:550px;top: 37px;overflow:scroll;">
				<iframe  width="100%" id="ifmap" height="600px" frameborder="no" border="0"  src=""  ></iframe>
   			 </div>
  		</div>
	
</body>
</html>