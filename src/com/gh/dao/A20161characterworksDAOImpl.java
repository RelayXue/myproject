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

 

 
public class A20161characterworksDAOImpl extends SqlMapClientDaoSupport implements A20161characterworksDAO  {
	
		public A20161characterworksDAOImpl() {
			super();
		}
		
		public void insert(A20161characterworks record){
			 getSqlMapClientTemplate().insert("tj_A20161characterworks.insert", record);
		 }
	 
		public int updateByPrimaryKey(A20161characterworks record){
	    	  int rows = getSqlMapClientTemplate().update("tj_A20161characterworks.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(A20161characterworks record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_A20161characterworks.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public A20161characterworks selectByPrimaryKey(String fuid){
	    	A20161characterworks key = new A20161characterworks();
	         key.setFuid(fuid);
	         A20161characterworks record = (A20161characterworks) getSqlMapClientTemplate().queryForObject("tj_A20161characterworks.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 A20161characterworks key = new A20161characterworks();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_A20161characterworks.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_A20161characterworks.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<A20161characterworks> execSql(String sql){
	    	return (List<A20161characterworks>) getSqlMapClientTemplate().queryForList(
					"tj_A20161characterworks.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_A20161characterworks.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM a2016_1_characterworks";
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
