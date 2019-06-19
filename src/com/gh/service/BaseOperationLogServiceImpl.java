package com.gh.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gh.common.UUIDCreater;
import com.gh.dao.BaseOperationLogDAO;
import com.gh.entity.BaseOperationLog;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BaseOperationLogServiceImpl implements BaseOperationLogService{
	private BaseOperationLogDAO baseOperationLogDAO;
	
	public void setBaseOperationLogDAO(BaseOperationLogDAO baseOperationLogDAO) {
		this.baseOperationLogDAO = baseOperationLogDAO;
	}
	
	public BaseOperationLogDAO getBaseOperationLogDAO() {
		return this.baseOperationLogDAO;
	}
	@Override
	public BaseOperationLog findById(String id) {
		// TODO Auto-generated method stub
		return baseOperationLogDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BaseOperationLog item) {
		// TODO Auto-generated method stub
		baseOperationLogDAO.insert(item);
	}

	@Override
	public void update(BaseOperationLog item) {
		// TODO Auto-generated method stub
		baseOperationLogDAO.updateByPrimaryKey(item);
	}
	@Override
	public void updateSelective(BaseOperationLog item) {
		baseOperationLogDAO.updateByPrimaryKeySelective(item);
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseOperationLogDAO.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseOperationLog> execSql(String sql) {
		// TODO Auto-generated method stub
		return baseOperationLogDAO.execSql(sql);
	}

	@Override
	public List<BaseOperationLog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM base_operation_log";
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
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<BaseOperationLog> xList = this.execSql(mainSql);
		return xList;
	}
	/*@Override
	public List<BaseOperationLog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
	     String mainSql = "SELECT * FROM base_operation_log";
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
	     List<BaseOperationLog> xList = this.execSql(sql);
	     return xList;
	}
	@Override
	public List<BaseOperationLog> selectByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
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
				+ " * from BaseOperationLog  where fuid  not in (select top " + min
				+ " fuid from BaseOperationLog "+ where + _oB + ") " + where2 + _oB; 
		List<BaseOperationLog> xList = this.execSql(mainSql); 
		return xList;
	}*/
	 
	@Override
	public int getRecordCount(String strWhere) {

	     String sql = "SELECT COUNT(*) FROM base_operation_log";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  baseOperationLogDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( baseOperationLogDAO.selectObject(sql).toString());
	     }
	     return 0; 
	
	
	}
	public void saveLog(String data) {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		BaseOperationLog baseOperationLog=new BaseOperationLog();
		baseOperationLog.setFuid(UUIDCreater.getUUID());
		baseOperationLog.setUsername(username);
		baseOperationLog.setUserid(userid);
		baseOperationLog.setOperating(data);
		baseOperationLog.setCreatedate(new Date());
		baseOperationLogDAO.insert(baseOperationLog);
	}

}
