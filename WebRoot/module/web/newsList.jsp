<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>乌镇国际旅游区新鲜速递</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Resource/Theme/StyleDefault/style.css"/>
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.css" />
<link rel="stylesheet" href="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.theme.css" />  
<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TTuJing.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TMapType.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TJSoft.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/layer-v1.8.5/layer.min.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/OwlCarousel-master/owl-carousel/owl.carousel.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
    <script type="text/javascript">
    var types = ['004014','004015','004016','004017'];
    var type = '${type}';
    var pathh = "<%=basePath%>";
    var p = '${page}';
    var total = '${totalPage}';
    
		$(function(){
			$("._menu_btn").click(function(){
				var css = $(this).attr("class");
				if(css.indexOf("_hover") < 0){
					$("._menu_btn").removeClass("_hover");
					$(this).addClass("_hover");
					menuBtnClickListener($(this).index());
				}
			});	
			
			//默认选择按钮
			//$("._menu_btn").eq(0).click();
			
			
			//$('.owl-carousel').owlCarousel({
			//	items: 1,
			//	autoPlay: true
			//});
			
			
			//实时路况播报
			$("#d_show_"+type).addClass("_hover");
			$("#d_show_"+type).css("cursor","default");
			$("#d_show_"+type).attr("onclick","");
			divClickInit(type);
		});
		
		function divClickInit(type){
			for(var i=0;i<types.length;i++){
				if(types[i]!=type){
					$("#d_show_"+types[i]).removeClass("_hover");
					$("#d_show_"+types[i]).css("cursor","pointer");
				}
			}
		}
		
		//菜单点击触发事件
		function menuBtnClickListener(index){
			
		}
		
		function redirectList(url){
			window.location.href = url;
		}
		
		function pageScroll(){
			$('html,body').animate({scrollTop:0},1000);
		}
		
		function nextPage(){
			p++;
			if(total==p){
				$("#nextBtn").css("display","none");
			}
			var data = "page="+p+"&flag=1";
			var loadi = layer.load(0);
			$.ajax({
				  type: 'POST',
				  async:false,
				  url: "web/search_newsList",
				  data: data,
				  dataType: 'html',
				  success: function(data){
					  $("#nextPageDiv").append(data);
					  layer.close(loadi);
				  }
			 });
		}
		
		function praiseClick(type,id){
			 var val = $("#praiseDiv_"+id).html();
			 if(val.length!=0){
				 $("#praiseDiv_"+id).html(parseInt(val)+1);
				 var data = "q="+$("#praiseDiv_"+id).html()+"&type="+type+"&id="+id;
				 $.ajax({
					  type: 'POST',
					  async:false,
					  url: "web/search_praiseClick",
					  data: data,
					  dataType: 'html',
					  success: function(data){
					  }
				 });
			 }
		 }
		
		//综合查询
		function searchAll(){
			var skey = $("#searchText").val();
			window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
		}
		
		function show_detail(id){
			if(id.length==0||type.length==0){
				alert("参数传递出错，请刷新页面！");
				return;
			}
			window.open("search_newsDetail?id="+id);
		}
	
    </script>
    </head>

    <body>
<div class="_top">
	<div class="_grop">
    	<div class="_logo"></div>
        <div class="_box">
        	<div class="_name1" style="cursor: pointer;" onclick="redirectList('index')"></div>
            <div class="_ms">
            	<div class="_search_box">
               		<input id="searchText" type="text" value=""/>
                    <div class="_search_btn" style="cursor:pointer;" onclick="searchAll();"></div>
                </div>
                <div class="_menu"><span onclick="redirectList('search_scenicList');" style="cursor: pointer;">景区</span>|<span onclick="redirectList('search_typeSearch?type=002018');" style="cursor: pointer;">餐饮</span>|<span onclick="redirectList('search_typeSearch?type=002017');" style="cursor: pointer;">住宿</span>|<span onclick="redirectList('search_typeSearch?type=002016');" style="cursor: pointer;">娱乐</span>|<span onclick="redirectList('search_typeSearch?type=002015');" style="cursor: pointer;">购物</span>|<span onclick="redirectList('search_trafficPlayList');" style="cursor: pointer;">交通播报</span>|<span onclick="redirectList('search_newsList');" style="cursor: pointer;">新鲜速递</span></div>
            </div>
            <div class="_weather">
            	<img src="<%=basePath%>module/weixin/images/weather/${bw.image}" width="65" height="50"/>
                <div class="_tt1">${bw.temperaturenow}℃ </div>
                <div class="_tt2">${bw.changes}</div>
            </div>
        </div>
    </div>
    <div class="_tllp"></div>
</div>
<div style="width:980px; margin:0 auto;">
      <div class="_body_top_menu" style="height: 35px;">
    <div style="background-image:url(<%=basePath%>module/web/images/21_03.png); background-position:0 center; background-repeat:no-repeat; padding-left:36px; height:32px; position:absolute; left:15px; top:12px;" ><span style="font-size:18px; line-height:35px;">新鲜速递</span></div>
  </div>
      
      <!-- 详细列表 -->
      <div class="_news_detail_list">
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
    </s:iterator>
    </s:if>
    <div id="nextPageDiv"></div> 
  </div>
      <!-- 分页 -->
    <s:if test="totalPage>page">
    <div id="nextBtn" class="_next_btn" onclick="nextPage();" style="cursor: pointer;">展开更多</div>
    </s:if>
    
    </div>
<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>
<!-- 回首页 -->
<div class="_totop" onclick="pageScroll();" style="cursor: pointer;">回顶部</div>

</body>
</html>