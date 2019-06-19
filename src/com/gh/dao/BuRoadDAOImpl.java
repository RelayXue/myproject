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

 

 
public class BuRoadDAOImpl extends SqlMapClientDaoSupport implements BuRoadDAO  {
	
		public BuRoadDAOImpl() {
			super();
		}
		
		public void insert(BuRoad record){
			 getSqlMapClientTemplate().insert("tj_BuRoad.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuRoad record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuRoad.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuRoad record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuRoad.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuRoad selectByPrimaryKey(String fuid){
	    	BuRoad key = new BuRoad();
	         key.setFuid(fuid);
	         BuRoad record = (BuRoad) getSqlMapClientTemplate().queryForObject("tj_BuRoad.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuRoad key = new BuRoad();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuRoad.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuRoad.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuRoad> execSql(String sql){
	    	return (List<BuRoad>) getSqlMapClientTemplate().queryForList(
					"tj_BuRoad.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuRoad.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_road";
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
