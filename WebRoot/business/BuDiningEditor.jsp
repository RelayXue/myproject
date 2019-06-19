<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 

<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String type = request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
<head>
		<title>后台维护信息</title>
		<link id="bs-css" href="<%=basePath%>js/BootstrapV2/css/bootstrap-cerulean.css" rel="stylesheet"/>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/jquery.validate.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath%>js/common/validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/uploadPreview.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ImgUpload/FileUpload.js"></script>
		<script type="text/javascript" language="javascript" src="<%=basePath %>js/common/AutoSelect.js"></script>
		<%--
		<script type="text/javascript"src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript"src="<%=basePath%>js/ckeditor/config.js"></script>
		--%>
		<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>
		<script>
	function edit(){
	var myform=$("#myform");
	//-----图片编辑参数----
	var imgNum="";
		$("img[id^=ImgPr]").each(function (i){
			var isUpdata=$(this).parent().attr("style");
			var src=$(this).attr("src");
			//IE
			if(isUpdata!=null&&isUpdata.length>0){
				imgNum+=i+",";
			}
			//GOOGLE  firfox
			if(src!=null&&src.split("blob").length>1){
				imgNum+=i+",";
			}
		});
	imgNum=imgNum.length>0?imgNum.substring(0,imgNum.length-1):imgNum;
	reNum=reNum.lenth>0?reNum:reNum.substring(0,reNum.length-1);
	//-----------------------------------------
	var select =$("select").val();
	if(select==-1){
		alert("请您选择一种类型！");
		return ;
	}
		myform.attr("action","<%=basePath%>buDining!edit?reNum="+reNum+"&imgNum="+imgNum+"&skey=${skey}&indexPage=${indexPage}");
		myform.submit();
}
function resetB(){
	$("#myform").find(("input[type='text']")).val("");
}
function res(){
	 window.location.href="javascript:history.go(-1);";
}
	$(document).ready(function() {
		/*CKEDITOR.replace('buDining.feature');*/
		
		$("#myform").validate({
			rules : {
				fullname:{
					required: true
				},
				supervisorphone:{
					mysj: true
				},
				staffnum:{
					number:true
				},
				area:{
					number:true
				},
				capital:{
					number:true
				}
			} 
			});
	//判断图片是否是大小图
	var size="${buDining.dimagesStatus}";
	if(size==1){
	   $("#big").attr("checked","checked");
	}else{
	   $("#small").attr("checked","checked");
	}
		
	//判断是否是党员
	var dangyuan="${buDining.dangyuan}";
	if(dangyuan==1){
	   $("#yes").attr("checked","checked");
	}else{
	   $("#no").attr("checked","checked");
	}
			
	//类型
	var codelx='${buDining.type}';
		codelx=codelx.length==0?'<%=type%>':codelx;
		$("#ftype").AutoSelect({
			data:basetemp,	
			name:"buDining.type",
			showNum :3,
			initCode : codelx
		});
			
			
			
			//-----图片上传--------
	var img='${buDining.dimages}';
	if(img!='null'&&img.length>0&&img!='default.jpg'){
		$("").uploadShow({data : img,path:"/upload/B_" });
	}else{
		$("").uploadInit();
	}
	
			
			
      });
	</script>
		<style>
 
</style>
	</head>
