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
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script>
	 jQuery(document).ready(function() {
		 //--------------分页-------------
		$("#page").AutoPage({
				totalPage : '${totalPage}', // 总页数
				currentPage: '${indexPage}',//当前页
				pageSize:'${pageSize}',    //每页显示条数
				url:'<%=basePath%>one!listPeople',
				showNum : 6 , // 显示数字
				show:1
			});
	 });
	 
	 
	 function exportFront50(){
	 	var time=$("#time").val();
	 	if(time==""){
	 		alert("请选择一天进行用户导出 ")
	 		return
	 	}
	 	window.location.href="<%=basePath%>2017/one!exportFront50?time="+time;
	 }
</script>
<style>
	a{
		color:#457DC7
	}
</style>
 
</head>
<body>
	<div class="rightnav"> 
		<a href="#" class="active">用户数据信息 &nbsp;</a>
	</div> 
	<div class="rhandle" style="padding-top:5px;padding-bottom: 5px"> 
		<input type="text" id="time" placeholder="请选择导出的时间…" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		<a class="btn btn-success" href="javascript:void(0)" style="padding:7px 10px 7px 10px;margin-top:5px" onclick="exportFront50()">导出前50名</a>
		<a class="btn btn-success" href="<%=basePath%>2017/one!exportWinning" style="padding:7px 10px 7px 10px;margin-top:5px">导出中奖人名单</a>
	</div>
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="14%">
						姓名
					</th>
					<th width="14%">
						电话
					</th>
					<th width="14%">
						路名暂定名
					</th>
					<th width="14%">
						参加者取名
					</th>
					<th width="14%">
						创建时间
					</th>
					<th width="14%">
						是否中奖
					</th>
					<th width="16%">
						操作
					</th>
				</tr>
				<s:iterator value="list_a20171user" var="li">
				<tr>
					<td>
						<s:property value="#li.name"/>
					</td>
					<td>
						<s:property value="#li.phone"/>
					</td>
					<td>
						<s:property value="#li.bridgeroadTemporaryName"/>
					</td>
					<td>
						<s:property value="#li.bridgeroadName"/>
					</td>
					<td>
						<s:date name="#li.createtime" format="yyyy-MM-dd"/>
					</td>
					<td>
						<s:if test="#li.isWinning==1">
							<b style="color:#5D46FC">未中奖</b>
						</s:if>
						<s:elseif test="#li.isWinning==2">
							<b style="color:green">已中奖</b>
						</s:elseif>
					</td>
					<td>
						<s:if test="#li.isWinning==1">
							<a href="<%=basePath%>2017/one!updateIswing?totalPage=${totalPage}&indexPage=${indexPage}&pageSize=${pageSize}&a20171user.isWinning=2&a20171user.fuid=<s:property value="#li.fuid"/>">设用户中奖</a>
						</s:if>
						<s:elseif test="#li.isWinning==2">
							<a href="<%=basePath%>2017/one!updateIswing?totalPage=${totalPage}&indexPage=${indexPage}&pageSize=${pageSize}&a20171user.isWinning=1&a20171user.fuid=<s:property value="#li.fuid"/>">取消用户中奖</a>
						</s:elseif>
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<div id="page"></div>
</body>
</html>