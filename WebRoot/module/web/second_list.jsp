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
<title>无标题文档</title>
<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript">
            var dragging = false;
            var iY;
			var iOffsetY;
			var s = 0;
			var mi = 0;	//最小层级
			var ma = 10;	//最大层级
			var interval = 11;	//滚动间隔
			$(function(){
				$("._zoom_bar_line").height(interval * (ma+2));
				$("#_zoom_bar_btn").mousedown(function(e) {
					dragging = true;
					iY = e.clientY;
					iOffsetY = this.offsetTop;
					$("#v").html(iY+","+this.offsetTop);
					this.setCapture && this.setCapture();
					return false;
				});
				document.onmousemove = function(e) {
					if (dragging && s <= (ma*interval) && s >= (mi*interval)) {
						var e = e || window.event;
						var oY = e.clientY - iY + iOffsetY;
						setZoomBar(oY);
						return false;
					}
					
				};
				$(document).mouseup(function(e) {
					dragging = false;
					$("#v").html("-1");
				})
				
				setZoomBar(interval*3)
			})

			function runZoomBarBtn(i){
				var oY = parseInt($("#_zoom_bar_btn").css("top"));
				if(i == 1){
					oY += interval;
				}else if(i == 0){
					oY -= interval;
				}
				setZoomBar(oY);
			}
			
			function setZoomBar(oY){
				if (oY <= (ma*interval) && oY >= (mi*interval)) {
					if(oY < (mi*interval)) oY = (mi*interval);
					if(oY > (ma*interval)) oY = (ma*interval);
					if(oY % interval > 0) oY = parseInt(oY / interval) * interval;
					$("#_zoom_bar_btn").css({"top":oY + "px"});
					s = oY;
					zoomBarScrollListener(parseInt(oY / interval));
				}
			}
			
			function zoomBarScrollListener(level){
				
			}
 
		 //地图标签内容切换
		 function setMapTab(i){
			 $("._tab1, ._tab2, ._tab3").removeClass("_hover");
			 $("._tabc").hide();
			 $("._tab"+i).addClass("_hover");
			 $("#_tabc"+i).show();
		 }
    </script>

</head>

<body>
<!-- 2014-11-03 新增 -->
                <div id="_ipage">
                	<div class="" style=" background-color:#ddddd3; border-radius:0 8px 0 0; text-align:center; color:#717699; padding:8px 0; font-size:18px; background-position:85px center; background-image:url(images/m_icon4_03.png); background-repeat:no-repeat;">罗列</div>
                    <div class="listB">
                        <div class="row right_icon icon_in right_icon_bb ltop">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                        <div class="row right_icon icon_in right_icon_bb">
                            <div class="fleft" style="background-image:url(images/m_icon3_03.png); background-repeat:no-repeat; background-position:center; width:25px;  display:block; height:31px; text-align:center; line-height:18px; margin-right:10px; color:#FFF;"></div>
                            <div class="fleft">西彭景点</div>
                            <div style="clear:both"></div>
                        </div>
                    </div>
                </div>
                <!-- end -->
</body>
</html>