<body>
		<form id="myform" action="" method="post" ENCTYPE="multipart/form-data">
			<div class="inright">
				<div class="rightnav">
					<a href="#" class="active">餐饮管理</a>
				</div>
				<div class="sdetail">
					<div class="sdtable">
	<table width="100%" border="0">
			<tr>
				<td align="right" style="width: 70px;">名称：</td>
				<td align="left" style="width: 100px">
					 <input type="text"   id="fullname"  name="buDining.fullname"  value="${buDining.fullname}" />
					 <input type="hidden" id="fuid"    name="buDining.fuid"  value='${buDining.fuid}' />
				</td>
								
				<td align="right" style="width:70px;">行业类型：</td>
				<td align="left" style="width: 100px">
					 <div id="ftype"></div>
				</td>
			</tr>
			
			
		<tr>
			<td align="right" style="width: 70px;">负责人：</td>
			<td align="left" style="width: 100px">
				 <input type="text"      name="buDining.supervisor"  value='${buDining.supervisor}' />
			</td>
			
			<td align="right" style="width:70px;">负责人手机：</td>
			<td align="left" style="width: 100px">
				<input type="text"   id="supervisorphone"  name="buDining.supervisorphone"  value='${buDining.supervisorphone}'/>
			</td>
		</tr>
			
		<tr>
			<td align="right" style="width: 70px;">店铺电话：</td>
			<td align="left" style="width: 100px">
 				 <input type="text"   id="phone"   name="buDining.phone"  value='${buDining.phone}' />
			</td>
			
			<td align="right" style="width:70px;">地址：</td>
			<td align="left" style="width: 100px">
				<input type="text"   name="buDining.address"  value='${buDining.address}' />
			</td>
		</tr>			
						
		<tr>
			<td align="right" style="width:70px;">菜系：</td>
			<td align="left" style="width: 100px">
				<input type="text"name="buDining.cuisine" value='${buDining.cuisine}'/>
			</td>
			<td align="right" style="width:70px;">商家承诺：</td>
			<td align="left" style="width: 100px">
				<input type="text"  name="buDining.promise"  value='${buDining.promise}' />
			</td>
		</tr>				    
		<tr>
			<td align="right" style="width:70px;">是否属于子夜路：</td>
			<td align="left" style="width: 100px">
				<s:if test="buDining.iszy==1">
				<input type="radio" checked="checked" name="buDining.iszy" value='1'  />是
				<input type="radio"  name="buDining.iszy" value='0'  />否
				</s:if>
				<s:else>
				<input type="radio" name="buDining.iszy" value='1'  />是
				<input type="radio" checked="checked" name="buDining.iszy" value='0'  />否
				</s:else>
			</td>
			<td align="right" style="width:70px;"> </td>
			<td align="left" style="width: 100px">
			 
			</td>
		</tr>				
							
		<tr>
			<td align="right" style="width: 70px;">图片尺寸： </td>
			<td align="left" style="width: 100px">
					<input id="big"  type="radio"  name="buDining.dimagesStatus"  value="1">大图</input>
            		<input id="small" type="radio" name="buDining.dimagesStatus"  value="0">小图</input>
			</td>
			
			<td align="right" style="width:70px;">党员：</td>
			<td align="left" style="width: 100px">
				<input type="radio" id="yes" name="buDining.dangyuan" value="1"  />是
				<input type="radio" id="no" name="buDining.dangyuan" value="0" />否
				<font style="color: gray;">(这里的党员是针对<font style="color: red">负责人的身份</font>,默认是否)</font>
			</td>
		</tr>	
		
		
		<tr>
			<td align="right" style="width: 70px;;position: absolute;">图片上传：</td>
			<td align="left" style="width: 100px">
			   	<div id="file_div"></div>
				<div style="margin-top: 70px;margin-bottom: 70px;"><a id="AddBtn"   href="javascript:;"><img src="<%=basePath%>images/upload.png"/></a></div>
			</td>
		</tr>	
	</table>			
				
							
	<table width="100%" border="0">					
		<tr>
			<td align="right" style="width: 70px;">特色：</td>
			<td align="left" style="width: 400px">
				<textarea  style='border: 1px solid #bfbfbf;width: 610px' rows="5" name="buDining.feature">${buDining.feature}</textarea>
			</td>
		</tr>
		
		<tr>
			<td align="right" style="width: 70px;">介绍：</td>
			<td align="left" style="width: 400px">
				<textarea  style='border: 1px solid #bfbfbf;width: 610px' rows="5" name="buDining.introduction">${buDining.introduction}</textarea>
			</td>
		</tr>		
	</table>			
	
