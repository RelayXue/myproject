 
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
					url:'buWeixinmass?skey=${skey}',
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
		 var url="buWeixinmass?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>module/weixin/BuWeixinmassEditor.jsp";
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
	 
	 function send(type){
		 var fuid="";
		 if(type=='m'){
			 $("input[name='ch']").each(function (){
				 if($(this).attr("checked")){
					 fuid+=$(this).val()+",";
				 }
			 })
			 if(fuid.length==0){
				 alert("请先勾选发送的内容！");
				 return;
			 }else{
				 fuid=fuid.substring(0,fuid.length-1);
			 }
		 }else{
			 fuid=type;
		 }
		 $.ajax({
			    url: "buWeixinmass!send?time=" + new Date(),
			    type:"post",
			    data:"id="+fuid,
			    async: true,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			    	alert("群发成功！微信收到群发信息较慢，请耐心等待！");
			    	window.location.reload();
			    }
			});
	 }
	 var fuid="";
	 function ysend(type){
		 $('#dd').dialog({
			    modal:true,
				title:"请输入微信号"
			  });
		 if(type=='m'){
			 $("input[name='ch']").each(function (){
				 if($(this).attr("checked")){
					 fuid+=$(this).val()+",";
				 }
			 })
			 if(fuid.length==0){
				 alert("请先勾选发送的内容！");
				 return;
			 }else{
				 fuid=fuid.substring(0,fuid.length-1);
			 }
		 }else{
			 fuid=type;
		 }
	 }
	 function cc(){
		 var weixinname=$("#weixinname").val();
		 $.ajax({
			    url: "buWeixinmass!ysend?time=" + new Date(),
			    type:"post",
			    data:"id="+fuid+"&weixinname="+weixinname,
			    async: true,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			    	alert(data);
			    	window.location.reload();
			    }
			});
		 $('#dd').dialog("close");
	 }
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">微信信息群发</a>
		</div>
		<div class="rsearch">
			<span class="info">搜索:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
			<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
			<span style="color: red;font-weight: bold;padding-left: 80px">微信信息群发每月限制为4次，超过4次发送无效</span>
		</div>
		<div class="rhandle">
			<a href="javascript:add()" class="btn btn-success"><i class="icon-plus-sign icon-white"></i>新增</a>
			<a   class="btn btn-success" href="javascript:ysend('m')"><i class="icon-edit icon-white"></i>批量预发</a>
			<a  onclick="if(!confirm('您确定发送吗？')){return false;}"  class="btn btn-success" href="javascript:send('m')"><i class="icon-edit icon-white"></i>批量群发</a>
			<span style="color: blue;font-weight: bold;">注：群发每次最多发送10条数据，多条一起发送算1次</span>
			<!--<a href="#" class="tip2">编辑</a>-->
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
					<th width="14%">
						标题
					</th>
					<th width="6%">
						发送状态
					</th>
					<th width="6%">
						排序
					</th>
					<th width="8%">
						发送时间
					</th>
					<th width="14%">
						操作
					</th>
				</tr>
				<s:iterator value="buWeixinmass_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
								<td><s:property value="#list.title" />&nbsp; </td>
								
								<td>
									<s:if test="#list.state==0||#list.state==null">未发送</s:if>
									<s:elseif test="#list.state==1">发送成功</s:elseif>
									<s:elseif test="#list.state==2">发送失败</s:elseif>
								</td>
								<td><s:property value="#list.orderby" />&nbsp; </td>
						<td>
							<s:date name="#list.createdate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
						<a class="btn btn-success"   href="javascript:ysend('<s:property value="#list.fuid"  />')"><i class="icon-edit icon-white"></i>预发送</a>
						<a class="btn btn-success" onclick="if(!confirm('您确定发送吗？')){return false;}" href="javascript:send('<s:property value="#list.fuid"  />')"><i class="icon-edit icon-white"></i>发送</a>
							<s:if test="com.hisUpdate==true">
							<a class="btn btn-info" href="<%=basePath%>buWeixinmass!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-edit icon-white"></i> 编辑 </a>	
							</s:if>
							<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href="<%=basePath%>buWeixinmass!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}"><i class="icon-trash icon-white"></i>删除</a>
							
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 80px;width: 350px">
					 <input type="text"  id="weixinname" value="${weixinname }" />
					<a   class="btn btn-success" href="javascript:cc()">确定</a>
					<a  class="btn btn-danger"  onclick="$('#dd').dialog('close');">取消</a>
				</div>
			</div>
		<div style="display: none;">
			<form id="mform" action="buWeixinmass!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
	</body>

</html>