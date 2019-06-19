var TJSoft = {};
TJSoft.map = {
		config:{
			map:null,
			//默认地图类别
			mapType:5,
			mapTypeObject:null,
			//监控刷新时间
			monitorIntervalTime:'30s',
			//监控页异步加载标志
			monitorAsync:false,
			curAsyncCount:0,
			curStatus:"init",
			goAsync:false,
			//报警查询时间
			alarmIntervalTime:'15s',
			//车辆编号长度、用于区分车辆与部门
			carIdLength:16,
			iconType:{
				monitorIcon:"monitorIcon",
				monitorRunIcon:"monitorRunIcon",
				monitorStopIcon:"monitorStopIcon",
				monitorAlarmIcon:"monitorAlarmIcon",
				historyIcon:"historyIcon",
				historyPlayIcon:"historyPlayIcon",
				historyAlarmIcon:"historyAlarmIcon",
				hnCheckIcon:"/images/v_mappoint.png"
			},
			monitorIcon:{
				north:"/Resource/img/icon/carNexact0.gif",
				northeast:"/Resource/img/icon/carNexact1.gif",
				east:"/Resource/img/icon/carNexact2.gif",
				southeast:"/Resource/img/icon/carNexact3.gif",
				south:"/Resource/img/icon/carNexact4.gif",
				southwest:"/Resource/img/icon/carNexact5.gif",
				west:"/Resource/img/icon/carNexact6.gif",
				northwest:"/Resource/img/icon/carNexact7.gif"
			},
			monitorRunIcon:{
				north:"/Resource/img/icon/carRun0.gif",
				northeast:"/Resource/img/icon/carRun1.gif",
				east:"/Resource/img/icon/carRun2.gif",
				southeast:"/Resource/img/icon/carRun3.gif",
				south:"/Resource/img/icon/carRun4.gif",
				southwest:"/Resource/img/icon/carRun5.gif",
				west:"/Resource/img/icon/carRun6.gif",
				northwest:"/Resource/img/icon/carRun7.gif"
			},
			monitorStopIcon:{
				north:"/Resource/img/icon/carStop0.gif",
				northeast:"/Resource/img/icon/carStop1.gif",
				east:"/Resource/img/icon/carStop2.gif",
				southeast:"/Resource/img/icon/carStop3.gif",
				south:"/Resource/img/icon/carStop4.gif",
				southwest:"/Resource/img/icon/carStop5.gif",
				west:"/Resource/img/icon/carStop6.gif",
				northwest:"/Resource/img/icon/carStop7.gif"
			},
			monitorAlarmIcon:{
				north:"/Resource/img/icon/carAlarm0.gif",
				northeast:"/Resource/img/icon/carAlarm1.gif",
				east:"/Resource/img/icon/carAlarm2.gif",
				southeast:"/Resource/img/icon/carAlarm3.gif",
				south:"/Resource/img/icon/carAlarm4.gif",
				southwest:"/Resource/img/icon/carAlarm5.gif",
				west:"/Resource/img/icon/carAlarm6.gif",
				northwest:"/Resource/img/icon/carAlarm7.gif"
			},
			historyIcon:{
				north:"/Resource/img/icon/carRun0.gif",
				northeast:"/Resource/img/icon/carRun1.gif",
				east:"/Resource/img/icon/carRun2.gif",
				southeast:"/Resource/img/icon/carRun3.gif",
				south:"/Resource/img/icon/carRun4.gif",
				southwest:"/Resource/img/icon/carRun5.gif",
				west:"/Resource/img/icon/carRun6.gif",
				northwest:"/Resource/img/icon/carRun7.gif"
			},
			historyPlayIcon:{
				north:"/Resource/img/icon/run0.png",
				northeast:"/Resource/img/icon/run1.png",
				east:"/Resource/img/icon/run2.png",
				southeast:"/Resource/img/icon/run3.png",
				south:"/Resource/img/icon/run4.png",
				southwest:"/Resource/img/icon/run5.png",
				west:"/Resource/img/icon/run6.png",
				northwest:"/Resource/img/icon/run7.png"
			},
			historyAlarmIcon:{
				north:"/Resource/img/icon/alarm0.png",
				northeast:"/Resource/img/icon/alarm1.png",
				east:"/Resource/img/icon/alarm2.png",
				southeast:"/Resource/img/icon/alarm3.png",
				south:"/Resource/img/icon/alarm4.png",
				southwest:"/Resource/img/icon/alarm5.png",
				west:"/Resource/img/icon/alarm6.png",
				northwest:"/Resource/img/icon/alarm7.png"
			}

		},
		init:function(){
			if (TJSoft.map.config.mapType == TMapType.TBaiDu) {
				TJSoft.map.config.map = new TBaiDu();
		    }else if(TJSoft.map.config.mapType == TMapType.TBaiDuW){
		    	TJSoft.map.config.map = new TBaiDuW();
		    }else if(TJSoft.map.config.mapType == TMapType.TGoogle){
		    	TJSoft.map.config.map = new TGoogle();
		    }else if(TJSoft.map.config.mapType == TMapType.TGoogleW){
		    	TJSoft.map.config.map = new TGoogleW();
		    }else if(TJSoft.map.config.mapType == TMapType.TTuJing){
		    	TJSoft.map.config.map = new TTuJing();
		    }else if(TJSoft.map.config.mapType == TMapType.TMapAbc){
		    	TJSoft.map.config.map = new TMapAbc();
		    }
			if(TJSoft.map.config.mapTypeObject == null){
				TJSoft.map.config.mapTypeObject = new TMapType();
			}
			TJSoft.map.config.map.Init();
		},
		changeMap:function(type,page){
			TJSoft.map.config.mapType = type;
			//$("#mapSwitch").html(mapTypeObject.GetMapTypeName(mapType));
			TJSoft.map.config.map = null;
			//1、清空地图图层
			$("#map").html("");
			//2、加载新地图
			this.init();
			TJSoft.cache.changeFlag = true;
			//3、绘制监控原有数据
			TJSoft.monitor.showIcon();
		},
		zoomIn:function(){
			TJSoft.map.config.map.ZoomIn();
		},
		zoomOut:function(){
			TJSoft.map.config.map.ZoomOut();
		},
		openMeasure:function(){
			TJSoft.map.config.map.OpenMeasure();
		},
		measureArea:function(){
			TJSoft.map.config.map.Roam();
		},
		getCenterPoint:function(){
			return TJSoft.map.config.map.GetCenterPoint();
		},
		panTo:function(p){
			TJSoft.map.config.map.PanTo(p);
		},
		fullScreen:function(id,div,iframe){
			//map.ClearAll();
			var _h = window.parent.document.documentElement.clientHeight;
		    var tlc = window.parent.document.getElementById(div);
		    if(tlc!=undefined){
		    	var topDiv = window.parent.document.getElementById(iframe);
			    var top = tlc.style.top;
			    if(top!="0px"){
			    	topDiv.style.height = _h+"px";
			    	tlc.style.top = "0px";
			    	tlc.style.height = _h+"px";
			        $(id).html("还原");
			    }else{
			    	topDiv.style.height = (_h  - 70) + "px";
			    	tlc.style.top = "70px";
			        $(id).html("全屏");
			    }
		    }
		},
		clearAll:function(){
			TJSoft.map.config.map.ClearAll();
		},
		addMarker:function(obj,iconType,show,flag,isHistory){
			if(obj==null){
				return;
			}
			//var icon = this.selectIcon(obj.direct,iconType);
			var content = this.windowInfo(obj);
			if(flag){
				TJSoft.map.config.map.AddOneMarker(obj,iconType,"20","32",content);
			}else{
				TJSoft.map.config.map.AddMarker(obj,iconType,"20","32",content,show,isHistory);
			}
			icon = null,content = null;
		},
		drawLine:function(arr){
			TJSoft.map.config.map.DrawLine(arr);
		},
		setViewPoint:function(points){
			TJSoft.map.config.map.SetViewport(points);
		},
		selectIcon:function(directNum,icontype){
			if(directNum>22.5&&directNum<67.5){
				//东北
				return TJSoft.map.config[icontype]["northeast"];
			}else if(directNum>67.5&&directNum<112.5){
				//正东
				return TJSoft.map.config[icontype]["east"];
			}else if(directNum>112.5&&directNum<157.5){
				//东南
				return TJSoft.map.config[icontype]["southeast"];
			}else if(directNum>157.5&&directNum<202.5){
				//正南
				return TJSoft.map.config[icontype]["south"];
			}else if(directNum>202.5&&directNum<247.5){
				//西南
				return TJSoft.map.config[icontype]["southwest"];
			}else if(directNum>247.5&&directNum<292.5){
				//正西
				return TJSoft.map.config[icontype]["west"];
			}else if(directNum>292.5&&directNum<337.55){
				//西北
				return TJSoft.map.config[icontype]["northwest"];
			}else{
				//正北 0<directNum<22.5||337.5<directNum<360
				return TJSoft.map.config[icontype]["north"];
			}
		},
		windowInfo:function(obj){
			var content = "<iframe id='countIframe' frameborder='0' style='height: 307px;z-index:4000; width: 428px; font-size: 12px; line-height: 20px;' src='search_viewPointDetail?id="+obj.id+"&type="+obj.type+"'></iframe>";
//			var content="<div style='font-size:16px;font-weight:bold;'>"+obj.name+"</div>";
//			content+="<div style='border-bottom:#BB4444 2px solid;height:5px;line-height:5px;'></div>";
//		    content+="<div style='width:200px;height:150px;overflow:auto;'>";
//		    content+="<table border='0' style='height:100%;width:100%'>";
//		    content+="<tr><td style='font-weight: bold;font-size:12px;'>所属公司:</td><td align='left'>华西建设公司</td></tr>";
//		    content+="<tr><td style='font-weight: bold;font-size:12px;'>昨日上工人数:</td><td align='left'><span style='color:red;font-size:20px;font-weight:bold;'>95</span>人</td></tr>";
//		    content+="<tr><td style='font-weight: bold;font-size:12px;'>今日上工人数:</td><td align='left'><span style='color:red;font-size:20px;font-weight:bold;'>暂未统计</span>人</td></tr>";
//		    content+="</table>";
//		    content+="</div>";
		    return content;
		}
		
};
TJSoft.common = {
		ajax:function(url,data,callback){
			$.ajax({
				type:"post",
				url:url,
				data:data,
				dataType:"json",
				success:callback,
				error:function(data){
					$.messager.alert('提示信息',"运行出错！","error");
					$.unblockUI();
				}
			});
		},
		startTimmer:function(time,name,func){
			//'ms': 1,'cs': 10,'ds': 100,'s': 1000,'das': 10000,'hs': 100000,'ks': 1000000
			$('body').everyTime(time,name,func);
		},
		stopTimmer:function(name){
			$('body').stopTime(name);
		}
};
TJSoft.highcharts = {
		init:function(url,param,id){
			TJSoft.common.ajax(url,param,function(d){
				$('#'+id).highcharts(d);
			});
		}
		
};
TJSoft.easyui = {
		layoutOpen:function(){
			$("#dataGridShow").layout("panel", "south").panel("open");
			$("#dataGridShow").layout("panel", "center").panel("restore");
			$("#dataGridShow").layout("resize");
		},
		layoutClose:function(){
			$("#dataGridShow").layout("panel", "south").panel("close");
			$("#dataGridShow").layout("panel", "center").panel("maximize");
			$("#dataGridShow").layout("resize");
		},
		onDblClickRow:function(rowIndex, rowData){
			alert(rowIndex);
		},onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			if(rowIndex<0){
				return;
			}
			$("#carGrid").datagrid('selectRow',rowIndex);
			//var selected=$("#carGrid").datagrid('getRows'); //获取所有行集合对象
			//selected[rowIndex].carLicenseNum; //index为当前右键行的索引，指向当前行对象
			$('#mm').menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		},
		tree:function(){

		}
};
TJSoft.ztree = {
		init:function(setting,data){
			$.fn.zTree.init($("#deviceTree"),setting,data);
			//$.fn.zTree.getZTreeObj("carTree").expandAll(true);
		},
		initCommon:function(id,setting,data,flag){
			$.fn.zTree.init($("#"+id),setting,data);
			if(flag){
				$.fn.zTree.getZTreeObj(id).expandAll(true);
			}

		},
		zTreeOnClick:function(event, treeId, treeNode){
			if(treeNode.checked){
				//取消勾选
				treeNode.checked = false;
				$.fn.zTree.getZTreeObj("carTree").updateNode(treeNode,true);
			}else{
				//勾选
				treeNode.checked = true;
				$.fn.zTree.getZTreeObj("carTree").updateNode(treeNode,true);
			}
		},
		zTreeOnCheck:function(event, treeId, treeNode){
			var data = [];
			var t = $.fn.zTree.getZTreeObj("carTree").getCheckedNodes(true);
			var len = 0;
			TJSoft.monitor.config.monitor = 0;
			if(t.length==0){
				TJSoft.common.stopTimmer('monitorTimmer');
				//清空列表
				TJSoft.monitor.layoutClose();
				$('#carGrid').datagrid('loadData', { total: 0, rows: [] });
				TJSoft.map.clearAll();
				TJSoft.cache.data = null;
				map.markers = null;
				data=null;t=null;ids = null;
				return;
			}
			TJSoft.map.clearAll();
			for(var i=0;i<t.length;i++){
				if(t[i].id.length<=TJSoft.map.config.carIdLength){
					data.push(t[i].id);
					len++;
				}
			}
			if(len>=10){
				//取消勾选
				$.messager.alert('提示信息',"实时监控最多可勾选10辆车！", 'info');
				$.fn.zTree.getZTreeObj("carTree").checkAllNodes(false);
				return;
			}
			if(data.length>0){
				TJSoft.monitor.config.ids ="ids="+data.join("#");
			}
			//clearAll();
			TJSoft.common.stopTimmer('monitorTimmer');
			TJSoft.monitor.layoutOpen();
			TJSoft.monitor.showIcon();
			TJSoft.common.startTimmer(TJSoft.map.config.monitorIntervalTime,'monitorTimmer',function(){
				if(TJSoft.monitor.config.ids.length==0){
					TJSoft.common.stopTimmer('monitorTimmer');
					TJSoft.cache.data = null;
					return;
				}
				TJSoft.monitor.showIcon();
			});
			data=null;t=null;
		},
		zTreeOnDblClick:function(event, treeId, treeNode){
			if(treeNode==null||treeNode.isParent){
				return;
			}
			TJSoft.common.ajax("car/active_data", "ids="+treeNode.id,function(d){
				if(d!=null&&d.flag!=null&&d.flag){
					$.messager.alert('提示信息',d.message, 'info');
					return;
				}
				//调整视野
				TJSoft.map.setViewPoint(d);
				for(var i=0;i<d.length;i++){
					var icon = TJSoft.map.config.iconType.monitorRunIcon;
					if(d[i].status==2){
						//停车
						icon = TJSoft.map.config.iconType.monitorStopIcon;
					}else if(d[i].status==4){
						//超速报警
						icon = TJSoft.map.config.iconType.monitorAlarmIcon;
					}
					TJSoft.map.addMarker(d[i],icon,true);
					icon = null;
				}
				d = null;
			});
		},

		zTreeOnAsyncError:function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown){
			$.messager.alert('提示信息',"数据加载出错,请刷新页面重试！", 'info');
		},
		zTreeOnAsyncSuccess:function(event, treeId, treeNode, msg){

		},
		addHoverDom:function(treeId, treeNode){
			if(treeNode.isParent){
				$("#treeTip").html("<div class='ztree'>"+treeNode.name+"</div>");
				$("#treeTip").css("top",($("#" + treeNode.tId + "_span").offset().top+20)+"px");
				$("#treeTip").css("left",($("#" + treeNode.tId + "_span").offset().left+3)+"px");
				$("#treeTip").css("visibility","visible");
			}
		},
		removeHoverDom:function(treeId, treeNode){
			$("#treeTip").html("");
			$("#treeTip").css("visibility","hidden");
		},
		showTitleForTree:function(treeId, treeNode){
			return treeNode.level == 2;
		},
		updateTreeNode:function(data){
			//查找节点
			var node = $.fn.zTree.getZTreeObj("carTree").getNodeByParam("id", data.carId, null);
			if(data.status==node.status){
				return;
			}
			if(data.status==1){
				//行驶
				node.icon = "/Resource/img/monitor/move.png";
				node.status = 1;
				TJSoft.ztree.updateParentTreeNode(node.getParentNode(),1);
			}else if(data.status==2){
				//停车
				node.icon = "/Resource/img/monitor/stop.png";
				node.status = 2;
				TJSoft.ztree.updateParentTreeNode(node.getParentNode(),2);
			}else{
				return;
			}
			TJSoft.map.addMarker(data[i],TJSoft.map.config.iconType.monitorIcon,false);
			$.fn.zTree.getZTreeObj("carTree").updateNode(node);
		},
		updateParentTreeNode:function(node,status){
			if(node == null||node.getParentNode()==null){
				return;
			}
			if(status==1){
				//行驶
				node.driveNum = node.driveNum+1;
				node.stopNum = node.stopNum-1;
			}else if(status==2){
				//停车
				node.driveNum = node.driveNum-1;
				node.stopNum = node.stopNum+1;
			}
			//更新本节点
			var name = "<span class='treename'>"+node.nameInit+"</span>";
			name+="<span class='statis sttt'>[</span>";
			name+="<span title='总数' class='statis sttn stt_total'>"+node.total+"</span>";
			name+="<span title='运行' class='statis sttn stt_move'>"+node.driveNum+"</span>";
			name+="<span title='停车' class='statis sttn stt_stop'>"+node.stopNum+"</span>";
			name+="<span class='statis sttt'>]</span>";
			node.name=name;
			$.fn.zTree.getZTreeObj("carTree").updateNode(node);
			name = null;
			if(node.getParentNode!=null){
				TJSoft.ztree.updateParentTreeNode(node.getParentNode(),status);
			}
		}

};

