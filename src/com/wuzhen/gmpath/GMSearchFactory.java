package com.wuzhen.gmpath;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gh.entity.GmPathCrossPoint;

public class GMSearchFactory {
	/*
	 * 根据起，始点路口编号，获取最优路径结果对象
	 */
	public static GMOptimalPath GetOptimalPath(int beginNo, int endNo) {
		GMOptimalPath path = null;
		Date beginTime = new Date();
		List<GMOptimalPath> pathS = GMUtility.GetSearchOptimalPathModel();
		if (GMCacheCenter.GetInstance().CrossPointMatrixModel.size() > beginNo && GMCacheCenter.GetInstance().CrossPointMatrixModel.size() > endNo) {
			GMCrossPointModel model = GMCacheCenter.GetInstance().CrossPointMatrixModel.get(beginNo - 1);

			GMBusinessFactory.BuildOptimalPath3(model, pathS);
			path = pathS.get(endNo - 1);
			Date endTime = new Date();
			long diff = endTime.getTime() - beginTime.getTime();
			path.ExecSeconds = diff / 1000;

		}
		return path;
	}

	/*
	 * 根据线路交叉号编号进行最优路径查询,返回路径空间坐标串
	 */
	public static String GetOptimalPathString(int beginNo, int endNo) {
		GMOptimalPath path = GetOptimalPath(beginNo, endNo);
		return path.GetPathString();
	}

	/*
	 * 根据起点X,Y、终点X,Y进行最优路径查询，返回最优路径空间坐标串信息
	 */
	public static String GetOptimalPathStringByXY(double beginX, double beginY, double endX, double endY) {
		GMOptimalPath path = GetOptimalPathStringByXY2(beginX, beginY, endX, endY);
		if (path != null) {
			String pathString = path.GetPathString();
			return pathString;
		}
		return "";
	}

	/*
	 * 根据起点X,Y、终点X,Y进行最优路径查询，返回最优路径对象信息
	 */
	public static GMOptimalPath GetOptimalPathStringByXY2(double beginX, double beginY, double endX, double endY) {
		GMOptimalPath x = new GMOptimalPath();
		List<GmPathCrossPoint> bPointS = GMDaoFactory.GmPathCrossPointInstance.execSql("SELECT * FROM GM_PATH_CROSS_POINT ORDER BY (F_X-" + beginX + ")*(F_X-" + beginX + ") + (F_Y-" + beginY + ")*(F_Y-" + beginY + ") ASC LIMIT 1");
		List<GmPathCrossPoint> ePointS = GMDaoFactory.GmPathCrossPointInstance.execSql("SELECT   * FROM GM_PATH_CROSS_POINT  ORDER BY (F_X-" + endX + ")*(F_X-" + endX + ") + (F_Y-" + endY + ")*(F_Y-" + endY + ") ASC LIMIT 1");
		if (bPointS != null && bPointS.size() > 0 && ePointS != null && ePointS.size() > 0) {
			x = GetOptimalPath(bPointS.get(0).getFno(), ePointS.get(0).getFno());
			x.BeginX = beginX;
			x.BeginY = beginY;
			x.EndX = endX;
			x.EndY = endY;
			HandlerResultPath(x);
		}
		return x;
	}

