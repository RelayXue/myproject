
var temp;
var re;
$.fn.Autocomplete = function(options) {
	var defaults = $.extend({
		inputId : '', // input ID
		url: '',// 地址
		businessType:'' //业务参数
	}, options);
	var opts = $.extend(defaults, options);
	this.Autocomplete.show(options);
};
$.fn.Autocomplete.show = function(options) {
	$("#" + options.inputId).keyup(function () {
		$("#auto_"+options.inputId).css("display", "none");
		var parm = $(this).val();
		if (parm != null && parm.length > 0) {
			parm = encodeURIComponent(encodeURIComponent(parm));
			if(options.businessType=='DangerousShip'||options.businessType=='Pilot'){
				var wnumber=$("#wnumber").val();
				ajax_data(options.url + parm+"&wnumber="+wnumber);
			}else{
				ajax_data(options.url + parm);
			}
		} else {
			$("#auto_"+options.inputId).css("display", "none");
		}
		if (temp != null && temp.length > 0) {
			var data = temp;
			if (data != null && data.length > 0) {
				var table = "<table style=width:100%>";
				for (var a = 0; a < data.length; a++) {
					table += "<tr>";
					table += "<td onclick=indata('" + a + "','" + options.inputId + "','"+options.businessType+"')>";
					if (a % 2 == 0) {
						table += "<span class=complete id=data" + a + ">";
					} else {
						table += "<span class=complete1 id=data" + a + ">";
					}
					Strong(data[a].fullname, decodeURIComponent(decodeURIComponent(parm)));
					if (re != null && re.length > 0) {
						table += re;
					}
					table += "</span>";
					table += "</td>";
					table += "</tr>";
				}
				table += "</table>";
			}
			$("#auto_"+options.inputId).css("display", "block");
			$("#auto_"+options.inputId).html(table);
		}
	});
}

function ajax_data(url) {
	$.ajax({url:url + "&time=" + new Date(), async:false, contentType:"application/x-www-form-urlencoded;charset=UTF-8", success:function (data) {
		temp = eval(data);
	}});
}


function Strong(data, val) {
	data=data.toUpperCase();
	val=val.toUpperCase();
	var a = data.indexOf(val);
	if(a!=-1){
		var data1 = data.substr(0, a);
		var data2 = data.substr(a, val.length);
		var data3 = data.substr(a + val.length, data.length);
		re = data1 + "<strong>" + data2 + "</strong>" + data3;
	}
}

