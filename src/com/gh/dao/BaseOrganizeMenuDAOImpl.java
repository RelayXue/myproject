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
 

 
public class BaseOrganizeMenuDAOImpl extends SqlMapClientDaoSupport implements BaseOrganizeMenuDAO  {
	
		public BaseOrganizeMenuDAOImpl() {
			super();
		}
		
		public void insert(BaseOrganizeMenu record){
			 getSqlMapClientTemplate().insert("tj_BaseOrganizeMenu.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseOrganizeMenu record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseOrganizeMenu.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseOrganizeMenu record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseOrganizeMenu.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseOrganizeMenu selectByPrimaryKey(String fuid){
	    	BaseOrganizeMenu key = new BaseOrganizeMenu();
	         key.setFuid(fuid);
	         BaseOrganizeMenu record = (BaseOrganizeMenu) getSqlMapClientTemplate().queryForObject("tj_BaseOrganizeMenu.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseOrganizeMenu key = new BaseOrganizeMenu();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseOrganizeMenu.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseOrganizeMenu> execSql(String sql){
	    	return (List<BaseOrganizeMenu>) getSqlMapClientTemplate().queryForList(
					"tj_BaseOrganizeMenu.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseOrganizeMenu.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_organize_menu";
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
