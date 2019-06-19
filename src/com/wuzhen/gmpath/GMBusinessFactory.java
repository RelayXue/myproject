package com.wuzhen.gmpath;

import java.util.ArrayList;
import java.util.List;

import com.gh.entity.GmPathCrossPoint;
import com.gh.entity.GmPathLine;
import com.gh.service.GmPathCrossPointService;
import com.gh.service.GmPathLineService;

public class GMBusinessFactory {

	// / <summary>
	// / 创建并返回，交叉口数据模型对象（已构建网络结构）
	// / </summary>
	// / <returns></returns>
	public static List<GMCrossPointModel> GetCrossPointSDataMode() {
		List<GMCrossPointModel> rtn = new ArrayList<GMCrossPointModel>();
		GmPathCrossPointService xPoint = GMDaoFactory.GmPathCrossPointInstance;
		GmPathLineService xLine = GMDaoFactory.GmPathLineInstance;
		List<GmPathCrossPoint> pointS = xPoint.execSql("SELECT * FROM GM_PATH_CROSS_POINT ORDER BY F_NO");
		List<GMLineModel> lineS = GMUtility.ConvertToLineModel(xLine.execSql("SELECT * FROM GM_PATH_LINE ORDER BY F_NO"));
		rtn = GMUtility.ConvertCorssPointSToModel(pointS);
		for (int u = 0; u < rtn.size(); u++) {
			GMCrossPointModel item = rtn.get(u);
			List<GMLineModel> pointLineS = GMUtility.GetPathLineByNo(lineS, item.getFno());
			for (int m = 0; m < pointLineS.size(); m++) {
				GMLineModel item2 = pointLineS.get(m);
				int otherNo = GMUtility.GetOtherCrossPointNo(item2.getForderByAsc(), item.getFno());
				GMCrossPointModel _tmpItem = GMUtility.GetCrossPointByNo(rtn, otherNo);
				if (_tmpItem != null) {
					item.AdjactentLineS.add(item2);
					item.AdjactentCrossPointS.add(_tmpItem);
				}
			}
		}
		return rtn;
	}

	// / <summary>
	// / 产生当前点到所有点的最短路径信息,采用（Dijkstra 算法）
	// / </summary>
	// / <param name="pathS"></param>
	// / <param name="current"></param>
	// / <param name="path"></param>
	public static void BuildOptimalPath(List<GMOptimalPath> pathS, GMCrossPointModel current, GMOptimalPath prePath, List<GMCrossPointModel> oldPointS, int currentNo) {
		if (pathS != null && current != null) {
			if (prePath == null) {
				prePath = new GMOptimalPath();
				prePath.Length = 0;
				prePath.PointPath = "," + current.getFno() + ",";
				prePath.RoadPath = "";
				prePath.BeginNo = current.getFno();
			}
			Boolean tag = true;
			if (oldPointS == null) {
				tag = false;
			}
			for (int u = 0; u < current.AdjactentCrossPointS.size(); u++) {
				GMCrossPointModel model = current.AdjactentCrossPointS.get(u);
				if (model.getFno() == currentNo) {
					continue;
				}
				List<GMCrossPointModel> oldPointS2 = null;
				if (tag) {
					oldPointS2 = oldPointS;
				} else {
					oldPointS2 = new ArrayList<GMCrossPointModel>();
				}
				oldPointS2.add(current);// 把已选入的点，放入到集合对象中去。
				GmPathLine line = current.AdjactentLineS.get(u);
				GMOptimalPath optimal = GMUtility.GetOptimalPathBy2No(pathS, currentNo, model.getFno());
				if (optimal == null) {
					optimal = new GMOptimalPath();
					optimal.BeginNo = currentNo;
					optimal.EndNo = model.getFno();
					optimal.Length = Double.valueOf(String.valueOf(line.getFlength())) + prePath.Length;
					optimal.PointPath = prePath.PointPath + optimal.EndNo + ",";
					optimal.RoadPath = prePath.RoadPath + line.getFno() + ",";
					pathS.add(optimal);
				} else {
					if (optimal.Length > (prePath.Length + Double.valueOf(String.valueOf(line.getFlength())))) {
						optimal.Length = prePath.Length + Double.valueOf(String.valueOf(line.getFlength()));
						optimal.PointPath = prePath.PointPath + optimal.EndNo + ",";
						optimal.RoadPath = prePath.RoadPath + line.getFno() + ",";
					}
				}

				// 递归，寻找相邻节点信息。
				if (!GMUtility.InCrossPointSByNo(oldPointS2, model.getFno())) {
					BuildOptimalPath(pathS, model, optimal, oldPointS2, currentNo);
				}
			}
		}
	}

