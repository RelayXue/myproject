//地图相关参数配置

function mapURL(s){
	index=s;
	switch(s){
	//二维图
	case 1:
		return '/GHPort/map/ew/';	 
		break;
	//海图
	case 2:
		return '/GHPort/map/ht/';	 
		break;
	//三维图
	case 3:
		return 'http://60.12.184.19/wuzhen/tiles/'; 
		break;
	default:
		return 'http://60.12.184.19/wuzhen/tiles/';	 
		break;
	}
}



//地图范围坐标
var MAP_AREA_TOP = "31.00831086628195";
var MAP_AREA_RIGHT = "120.7717292886409";
var MAP_AREA_BOTTOM = "30.46069566628205";
var MAP_AREA_LEFT = "120.2241140886411";



