package com.gh.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseMenu;
import com.gh.entity.BaseRole;
import com.gh.entity.BaseScope;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseOperationLogService;
import com.gh.service.BaseRoleService;
import com.gh.service.BaseScopeService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class ScopeAction  extends Action{
	
	
	private BaseOperationLogService baseOperationLogService;
	private BaseScopeService baseScopeService;
	private BaseMenuService baseMenuService;
	private BaseRoleService baseRoleService;
	private String id;
	private String OrganizeId;
	private String RoleId;
	private String MenuId;
	private List<BaseScope> baseScope_list;
	private BaseScope baseScope;
	
	public String show(){
		return "BaseScope";
	}
	public String showScope(){
		 return "BaseScope";
	}
	public String showScopeMenu() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseRole> baseRole_list=baseRoleService.execSql("select * from base_role where  DELETEMARK=0 and  organizeid='"+OrganizeId+"' order by REALNAME,ORGANIZEID,fuid");
		if(baseRole_list!=null){
			for(int a=0;a<baseRole_list.size();a++){
				List<BaseMenu> baseMenu_list=baseMenuService.execSql("select UUID() FUID,m.fuid as modifyuserrealname,m.* from Base_menu m inner join base_menu_role mr on m.fuid=mr.menu_id and mr.role_id='"+baseRole_list.get(a).getFuid()+"' and mr.operating_id='1111'");
				BaseRole r=baseRole_list.get(a);
				if(baseMenu_list!=null){
					for(int b=0;b<baseMenu_list.size();b++){
						r.getChildren().add(baseMenu_list.get(b));
					}
				}
			}
		}
		out.print(TransformJSON.toJSON(baseRole_list));
		return null;
	}
	public String ScopeQx(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			baseScopeService.execSql("delete from base_Scope where menu_id='"+MenuId+"' and role_id='"+RoleId+"'");
			String fuid[]=id.split(",");
			if(fuid!= null){
				for(int a=0;a<fuid.length;a++){
					baseScope=new BaseScope();
					baseScope.setFuid(UUIDCreater.getUUID());
					baseScope.setMenuId(MenuId);
					baseScope.setRoleId(RoleId);
					baseScope.setOrganizeId(fuid[a]);
					baseScope.setCreatedate(new Date());
					baseScope.setEnabled(1);
					baseScope.setDeletemark(0);
					baseScope.setCreateuserid(userid);
					baseScope.setCreateuserrealname(username);
					baseScope.setModifydate(new Date());
					baseScope.setModifyuserid(userid);
					baseScope.setModifyuserrealname(username);
					baseScopeService.save(baseScope);
				}
				/*
				 * 记录操作日志
				 */
				BaseRole  baseRole =baseRoleService.findById(RoleId);
				BaseMenu  baseMenu =baseMenuService.findById(MenuId);
				baseOperationLogService.saveLog("为角色"+baseRole.getRealname()+"分配"+baseMenu.getMenuName()+"菜单数据集权限");
				ServletContext context=ServletActionContext.getServletContext();
				CompetenceManager a=new CompetenceManager();
				a.RefreshDataScope(context);
			}
		}
		return "show";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseScope.getFuid();
		if(id!=null&&id.length()>0){
			baseScope.setModifydate(new Date());
			baseScope.setModifyuserid(userid);
			baseScope.setModifyuserrealname(username);
			baseScopeService.updateSelective(baseScope);
		}else{
			baseScope.setFuid(UUIDCreater.getUUID());
			baseScope.setCreatedate(new Date());
			baseScope.setCreateuserid(userid);
			baseScope.setCreateuserrealname(username);
			baseScope.setModifydate(new Date());
			baseScope.setModifyuserid(userid);
			baseScope.setModifyuserrealname(username);
			baseScopeService.save(baseScope);
		}
		return "show";
	}
	public String update_show(){
		baseScope=baseScopeService.findById(id);
		return "baseScopeEditor";
	}
	public String delete(){
		baseScopeService.delete(id);
		return this.show();
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BaseScopeService getBaseScopeService() {
		return baseScopeService;
	}
	public void setBaseScopeService(BaseScopeService baseScopeService) {
		this.baseScopeService = baseScopeService;
	}
	public List<BaseScope> getBaseScope_list() {
		return baseScope_list;
	}
	public void setBaseScope_list(List<BaseScope> baseScope_list) {
		this.baseScope_list = baseScope_list;
	}
	public BaseScope getBaseScope() {
		return baseScope;
	}
	public void setBaseScope(BaseScope baseScope) {
		this.baseScope = baseScope;
	}
	public BaseRoleService getBaseRoleService() {
		return baseRoleService;
	}
	public void setBaseRoleService(BaseRoleService baseRoleService) {
		this.baseRoleService = baseRoleService;
	}
	public String getOrganizeId() {
		return OrganizeId;
	}
	public void setOrganizeId(String organizeId) {
		OrganizeId = organizeId;
	}
	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}
	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
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
