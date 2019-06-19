package com.gh.common;

public class mapTool {
	/**
	 * 地图上一单位实际距离（单位“米”）
	 **/
	public static double MapUnitPerMeter = 94690.206;

	/**
	 * @see得到两点距离
	 * @return 距离(经纬度)
	 **/
	public static double getPointsDistance(double x1, double y1, double x2, double y2) {
		double rtn = 0;
		rtn = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) ;
		return rtn;
	}
}