TJSoft.flexbox = {
		init:function(data){
			$('#autoInput').flexbox(data, {   
	    	    watermark: '请输入车牌号',
	    	    noResultsText: '未找到相关车辆！',
	    	    selectFirstMatch: true, // 是否自动选中第一个结果 
	    	    highlightMatches: true, // 高亮显示匹配的结果
	    	    showArrow: false, // 是否显示右边的倒三角，false：形如google提示样式
	    	    paging: false,
	    	    width: 140,
	    	    maxVisibleRows: 8,
	    	    onSelect: function() {
	    	    	var zTree = $.fn.zTree.getZTreeObj("carTree");
	    	    	zTree.selectNode(zTree.getNodeByParam("name",this.value, null));
	    	    	zTree = null;
	    	    	$("#hidCarbrand").val(this.value);
	            }
	    	});
		},
		initAsync:function(url,id){
			$('#'+id).flexbox(url, {
				method: 'POST',
				queryDelay: 150, // 查询延迟，在查询前的时间毫秒单位;默认为100毫秒
	    	    watermark: '请输入姓名或卡号',
	    	    noResultsText: '未找到相关人员！',
	    	    selectFirstMatch: true, // 是否自动选中第一个结果 
	    	    autoCompleteFirstMatch: false, // 是否自动根据第一条数据补充完成  
	    	    highlightMatches: true, // 高亮显示匹配的结果
	    	    showArrow: false, // 是否显示右边的倒三角，false：形如google提示样式
	    	    maxCacheBytes:0,
	    	    resultTemplate:'{name}-{id}',
	    	    hiddenValue:'id',
	    	    width: 150,
	    	    //maxVisibleRows: 5,//最大分页条数
	    	    paging:{  
                    style: 'input', // 跳转页数输入格式，默认输入框或是列表(1，2，3...)or 'links'  
                    cssClass: 'paging', // 分页样式 prefix with containerClass (e.g. .ffb .paging)  
                    pageSize: 8, // 每页分页数如果显示条数小于这个数则不显示分页按钮 acts as a threshold.  if <= pageSize results, paging doesn't appear  
                    maxPageLinks: 5, // 最大分页数字显示，只对分页链表样式有作用 used only if style is 'links'  
                    showSummary: true, // 是否显示分页(共 {total}条 当前{page}/{pages}页)提示信息 whether to show '共 {total}条 当前{page}/{pages}页' text  
                    summaryClass: 'summary', // 分页（共 {total}条 当前{page}/{pages}页）提示信息的样式 class for '共 {total}条 当前{page}/{pages}页', prefix with containerClass  
                    summaryTemplate: '共 {total}条 当前{page}/{pages}页' // can use {page} and {pages} as well  
                },
	    	    onSelect: function() {
//	    	    	$("#searchName").val(this.value);
	            }
	    	});
		},
		initAsyncOther:function(url,id){
			$('#'+id).flexbox(url, {
				method: 'POST',
				queryDelay: 200, // 查询延迟，在查询前的时间毫秒单位;默认为100毫秒
	    	    watermark: '请输入车牌号',
	    	    noResultsText: '未找到相关车辆！',
	    	    selectFirstMatch: true, // 是否自动选中第一个结果 
	    	    autoCompleteFirstMatch: false, // 是否自动根据第一条数据补充完成  
	    	    highlightMatches: true, // 高亮显示匹配的结果
	    	    showArrow: false, // 是否显示右边的倒三角，false：形如google提示样式
	    	    maxCacheBytes:0,
	    	    resultTemplate:'{name}',
	    	    hiddenValue:'{name}',
	    	    width: 110,
	    	    //maxVisibleRows: 5,//最大分页条数
	    	    paging:{  
                    style: 'input', // 跳转页数输入格式，默认输入框或是列表(1，2，3...)or 'links'  
                    cssClass: 'paging', // 分页样式 prefix with containerClass (e.g. .ffb .paging)  
                    pageSize: 5, // 每页分页数如果显示条数小于这个数则不显示分页按钮 acts as a threshold.  if <= pageSize results, paging doesn't appear  
                    maxPageLinks: 5, // 最大分页数字显示，只对分页链表样式有作用 used only if style is 'links'  
                    showSummary: true, // 是否显示分页(共 {total}条 当前{page}/{pages}页)提示信息 whether to show '共 {total}条 当前{page}/{pages}页' text  
                    summaryClass: 'summary', // 分页（共 {total}条 当前{page}/{pages}页）提示信息的样式 class for '共 {total}条 当前{page}/{pages}页', prefix with containerClass  
                    summaryTemplate: '共 {total}条 当前{page}/{pages}页' // can use {page} and {pages} as well  
                },
	    	    onSelect: function() {
	    	    	$("#hidCarbrand").val(this.value);
	            }
	    	});
		},
		search:function(){
			var name = $("#hidCarbrand").val();
			if(name==undefined||name.length==0){
				$.messager.alert('提示信息', '未找到该车辆信息!', 'info');
				return;
			}
			var zTree = $.fn.zTree.getZTreeObj("carTree");
	    	zTree.selectNode(zTree.getNodeByParam("name",name, null));
	    	zTree = null;
		}
};

