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

 

 
public class A20162subjectDAOImpl extends SqlMapClientDaoSupport implements A20162subjectDAO  {
	
		public A20162subjectDAOImpl() {
			super();
		}
		
		public void insert(A20162subject record){
			 getSqlMapClientTemplate().insert("tj_A20162subject.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20162subject record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20162subject.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20162subject record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20162subject.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20162subject selectByPrimaryKey(String fuid){
	    	A20162subject key = new A20162subject();
	         key.setFuid(fuid);
	         A20162subject record = (A20162subject) getSqlMapClientTemplate().queryForObject("tj_A20162subject.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20162subject key = new A20162subject();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20162subject.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20162subject.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20162subject> execSql(String sql){
	    	return (List<A20162subject>) getSqlMapClientTemplate().queryForList(
					"tj_A20162subject.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20162subject.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2016_2_subject";
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
