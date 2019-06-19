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

 

 
public class BuNavigationDAOImpl extends SqlMapClientDaoSupport implements BuNavigationDAO  {
	
		public BuNavigationDAOImpl() {
			super();
		}
		
		public void insert(BuNavigation record){
			 getSqlMapClientTemplate().insert("tj_BuNavigation.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuNavigation record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuNavigation.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuNavigation record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuNavigation.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuNavigation selectByPrimaryKey(String fuid){
	    	BuNavigation key = new BuNavigation();
	         key.setFuid(fuid);
	         BuNavigation record = (BuNavigation) getSqlMapClientTemplate().queryForObject("tj_BuNavigation.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuNavigation key = new BuNavigation();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuNavigation.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuNavigation.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuNavigation> execSql(String sql){
	    	return (List<BuNavigation>) getSqlMapClientTemplate().queryForList(
					"tj_BuNavigation.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuNavigation.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_navigation";
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
