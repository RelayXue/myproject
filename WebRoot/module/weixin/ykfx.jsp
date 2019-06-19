
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>美图欣赏</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" media="all" href="<%=basePath%>module/weixin/css/photo.css"/>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>
		 
		<script type="text/javascript">
	$(function() {
		liuxiaofan();
	});
</script> 

	</head>
	<body>
		<ul id="lxf-box">
			<s:iterator value="buNews_list" var="list">
				<li>
					<div class="photo-img">
						<a href="<%=basePath%>fnews!shareDetails?id=<s:property  value="#list.fuid"/>""><img src="<%=basePath%>upload/B_<s:property  value="#list.img1"/>" />
						</a>
						<h3>
							<a href="<%=basePath%>fnews!shareDetails?id=<s:property  value="#list.fuid"/>""><s:property value="@com.gh.common.SplitChinese@splitStr(fullname,16)" escape="false"/> </a>
						</h3>
					</div>
				</li>
				</s:iterator>
		</ul>
					
		<script type="text/javascript">
		
		
		var indexPage='2';
		$('#lxf-box').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!share', 
			'contentData' : 'pageType=page&&indexPage='+indexPage,  
			'scrollTarget' : $(window),  
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
				liuxiaofan();
			},
			'afterLoad' : function(elementsLoaded) { 
				liuxiaofan();
				indexPage++;
				this.contentData='pageType=page&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#lxf-box').children().size() > 100) { 
					$('#nomoreresults').fadeIn();
					$('#lxf-box').stopScrollPagination();
				}
			}
		});

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};
		
		
	var margin = 10;//这里设置间距
	
	
	function liuxiaofan(){//定义成函数便于调用
		var li=$("li");//这里是区块名称
		var	li_W = li[0].offsetWidth+margin;//取区块的实际宽度（包含间距，这里使用源生的offsetWidth函数，不适用jQuery的width()函数是因为它不能取得实际宽度，例如元素内有pandding就不行了）
		var h=[];//记录区块高度的数组
		var n = 2//窗口的宽度除以区块宽度就是一行能放几个区块
		for(var i = 0;i < li.length;i++) {//有多少个li就循环多少次
			li_H = li[i].offsetHeight;//获取每个li的高度
			if(i < n) {//n是一行最多的li，所以小于n就是第一行了
				h[i]=li_H;//把每个li放到数组里面
				li.eq(i).css("top",0);//第一行的Li的top值为0
				li.eq(i).css("left",i * li_W);//第i个li的左坐标就是i*li的宽度
				}
			else{
				min_H =Math.min.apply(null,h) ;//取得数组中的最小值，区块中高度值最小的那个
				minKey = getarraykey(h, min_H);//最小的值对应的指针
				h[minKey] += li_H+margin ;//加上新高度后更新高度值
				li.eq(i).css("top",min_H+margin);//先得到高度最小的Li，然后把接下来的li放到它的下面
				li.eq(i).css("left",minKey * li_W);	//第i个li的左坐标就是i*li的宽度
			}
			
		}
	}
	/* 使用for in运算返回数组中某一值的对应项数(比如算出最小的高度值是数组里面的第几个) */
	function getarraykey(s, v) {for(k in s) {if(s[k] == v) {return k;}}}
	/*这里一定要用onload，因为图片不加载完就不知道高度值*/
	window.onload = function() {liuxiaofan();};
	/*浏览器窗口改变时也运行函数*/
	window.onresize = function() {liuxiaofan();};
	
	
	
	
	
</script>
	</body>

</html>