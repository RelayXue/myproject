package com.gh.action.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuPublicfacilities;
import com.gh.service.BuPublicfacilitiesService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuPublicfacilitiesAction  extends Action{
	
	
	private BuPublicfacilitiesService buPublicfacilitiesService;
	private String id;
	private String skey;
	private List<BuPublicfacilities> buPublicfacilities_list;
	private BuPublicfacilities buPublicfacilities;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buPublicfacilities");
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" 0=0";
		    if(skey!=null&&skey.length()>0){
		    	SqlWhere+=" and fullname like '%"+skey+"%'";
		    } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buPublicfacilitiesService.getRecordCount(SqlWhere);
		buPublicfacilities_list=buPublicfacilitiesService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "BuPublicfacilities";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buPublicfacilities.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buPublicfacilities");
			if(!com.getHisSelect()){
				return "error";
			}
			buPublicfacilities.setModifydate(new Date());
			buPublicfacilities.setModifyuserrealname(username);
			buPublicfacilitiesService.updateSelective(buPublicfacilities);
		}else{
			com=CompetenceManager.getCom(roleid, "buPublicfacilities");
			if(!com.getHisAdd()){
				return "error";
			}
			buPublicfacilities.setFuid(UUIDCreater.getUUID());
			buPublicfacilities.setCreatedate(new Date());
			buPublicfacilities.setCreateuserrealname(username);
			buPublicfacilities.setModifydate(new Date());
			buPublicfacilities.setModifyuserrealname(username);
			buPublicfacilitiesService.save(buPublicfacilities);
		}
		return this.show();
	}
	public String update_show(){
		buPublicfacilities=buPublicfacilitiesService.findById(id);
		return "buPublicfacilitiesEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buPublicfacilities");
		if(!com.getHisSelect()){
			return "error";
		}
		buPublicfacilitiesService.delete(id);
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
	public BuPublicfacilitiesService getBuPublicfacilitiesService() {
		return buPublicfacilitiesService;
	}
	public void setBuPublicfacilitiesService(BuPublicfacilitiesService buPublicfacilitiesService) {
		this.buPublicfacilitiesService = buPublicfacilitiesService;
	}
	public List<BuPublicfacilities> getBuPublicfacilities_list() {
		return buPublicfacilities_list;
	}
	public void setBuPublicfacilities_list(List<BuPublicfacilities> buPublicfacilities_list) {
		this.buPublicfacilities_list = buPublicfacilities_list;
	}
	public BuPublicfacilities getBuPublicfacilities() {
		return buPublicfacilities;
	}
	public void setBuPublicfacilities(BuPublicfacilities buPublicfacilities) {
		this.buPublicfacilities = buPublicfacilities;
	}
}
