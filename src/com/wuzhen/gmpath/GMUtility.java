package com.wuzhen.gmpath;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gh.entity.GmPathCrossPoint;
import com.gh.entity.GmPathLine;

public class GMUtility {
	// / <summary>
	// / 数据集合对角转换
	// / </summary>
	// / <param name="pointS"></param>
	// / <returns></returns>
	public static List<GMCrossPointModel> ConvertCorssPointSToModel(List<GmPathCrossPoint> pointS) {
		List<GMCrossPointModel> rtn = new ArrayList<GMCrossPointModel>();
		if (pointS != null) {
			for (int u = 0; u < pointS.size(); u++) {
				GmPathCrossPoint itm = pointS.get(u);
				GMCrossPointModel model = new GMCrossPointModel();
				model.setFno(itm.getFno());
				model.setFno(itm.getFno());
				model.setFno(itm.getFno());
				model.setFno(itm.getFno());
				model.setFcreateTime(itm.getFcreateTime());
				rtn.add(model);
			}
		}
		return rtn;
	}

	// / <summary>
	// / 根据FNo从集合中寻找对应数据
	// / </summary>
	// / <param name="pointS"></param>
	// / <param name="no"></param>
	// / <returns></returns>
	public static GMCrossPointModel GetCrossPointByNo(List<GMCrossPointModel> pointS, int no) {
		if (pointS != null) {
			for (int u = 0; u < pointS.size(); u++) {
				GMCrossPointModel item = pointS.get(u);
				if (item.getFno() == no) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * 根据交叉点编号，判断是要否交叉点集合内
	 * 
	 * @param pointS
	 * @param no
	 * @return
	 */
	public static boolean InCrossPointSByNo(List<GMCrossPointModel> pointS, int no) {
		if (GetCrossPointByNo(pointS, no) == null) {
			return false;
		}
		return true;
	}

	// / <summary>
	// / 根据交叉口号寻找相关边数据集合
	// / </summary>
	// / <param name="lineS"></param>
	// / <param name="no"></param>
	// / <returns></returns>
	public static List<GMLineModel> GetPathLineByNo(List<GMLineModel> lineS, int no) {
		List<GMLineModel> rtn = new ArrayList<GMLineModel>();
		if (lineS != null) {
			for (int u = 0; u < lineS.size(); u++) {
				GMLineModel item = lineS.get(u);
				if (item.getForderByAsc().contains("," + no + ",")) {
					rtn.add(item);
				}
			}
		}
		return rtn;
	}

	/**
	 * 从线路两端点组成格式串中，根据一个端点编号，解析出另一编号
	 * 
	 * @param lineOrderNo
	 * @param no
	 * @return
	 */
	public static int GetOtherCrossPointNo(String lineOrderNo, int no) {
		if (lineOrderNo != null) {
			return Integer.valueOf(lineOrderNo.replace("," + no + ",", "").replace(",", "").trim());
		}
		return 0;
	}

	public static GMOptimalPath GetOptimalPathBy2No(List<GMOptimalPath> pathS, int beginNo, int endNo) {
		if (pathS != null) {
			for (int u = 0; u < pathS.size(); u++) {
				GMOptimalPath item = pathS.get(u);
				if (item.BeginNo == beginNo && item.EndNo == endNo) {
					return item;
				}
			}
		}
		return null;
	}

	public static GMOptimalPath GetOptimalPathByEndNo(List<GMOptimalPath> pathS, int endNo) {
		if (pathS != null) {
			for (int u = 0; u < pathS.size(); u++) {
				GMOptimalPath item = pathS.get(u);
				if (item.EndNo == endNo) {
					return item;
				}
			}
		}
		return null;
	}

	// / <summary>
	// / 从集合中删除指定项。
	// / </summary>
	// / <param name="pointS"></param>
	// / <param name="no"></param>
	public static void RemoveCrossPointModel(List<GMCrossPointModel> pointS, int no) {
		for (int u = 0; u < pointS.size(); u++) {
			if (pointS.get(u).getFno() == no) {
				pointS.remove(u);
				break;
			}
		}
	}

	// / <summary>
	// / 转为GmLineModel类型集合返回
	// / </summary>
	// / <param name="lineS"></param>
	// / <returns></returns>
	public static List<GMLineModel> ConvertToLineModel(List<GmPathLine> lineS) {
		List<GMLineModel> rtn = new ArrayList<GMLineModel>();
		if (lineS != null) {
			for (int u = 0; u < lineS.size(); u++) {
				GMLineModel x = new GMLineModel();
				x.ReadFrom(lineS.get(u));
				rtn.add(x);
			}
		}
		return rtn;
	}

	// / <summary>
	// / 得到一次最优路径搜索
	// / </summary>
	// / <returns></returns>
	public static List<GMOptimalPath> GetSearchOptimalPathModel() {
		List<GMOptimalPath> rtn = new ArrayList<GMOptimalPath>();
		List<GMCrossPointModel> mS = GMCacheCenter.GetInstance().CrossPointMatrixModel;
		for (int u = 0; u < mS.size(); u++) {
			GMOptimalPath itm = new GMOptimalPath();
			itm.EndPointModel = mS.get(u);
			rtn.add(itm);
		}
		return rtn;
	}

	/**
	 * 根据两点得到以bPoint为原点的水平角度
	 * 
	 * @param bPoint
	 * @param ePoint
	 * @return
	 */
	public static double GetTwoPointAngle(GMPoint bPoint, GMPoint ePoint) {
		double angle = 0;
		GMPoint eP = new GMPoint();
		eP.setX(ePoint.getX() - bPoint.getX());
		eP.setY(ePoint.getY() - bPoint.getY());
		angle = Math.atan2(eP.getY(), eP.getX());
		if (angle == Double.NaN) {
			if (eP.getY() > 0) {
				angle = 90;
			} else {
				angle = -90;
			}
		} else {
			angle = angle / (Math.PI / 180);
		}
		if (angle < 0) {
			angle = 360 + angle;
		}
		return angle;
	}

	public static String GetDistanceText(double distance) {
		String rtn = "";
		if (distance > 1000) {
			rtn = GetDoubleFormatString(distance / 1000) + "公里";
		} else {
			rtn = String.valueOf((int) distance) + "米";
		}
		return rtn;
	}

	public static String GetDoubleFormatString(double value) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(value);
	}

	/**
	 * 得到得到两线段的夹角,夹角以line1Point1\line1Point2组成的线段为X轴
	 * 
	 * @param line1Point1
	 * @param line1Point2
	 * @param line2Point1
	 * @param line2Point2
	 * @return
	 */
	public static double GetSegmentAngle(GMPoint line1Point1, GMPoint line1Point2, GMPoint line2Point1, GMPoint line2Point2) {
		double angle = 0;
		double ag1 = GetTwoPointAngle(line1Point1, line1Point2);
		double ag2 = GetTwoPointAngle(line2Point1, line2Point2);
		angle = ag2 - ag1;
		if (angle < 0) {
			angle = 360 + angle;
		}
		return angle;
	}

	public static String GetStrongString(String value) {
		if (value != null && !value.trim().equals("")) {
			return "<strong>" + value + "</strong>";
		}
		return "";
	}
	
	/**
	 * 求点到线路的最短距离点
	 * @param linePB
	 * @param linePE
	 * @param point
	 * @return [最短距离,ling.PointS索引值,最短距离点X,最短距离点Y]
	 */
	public static double[] GetMinDistancePoint(GMLineModel line, GMPoint point) {
		double rtn[] = new double[4];
		Arrays.fill(rtn,0);
		GMPointCollection outPS = new GMPointCollection(); 
		double minDistance = 0.0;
		int minIndex = -1;
		double x=0,y=0;
		for(int u=0;u<line.PointS.size()-1;u++){
			double tmpDistance = GetMinDistance(point,line.PointS.get(u),line.PointS.get(u+1),outPS);
			if(tmpDistance<minDistance || minDistance==0){
				minDistance = tmpDistance;
				minIndex = u;
				if(outPS.size()>0){
					x = outPS.get(0).getX();
					y = outPS.get(0).getY();
				}
			}
		}
		rtn[0] = minDistance;
		rtn[1] = minIndex;
		rtn[2] = x;
		rtn[3] = y;
		return rtn;
	} 
	  
	/***
	 * 得到sp点到sb和se组成线段的最小距离
	 * @param sp
	 * @param sb
	 * @param se
	 * @param outPS
	 * @return 在返回的结果中只需要用到outPS中前三个点即可，第一个点为最近点，如果点不在线路的坐标串内，则该点为垂足点，第二点为对应垂足所在线的左边点，第三点对应垂足所在线的右边点
	 */
    public static double GetMinDistance(GMPoint sp, GMPoint sb, GMPoint se,   GMPointCollection outPS)
    {
        if (outPS == null)
        {
            outPS =  new GMPointCollection();
        } 
        GMPoint _p1 = new GMPoint();
        GMPoint _p2 = new GMPoint();
        GMPoint _p3 = new GMPoint();
        GMPoint _p4 = new GMPoint();
        GMPoint _p5 = new GMPoint();
        double c = Math.sqrt(Math.pow(Math.abs(sp.getX() - sb.getX()), 2) + Math.pow(Math.abs(sp.getY() - sb.getY()), 2));
        double b = Math.sqrt(Math.pow(Math.abs(sp.getX() - se.getX()), 2) + Math.pow(Math.abs(sp.getY() - se.getY()), 2));
        double a = Math.sqrt(Math.pow(Math.abs(sb.getX() - se.getX()), 2) + Math.pow(Math.abs(sb.getY() - se.getY()), 2));
        double zA = 0;
        if (b == 0 || c == 0 || a == 0)
        {
            if (b == 0)
            {
                _p1 = new GMPoint(se.getX(), se.getY());
                _p2 = new GMPoint(se.getX(), se.getY());
                _p3 = new GMPoint(se.getX(), se.getY());
                _p4 = new GMPoint(se.getX(), se.getY());
                _p5 = new GMPoint(se.getX(), se.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return b;
            }
            else if (c == 0)
            {
                _p1 = new GMPoint(sb.getX(), sb.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(sb.getX(), sb.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(sb.getX(), sb.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return c;
            }
            else if (a == 0)
            {
                _p1 = new GMPoint(sb.getX(), sb.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(sb.getX(), sb.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(sb.getX(), sb.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return c;
            }
        }
        else
        {
            if (a + b <= c)
            {
                _p1 = new GMPoint(sp.getX(), sp.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(se.getX(), se.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(se.getX(), se.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return c-a;
            }
            else if (a + c <= b)
            {
                _p1 = new GMPoint(sp.getX(), sp.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(se.getX(), se.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(se.getX(), se.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return b-a;
            }
            else if (b + c <= a)
            {
                _p1 = new GMPoint(sp.getX(), sp.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(se.getX(), se.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(se.getX(), se.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return 0;
            }
        }
        zA = Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c)) * 180 / Math.PI;
        double zB = 0;
        zB = Math.acos((Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2)) / (2 * a * c)) * 180 / Math.PI;
        double zC = 0;
        zC = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)) * 180 / Math.PI;
        if (Double.isNaN(zA) || Double.isNaN(zB) || Double.isNaN(zC))
        {
            String uu = "gg";
        }
        if (zC >= 90 || zB >= 90)
        {
            if (c > b)
            {
                _p1 = new GMPoint(se.getX(), se.getY());
                _p2 = new GMPoint(se.getX(), se.getY());
                _p3 = new GMPoint(se.getX(), se.getY());
                _p4 = new GMPoint(se.getX(), se.getY());
                _p5 = new GMPoint(se.getX(), se.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return b;
            }
            else
            {
                _p1 = new GMPoint(sb.getX(), sb.getY());
                _p2 = new GMPoint(sb.getX(), sb.getY());
                _p3 = new GMPoint(sb.getX(), sb.getY());
                _p4 = new GMPoint(sb.getX(), sb.getY());
                _p5 = new GMPoint(sb.getX(), sb.getY());
                outPS.clear();
                outPS.add(_p1);
                outPS.add(_p2);
                outPS.add(_p3);
                outPS.add(_p4);
                outPS.add(_p5);
                return c;
            }
        }
        else
        {
            //double len1 = Math.Cos(zB) * c;
            ////垂足y坐标
            //double y1 = (se.getY() - sb.getY()) * len1 / a + sb.getY();
            ////垂足x坐标
            //double x1 = (se.getX() - sb.getX()) * len1 / a + sb.getX();
            ////垂点
            double x1 = 0, y1 = 0;
            double k = 0;
            if ((se.getX() - sb.getX()) == 0)
            {
            }
            else
            {
                k = (se.getY() - sb.getY()) / (se.getX() - sb.getX());
            }
            double b2 = se.getY() - k * se.getX();
            GMPoint cz = FootOfPerpendicular(sp.getX(), sp.getY(), k, -1, b2);
            _p1 = new GMPoint(cz.getX(), cz.getY());
            _p2 = new GMPoint(sb.getX(), sb.getY());
            _p3 = new GMPoint(se.getX(), se.getY());
            _p4 = new GMPoint(sb.getX(), sb.getY());
            _p5 = new GMPoint(se.getX(), se.getY());
            outPS.clear();
            outPS.add(_p1);
            outPS.add(_p2);
            outPS.add(_p3);
            outPS.add(_p4);
            outPS.add(_p5);
            return Math.sin(zB * Math.PI / 180) * c;
        }
    }

    
    /***
     * 求点到直线的垂足
     * @param x1 点横坐标
     * @param y1 点纵坐标
     * @param A 直线方程一般式系数A
     * @param B 直线方程一般式系数B
     * @param C 直线方程一般式系数C
     * @return 成功返回真，失败返回假
     */
    public static GMPoint FootOfPerpendicular(double x1, double y1, double A, double B, double C)
    {
    	GMPoint rtn = new GMPoint(0,0);
        if (A * A + B * B < 1e-13) return rtn;
        if (Math.abs(A * x1 + B * y1 + C) < 1e-13)
        {
        	rtn.setX(x1);
            rtn.setY(y1);
        }
        else
        {
           rtn.setX((B * B * x1 - A * B * y1 - A * C) / (A * A + B * B));
            rtn.setY((-A * B * x1 + A * A * y1 - B * C) / (A * A + B * B));
        }
        return rtn;
    }
	
	
	

}
