<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String superAdmin=(String)request.getSession().getAttribute("superAdmin");
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>单位管理</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
	<script>
		$(function(){
			$('#OrganizeTree').treegrid({
				title:'部门',
				iconCls:'icon-save',
				height:450,
				nowrap: false,
				rownumbers: true,
				animate:true,
				collapsible:true,
				url:'<%=basePath %>Organize!tree',
				idField:'fuid',
				treeField:'fullname',
				frozenColumns:[[
	                {title:'部门名称',field:'fullname',width:380,
		                formatter:function(value){
		                	return '<span style="color:red">'+value+'</span>';
		                }
	                }
				]],
				columns:[[
					{field:'outerphone',title:'电话',width:150}, 
					{field:'manager',title:'负责人',width:150}, 
					{field:'description',title:'备注',width:250} 
					 
				]],
				//右键菜单
				onContextMenu: function(e,row){
					e.preventDefault();
					$(this).treegrid('unselectAll');
					$(this).treegrid('select', row.fuid);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			});
		});
		//重载
		function reload(){
			$('#OrganizeTree').treegrid('reload');
			/*var node = $('#OrganizeTree').treegrid('getSelected');
			if (node){
				$('#OrganizeTree').treegrid('reload', node.fuid);
			} else {
				$('#OrganizeTree').treegrid('reload');
			}*/
		}
		function getChildren(){
			var node = $('#OrganizeTree').treegrid('getSelected');
			if (node){
				var nodes = $('#OrganizeTree').treegrid('getChildren', node.fuid);
			} else {
				var nodes = $('#OrganizeTree').treegrid('getChildren');
			}
			var s = '';
			for(var i=0; i<nodes.length; i++){
				s += nodes[i].fuid + ',';
			}
			alert(s);
		}
		//关闭所有节点
		function collapseAll(){
			var node = $('#OrganizeTree').treegrid('getSelected');
			if (node){
				$('#OrganizeTree').treegrid('collapseAll', node.fuid);
			} else {
				$('#OrganizeTree').treegrid('collapseAll');
			}
		}
		//展开所有节点
		function expandAll(){
			var node = $('#OrganizeTree').treegrid('getSelected');
			if (node){
				$('#OrganizeTree').treegrid('expandAll', node.fuid);
			} else {
				$('#OrganizeTree').treegrid('expandAll');
			}
		}
		function close(){
			$('#d22').dialog('close');
		}
		//新增
		function add(){
			var node = $('#OrganizeTree').treegrid('getSelected');
			var parentid=node.fuid;
			$("#ifr").attr("src","<%=basePath %>system/OrganizeEditor.jsp?parentid="+parentid);
			$('#d22').dialog({
				title:"单位管理"
			});
		}
		//修改
		function update(){
			var node = $('#OrganizeTree').treegrid('getSelected');
			var parentid=node.fuid;
			$("#ifr").attr("src","<%=basePath %>Organize!update_show?id="+parentid+"&time="+new Date());
			$('#d22').dialog({
				title:"单位管理"
			});
			
		}
		
		//删除节点
		function remove(){
			var choice=confirm("您确认要删除吗？", function() { }, null);
			if(choice){
				var node = $('#OrganizeTree').treegrid('getSelected');
				if (node){
					var child = $('#OrganizeTree').treegrid('getChildren', node.fuid);
				} else {
					var child = $('#OrganizeTree').treegrid('getChildren');
				}
				if(child!=null&&child.length>0){
					alert("请先删除子目录！");
					return;
				}
				var id=node.fuid;
				$.ajax({
					url:"Organize!delete?id="+id+"&time="+new Date(),
					async: false,
					  success: function(data){
					  	if(data==1){
					  		 reload();
					  	}else{
					  		alert("服务器异常，请稍后再试！");
					  	}
		              }
				});
			}
			
			
			/*if (node){
				var fuid=node.fuid;
				$('#OrganizeTree').treegrid('remove', node.code);
			}*/
		}
	</script>
</head>
<body>
	<div class="content-center"  style="overflow: hidden;">
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
	</div>
	
	
	<table id="OrganizeTree"></table>
	
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="add()">新增</div>
		<div onclick="update()">修改</div>
		<%if(superAdmin!=null&&superAdmin.equals("spadmin")){ %>
		<div onclick="remove()">删除</div>
		<%} %>
	</div>
	<div style="display: none;">
		<div id="d22" style="width: 800px;height: 500px;top: 50px;overflow:hidden;">
	  		 <iframe id="ifr"  style="width: 100%;height: 100%;overflow: hidden;"  scrolling="auto" frameborder="0"   src="<%=basePath %>system/OrganizeEditor.jsp"></iframe>
	  </div>
  	</div>
  	</div>
</body>
</html>
