package com.gh.action.system;

import java.util.Date;
import java.util.List;

import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseOperationLog;
import com.gh.service.BaseOperationLogService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class OperationLogAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseOperationLogService baseOperationLogService;
	private List<BaseOperationLog> baseOperationLog_list;
	private BaseOperationLog baseOperationLog;
	private String skey;
	
	public OperationLogAction(){
		this.getBaseOperationLogService();
	}
	
	public String show(){
		String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  username like '%"+skey+"%'";
		} 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseOperationLogService.getRecordCount(SqlWhere);
		baseOperationLog_list=baseOperationLogService.selectByPage(indexPage,pageSize, SqlWhere, "CREATEDATE desc");
		return "OperatingLog";
	}
	public void edit(String data){
		baseOperationLogService.saveLog(data);
	}
	

	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}
	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
	}
	public List<BaseOperationLog> getBaseOperationLog_list() {
		return baseOperationLog_list;
	}
	public void setBaseOperationLog_list(List<BaseOperationLog> baseOperationLog_list) {
		this.baseOperationLog_list = baseOperationLog_list;
	}
	public BaseOperationLog getBaseOperationLog() {
		return baseOperationLog;
	}
	public void setBaseOperationLog(BaseOperationLog baseOperationLog) {
		this.baseOperationLog = baseOperationLog;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
}
