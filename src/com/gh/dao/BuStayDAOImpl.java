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

 

 
public class BuStayDAOImpl extends SqlMapClientDaoSupport implements BuStayDAO  {
	
		public BuStayDAOImpl() {
			super();
		}
		
		public void insert(BuStay record){
			 getSqlMapClientTemplate().insert("tj_BuStay.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuStay record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuStay.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuStay record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuStay.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuStay selectByPrimaryKey(String fuid){
	    	BuStay key = new BuStay();
	         key.setFuid(fuid);
	         BuStay record = (BuStay) getSqlMapClientTemplate().queryForObject("tj_BuStay.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuStay key = new BuStay();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuStay.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuStay.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuStay> execSql(String sql){
	    	return (List<BuStay>) getSqlMapClientTemplate().queryForList(
					"tj_BuStay.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuStay.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_stay";
		     if(strWhere!=null && strWhere.trim().length()>0){
		         sql = sql + " WHERE " + strWhere;
		     }
		     Object obj =  this.selectObject(sql);
		     if(obj!=null){
		         return Integer.valueOf( this.selectObject(sql).toString());
		     }
		     return 0; 
		
	    }
	    
	    public List<DataEntity> execData(String sql){
	    	return (List<DataEntity>) getSqlMapClientTemplate().queryForList(
					"tj_BuStay.execData", sql);
	    }
	    
	    public Object updateBySql(String sql){
	    	return getSqlMapClientTemplate().update(
					"tj_BuStay.updateBySql", sql);
	    }

}
