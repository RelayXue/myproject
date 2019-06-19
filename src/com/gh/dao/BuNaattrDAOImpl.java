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

 

 
public class BuNaattrDAOImpl extends SqlMapClientDaoSupport implements BuNaattrDAO  {
	
		public BuNaattrDAOImpl() {
			super();
		}
		
		public void insert(BuNaattr record){
			 getSqlMapClientTemplate().insert("tj_BuNaattr.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuNaattr record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuNaattr.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuNaattr record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuNaattr.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuNaattr selectByPrimaryKey(String fuid){
	    	BuNaattr key = new BuNaattr();
	         key.setFuid(fuid);
	         BuNaattr record = (BuNaattr) getSqlMapClientTemplate().queryForObject("tj_BuNaattr.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuNaattr key = new BuNaattr();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuNaattr.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuNaattr.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuNaattr> execSql(String sql){
	    	return (List<BuNaattr>) getSqlMapClientTemplate().queryForList(
					"tj_BuNaattr.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuNaattr.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_naattr";
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
