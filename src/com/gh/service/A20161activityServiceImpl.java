package com.gh.service;

import java.util.List;



import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class A20161activityServiceImpl implements A20161activityService{
	private A20161activityDAO a20161activityDAO;
	
	public void setA20161activityDAO(A20161activityDAO a20161activityDAO) {
		this.a20161activityDAO = a20161activityDAO;
	}
	
	public A20161activityDAO getA20161activityDAO() {
		return this.a20161activityDAO;
	}
	@Override
	public A20161activity findById(String id) {
		// TODO Auto-generated method stub
		return a20161activityDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(A20161activity item) {
		// TODO Auto-generated method stub
		a20161activityDAO.insert(item);
	}

	@Override
	public void update(A20161activity item) {
		// TODO Auto-generated method stub
		a20161activityDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(A20161activity item) {
		a20161activityDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		a20161activityDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		a20161activityDAO.deleteByWhere(swhere);
	}

	@Override
	public List<A20161activity> execSql(String sql) {
		// TODO Auto-generated method stub
		return a20161activityDAO.execSql(sql);
	}

	@Override
	public List<A20161activity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM a2016_1_activity";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.getRecordCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<A20161activity> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<A20161activity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM a2016_1_activity";
	     if (strWhere != null && strWhere.trim().length() > 0) {
	         mainSql = mainSql + " WHERE " + strWhere;
	     }
	     if (strOrderBy != null && strOrderBy.trim().length() > 0) {
	         mainSql = mainSql + " ORDER BY " + strOrderBy;
	     }
	     int size =(int)this.getRecordCount(strWhere);
	     int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
	     int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
	     int min = pageIndex == 1 ? 1 : (pageIndex - 1) * pageSize + 1;
	     if (max <= min) {
	         min = min - pageSize;
	         pageIndex -= 1;
	     }
	     String sql = "SELECT * FROM (SELECT t01.*, rownum as newRowNum FROM (" + mainSql + ") t01 WHERE rownum <='" + max + "' ) WHERE newRowNum >='" + min + "'";
	     List<A20161activity> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<A20161activity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	 int min = pageIndex == 1 ? 0 : (pageIndex - 1) * pageSize;
		String _oB = "";
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			_oB = " ORDER BY " + strOrderBy;
		}
		String where="",where2="";
		if (strWhere != null && strWhere.trim().length() > 0) {
			where = " WHERE " + strWhere + " ";
			where2 = " AND " + strWhere;
		}
		String mainSql = "select top " + pageSize
				+ " * from A20161activity  where fuid  not in (select top " + min
				+ " fuid from A20161activity "+ where + _oB + ") " + where2 + _oB; 
		List<A20161activity> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM a2016_1_activity";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  a20161activityDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( a20161activityDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
