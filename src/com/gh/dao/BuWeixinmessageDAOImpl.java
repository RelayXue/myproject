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

 

 
public class BuWeixinmessageDAOImpl extends SqlMapClientDaoSupport implements BuWeixinmessageDAO  {
	
		public BuWeixinmessageDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinmessage record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinmessage.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinmessage record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinmessage.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinmessage record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinmessage.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinmessage selectByPrimaryKey(String fuid){
	    	BuWeixinmessage key = new BuWeixinmessage();
	         key.setFuid(fuid);
	         BuWeixinmessage record = (BuWeixinmessage) getSqlMapClientTemplate().queryForObject("tj_BuWeixinmessage.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinmessage key = new BuWeixinmessage();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmessage.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmessage.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinmessage> execSql(String sql){
	    	return (List<BuWeixinmessage>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinmessage.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinmessage.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinmessage";
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
