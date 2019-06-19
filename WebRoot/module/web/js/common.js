/**
 * 添加
 */
function add(url){
	window.location.href=url;
};
/**
 * 修改
 */
function edit(url,id){ 
	window.location.href=url+"?id="+id;
};
/**
 * 删除
 */
function del(url,id){
	if(confirm("你确定要删除所选的记录么?")){
		window.location.href=url+"?id="+id;
		
	}
};
//删除人员
function delPeople(url,id,flag){
	if(confirm("你确定要删除所选的记录么?")){
		window.location.href=url+"?id="+id+"&flag="+flag;
	}
}

//添加
function baseAdd(url){
	window.location.href=url;
}
//修改
function baseEdit(url,param1,param2,param3){
	window.location.href=url+"?"+param1+"&"+param2+"&"+param3;
}
//删除
function baseDel(url,param1,param2,param3){
	if(confirm("你确定要删除所选的记录么?")){
		window.location.href=url+"?"+param1+"&"+param2+"&"+param3;
	}
}

/**
 * 修改字典
 */
function editData(url,id,parentCode){ 
	window.location.href=url+"?id="+id+"&parentCode="+parentCode;
};

//删除字典
function delData(url,id,parentCode){
	if(confirm("你确定要删除所选的记录么?")){
		window.location.href=url+"?id="+id+"&parentCode="+parentCode;
	}
}
//跳转
function redirect(url){
	window.location.href=url;
}
//查看
function view(url,id){
	window.location.href=url+"?id="+id;
}
//查看房屋
function viewHouse(url,id){
	if(id.length==0){
		alert("该人员当前租住信息未找到！");
		return;
	}
	window.location.href=url+"?id="+id;
}
/**
 * 
 */
function delParentMenu(id){
	if(confirm("你确定要删除所选的记录么?")){
		var data = "id="+id;
        $.ajax({
           type:"post",
           url:"auth/menu_delParentMenu",
           data:data,
           dataType:"json",
           success:function(data){
        	  if(!data.flag){
				alert(data.message);
			  }else{
				  window.location.href="auth/menu_list";
			  }
           },
           error:function(data){
        	   alert("运行出错！");
           }
        });
	}
};
function enable(url,abbr,enabled,id){
	if(enabled==1){
		enabled=0;
	}else{
		enabled=1;
	}
	window.location.href=url+"?id="+id+"&"+abbr+"="+enabled;
};
/**
 * 升序
 */
function orderUp(url,abbr,order,id){
	order = parseInt(order)+1;
	window.location.href=url+"?id="+id+"&"+abbr+"="+order;
};
/**
 * 降序
 */
function orderDown(url,abbr,order,id){
	order = parseInt(order)-1;
	window.location.href=url+"?id="+id+"&"+abbr+"="+order;
};

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
Array.prototype.in_array = function(e)  
{  
for(i=0;i<this.length;i++)  
{  
if(this[i] == e)  
return true;  
}  
return false;  
}; 

//格式化日期
Date.prototype.format = function(format){ 
	var o = { 
			"M+" : this.getMonth()+1, //month 
			"d+" : this.getDate(), //day 
			"h+" : this.getHours(), //hour 
			"m+" : this.getMinutes(), //minute 
			"s+" : this.getSeconds(), //second 
			"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
			"S" : this.getMilliseconds() //millisecond 
			}; 
	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
};

/**
 * 使用方法 
 * var now = new Date();
 * var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
 * 使用方法2: 
 * var testDate = new Date(); 
 * var testStr = testDate.format("YYYY年MM月dd日hh小时mm分ss秒"); 
 * alert(testStr); 
 * 示例： 
 * alert(new Date().Format("yyyy年MM月dd日")); 
 * alert(new Date().Format("MM/dd/yyyy")); 
 * alert(new Date().Format("yyyyMMdd")); 
 * alert(new Date().Format("yyyy-MM-dd hh:mm:ss"));
 * 
 */
 
/*
 * 获取指定(NAME:PARAM)浏览器地址参数.
 */
function GetWebParamValue(param) {
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
// js获取url参数的function
function request(paras){
var url = location.href;   // url
var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");  
var paraObj = {};   // 参数组
for (i=0; j=paraString[i]; i++){  
paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf
("=")+1,j.length);  
}  
var returnValue = paraObj[paras.toLowerCase()];  
if(typeof(returnValue)=="undefined"){  
return "";  
}else{  
return returnValue;  
}  
}
var theurl;
theurl=request("url");
if (theurl!=''){
location=theurl;
} 

