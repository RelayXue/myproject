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


public class BuWeixinactivityServiceImpl implements BuWeixinactivityService{
	private BuWeixinactivityDAO buWeixinactivityDAO;
	
	public void setBuWeixinactivityDAO(BuWeixinactivityDAO buWeixinactivityDAO) {
		this.buWeixinactivityDAO = buWeixinactivityDAO;
	}
	
	public BuWeixinactivityDAO getBuWeixinactivityDAO() {
		return this.buWeixinactivityDAO;
	}
	@Override
	public BuWeixinactivity findById(String id) {
		// TODO Auto-generated method stub
		return buWeixinactivityDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeixinactivity item) {
		// TODO Auto-generated method stub
		buWeixinactivityDAO.insert(item);
	}

	@Override
	public void update(BuWeixinactivity item) {
		// TODO Auto-generated method stub
		buWeixinactivityDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuWeixinactivity item) {
		buWeixinactivityDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeixinactivityDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buWeixinactivityDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeixinactivity> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeixinactivityDAO.execSql(sql);
	}

	@Override
	public List<BuWeixinactivity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weixinactivity";
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
		List<BuWeixinactivity> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuWeixinactivity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_weixinactivity";
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
	     List<BuWeixinactivity> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuWeixinactivity> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuWeixinactivity  where fuid  not in (select top " + min
				+ " fuid from BuWeixinactivity "+ where + _oB + ") " + where2 + _oB; 
		List<BuWeixinactivity> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_weixinactivity";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buWeixinactivityDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buWeixinactivityDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