TJSoft.device = {
		ztreeSetting:{
			check: {
				enable: true
			},
			data:{
				key:{
					title: "title"
				}
			}
		},
		init:function(setting,data){
			TJSoft.common.ajax("device_ztreeJsonData",null,function(data){
				TJSoft.ztree.initCommon("deviceTree",TJSoft.device.ztreeSetting, data,true);
				TJSoft.device.onclick();
				$.unblockUI();
			});
		},
		onclick:function(){
			$("#writeBtn,#readBtn,#resetBtn,#noticeBtn,#timeSyncBtn").click(function(){
				var t = $.fn.zTree.getZTreeObj("deviceTree").getCheckedNodes(true);
				var ids=[];
				if(t.length==0||t.length==1){
					window.parent.$.messager.alert('提示信息', '请先选择一台设备！', 'info');
					return;
				}
				for(var i=0;i<t.length;i++){
					if(t[i].id.length==32){
						ids.push(t[i].id);
					}
				}
				if(ids.length==0){
					window.parent.$.messager.alert('提示信息', '请先选择一台设备！', 'info');
					return;
				}
				$.blockUI({ message: "<div style=' height:15px; line-height:25px;'><span><img src='/Resource/img/loading.gif'/></span>正在与设备交互，请稍等…</div>",css: {width: '300px',height:'15px',left:'15%'}});
				if($(this).attr("id")=="writeBtn"){
					TJSoft.common.ajax("chk/remoteDevice_write", "ids="+ids.join("#"),function(d){
						if(d.flag){
							window.parent.$.messager.alert('提示信息', d.message, 'info');
						}else{
							window.parent.$.messager.alert('提示信息',d.message, 'error');
						}
						$.unblockUI();
					});
				}else if($(this).attr("id")=="readBtn"){
					TJSoft.common.ajax("chk/remoteDevice_read", "ids="+ids.join("#"),function(d){
						if(d.flag){
							window.parent.$.messager.alert('提示信息', d.message, 'info');
						}else{
							window.parent.$.messager.alert('提示信息',d.message, 'error');
						}
						$.unblockUI();
					});
				}else if($(this).attr("id")=="resetBtn"){
                    TJSoft.common.ajax("chk/remoteDevice_reset", "ids="+ids.join("#"),function(d){
                    	if(d.flag){
							window.parent.$.messager.alert('提示信息', d.message, 'info');
						}else{
							window.parent.$.messager.alert('提示信息',d.message, 'error');
						}
                    	$.unblockUI();
					});
				}else if($(this).attr("id")=="noticeBtn"){
                    TJSoft.common.ajax("chk/remoteDevice_notice", "ids="+ids.join("#"),function(d){
                    	if(d.flag){
							window.parent.$.messager.alert('提示信息', d.message, 'info');
						}else{
							window.parent.$.messager.alert('提示信息',d.message, 'error');
						}
                    	$.unblockUI();
					});
				}else if($(this).attr("id")=="timeSyncBtn"){
                    TJSoft.common.ajax("chk/remoteDevice_sycTime", "ids="+ids.join("#"),function(d){
                    	if(d.flag){
							window.parent.$.messager.alert('提示信息', d.message, 'info');
						}else{
							window.parent.$.messager.alert('提示信息',d.message, 'error');
						}
                    	$.unblockUI();
					});
				}
			});
		}
};
