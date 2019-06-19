package com.wuzhen.gmpath;

import java.util.ArrayList;
import java.util.List;

public class GMOptimalPath {
	// / <summary>
	// / 起始交叉点
	// / </summary>
	public int BeginNo = 0;
	// / <summary>
	// / 起始交叉点
	// / </summary>
	public int EndNo = 0;
	// / <summary>
	// /最优交叉点路径
	// / </summary>
	public String PointPath = "";
	// / <summary>
	// / 距离长度
	// / </summary>
	public double Length = GMIndexConfig.MaxLength;
	// / <summary>
	// / 最优线路路径
	// / </summary>
	public String RoadPath = "";
	// / <summary>
	// / 是否已处理
	// / </summary>
	public Boolean IsHandler = false;

	public GMCrossPointModel StartPointModel = null;
	public GMCrossPointModel EndPointModel = null;
	/*
	 * 最优路径经过线路信息集合
	 */
	public List<GMLineModel> LineS = new ArrayList<GMLineModel>();

	private List<GMLineModel> _innerLineS = null;

	/*
	 * 得到最优线路编号
	 */
	public String GetRoadPath() {
		String rtn = "";
		for (int u = 0; u < this.LineS.size(); u++) {
			if (rtn != "") {
				rtn += ",";
			}
			rtn = rtn + this.LineS.get(u).getFno();
		}
		return rtn;
	}

	/*
	 * 执行耗时(秒)
	 */
	public double ExecSeconds = 0;
	/*
	 * 起点X
	 */
	public double BeginX = 0;
	/*
	 * 起点Y
	 */
	public double BeginY = 0;
	/*
	 * 始点X
	 */
	public double EndX = 0;
	/*
	 * 始点Y
	 */
	public double EndY = 0;

	/**
	 * 得到最优路径空间坐标串信息
	 */
	public String GetPathString() {
		String rtn = "";
		if (this.LineS.size() > 0) {
			boolean order = false;
			for (int u = 0; u < this.LineS.size(); u++) {
				GMLineModel line = this.LineS.get(u);
				if (u == 0) {
					if (line.FBeginNo == this.BeginNo) {
						order = true;
					}
				} else {
					GMLineModel line2 = this.LineS.get(u - 1);
					if (order) {
						if (line.FBeginNo == line2.FEndNo) {
							order = true;
						} else {
							order = false;
						}
					} else {
						if (line.FBeginNo == line2.FBeginNo) {
							order = true;
						} else {
							order = false;
						}
					}
				}
				if (order) {
					if (line.PointS.size() > 0) {
						for (int m = 0; m < line.PointS.size(); m++) {
							if (rtn != "") {
								rtn = rtn + ",";
							}
							rtn = rtn + line.PointS.get(m).ToFormatString(" ");
						}
					}
					order = true;
				} else {
					if (line.PointS.size() > 1) {
						for (int m = line.PointS.size() - 1; m >= 0; m--) {
							if (rtn != "") {
								rtn = rtn + ",";
							}
							rtn = rtn + line.PointS.get(m).ToFormatString(" ");
						}
					}
					order = false;
				}
			}
		}
		/*
		 * if (rtn != "") { if (this.BeginX != 0 && this.BeginY != 0) { rtn =
		 * this.BeginX + " " + this.BeginY + "," + rtn; } if (this.EndX != 0 &&
		 * this.EndY != 0) { rtn = rtn + "," + this.EndX + " " + this.EndY; } }
		 */
		return rtn;
	}

