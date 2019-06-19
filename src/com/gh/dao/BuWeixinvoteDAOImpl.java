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

 

 
public class BuWeixinvoteDAOImpl extends SqlMapClientDaoSupport implements BuWeixinvoteDAO  {
	
		public BuWeixinvoteDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinvote record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinvote.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinvote record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinvote.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinvote record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinvote.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinvote selectByPrimaryKey(String fuid){
	    	BuWeixinvote key = new BuWeixinvote();
	         key.setFuid(fuid);
	         BuWeixinvote record = (BuWeixinvote) getSqlMapClientTemplate().queryForObject("tj_BuWeixinvote.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinvote key = new BuWeixinvote();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinvote.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinvote.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinvote> execSql(String sql){
	    	return (List<BuWeixinvote>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinvote.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinvote.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinvote";
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
