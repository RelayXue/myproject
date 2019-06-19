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

 

 
public class BuQuicklinksDAOImpl extends SqlMapClientDaoSupport implements BuQuicklinksDAO  {
	
		public BuQuicklinksDAOImpl() {
			super();
		}
		
		public void insert(BuQuicklinks record){
			 getSqlMapClientTemplate().insert("tj_BuQuicklinks.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuQuicklinks record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuQuicklinks.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuQuicklinks record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuQuicklinks.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuQuicklinks selectByPrimaryKey(String fuid){
	    	BuQuicklinks key = new BuQuicklinks();
	         key.setFuid(fuid);
	         BuQuicklinks record = (BuQuicklinks) getSqlMapClientTemplate().queryForObject("tj_BuQuicklinks.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuQuicklinks key = new BuQuicklinks();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuQuicklinks.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuQuicklinks.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuQuicklinks> execSql(String sql){
	    	return (List<BuQuicklinks>) getSqlMapClientTemplate().queryForList(
					"tj_BuQuicklinks.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuQuicklinks.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_quicklinks";
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
