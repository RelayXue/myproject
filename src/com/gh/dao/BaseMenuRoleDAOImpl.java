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
 

 
public class BaseMenuRoleDAOImpl extends SqlMapClientDaoSupport implements BaseMenuRoleDAO  {
	
		public BaseMenuRoleDAOImpl() {
			super();
		}
		
		public void insert(BaseMenuRole record){
			 getSqlMapClientTemplate().insert("tj_BaseMenuRole.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseMenuRole record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseMenuRole.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseMenuRole record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseMenuRole.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseMenuRole selectByPrimaryKey(String fuid){
	    	BaseMenuRole key = new BaseMenuRole();
	         key.setFuid(fuid);
	         BaseMenuRole record = (BaseMenuRole) getSqlMapClientTemplate().queryForObject("tj_BaseMenuRole.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
			BaseMenuRole key = new BaseMenuRole();
			key.setFuid(fuid);
			int rows = getSqlMapClientTemplate().delete("tj_BaseMenuRole.deleteByPrimaryKey", key);
			return rows;
		}
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BaseMenuRole.deleteByWhere", swhere);
	         return rows;
	    }
	    
	    public List<BaseMenuRole> execSql(String sql){
	    	return (List<BaseMenuRole>) getSqlMapClientTemplate().queryForList(
					"tj_BaseMenuRole.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseMenuRole.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_menu_role";
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
