package com.gh.action.system;

import java.util.Date;
import java.util.List;

import com.gh.base.Action;
import com.gh.entity.BaseUser;
import com.gh.entity.BaseUserRole;
import com.gh.entity.ComData;
import com.gh.interceptor.SqlAbnormalFilter;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseUserRoleService;
import com.gh.service.BaseUserService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class LoginAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseUserService baseUserService;
	private BaseUserRoleService baseUserRoleService;
	private BaseMenuService baseMenuService;
	private String username;
	private String password;
	private String type;
	private String error;
	private List<BaseUser> baseUser_list;
	
	public String show(){
		return "user";
	}
	
	public String login(){
		if(SqlAbnormalFilter.sqlValidate(username)||SqlAbnormalFilter.sqlValidate(password)){
			error="用户名或密码错误";
			return "login";
		}
		if(username!=null&&password!=null){
			baseUser_list=baseUserService.execSql("select * from  base_user where username='"+username.trim()+"' and USERPASSWORD ='"+password.trim()+"' and DELETEMARK=0");
			if(baseUser_list!=null&&baseUser_list.size()>0){
				String username=baseUser_list.get(0).getUsername();
				String userid=baseUser_list.get(0).getFuid();
				String departmentid=baseUser_list.get(0).getDepartmentid();
				String departmentname=baseUser_list.get(0).getDepartmentname();
				String superAdmin=baseUser_list.get(0).getCode();
				request.getSession().setMaxInactiveInterval(3600*4);
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("userid",userid);
				request.getSession().setAttribute("departmentid",departmentid);
				request.getSession().setAttribute("departmentname",departmentname);
				request.getSession().setAttribute("superAdmin",superAdmin);
				String roleid="";
				List<BaseUserRole> baseUserRole_list=baseUserRoleService.execSql("select * from base_user_role where userid='"+userid+"'");
				if(baseUserRole_list!=null){
					for(int a=0;a<baseUserRole_list.size();a++){
						roleid+=baseUserRole_list.get(a).getRoleid()+",";
					}
				}
				roleid=roleid.length()>0?roleid.substring(0,roleid.length()-1):"";
				request.getSession().setAttribute("roleid",roleid);
				//更新最后登录时间
				baseUser_list.get(0).setPreviousvisit(baseUser_list.get(0).getLastvisit());
				baseUser_list.get(0).setLastvisit(new Date());
				baseUserService.updateSelective(baseUser_list.get(0));
				request.getSession().setAttribute("previousvisit",baseUser_list.get(0).getPreviousvisit());
				//----------------
				//4：虚拟游  3：二维码识别系统 2：三维应用系统 1：综合管理平台
				if("2".equals(type)){
					return "indexY";
				}else{
					return "index";
				}
				
			}else{
				error="用户名或密码错误";
				return "login";
			}
		}else{
			error="请输入用户名或密码";
			return "logins";
		}
	}
	
	 public String logout(){
	    	getHttpSession().invalidate();
	    	return "logins";
	    }

	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<BaseUser> getBaseUser_list() {
		return baseUser_list;
	}

	public void setBaseUser_list(List<BaseUser> baseUser_list) {
		this.baseUser_list = baseUser_list;
	}

	public BaseMenuService getBaseMenuService() {
		return baseMenuService;
	}

	public void setBaseMenuService(BaseMenuService baseMenuService) {
		this.baseMenuService = baseMenuService;
	}

	public BaseUserRoleService getBaseUserRoleService() {
		return baseUserRoleService;
	}

	public void setBaseUserRoleService(BaseUserRoleService baseUserRoleService) {
		this.baseUserRoleService = baseUserRoleService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	 
}