	/**
	 * 得到originID对应交叉点相连点集合中找到路径最短的一个点返回
	 * 
	 * @param nodeList
	 * @param originID
	 * @param pathS
	 * @return
	 */
	public static GMCrossPointModel GetMinWeightRudeNode(List<GMCrossPointModel> nodeList, int originID, List<GMOptimalPath> pathS) {
		double weight = GMIndexConfig.MaxLength;
		GMCrossPointModel destNode = null;
		for (GMCrossPointModel node : nodeList) {
			if (node.getFno() == originID) {
				continue;
			}
			GMOptimalPath pPath = pathS.get(node.getFno() - 1);// node.AdjactentPath;
			if (pPath.Length < weight) {
				weight = pPath.Length;
				destNode = node;
			}
		}
		return destNode;
	}

	public static void InitializeWeight(GMCrossPointModel originNode, List<GMCrossPointModel> notHandlerCrossPointS, List<GMOptimalPath> pathS) {
		if ((originNode.AdjactentCrossPointS == null) || (originNode.AdjactentCrossPointS.size() == 0)) {
			return;
		}
		for (int u = 0; u < originNode.AdjactentCrossPointS.size(); u++) {
			GMCrossPointModel item = originNode.AdjactentCrossPointS.get(u);
			GMLineModel line = originNode.AdjactentLineS.get(u);
			GMOptimalPath pPath = pathS.get(item.getFno() - 1);
			pPath.PointPath = originNode.getFno() + "," + item.getFno();
			pPath.RoadPath = String.valueOf(line.getFno());
			pPath.Length = Double.valueOf(String.valueOf(line.getFlength()));
			pPath.BeginNo = originNode.getFno();
			pPath.EndNo = item.getFno();
			pPath.LineS.clear();
			for (int m = 0; m < pPath.LineS.size(); m++) {
				pPath.LineS.add(pPath.LineS.get(m));
			}
			pPath.LineS.add(line);
			notHandlerCrossPointS.add(item);
		}
	}

	public static void BuildOptimalPath2(List<GMCrossPointModel> pointS, GMCrossPointModel current) {
		// / <summary>
		// / 未处理的路径
		// / </summary>
		List<GMCrossPointModel> notHandlerCrossPointS = new ArrayList<GMCrossPointModel>();
		InitializeWeight(current, notHandlerCrossPointS, null);

		GMCrossPointModel curNode = GetMinWeightRudeNode(notHandlerCrossPointS, current.getFno(), null);
		while (curNode != null) {
			GMOptimalPath curPath = curNode.AdjactentPath;
			for (int u = 0; u < curNode.AdjactentCrossPointS.size(); u++) {
				GMCrossPointModel itm = curNode.AdjactentCrossPointS.get(u);
				if (itm.getFno() == current.getFno()) {
					continue;
				}
				GMOptimalPath targetPath = itm.AdjactentPath;
				GMLineModel line = curNode.AdjactentLineS.get(u);
				double tempWeight = curPath.Length + Double.valueOf(String.valueOf(line.getFlength()));

				if (tempWeight < targetPath.Length) {
					targetPath.BeginNo = current.getFno();
					targetPath.EndNo = itm.getFno();
					targetPath.Length = tempWeight;
					// targetPath.PointPath = curPath.PointPath + "," +
					// itm.getFNo();
					// targetPath.RoadPath = curPath.RoadPath + "," +
					// line.getFNo();
					targetPath.LineS.clear();
					for (int m = 0; m < curPath.LineS.size(); m++) {
						targetPath.LineS.add(curPath.LineS.get(m));
					}
					targetPath.LineS.add(line);
					notHandlerCrossPointS.add(itm);
				}
			}
			// 标志为已处理
			curPath.IsHandler = true;
			GMUtility.RemoveCrossPointModel(notHandlerCrossPointS, curNode.getFno());
			// 获取下一个未处理节点
			curNode = GetMinWeightRudeNode(notHandlerCrossPointS, current.getFno(), null);
		}
	}

