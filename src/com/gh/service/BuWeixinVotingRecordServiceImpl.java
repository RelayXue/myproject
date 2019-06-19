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


public class BuWeixinVotingRecordServiceImpl implements BuWeixinVotingRecordService{
	private BuWeixinVotingRecordDAO buWeixinVotingRecordDAO;
	
	public void setBuWeixinVotingRecordDAO(BuWeixinVotingRecordDAO buWeixinVotingRecordDAO) {
		this.buWeixinVotingRecordDAO = buWeixinVotingRecordDAO;
	}
	
	public BuWeixinVotingRecordDAO getBuWeixinVotingRecordDAO() {
		return this.buWeixinVotingRecordDAO;
	}
	@Override
	public BuWeixinVotingRecord findById(String id) {
		// TODO Auto-generated method stub
		return buWeixinVotingRecordDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeixinVotingRecord item) {
		// TODO Auto-generated method stub
		buWeixinVotingRecordDAO.insert(item);
	}

	@Override
	public void update(BuWeixinVotingRecord item) {
		// TODO Auto-generated method stub
		buWeixinVotingRecordDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BuWeixinVotingRecord item) {
		buWeixinVotingRecordDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeixinVotingRecordDAO.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub  
		buWeixinVotingRecordDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeixinVotingRecord> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeixinVotingRecordDAO.execSql(sql);
	}

	@Override
	public List<BuWeixinVotingRecord> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weixin_voting_record";
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
		List<BuWeixinVotingRecord> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BuWeixinVotingRecord> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM bu_weixin_voting_record";
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
	     List<BuWeixinVotingRecord> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BuWeixinVotingRecord> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BuWeixinVotingRecord  where fuid  not in (select top " + min
				+ " fuid from BuWeixinVotingRecord "+ where + _oB + ") " + where2 + _oB; 
		List<BuWeixinVotingRecord> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM bu_weixin_voting_record";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buWeixinVotingRecordDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buWeixinVotingRecordDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	

}
