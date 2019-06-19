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


public class BuWeixinvoteServiceImpl implements BuWeixinvoteService{
	private BuWeixinvoteDAO buWeixinvoteDAO;
	
	public void setBuWeixinvoteDAO(BuWeixinvoteDAO buWeixinvoteDAO) {
		this.buWeixinvoteDAO = buWeixinvoteDAO;
	}
	
	public BuWeixinvoteDAO getBuWeixinvoteDAO() {
		return this.buWeixinvoteDAO;
	}
	@Override
	public BuWeixinvote findById(String id) {
		// TODO Auto-generated method stub
		return buWeixinvoteDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeixinvote item) {
		// TODO Auto-generated method stub
		buWeixinvoteDAO.insert(item);
	}

	@Override
	public void update(BuWeixinvote item) {
		// TODO Auto-generated method stub
		buWeixinvoteDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuWeixinvote item) {
		buWeixinvoteDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeixinvoteDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buWeixinvoteDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeixinvote> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeixinvoteDAO.execSql(sql);
	}

	@Override
	public List<BuWeixinvote> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weixinvote";
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
		List<BuWeixinvote> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuWeixinvote> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_weixinvote";
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
	     List<BuWeixinvote> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuWeixinvote> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuWeixinvote  where fuid  not in (select top " + min
				+ " fuid from BuWeixinvote "+ where + _oB + ") " + where2 + _oB; 
		List<BuWeixinvote> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_weixinvote";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buWeixinvoteDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buWeixinvoteDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
