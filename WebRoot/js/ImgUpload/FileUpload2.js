	var ImgUpNum = 0;
	var reNum="";	
$.fn.uploadInit = function(options) {
	$("#up_btn").uploadPreview({
		Img : "ImgPr",
		Width : 120,
		Height : 120,
		ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
		Callback : function() {
		}
	});
	 
	$("#AddBtn")
	.click(
			function() {
				ImgUpNum = ImgUpNum + 1;
				$("#file_div")
				.append(
						'<div><input type="file" name="img" id="up_btn'+ ImgUpNum+ '" /><div><img style="border:1px solid gray" src="/images/tishi.png" id="ImgPr'+ ImgUpNum
						+ '" width="120px" height="120px"  /></div><div style="margin-left: 140px;margin-top: -16px;"><a href="javascript:remove('+ImgUpNum+')"><img src="/images/cancel.png"></a></div></div>');
				$("#up_btn" + ImgUpNum).uploadPreview({
					Img : "ImgPr" + ImgUpNum,
					Width : 120,
					Height : 120,
					ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
					Callback : function() {
					}
				});
				
			});
	
		$("#AddBtn").click();
	
};
$.fn.uploadShow = function(options) {
	var defaults = $.extend({
		data : '',
		path :''  // 图片相对路径
	}, options);
	var opts = $.extend(defaults, options);
	 ImgUpNum = options.data.split(",").length-1;


	$("#AddBtn")
	.click(
			function() {
				ImgUpNum = ImgUpNum + 1;
				$("#file_div")
				.append(
						'<div><input type="file" name="img" id="up_btn'+ ImgUpNum+ '" /><div><img style="border:1px solid gray" src="/images/tishi.png" id="ImgPr'+ ImgUpNum
						+ '" width="120px" height="120px" /></div><div style="margin-left: 140px;margin-top: -16px;"><a href="javascript:remove('+ImgUpNum+')"><img src="/images/cancel.png"></a></div></div>');
				$("#up_btn" + ImgUpNum).uploadPreview({
					Img : "ImgPr" + ImgUpNum,
					Width : 120,
					Height : 120,
					ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
					Callback : function() {
					}
				});
				
			});
		this.uploadShow.show(options);
};
$.fn.uploadShow.show = function(options) {
	var temp=options.data;
	 var path=options.path;

	 if(temp!=null&&temp.length>0&&temp!='null'){
		 var data=temp.split(",");
		 for(var a=0;a<data.length;a++){
			 $("#file_div")
				.append(
						'<img style="border:1px solid gray;margin-left: 20px" src='+path+data[a]+' id="ImgPr' + a
						+ '" width="120px" height="120px" onclick="show(this.id)"/>');
				$("#up_btn" + a).uploadPreview({
					Img : "ImgPr" + a,
					Width : 120,
					Height : 120,
					ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
					Callback : function() { }
		 });
	 }
}
}


function remove(num){
	$("input[id='up_btn"+ num+ "']").parent().remove();
	reNum+=num+",";
	//$("input[id='up_btn"+ num+ "']").parent().remove();
	
}

////----------------------------------------图片处理后----------------------------------------------------

var ImgUpNum_ = 0;
var reNum_="";	
$.fn.uploadInit_ = function(options) {
$("#up_btn_").uploadPreview({
	Img : "ImgPr_",
	Width : 120,
	Height : 120,
	ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
	Callback : function() {
	}
});
 
$("#AddBtn_")
.click(
		function() {
			ImgUpNum_ = ImgUpNum_ + 1;
			$("#file_div_")
			.append(
					'<div><input type="file" name="img_" id="up_btn_'+ ImgUpNum_+ '" /><div><img style="border:1px solid gray" src="/images/tishi.png" id="ImgPr_'+ ImgUpNum_
					+ '" width="120px" height="120px"  /></div><div style="margin-left: 140px;margin-top: -16px;"><a href="javascript:remove_('+ImgUpNum_+')"><img src="/images/cancel.png"></a></div></div>');
			$("#up_btn_" + ImgUpNum_).uploadPreview({
				Img : "ImgPr_" + ImgUpNum_,
				Width : 120,
				Height : 120,
				ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
				Callback : function() {
				}
			});
			
		});

	$("#AddBtn_").click();

};
$.fn.uploadShow_ = function(options) {
var defaults = $.extend({
	data : '',
	path :''  // 图片相对路径
}, options);
var opts = $.extend(defaults, options);
 ImgUpNum_ = options.data.split(",").length-1;


$("#AddBtn_")
.click(
		function() {
			ImgUpNum_ = ImgUpNum_ + 1;
			$("#file_div_")
			.append(
					'<div><input type="file" name="img_" id="up_btn_'+ ImgUpNum_+ '" /><div><img style="border:1px solid gray" src="/images/tishi.png" id="ImgPr_'+ ImgUpNum_
					+ '" width="120px" height="120px" /></div><div style="margin-left: 140px;margin-top: -16px;"><a href="javascript:remove_('+ImgUpNum_+')"><img src="/images/cancel.png"></a></div></div>');
			$("#up_btn_" + ImgUpNum_).uploadPreview({
				Img : "ImgPr_" + ImgUpNum_,
				Width : 120,
				Height : 120,
				ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
				Callback : function() {
				}
			});
			
		});
	this.uploadShow.show_(options);
};
$.fn.uploadShow.show_ = function(options) {
var temp=options.data;
 var path=options.path;

 if(temp!=null&&temp.length>0&&temp!='null'){
	 var data=temp.split(",");
	 for(var a=0;a<data.length;a++){
		 $("#file_div_")
			.append(
					'<div><input type="file" name="img_" id="up_btn_' + a + '" /><div><img style="border:1px solid gray" src='+path+data[a]+' id="ImgPr_' + a
					+ '" width="120px" height="120px" /></div><div style="margin-left: 140px;margin-top: -16px;"><a href="javascript:remove_('+a+')"><img src="/images/cancel.png"></a></div></div>');
			$("#up_btn_" + a).uploadPreview({
				Img : "ImgPr_" + a,
				Width : 120,
				Height : 120,
				ImgType : [ "gif", "jpeg", "jpg", "bmp", "png" ],
				Callback : function() { }
	 });
 }
}
}


function remove_(num){
$("input[id='up_btn_"+ num+ "']").parent().remove();
reNum_+=num+",";

}


