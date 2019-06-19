/**
 * @author xiao
 * @see 判断文件后缀名
 * @param v
 * @returns {Boolean}
 */
function CheckPic(v) {
	if (v.indexOf(".") > 0) {
		var o = v.split(".");
		var e = o[o.length - 1].toLowerCase();
		if (e != "gif" && e != "jpeg"&& e != "png"&& e != "bmp"&& e != "pic"&& e != "jpg")
			return false;
		else
			return true;
	}
}


function Utility() { };
/*
获取指定(NAME:PARAM)浏览器地址参数.
*/
Utility.GetWebParamValue = function(param) {
    var params = document.location.search;
    var sIndex = params.indexOf("?");
    if (sIndex != -1) {
        params = params.substring(1, params.length);
        if (params.indexOf("&") != -1) {
            params = params.split("&");
            for (var i = 0; i < params.length; i++) {
                if (params[i].indexOf("=") != -1) {
                    name = params[i].split("=")[0];
                    if (name.toLowerCase() == param.toLowerCase()) {
                        return params[i].split("=")[1];
                        break;
                    }
                }
            }
        } else {
            if (params.indexOf("=") != -1) {
                name = params.split("=")[0];
                if (name.toLowerCase() == param.toLowerCase()) {
                    return params.split("=")[1];
                }
            }
        }
    }
    return "";
};