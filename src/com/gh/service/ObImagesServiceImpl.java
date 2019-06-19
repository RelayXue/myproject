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


public class ObImagesServiceImpl implements ObImagesService{
	private ObImagesDAO obImagesDAO;
	
	public void setObImagesDAO(ObImagesDAO obImagesDAO) {
		this.obImagesDAO = obImagesDAO;
	}
	
	public ObImagesDAO getObImagesDAO() {
		return this.obImagesDAO;
	}
	@Override
	public ObImages findById(String id) {
		// TODO Auto-generated method stub
		return obImagesDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(ObImages item) {
		// TODO Auto-generated method stub
		obImagesDAO.insert(item);
	}

	@Override
	public void update(ObImages item) {
		// TODO Auto-generated method stub
		obImagesDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(ObImages item) {
		obImagesDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		obImagesDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		obImagesDAO.deleteByWhere(swhere);
	}

	@Override
	public List<ObImages> execSql(String sql) {
		// TODO Auto-generated method stub
		return obImagesDAO.execSql(sql);
	}

	@Override
	public List<ObImages> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM ob_images";
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
		List<ObImages> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<ObImages> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM ob_images";
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
	     List<ObImages> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<ObImages> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from ObImages  where fuid  not in (select top " + min
				+ " fuid from ObImages "+ where + _oB + ") " + where2 + _oB; 
		List<ObImages> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM ob_images";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  obImagesDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( obImagesDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
