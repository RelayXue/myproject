package com.wuzhen.gmpath;

import java.util.ArrayList;
import java.util.List;

import com.gh.entity.GmPathCrossPoint;
import com.gh.service.GmPathCrossPointService;
import com.gh.service.GmPathLineService;

public class GMCacheCenter {
	 public static GMCacheCenter _Instance = null;
     public static GMCacheCenter GetInstance()
     {
         if (_Instance == null)
             {
                 _Instance = new GMCacheCenter();
                 if (_Instance.LineS == null || _Instance.CrossPointMatrixModel == null)
                 {
                     InitPathCache();
                 }
             }
             return _Instance;  
     }
     /*
      * 初始化缓存
      */
     public static void InitPathCache()
     {
         if (_Instance == null)
         {
             _Instance = new GMCacheCenter();
         } 
          GmPathLineService xLine =  GMDaoFactory.GmPathLineInstance;
         _Instance.LineS = GMUtility.ConvertToLineModel( xLine.execSql("SELECT * FROM GM_PATH_LINE"));
         _Instance.CrossPointMatrixModel = new ArrayList<GMCrossPointModel>();
         GmPathCrossPointService xPoint =  GMDaoFactory.GmPathCrossPointInstance;
         List<GmPathCrossPoint> pointS = xPoint.execSql("SELECT * FROM GM_PATH_CROSS_POINT ORDER BY F_NO ASC");
         _Instance.CrossPointMatrixModel = GMUtility.ConvertCorssPointSToModel(pointS);
         for (int u = 0; u < _Instance.CrossPointMatrixModel.size(); u++)
         {
             GMCrossPointModel item = _Instance.CrossPointMatrixModel.get(u);
             List<GMLineModel> pointLineS = GMUtility.GetPathLineByNo(_Instance.LineS, item.getFno());
             for (int m = 0; m < pointLineS.size(); m++)
             {
                 GMLineModel item2 = pointLineS.get(m);
				int otherNo = GMUtility.GetOtherCrossPointNo(item2.getForderByAsc(), item.getFno());
                 GMCrossPointModel _tmpItem = GMUtility.GetCrossPointByNo(_Instance.CrossPointMatrixModel, otherNo);
                 if (_tmpItem != null)
                 {
                     item.AdjactentLineS.add(item2);
                     item.AdjactentCrossPointS.add(_tmpItem);
                 }
             }
         } 
     } 
     /*
      * 路径数据
      */
     public List<GMLineModel> LineS = null;
     /*
      * 交叉点链接矩阵模型
      */
     public List<GMCrossPointModel> CrossPointMatrixModel = null;
}
