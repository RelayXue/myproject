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

 

 
public class BuDiningDAOImpl extends SqlMapClientDaoSupport implements BuDiningDAO  {
	
		public BuDiningDAOImpl() {
			super();
		}
		
		public void insert(BuDining record){
			 getSqlMapClientTemplate().insert("tj_BuDining.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuDining record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuDining.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuDining record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuDining.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuDining selectByPrimaryKey(String fuid){
	    	BuDining key = new BuDining();
	         key.setFuid(fuid);
	         BuDining record = (BuDining) getSqlMapClientTemplate().queryForObject("tj_BuDining.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuDining key = new BuDining();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuDining.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuDining.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuDining> execSql(String sql){
	    	return (List<BuDining>) getSqlMapClientTemplate().queryForList(
					"tj_BuDining.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuDining.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_dining";
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
