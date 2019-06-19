function SRoute() { 
}
SRoute.FIndex = 0;
SRoute.FInterval = 300;
SRoute.FMarker = null;
SRoute.FMap = null;
SRoute.FMakerLayer = null;
SRoute.FVectorLayer = null;
SRoute.FDataS = [];
SRoute.FFlag = false; //是否暂停
SRoute.FLineStyle = null;
SRoute._Init = function () { 
    var gg5 = new SIcon("/images/gps/point.png", new SSize(30, 31)); 
    SRoute.FMarker = new SMarker(new SLonLat(0, 0), gg5);  
    SRoute.FMakerLayer.AddMarker(SRoute.FMarker); 
    SRoute.FMarker.AddEventListener("click", SRoute.FMarker, function () {
        SRoute.FMap.SInfoWindow.SetLonLat(SRoute.FMarker.GetLonLat(), new SSize(0, -33));
        SRoute.FMap.SInfoWindow.SetInnerHTML("");
        SRoute.FMap.SInfoWindow.Show();
        SRoute.FMap.SInfoWindow.SetSize(new SSize(300, 150));
    });
    SRoute.FLineStyle = new SFeatureStyle();
    SRoute.FLineStyle.SetFillColor("none");
    SRoute.FLineStyle.SetHoverFillColor("red");
    SRoute.FLineStyle.SetStrokeColor("green");
    SRoute.FLineStyle.SetStrokeStartArrow("Oval");
    SRoute.FLineStyle.SetStrokeEndArrow("Classic");
    SRoute.FLineStyle.SetStrokeDashStyle("ShortDashDot"); 
}
SRoute.Init = function (map, markerLayer, vectorLayer, dataS, intervalValue) {
    SRoute.FMap = map;
    SRoute.FMakerLayer = markerLayer;
    SRoute.FDataS = dataS;
    SRoute.FVectorLayer = vectorLayer;
    SRoute.FDataS = dataS;
    SRoute.FInterval = intervalValue;
    SRoute._Init();
}
//行走
SRoute.XingZhou = function () {
    if (SRoute.FFlag) {
        return;
    }
    if (SRoute.FIndex < SRoute.FDataS.length - 1) {
        SRoute.FIndex++;
    } else {
        SRoute.FIndex = 0;
        SRoute.Clear();
        return;
    }
    var _ll = new SLonLat(SRoute.FDataS[SRoute.FIndex].GetLon(), SRoute.FDataS[SRoute.FIndex].GetLat());
    SRoute.FMarker.MoveToWithLonLat(_ll);
    SRoute.FMap.SetCenterMoveSlowing(_ll);
    SRoute.FMap.SInfoWindow.SetLonLat(_ll);
    SRoute.ReDrawLine();
}
SRoute.ReDrawLine = function () {
    SRoute.FVectorLayer.DestroyFeatures();  
    var xL = new SLineString(SRoute.GetPointSByIndex());
    var pf = new SFeature(xL, SRoute.FLineStyle);
    SRoute.FVectorLayer.AddFeatures([pf]);
}
SRoute.Start = function () {
    SRoute.FIntervalHandler = setInterval(SRoute.XingZhou, SRoute.FInterval);
    SRoute.XingZhou();
}
SRoute.Pause = function () {
    SRoute.FFlag = true;
}
SRoute.Continue = function () {
    SRoute.FFlag = false;
}
SRoute.Clear = function () {
    clearInterval(SRoute.FIntervalHandler);
    SRoute.FFlag = false;
}
SRoute.GetPointSByIndex = function () {
    var rtnS = [];
    for (var u = 0; u <= SRoute.FIndex; u++) {
        var itm = SRoute.FDataS[u]
        rtnS.push(new SPoint(itm.GetLon(), itm.GetLat()));
        itm = null;
    }
    return rtnS;
} 