package com.gh.action.web;

import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuRoad;
import com.gh.service.BuRoadService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuRoadAction  extends Action{
	
	
	private BuRoadService buRoadService;
	private String uid;
	private String id;
	private String skey;
	private List<BuRoad> buRoad_list;
	private BuRoad buRoad;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buRoad");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buRoadService.getRecordCount(SqlWhere);
		buRoad_list=buRoadService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buRoad";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buRoad.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buRoad");
			if(!com.getHisUpdate()){
				return "error";
			}
			buRoad.setModifydate(new Date());
			buRoadService.updateSelective(buRoad);
		}else{
			com=CompetenceManager.getCom(roleid, "buRoad");
			if(!com.getHisUpdate()){
				return "error";
			}
			buRoad.setFuid(UUIDCreater.getUUID());
			buRoad.setCreatedate(new Date());
			buRoad.setModifydate(new Date());
			buRoadService.save(buRoad);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buRoad=buRoadService.findById(id);
		return "buRoadEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buRoad");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buRoadService.delete(ids[a]);
				}
			}else{
				buRoadService.delete(id);
			}
		}
		return "show";
	}
	

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
	public BuRoadService getBuRoadService() {
		return buRoadService;
	}
	public void setBuRoadService(BuRoadService buRoadService) {
		this.buRoadService = buRoadService;
	}
	public List<BuRoad> getBuRoad_list() {
		return buRoad_list;
	}
	public void setBuRoad_list(List<BuRoad> buRoad_list) {
		this.buRoad_list = buRoad_list;
	}
	public BuRoad getBuRoad() {
		return buRoad;
	}
	public void setBuRoad(BuRoad buRoad) {
		this.buRoad = buRoad;
	}
}
