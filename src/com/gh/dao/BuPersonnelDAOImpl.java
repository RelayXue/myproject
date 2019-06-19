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

 

 
public class BuPersonnelDAOImpl extends SqlMapClientDaoSupport implements BuPersonnelDAO  {
	
		public BuPersonnelDAOImpl() {
			super();
		}
		
		public void insert(BuPersonnel record){
			 getSqlMapClientTemplate().insert("tj_BuPersonnel.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuPersonnel record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuPersonnel.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuPersonnel record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuPersonnel.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuPersonnel selectByPrimaryKey(String fuid){
	    	BuPersonnel key = new BuPersonnel();
	         key.setFuid(fuid);
	         BuPersonnel record = (BuPersonnel) getSqlMapClientTemplate().queryForObject("tj_BuPersonnel.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuPersonnel key = new BuPersonnel();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuPersonnel.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuPersonnel.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuPersonnel> execSql(String sql){
	    	return (List<BuPersonnel>) getSqlMapClientTemplate().queryForList(
					"tj_BuPersonnel.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuPersonnel.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_personnel";
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
