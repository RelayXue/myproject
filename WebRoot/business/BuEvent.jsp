<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
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
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/common/page_dh.js"></script>
		<script>
	 jQuery(document).ready(function($) {
	 		//--------------分页-------------
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'buEvent?skey=${skey}',
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
		 var url="buEvent?skey="+skey;
		 window.location.href=url;
	 }
	 function add(){
		 var url="<%=basePath%>business/BuEventEditor.jsp";
		 window.location.href = url;
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
		 
	</head>
	<body>
		<div class="rightnav">
			<a href="#" class="active">事件管理</a>
		</div>
		<div class="rsearch">
			<span class="info">搜索:</span>
			<input name="" id="mc" type="text" class="inp" value="${skey }" />
			<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
		</div>
		<div class="rhandle">
			<a href="javascript:add()" class="tip1">添加</a>
			<!--<a href="#" class="tip2">编辑</a>-->
			<a href="javascript:delMore()" class="tip3">删除</a>
		</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%">
						<input name="chAll" type="checkbox" value="" />
					</th>
					<th width="3%">
						&nbsp;
					</th>
					<th width="11%">
						编号
					</th>
					<th width="14%">
						类型
					</th>
					<th width="14%">
						标题
					</th>
					<th width="14%">
						状态
					</th>
					<th width="8%">
						上传时间
					</th>
					<th width="16%">
						操作
					</th>
				</tr>
				<s:iterator value="buEvent_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						<td>
							<span><s:property value="#stuts.index+1" /> </span>
						</td>
						<td>
							<s:property value="#list.dnumber" />
						</td>
						<td>
							<s:property value="#application.DatadictionaryMap[type]"  /> 
						</td>
						<td>
							<s:property value="#list.title" />
						</td>
						<td>
						 <s:if test="#list.status==1">
						 	已处理
						 </s:if>
						 <s:else>
						 	未处理
						 </s:else>
						</td>
						<td>
							<s:date name="#list.modifydate" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<a href="javascript:add()">添加</a>&nbsp;&nbsp;
							<a href="<%=basePath%>buEvent!update_show?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">编辑</a>&nbsp;&nbsp;
							<a onclick="if(!confirm('确定删除！')){return false;}" href="<%=basePath%>buEvent!delete?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
		<div style="display: none;">
			<form id="mform" action="buEvent!delete" method="post">
				<input type="hidden" name="id" value="" id="ids" />
			</form>
		</div>
	</body>

</html>