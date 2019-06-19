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


public class BuExamineServiceImpl implements BuExamineService{
	private BuExamineDAO buExamineDAO;
	
	public void setBuExamineDAO(BuExamineDAO buExamineDAO) {
		this.buExamineDAO = buExamineDAO;
	}
	
	public BuExamineDAO getBuExamineDAO() {
		return this.buExamineDAO;
	}
	@Override
	public BuExamine findById(String id) {
		// TODO Auto-generated method stub
		return buExamineDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuExamine item) {
		// TODO Auto-generated method stub
		buExamineDAO.insert(item);
	}

	@Override
	public void update(BuExamine item) {
		// TODO Auto-generated method stub
		buExamineDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuExamine item) {
		buExamineDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buExamineDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buExamineDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuExamine> execSql(String sql) {
		// TODO Auto-generated method stub
		return buExamineDAO.execSql(sql);
	}

	@Override
	public List<BuExamine> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_examine";
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
		List<BuExamine> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuExamine> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_examine";
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
	     List<BuExamine> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuExamine> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuExamine  where fuid  not in (select top " + min
				+ " fuid from BuExamine "+ where + _oB + ") " + where2 + _oB; 
		List<BuExamine> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_examine";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buExamineDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buExamineDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
