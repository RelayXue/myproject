package com.gh.action.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuQuicklinks;
import com.gh.service.BuQuicklinksService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuQuicklinksAction  extends Action{
	
	
	private BuQuicklinksService buQuicklinksService;
	private String id;
	private String skey;
	private List<BuQuicklinks> buQuicklinks_list;
	private BuQuicklinks buQuicklinks;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buQuicklinks");
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  fullname like '%"+skey+"%'";
		 } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buQuicklinksService.getRecordCount(SqlWhere);
		buQuicklinks_list=buQuicklinksService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "BuQuicklinks";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buQuicklinks.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buQuicklinks");
			if(!com.getHisSelect()){
				return "error";
			}
			buQuicklinks.setModifydate(new Date());
			buQuicklinks.setModifyuserrealname(username);
			buQuicklinksService.updateSelective(buQuicklinks);
		}else{
			com=CompetenceManager.getCom(roleid, "buQuicklinks");
			if(!com.getHisSelect()){
				return "error";
			}
			buQuicklinks.setFuid(UUIDCreater.getUUID());
			buQuicklinks.setCreatedate(new Date());
			buQuicklinks.setCreateuserrealname(username);
			buQuicklinks.setModifydate(new Date());
			buQuicklinks.setModifyuserrealname(username);
			buQuicklinksService.save(buQuicklinks);
		}
		return this.show();
	}
	public String update_show(){
		buQuicklinks=buQuicklinksService.findById(id);
		return "buQuicklinksEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buQuicklinks");
		if(!com.getHisSelect()){
			return "error";
		}
		buQuicklinksService.delete(id);
		return this.show();
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuQuicklinksService getBuQuicklinksService() {
		return buQuicklinksService;
	}
	public void setBuQuicklinksService(BuQuicklinksService buQuicklinksService) {
		this.buQuicklinksService = buQuicklinksService;
	}
	public List<BuQuicklinks> getBuQuicklinks_list() {
		return buQuicklinks_list;
	}
	public void setBuQuicklinks_list(List<BuQuicklinks> buQuicklinks_list) {
		this.buQuicklinks_list = buQuicklinks_list;
	}
	public BuQuicklinks getBuQuicklinks() {
		return buQuicklinks;
	}
	public void setBuQuicklinks(BuQuicklinks buQuicklinks) {
		this.buQuicklinks = buQuicklinks;
	}
}
