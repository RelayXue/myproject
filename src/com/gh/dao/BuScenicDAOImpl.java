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

 

 
public class BuScenicDAOImpl extends SqlMapClientDaoSupport implements BuScenicDAO  {
	
		public BuScenicDAOImpl() {
			super();
		}
		
		public void insert(BuScenic record){
			 getSqlMapClientTemplate().insert("tj_BuScenic.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuScenic record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuScenic.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuScenic record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuScenic.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuScenic selectByPrimaryKey(String fuid){
	    	BuScenic key = new BuScenic();
	         key.setFuid(fuid);
	         BuScenic record = (BuScenic) getSqlMapClientTemplate().queryForObject("tj_BuScenic.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuScenic key = new BuScenic();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuScenic.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuScenic.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuScenic> execSql(String sql){
	    	return (List<BuScenic>) getSqlMapClientTemplate().queryForList(
					"tj_BuScenic.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuScenic.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_scenic";
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
