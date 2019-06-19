// JavaScript Document

//点赞(div id,数据id,点赞类型1新闻)
function clickLike(div_id,data_id,category){
	var click_praise_history = localStorage.getItem("click_praise_history");
	if(click_praise_history == null){
		click_praise_history = "";
	}
	if(click_praise_history.indexOf(data_id) >= 0){ return; }
	var parms = new Object();
	parms.id = data_id;
	parms.category = category;
	$.post("phone_main_clickLike", parms, function(json) {
		var code = json.code;
		var hint = json.hint;
		if (code == 1) {
			//$("#"+div_id).html(count);
			click_praise_history += ","+data_id;
			localStorage.setItem("click_praise_history", click_praise_history);
			setLikeImg(div_id, data_id, json.os.count);
		}
	}, "json");
}

//设置点赞图片
function setLikeImg(div_id, data_id, count){
	var click_praise_history = localStorage.getItem("click_praise_history");
	var img = "";
	if(click_praise_history != null && click_praise_history.indexOf(data_id) >= 0){
		img = "dmz3.png";
	}else{
		img = "dmz2.png";
	}
	$("#"+div_id).html('<img src="images/'+img+'" />&nbsp;'+count);
}