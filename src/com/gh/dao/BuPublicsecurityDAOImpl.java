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

 

 
public class BuPublicsecurityDAOImpl extends SqlMapClientDaoSupport implements BuPublicsecurityDAO  {
	
		public BuPublicsecurityDAOImpl() {
			super();
		}
		
		public void insert(BuPublicsecurity record){
			 getSqlMapClientTemplate().insert("tj_BuPublicsecurity.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuPublicsecurity record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuPublicsecurity.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuPublicsecurity record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuPublicsecurity.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuPublicsecurity selectByPrimaryKey(String fuid){
	    	BuPublicsecurity key = new BuPublicsecurity();
	         key.setFuid(fuid);
	         BuPublicsecurity record = (BuPublicsecurity) getSqlMapClientTemplate().queryForObject("tj_BuPublicsecurity.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuPublicsecurity key = new BuPublicsecurity();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuPublicsecurity.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuPublicsecurity.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuPublicsecurity> execSql(String sql){
	    	return (List<BuPublicsecurity>) getSqlMapClientTemplate().queryForList(
					"tj_BuPublicsecurity.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuPublicsecurity.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_publicsecurity";
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
