$.fn.AutoPage = function(options) {
	var defaults = $.extend({
		totalPage : 0, // 总条数
		currentPage: 1,// 当前页
		pageSize: 10,// 每页显示数
		showNum : 6,  // 显示数字
		show:1,       //显示类型 1默认显示 0只显示上一页下一页 
		url:'javascript:void(0)'
	}, options);
	var opts = $.extend(defaults, options);
	var id = $(this).attr("id");
	this.AutoPage.form(id,options);
	this.AutoPage.show(id,options);
};
$.fn.AutoPage.show = function(id,options) {
	if(options.show==null){
		options.show=1;
	}
	var div=$("#"+id);
	var url=options.url;
	var index=parseInt(options.totalPage/options.pageSize);
	if(options.totalPage%options.pageSize!=0){
		index+=1;
	}
	var content="<div class='rpage'><div class='rpage_num clearfix'>";
	if(options.currentPage==1){
		content+="<span class='disable'><a href='javascript:void(0)' class='perall'></a></span><span class='disable'><a href='javascript:void(0)' class='per'></a></span>";
	}else{
//		前面
		content+="<span class=''><a href='javascript:AutoSubmit("+(1)+","+index+")' class='perall'></a></span><span class=''><a href='javascript:AutoSubmit("+(parseInt(options.currentPage)-1)+","+index+")' class='per'></a></span>";
	}
	
//	中间
	content+="<span>第</span>";
	content+="<input name='' id='cur' type='text' value='"+options.currentPage+"'>";
	content+="<span>/ "+index+" 页</span>";
	
	
	if(options.currentPage==index){
		content+="<span class='disable'><a href='javascript:void(0)' class='next'></a></span>";
		content+="<span class='disable'><a href='javascript:void(0)' class='nextall'></a></span>";
	}else{
//		后面
		content+="<span><a href='javascript:AutoSubmit("+(parseInt(options.currentPage)+1)+","+index+")' class='next'></a></span>";
		content+="<span><a href='javascript:AutoSubmit("+index+","+index+")' class='nextall'></a></span>";
	}
	content+="</div>";
	if(options.currentPage==index){
		content+="<div class='fr'>第"+((options.currentPage-1)*options.pageSize+1)+"到"+options.totalPage+"条数据  共计 "+options.totalPage+"条</div>";
	}else{
		content+="<div class='fr'>第"+((options.currentPage-1)*options.pageSize+1)+"到"+((options.currentPage)*options.pageSize)+"条数据  共计 "+options.totalPage+"条</div>";
	}
	content+="</div>";
	div.append(content);
	
	$('#cur').keypress(function (e) {  
	    var key = e.which;  
	    if (key == 13) {
	    	AutoSure(index);
	    }
	});
};
$.fn.AutoPage.form = function(id,options) {
	var content="<div><form id='Autoform' method='post' action='"+options.url+"' >";
	content+="<input type='hidden' name='indexPage' value='"+options.currentPage+"'>";
	content+="</form></div>";
	$("#"+id).append(content);
};

function AutoSubmit(currentPage,index){
	if(currentPage>index){
		return;
	}
	if(currentPage<1){
		return;
	}
	$("input[name='indexPage']").attr("value",currentPage);
	$("#Autoform").submit();
}
function AutoSure(index){
	var currentPage=$("#cur").val();
	if(currentPage>index){
		return;
	}
	if(isNaN(currentPage)){
		return;
	}
	AutoSubmit(currentPage,index);
}