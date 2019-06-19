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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/eayui/easyui.css"/>
		<script type="text/javascript" src="<%=basePath%>js/eayui/jquery.easyui.min.js"></script>
		<script>
	 jQuery(document).ready(function($) {
		 //--------------分页-------------
		 var type=0;
		 if('${type}'!=null && '${type}'==1){
			 type=1;
		 }
			$("#page").AutoPage({
					totalPage : '${totalPage}', // 总页数
					currentPage: '${indexPage}',//当前页
					pageSize:'${pageSize}',    //每页显示条数
					url:'characterworks!findByType?type='+type,
					showNum : 6 , // 显示数字
					show:1
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
				window.location.href="<%=basePath%>characterworks?ord="+$(this).val();
			})	
	 });
	 function serach(){
		 var skey=$("#mc").val();
		 var type=$("#type").val();
		 var url="characterworks?skey="+skey+"&type="+type;
		 window.location.href=url;
	 }
	  function serach1(){
		 var url="characterworks!findByType?type=0";
		 window.location.href=url;
	 }
	  function serach2(){
		 var url="characterworks!findByType?type=1";
		 window.location.href=url;
	 }
	 function detail(){
	 var url="<%=basePath %>module/weixin/characterworksdetail.jsp";
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
	 
	
			function Open_Dialog(id,skey,indexpage) {
				$('#mydialog').show();
				$('#mydialog').dialog({
					collapsible : true,
					minimizable : true,
					maximizable : true,
					buttons : [ {
						text : '提交',
						handler : function() {
						 var val = $("#score").val();
						 var type=$("#ty").val();
						 if(!isNaN(val)){
						 	if(val<=100&&val>=0){
						 	var url="characterworks!setScore?id="+id+"&skey="+skey+"&indexPage="+indexpage+"&score="+val+"&type="+type;
						 	window.location.href=url;
						 	}else{
						 		alert("该评分满分100分，请输入0-100的数字！");
						 	}
						 }else{
						 	alert("输入的值不是数字,请正确输入数字!");
						 }
						 
						}
					}, {
						text : '取消',
						handler : function() {
							$('#mydialog').window('close');
						}
					} ]
				});
			}
			
			function download(type){
				$.messager.confirm('提示:','你确认要导出图片么?',function(r){
				    if (r){
				    	$(".bgDiv").css("display","block");
						$.ajax({
							type : "POST",
							dataType : "text",
							url : "characterworks!downLoad",
							data:"type="+type,
							success : function(msg){
								$(".bgDiv").css("display","none");
								alert("已经保存在D:\我为乌镇拍封面");
							}
						});
				    }
				});
			}
		</script>
		<style>
 			.bgDiv{
 				width:100%;
 				height:100%;
 				position:absolute;
 				z-index:999;
 				float:left;
 				text-align:center;
 				background:rgba(255, 255, 255, 0.6)!important;
 				padding-top:200px;
 				display: none
 			}
		</style>
	</head>
	<body>
	<div class="bgDiv">
		<img src="<%=basePath%>Photography/PhotographyImg/upload.gif">
	</div>
	<div class="rightnav">
			<a href="#" class="active"> 专家操作区</a>
		    <!-- <a href="#" class="active">基础数据管理</a> -->	
	</div>
	<div class="rsearch" style="float:left">
		<!--<span class="info">搜索:</span>-->
		<input type="hidden" id="ty" value="${type}"/>
		<input name="" id="mc" type="text" class="inp" value="${skey}" />
		<a href="javascript:serach()" class="btn btn-success" title="搜索"><i class=" icon-search icon-white"></i>搜索</a>
		<a href="javascript:serach1()" class="btn btn-success" title="手机组作品">手机组作品</a>
		<a href="<%=basePath%>characterworks!exportExcel?type=0" class="btn btn-success" title="手机组作品导出">手机组信息导出</a>
		<a href="javascript:download(0)" class="btn btn-success" title="手机组作品导出">手机组照片导出</a>
		
		<a href="javascript:serach2()" class="btn btn-success" title="相机组作品">相机组作品</a>
		<a href="<%=basePath%>characterworks!exportExcel?type=1" class="btn btn-success" title="相机组作品导出">相机组信息导出</a>
		<a href="javascript:download(1)" class="btn btn-success" title="手机组作品导出">相机组照片导出</a>
	</div>
	
	
	
	<div class="rhandle" style="clean:both">
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
					<th width="6%">
						选中状态
					</th>
					<th width="3%">
						分数
					</th>
					<th width="8%">
						日期
					</th>
					<th width="12%">
						操作
					</th>
				</tr>
				
				<s:iterator value="a20161characterworks_list" var="list" status="stuts">
					<tr>
						<td>
							<input name="ch" type="checkbox" value="<s:property value="#list.fuid"  />" />
						</td>
						
						<td>
							<span><s:property value="(indexPage-1)*pageSize+(#stuts.index+1)"/></span>
						</td>
						
						<td>
							<s:property value="#list.title" />&nbsp;
						 </td>
						 
					
						<td>
							<s:property value="#list.peoplename" />&nbsp; 
						</td>
						<td>
						<s:if test="#list.votetype==1">
							<span style="color: red;">已选中</span> 
						</s:if>
						<s:else>
						未选中
						</s:else>
						 &nbsp; 
						</td>
						<td>
							<s:property value="#list.expertmark" />&nbsp; 
						</td>
						
						<td>
							<s:date name="#list.createtime" format="yyyy-MM-dd HH:mm:ss" />
						</td>
						
						<td>
							<s:if test="#list.votetype==1">
								<s:if test="a20161activity.type==3">
								<a class="btn btn-success" href="javascript:Open_Dialog('<s:property value="#list.fuid"  />','${skey}','${indexPage }')"><i class="icon-edit icon-white"></i>专家评分</a>
								</s:if>
								<s:elseif test="a20161activity.type<3">
									<a class="btn btn-danger" href="javascript:;">评分未开启</a>
								</s:elseif>
								<s:elseif test="a20161activity.type>3">
									<a class="btn btn-warning" href="javascript:;">评分已完成</a>
								</s:elseif>
							</s:if>
							<s:if test="a20161activity.type==2">
							<s:if test="#list.votetype!=1">
							<a class="btn btn-success" href="<%=basePath%>characterworks!setVote?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=1"><i class="icon-edit icon-white"></i>设为选中</a>
							</s:if>
							<s:else>
							<a class="btn btn-success" href="<%=basePath%>characterworks!setVote?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&isVote=0"><i class="icon-edit icon-white"></i>取消选中</a>
							</s:else>
							</s:if>
							<s:elseif test="a20161activity.type<2">
							<a class="btn btn-danger" href="javascript:;">筛选未开启</a>	
							</s:elseif>
							<s:elseif test="a20161activity.type>2">
							<a class="btn btn-warning" href="javascript:;">筛选已完成</a>	
							</s:elseif>
							<a class="btn btn-info" href="<%=basePath%>characterworks!showDetail?id=<s:property value="#list.fuid"  />&skey=${skey}&indexPage=${indexPage}&type=${type}&choose=<s:property value="a20161activity.type"  />"><i class="icon-edit icon-white"></i> 查看详情 </a>		
							
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<div id="page"></div>
		<div id="mydialog" style="display:none;padding:5px;width:300px;height:180px;" title="评分弹框" data-options="modal:true"> 
			<div style="padding:20px 20px 10px 40px;">
			<label class="lbInfo">分数：</label> 
			<input id="score" type="text" class="easyui-validatebox" required="true"  /><br /> 
			</div>
		</div> 
		<div style="display: none;">
			<form id="mform" action="characterworks!delete" method="post">
				<input type="hidden" name="id" value="" id="ids" />
			</form>
		</div>
</body>

</html>