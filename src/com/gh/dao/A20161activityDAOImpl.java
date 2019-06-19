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

 

 
public class A20161activityDAOImpl extends SqlMapClientDaoSupport implements A20161activityDAO  {
	
		public A20161activityDAOImpl() {
			super();
		}
		
		public void insert(A20161activity record){
			 getSqlMapClientTemplate().insert("tj_A20161activity.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20161activity record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20161activity.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20161activity record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20161activity.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20161activity selectByPrimaryKey(String fuid){
	    	A20161activity key = new A20161activity();
	         key.setFuid(fuid);
	         A20161activity record = (A20161activity) getSqlMapClientTemplate().queryForObject("tj_A20161activity.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20161activity key = new A20161activity();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20161activity.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20161activity.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20161activity> execSql(String sql){
	    	
	    	return (List<A20161activity>) getSqlMapClientTemplate().queryForList("tj_A20161activity.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20161activity.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2016_1_activity";
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
