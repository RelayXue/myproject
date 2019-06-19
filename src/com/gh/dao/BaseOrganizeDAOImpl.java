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
 

 
public class BaseOrganizeDAOImpl extends SqlMapClientDaoSupport implements BaseOrganizeDAO  {
	
		public BaseOrganizeDAOImpl() {
			super();
		}
		
		public void insert(BaseOrganize record){
			 getSqlMapClientTemplate().insert("tj_BaseOrganize.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseOrganize record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseOrganize.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseOrganize record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseOrganize.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseOrganize selectByPrimaryKey(String fuid){
	    	BaseOrganize key = new BaseOrganize();
	         key.setFuid(fuid);
	         BaseOrganize record = (BaseOrganize) getSqlMapClientTemplate().queryForObject("tj_BaseOrganize.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseOrganize key = new BaseOrganize();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseOrganize.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseOrganize> execSql(String sql){
	    	return (List<BaseOrganize>) getSqlMapClientTemplate().queryForList(
					"tj_BaseOrganize.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseOrganize.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_organize";
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
