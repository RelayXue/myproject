
function SMath() {
}
//求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
SMath.GetLineCrossPoint = function(pt0, pt1, pt2, pt3) {
    //如果线段两点坐标一样，不进行计算 
    if (pt0.GetX() == pt2.GetX() && pt0.GetY() == pt2.GetY()) {
        return pt0;
    }
    if (pt1.GetX() == pt3.GetX() && pt1.GetY() == pt3.GetY()) {
        return pt1;
    }
    // 假设是求线段pt0-pt1与线段pt2-pt3的交点 
    var t0, t1;
    // (1-t0)*pt0+t0*pt1=(1-t1)*pt2+t1*pt3
    // 两个方程，两个未知数，求解t0和t1
    t0 = pt2.GetY() * (pt3.GetX() - pt2.GetX()) - pt2.GetX() * (pt3.GetY() - pt2.GetY()) - (pt0.GetY() * (pt3.GetX() - pt2.GetX()) - pt0.GetX() * (pt3.GetY() - pt2.GetY()));
    t0 /= ((pt1.GetY() - pt0.GetY()) * (pt3.GetX() - pt2.GetX()) - (pt1.GetX() - pt0.GetX()) * (pt3.GetY() - pt2.GetY()));
    t1 = parseFloat(pt0.GetX()) + parseFloat(t0 * (pt1.GetX() - pt0.GetX())) - parseFloat(pt2.GetX());
    t1 /= (pt3.GetX() - pt2.GetX());
    // 检查t0和t1以判断交点是否在线段上
    if (isNaN(t0) || isNaN(t1)) {
        return null;
    }
    if (t0 < 0 || t0 > 1 || t1 < 0 || t1 > 1) {
        return null;
    }
    return new SPoint((1 - t0) * pt0.GetX() + t0 * pt1.GetX(), (1 - t0) * pt0.GetY() + t0 * pt1.GetY());
};
//求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
SMath.GetLineIsCross = function(pt0, pt1, pt2, pt3) {
    var _tu = SMath.GetLineCrossPoint(pt0, pt1, pt2, pt3);
    if (_tu != null) {
        return true;
    }
    return false;
};
SMath.IsPointInSuface = function(pPt, pPtS) {
    if (pPt != null && pPtS != null) {
        var __c = 0;
        for (var uu = 0; uu < pPtS.length - 1; uu++) {
            var p1 = pPtS[uu];
            var p2 = pPtS[uu + 1];
            if (SMath.GetLineIsCross(pPt, new SPoint(0, 0), p1, p2)) {
                __c = __c + 1;
            }
        }
        if (__c % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }
    return false;
};
SMath.GetPointSFromString = function(pStr) {
    var rtnS = [];
    if (pStr != null && pStr != "") {
        var tmR = pStr.split(",");
        for (var u = 0; u < tmR.length; u++) {
            if (tmR[u] != "") {
                var tmp2 = tmR[u].split(" ");
                if (tmp2.length == 2) {
                    var pt = new SPoint(tmp2[0], tmp2[1]);
                    rtnS.push(pt);
                }
            }
        }
    }
    return rtnS;
};