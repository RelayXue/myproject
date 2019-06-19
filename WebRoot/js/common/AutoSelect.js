$.fn.AutoSelect = function(options) {
	var defaults = $.extend({
		data : "", // 数据源
		initCode : "001", // 初始代码值
		showNum : 1, // 第几级开始显示
		name : "", // 生成select name属性
		disabled : 0,      // 能否选择 数字为极数
		OnlyShow :0        //只显示级数
	}, options);
	var opts = $.extend(defaults, options);
	var id = $(this).attr("id");
	this.AutoSelect.show(id, options);
};
$.fn.AutoSelect.show = function(id, option) {
	if (option.index == null) {
		option.index = 1;
	}
	$("#" + id).empty();
	var num = 1;
	if (option.initCode != null) {
		num = option.initCode.length / 3;
	} else {
		option.initCode = "";
	}
	var sel = "";
	for ( var b = 1; b <= num + 1; b++) {
		sel = "<select class='select-type' style='border: 1px solid #bfbfbf' name='" + option.name + "' id='" + id + "_" + b
				+ "'><option value='-1'>请选择";
		for ( var a = 0; a < option.data.length; a++) {
			if (option.data[a].code.length == 3 * (b)
					&& option.data[a].code.substring(0, 3 * (b - 1)) == (option.initCode
							.substring(0, 3 * (b - 1)))) {
				if (option.initCode != null
						&& option.initCode.substring(0, 3 * (b)) == option.data[a].code) {
					sel += "<option selected value=" + option.data[a].code
							+ ">" + option.data[a].fullname;
				} else {
					sel += "<option value=" + option.data[a].code + ">"
							+ option.data[a].fullname;
				}
			}
		}
		sel += "</select>";
		$("#" + id).append(sel);
		$("select[id='" + id + "_" + b + "']").each(
				function() {
					if ($(this).find("option").length <= 1) {
						$(this).remove();
					}
					if (option.showNum != null) {
						for ( var c = 1; c < option.showNum; c++) {
							$("select[id='" + id + "_" + c + "']").remove();
						}
					}
					if (option.disabled != 0) {
						$("select[id='" + id + "_" + option.disabled + "']")
						.attr("disabled", "disabled");
					}
					if (option.OnlyShow != 0) {
						$("select[id='" + id + "_" + option.OnlyShow + "'] + select")
								.remove();
					}
				});
		$("select[id='" + id + "_" + b + "']").change(function() {
			var val = $(this).val();
			var name = $(this).attr("id");
			var index = name.split("_")[1];
			if (val != -1) {
				$("#" + id).AutoSelect({
					data : option.data,
					initCode : val,
					showNum : option.showNum,
					name : option.name,
					disabled : option.disabled,
					OnlyShow : option.OnlyShow
					
				});
			} 

		});
	}
};
