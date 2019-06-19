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


public class BuInterestServiceImpl implements BuInterestService{
	private BuInterestDAO buInterestDAO;
	
	public void setBuInterestDAO(BuInterestDAO buInterestDAO) {
		this.buInterestDAO = buInterestDAO;
	}
	
	public BuInterestDAO getBuInterestDAO() {
		return this.buInterestDAO;
	}
	@Override
	public BuInterest findById(String id) {
		// TODO Auto-generated method stub
		return buInterestDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuInterest item) {
		// TODO Auto-generated method stub
		buInterestDAO.insert(item);
	}

	@Override
	public void update(BuInterest item) {
		// TODO Auto-generated method stub
		buInterestDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuInterest item) {
		buInterestDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buInterestDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buInterestDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuInterest> execSql(String sql) {
		// TODO Auto-generated method stub
		return buInterestDAO.execSql(sql);
	}

	@Override
	public List<BuInterest> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_interest";
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
		List<BuInterest> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuInterest> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_interest";
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
	     List<BuInterest> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuInterest> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuInterest  where fuid  not in (select top " + min
				+ " fuid from BuInterest "+ where + _oB + ") " + where2 + _oB; 
		List<BuInterest> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_interest";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buInterestDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buInterestDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}

	@Override
	public List<BuInterest> execSqlCount(String sql) {
		// TODO Auto-generated method stub
		return buInterestDAO.execSqlCount(sql);
	}
	

}