function indata(n, inputId,businessType) {
	var val=temp[n].fullname;
	$("#" + inputId).attr("value", val);
	$("#auto_"+inputId).css("display", "none");	
	if(businessType=='ship'){
		$("#nationality").attr("value", temp[n].nationality==null?"":temp[n].nationality);
		$("#englishname").attr("value", temp[n].englishname==null?"":temp[n].englishname);
		$("#captainc").attr("value", temp[n].captainc==null?"":temp[n].captainc);
		$("#code").attr("value", temp[n].code==null?"":temp[n].code);
		$("captaine").attr("value", temp[n].captaine==null?"":temp[n].captaine);
		$("#imo").attr("value", temp[n].imo==null?"":temp[n].imo);
		$("#nettonnage").attr("value", temp[n].tonnage==null?"":temp[n].tonnage);
		$("#clength").attr("value", temp[n].clength==null?"":temp[n].clength);
		$("#cwidth").attr("value", temp[n].cwidth==null?"":temp[n].cwidth);
		$("#dwt").attr("value", temp[n].dwt==null?"":temp[n].dwt);
		$("#color").attr("value", temp[n].color==null?"":temp[n].color);
		$("#ctype").attr("value", temp[n].ctype==null?"":temp[n].ctype);
		$("#approvedload").attr("value", temp[n].approvedload==null?"":temp[n].approvedload);
		$("#totaltonnage").attr("value", temp[n].totaltonnage==null?"":temp[n].totaltonnage);
		$("#nettonnage").attr("value", temp[n].nettonnage==null?"":temp[n].nettonnage);
		$("#cspeed").attr("value", temp[n].cspeed==null?"":temp[n].cspeed);
	}
	if(businessType=='DangerousShip'){
		$("#approvedload").attr("value", temp[n].dwt==null?"":temp[n].dwt);
		$("#shippingport").attr("value", temp[n].startport==null?"":temp[n].startport);
		$("#dischargeport").attr("value", temp[n].boundport==null?"":temp[n].boundport);
		$("#arrivaltime").attr("value", temp[n].coutcrew==null?"":temp[n].coutcrew);
		$("#leavetime").attr("value", temp[n].outcrew==null?"":temp[n].outcrew);
		$("#dapplicant").attr("value", temp[n].organizename==null?"":temp[n].organizename);
		$("#carrierunit").attr("value", temp[n].captainc==null?"":temp[n].captainc);
	}
	if(businessType=='dangerousgoods'){
		$("#countrycode").attr("value", temp[n].countrycode==null?"":temp[n].countrycode);
		$("#nature").attr("value", temp[n].nature==null?"":temp[n].nature);
		$("#specialrequirements").attr("value", temp[n].specialrequirements==null?"":temp[n].specialrequirements);
		$("#prevention").attr("value", temp[n].prevention==null?"":temp[n].prevention);
	}
	if(businessType=='Pilot'){
		$("#boardingaddress").attr("value", temp[n].preport==null?"":temp[n].preport);
		$("#disembarkationaddress").attr("value", temp[n].preport==null?"":temp[n].preport);
		//$("#boardingtime").attr("value", temp[n].pretime==null?"":temp[n].pretime);
		//$("#disembarkationtime").attr("value", temp[n].exptime==null?"":temp[n].exptime);
		$("#importdraft").attr("value", temp[n].maxdraft==null?"":temp[n].maxdraft);
		$("#totaltonnage").attr("value", temp[n].totaltonnage==null?"":temp[n].totaltonnage);
		$("#dwt").attr("value", temp[n].dwt==null?"":temp[n].dwt);
		$("#clength").attr("value", temp[n].clength==null?"":temp[n].clength);
		$("#cwidth").attr("value", temp[n].cwidth==null?"":temp[n].cwidth);
		$("#nettonnage").attr("value", temp[n].nettonnage==null?"":temp[n].nettonnage);
		$("#nationality").attr("value", temp[n].nationality==null?"":temp[n].nationality);
	}
	if(businessType=='port'){
		$("#myform").validate().resetForm();
	}
	$("#auto_"+inputId).html("");
}
/*function Autocomplete(inputId, options, url) {
$("#" + inputId).keyup(function () {
	$("#auto_"+inputId).css("display", "none");
	var parm = $(this).val();
	if (parm != null && parm.length > 0) {
		parm = encodeURIComponent(encodeURIComponent(parm));
		ajax_data(url + parm);
	} else {
		$("#auto_"+inputId).css("display", "none");
	}
	if (temp != null && temp.length > 0) {
		var data = temp;
		if (data != null && data.length > 0) {
			var table = "<table style=width:100%>";
			for (var a = 0; a < data.length; a++) {
				table += "<tr>";
				table += "<td onclick=indata('" + a + "','" + inputId + "')>";
				if (a % 2 == 0) {
					table += "<span class=complete id=data" + a + ">";
				} else {
					table += "<span class=complete1 id=data" + a + ">";
				}
				Strong(data[a].fullname, decodeURIComponent(decodeURIComponent(parm)));
				if (re != null && re.length > 0) {
					table += re;
				}
				table += "</span>";
				table += "</td>";
				table += "</tr>";
			}
			table += "</table>";
		}
		$("#auto_"+inputId).css("display", "block");
		$("#auto_"+inputId).html(table);
	}
});
}*/
