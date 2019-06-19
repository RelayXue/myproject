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


public class ObVideoServiceImpl implements ObVideoService{
	private ObVideoDAO obVideoDAO;
	
	public void setObVideoDAO(ObVideoDAO obVideoDAO) {
		this.obVideoDAO = obVideoDAO;
	}
	
	public ObVideoDAO getObVideoDAO() {
		return this.obVideoDAO;
	}
	@Override
	public ObVideo findById(String id) {
		// TODO Auto-generated method stub
		return obVideoDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(ObVideo item) {
		// TODO Auto-generated method stub
		obVideoDAO.insert(item);
	}

	@Override
	public void update(ObVideo item) {
		// TODO Auto-generated method stub
		obVideoDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(ObVideo item) {
		obVideoDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		obVideoDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<ObVideo> execSql(String sql) {
		// TODO Auto-generated method stub
		return obVideoDAO.execSql(sql);
	}

	@Override
	public List<ObVideo> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM ob_video";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.getRecordCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<ObVideo> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<ObVideo> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM ob_video";
	     if (strWhere != null && strWhere.trim().length() > 0) {
	         mainSql = mainSql + " WHERE " + strWhere;
	     }
	     if (strOrderBy != null && strOrderBy.trim().length() > 0) {
	         mainSql = mainSql + " ORDER BY " + strOrderBy;
	     }
	     int size =(int)this.getRecordCount(strWhere);
	     int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
	     int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
	     int min = pageIndex == 1 ? 0 : pageIndex * pageSize;
	     if (max <= min) {
	         min = min - pageSize;
	         pageIndex -= 1;
	     }
	     String sql = "SELECT * FROM (SELECT t01.*, rownum as newRowNum FROM (" + mainSql + ") t01 WHERE rownum <='" + max + "' ) WHERE newRowNum >='" + min + "'";
	     List<ObVideo> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<ObVideo> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from ObVideo  where fuid  not in (select top " + min
				+ " fuid from ObVideo "+ where + _oB + ") " + where2 + _oB; 
		List<ObVideo> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM ob_video";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  obVideoDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( obVideoDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
