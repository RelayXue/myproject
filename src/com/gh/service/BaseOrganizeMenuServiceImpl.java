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


public class BaseOrganizeMenuServiceImpl implements BaseOrganizeMenuService{
	private BaseOrganizeMenuDAO baseOrganizeMenuDAO;
	
	public void setBaseOrganizeMenuDAO(BaseOrganizeMenuDAO baseOrganizeMenuDAO) {
		this.baseOrganizeMenuDAO = baseOrganizeMenuDAO;
	}
	
	public BaseOrganizeMenuDAO getBaseOrganizeMenuDAO() {
		return this.baseOrganizeMenuDAO;
	}
	@Override
	public BaseOrganizeMenu findById(String id) {
		// TODO Auto-generated method stub
		return baseOrganizeMenuDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseOrganizeMenu item) {
		// TODO Auto-generated method stub
		baseOrganizeMenuDAO.insert(item);
	}

	@Override
	public void update(BaseOrganizeMenu item) {
		// TODO Auto-generated method stub
		baseOrganizeMenuDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseOrganizeMenu item) {
		baseOrganizeMenuDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseOrganizeMenuDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseOrganizeMenu> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseOrganizeMenuDAO.execSql(sql);
	}

	@Override
	public List<BaseOrganizeMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_organize_menu";
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
		List<BaseOrganizeMenu> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseOrganizeMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_organize_menu";
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
	     List<BaseOrganizeMenu> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseOrganizeMenu> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseOrganizeMenu  where fuid  not in (select top " + min
				+ " fuid from BaseOrganizeMenu "+ where + _oB + ") " + where2 + _oB; 
		List<BaseOrganizeMenu> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_organize_menu";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseOrganizeMenuDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseOrganizeMenuDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
