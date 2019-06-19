<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String parentid=request.getParameter("parentid");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>后台维护信息</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/manage-style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/eayui/easyui.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>.
 		<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
	<script>
	 jQuery(document).ready(function($) {
	 	$('#tt2').tree({
	        url:'<%=basePath%>pier!treeBerth?time='+new Date(),
	        checkbox:true,
	        animate :true,
	        onLoadSuccess:function(node, data){
	        $('#tt2').tree('collapseAll');
	        }
   		 });
	 });
	 
	 function change(){
	 	var nodes = $('#tt2').tree('getChecked');
	 	var id="";
	 	var name="";
	 	for(var i=0; i<nodes.length; i++){
				var ch=$('#tt2').tree('getChildren',nodes[i].target);
				if(ch.length==0){
					id+= nodes[i].id+";";
					name+=nodes[i].text+";";
				} 
		}
		id=id.length>0?id.substring(0,id.length-1):id;
		$("#texta").html(name);
		$("#berthid").attr("value",id);
		$('#dd').dialog('close');
	 }
	 function changeWH(){
	 	var goods=$("input[name=goods]:checked");
	 	var id="";
	 	var name="";
	 	goods.each(function (i){
	 		name+=$(this).attr("val")+";";
	 		id+=$(this).val()+";";
	 	})
	 	id=id.length>0?id.substring(0,id.length-1):id;
	 	$("#gg").html(name);
	 	$("#goodsid").val(id);
		$('#wh').dialog('close');
	 }
	 function showWh(){
		 $('#wh').dialog({
		           	modal:true,
					title:"危货列表"
		    });
	 }
	 function showBer(){
	 	 $('#dd').dialog({
		           	modal:true,
					title:"泊位列表"
		    });
	 }
function update() {
    var update = $("#updateButton");
    var myform = $("#myform");
    myform.attr("action", "AddressBook!update");
    myform.submit();
}
function edit() {
    var data=$("#myform").serialize();
 	 $("#myform").ajaxSubmit({
                    type: 'post',
                    url: "Organize!edit" ,
                    success: function(data){
                    	window.parent.close();
                    	window.parent.reload();
                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        alert( "error");
                    }
                });
}
function resetB() {
    $("#myform").find(("input[type='text']")).val("");
}
function re() {
    window.location.href = "javascript:history.go(-1);";
}
	
	</script>
	</head>
	<body  >
		<form id="myform" action="" method="post" >
			<div id="content">
				<div class="">
					<div class="manage-title">
						<div class="manage-title-l">
							<img src="<%=basePath%>images/title_06.png" />
							<span class="hui-14-41-b">单位管理</span>
						</div>
					</div>
					<div class="manage-content" style="margin-top: 0px">
						<div class="edit-content" style="margin-top: 0px">
							<ul>
								<li class="titlel" style="width: 70px" >
									名称：
								</li>
								<li class="input-box" style="width: 150px">
									<input type="text" id="name" class="input-text" name="baseOrganize.fullname" value="${baseOrganize.fullname }" />
									<input type="hidden" id="fuid"   name="baseOrganize.fuid" value="${baseOrganize.fuid }" />
								</li>
								<li class="titler" style="width: 110px">
									 营业执照号：
								</li>
								<li class="input-box" >
								 <input type="text"   class="input-text" name="baseOrganize.businesslicense" value="${baseOrganize.businesslicense }" />
								</li>
							</ul>
							<ul>
								<li class="titlel" style="width: 70px">
									电话号码：
								</li>
								<li class="input-box" style="width: 150px"> 	
									<input type="text" id="sj" class="input-text" name="baseOrganize.outerphone" value="${baseOrganize.outerphone }" />
								</li>
								<li class="titler" style="width: 110px">
									 注册资本（万）：
								</li>
								<li class="input-box">
								 <input type="text"   class="input-text" name="baseOrganize.registeredcapital" value="${baseOrganize.registeredcapital }" />
								</li>
							</ul>
							<ul>
								<li class="titlel" style="width: 70px">
									法人：
								</li>
								<li class="input-box"  style="width: 150px">
									<input type="text" id="lxdh" name="baseOrganize.manager" class="input-text" value="${baseOrganize.manager }" />
								</li>
								<li class="titler" style="width: 110px">
									 法人身份证：
								</li>
								<li class="input-box">
								 <input type="text"   class="input-text" name="baseOrganize.managerid" value="${baseOrganize.managerid }" />
								</li>
							</ul>
							<ul>
								<li class="titlel" style="width: 70px">
									电子邮箱：
								</li>
								<li class="input-box"  style="width: 150px">
									<input type="text" id="lxdh" name="baseOrganize.email" class="input-text" value="${baseOrganize.email }" />
								</li>
								<li class="titler" style="width: 110px">
									 传真：
								</li>
								<li class="input-box">
								 <input type="text"   class="input-text" name="baseOrganize.fax" value="${baseOrganize.fax }" />
								</li>
							</ul>
							<ul>
								<li class="titlel" style="width: 70px">
									备注：
								</li>
								<li class="input-box" style="width: 150px">
									<input type="text" id="lxdh" name="baseOrganize.description" class="input-text" value="${baseOrganize.description }" />
								</li>
								<li class="titler" style="width: 110px">
									 排序：
								</li>
								<li class="input-box">
									 <input type="text" id="lxdh" name="baseOrganize.sortcode" class="input-text" value="${baseOrganize.sortcode }" />
									<input type="hidden"  name="baseOrganize.parentid" class="input-text" value="<%=parentid %>" />
								</li>
							</ul>
							<ul>
								<li class="titlel" style="width: 70px">

								</li>
								<li class="input-box" style="line-height: 22px">
								</li>
								<li class="input-box" style="line-height: 22px; width: 400px">
									<input type="button" name="" id="saveButton" value="保存" onclick="edit()" class="input-page-btn" />
									<input type="button" onclick="resetB()" id="resetButton" value="重置" class="input-page-btn" />

								</li>
								<li class="input-box" style="line-height: 22px">

								</li>
							</ul>

						</div>
					</div>
				</div>
			</div>
			<div style="display: none;">
				<div id="dd" style="top: 10px;height: 350px;width: 300px">
				<ul id="tt2">
				
				</ul>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="button" name="" align="middle"  value="确定" onclick="change()" class="input-page-btn" />
				</div>
				<div id="wh" style="top: 10px;height: 350px;width: 200px">
					<s:iterator value="good_list" var="list">
						<input name="goods" style="padding-left: 20px;padding-top: 5px" val="<s:property value="#list.fullname" />" type="checkbox" value="<s:property value="#list.fuid" />" /><s:property value="#list.fullname" /><br/>
					</s:iterator>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="button" name="" align="middle"  value="确定" onclick="changeWH()" class="input-page-btn" />
				</div>
			</div>
		</form>
	</body>

</html>