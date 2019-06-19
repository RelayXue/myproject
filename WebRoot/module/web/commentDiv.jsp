<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="!comments.isEmpty()">
    <s:iterator var="s" value="comments" status="status">
    <div class="_row">
                        <div style="height:32px; line-height:35px; overflow:hidden;" class="font_organize f_18">
                           	<span class="fleft">评分：</span>
                        	<span class="_star fleft" style="width:32px; height:32px;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	<span class="fleft">${s.score}&nbsp;&nbsp;&nbsp;&nbsp;人均消费：&nbsp;&nbsp;￥${s.capita}</span>
                        </div>
                        <div class="f_16" style="padding:10px 5px;">${s.description }</div>
                        <div style="line-height:38px;">
                        	<div id="commentPraiseDiv_${s.fuid}" class="_y_praise fleft" style=" background-repeat:no-repeat; padding-left:34px;cursor: pointer; " onclick="commentPraiseClick('comment','${s.fuid}');">${s.praise}</div>
                            <div class="font_blackgrey fright">发表于 <s:date name="#s.createdate" format="yyyy-MM-dd HH:mm:ss" /></div>
                        </div>
                    </div>
    </s:iterator>
    </s:if>
    