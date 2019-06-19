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
			url:'buStay!showExamine?skey=${skey}',
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
	 var url="buStay!showExamine?skey="+skey;
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

	 
	//生成二维码
	function Generate(fuid,name){
		 var skey=$("#mc").val();
		 var val=$("input[name=ch]:checked");
		 if(fuid!=null&&fuid.length>0){
			 $("#ids").attr("value",fuid);
		 	 $("#names").attr("value",name);
		 	 $("#form1").attr("action","buStay!generate?skey="+skey);
		 	 $("#form1").submit();
		 }else{
			 if(val.length==0){
				 	alert("请先选择!");
				 	return;
				 }else{
				 	var ids="";
				 	var names="";
				 	 val.each(function (){
				 	 	ids+=$(this).val()+",";
				 	 	names+=$(this).attr("sname")+",";
				 	 });
				 	 ids=ids.substring(0,ids.length-1);
				 	 names=names.substring(0,names.length-1);
				 	 $("#ids").attr("value",ids);
				 	 $("#names").attr("value",names);
				 	 $("#form1").attr("action","buStay!generate?skey="+skey);
				 	 $("#form1").submit();
				 }
			 
		 }
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
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		<!-- <a href="javascript:serach()" class="btn" title="搜索">搜索</a> -->
	</div>
	
	<div class="rhandle">
		<a  onclick="if(!confirm('确定生成！')){return false;}" class="btn btn-success" href="javascript:Generate()"><i class="icon-plus-sign icon-white"></i>批量生成</a>
				<a onclick="if(!confirm('确定生成所有！生成所有需要些时间，请耐心等待！')){return false;}"  class="btn btn-success" href="<%=basePath %>buStay!generateAll"><i class="icon-plus-sign icon-white"></i>生成所有</a>
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
					<th width="10%">
						名称
					</th>
					<!--<th width="10%">
						行业类型
					</th>-->
					<th width="10%">
						店铺电话
					</th>
					<th width="18%">
						地址
					</th>
					<th width="4%">
						状态
					</th>
					<th width="8%">
						操作
					</th>
				</tr>
				<s:iterator value="buStay_list" var="list" status="stuts">
					<tr>
						 <td>
							<input name="ch" sname='<s:property value="#list.fullname" />' type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td> 
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
								<td><s:property value="#list.fullname" />&nbsp; </td>
								<!--<td><s:property value="#list.industry" />&nbsp; </td>-->
								
								<!--<td><s:property value="#list.supervisor" />&nbsp; </td>
								<td><s:property value="#list.supervisorphone" />&nbsp; </td>
								-->
								<td><s:property value="#list.phone" />&nbsp; </td>
								<td style="text-align:left;padding-left:6px"><s:property value="#list.address" />&nbsp; </td>
						<td>
							<s:if test="#list.prstatus==1">
								<span style="color: blue;">已生成 </span> 
							</s:if>
							<s:else>
								<span style="color: red;">未生成 </span> 
							</s:else>
						</td>
						
						<td >
						<s:if test="#list.prstatus==1">
							<a class="btn btn-success" href="<%=basePath %>business/down.jsp?fileName=<s:property value="#list.fullname" />&filePath=<s:property value="#list.codepath" />" ><i class="icon-share icon-white"></i>下载二维码</a>
							</s:if>
							<s:else>
							
								<a onclick="if(!confirm('确定生成！')){return false;}" class="btn btn-info" href="javascript:Generate('${fuid}','<s:property value="#list.fullname" />')"><i class="icon-share icon-white"></i>生成二维码</a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="form1" action="buStay!delete" method="post">
				<input type="hidden" name="ids" value="" id="ids" />
				<input type="hidden" name="names" value="" id="names" />
			</form>
		</div>
		
	
</body>
</html>