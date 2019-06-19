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
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
	<script>
 jQuery(document).ready(function() {
	 //--------------分页-------------
	$("#page").AutoPage({
			totalPage : '${totalPage}', // 总页数
			currentPage: '${indexPage}',//当前页
			pageSize:'${pageSize}',    //每页显示条数
			url:'touchNews?type=<%=type%>&skey=${skey}',
			showNum : 6 , // 显示数字
			show:1
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
 //---搜索---
 function serach(){
	 var skey=$("#mc").val();
	
	 var url="touchNews?type="+'${type}'+"&skey="+skey;
	 window.location.href=url;
 }
 //---添加---
 function add(){
	 var url="<%=basePath%>/module/touch/touchEditor.jsp?type=${type}";
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

</script>
<style>
</style>
 
</head>
<body>
	<div class="rightnav">
			<a href="#" class="active"><s:property value="#application.DatadictionaryMap[type]"  /></a>
		    <!-- <a href="#" class="active">基础数据管理</a> -->	
	</div>
	
	<div class="rsearch">
		<!--<span class="info">搜索:</span>-->
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
	</div>
	
	<div class="rhandle">
		<a class="btn btn-success" href="javascript:add()"><i class="icon-plus-sign icon-white"></i>添加</a>
		
		<a class="btn btn-danger" href="javascript:delMore()"><i class="icon-trash icon-white"></i>批量删除</a>
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
					<th width="14%">
						标题
					</th>
					<th width="3%">
						浏览次数
					</th>
					<th width="8%">
						修改日期
					</th>
					<th width="10%">
						操作
					</th>
				</tr>
				
				<s:iterator value="buNews_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
						
						<td>
							<s:property value="#list.fullname" />&nbsp;
						 </td>
						 
						<td>
							<s:property value="#list.readnum" />&nbsp; 
						</td>
						
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						
						<td>
							<s:if test="com.hisUpdate==true">
							<a class="btn btn-info" href="<%=basePath%>touchNews!update_shows?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}"><i class="icon-edit icon-white"></i> 编辑 </a>		
							</s:if>
							
							<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href="<%=basePath%>touchNews!deletes?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}"><i class="icon-trash icon-white"></i>删除</a>
							
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="touchNews!deletes?type=${type}" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
</body>
</html>