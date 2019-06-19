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

public class BuNewsServiceImpl implements BuNewsService {
	private BuNewsDAO buNewsDAO;

	public void setBuNewsDAO(BuNewsDAO buNewsDAO) {
		this.buNewsDAO = buNewsDAO;
	}

	public BuNewsDAO getBuNewsDAO() {
		return this.buNewsDAO;
	}

	@Override
	public BuNews findById(String id) {
		// TODO Auto-generated method stub
		return buNewsDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuNews item) {
		// TODO Auto-generated method stub
		buNewsDAO.insert(item);
	}

	@Override
	public void update(BuNews item) {
		// TODO Auto-generated method stub
		buNewsDAO.updateByPrimaryKey(item);
	}

	@Override
	public void updateSelective(BuNews item) {
		buNewsDAO.updateByPrimaryKeySelective(item);

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buNewsDAO.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub
		buNewsDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuNews> execSql(String sql) {
		// TODO Auto-generated method stub
		return buNewsDAO.execSql(sql);
	}

	@Override
	public List<BuNews> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_news";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size = (int) this.getRecordCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min = min > 0 ? min : 0;
		mainSql += "  LIMIT " + min + "," + pageSize;
		List<BuNews> xList = this.execSql(mainSql);
		return xList;
	}

	/*
	 * @Override public List<BuNews> selectByPage(int pageIndex, int
	 * pageSize,String strWhere, String strOrderBy) { String mainSql =
	 * "SELECT * FROM bu_news"; if (strWhere != null && strWhere.trim().length()
	 * > 0) { mainSql = mainSql + " WHERE " + strWhere; } if (strOrderBy != null
	 * && strOrderBy.trim().length() > 0) { mainSql = mainSql + " ORDER BY " +
	 * strOrderBy; } int size =(int)this.getRecordCount(strWhere); int totalsize
	 * = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1); int
	 * max = pageIndex > totalsize ? totalsize * pageSize : pageIndex *
	 * pageSize; int min = pageIndex == 1 ? 1 : (pageIndex - 1) * pageSize + 1;
	 * if (max <= min) { min = min - pageSize; pageIndex -= 1; } String sql =
	 * "SELECT * FROM (SELECT t01.*, rownum as newRowNum FROM (" + mainSql +
	 * ") t01 WHERE rownum <='" + max + "' ) WHERE newRowNum >='" + min + "'";
	 * List<BuNews> xList = this.execSql(sql); return xList; }
	 * 
	 * @Override public List<BuNews> selectByPage(int pageIndex, int
	 * pageSize,String strWhere, String strOrderBy) { int min = pageIndex == 1 ?
	 * 0 : (pageIndex - 1) * pageSize; String _oB = ""; if (strOrderBy != null
	 * && strOrderBy.trim().length() > 0) { _oB = " ORDER BY " + strOrderBy; }
	 * String where="",where2=""; if (strWhere != null &&
	 * strWhere.trim().length() > 0) { where = " WHERE " + strWhere + " ";
	 * where2 = " AND " + strWhere; } String mainSql = "select top " + pageSize
	 * + " * from BuNews  where fuid  not in (select top " + min +
	 * " fuid from BuNews "+ where + _oB + ") " + where2 + _oB; List<BuNews>
	 * xList = this.execSql(mainSql); return xList; }
	 */

	@Override
	public int getRecordCount(String strWhere) {
		return buNewsDAO.count(strWhere);
	}
	
	public int SumFieldForPraise(String where){
		return buNewsDAO.SumField("execPriaseSum", "praise", where);
	}
	
	public int SumFieldForReadNum(String where){
		return buNewsDAO.SumField("execReadNumSum", "readnum", where);
	}

}
