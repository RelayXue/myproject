package com.wuzhen.gmpath;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class GMPointCollection extends ArrayList<GMPoint> {
	// / <summary>
	// / 根据ShapeString，以splitChar为多个的PointString分隔符,以splitXY为一个Point的分隔符,如X Y,X
	// Y
	// / </summary>
	// / <param name="shape">线坐标串</param>
	// / <param name="splitChar">多个坐标使用分隔符，如','</param>
	// / <param name="splitXY">一个坐标使用分隔符,如' '</param>
	// / <returns></returns>
	public static GMPointCollection FromStringBySplitChar(String shape, String splitChar, String splitXY) {
		GMPointCollection xS = new GMPointCollection();
		String[] s = shape.split(splitChar);
		if (s.length > 0) {
			try {
				if (splitChar.equalsIgnoreCase(splitXY)) {
					for (int u = 0; u < s.length; u++) {
						if (s.length <= u + 1) {
							break;
						}
						String tmS = s[u];
						String tmS2 = s[u + 1];
						if (tmS.trim() != "" && tmS2.trim() != "") {
							GMPoint xSP = new GMPoint();
							xSP.setX(Double.valueOf(tmS));
							xSP.setY(Double.valueOf(tmS2));
							xS.add(xSP);

						}
						u++;
					}
				} else {
					for (int u = 0; u < s.length; u++) {
						String tmS = s[u];
						if (tmS != "") {
							String[] tmpXY = tmS.split(splitXY);
							if (tmpXY.length == 2) {
								GMPoint xSP = new GMPoint();
								xSP.setX(Double.valueOf(tmpXY[0].trim()));
								xSP.setY(Double.valueOf(tmpXY[1].trim()));
								xS.add(xSP);
							}
						}
					}
				}
			} catch (Exception ex) {
				return null;
			}
		} else {
			return null;
		}
		return xS;
	}

	// / <summary>
	// / 根据ShapeString，以splitChar为多个的PointString分隔符,以splitXY为一个Point的分隔符,如X Y,X
	// Y
	// / </summary>
	// / <param name="shape">线坐标串</param>
	// / <returns></returns>
	public static GMPointCollection FromStringByComma(String shape) {
		if (shape != null) {
			return GMPointCollection.FromStringBySplitChar(shape, ",", " ");
		}
		return new GMPointCollection();
	}

	public String ToStringBySplitChar(char splitChar, char splitXY) {
		String rtnS = "";
		for (int m = 0; m < this.size(); m++) {
			if (m == 0) {
				rtnS = rtnS + this.get(m).getX() + splitXY + this.get(m).getY();
			} else {
				rtnS = rtnS + splitChar + this.get(m).getX() + splitXY + this.get(m).getY();
			}
		}
		return rtnS;
	}
	public String ToDefaultString() {
		return ToStringBySplitChar(',', ' ');
	}

	// / <summary>
	// / 得到距离（米）
	// / </summary>
	// / <returns></returns>
	public double GetMeterDistance() {
		double rtn = 0;
		for (int u = 0; u < this.size() - 1; u++) {
			rtn += GMPoint.Distance(this.get(u), this.get(u + 1));
		}
		rtn = rtn * GMIndexConfig.MapUnitPerMeter;
		return rtn;
	}

	public GMPointCollection getList(int begin, int end) {
		GMPointCollection rtn = new GMPointCollection();

		if (begin >= 0 && begin < this.size() && end >= 0 && end < this.size()) { 
			for (int u = begin; u <= end; u++) {
				rtn.add(this.get(u)); 
			}
		}
		return rtn;
	}

}
