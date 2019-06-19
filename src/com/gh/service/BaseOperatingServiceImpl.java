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


public class BaseOperatingServiceImpl implements BaseOperatingService{
	private BaseOperatingDAO baseOperatingDAO;
	
	public void setBaseOperatingDAO(BaseOperatingDAO baseOperatingDAO) {
		this.baseOperatingDAO = baseOperatingDAO;
	}
	
	public BaseOperatingDAO getBaseOperatingDAO() {
		return this.baseOperatingDAO;
	}
	@Override
	public BaseOperating findById(String id) {
		// TODO Auto-generated method stub
		return baseOperatingDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseOperating item) {
		// TODO Auto-generated method stub
		baseOperatingDAO.insert(item);
	}

	@Override
	public void update(BaseOperating item) {
		// TODO Auto-generated method stub
		baseOperatingDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseOperating item) {
		baseOperatingDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseOperatingDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseOperating> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseOperatingDAO.execSql(sql);
	}

	@Override
	public List<BaseOperating> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_operating";
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
		List<BaseOperating> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseOperating> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_operating";
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
	     List<BaseOperating> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseOperating> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseOperating  where fuid  not in (select top " + min
				+ " fuid from BaseOperating "+ where + _oB + ") " + where2 + _oB; 
		List<BaseOperating> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_operating";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseOperatingDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseOperatingDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
