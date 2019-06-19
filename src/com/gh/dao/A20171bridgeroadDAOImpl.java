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

 

 
public class A20171bridgeroadDAOImpl extends SqlMapClientDaoSupport implements A20171bridgeroadDAO  {
	
		public A20171bridgeroadDAOImpl() {
			super();
		}
		
		public void insert(A20171bridgeroad record){
			 getSqlMapClientTemplate().insert("tj_A20171bridgeroad.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20171bridgeroad record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20171bridgeroad.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20171bridgeroad record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20171bridgeroad.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20171bridgeroad selectByPrimaryKey(String fuid){
	    	A20171bridgeroad key = new A20171bridgeroad();
	         key.setFuid(fuid);
	         A20171bridgeroad record = (A20171bridgeroad) getSqlMapClientTemplate().queryForObject("tj_A20171bridgeroad.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20171bridgeroad key = new A20171bridgeroad();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20171bridgeroad.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20171bridgeroad.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20171bridgeroad> execSql(String sql){
	    	return (List<A20171bridgeroad>) getSqlMapClientTemplate().queryForList(
					"tj_A20171bridgeroad.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20171bridgeroad.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2017_1_bridgeroad";
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
