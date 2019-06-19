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
<title>乌镇国际旅游区信息详情</title>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/star.css"/>
    <script type="text/javascript">
    function prevBtn(){
    	$(".owl-carousel").data('owlCarousel').prev();
    }
    
    function nextBtn(){
    	$(".owl-carousel").data('owlCarousel').next();
    }
		function setTab(i){
			$("._tab a").removeClass("_hover");
			$("._tab a").eq(i-1).addClass("_hover");
			$("._de_list ._box").hide();
			$("._de_list #_box"+i).show();
		}
		$(function (){
			setTab(1);
			fullView();
			$('.owl-carousel').owlCarousel({
				items: 1,
				autoPlay: true
			});
			
			var data = "type=${type}&id=${dataContainer.obj.fuid}";
			$.ajax({
				  type: 'POST',
				  async:false,
				  url: "web/search_likeToGo",
				  data: data,
				  dataType: 'html',
				  success: function(data){
					  $("#likeToGo").append(data);
				  }
			 });
			
			$.ajax({
				  type: 'POST',
				  async:false,
				  url: "web/search_commentList",
				  data: data,
				  dataType: 'html',
				  success: function(data){
					  $("#commentListDiv").append(data);
				  }
			 });
			
			
			$('.star_ul a').hover(function(){
				$('.star_ul a').each(function(i){
					$("#level"+(i+1)).removeClass('active-star');
				});
				$(this).addClass('active-star');
				$('.s_result').html($(this).attr('title'));
			},function(){
				$(this).removeClass('active-star');
				$('.s_result').html('请打分');
			});
			
			$('.star_ul').hover(function(){},function(){
				$('.star_ul a').each(function(i){
					var t = $("#resultComment").val();
					if(t!=0&&t!=(i+1)){
						$("#level"+(i+1)).removeClass('active-star');
					}
				});
				$("#level"+$("#resultComment").val()).addClass('active-star');
				$('.s_result').html($("#level"+$("#resultComment").val()).attr('title'));
			});

			
			$('.star_ul a').click(function(){
				$(this).addClass('active-star');
				$("#resultComment").val($(this).attr('name'));
			});
		});
		
		 function fullView(){
			 var max_width = 980;
			 var max_height = 524;
			$("._top_menu").width(max_width-2*72);
			$("._left_menu").height(max_height-2*72);
			$("._right_menu").height(max_height-2*72);
		 }
		 
		 function show_map(name,x,y){
				if(name.length==0||x.length==0||y.length==0){
					alert("参数传递出错，请刷新页面！");
					return;
				}
				var q = encodeURIComponent(encodeURIComponent(name+"|"+x+","+y+"|oe_l,oe_h"));
				window.location.href = "index?q="+q;
			}
		 function show_point(x,y){
			 if(x.length==0||y.length==0){
				 return;
			 }
			 var data = x+"|"+y;
			 var pageii = $.layer({
				    type: 1,
				    title: false,
				    area: ['700px', '400px'],
				    shift: 'top', //从左动画弹出
				    page: {
				        html: "<iframe id='countIframe' frameborder='0' style='height: 400px;z-index:4000; width: 700px; font-size: 12px; line-height: 20px;' src='search_showMap?xy="+data+"'></iframe>"
				    }
				});
		 }
		 
		 function show_detail(id,type){
				if(id.length==0||type.length==0){
					alert("参数传递出错，请刷新页面！");
					return;
				}
				window.open("search_showDetail?id="+id+"&type="+type);
			}
		 function pageScroll(){
				$('html,body').animate({scrollTop:0},1000);
			}
		 
		 function redirectList(url){
				window.location.href = url;
			}
		 
		 function praiseClick(type,id){
			 var val = $("#praiseDiv").html();
			 if(val.length!=0){
				 $("#praiseDiv").html(parseInt(val)+1);
				 var data = "q="+$("#praiseDiv").html()+"&type="+type+"&id="+id;
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
		 
		 function commentPraiseClick(type,id){
			 var val = $("#commentPraiseDiv_"+id).html();
			 if(val.length!=0){
				 $("#commentPraiseDiv_"+id).html(parseInt(val)+1);
				 var data = "q="+$("#commentPraiseDiv_"+id).html()+"&type="+type+"&id="+id;
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
		 
		 function commentSubmit(){
			 var level = $("#resultComment").val();
			 if(level==0){
				 alert("请打分");
				 return;
			 }
			 var money = $("#commentMoney").val();
			 if(isNaN(money)){
				 alert("人均消费应为数字!");
				 return;
			 }
			 var data = "q="+$("#commentContent").val()+"&flag="+money+"&skey="+level+"&id=${dataContainer.obj.fuid}&type=${type}";
			 $.ajax({
				  type: 'POST',
				  async:false,
				  url: "web/search_saveComment",
				  data: data,
				  dataType: 'html',
				  success: function(data){
					  layer.msg('评论成功,审核中！', 1,1);
					  //$("#commentListDiv").html("");
					  //$("#commentListDiv").append(data);
					  //清空数据
					  $("#resultComment").val("0");
					  $("#commentMoney").val("");
					  $("#commentContent").val("");
					  $('.star_ul a').each(function(i){
							$("#level"+(i+1)).removeClass('active-star');
						});
					  $('.s_result').html('请打分');
				  }
			 });
		 }
		 
		//综合查询
		 function searchAll(){
		 	var skey = $("#searchText").val();
		 	window.location.href = "search_searchAll?skey="+encodeURIComponent(encodeURIComponent(skey));
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
<div class="_body">
	<div class="_canvas" style="height: 524px">
    	<!-- 底部背景 -->
        <div class="" style=" width:72px; height:72px; top:0; left:0; background-image:url(<%=basePath%>module/web/images/border1_03.jpg); z-index:1; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; top:0; right:0; background-image:url(<%=basePath%>module/web/images/border1_07.jpg); z-index:1; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; bottom:0; left:0; background-image:url(<%=basePath%>module/web/images/border1_16.jpg); z-index:1; position:absolute;"></div>
        <div class="" style=" width:72px; height:72px; bottom:0; right:0; background-image:url(<%=basePath%>module/web/images/border1_18.jpg); z-index:1; position:absolute;"></div>
        <div class="_top_menu"></div>
        <div class="_left_menu"></div>
        <div class="_right_menu"></div>
        <!-- end -->
		<div class="_path_box"><span style="cursor: pointer;" onclick="redirectList('index')">首页</span> > <s:if test="type==4"><span></s:if><s:else><span style="cursor: pointer;" onclick="redirectList('search_typeSearch?type=${type}')"></s:else>${skey}</span></div>
        <div class="_title">${dataContainer.obj.fullname }<span class="font_blued">【${skey}】</span></div>
        <div class="_right_icos">
            <!-- 
        	<div class="_star font_organize" style="background-position:0 7px;">${dataContainer.obj.star }</div>
             -->
            <div class="_y_eye font_organize">${dataContainer.obj.examine }</div>
            <div id="praiseDiv" onclick="praiseClick('${type}','${dataContainer.obj.fuid }');" class="_y_praise font_organize" style="background-position:3px 7px;cursor: pointer;">${dataContainer.obj.praise }</div>
            <div class="_pos" style="background-position:5px center;cursor: pointer;" onclick="show_map('${dataContainer.obj.fullname}','${dataContainer.obj.fx }','${dataContainer.obj.fy }');">去这里</div>
            <div class="_pos_area" style="background-position:0px center;cursor: pointer;" onclick="show_point('${dataContainer.obj.fx }','${dataContainer.obj.fy }');">看地图</div>
        </div>
        <!-- 
        <div class="_right_menu">
            <span class="_ico3">分享</span>
        </div>
         -->
        <div style="width:835px; height:380px; position:absolute; left:72px; top:73px;">
          <!-- <img src="module/web/images/bg_08.jpg" /> -->
          <div id="owl-example" class="owl-carousel">
             <s:if test="!dataContainer.obj.images.isEmpty()">
		     <s:iterator var="img" value="dataContainer.obj.images" status="status">
             <div class="item">
               <img src="<%=basePath%>upload/B_${img}" width="836" height="380"/>
             </div> 
             </s:iterator>
             </s:if>
          </div>
        
        
        </div>
        <div style="position:absolute; background-image:url(<%=basePath%>module/web/images/d_icon2_03.png); background-repeat:no-repeat; width:26px; height:45px; left:32px; top:45%;cursor:pointer;" onclick="prevBtn();"></div>
        <div style="position:absolute; background-image:url(<%=basePath%>module/web/images/d_icon2_05.png); background-repeat:no-repeat; width:26px; height:45px; right:32px; top:45%;cursor:pointer;" onclick="nextBtn();"></div>
        <!-- 
        <div class="_simgs">
        	<div style="width:360px; margin:0 auto; overflow:hidden;">
            	<div class="_iim" style="background-image:url(<%=basePath%>module/web/images/bg_05.jpg)"></div>
                <div class="_iim" style="background-image:url(<%=basePath%>module/web/images/bg_05.jpg)"></div>
                <div class="_iim _hover" style="background-image:url(<%=basePath%>module/web/images/bg_05.jpg)"></div>
                <div class="_iim" style="background-image:url(<%=basePath%>module/web/images/bg_05.jpg)"></div>
                <div class="_iim" style="background-image:url(<%=basePath%>module/web/images/bg_05.jpg)"></div>
            </div>
        </div>
         -->
    </div>
    <!-- 详情描述列表 -->
    <div class="_de_list">
    	<div class="_tab">
    		<div style="padding-left:25px;">
            <a onclick="setTab(1);">详情描述</a><span>|</span><!--  <a onclick="setTab(2)">更多火锅</a><span>|</span><a onclick="setTab(3)">本店美食</a><span>|</span>--><a onclick="setTab(2);">游客点评</a><div style="clear:both;"></div></div>
            </div>
            <!-- box1 -->
            <div class="_box" id="_box1" style="display:none; padding:15px 25px;">
            ${dataContainer.obj.introduction }
            </div>
            <!-- box4 -->
            <div class="_box" id="_box2" style="display:none">
				<div id="commentListDiv" class="_assess">
                </div>
                <div class="_assess_from">
                	<div class="_iot" style=" float:left;">点评</div>
                    <div class="starbox" style="margin:15px; float:left; width:180px; border-right:1px solid #a3a6a7;">
	<ul class="star_ul fl">
		<li><a id="level1" class="star1" title="很差" name="1"></a></li>
		<li><a id="level2" class="star2" title="差" name="2"></a></li>
		<li><a id="level3" class="star3" title="还行" name="3"></a></li>
		<li><a id="level4" class="star4" title="好" name="4"></a></li>
		<li><a id="level5" class="star5" title="很好" name="5"></a></li>
	</ul>
	<div style="clear:both"></div>
                        <br />
	<div class="s_result fl" style="background-color:#FFF; width:130px; line-height:30px; margin:0 auto; text-align:center; border:1px solid #a0a0a0;">请打分</div>
	<input id="resultComment" type="hidden" value="0"/>
</div>
                    <div style="margin:15px 8px ; float:left;">
                    	<textarea id="commentContent" style="width:320px; height:80px;" class="fleft"></textarea>
                        <div class="fleft" style="margin-left:8px; padding-top:20px;">
                             人均消费：<br />
                       		<input id="commentMoney" type="text" style="width:115px; height:30px; line-height:30px; font-size:18px; padding-top:10px;" />
                        </div>
                    </div>
                    <div class="_submit_btn" style="cursor: pointer;" onclick="commentSubmit();">提交</div>
                </div>
            </div>
        </div>
</div>
<div class="_d_list2">
	<div style="width:980px; margin:0 auto;">
    	<div style="font-size:20px; height:40px; line-height:40px; padding-left:25px; border-bottom:1px solid #969696;">大家都喜欢去</div>
        <div id="likeToGo" class="_more">
        </div>
        
        <!-- category list
        <div class="_category_list">
        	<div class="_title">——住宿——</div>
            <div class="_bod">
            	<div class="_row ltop">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
            </div>
        </div>
        <div class="_category_list">
        	<div class="_title">——特色街——</div>
            <div class="_bod">
            	<div class="_row ltop">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
            </div>
        </div>
        <div class="_category_list">
        	<div class="_title">—诚信商家—</div>
            <div class="_bod">
            	<div class="_row ltop">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
            </div>
        </div>
        <div class="_category_list">
        	<div class="_title">——特产——</div>
            <div class="_bod">
            	<div class="_row ltop">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
                <div class="_row">酒店</div>
            </div>
        </div>
         -->
        <div style="clear:both"></div>
    </div>
</div>
<div class="_bottom">
<span style="color:#969696">————————————————</span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="redirectList('search_wuzhen');" style="cursor: pointer;">乌镇国际旅游区</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_contact');" style="cursor: pointer;">联系我们</span>&nbsp;&nbsp;·&nbsp;&nbsp;<span onclick="redirectList('search_link');" style="cursor: pointer;">友情链接</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#969696">————————————————</span>
</div>

<!-- 回首页 -->
<div class="_totop" onclick="pageScroll();" style="cursor: pointer;">回顶部</div>
</body>
</html>