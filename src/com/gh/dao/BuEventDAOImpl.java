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

 

 
public class BuEventDAOImpl extends SqlMapClientDaoSupport implements BuEventDAO  {
	
		public BuEventDAOImpl() {
			super();
		}
		
		public void insert(BuEvent record){
			 getSqlMapClientTemplate().insert("tj_BuEvent.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuEvent record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuEvent.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuEvent record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuEvent.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuEvent selectByPrimaryKey(String fuid){
	    	BuEvent key = new BuEvent();
	         key.setFuid(fuid);
	         BuEvent record = (BuEvent) getSqlMapClientTemplate().queryForObject("tj_BuEvent.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuEvent key = new BuEvent();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuEvent.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuEvent.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuEvent> execSql(String sql){
	    	return (List<BuEvent>) getSqlMapClientTemplate().queryForList(
					"tj_BuEvent.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuEvent.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_event";
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
