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

 

 
public class BuBarcodeDAOImpl extends SqlMapClientDaoSupport implements BuBarcodeDAO  {
	
		public BuBarcodeDAOImpl() {
			super();
		}
		
		public void insert(BuBarcode record){
			 getSqlMapClientTemplate().insert("tj_BuBarcode.insert", record);
		 }
	 
		public int updateByPrimaryKey(BuBarcode record){
	    	  int rows = getSqlMapClientTemplate().update("tj_BuBarcode.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(BuBarcode record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_BuBarcode.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public BuBarcode selectByPrimaryKey(String fuid){
	    	BuBarcode key = new BuBarcode();
	         key.setFuid(fuid);
	         BuBarcode record = (BuBarcode) getSqlMapClientTemplate().queryForObject("tj_BuBarcode.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 BuBarcode key = new BuBarcode();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_BuBarcode.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_BuBarcode.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<BuBarcode> execSql(String sql){
	    	return (List<BuBarcode>) getSqlMapClientTemplate().queryForList(
					"tj_BuBarcode.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_BuBarcode.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM bu_barcode";
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
