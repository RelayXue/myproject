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
 jQuery(document).ready(function() {
	 //--------------分页-------------
	$("#page").AutoPage({
			totalPage : '${totalPage}', // 总页数
			currentPage: '${indexPage}',//当前页
			pageSize:'${pageSize}',    //每页显示条数
			url:'<%=basePath%>accommodation!selectByPage',
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
	 var url="<%=basePath%>accommodation!selectByPage?name="+$("#mc").val();
	 window.location.href=url;
 }
 //---添加---
 function add(){
	 var url="<%=basePath %>/module/weixin/BuNewsEditor.jsp?type=${type}";
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
			<a href="#" class="active">最美乌镇人家</a>
		    <!-- <a href="#" class="active">基础数据管理</a> -->	
	</div>
	
	<div class="rsearch">
		<!--<span class="info">搜索:</span>-->
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
	</div>
	
	<div class="rhandle">
		<a class="btn btn-success" href="<%=basePath%>module/weixin/AddAccommodation.jsp"><i class="icon-plus-sign icon-white"></i>添加</a>
	</div>
	
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="10%">
						旅店名称
					</th>
					<th width="20%">
						旅店简介
					</th>
					<th width="15%">
						联系电话
					</th>
					<th width="20%">
						地址
					</th>
					<th width="10%">
						旅店所有人
					</th>
					<th width="10%">
						创建日期
					</th>
					<th width="15%">
						操作
					</th>
				</tr>
				
				<s:iterator value="buAccommodation_list" var="list" status="stuts">
					<td>
						<s:property value="#list.hname"/>
					</td>
					<td>
						<div style="width:380px;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow: hidden; "><s:property value="#list.description"/></div>
					</td>
					
					<td>
						<s:property value="#list.mobile"/>
					</td>
					<td width="10%">
						<s:property value="#list.address"/>
					</td>
					<td width="10%">
						<s:property value="#list.householder"/>
					</td>
					<td>
						<s:date name="#list.createdate" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
						
						<td>
							<a class="btn btn-info" href='<%=basePath%>accommodation!selectById?id=<s:property value="#list.fuid"/>'><i class="icon-edit icon-white"></i> 编辑 </a>		
							<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href='<%=basePath%>accommodation!deleteHostl?id=<s:property value="#list.fuid"/>'><i class="icon-trash icon-white"></i>删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
</body>
</html>