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
<title>乌镇国际旅游区</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Resource/Theme/StyleDefault/style.css"/>  
<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TTuJing.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TMapType.js"></script>
<script type="text/javascript" src="<%=basePath%>module/web/js/TJSoft.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>module/web/css/jquery.autocomplete.css" />
<script type='text/javascript' src='<%=basePath%>module/web/js/jquery.autocomplete.min.js'></script>
<script type="text/javascript">
$(function(){
	$("#month").autocomplete("search_autoCompleteData", {
		minChars: 0,
		max: 10,
		autoFill: false,
		mustMatch: true,
		matchContains: false,
		selectFirst:true,
		scrollHeight: 220,
			formatItem: function(row, i,max) { 
				var obj =eval("(" + row + ")"); //转换成js对象 
				return obj.text; 
				}, 
				formatResult: function(row) { 
				var obj =eval("(" + row + ")"); //转换成js对象 
				return obj.text; 
				},
				formatMatch:function(row){
					var obj =eval("(" + row + ")"); //转换成js对象 
					return obj.text; 
				}
	});

	$("#month").result(function(event, data, formatted) {
		var obj =eval("(" + data + ")");
		alert(obj.url);
		//var hidden = $(this).parent().next().find(">:input");
		//hidden.val( (hidden.val() ? hidden.val() + ";" : hidden.val()) + data[1]);
	});
});
</script>

  </head>
  
  <body>
  <input id="month" value=""/>
  
  </body>
</html>
