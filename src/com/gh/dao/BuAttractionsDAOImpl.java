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

 

 
public class BuAttractionsDAOImpl extends SqlMapClientDaoSupport implements BuAttractionsDAO  {
	
		public BuAttractionsDAOImpl() {
			super();
		}
		
		public void insert(BuAttractions record){
			 getSqlMapClientTemplate().insert("tj_BuAttractions.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuAttractions record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuAttractions.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuAttractions record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuAttractions.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuAttractions selectByPrimaryKey(String fuid){
	    	BuAttractions key = new BuAttractions();
	         key.setFuid(fuid);
	         BuAttractions record = (BuAttractions) getSqlMapClientTemplate().queryForObject("tj_BuAttractions.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuAttractions key = new BuAttractions();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuAttractions.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuAttractions.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuAttractions> execSql(String sql){
	    	return (List<BuAttractions>) getSqlMapClientTemplate().queryForList(
					"tj_BuAttractions.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuAttractions.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_attractions";
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
