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
			url:'buComment?skey=${skey}&typeName=${typeName}',
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
	 var url="buComment?skey="+skey+"&type=<%=type%>";
	 window.location.href=url;
 }
 


</script>
<style>
</style>
</head>
<body>
	<div class="rightnav">
		<%if(type.equals("002017")){%>
			<a  href="#" class="active">住宿评价</a>
		<%}else if(type.equals("002018")){%>
			<a  href="#" class="active">餐饮评价</a>
		<%}else if(type.equals("002015")){ %>
		    <a  href="#" class="active">购物评价</a>
		<%}else if(type.equals("002016")){ %>
		 	<a  href="#" class="active">娱乐评价</a>
		<%}else{%>
			<a  href="#" class="active">其他评价</a>
		<%} %>
			
	</div>
	
	<div class="rsearch">
		
		<!-- <span class="info">搜索:</span> -->
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		
		<!-- <a href="javascript:serach()" class="btn" title="搜索">搜索</a> -->
	</div>
	
	<div class="rhandle">
	</div>
	
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<!--<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>-->
					<th width="20%"  style="text-align:left;padding-left:10px">
						 评论内容
					</th>
					<th width="3%">
						评分
					</th>
					<th width="6%" >
						审核状态
					</th>
					<th width="7%">
						评论时间
					</th>
					<th width="12%">
						操作
					</th>
				</tr>
				<s:iterator value="buComment_list" var="list" status="stuts">
					<tr>
						<!--<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>-->
								<td  style="text-align:left;padding-left:10px"><s:property value="#list.description" />&nbsp; </td>
								<td> <s:property value="#list.score" /></td>
								<td>
									<s:if test="#list.examine==1">
										审核通过
									</s:if>
									<s:elseif test="#list.examine==0">
										<span style="color: red;"> 审核不通过</span>
									</s:elseif>
									<s:else>
										未审核
									</s:else>  
								&nbsp; </td>
								
								<td><s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />&nbsp; </td>
						<td >
							<s:if test="#list.examine==1">
								已审核
							</s:if>
							<s:else>
							<a class="btn btn-info" onclick="if(!confirm('您确定审核通过吗？')){return false;}"  href="<%=basePath%>buComment!examine?id=<s:property value="#list.fuid" />&skey=${skey}&indexPage=${indexPage}&examine=1&type=<%=type %>"><i class="icon-edit icon-white"></i> 审核通过 </a>		
							<a class="btn btn-info" onclick="if(!confirm('您确定审核不通过吗？')){return false;}"  href="<%=basePath%>buComment!examine?id=<s:property value="#list.fuid" />&skey=${skey}&indexPage=${indexPage}&examine=0&type=<%=type %>"><i class="icon-edit icon-white"></i> 审核不通过 </a>		
							</s:else>
							
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="buComment!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		
	
</body>
</html>