	public static void BuildOptimalPath3(GMCrossPointModel current, List<GMOptimalPath> pathS) {
		// / <summary>
		// / 未处理的路径
		// / </summary>
		List<GMCrossPointModel> notHandlerCrossPointS = new ArrayList<GMCrossPointModel>();
		InitializeWeight(current, notHandlerCrossPointS, pathS);

		GMCrossPointModel curNode = GetMinWeightRudeNode(notHandlerCrossPointS, current.getFno(), pathS);
		while (curNode != null) {
			GMOptimalPath curPath = pathS.get(curNode.getFno() - 1);// curNode.AdjactentPath;
			for (int u = 0; u < curNode.AdjactentCrossPointS.size(); u++) {
				GMCrossPointModel itm = curNode.AdjactentCrossPointS.get(u);
				if (itm.getFno() == current.getFno()) {
					continue;
				}
				GMOptimalPath targetPath = pathS.get(itm.getFno() - 1);// itm.AdjactentPath;
				GMLineModel line = curNode.AdjactentLineS.get(u);
				double tempWeight = curPath.Length + Double.valueOf(String.valueOf(line.getFlength()));

				if (tempWeight < targetPath.Length) {
					targetPath.BeginNo = current.getFno();
					targetPath.EndNo = itm.getFno();
					targetPath.Length = tempWeight;
					// targetPath.PointPath = curPath.PointPath + "," +
					// itm.getFNo();
					// targetPath.RoadPath = curPath.RoadPath + "," +
					// line.getFNo();
					targetPath.LineS.clear();
					for (int m = 0; m < curPath.LineS.size(); m++) {
						targetPath.LineS.add(curPath.LineS.get(m));
					}
					targetPath.LineS.add(line);
					notHandlerCrossPointS.add(itm);
				}
			}
			// 标志为已处理
			curPath.IsHandler = true;
			curPath.StartPointModel = current;
			GMUtility.RemoveCrossPointModel(notHandlerCrossPointS, curNode.getFno());
			// 获取下一个未处理节点
			curNode = GetMinWeightRudeNode(notHandlerCrossPointS, current.getFno(), pathS);
		}
	}

	public static String GetPathTextByAngle2(double angle) {
		String rtn = "";
		if (angle >= 15 && angle < 75) {
			rtn = "东北";

		} else if (angle >= 75 && angle < 105) {
			rtn = "正北";

		} else if (angle >= 105 && angle < 165) {
			rtn = "西北";

		} else if (angle >= 165 && angle < 195) {
			rtn = "正西";

		} else if (angle >= 195 && angle < 255) {
			rtn = "西南";

		} else if (angle >= 255 && angle < 285) {
			rtn = "正南";

		} else if (angle >= 285 && angle < 315) {
			rtn = "东南";

		} else if (angle >= 315 || angle < 15) {
			rtn = "正东";
		}
		rtn += "方向";
		return rtn;
	}

	/**
	 * 根据角度得到转向信息
	 * 
	 * @param angle
	 * @return
	 */
	public static String GetPathTurnTextByAngle2(double angle) {
		String rtn = "";
		if (angle >= 45 && angle < 60) {
			rtn = "右后方转弯";

		} else if (angle >= 60 && angle < 120) {
			rtn = "右转";

		} else if (angle >= 120 && angle < 150) {
			rtn = "右前方转弯";

		} else if (angle >= 210 && angle < 240) {
			rtn = "稍向左转";

		} else if (angle >= 240 && angle < 320) {
			rtn = "左转";

		} else if (angle >= 320 || angle < 30) {
			rtn = "调头";
		}
		return rtn;
	}

	/**
	 * 根据两坐标，以bPoint为原点，得到bPoint到ePoint方位串
	 * 
	 * @param bPoint
	 * @param ePoint
	 * @return
	 */
	public static String GetPathTextByBE(GMPoint bPoint, GMPoint ePoint) {
		return GetPathTextByAngle2(GMUtility.GetTwoPointAngle(bPoint, ePoint));
	}

	public static String GetPathTextByLine(GMLineModel line) {
		String rtn = "";
		if (line != null && line.PointS.size() > 1) {
			rtn = GMBusinessFactory.GetPathTextByBE(line.PointS.get(0), line.PointS.get(line.PointS.size() - 1));
		}
		if (rtn != "") {
			rtn = "向" + rtn;
		}
		return rtn;
	}

	/**
	 * 把连续的道路合并成一条。返回整理后的数组
	 * 
	 * @param lines
	 * @return
	 */
	public static List<GMLineModel> GetIntegrateNextRepeatData(List<GMLineModel> lines) {
		List<GMLineModel> rtn = new ArrayList<GMLineModel>();
		if (lines != null && lines.size() > 0) {
			for (int u = 0; u < lines.size(); u++) {
				GMLineModel itm = lines.get(u);
				GMLineModel line = itm.Clone();
				if (u == 0) {
					rtn.add(line);
				} else {
					GMLineModel itm2 = rtn.get(rtn.size() - 1);
					if (itm2.getFname() != null && itm2.getFname().equalsIgnoreCase(itm.getFname())) {
						itm2.setFlength(line.getFlength() + itm2.getFlength());
						if (itm2.FBeginNo == itm.FBeginNo) {
							for (int m = 0; m < itm.PointS.size(); m++) {
								itm2.PointS.add(0, itm.PointS.get(m));
							}
							itm2.FBeginNo = itm.FEndNo;
						} else if (itm.FBeginNo == itm2.FEndNo) {
							for (int m = 0; m<itm.PointS.size(); m++) {
								itm2.PointS.add(itm.PointS.get(m));
							}
							itm2.FEndNo = itm.FEndNo;
						} else if (itm.FEndNo == itm2.FEndNo) {
							for (int m = itm.PointS.size() - 1; m >= 0; m--) {
								itm2.PointS.add(itm.PointS.get(m));
							}
							itm2.FEndNo = itm.FBeginNo;
						} else if (itm.FEndNo == itm2.FBeginNo) {
							for (int m = itm.PointS.size()-1; m >=0; m--) {
								itm2.PointS.add(0,itm.PointS.get(m));
							}
							itm2.FBeginNo = itm.FBeginNo;
						}
					} else {
						rtn.add(line);
					}
				}
			}
		}
		return rtn;
	}

