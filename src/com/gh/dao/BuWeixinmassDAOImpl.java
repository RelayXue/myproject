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

 

 
public class BuWeixinmassDAOImpl extends SqlMapClientDaoSupport implements BuWeixinmassDAO  {
	
		public BuWeixinmassDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinmass record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinmass.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinmass record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinmass.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinmass record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinmass.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinmass selectByPrimaryKey(String fuid){
	    	BuWeixinmass key = new BuWeixinmass();
	         key.setFuid(fuid);
	         BuWeixinmass record = (BuWeixinmass) getSqlMapClientTemplate().queryForObject("tj_BuWeixinmass.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinmass key = new BuWeixinmass();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmass.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmass.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinmass> execSql(String sql){
	    	return (List<BuWeixinmass>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinmass.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinmass.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinmass";
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
