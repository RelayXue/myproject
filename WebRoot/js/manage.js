$(document).ready(function(){


$(".row1").mouseover(function () {
   $(this).removeClass("row1").addClass("rowhover");
});
$(".row2").mouseover(function () {
   $(this).removeClass("row2").addClass("rowhover");
});
$(".row1").mouseout(function () {
   $(this).removeClass("rowhover").addClass("row1");
});
$(".row2").mouseout(function () {
   $(this).removeClass("rowhover").addClass("row2");
});

$('#navmenu-h ul li').each(function(index) {
			 if ($(this).find("ul").length > 0) 
				 $(this).prepend("<div class='getSubItem'></div>"); 
		});


 });
 
 function add_zero(temp){ if(temp<10) return "0"+temp; else return temp;}

function getCurDate()
{ 
var d = new Date(); 
var week; 
switch (d.getDay())
{ 
case 1: week="星期一";
 break;
  case 2: week="星期二";
   break; 
   case 3: week="星期三";
    break; 
	case 4: week="星期四";
	 break; 
	 case 5: week="星期五";
	  break; 
	  case 6: week="星期六";
	   break; 
	   default: week="星期日";
	    } 
		var years = d.getYear(); 
		if (years < 1900)
		{
			years = years + 1900;
			}
		var month = add_zero(d.getMonth()+1); 
		var days = add_zero(d.getDate()); 
		var ndate = years+"年 "+month+"月"+days+"日 "+week;
		$("#date").html(ndate);
		//divT.innerHTML= ndate;
}
 
stuHover = function() {   
var cssRule;   
var newSelector;      
var g=document.getElementById("navmenu-h");
if(g!=null){
	var getElm = g.getElementsByTagName("LI");   
	for (var i=0; i<getElm.length; i++) {   
	   getElm[i].onmouseover=function() {   
	    this.className+=" iehover";   
	   }   
	   getElm[i].onmouseout=function() {   
	    this.className=this.className.replace(new RegExp(" iehover\\b"), "");   
	   }   
	} 
}
}   
if (window.attachEvent) stuHover();
getCurDate();