package com.gh.action.system;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.dao.BaseMenuRoleDAO;
import com.gh.entity.BaseMenuRole;
import com.gh.entity.BaseRole;
import com.gh.service.BaseMenuRoleService;
import com.gh.service.BaseOperationLogService;
import com.gh.service.BaseRoleService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class RoleAction  extends Action{
	
	
	private BaseOperationLogService baseOperationLogService;
	private BaseRoleService baseRoleService;
	private BaseMenuRoleService baseMenuRoleService;
	private BaseMenuRoleDAO baseMenuRoleDAO;
	private String id;
	private String OrganizeId;
	private String RoleId;
	private String skey;
	private List<BaseRole> baseRole_list;
	private BaseRole baseRole;
	
	public String show(){
		String SqlWhere="";
		if(skey!=null&&skey.length()>0){
			if(SqlWhere.length()==0){
				SqlWhere+=" fname like '%"+skey+"%'";
			}else{
				SqlWhere+=" and fname like '%"+skey+"%'";
			}
		} 
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseRoleService.getRecordCount(SqlWhere);
		baseRole_list=baseRoleService.selectByPage(indexPage,pageSize, SqlWhere, "ORGANIZEID,REALNAME,fuid");
		return "role";
	}
	public String showRole(){
		baseRole_list=baseRoleService.execSql("select * from  base_role where  DELETEMARK=0 and  organizeid='"+OrganizeId+"' order by REALNAME,ORGANIZEID,fuid");
		return "role";
	}
	public String scopeRole(){
		baseRole_list=baseRoleService.execSql("select * from  base_role where  DELETEMARK=0 and  organizeid='"+OrganizeId+"' order by REALNAME,ORGANIZEID,fuid");
		if(baseRole_list!=null){
			for(int a=0;a<baseRole_list.size();a++){
				
			}
		}
		return "scopeRole";
	}
	public String showChange(){
		String departmentid=(String)request.getSession().getAttribute("departmentid");
		String superAdmin=(String)request.getSession().getAttribute("superAdmin");
		String where="";
		if(superAdmin!=null&&superAdmin.equals("spadmin")){
			where="where DELETEMARK=0 and ORGANIZEID='"+OrganizeId+"'";
		}else{
			where="where DELETEMARK=0 and ORGANIZEID='"+departmentid+"'";
		}
		baseRole_list=baseRoleService.execSql("select * from base_role "+where+" order by ORGANIZEID,REALNAME,fuid");
		return "roleChange";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseRole.getFuid();
		if(id!=null&&id.length()>0){
			baseRole.setModifydate(new Date());
			baseRole.setModifyuserid(userid);
			baseRole.setModifyuserrealname(username);
			baseRole.setEnabled(1);
			baseRoleService.updateSelective(baseRole);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("修改角色"+baseRole.getRealname());
		}else{
			baseRole.setFuid(UUIDCreater.getUUID());
			baseRole.setDeletemark(0);
			baseRole.setOrganizeid(OrganizeId);
			baseRole.setCreatedate(new Date());
			baseRole.setCreateuserid(userid);
			baseRole.setEnabled(1);
			baseRole.setCreateuserrealname(username);
			baseRole.setModifydate(new Date());
			baseRole.setModifyuserid(userid);
			baseRole.setModifyuserrealname(username);
			baseRoleService.save(baseRole);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("新增角色"+baseRole.getRealname());
		}
		return "show";
	}
	public String Enable(){
		if(id!=null&&id.length()>0){
			baseRole.setEnabled(1);
			baseRoleService.updateSelective(baseRole);
		} 
		return "show";
	}
	public String update_show(){
		baseRole=baseRoleService.findById(id);
		return "roleEditor";
	}
	public String delete(){
		baseRoleService.delete(id);
		return "show";
	}
	
	public String valid(){
		BaseRole  baseRole =baseRoleService.findById(id);
		if(skey!=null&&skey.equals("1")){
			baseRole.setEnabled(1);
			baseRoleService.update(baseRole);
		}else{
			baseRole.setEnabled(0);
			baseRoleService.update(baseRole);
		}
		return "show";
	}
	
	public String Assign(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			baseMenuRoleDAO.deleteByWhere("role_id='"+RoleId+"'");
			String fuid[]=id.split(",");
			if(fuid!=null){
				for(int a=0;a<fuid.length;a++){
					String tt[]=fuid[a].trim().split(";");
					BaseMenuRole baseMenuRole=new BaseMenuRole();
					baseMenuRole.setFuid(UUIDCreater.getUUID());
					baseMenuRole.setRoleId(RoleId);
					baseMenuRole.setOperatingId(tt[1].trim());
					baseMenuRole.setMenuId(tt[0].trim());
					baseMenuRole.setCreatedate(new Date());
					baseMenuRole.setCreateuserid(userid);
					baseMenuRole.setCreateuserrealname(username);
					baseMenuRole.setModifydate(new Date());
					baseMenuRole.setModifyuserid(userid);
					baseMenuRole.setModifyuserrealname(username);
					baseMenuRoleService.save(baseMenuRole);
				}
				/*
				 * 记录操作日志
				 */
				BaseRole baseRole =baseRoleService.findById(RoleId);
				baseOperationLogService.saveLog("为角色"+baseRole.getRealname()+"分配权限");
			}
		}
		/*
		 * 刷新缓存
		 */
		CompetenceManager a=new CompetenceManager();
		ServletContext context=ServletActionContext.getServletContext();
		a.refreshRoleMenu(context);
		return null;
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
	public BaseRoleService getBaseRoleService() {
		return baseRoleService;
	}
	public void setBaseRoleService(BaseRoleService baseRoleService) {
		this.baseRoleService = baseRoleService;
	}
	public List<BaseRole> getBaseRole_list() {
		return baseRole_list;
	}
	public void setBaseRole_list(List<BaseRole> baseRole_list) {
		this.baseRole_list = baseRole_list;
	}
	public BaseRole getBaseRole() {
		return baseRole;
	}
	public void setBaseRole(BaseRole baseRole) {
		this.baseRole = baseRole;
	}
	public String getOrganizeId() {
		return OrganizeId;
	}
	public void setOrganizeId(String organizeId) {
		OrganizeId = organizeId;
	}
	public BaseMenuRoleService getBaseMenuRoleService() {
		return baseMenuRoleService;
	}
	public void setBaseMenuRoleService(BaseMenuRoleService baseMenuRoleService) {
		this.baseMenuRoleService = baseMenuRoleService;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}
	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
	}
	public BaseMenuRoleDAO getBaseMenuRoleDAO() {
		return baseMenuRoleDAO;
	}
	public void setBaseMenuRoleDAO(BaseMenuRoleDAO baseMenuRoleDAO) {
		this.baseMenuRoleDAO = baseMenuRoleDAO;
	}
}
