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

 

 
public class OpinionDAOImpl extends SqlMapClientDaoSupport implements OpinionDAO  {
	
		public OpinionDAOImpl() {
			super();
		}
		
		public void insert(Opinion record){
			 getSqlMapClientTemplate().insert("tj_Opinion.insert", record);
		 }
	 
		public int updateByPrimaryKey(Opinion record){
	    	  int rows = getSqlMapClientTemplate().update("tj_Opinion.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(Opinion record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_Opinion.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public Opinion selectByPrimaryKey(String fuid){
	    	Opinion key = new Opinion();
	         key.setFuid(fuid);
	         Opinion record = (Opinion) getSqlMapClientTemplate().queryForObject("tj_Opinion.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 Opinion key = new Opinion();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_Opinion.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_Opinion.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<Opinion> execSql(String sql){
	    	return (List<Opinion>) getSqlMapClientTemplate().queryForList(
					"tj_Opinion.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_Opinion.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM opinion";
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
