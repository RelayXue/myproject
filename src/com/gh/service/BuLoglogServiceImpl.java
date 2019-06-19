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


public class BuLoglogServiceImpl implements BuLoglogService{
	private BuLoglogDAO buLoglogDAO;
	
	public void setBuLoglogDAO(BuLoglogDAO buLoglogDAO) {
		this.buLoglogDAO = buLoglogDAO;
	}
	
	public BuLoglogDAO getBuLoglogDAO() {
		return this.buLoglogDAO;
	}
	@Override
	public BuLoglog findById(String id) {
		// TODO Auto-generated method stub
		return buLoglogDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuLoglog item) {
		// TODO Auto-generated method stub
		buLoglogDAO.insert(item);
	}

	@Override
	public void update(BuLoglog item) {
		// TODO Auto-generated method stub
		buLoglogDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuLoglog item) {
		buLoglogDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buLoglogDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buLoglogDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuLoglog> execSql(String sql) {
		// TODO Auto-generated method stub
		return buLoglogDAO.execSql(sql);
	}

	@Override
	public List<BuLoglog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_loglog";
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
		mainSql+="  LIMIT "+min+","+max;
		List<BuLoglog> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuLoglog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_loglog";
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
	     List<BuLoglog> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuLoglog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuLoglog  where fuid  not in (select top " + min
				+ " fuid from BuLoglog "+ where + _oB + ") " + where2 + _oB; 
		List<BuLoglog> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_loglog";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buLoglogDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buLoglogDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	
    public void execUpdateSql(String sql){
    	if(sql!=null && sql.trim().length()>0){
    		buLoglogDAO.execUpdateSql(sql);
    	}
    }
	

}
