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
 

 
public class BaseRoleDAOImpl extends SqlMapClientDaoSupport implements BaseRoleDAO  {
	
		public BaseRoleDAOImpl() {
			super();
		}
		
		public void insert(BaseRole record){
			 getSqlMapClientTemplate().insert("tj_BaseRole.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseRole record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseRole.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseRole record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseRole.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseRole selectByPrimaryKey(String fuid){
	    	BaseRole key = new BaseRole();
	         key.setFuid(fuid);
	         BaseRole record = (BaseRole) getSqlMapClientTemplate().queryForObject("tj_BaseRole.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseRole key = new BaseRole();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseRole.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseRole> execSql(String sql){
	    	return (List<BaseRole>) getSqlMapClientTemplate().queryForList(
					"tj_BaseRole.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseRole.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_role";
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
