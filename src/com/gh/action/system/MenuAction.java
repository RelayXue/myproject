package com.gh.action.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.common.Upload;
import com.gh.entity.BaseMenu;
import com.gh.entity.BaseMenuRole;
import com.gh.entity.BaseOperating;
import com.gh.entity.BaseOrganizeMenu;
import com.gh.service.BaseMenuRoleService;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseOperatingService;
import com.gh.service.BaseOperationLogService;
import com.gh.service.BaseOrganizeMenuService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class MenuAction  extends Action{
	
	
	private BaseOperationLogService baseOperationLogService;
	private BaseMenuService baseMenuService;
	private BaseOperatingService baseOperatingService;
	private BaseMenuRoleService baseMenuRoleService;
	private BaseOrganizeMenuService baseOrganizeMenuService;
	private String id;
	private String OrganizeId;
	private String parentid;
	private String RoleId;
	private String skey;
	private Map<String,List<BaseMenu>> baseMenu_map=new HashMap<String, List<BaseMenu>>();
	private List<BaseMenu> baseMenu_list;
	private List<BaseMenu> baseMenu_all;
	private List<BaseMenu> parentList;
	private List<BaseOrganizeMenu> baseOrganizeMenu_list;
	private BaseMenu baseMenu;
	private List<BaseOperating> Op_lst=new ArrayList<BaseOperating>();
	private  List<BaseMenuRole> MenuRole_list =new ArrayList<BaseMenuRole>();
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	
	public String show(){
		baseMenu_list=baseMenuService.execSql("select * from  base_menu  order by MENU_ORDER");
		parentList=new ArrayList<BaseMenu>();
		if(baseMenu_list!=null&&baseMenu_list.size()>0){
			for(int a=0;a<baseMenu_list.size();a++){
				BaseMenu m = (BaseMenu) baseMenu_list.get(a);
				if(m.getMenuParentid()!=null&&m.getMenuParentid().equals("1")){
					m = getChildMenu(baseMenu_list, m.getFuid(), m);
					parentList.add(m);
				}
			}
		}
		return "menu";
	}
	public String showMenu(){
		 return "menuorganize";
	}
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=baseMenu.getFuid();
		if(id!=null&&id.length()>0){
			baseMenu.setModifydate(new Date());
			baseMenu.setModifyuserid(userid);
			baseMenu.setModifyuserrealname(username);
			String picaddress = Upload.upload(uploadpic, uploadpicFileName, "/upload",
					context);
			if(picaddress!=null){
				baseMenu.setImages(picaddress);
			}
			baseMenuService.updateSelective(baseMenu);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("修改菜单"+baseMenu.getMenuName()+"的信息");
		}else{
			baseMenu.setFuid(UUIDCreater.getUUID());
			if(parentid!=null&&!parentid.equals("null")){
				baseMenu.setMenuParentid(parentid);
			}else{
				baseMenu.setMenuParentid("1");
			}
			baseMenu.setCreatedate(new Date());
			baseMenu.setCreateuserid(userid);
			baseMenu.setCreateuserrealname(username);
			baseMenu.setModifydate(new Date());
			baseMenu.setModifyuserid(userid);
			baseMenu.setModifyuserrealname(username);
			// file 上传文件、filename 上传文件名、address 文件保存路径
			String picaddress = Upload.upload(uploadpic, uploadpicFileName, "/upload",
					context);
			if(picaddress!=null){
				baseMenu.setImages(picaddress);
			}
			baseMenuService.save(baseMenu);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("新增菜单"+baseMenu.getMenuName()+"的信息");
		}
		CompetenceManager a=new CompetenceManager();
		ServletContext context=ServletActionContext.getServletContext();
		a.refreshRoleMenu(context);
		return "show";
	}
	public String update_show(){
		baseMenu=baseMenuService.findById(id);
		return "MenuEditor";
	}
	public String delete(){
		 List<BaseMenuRole>baseMenuRole_ist =baseMenuRoleService.execSql("select * from base_menu_role where menu_id='"+id+"'");
		 if(baseMenuRole_ist!=null){
			 for(int a=0;a<baseMenuRole_ist.size();a++){
				 baseMenuRoleService.delete(baseMenuRole_ist.get(a).getFuid());
			 }
		 }
		baseMenuService.delete(id);
		/*
		 * 记录操作日志
		 */
		//baseOperationLogService.saveLog("删除菜单"+baseMenu.getMenuName());
		return "show";
	}
	public String deletePar() throws IOException{
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		int a=baseMenuService.getRecordCount(" menu_parentid='"+parentid+"'");
		if(a>0){
			out.print("0");
		}else{
			baseMenuService.delete(parentid);
			out.print("1");
		}
		
		return null;
	}
	public String ShowMenuRole(){
		return "menurole";
	}
	
	public String treeRole() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseMenu> parentList=new ArrayList<BaseMenu>();
		String superAdmin=(String)request.getSession().getAttribute("superAdmin");
		String sql="";
		if(superAdmin!=null&&superAdmin.equals("spadmin")){
			sql="select * from Base_menu order by menu_order";
		}else{
			sql="select * from Base_menu where fuid in (select menu_id from BASE_ORGANIZE_MENU where organize_id ='"+OrganizeId+"' group by menu_id) order by menu_order";
		}
		List<BaseMenu> baseMenu_list=baseMenuService.execSql(sql);
		baseMenu_all=baseMenuService.execSql("select * from Base_menu");
				for(int i=0;i<baseMenu_list.size();i++){
					BaseMenu n=hasParent(baseMenu_all,baseMenu_list.get(i).getMenuParentid());
					if(n!=null){
						if(!isexit(n.getFuid(),baseMenu_list)){
							baseMenu_list.add(n);
						}
					}
				}
	    MenuRole_list =baseMenuRoleService.execSql("select * from  base_menu_role where role_id='"+RoleId+"'");
		for(int i=0;i<baseMenu_list.size();i++){
			BaseMenu m = baseMenu_list.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid());
			if(m.getMenuParentid().equals("1")){
				BaseMenu m1 = getChildRole(baseMenu_list, m.getFuid(), m,superAdmin);
				parentList.add(m1);
			}
		}
		String strs = TransformJSON.toJSON(parentList);
		out.print(strs);
		return null;
	}
	public BaseMenu hasParent(List<BaseMenu> list,String parentid){
		BaseMenu m=null;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getFuid().equals(parentid)){
					m=list.get(i); 
				}
			}
		} 
		return m;
	}
	public Boolean isexit(String id,List<BaseMenu> list){
		Boolean s=false;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getFuid().equals(id)){
					s=true;
					break;
				} 
			}
		} 
		return s;
	}
	public int hasParent(String id,List<BaseMenu> list){
		int s=0;
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getMenuParentid().equals(id)){
					s++;
				} 
			}
		} 
		return s;
	}
	public BaseMenu getChildRole(List<?> list,String id,BaseMenu menu,String superAdmin){
		for(int i=0;i<list.size();i++){
			BaseMenu m = (BaseMenu) list.get(i);
			if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
				m = getChildRole(list, m.getFuid(), m,superAdmin);
				int g=this.hasParent(m.getFuid(), baseMenu_all);
				if(g==0){
					List<BaseOperating> Op_lst=new ArrayList<BaseOperating>();
					if(superAdmin!=null&&superAdmin.equals("spadmin")){
						Op_lst=baseOperatingService.execSql("select * from base_operating where  deletemark=0");
					}else{
						Op_lst=baseOperatingService.execSql("select * from  base_operating where fuid in (select operationid from BASE_ORGANIZE_MENU where organize_id ='"+OrganizeId+"' and menu_id='"+m.getFuid()+"')");
					}
					if(Op_lst!=null){
						for(int d=0;d<Op_lst.size();d++){
							String code=Op_lst.get(d).getCode();
							String menuid=Op_lst.get(d).getMenuid();
							//判断操作是否该菜单下面 包含基础操作
							if((code!=null&&code.startsWith("base"))||(menuid!=null&&menuid.equals(m.getFuid()))){
								Op_lst.get(d).setText(Op_lst.get(d).getFullname());
								Op_lst.get(d).setId(Op_lst.get(d).getFuid());
								Op_lst.get(d).setAttributes(Op_lst.get(d).getCode());
								Op_lst.get(d).setChecked(false);
								String opid=Op_lst.get(d).getFuid();
								if(MenuRole_list!=null){
									for(int f=0;f<MenuRole_list.size();f++){
										String menu_id=MenuRole_list.get(f).getMenuId();
										String operating_id=MenuRole_list.get(f).getOperatingId();
										//判断该菜单是否有该操作
										if(menu_id!=null&&menu_id.equals(m.getFuid())&&operating_id!=null&&operating_id.equals(opid)){
											Op_lst.get(d).setChecked(true);
										}
									}
								}
								m.getChildren().add(Op_lst.get(d));
							}
							
						}
					}
				} 
				menu.getChildren().add(m);
			} 
		}
		return menu;
	}
	public String treeOrg() throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		List<BaseMenu> parentList=new ArrayList<BaseMenu>();
		List<BaseMenu> baseMenu_list=baseMenuService.execSql("select * from Base_menu  order by menu_order");
		baseOrganizeMenu_list=baseOrganizeMenuService.execSql("select * from base_organize_menu where ORGANIZE_ID='"+OrganizeId+"'");
		for(int i=0;i<baseMenu_list.size();i++){
			BaseMenu m = (BaseMenu) baseMenu_list.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid());
			if(m.getMenuParentid().equals("1")){
    			m = getChildOrg(baseMenu_list, m.getFuid(), m);
    			parentList.add(m);
    		}
    	}
		String strs = TransformJSON.toJSON(parentList);
		out.print(strs);
		return null;
	}
	public BaseMenu getChildOrg(List<?> list,String id,BaseMenu menu){
		for(int i=0;i<list.size();i++){
			BaseMenu m = (BaseMenu) list.get(i);
			if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
				m = getChildOrg(list, m.getFuid(), m);
				int g=this.hasParent(m.getFuid(), baseMenu_all);
				if(g==0){
					List<BaseOperating> Op_lst=baseOperatingService.execSql("select * from base_operating where  deletemark=0");
					for(int d=0;d<Op_lst.size();d++){
						String code=Op_lst.get(d).getCode();
						String op_menuid=Op_lst.get(d).getMenuid();
						//判断操作是否该菜单下面 包含基础操作
						if((code!=null&&code.startsWith("base"))||(op_menuid!=null&&op_menuid.equals(m.getFuid()))){
							Op_lst.get(d).setText(Op_lst.get(d).getFullname());
							Op_lst.get(d).setId(Op_lst.get(d).getFuid());
							Op_lst.get(d).setAttributes(Op_lst.get(d).getCode());
							Op_lst.get(d).setChecked(false);
							String opid=Op_lst.get(d).getFuid();
							if(baseOrganizeMenu_list!=null){
								for(int f=0;f<baseOrganizeMenu_list.size();f++){
									String menu_id=baseOrganizeMenu_list.get(f).getMenuId();
									String operating_id=baseOrganizeMenu_list.get(f).getOperationid();
									//判断该菜单是否有该操作
									if(menu_id!=null&&menu_id.equals(m.getFuid())&&operating_id!=null&&operating_id.equals(opid)){
										Op_lst.get(d).setChecked(true);
									}
								}
							}
							m.getChildren().add(Op_lst.get(d));
						}
						
					}
				}
				menu.getChildren().add(m);
			}
		}
		return menu;
	}
	//获取菜单的子菜单
	public BaseMenu getChildMenu(List<?> list,String id,BaseMenu menu){
    	for(int i=0;i<list.size();i++){
    		BaseMenu m = (BaseMenu) list.get(i);
    		if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
    			m = getChildOrg(list, m.getFuid(), m);
    			menu.getChildren().add(m);
    		}
    	}
    	return menu;
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
	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}
	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}
	public List<BaseMenu> getBaseMenu_list() {
		return baseMenu_list;
	}
	public void setBaseMenu_list(List<BaseMenu> baseMenu_list) {
		this.baseMenu_list = baseMenu_list;
	}
	public BaseMenu getBaseMenu() {
		return baseMenu;
	}
	public void setBaseMenu(BaseMenu baseMenu) {
		this.baseMenu = baseMenu;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public List<BaseMenu> getParentList() {
		return parentList;
	}
	public void setParentList(List<BaseMenu> parentList) {
		this.parentList = parentList;
	}
	public BaseMenuRoleService getBaseMenuRoleService() {
		return baseMenuRoleService;
	}
	public void setBaseMenuRoleService(BaseMenuRoleService baseMenuRoleService) {
		this.baseMenuRoleService = baseMenuRoleService;
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
	public BaseOrganizeMenuService getBaseOrganizeMenuService() {
		return baseOrganizeMenuService;
	}
	public void setBaseOrganizeMenuService(BaseOrganizeMenuService baseOrganizeMenuService) {
		this.baseOrganizeMenuService = baseOrganizeMenuService;
	}
	public Map<String, List<BaseMenu>> getBaseMenu_map() {
		return baseMenu_map;
	}
	public void setBaseMenu_map(Map<String, List<BaseMenu>> baseMenu_map) {
		this.baseMenu_map = baseMenu_map;
	}
	public BaseOperatingService getBaseOperatingService() {
		return baseOperatingService;
	}
	public void setBaseOperatingService(BaseOperatingService baseOperatingService) {
		this.baseOperatingService = baseOperatingService;
	}
	public List<BaseMenu> getBaseMenu_all() {
		return baseMenu_all;
	}
	public void setBaseMenu_all(List<BaseMenu> baseMenu_all) {
		this.baseMenu_all = baseMenu_all;
	}
	public List<BaseOrganizeMenu> getBaseOrganizeMenu_list() {
		return baseOrganizeMenu_list;
	}
	public void setBaseOrganizeMenu_list(List<BaseOrganizeMenu> baseOrganizeMenu_list) {
		this.baseOrganizeMenu_list = baseOrganizeMenu_list;
	}
	public List<BaseOperating> getOp_lst() {
		return Op_lst;
	}
	public void setOp_lst(List<BaseOperating> op_lst) {
		Op_lst = op_lst;
	}
	public List<BaseMenuRole> getMenuRole_list() {
		return MenuRole_list;
	}
	public void setMenuRole_list(List<BaseMenuRole> menuRole_list) {
		MenuRole_list = menuRole_list;
	}
	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}
	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
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
