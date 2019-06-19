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

 

 
public class ObImagesDAOImpl extends SqlMapClientDaoSupport implements ObImagesDAO  {
	
		public ObImagesDAOImpl() {
			super();
		}
		
		public void insert(ObImages record){
			 getSqlMapClientTemplate().insert("tj_ObImages.insert", record);
		 }
	 
		public int updateByPrimaryKey(ObImages record){
	    	  int rows = getSqlMapClientTemplate().update("tj_ObImages.updateByPrimaryKey", record);
	          return rows;
	    }
		
		public int updateByPrimaryKeySelective(ObImages record) {
			// TODO Auto-generated method stub
		 int rows = getSqlMapClientTemplate().update("tj_ObImages.updateByPrimaryKeySelective", record);
	        return rows;
		}
		
		public ObImages selectByPrimaryKey(String fuid){
	    	ObImages key = new ObImages();
	         key.setFuid(fuid);
	         ObImages record = (ObImages) getSqlMapClientTemplate().queryForObject("tj_ObImages.selectByPrimaryKey", key);
	         return record;
	    }
	 
		public int deleteByPrimaryKey(String fuid){
	    	 ObImages key = new ObImages();
	         key.setFuid(fuid);
	         int rows = getSqlMapClientTemplate().delete("tj_ObImages.deleteByPrimaryKey", key);
	         return rows;
	    }
		
		public int deleteByWhere(String swhere){
	         int rows = getSqlMapClientTemplate().delete("tj_ObImages.deleteByWhere", swhere);
	         return rows;
	    }
		
	    public List<ObImages> execSql(String sql){
	    	return (List<ObImages>) getSqlMapClientTemplate().queryForList(
					"tj_ObImages.execSql", sql);
	    }

	    public Object selectObject(String sql){
		     if(sql!=null && sql.trim().length()>0){
		         return getSqlMapClientTemplate().queryForObject("tj_ObImages.execSqlObject",sql);
		     }
		     return null;
		
	    }

	    public int count(String strWhere){
			String sql = "SELECT COUNT(*) FROM ob_images";
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
