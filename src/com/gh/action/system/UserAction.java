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
import com.gh.entity.BaseOrganize;
import com.gh.entity.BaseUser;
import com.gh.entity.BaseUserRole;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseOperationLogService;
import com.gh.service.BaseOrganizeService;
import com.gh.service.BaseUserRoleService;
import com.gh.service.BaseUserService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class UserAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseOperationLogService baseOperationLogService;
	private BaseUserService baseUserService;
	private BaseOrganizeService baseOrganizeService;
	private BaseUserRoleService baseUserRoleService;
	private BaseMenuService baseMenuService;
	private String OrganizeId;
	private String id;
	private String userid;
	private String type;
	private String roleid;
	private String skey;
	private String username;
	private String password;
	private String error;
	private List<BaseUser> baseUser_list;
	private List<BaseMenu> parentList;
	private BaseUser baseUser;
	
	public String show(){
		return "user";
	}
	
	public String Usershow(){
	    String SqlWhere=" DEPARTMENTID='"+OrganizeId+"' and DELETEMARK=0";
	    if(skey!=null&&skey.length()>0){
	    	SqlWhere+=" and USERNAME like '%"+skey+"%'";
	    } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseUserService.getRecordCount(SqlWhere);
	    baseUser_list=baseUserService.selectByPage(indexPage,pageSize, SqlWhere, "USERNAME,fuid");
	    if(baseUser_list!=null&&baseUser_list.size()>0){
	    	for(int a=0;a<baseUser_list.size();a++){
	    		List<BaseUserRole> baseUserRole_list= baseUserRoleService.selectView("select ur.fuid,ur.userid,ur.roleid,ur.modifydate,r.realname from base_user_role  ur,base_role r where r.fuid=ur.roleid and ur.userid='"+baseUser_list.get(a).getFuid()+"'");
	    		if(baseUserRole_list!=null&&baseUserRole_list.size()>0){
	    			String rolename="";
	    			for(int b=0;b<baseUserRole_list.size();b++){
	    				rolename+=baseUserRole_list.get(b).getRealname()+";";
	    			}
	    			baseUser_list.get(a).setRoleid(rolename);
	    		}
	    	}
	    }
		 return "userinfo";
	}
	public String index(){
		String userid=(String)request.getSession().getAttribute("userid");
		List<BaseMenu> list=baseMenuService.execSql("select * from base_menu m inner join  base_menu_role mr on mr.menu_id=m.fuid inner join base_user_role ur on ur.roleid=mr.role_id and ur.userid='"+
				userid+"' and mr.operating_id=(select fuid from base_operating where code='base_show') order by m.menu_order");
		List<BaseMenu> baseMenu_all=baseMenuService.execSql("select * from Base_menu order by menu_order");
		for(int i=0;i<list.size();i++){
			BaseMenu n=hasParent(baseMenu_all,list.get(i).getMenuParentid());
			if(n!=null){
				if(!isexit(n.getFuid(),list)){
					list.add(n);
				}
			}
		}
		parentList=new ArrayList<BaseMenu>();
		for(int i=0;i<baseMenu_all.size();i++){
			BaseMenu m = (BaseMenu) baseMenu_all.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid());
    		if(m.getMenuParentid().equals("1")){
    			m = getChildOrg(baseMenu_all, m.getFuid(), m,list);
    			parentList.add(m);
    		}
    	}
		String in="";
		if(parentList!=null){
			for(int a=0;a<parentList.size();a++){
				if(parentList.get(a).getChildren()==null||parentList.get(a).getChildren().size()==0){
					in+=""+a+",";
				}
			}
		}
		in=in.length()>0?in.substring(0,in.length()-1):in;
		String temp[]=in.split(",");
		int b=0;
		if(temp!=null&&temp.length>0&&in.length()>0){
			for(int a=0;a<temp.length;a++){
				parentList.remove(Integer.parseInt(temp[a])-b);
				b++;
			}
		}
		if("2".equals(type)){
			return "mainY";
		}else{
			return "main";
		}
	}
	/***********************修改密码*********************************/
	/**
	 * 修改密码 UI
	 */
	public String UpdateShowPass(){
		return "PasswordUpdate";
	}
	
	/**
	 * @see 原始密码验证
	 * @return
	 * @throws IOException
	 */
	
	public String  PasswordRepeat() throws IOException{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String userid=(String)request.getSession().getAttribute("userid");
		if (userid != null) {
			BaseUser baseUser =baseUserService.findById(userid);
			if (baseUser.getUserpassword().trim().equals(password.trim())) {
				out.print("false");
			} else {
				out.print("true");
			}
		}
		return null;
	}
	
	
	/**
	 * @see 修改密码
	 * @return
	 */
	
	public String  UpdatePass() throws IOException{
		String userid=(String)request.getSession().getAttribute("userid");
		if (userid != null) {
			BaseUser baseUser =baseUserService.findById(userid);
			baseUser.setUserpassword(password);
			baseUserService.update(baseUser);
		}
		return "passLogout";
	}
	
	
	/********************************************************/
	
	
	/**
	 * @see 获取二级菜单
	 * @author xiao
	 * @return
	 */
	public String getChild(){
		String userid=(String)request.getSession().getAttribute("userid");
		List<BaseMenu> list=baseMenuService.execSql("select * from base_menu m inner join  base_menu_role mr on mr.menu_id=m.fuid inner join base_user_role ur on ur.roleid=mr.role_id and ur.userid='"+
				userid+"' and mr.operating_id=(select fuid from base_operating where code='base_show') order by m.menu_order");
		List<BaseMenu> baseMenu_all=baseMenuService.execSql("select * from Base_Menu order by menu_order");
		parentList=new ArrayList<BaseMenu>();
		for(int i=0;i<list.size();i++){
			BaseMenu m = (BaseMenu) list.get(i);
			m.setText(m.getMenuName());
			m.setId(m.getFuid()); 
    		if(m.getMenuParentid().equals(id)){
    			m = getChildOrg(baseMenu_all, m.getFuid(), m,list);
    			parentList.add(m);
    		}
    	}
		return "left";
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
	public BaseMenu getChildOrg(List<?> list,String id,BaseMenu menu,List<BaseMenu> mlist){
    	for(int i=0;i<list.size();i++){
    		BaseMenu m = (BaseMenu) list.get(i);
    		if(!m.getMenuParentid().equals("1")&&m.getMenuParentid().equals(id)){
    			m = getChildOrg(list, m.getFuid(), m,mlist);
    			if(mlist!=null){
    				for(int a=0;a<mlist.size();a++){
    					if(m.getFuid().equals(mlist.get(a).getFuid())){
    						menu.getChildren().add(m);
    					}
    				}
    			}
    		}
    	}
    	return menu;
    }
	public String add(){
		baseUser.setFuid(UUIDCreater.getUUID());
		baseUserService.save(baseUser);
		OperationLogAction log=new OperationLogAction();
		log.edit("新增用户"+baseUser.getUsername());
		return "show";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String DepartmentName="";
		if(OrganizeId!=null){
			BaseOrganize  baseOrganize =baseOrganizeService.findById(OrganizeId);
			if(baseOrganize!=null){
				DepartmentName=baseOrganize.getFullname();
			}
		}
		String id=baseUser.getFuid();
		if(id!=null&&id.length()>0){
			baseUser.setModifydate(new Date());
			baseUser.setModifyuserid(userid);
			baseUser.setModifyuserrealname(username);
			baseUserService.updateSelective(baseUser);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("修改用户"+baseUser.getUsername());
		}else{
			baseUser.setFuid(UUIDCreater.getUUID());
			baseUser.setDeletemark(0);
			baseUser.setCreatedate(new Date());
			baseUser.setCreateuserid(userid);
			baseUser.setDepartmentid(OrganizeId);
			baseUser.setDepartmentname(DepartmentName);
			baseUser.setCreateuserrealname(username);
			baseUser.setModifydate(new Date());
			baseUser.setModifyuserid(userid);
			baseUser.setModifyuserrealname(username);
			baseUserService.save(baseUser);
			/*
			 * 记录操作日志
			 */
			baseOperationLogService.saveLog("新增用户"+baseUser.getUsername());
		}
		return "show";
	}
	public String update_show(){
		baseUser=baseUserService.findById(id);
		return "usereditor";
	}
	public String delete(){
		baseUser=baseUserService.findById(id);
		baseUser.setDeletemark(1);
		baseUserService.update(baseUser);
		/*
		 * 记录操作日志
		 */
		baseOperationLogService.saveLog("删除用户"+baseUser.getUsername());
		return "show";
	}
	
	public String IsExist() throws IOException{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int a =baseUserService.getRecordCount(" USERNAME='"+username+"' and DELETEMARK=0");
		if (a>0) {
			out.print("true");
		} else {
			out.print("false");
		}
		return null;
	}
	//用户分配角色
	public String AssignRoles(){
		String username1=(String)request.getSession().getAttribute("username");
		String userid1=(String)request.getSession().getAttribute("userid");
		if(userid!=null&&userid.length()>0){
			String fuid[]=userid.split(",");
			if(fuid!=null&&fuid.length>0){
				for(int a=0;a<fuid.length;a++){
					//清空原先角色
					List<BaseUserRole> BaseUserRole_list=baseUserRoleService.execSql("select * from base_user_role where userid='"+fuid[a]+"'");
					if(BaseUserRole_list!=null&&BaseUserRole_list.size()>0){
						for(int i=0;i<BaseUserRole_list.size();i++){
							baseUserRoleService.delete(BaseUserRole_list.get(i).getFuid());
						}
					}
					//赋予新角色
					if(roleid!=null&&roleid.length()>0){
						String uid[]=roleid.split(",");
						if(uid!=null&&uid.length>0){ 
							for(int b=0;b<uid.length;b++){
								BaseUserRole baseUserRole =new BaseUserRole();
								baseUserRole.setFuid(UUIDCreater.getUUID());
								baseUserRole.setCreatedate(new Date());
								baseUserRole.setCreateuserid(userid1);
								baseUserRole.setCreateuserrealname(username1);
								baseUserRole.setModifydate(new Date());
								baseUserRole.setModifyuserid(userid1);
								baseUserRole.setModifyuserrealname(username1);
								baseUserRole.setUserid(fuid[a]);
								baseUserRole.setRoleid(uid[b]);
								baseUserRoleService.save(baseUserRole);
								/*
								 * 记录操作日志
								 */
								baseUser=baseUserService.findById(fuid[a]);
								baseOperationLogService.saveLog("为用户"+baseUser.getUsername()+"分配角色");
							}
						}
					}
				}
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
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}
	public List<BaseUser> getBaseUser_list() {
		return baseUser_list;
	}
	public void setBaseUser_list(List<BaseUser> baseUser_list) {
		this.baseUser_list = baseUser_list;
	}
	public BaseUser getBaseUser() {
		return baseUser;
	}
	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}
	public String getOrganizeId() {
		return OrganizeId;
	}
	public void setOrganizeId(String organizeId) {
		OrganizeId = organizeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public BaseUserRoleService getBaseUserRoleService() {
		return baseUserRoleService;
	}
	public void setBaseUserRoleService(BaseUserRoleService baseUserRoleService) {
		this.baseUserRoleService = baseUserRoleService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}

	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}

	public List<BaseMenu> getParentList() {
		return parentList;
	}

	public void setParentList(List<BaseMenu> parentList) {
		this.parentList = parentList;
	}

	public BaseOrganizeService getBaseOrganizeService() {
		return baseOrganizeService;
	}

	public void setBaseOrganizeService(BaseOrganizeService baseOrganizeService) {
		this.baseOrganizeService = baseOrganizeService;
	}

	public BaseOperationLogService getBaseOperationLogService() {
		return baseOperationLogService;
	}

	public void setBaseOperationLogService(BaseOperationLogService baseOperationLogService) {
		this.baseOperationLogService = baseOperationLogService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
