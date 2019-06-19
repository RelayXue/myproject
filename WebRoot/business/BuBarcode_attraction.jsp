 
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
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buAttractions!showCode?skey=${skey}',
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
		 var url="buAttractions!showCode?skey="+skey;
		 window.location.href=url;
	 }
	 
 function Generate(){
	 	 var skey=$("#skey").val();
	 	 var val=$("input[name=ch]:checked");
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
	 	 	 $("#form1").attr("action","buAttractions!generate?skey="+skey);
	 	 	 $("#form1").submit();
	 	 }
	 	}
	 	//审核
	 	function examine(fuid){
	 		$("#ifr").attr("src","<%=basePath %>buAttractions!examineShow?id="+fuid+"&time="+new Date());
			 	 $('#dd').dialog({
				    modal:true,
					title:"审核列表"
				  });
	 	}
	function closeDialog(){
		$('#dd').dialog("close");
		location.reload();
	}
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">景点码管理</a>
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
			<a href="javascript:Generate()" class="tip2">生成二维码</a>
		</div>
		<div class="rtable">
			<form action="" name="form1" method="post" id="form1">
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
						生成状态
					</th>
					<th width="14%">
						审核状态
					</th>
					 
					<th width="8%">
						修改时间
					</th>
					<th width="16%">
						操作
					</th>
				</tr>
				<s:iterator value="buAttractions_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox"  sname="<s:property value="#list.fullname" />" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
							 
								<td><s:property value="#list.fullname" />&nbsp; </td>
								<td>
									<s:if test="#list.prstatus==1">
										已生成
									</s:if>
									<s:else>
										未生成
									</s:else>
								</td>
								 
								<td>
									<s:if test="#list.status==1">
										审核通过 
									</s:if>
									<s:elseif test="#list.status==2">
										审核不通过
									</s:elseif>
									<s:else>
										未审核
									</s:else>
								 </td>
							 
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
						<s:if test="com.hisOther.get('business_jd_sh')==true">
							<s:if test="#list.status!=1">
								 <a class="blue-12"  href="javascript:examine('<s:property value="#list.fuid"  />')">审核</a>
							</s:if>
							<s:elseif test="#list.status==1">
								<a class="blue-12"  href="javascript:examine('<s:property value="#list.fuid"  />')">重新审核</a>
							</s:elseif>
						</s:if>
						<s:if test="#list.status==1&&#list.prstatus==1">
						 <a href="<%=basePath %>down.jsp?fileName=<s:property value="#list.fullname" />&filePath=<s:property value="#list.codepath" />" class="blue-12">下载二维码</a>
						</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
				 <input type="hidden" name="ids"  value="" id="ids"/>
				 <input type="hidden" name="names"  value="" id="names"/>
			</form>
		</div>
		<div id="page"></div>
	
		<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 390px;">
					<iframe id="ifr" width="100%" height="100%" src="" scrolling="yes" frameborder="0"></iframe>
				</div>
			</div>
	</body>

</html>