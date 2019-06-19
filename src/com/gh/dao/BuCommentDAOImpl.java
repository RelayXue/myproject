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

 

 
public class BuCommentDAOImpl extends SqlMapClientDaoSupport implements BuCommentDAO  {
	
		public BuCommentDAOImpl() {
			super();
		}
		
		public void insert(BuComment record){
			 getSqlMapClientTemplate().insert("tj_BuComment.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuComment record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuComment.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuComment record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuComment.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuComment selectByPrimaryKey(String fuid){
	    	BuComment key = new BuComment();
	         key.setFuid(fuid);
	         BuComment record = (BuComment) getSqlMapClientTemplate().queryForObject("tj_BuComment.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuComment key = new BuComment();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuComment.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuComment.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuComment> execSql(String sql){
	    	return (List<BuComment>) getSqlMapClientTemplate().queryForList(
					"tj_BuComment.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuComment.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_comment";
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
