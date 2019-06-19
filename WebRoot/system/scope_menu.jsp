<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>部门管理</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
	<script>
		$(function(){
			$('#RoleMenu').treegrid({
				width:'100%',
				height:'2000',
				nowrap: false,
				rownumbers: true,
				animate:true,
				collapsible:true,
				url:'<%=basePath %>Scope!showScopeMenu?OrganizeId=${OrganizeId}',
				idField:'fuid',
				treeField:'realname',
				frozenColumns:[[
	                {title:'角色名称',field:'realname',width:200,
		                formatter:function(value){
		                	return '<span style="color:red">'+(value==null?'---':value)+'</span>';
		                }
	                }
				]],
				columns:[[
					{field:'menuName',title:'菜单名称',width:150}, 
					{field:'sortcode',title:'数据集权限',width:250,
						 formatter:function(value){
						 		if(value==null){
						 			return '<a style="TEXT-DECORATION:none" href="javascript:showOr()">分配</a>';
						 		}
			                }
					}
					 
				]] 
			});
			
		}); 
		function showOr(){
			var node = $('#RoleMenu').treegrid('getSelected');
			if (node){
				var menuid=node.modifyuserrealname;
				var par = $('#RoleMenu').treegrid('getParent',node.fuid);
				var roleid=par.fuid;
				 $("#ifr").attr("src","<%=basePath %>system/scope_organize.jsp?RoleId="+roleid+"&MenuId="+menuid);
			 	 $('#dd').dialog({
				           	modal:true,
							title:"部门列表"
				 });
			}
	 	
	 }
	 function close(){
	 	$('#dd').dialog("close");
	 }
	</script>
</head>
<body>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
	</div>
	
	<div id="par"  >
	<table id="RoleMenu"  ></table>
	</div>
	<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 550px;">
					<iframe id="ifr" width="100%" height="100%" src="" frameborder="0"></iframe>
				</div>
			</div>
</body>
</html>
