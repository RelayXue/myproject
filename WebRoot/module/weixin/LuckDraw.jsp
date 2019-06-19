<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <title>活动抽奖</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <style type="text/css"> 
    </style>
    <script type="text/javascript">
    var ar = ['7:00','22:00'];
    function checkTime(ar) {
        var d = new Date();
        var current = d.getHours() * 60 + d.getMinutes();
        var ar_begin = ar[0].split(':');
        var ar_end = ar[1].split(':');
        var b = parseInt(ar_begin[0]) * 60 + parseInt(ar_begin[1]);
        var e = parseInt(ar_end[0]) * 60 + parseInt(ar_end[1]);
        //周1-周3
        if (d.getDay() >=1  && d.getDay() <=3) {
        	// if (current >= b && current <= e){
        	//	 return true;
        	// }else{
        	//	 return false;
        	// }
        	 return true;
        }else{
        	return false;
        }
        
    }
    
    function chou(){
    	var t1=new Date("2015-7-17");
    	var t2=new Date("2015-8-20");
    	//if(checkTime(ar)&&(new Date()>t1)&&(new Date()<t2)){
    	if(true){
    		$("#form1").submit();
    		$("#ja").attr("href","javascript:void(0)");
    		//window.location.href="<%=basePath%>fnews!HasLuckDraw?openid=&=${nickname}&";
    	}else{
    		alert("抽奖还未开始，抽奖时间为2015-7-19 07:00至2015-8-19 22:00");
    	}
    }
    
    
    </script>
    
</head>
<body style="background: #e5e0da">
    <div id="ww">
        <div id="dvTop" style="padding-bottom: 24px">
            <img src="<%=basePath %>module/weixin/images/cj_main_top.png" style="width: 100%" />
        </div>
        <div id="dvCenter">
            <a id="ja" href="javascript:chou()"><img src="<%=basePath %>module/weixin/images/cj_main_center.gif" style="width: 100%" /></a>
        </div>
        <div style="text-align: center; padding-top: 15px">
            <a href="<%=basePath %>fnews!showRule"><img src="<%=basePath %>module/weixin/images/cj_main_gz.gif"  style="width: 170px;"  /></a>
        </div>
    </div> 
    <form action="<%=basePath%>fnews!HasLuckDraw" method="post" id="form1" name="form1" >
    	<input type="hidden" name="openid" value="${openid}" >
    	<input type="hidden" name="nickname" value="${nickname}" >
    	<input type="hidden" name="headimgurl" value="${headimgurl}" >
    </form>
</body>
</html>
