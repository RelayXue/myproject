function SRouteDataItem(id,lon,lat,text) {
    this.FId = id;
    this.FLon = lon;
    this.FLat = lat;
    this.FText = text;
}
SRouteDataItem.prototype.SetId = function (id) {
    this.FId = id;
}
SRouteDataItem.prototype.GetLon = function () {
    return this.FLon;
}
SRouteDataItem.prototype.GetLat = function () {
    return this.FLat;
}
SRouteDataItem.prototype.GetText = function () {
    return this.FText;
}
SRouteDataItem.prototype.GetId = function () {
    return this.FId;
}