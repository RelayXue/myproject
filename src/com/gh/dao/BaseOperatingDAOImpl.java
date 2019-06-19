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
 

 
public class BaseOperatingDAOImpl extends SqlMapClientDaoSupport implements BaseOperatingDAO  {
	
		public BaseOperatingDAOImpl() {
			super();
		}
		
		public void insert(BaseOperating record){
			 getSqlMapClientTemplate().insert("tj_BaseOperating.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseOperating record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseOperating.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseOperating record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseOperating.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseOperating selectByPrimaryKey(String fuid){
	    	BaseOperating key = new BaseOperating();
	         key.setFuid(fuid);
	         BaseOperating record = (BaseOperating) getSqlMapClientTemplate().queryForObject("tj_BaseOperating.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseOperating key = new BaseOperating();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseOperating.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseOperating> execSql(String sql){
	    	return (List<BaseOperating>) getSqlMapClientTemplate().queryForList(
					"tj_BaseOperating.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseOperating.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_operating";
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
