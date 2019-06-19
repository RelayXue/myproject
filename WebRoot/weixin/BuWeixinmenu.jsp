 
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<title>后台维护信息</title>
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
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
		 var url="weixinMenu?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 		var num=5;
	 	 $.ajax({
			    url: "weixinMenu!menuNum?time=" + new Date(),
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			   	 num=data;
			    }
			});
			if(num>=3){
			   $('#dlg').dialog('open');
			   return;
			}
		 var url="<%=basePath %>weixin/BuWeixinmenuEditor.jsp";
		 window.location.href=url;
	 }
	 function addChild(parentId){
		 var url="<%=basePath %>weixin/BuWeixinmenuEditor.jsp?parentId="+parentId;
		 window.location.href=url;
	 }
	 function inWinxin(){
	 	$.ajax({
			    url: "weixinMenu!subWeixin?time=" + new Date(),
			    async: false,
			    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			    dataType: "text",
			    success: function(data) {
			    	if(data!=null&&data.length>0){
			    		var re=data.split(",");
			    		if(re!=null&&re[0]==0){
			    			$('#dl2').dialog('open');
			    		}else{
			    			alert("提交失败，错误代码："+re[0]);
			    		}
			    	}
			   	   
			    }
			});
	 }
	 
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">微信菜单管理</a>
		</div>
		<div class="rsearch">
			<!--<span class="info">搜索:</span>-->
			<input name="" id="mc" type="text" class="inp" value="${skey}" />
			<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
			<span style="color:red  ; font-weight:bold;  ; display:block; margin: 0 auto;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;提示：微信菜单提交后，微信客户端将在24小时后更新菜单内容。</span>
		</div>
		<div class="rhandle">
			<!--<a href="javascript:add()" class="tip1">添加根目录</a>-->
			<a class="btn btn-success" href="javascript:add()"><i class="icon-plus-sign icon-white"></i>添加根目录</a>
			<!--<a href="#" class="tip2">编辑</a>-->
			<!--<a href="javascript:delMore()" class="tip3">删除</a>
		--></div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="8%">
						所属菜单
					</th>
					<th width="10%">
						名称
					</th>
					<th width="13%">
						链接地址
					</th>
				 
					<th width="10%">
						修改时间
					</th>
					<th width="14%">
						操作
					</th>
				</tr>
				<s:iterator value="buWeixinmenu_list" var="list" status="stuts">
					<tr>
						 
						<td>
							<span>
								<s:if test="#list.parentid==1">
									 根目录 
								</s:if>
								<s:else>
									 &nbsp; &nbsp; &nbsp; ---<s:property value="#list.parentid" />
								</s:else>
							 </span>
						</td>
								<td>   
								<s:if test="#list.parentid==1">  
									<s:property value="#list.fullname" />
								</s:if>
								<s:else>
									&nbsp; &nbsp; &nbsp; ---<s:property value="#list.fullname" />
								</s:else>
						 		</td>
								<td style="text-align:left;padding-left:6px">
								 &nbsp;
								<s:property value="@com.gh.common.SplitChinese@splitStr(#list.wurl,15)"/>
								 </td>
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<s:if test="com.hisAdd==true">
								<s:if test="#list.parentid==1">
									<!--<a href="javascript:addChild('<s:property value="#list.fuid"  />')">添加二级目录</a>&nbsp;&nbsp;-->
									<a class="btn btn-success" href="javascript:addChild('<s:property value="#list.fuid"  />')"><i class="icon-plus-sign icon-white"></i>添加二级目录</a>
								</s:if>
							</s:if>
							<s:if test="com.hisUpdate==true">
							<!--<a href="<%=basePath%>weixinMenu!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">编辑</a>&nbsp;&nbsp;-->
							<a class="btn btn-info" href="<%=basePath%>weixinMenu!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-edit icon-white"></i> 编辑 </a>		
							</s:if>
							<s:if test="com.hisDelete==true">
								<s:if test="#list.parentid==1">
								</s:if>
								<s:else>
									<!--<a onclick="if(!confirm('确定删除！')){return false;}" href="<%=basePath%>weixinMenu!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">删除</a>-->
								<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href="<%=basePath%>weixinMenu!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}"><i class="icon-trash icon-white"></i>删除</a>
								</s:else>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page">
			<div class="rpage"><tt ><input name="" value="提交" type="button" class="btn2" style="display: block;margin:0 auto;float: none;" onclick="inWinxin()" /></tt></div>
		</div>
		<div style="display: none;">
			<form id="mform" action="weixinMenu!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		<div id="dlg"  class="easyui-dialog" title="提示" data-options="iconCls:'icon-save'" closed="true" style="width:200px;height:130px;padding:10px">
       	  	微信一级菜单最多3个<br/><br/>微信二级菜单最多5个！
    	</div>
		<div id="dl2"  class="easyui-dialog" title="提示" data-options="iconCls:'icon-save'" closed="true" style="width:200px;height:130px;padding:10px">
       	  	微信菜单提交成功<br/><br/>微信客户端将在24小时后更新！
    	</div>
	</body>

</html>