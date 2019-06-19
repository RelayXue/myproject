package com.gh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.entity.BaseMenu;
import com.gh.entity.BaseUser;
 


import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BaseUserRoleServiceImpl implements BaseUserRoleService{
	private BaseUserRoleDAO baseUserRoleDAO;
	
	public void setBaseUserRoleDAO(BaseUserRoleDAO baseUserRoleDAO) {
		this.baseUserRoleDAO = baseUserRoleDAO;
	}
	
	public BaseUserRoleDAO getBaseUserRoleDAO() {
		return this.baseUserRoleDAO;
	}
	@Override
	public BaseUserRole findById(String id) {
		// TODO Auto-generated method stub
		return baseUserRoleDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseUserRole item) {
		// TODO Auto-generated method stub
		baseUserRoleDAO.insert(item);
	}

	@Override
	public void update(BaseUserRole item) {
		// TODO Auto-generated method stub
		baseUserRoleDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseUserRole item) {
		baseUserRoleDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseUserRoleDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseUserRole> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseUserRoleDAO.execSql(sql);
	}

	@Override
	public List<BaseUserRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_user_role";
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
		List<BaseUserRole> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseUserRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_user_role";
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
	     List<BaseUserRole> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseUserRole> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseUserRole  where fuid  not in (select top " + min
				+ " fuid from BaseUserRole "+ where + _oB + ") " + where2 + _oB; 
		List<BaseUserRole> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_user_role";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseUserRoleDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseUserRoleDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	@Override
	public List<BaseUserRole> selectView(String sql) {
		// TODO Auto-generated method stub
		return baseUserRoleDAO.selectView(sql);
	}

}
