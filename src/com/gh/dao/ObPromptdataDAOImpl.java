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
 

 
public class ObPromptdataDAOImpl extends SqlMapClientDaoSupport implements ObPromptdataDAO  {
	
		public ObPromptdataDAOImpl() {
			super();
		}
		
		public void insert(ObPromptdata record){
			 getSqlMapClientTemplate().insert("tj_ObPromptdata.insert", record);
		 }
	 
		public int updateByPrimaryKey(ObPromptdata record){
	    	  int rows = getSqlMapClientTemplate().update("tj_ObPromptdata.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(ObPromptdata record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_ObPromptdata.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public ObPromptdata selectByPrimaryKey(String fuid){
	    	ObPromptdata key = new ObPromptdata();
	         key.setFuid(fuid);
	         ObPromptdata record = (ObPromptdata) getSqlMapClientTemplate().queryForObject("tj_ObPromptdata.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 ObPromptdata key = new ObPromptdata();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_ObPromptdata.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<ObPromptdata> execSql(String sql){
	    	return (List<ObPromptdata>) getSqlMapClientTemplate().queryForList(
					"tj_ObPromptdata.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_ObPromptdata.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM ob_promptdata";
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
