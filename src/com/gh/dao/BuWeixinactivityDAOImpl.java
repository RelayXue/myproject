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

 

 
public class BuWeixinactivityDAOImpl extends SqlMapClientDaoSupport implements BuWeixinactivityDAO  {
	
		public BuWeixinactivityDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinactivity record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinactivity.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinactivity record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinactivity.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinactivity record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinactivity.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinactivity selectByPrimaryKey(String fuid){
	    	BuWeixinactivity key = new BuWeixinactivity();
	         key.setFuid(fuid);
	         BuWeixinactivity record = (BuWeixinactivity) getSqlMapClientTemplate().queryForObject("tj_BuWeixinactivity.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinactivity key = new BuWeixinactivity();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinactivity.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinactivity.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinactivity> execSql(String sql){
	    	return (List<BuWeixinactivity>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinactivity.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinactivity.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinactivity";
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
