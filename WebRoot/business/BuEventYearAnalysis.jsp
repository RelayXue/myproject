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
		<script   src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	  <script language="JavaScript" src="<%=basePath%>js/FusionCharts/JSClass/FusionCharts.js"></script>
		<script type="text/javascript">
		//默认图表高
		var egr_height=360;
		//
		var xml = escape("<%=basePath%>buEvent!YearAnalysis");
		//根据Box的宽度得到其百分比.bat为百分比
		function getWidthInBox(bat){
			return $(".panel-container").width()*bat-10;
		}
		
		 //get xml
		 function loadXML(data, pie, column, width1, width2, height){
		 	var name1 = Math.random()+"1";
		 	var name2 = Math.random()+"2";
		 	var chart = new FusionCharts("<%=basePath%>js/FusionCharts/Charts/MSColumnLine3D.swf", name1, width1, height, "0", "0");
		    chart.setDataXML(data);
			chart.render(pie);
			var chart = new FusionCharts("<%=basePath%>js/FusionCharts/Charts/MSLine.swf", name2, width2, height, "0", "0");
		    chart.setDataXML(data);
			chart.render(column);
		 }
		
		 jQuery(document).ready(function($) {
				serach();
		});
		
		function serach(){
			var type="";
			var time1=$("#time1").val();
			$.get(xml,{code: type, time1: time1, ss:Math.random()}, function(data,status){
				loadXML(data, "affairsYear", "affairsYearLine", getWidthInBox(1), getWidthInBox(1), egr_height);
	 		 });
		}
</script>
 

	</head>

	<body style="overflow-x:hidden; overflow-y:auto;margin:0 0 0 0;padding:0 0 0 0">
	
	<div id="box">
		<div class="rightnav">
			<a href="#" class="active">事件分析</a>
		</div>
	<div id="box">
			<div class="sah_cdn well" style="margin-top: 0">
				<div class="sah_con">
					<div class="rsearch">
						<span class="info">按年份搜索:</span>
						<input id="time1" style="width: 80px;" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})" class="inp" />  
						<a href="javascript:serach()" class="btn" title="搜索">搜索</a>
					</div>
				</div>
			</div>
<br/>
<br/>

<div id="tab-container" class='tab-container'>
 <div class='panel-container'>
  <div id="tabs1-A">
  	<div class="lineBox" >
		<div class="ictx b_line">
		    <div class="tbe">
		    <span class="itit"><div class="chart line" ></div>&nbsp;&nbsp;【事务量柱状图】</span><hr/>
		    	<div id="affairsYear"></div>
		    </div>
		    <div style="clear: both;" mce_style="clear: both;"></div>
		</div>
  	</div>
  </div>
  <div id="tabs1-B">
  	<div class="lineBox" >
		<div class="ictx b_line">
		    <div class="tbe">
		    <span class="itit"><div class="chart line" ></div>&nbsp;&nbsp;【事务量趋势图】</span><hr/>
		    	<div id="affairsYearLine"></div>
		    </div>
		    <div style="clear: both;" mce_style="clear: both;"></div>
		</div>
  	</div>
  </div>
 </div>
</div>

</div>

</div>
	 
	</body>
</html>
