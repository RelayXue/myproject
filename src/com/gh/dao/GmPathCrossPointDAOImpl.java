package com.gh.dao;

import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

 

 
public class GmPathCrossPointDAOImpl extends SqlMapClientDaoSupport implements GmPathCrossPointDAO  {
	
		public GmPathCrossPointDAOImpl() {
			super();
		}
		
		public void insert(GmPathCrossPoint record){
			 getSqlMapClientTemplate().insert("tj_GmPathCrossPoint.insert", record);
		 }
	 
		public int updateByPrimaryKey(GmPathCrossPoint record){
	    	  int rows = getSqlMapClientTemplate().update("tj_GmPathCrossPoint.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(GmPathCrossPoint record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_GmPathCrossPoint.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public GmPathCrossPoint selectByPrimaryKey(String fuid){
	    	GmPathCrossPoint key = new GmPathCrossPoint();
	         key.setFuid(fuid);
	         GmPathCrossPoint record = (GmPathCrossPoint) getSqlMapClientTemplate().queryForObject("tj_GmPathCrossPoint.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 GmPathCrossPoint key = new GmPathCrossPoint();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_GmPathCrossPoint.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_GmPathCrossPoint.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<GmPathCrossPoint> execSql(String sql){
	    	return (List<GmPathCrossPoint>) getSqlMapClientTemplate().queryForList(
					"tj_GmPathCrossPoint.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_GmPathCrossPoint.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM gm_path_cross_point";
		     if(strWhere!=null && strWhere.trim().length()>0){
		         sql = sql + " WHERE " + strWhere;
		     }
		     Object obj =  this.selectObject(sql);
		     if(obj!=null){
		         return Integer.valueOf( this.selectObject(sql).toString());
		     }
		     return 0; 
		
	    }

}