	/**
	 * 得到路径描述信息
	 * 
	 * @return
	 */
	public String GetPathDesString() {
		StringBuilder sb = new StringBuilder();
		String temp = "";
		String name = "";
		String distanceText = "";
		List<GMLineModel> lines = GMBusinessFactory.GetIntegrateNextRepeatData(this.LineS);
		if (this.LineS != null) {
			for (int u = 0; u < lines.size(); u++) {
				GMLineModel md = lines.get(u);
				GMLineModel md2 = null;
				if (lines.size() > u + 1) {
					md2 = lines.get(u + 1);
				}
				distanceText = GMUtility.GetDistanceText(md.getFlength());
				name = md.getFname() == null ? "" : md.getFname().trim();
				if (u == 0) {
					temp = GMBusinessFactory.GetPathTextByLine(md);
					temp = "从起点" + name + temp + "出发,大概途经" + distanceText;
					if (md2 != null) {
						String name2 = md2.getFname() == null ? "" : md2.getFname().trim();
						String zx = GMBusinessFactory.GetTurnDirectionText2(md, md2);
//						 double dd = GMBusinessFactory.GetTurnAngle3(md, md2);
//						 zx += "("+dd+")";
						if (!name2.equals("")) {
							temp += "," + zx + "进入" + name2;
						} else {
							temp += ",到达下一路口" + zx;
						}
					}
				} else if (u == lines.size() - 1) {
					temp += name + distanceText + "，到达终点";
				} else {
					if (name.equals("")) {
						temp += "行驶" + distanceText;
					} else {
						temp += "沿" + name + distanceText;
					}
					if (md2 != null) {
						String name2 = md2.getFname() == null ? "" : md2.getFname().trim();
						String zx = GMBusinessFactory.GetTurnDirectionText2(md, md2);
						 //double dd = GMBusinessFactory.GetTurnAngle3(md, md2);
						 //zx += "("+dd+")";
						if (!name2.equals("")) {
							temp += "," + zx + "进入" + md2.getFname();
						} else {
							temp += ",到达下一路口" + zx;
						}
					}
				}
				sb.append(temp);
				sb.append("##");
				temp= "";
			}
		}
		return sb.toString();
	}

	/**
	 * 得到最优路径，道路坐标串数组信息
	 * 
	 * @return
	 */
	public List<String> GetPathDesLineArr() {
		List<String> rtn = new ArrayList<String>();
		if (_innerLineS == null) {
			_innerLineS = GMBusinessFactory.GetIntegrateNextRepeatData(this.LineS);
		}
		if (_innerLineS != null) {
			for (int u = 0; u < _innerLineS.size(); u++) {
				rtn.add(_innerLineS.get(u).PointS.ToDefaultString());
			}
		}
		return rtn;
	}

	/**
	 * 得到最优路径，道路走向，文本描述信息
	 * 
	 * @return
	 */
	public List<String> GetPathDesStringArr() {
		List<String> sb = new ArrayList<String>();
		String temp = "";
		String name = "";
		String distanceText = "";
		List<GMLineModel> lines = GMBusinessFactory.GetIntegrateNextRepeatData(this.LineS);
		if (this.LineS != null) {
			for (int u = 0; u < lines.size(); u++) {
				GMLineModel md = lines.get(u);
				GMLineModel md2 = null;
				if (lines.size() > u + 1) {
					md2 = lines.get(u + 1);
				}
				distanceText = GMUtility.GetDistanceText(md.getFlength());
				name = md.getFname() == null ? "" : md.getFname().trim();
				if (u == 0) {
					temp = GMBusinessFactory.GetPathTextByLine(md);
					temp = "从<strong>起点</strong>" + GMUtility.GetStrongString(name) + temp + "出发,大概途经" + distanceText;
					if (md2 != null) {
						String name2 = md2.getFname() == null ? "" : md2.getFname().trim();
						String zx = GMBusinessFactory.GetTurnDirectionText2(md, md2);
						// double dd = GMBusinessFactory.GetTurnAngle3(md, md2);
						 //zx += "("+dd+")";
						if (!name2.equals("")) {
							temp += "," + zx + "进入" + GMUtility.GetStrongString(name2);
						} else {
							temp += ",到达下一路口" + zx;
						}
					}
				} else if (u == lines.size() - 1) {
					if (name.equals("")) {
						temp += "行驶" + distanceText;
					} else {
						temp += "沿" + GMUtility.GetStrongString(name) + distanceText;
					}
					temp +="，到达<strong>终点</strong>";
				} else {
					if (name.equals("")) {
						temp += "行驶" + distanceText;
					} else {
						temp += "沿" + GMUtility.GetStrongString(name) + distanceText;
					}
					if (md2 != null) {
						String name2 = md2.getFname() == null ? "" : md2.getFname().trim();
						String zx = GMBusinessFactory.GetTurnDirectionText2(md, md2);
						// double dd = GMBusinessFactory.GetTurnAngle3(md, md2);
						 //zx += "("+dd+")";
						if (!name2.equals("")) {
							temp += "," + zx + "进入" + GMUtility.GetStrongString(md2.getFname());
						} else {
							temp += ",到达下一路口" + zx;
						}
					}
				}
				if(lines.size()==1){
					temp +=",到达<strong>终点</strong>"; 
				}
				sb.add(temp);
				temp="";
			}
		}
		return sb;
	}
}
