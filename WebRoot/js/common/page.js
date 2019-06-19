$.fn.AutoPage = function(options) {
	var defaults = $.extend({
		totalPage : 0, // 总页数
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
	if(index<=1){
		return;
	}
	var content="";
	if(options.show==0){
		 content+="<a class='page pre' href='javascript:AutoSubmit("+(parseInt(options.currentPage)-1)+","+index+")'>上一页</a>";
		 content+="<div class='current-page hui-12-41'><span class='orange-12-ar'>"+options.currentPage+"</span>/<span class='hui-12-41-ar'>"+index+"</span>页</div>";
	}else{
		 content+="<div class='current-page hui-12-41'>当前是第<span class='orange-12-ar'>"+options.currentPage+"</span>/<span class='hui-12-41-ar'>"+index+"</span>页</div>";
		 content+="<a class='page pre' href='javascript:AutoSubmit("+(1)+","+index+")'>首页</a> <a class='page pre' href='javascript:AutoSubmit("+(parseInt(options.currentPage)-1)+","+index+")'>上一页</a>";
	}
	if(index<=options.showNum&&options.show==1){
		for(var a=1;a<index+1;a++){
			content+="<a href='javascript:AutoSubmit("+(a)+","+index+")'>"+a+"</a>";
			
		}
	}
	if(index>options.showNum&&options.show==1){
		if(options.currentPage<=options.showNum){
			for(var a=1;a<options.showNum+1;a++){
				content+="<a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a>";
			}
			content+="<a class='more'>……</a>";
		}else{  
			var t=parseInt((index)/options.showNum);                       
			var j=parseInt(options.currentPage/options.showNum);     
			if(options.currentPage%options.showNum==0){
				j-=1;
			}
			if(j>=1&&j<t){
				for(var a=(parseInt(options.showNum)*j+1);a<(parseInt(options.showNum)*(j+1)+1);a++){
					content+="<a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a>";
				}
				content+="<a class='more'>……</a>";
			}else{ 
				for(var a=(parseInt(options.showNum)*j+1);a<=index;a++){
					content+="<a href='javascript:AutoSubmit("+(a)+","+index+")')>"+a+"</a>";
				}
			}
		}
		
	}
	if(options.show==0){
		content+="<a class='page next' href='javascript:AutoSubmit("+(parseInt(options.currentPage)+1)+","+index+")'>下一页</a> ";
	}else{
		content+="<a class='page next' href='javascript:AutoSubmit("+(parseInt(options.currentPage)+1)+","+index+")'>下一页</a>  <a class='page next' href='javascript:AutoSubmit("+index+","+index+")'>尾页</a>";
		content+="<div class='turn-page'><span>转到第</span> <span><input type='text' class='page-text' value='"+options.currentPage+"' /></span> <span>页</span></div>";
		content+="<div class='page-btn'><input type='button' onclick='AutoSure("+index+")' class='input-page-btn' value='确定' /></div>";
	}
	div.attr("class","page-list");
	div.append(content);
	var a=$(".page-list").find("a");
	a.each(function (i){
		if(a.eq(i).html()==options.currentPage){
			$(this).attr("class","hover");
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
	var currentPage=$(".page-text").val();
	if(isNaN(currentPage)){
		return;
	}
	AutoSubmit(currentPage,index);
}