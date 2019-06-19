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

 

 
public class BuAccommodationDAOImpl extends SqlMapClientDaoSupport implements BuAccommodationDAO  {
	
		public BuAccommodationDAOImpl() {
			super();
		}
		
		public void insert(BuAccommodation record){
			 getSqlMapClientTemplate().insert("tj_BuAccommodation.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuAccommodation record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuAccommodation.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuAccommodation record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuAccommodation.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuAccommodation selectByPrimaryKey(String fuid){
	    	BuAccommodation key = new BuAccommodation();
	         key.setFuid(fuid);
	         BuAccommodation record = (BuAccommodation) getSqlMapClientTemplate().queryForObject("tj_BuAccommodation.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuAccommodation key = new BuAccommodation();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuAccommodation.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuAccommodation.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuAccommodation> execSql(String sql){
	    	return (List<BuAccommodation>) getSqlMapClientTemplate().queryForList(
					"tj_BuAccommodation.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuAccommodation.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_accommodation";
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
