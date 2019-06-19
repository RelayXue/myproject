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
<script src="<%=curl %>js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=curl %>js/common_function.js"></script>
<script type="text/javascript" src="<%=curl %>js/common_business.js"></script>
<script src="<%=curl %>js/bootstrap.min.js"></script>
<script src="<%=curl %>js/owl.carousel.min.js"></script>
<script type="text/javascript" src="JsContext!DictionaryData"></script>
<title>游在乌镇-详细信息</title>
<script type="text/javascript">
	var use = "<%=request.getParameter("use")%>";
	$(function(){
		if(use != null && use != undefined && use == "1"){ $("#return a").eq(0).attr("href", "<%=basePath%>phone/index"); }
		var id = "<%=request.getParameter("id")%>";
		
		//关闭评级框
		$("#cbtn").click(function(){
			shStarDialog(0);
		});
		//提交评级信息
		$("#ybtn").click(function(){
			var star = $(".bckc input:checked").val();
			var parms = new Object();
			parms.id = id;
			parms.category = "<%=request.getParameter("category")%>";
			parms.star = star;
			$.post("phone_main_rating", parms, function(json) {
				var code = json.code;
				var hint = json.hint;
				alert(hint);
				shStarDialog(0);
			}, "json");
		});
		
		var parms = new Object();
		parms.id = id;
		parms.category = "<%=request.getParameter("category")%>";
		$.post("phone_main_detail", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			
			if (code == 1) {
				var o = json.o;
				
				$("#title").html(cutString(o.fullname, 18));
				$(".title").prepend(o.fullname);
				$("#star_num").html(checkNull(o.star, "0"));
				setLikeImg("praise", id, checkNull(o.praise, 0));
				if(parms.category.indexOf("002003") < 0 && parms.category.indexOf("002011001") < 0 && parms.category.indexOf("002014002") && parms.category.indexOf("002019")){
					$(".phone").attr("href", "tel:"+o.phone);
					var consumption = checkNull(o.consumption, "-");
					if(consumption == 0) consumption = "-";
 					if(o.consumption != "" && parms.category != "0"){ $("#consumption").html("人均消费："+consumption); }
				}
				var main_col = "";
				main_col += "电话："+checkNull(o.phone, "-");
				main_col += "<br />地址："+checkNull(o.address, "-");
				main_col += "<br />简介："+checkNull(o.introduction, "");
				if(!isNull(o.feature)){
					main_col += "<br />特色："+o.feature;
				}
				if(!isNull(o.promise)){
					main_col += "<br />商家承诺："+o.promise;
				}
				$("#main_col").prepend(main_col);
				
				if(o.dimages != undefined && o.dimages.length > 0){
					var imgs = o.dimages.split(",");
					for(g = 0;g<imgs.length;g++){
						$("#pictures").append('<div class="img-thumbnail"><img class="img-responsive" style="width:100%; max-height:250px;" src="<%=basePath%>upload/B_'+imgs[g]+'" /></div>');
					}
				}else{
					$("#pictures").append('<div class="img-thumbnail"><img class="img-responsive" src="<%=basePath%>images/default.jpg"  style="width:100%; max-height:250px;" /></div>');
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
				/*
				if(parms.category != "002019"){
					content +='<li>负责人：'+checkNull(o.supervisor, "无")+'</li>';
					content +='<li>负责人电话：'+checkNull(o.supervisorphone, "无")+'</li>';
				}
				*/
				content +='<li>'+checkNull(o.introduction, "")+'</li>';
				$("#k1 ul").html(content);
				
				$("#go").click(function(){
					window.location.href='<%=curl%>line_in_query.jsp?end_point_id='+o.fuid+'&end_point_name='+o.fullname;
				});
				
				$("#show_in_map").click(function(){
					window.location.href= '<%=curl%>search_map.jsp?p=2&x='+o.fx+'&y='+o.fy+'&id='+o.fuid+'&name='+o.fullname+'&address='+o.address+'&type='+o.type;
				});
				
				//填充列表
				if(json.os["more"]!=undefined){
					var more = $.parseJSON(json.os["more"]);
					var html = '';
					for(i=0;i<more.length;i++){
						var te = "";
						html += '<div id="k2_cont" '+((i == more.length -1)?'style="border:none"':'')+'><ul><li class="title" style="font-weight: bold;">'+more[i].fullname+'</li><li>地址：'+more[i].address+'</li>'+((parms.category == "002019")?'':'<li>电话：'+more[i].phone+'</li>')+'</ul><div id="btn"><p class="zzb"><a href="<%=curl%>search_detail.jsp?id='+more[i].fuid+'&category=<%=request.getParameter("category")%>">详情<img src="images/jt11.jpg"></a></p><p class="phone"><a href="<%=curl%>line_in_query.jsp?end_point_id='+more[i].fuid+'&end_point_name='+more[i].fullname+'">前往<img src="images/jt14.jpg"></a></p></div></div>';
						
					}
					$("#more_list").html(html);
				}
				
				$("#container").show();
			}else{alert(hint);}
		}, "json");
	});
	
	function shStarDialog(i){
		if(i == 0){
			$("#star_dialog_box").hide();
		}else if(i == 1){
			$(".bckc img").each(function(index, element) {
				$(this).attr("src", "<%=curl%>images/wjx2.png");
				$(this).removeAttr("checked", "checked");
			});
			$("#star_dialog_box").show();
		}
	}
	
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
	<style type="text/css">
		#k2 #k2_cont{ border-bottom: 1px solid #ccc; overflow: hidden; padding-top: 12px;}
		.owl-carousel .owl-item {
			float: left;
		}
		
		.star_dialog_box{ display: block;position:fixed; width: 100%; height: 100%; background-color: rgba(0,0,0,0.8); overflow: hidden;z-index: 999; top: 0; left: 0; display: none;}
		.star_dialog_bck{ display: block; margin: 20% auto 0 auto; background-color: #f0f0f0; border-radius: 8px; width: 270px; color: #000; overflow: hidden; }
		.star_dialog_bck .stt{
			font-size: 15px; margin:5px 10px 5px 10px;
			line-height: 36px; border-bottom: 1px solid #999;
			position: relative;
		}
		.star_dialog_bck .stt span{ position: absolute; right: 8px; width: 18px; height: 18px; background-color: #575757; text-align: center; top: 8px; color: #fff;line-height: 20px; border-radius:10px;
			font-size: 12px; 
		}
		.star_dialog_bck .bckc{
			margin:10px 10px 18px 10px;
			position: relative;
		}
		.star_dialog_bck .bckc img{ margin-left: 6px; }
		.star_dialog_bck .bckc span{ position: absolute; right: 20px; top: 3px; width: 80px; text-align: center; display: block;   }
		.star_ybtn{
			width: 100%;display: block;padding: 10px;font-size: 14px; background-color: #6786AE;text-align: center;color: #fff; margin: 10px 0;
		}
		
label {
	display: inline-block;
	cursor: pointer;
	position: relative;
	padding-left: 25px;
	margin-right: 15px;
	font-size: 13px;
	margin-bottom: 15px;
}
input[type=radio],input[type=checkbox] {
	display: none;
}
label:before {
	content: "";
	display: inline-block;
	
	width: 17px;
	height: 16px;
	
	margin-right: 10px;
	position: absolute;
	left: 0;
	bottom: 0;
	background-color: #999;
	box-shadow: inset 0px 2px 3px 0px rgba(0, 0, 0, .3), 0px 1px 0px 0px rgba(255, 255, 255, .8);
}
.radio label:before {
	border-radius: 8px;
}
.checkbox label {
	margin-bottom: 10px;
}
.checkbox label:before {
	border-radius: 3px;
}

input[type=radio]:checked + label:before {
	content: "\2022";
	color: #f3f3f3;
	font-size: 31px;
	text-align: center;
	line-height: 18px;
}

input[type=checkbox]:checked + label:before {
	content: "\2713";
	text-shadow: 1px 1px 1px rgba(0, 0, 0, .2);
	font-size: 15px;
	color: #f3f3f3;
	text-align: center;
	line-height: 15px;
}
		
	</style>
</head>

<body>
	<div id="container" style="overflow: hidden; display: none; padding-bottom: 43px;">
    	<div id="header">
           <div id="return"><a href="javascript:toBack()">&nbsp;</a></div>
           <div id="title" ></div>
           <div id="xq"><a href="javascript:void(0)" id="show_in_map"></a></div>
        </div>
        <div id="hover">
        	<div class="img-thumbnail" id="pictures" style="overflow: hidden;"></div>
            <div id="describe">
            	<ul>
                	<li class="title"><!--<a href="#" name="category">【<s:property value="#application.DatadictionaryMap[category]"  />】</a>--><span style="font-size: 14px;font-weight: normal;"><a id="star_num" style=" color: #000" >0</a><img src="images/wjx.png"></span></li>
                    <li style="display: none;"><tt id="consumption"></tt></li>
                    <li id="main_col"><span name="praise"><tt id="praise"></tt></span></li>
                    <!--<li>地址：<tt name="address">11</tt><br />1<br />2<br />3<br />4</li>
                    --><li class="btn">
                    	<a href="javascript:void(0)" class="phone" style=" width: 50%;"><img src="images/phone.jpg">电话</a>
                        <a href="javascript:void(0)" class="zzb" id="go" style=" width: 50%;"><img src="images/qza.jpg" style="padding-top:5px;">前往</a>
                        <!--<a href="#" class="zzb"><img src="images/zzb.jpg">周边</a> -->
                    </li>
                </ul>
            </div>
        </div>
        <%--<div id="menu" style="margin-bottom: 10px; overflow: hidden;">
        	<div id="cont">
                <div id="menu_title">
                    <a href="javascript:void(0)" id="a1">详情</a>
                    <a href="javascript:void(0)" id="a2">更多</a>
                </div>
                <div id="k1">
                    <ul>
                    </ul>
                </div>
                <div id="k2">
 					<div id="more_list"></div>
                    <!--<div id="gd" style="margin-top:12px;"><a href="javascript:void(0)">展开更多<img src="images/xjt2.png"></a></div>
                --></div>
            </div>
        </div>
        --%><script type="text/javascript">
        	document.getElementById("k2").style.display="none";
			document.getElementById("a1").style.background="#fff";
			document.getElementById("a1").style.color="#282828";
			var a=document.getElementById("menu_title").getElementsByTagName("a");
			function style(count){
				for(var i=0;i<a.length;i++){
					document.getElementById("k"+(i+1)).style.display="none";
					document.getElementById("a"+(i+1)).style.background="#c4c2bc";
					document.getElementById("a"+(i+1)).style.color="#fff";
				}
				document.getElementById("k"+count).style.display="block";
				document.getElementById("a"+count).style.background="#fff";
				document.getElementById("a"+count).style.color="#282828";
			}
			for(var i=0;i<a.length;i++){
				
				a[i].i=i;
				a[i].onclick=function (){
					style(this.i+1);
				}
			}
        </script>
        <div id="footer" style="margin-top: 10px;">
        	<a href="javascript:shStarDialog(1)" style="width: 100%; "><img src="images/tx.png">评级</a>
        </div>
    </div>
    
    <!-- 评级dialog部分 -->
    <div class="star_dialog_box" id="star_dialog_box">
    	<div class="star_dialog_bck">
    		<div class="stt">评级<span id="cbtn">×</span></div>
    		<div class="bckc">
				<div class="radio">
					<input id="radio1" type="radio" name="gender" value="1">
					<label for="radio1">很差</label>
	                <br />
					<input id="radio2" type="radio" name="gender" value="2">
					<label for="radio2">差</label>
	                <br />
					<input id="radio3" type="radio" name="gender" value="3" checked>
					<label for="radio3">还行</label>
	                <br />
					<input id="radio4" type="radio" name="gender" value="4">
					<label for="radio4">好</label>
	                <br />
					<input id="radio5" type="radio" name="gender" value="5">
					<label for="radio5">很好</label>
				</div>
			</div>
    		<!-- 
    		<div class="bckc">
    			<img src="images/wjx2.png"><img src="images/wjx2.png"><img src="images/wjx2.png"><img src="images/wjx2.png"><img src="images/wjx2.png">
    			<span>点击星评级</span>
    		</div>
    		 -->
    		<div class="star_ybtn" id="ybtn">确定</div>
    	</div>
    </div>
</body>
</html>
	