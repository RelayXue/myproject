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
			url:'buWeixinluckdraw?skey=${skey}&drawStartTime=${drawStartTime}&drawEndTime=${drawEndTime}&iswinning=${iswinning}&isaward=${isaward}',
			showNum : 6  // 显示数字
			,show:1
		});
	 
	 	/*回显时*/
	 	var iswinning = '${iswinning}';//是否中奖
	 	var isaward = '${isaward}';//是否领奖
	 	if(iswinning){
		 	if(iswinning==1){
		 		$("#iswinning").val("1");
		 	}else if(iswinning==0){
		 		$("#iswinning").val("0");
		 	}
	 	}
	 	/*if(isaward){
		 	if(isaward==1){
		 		$("#isaward").val("1");
		 	}else if(isaward==0){
		 		$("#isaward").val("0");
		 	}
	 	}*/
		//--------------全选-------------
		$("input[name='chAll']").click(function (){
			if($(this).attr("checked")){
				$("input[name='ch']").attr("checked","true");
			}else{
				$("input[name='ch']").removeAttr("checked");
			}
		});
	});
	 
	 //声明：
	  var drawStartTime=null;
	  var drawEndTime = null;
	  var iswinning =null;
	  var isaward = null;
	  var skey = null;
	 //获取
	 function fetchs(){
		drawStartTime = $("#drawStartTime").val();
		 drawEndTime = $("#drawEndTime").val();
		 iswinning = $("#iswinning").val();
		 isaward = $("#isaward").val();
		 skey = $("#mc").val();
		 skey = skey.replace(/(^\s*)|(\s*$)/g, "");//去掉左右空格
	 }
	
	 /*搜索*/
	 function serach(){
		fetchs();
		var url="buWeixinluckdraw?skey="+skey+"&drawStartTime="+drawStartTime+"&drawEndTime="+drawEndTime+"&iswinning="+iswinning+"&isaward="+isaward;
		 window.location.href=url;
	 }
	 
	 /*导出*/
	 function exportData(){
		fetchs();
		var urls = "buWeixinluckdraw!countDataExport?skey="+skey+"&drawStartTime="+drawStartTime+"&drawEndTime="+drawEndTime+"&iswinning="+iswinning+"&isaward="+isaward;
		window.open(urls);
	}
	 
	
	 
	 /*批量删除*/
	/* function delMore() {
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
	}*/
	
	
	</script>
	
	</head>
<body>
		<div class="rightnav"><a href="#" class="active">抽将人员列表</a></div>
		<div class="rsearch">
			抽奖开始时间：<input type="text" id="drawStartTime"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" name="drawStartTime"  style="width: 70px;" value="${drawStartTime}"/>
			抽奖结束时间：<input type="text" id="drawEndTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" name="drawEndTime" style="width: 70px;" value="${drawEndTime}" />
			是否中奖：<select style="width: 90px;border:1px solid gray" name="iswinning" id="iswinning">
				<option value="">-请选择-</option >
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
			
			
			
			<%--是否领奖：<select style="width: 90px;border:1px solid gray" name="isaward" id="isaward">
				<option value="">-请选择-</option >
				<option value="1">是</option>
				<option value="0">否</option>
			</select>--%>
				
			
			
			<input name="" id="mc"  type="text" value="${skey}" placeholder="请输入抽奖人昵称..." />
			<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
			<a href="javascript:void(0)" class="btn btn-success" title="" onclick="exportData()"><i class="icon-share icon-white"></i>导出Excle</a>
		</div>
		
		<div class="rhandle">
			<%--<a class="btn btn-success" href="javascript:add()"><i class="icon-plus-sign icon-white"></i>添加</a>--%>
			<%--<a class="btn btn-danger" href="javascript:delMore()"><i class="icon-trash icon-white"></i>批量删除</a>--%>
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
					
					
					<th width="5%">是否领奖</th>
					
					<th width="10%">领奖时间</th>
					
					
					<th width="10%">操作</th>
				</tr>
				
				<s:iterator value="buWeixinluckdraw_list" var="list" status="stuts">
					<tr>
						<td><input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" /></td>
							
						<td><span><s:property value="#stuts.index+1" /> </span></td>
						
						<td><s:property value="#list.userid" />&nbsp; </td>
						
						<td><s:property value="#list.username" />&nbsp; </td>
						
						<td><img src="<s:property value="#list.headerimg" />" alt=""  style="width: 50px;height: 50px" />&nbsp; </td>
							
						<td><s:date name="#list.drawtime" format="yyyy-MM-dd HH:mm:ss" /></td>
						
						<s:if test="#list.iswinning==1"><td><span style="color: red;">是</span> </td></s:if><s:else><td>否</td></s:else>

						<s:if test="#list.isaward==1"><td>是</td></s:if><s:else><td>否</td></s:else>
							
						<td><s:date name="#list.awardtime" format="yyyy-MM-dd HH:mm:ss" /></td>
						
						<td>
							<s:if test="com.hisUpdate==true">
								<s:if test="#list.iswinning==1&&#list.isaward==0">
									<a onclick="if(!confirm('确定领取奖品？')){return false;}" class="btn btn-info" href="<%=basePath%>buWeixinluckdraw!checkInfo?id=<s:property value="#list.fuid" />"><i class="icon-edit icon-white"></i>领奖 </a>	
								</s:if>
								
								<s:elseif test="#list.iswinning==1&&#list.isaward==1">
									<a class="btn btn-success" href="#"><i class="icon-share icon-white"></i>已领取 </a>
								</s:elseif>
								<s:else>
									<a class="btn btn-danger" href="#"><i class="icon-share icon-white"></i>未中奖 </a>
								</s:else>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		
		<%--<div style="display: none;">
			<form id="mform" action="buWeixinluckdraw!delete" method="post">
				<input type="hidden" name="uid" value="" id="ids" />
			</form>
		</div>--%>
		
	</body>
</html>