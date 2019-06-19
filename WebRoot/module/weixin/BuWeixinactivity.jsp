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
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buWeixinactivity?skey=${skey}',
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
				
			$("#od").change(function (){
				window.location.href="<%=basePath%>buWeixinactivity?ord="+$(this).val();
			})	
	 });
	 
	 function serach(){
		 var skey=$("#mc").val();
		 var url="buWeixinactivity?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
	 var url="<%=basePath %>module/weixin/BuWeixinactivityEditor.jsp";
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
	 
	 
	 
	</script>
		<style>
 
</style>
	</head>
	<body>
	<div class="rightnav">
			<a href="#" class="active">  赛诗会</a>
		    <!-- <a href="#" class="active">基础数据管理</a> -->	
	</div>
	
	<div class="rsearch" style="float:left">
		<!--<span class="info">搜索:</span>-->
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
	</div>
	
	<div class="order" style="color:#4a4a4a; font-weight:bold;">
	<a href="javascript:order()" class="order-success" title="排序"><i class="icon-order"></i>排序 </a>
	   <!-- 排序 -->
	   <select id="od" style="border :solid 1px #e2e2e2">
	   		<option>请选择</option>
	   		<option value="number">按照投票数排序</option>
	   		<option value="worder">按照自定义排序</option>
	   		<option value="createtime" > 按照日期数排序</option>
	   </select>
	   
	</div>
	
	
	
	<div class="rhandle" style="clean:both">
		<a class="btn btn-success" href="javascript:add()"><i class="icon-plus-sign icon-white"></i>添加</a>
		
		<a class="btn btn-danger" href="javascript:delMore()"><i class="icon-trash icon-white"></i>批量删除</a>
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
						标题
					</th>
					<th width="6%">
						作者
					</th>
					<th width="3%">
						是否投票
					</th>
					<th width="3%">
						投票数量
					</th>
					<th width="3%">
						排序
					</th>
					<th width="8%">
						日期
					</th>
					<th width="10%">
						操作
					</th>
				</tr>
				
				<s:iterator value="buWeixinactivity_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
						
						<td>
							<s:property value="#list.title" />&nbsp;
						 </td>
						 
					
						<td>
							<s:property value="#list.author" />&nbsp; 
						</td>
						<td>
						<s:if test="#list.isVote==1">
							<span style="color: red;">是</span> 
						</s:if>
						<s:else>
						否
						</s:else>
						 &nbsp; 
						</td>
						<td>
							<s:property value="#list.number" />&nbsp; 
						</td>
						
						<td>
							<s:property value="#list.worder" />&nbsp;
						</td>
						
						<td>
							<s:date name="#list.createtime" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						
						<td>
							<s:if test="#list.isVote!=1">
							<a class="btn btn-success" href="<%=basePath%>buWeixinactivity!setVote?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=1"><i class="icon-edit icon-white"></i>设为投票</a>
							</s:if>
							<s:else>
							<a class="btn btn-success" href="<%=basePath%>buWeixinactivity!setVote?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=0"><i class="icon-edit icon-white"></i>取消投票</a>
							</s:else>
							<s:if test="com.hisUpdate==true">
							<a class="btn btn-info" href="<%=basePath%>buWeixinactivity!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}"><i class="icon-edit icon-white"></i> 编辑 </a>		
							</s:if>
							
							<s:if test="com.hisDelete==true">
							<a onclick="if(!confirm('您确定删除吗？')){return false;}" class="btn btn-danger" href="<%=basePath%>buWeixinactivity!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}"><i class="icon-trash icon-white"></i>删除</a>
							
							</s:if>
							
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="buWeixinactivity!delete" method="post">
				<input type="hidden" name="id" value="" id="ids" />
			</form>
		</div>
</body>

</html>