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

 

 
public class BuLoglogDAOImpl extends SqlMapClientDaoSupport implements BuLoglogDAO  {
	
		public BuLoglogDAOImpl() {
			super();
		}
		
		public void insert(BuLoglog record){
			 getSqlMapClientTemplate().insert("tj_BuLoglog.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuLoglog record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuLoglog.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuLoglog record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuLoglog.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuLoglog selectByPrimaryKey(String fuid){
	    	BuLoglog key = new BuLoglog();
	         key.setFuid(fuid);
	         BuLoglog record = (BuLoglog) getSqlMapClientTemplate().queryForObject("tj_BuLoglog.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuLoglog key = new BuLoglog();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuLoglog.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuLoglog.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuLoglog> execSql(String sql){
	    	return (List<BuLoglog>) getSqlMapClientTemplate().queryForList(
					"tj_BuLoglog.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuLoglog.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_loglog";
		     if(strWhere!=null && strWhere.trim().length()>0){
		         sql = sql + " WHERE " + strWhere;
		     }
		     Object obj =  this.selectObject(sql);
		     if(obj!=null){
		         return Integer.valueOf( this.selectObject(sql).toString());
		     }
		     return 0; 
		
	    }
	    
	    public void execUpdateSql(String sql){
	    	if(sql!=null && sql.trim().length()>0){
	    		getSqlMapClientTemplate().update("tj_BuLoglog.updateSql", sql);
	    	}
	    }

}
