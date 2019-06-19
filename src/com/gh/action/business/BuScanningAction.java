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


public class BuScanningAction  extends Action{
	
	
	private BuScanningService buScanningService;
	private String uid;
	private String id;
	private String skey;
	private List<BuScanning> buScanning_list;
	private BuScanning buScanning;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buScanning");
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
		totalPage=buScanningService.getRecordCount(SqlWhere);
		buScanning_list=buScanningService.selectByPage(indexPage,pageSize, SqlWhere, "SCANNINGTIME desc");
		 return "buScanning";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 *//*
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buScanning.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buScanning");
			if(!com.getHisUpdate()){
				return "error";
			}
			buScanning.setModifydate(new Date());
			buScanning.setModifyuserid(userid);
			buScanning.setModifyuserrealname(username);
			buScanningService.updateSelective(buScanning);
		}else{
			com=CompetenceManager.getCom(roleid, "buScanning");
			if(!com.getHisUpdate()){
				return "error";
			}
			buScanning.setFuid(UUIDCreater.getUUID());
			buScanning.setDeletemark(new BigDecimal(0));
			buScanning.setCreatedate(new Date());
			buScanning.setCreateuserid(userid);
			buScanning.setCreateuserrealname(username);
			buScanning.setModifydate(new Date());
			buScanning.setModifyuserid(userid);
			buScanning.setModifyuserrealname(username);
			buScanningService.save(buScanning);
		}
		return "show";
	}
	*//**
	 * @see 显示详细页或修改页
	 * @author xiao
	 *//*
	public String update_show(){
		buScanning=buScanningService.findById(id);
		return "buScanningEditor";
	}
	*//**
	 * @see 删除单条数据或多条
	 * @author xiao
	 *//*
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buScanning");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buScanningService.delete(ids[a]);
				}
			}else{
				buScanningService.delete(id);
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
	public BuScanningService getBuScanningService() {
		return buScanningService;
	}
	public void setBuScanningService(BuScanningService buScanningService) {
		this.buScanningService = buScanningService;
	}
	public List<BuScanning> getBuScanning_list() {
		return buScanning_list;
	}
	public void setBuScanning_list(List<BuScanning> buScanning_list) {
		this.buScanning_list = buScanning_list;
	}
	public BuScanning getBuScanning() {
		return buScanning;
	}
	public void setBuScanning(BuScanning buScanning) {
		this.buScanning = buScanning;
	}
}
