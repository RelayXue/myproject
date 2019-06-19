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
 

 
public class ObVideoDAOImpl extends SqlMapClientDaoSupport implements ObVideoDAO  {
	
		public ObVideoDAOImpl() {
			super();
		}
		
		public void insert(ObVideo record){
			 getSqlMapClientTemplate().insert("tj_ObVideo.insert", record);
		 }
	 
		public int updateByPrimaryKey(ObVideo record){
	    	  int rows = getSqlMapClientTemplate().update("tj_ObVideo.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(ObVideo record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_ObVideo.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public ObVideo selectByPrimaryKey(String fuid){
	    	ObVideo key = new ObVideo();
	         key.setFuid(fuid);
	         ObVideo record = (ObVideo) getSqlMapClientTemplate().queryForObject("tj_ObVideo.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 ObVideo key = new ObVideo();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_ObVideo.deleteByPrimaryKey", key);
	         return rows;
	    }
	    
	    public List<ObVideo> execSql(String sql){
	    	return (List<ObVideo>) getSqlMapClientTemplate().queryForList(
					"tj_ObVideo.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_ObVideo.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM ob_video";
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
