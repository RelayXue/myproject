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

 

 
public class BuWeixinmenuDAOImpl extends SqlMapClientDaoSupport implements BuWeixinmenuDAO  {
	
		public BuWeixinmenuDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinmenu record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinmenu.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinmenu record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinmenu.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinmenu record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinmenu.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinmenu selectByPrimaryKey(String fuid){
	    	BuWeixinmenu key = new BuWeixinmenu();
	         key.setFuid(fuid);
	         BuWeixinmenu record = (BuWeixinmenu) getSqlMapClientTemplate().queryForObject("tj_BuWeixinmenu.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinmenu key = new BuWeixinmenu();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmenu.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinmenu.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinmenu> execSql(String sql){
	    	return (List<BuWeixinmenu>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinmenu.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinmenu.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixinmenu";
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
