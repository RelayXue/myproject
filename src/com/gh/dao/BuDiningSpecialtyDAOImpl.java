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

 

 
public class BuDiningSpecialtyDAOImpl extends SqlMapClientDaoSupport implements BuDiningSpecialtyDAO  {
	
		public BuDiningSpecialtyDAOImpl() {
			super();
		}
		
		public void insert(BuDiningSpecialty record){
			 getSqlMapClientTemplate().insert("tj_BuDiningSpecialty.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuDiningSpecialty record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuDiningSpecialty.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuDiningSpecialty record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuDiningSpecialty.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuDiningSpecialty selectByPrimaryKey(String fuid){
	    	BuDiningSpecialty key = new BuDiningSpecialty();
	         key.setFuid(fuid);
	         BuDiningSpecialty record = (BuDiningSpecialty) getSqlMapClientTemplate().queryForObject("tj_BuDiningSpecialty.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuDiningSpecialty key = new BuDiningSpecialty();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuDiningSpecialty.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuDiningSpecialty.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuDiningSpecialty> execSql(String sql){
	    	return (List<BuDiningSpecialty>) getSqlMapClientTemplate().queryForList(
					"tj_BuDiningSpecialty.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuDiningSpecialty.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_dining_specialty";
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