function DateAdd(interval,number,date)
{
/*
 *--------------- DateAdd(interval,number,date) -----------------
 * DateAdd(interval,number,date) 
 * 功能:实现VBScript的DateAdd功能.
 * 参数:interval,字符串表达式，表示要添加的时间间隔.
 * 参数:number,数值表达式，表示要添加的时间间隔的个数.
 * 参数:date,时间对象.
 * 返回:新的时间对象.
 * var now = new Date();
 * var newDate = DateAdd("d",5,now);
 *--------------- DateAdd(interval,number,date) -----------------
 */
    switch(interval)
    {
        case "y" : {
            date.setFullYear(date.getFullYear()+number);
            return date;
            break;
        }
        case "q" : {
            date.setMonth(date.getMonth()+number*3);
            return date;
            break;
        }
        case "m" : {
            date.setMonth(date.getMonth()+number);
            return date;
            break;
        }
        case "w" : {
            date.setDate(date.getDate()+number*7);
            return date;
            break;
        }
        case "d" : {
            date.setDate(date.getDate()+number);
            return date;
            break;
        }
        case "h" : {
            date.setHours(date.getHours()+number);
            return date;
            break;
        }
        case "m" : {
            date.setMinutes(date.getMinutes()+number);
            return date;
            break;
        }
        case "s" : {
            date.setSeconds(date.getSeconds()+number);
            return date;
            break;
        }
        default : {
            date.setDate(d.getDate()+number);
            return date;
            break;
        }
    }
}

/**
 * 添加标签
 * @param title 标题
 * @param url 链接地址
 * @param refresh 是否刷新
 */
function tabs(title, url,navIcon,refresh) {
	if ($('#tab').tabs('exists', title)) {
		$('#tab').tabs('select', title);
		if(refresh){
			refreshTab({title:title,url:url});
		}
	} else {
		if (url){
            var content = '<iframe frameborder="0" src="'+url+'" style="width:'+clientWidth()+'px;height:100%;"></iframe>';  
        } else {  
            var content = '未实现';  
        }  
		$('#tab').tabs('add', {
			border : false,
			title : title,
			content:content,
			closable : true,
			iconCls:navIcon
		});
	}
}

/**
 * 刷新标签页
 * @param obj 标签页对象
 */
function refreshTab(obj){  
	var refresh_tab = obj.title?$('#tab').tabs('getTab',obj.title):$('#tab').tabs('getSelected');  
	if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		var refresh_iframe = refresh_tab.find('iframe')[0];  
		var refresh_url = obj.url?obj.url:refresh_iframe.src;  
		refresh_iframe.contentWindow.location.href=refresh_url;  
	}  
}

/**
 * 添加或修改信息
 * @param url 地址
 * @param isEdit 编辑or添加
 * @param isTreeList 是否为treegrid
 * @param code 标识码
 */
function addOrEdit(url,flag,isTreeList,id,orgCode,code,params){
	var rows = [];
	if(isTreeList){
		rows = $("#listgrid").treegrid("getSelections");
	}else{
		rows = $("#listgrid").datagrid("getSelections");
	}
	//ognl异常
	var param = "?1=1";
	if(flag=="edit"){
		if(rows.length != 1){
			$.messager.alert('提示信息', '请选择需要编辑的数据!', 'info');
			return;
		}
		if(id != null&&id != ""){
			param += "&id="+rows[0][id];
		}
	}
	if(orgCode != null&&orgCode != ""){
		param += "&orgCode="+rows[0][orgCode];
	}
	if(code != null&&code != ""){
		param += "&code="+code;
	}
	if(params!=undefined&&params!=null&&params!=""){
		param += "&"+params;
	}
	window.location.href=url+param;
}

/**
 * 删除
 * @param url 链接地址
 * @param gridId 列表grid ID
 * @param isTreeList 是否为treegrid
 * @param code 标识码
 */
