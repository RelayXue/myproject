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


public class BuAffairsServiceImpl implements BuAffairsService{
	private BuAffairsDAO buAffairsDAO;
	
	public void setBuAffairsDAO(BuAffairsDAO buAffairsDAO) {
		this.buAffairsDAO = buAffairsDAO;
	}
	
	public BuAffairsDAO getBuAffairsDAO() {
		return this.buAffairsDAO;
	}
	@Override
	public BuAffairs findById(String id) {
		// TODO Auto-generated method stub
		return buAffairsDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuAffairs item) {
		// TODO Auto-generated method stub
		buAffairsDAO.insert(item);
	}

	@Override
	public void update(BuAffairs item) {
		// TODO Auto-generated method stub
		buAffairsDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuAffairs item) {
		buAffairsDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buAffairsDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buAffairsDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuAffairs> execSql(String sql) {
		// TODO Auto-generated method stub
		return buAffairsDAO.execSql(sql);
	}

	@Override
	public List<BuAffairs> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_affairs";
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
		List<BuAffairs> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuAffairs> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_affairs";
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
	     List<BuAffairs> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuAffairs> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuAffairs  where fuid  not in (select top " + min
				+ " fuid from BuAffairs "+ where + _oB + ") " + where2 + _oB; 
		List<BuAffairs> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_affairs";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buAffairsDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buAffairsDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
