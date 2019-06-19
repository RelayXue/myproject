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

 

 
public class BuGpsdeviceDAOImpl extends SqlMapClientDaoSupport implements BuGpsdeviceDAO  {
	
		public BuGpsdeviceDAOImpl() {
			super();
		}
		
		public void insert(BuGpsdevice record){
			 getSqlMapClientTemplate().insert("tj_BuGpsdevice.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuGpsdevice record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuGpsdevice.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuGpsdevice record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuGpsdevice.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuGpsdevice selectByPrimaryKey(String fuid){
	    	BuGpsdevice key = new BuGpsdevice();
	         key.setFuid(fuid);
	         BuGpsdevice record = (BuGpsdevice) getSqlMapClientTemplate().queryForObject("tj_BuGpsdevice.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuGpsdevice key = new BuGpsdevice();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuGpsdevice.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuGpsdevice.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuGpsdevice> execSql(String sql){
	    	return (List<BuGpsdevice>) getSqlMapClientTemplate().queryForList(
					"tj_BuGpsdevice.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuGpsdevice.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_gpsdevice";
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
