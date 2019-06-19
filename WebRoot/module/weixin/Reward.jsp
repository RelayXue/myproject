<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
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
		
	<script type="text/javascript">
	 jQuery(document).ready(function($) {
	 //--------------分页-------------
		$("#page").AutoPage({
			totalPage : '${totalPage}', // 总页数
			currentPage: '${indexPage}',//当前页
			pageSize:'${pageSize}',    //每页显示条数
			url:'<%=basePath%>accommodation!listReward?win=${win}&val=${val}',
			showNum : 6  // 显示数字
			,show:1
		});
	})
	
	function submit(){
		var V=""
		if($("#iswinning").val()!=""){
			V=V+"win="+$("#iswinning").val()+"&"
		}
		if($("#mc").val()!=""){
			V=V+"val="+$("#mc").val()+"&"
		}
		window.location.href="<%=basePath%>accommodation!listReward?"+V
	}
	</script>
	
	</head>
<body>
		<div class="rightnav"><a href="#" class="active">抽奖人员列表</a></div>
		<div class="rsearch">
	是否中奖：<select style="width: 90px;border:1px solid gray" id="iswinning">
				<option value="">-请选择-</option >
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
			<input name="" id="mc"  type="text" value="" placeholder="请输入抽奖人昵称..." />
			<a href="javascript:serach()" class="btn btn-success" title="搜索" onclick="submit()"><i class=" icon-search icon-white"></i>搜索</a>
		</div>
		
		<div class="rhandle">
		</div>
		
		<div class="rtable">
			<table width="100%" border="0">
				<tr>
					<th width="5%"><input name="chAll" type="checkbox" value="" /></th>
					<th width=3%">编号</th>
					<th width="10%">抽奖人ID</th>
					<th width="10%">抽奖人昵称</th>
					<th width="10%">抽奖人头像</th>
					<th width="10%">抽奖时间</th>
					<th width="5%">是否中奖</th>
					<th width="10%">领奖时间</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator var="list" value="list_Reward" status="st">
				<tr>
					<td width="5%"><input name="chAll" type="checkbox" value="" /></td>
					<td width=3%"><s:property value="#st.index+1"/></td> 
					<td width="10%" style="color:#457DC7"><s:property value="#list.weixinid"/></td>
					<td width="10%" style="color:#457DC7"><s:property value="#list.weixinname"/></td>
					<td width="10%">
						<s:if test="#list.weixinimg==null || #list.weixinimg==''">
							<img src="<%=basePath%>module/weixin/images/tishi.png" style="width: 50px;height: 50px">
						</s:if>
						<s:else>
							<img src='<s:property value="#list.weixinimg"/>' style="width: 50px;height: 50px">
						</s:else>
					</td>
					<td width="10%" style="color:#457DC7"><s:date name="#list.createdate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td width="5%" style="color:#457DC7">
						<s:if test="accommodationId==null || accommodationId=='' || accommodationId=='No'">
							否 
						</s:if>
						<s:else>
							<span style="color:red">是</span>
						</s:else>
					</td>
					<td width="10%" style="color:#457DC7"><s:date name="#list.receivetime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td width="10%">
						<s:if test="accommodationId==null || accommodationId=='' || accommodationId=='No'">
							<a class="btn btn-danger" href="#"><i class="icon-share icon-white"></i>未中奖 </a>
						</s:if>
						<s:else>
							<s:if test="receivetime==null || receivetime==''">
								<a onclick="if(!confirm('确定领取奖品？')){return false;}" class="btn btn-info" href='<%=basePath%>accommodation!isReceive?id=<s:property value="#list.fuid"/>'><i class="icon-edit icon-white"></i>领奖 </a>	
							</s:if>
							<s:else>
								<a class="btn btn-success" href="#"><i class="icon-share icon-white"></i>已领取 </a>
							</s:else>
						</s:else>
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<div style="display: none;">
			<form id="mform" action="buWeixinluckdraw!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>
		
	</body>
</html>