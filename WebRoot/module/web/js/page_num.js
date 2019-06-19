//if (totalsize > numdisplay*2) {
//			if (totalsize-currentpage+1 >= numdisplay*2) {
//if(shownum != null && shownum == 1){
//					link+="...";
//					for(var a=totalsize-numdisplay;a<totalsize;a++){
//						link += " <a href=javascript:page_url(" + totalsize + "," + a + ",'" + url + "')>" + a + "</a> ";
//					}
//				}
function page_url(totalsize, currentpage, _url) {

	var url = window.location.href;
	var a = url.indexOf("?");
	var par = "";
	var parameter = "";       //原参数
	if (a > 0) {
		url = url.substring(a + 1, url.length);
		var par = url.split("&");
		if (par != null && par.length > 0) {
			for (var a = 0; a < par.length; a++) {
				if (par[a].indexOf("_pn") < 0) {
					parameter += par[a] + "&";
				}
			}
		}
	}
	parameter = parameter.substring(0, parameter.length - 1);
	currentpage = currentpage == 0 ? 1 : currentpage;
	currentpage = currentpage > totalsize ? totalsize : currentpage;
	var _parameter = "_pn=" + totalsize + "," + currentpage;    //页码参数
	if (parameter != null && parameter.length > 0) {
		if (url.indexOf("?") > 0) {
			_url = _url + "?" + parameter + "&" + _parameter;
		} else {
			_url = _url + "?" + parameter + "&" + _parameter;
		}
	} else {
		if (url.indexOf("?") > 0) {
			_url = _url + "?" + _parameter;
		} else {
			_url += "?" + _parameter;
		}
	}
	window.location.href = _url;
}
//totalsize 总页数    currentpage 当前页数  numdisplay  中间数字的长度  url 跳转的url  shownum 1只显示上一页 下一页
function createurl(totalsize, currentpage, numdisplay, _url, shownum) {
	var url = window.location.href;
	var a = url.indexOf("?");
	if (a >= 0) {
		var parameter = url.substring(a + 1, url.length);
		var par = "";
		var parameter1 = parameter.split("&");
		if (parameter1 != null && parameter1.length > 0) {
			for (var a = 0; a < parameter1.length; a++) {
				if (parameter1[a].indexOf("_pn") >= 0) {
					par = parameter1[a];
					par = par.substring(4, par.length);
					var par = par.split(",");
					totalsize = Number(par[0]);
					currentpage = Number(par[1]);
					break;
				}
			}
		}
	}
	url = _url;
	var div = $("#page");
	var link = "";
	if (totalsize > 0) {
		if (shownum != null && shownum == 1) {
				var link = " <span onclick=\"page_url(" + totalsize + "," + (currentpage - 1) + ",'" + url + "')\">\u4e0a\u4e00\u9875</span>";
		} else {
			if(currentpage==1){
				var link = "<span>\u9996\u9875</span> <span>\u4e0a\u4e00\u9875</span>";
			}else{
				var link = "<span onclick=\"page_url(" + totalsize + ",1,'" + url + "')\">\u9996\u9875</span> <span onclick=\"page_url(" + totalsize + "," + (currentpage - 1) + ",'" + url + "')\">\u4e0a\u4e00\u9875</span>";
			}
		}
		if (totalsize > numdisplay) {
			if (Math.round(numdisplay / 2) >= currentpage) {
				for (var a = 1; a <= numdisplay; a++) {
					if (shownum != null && shownum == 1) {
						if (currentpage == a) {
							link += " <span class='checked'>" + a + "/" + totalsize + "</span> ";
						} else {
							link += " <span onclick=\"page_url(" + totalsize + "," + a + ",'" + url + "')\">" + a + "</span> ";
						}
					} else {
						if (currentpage == a) {
							link += " <span class='checked'>" + a + "</span> ";
						} else {
							link += " <span onclick=\"page_url(" + totalsize + "," + a + ",'" + url + "')\">" + a + "</span>";
						}
					}
				}
			} else {
				if (numdisplay / 2 < currentpage && currentpage < (totalsize - numdisplay / 2)) {
					for (var a = currentpage - Math.floor(numdisplay / 2); a < currentpage + numdisplay / 2; a++) {
						if (shownum != null && shownum == 1) {
							if (currentpage == a) {
								link += " <span class='checked'>" + a + "/" + totalsize + "</span> ";
							} else {
								link += " <span onclick=\"page_url(" + totalsize + "," + Number(a) + ",'" + url + "')\">" + Number(a) + "</span> ";
							}
						} else {
							if (currentpage == a) {
								link += " <span class='checked'>" + a + "</span> ";
							} else {
								link += " <span onclick=\"page_url(" + totalsize + "," + Number(a) + ",'" + url + "')\">" + Number(a) + "</span>";
							}
						}
					}
				} else {
					if (currentpage >= (totalsize - numdisplay / 2)) {
						for (var a = totalsize - numdisplay + 1; a <= totalsize; a++) {
							if (shownum != null && shownum == 1) {
								if (currentpage == a) {
									link += "<span class='checked'>" + a + "/" + totalsize + "</span> ";
								} else {
									link += "<span onclick=\"page_url(" + totalsize + "," + a + ",'" + url + "')\">" + a + "</span> ";
								}
							} else {
								if (currentpage == a) {
									link += "<span class='checked'>" + a + "</span> ";
								} else {
									link += "<span onclick=\"page_url(" + totalsize + "," + a + ",'" + url + "')\">" + a + "</span> ";
								}
							}
						}
					}
				}
			}
			if (shownum != null && shownum == 1) {
				link += "<span  onclick=\"page_url(" + totalsize + "," + (currentpage + 1) + ",'" + url + "')\">\u4e0b\u4e00\u9875</span>";
			} else {
				if(currentpage==totalsize){
					link += "<span>\u4e0b\u4e00\u9875 \u5c3e\u9875</span>>";
				}else{
					link += "<span  onclick=\"page_url(" + totalsize + "," + (currentpage + 1) + ",'" + url + "')\">\u4e0b\u4e00\u9875</span> <span  onclick=\"page_url(" + totalsize + "," + totalsize + ",'" + url + "')\">\u5c3e\u9875</span>";
				}
				
			}
		} else {
			for (var a = 1; a <= totalsize; a++) {
				if (shownum != null && shownum == 1) {
					if (currentpage == a) {
						link += "<span  class='checked'>" + a + "/" + totalsize + "</span> ";
					} else {
						link += "<span><a href=javascript:page_url(" + totalsize + "," + a + ",'" + url + "')>" + a + "</a></span> ";
					}
				} else {
					if (currentpage == a) {
						link += "<span class='checked'>" + a + "</span> ";
					} else {
						link += "<span><a href=javascript:page_url(" + totalsize + "," + a + ",'" + url + "')>" + a + "</a></span> ";
					}
				}
			}
			if (shownum != null && shownum == 1) {
				//link += "<a href=javascript:page_url(" + totalsize + "," + (currentpage + 1) + ",'" + url + "')>\u4e0b\u4e00\u9875</a>";
			} else {
				if(currentpage==totalsize){
					//link += "\u4e0b\u4e00\u9875 \u5c3e\u9875";
				}else{
					//link += "<a href=javascript:page_url(" + totalsize + "," + (currentpage + 1) + ",'" + url + "')>\u4e0b\u4e00\u9875</a> <a  href=javascript:page_url(" + totalsize + "," + totalsize + ",'" + url + "')>\u5c3e\u9875</a>";
				}
			}
		}
	}
	div.empty().append(link);
}

