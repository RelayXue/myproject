<%@ page import="java.util.*" contentType="text/html;charset=UTF-8" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String curl = basePath+"module/phone/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/OwlCarousel-master/owl-carousel/owl.carousel.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=curl %>css/common.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/index2.css">
<link rel="stylesheet" href="<%=curl %>js/OwlCarousel-master/owl-carousel/owl.carousel.css" />
<link rel="stylesheet" href="<%=curl %>js/OwlCarousel-master/owl-carousel/owl.theme.css" />
<title>游在乌镇</title>
<script type="text/javascript">
$(function(){
    $('#owl-demo').owlCarousel({
        items: 1,
        autoPlay: true
    });
});
</script>
</head>

<body>
	<div id="container">
    	<div id="header">
            <div id="search" onclick="window.location.href='<%=curl%>rim.jsp'">
            	<span></span>
                <input type="text" placeholder="如：餐馆、景点、购物...." />
                <!--<a href="#"></a>
            --></div>
            <div id="search_bg"></div>
        </div>
        <div id="main">
        	<div id="top">
            	<div id="top_left">
                	<div id="left_cont">
                    	<a href="javascript:void(0)">
                            <p><img src="<%=basePath %>module/weixin/images/<s:property value="buWeater.image"/>"></p>
                            <p><s:property value="buWeater.temperaturenow"/>℃</p>
                            <p><s:property value="buWeater.changes"/></p>
                            <p><s:property value="buWeater.wind"/></p>
                        </a>
                    </div>
                    <div id="bg"></div>
                </div>
                <div id="top_right">
                	<div id="right_cont">
                        <!--<div class="right_title">特色景点</div>-->
                        <div id="banner">
                            <div class="banner_img">
								<div id="owl-demo" class="owl-carousel">
									<s:generator val="news_list[0].img1" separator="," id="iter1">   
									</s:generator>  
                                	<s:iterator value="#request.iter1" var="ig">
                                		<a class="item"><img src="<%=basePath %>upload/L_<s:property value="ig"/>" style="height: 162px; "></a>
                                	</s:iterator>
                          		</div>
                            </div>
                        </div>
                	</div>
                </div>
            </div>
            <div id="bottom">
            	<ul>
                	<li>
                    	<a href="<%=curl%>search_list.jsp?category=002018&title=餐饮">
                        	<span class="s1"><img src="<%=curl %>images/zms2.png"></span>
                            <span>餐饮</span>
                        </a>
                    </li>
                    <li>
                    	<a href="<%=curl%>search_list.jsp?category=002017&title=住宿">
                        	<span class="s1"><img src="<%=curl %>images/zs2.png"></span>
                            <span>住宿</span>
                        </a>
                    </li>
                    <li>
                    	<a href="<%=curl%>search_list.jsp?category=002016&title=休闲娱乐">
                        	<span class="s1"><img src="<%=curl %>images/qkg2.png"></span>
                            <span>娱乐</span>
                        </a>
                    </li>
                    <li class="gd">
                    	<a href="<%=curl%>rim.jsp" class="nomar">
                        	<span class="s1"><img src="<%=curl %>images/gd.png"></span>
                            <span>更多</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <script type="text/javascript">
        	var a=document.getElementById("left_cont").getElementsByTagName("a")[0];
			a.onclick=function (){
				document.getElementById("bg").style.opacity="0.5";
			}
			var list=document.getElementById("bottom").getElementsByTagName("a");
			for(var i=0;i<list.length;i++){
				list[i].i=i;
				list[i].onclick=function (){
					bg(this.i);
				}
			}
			function bg(index){
				for(var i=0;i<list.length;i++){
					list[i].style.opacity="0.2";
				}
				list[index].style.opacity="0.5";
			}
        </script>
        <div id="footer">
        	<a href="<%=curl %>index.jsp">进入地图<img src="<%=curl %>images/jt16.png"></a>
            <div id="footer_bg"></div>
        </div>
    </div>
</body>
</html>
