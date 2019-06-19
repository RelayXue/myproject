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

 

 
public class BuExamineDAOImpl extends SqlMapClientDaoSupport implements BuExamineDAO  {
	
		public BuExamineDAOImpl() {
			super();
		}
		
		public void insert(BuExamine record){
			 getSqlMapClientTemplate().insert("tj_BuExamine.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuExamine record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuExamine.updateByPrimaryKey", record);
	          return rows;
	    }
		public int updateByPrimaryKeySelective(BuExamine record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuExamine.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuExamine selectByPrimaryKey(String fuid){
	    	BuExamine key = new BuExamine();
	         key.setFuid(fuid);
	         BuExamine record = (BuExamine) getSqlMapClientTemplate().queryForObject("tj_BuExamine.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuExamine key = new BuExamine();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuExamine.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuExamine.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuExamine> execSql(String sql){
	    	return (List<BuExamine>) getSqlMapClientTemplate().queryForList(
					"tj_BuExamine.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuExamine.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_examine";
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
