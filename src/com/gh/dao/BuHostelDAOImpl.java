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

 

 
public class BuHostelDAOImpl extends SqlMapClientDaoSupport implements BuHostelDAO  {
	
		public BuHostelDAOImpl() {
			super();
		}
		
		public void insert(BuHostel record){
			 getSqlMapClientTemplate().insert("tj_BuHostel.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuHostel record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuHostel.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuHostel record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuHostel.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuHostel selectByPrimaryKey(String fuid){
	    	BuHostel key = new BuHostel();
	         key.setFuid(fuid);
	         BuHostel record = (BuHostel) getSqlMapClientTemplate().queryForObject("tj_BuHostel.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuHostel key = new BuHostel();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuHostel.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuHostel.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuHostel> execSql(String sql){
	    	return (List<BuHostel>) getSqlMapClientTemplate().queryForList(
					"tj_BuHostel.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuHostel.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_hostel";
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
