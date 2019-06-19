<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="!dataContainer.obj.isEmpty()">
    <s:iterator var="s" value="dataContainer.obj" status="status">
    <div class="_news_detail_item">
          <div class="_title">${s.fullname}</div>
          <!--  
          <div class="_images">
          <div id="owl-example" class="owl-carousel">
             <s:if test="!#s.images.isEmpty()">
		     <s:iterator var="img" value="#s.images" status="status">
             <div class="item">
	             <s:if test="%{type!='004016'}">
	             	 <img src="<%=basePath%>upload/B_${img}" width="100%"/>
	             	</s:if>
	             <s:else>
	            </s:else>
             </div> 
            </s:iterator>
             </s:if>
          </div>
      </div>
      -->
          <div class="_content" onclick="show_detail('${s.fuid}');" style="cursor: pointer;">
          <div align="center">
          <s:if test="#s.img1!=null">
          <img src="<%=basePath%>upload/B_<s:property  value="#s.img1"/>" />
          </s:if>
          </div>
				<br/>
          <s:property value="@com.gh.common.SplitChinese@splitStr(content,80)" escape="false"/> 
          </div>
          <div class="_time"><div style="float: left">发布于<s:date name="#s.createdate" format="yyyy-MM-dd hh:mm:ss" /></div><div style="float: right;cursor: pointer" onclick="show_detail('${s.fuid}');">查看详情></div></div>
          <div class="_time"></div>
          <div style="padding:15px; font-size:15px; color:#666; overflow:hidden; border-top:1px solid #ccc;">
        <!-- 
        <div class="fleft" style="overflow:hidden">
              <div class="fleft _eye_btn">${s.readnum}</div>
              <div id="praiseDiv_${s.fuid}" class="fleft _point_like_btn" style="margin-left:10px;cursor: pointer;" onclick="praiseClick('news','${s.fuid}');">${s.praise}</div>
            </div>
         -->
        <!-- 
        <div class="fright">分享于</div>
         -->
      </div>
        </div>
        <script type="text/javascript">
    //$('#'+"${s.fuid}").owlCarousel({
	//	items: 1,
	//	autoPlay: true
	//});
    </script>
    </s:iterator>
    </s:if>
    