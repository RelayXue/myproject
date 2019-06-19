<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type"); 
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">
<html>
  <head>
    <base href="<%=basePath%>" />
    <title></title>
	<link href="css/layout.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/eayui/themes/default/easyui.css" />
	<link href="css/virtual.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.timers.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/GPS/map_common.js"></script> 
	<script type="text/javascript" src="<%=basePath %>js/GPS/business.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/autonav.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/eayui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	
	 jQuery(document).ready(function($) {
		 	navAuto();
			$(window).bind("resize", navAuto);
			InitCG();
			
			run();
	 });
	 //--------------------时间获取昨天,今天,明天----------------------
	  	Date.prototype.Format = function(fmt) 
{
    //author: meizz 
    var o =
     { 
        "M+" : this.getMonth() + 1, //月份 
        "d+" : this.getDate(), //日 
        "h+" : this.getHours(), //小时 
        "m+" : this.getMinutes(), //分 
        "s+" : this.getSeconds(), //秒 
        "q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
        "S" : this.getMilliseconds() //毫秒 
     }; 
    if (/(y+)/.test(fmt)) 
         fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
    for (var k in o) 
        if (new RegExp("(" + k + ")").test(fmt)) 
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
    return fmt; 
};


Date.prototype.addDays = function(d)
{
    this.setDate(this.getDate() + d);
};


Date.prototype.addWeeks = function(w)
{
    this.addDays(w * 7);
};


Date.prototype.addMonths= function(m)
{
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);

    if (this.getDate() < d)
        this.setDate(0);
};


Date.prototype.addYears = function(y)
{
    var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);

    if (m < this.getMonth()) 
     {
        this.setDate(0);
     }
};
	
//----------------------------------------------
	 
	 	function run(){//时间间隔0.5秒请求一次
	 		 fun();
	 		var  interval = setInterval(fun, 5000); 
	 	}
	 	function fun(){  
	 	var now = new Date();
	 	now.addDays(-1);
	 		$.ajax({
	 			type:"post",
	 			dataType:"json",
	 			url:"<%=basePath%>buPersonnel!showmap?time=" + now.Format('yyyy-MM-dd')+"&times="+new Date(),
	 			success:function(data){  
	 				markerL.ClearMarkers();  
	 				var SecurityData=data;
	 				for(var i=0;i<SecurityData.length;i++){
	 					//dw(SecurityData[i]);
	 					try{
	 					var fx=SecurityData[i].fx;
	 					var fy=SecurityData[i].fy;
	 					var ll = new SLonLat(fx,fy); 
	    			    AddMarkerTest(ll,SecurityData[i]);
	    			    fx = null;
	    			    fy = null;
	    			    ll = null;
	    			    }catch(vesdf){}
	 				}
	 			}
	 		}); 
	 	}
	 
	 function dw(nodes){
	 	if(nodes!=null){
			var fx=nodes.fx;
			var fy=nodes.fy;
			var name=nodes.fullname;
			alert(name);
			if (fx != null && fx.length > 0) {
				var icon2 = new SIcon("<%=basePath%>images/gps/point.png",new SSize(21, 25));
				icon2.SetOpacity(0.2);
				//var ll = JZbConvert.GetGpsToSwLonLat(new SPoint(fx,fy));
				var ll = new SLonLat(fx,fy); 
				mm = new SMarker(ll, icon2, nodes.fuid);
				mm.__ll = ll;
				mm.__text = getPersonPanelHtml(nodes); 
				markerL.AddMarker(mm);
				mm.AddEventListener("click", mm, function() { 
					map.SInfoWindow.SetLonLat(this.__ll); 
					map.SInfoWindow.SetInnerHTML(this.__text);
				 	 map.SInfoWindow.SetSize(new SSize(200, 200));
					 map.SInfoWindow.Show();
				}); 
			}
			var lbl = new SLabel(null, ll, null, name);
			lbl.SetOpacity(0.8);
			lbl.SetOffset(new SSize(10, -30));
			lbl.SetAdaptive();
			map.AddLabel(lbl);
			lbl.SetCssStyle("tJLabel");
		}
 
	 }
	 
	 
	 function AddMarkerTest(ll, data) {
        var gg = new SIcon("<%=basePath %>images/gps/point.png", new SSize(30, 31), new SPixel(-15, -31));
        
        var mm = new SMarker(ll, gg, "wu");
        mm.__ll = ll;
        if(data.sex==1){
        	var sex='男';
        }else{
        	var sex = '女';
        }
		mm.__text ="<div style=\"padding:10px 20px 2px 2px\">人员姓名："+data.realname+"<br><br>"+"手机号："+data.mobile+"<br><br>"+"性别："+sex+"<br><br>"+"描述："+data.description + "</div>";
        markerL.AddMarker(mm); 
        mm.AddEventListener("click", mm, function() { 
					map.SInfoWindow.SetLonLat(this.__ll); 
					map.SInfoWindow.SetInnerHTML(this.__text);
				 	 map.SInfoWindow.SetSize(new SSize(220, 150)); 
            		map.SInfoWindow.SetOffset(new SSize(-7, -33));
					 map.SInfoWindow.Show();
				}); 
        gg = null;
        //mm.__ll = null;
        //mm.__text = null;
        mm = null;
        ll = null;
       //name = null; 
      
        data = null; 
     
    }
	
	 
	</script>
	

 <style type="text/css"><!--

/* .v_logo img{ filter: Alpha(Opacity=70, FinishOpacity=0, Style=1, StartX=0, StartY=0, FinishX=100, FinishY=100)} */
.v_top{filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5; background-color: #edf9fe}


 
       #map
        {
            width: 100%;
            height: 100%;
            position:absolute;
            left: 0px;
            top: 0px; 
            background-color: #eaedf2;
        }
 


</style>
  </head>
  
  <body>
<div id="map" style="width: 100%;">
</div>
	
	
</body>
</html>
