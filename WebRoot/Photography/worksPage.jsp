<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
 <s:iterator value="list_works" var="list" status="st">
    	<div class="jianjie">
    		<table style="width:100%" onclick="tiaozhuan(this.id)" id="<s:property value="#list.fuid"/>">
    		<input type="hidden" value="<s:property value="#list.fuid"/>">
    			<tr>
    				<td style="width:70%;color:#C6A572">编号：<s:property value="#list.fuid"/></td>
    				<td style="text-align:right;width:30%;color:#6096D1">
    					<b> 详情</b>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" style="width:100%"><img src="<%=basePath%>upload/<s:property value="#list.imgurl"/>" style="width:100%;height:120px;border:1px solid #C6A572"></td> 
    			</tr>
    			<tr>
    				<td colspan="2" style="color:#C6A572">标题：<s:property value="#list.title"/></td>
    			</tr>
    			<tr>
    				<td colspan="2"><hr style="border: #C6A572 1px dashed"/></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="font-size:14px;color:888888">作者：<s:property value="#list.peoplename"/></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="font-size:14px;color:888888;width:100%">
    				<div class="jianjie1">
    					<div class="jianjie1">
    					<s:if test="#list.introduce==null || #list.introduce==''">
    						&nbsp;&nbsp;&nbsp;
    					</s:if>
    					<s:else>
    						<s:property value="#list.introduce"/>
    					</s:else>
    				</div>
    				</div>
    				</td>
    			</tr>
    		</table>
    		<s:if test="#list.operation==1">
	    		<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx598df1869ba5231a&redirect_uri=http%3a%2f%2fwuzhen.gov.cn%2fPhotography!vote%3fid%3d<s:property value="#list.fuid"/>&response_type=code&scope=snsapi_base&state=123#wechat_redirect">
	    		<!-- <a href="<%=basePath%>Photography!vote?id=<s:property value="#list.fuid"/>">-->
	    			<div style="width:100%;border-radius:5px;text-align:center;padding-bottom: 5px" id="fuid<s:property value="#st.index"/>">
				    	<input type="hidden" value="<s:property value="#list.fuid"/>">
				    	<img src="<%=basePath%>Photography/PhotographyImg/dianzan.png" style="width:100%"/>
				    	<div style="float:left;position:relative;left:43%;top:-25px;height:0px;color:#fff;font-size:19px"><s:property value="#list.num"/></div>
				    </div>
				</a>
    		</s:if>
    	</div>
    </s:iterator>