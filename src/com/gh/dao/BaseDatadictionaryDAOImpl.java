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

 

 
public class BaseDatadictionaryDAOImpl extends SqlMapClientDaoSupport implements BaseDatadictionaryDAO  {
	
		public BaseDatadictionaryDAOImpl() {
			super();
		}
		
		public void insert(BaseDatadictionary record){
			 getSqlMapClientTemplate().insert("tj_BaseDatadictionary.insert", record);
		 }
	 
		public int updateByPrimaryKey(BaseDatadictionary record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BaseDatadictionary.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BaseDatadictionary record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BaseDatadictionary.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BaseDatadictionary selectByPrimaryKey(String fuid){
	    	BaseDatadictionary key = new BaseDatadictionary();
	         key.setFuid(fuid);
	         BaseDatadictionary record = (BaseDatadictionary) getSqlMapClientTemplate().queryForObject("tj_BaseDatadictionary.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BaseDatadictionary key = new BaseDatadictionary();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BaseDatadictionary.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<BaseDatadictionary> execSql(String sql){
	    	return (List<BaseDatadictionary>) getSqlMapClientTemplate().queryForList(
					"tj_BaseDatadictionary.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BaseDatadictionary.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM base_datadictionary";
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
