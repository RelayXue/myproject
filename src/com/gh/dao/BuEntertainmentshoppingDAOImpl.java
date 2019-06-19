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

 

 
public class BuEntertainmentshoppingDAOImpl extends SqlMapClientDaoSupport implements BuEntertainmentshoppingDAO  {
	
		public BuEntertainmentshoppingDAOImpl() {
			super();
		}
		
		public void insert(BuEntertainmentshopping record){
			 getSqlMapClientTemplate().insert("tj_BuEntertainmentshopping.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuEntertainmentshopping record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuEntertainmentshopping.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuEntertainmentshopping record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuEntertainmentshopping.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuEntertainmentshopping selectByPrimaryKey(String fuid){
	    	BuEntertainmentshopping key = new BuEntertainmentshopping();
	         key.setFuid(fuid);
	         BuEntertainmentshopping record = (BuEntertainmentshopping) getSqlMapClientTemplate().queryForObject("tj_BuEntertainmentshopping.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuEntertainmentshopping key = new BuEntertainmentshopping();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuEntertainmentshopping.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuEntertainmentshopping.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuEntertainmentshopping> execSql(String sql){
	    	return (List<BuEntertainmentshopping>) getSqlMapClientTemplate().queryForList(
					"tj_BuEntertainmentshopping.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuEntertainmentshopping.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_entertainmentshopping";
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
