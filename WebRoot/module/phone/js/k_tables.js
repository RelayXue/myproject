// k_tables控件

var js = document.scripts;
var root_path = js[js.length-1].src.substring(0,js[js.length-1].src.lastIndexOf("/"));
root_path = root_path.substring(0, root_path.lastIndexOf("/")+1);

$(function(){

					//多选框  <div class="ckeb" name="xxx">xxx</div>
					$(".ckeb").click(function(){
						if($(this).attr("checked") == "checked"){
								$(this).removeAttr("checked");
								$(this).css("background-image", "url("+root_path+"images/checked0.png)");
							ckebEvent(0, $(this))
						}else{
								$(this).attr("checked", "checked");
								$(this).css("background-image", "url("+root_path+"images/checked1.png)");
							ckebEvent(1, $(this))
						}
					});
					
					//单选框
					$(".riob").click(function(){
						var name = $(this).attr("name");
						if($(this).attr("roed") != "roed"){
							var sel_obj = $("[name='"+name+"'][roed='roed']");
							sel_obj.removeAttr("roed");
							sel_obj.css("background-image", "url("+root_path+"/images/radio.png)");
							$(this).attr("roed", "roed");
							$(this).css("background-image", "url("+root_path+"/images/radio1.png)");
							riobEvent($(this), name);
						}
					});
});
				
					/* .ckeb 多选框重新方法*/
					function ckebEvent(status, obj){}
					/* .riob 单选框重新方法*/
					function riobEvent(obj, group_name){}

					/* 初始化标签转换 */
					function initHeader(index){
						$(".header a").click(function(){
							$(".cnt").css("display","none");
							$(".header a").attr("class", "tab");
							$(this).attr("class", "tab_hover");
							$($(this).attr("href")).css("display","block");
							return false;
						});
						$(".header a").eq(index).click();
					}

					/* 初始化标签转换 */
					function initTab(ix, father_lab, per_lab, a_link, a_hover){
						$(father_lab + " " + per_lab).click(function(){
							var tab_page_index = $(father_lab + " " + per_lab).index(this);
							if(initTabOnClick(tab_page_index) != false){
								$(father_lab + " " + per_lab).each(function(index, element) {
									$("#" + $(this).attr("href")).hide();
									var css = $(this).attr("class");
									if(css == undefined) css="";
									if(css.indexOf(a_hover) >= 0){
										$(this).attr("class", css.replace(a_hover, "  "+a_link+"  "));		
									}else{ }
								});
								
								$("#" + $(this).attr("href")).show();
								//$(this).attr("class", a_hover);
								var css = $(this).attr("class");
								if(css == undefined) css="";
								if(css.indexOf(a_link) >= 0){
									$(this).attr("class", css.replace(a_link, "  "+a_hover+"  "));		
								}else{ }
								try{sessionStorage.setItem("tab_page_index", tab_page_index);}catch(e){}
							}
	
						});
						 $(father_lab + " " + per_lab).eq(ix).click();
					}
					
		/* 设置左按钮 */
		function setLeftMenuButton(txt, click){
			$("#left_menu_btn").remove();
			if(txt != null)	$("nav").append('<a href="javascript:'+click+'" class="aL sin" id="left_menu_btn"><span>'+txt+'</span></a>');
		}
		/* 设置右按钮 */
		function setRightMenuButton(txt, click){
			$("#right_menu_btn").remove();
			if(txt != null) $("nav").append('<span onclick="'+click+'" class="more aR" id="right_menu_btn" >'+txt+'</span>');
		}
					
					function initTabOnClick(index){ return true; }
					
					/* 菜单显示隐藏 */
					function boxToogle(id){
						if($("#"+id).css("display") == "block"){
							$("#"+id).remove();
							$("#"+id+"_overlay").remove();
						}else{
							$("#"+id).show();
							var height = $(document).height();
							$("body").append('<div class="overlay" onclick=boxToogle("'+id+'") id="'+id+'_overlay" style="display:block;height:'+height+'px"></div>');
						}
					}

/* simple dialog */		
function dialog(mes){
	if(mes != null && mes != undefined && mes.length > 0){
		$(".dialog").remove();
		var html = '<div class="dialog">'+mes+'</div>';
		$("body").append(html);
		var d_w = $(".dialog").width();
		var d_h = $(".dialog").height();
		$(".dialog").css("top", ($(window).height() - d_h) / 2);
		$(".dialog").css("left", ($(window).width() - d_w) / 2);
		$(".dialog").show().delay(2000).fadeOut(600, function(){
			$(this).remove();
		});
	}
}
function dialogForBig(mes){
	if(mes != null && mes != undefined && mes.length > 0){
		$(".big_dialog").remove();
		var html = '<div class="big_dialog">'+mes+'</div>';
		$("body").append(html);
		var d_w = $(".big_dialog").width();
		var d_h = $(".big_dialog").height();
		$(".big_dialog").css("top", ($(window).height() - d_h) / 2);
		$(".big_dialog").css("left", ($(window).width() - d_w) / 2);
		$(".big_dialog").show().delay(2000).fadeOut(600, function(){
			$(this).remove();
		});
	}
}
/* 可回调的dialog */
function dialog(mes, callback){
	if(mes != null && mes != undefined && mes.length > 0){
		$(".dialog").remove();
		var html = '<div class="dialog">'+mes+'</div>';
		$("body").append(html);
		var d_w = $(".dialog").width();
		var d_h = $(".dialog").height();
		$(".dialog").css("top", ($(window).height() - d_h) / 2);
		$(".dialog").css("left", ($(window).width() - d_w) / 2);
		$(".dialog").show().delay(2000).fadeOut(600, function(){
			$(this).remove();
			eval(callback);
		});
	}
}

