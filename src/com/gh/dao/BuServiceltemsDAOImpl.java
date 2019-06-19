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

 

 
public class BuServiceltemsDAOImpl extends SqlMapClientDaoSupport implements BuServiceltemsDAO  {
	
		public BuServiceltemsDAOImpl() {
			super();
		}
		
		public void insert(BuServiceltems record){
			 getSqlMapClientTemplate().insert("tj_BuServiceltems.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuServiceltems record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuServiceltems.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuServiceltems record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuServiceltems.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuServiceltems selectByPrimaryKey(String fuid){
	    	BuServiceltems key = new BuServiceltems();
	         key.setFuid(fuid);
	         BuServiceltems record = (BuServiceltems) getSqlMapClientTemplate().queryForObject("tj_BuServiceltems.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuServiceltems key = new BuServiceltems();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuServiceltems.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuServiceltems.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuServiceltems> execSql(String sql){
	    	return (List<BuServiceltems>) getSqlMapClientTemplate().queryForList(
					"tj_BuServiceltems.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuServiceltems.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_serviceltems";
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