function delGrid(url,isTreeList,id){
	var rows = [];
	if(isTreeList){
		rows = $("#listgrid").treegrid("getSelections");
	}else{
		rows = $("#listgrid").datagrid("getSelections");
	}
	if (rows.length > 0) {
		$.messager.confirm('提示信息','确定要删除选择的数据吗?', function(r){
			if(r){
				var data = "1=1";
				if(id != null&&id != ""){
					data += "&id="+rows[0][id];
				}
				$.ajax({
					type:"post",
					url:url,
					data:data,
					dataType:"json",
					success:function(data){
						if(!data.flag){
							$.messager.alert('提示信息',data.message,'info'); 
						}else{
							if(isTreeList){
								$("#listgrid").treegrid('reload');
								$("#listgrid").treegrid('clearSelections');
							}else{
								$("#listgrid").datagrid('reload');
								$("#listgrid").datagrid('clearSelections');
							}
							
						}
					},
					error:function(data){
						$.messager.alert('提示信息',"运行出错！","error"); 
					}
				});
			}
		});
	}else{
		$.messager.alert('提示信息', '请选择要删除的数据！','info');
	}
}

/**
 * 重新加载grid
 * @param gridId id
 * @param isTree 是否为treegrid
 */
function reloadGrid(isTreeGrid) {
	if(isTreeGrid){
		$("#listgrid").treegrid('reload');
	}else{
		$("#listgrid").datagrid('reload');
	}
}

function viewGrid(url,isTreeList,id){
	var rows = [];
	if(isTreeList){
		rows = $("#listgrid").treegrid("getSelections");
	}else{
		rows = $("#listgrid").datagrid("getSelections");
	}
	//ognl异常
	if(rows.length != 1){
		$.messager.alert('提示信息', '请先选择一个班组!', 'info');
		return;
	}
	var param = "";
	if(id != null&&id != ""){
		param += "?id="+rows[0][id];
	}else{
		return;
	}
	window.location.href=url+param;
}

/**
 * datagrid自适应屏幕分辨率宽度
 * @param percent 百分比
 */
function fixWidth(percent){
    return clientWidth() * percent ; //这里你可以自己做调整  
}

/**
 * 取得当前窗口宽度
 * @returns {Number}
 */
function clientWidth(){
    return document.body.clientWidth-2; //这里你可以自己做调整  
}

/**
 * 角色分配权限
 * @param gridId ID
 * @param url 链接地址
 * @param code 组织标识码
 */
function configAuth(url){
	var rows = $("#listgrid").datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('提示信息', '请选择需要分配权限的角色!', 'info');
		return;
	}
	window.location.href=url+"?code="+rows[0].code+"&id="+rows[0].roleId;
}

/**
 * 用户角色分配
 * @param url 链接地址
 * @param code 组织标识码
 */
function configRole(url){
	var rows = $("#listgrid").datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('提示信息', '请选择需要分配角色的用户!', 'info');
		return;
	}
	window.location.href=url+"?code="+rows[0].code+"&id="+rows[0].userId;
}

/**
 * 用户群组分配
 * @param url 链接地址
 * @param code 组织标识码
 */
function configGroup(url){
	var rows = $("#listgrid").datagrid("getSelections");
	if(rows.length != 1){
		$.messager.alert('提示信息', '请选择需要分配群组的用户!', 'info');
		return;
	}
	window.location.href=url+"?code="+rows[0].code+"&id="+rows[0].userId;
}

function ajax(url,data){
	$.ajax({
		type:"post",
		url:url,
		data:data,
		dataType:"json",
		success:function(data){
			if(!data.flag){
				//失败
				$.messager.alert('提示信息',data.message,'error'); 
			}else{
				//成功
				$.messager.alert('提示信息',data.message,'info');
				history.back();
			}
		},
		error:function(data){
			$.messager.alert('提示信息',"运行出错！","error"); 
		}
	});
}




/**
 * 判断数组中是否包含某对象
 */
Array.prototype.contains = function(obj){
	for(var i=0;i<this.length;i++){
		if(this[i].id == obj.id){
			return true;
		}
	}
	return false;
};

Array.prototype.replace = function(obj){
	for(var i=0;i<this.length;i++){
		if(this[i].id==obj.id){
			this[i] = obj;
		}
	}
};
Array.prototype.find = function(id){
	for(var i=0;i<this.length;i++){
		if(this[i].id==id){
			return this[i];
		}
	}
	return null;
};

Array.prototype.has = function(id){
	for(var i=0;i<this.length;i++){
		if(this[i] == id){
			return true;
		}
	}
	return false;
};

/**  
 * Simple Map  
 *   
 *   
 * var m = new Map();  
 * m.put('key','value');  
 * ...  
 * var s = "";  
 * m.each(function(key,value,index){  
 *      s += index+":"+ key+"="+value+"/n";  
 * });  
 * alert(s);  
 *   
 * @author dewitt  
 * @date 2008-05-24  
 */  
