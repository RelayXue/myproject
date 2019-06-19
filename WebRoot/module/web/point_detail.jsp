<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 2014-11-03 新增 -->
        <div style=" position:absolute; width:427px; height:306px;">
        	<div style="background:#f8f8ec; padding:8px; position:relative;">
            	<!-- 图片 -->
            	<div style="background-color:#666666; border-radius: 8px; overflow:hidden; width:200px; height:200px; float:left; "><img src="<%=basePath%>upload/L_${dataContainer.obj.dimages }" width="200" height="200"/></div>
                <!-- 内容 -->
                <div style="width:198px; float:left; margin-left:12px; ">
                	<div style="border-bottom:1px solid #ccc; padding-bottom:8px;">
                	    <!-- 详情 -->
                        <img src="<%=basePath%>module/web/images/m_icon5_05.png" style="float:right; margin-left:10px;cursor: pointer;visibility: hidden;" onclick="closeWindows();"/>
                        <img src="<%=basePath%>module/web/images/m_icon5_03.png" style="float:right; margin-left:10px;" onclick="show_detail('${dataContainer.obj.fuid}','${type }');"/>
                    	<!-- 
                    	<div style="float:right; height:30px; width:24px; background-image:url(<%=basePath%>module/web/images/m_icon5_08.png); background-repeat:no-repeat; background-position:center; "></div>
                         -->
                        <div style="clear:both"></div>
                    </div>
                    <div style="padding-top:8px;">
                    	<div style="font-size:24px;">${dataContainer.obj.name }</div>
                    	<!-- 
                        <div style="margin-top:8px; font-size: 16px; color:#e5772d; background-image:url(<%=basePath%>module/web/images/m_icon3_06.png); background-repeat:no-repeat; background-position:0px center; padding-left: 25px;">${dataContainer.obj.star }  人均 ￥${dataContainer.obj.consumption }</div>
                         -->
                        <div style="margin-top:8px; font-size: 14px; color:#666; ">
                        	类型：${skey }<br /><br />
                            电话：(${dataContainer.obj.phone})<br /><br />
                            地址：${dataContainer.obj.address}
                        </div>
                    </div>
                </div>
                <div style="clear:both"></div>
                <!-- 底部内容 -->
                <div class="_bom_tab_box">
                	<span class="_tab1 _hover" onclick="setMapTab(1);">去这里</span><span class="_tab2" onclick="setMapTab(2);">从这走</span><!-- <span class="_tab3" onclick="setMapTab(3);">找周边</span>  -->
                    <div style="clear:both"></div>
                    <div id="_tabc1" class="_tabc">
                    	<div style="background-color:#64a489; border-radius: 4px; text-align:center; width:32px; height:32px; line-height:32px; color:#FFFFFF;float:left;">起</div>
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; margin-left:10px; float:left;z-index: 999;"><input id="startText" type="text" placeholder="输入关键字" onblur="startBlur();"/><input type="hidden" id="startHiddTxt"/></div>
                        <div class="_ico2" onclick="searchCarLine1('2','${dataContainer.obj.fullname}','${dataContainer.obj.fx }','${dataContainer.obj.fy }','car');"></div>
                        <div class="_ico3" onclick="searchCarLine1('2','${dataContainer.obj.fullname}','${dataContainer.obj.fx }','${dataContainer.obj.fy }','walk');"></div>
                        <div style="clear:both"></div>
                    </div>
                    <div id="_tabc2" class="_tabc" style="display:none">
                    	<div style="background-color:#717699; border-radius: 4px; text-align:center; width:32px; height:32px; line-height:32px; color:#FFFFFF;float:left;">终</div>
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; margin-left:10px; float:left;"><input id="endText" type="text" placeholder="输入关键字" onblur="endBlur();"/><input type="hidden" id="endHiddTxt"/></div>
                        <div class="_ico2" onclick="searchCarLine1('1','${dataContainer.obj.fullname}','${dataContainer.obj.fx }','${dataContainer.obj.fy }','car');"></div>
                        <div class="_ico3" onclick="searchCarLine1('1','${dataContainer.obj.fullname}','${dataContainer.obj.fx }','${dataContainer.obj.fy }','walk');"></div>
                        <div style="clear:both"></div>
                    </div>
                    <div id="_tabc3" class="_tabc" style="display:none">
                        <div style="background-color:#fff; border:1px solid #ccc; border-radius: 4px; height:32px; line-height:32px; float:left;"><input type="text" placeholder="输入关键字" /><div class="_search_btn"></div></div> <span style="font-size:14px; color:#6a7685; line-height:32px; padding-left:10px;">美食&nbsp;&nbsp;服务&nbsp;&nbsp;住宿&nbsp;&nbsp;娱乐</span> 
                        <div style="clear:both"></div>
                    </div>
                </div>
            </div>
        </div>