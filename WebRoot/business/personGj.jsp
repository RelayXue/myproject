<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String data=(String)request.getAttribute("data");		
	String data_yx=(String)request.getAttribute("data_yx");		
	String PerNum=request.getParameter("PerNum");		
	String code1=request.getParameter("code1");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>条件选择</title>
		<link rel="stylesheet" href="<%=basePath%>js/menu/style.css" type="text/css" />
		<link href="<%=basePath%>js/menu/tool.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/GPS/map.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/Date.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/menu/jquery.ui.core.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/menu/jquery.ui.widget.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/menu/jquery.ui.tabs.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/GPS/map_common.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/GPS/business.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/GPS/mapgj.js"></script> 
		
		<script type="text/javascript">
		
		var data='<%=data%>';
		var data_yx='<%=data_yx%>';
	$(document).ready(function() {
		var PerNum='<%=PerNum%>';
		if(data!='null'&&data.length!=0){
			data=decodeURIComponent(data);
			$("#btnStart").removeAttr("disabled");
		}else if(data=='nodata'){
			alert("暂无数据");
			return;
		} 
		if(PerNum!='null'&&data!='null'){
			$("#searchKey").attr("GpsId",PerNum);
			window.parent.showLine(data,data_yx);
		}
		//暂停 继续
		 $("#btnPause").click(function (){
		 	var speed=$("#playspeed").find("option:selected").val();
		 	var ty=$(this).attr("ty");
		 	if(ty=='zt'){
		 		$(this).html("继续");
		 		$(this).attr("ty","jx"); 
		 		window.parent.SRoute.Pause();
		 	}
		 	if(ty=='jx'){
		 		$(this).html("暂停");
		 		$(this).attr("ty","zt"); 
		 		window.parent.SRoute.Continue();
		 	}
		 })
	});
	 
	function control(te){
	if(te=='bf'){
		var speed=$("#playspeed").find("option:selected").val();
		$("#playspeed").change(function(){
			 speed=$("#playspeed").find("option:selected").val();
			 window.parent.speedInit(speed);
		});
		 window.parent.GpsPlay(data,data_yx,speed);
		 $("#btnStart").attr("disabled","disabled");
		 $("#btnPause").removeAttr("disabled"); 
		 $("#btnStop").removeAttr("disabled");
	}else if(te=='stop'){
		$("#btnStart").removeAttr("disabled");
		$("#btnPause").attr("disabled","disabled");
		 $("#btnStop").attr("disabled","disabled");
		 $("#btnPause").html("暂停");
	 	 $("#btnPause").attr("ty","zt");
		 this.parent.suspend_gj('stop');
		}
		
	}
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
	
	function InOne() {
		var now1 = new Date();
		var now = new Date();
		now.addDays(-1);
		$("#startTime1").attr("value", now.Format("yyyy-MM-dd"));//近一天
		$("#endTime1").attr("value", now1.Format("yyyy-MM-dd"));//今天
	}
	function InThree() {
	    var now1 = new Date();
		var now = new Date();
		now.addDays(-3);
		$("#startTime1").attr("value", now.Format("yyyy-MM-dd"));//近三天
		$("#endTime1").attr("value", now1.Format("yyyy-MM-dd"));//今天
	}
	//选择人员
	function showPersonGJ(){
		$("#ifr").attr("src","<%=basePath%>buPersonnel!selectPerson");
	 		 $('#dd').dialog({ });
		         
	}
	
	//读取人员号码与时间
	function readGj(){
		
		/*var phone=$("#searchKey").val();
		alert(phone);
		var startTime=$("#startTime1").val();
		var endTime=$("#endTime1").val();*/
	
		if($("#startTime1").val()==''||$("#startTime1").val()==null){
			alert("请您输入开始时间！");
			return ;
		}
	    if($("#endTime1").val()==''||$("#endTime1").val()==null){
			alert("请您输入结束时间！");
			return ;
		}
		
		
		 $.ajax({
             type: "post",
             dataType:"json",
             url: "<%=basePath%>buPersonnel!personTrajectory",
             data: {
             	phone:$("#searchKey").val(),//人员手机号码
             	startTime:$("#startTime1").val(),//开始时间
             	endTime:$("#endTime1").val()//结束时间
             },
             success: function (data) {//result
               StartGJ(data);
             }
         });
       
	}
	function StartGJ(data){
	 			if(data!=null){ 
                	window.parent.RouteInit(data);
                }
                
	}

	
	
	
	 	
	
</script>
	 </head>

	<body>
		<div id="tabs">
			<ul>
				<li>
					  <span class=""><strong>人员轨迹信息</strong> </span>  
				</li>
			</ul>
			<div id="tabs-1" style="padding: 5px 0 0 5px;">
				<div class="searchBox">
					<input type="text" readonly="readonly" id="searchKey" value="${fname }"  />
					&nbsp;
					<a onclick="javascript:window.parent.showPersonGJ()" class="btn">选择</a>
					<div class="pbr"></div>
					<div class="pbr"></div>
					<div class="pbr"></div>
					开始时间：<input type="text" value="${startTime}" id="startTime1" style="height: 10px" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endTime1\',{d:-3})}',maxDate:'#F{$dp.$D(\'endTime1\')}',onpicked:function(){endTime1.focus();}})" />
					<div class="pbr"></div>
					结束时间：<input type="text" id="endTime1" value="${endTime }" style="height: 10px" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startTime1\',{d:3})}',minDate:'#F{$dp.$D(\'startTime1\')}'})" />
					<div class="pbr"></div>
					<div class="pbr"></div>
					<a href="javascript:void(0)" onclick="InOne()" class="btn">最近一天</a>
					<a href="javascript:void(0)" onclick="InThree()" class="btn">最近三天</a>
					<div class="pbr"></div>
					<a href="javascript:void(0)" onclick="readGj()"  class="btn btn-red">读取轨迹</a>
					<!--<a   id="Button1" class="btn btn-red">读取轨迹</a>-->
					<div class="pbr"></div>
					<DIV  style="display:none">
						<FONT size=2>播放速度:</FONT>
						<SELECT id=playspeed >
							<OPTION value=1>
								1
							</OPTION>
							<OPTION value=2>
								2
							</OPTION>
							<OPTION value=3>
								3
							</OPTION>
							<OPTION value=4>
								4
							</OPTION>
							<OPTION selected value=5>
								5
							</OPTION>
							<OPTION value=6>
								6
							</OPTION>
							<OPTION value=7>
								7
							</OPTION>
							<OPTION value=8>
								8
							</OPTION>
							<OPTION value=9>
								9
							</OPTION>
						</SELECT>
						&nbsp;9为最慢，1为最快
					</DIV>
					<div class="pbr"></div>
					<!-- class="btn btn-blue" btn-green -->
						<button class="btn btn-green" id="btnPause"   ty="zt" onclick="window.parent.SRoute.Pause()">暂停</button>
					<button class="btn btn-green" id="btnStart"  style="display:none"  onclick="window.parent.SRoute.Continue()">继续</button>
				
					<button class="btn btn-green" id="btnStop"    onclick="window.parent.SRoute.Clear()" >停止</button>
					<div class="pbr"></div>
				</div>
			</div>
		</div>
	<div  style="display: none;">
				<div id="dd" style="top: 10px;height: 550px;width: 500px">
					<iframe id="ifr" width="100%" height="100%" src="" frameborder="0"></iframe>
				</div>
	</div>
	</body>
</html>


