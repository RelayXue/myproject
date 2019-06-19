<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>乌镇国际旅游区驾驶路线</title>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/jquery.autocomplete.css" />
        <script type='text/javascript' src='<%=basePath%>module/web/js/jquery.autocomplete.min.js' charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
		<script type="text/javascript" src="<%=basePath%>module/web/js/TMap.js"></script>
		<script type="text/javascript" src="<%=basePath%>module/web/js/layer-v1.8.5/layer.min.js"></script>
	</head>
	<script type="text/javascript">
	var txthidden = "";
	$(document).ready(function(){
		$("#startText").autocomplete("web/search_autoCompleteData", {
			minChars: 0,
			max: 10,
			autoFill: false,
			mustMatch: false,
			matchContains: true,
			selectFirst:false,
			scrollHeight: 220,
				formatItem: function(row, i,max) {
					$("#startHiddTxt").val("");
					var obj =eval("(" + row + ")"); //转换成js对象 
					return obj.name;
					}, 
					formatResult: function(row) { 
					var obj =eval("(" + row + ")"); //转换成js对象 
					return obj.name;
					},
					formatMatch:function(row){
						var obj =eval("(" + row + ")"); //转换成js对象
						return obj.name;
					}
		});

		$("#startText").result(function(event, data, formatted) {
			var obj =eval("(" + data + ")");
			$("#startHiddTxt").val(obj.x+","+obj.y);
		});
		
		$("#endText").autocomplete("web/search_autoCompleteData", {
			minChars: 0,
			max: 10,
			autoFill: false,
			mustMatch: false,
			matchContains: true,
			selectFirst:false,
			scrollHeight: 220,
				formatItem: function(row, i,max) { 
					$("#endHiddTxt").val("");
					var obj =eval("(" + row + ")"); //转换成js对象 
					if(obj.name.length>0){
						return obj.name;
					}
					}, 
					formatResult: function(row) { 
					var obj =eval("(" + row + ")"); //转换成js对象 
					if(obj.name.length>0){
						return obj.name;
					}
					},
					formatMatch:function(row){
						var obj =eval("(" + row + ")"); //转换成js对象 
						if(obj.name.length>0){
							return obj.name;
						}
					}
		});

		$("#endText").result(function(event, data, formatted) {
			var obj =eval("(" + data + ")");
			$("#endHiddTxt").val(obj.x+","+obj.y);
		});
		
		var name = '${skey}';
		var xy = '${xy}';
		var xy1 = '${xy1}';
		var name1 = '${name1}';
		var type = '${type}';
		if(type=='1'){
			$("#startText").val(name);
			$("#startHiddTxt").val(xy);
			$("#endText").val(name1);
			$("#endHiddTxt").val(xy1);
			searchCarLine();
		}else if(type=='2'){
			$("#startText").val(name1);
			$("#startHiddTxt").val(xy1);
			$("#endText").val(name);
			$("#endHiddTxt").val(xy);
			searchCarLine();
		}else{
			if(name.length!=0&&xy.length!=0){
				$("#endText").val(name);
				$("#endHiddTxt").val(xy);
			}
		}
	});
	
	function startBlur(){
		var val = $("#startHiddTxt").val();
		if(val.length==0){
			$("#startText").val("");
		}
	}
	
	function endBlur(){
		var val = $("#endHiddTxt").val();
		if(val.length==0){
			$("#endText").val("");
		}
	}
	
	function changeText(){
		var temp = $("#startText").val();
		$("#startText").val($("#endText").val());
		$("#endText").val(temp);
		temp = $("#startHiddTxt").val();
		$("#startHiddTxt").val($("#endHiddTxt").val());
		$("#endHiddTxt").val(temp);
	}
	
	function search(){
		var start = $("#startHiddTxt").val();
		var end = $("#endHiddTxt").val();
		if(start.length==0||end.length==0){
			 window.parent.showlayer('请输入起点或终点！', 2,2);
			return;
		}
		
	}
	
	function clearHtmlTxt(){
		$("#startText").val("");
		$("#endText").val("");
		$("#startHiddTxt").val("");
		$("#endHiddTxt").val("");
	}
	
	function setText(start,end,total,time){
		$("#start").html(start);
	    $("#end").html(end);
	    $("#total_length").html(total);
	    $("#total_time").html(time);
	}
	
	function setResultText(txt){
		if(txt.length==0){
			$("#roadText").html("");
		}else{
			$("#roadText").append(txt);
		}
	}
	
	function getResultDisplay(){
		return $("#resultText").css("display");
	}
	
	function setResultDisplay(){
		$("#resultText").css("display","block");
	}
	
</script>
	<body style="background-image: none; background-color: #F8F8EC; padding-top: 10px;">
	<!-- 
		<div class="font_organize" style="margin: 10px; font-size: 20px;">
			驾驶路线查询
		</div>
	 -->
		<!-- 查询框 -->
		<div class="_routes_query_box">
			<div class="_ref" style="cursor: pointer;"
				onclick="changeText();"></div>
			<div class="fleft">
				<div
					style="height: 51px; border-bottom: 1px solid #ccc;">
					<div
						style="background-color: #64a489; border-radius: 4px; text-align: center; width: 32px; height: 32px; line-height: 32px; color: #FFFFFF; float: left; margin: 10px;">
						起
					</div>
					<input type="text" id="startText"
						style="width: 200px; height: 25px; line-height: 25px; margin: 12px 0; border: none"
						class="f_16" placeholder="输入起点" onblur="startBlur();"/>
					<input type="hidden" id="startHiddTxt"/>
				</div>
				<div style="height: 51px;">
					<div
						style="background-color: #717699; border-radius: 4px; text-align: center; width: 32px; height: 32px; line-height: 32px; color: #FFFFFF; float: left; margin: 10px;">
						终
					</div>
					<input type="text" id="endText"
						style="width: 200px; height: 25px; line-height: 25px; margin: 12px 0; border: none"
						class="f_16" placeholder="输入终点" onblur="endBlur();"/>
					<input type="hidden" id="endHiddTxt"/>
				</div>
			</div>
			<div class="_search" style="cursor: pointer;" onclick="searchCarLine();"></div>
		</div>
		<!-- end-->
		<!-- 查询结果 -->
		<div class="_texi_routes_query_box" id="resultText" style="display: none">
			<div style="padding: 8px;" class=" f_14 font_blackgrey">
				总路程：约<span id="total_time">0</span>分钟 | <span id="total_length">0</span>公里
			</div>
			<div style="border-top: 1px solid #ccc; overflow: hidden">
				<div
					style="background-color: #64a489; border-radius: 4px; text-align: center; width: 32px; height: 32px; line-height: 32px; color: #FFFFFF; float: left; margin: 10px;">
					起
				</div>
				<span style="line-height: 50px;" class="weight" id="start">起点</span>
			</div>
			<div class="_texi_routes_query_item">
				<div id="roadText" class="_child_items" style="display: block;">
					<div class="f_14">
						未找到相关路径
					</div>
				</div>
			</div>
			<div style="border-top: 1px solid #ccc; overflow: hidden">
				<div
					style="background-color: #717699; border-radius: 4px; text-align: center; width: 32px; height: 32px; line-height: 32px; color: #FFFFFF; float: left; margin: 10px;">
					终
				</div>
				<span style="line-height: 50px;" class="weight" id="end">终点</span>
			</div>
		</div>
		<!-- end-->
	</body>
</html>