	public static void HandlerResultPath(GMOptimalPath path) {

		if (path != null) {
			GMPoint begin = new GMPoint(path.BeginX, path.BeginY);
			GMPoint end = new GMPoint(path.EndX, path.EndY);
			double[] minValueS = new double[4];
			Arrays.fill(minValueS, Double.MAX_VALUE);
			GMLineModel minLine = null;
			if (true) {//begin model
				for (int u = 0; u < path.StartPointModel.AdjactentLineS.size(); u++) {
					GMLineModel itm = path.StartPointModel.AdjactentLineS.get(u);
					double[] temp = GMUtility.GetMinDistancePoint(itm, begin);
					if (temp[0] < minValueS[0]) {
						minLine = itm;
						minValueS = temp;
					}
				}
				if (minLine.getFno().equals(path.LineS.get(0).getFno())) {
					GMLineModel mm = path.LineS.get(0).Clone();
					GMPointCollection pcS = new GMPointCollection();
					if (path.BeginNo == mm.FBeginNo) {
						//pcS = (GMPointCollection) mm.PointS.getList(0, (int) minValueS[1]);
						
						pcS = (GMPointCollection) mm.PointS.getList((int) minValueS[1] + 1, mm.PointS.size() - 1);
						
						pcS.add(0, new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
					} else {
						pcS = (GMPointCollection) mm.PointS.getList(0, (int) minValueS[1]);
						pcS.add(new GMPoint(minValueS[2], minValueS[3]));
						
						mm.PointS = pcS;
					}
					path.LineS.set(0, mm);
				} else {
					GMLineModel mm = minLine.Clone();
					GMPointCollection pcS = new GMPointCollection();
					if (path.BeginNo == mm.FBeginNo) {
						pcS = (GMPointCollection) mm.PointS.getList(0, (int) minValueS[1]);
						pcS.add(new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
						path.BeginNo = mm.FEndNo;
					} else {
						pcS = (GMPointCollection) mm.PointS.getList((int) minValueS[1] + 1, mm.PointS.size() - 1);
						pcS.add(0, new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
						path.BeginNo = mm.FBeginNo;
					}
					// path.EndNo = mm.FEndNo;
					path.LineS.add(0, mm);
					System.out.println(mm.PointS.ToStringBySplitChar(',', ' '));
				}
			}
			
			if (true) {//end model
				Arrays.fill(minValueS, Double.MAX_VALUE);
				minLine = null;
				for (int u = 0; u < path.EndPointModel.AdjactentLineS.size(); u++) {
					GMLineModel itm = path.EndPointModel.AdjactentLineS.get(u);
					double[] temp = GMUtility.GetMinDistancePoint(itm, end);
					if (temp[0] < minValueS[0]) {
						minLine = itm;
						minValueS = temp;
					}
				}
				if (minLine.getFno().equals(path.LineS.get(path.LineS.size()-1).getFno())) {
					GMLineModel mm = path.LineS.get(path.LineS.size()-1).Clone();
					GMPointCollection pcS = new GMPointCollection();
					if (path.EndNo == mm.FEndNo) {
						pcS = (GMPointCollection) mm.PointS.getList(0, (int) minValueS[1]);
						pcS.add(new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
					} else {
						pcS = (GMPointCollection) mm.PointS.getList((int) minValueS[1] + 1, mm.PointS.size() - 1);
						pcS.add(0, new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
					}
					path.LineS.set(path.LineS.size()-1, mm);
				} else {
					GMLineModel mm = minLine.Clone();
					GMPointCollection pcS = new GMPointCollection();
					if (path.EndNo == mm.FBeginNo) {
						pcS = (GMPointCollection) mm.PointS.getList(0, (int) minValueS[1]);
						pcS.add(new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
						path.EndNo = mm.FEndNo;
					} else {
						pcS = (GMPointCollection) mm.PointS.getList((int) minValueS[1] + 1, mm.PointS.size() - 1);
						pcS.add(0, new GMPoint(minValueS[2], minValueS[3]));
						mm.PointS = pcS;
						path.EndNo = mm.FBeginNo;
					}
					// path.EndNo = mm.FEndNo;
					path.LineS.add(mm);
					//System.out.println(mm.PointS.ToStringBySplitChar(',', ' '));
				}
			}

		}
	}

	public static GMOptimalPath GetOptimalPathByBE(double beginX, double beginY, double endX, double endY) {
		GMOptimalPath x = new GMOptimalPath();
		List<GmPathCrossPoint> bPointS = GMDaoFactory.GmPathCrossPointInstance.execSql("SELECT  * FROM GM_PATH_CROSS_POINT ORDER BY (F_X-" + beginX + ")*(F_X-" + beginX + ") + (F_Y-" + beginY + ")*(F_Y-" + beginY + ") ASC LIMIT 1");
		List<GmPathCrossPoint> ePointS = GMDaoFactory.GmPathCrossPointInstance.execSql("SELECT  * FROM GM_PATH_CROSS_POINT  ORDER BY (F_X-" + endX + ")*(F_X-" + endX + ") + (F_Y-" + endY + ")*(F_Y-" + endY + ") ASC LIMIT1");
		if (bPointS != null && bPointS.size() > 0 && ePointS != null && ePointS.size() > 0) {
			x = GetOptimalPath(bPointS.get(0).getFno(), ePointS.get(0).getFno());
			x.BeginX = beginX;
			x.BeginY = beginY;
			x.EndX = endX;
			x.EndY = endY;
		}
		return x;
	}

}
