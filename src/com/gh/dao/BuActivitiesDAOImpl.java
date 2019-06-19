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

 

 
public class BuActivitiesDAOImpl extends SqlMapClientDaoSupport implements BuActivitiesDAO  {
	
		public BuActivitiesDAOImpl() {
			super();
		}
		
		public void insert(BuActivities record){
			 getSqlMapClientTemplate().insert("tj_BuActivities.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuActivities record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuActivities.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuActivities record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuActivities.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuActivities selectByPrimaryKey(String fuid){
	    	BuActivities key = new BuActivities();
	         key.setFuid(fuid);
	         BuActivities record = (BuActivities) getSqlMapClientTemplate().queryForObject("tj_BuActivities.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuActivities key = new BuActivities();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuActivities.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuActivities.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuActivities> execSql(String sql){
	    	return (List<BuActivities>) getSqlMapClientTemplate().queryForList(
					"tj_BuActivities.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuActivities.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_activities";
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
