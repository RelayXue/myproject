package com.gh.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseOrganize;
import com.gh.entity.BaseOrganizeMenu;
import com.gh.entity.BaseScope;
import com.gh.service.BaseOperationLogService;
import com.gh.service.BaseOrganizeMenuService;
import com.gh.service.BaseOrganizeService;
import com.gh.service.BaseScopeService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class OrganizeAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseOperationLogService baseOperationLogService;
	private BaseOrganizeService baseOrganizeService;
	private BaseOrganizeMenuService baseOrganizeMenuService;
	private String OrganizeId;
	private String RoleId;
	private String MenuId;
	private String id;
	private String skey;
	private List<BaseOrganize> baseOrganize_list;
	private BaseOrganize baseOrganize;
	private BaseScopeService baseScopeService;
	
	public String add(){
		
		return null;
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String id=baseOrganize.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "system/Organize.jsp");
			if(!com.getHisSelect()){
				return "error";
			}
			BaseOrganize baseOrganize_new=baseOrganizeService.findById(id);
			baseOrganize_new.setCode(baseOrganize.getCode());
			baseOrganize_new.setDescription(baseOrganize.getDescription());
			baseOrganize_new.setFullname(baseOrganize.getFullname());
			baseOrganize_new.setIscargo(baseOrganize.getIscargo());
			baseOrganize_new.setInnerphone(baseOrganize.getInnerphone());
			baseOrganize_new.setManager(baseOrganize.getManager());
			baseOrganize_new.setSortcode(baseOrganize.getSortcode());
			baseOrganize_new.setOuterphone(baseOrganize.getOuterphone());
			baseOrganize_new.setAddress(baseOrganize.getAddress());
			baseOrganize_new.setPossonoapplicare(baseOrganize.getPossonoapplicare());
			baseOrganize_new.setBusinesslicense(baseOrganize.getBusinesslicense());
			baseOrganize_new.setManagerid(baseOrganize.getManagerid());
			baseOrganize_new.setFax(baseOrganize.getFax());
			baseOrganize_new.setRegisteredcapital(baseOrganize.getRegisteredcapital());
			baseOrganize_new.setContact(baseOrganize.getContact());
			baseOrganize_new.setEmail(baseOrganize.getEmail());
			baseOrganize_new.setModifydate(new Date());
			baseOrganize_new.setModifyuserrealname(username);
			baseOrganizeService.update(baseOrganize_new);
		}else{

			com=CompetenceManager.getCom(roleid, "system/Organize.jsp");
			if(!com.getHisSelect()){
				return "error";
			}
			baseOrganize.setFuid(UUIDCreater.getUUID());
			if(baseOrganize.getParentid()!=null){
				String parentid=baseOrganize.getParentid();
				 BaseOrganize Organize=baseOrganizeService.findById(parentid);
				 if(Organize.getParentid()!=null){
					 baseOrganize.setLayer(Organize.getLayer()+1);
				 } 
			}else{
				baseOrganize.setLayer(1);
			}
			baseOrganize.setDeletemark(0);
			baseOrganize.setEnabled(1);
			baseOrganize.setCreatedate(new Date());
			baseOrganize.setModifydate(new Date());
			baseOrganizeService.save(baseOrganize);
		}
		CompetenceManager a=new CompetenceManager();
		ServletContext context=ServletActionContext.getServletContext();
		a.RefreshDataOrganize(context);
		return null;
	}
	public String update_show(){
		baseOrganize=baseOrganizeService.findById(id);
		return "OrganizeEditor";
	}
	public String tree() throws IOException{
		response.setCharacterEncoding("UTF-8");
		String departmentid=(String)request.getSession().getAttribute("departmentid");
		int layer=baseOrganizeService.findById(departmentid).getLayer();
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseOrganize> parentList=new ArrayList<BaseOrganize>();
		baseOrganize_list=baseOrganizeService.execSql("select * from Base_Organize where DELETEMARK=0 and  (fuid='"+departmentid+"' or layer>"+layer+")  order by SortCode");
		for(int i=0;i<baseOrganize_list.size();i++){
			BaseOrganize m = (BaseOrganize) baseOrganize_list.get(i);
			if(m.getLayer()==layer){
				m = getChild(baseOrganize_list, m.getFuid(), m);
				parentList.add(m);
			}
		}
		String strs = TransformJSON.toJSON(parentList);
		out.print(strs);
		return null;
	}
	public String ScopeTree() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseOrganize> parentList=new ArrayList<BaseOrganize>();
		baseOrganize_list=baseOrganizeService.execSql("select * from Base_Organize  order by SortCode");
		List<BaseScope> baseScope_list=baseScopeService.execSql("select * from  base_scope where role_id='"+RoleId+"' and menu_id ='"+MenuId+"' ");
		for(int i=0;i<baseOrganize_list.size();i++){
			baseOrganize_list.get(i).setText(baseOrganize_list.get(i).getFullname());
			baseOrganize_list.get(i).setId(baseOrganize_list.get(i).getFuid());
			if(baseScope_list!=null){
				for(int a=0;a<baseScope_list.size();a++){
					if(baseScope_list.get(a).getOrganizeId().equals(baseOrganize_list.get(i).getFuid())){
						baseOrganize_list.get(i).setChecked(true);
					}
				}
			}
			BaseOrganize m = (BaseOrganize) baseOrganize_list.get(i);
    		if(m.getParentid().equals("1")){
    			m = getChild(baseOrganize_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = TransformJSON.toJSON(parentList);
		out.print(strs);
		return null;
	}
	 public BaseOrganize getChild(List<?> list,String id,BaseOrganize organize){
	    	for(int i=0;i<list.size();i++){
	    		BaseOrganize m = (BaseOrganize) list.get(i);
	    		if(!m.getParentid().equals("1")&&m.getParentid().equals(id)){
	    			m = getChild(list, m.getFuid(), m);
	    			organize.getChildren().add(m);
	    		}
	    	}
	    	return organize;
	    }
	 public String delete() throws IOException{
		 PrintWriter out=ServletActionContext.getResponse().getWriter();
		 BaseOrganize  baseOrganize =baseOrganizeService.findById(id);
		 baseOrganize.setDeletemark(1);
		 baseOrganizeService.update(baseOrganize);
		 out.print(1);
		 CompetenceManager a=new CompetenceManager();
		 ServletContext context=ServletActionContext.getServletContext();
		 a.RefreshDataOrganize(context);
		 return null;
	 }
	 
	public String Assign(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		if(id!=null){
			baseOrganizeMenuService.execSql("delete from  BASE_ORGANIZE_MENU where ORGANIZE_ID='"+OrganizeId+"'");
			String fuid[]=id.split(",");
			if(fuid!=null){
				for(int a=0;a<fuid.length;a++){
					String temp[]=fuid[a].trim().split("~");
					BaseOrganizeMenu baseOrganizeMenu=new BaseOrganizeMenu();
					baseOrganizeMenu.setFuid(UUIDCreater.getUUID());
					baseOrganizeMenu.setOrganizeId(OrganizeId);
					baseOrganizeMenu.setMenuId(temp[0]);
					baseOrganizeMenu.setOperationid(temp[1]);
					baseOrganizeMenu.setCreatedate(new Date());
					baseOrganizeMenu.setCreateuserid(userid);
					baseOrganizeMenu.setCreateuserrealname(username);
					baseOrganizeMenu.setModifydate(new Date());
					baseOrganizeMenu.setModifyuserid(userid);
					baseOrganizeMenu.setModifyuserrealname(username);
					baseOrganizeMenuService.save(baseOrganizeMenu);
				}
			}
		}
		/*
		 * 记录操作日志
		 */
		BaseOrganize  baseOrganize =baseOrganizeService.findById(OrganizeId);
		baseOperationLogService.saveLog("为部门"+baseOrganize.getFullname()+"分配部门权限");
		return "showMenu";
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
	public BaseOrganizeService getBaseOrganizeService() {
		return baseOrganizeService;
	}
	public void setBaseOrganizeService(BaseOrganizeService baseOrganizeService) {
		this.baseOrganizeService = baseOrganizeService;
	}
	public List<BaseOrganize> getBaseOrganize_list() {
		return baseOrganize_list;
	}
	public void setBaseOrganize_list(List<BaseOrganize> baseOrganize_list) {
		this.baseOrganize_list = baseOrganize_list;
	}
	public BaseOrganize getBaseOrganize() {
		return baseOrganize;
	}
	public void setBaseOrganize(BaseOrganize baseOrganize) {
		this.baseOrganize = baseOrganize;
	}
	public BaseOrganizeMenuService getBaseOrganizeMenuService() {
		return baseOrganizeMenuService;
	}
	public void setBaseOrganizeMenuService(BaseOrganizeMenuService baseOrganizeMenuService) {
		this.baseOrganizeMenuService = baseOrganizeMenuService;
	}
	public String getOrganizeId() {
		return OrganizeId;
	}
	public void setOrganizeId(String organizeId) {
		OrganizeId = organizeId;
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
	public BaseScopeService getBaseScopeService() {
		return baseScopeService;
	}
	public void setBaseScopeService(BaseScopeService baseScopeService) {
		this.baseScopeService = baseScopeService;
	}
	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}
	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
	}
}
