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

 

 
public class BuStayRoomDAOImpl extends SqlMapClientDaoSupport implements BuStayRoomDAO  {
	
		public BuStayRoomDAOImpl() {
			super();
		}
		
		public void insert(BuStayRoom record){
			 getSqlMapClientTemplate().insert("tj_BuStayRoom.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuStayRoom record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuStayRoom.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuStayRoom record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuStayRoom.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuStayRoom selectByPrimaryKey(String fuid){
	    	BuStayRoom key = new BuStayRoom();
	         key.setFuid(fuid);
	         BuStayRoom record = (BuStayRoom) getSqlMapClientTemplate().queryForObject("tj_BuStayRoom.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuStayRoom key = new BuStayRoom();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuStayRoom.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuStayRoom.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuStayRoom> execSql(String sql){
	    	return (List<BuStayRoom>) getSqlMapClientTemplate().queryForList(
					"tj_BuStayRoom.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuStayRoom.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_stay_room";
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
