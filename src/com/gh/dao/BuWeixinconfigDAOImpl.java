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

 

 
public class BuWeixinconfigDAOImpl extends SqlMapClientDaoSupport implements BuWeixinconfigDAO  {
	
		public BuWeixinconfigDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinconfig record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinconfig.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinconfig record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinconfig.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinconfig record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinconfig.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinconfig selectByPrimaryKey(String fuid){
	    	BuWeixinconfig key = new BuWeixinconfig();
	         key.setFuid(fuid);
	         BuWeixinconfig record = (BuWeixinconfig) getSqlMapClientTemplate().queryForObject("tj_BuWeixinconfig.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinconfig key = new BuWeixinconfig();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinconfig.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinconfig.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinconfig> execSql(String sql){
	    	return (List<BuWeixinconfig>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinconfig.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinconfig.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinconfig";
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
