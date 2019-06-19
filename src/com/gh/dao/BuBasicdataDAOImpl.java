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

 

 
public class BuBasicdataDAOImpl extends SqlMapClientDaoSupport implements BuBasicdataDAO  {
	
		public BuBasicdataDAOImpl() {
			super();
		}
		
		public void insert(BuBasicdata record){
			 getSqlMapClientTemplate().insert("tj_BuBasicdata.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuBasicdata record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuBasicdata.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuBasicdata record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuBasicdata.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuBasicdata selectByPrimaryKey(String fuid){
	    	BuBasicdata key = new BuBasicdata();
	         key.setFuid(fuid);
	         BuBasicdata record = (BuBasicdata) getSqlMapClientTemplate().queryForObject("tj_BuBasicdata.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuBasicdata key = new BuBasicdata();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuBasicdata.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuBasicdata.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuBasicdata> execSql(String sql){
	    	return (List<BuBasicdata>) getSqlMapClientTemplate().queryForList(
					"tj_BuBasicdata.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuBasicdata.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_basicdata";
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