/* 添加货物dialog */
function showAddDialog(id){
	var html = "";
	html += '<div id="'+id+'" class="addGoodsDialog" style="z-index:5;" >';
	html += '<div class="dialog-body">';
	html += '<h2>添加产品信息</h2>';
	html += '<div class="dialog-content">';
	html += '<div class="listC g_12">';
	html += '<div class="ctr"><span class="short">名称</span><input type="text" class="ce_inp" id="b_title" /></div>';
	html += '<div class="ctr"><span class="short">长</span><input type="text" class="ce_inp" placeholder="单位(MM)" id="b_len" /></div>';
	html += '<div class="ctr"><span class="short">宽</span><input type="text" class="ce_inp" placeholder="单位(MM)" id="b_wid" /></div>';
	html += '<div class="ctr"><span class="short">高</span><input type="text" class="ce_inp" placeholder="单位(MM)" id="b_hei" /></div>';
	html += '<div class="ctr"><span class="short">数量</span><input type="number" class="ce_inp" id="b_count" /></div>';
	html += '<div class="ctr"><span class="short">单位</span><select class="ce_inp" style="height:26px; width:160px;" id="b_unit">';
	html += '<option value="个">个</option>';
	html += '<option value="套">套</option>';
	html += '<option value="件">件</option>';
	html += '<option value="只">只</option>';
	html += '<option value="台">台</option>';
	html += '</select></div></div></div>';
	html += '<div class="g_12" style="margin-top:-5px">';
	html += '<a href=javascript:if(saveGoods()==1){boxToogle("'+id+'");$("#'+id+'").remove();} class="button green mbom mtp allp" style="width:78px">保存</a>';
	html += '<a href=javascript:boxToogle("'+id+'");$("#'+id+'").remove(); class="button grey mbom mtp allp" style="width:78px">取消</a>';
	html += '</div></div></div>';
	
	$("body").append(html);
	boxToogle(id);
}

/* confirmDialog */
function confirmDialog(id,content,yes_txt,no_txt,method){
	var html = "";
	html += '<div id="'+id+'" class="addGoodsDialog" style="z-index:5;" >';
	html += '<div class="dialog-body">';
	html += '<div class="dialog-content">';
	html += '<div style="padding:15px 10px; text-align:center;">'+content+'</div>';
	if(no_txt == null){
		html += '<a href=javascript:'+method+'(1);boxToogle("'+id+'"); class="button green mbom mtp allp" style="width:215px;">'+yes_txt+'</a>';
	} else{
		html += '<a href=javascript:'+method+'(1);boxToogle("'+id+'"); class="button green mbom mtp allp" style="width:89px; float:left;">'+yes_txt+'</a>';
		html += '<a href=javascript:'+method+'(0);boxToogle("'+id+'"); class="button grey mbom mtp allp" style="width:89px; float:left;">'+no_txt+'</a>';
	}
	html += '</div></div></div>';
	
	$("body").append(html);
	boxToogle(id);
}

/* 初始化列表多选框 */
function initListChoose(cla){
					$("."+cla).css("background-image", "url("+root_path+"images/checked0.png)");
					$("."+cla).css("background-position", "99% center");
					$("."+cla).click(function(){
						if($(this).attr("checked") == "checked"){
								$(this).removeAttr("checked");
								$(this).css("background-image", "url("+root_path+"images/checked0.png)");
							ckebEvent(0, $(this))
						}else{
								$(this).attr("checked", "checked");
								$(this).css("background-image", "url("+root_path+"images/checked1.png)");
							ckebEvent(1, $(this))
						}
					});
}

/* 初始化列表多选框 - 自定义图标 */
function initListChooseForDiy(cla, icon0, icon1, width, height){
					$("."+cla).css("background-image", "url("+root_path+"images/"+icon0+")");
					$("."+cla).css("background-size", width+"px "+height+"px");
					$("."+cla).css("background-position", "99% center");
					$("."+cla).click(function(){
						if($(this).attr("checked") == "checked"){
								$(this).removeAttr("checked");
								$(this).css("background-image", "url("+root_path+"images/"+icon0+")");
							ckebEvent(0, $(this))
						}else{
								$(this).attr("checked", "checked");
								$(this).css("background-image", "url("+root_path+"images/"+icon1+")");
							ckebEvent(1, $(this))
						}
					});
}

/* 初始化列表单选框 */
function initListSingleChoose(cla){
$("."+cla).css("background-image", "url("+root_path+"images/radio.png)");
$("."+cla).css("background-position", "99% center");
					$("."+cla).click(function(){
						var name = $(this).attr("name");
						if($(this).attr("roed") != "roed"){
							var sel_obj = $("[name='"+name+"'][roed='roed']");
							sel_obj.removeAttr("roed");
							sel_obj.css("background-image", "url("+root_path+"/images/radio.png)");
							$(this).attr("roed", "roed");
							$(this).css("background-image", "url("+root_path+"/images/radio1.png)");
							riobEvent($(this), name);
						}
					});
}
