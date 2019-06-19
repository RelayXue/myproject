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

 

 
public class A20162userDAOImpl extends SqlMapClientDaoSupport implements A20162userDAO  {
	
		public A20162userDAOImpl() {
			super();
		}
		
		public void insert(A20162user record){
			 getSqlMapClientTemplate().insert("tj_A20162user.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20162user record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20162user.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20162user record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20162user.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20162user selectByPrimaryKey(String fuid){
	    	A20162user key = new A20162user();
	         key.setFuid(fuid);
	         A20162user record = (A20162user) getSqlMapClientTemplate().queryForObject("tj_A20162user.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20162user key = new A20162user();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20162user.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20162user.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20162user> execSql(String sql){
	    	return (List<A20162user>) getSqlMapClientTemplate().queryForList(
					"tj_A20162user.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20162user.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2016_2_user";
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
