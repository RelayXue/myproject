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

 

 
public class BuWeixinluckdrawDAOImpl extends SqlMapClientDaoSupport implements BuWeixinluckdrawDAO  {
	
		public BuWeixinluckdrawDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinluckdraw record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinluckdraw.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinluckdraw record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinluckdraw.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinluckdraw record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinluckdraw.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinluckdraw selectByPrimaryKey(String fuid){
	    	BuWeixinluckdraw key = new BuWeixinluckdraw();
	         key.setFuid(fuid);
	         BuWeixinluckdraw record = (BuWeixinluckdraw) getSqlMapClientTemplate().queryForObject("tj_BuWeixinluckdraw.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinluckdraw key = new BuWeixinluckdraw();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinluckdraw.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinluckdraw.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinluckdraw> execSql(String sql){
	    	return (List<BuWeixinluckdraw>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinluckdraw.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinluckdraw.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinluckdraw";
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
