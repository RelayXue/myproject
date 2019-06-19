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

 

 
public class A20161clickDAOImpl extends SqlMapClientDaoSupport implements A20161clickDAO  {
	
		public A20161clickDAOImpl() {
			super();
		}
		
		public void insert(A20161click record){
			 getSqlMapClientTemplate().insert("tj_A20161click.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20161click record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20161click.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20161click record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20161click.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20161click selectByPrimaryKey(String fuid){
	    	A20161click key = new A20161click();
	         key.setFuid(fuid);
	         A20161click record = (A20161click) getSqlMapClientTemplate().queryForObject("tj_A20161click.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20161click key = new A20161click();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20161click.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20161click.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20161click> execSql(String sql){
	    	return (List<A20161click>) getSqlMapClientTemplate().queryForList(
					"tj_A20161click.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20161click.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2016_1_click";
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
