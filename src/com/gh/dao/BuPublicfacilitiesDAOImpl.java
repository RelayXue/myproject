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

 

 
public class BuPublicfacilitiesDAOImpl extends SqlMapClientDaoSupport implements BuPublicfacilitiesDAO  {
	
		public BuPublicfacilitiesDAOImpl() {
			super();
		}
		
		public void insert(BuPublicfacilities record){
			 getSqlMapClientTemplate().insert("tj_BuPublicfacilities.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuPublicfacilities record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuPublicfacilities.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuPublicfacilities record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuPublicfacilities.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuPublicfacilities selectByPrimaryKey(String fuid){
	    	BuPublicfacilities key = new BuPublicfacilities();
	         key.setFuid(fuid);
	         BuPublicfacilities record = (BuPublicfacilities) getSqlMapClientTemplate().queryForObject("tj_BuPublicfacilities.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuPublicfacilities key = new BuPublicfacilities();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuPublicfacilities.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuPublicfacilities.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuPublicfacilities> execSql(String sql){
	    	return (List<BuPublicfacilities>) getSqlMapClientTemplate().queryForList(
					"tj_BuPublicfacilities.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuPublicfacilities.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_publicfacilities";
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
