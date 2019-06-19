
<%@ page import="java.util.*,com.gh.entity.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String RoleId = request.getParameter("RoleId");
	Map<String, List<BaseMenu>> baseMenu_map = (Map<String, List<BaseMenu>>) request.getAttribute("baseMenu_map");
	List<BaseMenu> baseMenu_list = (List<BaseMenu>) request.getAttribute("baseMenu_list");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link type="text/css" rel="stylesheet" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/manage.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/AutoSelect.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 	$('#tt2').tree({
	        url:'<%=basePath%>Menu!treeRole?RoleId=<%=RoleId%>&OrganizeId=${OrganizeId}',
	        checkbox:true,
	        animate :true,
	        onLoadSuccess:function(node, data){
	        	$('#tt2').tree('collapseAll');
	        }
   		 });
	 });
		function add(){
			var nodes = $('#tt2').tree('getChecked');
			var s = '';
			$("#hid").empty();
			for(var i=0; i<nodes.length; i++){
				var ch=$('#tt2').tree('getChildren',nodes[i].target);
				if(ch.length==0){
					s = nodes[i].id;
					var att=nodes[i].attributes;
					if(att!=null){
						var parid=$('#tt2').tree('getParent',nodes[i].target).id;
						var co=parid+";"+s;
						$("#hid").append("<input type='hidden' name='id' value='"+co+"' />");
					}
				}
			}
 	 		$("#myform").ajaxSubmit({
                    type: 'post',
                    url: "Role!Assign?RoleId=<%=RoleId%>",

					success : function(data) {
						alert("分配成功！");
						window.parent.close();
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						alert("error");
					}
			});
	}
</script>
		<style>
</style>
	</head>
	<body>
		<div id="content">
			<div class="content-center" style="height: 1110px">
				<ul id="tt2"></ul>
				 <%--<ul id="tt1" class="easyui-tree" data-options="animate:true,dnd:true,checkbox:true">
					<%
						if (baseMenu_list != null) {
							for (int a = 0; a < baseMenu_list.size(); a++) {
								if (baseMenu_list.get(a).getHasChild()) {
					%>
					<li>
						<span><%=baseMenu_list.get(a).getMenuName()%> </span>
						<ul>
							<%
								List<BaseMenu> list = baseMenu_map.get(baseMenu_list.get(a).getFuid());
											if (list != null) {
												for (int b = 0; b < list.size(); b++) {
													if (list.get(b).getHasChild()) {
							%>

							<li data-options="state:'closed'">
								<span><%=list.get(b).getMenuName()%> </span>
								<ul>
									<%
										List<BaseMenu> list_c = baseMenu_map.get(list.get(b).getFuid());
																for (int c = 0; c < list_c.size(); c++) {
									%>
									<li>
										<span><%=list_c.get(c).getMenuName()%>
											<%List<BaseOperating>oplist= list_c.get(c).getOperating_list();
													for(int g=0;g<oplist.size();g++){
												 %>
												<span><input value="<%=oplist.get(g).getFuid() %>" type="checkbox" /><%=oplist.get(g).getFullname() %> </span>
												<%} %>
										</span>
									</li>
									<%
										}
									%>
								</ul>
							</li>

							<%
								} else {
							%>
							<li>
								<span><%=list.get(b).getMenuName()%>
									<%List<BaseOperating>oplist= list.get(b).getOperating_list();
										for(int g=0;g<oplist.size();g++){
									 %>
									<span><input value="<%=oplist.get(g).getFuid() %>" type="checkbox" /><%=oplist.get(g).getFullname() %> </span>
									<%} %>
								</span>
							</li>
							<%
								}
												}
											}
							%>
						</ul>
					</li>
					<%
						} else {
					%>
					<li>
						<span>
						 <div style="width: 200px">
						 <span style="float: left;"><%=baseMenu_list.get(a).getMenuName()%>
						  </span> 
						 </div>	
						
							<%List<BaseOperating>oplist= baseMenu_list.get(a).getOperating_list();
								for(int g=0;g<oplist.size();g++){
							 %>
							<span><input  value="<%=oplist.get(g).getFuid() %>" type="checkbox" /><%=oplist.get(g).getFullname() %> </span>
							<%} %>
						</span> 
					</li>
					<%
						}
							}
						}
					%>


				</ul>
				--%><div style="text-align: center;">
					<input type="button" onclick="add()" id="resetButton" value="保存" class="input-page-btn" />
				</div>
			</div>
		</div>
		<form id="myform" action="" method="post">
			<div id='hid'>
				<input type='hidden' />
			</div>
		</form>
	</body>

</html>