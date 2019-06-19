package com.gh.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseMenu;
import com.gh.entity.BaseOperating;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseOperatingService;
import com.gh.service.BaseOperationLogService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class OperatingAction  extends Action{
	
	
	private BaseOperationLogService baseOperationLogService;
	private BaseOperatingService baseOperatingService;
	private BaseMenuService baseMenuService;
	private String id;
	private String skey;
	private String MenuId;
	private List<BaseOperating> baseOperating_list;
	private BaseOperating baseOperating;
	
	
	public String show(){
		return "operating";
	}
	public String Operatingshow(){
		String SqlWhere=" (MENUID='"+MenuId+"' and DELETEMARK=0) or code like 'base%'";
		baseOperating_list=baseOperatingService.execSql("select * from base_operating where"+SqlWhere+" order by fullname,fuid ");
		 return "OperatingInfo";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseOperating.getFuid();
		if(id!=null&&id.length()>0){
			baseOperating.setModifydate(new Date());
			baseOperating.setModifyuserid(userid);
			baseOperating.setModifyuserrealname(username);
			baseOperatingService.updateSelective(baseOperating);
			/*
			 * 记录操作日志
			 */
			BaseMenu  baseMenu =baseMenuService.findById(MenuId);
			baseOperationLogService.saveLog("修改菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		}else{
			baseOperating.setFuid(UUIDCreater.getUUID());
			baseOperating.setDeletemark(0);
			baseOperating.setMenuid(MenuId);
			baseOperating.setCreatedate(new Date());
			baseOperating.setCreateuserid(userid);
			baseOperating.setCreateuserrealname(username);
			baseOperating.setModifydate(new Date());
			baseOperating.setModifyuserid(userid);
			baseOperating.setModifyuserrealname(username);
			baseOperatingService.save(baseOperating);
			/*
			 * 记录操作日志
			 */
			BaseMenu  baseMenu =baseMenuService.findById(MenuId);
			baseOperationLogService.saveLog("新增菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		}
		return "show";
	}
	public String update_show(){
		baseOperating=baseOperatingService.findById(id);
		return "operatingEditor";
	}
	public String delete(){
		BaseOperating baseOperating= baseOperatingService.findById(id);
		baseOperating.setDeletemark(1);
		baseOperatingService.update(baseOperating);
		/*
		 * 记录操作日志
		 */
		BaseMenu  baseMenu =baseMenuService.findById(MenuId);
		baseOperationLogService.saveLog("删除菜单"+baseMenu.getMenuName()+baseOperating.getFullname()+"的操作权限");
		return "show";
	}
	public String tree() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseMenu> parentList=new ArrayList<BaseMenu>();
		List<BaseMenu> baseMenu_list = baseMenuService.execSql("select * from base_menu  order by MENU_ORDER,fuid");
		for(int i=0;i<baseMenu_list.size();i++){
			BaseMenu m = (BaseMenu) baseMenu_list.get(i);
    		if(m.getMenuParentid().equals("1")){
    			m = getChild(baseMenu_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = TransformJSON.toJSON(parentList);
		out.print(strs);
		return null;
	}
	 public BaseMenu getChild(List<?> list,String id,BaseMenu baseMenu){
	    	for(int i=0;i<list.size();i++){
	    		BaseMenu m = (BaseMenu) list.get(i);
	    		if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
	    			m = getChild(list, m.getFuid(), m);
	    			baseMenu.getChildren().add(m);
	    		}
	    	}
	    	return baseMenu;
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
	public BaseOperatingService getBaseOperatingService() {
		return baseOperatingService;
	}
	public void setBaseOperatingService(BaseOperatingService baseOperatingService) {
		this.baseOperatingService = baseOperatingService;
	}
	public List<BaseOperating> getBaseOperating_list() {
		return baseOperating_list;
	}
	public void setBaseOperating_list(List<BaseOperating> baseOperating_list) {
		this.baseOperating_list = baseOperating_list;
	}
	public BaseOperating getBaseOperating() {
		return baseOperating;
	}
	public void setBaseOperating(BaseOperating baseOperating) {
		this.baseOperating = baseOperating;
	}
	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}
	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}
	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
	}
}
