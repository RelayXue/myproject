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

 

 
public class BuWeixinVotingRecordDAOImpl extends SqlMapClientDaoSupport implements BuWeixinVotingRecordDAO  {
	
		public BuWeixinVotingRecordDAOImpl() {
			super();
		}
		
		public void insert(BuWeixinVotingRecord record){
			 getSqlMapClientTemplate().insert("tj_BuWeixinVotingRecord.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuWeixinVotingRecord record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuWeixinVotingRecord.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuWeixinVotingRecord record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuWeixinVotingRecord.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuWeixinVotingRecord selectByPrimaryKey(String fuid){
	    	BuWeixinVotingRecord key = new BuWeixinVotingRecord();
	         key.setFuid(fuid);
	         BuWeixinVotingRecord record = (BuWeixinVotingRecord) getSqlMapClientTemplate().queryForObject("tj_BuWeixinVotingRecord.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuWeixinVotingRecord key = new BuWeixinVotingRecord();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinVotingRecord.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuWeixinVotingRecord.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuWeixinVotingRecord> execSql(String sql){
	    	return (List<BuWeixinVotingRecord>) getSqlMapClientTemplate().queryForList(
					"tj_BuWeixinVotingRecord.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuWeixinVotingRecord.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_weixin_voting_record";
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
