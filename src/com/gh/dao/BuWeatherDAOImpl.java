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

 

 
public class BuWeatherDAOImpl extends SqlMapClientDaoSupport implements BuWeatherDAO  {
	
		public BuWeatherDAOImpl() {
			super();
		}
		
		public void insert(BuWeather record){
			 getSqlMapClientTemplate().insert("tj_BuWeather.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeather record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeather.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeather record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeather.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeather selectByPrimaryKey(String fuid){
	    	BuWeather key = new BuWeather();
	         key.setFuid(fuid);
	         BuWeather record = (BuWeather) getSqlMapClientTemplate().queryForObject("tj_BuWeather.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeather key = new BuWeather();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeather.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeather.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeather> execSql(String sql){
	    	return (List<BuWeather>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeather.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeather.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weather";
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
