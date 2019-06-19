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
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/icon.css"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buScanning?skey=${skey}',
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
		 var url="buScanning?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>business/BuScanningEditor.jsp";
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
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">二维码扫描</a>
		</div>
		<div class="rsearch">
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		</div>
		<div class="rhandle">
			
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
					<th width="8%">
						创建时间
					</th>
					<th width="8%">
						扫描时间
					</th>
					<th width="8%">
						备注
					</th>
				</tr>
				<s:iterator value="buScanning_list" var="list" status="stuts">
					<tr>
						<td><input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" /></td>
						<td><span><s:property value="#stuts.index+1" /></span></td>
						<td><s:property value="#list.realname" />&nbsp; </td>
						<td><s:property value="#list.mobile" />&nbsp; </td>
						<td><s:date name="#list.createdate" format="yyyy-MM-dd HH:mm:ss" /></td>
						<td><s:date name="#list.scanningtime" format="yyyy-MM-dd HH:mm:ss" /></td>
					    <td><s:property value="#list.description" /></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		<div style="display: none;">
			<form id="mform" action="buScanning!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
	</body>

</html>