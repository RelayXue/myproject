package com.wuzhen.gmpath;

import java.awt.geom.GeneralPath;

public class GMPointInSurface {
	// 求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
	public static GMPoint GetLineCrossPoint(GMPoint pt0, GMPoint pt1,
			GMPoint pt2, GMPoint pt3) {

		// 如果线段两点坐标一样，不进行计算
		if (pt0.getX() == pt2.getX() && pt0.getY() == pt2.getY()) {
			return pt0;
		}
		if (pt1.getX() == pt3.getX() && pt1.getY() == pt3.getY()) {
			return pt1;
		}

		// 假设是求线段pt0-pt1与线段pt2-pt3的交点
		double t0 = GMIndexConfig.MaxLength, t1 = GMIndexConfig.MaxLength;

		// (1-t0)*pt0+t0*pt1=(1-t1)*pt2+t1*pt3

		// 两个方程，两个未知数，求解t0和t1
		t0 = pt2.getY()
				* (pt3.getX() - pt2.getX())
				- pt2.getX()
				* (pt3.getY() - pt2.getY())
				- (pt0.getY() * (pt3.getX() - pt2.getX()) - pt0.getX()
						* (pt3.getY() - pt2.getY()));
		t0 /= ((pt1.getY() - pt0.getY()) * (pt3.getX() - pt2.getX()) - (pt1
				.getX() - pt0.getX()) * (pt3.getY() - pt2.getY()));
		t1 = pt0.getX() + t0 * (pt1.getX() - pt0.getX()) - pt2.getX();
		t1 /= (pt3.getX() - pt2.getX());

		// 检查t0和t1以判断交点是否在线段上
		if (t0 == GMIndexConfig.MaxLength || t1 == GMIndexConfig.MaxLength) {
			return null;
		}
		if (t0 < 0 || t0 > 1 || t1 < 0 || t1 > 1) {
			return null;
		}
		return new GMPoint((1 - t0) * pt0.getX() + t0 * pt1.getX(), (1 - t0)
				* pt0.getY() + t0 * pt1.getY());
	}

	// 求线段pt0-pt1与线段pt2-pt3的交点,如果找不到交点则返回null
	public static boolean GetLineIsCross(GMPoint pt0, GMPoint pt1, GMPoint pt2,
			GMPoint pt3) {
		GMPoint _tu = GetLineCrossPoint(pt0, pt1, pt2, pt3);
		if (_tu != null) {
			return true;
		}
		return false;
	}

	public static boolean IsPointInSuface2(GMPoint pPt, GMPointCollection pPtS) {
		if (pPt != null && pPtS != null) {
			int __c = 0;
			for (int uu = 0; uu < pPtS.size() - 1; uu++) {
				GMPoint p1 = pPtS.get(uu);
				GMPoint p2 = pPtS.get(uu + 1);
				if (GetLineIsCross(pPt, new GMPoint(0, 0), p1, p2)) {
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
	}
	/*
	 *判断点是否在点集合所组成的面区域内，true：在面内，false:在面外 
	 */
	public static boolean IsPointInSuface(GMPoint pPt, GMPointCollection pPtS) {
		GeneralPath path = getGeneralPath(pPtS);
		if(path==null || pPt == null){
			return false;
		} 
		return path.contains(pPt.getX(), pPt.getY());
	} 

	public static boolean IsPointInSuface2(GMPoint pPt, String pPointSString) {
		return IsPointInSuface(pPt,
				GMPointCollection.FromStringByComma(pPointSString));
	}

	public static GeneralPath getGeneralPath(GMPointCollection points) {
		GeneralPath path = new GeneralPath(); 
		if (points.size() < 3) {
			return null;
		} 
		path.moveTo((float) points.get(0).getX(), (float) points.get(0).getY());

		for (int u = 0; u < points.size(); u++) {
			GMPoint point = points.get(u); 
			path.lineTo((float) point.getX(), (float) point.getY());
		} 
		path.closePath(); 
		return path;
	}

}
