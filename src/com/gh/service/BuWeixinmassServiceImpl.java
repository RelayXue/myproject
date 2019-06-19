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


public class BuWeixinmassServiceImpl implements BuWeixinmassService{
	private BuWeixinmassDAO buWeixinmassDAO;
	
	public void setBuWeixinmassDAO(BuWeixinmassDAO buWeixinmassDAO) {
		this.buWeixinmassDAO = buWeixinmassDAO;
	}
	
	public BuWeixinmassDAO getBuWeixinmassDAO() {
		return this.buWeixinmassDAO;
	}
	@Override
	public BuWeixinmass findById(String id) {
		// TODO Auto-generated method stub
		return buWeixinmassDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeixinmass item) {
		// TODO Auto-generated method stub
		buWeixinmassDAO.insert(item);
	}

	@Override
	public void update(BuWeixinmass item) {
		// TODO Auto-generated method stub
		buWeixinmassDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuWeixinmass item) {
		buWeixinmassDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeixinmassDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buWeixinmassDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeixinmass> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeixinmassDAO.execSql(sql);
	}

	@Override
	public List<BuWeixinmass> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weixinmass";
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
		List<BuWeixinmass> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuWeixinmass> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_weixinmass";
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
	     List<BuWeixinmass> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuWeixinmass> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuWeixinmass  where fuid  not in (select top " + min
				+ " fuid from BuWeixinmass "+ where + _oB + ") " + where2 + _oB; 
		List<BuWeixinmass> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_weixinmass";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buWeixinmassDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buWeixinmassDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
