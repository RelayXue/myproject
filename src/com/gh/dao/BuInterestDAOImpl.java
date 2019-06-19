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

 

 
public class BuInterestDAOImpl extends SqlMapClientDaoSupport implements BuInterestDAO  {
	
		public BuInterestDAOImpl() {
			super();
		}
		
		public void insert(BuInterest record){
			 getSqlMapClientTemplate().insert("tj_BuInterest.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuInterest record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuInterest.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuInterest record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuInterest.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuInterest selectByPrimaryKey(String fuid){
	    	BuInterest key = new BuInterest();
	         key.setFuid(fuid);
	         BuInterest record = (BuInterest) getSqlMapClientTemplate().queryForObject("tj_BuInterest.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuInterest key = new BuInterest();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuInterest.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuInterest.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuInterest> execSql(String sql){
	    	return (List<BuInterest>) getSqlMapClientTemplate().queryForList(
					"tj_BuInterest.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuInterest.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_interest";
		     if(strWhere!=null && strWhere.trim().length()>0){
		         sql = sql + " WHERE " + strWhere;
		     }
		     Object obj =  this.selectObject(sql);
		     if(obj!=null){
		         return Integer.valueOf( this.selectObject(sql).toString());
		     }
		     return 0; 
		
	    }

		@Override
		public List<BuInterest> execSqlCount(String sql) {
			// TODO Auto-generated method stub
			return (List<BuInterest>) getSqlMapClientTemplate().queryForList(
					"tj_BuInterest.execSqlCount", sql);
		}

}
