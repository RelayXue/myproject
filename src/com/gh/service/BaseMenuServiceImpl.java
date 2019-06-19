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


public class BaseMenuServiceImpl implements BaseMenuService{
	private BaseMenuDAO baseMenuDAO;
	
	public void setBaseMenuDAO(BaseMenuDAO baseMenuDAO) {
		this.baseMenuDAO = baseMenuDAO;
	}
	
	public BaseMenuDAO getBaseMenuDAO() {
		return this.baseMenuDAO;
	}
	@Override
	public BaseMenu findById(String id) {
		// TODO Auto-generated method stub
		return baseMenuDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseMenu item) {
		// TODO Auto-generated method stub
		baseMenuDAO.insert(item);
	}

	@Override
	public void update(BaseMenu item) {
		// TODO Auto-generated method stub
		baseMenuDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseMenu item) {
		baseMenuDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseMenuDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseMenu> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseMenuDAO.execSql(sql);
	}

	@Override
	public List<BaseMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_menu";
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
		List<BaseMenu> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_menu";
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
	     List<BaseMenu> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseMenu  where fuid  not in (select top " + min
				+ " fuid from BaseMenu "+ where + _oB + ") " + where2 + _oB; 
		List<BaseMenu> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_menu";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseMenuDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseMenuDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
