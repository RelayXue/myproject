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

 

 
public class BuLinkDAOImpl extends SqlMapClientDaoSupport implements BuLinkDAO  {
	
		public BuLinkDAOImpl() {
			super();
		}
		
		public void insert(BuLink record){
			 getSqlMapClientTemplate().insert("tj_BuLink.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuLink record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuLink.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuLink record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuLink.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuLink selectByPrimaryKey(String fuid){
	    	BuLink key = new BuLink();
	         key.setFuid(fuid);
	         BuLink record = (BuLink) getSqlMapClientTemplate().queryForObject("tj_BuLink.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuLink key = new BuLink();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuLink.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuLink.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuLink> execSql(String sql){
	    	return (List<BuLink>) getSqlMapClientTemplate().queryForList(
					"tj_BuLink.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuLink.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_link";
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
