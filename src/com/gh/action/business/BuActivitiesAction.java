package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.ImageCompression;
import com.gh.common.UUIDCreater;
import com.gh.common.Upload;
import com.gh.entity.BuActivities;
import com.gh.service.BuActivitiesService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuActivitiesAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuActivitiesService buActivitiesService;
	private String uid;
	private String id;
	private String skey;
	private List<BuActivities> buActivities_list;
	private BuActivities buActivities;
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buActivities");
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
		totalPage=buActivitiesService.getRecordCount(SqlWhere);
		buActivities_list=buActivitiesService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buActivities";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String id=buActivities.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buActivities");
			if(!com.getHisUpdate()){
				return "error";
			}
			buActivities.setModifydate(new Date());
			buActivities.setModifyuserrealname(username);
			buActivitiesService.updateSelective(buActivities);
		}else{
			com=CompetenceManager.getCom(roleid, "buActivities");
			if(!com.getHisAdd()){
				return "error";
			}
			//uid 图片关联
			if(uid!=null&&uid.length()>0){
				buActivities.setFuid(uid);
			}else{
				buActivities.setFuid(UUIDCreater.getUUID());
			}
			buActivities.setCreatedate(new Date());
			buActivities.setCreateuserrealname(username);
			buActivities.setModifydate(new Date());
			buActivities.setModifyuserrealname(username);
			buActivitiesService.save(buActivities);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buActivities=buActivitiesService.findById(id);
		return "buActivitiesEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buActivities");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buActivitiesService.delete(ids[a]);
				}
			}else{
				buActivitiesService.delete(id);
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
	public BuActivitiesService getBuActivitiesService() {
		return buActivitiesService;
	}
	public void setBuActivitiesService(BuActivitiesService buActivitiesService) {
		this.buActivitiesService = buActivitiesService;
	}
	public List<BuActivities> getBuActivities_list() {
		return buActivities_list;
	}
	public void setBuActivities_list(List<BuActivities> buActivities_list) {
		this.buActivities_list = buActivities_list;
	}
	public BuActivities getBuActivities() {
		return buActivities;
	}
	public void setBuActivities(BuActivities buActivities) {
		this.buActivities = buActivities;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public File getUploadpic() {
		return uploadpic;
	}
	public void setUploadpic(File uploadpic) {
		this.uploadpic = uploadpic;
	}
	public String getUploadpicFileName() {
		return uploadpicFileName;
	}
	public void setUploadpicFileName(String uploadpicFileName) {
		this.uploadpicFileName = uploadpicFileName;
	}
}
