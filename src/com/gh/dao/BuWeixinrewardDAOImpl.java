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

 

 
public class BuWeixinrewardDAOImpl extends SqlMapClientDaoSupport implements BuWeixinrewardDAO  {
	
		public BuWeixinrewardDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinreward record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinreward.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinreward record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinreward.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinreward record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinreward.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinreward selectByPrimaryKey(String fuid){
	    	BuWeixinreward key = new BuWeixinreward();
	         key.setFuid(fuid);
	         BuWeixinreward record = (BuWeixinreward) getSqlMapClientTemplate().queryForObject("tj_BuWeixinreward.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinreward key = new BuWeixinreward();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinreward.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinreward.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinreward> execSql(String sql){
	    	return (List<BuWeixinreward>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinreward.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinreward.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinreward";
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
