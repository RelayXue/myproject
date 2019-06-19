package com.gh.action.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuLoglogAction  extends Action{
	
	
	private BuLoglogService buLoglogService;
	private String uid;
	private String id;
	private String skey;
	private List<BuLoglog> buLoglog_list;
	private BuLoglog buLoglog;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buLoglog");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  realname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buLoglogService.getRecordCount(SqlWhere);
		buLoglog_list=buLoglogService.selectByPage(indexPage,pageSize, SqlWhere, "LOGINGTIME desc");
		 return "buLoglog";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 *//*
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buLoglog.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buLoglog");
			if(!com.getHisUpdate()){
				return "error";
			}
			buLoglog.setModifydate(new Date());
			buLoglog.setModifyuserid(userid);
			buLoglog.setModifyuserrealname(username);
			buLoglogService.updateSelective(buLoglog);
		}else{
			com=CompetenceManager.getCom(roleid, "buLoglog");
			if(!com.getHisUpdate()){
				return "error";
			}
			buLoglog.setFuid(UUIDCreater.getUUID());
			buLoglog.setDeletemark(new BigDecimal(0));
			buLoglog.setCreatedate(new Date());
			buLoglog.setCreateuserid(userid);
			buLoglog.setCreateuserrealname(username);
			buLoglog.setModifydate(new Date());
			buLoglog.setModifyuserid(userid);
			buLoglog.setModifyuserrealname(username);
			buLoglogService.save(buLoglog);
		}
		return "show";
	}
	*//**
	 * @see 显示详细页或修改页
	 * @author xiao
	 *//*
	public String update_show(){
		buLoglog=buLoglogService.findById(id);
		return "buLoglogEditor";
	}
	*//**
	 * @see 删除单条数据或多条
	 * @author xiao
	 *//*
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buLoglog");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buLoglogService.delete(ids[a]);
				}
			}else{
				buLoglogService.delete(id);
			}
		}
		return "show";
	}*/
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuLoglogService getBuLoglogService() {
		return buLoglogService;
	}
	public void setBuLoglogService(BuLoglogService buLoglogService) {
		this.buLoglogService = buLoglogService;
	}
	public List<BuLoglog> getBuLoglog_list() {
		return buLoglog_list;
	}
	public void setBuLoglog_list(List<BuLoglog> buLoglog_list) {
		this.buLoglog_list = buLoglog_list;
	}
	public BuLoglog getBuLoglog() {
		return buLoglog;
	}
	public void setBuLoglog(BuLoglog buLoglog) {
		this.buLoglog = buLoglog;
	}
}
