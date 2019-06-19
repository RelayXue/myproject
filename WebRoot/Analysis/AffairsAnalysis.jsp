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
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script language="JavaScript" src="<%=basePath%>js/FusionCharts/JSClass/FusionCharts.js"></script>
		<script type="text/javascript">
		//默认图表高
		var egr_height=560;
		//
		var xml = escape("<%=basePath%>Analysis!AffairsAnalysis");
		//根据Box的宽度得到其百分比.bat为百分比
		function getWidthInBox(bat){
			return $(".panel-container").width()*bat-10;
		}
		
		 //get xml
		 function loadXML(data, pie, width1,  height){
		 	var name1 = Math.random()+"1";
		 	var chart = new FusionCharts("<%=basePath%>js/FusionCharts/Charts/Column3D.swf", name1, width1, height, "0", "0");
		    chart.setDataXML(data);
			chart.render(pie);
		 }
		
		 jQuery(document).ready(function($) {
				var type="${type }";
				if(type=='null'||type.length==0){
					type="015";
				} 	
				serach();
		});
		
		function serach(){
			var va=$("select[name='affairs.type']");
			var type="";
			va.each(function (i){
				if($(this).val()!=-1){
					type=$(this).val();
				}
			});
			var time1=$("#time1").val();
			$.get(xml,{code: type, time1: time1, ss:Math.random()}, function(data,status){
				loadXML(data, "affairsYear",  1200, egr_height);
	 		 });
		}
</script>


	</head>

	<body>
	
		    	<div id="affairsYear"></div>
	</body>
</html>
