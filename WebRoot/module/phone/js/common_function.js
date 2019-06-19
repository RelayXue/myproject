// JavaScript Document


function toBack(){
	history.go(-1);
}

jQuery.fn.limit=function(){ 
    var self = $("[limit]"); 
    self.each(function(){ 
        var objString = $(this).text(); 
        var objLength = $(this).text().length; 
        var num = $(this).attr("limit");
        if(objLength > num){ 
        	$(this).attr("title",objString); 
            objString = $(this).text(objString.substring(0,num) + "..."); 
        } 
    }); 
};

function getNowDate(date){
	var year = (date.getYear() < 1000)?date.getYear()+1900:date.getYear();
	var month = date.getMonth()+1;
	if(month < 10) month = "0"+month;
	var day = date.getDate();
	if(day < 10) day = "0"+day;
	return year+"-"+month+"-"+day;
}

/**
 * 将UTC格式字符串转换成本地格式
 * @param date
 * @returns
 */
function parseDate(date_str){
	var date = new Date();
	date.setTime(Date.parse(date_str));
	return getNowDate(date);
}

function parseDate2(date_str){
	var date = new Date();
	date.setTime(Date.parse(date_str));
	var year = (date.getYear() < 1000)?date.getYear()+1900:date.getYear();
	var month = date.getMonth()+1;
	if(month < 10) month = "0"+month;
	var day = date.getDate();
	if(day < 10) day = "0"+day;
	return year+"-"+month+"-"+day+" "+date.getHours()+":"+date.getMinutes();
}

//随机字符串
function randomStr() {
    var x = "123456789poiuytrewqasdfghjklmnbvcxzQWERTYUIPLKJHGFDSAZXCVBNM";
    var str = "";
    for (var i = 0; i < 5; i++) {
        str += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
    }
    return str;
}

function isNull(str){
	if(str == undefined || str == "" || str == null || str == "null" || str == "undefined"){
		return true;
	}
	return false;
}
//检查字符串
function checkNull(str, def_str){
	if(isNull(str)){
		return def_str;
	}
	return str;
}