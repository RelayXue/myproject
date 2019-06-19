<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script language="JavaScript" src="<%=basePath%>js/FusionCharts/JSClass/FusionCharts.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>

		<script type="text/javascript">
		//默认图表高
		var egr_height=360;
		//
		var xml = escape("<%=basePath%>buEvent!CountAnalysis");
		
		//根据Box的宽度得到其百分比.bat为百分比
		function getWidthInBox(bat){
			return $(".panel-container").width()*bat-10;
		}

		 //get xml
		 function loadXML(data, pie, column, width1, width2, height){
		 	var name1 = Math.random()+"1";
		 	var name2 = Math.random()+"2";
		 	var chart = new FusionCharts("<%=basePath%>js/FusionCharts/Charts/Pie2D.swf", name1, width1, height, "0", "0");
		    chart.setDataXML(data);
			chart.render(pie);
			var chart = new FusionCharts("<%=basePath%>js/FusionCharts/Charts/Column2D.swf", name2, width2, height, "0", "0");
		    chart.setDataXML(data);
			chart.render(column);
		 }
		
		 jQuery(document).ready(function($) {
		 	serach();
		});
		
		function serach(){
			var time1=$("#time1").val();
			var time2=$("#time2").val();
			$.get(xml,{type:"bm", time1:time1, time2:time2, ss:Math.random()}, function(data,status){
				loadXML(data, "affairsBmdiv", "affairsBmdivCol", getWidthInBox(0.4), getWidthInBox(0.6), egr_height);
	 		 });
		}
</script>


	</head>

	<body>
	<div class="rightnav">
			<a href="#" class="active">事件分析</a>
		</div>
	<div id="box">
			<div class="sah_cdn well" style="margin-top: 0">
				<div class="sah_con">
					<div class="rsearch">
						<span class="info">按时间搜索:</span>
						<input id="time1" style="width: 80px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="inp" />  
							<input id="time2" style="width: 80px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="inp"/>
						<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
					</div>
				</div>
			</div>
<br/>
<br/>
 <div class='panel-container'>
  <div id="tabs1">
  	<div class="lineBox" >
		<div class="ictx">
		    <div class="tbe r_line">
		    <span   style="font-size: 14px">&nbsp;&nbsp;【事件数量分析】</span><hr/>
		    	<div id="affairsBmdivCol"></div>
		    </div>
		    <div class="tbe">
		    <span style="font-size: 14px">&nbsp;&nbsp;【事件比例统计】</span><hr/>
		    	<div id="affairsBmdiv"></div>
		    </div>
		    <div style="clear: both;" mce_style="clear: both;"></div>
		</div>
  	</div>
  </div>
  	</div>
  </div>

	
	</body>
</html>
