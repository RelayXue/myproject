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


public class A20162subjectServiceImpl implements A20162subjectService{
	private A20162subjectDAO a20162subjectDAO;
	
	public void setA20162subjectDAO(A20162subjectDAO a20162subjectDAO) {
		this.a20162subjectDAO = a20162subjectDAO;
	}
	
	public A20162subjectDAO getA20162subjectDAO() {
		return this.a20162subjectDAO;
	}
	@Override
	public A20162subject findById(String id) {
		// TODO Auto-generated method stub
		return a20162subjectDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(A20162subject item) {
		// TODO Auto-generated method stub
		a20162subjectDAO.insert(item);
	}

	@Override
	public void update(A20162subject item) {
		// TODO Auto-generated method stub
		a20162subjectDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(A20162subject item) {
		a20162subjectDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		a20162subjectDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		a20162subjectDAO.deleteByWhere(swhere);
	}

	@Override
	public List<A20162subject> execSql(String sql) {
		// TODO Auto-generated method stub
		return a20162subjectDAO.execSql(sql);
	}

	@Override
	public List<A20162subject> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM a2016_2_subject";
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
		List<A20162subject> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<A20162subject> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM a2016_2_subject";
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
	     List<A20162subject> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<A20162subject> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from A20162subject  where fuid  not in (select top " + min
				+ " fuid from A20162subject "+ where + _oB + ") " + where2 + _oB; 
		List<A20162subject> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM a2016_2_subject";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  a20162subjectDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( a20162subjectDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
