function TMapType() { }
//百度地图
TMapType.TBaiDu = 1;
//百度卫星
TMapType.TBaiDuW = 2;
//谷歌地图
TMapType.TGoogle = 3;
//谷歌卫星
TMapType.TGoogleW = 4;
//图景地图
TMapType.TTuJing = 5;
//高德地图
TMapType.TMapAbc = 6;

//监控中心
TMapType.Monitor = 0;
//轨迹回放
TMapType.History = 1;

TMapType.prototype.GetMapTypeName = function (type){
	var name = "";
	switch(type){
	case 1:
		name = "百度地图";
		break;
	case 2:
		name = "百度卫星";
		break;
	case 3:
		name = "谷歌地图";
		break;
	case 4:
		return "谷歌卫星";
		break;
	case 5:
		name = "内网地图";
		break;
	case 6:
		return "高德地图";
		break;
	default:
		name = "图景地图";
	}
	return name;
};
TMapType.prototype.GetMapTypeX = function (type){
	var typePrototype = "";
	switch(type){
	case 1:
		typePrototype = "blon";
		break;
	case 2:
		typePrototype = "blon";
		break;
	case 3:
		typePrototype = "blon";
		break;
	case 4:
		typePrototype = "blon";
		break;
	case 5:
		typePrototype = "glon";
		break;
	case 6:
		typePrototype = "glon";
		break;
	default:
		typePrototype = "blon";
	}
	return typePrototype;
};
TMapType.prototype.GetMapTypeY = function (type){
	var typePrototype = "";
	switch(type){
	case 1:
		typePrototype = "blat";
		break;
	case 2:
		typePrototype = "blat";
		break;
	case 3:
		typePrototype = "blat";
		break;
	case 4:
		typePrototype = "blat";
		break;
	case 5:
		typePrototype = "glat";
		break;
	case 6:
		typePrototype = "glat";
		break;
	default:
		typePrototype = "blat";
	}
	return typePrototype;
};