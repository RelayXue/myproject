<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
	<script>
		$(function(){
		
			$('#OrganizeTree').treegrid({
				title:'',
				iconCls:'icon-save',
				width:200,
				height:1500,
				nowrap: false,
				animate:true,
				collapsible:true,
				url:'<%=basePath %>Operating!tree',
				idField:'fuid',
				treeField:'menuName',
				frozenColumns:[[
	                {title:'菜单名称',field:'menuName',width:230,
		                formatter:function(value){
		                	return '<span style="color:red">'+value+'</span>';
		                }
	                }
				]],
				onClickRow:function(row){
					$("#userFr").attr("src","Operating!Operatingshow?MenuId="+row.fuid+"&date="+new Date());
				} 
			});
		});
		
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'菜单'" style="width:230px;padding:10px;">
		<table id="OrganizeTree"></table>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'部门'" style="width:100px;padding:10px;">
	</div>
	<div data-options="region:'center',title:'用户管理'">
			 <iframe  scrolling="no"    frameborder="0" id="userFr" width="100%" height="91%"  src=""></iframe>
	</div>
</body>
</html>
</html>
