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


public class BuWeixinmessageServiceImpl implements BuWeixinmessageService{
	private BuWeixinmessageDAO buWeixinmessageDAO;
	
	public void setBuWeixinmessageDAO(BuWeixinmessageDAO buWeixinmessageDAO) {
		this.buWeixinmessageDAO = buWeixinmessageDAO;
	}
	
	public BuWeixinmessageDAO getBuWeixinmessageDAO() {
		return this.buWeixinmessageDAO;
	}
	@Override
	public BuWeixinmessage findById(String id) {
		// TODO Auto-generated method stub
		return buWeixinmessageDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeixinmessage item) {
		// TODO Auto-generated method stub
		buWeixinmessageDAO.insert(item);
	}

	@Override
	public void update(BuWeixinmessage item) {
		// TODO Auto-generated method stub
		buWeixinmessageDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuWeixinmessage item) {
		buWeixinmessageDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeixinmessageDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buWeixinmessageDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeixinmessage> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeixinmessageDAO.execSql(sql);
	}

	@Override
	public List<BuWeixinmessage> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weixinmessage";
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
		List<BuWeixinmessage> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuWeixinmessage> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_weixinmessage";
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
	     List<BuWeixinmessage> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuWeixinmessage> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuWeixinmessage  where fuid  not in (select top " + min
				+ " fuid from BuWeixinmessage "+ where + _oB + ") " + where2 + _oB; 
		List<BuWeixinmessage> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_weixinmessage";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buWeixinmessageDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buWeixinmessageDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
