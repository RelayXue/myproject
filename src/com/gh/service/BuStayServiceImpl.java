package com.gh.service;

import java.util.List;



import java.util.*;

import org.apache.commons.lang.StringUtils;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.phone.StringUtil;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuStayServiceImpl implements BuStayService{
	private BuStayDAO buStayDAO;
	
	public void setBuStayDAO(BuStayDAO buStayDAO) {
		this.buStayDAO = buStayDAO;
	}
	
	public BuStayDAO getBuStayDAO() {
		return this.buStayDAO;
	}
	@Override
	public BuStay findById(String id) {
		// TODO Auto-generated method stub
		return buStayDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuStay item) {
		// TODO Auto-generated method stub
		buStayDAO.insert(item);
	}

	@Override
	public void update(BuStay item) {
		// TODO Auto-generated method stub
		buStayDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuStay item) {
		buStayDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buStayDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buStayDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuStay> execSql(String sql) {
		// TODO Auto-generated method stub
		return buStayDAO.execSql(sql);
	}

	@Override
	public List<BuStay> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_stay";
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
		List<BuStay> xList = this.execSql(mainSql);
		return xList;
	}
	
	public int getDataCount(String strWhere) {
		String sql = "SELECT COUNT(*) FROM (SELECT FUID,FULLNAME,FX,FY,1 AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE FROM bu_dining union SELECT FUID,FULLNAME,FX,FY,4 AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE FROM bu_stay union SELECT FUID,FULLNAME,FX,FY,TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE FROM bu_entertainmentshopping union SELECT FUID,FULLNAME,FX,FY,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,TYPE FROM bu_geography) a";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buStayDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( obj.toString());
	     }
	     return 0; 
	}
	
	/*@Override
	public List<BuStay> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_stay";
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
	     List<BuStay> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuStay> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuStay  where fuid  not in (select top " + min
				+ " fuid from BuStay "+ where + _oB + ") " + where2 + _oB; 
		List<BuStay> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_stay";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buStayDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buStayDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
