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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/common.css">
<link rel="stylesheet" type="text/css" href="<%=curl %>css/nearby-locating6.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=curl %>js/common_function.js"></script>
<script type="text/javascript" src="<%=curl %>js/common_business.js"></script>
<script src="<%=curl %>js/bootstrap.min.js"></script>
<script src="<%=curl %>js/owl.carousel.min.js"></script>
<script type="text/javascript" src="JsContext!DictionaryData"></script>
<title>游在乌镇-详细信息</title>
		
<script type="text/javascript">

	$(function(){
		$("#content").width($(document).width() + 18);
		$("#content").height($(document).height() - 55);
	
		var parms = new Object();
		parms.id = "<%=request.getParameter("id")%>";
		parms.category = "<%=request.getParameter("category")%>";
		$.post("phone_main_detail", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			if (code == 1) {
				var o = $.parseJSON(json.o);
				
				$("#title").html(cutString(o.fullname, 18));
				$(".title").html(o.fullname);
				$("[name='address']").html(checkNull(o.address, "无"));
				$("#praise").html((o.praise == "")?0:o.praise);
				if(parms.category != "002019"){
					$("[name='phone']").html("店铺电话："+checkNull(o.phone, "无"));
					$(".phone").attr("href", "tel:"+o.phone);
					if(o.consumption != "" && parms.category != "0"){ $("#consumption").html("人均消费："+checkNull(o.consumption, 0)); }
				}else{ 
					$("[name='phone']").html("&nbsp;&nbsp;");
				}
				
				var imgs = o.dimages.split(",");
				for(g = 0;g<imgs.length;g++){
					$("#pictures").append('<div class="img-thumbnail"><img class="img-responsive" style="width:100%; max-height:250px;" src="<%=basePath%>upload/B_'+imgs[g]+'" /></div>');
				}
				  $("#pictures").owlCarousel({
			      navigation : false, // Show next and prev buttons
			      slideSpeed : 300,
			      paginationSpeed : 400,
			      singleItem:true
			  	  });
				
				$("[name='praise']").click(function(){ clickLike("praise",o.fuid,"<%=request.getParameter("category")%>"); });
				
				//填充简介
				var content = '';
				if(parms.category != "002019"){
					content +='<li>负责人：'+checkNull(o.supervisor, "无")+'</li>';
					content +='<li>负责人电话：'+checkNull(o.supervisorphone, "无")+'</li>';
				}
				content +='<li>介绍：'+checkNull(o.introduction, "无")+'</li>';
				$("#k1 ul").html(content);
				
				$("#go").click(function(){
					window.location.href='<%=curl%>line_in_query.jsp?end_point_id='+o.fuid+'&end_point_name='+o.fullname;
				});
				
				//填充列表
				if(json.os["more"]!=undefined){
					var more = $.parseJSON(json.os["more"]);
					var html = '';
					for(i=0;i<more.length;i++){
						var te = "";
						html += '<div id="k2_cont"><ul><li class="title" style="font-weight: bold;">'+more[i].fullname+'</li><li>地址：'+more[i].address+'</li>'+((parms.category == "002019")?'':'<li>电话：'+more[i].phone+'</li>')+'</ul><div id="btn"><p class="zzb"><a href="<%=curl%>search_detail.jsp?id='+more[i].fuid+'&category=<%=request.getParameter("category")%>">详情<img src="images/jt11.jpg"></a></p><p class="phone"><a href="<%=curl%>line_in_query.jsp?end_point_id='+more[i].fuid+'&end_point_name='+more[i].fullname+'">前往<img src="images/jt14.jpg"></a></p></div></div>';
					}
					$("#more_list").html(html);
				}
			}
		}, "json");
	});
	
	/**参数说明：
	 * 根据长度截取先使用字符串，超长部分追加…
	 * str 对象字符串
	 * len 目标字节长度
	 * 返回值： 处理结果字符串
	 */
	function cutString(str, len) {
		//length属性读出来的汉字长度为1
		if(str.length*2 <= len) {
			return str;
		}
		var strlen = 0;
		var s = "";
		for(var i = 0;i < str.length; i++) {
			s = s + str.charAt(i);
			if (str.charCodeAt(i) > 128) {
				strlen = strlen + 2;
				if(strlen >= len){
					return s.substring(0,s.length-1) + "...";
				}
			} else {
				strlen = strlen + 1;
				if(strlen >= len){
					return s.substring(0,s.length-2) + "...";
				}
			}
		}
		return s;
	}
	</script>
</head>

<body style="overflow: hidden;" >
	<div id="container" style="overflow: hidden;">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title" >美图分享</div>
        </div>
        <div style="width: 100%; overflow: hidden;" >
        	<iframe src="<%=basePath %>fnews!share" style="border: none; overflow-x:hidden;" id="content" scrolling="yes"></iframe>
        </div>
    </div>
</body>
</html>
	