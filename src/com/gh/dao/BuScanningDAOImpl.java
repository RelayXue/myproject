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

 

 
public class BuScanningDAOImpl extends SqlMapClientDaoSupport implements BuScanningDAO  {
	
		public BuScanningDAOImpl() {
			super();
		}
		
		public void insert(BuScanning record){
			 getSqlMapClientTemplate().insert("tj_BuScanning.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuScanning record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuScanning.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuScanning record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuScanning.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuScanning selectByPrimaryKey(String fuid){
	    	BuScanning key = new BuScanning();
	         key.setFuid(fuid);
	         BuScanning record = (BuScanning) getSqlMapClientTemplate().queryForObject("tj_BuScanning.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuScanning key = new BuScanning();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuScanning.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuScanning.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuScanning> execSql(String sql){
	    	return (List<BuScanning>) getSqlMapClientTemplate().queryForList(
					"tj_BuScanning.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuScanning.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_scanning";
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
