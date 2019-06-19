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
import com.gh.entity.BuServiceltems;
import com.gh.service.BuServiceltemsService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuServiceltemsAction  extends Action{
	
	
	private BuServiceltemsService buServiceltemsService;
	private String uid;
	private String id;
	private String skey;
	private List<BuServiceltems> buServiceltems_list;
	private BuServiceltems buServiceltems;
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buServiceltems");
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
		totalPage=buServiceltemsService.getRecordCount(SqlWhere);
		buServiceltems_list=buServiceltemsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buServiceltems";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String id=buServiceltems.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		// file 上传文件、filename 上传文件名、address 文件保存路径
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buServiceltems");
			if(!com.getHisUpdate()){
				return "error";
			}
			buServiceltems.setModifydate(new Date());
			buServiceltems.setModifyuserrealname(username);
			buServiceltemsService.updateSelective(buServiceltems);
		}else{
			com=CompetenceManager.getCom(roleid, "buServiceltems");
			if(!com.getHisUpdate()){
				return "error";
			}
			//uid 图片关联
			if(uid!=null&&uid.length()>0){
				buServiceltems.setFuid(uid);
			}else{
				buServiceltems.setFuid(UUIDCreater.getUUID());
			}
			buServiceltems.setCreatedate(new Date());
			buServiceltems.setCreateuserrealname(username);
			buServiceltems.setModifydate(new Date());
			buServiceltems.setModifyuserrealname(username);
			buServiceltemsService.save(buServiceltems);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buServiceltems=buServiceltemsService.findById(id);
		return "buServiceltemsEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buServiceltems");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buServiceltemsService.delete(ids[a]);
				}
			}else{
				buServiceltemsService.delete(id);
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
	public BuServiceltemsService getBuServiceltemsService() {
		return buServiceltemsService;
	}
	public void setBuServiceltemsService(BuServiceltemsService buServiceltemsService) {
		this.buServiceltemsService = buServiceltemsService;
	}
	public List<BuServiceltems> getBuServiceltems_list() {
		return buServiceltems_list;
	}
	public void setBuServiceltems_list(List<BuServiceltems> buServiceltems_list) {
		this.buServiceltems_list = buServiceltems_list;
	}
	public BuServiceltems getBuServiceltems() {
		return buServiceltems;
	}
	public void setBuServiceltems(BuServiceltems buServiceltems) {
		this.buServiceltems = buServiceltems;
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
