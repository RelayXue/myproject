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


public class BuAttractionsServiceImpl implements BuAttractionsService{
	private BuAttractionsDAO buAttractionsDAO;
	
	public void setBuAttractionsDAO(BuAttractionsDAO buAttractionsDAO) {
		this.buAttractionsDAO = buAttractionsDAO;
	}
	
	public BuAttractionsDAO getBuAttractionsDAO() {
		return this.buAttractionsDAO;
	}
	@Override
	public BuAttractions findById(String id) {
		// TODO Auto-generated method stub
		return buAttractionsDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuAttractions item) {
		// TODO Auto-generated method stub
		buAttractionsDAO.insert(item);
	}

	@Override
	public void update(BuAttractions item) {
		// TODO Auto-generated method stub
		buAttractionsDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuAttractions item) {
		buAttractionsDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buAttractionsDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buAttractionsDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuAttractions> execSql(String sql) {
		// TODO Auto-generated method stub
		return buAttractionsDAO.execSql(sql);
	}

	@Override
	public List<BuAttractions> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_attractions";
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
		List<BuAttractions> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuAttractions> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_attractions";
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
	     List<BuAttractions> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuAttractions> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuAttractions  where fuid  not in (select top " + min
				+ " fuid from BuAttractions "+ where + _oB + ") " + where2 + _oB; 
		List<BuAttractions> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_attractions";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buAttractionsDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buAttractionsDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
