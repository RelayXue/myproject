<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="!list.isEmpty()">
		<s:iterator var="s" value="list" status="status">
		<div class="_row">
                <div class="_img" style="background-image:url(<%=basePath%>upload/L_${s.dimages })"></div>
                <div class="_content">
                    <div class="f_18">${s.fullname }</div>
                    <div class="fs_14">
                    <!-- 
        <div style="display:block; height:30px; line-height:30px; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0 3px; padding-left:26px;color:#e5772d; font-size:16px;">${s.star } 人均：￥${s.consumption }</div>
         -->
         <br>
        <div style="font-size:14px; color:#615F5F; ">电话：${s.phone }</div>
        <div style="font-size:14px;  color:#615F5F; margin-bottom:10px;"> 地址：${s.address }</div>
                    </div>
                    <div class="f_14 font_blackgrey _botm" style="margin-left:-10px;">
                        <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png); background-position:0px center;">${s.examine }</span>
                        <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:5px 0px;">${s.praise }</span>
                        <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:6px 5px;">${s.comment }</span>
                    </div>
                    <div class="_btns">
                        <img src="<%=basePath%>module/web/images/d_icon1_31.png" style="cursor: pointer;" onclick="show_detail('${s.fuid}','${s.type }');"/>
                        <br /><br />
                        <img src="<%=basePath%>module/web/images/d_icon1_35.png" style="cursor: pointer;" onclick="show_map('${s.fullname}','${s.fx }','${s.fy }');"/>
                    </div>
                </div>
            </div>
        </s:iterator>
        </s:if>