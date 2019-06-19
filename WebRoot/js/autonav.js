function navAuto(){
	var clientWidth = parseInt(document.documentElement.clientWidth);
	if (clientWidth <= 1024)
	{
		$(".nav-c li a").css("paddingLeft",8+"px");
		$(".nav-c li a").css("paddingRight",8+"px");
	}
	else if(clientWidth <= 1353)
	{
		$(".nav-c li>a").css("paddingLeft",9+"px");
		$(".nav-c li>a").css("paddingRight",9+"px");
	}
	else if(clientWidth <= 1600){
		$(".nav-c li>a").css("paddingLeft",14+"px");
		$(".nav-c li>a").css("paddingRight",14+"px");
	}
	else{
		$(".nav-c li>a").css("paddingLeft",20+"px");
		$(".nav-c li>a").css("paddingRight",20+"px");
	}
		$(".nav-c li li a").css("paddingLeft",0+"px");
		$(".nav-c li li a").css("paddingRight",0+"px");
		var contentWidth = ($("#content").css("width"));
		$("#header").css("width",contentWidth);
		$('#main').height($(this).height()-150);
}

navAuto();
$(window).bind("resize", navAuto);