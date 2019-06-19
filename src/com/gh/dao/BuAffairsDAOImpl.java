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

 

 
public class BuAffairsDAOImpl extends SqlMapClientDaoSupport implements BuAffairsDAO  {
	
		public BuAffairsDAOImpl() {
			super();
		}
		
		public void insert(BuAffairs record){
			 getSqlMapClientTemplate().insert("tj_BuAffairs.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuAffairs record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuAffairs.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuAffairs record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuAffairs.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuAffairs selectByPrimaryKey(String fuid){
	    	BuAffairs key = new BuAffairs();
	         key.setFuid(fuid);
	         BuAffairs record = (BuAffairs) getSqlMapClientTemplate().queryForObject("tj_BuAffairs.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuAffairs key = new BuAffairs();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuAffairs.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuAffairs.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuAffairs> execSql(String sql){
	    	return (List<BuAffairs>) getSqlMapClientTemplate().queryForList(
					"tj_BuAffairs.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuAffairs.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_affairs";
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
