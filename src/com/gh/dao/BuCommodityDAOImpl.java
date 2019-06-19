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

 

 
public class BuCommodityDAOImpl extends SqlMapClientDaoSupport implements BuCommodityDAO  {
	
		public BuCommodityDAOImpl() {
			super();
		}
		
		public void insert(BuCommodity record){
			 getSqlMapClientTemplate().insert("tj_BuCommodity.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuCommodity record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuCommodity.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuCommodity record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuCommodity.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuCommodity selectByPrimaryKey(String fuid){
	    	BuCommodity key = new BuCommodity();
	         key.setFuid(fuid);
	         BuCommodity record = (BuCommodity) getSqlMapClientTemplate().queryForObject("tj_BuCommodity.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuCommodity key = new BuCommodity();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuCommodity.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuCommodity.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuCommodity> execSql(String sql){
	    	return (List<BuCommodity>) getSqlMapClientTemplate().queryForList(
					"tj_BuCommodity.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuCommodity.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_commodity";
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