<hr/>	
	<table width="100%" border="0">							
		<tr>
			<td align="right" style="width: 75px;">企业类型：</td>
			<td align="left" style="width: 100px">
				 <input type="text"    name="buDining.compnaytype"  value='${buDining.compnaytype}' />
			</td>
			
			<td align="right" style="width:75px;">成立时间：</td>
			<td align="left" style="width: 100px">
				  <input type="text"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"    name="buDining.founded"  value='<s:date name="buDining.founded" format="yyyy-MM-dd" />' />
			</td>
		</tr>	
		<tr>
			<td align="right" style="width: 75px;">营业时间：</td>
			<td align="left" style="width: 100px">
				 <input type="text"   name="buDining.hours"  value='${buDining.hours}' />
			</td>
			
			<td align="right" style="width:75px;">档案号：</td>
			<td align="left" style="width: 100px">
				 <input type="text"   name="buDining.fileno"  value='${buDining.fileno}' />
			</td>
		</tr>	
		
		<tr>
			<td align="right" style="width: 75px;">职工：</td>
			<td align="left" style="width: 100px">
				<input type="text"   id="staffnum"   name="buDining.staffnum"  value='${buDining.staffnum}' />
			</td>
			
			<td align="right" style="width:75px;">面积：</td>
			<td align="left" style="width: 100px">
				 <input type="text"  id="area"   name="buDining.area"  value='${buDining.area}' />
			</td>
		</tr>	
		<tr>
			<td align="right" style="width: 75px;">卫生许可证号：</td>
			<td align="left" style="width: 100px">
			 <input type="text"     name="buDining.healthpermits"  value='${buDining.healthpermits}' />
			</td>
			
			<td align="right" style="width:75px;">许可证有效期限：</td>
			<td align="left" style="width: 100px">
				  <input type="text"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"    name="buDining.healthtime"  value='<s:date name="buDining.healthtime" format="yyyy-MM-dd" />' />
			</td>
		</tr>	
		
		<tr>
			<td align="right" style="width: 75px;">注册资金：</td>
			<td align="left" style="width: 100px">
				 <input type="text"  id="capital"   name="buDining.capital"  value='${buDining.capital}' />
			</td>
			
			<td align="right" style="width:75px;">注册号：</td>
			<td align="left" style="width: 100px">
				  <input type="text"   name="buDining.registrationno"  value='${buDining.registrationno}' />
			</td>
		</tr>
		
		<tr>
			<td align="right" style="width: 75px;">经营范围：</td>
			<td align="left" style="width: 100px">
				   <input type="text"    name="buDining.business"  value='${buDining.business}' />
			</td>
			
			<td align="right" style="width:75px;">截至日期：</td>
			<td align="left" style="width: 100px">
				 <input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"    name="buDining.deadline"  value='<s:date name="buDining.deadline" format="yyyy-MM-dd" />' />
			</td>
		</tr>
	</table>
		
	<table width="100%" border="0">
		<tr>
			<td align="right" style="width: 75px;">地图：</td>
			<td align="left" style="width: 100px">
				<input type="hidden"  id="fx" class="input-text"    name="buDining.fx"  value='${buDining.fx}' />
				 <input type="hidden"  class="input-text"  id="fy"  name="buDining.fy"  value='${buDining.fy}' />
				 <input id="btnPoint" type="button" class="input-page-btn"   value="开启描绘"   />
			</td>
				
			
		</tr>
		<tr>
			<td align="right" style="width: 70px;"></td>
			<td align="left" >
				<iframe  width="690px" id="ifmap" height="500px" frameborder="no" border="0"  src="<%=basePath%>business/MapEdit.jsp?type=attractions"></iframe>
			</td>
			
		</tr>
		
		<tr>
			<td align="right" style="width: 70px;"></td>
			<td align="left" style="width: 100px">
					<button class="btn btn-large btn-success"  type="button" onclick="edit()" ><i class="icon-check icon-white"></i>提交</button>
					<button class="btn btn-large btn-primary " type="button"  onclick="res()"><i class="icon-backward icon-white"></i>返回</button>
			</td>
		</tr>
						</table>
					</div>
				</div>
			</div>
		</form>	
	</body>

</html>

