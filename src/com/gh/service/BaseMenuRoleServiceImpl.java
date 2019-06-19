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


public class BaseMenuRoleServiceImpl implements BaseMenuRoleService{
	private BaseMenuRoleDAO baseMenuRoleDAO;
	
	public void setBaseMenuRoleDAO(BaseMenuRoleDAO baseMenuRoleDAO) {
		this.baseMenuRoleDAO = baseMenuRoleDAO;
	}
	
	public BaseMenuRoleDAO getBaseMenuRoleDAO() {
		return this.baseMenuRoleDAO;
	}
	@Override
	public BaseMenuRole findById(String id) {
		// TODO Auto-generated method stub
		return baseMenuRoleDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseMenuRole item) {
		// TODO Auto-generated method stub
		baseMenuRoleDAO.insert(item);
	}

	@Override
	public void update(BaseMenuRole item) {
		// TODO Auto-generated method stub
		baseMenuRoleDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseMenuRole item) {
		baseMenuRoleDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseMenuRoleDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseMenuRole> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseMenuRoleDAO.execSql(sql);
	}

	@Override
	public List<BaseMenuRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_menu_role";
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
		List<BaseMenuRole> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseMenuRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_menu_role";
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
	     List<BaseMenuRole> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseMenuRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseMenuRole  where fuid  not in (select top " + min
				+ " fuid from BaseMenuRole "+ where + _oB + ") " + where2 + _oB; 
		List<BaseMenuRole> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_menu_role";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseMenuRoleDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseMenuRoleDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
