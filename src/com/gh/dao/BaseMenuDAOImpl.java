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
 

 
public class BaseMenuDAOImpl extends SqlMapClientDaoSupport implements BaseMenuDAO  {
	
		public BaseMenuDAOImpl() {
			super();
		}
		
		public void insert(BaseMenu record){
			 getSqlMapClientTemplate().insert("tj_BaseMenu.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseMenu record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseMenu.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseMenu record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseMenu.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseMenu selectByPrimaryKey(String fuid){
	    	BaseMenu key = new BaseMenu();
	         key.setFuid(fuid);
	         BaseMenu record = (BaseMenu) getSqlMapClientTemplate().queryForObject("tj_BaseMenu.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseMenu key = new BaseMenu();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseMenu.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseMenu> execSql(String sql){
	    	return (List<BaseMenu>) getSqlMapClientTemplate().queryForList(
					"tj_BaseMenu.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseMenu.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_menu";
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
