package com.wuzhen.gmpath;

import java.util.ArrayList;
import java.util.List;

import com.gh.entity.GmPathCrossPoint;

public class GMCrossPointModel extends GmPathCrossPoint {
	   /// <summary>
    /// 相邻边
    /// </summary>
    public List<GMCrossPointModel> AdjactentCrossPointS = new ArrayList<GMCrossPointModel>();
    /// <summary>
    /// 相邻边信息（权值、坐标串）
    /// </summary>
    public List<GMLineModel> AdjactentLineS = new ArrayList<GMLineModel>();
    /// <summary>
    /// 点到当前点的最短路径
    /// </summary>
    public GMOptimalPath AdjactentPath = new GMOptimalPath();
    
}
