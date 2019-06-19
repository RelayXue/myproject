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
<link rel="stylesheet" type="text/css" href="<%=curl %>css/nearby-locating3.css">
<script src="<%=curl %>js/jquery.min.js"></script>
<script src="<%=curl %>js/k_tables.js"></script>
<script type="text/javascript" language="javascript" src="JsContext!DictionaryData"></script>
<title>游在乌镇-周边<%=request.getParameter("title")%></title>
<script type="text/javascript">
	var category = "<%=request.getParameter("category")%>";
	var cur_page_index = <%=(request.getParameter("p") == null)?1:request.getParameter("p")%>;	//页码索引
	
	var pos_x = 0;
	var pos_y = 0;
	
	//滚动加载标识 用于判断当前是否在加载中
	function loadDate(meo){
		category = $("#filter_1 .item_choosed a").attr("val");
		if(meo == 0) { cur_page_index = 1;}
		var parms = new Object();
		parms.category = category;
		parms.key = "<%=request.getParameter("key")%>";
		parms.area = $("#filter_3 .item_choosed a").attr("val");
		parms.orderBy = $("#filter_2 .item_choosed a").attr("val");
		parms.indexPage = cur_page_index;
		parms.pageSize = 10;
		parms.pos_x = pos_x;
		parms.pos_y = pos_y;
		$.post("phone_main_list", parms, function(json) {
			var code = json.code;
			var hint = json.hint;
			if (code == 1) {
				var o = json.o;
				localStorage.setItem("point_list", JSON.stringify(o));
				var html = "";
				for(i=0;i<o.length;i++){
					var url = '<%=curl%>search_detail.jsp?id='+o[i].fuid+'&category='+category;
					var image = '';
					if(o[i].dimages != undefined && o[i].dimages != null && o[i].dimages.length > 0){
						image = o[i].dimages.split(",")[0];
					}
					html += genItem(image, o[i].dimagesStatus, o[i].fullname, o[i].type, getStringSub(o[i].phone, '-'), getStringSub(o[i].address, '-'), o[i].fuid, url);
					/*
					if(category.substring(0,6) != "002019"){
						html += '<li onclick=toDetail("'+url+'")>电话：'+((o[i].phone == undefined)?"无":o[i].phone)+'</li>';
						html += '<li onclick=toDetail("'+url+'")>地址：'+((o[i].address == undefined)?"无":o[i].address)+'</li>';
					}else{
						html += '<li><br /><br /></li>';
					}
					*/
				}
				$("#list_box").html(html); 
				if(cur_page_index > 1){$("#previous_btn").show();}else{$("#previous_btn").hide();}
				var total_page = json.os.total_page;
				if(cur_page_index < total_page){$("#next_btn").show();}else{$("#next_btn").hide();}
			}
		}, "json");
	}
	
	function genItem(image,image_status,title,type_code,mobile,address,id,url){
		var html = '';
		var type = "";
		for(p in types){
			if(types[p].code == type_code){
				type = types[p].fullname; break;
			}
		}
		if(!isNull(type)){ type = "["+type+"]"; }
		if(isNull(image) || image_status == 0){
			html += '<div class="_item2">';
			html += '<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>'+getStringSub(title, 7)+'</td></tr></table>';
			html += '<div style="margin: 10px 15px 6px 152px;">';
			html += '<div style="font-size: 18px; white-space: nowrap;overflow: hidden;text-overflow: ellipsis;" onclick=toDetail("'+url+'")>'+title+'<!-- <span style="color: rgb(102,107,145)">'+type+'</span> --></div>';
			html += '<div style="height: 1px; width: 100%; background-color: rgb(200,200,200); margin: 6px 0;"></div>';
			html += '<div style="font-size: 13px; line-height: 20px; white-space: nowrap;overflow: hidden;text-overflow: ellipsis;" onclick=toDetail("'+url+'")>';
			if(type_code.indexOf("002003") < 0 && type_code.indexOf("002011001") < 0 && type_code.indexOf("002014002") && type_code.indexOf("002019")){
				html += '联系电话：'+getNullString(mobile, '-')+'<br />';
			}
			html += '地址：'+getNullString(address, '-');
			html += '</div><br />';
			html += '<div style="overflow: hidden;"><span class="_btn _to1" style="width: 45%;float: left;" onclick=window.location.href="<%=curl%>line_in_query.jsp?end_point_id='+id+'&end_point_name='+title+'">前往</span><span class="_btn _to2" style="float: left;width: 45%;" onclick=toDetail("'+url+'")>详细</span></div>';
			html += '</div></div>';
		}else{
			html += '<div class="_item1">';
			html += '<img src="<%=basePath%>upload/B_'+image+'" style="width: 100%;height: 182px;"/>';
			html += '<div style="padding: 0 10px 6px 10px">';
			html += '<div style="position: relative; margin-top: 10px">';
			
			html += '<div style="font-size: 18px;" onclick=toDetail("'+url+'")>'+cutString(title, 16)+'<br /><!-- <span style="color: rgb(102,107,145)">'+type+'</span> --></div>';
			html += '<span class="_btn _to1" style="width: 80px;position: absolute;right: 0; top: -6px; text-align: center;" onclick=window.location.href="<%=curl%>line_in_query.jsp?end_point_id='+id+'&end_point_name='+title+'">前往</span>';
			html += '</div>';
			html += '<div style="height: 1px; width: 100%; background-color: rgb(200,200,200); margin: 8px 0;"></div>';
			html += '<div style="font-size: 14px; line-height: 20px; position: relative;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;" onclick=toDetail("'+url+'")>';
			if(type_code.indexOf("002003") < 0 && type_code.indexOf("002011001") < 0 && type_code.indexOf("002014002") && type_code.indexOf("002019")){
				html += '联系电话：'+getNullString(mobile, '-')+'<br />';
			}
			html += '地址：'+cutString(address, 20);
			html += '<span class="_btn _to2" style="width: 80px;position: absolute;right: 0; top: 3px;" onclick=toDetail("'+url+'")>详细</span>';
			html += '</div></div></div>';
		}
		return  html;
	}
	
	function getStringSub(str, length){
		if(str.length > length){
			return (str.substring(0, length) + "..");
		}
		return str;
	}
	function isNull(str){
		if(str == undefined || str == "" || str == null || str == "null" || str == "undefined"){
			return true;
		}
		return false;
	}
	function getNullString(str, def_str){
		if(isNull(str)){
			return def_str;
		}
		return str;
	}
	
	function toPage(ix){
		if(ix == 0) {cur_page_index-=1; }
		else if(ix == 1){ cur_page_index+=1; }
		loadDate(cur_page_index-1);
	}
	
	function toDetail(url){
		window.location.href=url;
	}

	
	//滚动加载
	function aa(){
	    var winH = $(window).height(); //页面可视区域高度 
	    $(window).scroll(function () { 
	        var pageH = $(document).height(); 
	        var scrollT = $(window).scrollTop(); //滚动条top 
	        var aa = (pageH-winH-scrollT)/winH;
	        if(aa<0.02 && !scroll_loading){
				loadDate(1);
	        } 
	    }); 
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
	
	var types = new Array();	//类别数组

	$(function(){
		//添加筛选列表子类
		for(i = 0;i<basetemp.length;i++){
			var code = basetemp[i].code;
			if(code.substring(0,6) == category){
				types.push(basetemp[i]);
				if(code.length > 6)	$("#filter_1").append('<li><a href="javascript:void(0)" val="'+basetemp[i].code+'">'+basetemp[i].fullname+'</a></li>');
			}else if(code.length == 6 && code.substring(0,3) == "002" && category == "0"){
				$("#filter_1").append('<li><a href="javascript:void(0)" val="'+basetemp[i].code+'">'+basetemp[i].fullname+'</a></li>');
			}
		}
		
		//筛选
		var li=document.getElementById("condition").getElementsByTagName("li");
		for(var i=0;i<li.length;i++){
			li[i].i=i;
			li[i].onclick=function (){
				if($("#menu div").eq(this.i).css("display") != "block"){
					for(var j=0;j<li.length;j++){
						li[j].style.background="#fff";
						$("#condition a").eq(j).css("color","#787878");
						$("#menu li img").eq(j).attr("src","images/xjt2.png");
						$("#menu div").eq(j).hide();	
					}
					
					this.style.background="#717699";
					$("#condition a").eq(this.i).css("color","#fff");
					$("#menu li img").eq(this.i).attr("src","images/xjt3.png");
					$("#menu div").eq(this.i).show();
					$("#bg").height($(document).height());
					$("#bg").show();
					scroll_loading = true;
				}else{
					$("#condition li").css("background-color", "#fff");
					$("#condition a").css("color","#787878");
					$("#condition li img").attr("src","images/xjt2.png");
					$("#menu div").eq(this.i).hide();
					$("#bg").hide();
					scroll_loading = false;
				}
			};
		};
    	document.getElementById("bg").onclick=function (){
			this.style.display="none";
			for(var k=0;k<li.length;k++){
				li[k].style.background="#fff";
				$("#condition a").eq(k).css("color","#787878");
				$("#menu li img").eq(k).attr("src","images/xjt2.png");
				$("#menu div").eq(k).hide();
				scroll_loading = false;
			};
		};
		
		//调用条件过滤
		filterListener("filter_1");
		filterListener("filter_2");
		filterListener("filter_3");
	    
	    
		loadDate(1);
		//aa();
	});	
	
	//条件过滤监听
	function filterListener(box){
		$("#"+box+" li").click(function(){
			if($(this).attr("class") == undefined || $(this).attr("class").indexOf("item_choosed") < 0){
				$("#"+box+" li").removeClass("item_choosed");
				$(this).addClass("item_choosed");
				dialog("正在查询..");
				loadDate(0);
			}
			$("#bg").click();
		});
	}
	
        if (window.navigator.geolocation) {
            var options = {
                enableHighAccuracy: true
            };
            window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
        } else {
            alert("浏览器不支持html5来获取地理位置信息");
        }
        
        function handleSuccess(position){
            // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
            pos_x = position.coords.longitude;
            pos_y = position.coords.latitude;
        }
        
        function handleError(error){
        }
	
</script>
<style type="text/css">
	.item_choosed{background-image: url(images/checked.png) !important; background-repeat: no-repeat !important; background-position: 95% center !important; background-size:21px !important;}
	.item_choosed a{ color: #717699 !important;}
	/* dialog */
	.dialog {
		position: fixed;
		background-color: #000;
		color: #FFF;
		text-align: center;
		padding: 15px;
		-moz-border-radius: 8px;      /* Gecko browsers */
		-webkit-border-radius: 8px;   /* Webkit browsers */
		border-radius:8px;
		filter:alpha(Opacity=70);
		-moz-opacity:0.7;
		opacity: 0.7;
		display: none;
		font-family: "微软雅黑", "幼圆", "黑体";
		font-size: 12px;
		z-index:99;
	}
	
	._item1{
		display: block;
		margin-bottom: 10px;
		color: black;
		overflow: hidden;
		background-color: #fff;
		border: 1px solid rgb(202,201,199);
	}
	._btn{padding:6px 0px;display:block;background:#fff;border:1px solid;border-radius:5px;margin-right:4px;box-sizing:border-box;text-align: center;}
	._to1{ color:#c9af9f; background-image: url(images/jt12.jpg);background-repeat: no-repeat; background-position: 15% center;}
	._to2{ color:#8589a7; background-image: url(images/jt11.jpg);background-repeat: no-repeat; background-position: 78% center;}
	
	._item2{
		display: block;
		height:142px;
		overflow: hidden;
		background-color: #fff;
		border: 1px solid rgb(202,201,199);
		color: black;
		position: relative;
		margin-bottom: 8px;
	}
._only_txt{
	height:142px;
	width:140px;
	overflow:hidden;
	background-image:url(images/itme_bg.jpg);
	background-size:140px;
	border:0;
	margin: 0;
	position: absolute;
	top: 0;
	left: 0;
}

._only_txt td{
	font-family: "微软雅黑";
	font-size:27px;
	color:#FFF;
	height:142px; 
	width:140px;
	text-align:center;
	padding:0 15px;
}
</style>
</head>

<body>

	<div id="container">
    	<div id="header">
           <div id="return"><a href="javascript:history.go(-1)"><br></a></div>
           <div id="title"><%=request.getParameter("title") %></div>
           <div id="xq"><a href="<%=curl%>search_map.jsp?p=1"></a></div>
        </div>
        <div id="menu">
        	<ul id="condition">
            	<li><a href="javascript:void(0)">筛选<img src="images/xjt2.png"></a></li>
                <li><a href="javascript:void(0)">排序<img src="images/xjt2.png"></a></li>
                <li><a href="javascript:void(0)">范围<img src="images/xjt2.png"></a></li>
            </ul>
            <div>
            	<ul id="filter_1">
                	<li class="item_choosed"><a href="javascript:void(0)" val="<%=request.getParameter("category")%>">所有</a></li>
                </ul>
            </div>
            <div>
            	<ul id="filter_2">
                	<li class="item_choosed"><a href="javascript:void(0)" val="0">综合排序</a></li>
                    <li><a href="javascript:void(0)" val="1">距离由近到远</a></li>
                    <li><a href="javascript:void(0)" val="2">价格由高到低</a></li>
                    <li><a href="javascript:void(0)" val="3">价格由低到高</a></li>
                    <li><a href="javascript:void(0)" val="4">人气由高到底</a></li>
                </ul>
            </div>
            <div>
            	<ul id="filter_3">
                	<li class="item_choosed"><a href="javascript:void(0)" val="0">所有</a></li>
                    <li><a href="javascript:void(0)" val="200">200米内</a></li>
                    <li><a href="javascript:void(0)" val="500">500米内</a></li>
                    <li><a href="javascript:void(0)" val="1000">1000米内</a></li>
                    <li><a href="javascript:void(0)" val="2000">2000米内</a></li>
                </ul>
            </div>
        </div>
        <div id="hover">
        	<div id="previous_btn" onclick="toPage(0)" style="line-height: 36px;font-size: 16px; border: 1px solid #728DB5; text-align: center;background-color: #fff; margin-top: 5px; color: #728DB5; display: none;">上一页</div>
        	<div id="list_box" style="overflow: hidden; padding-top:10px ">
        		<!-- 
        		<div class="_item1">
        			<img src="http://image.tianjimedia.com/uploadImages/2015/030/00/H673S6WGEF0A_680x500.JPEG" style="width: 100%;"/>
        			<div style="padding: 0 10px 6px 10px">
        			<div style="position: relative; margin-top: 10px">
        				<div style="font-size: 18px;">乌镇黄金水岸大酒店<br /><span style="color: rgb(102,107,145)">[住宿]</span></div>
        				<span class="_btn _to1" style="width: 80px;position: absolute;right: 0; top: 3px; text-align: center;">前往</span>
        			</div>
        			<div style="height: 1px; width: 100%; background-color: rgb(200,200,200); margin: 8px 0;"></div>
        			<div style="font-size: 14px; line-height: 20px; position: relative;">
        				联系电话：12845574566<br />
        				地址：桐乡市乌镇地区
        				<span class="_btn _to2" style="width: 80px;position: absolute;right: 0; top: 3px;">详细</span>
        			</div>
        			</div>
        		</div>
        		<div class="_item2">
        			<table class="_only_txt" cellpadding="0" cellspacing="0"><tr><td>士大夫第三方渡水复士大夫第三方渡水复渡水渡水</td></tr></table>
        			<div style="margin: 10px 15px 6px 152px;">
        				<div style="font-size: 18px;">乌镇黄金水岸大酒店<span style="color: rgb(102,107,145)">[住宿]</span></div>
        				<div style="height: 1px; width: 100%; background-color: rgb(200,200,200); margin: 6px 0;"></div>
						<div style="font-size: 13px; line-height: 20px;">
	        				联系电话：12845574566<br />
	        				地址：桐乡市乌镇地区
	        			</div>
	        			<div style="overflow: hidden;"><span class="_btn _to1" style="width: 45%;float: left;">前往</span><span class="_btn _to2" style="float: left;width: 45%;">详细</span></div>
        			</div>
        		</div>
        		 -->
        	</div>
        	<div id="next_btn" onclick="toPage(1)" style="line-height: 36px;font-size: 16px; border: 1px solid #728DB5; text-align: center;background-color: #fff; margin: 5px 0; color: #728DB5; display: none;">下一页</div>
        </div>
    </div>
    <div id="bg"></div>
</body>
</html>
	