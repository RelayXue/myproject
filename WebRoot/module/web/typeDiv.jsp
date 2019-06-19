<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
pic_status = '${pic_status}';
p = '${page}';
    </script>
<s:if test="!list.isEmpty()">
		<s:iterator var="s" value="list" status="status">
    	<s:if test="pic_status==1">
           <!-- 有图片 -->
        <div class="_body_list_item1">
        	<div class="_img_box">
                <div id="${s.fuid}" class="owl-carousel owl-theme">
                <s:if test="%{!#s.images.isEmpty()}">
                  <s:iterator var="img" value="%{#s.images}">
                  <div class="item"><img src="<%=basePath%>upload/${img }" style="height: 425px;"></div>
                  </s:iterator>
                  </s:if>
                </div>
            
            </div>
            <div class="_content">
            	<div style="font-size:20px;" class="mtp"><span>${s.fullname }</span></div>
                <div style="overflow:hidden;" class="mtp">
                <!-- 
                <img src="<%=basePath%>module/web/images/m_icon3_06.png" class="fleft"/>
                <span class="fleft" style="line-height:33px; color:#e5772d";>${s.star } 人均：￥${s.consumption }&nbsp;&nbsp;</span>
                 -->
                <span style="color:#888888;line-height: 32px;" class="fleft">&nbsp;&nbsp; 电话：${s.phone }&nbsp;&nbsp;&nbsp;&nbsp; 地址：${s.address }</span>
                </div>
                <div style="border-top:1px solid #cfcfca; margin:auto 6px; min-height:10px; color:#888888; padding-top:8px;" class="mtp">
                </div>
            </div>

            <div class="_botm fleft">
            	<span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png);">${s.examine }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:10px 2px;">${s.praise }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:10px 5px;">${s.comment }</span>
                <div style="clear:both"></div>
            </div>
            <div class="_btn1" onclick="show_map('${s.fullname}','${s.fx }','${s.fy }');">前往&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="_btn2" onclick="show_detail('${s.fuid}','${s.type }');">详情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> 
        </div>
        <script type="text/javascript">
    $('#'+"${s.fuid}").owlCarousel({
		items: 1,
		navigation : false, // Show next and prev buttons
		  slideSpeed : 300,
		  paginationSpeed : 400,
		  singleItem:true
	});
    </script>
        </s:if>
        
        <s:else>
           <!-- 无图片 -->
        <div class="_body_list_item">
        	<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>${s.fullname }</td></tr></table>
            <div class="_content">
            	<div style="font-size:20px;"><span>${s.fullname }</span></div>
            	<!-- 
                <div style="display:block; height:30px; line-height:30px; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0 3px; padding-left:26px;color:#e5772d; font-size:16px;margin-top:3px;">${s.star } 人均：￥${s.consumption }</div>
                 -->
                <div style="font-size:14px; margin-top:3px; color:#615F5F;">电话：${s.phone }</div>
                <div style="font-size:14px; margin-top:3px; color:#615F5F; margin-bottom:10px;"> 地址：${s.address }</div>
            </div>
                <div class="_btn1" onclick="show_map('${s.fullname}','${s.fx }','${s.fy }');">前往&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <div class="_btn2" onclick="show_detail('${s.fuid}','${s.type }');">详情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> 
                <div style="clear:both"></div>
            <div class="_botm">
            	<span style="background-image:url(<%=basePath%>module/web/images/list_icon1_25.png)">${s.examine }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_19.png); background-position:10px 2px;">${s.praise }</span>
                <span style="background-image:url(<%=basePath%>module/web/images/list_icon1_22.png); background-position:10px 5px;">${s.comment }</span>
                <div style="clear:both"></div>
            </div>
        </div>
        </s:else>
        
        </s:iterator>
        </s:if>