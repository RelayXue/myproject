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


public class A20171bridgeroadServiceImpl implements A20171bridgeroadService{
	private A20171bridgeroadDAO a20171bridgeroadDAO;
	
	public void setA20171bridgeroadDAO(A20171bridgeroadDAO a20171bridgeroadDAO) {
		this.a20171bridgeroadDAO = a20171bridgeroadDAO;
	}
	
	public A20171bridgeroadDAO getA20171bridgeroadDAO() {
		return this.a20171bridgeroadDAO;
	}
	@Override
	public A20171bridgeroad findById(String id) {
		// TODO Auto-generated method stub
		return a20171bridgeroadDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(A20171bridgeroad item) {
		// TODO Auto-generated method stub
		a20171bridgeroadDAO.insert(item);
	}

	@Override
	public void update(A20171bridgeroad item) {
		// TODO Auto-generated method stub
		a20171bridgeroadDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(A20171bridgeroad item) {
		a20171bridgeroadDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		a20171bridgeroadDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		a20171bridgeroadDAO.deleteByWhere(swhere);
	}

	@Override
	public List<A20171bridgeroad> execSql(String sql) {
		// TODO Auto-generated method stub
		return a20171bridgeroadDAO.execSql(sql);
	}

	@Override
	public List<A20171bridgeroad> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM a2017_1_bridgeroad";
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
		List<A20171bridgeroad> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<A20171bridgeroad> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM a2017_1_bridgeroad";
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
	     List<A20171bridgeroad> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<A20171bridgeroad> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from A20171bridgeroad  where fuid  not in (select top " + min
				+ " fuid from A20171bridgeroad "+ where + _oB + ") " + where2 + _oB; 
		List<A20171bridgeroad> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM a2017_1_bridgeroad";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  a20171bridgeroadDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( a20171bridgeroadDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
