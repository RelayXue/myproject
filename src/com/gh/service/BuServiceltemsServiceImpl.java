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


public class BuServiceltemsServiceImpl implements BuServiceltemsService{
	private BuServiceltemsDAO buServiceltemsDAO;
	
	public void setBuServiceltemsDAO(BuServiceltemsDAO buServiceltemsDAO) {
		this.buServiceltemsDAO = buServiceltemsDAO;
	}
	
	public BuServiceltemsDAO getBuServiceltemsDAO() {
		return this.buServiceltemsDAO;
	}
	@Override
	public BuServiceltems findById(String id) {
		// TODO Auto-generated method stub
		return buServiceltemsDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuServiceltems item) {
		// TODO Auto-generated method stub
		buServiceltemsDAO.insert(item);
	}

	@Override
	public void update(BuServiceltems item) {
		// TODO Auto-generated method stub
		buServiceltemsDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuServiceltems item) {
		buServiceltemsDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buServiceltemsDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buServiceltemsDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuServiceltems> execSql(String sql) {
		// TODO Auto-generated method stub
		return buServiceltemsDAO.execSql(sql);
	}

	@Override
	public List<BuServiceltems> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_serviceltems";
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
		List<BuServiceltems> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuServiceltems> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_serviceltems";
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
	     List<BuServiceltems> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuServiceltems> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuServiceltems  where fuid  not in (select top " + min
				+ " fuid from BuServiceltems "+ where + _oB + ") " + where2 + _oB; 
		List<BuServiceltems> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_serviceltems";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buServiceltemsDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buServiceltemsDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
