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

 

 
public class BuGeographyDAOImpl extends SqlMapClientDaoSupport implements BuGeographyDAO  {
	
		public BuGeographyDAOImpl() {
			super();
		}
		
		public void insert(BuGeography record){
			 getSqlMapClientTemplate().insert("tj_BuGeography.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuGeography record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuGeography.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuGeography record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuGeography.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuGeography selectByPrimaryKey(String fuid){
	    	BuGeography key = new BuGeography();
	         key.setFuid(fuid);
	         BuGeography record = (BuGeography) getSqlMapClientTemplate().queryForObject("tj_BuGeography.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuGeography key = new BuGeography();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuGeography.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuGeography.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuGeography> execSql(String sql){
	    	return (List<BuGeography>) getSqlMapClientTemplate().queryForList(
					"tj_BuGeography.execSql", sql);
	    }
	    
	    public List<AutoCompleteEntity> execAutoData(String sql){
	    	return (List<AutoCompleteEntity>) getSqlMapClientTemplate().queryForList(
					"tj_BuGeography.execAutoData", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuGeography.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_geography";
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
