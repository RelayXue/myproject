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

 

 
public class BuStreetlightsDAOImpl extends SqlMapClientDaoSupport implements BuStreetlightsDAO  {
	
		public BuStreetlightsDAOImpl() {
			super();
		}
		
		public void insert(BuStreetlights record){
			 getSqlMapClientTemplate().insert("tj_BuStreetlights.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuStreetlights record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuStreetlights.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuStreetlights record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuStreetlights.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuStreetlights selectByPrimaryKey(String fuid){
	    	BuStreetlights key = new BuStreetlights();
	         key.setFuid(fuid);
	         BuStreetlights record = (BuStreetlights) getSqlMapClientTemplate().queryForObject("tj_BuStreetlights.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuStreetlights key = new BuStreetlights();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuStreetlights.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuStreetlights.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuStreetlights> execSql(String sql){
	    	return (List<BuStreetlights>) getSqlMapClientTemplate().queryForList(
					"tj_BuStreetlights.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuStreetlights.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_streetlights";
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