function Map() {   
    /** 存放键的数组(遍历用到) */  
    this.keys = new Array();   
    /** 存放数据 */  
    this.data = new Object();   
       
    /**  
     * 放入一个键值对  
     * @param {String} key  
     * @param {Object} value  
     */  
    this.put = function(key, value) {   
        if(this.data[key] == null){   
            this.keys.push(key);   
        }   
        this.data[key] = value;   
    };   
       
    /**  
     * 获取某键对应的值  
     * @param {String} key  
     * @return {Object} value  
     */  
    this.get = function(key) {   
        return this.data[key];   
    };   
       
    /**  
     * 删除一个键值对  
     * @param {String} key  
     */  
    this.remove = function(key) {   
        this.keys.remove(key);   
        this.data[key] = null;   
    };   
       
    /**  
     * 遍历Map,执行处理函数  
     *   
     * @param {Function} 回调函数 function(key,value,index){..}  
     */  
    this.each = function(fn){   
        if(typeof fn != 'function'){   
            return;   
        }   
        var len = this.keys.length;   
        for(var i=0;i<len;i++){   
            var k = this.keys[i];   
            fn(k,this.data[k],i);   
        }   
    };   
       
    /**  
     * 获取键值数组(类似Java的entrySet())  
     * @return 键值对象{key,value}的数组  
     */  
    this.entrys = function() {   
        var len = this.keys.length;   
        var entrys = new Array(len);   
        for (var i = 0; i < len; i++) {   
            entrys[i] = {   
                key : this.keys[i],   
                value : this.data[i]   
            };   
        }   
        return entrys;   
    };   
       
    /**  
     * 判断Map是否为空  
     */  
    this.isEmpty = function() {   
        return this.keys.length == 0;   
    };   
       
    /**  
     * 获取键值对数量  
     */  
    this.size = function(){   
        return this.keys.length;   
    };   
       
    /**  
     * 重写toString   
     */  
    this.toString = function(){   
        var s = "{";   
        for(var i=0;i<this.keys.length;i++,s+=','){   
            var k = this.keys[i];   
            s += k+"="+this.data[k];   
        }   
        s+="}";   
        return s;   
    };   
}

/**
 * 首页页面缓存
 */
function pageCache(url,code){
	//$(body).append();
}

/**
 * 选中的权限
 */
function fillData(id,configId,node,checked){
	menuData = [];
	var nodes = $('#'+id).tree('getChecked');
    for(var i=0; i<nodes.length; i++){
    	var isLeaf = $('#'+id).tree('isLeaf',nodes[i].target);
    	if(isLeaf){
    		//取出叶子节点父节点
    		var parent = $('#'+id).tree('getParent',nodes[i].target);
    		if(parent!=null){
    			var parentt = $('#'+id).tree('getParent',parent.target);
        		//判断父节点是否有父节点
        		if(!parentt){
        			//没有，说明为一级菜单
        			if(menuData.contains(parent)){
        				//已存在则不作处理
        			}else{
        				menuData.push(parent);
        			}
        			menuData.push(nodes[i]);
        		}else{
        			//说明为二级菜单...
        			if(!menuData.contains(parentt)){
        				menuData.push(parentt);
        			}
        			var t = menuData.find(parent.id);
        			if(t){
        				//存在
        				if(!t.children){
        					t.children = [];
        				}
        				t.children.push(nodes[i]);
        				menuData.replace(t);
        			}else{
        				//不存在
        				parent.children = [];
        				parent.children.push(nodes[i]);
        				menuData.push(parent);
        			}
        			t = null;
        		}
        		parentt=null;
    		}else{
    			menuData.push(nodes[i]);
    		}
    		parent = null;
    		
    	}else{
    		if(!menuData.contains(nodes[i])){
    			menuData.push(nodes[i]);
    		}
    	}
    }
    $('#'+configId).tree({data:menuData,animate:true});
}

/**
 * 初始化树形控件
 */
function initTree(id,configId,type,url){
	$('#menuTree').tree({
		url:'roleAuth_menuAuthData?id='+id,
		idField:'id',
		textField:'text',
		animate:true,
		checkbox:true,
		lines:true,
		loadMsg:'正在加载。。。',
		onBeforeLoad:function(node, param){
		    loading=true; 
		},
		onLoadSuccess:function(node, data){ 
		    loading=false; 
		    fillData(id,configId,"","");
		}, 
		onCheck:function(node, checked){
			if(!loading){
				fillData(id,configId,node, checked);
			}
		}
	});
}