	/**
	 * 根据两条线段点得到转向信息
	 * 
	 * @param line1Point1
	 * @param line1Point2
	 * @param line2Point1
	 * @param line2Point2
	 * @return
	 */
	public static String GetTurnDirectionText(GMPoint line1Point1, GMPoint line1Point2, GMPoint line2Point1, GMPoint line2Point2) {
		return GetPathTurnTextByAngle2(GMUtility.GetSegmentAngle(line1Point1, line1Point2, line2Point1, line2Point2));
	}

	public static String GetTurnDirectionText2(GMLineModel line1, GMLineModel line2) {
		if (line1 != null && line1.PointS.size() > 1 && line2 != null && line2.PointS.size() > 1) {
			GMPoint p1 = new GMPoint();
			GMPoint p2 = new GMPoint();
			GMPoint p3 = new GMPoint();
			GMPoint p4 = new GMPoint();
			if (line1.FBeginNo == line2.FBeginNo) {
				p1 = line1.PointS.get(0);
				p2 = line1.PointS.get(line1.PointS.size() - 1);
				p3 = line2.PointS.get(0); 
				p4 = line2.PointS.get(line2.PointS.size() - 1);
			}else if(line1.FBeginNo == line2.FEndNo){
				p1 = line1.PointS.get(0);
				p2 = line1.PointS.get(line1.PointS.size() - 1);
				p3 = line2.PointS.get(line2.PointS.size() - 1);
				p4 = line2.PointS.get(0);  
			}else if(line1.FEndNo == line2.FBeginNo) {
				p1 = line1.PointS.get(line1.PointS.size() - 1);
				p2 = line1.PointS.get(0);
				p3 = line2.PointS.get(0); 
				p4 = line2.PointS.get(line2.PointS.size() - 1);
			}else if(line1.FEndNo == line2.FEndNo) {
				p1 = line1.PointS.get(line1.PointS.size() - 1);
				p2 = line1.PointS.get(0);
				p3 = line2.PointS.get(line2.PointS.size() - 1);
				p4 = line2.PointS.get(0); 
			}
			return GetPathTurnTextByAngle2(GMUtility.GetSegmentAngle(p1,p2,p3,p4));
		}
		return "";
	}
	public static double GetTurnAngle3(GMLineModel line1, GMLineModel line2) {
		if (line1 != null && line1.PointS.size() > 1 && line2 != null && line2.PointS.size() > 1) {
			GMPoint p1 = new GMPoint();
			GMPoint p2 = new GMPoint();
			GMPoint p3 = new GMPoint();
			GMPoint p4 = new GMPoint();
			if (line1.FBeginNo == line2.FBeginNo) {
				p1 = line1.PointS.get(0);
				p2 = line1.PointS.get(line1.PointS.size() - 1);
				p3 = line2.PointS.get(0); 
				p4 = line2.PointS.get(line2.PointS.size() - 1);
			}else if(line1.FBeginNo == line2.FEndNo){
				p1 = line1.PointS.get(0);
				p2 = line1.PointS.get(line1.PointS.size() - 1);
				p3 = line2.PointS.get(line2.PointS.size() - 1);
				p4 = line2.PointS.get(0);  
			}else if(line1.FEndNo == line2.FBeginNo) {
				p1 = line1.PointS.get(line1.PointS.size() - 1);
				p2 = line1.PointS.get(0);
				p3 = line2.PointS.get(0); 
				p4 = line2.PointS.get(line2.PointS.size() - 1);
			}else if(line1.FEndNo == line2.FEndNo) {
				p1 = line1.PointS.get(line1.PointS.size() - 1);
				p2 = line1.PointS.get(0);
				p3 = line2.PointS.get(line2.PointS.size() - 1);
				p4 = line2.PointS.get(0); 
			}
			return  GMUtility.GetSegmentAngle(p1,p2,p3,p4);
		}
		return 0;
	}
}
