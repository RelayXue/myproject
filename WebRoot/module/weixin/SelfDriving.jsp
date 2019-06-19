
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String type=request.getParameter("type");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>自驾攻略</title>
		<%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />		
		--%><link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>
		<script type="text/javascript" src="<%=basePath%>module/phone/js/k_tables.js"></script>
		<style type="text/css">  
		/* dialog */
		.big_dialog {
			position: fixed;
			background-color: #000;
			color: #FFF;
			text-align: center;
			padding: 30px;
			-moz-border-radius: 8px;      /* Gecko browsers */
			-webkit-border-radius: 8px;   /* Webkit browsers */
			border-radius:8px;
			filter:alpha(Opacity=70);
			-moz-opacity:0.7;
			opacity: 0.7;
			display: none;
			font-family: "微软雅黑", "幼圆", "黑体";
			font-size: 30px;
			z-index:99;
		}
		#car-2 ul li{
		font-size:1.8em
		}
		</style>
		<script type="text/javascript">
	$(function() {
	
    	//判断是否在乌镇内
    	if(true){
    		$("div[name='n1']").show();
    	}
    	
		var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!selfDriving', 
			'contentData' : 'pageType=page&&indexPage='+indexPage,  
			'scrollTarget' : $(window),  
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 100) { 
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
		});
		
		function ad(o){
			return "";
		}

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};
	});
	
	
	var cur_x = 0;
	var cur_y = 0;
	var location_requesting = 0;	//是否在获取位置
    if (window.navigator.geolocation) {
        var options = {
            enableHighAccuracy: true
        };
        window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
    } else {
        alert("浏览器不支持html5来获取地理位置信息");
    }
    
    //获得经纬度回调
    function handleSuccess(position){
    	location_requesting = 1;
        // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
        cur_x = position.coords.longitude;
        cur_y = position.coords.latitude;
        
    }
    //获取失败回调
    function handleError(error){location_requesting = 1;}
    
    function toSearchResultsPage(end_address){
	try {
		if(location_requesting == 0){ dialogForBig("正在尝试获取您的位置, 请稍候"); return; }
    	if(cur_x > 0 && cur_y > 0){
	        if(cur_x > 120.429469788641 && cur_x < 120.566373588641 && cur_y > 30.666051366282 && cur_y < 30.802955166282){
		    	dialogForBig("开始查询路线");
				var parms = new Object();
				parms.pos_x = cur_x;
				parms.pos_y = cur_y;
				parms.end_point  = end_address;
				$.post("phone_main_queryRouteMes", parms, function(json) {
					var code = json.code;
					var hint = json.hint;
					try {
						if (code == 1) {
							var o = json.o;
							localStorage.setItem("route_detail_json", JSON.stringify(o)); 
							window.location.href = "<%=basePath%>module/phone/route_detail.jsp";
						}else{
							dialogForBig("未找到目的地");
						}
					} catch (e) {alert(e);	}
				}, "json");
	        }else{
	        	dialogForBig("抱歉～ 由于您的位置不在乌镇内,请使用外地引导");
	        }
    	}else{
    		dialogForBig("抱歉～ 无法获取到您的位置,请打开您手机的定位功能后重新尝试");
    	}
	} catch (e) {alert(e);	}
    }
</script>
		<style>
</style>
	</head>
	<body>
	<div style="background-color: #fff; border-top:1px solid #ccc; border-bottom:1px solid #ccc; padding:20px 15px; font-size: 50px;text-align: center;">乌镇内引导</div>
	
	<div id="car-2" name="n1" style="display: none;">
		<ul>
			<s:iterator value="other_places_route" var="list">
				<li onclick="toSearchResultsPage('<s:property value="#list.remark"/>')"> 
					<div class="read" style="width: 80%;">
						<a href="javascript:void(0)">当前位置 ➞ <s:property value="#list.remark"/></a>
					</div>
					<div class="correction">
						<a href="javascript:void(0)">详情&gt;</a>
					</div>
				</li>
			</s:iterator>
		</ul>
	</div>
	
	<!-- 分割线 -->
	<div style="background-color: #fff; border-top:1px solid #ccc; border-bottom:1px solid #ccc; padding:20px 15px; font-size: 50px;text-align: center; margin-top: 15px;">来乌镇引导</div>
		<div id="car-2">
			<ul>
				<s:iterator value="buNews_list" var="list">
					<li>
						<div class="read">
							<a href="<%=basePath%>fnews!selfDrivingDetails?id=<s:property  value="#list.fuid"/>&type=<s:property  value="#list.type"/>"><s:property  value="#list.fullname"/></a>
						</div>
						<div class="chan"> 
							阅读 <s:property  value="#list.readnum"/>
						</div>
						<div class="shareit">
							<%--<a href="<%=basePath%>fnews!Praise?id=<s:property  value="#list.fuid"/>&re=selfDriving"><img src="<%=basePath%>module/weixin/images/chan.png" /> </a> <s:property   value="#list.praise"/> --%>
						</div>
						<div class="correction">
							<a href="<%=basePath%>fnews!selfDrivingDetails?id=<s:property  value="#list.fuid"/>&type=<s:property  value="#list.type"/>">详情&gt;</a>
						</div>
					</li>
				</s:iterator>
				<div id="content"></div>
			
				
				
			</ul>
		</div>
	</body>

</html>