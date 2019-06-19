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

import com.gh.entity.BaseUser;
 

 
public class BaseScopeDAOImpl extends SqlMapClientDaoSupport implements BaseScopeDAO  {
	
		public BaseScopeDAOImpl() {
			super();
		}
		
		public void insert(BaseScope record){
			 getSqlMapClientTemplate().insert("tj_BaseScope.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseScope record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseScope.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseScope record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseScope.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseScope selectByPrimaryKey(String fuid){
	    	BaseScope key = new BaseScope();
	         key.setFuid(fuid);
	         BaseScope record = (BaseScope) getSqlMapClientTemplate().queryForObject("tj_BaseScope.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseScope key = new BaseScope();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseScope.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseScope> execSql(String sql){
	    	return (List<BaseScope>) getSqlMapClientTemplate().queryForList(
					"tj_BaseScope.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseScope.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_scope";
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
