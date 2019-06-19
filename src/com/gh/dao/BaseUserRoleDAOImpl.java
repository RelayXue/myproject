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
 

 
public class BaseUserRoleDAOImpl extends SqlMapClientDaoSupport implements BaseUserRoleDAO  {
	
		public BaseUserRoleDAOImpl() {
			super();
		}
		
		public void insert(BaseUserRole record){
			 getSqlMapClientTemplate().insert("tj_BaseUserRole.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseUserRole record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseUserRole.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseUserRole record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseUserRole.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseUserRole selectByPrimaryKey(String fuid){
	    	BaseUserRole key = new BaseUserRole();
	         key.setFuid(fuid);
	         BaseUserRole record = (BaseUserRole) getSqlMapClientTemplate().queryForObject("tj_BaseUserRole.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseUserRole key = new BaseUserRole();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseUserRole.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseUserRole> execSql(String sql){
	    	return (List<BaseUserRole>) getSqlMapClientTemplate().queryForList(
					"tj_BaseUserRole.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseUserRole.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_user_role";
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
		public List<BaseUserRole> selectView(String sql) {
			// TODO Auto-generated method stub
			 if(sql!=null && sql.trim().length()>0){
		         return (List<BaseUserRole>) getSqlMapClientTemplate().queryForList("tj_BaseUserRole.abatorgenerated_selectView",sql);
		     }
		     return null;
		}

}
