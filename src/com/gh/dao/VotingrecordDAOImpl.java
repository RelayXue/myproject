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

 

 
public class VotingrecordDAOImpl extends SqlMapClientDaoSupport implements VotingrecordDAO  {
	
		public VotingrecordDAOImpl() {
			super();
		}
		
		public void insert(Votingrecord record){
			 getSqlMapClientTemplate().insert("tj_Votingrecord.insert", record);
		 }
	 
		public int updateByPrimaryKey(Votingrecord record){
	    	  int rows = getSqlMapClientTemplate().update("tj_Votingrecord.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(Votingrecord record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_Votingrecord.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public Votingrecord selectByPrimaryKey(String fuid){
	    	Votingrecord key = new Votingrecord();
	         key.setFuid(fuid);
	         Votingrecord record = (Votingrecord) getSqlMapClientTemplate().queryForObject("tj_Votingrecord.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 Votingrecord key = new Votingrecord();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_Votingrecord.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_Votingrecord.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<Votingrecord> execSql(String sql){
	    	return (List<Votingrecord>) getSqlMapClientTemplate().queryForList(
					"tj_Votingrecord.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_Votingrecord.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM votingrecord";
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
