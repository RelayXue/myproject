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

 

 
public class GmPathLineDAOImpl extends SqlMapClientDaoSupport implements GmPathLineDAO  {
	
		public GmPathLineDAOImpl() {
			super();
		}
		
		public void insert(GmPathLine record){
			 getSqlMapClientTemplate().insert("tj_GmPathLine.insert", record);
		 }
	 
		public int updateByPrimaryKey(GmPathLine record){
	    	  int rows = getSqlMapClientTemplate().update("tj_GmPathLine.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(GmPathLine record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_GmPathLine.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public GmPathLine selectByPrimaryKey(String fuid){
	    	GmPathLine key = new GmPathLine();
	         key.setFuid(fuid);
	         GmPathLine record = (GmPathLine) getSqlMapClientTemplate().queryForObject("tj_GmPathLine.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 GmPathLine key = new GmPathLine();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_GmPathLine.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_GmPathLine.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<GmPathLine> execSql(String sql){
	    	return (List<GmPathLine>) getSqlMapClientTemplate().queryForList(
					"tj_GmPathLine.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_GmPathLine.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM gm_path_line";
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
