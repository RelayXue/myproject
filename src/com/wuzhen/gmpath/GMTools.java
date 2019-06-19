package com.wuzhen.gmpath;

public class GMTools {
	// / <summary>
	// / 地图上一单位实际距离（单位“米”）
	// / </summary>
	public static double MapUnitPerMeter = 94690.206;
	/*
	 * 判断两点是否在指定距离内
	 */
	public static boolean isPointValidate(double x1, double y1, double x2,
			double y2, double distance) {
		boolean rtn = false;
		if (getPointsDistance(x1, y1, x2, y2) <= distance) {
			rtn = true;
		}
		return rtn;
	}
	/*
	 * 得到两点距离（米）
	 */
	public static double getPointsDistance(double x1, double y1, double x2,
			double y2) {
		double rtn = 0;
		rtn = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
				* MapUnitPerMeter;
		return rtn;
	}

	/*
	 * 判断点是否在范围内
	 */
	public boolean isInBounds(double boundLeft, double boundRight,
			double boundBottom, double boundTop, double pointX, double pointY) {

		if (pointX >= boundLeft && pointX <= boundRight
				&& pointY >= boundBottom && pointY <= boundTop) {
			return true;
		}
		return false;
	}
}
