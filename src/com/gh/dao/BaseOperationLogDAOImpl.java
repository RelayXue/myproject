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

import com.gh.entity.BaseUser;
 

 
public class BaseOperationLogDAOImpl extends SqlMapClientDaoSupport implements BaseOperationLogDAO  {
	
		public BaseOperationLogDAOImpl() {
			super();
		}
		
		public void insert(BaseOperationLog record){
			 getSqlMapClientTemplate().insert("tj_BaseOperationLog.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseOperationLog record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseOperationLog.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseOperationLog record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseOperationLog.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseOperationLog selectByPrimaryKey(String fuid){
	    	BaseOperationLog key = new BaseOperationLog();
	         key.setFuid(fuid);
	         BaseOperationLog record = (BaseOperationLog) getSqlMapClientTemplate().queryForObject("tj_BaseOperationLog.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseOperationLog key = new BaseOperationLog();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseOperationLog.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseOperationLog> execSql(String sql){
	    	return (List<BaseOperationLog>) getSqlMapClientTemplate().queryForList(
					"tj_BaseOperationLog.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseOperationLog.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_operation_log";
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
