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
 

 
public class BaseUserDAOImpl extends SqlMapClientDaoSupport implements BaseUserDAO  {
	
		public BaseUserDAOImpl() {
			super();
		}
		
		public void insert(BaseUser record){
			 getSqlMapClientTemplate().insert("tj_BaseUser.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseUser record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseUser.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseUser record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseUser.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseUser selectByPrimaryKey(String fuid){
	    	BaseUser key = new BaseUser();
	         key.setFuid(fuid);
	         BaseUser record = (BaseUser) getSqlMapClientTemplate().queryForObject("tj_BaseUser.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseUser key = new BaseUser();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseUser.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseUser> execSql(String sql){
	    	return (List<BaseUser>) getSqlMapClientTemplate().queryForList(
					"tj_BaseUser.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseUser.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_user";
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