/**
 * 保存配置
 */
function saveConfig(id,configId){
	$("#"+id).click(function(){
		if(menuData.length==0){
			$.messager.alert('提示信息', '您还没有选择菜单信息！','info');
		}else{
			var data = [];
			for(var i=0;i<menuData.length;i++){
				if(!data.has(menuData[i].id)){
					data.push(menuData[i].id);
				}
				var children = menuData[i].children;
				if(children){
					for(var j=0;j<children.length;j++){
						if(!data.has(children[j].id)){
							data.push(children[j].id);
						}
					}
				}
			}
			ajax("roleAuth_saveMenuAuth","id="+$("#roleId").val()+"&codes="+data.join("#"));
			menuData = null;
		}
	});
}

/**
 * 重新登录
 */
function reset(username,userpass){
	var data= "userName="+username+"&userPass="+userpass;
	$.ajax({
        type:"post",
        url:"/resetLogin",
        data:data,
        dataType:"json",
        success:function(data){
        	if(data.flag){
        		//登录成功,关闭窗口
        		window.parent.close();
        	}else{
        		//登录失败
        		window.parent.window.$.messager.alert('登录失败',data.message,"error");
        	}
        },
        error:function(data){
        	window.parent.window.$.messager.alert('提示信息',"运行出错！","error");
        }
     });
}

function getYestoday(date){    
	var yesterday_milliseconds=date.getTime()-1000*60*60*24;     
	var yesterday = new Date();     
	    yesterday.setTime(yesterday_milliseconds);     
	  
	var strYear = yesterday.getFullYear();  
	var strDay = yesterday.getDate();  
	var strMonth = yesterday.getMonth()+1;
	if(strMonth<10)  
	{  
		strMonth="0"+strMonth;  
	}  
	datastr = strYear+"-"+strMonth+"-"+strDay;
	return datastr;
  }
function statusFormate(value){
	var name = "";
	if(value==0){
		name = "<span style='color:#007500'>正常使用</span>";
	}else if(value==1){
		name="<span style='color:#6C6C6C'>已删除</span>";
	}else if(value==2){
		name="<span style='color:#6C6C6C'>已禁用</span>";
	}else if(value==3){
		name="<span style='color:#00DB00'>待审核</span>";
	}else if(value==4){
		name="<span style='color:#00BB00'>待制卡</span>";
	}else if(value==5){
		name="<span style='color:#FF8040'>已退回</span>";
	}else if(value==6){
		name="<span style='color:#FF8040'>待办居住证</span>";
	}else if(value==8){
		name="<span style='color:#000000;font-weight: bold;'>黑名单</span>";
	}else if(value==9){
		name="<span style='color:#009600;'>已打印</span>";
	}else{
		name="<span style='font-weight: bold;'>未知</span>";
	}
	return name;
}

	/*
	 * 将form对象直接序列成对象，一般配合datagrid使用
	 */
	  $.fn.serializeObject = function() {
	    if ( !this.length ) { return false; }
	    var $el = this,
	      data = {},
	      lookup = data; //TODO current reference of data
	      $el.find(':input[type!="checkbox"][type!="radio"][name], input:checked').each(function() {
	        var named = this.name.replace(/\[([^\]]+)?\]/g, ',$1').split(','),
	            cap = named.length - 1,
	            i = 0;
	        for ( ; i < cap; i++ ) {
	            lookup = lookup[ named[i] ] = lookup[ named[i] ] ||
	                ( named[i+1] == "" ? [] : {} );
	        }
	        if ( lookup.length != undefined ) {
	             lookup.push( $(this).val() );
	        }else {
	              lookup[ named[ cap ] ]  = $(this).val();
	        }
	        lookup = data;
	      });
	    return data;
	  };
	  //TODO 通过name  和   val选中radio 
	  $.fn.selectRadio = function(name,val){
		  this.find("input[type='radio'][name='"+name+"']").each(function(){
			  this.checked=false;
				if(this.value==val)
					this.checked=true;
		  });
	  };
	  $.checkBox=function(name){
	 	    var areaids=[];
	         $("input[type='checkbox'][name='"+name+"']:checked").each(function(){
		                areaids.push($(this).val());
	         });
         return areaids.join(",");
    };
